import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MSDRadixSort {
    private Node head;

    private static class Node {
        List<Integer> array;
        Node[] next;

        Node() {
            array = new ArrayList<>();
            next = new Node[10];
            Arrays.fill(next, null);
        }
    }

    MSDRadixSort() {
        head = createNode();
    }

    private static Node createNode() {
        return new Node();
    }

    public void insert(int num) {
        head.array.add(num);
    }

    private int maxDigit() {
        int max = 0;
        for (int i : head.array) {
            int digit = (int) Math.log10(i);
            if (digit > max) {
                max = digit;
            }
        }
        return max;
    }

    private void sortHelper(Node node, int exponent, List<Integer> sortedArray) {
        if (exponent <= 0)
            return;

        for (int i : node.array) {
            int index = i / exponent % 10;
            if (node.next[index] == null)
                node.next[index] = createNode();
            node.next[index].array.add(i);
        }

        for (Node i : node.next) {
            if (i == null)
                continue;
            else if (i.array.size() > 1) {
                if (exponent > 1)
                    sortHelper(i, exponent / 10, sortedArray);
                else {
                    sortedArray.addAll(i.array);
                    i.array.clear();
                }
            } else
                sortedArray.add(i.array.get(0));
        }
    }

    private int minNum() {
        int minimum = Integer.MAX_VALUE;
        for (int i : head.array)
            if (i < minimum)
                minimum = i;
        return minimum;
    }

    public void sort() {
        List<Integer> sortedArray = new ArrayList<>();
        int minimum = minNum();
        if (minimum < 0)
            for (int i = 0; i < head.array.size(); i++)
                head.array.set(i, head.array.get(i) - minimum);
        sortHelper(head, (int) Math.pow(10, maxDigit()), sortedArray);
        head.array = sortedArray;
        if (minimum < 0)
            for (int i = 0; i < head.array.size(); i++)
                head.array.set(i, head.array.get(i) + minimum);
    }

    public void print() {
        for (int i : head.array)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        MSDRadixSort radixSort = new MSDRadixSort();
        radixSort.insert(170);
        radixSort.insert(45);
        radixSort.insert(75);
        radixSort.insert(90);
        radixSort.insert(802);
        radixSort.insert(24);
        radixSort.insert(2);
        radixSort.insert(66);

        System.out.println("Array before sorting:");
        radixSort.print();

        radixSort.sort();

        System.out.println("Array after sorting:");
        radixSort.print();
    }
}
