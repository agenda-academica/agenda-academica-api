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
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dao.DisciplinaDAO;
import model.DisciplinaModel;
import model.RequestStatusModel;

@Path("/disciplina")
public class DisciplinaResources {

    DisciplinaDAO dao = new DisciplinaDAO();
    Gson gson = new Gson();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findAll() {
        List<DisciplinaModel> list = dao.findAll();
        if (list.size() < 1) {
            return this.getRequestStatusOk();
        }
        
        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByName(@PathParam("query") String query) {
        return Response.ok(
            gson.toJson(dao.findByName(query)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findById(@PathParam("id") int id) {
        return Response.ok(
            gson.toJson(dao.findById(id)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("usuario/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByUsuarioId(@PathParam("id") String idUsuario) {
        List<DisciplinaModel> list = dao.findByUsuarioId(Integer.parseInt(idUsuario));
        if (list.size() < 1) {
            return this.getRequestStatusOk();
        }

        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response create(DisciplinaModel disciplina) {
        return Response.ok(
            gson.toJson(dao.create(disciplina)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(DisciplinaModel disciplina, @PathParam("id") int id) {
        disciplina.setId(id);
        return Response.ok(
            gson.toJson(dao.update(disciplina)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(@PathParam("id") int id) {
        DisciplinaModel disciplina = new DisciplinaModel();
        disciplina.setId(id);
        return Response.ok(
            gson.toJson(dao.remove(disciplina)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    private Response getRequestStatusOk() {
        return Response.ok(
            gson.toJson(new RequestStatusModel(true)),
            MediaType.APPLICATION_JSON
        ).build();
    }
}
