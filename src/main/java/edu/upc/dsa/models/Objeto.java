package edu.upc.dsa.models;

public class Objeto {
    private String name;
    private String description;
    private double precio;

    public Objeto() {}

    public Objeto(String name, String description, double precio) {
        this.name = name;
        this.description = description;
        this.precio = precio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString() {
        return "L'objecte " + name + ", descrit com a " + this.description + ", te un preu de " + this.precio + "dsaCoins\n";
    }
}
