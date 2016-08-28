package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.EventoModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class EventoDAO {
    public EventoDAO() {}

    public List<EventoModel> findAll() {
        List<EventoModel> list = new ArrayList<EventoModel>();
        Connection c = null;
        String sql = "SELECT * FROM Evento ORDER BY id";
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

    public List<EventoModel> findByName(String nome) {
        List<EventoModel> list = new ArrayList<EventoModel>();
        Connection c = null;
        String sql = "SELECT * FROM Evento as e " +
            "WHERE UPPER(titulo) LIKE ? " +
            "ORDER BY titulo";
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

    public EventoModel findById(int id) {
        String sql = "SELECT * FROM Evento WHERE id = ?";
        EventoModel evento = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                evento = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return evento;
    }

    public List<EventoModel> findByUsuarioId(int id) {
        List<EventoModel> list = new ArrayList<EventoModel>();
        String sql = "SELECT * FROM Evento WHERE idUsuario = ?";
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EventoModel evento = processRow(rs);
                evento.setRequestStatus(true);
                list.add(evento);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return list;
    }

    public List<EventoModel> findByFatherIdTurma(int id) {
        List<EventoModel> list = new ArrayList<EventoModel>();
        String sql = "SELECT * FROM Evento WHERE idTurma = ?";
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

    public List<EventoModel> findByFatherIdCurso(int id) {
        List<EventoModel> list = new ArrayList<EventoModel>();
        String sql = "SELECT * FROM Evento WHERE idCurso = ?";
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


    public EventoModel save(EventoModel evento)
    {
        return evento.getId() > 0 ? update(evento) : create(evento);
    }

    public EventoModel create(EventoModel evento) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO Evento ("
                + "idUsuario,"
                + " idUniversidade,"
                + " idUnidade,"
                + " idCurso,"
                + " idTurma,"
                + " idDisciplina,"
                + " tipo,"
                + " titulo,"
                + " descricao,"
                + " dataInicio,"
                + " dataFim,"
                + " horaInicio,"
                + " horaFim,"
            + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            new String[] { "ID" });

            ps.setInt(1, evento.getIdUsuario());
            ps.setInt(2, evento.getIdUniversidade());
            ps.setInt(3, evento.getIdUnidade());
            ps.setInt(4, evento.getIdCurso());
            ps.setInt(5, evento.getIdTurma());
            ps.setInt(6, evento.getIdDisciplina());
            ps.setString(7, evento.getTipo());
            ps.setString(8, evento.getTitulo());
            ps.setString(9, evento.getDescricao());
            ps.setString(10, evento.getDataInicio());
            ps.setString(11, evento.getDataFim());
            ps.setString(12, evento.getHoraInicio());
            ps.setString(13, evento.getHoraFim());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            evento.setId(rs.getInt(1));
            evento.setRequestStatus(true);
        } catch (Exception e) {
            e.printStackTrace();
            evento.setRequestStatus(false);
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return evento;
    }

    public EventoModel update(EventoModel evento) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(
                "UPDATE Evento SET"
                    + " idUsuario=?,"
                    + " idUniversidade=?,"
                    + " idUnidade=?,"
                    + " idCurso=?,"
                    + " idTurma=?,"
                    + " idDisciplina=?,"
                    + " tipo=?,"
                    + " titulo=?,"
                    + " descricao=?,"
                    + " dataInicio=?,"
                    + " dataFim=?,"
                    + " horaInicio=?,"
                    + " horaFim=?,"
                + " WHERE id=?"
            );

            ps.setInt(1, evento.getIdUsuario());
            ps.setInt(2, evento.getIdUniversidade());
            ps.setInt(3, evento.getIdUnidade());
            ps.setInt(4, evento.getIdCurso());
            ps.setInt(5, evento.getIdTurma());
            ps.setInt(6, evento.getIdDisciplina());
            ps.setString(7, evento.getTipo());
            ps.setString(8, evento.getTitulo());
            ps.setString(9, evento.getDescricao());
            ps.setString(10, evento.getDataInicio());
            ps.setString(11, evento.getDataFim());
            ps.setString(12, evento.getHoraInicio());
            ps.setString(13, evento.getHoraFim());
            ps.setInt(14, evento.getId());
            ps.executeUpdate();
            evento.setRequestStatus(true);
        } catch (SQLException e) {
              e.printStackTrace();
              evento.setRequestStatus(false);
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return evento;
    }

    public EventoModel remove(int id) {
        Connection c = null;
        EventoModel evento = new EventoModel();
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Evento WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            evento.setId(id);
            evento.setRequestStatus(count == 1);
            return evento;
          } catch (Exception e) {
              evento.setRequestStatus(false);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected EventoModel processRow(ResultSet rs) throws SQLException {
        return new EventoModel()
            .setId(rs.getInt("id"))
            .setIdUsuario(rs.getInt("idUsuario"))
            .setIdUniversidade(rs.getInt("idUniversidade"))
            .setIdUnidade(rs.getInt("idUnidade"))
            .setIdCurso(rs.getInt("idCurso"))
            .setIdTurma(rs.getInt("idTurma"))
            .setIdDisciplina(rs.getInt("idDisciplina"))
            .setTipo(rs.getString("tipo"))
            .setTitulo(rs.getString("titulo"))
            .setDescricao(rs.getString("descricao"))
            .setDataInicio(rs.getString("dataInicio"))
            .setDataFim(rs.getString("dataFim"))
            .setHoraInicio(rs.getString("horaInicio"))
            .setHoraFim(rs.getString("horaFim"));
    }

}
