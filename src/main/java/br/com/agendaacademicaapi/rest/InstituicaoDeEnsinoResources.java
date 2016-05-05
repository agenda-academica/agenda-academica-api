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
import dao.UnidadeDAO;
import model.InstituicaoDeEnsinoModel;
import model.UnidadeModel;

@Path("/instituicao")
public class InstituicaoDeEnsinoResources {

    InstituicaoDeEnsinoDAO dao = new InstituicaoDeEnsinoDAO();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<InstituicaoDeEnsinoModel> findAll() {
        return dao.findAll();
    }

    @GET @Path("search/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<InstituicaoDeEnsinoModel> findByName(@PathParam("query") String query) {
        return dao.findByName(query);
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public InstituicaoDeEnsinoModel findById(@PathParam("id") String codigo) {
        return dao.findById(Integer.parseInt(codigo));
    }

    @GET @Path("usuario/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<InstituicaoDeEnsinoModel> findByUsuarioId(@PathParam("id") String codigoUsuario) {
        return dao.findByUsuarioId(Integer.parseInt(codigoUsuario));
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
    public InstituicaoDeEnsinoModel create(InstituicaoDeEnsinoModel Instituicao) {
        return dao.create(Instituicao);
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public InstituicaoDeEnsinoModel update(InstituicaoDeEnsinoModel Instituicao) {
        dao.update(Instituicao);
        return Instituicao;
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public InstituicaoDeEnsinoModel remove(@PathParam("id") int id) {
        return dao.remove(id);
    }

}
