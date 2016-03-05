 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.ConteudoModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class ConteudoDAO {

		
		List<ConteudoModel> list = new ArrayList<ConteudoModel>();
		
		public ConteudoDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            ConteudoModel Conteudo = new ConteudoModel();
	            Conteudo.setCodigo(i);
	            Conteudo.setNome("name" + i);
	            Conteudo.setDescricao("celular" +i);
 
	             
	                    list.add(Conteudo);
	        	
	        }
			
		}

	    public List<ConteudoModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<ConteudoModel> findByName(String name) {
	    	List<ConteudoModel> listName = new ArrayList<ConteudoModel>();
	    	
	     for(ConteudoModel item : list ) {
	    	 if(item.getNome().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public ConteudoModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public ConteudoModel save(ConteudoModel Conteudo)
		{
			return Conteudo.getCodigo() > 0 ? update(Conteudo) : create(Conteudo);
		}    
	    
	    public ConteudoModel create(ConteudoModel conteudo) {
	    	
	    	//
	    	conteudo.setCodigo(list.size()+1);
	    	list.add(conteudo);
	    	return conteudo;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Conteudo (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Conteudo.getName());
	            ps.setString(2, Conteudo.getGrapes());
	            ps.setString(3, Conteudo.getCountry());
	            ps.setString(4, Conteudo.getRegion());
	            ps.setString(5, Conteudo.getYear());
	            ps.setString(6, Conteudo.getPicture());
	            ps.setString(7, Conteudo.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Conteudo.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return conteudo;*/
	        
	        
	    }

	    public ConteudoModel update(ConteudoModel conteudo) {
	    	
	    	//
/*	    	list.get(Conteudo.getId()).setName(Conteudo.getName());
	    	list.get(Conteudo.getId()).setGrapes(Conteudo.getGrapes());

	    	list.get(Conteudo.getId()).setCountry(Conteudo.getCountry());
	    	
	    	list.get(Conteudo.getId()).setGrapes(Conteudo.getRegion());
	    	list.get(Conteudo.getId()).setGrapes(Conteudo.getYear());
	    	list.get(Conteudo.getId()).setGrapes("bouscat.jpg");
	    	list.get(Conteudo.getId()).setGrapes(Conteudo.getDescription());*/
	 
	    	
	    	return conteudo;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Conteudo SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Conteudo.getName());
	            ps.setString(2, Conteudo.getGrapes());
	            ps.setString(3, Conteudo.getCountry());
	            ps.setString(4, Conteudo.getRegion());
	            ps.setString(5, Conteudo.getYear());
	            ps.setString(6, Conteudo.getPicture());
	            ps.setString(7, Conteudo.getDescription());
	            ps.setInt(8, Conteudo.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Conteudo;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Conteudo WHERE id=?");
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

	    protected ConteudoModel processRow(ResultSet rs) throws SQLException {
	    	ConteudoModel conteudo = new ConteudoModel();
	    	  /* Conteudo.setCodigo(i);
	            Conteudo.setNome("name" + i);
	            Conteudo.setCelular("celular" +i);
	            Conteudo.setEmail( "email"+ i);
	            Conteudo.setSenha( "senha"+ i);
	        Conteudo.setId(rs.getInt("id"));
	        Conteudo.setName(rs.getString("name"));
	        Conteudo.setGrapes(rs.getString("grapes"));
	        Conteudo.setCountry(rs.getString("country"));
	        Conteudo.setRegion(rs.getString("region"));
	        Conteudo.setYear(rs.getString("year"));
	        Conteudo.setPicture(rs.getString("picture"));
	        Conteudo.setDescription(rs.getString("description"));*/
	        return conteudo;
	    }
	    
	}

