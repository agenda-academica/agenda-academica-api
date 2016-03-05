package model;
import javax.xml.bind.annotation.XmlRootElement;
//import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class NotaModel {

	private int codigo;

	//aqui verifico se é prova ou trabalho
	private int codigoProva;
	private int codigoTrabalho;

	private String descricao;
	private int nota;
	private UsuarioModel aluno;


	//private boolean isProfessor;

	public NotaModel(){

	}

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
	 * @return the nota
	 */
	public int getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(int nota) {
		this.nota = nota;
	}
	/**
	 * @return the aluno
	 */
	public UsuarioModel getAluno() {
		return aluno;
	}
	/**
	 * @param aluno the aluno to set
	 */
	public void setAluno(UsuarioModel aluno) {
		this.aluno = aluno;
	}

	/**
	 * @return the codigoProva
	 */
	public int getCodigoProva() {
		return codigoProva;
	}

	/**
	 * @param codigoProva the codigoProva to set
	 */
	public void setCodigoProva(int codigoProva) {
		this.codigoProva = codigoProva;
	}

	/**
	 * @return the codigoTrabalho
	 */
	public int getCodigoTrabalho() {
		return codigoTrabalho;
	}

	/**
	 * @param codigoTrabalho the codigoTrabalho to set
	 */
	public void setCodigoTrabalho(int codigoTrabalho) {
		this.codigoTrabalho = codigoTrabalho;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}
