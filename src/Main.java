import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        int[] array = {6, 56, 7898, 445, 7456, 445, 9565, 556, 156, 45, 25, 32};
        RadixSortLSD radixSortLSD = new RadixSortLSD();
        radixSortLSD.sort(array);
        System.out.println();
        RadixSortMSD radixSortMSD = new RadixSortMSD();
        radixSortMSD.sort(array);
    }
}