package enteties;

public class User {

    public String id;
    public String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User () {}

    public User (String id) {
        this.id = id;
    }
    public void setId(String id) {this.id = id;}
    public String getId() {return id;}

    public String toString() {return "ID: " + this.id;}


}
