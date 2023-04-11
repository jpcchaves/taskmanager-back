package com.ws.taskmanager.data.DTO;

public class DashboardDto {
    private Integer totalTasksAmount;
    private Integer totalTasksConcluded;
    private Integer totalTasksNotConcluded;
    private String concludedPercentage;
    private String notConcludedPercentage;

    public DashboardDto() {
    }

    public DashboardDto(Integer totalTasksAmount,
                        Integer totalTasksConcluded,
                        Integer totalTasksNotConcluded,
                        String concludedPercentage,
                        String notConcludedPercentage) {
        this.totalTasksAmount = totalTasksAmount;
        this.totalTasksConcluded = totalTasksConcluded;
        this.totalTasksNotConcluded = totalTasksNotConcluded;
        this.concludedPercentage = concludedPercentage;
        this.notConcludedPercentage = notConcludedPercentage;
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

    public String getConcludedPercentage() {
        return concludedPercentage;
    }

    public void setConcludedPercentage(String concludedPercentage) {
        this.concludedPercentage = concludedPercentage;
    }

    public String getNotConcludedPercentage() {
        return notConcludedPercentage;
    }

    public void setNotConcludedPercentage(String notConcludedPercentage) {
        this.notConcludedPercentage = notConcludedPercentage;
    }
}
