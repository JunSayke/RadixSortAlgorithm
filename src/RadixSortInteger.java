import java.util.Arrays;

public class RadixSortInteger implements SortingAlgorithm {
    private final int[] arr;

    RadixSortInteger(int[] arr) {
        this.arr = arr;
    }

    public int getDigitCount(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    @Override
    public void sort() {
        System.out.println("Sorting array . . .");

        int highestNum = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        int maxDigit = getDigitCount(highestNum);

        // Sort each number in the original array by digit
        for (int exp = 1; maxDigit / exp  > 0; exp *= 10) {
            countingSort(arr, exp);
        }

        printArr(arr);
    }

    // For Whole Numbers Only (Positive)
    public void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] frequency = new int[10];

        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            frequency[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            frequency[i] += frequency[i - 1];
        }

        // Reverse to preserve their relative order (stable sorting)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            int position = frequency[digit] - 1;
            output[position] = arr[i];
            frequency[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, output.length);
    }

    private void printArr(int[] arr) {
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
