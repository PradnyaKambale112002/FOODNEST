package com.foodnest.foodnest.dto;

public class DashboardDTO {

    private long totalUsers;
    private long totalOrders;
    private long totalMenuItems;
    private double totalRevenue;

    public DashboardDTO() {
    }

    public DashboardDTO(long totalUsers, long totalOrders,
                        long totalMenuItems, double totalRevenue) {
        this.totalUsers = totalUsers;
        this.totalOrders = totalOrders;
        this.totalMenuItems = totalMenuItems;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public long getTotalMenuItems() {
        return totalMenuItems;
    }

    public void setTotalMenuItems(long totalMenuItems) {
        this.totalMenuItems = totalMenuItems;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}