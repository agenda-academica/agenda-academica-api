 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

import model.CursoModel;
import model.TurmaModel;
 
	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class TurmaDAO {

		
		//List<TurmaModel> list = new ArrayList<TurmaModel>();
		
		public TurmaDAO(){
 
			
		}

	    public List<TurmaModel> findAll() {
	    	
	 
	List<TurmaModel> list = new ArrayList<TurmaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM turma ORDER BY nome";
	    	
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

	    
	    public List<TurmaModel> findByName(String nome) {
	    	
	        List<TurmaModel> list = new ArrayList<TurmaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM turma as e " +
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
	    
	    public TurmaModel findById(int id) {
	  
	    	String sql = "SELECT * FROM turma WHERE codigo = ?";
	    	
	    	TurmaModel turma = null;
	    	
	        Connection c = null;
	        
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                turma = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return turma;
	    }
	    

	    public List<TurmaModel> findByFatherId(int id) {
	    	
	    	 List<TurmaModel> list = new ArrayList<TurmaModel>();
	  	  
	    	String sql = "SELECT * FROM turma WHERE codigoCurso = ?";
 
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

	    public TurmaModel save(TurmaModel turma)
		{
			return turma.getCodigo() > 0 ? update(turma) : create(turma);
		}    
	    
	    public TurmaModel create(TurmaModel turma) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO turma (nome, codigoCurso, descricao, emailTurma) VALUES (?, ?, ?, ?)",
	                new String[] { "ID" });
	            
	            	ps.setString(1, turma.getNome());	
	            	ps.setInt(2, turma.getCodigoCurso());
	            	ps.setString(3, turma.getDescricao());
	            	ps.setString(4, turma.getEmailTurma());
	            	//ps.setInt(5, turma.getCodigo());
	  
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            turma.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return turma;
	        
	    }

	    public TurmaModel update(TurmaModel turma) {
	    	 Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
 
	              PreparedStatement ps = c.prepareStatement("UPDATE turma SET nome=?, codigoCurso=?, descricao=?, emailTurma=? WHERE codigo=?");
	  
	          	ps.setString(1, turma.getNome());	
            	ps.setInt(2, turma.getCodigoCurso());
            	ps.setString(3, turma.getDescricao());
             	ps.setString(4, turma.getEmailTurma());
            	ps.setInt(5, turma.getCodigo());
	         
	              
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return turma;	    }

 

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM turma WHERE codigo=?");
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

	    protected TurmaModel processRow(ResultSet rs) throws SQLException {
	    	
	    	TurmaModel turma = new TurmaModel();
	    	
	    		turma.setCodigo(rs.getInt("codigo"));
	            turma.setNome(rs.getString("nome"));
	            turma.setCodigoCurso(rs.getInt("codigoCurso"));
	            turma.setDescricao(rs.getString("descricao"));
	            
	            /*turma.setListaDeAlunos(listaDeAlunos);
	            turma.setListaDeMateria(listaDeMateria);
	            turma.setListaDeRepresentantes(listaDeRepresentantes);
	            */
	            //
	            
	            turma.setEmailTurma(rs.getString("emailTurma"));
	  
	        
	        return turma;
	    }
	    
	}

