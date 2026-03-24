import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory class
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, -1);
    }

    public void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

// Booking Service
class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBooking(Reservation r) {

        try {
            validate(r);

            inventory.reduceRoom(r.roomType);

            System.out.println("Booking successful for Guest: " + r.guestName +
                    ", Room Type: " + r.roomType);

        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: "+ e.getMessage());
        }
    }

    private void validate(Reservation r) throws InvalidBookingException {

        if (r.roomType == null || r.roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        int availability = inventory.getAvailability(r.roomType);

        if (availability == -1) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (availability <= 0) {
            throw new InvalidBookingException("No rooms available");
        }
    }
}

// Main class
public class Book_My_Stay {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Booking Validation\n");

        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Room Type (Single/Double/Suite): ");
        String type = sc.nextLine();

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        Reservation r = new Reservation(name, type);

        service.processBooking(r);

        sc.close();
    }
}