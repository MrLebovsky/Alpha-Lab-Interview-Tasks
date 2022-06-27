package com.alpha.lab.interview;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Objects;

import static com.alpha.lab.interview.MathUtils.isPrime;
import static java.lang.Math.pow;

public class PrimeNumberService {
    public void a() throws IOException {
        try (FileWriter file = new FileWriter("Result.txt")) {
            for (int i = 0; i < pow(10, 6); i++) {
                if (isPrime(i)) {
                    WeakReference<String> currentNumber
                            = new WeakReference<>(String.valueOf(i).concat(" "));
                    file.write(Objects.requireNonNull(currentNumber.get()));
                }
            }
        }
    }
}
