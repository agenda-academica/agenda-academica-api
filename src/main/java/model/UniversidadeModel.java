package model;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class UniversidadeModel {
    private int id;
    private int idUsuario;
    private String nome;
    private String abreviacao;
    private String site;
    private String logo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public String toString() {
        return "UniversidadeModel [id=" + id + ", idUsuario=" + idUsuario + ", nome=" + nome + ", abreviacao="
                + abreviacao + ", site=" + site + ", logo=" + logo + ", requestStatus=" + requestStatus + "]";
    }
}
