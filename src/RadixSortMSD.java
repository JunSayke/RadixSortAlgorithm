import java.util.ArrayList;
import java.util.Arrays;

// For Positive Whole Numbers Only
public class RadixSortMSD implements SortingAlgorithm {
    private final int[] originalArray;
    private int index;

    private Node createNode() {
        return new Node();
    }

    public RadixSortMSD(int[] array) {
        this.originalArray = array;
        index = 0;
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

    public void sort() {
        System.out.println("Radix Sort MSD");
        printArr(originalArray);
        Node n = createNode();
        for (int i = 0; i < originalArray.length; i++) {
            n.insertNum(originalArray[i]);
        }
        int max = getArrayMax(originalArray);
        bucketSort(n, (int) Math.pow(10, getDigitCount(max) - 1));
        printArr(originalArray);
    }

    public void bucketSort(Node n, int exp) {
        ArrayList<Integer> arr = n.arr;

        if (exp < 1) {
            for (int i = 0; i < arr.size(); i++) {
                originalArray[index++] = arr.get(i);
            }
            return;
        }

        Node[] nodes = n.next;
        for (int i = 0; i < arr.size(); i++) {
            int currentDigit = (arr.get(i) / exp) % 10;
            if (nodes[currentDigit] == null) {
                nodes[currentDigit] = createNode();
            }
            nodes[currentDigit].insertNum(arr.get(i));
        }

        for (int i = 0; i < 10; i++) {
            if (nodes[i] == null) {
                continue;
            }
            ArrayList<Integer> currentNodeArr = nodes[i].arr;
            if (currentNodeArr.size() <= 1) {
                originalArray[index++] = currentNodeArr.get(0);
            } else {
                bucketSort(nodes[i], exp / 10);
            }
        }
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
    ArrayList<Integer> arr;
    Node[] next;
    public Node() {
        arr = new ArrayList<>();
        next = new Node[10];
    }

    public void insertNum(int x) {
        arr.add(x);
    }
}