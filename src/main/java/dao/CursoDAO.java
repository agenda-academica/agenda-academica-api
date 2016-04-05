 
		package dao;

		import java.sql.Connection;
		import java.sql.PreparedStatement;
		import java.sql.ResultSet;
		import java.sql.SQLException;
		import java.sql.Statement;
		import java.util.ArrayList;
		import java.util.List;

import model.AnoLetivoModel;
import model.CursoModel;
import model.CursoModel;
	 

		/**
		 * @author Sergio Eduardo Bertolazo
		 */
		 

		public class CursoDAO {

 
			public CursoDAO(){
				
 
				
			}


		    public List<CursoModel> findAll() {
		    	List<CursoModel> list = new ArrayList<CursoModel>();
		        
		        Connection c = null;
		        
		    	String sql = "SELECT * FROM curso ORDER BY nome";
		    	
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

		    
		    public List<CursoModel> findByName(String nome) {
		        List<CursoModel> list = new ArrayList<CursoModel>();
		        Connection c = null;
		    	String sql = "SELECT * FROM curso as e " +
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
		    
		    public CursoModel findById(int id) {
		    	String sql = "SELECT * FROM curso WHERE codigo = ?";
		    	CursoModel curso = null;
		        Connection c = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            PreparedStatement ps = c.prepareStatement(sql);
		            ps.setInt(1, id);
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                curso = processRow(rs);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return curso;
		    }
		    
		    public List<CursoModel> findByFatherId(int id) {
		    	
		    	 List<CursoModel> list = new ArrayList<CursoModel>();
		  	  
		    	String sql = "SELECT * FROM curso WHERE codigoAnoLetivo = ?";
	 
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

		    public CursoModel save(CursoModel curso)
			{
				return curso.getCodigo() > 0 ? update(curso) : create(curso);
			}    
		    
		    public CursoModel create(CursoModel curso) {
		    	
		    	Connection c = null;
		        PreparedStatement ps = null;
		        try {
		            c = ConnectionHelper.getConnection();
		            ps = c.prepareStatement("INSERT INTO curso (nome, descricao, codigoAnoLetivo, areaDoConhecimento) VALUES (?, ?, ?, ?)",
		                new String[] { "ID" });
 
		            	ps.setString(1, curso.getNome());	
		            	ps.setString(2, curso.getDescricao());
		               	ps.setInt(3, curso.getCodigoAnoLetivo());	
		            	ps.setString(4, curso.getAreaDoConhecimento());	

				         //curso.setListaDeMaterias(rs.getString("listaDeMaterias"));
				        // curso.setListaDeTurmas(rs.getString("listaDeTurmas"));
		 
		            	//ps.setInt(5, curso.getCodigo());
		            
		            	ps.executeUpdate();
		            
		            ResultSet rs = ps.getGeneratedKeys();
		            
		            rs.next();
		            
		            // Update the id in the returned object. This is important as this value must be returned to the client.
		            int id = rs.getInt(1);
		            
		            curso.setCodigo(id);
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
				} finally {
					ConnectionHelper.close(c);
				}
		        return curso;
		        
		        
		    }

		    public CursoModel update(CursoModel curso) {
		    	
		    	  Connection c = null;
		    	  
		          try {
		        	  
		              c = ConnectionHelper.getConnection();

		              
		 	              PreparedStatement ps = c.prepareStatement("UPDATE curso SET nome=?, descricao=?, codigoAnoLetivo=?, areaDoConhecimento=? WHERE codigo=?");
 
			            	ps.setString(1, curso.getNome());	
			            	ps.setString(2, curso.getDescricao());
			               	ps.setInt(3, curso.getCodigoAnoLetivo());	
			            	ps.setString(4, curso.getAreaDoConhecimento());	

					         //curso.setListaDeMaterias(rs.getString("listaDeMaterias"));
					        // curso.setListaDeTurmas(rs.getString("listaDeTurmas"));
			 
			            	ps.setInt(5, curso.getCodigo());
	 	              
		              ps.executeUpdate();
		          } catch (SQLException e) {
		              e.printStackTrace();
		              throw new RuntimeException(e);
		  		} finally {
		  			ConnectionHelper.close(c);
		  		}
		          return curso;	    }

		    public boolean remove(int id) {
		    	  Connection c = null;
		          try {
		              c = ConnectionHelper.getConnection();
		              PreparedStatement ps = c.prepareStatement("DELETE FROM curso WHERE codigo=?");
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

		    protected CursoModel processRow(ResultSet rs) throws SQLException {
		    	
		    	CursoModel curso = new CursoModel();
		    	
		    	 curso.setCodigo(rs.getInt("codigo"));
		         curso.setNome(rs.getString("nome"));
		         curso.setDescricao(rs.getString("descricao"));
		         curso.setAreaDoConhecimento(rs.getString("areaDoConhecimento"));
		         curso.setCodigoAnoLetivo(rs.getInt("codigoAnoLetivo"));
		         
		         //curso.setListaDeMaterias(rs.getString("listaDeMaterias"));
		        // curso.setListaDeTurmas(rs.getString("listaDeTurmas"));
 
		        return curso;
		    }
		    
		}