import java.util.ArrayList;
import java.util.Arrays;

// For Positive Whole Numbers Only
public class RadixSortMSD {
    public RadixSortMSD() {
    }

    public int[] sort(int[] array) {
        System.out.println("Radix Sort MSD");
        printArr(array);

        Node n = new Node();
        for (int i = 0; i < array.length; i++) {
            n.insertNum(array[i]);
        }

        int[] sortedArray = new int[array.length];
        int[] index = {0};
        int max = getArrayMax(array);

        bucketSort(n, (int) Math.pow(10, getDigitCount(max) - 1), sortedArray, index);

        printArr(sortedArray);
        return sortedArray;
    }

    public void bucketSort(Node n, int exp, int[] sortedArray, int[] currentSortedArrayIndexReference) {
        ArrayList<Integer> array = n.getArray();

        if (exp < 1) {
            for (int i = 0; i < array.size(); i++) {
                int pos = currentSortedArrayIndexReference[0]++;
                sortedArray[pos] = array.get(i);
            }
            return;
        }

        Node[] nodes = n.getNodes();
        for (int i = 0; i < array.size(); i++) {
            int currentDigit = (array.get(i) / exp) % 10;
            if (nodes[currentDigit] == null) {
                nodes[currentDigit] = new Node();
            }
            nodes[currentDigit].insertNum(array.get(i));
        }

        for (int i = 0; i < 10; i++) {
            if (nodes[i] == null) {
                continue;
            }
            ArrayList<Integer> currentNodeArr = nodes[i].getArray();
            if (currentNodeArr.size() <= 1) {
                int pos = currentSortedArrayIndexReference[0]++;
                sortedArray[pos] = currentNodeArr.get(0);
            } else {
                bucketSort(nodes[i], exp / 10, sortedArray, currentSortedArrayIndexReference);
            }
        }

        System.out.println(Arrays.toString(sortedArray));
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

class Node {
    private final ArrayList<Integer> array;
    private final Node[] nodes;
    public Node() {
        array = new ArrayList<>();
        nodes = new Node[10];
    }

    public void insertNum(int x) {
        array.add(x);
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    public Node[] getNodes() {
        return nodes;
    }
}