import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static Map<Integer, Integer> arrayToMap(int[] num) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : num) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        return map;
    }

    public static int[] mapToArray(Map<Integer, Integer> map) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                deque.addLast(entry.getKey());
            }
        }
        return Arrays.stream(deque.toArray()).mapToInt(o -> (int) o).toArray();
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        int[] minLengthArray;
        int[] maxLengthArray;
        if (nums1.length < nums2.length) {
            minLengthArray = nums1;
            maxLengthArray = nums2;
        } else {
            minLengthArray = nums2;
            maxLengthArray = nums1;
        }
        Map<Integer, Integer> minMap = arrayToMap(minLengthArray);
        Map<Integer, Integer> maxMap = arrayToMap(maxLengthArray);
        for (Map.Entry<Integer, Integer> entry : minMap.entrySet()) {
            Integer key = entry.getKey();
            if (maxMap.get(key) != null) {
                if (entry.getValue() > maxMap.get(key)) {
                    minMap.put(key, maxMap.get(key));
                }
            } else {
                minMap.put(key, 0);
            }
        }
        minMap.entrySet().removeIf(entry -> entry.getValue() == 0);
        return mapToArray(minMap);
    }

    public static void main(String[] args) {
        int[] num1 = {4, 9, 5};
        int[] num2 = {9, 4, 9, 8, 4};
        System.out.println(Arrays.toString(intersect(num1, num2)));

    }
}

