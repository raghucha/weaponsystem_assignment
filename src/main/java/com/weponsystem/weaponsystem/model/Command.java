package com.weponsystem.weaponsystem.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Command {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;

    @NotBlank(message = "weapon system is mandatory ")
    private String weaponSystem;

    @NotBlank(message = "Battle Ship  is mandatory ")
    private String battleship;

    @NotBlank(message = "Target  is mandatory ")
    private String target;

    @PositiveOrZero
    private Double quantity;

    @PositiveOrZero
    private Double rate;


    public Command() {
    }

    public Command(String weaponSystem, String battleship, String target, Double quantity, Double rate) {
        this.id = count.incrementAndGet();
        this.weaponSystem = weaponSystem;
        this.battleship = battleship;
        this.target = target;
        this.quantity = quantity;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }


    public String getWeaponSystem() {
        return weaponSystem;
    }

    public void setWeaponSystem(String weaponSystem) {
        this.weaponSystem = weaponSystem;
    }

    public String getBattleship() {
        return battleship;
    }

    public void setBattleship(String battleship) {
        this.battleship = battleship;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        Command command = (Command) o;
        return getWeaponSystem().equalsIgnoreCase(command.getWeaponSystem()) && getBattleship().equalsIgnoreCase(command.getBattleship()) && getTarget().equalsIgnoreCase(command.getTarget());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeaponSystem(), getBattleship(), getTarget());
    }
}
