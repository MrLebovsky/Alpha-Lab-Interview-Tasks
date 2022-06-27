package com.alpha.lab.interview.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;

public class FileWriterUtils {
    private FileWriterUtils() {

    }

    public static void writeToFile(FileWriter file, int i) {
        WeakReference<String> currentNumber = new WeakReference<>(String.valueOf(i).concat(" "));
        try {
            file.write(Objects.requireNonNull(currentNumber.get()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
