package com.alpha.lab.interview.algorithms;

import com.alpha.lab.interview.benchmark.Benchmark;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

import static com.alpha.lab.interview.utils.FileWriterUtils.writeToFile;
import static com.alpha.lab.interview.utils.MathUtils.isPrime;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class PrimeNumberService {

    private static final int START_VALUE = 0;
    private static final int FINISH_VALUE = 1000000;

    private static final int THREADS_COUNT = 4;

    private void streamForEach(FileWriter file) {
        IntStream.range(START_VALUE, FINISH_VALUE)
                .parallel()
                .forEach(i -> {
                    if (isPrime(i))
                        writeToFile(file, i);
                });
    }

    @Benchmark
    public void sequentialAlgorithm() throws IOException {
        try (FileWriter file = new FileWriter("result.txt")) {
            for (int i = START_VALUE; i < FINISH_VALUE; i++) {
                if (isPrime(i))
                    writeToFile(file, i);
            }
        }
    }

    @Benchmark
    public void defaultPoolParallelStreamAlgorithm() throws IOException {
        try (FileWriter file = new FileWriter("result2.txt")) {
            streamForEach(file);
        }
    }

    @Benchmark
    public void parallelStreamAlgorithm() throws IOException {
        try (FileWriter file = new FileWriter("result2.txt")) {
            new ForkJoinPool(THREADS_COUNT).submit(() -> streamForEach(file));
        }
    }

    @Benchmark
    public void parallelAlgorithm() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
        int count = FINISH_VALUE / THREADS_COUNT;
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int number = i;
            executor.execute(() -> {
                String threadResult = "";
                try {
                    doThreadWork(number, count, threadResult);
                    Files.writeString(Path.of("result3.txt"), threadResult, CREATE, APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private void doThreadWork(int number, int count, String threadResult) {
        try (FileWriter file = new FileWriter(Thread.currentThread().getName().concat(".txt"))) {
            for (int j = number * count; j < number * count + count; j++) {
                if (isPrime(j)) {
                    String currentIterationResult = j + " ";
                    file.write(currentIterationResult);
                    threadResult = threadResult.concat(currentIterationResult);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
