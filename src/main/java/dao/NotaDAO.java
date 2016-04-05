 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.NotaModel;
 
	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class NotaDAO {
 
		
		public NotaDAO(){
 
			
		}

	    public List<NotaModel> findAll() {
	    	
			 
	List<NotaModel> list = new ArrayList<NotaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM nota ORDER BY descricao";
	    	
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

	    
	    public List<NotaModel> findByName(String descricao) {
	    	
	        List<NotaModel> list = new ArrayList<NotaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM nota as e " +
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
	    
	    public NotaModel findById(int id) {
	  
	    	String sql = "SELECT * FROM nota WHERE codigo = ?";
	    	
	    	NotaModel nota = null;
	    	
	        Connection c = null;
	        
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                nota = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return nota;
	    }
	    
	    public List<NotaModel> findByFatherId(int id) {
	    	
	    	 List<NotaModel> list = new ArrayList<NotaModel>();
	  	  
	    	String sql = "SELECT * FROM nota WHERE codigoAula = ?";
 
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

	    public NotaModel save(NotaModel nota)
		{
			return nota.getCodigo() > 0 ? update(nota) : create(nota);
		}    
	    
	    public NotaModel create(NotaModel nota) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO nota (codigoTrabalho, codigoProva, codigoAluno, descricao, nota) VALUES (?, ?, ?, ?, ?)",
	            		
	                new String[] { "ID" });
 
	            	ps.setInt(1, nota.getCodigoTrabalho());
	            	ps.setInt(2, nota.getCodigoProva());
	            	ps.setInt(3, nota.getCodigoAluno());
	            	ps.setString(4, nota.getDescricao());
	            	ps.setInt(5, nota.getNota());
 
	            	//ps.setInt(6, nota.getCodigo());

	 
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            nota.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return nota;
	        
	    }

	    public NotaModel update(NotaModel nota) {
	    	 Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
 
	              PreparedStatement ps = c.prepareStatement("UPDATE nota SET codigoTrabalho=?, codigoProva=?, codigoAluno=?, descricao=?, nota=?  WHERE codigo=?");
	  
	            	ps.setInt(1, nota.getCodigoTrabalho());
	            	ps.setInt(2, nota.getCodigoProva());
	            	ps.setInt(3, nota.getCodigoAluno());
	            	ps.setString(4, nota.getDescricao());
	            	ps.setInt(5, nota.getNota());

	            	ps.setInt(6, nota.getCodigo());
 
	              
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return nota;	    }

 

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM nota WHERE id=?");
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

	    protected NotaModel processRow(ResultSet rs) throws SQLException {
	    	
	    	NotaModel nota = new NotaModel();
 
	    		nota.setCodigo(rs.getInt("codigo"));
	    		nota.setNota(rs.getInt("nota"));
	       		nota.setCodigoAluno(rs.getInt("codigoAluno"));
	    		nota.setDescricao(rs.getString("descricao"));
	    		nota.setCodigoProva(rs.getInt("codigoProva"));
	            nota.setCodigoTrabalho(rs.getInt("codigoTrabalho"));
	            
	        return nota;
	    }
	    
	}


