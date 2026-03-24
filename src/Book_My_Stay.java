import java.io.*;
import java.util.*;

// Reservation (Serializable)
class Reservation implements Serializable {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Wrapper class to store full system state
class SystemState implements Serializable {
    Map<String, Integer> inventory;
    List<Reservation> bookings;

    public SystemState(Map<String, Integer> inventory, List<Reservation> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    // SAVE
    public static void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving system state.");
        }
    }

    // LOAD
    public static SystemState load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("System state loaded successfully.");
            return (SystemState) ois.readObject();
        } catch (Exception e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return null;
        }
    }
}

// Main
public class Book_My_Stay {

    public static void main(String[] args) {

        // Try loading existing data
        SystemState state = PersistenceService.load();

        Map<String, Integer> inventory;
        List<Reservation> bookings;

        if (state != null) {
            // Recovery
            inventory = state.inventory;
            bookings = state.bookings;

            System.out.println("\nRecovered Data:");
        } else {
            // Fresh start
            inventory = new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 1);

            bookings = new ArrayList<>();

            bookings.add(new Reservation("Abhi", "Single"));
            bookings.add(new Reservation("Subha", "Double"));
        }

        // Display current state
        System.out.println("\nCurrent Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
        System.out.println("Inventory saved successfully");

        System.out.println("\nBookings:");
        for (Reservation r : bookings) {
            System.out.println(r.guestName + " - " + r.roomType);
        }

        // Save state before exit
        SystemState newState = new SystemState(inventory, bookings);
        PersistenceService.save(newState);
    }
}