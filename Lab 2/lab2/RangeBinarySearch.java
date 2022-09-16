
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there is no matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        int low = 0;
        int high = a.length - 1;
        int result = -1; // if element is not found.

        while (low <= high) {
            int mid = (low + high) / 2;
            if (comparator.compare(key, a[mid]) == 0) {
                result = mid;
                high = mid - 1;
            }else if (comparator.compare(key, a[mid]) < 0) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return result;
    }

    // Returns the index of the *last* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there are is matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static<T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        int low = 0;
        int high = a.length - 1;
        int result = -1; // if element is not found.

        while (low <= high) {
            int mid = (low + high) / 2;
            if (comparator.compare(key, a[mid]) == 0) {
                result = mid;
                low = mid + 1;
            }else if (comparator.compare(key, a[mid]) < 0) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return result;
    }

 }
