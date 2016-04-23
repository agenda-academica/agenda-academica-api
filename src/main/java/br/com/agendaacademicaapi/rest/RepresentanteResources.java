package br.com.agendaacademicaapi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.InstituicaoDeEnsinoDAO;
import dao.RepresentanteDAO;
import model.InstituicaoDeEnsinoModel;
import model.RepresentanteModel;

@Path("/representante")
public class RepresentanteResources {

    RepresentanteDAO dao = new RepresentanteDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<RepresentanteModel> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<RepresentanteModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RepresentanteModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    @GET @Path("findByChildrenId/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<InstituicaoDeEnsinoModel> findByChildrenId(@PathParam("query") int query) {
        InstituicaoDeEnsinoDAO instituicaoDAO = new InstituicaoDeEnsinoDAO();
        return instituicaoDAO.findByFatherId(query);
    }

    /*
    @GET @Path("login/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public boolean findByLogin(RepresentanteModel representante) {
        return dao.login(representante);
    }
    */

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RepresentanteModel create(RepresentanteModel representante) {
        return dao.create(representante);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public RepresentanteModel update(RepresentanteModel representante) {
        dao.update(representante);
        return representante;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dao.remove(id);
    }

}
