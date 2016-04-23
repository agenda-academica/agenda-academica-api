package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.RepresentanteModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class RepresentanteDAO {
    public RepresentanteDAO() {}

    public List<RepresentanteModel> findAll() {
        List<RepresentanteModel> list = new ArrayList<RepresentanteModel>();
        Connection c = null;
        String sql = "SELECT * FROM representante ORDER BY nome";
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

    public List<RepresentanteModel> findByName(String nome) {
        List<RepresentanteModel> list = new ArrayList<RepresentanteModel>();
        Connection c = null;
        String sql = "SELECT * FROM representante as e " +
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

    public RepresentanteModel findById(int id) {
        String sql = "SELECT * FROM representante WHERE codigo = ?";
        RepresentanteModel representante = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                representante = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
          ConnectionHelper.close(c);
        }
        return representante;
    }

    /*
    public List<RepresentanteModel> findByFatherId(int id) {
        List<RepresentanteModel> list = new ArrayList<RepresentanteModel>();
        String sql = "SELECT * FROM representante WHERE codigoPai = ?";
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
    */

    public RepresentanteModel save(RepresentanteModel representante)
    {
        return representante.getCodigo() > 0 ? update(representante) : create(representante);
    }

    public RepresentanteModel create(RepresentanteModel representante) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO representante (nome, email, codigoTurma) VALUES (?, ?, ?)",
                new String[] { "ID" }
            );

            ps.setString(1, representante.getNome());
            ps.setString(2, representante.getEmail());
            ps.setInt(3, representante.getCodigoTurma());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            representante.setCodigo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
          ConnectionHelper.close(c);
        }
        return representante;
    }

    public RepresentanteModel update(RepresentanteModel representante) {
        Connection c = null;
          try {
              c = ConnectionHelper.getConnection();
              PreparedStatement ps = c.prepareStatement("UPDATE representante SET nome=?, email=?, codigoTurma=? WHERE codigo=?");
              ps.setString(1, representante.getNome());
              ps.setString(2, representante.getEmail());
              ps.setInt(3, representante.getCodigoTurma());
              ps.setInt(4, representante.getCodigo());

              ps.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
          } finally {
            ConnectionHelper.close(c);
          }
          return representante;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM representante WHERE codigo=?");
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

    protected RepresentanteModel processRow(ResultSet rs) throws SQLException {
        RepresentanteModel representante = new RepresentanteModel();
        representante.setCodigo(rs.getInt("codigo"));
        representante.setNome(rs.getString("nome"));
        representante.setEmail(rs.getString("email"));
        return representante;
    }

}
