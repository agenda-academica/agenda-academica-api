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
import dao.UsuarioDAO;
import model.BooleanModel;
import model.InstituicaoDeEnsinoModel;
import model.UsuarioModel;

	@Path("/usuario")
	public class UsuarioResources {

		UsuarioDAO dao = new UsuarioDAO();

		@GET
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<UsuarioModel> findAll() {
			//System.out.println("findAll");
			return dao.findAll();
		}

		@GET @Path("search/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<UsuarioModel> findByName(@PathParam("query") String query) {
			//System.out.println("findByName: " + query);
			return dao.findByName(query);
		}

		@GET @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public UsuarioModel findById(@PathParam("id") String codigo) {
			//System.out.println("findById " + codigo);
			return dao.findById(Integer.parseInt(codigo));
		}

		@GET @Path("findByChildrenId/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public List<InstituicaoDeEnsinoModel> findByChildrenId(@PathParam("query") int query) {
			//System.out.println("findByName: " + query);
			InstituicaoDeEnsinoDAO instituicaoDAO = new InstituicaoDeEnsinoDAO();
			return instituicaoDAO.findByFatherId(query);
		}

/*		@GET @Path("login/{query}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public boolean findByLogin(UsuarioModel usuario) {
			//System.out.println("findByName: " + query);
			return dao.login(usuario);
		}*/

		@POST
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public UsuarioModel create(UsuarioModel usuario) {
			//System.out.println("creating usuario");
			return dao.create(usuario);
		}

		@POST @Path("login")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public BooleanModel login(UsuarioModel usuario) {
			//System.out.println("creating usuario");
			return dao.login(usuario);
		}

		@PUT @Path("{id}")
		@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public UsuarioModel update(UsuarioModel usuario) {
			//System.out.println("Updating usuario: " + usuario.getNome());
			dao.update(usuario);
			return usuario;
		}

		@DELETE @Path("{id}")
		@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
		public void remove(@PathParam("id") int id) {
			dao.remove(id);
		}

	}

