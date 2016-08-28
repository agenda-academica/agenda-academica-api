package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class EventoModel {
    private int id;
    private int idUsuario;
    private int idUniversidade;
    private int idUnidade;
    private int idCurso;
    private int idTurma;
    private int idDisciplina;
    private String tipo;
    private String titulo;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private String horaInicio;
    private String horaFim;
    private boolean requestStatus;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public EventoModel setId(int id) {
        this.id = id;
        return this;
    }
    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    /**
     * @param idUsuario the idUsuario to set
     */
    public EventoModel setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
    /**
     * @return the idUniversidade
     */
    public int getIdUniversidade() {
        return idUniversidade;
    }
    /**
     * @param idUniversidade the idUniversidade to set
     */
    public EventoModel setIdUniversidade(int idUniversidade) {
        this.idUniversidade = idUniversidade;
        return this;
    }
    /**
     * @return the idUnidade
     */
    public int getIdUnidade() {
        return idUnidade;
    }
    /**
     * @param idUnidade the idUnidade to set
     */
    public EventoModel setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
        return this;
    }
    /**
     * @return the idCurso
     */
    public int getIdCurso() {
        return idCurso;
    }
    /**
     * @param idCurso the idCurso to set
     */
    public EventoModel setIdCurso(int idCurso) {
        this.idCurso = idCurso;
        return this;
    }
    /**
     * @return the idTurma
     */
    public int getIdTurma() {
        return idTurma;
    }
    /**
     * @param idTurma the idTurma to set
     */
    public EventoModel setIdTurma(int idTurma) {
        this.idTurma = idTurma;
        return this;
    }
    /**
     * @return the idDisciplina
     */
    public int getIdDisciplina() {
        return idDisciplina;
    }
    /**
     * @param idDisciplina the idDisciplina to set
     */
    public EventoModel setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
        return this;
    }
    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * @param tipo the tipo to set
     */
    public EventoModel setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * @param titulo the titulo to set
     */
    public EventoModel setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }
    /**
     * @param descricao the descricao to set
     */
    public EventoModel setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
    /**
     * @return the dataInicio
     */
    public String getDataInicio() {
        return dataInicio;
    }
    /**
     * @param dataInicio the dataInicio to set
     */
    public EventoModel setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }
    /**
     * @return the dataFim
     */
    public String getDataFim() {
        return dataFim;
    }
    /**
     * @param dataFim the dataFim to set
     */
    public EventoModel setDataFim(String dataFim) {
        this.dataFim = dataFim;
        return this;
    }
    /**
     * @return the horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }
    /**
     * @param horaInicio the horaInicio to set
     */
    public EventoModel setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }
    /**
     * @return the horaFim
     */
    public String getHoraFim() {
        return horaFim;
    }
    /**
     * @param horaFim the horaFim to set
     */
    public EventoModel setHoraFim(String horaFim) {
        this.horaFim = horaFim;
        return this;
    }
    /**
     * @return the requestStatus
     */
    public boolean getRequestStatus() {
        return requestStatus;
    }
    /**
     * @param requestStatus the requestStatus to set
     */
    public EventoModel setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }
    
    @Override
    public String toString() {
        return "EventoModel [id=" + id + ", idUsuario=" + idUsuario + ", idUniversidade=" + idUniversidade
                + ", idUnidade=" + idUnidade + ", idCurso=" + idCurso + ", idTurma=" + idTurma + ", idDisciplina="
                + idDisciplina + ", tipo=" + tipo + ", titulo=" + titulo + ", descricao=" + descricao + ", dataInicio="
                + dataInicio + ", dataFim=" + dataFim + ", horaInicio=" + horaInicio + ", horaFim=" + horaFim
                + ", getId()=" + getId() + ", getIdUsuario()=" + getIdUsuario() + ", getIdUniversidade()="
                + getIdUniversidade() + ", getIdUnidade()=" + getIdUnidade() + ", getIdCurso()=" + getIdCurso()
                + ", getIdTurma()=" + getIdTurma() + ", getIdDisciplina()=" + getIdDisciplina() + ", getTipo()="
                + getTipo() + ", getTitulo()=" + getTitulo() + ", getDescricao()=" + getDescricao()
                + ", getDataInicio()=" + getDataInicio() + ", getDataFim()=" + getDataFim() + ", getHoraInicio()="
                + getHoraInicio() + ", getHoraFim()=" + getHoraFim() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
}
