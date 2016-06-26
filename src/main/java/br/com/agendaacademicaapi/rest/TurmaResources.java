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

import dao.TurmaDAO;
import model.RequestStatusModel;
import model.TurmaModel;

@Path("/turma")
public class TurmaResources {

    TurmaDAO dao = new TurmaDAO();
    Gson gson = new Gson();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findAll() {
        List<TurmaModel> list = dao.findAll();
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
        List<TurmaModel> list = dao.findByUsuarioId(Integer.parseInt(idUsuario));
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
    public Response create(TurmaModel turma) {
        return Response.ok(
            gson.toJson(dao.create(turma)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(TurmaModel turma, @PathParam("id") int id) {
        turma.setId(id);
        return Response.ok(
            gson.toJson(dao.update(turma)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(@PathParam("id") int id) {
        TurmaModel turma = new TurmaModel();
        turma.setId(id);
        return Response.ok(
            gson.toJson(dao.remove(turma)),
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
