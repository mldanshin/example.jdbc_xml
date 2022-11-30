package com.example.testing.services;

import com.example.testing.models.TestDto;
import com.example.testing.repositories.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    private final TestRepository repository;

    public TestService(TestRepository repository) {
        this.repository = repository;
    }

    public void add(int size) {
        repository.add(size);
    }

    public List<TestDto> getAll() {
        return repository.getAll();
    }

    public int sum() {
        return repository.sum();
    }
}
