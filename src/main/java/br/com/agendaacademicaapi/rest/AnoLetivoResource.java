
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

	import dao.AnoLetivoDAO;
	import model.AnoLetivoModel;


		@Path("/anoLetivo")
		public class AnoLetivoResource {

			AnoLetivoDAO dao = new AnoLetivoDAO();

			@GET
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public List<AnoLetivoModel> findAll() {
				//System.out.println("findAll");
				return dao.findAll();
			}

			@GET @Path("search/{query}")
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public List<AnoLetivoModel> findByName(@PathParam("query") String query) {
				//System.out.println("findByName: " + query);
				return dao.findByName(query);
			}

			@GET @Path("{id}")
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public AnoLetivoModel findById(@PathParam("id") String codigo) {
				//System.out.println("findById " + codigo);
				return dao.findById(Integer.parseInt(codigo));
			}

			@POST
			@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public AnoLetivoModel create(AnoLetivoModel anoLetivo) {
				//System.out.println("creating anoLetivo");
				return dao.create(anoLetivo);
			}

			@PUT @Path("{id}")
			@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public AnoLetivoModel update(AnoLetivoModel anoLetivo) {
				//System.out.println("Updating anoLetivo: " + anoLetivo.getAnoLetivo());
				dao.update(anoLetivo);
				return anoLetivo;
			}

			@DELETE @Path("{id}")
			@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
			public void remove(@PathParam("id") int id) {
				dao.remove(id);
			}

		}

