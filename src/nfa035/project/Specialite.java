package nfa035.project;

import java.io.Serializable;

public class Specialite implements Serializable {

    
    private final int specialiteId;
    private String description;
    private static final long serialVersionUID = -7411320839089038258L;

    public Specialite(String desc) {
        this.description = desc;
        this.specialiteId =IDManager.getNextId(Specialite.class.getSimpleName());
    }

    public int getId() {
        return specialiteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;

    }

    @Override
    public String toString() {
        return "Specialite{" + "specialiteId=" + specialiteId + ", description=" + description + '}';
    }

}
