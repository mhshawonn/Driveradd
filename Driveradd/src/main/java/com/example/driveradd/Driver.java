package com.example.driveradd;

import java.io.Serializable;

public class Driver implements Serializable {
    private String name;
    private String  id;
    private int age;
    private double salary;
    private String route;

    public Driver(String name, String id, int age, double salary, String route) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.salary = salary;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
