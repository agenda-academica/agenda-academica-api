package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class UnidadeModel {
    private int codigo;
    private int codigoIntituicaoDeEnsino;
    private String nome;
    private ArrayList<CursoModel> listaDeCursos = new ArrayList<CursoModel>();

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

    public ArrayList<CursoModel> getListaDeCursos() {
        return listaDeCursos;
    }

    public void setListaDeCursos(ArrayList<CursoModel> listaDeCursos) {
        this.listaDeCursos = listaDeCursos;
    }

    public int getCodigoIntituicaoDeEnsino() {
        return codigoIntituicaoDeEnsino;
    }

    public void setCodigoIntituicaoDeEnsino(int codigoIntituicaoDeEnsino) {
        this.codigoIntituicaoDeEnsino = codigoIntituicaoDeEnsino;
    }
}
