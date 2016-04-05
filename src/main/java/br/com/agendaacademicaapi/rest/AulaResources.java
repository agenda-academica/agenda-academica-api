
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
import dao.AulaDAO;
import dao.ConteudoDAO;
import dao.ProvaDAO;
import dao.TrabalhoDAO;
import model.AnexoModel;
import model.AulaModel;
import model.ConteudoModel;
import model.ProvaModel;
import model.TrabalhoModel;

	@Path("/aula")
	public class AulaResources {

		AulaDAO dao = new AulaDAO();

		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<AulaModel> findAll() {
			//System.out.println("findAll");
			return dao.findAll();
		}

		@GET @Path("search/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<AulaModel> findByName(@PathParam("query") String query) {
			//System.out.println("findByName: " + query);
			return dao.findByName(query);
		}

		@GET @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public AulaModel findById(@PathParam("id") String codigo) {
			//System.out.println("findById " + codigo);
			return dao.findById(Integer.parseInt(codigo));
		}

		@GET @Path("findByChildrenIdProva/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<ProvaModel> findByChildrenIdProva(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			ProvaDAO provaDAO = new ProvaDAO();
			return provaDAO.findByFatherId(query);
		}

		@GET @Path("findByChildrenIdConteudo/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<ConteudoModel> findByChildrenIdConteudo(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			ConteudoDAO conteudoDAO = new ConteudoDAO();
			return conteudoDAO.findByFatherId(query);
		}

		@GET @Path("findByChildrenIdTrabalho/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<TrabalhoModel> findByChildrenIdTrabalho(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			TrabalhoDAO trabahoDao = new TrabalhoDAO();
			return trabahoDao.findByFatherId(query);
		}

		@POST
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public AulaModel create(AulaModel aula) {
			//System.out.println("creating aula");
			return dao.create(aula);
		}

		@PUT @Path("{id}")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public AulaModel update(AulaModel aula) {
			//System.out.println("Updating aula: " + Aula.getNome());
			dao.update(aula);
			return aula;
		}

		@DELETE @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void remove(@PathParam("id") int id) {
			dao.remove(id);
		}

	}

