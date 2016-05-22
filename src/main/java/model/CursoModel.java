package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class CursoModel {
    private int id;
    private int idUsuario;
    private int idUniversidade;
    private int idUnidade;
    private String abreviacao;
    private String nome;
    private String outrasInformacoes;
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
    public int getIdUnidade() {
        return idUnidade;
    }
    public void setIdUnidade(int idUnidade) {
        this.idUnidade = idUnidade;
    }
    public String getAbreviacao() {
        return abreviacao;
    }
    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }
    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }
    public boolean getRequestStatus() {
        return requestStatus;
    }
    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
    @Override
    public String toString() {
        return "CursoModel [id=" + id + ", idUsuario=" + idUsuario + ", idUniversidade=" + idUniversidade
                + ", idUnidade=" + idUnidade + ", abreviacao=" + abreviacao + ", nome=" + nome + ", outrasInformacoes="
                + outrasInformacoes + ", requestStatus=" + requestStatus + "]";
    }
    
    
}
