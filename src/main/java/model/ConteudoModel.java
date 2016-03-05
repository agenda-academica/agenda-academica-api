package model;


import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class ConteudoModel {
	
	private int codigo;
	private int codigoAula;
	private String nome;
	private String descricao;
	private ArrayList<AnexoModel> listaDeAnexos = new ArrayList<AnexoModel>();
	
	public ConteudoModel(){
		
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
	 * @return the listaDeAnexos
	 */
	public ArrayList<AnexoModel> getListaDeAnexos() {
		return listaDeAnexos;
	}

	/**
	 * @param listaDeAnexos the listaDeAnexos to set
	 */
	public void setListaDeAnexos(ArrayList<AnexoModel> listaDeAnexos) {
		this.listaDeAnexos = listaDeAnexos;
	}

	/**
	 * @return the codigoAula
	 */
	public int getCodigoAula() {
		return codigoAula;
	}

	/**
	 * @param codigoAula the codigoAula to set
	 */
	public void setCodigoAula(int codigoAula) {
		this.codigoAula = codigoAula;
	}
 	
}
