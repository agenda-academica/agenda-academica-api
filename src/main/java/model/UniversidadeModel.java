package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class UniversidadeModel {
    private int codigo;
    private int codigoUsuario;
    private String nome;
    private String abreviacao;
    private String site;
    private String logo;
    private boolean requestStatus;
    // private String email;
    // private String site;
    // private String descricao;
    // private String telefone;
    private ArrayList<UnidadeModel> listaDeUnidades = new ArrayList<UnidadeModel>();

    //ALTERA��O
    //CRIAR LISTA DE lista de unidades(SERA NESCESSARIO UM NOVO MODEL)
    //private ArrayList<AnoLetivoModel> listaDeAnosLetivos = new ArrayList<AnoLetivoModel>();

    public UniversidadeModel() {}

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

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public ArrayList<UnidadeModel> getListaDeUnidades() {
        return listaDeUnidades;
    }

    public void setListaDeUnidades(ArrayList<UnidadeModel> listaDeUnidades) {
        this.listaDeUnidades = listaDeUnidades;
    }

    public boolean getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(boolean requestStatus) {
        this.requestStatus = requestStatus;
    }
}
