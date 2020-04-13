package zxh.demo.program.pearls.sorted.integer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;

class FileRandomInSortedOutTest {
    @Test
    void should_sort_file() throws IOException {
        FileRandomInSortedOut.doSort();
    }
}