package librarymanagement;

import java.util.Date;

public class availableBooks {
    private int id;
    private String title;
    private String author;
    private String genre;
    private String image;
    private Date date;
    private String status;



    public availableBooks(int id ,String title, String author, String genre, String image, Date date){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.date = date;
    }

    public availableBooks(int id ,String title, String author, String genre, String image, Date date, String status){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.date = date;
        this.status = status;
    }


    public int getId() {
        return id;
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

    public String getStatus() {return  status;}

}
