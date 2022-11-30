package com.example.testing.converters.xml;

import com.example.testing.models.TestDto;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "entries")
class ListTestModelConverter {
    private List<TestModelConverter> tests = new ArrayList<>();

    @XmlElement(name = "entry")
    public List<TestModelConverter> getTests() {
        return tests;
    }

    public void addAll(List<TestDto> dto) {
        dto.stream().forEach(
                (item) -> tests.add(new TestModelConverter(item.getField()))
        );
    }
}
