package librarymanagement;

import java.util.Date;

public class userList {
    private String number;
    private String name;
    private Date date;
    private String gender;
    private String phone;
    private String email;
    private String image;
    private String password;


    public userList(String number, String name, Date date, String gender, String phone, String email, String image, String password) {
        this.number = number;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getImage() {
        return image;
    }

    public String getPassword() {
        return password;
    }
}

