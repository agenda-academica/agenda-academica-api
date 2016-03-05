 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.NotaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class NotaDAO {

		
		List<NotaModel> list = new ArrayList<NotaModel>();
		
		public NotaDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            NotaModel Nota = new NotaModel();
	            Nota.setCodigo(i);
	            //Nota.setNome("name" + i);
	            Nota.setDescricao("celular" +i);
	           // Nota.setEmail( "email"+ i);
	           // Nota.setSenha( "senha"+ i);
	             
	                    list.add(Nota);
	        	
	        }
			
		}

	    public List<NotaModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<NotaModel> findByName(String name) {
	    	List<NotaModel> listName = new ArrayList<NotaModel>();
	    	
	     for(NotaModel item : list ) {
	    	 if(item.getDescricao().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public NotaModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public NotaModel save(NotaModel Nota)
		{
			return Nota.getCodigo() > 0 ? update(Nota) : create(Nota);
		}    
	    
	    public NotaModel create(NotaModel Nota) {
	    	
	    	//
	    	Nota.setCodigo(list.size()+1);
	    	list.add(Nota);
	    	return Nota;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Nota (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Nota.getName());
	            ps.setString(2, Nota.getGrapes());
	            ps.setString(3, Nota.getCountry());
	            ps.setString(4, Nota.getRegion());
	            ps.setString(5, Nota.getYear());
	            ps.setString(6, Nota.getPicture());
	            ps.setString(7, Nota.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Nota.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Nota;*/
	        
	        
	    }

	    public NotaModel update(NotaModel Nota) {
	    	
	    	//
/*	    	list.get(Nota.getId()).setName(Nota.getName());
	    	list.get(Nota.getId()).setGrapes(Nota.getGrapes());

	    	list.get(Nota.getId()).setCountry(Nota.getCountry());
	    	
	    	list.get(Nota.getId()).setGrapes(Nota.getRegion());
	    	list.get(Nota.getId()).setGrapes(Nota.getYear());
	    	list.get(Nota.getId()).setGrapes("bouscat.jpg");
	    	list.get(Nota.getId()).setGrapes(Nota.getDescription());*/
	 
	    	
	    	return Nota;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Nota SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Nota.getName());
	            ps.setString(2, Nota.getGrapes());
	            ps.setString(3, Nota.getCountry());
	            ps.setString(4, Nota.getRegion());
	            ps.setString(5, Nota.getYear());
	            ps.setString(6, Nota.getPicture());
	            ps.setString(7, Nota.getDescription());
	            ps.setInt(8, Nota.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Nota;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Nota WHERE id=?");
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

	    protected NotaModel processRow(ResultSet rs) throws SQLException {
	    	NotaModel Nota = new NotaModel();
	    	  /* Nota.setCodigo(i);
	            Nota.setNome("name" + i);
	            Nota.setCelular("celular" +i);
	            Nota.setEmail( "email"+ i);
	            Nota.setSenha( "senha"+ i);
	        Nota.setId(rs.getInt("id"));
	        Nota.setName(rs.getString("name"));
	        Nota.setGrapes(rs.getString("grapes"));
	        Nota.setCountry(rs.getString("country"));
	        Nota.setRegion(rs.getString("region"));
	        Nota.setYear(rs.getString("year"));
	        Nota.setPicture(rs.getString("picture"));
	        Nota.setDescription(rs.getString("description"));*/
	        return Nota;
	    }
	    
	}

