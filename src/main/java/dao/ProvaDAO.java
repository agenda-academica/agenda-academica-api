	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.ProvaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class ProvaDAO {

		
		//List<ProvaModel> list = new ArrayList<ProvaModel>();
		
		public ProvaDAO(){
		 			
		}
 

		    public List<ProvaModel> findAll() {
		    	
		 
		List<ProvaModel> list = new ArrayList<ProvaModel>();
		        
		        Connection c = null;
		        
		    	String sql = "SELECT * FROM prova ORDER BY descricao";
		    	
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

		    
		    public List<ProvaModel> findByName(String descricao) {
		    	
		        List<ProvaModel> list = new ArrayList<ProvaModel>();
		        
		        Connection c = null;
		        
		    	String sql = "SELECT * FROM prova as e " +
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
		    
		    public ProvaModel findById(int id) {
		  
		    	String sql = "SELECT * FROM prova WHERE codigo = ?";
		    	
		    	ProvaModel prova = null;
		    	
		        Connection c = null;
		        
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement(sql);
		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            while (rs.next()) {
		                prova = processRow(rs);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return prova;
		    }
		    
		    public List<ProvaModel> findByFatherId(int id) {
		    	
		    	 List<ProvaModel> list = new ArrayList<ProvaModel>();
		  	  
		    	String sql = "SELECT * FROM prova WHERE codigoAula = ?";
	 
		        Connection c = null;
		        
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement(sql);
		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
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

		    public ProvaModel save(ProvaModel prova)
			{
				return prova.getCodigo() > 0 ? update(prova) : create(prova);
			}    
		    
		    public ProvaModel create(ProvaModel prova) {
		    	
		    	Connection c = null;
		        PreparedStatement ps = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            ps = c.prepareStatement("INSERT INTO prova (codigoAula, descricao, dataDeEntrega, nota) VALUES (?, ?, ?, ?)",
		                new String[] { "ID" });
		            
		            java.sql.Date dataSql = new java.sql.Date(prova.getDataDeEntrega().getTime());

	 
		            	ps.setInt(1, prova.getCodigoAula());
		            	ps.setString(2, prova.getDescricao());
		            	ps.setDate(3, dataSql);
		            	ps.setString(4, "Sem Not");

			            //depois ver como resolver os itens abaixo!
			            //prova.setListaDeAnexos(listaDeAnexos);
			            // prova.setListaDeNotas(listaDeAnexos);
			            //prova.setNota(listaDeAnexos);
		  
		            	ps.executeUpdate();
		            
		            ResultSet rs = ps.getGeneratedKeys();
		            
		            rs.next();
		            
		            // Update the id in the returned object. This is important as this value must be returned to the client.
		            int id = rs.getInt(1);
		            
		            prova.setCodigo(id);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return prova;
		        
		    }

		    public ProvaModel update(ProvaModel prova) {
		    	 Connection c = null;
		    	  
		          try {
		        	  
		              c = ConnectionHelper.getConnection();
		           
		              PreparedStatement ps = c.prepareStatement("UPDATE prova SET codigoAula=?, descricao=?, dataDeEntrega=? WHERE codigo=?");
		  
		              java.sql.Date dataSql = new java.sql.Date(prova.getDataDeEntrega().getTime());

		              
		            	ps.setInt(1, prova.getCodigoAula());
		            	ps.setString(2, prova.getDescricao());
		            	ps.setDate(3, dataSql);
		            	ps.setInt(4, prova.getCodigo());

			            //depois ver como resolver os itens abaixo!
			            //prova.setListaDeAnexos(listaDeAnexos);
			            // prova.setListaDeNotas(listaDeAnexos);
			            //prova.setNota(listaDeAnexos);
		         
		              
		              ps.executeUpdate();
		          } catch (SQLException e) {
		              e.printStackTrace();
		              throw new RuntimeException(e);
		  		} finally {
		  			ConnectionHelper.close(c);
		  		}
		          return prova;	    }

	 

		    public boolean remove(int id) {
		    	  Connection c = null;
		          try {
		              c = ConnectionHelper.getConnection();
		              PreparedStatement ps = c.prepareStatement("DELETE FROM prova WHERE codigo=?");
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

		    protected ProvaModel processRow(ResultSet rs) throws SQLException {
		    	
		    	ProvaModel prova = new ProvaModel();
		    	
		    		prova.setCodigo(rs.getInt("codigo"));
		    		prova.setDescricao(rs.getString("descricao"));
		    		prova.setCodigoAula(rs.getInt("codigoAula"));
		            prova.setDataDeEntrega(rs.getDate("dataDeEntrega"));
		            
		            //depois ver como resolver os itens abaixo!
		            //prova.setListaDeAnexos(listaDeAnexos);
		            // prova.setListaDeNotas(listaDeAnexos);
		            //prova.setNota(listaDeAnexos);

		        return prova;
		    }
		    
		}




