 
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
import model.MateriaModel;
import model.AulaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class AulaDAO {

		public AulaDAO(){
			
	        }
			

	    public List<AulaModel> findAll() {
	    	List<AulaModel> list = new ArrayList<AulaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM aula";
	    	
	        try {
	        	
	            c = ConnectionHelper.getConnection();
	            
	            Statement s = c.createStatement();
	            
	            ResultSet rs = s.executeQuery(sql);
	            
	            while (rs.next()) {
	            	
	                list.add(processRow(rs));
	                
	            }
	            
	        } catch (SQLException e) {
	        	
	            e.printStackTrace();
	            
	            throw new RuntimeException(e);
	            
			} finally {
				
				ConnectionHelper.close(c);
				
			}
	        return list;
	    }

	    
	    public List<AulaModel> findByName(String nome) {
	        List<AulaModel> list = new ArrayList<AulaModel>();
	        Connection c = null;
	    	String sql = "SELECT * FROM aula as e " +
				"WHERE UPPER(nome) LIKE ? " +	
				"ORDER BY nome";
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setString(1, "%" + nome.toUpperCase() + "%");
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	                list.add(processRow(rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return list;
	    }
	    
	    public AulaModel findById(int id) {
	    	String sql = "SELECT * FROM aula WHERE codigo = ?";
	    	AulaModel aula = null;
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                aula = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return aula;
	    }
	    
	    public List<AulaModel> findByFatherId(int id) {
	    	
	    	 List<AulaModel> list = new ArrayList<AulaModel>();
	  	  
	    	String sql = "SELECT * FROM aula WHERE codigoMateria = ?";

	        Connection c = null;
	        
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	list.add(processRow(rs));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return list;
	    }
	    

	    public AulaModel save(AulaModel aula)
		{
			return aula.getCodigo() > 0 ? update(aula) : create(aula);
		}    
	    
	    public AulaModel create(AulaModel aula) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO aula (assunto, codigoMateria, data) VALUES (?, ?, ?)",
	                new String[] { "ID" });
	            
	            java.sql.Date dataSql = new java.sql.Date(aula.getData().getTime());
	            
	            
	            	ps.setString(1, aula.getAssunto());	
	               	ps.setInt(2, aula.getCodigoMateria());	
	            	ps.setDate(3, dataSql);	
	            	
		  



	            	//ps.setString(4, "Salve");
	            
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            aula.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return aula;
	        
	        
	    }

	    public AulaModel update(AulaModel aula) {
	    	
	    	  Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
	              
	 	              PreparedStatement ps = c.prepareStatement("UPDATE aula SET assunto=?, codigoMateria=?, data=? WHERE codigo=?");
	 		      
	 		            java.sql.Date dataSql = new java.sql.Date(aula.getData().getTime());

	 	              
	 			            	ps.setString(1, aula.getAssunto());	
	 			               	ps.setInt(2, aula.getCodigoMateria());	
	 			            	ps.setDate(3, dataSql);	


	 			            	ps.setInt(4, aula.getCodigo());
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return aula;	    }

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM aula WHERE codigo=?");
	              ps.setInt(1, id);
	              int count = ps.executeUpdate();
	              return count == 1;
	          } catch (Exception e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	    }

	    protected AulaModel processRow(ResultSet rs) throws SQLException {
	    	
	    	AulaModel aula = new AulaModel();
	    
	    	 aula.setCodigo(rs.getInt("codigo"));
	         aula.setAssunto(rs.getString("assunto"));
	         aula.setCodigoMateria(rs.getInt("codigoMateria"));
	         aula.setData(rs.getDate("data"));

	        return aula;
	    }
	    
	}

	 