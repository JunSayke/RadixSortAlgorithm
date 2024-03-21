import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        int[] array = {6, 420, 696, 7891, 8400, 818, 6375, 1001, 1101, 1010};
        RadixSortLSD radixSortLSD = new RadixSortLSD();
        radixSortLSD.sort(array);
        RadixSortMSD radixSortMSD = new RadixSortMSD();
        radixSortMSD.sort(array);
    }
}