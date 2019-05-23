package enteties;

public class Product {
    String name;
    String description;
    String picture;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(){}
    public Product(String name,String description,String picture,String price) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {return this.name + " " + this.description + " " + this.picture + " " + this.price;}
}
