import java.util.Arrays;

// For Positive Whole Numbers Only
public class RadixSortLSD {
    public RadixSortLSD() {
    }

    public int[] sort(int[] array) {
        System.out.println("Radix Sort LSD");
        printArr(array);

        int[] sortedArray = new int[array.length];
        int max = getArrayMax(array);

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp, sortedArray);
        }

        printArr(sortedArray);
        return sortedArray;
    }

    private void countingSort(int[] array, int exp, int[] sortedArray) {
        int range = 10;
        int[] frequency = new int[range];

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
            sortedArray[digitPos] = array[i];
            frequency[digit]--;
        }
    }

    private int getArrayMax(int[] arr) {
        return Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
    }

    private int getDigitCount(int num) {
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
