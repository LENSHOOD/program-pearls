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
        // map num to store index(bit index)
        int baseIndex = num / 8;
        int modIndex = num % 8;
        store[baseIndex] = (byte) (store[baseIndex] | MASKS[modIndex]);
    }

    public int popNaturalOrder() {
        while (popPointer < maxPop) {
            // find next byte from last popped
            byte baseByte = store[popPointer / 8];

            // move to precise bit, test if there has a number (the bit was set)
            int[] bits = getBits(baseByte);
            for (int i = popPointer % 8; i < 8; i++) {
                popPointer++;
                if (bits[i] == 1) {
                    return popPointer - 1;
                }
            }
        }

        // return -1 if all number popped
        return -1;
    }

    /*
     * split one byte to 8 size int array, e.g. input -74 (b1011_0110) output: [0, 1, 1, 0, 1, 1, 0, 1]
     */
    static int[] getBits(byte b) {
        int[] bitsByIntArr = new int[8];
        for (int i = 0; i < MASKS.length; i++) {
            bitsByIntArr[i] = (b & MASKS[i]) != 0 ? 1 : 0;
        }
        return bitsByIntArr;
    }
}
