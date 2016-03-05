package model;

import java.io.File;
//import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class AnexoModel {
	
	private int codigo;
	
	//aqui tem que ter uma verificação, pois apenas um desses codigos vai ser preenchido, 
	//ou codigo do conteudo, ou trabalho ou prova
	
	private int codigoConteudo;
	private int codigoTrabalho;
	private int codigoProva;
	
	private String nome;
	private String caminhoArquivo;
	private String tipoArquivo;
	private String descricaoArquivo;
	private String tamanhoArquivo;
	private File arquivo;
	
	public AnexoModel(){
		
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
	 * @return the caminhoArquivo
	 */
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}
	/**
	 * @param caminhoArquivo the caminhoArquivo to set
	 */
	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	/**
	 * @return the tipoArquivo
	 */
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	/**
	 * @param tipoArquivo the tipoArquivo to set
	 */
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	/**
	 * @return the descricaoArquivo
	 */
	public String getDescricaoArquivo() {
		return descricaoArquivo;
	}
	/**
	 * @param descricaoArquivo the descricaoArquivo to set
	 */
	public void setDescricaoArquivo(String descricaoArquivo) {
		this.descricaoArquivo = descricaoArquivo;
	}
	/**
	 * @return the tamanhoArquivo
	 */
	public String getTamanhoArquivo() {
		return tamanhoArquivo;
	}
	/**
	 * @param tamanhoArquivo the tamanhoArquivo to set
	 */
	public void setTamanhoArquivo(String tamanhoArquivo) {
		this.tamanhoArquivo = tamanhoArquivo;
	}
	/**
	 * @return the arquivo
	 */
	public File getArquivo() {
		return arquivo;
	}
	/**
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * @return the codigoConteudo
	 */
	public int getCodigoConteudo() {
		return codigoConteudo;
	}

	/**
	 * @param codigoConteudo the codigoConteudo to set
	 */
	public void setCodigoConteudo(int codigoConteudo) {
		this.codigoConteudo = codigoConteudo;
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
 

}
