package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String surname;
    private String birthdate;
    private String email;
    private String password;
    private double dsaCoins;
    private List<Objeto> listaObjetosComprados = null;

    public User() {}

    public User(String name, String surname, String birthdate, String email, String password) {
        this.id = RandomUtils.getId();
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.dsaCoins = 50;
        this.listaObjetosComprados = new LinkedList<>();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDsaCoins() {
        return dsaCoins;
    }

    public void setDsaCoins(double dsaCoins) {
        this.dsaCoins = dsaCoins;
    }

    public List<Objeto> getListaObjetosComprados() {
        return listaObjetosComprados;
    }

    public void setListaObjetosComprados(List<Objeto> listaObjetosComprados) {
        this.listaObjetosComprados = listaObjetosComprados;
    }

    public String toString() {
        return "L'usuari " + this.name + " " + this.surname + ", registrat amb email " + this.email + ", te un saldo actual de " + this.dsaCoins + "dsaCoins\n";
    }
}
