package zxh.demo.program.pearls.sorted.integer;

/**
 * SortedIntegerManager:
 *
 * @author zhangxuhai
 * @date 2020/4/13
 */
public class SortedIntegerManager {
    private static final byte[] MASKS = {
            1, 2, 4, 8, 16, 32, 64, -128
    };
    private byte[] store;
    private int popPointer = 0;
    private final int maxPop;

    public SortedIntegerManager(int size) {
        maxPop = size;
        double storeSize = Math.ceil((double)size / (double) 8);
        store = new byte[(int)storeSize];
    }

    public void push(int num) {
        int baseIndex = num / 8;
        int modIndex = num % 8;
        store[baseIndex] = (byte) (store[baseIndex] | MASKS[modIndex]);
    }

    public int popNaturalOrder() {
        while (popPointer < maxPop) {
            int baseIndex = popPointer / 8;
            int modIndex = popPointer % 8;
            int[] bits = getBits(store[baseIndex]);
            for (int i = modIndex; i < 8; i++) {
                popPointer++;
                if (bits[i] == 1) {
                    return popPointer - 1;
                }
            }
        }

        return -1;
    }

    static int[] getBits(byte b) {
        int[] setIndicator = new int[8];
        for (int i = 0; i < MASKS.length; i++) {
            setIndicator[i] = (b & MASKS[i]) != 0 ? 1 : 0;
        }
        return setIndicator;
    }
}
