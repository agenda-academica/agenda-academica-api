package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class UnidadeModel {
    private int id;
    private int idUsuario;
    private int idUniversidade;
    private String nome;
    private String endereco;
    private String outrasInformacoes;
    private boolean unidadeSede;
    private boolean requestStatus;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdUniversidade() {
        return idUniversidade;
    }
    public void setIdUniversidade(int idUniversidade) {
        this.idUniversidade = idUniversidade;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }
    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }
    public boolean isUnidadeSede() {
        return unidadeSede;
    }
    public void setUnidadeSede(boolean unidadeSede) {
        this.unidadeSede = unidadeSede;
    }
    public boolean getRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "UnidadeModel [id=" + id + ", idUsuario=" + idUsuario + ", idUniversidade=" + idUniversidade + ", nome="
                + nome + ", endereco=" + endereco + ", outrasInformacoes=" + outrasInformacoes + ", unidadeSede="
                + unidadeSede + ", requestStatus=" + requestStatus + ", getId()=" + getId() + ", getIdUsuario()="
                + getIdUsuario() + ", getIdUniversidade()=" + getIdUniversidade() + ", getNome()=" + getNome()
                + ", getEndereco()=" + getEndereco() + ", getOutrasInformacoes()=" + getOutrasInformacoes()
                + ", isUnidadeSede()=" + isUnidadeSede() + ", getRequestStatus()=" + getRequestStatus()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }
}
