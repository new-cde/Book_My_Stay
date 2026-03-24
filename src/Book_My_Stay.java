import java.util.*;

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> counters = new HashMap<>(); // for Room IDs

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        counters.put("Single", 0);
        counters.put("Double", 0);
        counters.put("Suite", 0);
    }

    // synchronized → prevents race condition
    public synchronized void bookRoom(String type, String guest) {

        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {

            try { Thread.sleep(100); } catch (Exception e) {}

            // generate unique room ID
            int id = counters.get(type) + 1;
            counters.put(type, id);

            String roomId = type + "-" + id;

            inventory.put(type, available - 1);

            System.out.println("Booking confirmed for Guest: " + guest +
                    " Room ID: " + roomId);
        }
        else {
            System.out.println("Booking failed for Guest: " + guest +
                    " (" + type + ")");
        }
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
    }
}

// Thread class
class BookingTask extends Thread {

    private RoomInventory inventory;
    private String guestName;
    private String roomType;

    public BookingTask(RoomInventory inventory, String guestName, String roomType) {
        this.inventory = inventory;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        inventory.bookRoom(roomType, guestName);
    }
}

// Main
public class Book_My_Stay {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation\n");

        RoomInventory inventory = new RoomInventory();

        Thread t1 = new BookingTask(inventory, "Abhi", "Single");
        Thread t2 = new BookingTask(inventory, "Subha", "Single");
        Thread t3 = new BookingTask(inventory, "Ram", "Suite");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {}

        inventory.displayInventory();
    }
}