 
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

		
		//List<TrabalhoModel> list = new ArrayList<TrabalhoModel>();
		
		public TrabalhoDAO(){
	 
			
		}

	    public List<TrabalhoModel> findAll() {
	    	
	 
	List<TrabalhoModel> list = new ArrayList<TrabalhoModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM trabalho ORDER BY descricao";
	    	
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

	    
	    public List<TrabalhoModel> findByName(String descricao) {
	    	
	        List<TrabalhoModel> list = new ArrayList<TrabalhoModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM trabalho as e " +
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
	    
	    public TrabalhoModel findById(int id) {
	  
	    	String sql = "SELECT * FROM trabalho WHERE codigo = ?";
	    	
	    	TrabalhoModel trabalho = null;
	    	
	        Connection c = null;
	        
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                trabalho = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return trabalho;
	    }
	    
	    public List<TrabalhoModel> findByFatherId(int id) {
	    	
	    	 List<TrabalhoModel> list = new ArrayList<TrabalhoModel>();
	  	  
	    	String sql = "SELECT * FROM trabalho WHERE codigoAula = ?";
 
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

	    public TrabalhoModel save(TrabalhoModel trabalho)
		{
			return trabalho.getCodigo() > 0 ? update(trabalho) : create(trabalho);
		}    
	    
	    public TrabalhoModel create(TrabalhoModel trabalho) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO trabalho (codigoAula, descricao, dataDeEntrega, nota) VALUES (?, ?, ?, ?)",
	                new String[] { "ID" });
	            
	            //java.util.Date dataUtil = new java.util.Date();
	            java.sql.Date dataSql = new java.sql.Date(trabalho.getDataDeEntrega().getTime());
 
	            	ps.setInt(1, trabalho.getCodigoAula());
	            	ps.setString(2, trabalho.getDescricao());
	            	ps.setDate(3, dataSql);
	            	//ps.setString(4, trabalho.getNota());
	            	ps.setString(4, "Sem Nota");

		            //depois ver como resolver os itens abaixo!
		            //trabalho.setListaDeAnexos(listaDeAnexos);
		            // trabalho.setListaDeNotas(listaDeAnexos);
		            //trabalho.setNota(listaDeAnexos);
	  
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            trabalho.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return trabalho;
	        
	    }

	    public TrabalhoModel update(TrabalhoModel trabalho) {
	    	 Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
	           
	              PreparedStatement ps = c.prepareStatement("UPDATE trabalho SET codigoAula=?, descricao=?, dataDeEntrega=? WHERE codigo=?");
	  
	              java.sql.Date dataSql = new java.sql.Date(trabalho.getDataDeEntrega().getTime());
	              
	            	ps.setInt(1, trabalho.getCodigoAula());
	            	ps.setString(2, trabalho.getDescricao());
	            	ps.setDate(3, dataSql);
	            	ps.setInt(4, trabalho.getCodigo());

		            //depois ver como resolver os itens abaixo!
		            //trabalho.setListaDeAnexos(listaDeAnexos);
		            // trabalho.setListaDeNotas(listaDeAnexos);
		            //trabalho.setNota(listaDeAnexos);
	         
	              
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return trabalho;	    }

 

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM trabalho WHERE codigo=?");
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

	    protected TrabalhoModel processRow(ResultSet rs) throws SQLException {
	    	
	    	TrabalhoModel trabalho = new TrabalhoModel();
	    	
	    		trabalho.setCodigo(rs.getInt("codigo"));
	    		trabalho.setDescricao(rs.getString("descricao"));
	    		trabalho.setCodigoAula(rs.getInt("codigoAula"));
	            trabalho.setDataDeEntrega(rs.getDate("dataDeEntrega"));
	            
	            //depois ver como resolver os itens abaixo!
	            //trabalho.setListaDeAnexos(listaDeAnexos);
	            // trabalho.setListaDeNotas(listaDeAnexos);
	            //trabalho.setNota(listaDeAnexos);

	        return trabalho;
	    }
	    
	}




