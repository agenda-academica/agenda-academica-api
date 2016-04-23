package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
        String sql = "SELECT * FROM evento ORDER BY nome";
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
        String sql = "SELECT * FROM evento as e " +
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

    public EventoModel findById(int id) {
        String sql = "SELECT * FROM evento WHERE codigo = ?";
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

    public List<EventoModel> findByFatherIdTurma(int id) {
        List<EventoModel> list = new ArrayList<EventoModel>();
        String sql = "SELECT * FROM evento WHERE codigoTurma = ?";
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
        String sql = "SELECT * FROM evento WHERE codigoCurso = ?";
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
        return evento.getCodigo() > 0 ? update(evento) : create(evento);
    }

    public EventoModel create(EventoModel evento) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO evento (titulo, descricao, codigoUsuario, codigoUniversidade, codigoUnidade, codigoTurma, codigoDisciplina, dataInicioEvento, dataFimEvento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            new String[] { "ID" });

            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getDescricao());
            ps.setInt(3, evento.getCodigoUsuario());
            ps.setInt(4, evento.getCodigoInstituicaoDeEnsino());
            ps.setInt(5, evento.getCodigoUnidade());
            ps.setInt(6, evento.getCodigoTurma());
            ps.setInt(7, evento.getCodigoDisciplina());

            Date dataInicio = null;
            Date dataFim = null;

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            dataInicio = (Date) format.parse(evento.getDataInicioEvento());
            dataFim =  (Date) format.parse(evento.getDataFimEvento());
        }
        catch(Exception ex) {}

        //ps.setDate(8, (Date) evento.getDataInicioEvento());
        //ps.setDate(9, (Date) evento.getDataFimEvento());
        ps.setDate(8, dataInicio);
        ps.setDate(9, dataFim);
        // evento.setListaDeAulas(rs.getString("LISTAAAA"));
        // evento.setRoteiroDaevento(rs.getString("LISTAAAA"));
        ps.setInt(10, evento.getCodigo());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        // Update the id in the returned object. This is important as this value must be returned to the client.
        int id = rs.getInt(1);
        evento.setCodigo(id);
        } catch (Exception e) {
            e.printStackTrace();
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
            PreparedStatement ps = c.prepareStatement("UPDATE evento SET titulo=?, descricao=?, codigoUsuario=?, codigoUniversidade=?, codigoUnidade=?, codigoTurma=?, codigoDisciplina=?, dataInicioEvento=?, dataFimEvento=? WHERE codigo=?");
            ps.setString(1, evento.getTitulo());
            ps.setString(2, evento.getDescricao());
            ps.setInt(3, evento.getCodigoUsuario());
            ps.setInt(4, evento.getCodigoInstituicaoDeEnsino());
            ps.setInt(5, evento.getCodigoUnidade());
            ps.setInt(6, evento.getCodigoTurma());
            ps.setInt(7, evento.getCodigoDisciplina());

            Date dataInicio = null;
            Date dataFim = null;
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            try{
                dataInicio = (Date) format.parse(evento.getDataInicioEvento());
                dataFim =  (Date) format.parse(evento.getDataFimEvento());
            }
            catch(Exception ex){

            }
            //ps.setDate(8, (Date) evento.getDataInicioEvento());
            //ps.setDate(9, (Date) evento.getDataFimEvento());
            // evento.setListaDeAulas(rs.getString("LISTAAAA"));
            // evento.setRoteiroDaevento(rs.getString("LISTAAAA"));
            ps.setInt(10, evento.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return evento;
    }

    public boolean remove(int id) {
          Connection c = null;
          try {
              c = ConnectionHelper.getConnection();
              PreparedStatement ps = c.prepareStatement("DELETE FROM evento WHERE codigo=?");
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

    protected EventoModel processRow(ResultSet rs) throws SQLException {
        EventoModel evento = new EventoModel();

         evento.setCodigo(rs.getInt("codigo"));
         evento.setTitulo(rs.getString("titulo"));
         evento.setDescricao(rs.getString("descricao"));
         evento.setCodigoUsuario(rs.getInt("codigoUsuario"));
         evento.setCodigoTurma(rs.getInt("codigoUniversidade"));
         evento.setCodigoTurma(rs.getInt("codigoUnidade"));
         evento.setCodigoTurma(rs.getInt("codigoCurso"));
         evento.setCodigoTurma(rs.getInt("codigoTurma"));
         evento.setCodigoTurma(rs.getInt("codigoDisciplina"));
         //evento.setDataInicioEvento(rs.getDate("dataInicioEvento"));
        // evento.setDataFimEvento(rs.getDate("dataFimEvento"));
         evento.setDataInicioEvento(rs.getString("dataInicioEvento"));
         evento.setDataFimEvento(rs.getString("dataFimEvento"));

        return evento;
    }

}
