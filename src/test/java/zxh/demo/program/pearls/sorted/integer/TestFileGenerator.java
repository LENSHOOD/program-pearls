package zxh.demo.program.pearls.sorted.integer;

import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * zxh.demo.program.pearls.sorted.integer.TestFileGenerator:
 * @author zhangxuhai
 * @date 2020/4/12
*/
public class TestFileGenerator {
    public static final Path TEST_FILE_PATH = Paths.get("src", "test", "resources", "test-file");
    private static final int ITEM_SIZE = 10000000;
    private static final Random RANDOM = new Random();

    @Test
    void generate() throws IOException {
        Files.deleteIfExists(TEST_FILE_PATH);

        int loops = ITEM_SIZE;
        try(BufferedWriter writer = Files.newBufferedWriter(TEST_FILE_PATH);) {
            while (loops-- > 0) {
                writer.write(String.valueOf(RANDOM.nextInt(ITEM_SIZE)));
                writer.write("\n");
            }
        }
    }
}
