 
	package dao;

	import java.sql.Connection;
 
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;
	
	import model.TrabalhoModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class TrabalhoDAO {

		
		List<TrabalhoModel> list = new ArrayList<TrabalhoModel>();
		
		public TrabalhoDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            TrabalhoModel Trabalho = new TrabalhoModel();
	            Trabalho.setCodigo(i);
	            Trabalho.setDescricao("name" + i);
	            Trabalho.setDataDeEntrega(new Date());

	             
	                    list.add(Trabalho);
	        	
	        }
			
		}

	    public List<TrabalhoModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<TrabalhoModel> findByName(String name) {
	    	List<TrabalhoModel> listName = new ArrayList<TrabalhoModel>();
	    	
	     for(TrabalhoModel item : list ) {
	    	 if(item.getDescricao().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public TrabalhoModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public TrabalhoModel save(TrabalhoModel trabalho)
		{
			return trabalho.getCodigo() > 0 ? update(trabalho) : create(trabalho);
		}    
	    
	    public TrabalhoModel create(TrabalhoModel trabalho) {
	    	
	    	//
	    	trabalho.setCodigo(list.size()+1);
	    	list.add(trabalho);
	    	return trabalho;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Trabalho (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Trabalho.getName());
	            ps.setString(2, Trabalho.getGrapes());
	            ps.setString(3, Trabalho.getCountry());
	            ps.setString(4, Trabalho.getRegion());
	            ps.setString(5, Trabalho.getYear());
	            ps.setString(6, Trabalho.getPicture());
	            ps.setString(7, Trabalho.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Trabalho.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return trabalho;*/
	        
	        
	    }

	    public TrabalhoModel update(TrabalhoModel trabalho) {
	    	
	    	//
/*	    	list.get(Trabalho.getId()).setName(Trabalho.getName());
	    	list.get(Trabalho.getId()).setGrapes(Trabalho.getGrapes());

	    	list.get(Trabalho.getId()).setCountry(Trabalho.getCountry());
	    	
	    	list.get(Trabalho.getId()).setGrapes(Trabalho.getRegion());
	    	list.get(Trabalho.getId()).setGrapes(Trabalho.getYear());
	    	list.get(Trabalho.getId()).setGrapes("bouscat.jpg");
	    	list.get(Trabalho.getId()).setGrapes(Trabalho.getDescription());*/
	 
	    	
	    	return trabalho;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Trabalho SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Trabalho.getName());
	            ps.setString(2, Trabalho.getGrapes());
	            ps.setString(3, Trabalho.getCountry());
	            ps.setString(4, Trabalho.getRegion());
	            ps.setString(5, Trabalho.getYear());
	            ps.setString(6, Trabalho.getPicture());
	            ps.setString(7, Trabalho.getDescription());
	            ps.setInt(8, Trabalho.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return trabalho;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Trabalho WHERE id=?");
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

	    protected TrabalhoModel processRow(ResultSet rs) throws SQLException {
	    	TrabalhoModel trabalho = new TrabalhoModel();
	    	  /* Trabalho.setCodigo(i);
	            Trabalho.setNome("name" + i);
	            Trabalho.setCelular("celular" +i);
	            Trabalho.setEmail( "email"+ i);
	            Trabalho.setSenha( "senha"+ i);
	        Trabalho.setId(rs.getInt("id"));
	        Trabalho.setName(rs.getString("name"));
	        Trabalho.setGrapes(rs.getString("grapes"));
	        Trabalho.setCountry(rs.getString("country"));
	        Trabalho.setRegion(rs.getString("region"));
	        Trabalho.setYear(rs.getString("year"));
	        Trabalho.setPicture(rs.getString("picture"));
	        Trabalho.setDescription(rs.getString("description"));*/
	        return trabalho;
	    }
	    
	}


