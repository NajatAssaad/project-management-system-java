package nfa035.project;

import java.io.Serializable;

public abstract class Ressource implements Serializable {

    final protected int id;
    private static final long serialVersionUID = 7401289499855115132L;

    public Ressource() {
        this.id = IDManager.getNextId(Ressource.class.getSimpleName());
    }

    public int getId() {
        return id;
    }

}
