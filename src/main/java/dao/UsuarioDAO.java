package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.UsuarioModel;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class UsuarioDAO {
    public UsuarioDAO() {}

    public List<UsuarioModel> findAll() {
        List<UsuarioModel> list = new ArrayList<UsuarioModel>();
        Connection c = null;
        String sql = "SELECT * FROM Usuario ORDER BY nome";
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

    public List<UsuarioModel> findByName(String nome) {
        List<UsuarioModel> list = new ArrayList<UsuarioModel>();
        Connection c = null;
        String sql = "SELECT * FROM Usuario as e " +
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

    public UsuarioModel findById(int id) {
        String sql = "SELECT * FROM Usuario WHERE codigo = ?";
        UsuarioModel usuario = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return usuario;
    }

    /*
    public List<UsuarioModel> findByFatherId(int id) {
        List<UsuarioModel> list = new ArrayList<UsuarioModel>();
        String sql = "SELECT * FROM Usuario WHERE codigoPai = ?";
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

    public UsuarioModel save(UsuarioModel usuario)
    {
        return usuario.getCodigo() > 0 ? update(usuario) : create(usuario);
    }

    public UsuarioModel create(UsuarioModel usuario) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO Usuario (nome, celular, email, senha, login) VALUES (?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCelular());
            ps.setString(3, usuario.getEmail());
            //AQUI TEM QUE USAR CRIPTO GRAFIA
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getLogin());
            //ps.setInt(5, usuario.getCodigo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            usuario.setCodigo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return usuario;
    }

    public UsuarioModel update(UsuarioModel usuario) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE Usuario SET nome=?, celular=?, email=?, senha=?, login=? WHERE codigo=?");
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getCelular());
            ps.setString(3, usuario.getEmail());
            //AQUI TEM QUE USAR CRIPTO GRAFIA
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getLogin());
            ps.setInt(6, usuario.getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return usuario;
    }

    public boolean remove(int id) {
          Connection c = null;
          try {
              c = ConnectionHelper.getConnection();
              PreparedStatement ps = c.prepareStatement("DELETE FROM Usuario WHERE codigo=?");
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

    public UsuarioModel login(UsuarioModel usuario) {
        Connection c = null;
        UsuarioModel usuarioModel = new UsuarioModel();
        String sqlLogin = "SELECT * FROM Usuario WHERE login = ?";
        String sql = "SELECT * FROM Usuario where login = ? and senha = ?";

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuarioModel = processRow(rs);
                usuarioModel.setAuthenticated(true);
            }
            
            PreparedStatement psLogin = c.prepareStatement(sqlLogin);
            psLogin.setString(1, usuario.getLogin());
            ResultSet rsLogin = psLogin.executeQuery();
            if (rsLogin.next()) {
                usuarioModel.setHasLogin(true);
            }
            return usuarioModel;
        } catch (SQLException e) {
            e.printStackTrace();
            usuarioModel.setAuthenticated(false);
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
    }

    protected UsuarioModel processRow(ResultSet rs) throws SQLException {
        UsuarioModel usuario = new UsuarioModel();
        usuario.setCodigo(rs.getInt("codigo"));
        usuario.setNome(rs.getString("nome"));
        usuario.setCelular(rs.getString("celular"));
        usuario.setEmail(rs.getString("email"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setAuthenticated(false);
        return usuario;
    }

}
