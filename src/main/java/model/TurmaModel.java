package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class TurmaModel {
    private int id;
    private int idUsuario;
    private int idUniversidade;
    private int idUnidade;
    private int idCurso;
    private String nome;
    private String email;
    private String site;
    private String outrasInformacoes;
    private boolean requestStatus;

    public int getId() {
        return id;
    }
    public TurmaModel setId(int id) {
        this.id = id;
        return this;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public TurmaModel setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
    public int getIdUniversidade() {
        return idUniversidade;
    }
    public TurmaModel setIdUniversidade(int idUniversidade) {
        this.idUniversidade = idUniversidade;
        return this;
    }
    public int getIdUnidade() {
        return idUnidade;
    }
    public TurmaModel setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
        return this;
    }
    public int getIdCurso() {
        return idCurso;
    }
    public TurmaModel setIdCurso(int idCurso) {
        this.idCurso = idCurso;
        return this;
    }
    public String getNome() {
        return nome;
    }
    public TurmaModel setNome(String nome) {
        this.nome = nome;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public TurmaModel setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getSite() {
        return site;
    }
    public TurmaModel setSite(String site) {
        this.site = site;
        return this;
    }
    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }
    public TurmaModel setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
        return this;
    }
    public boolean getRequestStatus() {
        return requestStatus;
    }
    public TurmaModel setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    @Override
    public String toString() {
        return "TurmaModel [id=" + id + ", idUsuario=" + idUsuario + ", idUniversidade=" + idUniversidade
                + ", idUnidade=" + idUnidade + ", idCurso=" + idCurso + ", nome=" + nome + ", email=" + email
                + ", site=" + site + ", outrasInformacoes=" + outrasInformacoes + ", requestStatus=" + requestStatus
                + ", getId()=" + getId() + ", getIdUsuario()=" + getIdUsuario() + ", getIdUniversidade()="
                + getIdUniversidade() + ", getIdUnidade()=" + getIdUnidade() + ", getIdCurso()=" + getIdCurso()
                + ", getNome()=" + getNome() + ", getEmail()=" + getEmail() + ", getSite()=" + getSite()
                + ", getOutrasInformacoes()=" + getOutrasInformacoes() + ", getRequestStatus()=" + getRequestStatus()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
}
