package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class TurmaModel {
    private int codigo;
    //caso for professor so
    private int codigoCurso;
    private String nome;
    //private String descricao;
    //private String emailTurma;
    //private ArrayList<UsuarioModel> listaDeAlunos = new ArrayList<UsuarioModel>();
    private ArrayList<UsuarioModel> listaDeRepresentantes = new ArrayList<UsuarioModel>();
    //private boolean isProfessor = true;
    //SOMENTE CASO FOR PROFESSOR!!!!!!
    private ArrayList<DisciplinaModel> listaDeDisciplina = new ArrayList<DisciplinaModel>();

    public TurmaModel() {}

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


    public ArrayList<UsuarioModel> getListaDeRepresentantes() {
        return listaDeRepresentantes;
    }

    public void setListaDeRepresentantes(ArrayList<UsuarioModel> listaDeRepresentantes) {
        this.listaDeRepresentantes = listaDeRepresentantes;
    }

    public ArrayList<DisciplinaModel> getListaDeMateria() {
        return listaDeDisciplina;
    }

    public void setListaDeMateria(ArrayList<DisciplinaModel> listaDeMateria) {
        this.listaDeDisciplina = listaDeMateria;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}
