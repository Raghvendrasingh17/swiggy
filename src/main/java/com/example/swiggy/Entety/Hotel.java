package com.example.swiggy.Entety;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel {
    @Id
    @GeneratedValue (strategy= GenerationType.AUTO)
    private int id;
    private String name,price,description,photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Hotel(int id, String name, String price, String description, String photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
    }

    public Hotel(HotelData temp) {
        this.name = temp.getName();
         this.description=temp.getDescription();
         this.price=temp.getPrice();
        this.photo = temp.getPhoto().getOriginalFilename();

    }


    public Hotel() {
    }
}
