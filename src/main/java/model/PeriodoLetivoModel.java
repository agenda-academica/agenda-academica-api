

package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class PeriodoLetivoModel {
    private int id;
    private int idUniversidade;
    private int idUsuario;
    private String titulo;
    private String dataInicio;
    private String dataFim;
    private String cor;
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
    public void setId(int id) {
        this.id = id;
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
    public void setIdUniversidade(int idUniversidade) {
        this.idUniversidade = idUniversidade;
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
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }
    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
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
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
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
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
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
    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
    
}