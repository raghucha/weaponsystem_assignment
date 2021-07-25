package com.weponsystem.weaponsystem.model;

import java.util.List;

public class WeaponSummary {

    private String weapon;
    private List<Command> commands;
    private Double totalQuantity;
    private Double averageRate;

    public WeaponSummary() {
    }

    public WeaponSummary(String weapon, List<Command> commands) {
        this.weapon = weapon;
        this.commands = commands;
        this.totalQuantity = commands.stream().mapToDouble(Command::getQuantity).sum();
        this.averageRate = commands.stream().mapToDouble(Command::getRate).sum() / commands.size();
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
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



