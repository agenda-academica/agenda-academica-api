package model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class EventoModel {
    private int codigo;
    private String titulo;
    private int codigoDisciplina;
    private int codigoTurma;
    private int codigoCurso;
    private int codigoUnidade;
    private int codigoInstituicaoDeEnsino;
    private int codigoUsuario;
    // private Date dataInicioEvento;
    // private Date dataFimEvento;
    private String dataInicioEvento;
    private String dataFimEvento;
    private String descricao;
    //em minutos, para alertar antes do evento
    private int lembrete;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(int codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public int getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoUnidade() {
        return codigoUnidade;
    }

    public void setCodigoUnidade(int codigoUnidade) {
        this.codigoUnidade = codigoUnidade;
    }

    public int getCodigoInstituicaoDeEnsino() {
        return codigoInstituicaoDeEnsino;
    }

    public void setCodigoInstituicaoDeEnsino(int codigoInstituicaoDeEnsino) {
        this.codigoInstituicaoDeEnsino = codigoInstituicaoDeEnsino;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /*
    public Date getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(Date dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public Date getDataFimEvento() {
        return dataFimEvento;
    }

    public void setDataFimEvento(Date dataFimEvento) {
        this.dataFimEvento = dataFimEvento;
    }
    */

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLembrete() {
        return lembrete;
    }

    public void setLembrete(int lembrete) {
        this.lembrete = lembrete;
    }

    public String getDataInicioEvento() {
        return dataInicioEvento;
    }

    public void setDataInicioEvento(String dataInicioEvento) {
        this.dataInicioEvento = dataInicioEvento;
    }

    public String getDataFimEvento() {
        return dataFimEvento;
    }

    public void setDataFimEvento(String dataFimEvento) {
        this.dataFimEvento = dataFimEvento;
    }

    //talvez não vai existir
    //private ArrayList<AnexoModel> listaDeAnexos = new ArrayList<AnexoModel>();
}
