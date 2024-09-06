
package nfa035.project;

import java.io.Serializable;

public class Fonction implements Serializable {

    private final int fId;
    private String descriptionF;
    private double tarrifHeure;
    
    private static final long serialVersionUID = 7401289499855115132L;

    public Fonction(String desc, double tarrif) throws Exception {
        this.descriptionF = desc;
        setTarrifHeure(tarrif);
        this.fId = IDManager.getNextId(Fonction.class.getSimpleName());
    }

    public int getFId() {
        return fId;
    }

    public String getDescription() {
        return descriptionF;
    }

    public void setDescription(String desc) {
        this.descriptionF = desc;
    }

    public double getTarrifHeure() {
        return tarrifHeure;
    }

    public void setTarrifHeure(double tarrifHeure) throws InvalidTarrifHeurException {
        if (tarrifHeure <= 0 ) {
            throw new InvalidTarrifHeurException("tarrif heure doit etre positif");
        }
        if (tarrifHeure> 1000)   throw new InvalidTarrifHeurException("tarrif heure doit etre plus petit que 1000");
            
        this.tarrifHeure = tarrifHeure;

    }

    @Override
    public String toString() {
        return "Fonction{" + "fId=" + fId + ", description=" + descriptionF + ", tarrifHeure=" + tarrifHeure + '}';
    }

    class InvalidTarrifHeurException extends Exception {

        public InvalidTarrifHeurException(String message) {
            super(message);
        }

    }

}
