package ru.hogwarts.school.dto;

import java.util.Objects;

public class FacultyDto {
    private String name;
    private String color;

    public FacultyDto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public FacultyDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyDto that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getColor(), that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColor());
    }

    @Override
    public String toString() {
        return "FacultyDto{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
