package librarymanagement;

import java.util.Date;

public class userList {
    private String role;
    private String number;
    private String name;
    private Date date;
    private String gender;
    private String phone;
    private String email;
    private String image;
    private String password;


    public userList(String role,String number, String name, Date date, String gender, String phone, String email, String image, String password) {
        this.role = role;
        this.number = number;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.password = password;
    }

    public String getRole() {
        return role;
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

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

