package model;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class DisciplinaModel {
    // MUDAR PARA DISCIPLINA
    private int codigo;
    private int codigoTurma;
    private String nome;
    private String diaDaSemana;
    private Date horarioInicio;
    private Date horarioTermino;
    private String sala;
    // private ArrayList<String> roteiroDaMateria = new ArrayList<String>();
    // private ArrayList<EventoModel> listaDeEventos = new ArrayList<EventoModel>();

    public DisciplinaModel() {}

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

    public String getDiaDaSemana() {
        return diaDaSemana;
    }
    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    /*
    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
    */
    public int getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    /*
    public ArrayList<AulaModel> getListaDeAulas() {
        return listaDeAulas;
    }

    public void setListaDeAulas(ArrayList<AulaModel> listaDeAulas) {
        this.listaDeAulas = listaDeAulas;
    }
    */

    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(Date horarioTermino) {
        this.horarioTermino = horarioTermino;
    }
}
