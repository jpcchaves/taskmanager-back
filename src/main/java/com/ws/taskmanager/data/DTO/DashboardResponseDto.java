package com.ws.taskmanager.data.DTO;

public class DashboardResponseDto {
    private DashboardDto dashboard;

    public DashboardResponseDto() {
    }

    public DashboardResponseDto(DashboardDto dashboard) {
        this.dashboard = dashboard;
    }

    public DashboardDto getDashboard() {
        return dashboard;
    }

    public void setDashboard(DashboardDto dashboard) {
        this.dashboard = dashboard;
    }
}
