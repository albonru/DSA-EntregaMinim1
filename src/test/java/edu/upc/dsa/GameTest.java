package edu.upc.dsa;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
    private static Logger logger = Logger.getLogger(GameTest.class);
    GameManager manager = GameManagerImpl.getInstance();

    @Before
    public void setUp() {
        manager.addUser("Alba","Onrubia","03/07/1999","alba.onrubia@estudiantat.upc.edu","1234");
        manager.addUser("Aleix","Ventura","09/10/1998","aventura@something","f23bui");

        manager.addObject("Microchip","Los mejores del mercado",50.99);
        manager.addObject("Jamon Iberico", "De pata negra de verdad",99.99);
    }

    @After
    public void tearDown() {
        manager.clear();
    }

    @Test
    public void junitTest() {
        logger.info("Condicions inicials: ");
        logger.info("Llista usuaris: " + manager.getLlistaUsuaris().toString());
        logger.info("Llista objectes: " + manager.getListaObjetos().toString());

        logger.info("Es creen dos usuaris nous: Manolito i Jimeno");
        manager.addUser("Manolito","Delgado","28/02/2001","manolito@gmail.com","fno4i");
        manager.addUser("Jimeno", "Jimenez","27/09/1750","jj@outlook.com","ni3");

        logger.info("Es creen dos objectes nous: Escoba i Berengena");
        manager.addObject("Escoba", "Suelos tan brillantes que deslumbran",10.05);
        manager.addObject("Berengena","Producte de proximitat", 1.10);

        logger.info("Manolito se cree rico y compra: escoba, microchip, jamon iberico");
        manager.compraObjeto("Manolito","Escoba");
        manager.compraObjeto("Manolito","Microchip");
        manager.compraObjeto("Manolito","Jamon Iberico");

        logger.info("Filomena es un usuario que no existe. ¿Debería poder comprar un jamon iberico? No");
        manager.compraObjeto("Filomena", "Jamon Iberico");

        logger.info("Jimeno conoce su presupuesto y compra: escoba, berengena");
        manager.compraObjeto("Jimeno","Escoba");
        manager.compraObjeto("Jimeno","Berengena");

        logger.info("Condicions finals: ");
        logger.info("Llista usuaris: " + manager.getLlistaUsuaris().toString());
        logger.info("Llista objectes: " + manager.getListaObjetos().toString());

        logger.info("Compres fetes per Manolito: " + manager.objetosComprados("Manolito"));
        logger.info("Compres fetes per Jimeno: " + manager.objetosComprados("Jimeno"));
    }
}
