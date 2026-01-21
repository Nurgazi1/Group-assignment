package assignment3.Entities;

public class Customers {
    private int id;
    private String name;


    public Customers(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getInt() {
        return id;
    }
    public String getName() {
        return name;
    }
}
