package org.example.inserts;

public class ProjectWorkerInsert {
    private int projectId;
    private int workerId;

    public ProjectWorkerInsert(int projectId, int workerId) {
        this.projectId = projectId;
        this.workerId = workerId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getWorkerId() {
        return workerId;
    }
}
