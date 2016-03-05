package model;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class MateriaModel {
	
	private int codigo;
	
	//if
	
	//aqui caso for aluno
	private int codigoCurso;
	
	//caso professor
	private int codigoTurma;
	
	//fim if
	
	private String nome;
	private String descricao;
	private String diaDaSemana;
	private String sala;
	private ArrayList<String> roteiroDaMateria = new ArrayList<String>();
	
	private ArrayList<AulaModel> listaDeAulas = new ArrayList<AulaModel>();
 

	//private boolean isProfessor;

	public MateriaModel(){
		
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
	/**
	 * @return the diaDaSemana
	 */
	public String getDiaDaSemana() {
		return diaDaSemana;
	}
	/**
	 * @param diaDaSemana the diaDaSemana to set
	 */
	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}


	/**
	 * @return the sala
	 */
	public String getSala() {
		return sala;
	}


	/**
	 * @param sala the sala to set
	 */
	public void setSala(String sala) {
		this.sala = sala;
	}


	/**
	 * @return the roteiroDaMateria
	 */
	public ArrayList<String> getRoteiroDaMateria() {
		return roteiroDaMateria;
	}


	/**
	 * @param roteiroDaMateria the roteiroDaMateria to set
	 */
	public void setRoteiroDaMateria(ArrayList<String> roteiroDaMateria) {
		this.roteiroDaMateria = roteiroDaMateria;
	}


	/**
	 * @return the codigoCurso
	 */
	public int getCodigoCurso() {
		return codigoCurso;
	}


	/**
	 * @param codigoCurso the codigoCurso to set
	 */
	public void setCodigoCurso(int codigoCurso) {
		this.codigoCurso = codigoCurso;
	}


	/**
	 * @return the codigoTurma
	 */
	public int getCodigoTurma() {
		return codigoTurma;
	}


	/**
	 * @param codigoTurma the codigoTurma to set
	 */
	public void setCodigoTurma(int codigoTurma) {
		this.codigoTurma = codigoTurma;
	}


	/**
	 * @return the listaDeAulas
	 */
	public ArrayList<AulaModel> getListaDeAulas() {
		return listaDeAulas;
	}


	/**
	 * @param listaDeAulas the listaDeAulas to set
	 */
	public void setListaDeAulas(ArrayList<AulaModel> listaDeAulas) {
		this.listaDeAulas = listaDeAulas;
	}
	

}
