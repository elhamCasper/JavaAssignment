import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

// todo
// - update name event
// - delete event
// - joption
// - save to file text backup generate name by time date on exit
class Payment {
    private double amount;
    private String method;
    private String transactionId;

    public Payment(double amount, String method) {
        this.amount = amount;
        this.method = method;
        this.transactionId = generateTransactionId();
    }

    public double getAmount() {
        return this.amount;
    }

    public String getMethod() {
        return this.method;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    private String generateTransactionId() {
        // Code to generate a unique transaction ID
        return UUID.randomUUID().toString();
    }
}

class Booking {
    private String name;
    private int age;
    private String phone;
    private int tickets;
    private Payment payment;

    public Booking(String name, int age, String phone, int tickets, Payment payment) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.tickets = tickets;
        this.payment = payment;
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

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

class Event {
    private String name;
    private String location;
    private int capacity;
    private int attendees;
    private ArrayList<Booking> bookings;
    private double ticketPrice;

    public Event(String name, String location, int capacity, double ticketPrice) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.attendees = 0;
        this.bookings = new ArrayList<>();
        this.ticketPrice = ticketPrice;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
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

    public boolean book(String name, int age, String phone, int numTickets, Payment payment) {
        if (numTickets + this.attendees > this.capacity) {
            return false;
        }
        double cost = this.ticketPrice * numTickets;
        if (payment.getAmount() < cost)
            return false;
        this.attendees += numTickets;
        Booking booking = new Booking(name, age, phone, numTickets, payment);
        this.bookings.add(booking);
        return true;
    }

    public ArrayList<Booking> getBookings() {
        return this.bookings;
    }
}

class Users {
    private String name;
    private int age;
    private String phone;
    private String email;

    public Users(String name, int age, String phone, String email) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

class VIPEvent extends Event {
    private String vipArea;

    public VIPEvent(String name, String location, int capacity, double ticketPrice, String vipArea) {
        super(name, location, capacity, ticketPrice);
        this.vipArea = vipArea;
    }

    public String getVipArea() {
        return vipArea;
    }

    public void setVipArea(String vipArea) {
        this.vipArea = vipArea;
    }
}

public class EventBooking {

    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Users> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        VIPEvent vipEvent = new VIPEvent("New_Year", "Level 51, THE FACE Suites", 100, 150.00, "The VIP Lounge");
        events.add(vipEvent);

        while (running) {
            System.out.println("1. Create event");
            System.out.println("2. Book event");
            System.out.println("3. View events");
            System.out.println("4. View bookings for event");
            System.out.println("5. Add user");
            System.out.println("6. Update user");
            System.out.println("7. Delete user");
            System.out.println("8. View users");
            System.out.println("9. Exit");
            System.out.print("Enter a choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Create a new event
                System.out.println("1. Regular event");
                System.out.println("2. VIP event");
                System.out.print("Enter event type: ");
                int eventType = scanner.nextInt();
            
                System.out.print("Enter event name: ");
                String name = scanner.next();
                System.out.print("Enter event location: ");
                String location = scanner.next();
                System.out.print("Enter event capacity: ");
                int capacity = scanner.nextInt();
                System.out.print("Enter ticket price: ");
                double ticketPrice = scanner.nextDouble();
            
                if (eventType == 1) {
                    Event event = new Event(name, location, capacity, ticketPrice);
                    events.add(event);
                } else if (eventType == 2) {
                    System.out.print("Enter VIP area: ");
                    String vipArea = scanner.next();
                    VIPEvent event = new VIPEvent(name, location, capacity, ticketPrice, vipArea);
                    events.add(event);
                }
            
            } else if (choice == 2) {
                // Book an event
                System.out.print("Enter event name: ");
                String eventName = scanner.next();
                Event event = null;
                for (Event e : events) {
                    if (e.getName().equals(eventName)) {
                        event = e;
                        break;
                    }
                }
                if (event == null) {
                    System.out.println("Event not found!");
                    continue;
                }
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                System.out.print("Enter phone: ");
                String phone = scanner.next();
                System.out.print("Enter number of tickets: ");
                int numTickets = scanner.nextInt();
                System.out.print("Enter payment method: ");
                String paymentMethod = scanner.next();
                System.out.print("Enter payment amount: ");
                double paymentAmount = scanner.nextDouble();
                Payment payment = new Payment(paymentAmount, paymentMethod);
                if (event.book(name, age, phone, numTickets, payment)) {
                    System.out.println("Booking successful!");
                } else {
                    System.out.println("Booking failed. Not enough seats available or payment amount is insufficient.");
                }
            } else if (choice == 3) {
                // View events
                for (Event e : events) {
                    System.out.println("Event name: " + e.getName());
                    System.out.println("Event location: " + e.getLocation());
                    System.out.println("Event capacity: " + e.getCapacity());
                    System.out.println("Event attendees: " + e.getAttendees());
                    System.out.println("Event ticket price: " + e.getTicketPrice());
                    System.out.println("");
                }
            } else if (choice == 4) {
                // View bookings for event
                System.out.print("Enter event name: ");
                String eventName = scanner.next();
                Event event = null;
                for (Event e : events) {
                    if (e.getName().equals(eventName)) {
                        event = e;
                        break;
                    }
                }
                if (event == null) {
                    System.out.println("Event not found!");
                    continue;
                }
                ArrayList<Booking> bookings = event.getBookings();
                for (Booking b : bookings) {
                    System.out.println("Name: " + b.getName());
                    System.out.println("Age: " + b.getAge());
                    System.out.println("Phone: " + b.getPhone());
                    System.out.println("Number of tickets: " + b.getTickets());
                    Payment payment = b.getPayment();
                    System.out.println("Payment amount: " + payment.getAmount());
                    System.out.println("Payment method: " + payment.getMethod());
                    System.out.println("Transaction ID: " + payment.getTransactionId());
                    System.out.println("");
                }
            } else if (choice == 5) {
                // Add user
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter age: ");
                int age = scanner.nextInt();
                System.out.print("Enter phone: ");
                String phone = scanner.next();
                System.out.print("Enter email: ");
                String email = scanner.next();
                Users user = new Users(name, age, phone, email);
                users.add(user);
                System.out.println("User added successfully!");
            } else if (choice == 6) {
                // Update user
                System.out.print("Enter name: ");
                String name = scanner.next();
                Users user = null;
                for (Users u : users) {
                    if (u.getName().equals(name)) {
                        user = u;
                        break;
                    }
                }
                if (user == null) {
                    System.out.println("User not found!");
                    continue;
                }
                System.out.print("Enter new age: ");
                int age = scanner.nextInt();
                user.setAge(age);
                System.out.print("Enter new phone: ");
                String phone = scanner.next();
                user.setPhone(phone);
                System.out.println("User updated successfully!");
            } else if (choice == 7) {
                // Delete user
                System.out.print("Enter name: ");
                String name = scanner.next();
                Users user = null;
                for (Users u : users) {
                    if (u.getName().equals(name)) {
                        user = u;
                        break;
                    }
                }
                if (user == null) {
                    System.out.println("User not found!");
                    continue;
                }
                users.remove(user);
                System.out.println("User deleted successfully!");
            } else if (choice == 8) {
                // View users
                for (Users u : users) {
                    System.out.println("Name: " + u.getName());
                    System.out.println("Age: " + u.getAge());
                    System.out.println("Phone: " + u.getPhone());
                    System.out.println("Email: " + u.getEmail());
                    System.out.println("");
                }
            } else if (choice == 9) {
                // Exit
                running = false;
            } else {
                System.out.println("Invalid choice. Please enter a valid choice.");
            }
        }
        scanner.close();
    }
}