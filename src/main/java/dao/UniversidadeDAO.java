package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UniversidadeModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class UniversidadeDAO {
    public UniversidadeDAO() {}

    public List<UniversidadeModel> findAll() {
        List<UniversidadeModel> list = new ArrayList<UniversidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM Universidade ORDER BY nome";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                UniversidadeModel universidade = processRow(rs);
                universidade.setRequestStatus(true);
                list.add(universidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<UniversidadeModel> findByName(String nome) {
        List<UniversidadeModel> list = new ArrayList<UniversidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM Universidade as e " +
            "WHERE UPPER(nome) LIKE ? " +
            "ORDER BY nome";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + nome.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UniversidadeModel universidade = processRow(rs);
                universidade.setRequestStatus(true);
                list.add(universidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public UniversidadeModel findById(int id) {
        String sql = "SELECT * FROM Universidade WHERE id = ?";
        UniversidadeModel universidade = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                universidade = processRow(rs);
                universidade.setRequestStatus(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return universidade;
    }

    public List<UniversidadeModel> findByUsuarioId(int id) {
        List<UniversidadeModel> list = new ArrayList<UniversidadeModel>();
        String sql = "SELECT * FROM Universidade WHERE idUsuario = ? ORDER BY abreviacao";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UniversidadeModel universidade = processRow(rs);
                universidade.setRequestStatus(true);
                list.add(universidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<UniversidadeModel> findByFatherId(int id) {
        List<UniversidadeModel> list = new ArrayList<UniversidadeModel>();
        String sql = "SELECT * FROM Universidade WHERE idUsuario = ?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UniversidadeModel universidade = processRow(rs);
                universidade.setRequestStatus(true);
                list.add(universidade);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public UniversidadeModel save(UniversidadeModel universidade)
    {
        return universidade.getId() > 0
            ? update(universidade)
            : create(universidade);
    }

    public UniversidadeModel create(UniversidadeModel universidade) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO Universidade (idUsuario, nome, abreviacao, site, logo) VALUES (?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );

            ps.setInt(1, universidade.getIdUsuario());
            ps.setString(2, universidade.getNome());
            ps.setString(3, universidade.getAbreviacao());
            ps.setString(4, universidade.getSite());
            ps.setString(5, universidade.getLogo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            universidade.setId(id);
            universidade.setRequestStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return universidade;
    }

    public UniversidadeModel update(UniversidadeModel universidade) {
        Connection c = null;
        String query = String.format(
              " UPDATE Universidade"
            + " SET"
            + "   idUsuario=%d,"
            + "   nome='%s',"
            + "   abreviacao='%s',"
            + "   site='%s',"
            + "   logo='%s'"
            + " WHERE id=%d",
            universidade.getIdUsuario(),
            universidade.getNome(),
            universidade.getAbreviacao(),
            universidade.getSite(),
            universidade.getLogo(),
            universidade.getId()
        );

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ps.executeUpdate();
            universidade.setRequestStatus(true);
          } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return universidade;
    }

    public UniversidadeModel remove(int id) {
        Connection c = null;
        UniversidadeModel universidade = new UniversidadeModel();
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Universidade WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            universidade.setId(id);
            universidade.setRequestStatus(count == 1);
            return universidade;
          } catch (Exception e) {
            universidade.setRequestStatus(false);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected UniversidadeModel processRow(ResultSet rs) throws SQLException {
        UniversidadeModel universidade = new UniversidadeModel();

        universidade.setId(rs.getInt("id"));
        universidade.setIdUsuario(rs.getInt("idUsuario"));
        universidade.setNome(rs.getString("nome"));
        universidade.setAbreviacao(rs.getString("abreviacao"));
        universidade.setSite(rs.getString("site"));
        universidade.setLogo(rs.getString("logo"));
        return universidade;
    }

}
