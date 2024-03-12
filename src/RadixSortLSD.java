import java.util.Arrays;

// For Positive Whole Numbers Only
public class RadixSortLSD implements SortingAlgorithm {
    private final int[] array;
    public RadixSortLSD(int[] array) {
        this.array = array;
    }

    public void sort() {
        System.out.println("Radix Sort LSD");
        printArr(array);
        int max = getArrayMax(array);
        int maxDigits = getDigitCount(max);
        for (int exp = 1; maxDigits / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
        printArr(array);
    }

    public void countingSort(int[] array, int exp) {
        int range = 10;
        int[] frequency = new int[range];
        int[] sortedArr = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int digit = (array[i] / exp) % 10;
            frequency[digit]++;
        }

        for (int i = 1; i < range; i++) {
            frequency[i] += frequency[i-1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            int digitPos = frequency[digit] - 1;
            sortedArr[digitPos] = array[i];
            frequency[digit]--;
        }

        System.arraycopy(sortedArr, 0, array, 0, sortedArr.length);
    }

    public int getArrayMax(int[] arr) {
        return Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
    }

    public int getDigitCount(int num) {
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }

    private void printArr(int[] arr) {
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
