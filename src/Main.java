import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        int[] array = {5, 1, 7, 4, 7, 8, 9, 5, 1, 4, 2, 2};
        RadixSortLSD radixSortLSD = new RadixSortLSD();
        radixSortLSD.sort(array);
        RadixSortMSD radixSortMSD = new RadixSortMSD();
        radixSortMSD.sort(array);
    }
}