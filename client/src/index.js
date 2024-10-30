import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import ProjectForm from "./components/ProjectCreation/ProjectForm";
import TechStackList from "./components/ProjectCreation/TechStackList";
import reportWebVitals from "./reportWebVitals";
import TechStackSelector from "./components/ProjectCreation/TechStackSelector";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <ProjectForm />
    {/* <TechStackSelector /> */}
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
