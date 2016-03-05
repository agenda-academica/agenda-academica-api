

	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import model.InstituicaoDeEnsinoModel;
 

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	 

	public class InstituicaoDeEnsinoDAO {

		
		List<InstituicaoDeEnsinoModel> list = new ArrayList<InstituicaoDeEnsinoModel>();
		
		public InstituicaoDeEnsinoDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            InstituicaoDeEnsinoModel instituicaoDeEnsino = new InstituicaoDeEnsinoModel();
	            instituicaoDeEnsino.setCodigo(i);
	            instituicaoDeEnsino.setNome("isntituica" + i);
	            instituicaoDeEnsino.setProfessor(true); 
	            instituicaoDeEnsino.setEmail( "email"+ i);
	            instituicaoDeEnsino.setDescricao("descricao" +i);
	            instituicaoDeEnsino.setSite("salveeeeeeee" +i);
	            instituicaoDeEnsino.setTelefone("asdasdasdas" +i); 
	            instituicaoDeEnsino.setUnidade("mooca" +i);
	             
	                    list.add(instituicaoDeEnsino);
	        	
	        }
			
		}

	    public List<InstituicaoDeEnsinoModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<InstituicaoDeEnsinoModel> findByName(String name) {
	    	List<InstituicaoDeEnsinoModel> listName = new ArrayList<InstituicaoDeEnsinoModel>();
	    	
	     for(InstituicaoDeEnsinoModel item : list ) {
	    	 if(item.getNome().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public InstituicaoDeEnsinoModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public InstituicaoDeEnsinoModel save(InstituicaoDeEnsinoModel instituicaoDeEnsino)
		{
			return instituicaoDeEnsino.getCodigo() > 0 ? update(instituicaoDeEnsino) : create(instituicaoDeEnsino);
		}    
	    
	    public InstituicaoDeEnsinoModel create(InstituicaoDeEnsinoModel instituicaoDeEnsino) {
	    	
	    	//
	    	instituicaoDeEnsino.setCodigo(list.size()+1);
	    	list.add(instituicaoDeEnsino);
	    	return instituicaoDeEnsino;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO instituicaoDeEnsino (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, instituicaoDeEnsino.getName());
	            ps.setString(2, instituicaoDeEnsino.getGrapes());
	            ps.setString(3, instituicaoDeEnsino.getCountry());
	            ps.setString(4, instituicaoDeEnsino.getRegion());
	            ps.setString(5, instituicaoDeEnsino.getYear());
	            ps.setString(6, instituicaoDeEnsino.getPicture());
	            ps.setString(7, instituicaoDeEnsino.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            instituicaoDeEnsino.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return instituicaoDeEnsino;*/
	        
	        
	    }

	    public InstituicaoDeEnsinoModel update(InstituicaoDeEnsinoModel instituicaoDeEnsino) {
	    	
	    	//
/*	    	list.get(instituicaoDeEnsino.getId()).setName(instituicaoDeEnsino.getName());
	    	list.get(instituicaoDeEnsino.getId()).setGrapes(instituicaoDeEnsino.getGrapes());

	    	list.get(instituicaoDeEnsino.getId()).setCountry(instituicaoDeEnsino.getCountry());
	    	
	    	list.get(instituicaoDeEnsino.getId()).setGrapes(instituicaoDeEnsino.getRegion());
	    	list.get(instituicaoDeEnsino.getId()).setGrapes(instituicaoDeEnsino.getYear());
	    	list.get(instituicaoDeEnsino.getId()).setGrapes("bouscat.jpg");
	    	list.get(instituicaoDeEnsino.getId()).setGrapes(instituicaoDeEnsino.getDescription());*/
	 
	    	
	    	return instituicaoDeEnsino;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE instituicaoDeEnsino SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, instituicaoDeEnsino.getName());
	            ps.setString(2, instituicaoDeEnsino.getGrapes());
	            ps.setString(3, instituicaoDeEnsino.getCountry());
	            ps.setString(4, instituicaoDeEnsino.getRegion());
	            ps.setString(5, instituicaoDeEnsino.getYear());
	            ps.setString(6, instituicaoDeEnsino.getPicture());
	            ps.setString(7, instituicaoDeEnsino.getDescription());
	            ps.setInt(8, instituicaoDeEnsino.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return instituicaoDeEnsino;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM instituicaoDeEnsino WHERE id=?");
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

	    protected InstituicaoDeEnsinoModel processRow(ResultSet rs) throws SQLException {
	    	InstituicaoDeEnsinoModel instituicaoDeEnsino = new InstituicaoDeEnsinoModel();
	    	  /* instituicaoDeEnsino.setCodigo(i);
	            instituicaoDeEnsino.setNome("name" + i);
	            instituicaoDeEnsino.setCelular("celular" +i);
	            instituicaoDeEnsino.setEmail( "email"+ i);
	            instituicaoDeEnsino.setSenha( "senha"+ i);
	        instituicaoDeEnsino.setId(rs.getInt("id"));
	        instituicaoDeEnsino.setName(rs.getString("name"));
	        instituicaoDeEnsino.setGrapes(rs.getString("grapes"));
	        instituicaoDeEnsino.setCountry(rs.getString("country"));
	        instituicaoDeEnsino.setRegion(rs.getString("region"));
	        instituicaoDeEnsino.setYear(rs.getString("year"));
	        instituicaoDeEnsino.setPicture(rs.getString("picture"));
	        instituicaoDeEnsino.setDescription(rs.getString("description"));*/
	        return instituicaoDeEnsino;
	    }
	    
	}

