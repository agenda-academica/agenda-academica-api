package model;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * @author Sergio Eduardo Bertolazo
 */
@XmlRootElement
public class AnoLetivoModel {
	
	private int codigo;
	private int codigoInstituicaoDeEnsino;
	private String anoLetivo;
	private String descricao;

	//private boolean isProfessor;
	
	private ArrayList<CursoModel> listaDeCursos = new ArrayList<CursoModel>();
	
	public AnoLetivoModel(){
		
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
	 * @return the anoLetivo
	 */
	public String getAnoLetivo() {
		return anoLetivo;
	}

	/**
	 * @param anoLetivo the anoLetivo to set
	 */
	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
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
	 * @return the codigoInstituicaoDeEnsino
	 */
	public int getCodigoInstituicaoDeEnsino() {
		return codigoInstituicaoDeEnsino;
	}

	/**
	 * @param codigoInstituicaoDeEnsino the codigoInstituicaoDeEnsino to set
	 */
	public void setCodigoInstituicaoDeEnsino(int codigoInstituicaoDeEnsino) {
		this.codigoInstituicaoDeEnsino = codigoInstituicaoDeEnsino;
	}

	/**
	 * @return the listaDeCursos
	 */
	public ArrayList<CursoModel> getListaDeCursos() {
		return listaDeCursos;
	}

	/**
	 * @param listaDeCursos the listaDeCursos to set
	 */
	public void setListaDeCursos(ArrayList<CursoModel> listaDeCursos) {
		this.listaDeCursos = listaDeCursos;
	}

}
