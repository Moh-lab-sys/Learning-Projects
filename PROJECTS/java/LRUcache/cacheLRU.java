import java.util.*;

public class cacheLRU {
    private HashMap<Integer, Integer> cache;
    private int capacity;
    private int nData;
    private int tail;
    private List<Integer> list;
    public cacheLRU(int c) {
        capacity = c;
        list = new LinkedList<>();
        cache = new HashMap<>();
    }
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (nData <= capacity - 1) {
            nData += 1;
            
        } else if (!cache.containsKey(key) && nData >= capacity) {
            cache.remove(tail);
            list.removeFirst();
        }
        if(!cache.containsKey(key))
        list.addFirst(key);
        tail = key;
        cache.put(key, value);
    }

    public void printCache() {
        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}