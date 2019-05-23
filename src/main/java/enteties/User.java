package enteties;

import java.util.ArrayList;

public class User {

    public String id;
    ArrayList<Product> productArrayList = new ArrayList<Product>();


    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public void addProduct(Product product){
        productArrayList.add(product);
    }


    public User () {}

    public User (String id) {
        this.id = id;
    }
    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public String toString() {return "ID: " + this.id + " LIST: " + this.productArrayList;}


}
