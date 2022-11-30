package com.example.testing.repositories;

import com.example.testing.models.TestDto;

import java.util.List;

public interface TestRepository {
    void add(int size);

    List<TestDto> getAll();

    int sum();
}
