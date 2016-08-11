
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.PeriodoLetivoModel;

/**
 * @author Sergio Eduardo Bertolazo na briza
 */
public class PeriodoLetivoDAO {
    public PeriodoLetivoDAO() {}

    public List<PeriodoLetivoModel> findAll() {
        List<PeriodoLetivoModel> list = new ArrayList<PeriodoLetivoModel>();
        Connection c = null;
        String sql = "SELECT * FROM PeriodoLetivo ORDER BY nome";
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

    public List<PeriodoLetivoModel> findByName(String nome) {
        List<PeriodoLetivoModel> list = new ArrayList<PeriodoLetivoModel>();
        Connection c = null;
        String sql = "SELECT * FROM PeriodoLetivo as e " +
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

    public PeriodoLetivoModel findById(int id) {
        String sql = "SELECT * FROM PeriodoLetivo WHERE id = ?";
        PeriodoLetivoModel PeriodoLetivo = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PeriodoLetivo = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return PeriodoLetivo;
    }

    public List<PeriodoLetivoModel> findByUsuarioId(int id) {
        List<PeriodoLetivoModel> list = new ArrayList<PeriodoLetivoModel>();
        String sql = "SELECT * FROM PeriodoLetivo WHERE idUsuario = ?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PeriodoLetivoModel periodoLetivo = processRow(rs);
                periodoLetivo.setRequestStatus(true);
                list.add(periodoLetivo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<PeriodoLetivoModel> findByFatherIdTurma(int id) {
        List<PeriodoLetivoModel> list = new ArrayList<PeriodoLetivoModel>();
        String sql = "SELECT * FROM PeriodoLetivo WHERE idTurma = ?";
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

    public List<PeriodoLetivoModel> findByFatherIdCurso(int id) {
        List<PeriodoLetivoModel> list = new ArrayList<PeriodoLetivoModel>();
        String sql = "SELECT * FROM PeriodoLetivo WHERE idCurso = ?";
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


    public PeriodoLetivoModel save(PeriodoLetivoModel PeriodoLetivo)
    {
        return PeriodoLetivo.getId() > 0 ? update(PeriodoLetivo) : create(PeriodoLetivo);
    }

    public PeriodoLetivoModel create(PeriodoLetivoModel periodoLetivo) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();

            ps = c.prepareStatement("INSERT INTO PeriodoLetivo (idUsuario, idUniversidade, titulo, dataInicio, dataFim, cor) VALUES (?, ?, ?, ?, ?, ?)",
            new String[] { "ID" });

            ps.setInt(1, periodoLetivo.getIdUsuario());
            ps.setInt(2, periodoLetivo.getIdUniversidade());
            ps.setString(3, periodoLetivo.getTitulo());
            ps.setString(4, periodoLetivo.getDataInicio());
            ps.setString(5, periodoLetivo.getDataFim());
            ps.setString(6, periodoLetivo.getCor());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            periodoLetivo.setId(id);
            periodoLetivo.setRequestStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return periodoLetivo;
    }

    public PeriodoLetivoModel update(PeriodoLetivoModel periodoLetivo) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE PeriodoLetivo SET idUsuario=?, idUniversidade=?, titulo=?, dataInicio=?, dataFim=?, cor=? WHERE id=?");
        
            ps.setInt(1, periodoLetivo.getIdUsuario());
            ps.setInt(2, periodoLetivo.getIdUniversidade());
            ps.setString(3, periodoLetivo.getTitulo());
            ps.setString(4, periodoLetivo.getDataInicio());
            ps.setString(5, periodoLetivo.getDataFim());
            ps.setString(6, periodoLetivo.getCor());
            ps.setInt(7, periodoLetivo.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return periodoLetivo;
    }

    public PeriodoLetivoModel remove(int id) {
        Connection c = null;
        PeriodoLetivoModel periodoLetivo = new PeriodoLetivoModel();
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM PeriodoLetivo WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            periodoLetivo.setId(id);
            periodoLetivo.setRequestStatus(count == 1);
            return periodoLetivo;
          } catch (Exception e) {
              periodoLetivo.setRequestStatus(false);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected PeriodoLetivoModel processRow(ResultSet rs) throws SQLException {
        PeriodoLetivoModel periodo = new PeriodoLetivoModel();

        periodo.setId(rs.getInt("id"));
        periodo.setTitulo(rs.getString("titulo"));
        periodo.setIdUsuario(rs.getInt("idUsuario"));
        periodo.setIdUniversidade(rs.getInt("idUniversidade"));
        periodo.setDataInicio(rs.getString("dataInicio"));
        periodo.setDataFim(rs.getString("dataFim"));
        periodo.setCor(rs.getString("cor"));

        return periodo;
    }

}
