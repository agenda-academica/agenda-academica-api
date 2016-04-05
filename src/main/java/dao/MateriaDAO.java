 
	package dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	
	import model.MateriaModel;
import model.TurmaModel;
import model.MateriaModel;

	/**
	 * @author Sergio Eduardo Bertolazo
	 */
	public class MateriaDAO {
 
		public MateriaDAO(){
 
		}

	    public List<MateriaModel> findAll() {
	    	List<MateriaModel> list = new ArrayList<MateriaModel>();
	        
	        Connection c = null;
	        
	    	String sql = "SELECT * FROM materia ORDER BY nome";
	    	
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

	    
	    public List<MateriaModel> findByName(String nome) {
	        List<MateriaModel> list = new ArrayList<MateriaModel>();
	        Connection c = null;
	    	String sql = "SELECT * FROM materia as e " +
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
	    
	    public MateriaModel findById(int id) {
	    	String sql = "SELECT * FROM materia WHERE codigo = ?";
	    	MateriaModel materia = null;
	        Connection c = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            PreparedStatement ps = c.prepareStatement(sql);
	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                materia = processRow(rs);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return materia;
	    }
	    
	    public List<MateriaModel> findByFatherIdTurma(int id) {
	    	
	    	 List<MateriaModel> list = new ArrayList<MateriaModel>();
	  	  
	    	String sql = "SELECT * FROM materia WHERE codigoTurma = ?";

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
	    
	    public List<MateriaModel> findByFatherIdCurso(int id) {
	    	
	    	 List<MateriaModel> list = new ArrayList<MateriaModel>();
	  	  
	    	String sql = "SELECT * FROM materia WHERE codigoCurso = ?";

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
	    

	    public MateriaModel save(MateriaModel materia)
		{
			return materia.getCodigo() > 0 ? update(materia) : create(materia);
		}    
	    
	    public MateriaModel create(MateriaModel materia) {
	    	
	    	Connection c = null;
	        PreparedStatement ps = null;
	        try {
	            c = ConnectionHelper.getConnection();
	            ps = c.prepareStatement("INSERT INTO materia (nome, codigoCurso, codigoTurma, descricao, diaDaSemana, sala) VALUES (?, ?, ?, ?, ?, ?)",
	                new String[] { "ID" });
	            
	            	ps.setString(1, materia.getNome());	
	            	ps.setInt(2, materia.getCodigoCurso());	
	            	ps.setInt(3, materia.getCodigoTurma());	
	            	ps.setString(4, materia.getDescricao());	
	            	ps.setString(5, materia.getDiaDaSemana());	
	            	ps.setString(6, materia.getSala());	
 
		        // materia.setListaDeAulas(rs.getString("LISTAAAA"));
		        // materia.setRoteiroDaMateria(rs.getString("LISTAAAA"));
		         
	            	//ps.setInt(7, materia.getCodigo());
	            
	            	ps.executeUpdate();
	            
	            ResultSet rs = ps.getGeneratedKeys();
	            
	            rs.next();
	            
	            // Update the id in the returned object. This is important as this value must be returned to the client.
	            int id = rs.getInt(1);
	            
	            materia.setCodigo(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
			} finally {
				ConnectionHelper.close(c);
			}
	        return materia;
	        
	        
	    }

	    public MateriaModel update(MateriaModel materia) {
	    	
	    	  Connection c = null;
	    	  
	          try {
	        	  
	              c = ConnectionHelper.getConnection();
	    
	              PreparedStatement ps = c.prepareStatement("UPDATE materia SET nome=?, codigoCurso=?, codigoTurma=?, descricao=?, diaDaSemana=?, sala=? WHERE codigo=?");
	  
	            	ps.setString(1, materia.getNome());	
	            	ps.setInt(2, materia.getCodigoCurso());	
	            	ps.setInt(3, materia.getCodigoTurma());	
	            	ps.setString(4, materia.getDescricao());	
	            	ps.setString(5, materia.getDiaDaSemana());	
	            	ps.setString(6, materia.getSala());	
 
		        // materia.setListaDeAulas(rs.getString("LISTAAAA"));
		        // materia.setRoteiroDaMateria(rs.getString("LISTAAAA"));
	            	ps.setInt(7, materia.getCodigo());
 	              
	              ps.executeUpdate();
	          } catch (SQLException e) {
	              e.printStackTrace();
	              throw new RuntimeException(e);
	  		} finally {
	  			ConnectionHelper.close(c);
	  		}
	          return materia;	    }

	    public boolean remove(int id) {
	    	  Connection c = null;
	          try {
	              c = ConnectionHelper.getConnection();
	              PreparedStatement ps = c.prepareStatement("DELETE FROM materia WHERE codigo=?");
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

	    protected MateriaModel processRow(ResultSet rs) throws SQLException {
	    	
	    	MateriaModel materia = new MateriaModel();
	    	
	    	 materia.setCodigo(rs.getInt("codigo"));
	         materia.setNome(rs.getString("nome"));
	         materia.setCodigoCurso(rs.getInt("codigoCurso"));
	         materia.setCodigoTurma(rs.getInt("codigoTurma"));
	         materia.setDescricao(rs.getString("descricao"));
	         materia.setDiaDaSemana(rs.getString("diaDaSemana"));
	         materia.setSala(rs.getString("sala"));
	         
	        // materia.setListaDeAulas(rs.getString("LISTAAAA"));
	        // materia.setRoteiroDaMateria(rs.getString("LISTAAAA"));
	 
	        return materia;
	    }
	    
	}

