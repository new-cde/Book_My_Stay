import java.util.*;

class Reservation {
    String roomId;
    String roomType;

    public Reservation(String roomId, String roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 5);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);
    }

    public void increase(String type) {
        inventory.put(type, inventory.get(type) + 1);
    }

    public int getCount(String type) {
        return inventory.get(type);
    }
}

class BookingHistory {

    private Map<String, Reservation> map = new HashMap<>();

    public void add(Reservation r) {
        map.put(r.roomId, r);
    }

    public Reservation get(String roomId) {
        return map.get(roomId);
    }

    public void remove(String roomId) {
        map.remove(roomId);
    }
}

class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancel(String roomId) {

        Reservation r = history.get(roomId);

        if (r == null) {
            System.out.println("Cancellation failed: Invalid Room ID");
            return;
        }
        rollbackStack.push(roomId);

        inventory.increase(r.roomType);

        history.remove(roomId);

        System.out.println("Booking Cancellation");
        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + r.roomType);

        System.out.println("\nRollback History (Most Recent First):");
        System.out.println("Released Reservation ID: " + rollbackStack.peek());

        System.out.println("\nUpdated " + r.roomType + " Room Availability: " + inventory.getCount(r.roomType));
    }
}

public class Book_My_Stay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // existing booking
        history.add(new Reservation("Single-1", "Single"));

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("Single-1");
    }
}