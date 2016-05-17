package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RequestStatusModel;
import model.UnidadeModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class UnidadeDAO {
    public UnidadeDAO() {}

    public List<UnidadeModel> findAll() {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM Unidade ORDER BY nome";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
            	UnidadeModel unidade = processRow(rs);
            	unidade.setRequestStatus(true);
                list.add(unidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<UnidadeModel> findByName(String nome) {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM Unidade as e " +
            "WHERE UPPER(nome) LIKE ? " +
            "ORDER BY nome";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nome.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	UnidadeModel unidade = processRow(rs);
            	unidade.setRequestStatus(true);
                list.add(unidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public UnidadeModel findById(int id) {
        String sql = "SELECT * FROM Unidade WHERE id = ?";
        UnidadeModel unidade = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unidade = processRow(rs);
            	unidade.setRequestStatus(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return unidade;
    }

    public List<UnidadeModel> findByUsuarioId(int id) {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        String sql = "SELECT * FROM Unidade WHERE idUsuario = ?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	UnidadeModel unidade = processRow(rs);
            	unidade.setRequestStatus(true);
                list.add(unidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<UnidadeModel> findByFatherId(int id) {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        String sql = "SELECT * FROM Unidade WHERE id = ?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	UnidadeModel unidade = processRow(rs);
            	unidade.setRequestStatus(true);
                list.add(unidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public UnidadeModel save(UnidadeModel unidade)
    {
        return unidade.getId() > 0
    		? update(unidade)
			: create(unidade);
    }

    public UnidadeModel create(UnidadeModel unidade) {
        Connection c = null;
        PreparedStatement ps = null;
        String query = String.format(
              " INSERT INTO Unidade ("
	        + "   idUsuario,"
  	        + "   idUniversidade,"
  	        + "   nome,"
  	        + "   endereco,"
  	        + "   outrasInformacoes,"
  	        + "   unidadeSede"
            + " ) VALUES ("
  	        + "   %d, %d, '%s', '%s', '%s', %b"
  	        + " )",
            unidade.getIdUsuario(),
            unidade.getIdUniversidade(),
            unidade.getNome(),
            unidade.getEndereco(),
            unidade.getOutrasInformacoes(),
            unidade.isUnidadeSede()
        );
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(query);
            ps = c.prepareStatement(
                  "INSERT INTO Unidade ("
        		+ "  idUsuario, idUniversidade, nome, endereco, outrasInformacoes, unidadeSede"
        		+ ") VALUES (?, ?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );

            ps.setInt(1, unidade.getIdUsuario());
            ps.setInt(2, unidade.getIdUniversidade());
            ps.setString(3, unidade.getNome());
            ps.setString(4, unidade.getEndereco());
            ps.setString(5, unidade.getOutrasInformacoes());
            ps.setBoolean(6, unidade.isUnidadeSede());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            
            int id = rs.getInt(1);
            unidade.setId(id);
        	unidade.setRequestStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return unidade;
    }

    public UnidadeModel update(UnidadeModel unidade) {
          Connection c = null;
          String query = String.format(
                  " UPDATE Unidade"
    	        + " SET"
    	        + "   idUsuario=%d,"
    	        + "   idUniversidade=%d,"
    	        + "   nome='%s',"
    	        + "   endereco='%s',"
    	        + "   outrasInformacoes='%s',"
    	        + "   unidadeSede=%b"
    	        + " WHERE id=%d",
                unidade.getIdUsuario(),
                unidade.getIdUniversidade(),
                unidade.getNome(),
                unidade.getEndereco(),
                unidade.getOutrasInformacoes(),
                unidade.isUnidadeSede(),
                unidade.getId()
          );
          try {
                c = ConnectionHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(query);
                ps.executeUpdate();
            	unidade.setRequestStatus(true);
          } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return unidade;
    }

    public RequestStatusModel remove(int id) {
        Connection c = null;
        RequestStatusModel requestStatus = new RequestStatusModel(); 
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Unidade WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            requestStatus.set(count == 1);
            return requestStatus;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected UnidadeModel processRow(ResultSet rs) throws SQLException {
        UnidadeModel unidade = new UnidadeModel();
        unidade.setId(rs.getInt("id"));
        unidade.setIdUsuario(rs.getInt("idUsuario"));
        unidade.setIdUniversidade(rs.getInt("idUniversidade"));
        unidade.setNome(rs.getString("nome"));
        unidade.setEndereco(rs.getString("endereco"));
        unidade.setOutrasInformacoes(rs.getString("outrasInformacoes"));
        unidade.setUnidadeSede(rs.getBoolean("unidadeSede"));
        return unidade;
    }

}
