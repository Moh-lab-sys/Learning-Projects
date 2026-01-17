public class testDriver {
    public static void main(String[] args) {
 

        cacheLRU cache = new cacheLRU(2);

        cache.put(1, 10);
        cache.put(2, 20);
        System.out.println("get(1) → " + cache.get(1)); // 10
        cache.printCache(); // Cache order: 1,2

        System.out.println("\nTest 2: Eviction");
        cache.put(3, 30); // Evicts key 2
        System.out.println("get(2) → " + cache.get(2)); // -1
        cache.printCache(); // Cache order: 3,1

        System.out.println("\nTest 3: get() updates recency");
        System.out.println("get(1) → " + cache.get(1)); // 10
        cache.put(4, 40); // Evicts key 3
        cache.printCache(); // Cache order: 4,1

        System.out.println("\nTest 4: Update existing key");
        cache.put(1, 100); // Update value
        System.out.println("get(1) → " + cache.get(1)); // 100
        cache.printCache(); // Cache order: 1,4

        System.out.println("\nTest 5: Cache miss");
        System.out.println("get(5) → " + cache.get(5)); // -1
        cache.printCache(); // Cache order unchanged: 1,4
    }
}


