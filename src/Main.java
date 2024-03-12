import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        int[] arr = {5, 1, 7, 4, 7, 8, 9, 5, 1, 4, 2, 2};
        SortingAlgorithm radixSortLSD = new RadixSortLSD(arr.clone());
        radixSortLSD.sort();
        SortingAlgorithm radixSortMSD = new RadixSortMSD(arr.clone());
        radixSortMSD.sort();
    }
}