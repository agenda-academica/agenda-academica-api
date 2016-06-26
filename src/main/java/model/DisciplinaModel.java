package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class DisciplinaModel {
    private int id;
    private int idUsuario;
    private int idUniversidade;
    private int idUnidade;
    private int idCurso;
    private int idTurma;
    private String abreviacao;
    private String nome;
    private String horaInicio;
    private String horaFim;
    private int diaSemana;
    private boolean requestStatus;

    public int getId() {
        return id;
    }
    public DisciplinaModel setId(int id) {
        this.id = id;
        return this;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public DisciplinaModel setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
    public int getIdUniversidade() {
        return idUniversidade;
    }
    public DisciplinaModel setIdUniversidade(int idUniversidade) {
        this.idUniversidade = idUniversidade;
        return this;
    }
    public int getIdUnidade() {
        return idUnidade;
    }
    public DisciplinaModel setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
        return this;
    }
    public int getIdCurso() {
        return idCurso;
    }
    public DisciplinaModel setIdCurso(int idCurso) {
        this.idCurso = idCurso;
        return this;
    }
    public int getIdTurma() {
        return idTurma;
    }
    public DisciplinaModel setIdTurma(int idTurma) {
        this.idTurma = idTurma;
        return this;
    }
    public String getAbreviacao() {
        return abreviacao;
    }
    public DisciplinaModel setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
        return this;
    }
    public String getNome() {
        return nome;
    }
    public DisciplinaModel setNome(String nome) {
        this.nome = nome;
        return this;
    }
    public String getHoraInicio() {
        return horaInicio;
    }
    public DisciplinaModel setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }
    public String getHoraFim() {
        return horaFim;
    }
    public DisciplinaModel setHoraFim(String horaFim) {
        this.horaFim = horaFim;
        return this;
    }
    public int getDiaSemana() {
        return diaSemana;
    }
    public DisciplinaModel setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
        return this;
    }
    public boolean getRequestStatus() {
        return requestStatus;
    }
    public DisciplinaModel setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    @Override
    public String toString() {
        return "DisciplinaModel [id=" + id + ", idUsuario=" + idUsuario + ", idUniversidade=" + idUniversidade
                + ", idUnidade=" + idUnidade + ", idCurso=" + idCurso + ", idTurma=" + idTurma + ", abreviacao="
                + abreviacao + ", nome=" + nome + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim + ", diaSemana="
                + diaSemana + ", requestStatus=" + requestStatus + ", getId()=" + getId() + ", getIdUsuario()="
                + getIdUsuario() + ", getIdUniversidade()=" + getIdUniversidade() + ", getIdUnidade()=" + getIdUnidade()
                + ", getIdCurso()=" + getIdCurso() + ", getIdTurma()=" + getIdTurma() + ", getAbreviacao()="
                + getAbreviacao() + ", getNome()=" + getNome() + ", getHoraInicio()=" + getHoraInicio()
                + ", getHoraFim()=" + getHoraFim() + ", getDiaSemana()=" + getDiaSemana() + ", getRequestStatus()="
                + getRequestStatus() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }
}
