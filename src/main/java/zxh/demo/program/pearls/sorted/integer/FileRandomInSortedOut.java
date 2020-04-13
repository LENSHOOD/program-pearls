package zxh.demo.program.pearls.sorted.integer;

import static java.util.Objects.nonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * FileRandomInSortedOut:
 * @author zhangxuhai
 * @date 2020/4/13
*/
public class FileRandomInSortedOut {
    public static final Path INPUT_FILE_PATH = Paths.get("src", "main", "resources", "random-in");
    public static final Path OUTPUT_FILE_PATH = Paths.get("src", "main", "resources", "sorted-out");
    private static final int ITEM_SIZE = 10000000;
    private static final Random RANDOM = new Random();

    public static void doSort() throws IOException {
        SortedIntegerManager manager = new SortedIntegerManager(ITEM_SIZE);

        generate();
        initManager(manager);
        popToFile(manager);
    }

    private static void generate() throws IOException {
        Files.deleteIfExists(INPUT_FILE_PATH);

        int loops = ITEM_SIZE;
        try(BufferedWriter writer = Files.newBufferedWriter(INPUT_FILE_PATH);) {
            while (loops-- > 0) {
                writer.write(String.valueOf(RANDOM.nextInt(ITEM_SIZE)));
                writer.write("\n");
            }
        }
    }

    private static void initManager(SortedIntegerManager manager) throws IOException {
        try (BufferedReader bufferedReader = Files.newBufferedReader(INPUT_FILE_PATH)) {
            String currentLine;
            while (nonNull(currentLine = bufferedReader.readLine())) {
                manager.push(Integer.parseInt(currentLine));
            }
        }
    }

    private static void popToFile(SortedIntegerManager manager) throws IOException {
        Files.deleteIfExists(OUTPUT_FILE_PATH);
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(OUTPUT_FILE_PATH)) {
            while (true) {
                int i = manager.popNaturalOrder();
                if (i == -1) {
                    return;
                }
                bufferedWriter.write(String.valueOf(i));
                bufferedWriter.write("\n");
            }
        }
    }
}
