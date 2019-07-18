package enteties;

import java.util.ArrayList;

public class User {

    public String name;
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
    public User (String name) {
        this.name = name;
    }
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public String toString() {return "Name: " + this.name + " LIST: " + this.productArrayList;}


}
