public class CountingSortInteger implements SortingAlgorithm {
    private final int[] arr;
    private int min, max;

    public CountingSortInteger(int[] arr) {
        this.arr = arr;
        setMinMax();
    }

    public void sort() {
        System.out.println("Sorting array . . .");
//        sortFirstMethod();
        sortSecondMethod();
        printArr(arr);
    }

    // Does not preserve their relative order (non-stable sorting)
    public void sortFirstMethod() {
        int[] freq_arr = getIntArrFreq();

        // Sort the array
        int index_ctr = 0;
        for (int i = 0; i < freq_arr.length; i++) {
            while(freq_arr[i] > 0) {
                arr[index_ctr++] = i + min;
                freq_arr[i]--;
            }
        }
    }

    // Preserve their relative order (stable sorting)
    public void sortSecondMethod() {
        int[] freq_arr = getIntArrFreq();

        // Needed an additional array since it must not modify the original array
        int[] sorted_arr = new int[arr.length];


        // Calculate each number final position for the original array
        for (int i = 1; i < freq_arr.length; i++) {
            freq_arr[i] += freq_arr[i - 1];
        }

        // Sort the array
        // The reason why it iterates from end to start is to preserve their relative order
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i];
            int index_pos = freq_arr[num - min] - 1;
            sorted_arr[index_pos] = num;
            freq_arr[num - min]--;
        }

        System.arraycopy(sorted_arr, 0, arr, 0, arr.length);
    }

    // Helper functions
    private void setMinMax() {
        min = max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int num = arr[i];
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }
    }

    private int[] getIntArrFreq() {
        int[] freq_arr = new int[max - min + 1];

        // Count the frequency of each number found in the original array
        for (int num : arr) {
            freq_arr[num - min]++;
        }

        return freq_arr;
    }

    private void printArr(int[] arr) {
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
