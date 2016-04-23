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

import dao.CursoDAO;
import dao.UnidadeDAO;
import model.CursoModel;
import model.UnidadeModel;

@Path("/unidade")
public class UnidadeResources {

    UnidadeDAO dao = new UnidadeDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UnidadeModel> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<UnidadeModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UnidadeModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    /*
    @GET @Path("findByChildrenIdProva/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<ProvaModel> findByChildrenIdProva(@PathParam("query") int query) {
        ProvaDAO provaDAO = new ProvaDAO();
        return provaDAO.findByFatherId(query);
    }
    */

    /*
    @GET @Path("findByChildrenIdConteudo/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<ConteudoModel> findByChildrenIdConteudo(@PathParam("query") int query) {
        ConteudoDAO conteudoDAO = new ConteudoDAO();
        return conteudoDAO.findByFatherId(query);
    }
    */

    @GET @Path("findByChildrenIdCusro/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<CursoModel> findByChildrenIdTrabalho(@PathParam("query") int query) {
        CursoDAO trabahoDao = new CursoDAO();
        return trabahoDao.findByFatherId(query);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UnidadeModel create(UnidadeModel unidade) {
        return dao.create(unidade);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public UnidadeModel update(UnidadeModel unidade) {
        dao.update(unidade);
        return unidade;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void remove(@PathParam("id") int id) {
        dao.remove(id);
    }

}
