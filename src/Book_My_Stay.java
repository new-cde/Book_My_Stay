import java.util.HashMap;

// Abstract Room class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected int size;
    protected int price;

    public Room(String roomType, int beds, int size, int price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println(roomType + ": ");
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price per night: " + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 100);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 180);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 300);
    }
}

// Centralized inventory class
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Main class
public class Book_My_Stay {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        single.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Single Room"));
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Double Room"));
        System.out.println();

        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Suite Room"));
    }
}