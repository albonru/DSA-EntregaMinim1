package edu.upc.dsa.services;

import edu.upc.dsa.GameManager;
import edu.upc.dsa.models.Objeto;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/DSAminim1", description = "Endpoint to Game Service")
@Path("/users")
public class GameService {
    private GameManager manager;

    public GameService() {}

    @Path("basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    // POST new user
    @POST
    @ApiOperation(value = "create a new User", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {

        if (user.getName()==null || user.getId() == null)
            return Response.status(500).entity(user).build();
        this.manager.addUser(user.getName(), user.getSurname(), user.getBirthdate(), user.getEmail(), user.getPassword());
        return Response.status(201).entity(user).build();
    }

    // GET llista usuaris
    @GET
    @ApiOperation(value = "llista usuaris", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/getLlistaUsuaris")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaUsuaris() {
        List<User> u = this.manager.getLlistaUsuaris();
        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(u) {};
        return Response.status(201).entity(entity).build();
    }

    // login usuari
    @GET
    @ApiOperation(value = "login Usuari", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "Incorrect credentials")
    })
    @Path("/loginUsuari/{email}{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUsuari(@PathParam("email") String email, @PathParam("password") String password) {
        User u = this.manager.getUserByEmail(email);
        if (u != null) {
            if (u.getPassword().equals(password)) {
                return Response.status(201).entity(u).build();
            }
        }
        return Response.status(404).build();
    }

    // POST new objecte
    @POST
    @ApiOperation(value = "create a new Object", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/addObject")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newObjeto(Objeto objeto) {

        if (objeto.getName()==null || objeto.getDescription() == null || objeto.getPrecio() == 0.0)
            return Response.status(500).entity(objeto).build();
        this.manager.addObject(objeto.getName(), objeto.getDescription(), objeto.getPrecio());
        return Response.status(201).entity(objeto).build();
    }

    // GET llista objectes
    @GET
    @ApiOperation(value = "llista objectes", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/getLlistaUsuaris")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaObjectes() {
        List<Objeto> u = this.manager.getListaObjetos();
        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(u) {};
        return Response.status(201).entity(entity).build();
    }

    // compra objecte per part d'un usuari
    @POST
    @ApiOperation(value = "realizar compra", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/comprarObjeto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response comprarObjeto(String userName, String objetoName) {
        Objeto objeto = this.manager.getObjetoByName(objetoName);
        User user = this.manager.getUserByName(userName);
        if (user == null || objeto == null)  return Response.status(500).build();
        this.manager.compraObjeto(user.getName(), objeto.getName());
        return Response.status(201).build();
    }

    // GET llista objectes comprats per un usuari
    @GET
    @ApiOperation(value = "llista objectes comprats per usuari", notes = "no")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
    })
    @Path("/getLlistaObjectesCompratsPerUsuari/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaObjectesCompratsPerUsuari(@PathParam("userName") String userName) {
        List<Objeto> u = this.manager.objetosComprados(userName);
        GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(u) {};
        return Response.status(201).entity(entity).build();
    }
}
