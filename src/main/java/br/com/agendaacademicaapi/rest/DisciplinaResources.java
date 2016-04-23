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

import dao.DisciplinaDAO;
import model.DisciplinaModel;

//MUDAR AQUI DE MATERIA PARA DISCIPLINA!
@Path("/disciplina")
public class DisciplinaResources {

    DisciplinaDAO dao = new DisciplinaDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<DisciplinaModel> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<DisciplinaModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DisciplinaModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }


    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DisciplinaModel create(DisciplinaModel disciplina) {
        return dao.create(disciplina);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public DisciplinaModel update(DisciplinaModel disciplina) {
        dao.update(disciplina);
        return disciplina;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dao.remove(id);
    }

}
