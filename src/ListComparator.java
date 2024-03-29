import java.util.Comparator;
import java.util.List;

/**
 * Used to sort leader board scores list.
 */
public class ListComparator implements Comparator<List<String>> {

    @Override
    public int compare(List<String> o1, List<String> o2) {
        Double s1 = Double.parseDouble(o1.get(o1.size() - 1));
        Double s2 = Double.parseDouble(o2.get(o2.size() - 1));
        return s2.compareTo(s1);
    }
}