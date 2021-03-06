package com.alpha.lab.interview;


import com.alpha.lab.interview.algorithms.PrimeNumberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootApplication
public class AlphaLabTask3Application implements CommandLineRunner {

    private final PrimeNumberService primeNumberService;

    public AlphaLabTask3Application(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AlphaLabTask3Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        primeNumberService.sequentialAlgorithm();

        primeNumberService.parallelAlgorithm();

        String result = Arrays.equals(Files.readAllBytes(Paths.get("result.txt")),
                Files.readAllBytes(Paths.get("result2.txt")))
                ? "Полученные файлы полностью идентичны" : "Увы, алгоритм не верен";
        System.out.println(result);
    }
}
