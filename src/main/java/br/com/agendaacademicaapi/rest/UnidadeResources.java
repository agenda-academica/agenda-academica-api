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

import dao.CursoDAO;
import dao.UnidadeDAO;
import model.CursoModel;
import model.RequestStatusModel;
import model.UnidadeModel;
import model.UniversidadeModel;

@Path("/unidade")
public class UnidadeResources {

    UnidadeDAO dao = new UnidadeDAO();
    Gson gson = new Gson();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findAll() {
    	return Response.ok(
    		gson.toJson(dao.findAll()),
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
    public Response findById(@PathParam("id") String id) {
    	return Response.ok(
    		gson.toJson(dao.findById(Integer.parseInt(id))),
    		MediaType.APPLICATION_JSON
    	).build();
    }

    @GET @Path("usuario/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByUsuarioId(@PathParam("id") String idUsuario) {
    	List<UnidadeModel> list = dao.findByUsuarioId(Integer.parseInt(idUsuario));
        if (list.size() < 1) {
            return Response
            	.status(Response.Status.OK)
            	.entity(gson.toJson(new RequestStatusModel(true)))
            	.build();
        }
        return Response.ok(
        	gson.toJson(list), 
        	MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("findByChildrenIdCusro/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByChildrenIdTrabalho(@PathParam("query") int query) {
        CursoDAO trabahoDao = new CursoDAO();
        return Response.ok(
        	gson.toJson(trabahoDao.findByFatherId(query)), 
        	MediaType.APPLICATION_JSON
        ).build();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response create(UnidadeModel unidade) {
    	return Response.ok(
    		gson.toJson(dao.create(unidade)),
    		MediaType.APPLICATION_JSON
    	).build();
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(UnidadeModel unidade, @PathParam("id") String id) {
    	unidade.setId(Integer.parseInt(id));
        return Response.ok(
        	gson.toJson(dao.update(unidade)), 
        	MediaType.APPLICATION_JSON
        ).build();
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(@PathParam("id") int id) {
        RequestStatusModel requestStatus = dao.remove(id);
        return Response.ok(
    		gson.toJson(requestStatus), 
    		MediaType.APPLICATION_JSON
    	).build();
    }

}
