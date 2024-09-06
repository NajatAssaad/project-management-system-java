package nfa035.project;

import java.io.Serializable;

public class Employee extends Ressource implements Serializable {

    private String firstName;
    private String lastName;
    private Specialite specialite;
    private Fonction fonction;
    private static final long serialVersionUID = 2402363052162619584L;
    
    public Employee(String firstName, String lastName, Specialite specialite, Fonction fonction) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialite = specialite;
        this.fonction = fonction;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    @Override
    public String toString() {
        return "Employee{" + "firstName=" + firstName + ", lastName=" + lastName + 
                ", specialite=" + specialite.toString() + ", fonction=" + fonction.toString() + '}';
    }

}
