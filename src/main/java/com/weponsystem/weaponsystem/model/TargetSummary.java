package com.weponsystem.weaponsystem.model;

import java.util.List;

public class TargetSummary {
    private String target;
    private List<Command> commands;
    private Double totalQuantity;
    private Double averageRate;

    public TargetSummary() {
    }

    public TargetSummary(String target, List<Command> commands) {
        this.target = target;
        this.commands = commands;
        this.totalQuantity = commands.stream().mapToDouble(Command::getQuantity).sum();
        this.averageRate = commands.stream().mapToDouble(Command::getRate).sum() / commands.size();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public Double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }
}
