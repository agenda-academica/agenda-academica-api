package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class CursoModel {
	
	private int codigo;
	private int codigoAnoLetivo;
	private String nome;
	private String descricao;
	private String areaDoConhecimento;
	
	//private boolean isProfessor;
	
	//aqui é so caso for professor, tem que ver como vai ficar
	private ArrayList<TurmaModel> listaDeTurmas = new ArrayList<TurmaModel>();
	
	//caso for aluno ja é a lista de materias
	private ArrayList<MateriaModel> listaDeMaterias = new ArrayList<MateriaModel>();

	
	
	public CursoModel(){
		
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
	 * @return the areaDoConhecimento
	 */
	public String getAreaDoConhecimento() {
		return areaDoConhecimento;
	}

	/**
	 * @param areaDoConhecimento the areaDoConhecimento to set
	 */
	public void setAreaDoConhecimento(String areaDoConhecimento) {
		this.areaDoConhecimento = areaDoConhecimento;
	}

	/**
	 * @return the listaDeTurmas
	 */
	public ArrayList<TurmaModel> getListaDeTurmas() {
		return listaDeTurmas;
	}

	/**
	 * @param listaDeTurmas the listaDeTurmas to set
	 */
	public void setListaDeTurmas(ArrayList<TurmaModel> listaDeTurmas) {
		this.listaDeTurmas = listaDeTurmas;
	}

	/**
	 * @return the listaDeMaterias
	 */
	public ArrayList<MateriaModel> getListaDeMaterias() {
		return listaDeMaterias;
	}

	/**
	 * @param listaDeMaterias the listaDeMaterias to set
	 */
	public void setListaDeMaterias(ArrayList<MateriaModel> listaDeMaterias) {
		this.listaDeMaterias = listaDeMaterias;
	}

	/**
	 * @return the codigoAnoLetivo
	 */
	public int getCodigoAnoLetivo() {
		return codigoAnoLetivo;
	}

	/**
	 * @param codigoAnoLetivo the codigoAnoLetivo to set
	 */
	public void setCodigoAnoLetivo(int codigoAnoLetivo) {
		this.codigoAnoLetivo = codigoAnoLetivo;
	}

}
