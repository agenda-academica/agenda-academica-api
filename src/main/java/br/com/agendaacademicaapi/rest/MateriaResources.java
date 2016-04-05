
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

import dao.AulaDAO;
import dao.InstituicaoDeEnsinoDAO;
import dao.MateriaDAO;
import model.AulaModel;
import model.InstituicaoDeEnsinoModel;
import model.MateriaModel;

	@Path("/materia")
	public class MateriaResources {

		MateriaDAO dao = new MateriaDAO();

		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<MateriaModel> findAll() {
			//System.out.println("findAll");
			return dao.findAll();
		}

		@GET @Path("search/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<MateriaModel> findByName(@PathParam("query") String query) {
			//System.out.println("findByName: " + query);
			return dao.findByName(query);
		}

		@GET @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public MateriaModel findById(@PathParam("id") String codigo) {
			//System.out.println("findById " + codigo);
			return dao.findById(Integer.parseInt(codigo));
		}

		@GET @Path("findByChildrenId/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<AulaModel> findByChildrenId(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			AulaDAO aulaDAO = new AulaDAO();
			return aulaDAO.findByFatherId(query);
		}

		@POST
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public MateriaModel create(MateriaModel materia) {
			//System.out.println("creating Materia");
			return dao.create(materia);
		}

		@PUT @Path("{id}")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public MateriaModel update(MateriaModel materia) {
			//System.out.println("Updating Materia: " + materia.getNome());
			dao.update(materia);
			return materia;
		}

		@DELETE @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void remove(@PathParam("id") int id) {
			dao.remove(id);
		}

	}

