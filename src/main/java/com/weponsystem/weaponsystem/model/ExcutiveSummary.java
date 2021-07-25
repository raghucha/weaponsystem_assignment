package com.weponsystem.weaponsystem.model;

public class ExcutiveSummary {
    private double totalCommands;
    private double quantity;
    private Double averageRate;
    private Integer numberOfAttacks;

    public ExcutiveSummary() {
    }

    public ExcutiveSummary(double totalCommands, double quantity, Double averageRate,Integer numberOfAttacks) {
        this.totalCommands = totalCommands;
        this.quantity = quantity;
        this.averageRate = averageRate;
        this.numberOfAttacks = numberOfAttacks;
    }

    public double getTotalCommands() {
        return totalCommands;
    }

    public void setTotalCommands(double totalCommands) {
        this.totalCommands = totalCommands;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks(Integer numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }
}
