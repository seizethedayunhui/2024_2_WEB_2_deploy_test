package wap.web2.server.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wap.web2.server.domain.Project;
import wap.web2.server.payload.request.ProjectCreateRequest;
import wap.web2.server.repository.ProjectRepository;
import wap.web2.server.util.AwsUtils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AwsUtils awsUtils;

    @Transactional
    public void save(ProjectCreateRequest request) throws IOException {
        List<String> imageUrls = awsUtils.uploadImagesToS3(request.getImageS3());
        String thumbnailUrl = awsUtils.uploadImageToS3(request.getThumbnailS3());

        // request.toEntity() 를 호출함으로서 매개변수로 넘어온 객체(request)를 사용
        Project project = request.toEntity(request, imageUrls, thumbnailUrl);

        // 양방향 연관관계 데이터 일관성 유지
        project.getTechStacks().forEach(techStack -> techStack.updateTechStack(project));
        project.getTeamMembers().forEach(teamMember -> teamMember.updateTeamMember(project));
        project.getImages().forEach(image -> image.updateImage(project));

        projectRepository.save(project);
    }

}