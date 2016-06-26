package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.TurmaModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class TurmaDAO {
    public TurmaDAO() {}

    public List<TurmaModel> findAll() {
        List<TurmaModel> list = new ArrayList<TurmaModel>();
        Connection c = null;
        String sql = "SELECT * FROM Turma ORDER BY nome";
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
        String sql = "SELECT * FROM Turma as e " +
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
        String sql = "SELECT * FROM Turma WHERE id = ?";
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

    public List<TurmaModel> findByUsuarioId(int id) {
        List<TurmaModel> list = new ArrayList<TurmaModel>();
        String sql = "SELECT * FROM Turma WHERE idUsuario = ? ORDER BY nome";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TurmaModel turma = processRow(rs);
                turma.setRequestStatus(true);
                list.add(turma);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<TurmaModel> findByFatherId(int id) {
        List<TurmaModel> list = new ArrayList<TurmaModel>();
        String sql = "SELECT * FROM Turma WHERE idCurso = ?";
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
        return turma.getId() > 0 ? update(turma) : create(turma);
    }

    public TurmaModel create(TurmaModel turma) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO Turma ("
                + "  idUsuario,"
                + "  idUniversidade,"
                + "  idUnidade,"
                + "  idCurso,"
                + "  nome,"
                + "  email,"
                + "  site,"
                + "  outrasInformacoes"
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );
            ps.setInt(1, turma.getIdUsuario());
            ps.setInt(2, turma.getIdUniversidade());
            ps.setInt(3, turma.getIdUnidade());
            ps.setInt(4, turma.getIdCurso());
            ps.setString(5, turma.getNome());
            ps.setString(6, turma.getEmail());
            ps.setString(7, turma.getSite());
            ps.setString(8, turma.getOutrasInformacoes());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            turma.setId(id);
            turma.setRequestStatus(true);
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
            PreparedStatement ps = c.prepareStatement(
                "UPDATE Turma SET"
                + "  idUsuario=?,"
                + "  idUniversidade=?,"
                + "  idUnidade=?,"
                + "  idCurso=?,"
                + "  nome=?,"
                + "  email=?,"
                + "  site=?,"
                + "  outrasInformacoes=?"
                + " WHERE id=?"
            );
            ps.setInt(1, turma.getIdUsuario());
            ps.setInt(2, turma.getIdUniversidade());
            ps.setInt(3, turma.getIdUnidade());
            ps.setInt(4, turma.getIdCurso());
            ps.setString(5, turma.getNome());
            ps.setString(6, turma.getEmail());
            ps.setString(7, turma.getSite());
            ps.setString(8, turma.getOutrasInformacoes());
            ps.setInt(9, turma.getId());
            ps.executeUpdate();
            turma.setRequestStatus(true);
        } catch (SQLException e) {
            turma.setRequestStatus(false);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return turma;
    }

    public TurmaModel remove(TurmaModel turma) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Turma WHERE id=?");
            ps.setInt(1, turma.getId());
            int count = ps.executeUpdate();
            turma.setRequestStatus(count == 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return turma;
    }

    protected TurmaModel processRow(ResultSet rs) throws SQLException {
        return new TurmaModel()
            .setId(rs.getInt("id"))
            .setIdUsuario(rs.getInt("idUsuario"))
            .setIdUniversidade(rs.getInt("idUniversidade"))
            .setIdUnidade(rs.getInt("idUnidade"))
            .setIdCurso(rs.getInt("idCurso"))
            .setNome(rs.getString("nome"))
            .setEmail(rs.getString("email"))
            .setSite(rs.getString("site"))
            .setOutrasInformacoes(rs.getString("outrasInformacoes"))
            .setRequestStatus(true);
    }

}
