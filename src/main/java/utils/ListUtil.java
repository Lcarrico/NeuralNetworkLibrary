package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtil {
    /**
     * This method will convert any 2D array into a 2d ArrayList.
     * @param twoDArray This parameter will be the 2d array to convert.
     * @param <T> This parameter will be the type of the elements in the array.
     * @return List
     */
    public static <T> ArrayList<ArrayList<T>> twoDArrayToList(T[][] twoDArray) {
        ArrayList<ArrayList<T>> list2d = new ArrayList<>();
        for (T[] array : twoDArray) {
            ArrayList<T> list = new ArrayList<>(Arrays.asList(array));
            list2d.add(list);
        }

        return list2d;
    }
}
