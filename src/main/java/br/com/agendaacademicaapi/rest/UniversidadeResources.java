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

import dao.UniversidadeDAO;
import dao.UnidadeDAO;
import model.UniversidadeModel;
import model.RequestStatusModel;
import model.UnidadeModel;

@Path("/universidade")
public class UniversidadeResources {

    UniversidadeDAO dao = new UniversidadeDAO();
    Gson gson = new Gson();

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response findAll() {
        List<UniversidadeModel> list = dao.findAll();
        if (list.size() < 1) {
            return getRequestStatusOk();
        }

        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UniversidadeModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UniversidadeModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    @GET @Path("usuario/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByUsuarioId(@PathParam("id") String codigoUsuario) {
        List<UniversidadeModel> list = dao.findByUsuarioId(Integer.parseInt(codigoUsuario));
        if (list.size() < 1) {
            return this.getRequestStatusOk();
        }

        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("findByChildrenId/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UnidadeModel> findByChildrenId(@PathParam("query") int query) {
        UnidadeDAO anoDAO = new UnidadeDAO();
        return anoDAO.findByFatherId(query);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response create(UniversidadeModel universidade) {
        return Response.ok(
            gson.toJson(dao.create(universidade)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(UniversidadeModel universidade, @PathParam("id") String id) {
        universidade.setCodigo(Integer.parseInt(id));
        return Response.ok(
            gson.toJson(dao.update(universidade)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(@PathParam("id") int id) {
        return Response.ok(
            gson.toJson(dao.remove(id)),
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
