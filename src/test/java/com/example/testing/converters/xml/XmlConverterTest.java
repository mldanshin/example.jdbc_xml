package com.example.testing.converters.xml;

import com.example.testing.models.TestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.Diff;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class XmlConverterTest {
    @Autowired
    private XmlConverter converter;

    @Test
    void convertToXml() {
        String actual = converter.convertToXml(new ArrayList<>(
                Arrays.asList(
                        new TestDto(1),
                        new TestDto(2),
                        new TestDto(3)
                )
        ));

        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <entries>
                    <entry>
                        <field>1</field>
                    </entry>
                    <entry>
                        <field>2</field>
                    </entry>
                    <entry>
                        <field>3</field>
                    </entry>
                </entries>
                """;

        Diff diff = DiffBuilder.compare(expected).withTest(actual).checkForSimilar().build();

        Assertions.assertFalse(diff.hasDifferences());
    }

    @Test
    void convertToXmlWithAModifierXslt() {
        String actual = converter.convertToXml(
                new ArrayList<>(
                        Arrays.asList(
                                new TestDto(1),
                                new TestDto(2),
                                new TestDto(3)
                        )),
                new AModifierXslt());

        String expected = """
                <?xml version="1.0" encoding="UTF-8"?><entries>
                    <entry field="1"/>
                    <entry field="2"/>
                    <entry field="3"/>
                </entries>
                """;

        Diff diff = DiffBuilder.compare(expected).withTest(actual).checkForSimilar().build();

        Assertions.assertFalse(diff.hasDifferences());
    }
}