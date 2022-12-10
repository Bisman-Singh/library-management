import java.util.Scanner;

/**
 * Library Management - Menu-driven CLI.
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */
public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.load();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Management ===");
            System.out.println("1. Add book");
            System.out.println("2. Remove book");
            System.out.println("3. Add member");
            System.out.println("4. Remove member");
            System.out.println("5. Borrow book");
            System.out.println("6. Return book");
            System.out.println("7. List books");
            System.out.println("8. List members");
            System.out.println("9. Quit");
            System.out.print("Choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Author: ");
                    String author = sc.nextLine().trim();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine().trim();
                    lib.addBook(title, author, isbn);
                    lib.save();
                    System.out.println("Book added.");
                    break;
                case "2":
                    System.out.print("Book ID: ");
                    lib.removeBook(Integer.parseInt(sc.nextLine().trim()));
                    lib.save();
                    System.out.println("Book removed.");
                    break;
                case "3":
                    System.out.print("Member name: ");
                    lib.addMember(sc.nextLine().trim());
                    lib.save();
                    System.out.println("Member added.");
                    break;
                case "4":
                    System.out.print("Member ID: ");
                    lib.removeMember(Integer.parseInt(sc.nextLine().trim()));
                    lib.save();
                    System.out.println("Member removed.");
                    break;
                case "5":
                    System.out.print("Book ID: ");
                    int bid = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Member ID: ");
                    int mid = Integer.parseInt(sc.nextLine().trim());
                    if (lib.borrow(bid, mid)) {
                        System.out.println("Book borrowed.");
                    } else {
                        System.out.println("Cannot borrow.");
                    }
                    lib.save();
                    break;
                case "6":
                    System.out.print("Book ID: ");
                    if (lib.returnBook(Integer.parseInt(sc.nextLine().trim()))) {
                        System.out.println("Book returned.");
                    } else {
                        System.out.println("Cannot return.");
                    }
                    lib.save();
                    break;
                case "7":
                    for (Book b : lib.books) {
                        System.out.println(b.id + ": " + b.title + " by " + b.author + " [" + (b.available ? "available" : "borrowed") + "]");
                    }
                    break;
                case "8":
                    for (Member m : lib.members) {
                        System.out.println(m.id + ": " + m.name);
                    }
                    break;
                case "9":
                    lib.save();
                    System.out.println("Goodbye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
