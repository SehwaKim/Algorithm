import java.util.HashSet;
import java.util.Set;

public class FirstDuplicate {
    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 4, 3, 2};
        int result = firstDuplicate(a);
        System.out.println(result);
    }

    public static int firstDuplicate(int[] a) {
        Set<Integer> result = new HashSet();
        for(int i=0; i<a.length; i++) {
            if(result.contains(a[i])) {
                return a[i];
            } else {
                result.add(a[i]);
            }
        }
        return -1;
    }
}