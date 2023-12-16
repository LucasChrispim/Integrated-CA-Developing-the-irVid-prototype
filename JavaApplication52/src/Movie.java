/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gusta
 */
public class Movie {
    import java.util.Date;
    private final String title;
    private final double price;
    private boolean available;
    private Date rentedTime;

    public Movie(String title, double price) {
        this.title = title;
        this.price = price;
        this.available = true;
        this.rentedTime = null;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getRentedTime() {
        return rentedTime;
    }

    public void setRentedTime(Date rentedTime) {
        this.rentedTime = rentedTime;
    }
}


