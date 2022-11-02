package org.example;

import java.io.File;

public class extension_select {
    private File file;
    String extension;
    File[] listFiles = file.listFiles((d, s) -> {
        return s.toLowerCase().endsWith(extension);
    });


}
