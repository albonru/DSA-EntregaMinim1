package edu.upc.dsa;

import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class GameManagerImpl implements GameManager {

    LinkedList<User> listaUsuarios = new LinkedList<User>();
    LinkedList<Objeto> listaObjetos = new LinkedList<>();

    static final Logger logger = Logger.getLogger(GameManagerImpl.class.getName());
    private static GameManagerImpl manager;

    //Singleton
    public static GameManagerImpl getInstance(){
        if(manager==null){
            manager= new GameManagerImpl();
        }
        return manager;
    }

    private GameManagerImpl() {}

    @Override
    public User addUser(String name, String surname, String brithdate, String email, String password) {
        User user = getUserByEmail(email);
        if (user != null) {
            logger.info("User with email " + email + " already exists");
        }
        else {
            user = new User(name,surname,brithdate,email,password);
            this.listaUsuarios.add(user);
            logger.info("User " + name + " created");
        }
        return user;
    }

    @Override
    public List<User> getLlistaUsuaris() {
        LinkedList<User> llista = new LinkedList<User>(this.listaUsuarios);

        Collections.sort(llista, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getName().compareToIgnoreCase(o2.getName()) == 0) {
                    return o1.getSurname().compareToIgnoreCase(o2.getSurname());
                }
                else {
                    return o1.getName().compareToIgnoreCase(o2.getName());
                }
            }
        });
        logger.info("Users list: " + llista.toString());
        return llista;
    }

    @Override
    public void loginUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user == null) {
            logger.info("Email not registered, try again");
        }
        else {
            if (user.getPassword().equals(password)) {
                logger.info("Welcome back, " + user.getName());
            }
            else {
                logger.info("Wrong password, please try again");
            }
        }
    }

    @Override
    public void addObject(String name, String description, double coins) {
        Objeto objeto = new Objeto(name, description, coins);
        this.listaObjetos.add(objeto);
        logger.info("New object added: " + name);
    }

    @Override
    public List<Objeto> getListaObjetos() {
        LinkedList<Objeto> llista = new LinkedList<Objeto>(this.listaObjetos);

        Collections.sort(llista, new Comparator<Objeto>() {
            @Override
            public int compare(Objeto o1, Objeto o2) {
                return Double.compare(o1.getPrecio(), o2.getPrecio());
            }
        });
        Collections.reverse(llista);
        logger.info("Users list: " + llista.toString());
        return llista;
    }

    @Override
    public Objeto compraObjeto(String userName, String nameObjeto) {
        User user = getUserByName(userName);
        if (user == null) {
            logger.info("User " + userName + " does not exist");
        }
        else {
            Objeto objeto = getObjetoByName(nameObjeto);
            if (user.getDsaCoins() < objeto.getPrecio()) {
                logger.info("Too poor to buy");
            }
            else {
                user.getListaObjetosComprados().add(objeto);
                double saldo = user.getDsaCoins() - objeto.getPrecio();
                user.setDsaCoins(saldo);
                logger.info("Objeto " + nameObjeto + " comprado");
                logger.info(userName + ", tu saldo restante es de " + saldo + "dsaCoins");
                return objeto;
            }
        }
        return null;
    }

    @Override
    public List<Objeto> objetosComprados(String nameUser) {
        User user = getUserByName(nameUser);
        return user.getListaObjetosComprados();
    }

    // funcions auxiliars

    public User getUserByName(String name) {
        for (User u: this.listaUsuarios) {
            if(u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }

    public User getUserByEmail(String email) {
        for (User u: this.listaUsuarios) {
            if(u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public Objeto getObjetoByName(String name) {
        for (Objeto o: this.listaObjetos) {
            if(o.getName().equals(name)) {
                return o;
            }
        }
        return null;
    }

    public void clear() {
        this.listaUsuarios.clear();
        this.listaObjetos.clear();
    }
}
