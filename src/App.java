import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class App {

    private static final Random random = new Random();

    public static void main(final String[] args) {
        final int[] array = random.ints(1_000_000).toArray();
        // printArr(array, "Unsorted: ");
        // printArr(quickSort(array), "Sorted: ");

        long now = System.nanoTime();
        insertionSort(array.clone());
        System.out.println("Insertion sort: " + ((System.nanoTime() - now) / 1_000_000) + "ms");

        now = System.nanoTime();
        quickSort(array.clone());
        System.out.println("Quick sort: " + ((System.nanoTime() - now) / 1_000_000) + "ms");
    }

    private static void printArr(final int[] arr, final String... msg) {
        System.out.println(String.join(" ", msg) + Arrays.toString(arr));
    }

    private static int[] insertionSort(final int[] unsorted) {
        final int first = 0;
        final int last = unsorted.length - 1;

        int nextPos = last - 1;
        while (nextPos >= first) {
            final int next = unsorted[nextPos];
            int current = nextPos;
            while (current < last && next > unsorted[current + 1]) {
                current++;
                unsorted[current - 1] = unsorted[current];
            }
            unsorted[current] = next;
            nextPos--;
        }
        return unsorted;
    }

    private static int[] quickSort(final int[] unsorted) {
        return quickSort(Arrays.stream(unsorted).boxed().collect(Collectors.toList())).stream()
                .mapToInt(Integer::intValue).toArray();
    }

    private static List<Integer> quickSort(final List<Integer> unsorted) {
        if (unsorted.size() <= 1) {
            final List<Integer> sorted = new ArrayList<>(1);
            if (unsorted.size() == 1)
                sorted.add(unsorted.get(0));
            return sorted;
        }

        final int pivot = unsorted.get(0);
        final List<Integer> left = new ArrayList<>();
        final List<Integer> right = new ArrayList<>();

        for (int i = 1; i < unsorted.size(); i++) {
            final int element = unsorted.get(i);
            if (element < pivot)
                left.add(element);
            else
                right.add(element);
        }

        final List<Integer> sorted = new ArrayList<>();
        sorted.addAll(quickSort(left));
        sorted.add(pivot);
        sorted.addAll(quickSort(right));

        return sorted;
    }
}
