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
import dao.DisciplinaDAO;
import dao.TurmaDAO;
import model.CursoModel;
import model.DisciplinaModel;
import model.RequestStatusModel;
import model.TurmaModel;

@Path("/curso")
public class CursoResources {

    CursoDAO dao = new CursoDAO();
    Gson gson = new Gson();

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findAll() {
        List<CursoModel> list = dao.findAll();
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
        List<CursoModel> list = dao.findByName(query);
        if (list.size() < 1) {
            return this.getRequestStatusOk();
        }
        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findById(@PathParam("id") String codigo) {
        return Response.ok(
            gson.toJson(dao.findById(Integer.parseInt(codigo))),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("usuario/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByUsuarioId(@PathParam("id") String idUsuario) {
        List<CursoModel> list = dao.findByUsuarioId(Integer.parseInt(idUsuario));
        if (list.size() < 1) {
            return this.getRequestStatusOk();
        }

        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("findByChildrenIdTurmas/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByChildrenIdTurmas(@PathParam("query") int query) { 
        TurmaDAO turmaDAO = new TurmaDAO();
        List<TurmaModel> list = turmaDAO.findByFatherId(query);
        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @GET @Path("findByChildrenIdMaterias/{query}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response findByChildrenIdMaterias(@PathParam("query") int query) { 
        DisciplinaDAO materiaDAO = new DisciplinaDAO();
        List<DisciplinaModel> list = materiaDAO.findByFatherIdCurso(query);
        return Response.ok(
            gson.toJson(list),
            MediaType.APPLICATION_JSON
        ).build();
    }


    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response create(CursoModel curso) {
        return Response.ok(
            gson.toJson(dao.create(curso)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @PUT @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response update(CursoModel curso, @PathParam("id") int id) {
        curso.setId(id);
        return Response.ok(
            gson.toJson(dao.update(curso)),
            MediaType.APPLICATION_JSON
        ).build();
    }

    @DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response remove(@PathParam("id") int id) {
        CursoModel curso = new CursoModel();
        curso.setId(id);
        return Response.ok(
            gson.toJson(dao.remove(curso)),
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
