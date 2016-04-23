package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UnidadeModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class UnidadeDAO {
    public UnidadeDAO() {}

    public List<UnidadeModel> findAll() {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM unidade ORDER BY nome";
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

    public List<UnidadeModel> findByName(String nome) {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        Connection c = null;
        String sql = "SELECT * FROM unidade as e " +
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

    public UnidadeModel findById(int id) {
        String sql = "SELECT * FROM unidade WHERE codigo = ?";
        UnidadeModel unidade = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unidade = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return unidade;
    }

    public List<UnidadeModel> findByFatherId(int id) {
        List<UnidadeModel> list = new ArrayList<UnidadeModel>();
        String sql = "SELECT * FROM unidade WHERE codigoUnidade = ?";
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

    public UnidadeModel save(UnidadeModel unidade)
    {
        return unidade.getCodigo() > 0 ? update(unidade) : create(unidade);
    }

    public UnidadeModel create(UnidadeModel unidade) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO unidade (nome, codigoUnidade) VALUES (?, ?)",
                new String[] { "ID" }
            );
            ps.setString(1, unidade.getNome());
            ps.setInt(2, unidade.getCodigoIntituicaoDeEnsino());
            // ps.setString(2, unidade.getDescricao());
            // ps.setInt(3, unidade.getCodigoAnoLetivo());
            // ps.setString(4, unidade.getAreaDoConhecimento());

            // unidade.setListaDeMaterias(rs.getString("listaDeMaterias"));
            // unidade.setListaDeTurmas(rs.getString("listaDeTurmas"));

            //ps.setInt(5, unidade.getCodigo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            unidade.setCodigo(id);
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
          try {
                c = ConnectionHelper.getConnection();
                PreparedStatement ps = c.prepareStatement("UPDATE unidade SET nome=? cdigoUnidade=? WHERE codigo=?");
                ps.setString(1, unidade.getNome());
                ps.setInt(2, unidade.getCodigoIntituicaoDeEnsino());
                // unidade.setListaDeMaterias(rs.getString("listaDeMaterias"));
                // unidade.setListaDeTurmas(rs.getString("listaDeTurmas"));
                ps.setInt(5, unidade.getCodigo());
                ps.executeUpdate();
          } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return unidade;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM unidade WHERE codigo=?");
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

    protected UnidadeModel processRow(ResultSet rs) throws SQLException {
        UnidadeModel unidade = new UnidadeModel();
        unidade.setCodigo(rs.getInt("codigo"));
        unidade.setNome(rs.getString("nome"));
        unidade.setCodigoIntituicaoDeEnsino(rs.getInt("codigoUniversidade"));
        // unidade.setAreaDoConhecimento(rs.getString("areaDoConhecimento"));
        // unidade.setCodigoAnoLetivo(rs.getInt("codigoAnoLetivo"));
        // unidade.setListaDeMaterias(rs.getString("listaDeMaterias"));
        // unidade.setListaDeTurmas(rs.getString("listaDeTurmas"));
        return unidade;
    }

}
