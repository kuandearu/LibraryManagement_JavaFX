package librarymanagement;

import java.sql.Date;

public class returnBook {

    private final String title;
    private final String author;
    private final String type;
    private final Date date;
    private final String image;
    public returnBook(String title, String author, String type, Date date, String image) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
}
