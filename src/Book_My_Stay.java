import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class RoomAllocationService {

    private RoomInventory inventory;

    private HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    public void processBookings(Queue<Reservation> queue) {

        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();
            String type = r.roomType;

            if (inventory.getAvailability(type) > 0) {

                String roomId = generateRoomId(type);

                allocatedRooms.get(type).add(roomId);

                inventory.reduceRoom(type);

                System.out.println("Booking confirmed for Guest: " +
                        r.guestName + ", Room ID: " + roomId);
            }
        }
    }

    private String generateRoomId(String type) {

        int number = allocatedRooms.get(type).size() + 1;

        return type + "-" + number;
    }
}

public class Book_My_Stay {

    public static void main(String[] args) {

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Abhi", "Single"));
        queue.add(new Reservation("Subha", "Single"));
        queue.add(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();

        RoomAllocationService service = new RoomAllocationService(inventory);

        service.processBookings(queue);
    }
}