
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
import dao.MateriaDAO;
import dao.TurmaDAO;
import model.CursoModel;
import model.MateriaModel;
import model.TurmaModel;


	@Path("/curso")
	public class CursoResources {

		CursoDAO dao = new CursoDAO();

		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<CursoModel> findAll() {
			//System.out.println("findAll");
			return dao.findAll();
		}

		@GET @Path("search/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<CursoModel> findByName(@PathParam("query") String query) {
			//System.out.println("findByName: " + query);
			return dao.findByName(query);
		}

		@GET @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public CursoModel findById(@PathParam("id") String codigo) {
			//System.out.println("findById " + codigo);
			return dao.findById(Integer.parseInt(codigo));
		}

		@GET @Path("findByChildrenIdTurmas/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<TurmaModel> findByChildrenIdTurmas(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			TurmaDAO turmaDAO = new TurmaDAO();
			return turmaDAO.findByFatherId(query);
		}

		@GET @Path("findByChildrenIdMaterias/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<MateriaModel> findByChildrenIdMaterias(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			MateriaDAO materiaDAO = new MateriaDAO();
			return materiaDAO.findByFatherIdCurso(query);
		}


		@POST
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public CursoModel create(CursoModel curso) {
			//System.out.println("creating curso");
			return dao.create(curso);
		}

		@PUT @Path("{id}")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public CursoModel update(CursoModel curso) {
			//System.out.println("Updating curso: " + curso.getNome());
			dao.update(curso);
			return curso;
		}

		@DELETE @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void remove(@PathParam("id") int id) {
			dao.remove(id);
		}

	}

