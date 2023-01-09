import java.util.ArrayList;
import java.util.Scanner;
// todo
// - update name event
// - delete event
// - joption
// - save to file text backup generate name by time date on exit

class Booking {
    private String name;
    private int age;
    private String phone;
    private int tickets;

    public Booking(String name, int age, String phone, int tickets) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.tickets = tickets;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getTickets() {
        return this.tickets;
    }
}

class Event {
    private String name;
    private String location;
    private int capacity;
    private int attendees;
    private ArrayList<Booking> bookings;

    public Event(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.attendees = 0;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getAttendees() {
        return this.attendees;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public boolean book(int numTickets) {
        if (numTickets + this.attendees > this.capacity) {
            return false;
        }
        this.attendees += numTickets;
        return true;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }
}

public class EventBooking {

    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Create event");
            System.out.println("2. Book event");
            System.out.println("3. View events");
            System.out.println("4. View bookings for event");
            System.out.println("5. Exit");
            System.out.print("Enter a choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Create a new event
                System.out.print("Enter event name: ");
                String name = scanner.next();
                System.out.print("Enter event location: ");
                String location = scanner.next();
                System.out.print("Enter event capacity: ");
                int capacity = scanner.nextInt();
                events.add(new Event(name, location, capacity));
                System.out.println("Event created successfully!");
            } else if (choice == 2) {
                // Book tickets for an event
                System.out.print("Enter event name: ");
                String name = scanner.next();
                Event event = null;
                for (Event e : events) {
                    if (e.getName().equals(name)) {
                        event = e;
                        break;
                    }
                }
                if (event == null) {
                    System.out.println("Sorry, no such event exists.");
                    continue;
                }
                System.out.print("Enter number of tickets: ");
                int numTickets = scanner.nextInt();
                if (!event.book(numTickets)) {
                    System.out.println("Sorry, there are not enough tickets available.");
                } else {
                    System.out.println("Booking successful!");
                    System.out.print("Enter your name: ");
                    String bookerName = scanner.next();
                    System.out.print("Enter your age: ");
                    int bookerAge = scanner.nextInt();
                    System.out.print("Enter your phone number: ");
                    String bookerPhone = scanner.next();
                    Booking booking = new Booking(bookerName, bookerAge, bookerPhone, numTickets);
                    event.addBooking(booking);
                }
            } else if (choice == 3) {
                // View all events
                for (Event e : events) {
                    System.out.println(
                            "Event: " + e.getName() + " (" + e.getAttendees() + "/" + e.getCapacity() + " booked)");
                }
            } else if (choice == 4) {
                // View bookings for event
                System.out.print("Enter event name: ");
                String name = scanner.next();
                Event event = null;
                for (Event e : events) {
                  if (e.getName().equals(name)) {
                    event = e;
                    break;
                  }
                }
                if (event == null) {
                  System.out.println("Sorry, no such event exists.");
                  continue;
                }
                ArrayList<Booking> bookings = event.getBookings();
                if (bookings.isEmpty()) {
                  System.out.println("There are no bookings for this event.");
                } else {
                  System.out.println("Bookings for event: " + event.getName());
                  for (Booking booking : bookings) {
                    System.out.println("- " + booking.getName() + " (" + booking.getTickets() + " tickets)");
                  }
                }
              } else if (choice == 5) {
                // Exit
                running = false;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
