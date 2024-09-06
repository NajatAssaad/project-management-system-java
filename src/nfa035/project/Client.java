package nfa035.project;

import java.io.Serializable;

public class Client implements Serializable {

   
    private int id;
    private String name;

    public Client(String name) {
        id =  IDManager.getNextId(Client.class.getSimpleName());
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", name=" + name + '}';
    }

}
