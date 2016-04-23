package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class InstituicaoDeEnsinoModel {
    private int codigo;
    private int codigoUsuario;
    private String nome;
    // private String email;
    // private String site;
    // private String descricao;
    // private String telefone;
    private ArrayList<UnidadeModel> listaDeUnidades = new ArrayList<UnidadeModel>();

    //ALTERAÇÃO
    //CRIAR LISTA DE lista de unidades(SERA NESCESSARIO UM NOVO MODEL)
    //private ArrayList<AnoLetivoModel> listaDeAnosLetivos = new ArrayList<AnoLetivoModel>();

    public InstituicaoDeEnsinoModel() {}

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
}
