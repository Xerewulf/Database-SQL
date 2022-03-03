/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaskManager;

/**
 *
 * @author Acer
 */
public class Employer {
    
    private int employer_ID;
    private String name;
    private String surname;

    public Employer(String name, String surname) {
        this.employer_ID = getRandomInteger(100000000, 1000);
        this.name = name;
        this.surname = surname;
    }

    public int getEmployer_ID() {
        return employer_ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setEmployer_ID(int Employer_ID) {
        this.employer_ID = Employer_ID;
    }

    public void setSurname(String Surname) {
        this.surname = Surname;
    }

    public void setName(String Name) {
        this.name = Name;
    }
    
    public Object getByName(String attributeName) 
    {
        switch (attributeName) {
	    case "Employer_ID": return employer_ID;
	    case "Name": return name;
            case "Surname": return surname;
	    default: return null;
	}
    }
    
    public static int getRandomInteger(int maximum, int minimum)
    {
        return ((int) (Math.random()*(maximum - minimum))) + minimum; 
    }
    
    
    
    
}
