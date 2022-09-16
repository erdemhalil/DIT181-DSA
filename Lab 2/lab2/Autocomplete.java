import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] dictionary;

    // Initializes the dictionary from the given array of terms.
    public Autocomplete(Term[] dictionary) {
        this.dictionary = dictionary;
        sortDictionary();
    }

    // Sorts the dictionary in *case-insensitive* lexicographic order.
    // Complexity: O(N log N) where N is the number of dictionary terms
    private void sortDictionary() {
        // Sort by using Java's Arrays.sort
        Arrays.sort(dictionary, Term.byLexicographicOrder);
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N + M log M) where M is the number of matching terms
    public Term[] allMatches(String prefix) {
        //Gets the length of the prefix
        int length = prefix.length();

        //Creates a new term with the prefix as word, weight will not be used so we set it to 0
        //Later this term will be used as a key
        Term search = new Term(prefix, 0);

        //Finds the first occurrence of the search key in the dictionary
        int first = RangeBinarySearch.firstIndexOf(dictionary, search, Term.byPrefixOrder(length));
        //Finds the last occurrence of the search key in the dictionary
        int last = RangeBinarySearch.lastIndexOf(dictionary, search, Term.byPrefixOrder(length));

        int matches = numberOfMatches(prefix);

        //Let's create a new array that will store all matches
        Term[] allMatches = new Term[matches];

        //If there are no matches, we return an empty array
        if(matches == 0){
            return allMatches;
        } else {
            //This loop helps us copy all the matches which range from first index to the last index of occurrence
            //to the new array
            int j = 0;
            for (int i = first; i <= last; i++) {

                allMatches[j] = dictionary[i];
                j++;
            }
            //Then we sort the array by reverse weight order
            Arrays.sort(allMatches, Term.byReverseWeightOrder);
        }
        return allMatches;
    }

    // Returns the number of terms that start with the given prefix.
    // Precondition: the internal dictionary is in lexicographic order.
    // Complexity: O(log N) where N is the number of dictionary terms
    public int numberOfMatches(String prefix) {
        // Get the length of the prefix
        int length = prefix.length();

        //Creates a new term with the prefix as word, weight will not be used so we set it to 0
        //Later this term will be used as a key
        Term search = new Term(prefix, 0);

        //Finds the first occurrence of the search key in the dictionary
        int first = RangeBinarySearch.firstIndexOf(dictionary, search, Term.byPrefixOrder(length));

        //Finds the last occurrence of the search key in the dictionary
        int last = RangeBinarySearch.lastIndexOf(dictionary, search, Term.byPrefixOrder(length));

        // If there's at least one occurrence, subtract last and first index, then add 1
        // If not, we return 0
        if(first >= 0) {
            return last - first + 1;
        } else {
            return 0;
        }
    }

}
