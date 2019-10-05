package models;

/**
 *
 * @author Matheus Max
 */
public class Person {
    
    private String name, cpf, dt_birth;
    private double weight, height;
    
    /* Constructores */
    public Person() {}
    public Person(String name, String cpf, String dt_birth, double weight, double height) {
        this.name = name;
        this.cpf = cpf;
        this.dt_birth = dt_birth;
        this.weight = weight;
        this.height = height;
    }
    
    /* Getters and Setters*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDt_birth() {
        return dt_birth;
    }

    public void setDt_birth(String dt_birth) {
        this.dt_birth = dt_birth;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
}
