import java.io.*;
import java.util.*;

/**
 * Library: books, members, borrow/return. Persists to library.dat.
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */
public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATA_FILE = "library.dat";

    public ArrayList<Book> books = new ArrayList<>();
    public ArrayList<Member> members = new ArrayList<>();
    public Map<Integer, Integer> borrowed = new HashMap<>(); // bookId -> memberId

    private int nextBookId = 1;
    private int nextMemberId = 1;

    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Library lib = (Library) ois.readObject();
            this.books = lib.books;
            this.members = lib.members;
            this.borrowed = lib.borrowed != null ? lib.borrowed : new HashMap<>();
            for (Book b : books) if (b.id >= nextBookId) nextBookId = b.id + 1;
            for (Member m : members) if (m.id >= nextMemberId) nextMemberId = m.id + 1;
        } catch (FileNotFoundException e) {
            // fresh start
        } catch (IOException | ClassNotFoundException e) {
            books = new ArrayList<>();
            members = new ArrayList<>();
            borrowed = new HashMap<>();
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    public void addBook(String title, String author, String isbn) {
        books.add(new Book(nextBookId++, title, author, isbn));
    }

    public void removeBook(int id) {
        books.removeIf(b -> b.id == id);
        borrowed.remove(id);
    }

    public void addMember(String name) {
        members.add(new Member(nextMemberId++, name));
    }

    public void removeMember(int id) {
        members.removeIf(m -> m.id == id);
        borrowed.entrySet().removeIf(e -> e.getValue() == id);
    }

    public boolean borrow(int bookId, int memberId) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);
        if (b == null || m == null || !b.available) return false;
        b.available = false;
        borrowed.put(bookId, memberId);
        return true;
    }

    public boolean returnBook(int bookId) {
        if (!borrowed.containsKey(bookId)) return false;
        findBook(bookId).available = true;
        borrowed.remove(bookId);
        return true;
    }

    public Book findBook(int id) {
        for (Book b : books) if (b.id == id) return b;
        return null;
    }

    public Member findMember(int id) {
        for (Member m : members) if (m.id == id) return m;
        return null;
    }
}
