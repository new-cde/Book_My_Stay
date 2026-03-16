import java.util.LinkedList;
import java.util.Queue;

class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public void processRequests() {

        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            System.out.println(
                    "Processing booking for Guest: " +
                            r.guestName +
                            ", Room Type: " +
                            r.roomType
            );
        }
    }
}

public class Book_My_Stay {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        bookingQueue.processRequests();
    }
}