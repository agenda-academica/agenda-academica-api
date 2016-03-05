package model;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class ProvaModel {
	
	private int codigo;
	private int codigoAula;
	private Date dataDeEntrega;
	private String descricao;
	private ArrayList<AnexoModel> listaDeAnexos = new ArrayList<AnexoModel>();
 
	//private boolean isProfessor;
	
	//caso for aluno
	private String nota;
	
	//caso for professor
	private ArrayList<NotaModel> listaDeNotas = new ArrayList<NotaModel>();

	//mas tem que pensar em um jeito melhor de fazer isso
	
	public ProvaModel(){
		
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
	 * @return the dataDeEntrega
	 */
	public Date getDataDeEntrega() {
		return dataDeEntrega;
	}

	/**
	 * @param dataDeEntrega the dataDeEntrega to set
	 */
	public void setDataDeEntrega(Date dataDeEntrega) {
		this.dataDeEntrega = dataDeEntrega;
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
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}

	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	/**
	 * @return the listaDeNotas
	 */
	public ArrayList<NotaModel> getListaDeNotas() {
		return listaDeNotas;
	}

	/**
	 * @param listaDeNotas the listaDeNotas to set
	 */
	public void setListaDeNotas(ArrayList<NotaModel> listaDeNotas) {
		this.listaDeNotas = listaDeNotas;
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
