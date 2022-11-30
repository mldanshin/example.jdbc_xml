package com.example.testing.converters.xml;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public abstract class ModifierXslt {
    private ResourceLoader resourceLoader;

    public ModifierXslt() {
        resourceLoader = new FileSystemResourceLoader();
    }

    public Reader getReader() throws IOException {
        return new FileReader(resourceLoader.getResource(path()).getFile());
    }

    protected abstract String path();
}
