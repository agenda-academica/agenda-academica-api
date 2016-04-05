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
	private String descricao;
	private String emailTurma;
	private ArrayList<UsuarioModel> listaDeAlunos = new ArrayList<UsuarioModel>();
	private ArrayList<UsuarioModel> listaDeRepresentantes = new ArrayList<UsuarioModel>();

	//private boolean isProfessor = true;
	
	//SOMENTE CASO FOR PROFESSOR!!!!!!
	private ArrayList<MateriaModel> listaDeMateria = new ArrayList<MateriaModel>();
 
	
	public TurmaModel(){
		
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
	 * @return the listaDeAlunos
	 */
	public ArrayList<UsuarioModel> getListaDeAlunos() {
		return listaDeAlunos;
	}

	/**
	 * @param listaDeAlunos the listaDeAlunos to set
	 */
	public void setListaDeAlunos(ArrayList<UsuarioModel> listaDeAlunos) {
		this.listaDeAlunos = listaDeAlunos;
	}

	/**
	 * @return the listaDeRepresentantes
	 */
	public ArrayList<UsuarioModel> getListaDeRepresentantes() {
		return listaDeRepresentantes;
	}

	/**
	 * @param listaDeRepresentantes the listaDeRepresentantes to set
	 */
	public void setListaDeRepresentantes(ArrayList<UsuarioModel> listaDeRepresentantes) {
		this.listaDeRepresentantes = listaDeRepresentantes;
	}

	/**
	 * @return the listaDeMateria
	 */
	public ArrayList<MateriaModel> getListaDeMateria() {
		return listaDeMateria;
	}

	/**
	 * @param listaDeMateria the listaDeMateria to set
	 */
	public void setListaDeMateria(ArrayList<MateriaModel> listaDeMateria) {
		this.listaDeMateria = listaDeMateria;
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

	public String getEmailTurma() {
		return emailTurma;
	}

	public void setEmailTurma(String emailTurma) {
		this.emailTurma = emailTurma;
	}
	
}


