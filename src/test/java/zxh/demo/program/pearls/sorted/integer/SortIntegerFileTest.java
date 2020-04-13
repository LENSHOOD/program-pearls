package zxh.demo.program.pearls.sorted.integer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author zhangxuhai
 * @date 2020/4/12
*/
public class SortIntegerFileTest {
    @Test
    void should_add_one_number_to_sorted_integer_manager() {
        SortedIntegerManager sortedIntegerManager = new SortedIntegerManager(10000);
        sortedIntegerManager.push(521);
        assertThat(sortedIntegerManager.popNaturalOrder(), is(521));
    }

    @Test
    void should_return_all_set_bit_of_byte() {
        // 0101 0110
        byte b = 86;
        int[] indexes = SortedIntegerManager.getBits(b);
        assertThat(Arrays.stream(indexes).boxed().collect(Collectors.toList()), contains(0, 1, 1, 0, 1, 0, 1, 0));
    }
}
