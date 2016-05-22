package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CursoModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class CursoDAO {
    public CursoDAO() {}

    public List<CursoModel> findAll() {
        List<CursoModel> list = new ArrayList<CursoModel>();
        Connection c = null;
        String sql = "SELECT * FROM Curso ORDER BY nome";
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
        String sql =   " SELECT * FROM Curso as e"
                     + " WHERE UPPER(nome) LIKE ?"
                     + " ORDER BY nome";
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
        String sql = "SELECT * FROM Curso WHERE id = ?";
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

    public List<CursoModel> findByUsuarioId(int id) {
        List<CursoModel> list = new ArrayList<CursoModel>();
        String sql = "SELECT * FROM Curso WHERE idUsuario = ? ORDER BY abreviacao";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CursoModel curso = processRow(rs);
                curso.setRequestStatus(true);
                list.add(curso);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<CursoModel> findByFatherId(int id) {
       List<CursoModel> list = new ArrayList<CursoModel>();
       String sql = "SELECT * FROM Curso WHERE idUnidade = ?";
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
        return curso.getId() > 0 ? update(curso) : create(curso);
    }

    public CursoModel create(CursoModel curso) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                  "INSERT INTO Curso (idUsuario, idUniversidade, idUnidade, abreviacao, nome, outrasInformacoes) VALUES (?, ?, ?, ?, ?, ?)",
                new String[] { "ID" });

            ps.setInt(1, curso.getIdUsuario());
            ps.setInt(2, curso.getIdUniversidade());
            ps.setInt(3, curso.getIdUnidade());
            ps.setString(4, curso.getAbreviacao());
            ps.setString(5, curso.getNome());
            ps.setString(6, curso.getOutrasInformacoes());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            curso.setId(id);
            curso.setRequestStatus(true);
        } catch (Exception e) {
            curso.setRequestStatus(false);
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
            PreparedStatement ps = c.prepareStatement(
                  "UPDATE Curso"
                + " SET idUsuario=?,"
                + " idUniversidade=?,"
                + " idUnidade=?,"
                + " abreviacao=?,"
                + " nome=?,"
                + " outrasInformacoes=?"
                + " WHERE id=?"
            );
            ps.setInt(1, curso.getIdUsuario());
            ps.setInt(2, curso.getIdUniversidade());
            ps.setInt(3, curso.getIdUnidade());
            ps.setString(4, curso.getAbreviacao());
            ps.setString(5, curso.getNome());
            ps.setString(6, curso.getOutrasInformacoes());
            ps.setInt(7, curso.getId());
            ps.executeUpdate();
            curso.setRequestStatus(true);
          } catch (SQLException e) {
              curso.setRequestStatus(false);
              e.printStackTrace();
              throw new RuntimeException(e);
          } finally {
            ConnectionHelper.close(c);
        }
        return curso;
    }

    public CursoModel remove(CursoModel curso) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Curso WHERE id=?");
            ps.setInt(1, curso.getId());
            int count = ps.executeUpdate();
            curso.setRequestStatus(count == 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return curso;
    }

    protected CursoModel processRow(ResultSet rs) throws SQLException {
        CursoModel curso = new CursoModel();
        curso.setId(rs.getInt("id"));
        curso.setIdUsuario(rs.getInt("idUsuario"));
        curso.setIdUniversidade(rs.getInt("idUniversidade"));
        curso.setIdUnidade(rs.getInt("idUnidade"));
        curso.setAbreviacao(rs.getString("abreviacao"));
        curso.setNome(rs.getString("nome"));
        curso.setOutrasInformacoes(rs.getString("outrasInformacoes"));
        curso.setRequestStatus(true);
        return curso;
    }

}
