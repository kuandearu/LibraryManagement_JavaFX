package librarymanagement;

import java.util.Date;

public class availableBooks {
    private final String title;
    private final String author;
    private final String genre;
    private final String image;
    private final Date date;

    public availableBooks(String title, String author, String genre, String image, Date date){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }




}
