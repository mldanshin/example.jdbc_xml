package com.example.testing.repositories;

import com.example.testing.models.TestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TestJdbcRepositoryImpl implements TestRepository {
    private final JdbcTemplate jdbcTemplate;

    public TestJdbcRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(int size) {
        for (int i = 1; i <= size; i++) {
            jdbcTemplate.update("INSERT INTO test (field) values (?);", i);
        }
    }

    @Override
    public List<TestDto> getAll() {
        return jdbcTemplate.query("SELECT * FROM test;", new TestMapper());
    }

    @Override
    public int sum() {
        return jdbcTemplate.queryForObject("SELECT sum(field) FROM test;", Integer.class);
    }
}
