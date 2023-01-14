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

public class EventBooking {

    public static void main(String[] args) {
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Users> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

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
                    System.out.println("Event not found!");
                    continue;
                }
                System.out.print("Enter your name: ");
                String userName = scanner.next();
                System.out.print("Enter your age: ");
                int age = scanner.nextInt();
                System.out.print("Enter your phone number: ");
                String phone = scanner.next();
                System.out.print("Enter number of tickets: ");
                int numTickets = scanner.nextInt();
                Booking booking = new Booking(userName, age, phone, numTickets);
                event.addBooking(booking);
                if (!event.book(numTickets)) {
                    System.out.println("Booking failed. Not enough capacity.");
                } else {
                    System.out.println("Booking successful!");
                }
            } else if (choice == 3) {
                // View events
                for (Event e : events) {
                    System.out.println("Event: " + e.getName() + " at " + e.getLocation() + " has capacity for "
                            + e.getCapacity() + " attendees and currently has " + e.getAttendees() + " attendees.");
                }
            } else if (choice == 4) {
                // View bookings for an event
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
                    System.out.println("Event not found!");
                    continue;
                }
                ArrayList<Booking> bookings = event.getBookings();
                for (Booking b : bookings) {
                    System.out.println("Booking by: " + b.getName() + " Age: " + b.getAge() + " Phone: " + b.getPhone()
                            + " Tickets: " + b.getTickets());
                }
                System.out.println("End of bookings for event " + event.getName());
            } else if (choice == 5) {
                // Add a new user
                System.out.print("Enter user name: ");
                String name = scanner.next();
                System.out.print("Enter user age: ");
                int age = scanner.nextInt();
                System.out.print("Enter user phone: ");
                String phone = scanner.next();
                System.out.print("Enter user email: ");
                String email = scanner.next();
                users.add(new Users(name, age, phone, email));
                System.out.println("User added successfully!");
            } else if (choice == 6) {
                // Update a user
                System.out.print("Enter user name: ");
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
                System.out.print("Enter new phone: ");
                String phone = scanner.next();
                System.out.print("Enter new email: ");
                String email = scanner.next();
                // user.age = age;
                // user.phone = phone;
                // user.email = email;
                user.setAge(age);
                user.setPhone(phone);
                user.setEmail(email);
                System.out.println("User updated successfully!");
            } else if (choice == 7) {
                // Delete a user
                System.out.print("Enter user name: ");
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
                // View all users
                for (Users u : users) {
                    System.out.println("Name: " + u.getName() + " Age: " + u.getAge() + " Phone: " + u.getPhone()
                            + " Email: " + u.getEmail());
                }
                System.out.println("End of users list.");
            } else if (choice == 9) {
                // Exit the program
                running = false;
                System.out.println("Exiting program...");
            } else {
                System.out.println("Invalid choice! Please enter a valid choice.");
            }
        }
        scanner.close();
    }
}