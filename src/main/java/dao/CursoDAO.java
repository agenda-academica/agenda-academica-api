 
		package dao;

		import java.sql.Connection;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		import java.util.ArrayList;
		import java.util.List;

	import model.CursoModel;
	 

		/**
		 * @author Sergio Eduardo Bertolazo
		 */
		 

		public class CursoDAO {

			
			List<CursoModel> list = new ArrayList<CursoModel>();
			
			public CursoDAO(){
				
					for (int i = 1;i <= 11; i++){
		        	
		            CursoModel CursoDeEnsino = new CursoModel();
		            
		            CursoDeEnsino.setCodigo(i);
		            CursoDeEnsino.setNome("curso x" + i);
		            CursoDeEnsino.setDescricao("descricao do curso" + i);
		            CursoDeEnsino.setAreaDoConhecimento("exatas");
		       
		                    list.add(CursoDeEnsino);
		        	
		        }
				
			}

		    public List<CursoModel> findAll() {
		    	
		 
		        return list;
		    }

		    
		    public List<CursoModel> findByName(String name) {
		    	List<CursoModel> listName = new ArrayList<CursoModel>();
		    	
		     for(CursoModel item : list ) {
		    	 if(item.getNome().equals(name)){
		    		listName.add(item);
		    	 }
		     }
		     return listName;
		    }
		    
		    public CursoModel findById(int id) {
		  
		        return list.get(id);
		    }

		    public CursoModel save(CursoModel CursoDeEnsino)
			{
				return CursoDeEnsino.getCodigo() > 0 ? update(CursoDeEnsino) : create(CursoDeEnsino);
			}    
		    
		    public CursoModel create(CursoModel CursoDeEnsino) {
		    	
		    	//
		    	CursoDeEnsino.setCodigo(list.size()+1);
		    	list.add(CursoDeEnsino);
		    	return CursoDeEnsino;
		        
		    	//
		    	
		    	
		    	
		/*        Connection c = null;
		        PreparedStatement ps = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            ps = c.prepareStatement("INSERT INTO CursoDeEnsino (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
		                new String[] { "ID" });
		            ps.setString(1, CursoDeEnsino.getName());
		            ps.setString(2, CursoDeEnsino.getGrapes());
		            ps.setString(3, CursoDeEnsino.getCountry());
		            ps.setString(4, CursoDeEnsino.getRegion());
		            ps.setString(5, CursoDeEnsino.getYear());
		            ps.setString(6, CursoDeEnsino.getPicture());
		            ps.setString(7, CursoDeEnsino.getDescription());
		            ps.executeUpdate();
		            ResultSet rs = ps.getGeneratedKeys();
		            rs.next();
		            // Update the id in the returned object. This is important as this value must be returned to the client.
		            int id = rs.getInt(1);
		            CursoDeEnsino.setId(id);
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return CursoDeEnsino;*/
		        
		        
		    }

		    public CursoModel update(CursoModel CursoDeEnsino) {
		    	
		    	//
	/*	    	list.get(CursoDeEnsino.getId()).setName(CursoDeEnsino.getName());
		    	list.get(CursoDeEnsino.getId()).setGrapes(CursoDeEnsino.getGrapes());

		    	list.get(CursoDeEnsino.getId()).setCountry(CursoDeEnsino.getCountry());
		    	
		    	list.get(CursoDeEnsino.getId()).setGrapes(CursoDeEnsino.getRegion());
		    	list.get(CursoDeEnsino.getId()).setGrapes(CursoDeEnsino.getYear());
		    	list.get(CursoDeEnsino.getId()).setGrapes("bouscat.jpg");
		    	list.get(CursoDeEnsino.getId()).setGrapes(CursoDeEnsino.getDescription());*/
		 
		    	
		    	return CursoDeEnsino;
		    	//
		    	
		 /*       Connection c = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement("UPDATE CursoDeEnsino SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
		            ps.setString(1, CursoDeEnsino.getName());
		            ps.setString(2, CursoDeEnsino.getGrapes());
		            ps.setString(3, CursoDeEnsino.getCountry());
		            ps.setString(4, CursoDeEnsino.getRegion());
		            ps.setString(5, CursoDeEnsino.getYear());
		            ps.setString(6, CursoDeEnsino.getPicture());
		            ps.setString(7, CursoDeEnsino.getDescription());
		            ps.setInt(8, CursoDeEnsino.getId());
		            ps.executeUpdate();
		        } catch (SQLException e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return CursoDeEnsino;*/
		    }

		    public boolean remove(int id) {
		    	
		    	list.remove(id);
		    	return true;
		    	
		       /* Connection c = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement("DELETE FROM CursoDeEnsino WHERE id=?");
		            ps.setInt(1, id);
		            int count = ps.executeUpdate();
		            return count == 1;
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}*/
		    }

		    protected CursoModel processRow(ResultSet rs) throws SQLException {
		    	CursoModel curso = new CursoModel();
		    	  /* CursoDeEnsino.setCodigo(i);
		            CursoDeEnsino.setNome("name" + i);
		            CursoDeEnsino.setCelular("celular" +i);
		            CursoDeEnsino.setEmail( "email"+ i);
		            CursoDeEnsino.setSenha( "senha"+ i);
		        CursoDeEnsino.setId(rs.getInt("id"));
		        CursoDeEnsino.setName(rs.getString("name"));
		        CursoDeEnsino.setGrapes(rs.getString("grapes"));
		        CursoDeEnsino.setCountry(rs.getString("country"));
		        CursoDeEnsino.setRegion(rs.getString("region"));
		        CursoDeEnsino.setYear(rs.getString("year"));
		        CursoDeEnsino.setPicture(rs.getString("picture"));
		        CursoDeEnsino.setDescription(rs.getString("description"));*/
		        return curso;
		    }
		    
		}


