
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

class DayOne {
    public static void main(String[] args) {
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("./DayOne.txt")));

            int[] leftList = new int[lines.size()];
            int[] rightList = new int[lines.size()];

            for (int i = 0; i < lines.size(); i++) {
                String[] sides = lines.get(i).split("\\s+");
                leftList[i] = Integer.parseInt(sides[0]);
                rightList[i] = Integer.parseInt(sides[1]);
            }

            quicksort(leftList, 0, leftList.length - 1);
            quicksort(rightList, 0, rightList.length - 1);

            int[] differenceList = new int[lines.size()];

            for(int i = 0; i < lines.size(); i++) {
                differenceList[i] = Math.abs(leftList[i] - rightList[i]);
            }

            int sum = 0;
            for (int i : differenceList) {
                sum += i;
            }
            System.out.println(sum);

        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*
        function quicksort(arr, low, high):
        if low < high:
        pi = partition(arr, low, high)
        quicksort(arr, low, pi - 1)
        quicksort(arr, pi + 1, high)

        function partition(arr, low, high):
        pivot = arr[high]
        i = low - 1
        for j = low to high - 1:
            if arr[j] < pivot:
                i = i + 1
                swap arr[i] with arr[j]
        swap arr[i + 1] with arr[high]
        return (i + 1)
    */

    private static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quicksort(arr, low, pi - 1);
            quicksort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int indexOne, int indexTwo) {
        int temp = arr[indexOne];
        arr[indexOne] = arr[indexTwo];
        arr[indexTwo] = temp;
    }
}