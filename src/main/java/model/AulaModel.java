package model;

import java.util.ArrayList;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class AulaModel {
	
	private int codigo;
	private int codigoMateria;
	private String assunto;
	private Date data;
	
	
	//private boolean isProfessor;
	
	
	//aqui as 3 listas, de conteudo, trabalho e prova
	private ArrayList<ConteudoModel> listaDeConteudo = new ArrayList<ConteudoModel>();
	
	private ArrayList<ProvaModel> listaDeProva = new ArrayList<ProvaModel>();
	
	private ArrayList<TrabalhoModel> listaDeTrabalho = new ArrayList<TrabalhoModel>();


	
	
	public AulaModel(){
		
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
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}
	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	/**
	 * @return the data
	 */
	public Date getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return the codigoMateria
	 */
	public int getCodigoMateria() {
		return codigoMateria;
	}

	/**
	 * @param codigoMateria the codigoMateria to set
	 */
	public void setCodigoMateria(int codigoMateria) {
		this.codigoMateria = codigoMateria;
	}

	/**
	 * @return the listaDeConteudo
	 */
	public ArrayList<ConteudoModel> getListaDeConteudo() {
		return listaDeConteudo;
	}

	/**
	 * @param listaDeConteudo the listaDeConteudo to set
	 */
	public void setListaDeConteudo(ArrayList<ConteudoModel> listaDeConteudo) {
		this.listaDeConteudo = listaDeConteudo;
	}

	/**
	 * @return the listaDeProva
	 */
	public ArrayList<ProvaModel> getListaDeProva() {
		return listaDeProva;
	}

	/**
	 * @param listaDeProva the listaDeProva to set
	 */
	public void setListaDeProva(ArrayList<ProvaModel> listaDeProva) {
		this.listaDeProva = listaDeProva;
	}

	/**
	 * @return the listaDeTrabalho
	 */
	public ArrayList<TrabalhoModel> getListaDeTrabalho() {
		return listaDeTrabalho;
	}

	/**
	 * @param listaDeTrabalho the listaDeTrabalho to set
	 */
	public void setListaDeTrabalho(ArrayList<TrabalhoModel> listaDeTrabalho) {
		this.listaDeTrabalho = listaDeTrabalho;
	}
	

}
