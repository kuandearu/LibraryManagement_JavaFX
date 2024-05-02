package librarymanagement;

import java.sql.Date;

public class saveBook {

    private final String title;
    private final String author;
    private final String type;
    private final Date date;
    private final String img;

    public saveBook(String title, String author, String type, Date date, String img) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.date = date;
        this.img = img;
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

    public String getImg() {
        return img;
    }
}
