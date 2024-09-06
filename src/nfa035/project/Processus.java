package nfa035.project;

import java.io.Serializable;
import java.util.*;

public class Processus implements Serializable {

    private int processus_id;
    private String status;
    private double duree;
    private String name;
    private String description_processus;
    private HashMap<AutreMatiere, ArrayList<Number>> autreMatiere;
    private HashMap<MatierePremiere, ArrayList<Number>> matierePremiere;
    private HashMap<Employee, Double> employees;
    private static final long serialVersionUID = -6886413015017503581L;

    public Processus(String desc, double d, String name) {
        this.processus_id = IDManager.getNextId(Processus.class.getSimpleName());
        this.description_processus = desc;
        this.status = "Not Started";
        this.duree = d;
        this.name = name;
        this.matierePremiere = new HashMap<MatierePremiere, ArrayList<Number>>();
        this.autreMatiere = new HashMap<AutreMatiere, ArrayList<Number>>();
        this.employees = new HashMap<Employee, Double>();
    }

    public String getDescriptionProcessus() {
        return description_processus;
    }

    public String getStatus() {
        return status;
    }

    public double getDuree() {
        return duree;
    }

    public int getProcessus_id() {
        return processus_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription_processus() {
        return description_processus;
    }

    public HashMap<AutreMatiere, ArrayList<Number>> getAutreMatiere() {
        return autreMatiere;
    }

    public HashMap<MatierePremiere, ArrayList<Number>> getMatierePremiere() {
        return matierePremiere;
    }

    public HashMap<Employee, Double> getEmployee() {
        return employees;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public void setDescription_processus(String description_processus) {
        this.description_processus = description_processus;
    }

    public void addMatierePremiere(MatierePremiere materiel, double quantity, int used) throws Exception, ALreadyExixtsException {
        if (matierePremiere.containsKey(materiel)) {
            throw new ALreadyExixtsException("this matiere is already exists");
        }
        if (quantity < 0) {
            throw new Exception("quantitiy is positif");
        }
        ArrayList<Number> a = new ArrayList<Number>();
        a.add(quantity);
        a.add(used);
        matierePremiere.put(materiel, a);
    }

    public void addAutreMatiere(AutreMatiere materiel, double quantity, int used) throws Exception, ALreadyExixtsException {
        if (autreMatiere.containsKey(materiel)) {
            throw new ALreadyExixtsException("this matiere is already exists");
        }
        if (quantity < 0) {
            throw new Exception("quantitiy is positif");
        }
        ArrayList<Number> a = new ArrayList<Number>();
        a.add(quantity);
        a.add(used);
        autreMatiere.put(materiel, a);
    }

    public void addEmployee(Employee emp, double nbHeurs) throws Exception, ALreadyExixtsException {
        if (employees.containsKey(emp)) {
            throw new ALreadyExixtsException("this employee is already exists");
        }
        if (nbHeurs < 0) {
            throw new Exception("quantitiy is positif");
        }

        employees.put(emp, nbHeurs);
    }

    public void addEmpNbHeures(Employee emp, double nbHeure) throws Exception {
        if (nbHeure < 0) {
            throw new NonValidHeure("L'heure que vous avez mentionnée n'est pas valide");
        }
        if (employees.containsKey(emp)) {
            employees.put(emp, nbHeure);
        } else {
            throw new EmployeeNotFound("L'employé n'est pas trouvé");
        }
    }

    public void editMatierePremiereQuantite(MatierePremiere mp, double quantity, int used) throws Exception {
        if (quantity < 0) {
            throw new NonValidQuantity("La quantité que vous avez mentionnée n'est pas valide");
        }
        if (matierePremiere.containsKey(mp)) {
            ArrayList<Number> a = new ArrayList<Number>();
            a.add(quantity);
            a.add(used);
            matierePremiere.put(mp, a);
        } else {
            throw new MaterialNotFound("Matière n'est pas trouvée");
        }
    }

    public void editAutreMatiereQuantite(AutreMatiere am, double quantity, int used) throws Exception {
        if (quantity < 0) {
            throw new NonValidQuantity("La quantité que vous avez mentionnée n'est pas valide");
        }
        if (autreMatiere.containsKey(am)) {
            ArrayList<Number> a = new ArrayList<Number>();
            a.add(quantity);
            a.add(used);
            autreMatiere.put(am, a);
        } else {
            throw new MaterialNotFound("Matière n'est pas trouvée");
        }
    }

    public double calculeCoutHumain() {
        double cout = 0.0;
        Set<Map.Entry<Employee, Double>> coupleEmpH = employees.entrySet();
        for (Map.Entry<Employee, Double> empH : coupleEmpH) {
            double tarrifHeur = empH.getKey().getFonction().getTarrifHeure();
            cout += (empH.getValue() * tarrifHeur);
        }
        return Math.round(cout);
    }

    public double calculeCoutUsedMatieresPremieres() {//Only used
        double cout = 0.0;
        Set<MatierePremiere> mpSet = matierePremiere.keySet();
        for (MatierePremiere mp : mpSet) {
            ArrayList<Number> quantityList = matierePremiere.get(mp);
            int used = (int) quantityList.get(1);
            if (used == 1) {
                cout += mp.getPrixUnitaire() * (double) quantityList.get(0);
            }
        }
        return Math.round(cout);
    }

    public double calculeCoutUnUsedMatieresPremieres() {//Only used
        double cout = 0.0;
        Set<MatierePremiere> mpSet = matierePremiere.keySet();
        for (MatierePremiere mp : mpSet) {
            ArrayList<Number> quantityList = matierePremiere.get(mp);
            int used = (int) quantityList.get(1);
            if (used == 0) {
                cout += mp.getPrixUnitaire() * (double) quantityList.get(0);
            }
        }
        return Math.round(cout);
    }

    public double calculeCoutMatieresPremieres() {//used and unUsed
        double cout = 0.0;
        Set<MatierePremiere> mpSet = matierePremiere.keySet();
        for (MatierePremiere mp : mpSet) {

            ArrayList<Number> quantityList = matierePremiere.get(mp);

            cout += mp.getPrixUnitaire() * (double) quantityList.get(0);

        }
        return Math.round(cout);
    }

    public double calculeCoutUsedAutreMatieres() { //used
        double cout = 0.0;
        for (AutreMatiere am : autreMatiere.keySet()) {
            ArrayList<Number> quantity = autreMatiere.get(am);
            int used = (int) quantity.get(1);
            if (used == 1) {
                cout += am.getPrixUnitaire() * (double) quantity.get(0);
            }
        }

        return Math.round(cout);
    }

    public double calculeCoutUnUsedAutreMatieres() { //unused
        double cout = 0.0;
        for (AutreMatiere am : autreMatiere.keySet()) {
            ArrayList<Number> quantity = autreMatiere.get(am);
            int used = (int) quantity.get(1);
            if (used == 0) {
                cout += am.getPrixUnitaire() * (double) quantity.get(0);
            }
        }

        return Math.round(cout);
    }

    public double calculeCoutAutreMatieres() { //used and unUsed
        double cout = 0.0;
        for (AutreMatiere am : autreMatiere.keySet()) {
            ArrayList<Number> quantity = autreMatiere.get(am);

            cout += am.getPrixUnitaire() * (double) quantity.get(0);

        }

        return Math.round(cout);
    }

    public double calculeCoutUsed() {
        double totalCost = calculeCoutHumain() + calculeCoutUsedMatieresPremieres() + calculeCoutUsedAutreMatieres();
        return totalCost;
    }

    public double calculeCout() {
        double totalCost = calculeCoutHumain() + calculeCoutMatieresPremieres() + calculeCoutAutreMatieres();
        return totalCost;
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
    }

    public void removeMatierePremiere(MatierePremiere mp) {
        matierePremiere.remove(mp);
    }

    public void removeAutreMatiere(AutreMatiere am) {
        autreMatiere.remove(am);
    }

   

@Override
public String toString() {
        return "Processus{" + "processus_id=" + processus_id + ", status=" + status + ", duree=" + duree + ", name=" + name + ", description_processus=" + description_processus + '}';

}

    class NonValidHeure extends Exception {

    public NonValidHeure(String message) {
        super(message);
    }
}

class EmployeeNotFound extends Exception {

    public EmployeeNotFound(String message) {
        super(message);
    }
}

class NonValidQuantity extends Exception {

    public NonValidQuantity(String message) {
        super(message);

    }
}

class MaterialNotFound extends Exception {

    public MaterialNotFound(String message) {
        super(message);
    }
}

class ALreadyExixtsException extends Exception {

    public ALreadyExixtsException(String message) {
        super(message);
    }

}

}
