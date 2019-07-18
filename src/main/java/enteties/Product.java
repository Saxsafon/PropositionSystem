package enteties;

public class Product {
    String product;
    String description;
    String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Product(){}
    public Product(String product,String description,String price) {
        this.product = product;
        this.description = description;
        this.price = price;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String toString() {return this.product + " " + this.description + " " + this.price;}
}
