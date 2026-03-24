import java.util.*;

/**
 * Book My Stay App
 * Use Case 7: Add-On Service Selection
 * @version 7.0
 */

// Service class
class Service {

    String serviceName;
    int cost;

    public Service(String serviceName, int cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

// Manager class
class AddOnServiceManager {

    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, Service service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added " + service.serviceName +
                " to Reservation ID: " + reservationId);
    }

    // Display services and total cost
    public void displayServices(String reservationId) {

        System.out.println("\nAdd-On Services for Reservation ID: " + reservationId);

        List<Service> services = serviceMap.get(reservationId);

        int total = 0;

        if (services != null) {
            for (Service s : services) {
                System.out.println(s.serviceName + " - " + s.cost);
                total += s.cost;
            }
        }

        System.out.println("Total Add-On Cost: " + total);
    }
}

// Main class
public class Book_My_Stay {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        AddOnServiceManager manager = new AddOnServiceManager();

        // Example reservation IDs (from UC6)
        String res1 = "Single-1";
        String res2 = "Suite-1";

        // Add services
        manager.addService(res1, new Service("Breakfast", 20));
        manager.addService(res1, new Service("WiFi", 10));
        manager.addService(res2, new Service("Airport Pickup", 50));

        // Display services
        manager.displayServices(res1);
        manager.displayServices(res2);
    }
}