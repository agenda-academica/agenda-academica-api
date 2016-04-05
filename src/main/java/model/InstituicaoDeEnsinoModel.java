package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class InstituicaoDeEnsinoModel {
	
	private int codigo;
	private int codigoUsuario;
	private String nome;
	private String email;
	private String site;
	private String descricao;
	private String telefone;
	private String unidade;
	private boolean isProfessor;
	
	private ArrayList<AnoLetivoModel> listaDeAnosLetivos = new ArrayList<AnoLetivoModel>();
	
	
	public InstituicaoDeEnsinoModel(){
		
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the unidade
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * @param unidade the unidade to set
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	/**
	 * @return the isProfessor
	 */
	public boolean getIsProfessor() {
		return isProfessor;
	}

	/**
	 * @param isProfessor the isProfessor to set
	 */
	public void setIsProfessor(boolean isProfessor) {
		this.isProfessor = isProfessor;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site the site to set
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * @return the codigoUsuario
	 */
	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	/**
	 * @return the listaDeAnosLetivos
	 */
	public ArrayList<AnoLetivoModel> getListaDeAnosLetivos() {
		return listaDeAnosLetivos;
	}

	/**
	 * @param listaDeAnosLetivos the listaDeAnosLetivos to set
	 */
	public void setListaDeAnosLetivos(ArrayList<AnoLetivoModel> listaDeAnosLetivos) {
		this.listaDeAnosLetivos = listaDeAnosLetivos;
	}
}
	
	
	 