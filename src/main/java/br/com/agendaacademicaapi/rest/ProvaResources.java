
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

import dao.AnexoDAO;
import dao.ProvaDAO;
import model.AnexoModel;
import model.ProvaModel;

	@Path("/prova")
	public class ProvaResources {

		ProvaDAO dao = new ProvaDAO();

		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<ProvaModel> findAll() {
			//System.out.println("findAll");
			return dao.findAll();
		}

		@GET @Path("search/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<ProvaModel> findByName(@PathParam("query") String query) {
			//System.out.println("findByName: " + query);
			return dao.findByName(query);
		}

		@GET @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public ProvaModel findById(@PathParam("id") String codigo) {
			//System.out.println("findById " + codigo);
			return dao.findById(Integer.parseInt(codigo));
		}


		@GET @Path("findByChildrenId/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<AnexoModel> findByChildrenId(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			AnexoDAO anexoDAO = new AnexoDAO();
			return anexoDAO.findByFatherIdProva(query);
		}

		@POST
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public ProvaModel create(ProvaModel prova) {
			//System.out.println("creating Prova");
			return dao.create(prova);
		}

		@PUT @Path("{id}")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public ProvaModel update(ProvaModel prova) {
			//System.out.println("Updating Prova: " + Prova.getNome());
			dao.update(prova);
			return prova;
		}

		@DELETE @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void remove(@PathParam("id") int id) {
			dao.remove(id);
		}

	}

