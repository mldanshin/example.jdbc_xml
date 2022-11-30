package com.example.testing.models;


import java.util.Objects;

public class TestDto {

    private int field;

    public TestDto(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDto testDto = (TestDto) o;
        return field == testDto.field;
    }

    @Override
    public int hashCode() {
        return Objects.hash(field);
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "field=" + field +
                '}';
    }
}
