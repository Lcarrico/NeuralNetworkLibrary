package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
    public static <T> ArrayList<ArrayList<T>> twoDArrayToList(T[][] twoDArray) {
        ArrayList<ArrayList<T>> list2d = new ArrayList<>();
        for (T[] array : twoDArray) {
            ArrayList<T> list = new ArrayList<>(Arrays.asList(array));
            list2d.add(list);
        }

        return list2d;
    }
}
