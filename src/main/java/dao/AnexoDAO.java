	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.AnexoModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class AnexoDAO {

		
		List<AnexoModel> list = new ArrayList<AnexoModel>();
		
		public AnexoDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            AnexoModel anexo = new AnexoModel();
	            anexo.setCodigo(i);
	            anexo.setNome("name" + i);
	            anexo.setCaminhoArquivo("caminho pdf" +i);
	            anexo.setDescricaoArquivo( "ddescricao"+ i);
	            anexo.setTipoArquivo( "pdf"+ i);
	            anexo.setTamanhoArquivo( "103"+ i + "kbps");
	             
	                    list.add(anexo);
	        	
	        }
			
		}

	    public List<AnexoModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<AnexoModel> findByName(String name) {
	    	List<AnexoModel> listName = new ArrayList<AnexoModel>();
	    	
	     for(AnexoModel item : list ) {
	    	 if(item.getNome().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public AnexoModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public AnexoModel save(AnexoModel anexo)
		{
			return anexo.getCodigo() > 0 ? update(anexo) : create(anexo);
		}    
	    
	    public AnexoModel create(AnexoModel anexo) {
	    	
	    	//
	    	anexo.setCodigo(list.size()+1);
	    	list.add(anexo);
	    	return anexo;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Anexo (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Anexo.getName());
	            ps.setString(2, Anexo.getGrapes());
	            ps.setString(3, Anexo.getCountry());
	            ps.setString(4, Anexo.getRegion());
	            ps.setString(5, Anexo.getYear());
	            ps.setString(6, Anexo.getPicture());
	            ps.setString(7, Anexo.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Anexo.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Anexo;*/
	        
	        
	    }

	    public AnexoModel update(AnexoModel anexo) {
	    	
	    	//
/*	    	list.get(Anexo.getId()).setName(Anexo.getName());
	    	list.get(Anexo.getId()).setGrapes(Anexo.getGrapes());

	    	list.get(Anexo.getId()).setCountry(Anexo.getCountry());
	    	
	    	list.get(Anexo.getId()).setGrapes(Anexo.getRegion());
	    	list.get(Anexo.getId()).setGrapes(Anexo.getYear());
	    	list.get(Anexo.getId()).setGrapes("bouscat.jpg");
	    	list.get(Anexo.getId()).setGrapes(Anexo.getDescription());*/
	 
	    	
	    	return anexo;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Anexo SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Anexo.getName());
	            ps.setString(2, Anexo.getGrapes());
	            ps.setString(3, Anexo.getCountry());
	            ps.setString(4, Anexo.getRegion());
	            ps.setString(5, Anexo.getYear());
	            ps.setString(6, Anexo.getPicture());
	            ps.setString(7, Anexo.getDescription());
	            ps.setInt(8, Anexo.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Anexo;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Anexo WHERE id=?");
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

	    protected AnexoModel processRow(ResultSet rs) throws SQLException {
	    	AnexoModel anexo = new AnexoModel();
	    	  /* Anexo.setCodigo(i);
	            Anexo.setNome("name" + i);
	            Anexo.setCelular("celular" +i);
	            Anexo.setEmail( "email"+ i);
	            Anexo.setSenha( "senha"+ i);
	        Anexo.setId(rs.getInt("id"));
	        Anexo.setName(rs.getString("name"));
	        Anexo.setGrapes(rs.getString("grapes"));
	        Anexo.setCountry(rs.getString("country"));
	        Anexo.setRegion(rs.getString("region"));
	        Anexo.setYear(rs.getString("year"));
	        Anexo.setPicture(rs.getString("picture"));
	        Anexo.setDescription(rs.getString("description"));*/
	        return anexo;
	    }
	    
	}

