package model;

import java.io.File;
//import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class AnexoModel_TALVEZ_NAO_SERA_USADA {
    private int codigo;

    //aqui tem que ter uma verificação, pois apenas um desses codigos vai ser preenchido,
    //ou codigo do conteudo, ou trabalho ou prova

    //VAI SER DELETADA, SAIU NA VERSÃO (1.3)
    /*
    private int codigoConteudo;
    private int codigoTrabalho;
    private int codigoProva;*/

    //ALTERADO POR CODIGO DO EVENTO, ENTROU NA VERSÃO (1.3)
    private int codigoEvento;

    private String nome;
    private String caminhoArquivo;
    private String tipoArquivo;
    private String descricaoArquivo;
    private String tamanhoArquivo;
    private File arquivo;

    public AnexoModel_TALVEZ_NAO_SERA_USADA() {}

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getDescricaoArquivo() {
        return descricaoArquivo;
    }

    public void setDescricaoArquivo(String descricaoArquivo) {
        this.descricaoArquivo = descricaoArquivo;
    }

    public String getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(String tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public File getArquivo() {
        return arquivo;
    }

    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    /*
    public int getCodigoConteudo() {
        return codigoConteudo;
    }

    public void setCodigoConteudo(int codigoConteudo) {
        this.codigoConteudo = codigoConteudo;
    }

    public int getCodigoProva() {
        return codigoProva;
    }

    public void setCodigoProva(int codigoProva) {
        this.codigoProva = codigoProva;
    }

    public int getCodigoTrabalho() {
        return codigoTrabalho;
    }

    public void setCodigoTrabalho(int codigoTrabalho) {
        this.codigoTrabalho = codigoTrabalho;
    }
    */

    public int getCodigoEvento() {
        return codigoEvento;
    }

    public void setCodigoEvento(int codigoEvento) {
        this.codigoEvento = codigoEvento;
    }
}
