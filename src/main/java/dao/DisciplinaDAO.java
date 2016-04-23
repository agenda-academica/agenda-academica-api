package dao;

import java.sql.Connection;
import java.sql.Date;
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
        String sql = "SELECT * FROM disciplina ORDER BY nome";
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
        String sql = "SELECT * FROM disciplina as e " +
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

    public DisciplinaModel findById(int id) {
        String sql = "SELECT * FROM disciplina WHERE codigo = ?";
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

    public List<DisciplinaModel> findByFatherIdTurma(int id) {
        List<DisciplinaModel> list = new ArrayList<DisciplinaModel>();
        String sql = "SELECT * FROM disciplina WHERE codigoTurma = ?";
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
        String sql = "SELECT * FROM disciplina WHERE codigoCurso = ?";
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
        return disciplina.getCodigo() > 0 ? update(disciplina) : create(disciplina);
    }

    public DisciplinaModel create(DisciplinaModel disciplina) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO disciplina (nome, codigoTurma, horarioInicio, horarioFim, diaDaSemana, sala) VALUES (?, ?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCodigoTurma());
            ps.setDate(3, (Date) disciplina.getHorarioTermino());
            ps.setDate(4, (Date) disciplina.getHorarioInicio());
            ps.setString(5, disciplina.getDiaDaSemana());
            ps.setString(6, disciplina.getSala());

            //disciplina.setListaDeAulas(rs.getString("LISTAAAA"));
            //disciplina.setRoteiroDadisciplina(rs.getString("LISTAAAA"));
            //ps.setInt(7, disciplina.getCodigo());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            disciplina.setCodigo(id);
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
            PreparedStatement ps = c.prepareStatement("UPDATE disciplina SET nome=?, codigoTurma=?, horarioInicio=?, horarioFim=?, dataInicioEvento=?, diaDaSemana, sala=? WHERE codigo=?");
            ps.setString(1, disciplina.getNome());
            ps.setInt(2, disciplina.getCodigoTurma());
            ps.setDate(3, (Date) disciplina.getHorarioTermino());
            ps.setDate(4, (Date) disciplina.getHorarioInicio());
            ps.setString(5, disciplina.getDiaDaSemana());
            ps.setString(6, disciplina.getSala());
            // disciplina.setListaDeAulas(rs.getString("LISTAAAA"));
            // disciplina.setRoteiroDadisciplina(rs.getString("LISTAAAA"));
            ps.setInt(7, disciplina.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return disciplina;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM disciplina WHERE codigo=?");
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

    protected DisciplinaModel processRow(ResultSet rs) throws SQLException {
        DisciplinaModel disciplina = new DisciplinaModel();
        disciplina.setCodigo(rs.getInt("codigo"));
        disciplina.setNome(rs.getString("nome"));
        disciplina.setCodigoTurma(rs.getInt("codigoTurma"));
        disciplina.setHorarioInicio(rs.getDate("dataInicioEvento"));
        disciplina.setHorarioTermino(rs.getDate("dataFimEvento"));
        disciplina.setDiaDaSemana(rs.getString("diaDaSemana"));
        disciplina.setSala(rs.getString("sala"));
        return disciplina;
    }

}
