package nfa035.project;

import java.io.Serializable;

public abstract class Materiel extends Ressource  implements Serializable{

    private String name;
    private double prixUnitaire;

    public Materiel(String name, double prixUnitaire) throws Exception {
        
        this.name = name;
        setPrixUnitaire(prixUnitaire);
    }

    public String getName() {
        return name;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrixUnitaire(double prixUnitaire) throws Exception {
        if (prixUnitaire <= 0) {
            throw new Exception("le prix unitaire doit etre positif");
        }
        this.prixUnitaire = prixUnitaire;
    }

    @Override
    public String toString() {
        return "Materiel{ id: "+ super.getId() + "   name=" + name + ", prixUnitaire=" + prixUnitaire + '}';
    }

}
