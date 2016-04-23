package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.AnexoModel_TALVEZ_NAO_SERA_USADA;
import model.AnexoModel_TALVEZ_NAO_SERA_USADA;

/**
 * @author Sergio Eduardo Bertolazo
 */
public class AnexoDAO_TALVEZ_NAO_SERA_USADO {

    public AnexoDAO_TALVEZ_NAO_SERA_USADO() {}

    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findAll() {


        List<AnexoModel_TALVEZ_NAO_SERA_USADA> list = new ArrayList<AnexoModel_TALVEZ_NAO_SERA_USADA>();
        Connection c = null;
        String sql = "SELECT * FROM anexo";

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

    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByName(String descricao) {
        List<AnexoModel_TALVEZ_NAO_SERA_USADA> list = new ArrayList<AnexoModel_TALVEZ_NAO_SERA_USADA>();
        Connection c = null;
        String sql = "SELECT * FROM anexo as e " +
            "WHERE UPPER(descricao) LIKE ? ";

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + descricao.toUpperCase() + "%");
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

    public AnexoModel_TALVEZ_NAO_SERA_USADA findById(int id) {
        String sql = "SELECT * FROM anexo WHERE codigo = ?";
        AnexoModel_TALVEZ_NAO_SERA_USADA anexo = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                anexo = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return anexo;
    }

    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByFatherIdConteudo(int id) {
        List<AnexoModel_TALVEZ_NAO_SERA_USADA> list = new ArrayList<AnexoModel_TALVEZ_NAO_SERA_USADA>();
        String sql = "SELECT * FROM anexo WHERE codigoConteudo = ?";
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

    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByFatherIdProva(int id) {
        List<AnexoModel_TALVEZ_NAO_SERA_USADA> list = new ArrayList<AnexoModel_TALVEZ_NAO_SERA_USADA>();
        String sql = "SELECT * FROM anexo WHERE codigoProva = ?";
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

    public List<AnexoModel_TALVEZ_NAO_SERA_USADA> findByFatherIdTrabalho(int id) {
        List<AnexoModel_TALVEZ_NAO_SERA_USADA> list = new ArrayList<AnexoModel_TALVEZ_NAO_SERA_USADA>();
        String sql = "SELECT * FROM anexo WHERE codigoTrabalho = ?";
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

    public AnexoModel_TALVEZ_NAO_SERA_USADA save(AnexoModel_TALVEZ_NAO_SERA_USADA anexo) {
        return anexo.getCodigo() > 0 ? update(anexo) : create(anexo);
    }

    public AnexoModel_TALVEZ_NAO_SERA_USADA create(AnexoModel_TALVEZ_NAO_SERA_USADA anexo) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement(
                "INSERT INTO anexo (caminhoArquivo, codigoConteudo, codigoProva, codigoTrabalho, descricaoArquivo, nome, tamanhoArquivo, tipoArquivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new String[] { "ID" }
            );

            //vai ser usado servidor de arquivos
            //anexo.setArquivo(arquivo);

            ps.setString(1, anexo.getCaminhoArquivo());
            //ps.setInt(2, anexo.getCodigoConteudo());
            //ps.setInt(3, anexo.getCodigoProva());
            //ps.setInt(4, anexo.getCodigoTrabalho());
            ps.setString(5, anexo.getDescricaoArquivo());
            ps.setString(6, anexo.getNome());
            ps.setString(7, anexo.getTamanhoArquivo());
            ps.setString(8, anexo.getTipoArquivo());
            //ps.setInt(9, anexo.getCodigo());

            //depois ver como resolver os itens abaixo!
            //anexo.setListaDeAnexos(listaDeAnexos);
            // anexo.setListaDeNotas(listaDeAnexos);
            //anexo.setNota(listaDeAnexos);

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            // Update the id in the returned object. This is important as this value must be returned to the client.
            int id = rs.getInt(1);
            anexo.setCodigo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return anexo;
    }

    public AnexoModel_TALVEZ_NAO_SERA_USADA update(AnexoModel_TALVEZ_NAO_SERA_USADA anexo) {
        Connection c = null;

        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE anexo SET caminhoArquivo=?, codigoConteudo=?, codigoProva=?, codigoTrabalho=?, descricaoArquivo=?, nome=?, tamanhoArquivo=?, tipoArquivo=? WHERE codigo=?");
            //vai ser usado servidor de arquivos
            //anexo.setArquivo(arquivo);
            ps.setString(1, anexo.getCaminhoArquivo());
            //ps.setInt(2, anexo.getCodigoConteudo());
            //ps.setInt(3, anexo.getCodigoProva());
            //ps.setInt(4, anexo.getCodigoTrabalho());
            ps.setString(5, anexo.getDescricaoArquivo());
            ps.setString(6, anexo.getNome());
            ps.setString(7, anexo.getTamanhoArquivo());
            ps.setString(8, anexo.getTipoArquivo());
            ps.setInt(9, anexo.getCodigo());
            ps.executeUpdate();
          } catch (SQLException e) {
              e.printStackTrace();
              throw new RuntimeException(e);
        } finally {
            ConnectionHelper.close(c);
        }
        return anexo;
    }

    public boolean remove(int id) {
          Connection c = null;
          try {
              c = ConnectionHelper.getConnection();
              PreparedStatement ps = c.prepareStatement("DELETE FROM anexo WHERE codigo=?");
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

    protected AnexoModel_TALVEZ_NAO_SERA_USADA processRow(ResultSet rs) throws SQLException {
        AnexoModel_TALVEZ_NAO_SERA_USADA anexo = new AnexoModel_TALVEZ_NAO_SERA_USADA();
        anexo.setCodigo(rs.getInt("codigo"));
        //vai ser usado servidor de arquivos
        //anexo.setArquivo(arquivo);
        anexo.setCaminhoArquivo(rs.getString("caminhoArquivo"));
        //anexo.setCodigoConteudo(rs.getInt("codigoConteudo"));
        //anexo.setCodigoProva(rs.getInt("codigoProva"));
        //anexo.setCodigoTrabalho(rs.getInt("codigoTrabalho"));
        anexo.setDescricaoArquivo(rs.getString("descricaoArquivo"));
        anexo.setNome(rs.getString("nome"));
        anexo.setTamanhoArquivo(rs.getString("tamanhoArquivo"));
        anexo.setTipoArquivo(rs.getString("tipoArquivo"));
        return anexo;
    }

}
