package ReservationSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {
    public class Reservation {
        private int id;
        private String name;
        private int numberOfSeats;

        public Reservation(int id, String name, int numberOfSeats) {
            this.id = id;
            this.name = name;
            this.numberOfSeats = numberOfSeats;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getNumberOfSeats() {
            return numberOfSeats;
        }

        @Override
        public String toString() {
            return "Reservation{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", numberOfSeats=" + numberOfSeats +
                    '}';
        }
    }

    private List<Reservation> reservations;
    private int nextId;

    public ReservationSystem() {
        reservations = new ArrayList<>();
        nextId = 1;
    }

    public void addReservation(String name, int numberOfSeats) {
        Reservation reservation = new Reservation(nextId++, name, numberOfSeats);
        reservations.add(reservation);
        System.out.println("Reservation added: " + reservation);
    }

    public void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    public void cancelReservation(int id) {
        Reservation toRemove = null;
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                toRemove = reservation;
                break;
            }
        }
        if (toRemove != null) {
            reservations.remove(toRemove);
            System.out.println("Reservation canceled: " + toRemove);
        } else {
            System.out.println("Reservation with ID " + id + " not found.");
        }
    }

    public void searchReservation(String name) {
        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getName().equalsIgnoreCase(name)) {
                System.out.println(reservation);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reservations found for name: " + name);
        }
    }

    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOnline Reservation System Menu:");
            System.out.println("1. Add a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Search reservation by name");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter number of seats: ");
                    int seats = scanner.nextInt();
                    system.addReservation(name, seats);
                    break;
                case 2:
                    system.viewReservations();
                    break;
                case 3:
                    System.out.print("Enter reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    system.cancelReservation(id);
                    break;
                case 4:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    system.searchReservation(name);
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
