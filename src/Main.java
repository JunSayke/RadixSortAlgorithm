public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 4, 2, 2};
        SortingAlgorithm radixSortLSD = new RadixSortInteger(arr);
        radixSortLSD.sort();
    }
}