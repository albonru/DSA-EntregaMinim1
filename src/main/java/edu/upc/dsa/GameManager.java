package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;

import java.util.List;

public interface GameManager {

    public User addUser(String name, String surname, String brithdate, String email, String password);
    public List<User> getLlistaUsuaris();
    public void loginUser(String email, String password);
    public void addObject(String name, String description, double coins);
    public List<Objeto> getListaObjetos();
    public Objeto compraObjeto(String userName, String nameObjeto);
    public List<Objeto> objetosComprados(String nameUser);

    // funcions auxiliars
    public void clear();
    public User getUserByName(String name);
    public User getUserByEmail(String email);
    public Objeto getObjetoByName(String name);
}
