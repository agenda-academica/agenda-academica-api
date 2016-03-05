 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.MateriaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class MateriaDAO {

		
		List<MateriaModel> list = new ArrayList<MateriaModel>();
		
		public MateriaDAO(){
			
				for (int i = 1;i <= 11; i++){
	        	
	            MateriaModel Materia = new MateriaModel();
	            Materia.setCodigo(i);
	            Materia.setNome("name" + i);
	            Materia.setDescricao("desciçao" +i);
	            Materia.setSala( "201"+ i);
	            Materia.setDiaDaSemana( "dia de "+ i);
	             
	                    list.add(Materia);
	        	
	        }
			
		}

	    public List<MateriaModel> findAll() {
	    	
	 
	        return list;
	    }

	    
	    public List<MateriaModel> findByName(String name) {
	    	List<MateriaModel> listName = new ArrayList<MateriaModel>();
	    	
	     for(MateriaModel item : list ) {
	    	 if(item.getNome().equals(name)){
	    		listName.add(item);
	    	 }
	     }
	     return listName;
	    }
	    
	    public MateriaModel findById(int id) {
	  
	        return list.get(id);
	    }

	    public MateriaModel save(MateriaModel Materia)
		{
			return Materia.getCodigo() > 0 ? update(Materia) : create(Materia);
		}    
	    
	    public MateriaModel create(MateriaModel Materia) {
	    	
	    	//
	    	Materia.setCodigo(list.size()+1);
	    	list.add(Materia);
	    	return Materia;
	        
	    	//
	    	
	    	
	    	
	/*        Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO Materia (name, grapes, country, region, year, picture, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            ps.setString(1, Materia.getName());
	            ps.setString(2, Materia.getGrapes());
	            ps.setString(3, Materia.getCountry());
	            ps.setString(4, Materia.getRegion());
	            ps.setString(5, Materia.getYear());
	            ps.setString(6, Materia.getPicture());
	            ps.setString(7, Materia.getDescription());
	            ps.executeUpdate();
	            ResultSet rs = ps.getGeneratedKeys();
	            rs.next();
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            Materia.setId(id);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return Materia;*/
	        
	        
	    }

	    public MateriaModel update(MateriaModel materia) {
	    	
	    	//
/*	    	list.get(Materia.getId()).setName(Materia.getName());
	    	list.get(Materia.getId()).setGrapes(Materia.getGrapes());

	    	list.get(Materia.getId()).setCountry(Materia.getCountry());
	    	
	    	list.get(Materia.getId()).setGrapes(Materia.getRegion());
	    	list.get(Materia.getId()).setGrapes(Materia.getYear());
	    	list.get(Materia.getId()).setGrapes("bouscat.jpg");
	    	list.get(Materia.getId()).setGrapes(Materia.getDescription());*/
	 
	    	
	    	return materia;
	    	//
	    	
	 /*       Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("UPDATE Materia SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?");
	            ps.setString(1, Materia.getName());
	            ps.setString(2, Materia.getGrapes());
	            ps.setString(3, Materia.getCountry());
	            ps.setString(4, Materia.getRegion());
	            ps.setString(5, Materia.getYear());
	            ps.setString(6, Materia.getPicture());
	            ps.setString(7, Materia.getDescription());
	            ps.setInt(8, Materia.getId());
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return materia;*/
	    }

	    public boolean remove(int id) {
	    	
	    	list.remove(id);
	    	return true;
	    	
	       /* Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement("DELETE FROM Materia WHERE id=?");
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

	    protected MateriaModel processRow(ResultSet rs) throws SQLException {
	    	MateriaModel materia = new MateriaModel();
	    	  /* Materia.setCodigo(i);
	            Materia.setNome("name" + i);
	            Materia.setCelular("celular" +i);
	            Materia.setEmail( "email"+ i);
	            Materia.setSenha( "senha"+ i);
	        Materia.setId(rs.getInt("id"));
	        Materia.setName(rs.getString("name"));
	        Materia.setGrapes(rs.getString("grapes"));
	        Materia.setCountry(rs.getString("country"));
	        Materia.setRegion(rs.getString("region"));
	        Materia.setYear(rs.getString("year"));
	        Materia.setPicture(rs.getString("picture"));
	        Materia.setDescription(rs.getString("description"));*/
	        return materia;
	    }
	    
	}

