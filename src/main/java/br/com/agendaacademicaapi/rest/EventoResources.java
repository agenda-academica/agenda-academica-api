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

import dao.EventoDAO;
import model.EventoModel;

@Path("/evento")
public class EventoResources {

    EventoDAO dao = new EventoDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<EventoModel> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<EventoModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public EventoModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    /*
    @GET @Path("findByChildrenId/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByChildrenId(@PathParam("query") int query) {
        AnexoDAO_TALVEZ_NAO_SERA_USADO anexoDAO = new AnexoDAO_TALVEZ_NAO_SERA_USADO();
        return anexoDAO.findByFatherIdevento(query);
    }
    */

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public EventoModel create(EventoModel evento) {
        return dao.create(evento);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public EventoModel update(EventoModel evento) {
        dao.update(evento);
        return evento;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dao.remove(id);
    }

}
