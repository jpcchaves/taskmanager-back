package com.ws.taskmanager.data.DTO;

public class DashboardDto {
    private Integer totalTasksAmount;
    private Integer totalTasksConcluded;
    private Integer totalTasksNotConcluded;

    public DashboardDto() {
    }

    public DashboardDto(Integer totalTasksAmount,
                        Integer totalTasksConcluded,
                        Integer totalTasksNotConcluded) {
        this.totalTasksAmount = totalTasksAmount;
        this.totalTasksConcluded = totalTasksConcluded;
        this.totalTasksNotConcluded = totalTasksNotConcluded;
    }

    public Integer getTotalTasksAmount() {
        return totalTasksAmount;
    }

    public void setTotalTasksAmount(Integer totalTasksAmount) {
        this.totalTasksAmount = totalTasksAmount;
    }

    public Integer getTotalTasksConcluded() {
        return totalTasksConcluded;
    }

    public void setTotalTasksConcluded(Integer totalTasksConcluded) {
        this.totalTasksConcluded = totalTasksConcluded;
    }

    public Integer getTotalTasksNotConcluded() {
        return totalTasksNotConcluded;
    }

    public void setTotalTasksNotConcluded(Integer totalTasksNotConcluded) {
        this.totalTasksNotConcluded = totalTasksNotConcluded;
    }
}
