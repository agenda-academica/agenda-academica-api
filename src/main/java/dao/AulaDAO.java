 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import model.AulaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class AulaDAO {

		
		List<AulaModel> list = new ArrayList<AulaModel>();
		
		public AulaDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            AulaModel aula = new AulaModel();
	            aula.setCodigo(i);
	            aula.setAssunto ("assumto" + i);
	            aula.setData(new Date());
 
	             
	                    list.add(aula);
	        	
	        }
			
		}

	    public List<AulaModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<AulaModel> findByName(String name) {
	    	List<AulaModel> listName = new ArrayList<AulaModel>();
	    	
	     for(AulaModel item : list ) {
	    	 if(item.getAssunto().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public AulaModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public AulaModel save(AulaModel Aula)
		{
			return Aula.getCodigo() > 0 ? update(Aula) : create(Aula);
		}    
	    
	    public AulaModel create(AulaModel Aula) {
	    	
	    	//
	    	Aula.setCodigo(list.size()+1);
	    	list.add(Aula);
	    	return Aula;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Aula (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Aula.getName());
	            ps.setString(2, Aula.getGrapes());
	            ps.setString(3, Aula.getCountry());
	            ps.setString(4, Aula.getRegion());
	            ps.setString(5, Aula.getYear());
	            ps.setString(6, Aula.getPicture());
	            ps.setString(7, Aula.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Aula.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Aula;*/
	        
	        
	    }

	    public AulaModel update(AulaModel Aula) {
	    	
	    	//
/*	    	list.get(Aula.getId()).setName(Aula.getName());
	    	list.get(Aula.getId()).setGrapes(Aula.getGrapes());

	    	list.get(Aula.getId()).setCountry(Aula.getCountry());
	    	
	    	list.get(Aula.getId()).setGrapes(Aula.getRegion());
	    	list.get(Aula.getId()).setGrapes(Aula.getYear());
	    	list.get(Aula.getId()).setGrapes("bouscat.jpg");
	    	list.get(Aula.getId()).setGrapes(Aula.getDescription());*/
	 
	    	
	    	return Aula;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Aula SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Aula.getName());
	            ps.setString(2, Aula.getGrapes());
	            ps.setString(3, Aula.getCountry());
	            ps.setString(4, Aula.getRegion());
	            ps.setString(5, Aula.getYear());
	            ps.setString(6, Aula.getPicture());
	            ps.setString(7, Aula.getDescription());
	            ps.setInt(8, Aula.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Aula;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Aula WHERE id=?");
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

	    protected AulaModel processRow(ResultSet rs) throws SQLException {
	    	AulaModel Aula = new AulaModel();
	    	  /* Aula.setCodigo(i);
	            Aula.setNome("name" + i);
	            Aula.setCelular("celular" +i);
	            Aula.setEmail( "email"+ i);
	            Aula.setSenha( "senha"+ i);
	        Aula.setId(rs.getInt("id"));
	        Aula.setName(rs.getString("name"));
	        Aula.setGrapes(rs.getString("grapes"));
	        Aula.setCountry(rs.getString("country"));
	        Aula.setRegion(rs.getString("region"));
	        Aula.setYear(rs.getString("year"));
	        Aula.setPicture(rs.getString("picture"));
	        Aula.setDescription(rs.getString("description"));*/
	        return Aula;
	    }
	    
	}

