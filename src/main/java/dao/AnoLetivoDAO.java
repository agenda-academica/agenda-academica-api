 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import model.AnoLetivoModel;
import model.AnoLetivoModel;
 

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	 

	public class AnoLetivoDAO {

		
		public AnoLetivoDAO(){
			
				
	       
			
		}


	    public List<AnoLetivoModel> findAll() {
	    	
	 
	List<AnoLetivoModel> list = new ArrayList<AnoLetivoModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM anoLetivo ORDER BY descricao";
	    	
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

	    
	    public List<AnoLetivoModel> findByName(String descricao) {
	    	
	        List<AnoLetivoModel> list = new ArrayList<AnoLetivoModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM anoLetivo as e " +
				"WHERE UPPER(descricao) LIKE ? " +	
				"ORDER BY descricao";
	    	
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setString(1, "%" + descricao.toUpperCase() + "%");
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
	    
	    public AnoLetivoModel findById(int id) {
	  
	    	String sql = "SELECT * FROM anoLetivo WHERE codigo = ?";
	    	
	    	AnoLetivoModel anoLetivo = null;
	    	
	        Connection c = null;
	        
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                anoLetivo = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return anoLetivo;
	    }
	    
	    public List<AnoLetivoModel> findByFatherId(int id) {
	    	
	    	 List<AnoLetivoModel> list = new ArrayList<AnoLetivoModel>();
	  	  
	    	String sql = "SELECT * FROM anoLetivo WHERE codigoInstituicaoDeEnsino = ?";
 
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

	    public AnoLetivoModel save(AnoLetivoModel anoLetivo)
		{
			return anoLetivo.getCodigo() > 0 ? update(anoLetivo) : create(anoLetivo);
		}    
	    
	    public AnoLetivoModel create(AnoLetivoModel anoLetivo) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO anoLetivo (anoLetivo, descricao, codigoInstituicaoDeEnsino) VALUES (?, ?, ?)",
	                new String[] { "ID" });
 
	            	ps.setString(1, anoLetivo.getAnoLetivo());
	            	ps.setString(2, anoLetivo.getDescricao());
	            	ps.setInt(3, anoLetivo.getCodigoInstituicaoDeEnsino());
	            
	            	//ps.setInt(4, anoLetivo.getCodigo());
	    
		            //depois ver como resolver os itens abaixo!
		            //anoLetivo.setListaDeAnexos(listaDeAnexos);

		    
	  
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            anoLetivo.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return anoLetivo;
	        
	    }

	    public AnoLetivoModel update(AnoLetivoModel anoLetivo) {
	    	 Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
	             		           
	              PreparedStatement ps = c.prepareStatement("UPDATE anoLetivo SET anoLetivo=?, descricao=?, codigoInstituicaoDeEnsino=? WHERE codigo=?");
	  
	  	            	ps.setString(1, anoLetivo.getAnoLetivo());
	  	            	ps.setString(2, anoLetivo.getDescricao());
	  	            	ps.setInt(3, anoLetivo.getCodigoInstituicaoDeEnsino());
	  	            
	  	            	ps.setInt(4, anoLetivo.getCodigo());
	         
	              
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return anoLetivo;	    }

 

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM anoLetivo WHERE codigo=?");
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

	    protected AnoLetivoModel processRow(ResultSet rs) throws SQLException {
	    	
	    	AnoLetivoModel anoLetivo = new AnoLetivoModel();

	    		anoLetivo.setCodigo(rs.getInt("codigo"));
	    		anoLetivo.setAnoLetivo(rs.getString("anoLetivo"));
	    		anoLetivo.setDescricao(rs.getString("descricao"));
	    		anoLetivo.setCodigoInstituicaoDeEnsino(rs.getInt("codigoInstituicaoDeEnsino"));

	            //depois ver como resolver os itens abaixo!
	            //anoLetivo.setListaDeCursos(listaDeCursos);
 

	        return anoLetivo;
	    }
	    
	}




