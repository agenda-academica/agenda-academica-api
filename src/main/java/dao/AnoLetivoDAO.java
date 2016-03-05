 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import model.AnoLetivoModel;
 

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	 

	public class AnoLetivoDAO {

		
		List<AnoLetivoModel> list = new ArrayList<AnoLetivoModel>();
		
		public AnoLetivoDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            AnoLetivoModel AnoLetivoDeEnsino = new AnoLetivoModel();
	            
	            AnoLetivoDeEnsino.setCodigo(i);
	            AnoLetivoDeEnsino.setAnoLetivo("201" + i);
	            AnoLetivoDeEnsino.setDescricao("descricao do ano 201" + i);
	             
	                    list.add(AnoLetivoDeEnsino);
	        	
	        }
			
		}

	    public List<AnoLetivoModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<AnoLetivoModel> findByName(String name) {
	    	List<AnoLetivoModel> listName = new ArrayList<AnoLetivoModel>();
	    	
	     for(AnoLetivoModel item : list ) {
	    	 if(item.getAnoLetivo().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public AnoLetivoModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public AnoLetivoModel save(AnoLetivoModel AnoLetivoDeEnsino)
		{
			return AnoLetivoDeEnsino.getCodigo() > 0 ? update(AnoLetivoDeEnsino) : create(AnoLetivoDeEnsino);
		}    
	    
	    public AnoLetivoModel create(AnoLetivoModel AnoLetivoDeEnsino) {
	    	
	    	//
	    	AnoLetivoDeEnsino.setCodigo(list.size()+1);
	    	list.add(AnoLetivoDeEnsino);
	    	return AnoLetivoDeEnsino;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO AnoLetivoDeEnsino (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, AnoLetivoDeEnsino.getName());
	            ps.setString(2, AnoLetivoDeEnsino.getGrapes());
	            ps.setString(3, AnoLetivoDeEnsino.getCountry());
	            ps.setString(4, AnoLetivoDeEnsino.getRegion());
	            ps.setString(5, AnoLetivoDeEnsino.getYear());
	            ps.setString(6, AnoLetivoDeEnsino.getPicture());
	            ps.setString(7, AnoLetivoDeEnsino.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            AnoLetivoDeEnsino.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return AnoLetivoDeEnsino;*/
	        
	        
	    }

	    public AnoLetivoModel update(AnoLetivoModel AnoLetivoDeEnsino) {
	    	
	    	//
/*	    	list.get(AnoLetivoDeEnsino.getId()).setName(AnoLetivoDeEnsino.getName());
	    	list.get(AnoLetivoDeEnsino.getId()).setGrapes(AnoLetivoDeEnsino.getGrapes());

	    	list.get(AnoLetivoDeEnsino.getId()).setCountry(AnoLetivoDeEnsino.getCountry());
	    	
	    	list.get(AnoLetivoDeEnsino.getId()).setGrapes(AnoLetivoDeEnsino.getRegion());
	    	list.get(AnoLetivoDeEnsino.getId()).setGrapes(AnoLetivoDeEnsino.getYear());
	    	list.get(AnoLetivoDeEnsino.getId()).setGrapes("bouscat.jpg");
	    	list.get(AnoLetivoDeEnsino.getId()).setGrapes(AnoLetivoDeEnsino.getDescription());*/
	 
	    	
	    	return AnoLetivoDeEnsino;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE AnoLetivoDeEnsino SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, AnoLetivoDeEnsino.getName());
	            ps.setString(2, AnoLetivoDeEnsino.getGrapes());
	            ps.setString(3, AnoLetivoDeEnsino.getCountry());
	            ps.setString(4, AnoLetivoDeEnsino.getRegion());
	            ps.setString(5, AnoLetivoDeEnsino.getYear());
	            ps.setString(6, AnoLetivoDeEnsino.getPicture());
	            ps.setString(7, AnoLetivoDeEnsino.getDescription());
	            ps.setInt(8, AnoLetivoDeEnsino.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return AnoLetivoDeEnsino;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM AnoLetivoDeEnsino WHERE id=?");
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

	    protected AnoLetivoModel processRow(ResultSet rs) throws SQLException {
	    	AnoLetivoModel AnoLetivoDeEnsino = new AnoLetivoModel();
	    	  /* AnoLetivoDeEnsino.setCodigo(i);
	            AnoLetivoDeEnsino.setNome("name" + i);
	            AnoLetivoDeEnsino.setCelular("celular" +i);
	            AnoLetivoDeEnsino.setEmail( "email"+ i);
	            AnoLetivoDeEnsino.setSenha( "senha"+ i);
	        AnoLetivoDeEnsino.setId(rs.getInt("id"));
	        AnoLetivoDeEnsino.setName(rs.getString("name"));
	        AnoLetivoDeEnsino.setGrapes(rs.getString("grapes"));
	        AnoLetivoDeEnsino.setCountry(rs.getString("country"));
	        AnoLetivoDeEnsino.setRegion(rs.getString("region"));
	        AnoLetivoDeEnsino.setYear(rs.getString("year"));
	        AnoLetivoDeEnsino.setPicture(rs.getString("picture"));
	        AnoLetivoDeEnsino.setDescription(rs.getString("description"));*/
	        return AnoLetivoDeEnsino;
	    }
	    
	}

