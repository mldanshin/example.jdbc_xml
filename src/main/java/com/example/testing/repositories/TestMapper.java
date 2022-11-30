package com.example.testing.repositories;

import com.example.testing.models.TestDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestMapper implements RowMapper<TestDto> {
    @Override
    public TestDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TestDto(
                rs.getInt("field")
        );
    }
}
