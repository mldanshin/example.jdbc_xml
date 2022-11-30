package com.example.testing.converters.xml;


import jakarta.xml.bind.annotation.XmlElement;

class TestModelConverter {

    private int field;

    public TestModelConverter() {
    }

    public TestModelConverter(int field) {
        this.field = field;
    }

    @XmlElement
    public int getField() {
        return field;
    }
}
