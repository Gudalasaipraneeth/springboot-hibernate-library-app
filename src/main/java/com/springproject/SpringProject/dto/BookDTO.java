package com.springproject.SpringProject.dto;

public class BookDTO {
    private int id;
    private String name;
    private String genreName;
    private String description;
    private int price;
    private String image;
    private int weight;
    private int quantity;

    // Constructors
    public BookDTO() {}

    public BookDTO(int id, String name, String genreName, String description, int price, String image, int weight, int quantity) {
        this.id = id;
        this.name = name;
        this.genreName = genreName;
        this.description = description;
        this.price = price;
        this.image = image;
        this.weight = weight;
        this.quantity = quantity;
    }

    // Getters and Setters
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

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
