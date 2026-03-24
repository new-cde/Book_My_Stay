import java.util.*;

// Reservation class
class Reservation {

    String guestName;
    String roomId;

    public Reservation(String guestName, String roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }
}

// Booking History (stores confirmed bookings)
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation r) {
        history.add(r); // maintains order
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    public void displayReport(List<Reservation> reservations) {

        System.out.println("Booking History and Reporting\n");
        System.out.println("Booking History Report");

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.guestName +
                    ", Room ID: " + r.roomId);
        }

        System.out.println("Total Bookings: " + reservations.size());
    }
}

// Main class
public class Book_My_Stay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // Simulating confirmed bookings (from UC6)
        history.addReservation(new Reservation("Abhi", "Single-1"));
        history.addReservation(new Reservation("Subha", "Single-2"));
        history.addReservation(new Reservation("Vanmathi", "Suite-1"));

        BookingReportService reportService = new BookingReportService();

        reportService.displayReport(history.getAllReservations());
    }
}