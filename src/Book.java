import java.io.Serializable;

/**
 * Book model: id, title, author, isbn, available.
 * Author: Bisman Singh <bismanmadaan1@gmail.com>
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    public int id;
    public String title;
    public String author;
    public String isbn;
    public boolean available;

    public Book(int id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }
}
