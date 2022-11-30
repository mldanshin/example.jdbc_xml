package com.example.testing.services;

import com.example.testing.models.TestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class TestServicesTest {
    @Autowired
    private TestService services;

    @Test
    void add() {
        List<TestDto> expectedList = new ArrayList<>(
                Arrays.asList(
                        new TestDto(1),
                        new TestDto(2),
                        new TestDto(3)
                )
        );

        services.add(3);
        Assertions.assertEquals(3, services.getAll().size());
        Assertions.assertEquals(expectedList, services.getAll());

        expectedList.add(new TestDto(1));
        services.add(1);
        Assertions.assertEquals(4, services.getAll().size());
        Assertions.assertEquals(expectedList, services.getAll());

        services.add(-1);
        Assertions.assertEquals(4, services.getAll().size());
        Assertions.assertEquals(expectedList, services.getAll());

        services.add(0);
        Assertions.assertEquals(4, services.getAll().size());
        Assertions.assertEquals(expectedList, services.getAll());

        expectedList.add(new TestDto(1));
        expectedList.add(new TestDto(2));
        expectedList.add(new TestDto(3));
        expectedList.add(new TestDto(4));
        expectedList.add(new TestDto(5));
        services.add(5);
        Assertions.assertEquals(9, services.getAll().size());
        Assertions.assertEquals(expectedList, services.getAll());
    }

    @Test
    @Sql("classpath:data_test.sql")
    void getAll() {
        List<TestDto> actualList = services.getAll();
        List<TestDto> expectedList = Arrays.asList(
                new TestDto(12),
                new TestDto(14),
                new TestDto(23),
                new TestDto(22),
                new TestDto(512)
        );

        Assertions.assertEquals(5, actualList.size());
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @Sql("classpath:data_test.sql")
    void sum() {
        Assertions.assertEquals(583, services.sum());
    }
}