package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.InstituicaoDeEnsinoModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class InstituicaoDeEnsinoDAO {
    public InstituicaoDeEnsinoDAO() {}

    public List<InstituicaoDeEnsinoModel> findAll() {
        List<InstituicaoDeEnsinoModel> list = new ArrayList<InstituicaoDeEnsinoModel>();
        Connection c = null;
        String sql = "SELECT * FROM InstituicaoDeEnsino ORDER BY nome";
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

    public List<InstituicaoDeEnsinoModel> findByName(String nome) {
        List<InstituicaoDeEnsinoModel> list = new ArrayList<InstituicaoDeEnsinoModel>();
        Connection c = null;
        String sql = "SELECT * FROM InstituicaoDeEnsino as e " +
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

    public InstituicaoDeEnsinoModel findById(int id) {
        String sql = "SELECT * FROM InstituicaoDeEnsino WHERE codigo = ?";
        InstituicaoDeEnsinoModel instituicao = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                instituicao = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return instituicao;
    }

    public List<InstituicaoDeEnsinoModel> findByUsuarioId(int id) {
        List<InstituicaoDeEnsinoModel> list = new ArrayList<InstituicaoDeEnsinoModel>();
        String sql = "SELECT * FROM InstituicaoDeEnsino WHERE codigoUsuario = ?";
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

    public List<InstituicaoDeEnsinoModel> findByFatherId(int id) {
        List<InstituicaoDeEnsinoModel> list = new ArrayList<InstituicaoDeEnsinoModel>();
        String sql = "SELECT * FROM InstituicaoDeEnsino WHERE codigoUsuario = ?";
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

    public InstituicaoDeEnsinoModel save(InstituicaoDeEnsinoModel instituicao)
    {
        return instituicao.getCodigo() > 0 ? update(instituicao) : create(instituicao);
    }

    public InstituicaoDeEnsinoModel create(InstituicaoDeEnsinoModel instituicao) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO InstituicaoDeEnsino (codigoUsuario, nome, abreviacao, site, logo) VALUES (?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );
            
            ps.setInt(1, instituicao.getCodigoUsuario());
            ps.setString(2, instituicao.getNome());
            ps.setString(3, instituicao.getAbreviacao());
            ps.setString(4, instituicao.getSite());
            ps.setString(5, instituicao.getLogo());
            // instituicao.setListaDeAnosLetivos(listaDeAnosLetivos);
            // ps.setInt(9, instituicao.getCodigo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            instituicao.setCodigo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return instituicao;
    }

    public InstituicaoDeEnsinoModel update(InstituicaoDeEnsinoModel instituicao) {
        Connection c = null;

        String query = String.format(
              " UPDATE InstituicaoDeEnsino"
	        + " SET"
	        + "   codigoUsuario=%d,"
	        + "   nome='%s',"
	        + "   abreviacao='%s',"
	        + "   site='%s',"
	        + "   logo='%s'"
	        + " WHERE codigo=%d",
            instituicao.getCodigoUsuario(),
            instituicao.getNome(),
            instituicao.getAbreviacao(),
            instituicao.getSite(),
            instituicao.getLogo(),
            instituicao.getCodigo()
        );
        System.out.println(query);
        
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ps.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return instituicao;
    }

    public InstituicaoDeEnsinoModel remove(int id) {
        Connection c = null;
        InstituicaoDeEnsinoModel instituicao = new InstituicaoDeEnsinoModel();
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM InstituicaoDeEnsino WHERE codigo=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            instituicao.setRequestStatus(count == 1);
            return instituicao;
          } catch (Exception e) {
            instituicao.setRequestStatus(false);
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected InstituicaoDeEnsinoModel processRow(ResultSet rs) throws SQLException {
        InstituicaoDeEnsinoModel instituicao = new InstituicaoDeEnsinoModel();

        instituicao.setCodigo(rs.getInt("codigo"));
        instituicao.setCodigoUsuario(rs.getInt("codigoUsuario"));
        instituicao.setNome(rs.getString("nome"));
        instituicao.setAbreviacao(rs.getString("abreviacao"));
        instituicao.setSite(rs.getString("site"));
        instituicao.setLogo(rs.getString("logo"));
        // instituicao.setListaDeAnosLetivos(listaDeAnosLetivos);
        return instituicao;
    }

}
