package com.git.projecttracker;

/**
 * Created by Anurag on 4/3/21.
 */
class ProjectModel {
    private String projectName, projectDesc, projectdate;

    public ProjectModel(String projectName, String projectDesc, String projectdate) {
        this.projectName = projectName;
        this.projectDesc = projectDesc;
        this.projectdate = projectdate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectdate() {
        return projectdate;
    }

    public void setProjectdate(String projectdate) {
        this.projectdate = projectdate;
    }
}
