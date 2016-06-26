package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.DisciplinaModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class DisciplinaDAO {
    public DisciplinaDAO() {}

    public List<DisciplinaModel> findAll() {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        Connection c = null;
        String sql = "SELECT * FROM Disciplina ORDER BY horaInicio";
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

    public List<DisciplinaModel> findByName(String nome) {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        Connection c = null;
        String sql = "SELECT * FROM Disciplina as e " +
        "WHERE UPPER(nome) LIKE ? " +
        "ORDER BY horaInicio";
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

    public DisciplinaModel findById(int id) {
        String sql = "SELECT * FROM Disciplina WHERE id = ?";
        DisciplinaModel disciplina = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                disciplina = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return disciplina;
    }

    public List<DisciplinaModel> findByUsuarioId(int id) {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        String sql = "SELECT * FROM Disciplina WHERE idUsuario = ? ORDER BY horaInicio";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DisciplinaModel disciplina = processRow(rs);
                disciplina.setRequestStatus(true);
                list.add(disciplina);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<DisciplinaModel> findByFatherIdTurma(int id) {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        String sql = "SELECT * FROM Disciplina WHERE idTurma = ?";
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

    public List<DisciplinaModel> findByFatherIdCurso(int id) {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        String sql = "SELECT * FROM Disciplina WHERE idCurso = ?";
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

    public DisciplinaModel save(DisciplinaModel disciplina)
    {
        return disciplina.getId() > 0 ? update(disciplina) : create(disciplina);
    }

    public DisciplinaModel create(DisciplinaModel disciplina) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                    "INSERT INTO Disciplina ("
                  + "  idUsuario,"
                  + "  idUniversidade,"
                  + "  idUnidade,"
                  + "  idCurso,"
                  + "  idTurma,"
                  + "  abreviacao,"
                  + "  nome,"
                  + "  horaInicio,"
                  + "  horaFim,"
                  + "  diaSemana"
                  + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                  new String[] { "ID" }
              );
              ps.setInt(1, disciplina.getIdUsuario());
              ps.setInt(2, disciplina.getIdUniversidade());
              ps.setInt(3, disciplina.getIdUnidade());
              ps.setInt(4, disciplina.getIdCurso());
              ps.setInt(5, disciplina.getIdTurma());
              ps.setString(6, disciplina.getAbreviacao());
              ps.setString(7, disciplina.getNome());
              ps.setString(8, disciplina.getHoraInicio());
              ps.setString(9, disciplina.getHoraFim());
              ps.setInt(10, disciplina.getDiaSemana());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            
            // Update the id in the returned object. This is important as this value must be returned to the client.
            disciplina.setId(rs.getInt(1));
            disciplina.setRequestStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return disciplina;
    }

    public DisciplinaModel update(DisciplinaModel disciplina) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE Disciplina SET"
                  + "  idUsuario=?,"
                  + "  idUniversidade=?,"
                  + "  idUnidade=?,"
                  + "  idCurso=?,"
                  + "  idTurma=?,"
                  + "  abreviacao=?,"
                  + "  nome=?,"
                  + "  horaInicio=?,"
                  + "  horaFim=?,"
                  + "  diaSemana=?"
                  + " WHERE id=?"
            );
            ps.setInt(1, disciplina.getIdUsuario());
            ps.setInt(2, disciplina.getIdUniversidade());
            ps.setInt(3, disciplina.getIdUnidade());
            ps.setInt(4, disciplina.getIdCurso());
            ps.setInt(5, disciplina.getIdTurma());
            ps.setString(6, disciplina.getAbreviacao());
            ps.setString(7, disciplina.getNome());
            ps.setString(8, disciplina.getHoraInicio());
            ps.setString(9, disciplina.getHoraFim());
            ps.setInt(10, disciplina.getDiaSemana());
            ps.setInt(11, disciplina.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return disciplina;
    }

    public DisciplinaModel remove(DisciplinaModel disciplina) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Disciplina WHERE id=?");
            ps.setInt(1, disciplina.getId());
            int count = ps.executeUpdate();
            disciplina.setRequestStatus(count == 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return disciplina;
    }

    protected DisciplinaModel processRow(ResultSet rs) throws SQLException {
        return new DisciplinaModel()
            .setId(rs.getInt("id"))
            .setIdUsuario(rs.getInt("idUsuario"))
            .setIdUniversidade(rs.getInt("idUniversidade"))
            .setIdUnidade(rs.getInt("idUnidade"))
            .setIdCurso(rs.getInt("idCurso"))
            .setIdTurma(rs.getInt("idTurma"))
            .setAbreviacao(rs.getString("abreviacao"))
            .setNome(rs.getString("nome"))
            .setHoraInicio(rs.getString("horaInicio"))
            .setHoraFim(rs.getString("horaFim"))
            .setDiaSemana(rs.getInt("diaSemana"))
            .setRequestStatus(true);
    }

}
