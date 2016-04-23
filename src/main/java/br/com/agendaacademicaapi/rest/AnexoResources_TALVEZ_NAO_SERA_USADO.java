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

import dao.AnexoDAO_TALVEZ_NAO_SERA_USADO;
import model.AnexoModel_TALVEZ_NAO_SERA_USADA;

@Path("/anexo")
public class AnexoResources_TALVEZ_NAO_SERA_USADO {

    AnexoDAO_TALVEZ_NAO_SERA_USADO dao = new AnexoDAO_TALVEZ_NAO_SERA_USADO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AnexoModel_TALVEZ_NAO_SERA_USADA findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AnexoModel_TALVEZ_NAO_SERA_USADA create(AnexoModel_TALVEZ_NAO_SERA_USADA anexo) {
        return dao.create(anexo);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public AnexoModel_TALVEZ_NAO_SERA_USADA update(AnexoModel_TALVEZ_NAO_SERA_USADA anexo) {
        dao.update(anexo);
        return anexo;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dao.remove(id);
    }

}
