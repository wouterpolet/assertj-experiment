package sa.assertj;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class Experiment {

    private final DataProvider provider;
    private final AssertionRunner runner;
    private final List<Long> results;

    public Experiment(DataProvider provider, AssertionRunner runner) {
        this.provider = provider;
        this.runner = runner;
        this.results = new ArrayList<>();
    }

    public void run(int size, int numOfSamples, long timeLimit) {
        for (int i=0; i < numOfSamples; i++) {
            Object[] sample = provider.generate(size);
            long startTime = System.nanoTime();
            runner.run(sample);
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            if (time > timeLimit) {
                throw new RuntimeException("Operation took too long");
            }
            this.results.add(endTime - startTime);
        }
    }

    public void printOverview() {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (long result : results) {
            min = Math.min(min, result);
            max = Math.max(max, result);
        }
        System.out.println(min + " - " + max);
    }

    public void writeResults(PrintWriter writer) {
        writer.println("sample,time(ns)");
        for (int i=0; i < results.size(); i++) {
            writer.println("%d,%d".formatted(i, results.get(i)));
        }
        writer.close();
    }

    public void writeResults(File file) throws IOException {
        writeResults(new PrintWriter(file));
    }

    public void writeResults(String path) throws IOException {
        writeResults(new File(path));
    }

    public long getTotalTime() {
        return results.stream().reduce(Long::sum).orElse(-1L);
    }

    @FunctionalInterface
    public interface DataProvider {
        Object[] generate(int size);
    }

    @FunctionalInterface
    public interface AssertionRunner {
        void run(Object[] data);
    }
}






