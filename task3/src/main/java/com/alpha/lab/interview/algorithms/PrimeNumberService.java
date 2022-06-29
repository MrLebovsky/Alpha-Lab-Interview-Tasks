package com.alpha.lab.interview.algorithms;

import com.alpha.lab.interview.benchmark.Benchmark;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.alpha.lab.interview.utils.FileWriterUtils.writeToFile;
import static com.alpha.lab.interview.utils.MathUtils.isPrime;

@Service
public class PrimeNumberService {

    private static final int START_VALUE = 0;
    private static final int FINISH_VALUE = 1000000;

    private static final int THREADS_COUNT = 6;

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
    public void parallelAlgorithm() {

        // запустим все фичи (они активно пишут в свои файлы на диск)
        List<Future<String>> futures = collectFutures();

        // дождемся, когда все файлы готовы
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // объединим результаты всех потоков
        // последовательность соблюдается, потому что мы знаем очередность определения задачи для каждого потока
        unionThreadsResultToOneFile(futures);
    }

    private void unionThreadsResultToOneFile(List<Future<String>> futures) {
        try (FileWriter file = new FileWriter("result2.txt")) {
            futures.stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(Paths::get)
                    .map(path -> {
                        try {
                            return Files.readAllLines(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .flatMap(Collection::stream)
                    .forEach(line -> {
                        try {
                            file.write(line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private LinkedList<Future<String>> collectFutures() {
        LinkedList<Future<String>> futures = new LinkedList<>();
        int count = FINISH_VALUE / THREADS_COUNT;
        for (int i = 0; i < THREADS_COUNT; i++) {
            final int number = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                String fileName;
                try {
                    fileName = Thread.currentThread().getName().concat(".txt");
                    try (FileWriter file = new FileWriter(fileName)) {
                        for (int j = number * count; j < number * count + count; j++) {
                            if (isPrime(j))
                                writeToFile(file, j);
                        }
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }

                return fileName;
            });
            futures.add(future);
        }

        return futures;
    }

}
