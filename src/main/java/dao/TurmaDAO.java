 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.TurmaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class TurmaDAO {

		
		List<TurmaModel> list = new ArrayList<TurmaModel>();
		
		public TurmaDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            TurmaModel usuario = new TurmaModel();
	            usuario.setCodigo(i);
	            usuario.setNome("name" + i);
	            usuario.setDescricao("descrição" +i);
 	             
	                    list.add(usuario);
	        	
	        }
			
		}

	    public List<TurmaModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<TurmaModel> findByName(String name) {
	    	List<TurmaModel> listName = new ArrayList<TurmaModel>();
	    	
	     for(TurmaModel item : list ) {
	    	 if(item.getNome().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public TurmaModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public TurmaModel save(TurmaModel usuario)
		{
			return usuario.getCodigo() > 0 ? update(usuario) : create(usuario);
		}    
	    
	    public TurmaModel create(TurmaModel usuario) {
	    	
	    	//
	    	usuario.setCodigo(list.size()+1);
	    	list.add(usuario);
	    	return usuario;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO usuario (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, usuario.getName());
	            ps.setString(2, usuario.getGrapes());
	            ps.setString(3, usuario.getCountry());
	            ps.setString(4, usuario.getRegion());
	            ps.setString(5, usuario.getYear());
	            ps.setString(6, usuario.getPicture());
	            ps.setString(7, usuario.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            usuario.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return usuario;*/
	        
	        
	    }

	    public TurmaModel update(TurmaModel usuario) {
	    	
	    	//
/*	    	list.get(usuario.getId()).setName(usuario.getName());
	    	list.get(usuario.getId()).setGrapes(usuario.getGrapes());

	    	list.get(usuario.getId()).setCountry(usuario.getCountry());
	    	
	    	list.get(usuario.getId()).setGrapes(usuario.getRegion());
	    	list.get(usuario.getId()).setGrapes(usuario.getYear());
	    	list.get(usuario.getId()).setGrapes("bouscat.jpg");
	    	list.get(usuario.getId()).setGrapes(usuario.getDescription());*/
	 
	    	
	    	return usuario;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE usuario SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, usuario.getName());
	            ps.setString(2, usuario.getGrapes());
	            ps.setString(3, usuario.getCountry());
	            ps.setString(4, usuario.getRegion());
	            ps.setString(5, usuario.getYear());
	            ps.setString(6, usuario.getPicture());
	            ps.setString(7, usuario.getDescription());
	            ps.setInt(8, usuario.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return usuario;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM usuario WHERE id=?");
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

	    protected TurmaModel processRow(ResultSet rs) throws SQLException {
	    	TurmaModel usuario = new TurmaModel();
	    	  /* usuario.setCodigo(i);
	            usuario.setNome("name" + i);
	            usuario.setCelular("celular" +i);
	            usuario.setEmail( "email"+ i);
	            usuario.setSenha( "senha"+ i);
	        usuario.setId(rs.getInt("id"));
	        usuario.setName(rs.getString("name"));
	        usuario.setGrapes(rs.getString("grapes"));
	        usuario.setCountry(rs.getString("country"));
	        usuario.setRegion(rs.getString("region"));
	        usuario.setYear(rs.getString("year"));
	        usuario.setPicture(rs.getString("picture"));
	        usuario.setDescription(rs.getString("description"));*/
	        return usuario;
	    }
	    
	}

