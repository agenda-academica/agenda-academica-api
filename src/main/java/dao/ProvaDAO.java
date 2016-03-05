 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.ProvaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class ProvaDAO {

		
		List<ProvaModel> list = new ArrayList<ProvaModel>();
		
		public ProvaDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            ProvaModel Prova = new ProvaModel();
	            Prova.setCodigo(i);
	          /*  Prova.setNome("name" + i);
	            Prova.setCelular("celular" +i);
	            Prova.setEmail( "email"+ i);
	            Prova.setSenha( "senha"+ i);*/
	             
	                    list.add(Prova);
	        	
	        }
			
		}

	    public List<ProvaModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<ProvaModel> findByName(String name) {
	    	List<ProvaModel> listName = new ArrayList<ProvaModel>();
	    	
	     for(ProvaModel item : list ) {
	    	 if(item.getDescricao().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public ProvaModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public ProvaModel save(ProvaModel Prova)
		{
			return Prova.getCodigo() > 0 ? update(Prova) : create(Prova);
		}    
	    
	    public ProvaModel create(ProvaModel Prova) {
	    	
	    	//
	    	Prova.setCodigo(list.size()+1);
	    	list.add(Prova);
	    	return Prova;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Prova (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Prova.getName());
	            ps.setString(2, Prova.getGrapes());
	            ps.setString(3, Prova.getCountry());
	            ps.setString(4, Prova.getRegion());
	            ps.setString(5, Prova.getYear());
	            ps.setString(6, Prova.getPicture());
	            ps.setString(7, Prova.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Prova.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Prova;*/
	        
	        
	    }

	    public ProvaModel update(ProvaModel Prova) {
	    	
	    	//
/*	    	list.get(Prova.getId()).setName(Prova.getName());
	    	list.get(Prova.getId()).setGrapes(Prova.getGrapes());

	    	list.get(Prova.getId()).setCountry(Prova.getCountry());
	    	
	    	list.get(Prova.getId()).setGrapes(Prova.getRegion());
	    	list.get(Prova.getId()).setGrapes(Prova.getYear());
	    	list.get(Prova.getId()).setGrapes("bouscat.jpg");
	    	list.get(Prova.getId()).setGrapes(Prova.getDescription());*/
	 
	    	
	    	return Prova;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Prova SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Prova.getName());
	            ps.setString(2, Prova.getGrapes());
	            ps.setString(3, Prova.getCountry());
	            ps.setString(4, Prova.getRegion());
	            ps.setString(5, Prova.getYear());
	            ps.setString(6, Prova.getPicture());
	            ps.setString(7, Prova.getDescription());
	            ps.setInt(8, Prova.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Prova;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Prova WHERE id=?");
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

	    protected ProvaModel processRow(ResultSet rs) throws SQLException {
	    	ProvaModel Prova = new ProvaModel();
	    	  /* Prova.setCodigo(i);
	            Prova.setNome("name" + i);
	            Prova.setCelular("celular" +i);
	            Prova.setEmail( "email"+ i);
	            Prova.setSenha( "senha"+ i);
	        Prova.setId(rs.getInt("id"));
	        Prova.setName(rs.getString("name"));
	        Prova.setGrapes(rs.getString("grapes"));
	        Prova.setCountry(rs.getString("country"));
	        Prova.setRegion(rs.getString("region"));
	        Prova.setYear(rs.getString("year"));
	        Prova.setPicture(rs.getString("picture"));
	        Prova.setDescription(rs.getString("description"));*/
	        return Prova;
	    }
	    
	}

