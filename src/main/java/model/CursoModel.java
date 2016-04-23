package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class CursoModel {
    private int codigo;
    private int codigoUnidade;
    private String nome;

    //aqui é so caso for professor, tem que ver como vai ficar
    private ArrayList<TurmaModel> listaDeTurmas = new ArrayList<TurmaModel>();

    public CursoModel() {}

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the listaDeTurmas
     */
    public ArrayList<TurmaModel> getListaDeTurmas() {
        return listaDeTurmas;
    }

    /**
     * @param listaDeTurmas the listaDeTurmas to set
     */
    public void setListaDeTurmas(ArrayList<TurmaModel> listaDeTurmas) {
        this.listaDeTurmas = listaDeTurmas;
    }

    public int getCodigoUnidade() {
        return codigoUnidade;
    }

    public void setCodigoUnidade(int codigoUnidade) {
        this.codigoUnidade = codigoUnidade;
    }
}
