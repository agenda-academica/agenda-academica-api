 
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
 
		public ConteudoDAO(){
		 
		}

	 
		    public List<ConteudoModel> findAll() {
		    	
		 
		List<ConteudoModel> list = new ArrayList<ConteudoModel>();
		        
		        Connection c = null;
		        
		    	String sql = "SELECT * FROM conteudo ORDER BY descricao";
		    	
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

		    
		    public List<ConteudoModel> findByName(String descricao) {
		    	
		        List<ConteudoModel> list = new ArrayList<ConteudoModel>();
		        
		        Connection c = null;
		        
		    	String sql = "SELECT * FROM conteudo as e " +
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
		    
		    public ConteudoModel findById(int id) {
		  
		    	String sql = "SELECT * FROM conteudo WHERE codigo = ?";
		    	
		    	ConteudoModel conteudo = null;
		    	
		        Connection c = null;
		        
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement(sql);
		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            while (rs.next()) {
		                conteudo = processRow(rs);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return conteudo;
		    }
		    
		    public List<ConteudoModel> findByFatherId(int id) {
		    	
		    	 List<ConteudoModel> list = new ArrayList<ConteudoModel>();
		  	  
		    	String sql = "SELECT * FROM conteudo WHERE codigoAula = ?";
	 
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

		    public ConteudoModel save(ConteudoModel conteudo)
			{
				return conteudo.getCodigo() > 0 ? update(conteudo) : create(conteudo);
			}    
		    
		    public ConteudoModel create(ConteudoModel conteudo) {
		    	
		    	Connection c = null;
		        PreparedStatement ps = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            ps = c.prepareStatement("INSERT INTO conteudo (codigoAula, descricao, nome) VALUES (?, ?, ?)",
		                new String[] { "ID" });
	 
		            	ps.setInt(1, conteudo.getCodigoAula());
		            	ps.setString(2, conteudo.getDescricao());
		            	ps.setString(3, conteudo.getNome());
		            
		            	//ps.setInt(4, conteudo.getCodigo());
		    
	 
			            //depois ver como resolver os itens abaixo!
			            //conteudo.setListaDeAnexos(listaDeAnexos);

			    
		  
		            	ps.executeUpdate();
		            
		            ResultSet rs = ps.getGeneratedKeys();
		            
		            rs.next();
		            
		            // Update the id in the returned object. This is important as this value must be returned to the client.
		            int id = rs.getInt(1);
		            
		            conteudo.setCodigo(id);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return conteudo;
		        
		    }

		    public ConteudoModel update(ConteudoModel conteudo) {
		    	 Connection c = null;
		    	  
		          try {
		        	  
		              c = ConnectionHelper.getConnection();
		             		           
		              PreparedStatement ps = c.prepareStatement("UPDATE conteudo SET codigoAula=?, descricao=?, nome=? WHERE codigo=?");
		  
		            	ps.setInt(1, conteudo.getCodigoAula());
		            	ps.setString(2, conteudo.getDescricao());
		            	ps.setString(3, conteudo.getNome());
		            	ps.setInt(4, conteudo.getCodigo());

			            //depois ver como resolver os itens abaixo!
			            //conteudo.setListaDeAnexos(listaDeAnexos);
		         
		              
		              ps.executeUpdate();
		          } catch (SQLException e) {
		              e.printStackTrace();
		              throw new RuntimeException(e);
		  		} finally {
		  			ConnectionHelper.close(c);
		  		}
		          return conteudo;	    }

	 

		    public boolean remove(int id) {
		    	  Connection c = null;
		          try {
		              c = ConnectionHelper.getConnection();
		              PreparedStatement ps = c.prepareStatement("DELETE FROM conteudo WHERE codigo=?");
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

		    protected ConteudoModel processRow(ResultSet rs) throws SQLException {
		    	
		    	ConteudoModel conteudo = new ConteudoModel();
		    	
		    		conteudo.setCodigo(rs.getInt("codigo"));
		    		conteudo.setNome(rs.getString("nome"));
		    		conteudo.setDescricao(rs.getString("descricao"));
		    		conteudo.setCodigoAula(rs.getInt("codigoAula"));
 
		            //depois ver como resolver os itens abaixo!
		            //conteudo.setListaDeAnexos(listaDeAnexos);
	 

		        return conteudo;
		    }
		    
		}




