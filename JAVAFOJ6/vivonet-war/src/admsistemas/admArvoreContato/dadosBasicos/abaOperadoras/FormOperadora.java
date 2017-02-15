package admsistemas.admArvoreContato.dadosBasicos.abaOperadoras;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmContatoUFOperadoraVODocument.AdmContatoUFOperadoraVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormOperadora extends ActionForm implements Serializable {

	private static final long serialVersionUID = 7403909302553450614L;

	private String msgError = ConstantesCRM.SVAZIO;
	private String idUFOperadora;
	private AdmContatoUFOperadoraVO admContatoUFOperadora;
	private String idUFOperadoraEditado;
	private AdmContatoUFOperadoraVO[] listaOperadoras;
	private String[] a;
	private String[] arrayOperadoraAssociada;
	private String indeterminado;
	private String dtFimVigencia;
	private String dtInicioVigencia;
	private AdmContatoUFOperadoraVO[] operadorasAssociadasVO;
	private AdmContatoUFOperadoraVO[] operadorasExistentesVO;
	private String idContato;

	public FormOperadora() {
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setOperadorasExistentesVO(AdmContatoUFOperadoraVO[] operadorasExistentesVO) {
		this.operadorasExistentesVO = operadorasExistentesVO;
	}

	public AdmContatoUFOperadoraVO[] getOperadorasExistentesVO() {
		return this.operadorasExistentesVO;
	}

	public void setOperadorasAssociadasVO(AdmContatoUFOperadoraVO[] operadorasAssociadasVO) {
		this.operadorasAssociadasVO = operadorasAssociadasVO;
	}

	public AdmContatoUFOperadoraVO[] getOperadorasAssociadasVO() {
		return this.operadorasAssociadasVO;
	}

	public void setDtInicioVigencia(String dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public String getDtInicioVigencia() {
		return this.dtInicioVigencia;
	}

	public void setDtFimVigencia(String dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public String getDtFimVigencia() {
		return this.dtFimVigencia;
	}

	public void setIndeterminado(String indeterminado) {
		this.indeterminado = indeterminado;
	}

	public String getIndeterminado() {
		return this.indeterminado;
	}

	public void setArrayOperadoraAssociada(String[] arrayOperadoraAssociada) {
		this.arrayOperadoraAssociada = arrayOperadoraAssociada;
	}

	public String[] getArrayOperadoraAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayOperadoraAssociada == null || this.arrayOperadoraAssociada.length == 0) {
			this.arrayOperadoraAssociada = new String[1];
		}

		return this.arrayOperadoraAssociada;
	}

	public void setA(String[] a) {
		this.a = a;
	}

	public String[] getA() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.a == null || this.a.length == 0) {
			this.a = new String[1];
		}

		return this.a;
	}

	public void setListaOperadoras(AdmContatoUFOperadoraVO[] listaOperadoras) {
		this.listaOperadoras = listaOperadoras;
	}

	public AdmContatoUFOperadoraVO[] getListaOperadoras() {
		return this.listaOperadoras;
	}

	public void setIdUFOperadoraEditado(String idUFOperadoraEditado) {
		this.idUFOperadoraEditado = idUFOperadoraEditado;
	}

	public String getIdUFOperadoraEditado() {
		return this.idUFOperadoraEditado;
	}

	public void setAdmContatoUFOperadora(AdmContatoUFOperadoraVO admContatoUFOperadora) {
		this.admContatoUFOperadora = admContatoUFOperadora;
	}

	public AdmContatoUFOperadoraVO getAdmContatoUFOperadora() {
		return this.admContatoUFOperadora;
	}

	public void setIdUFOperadora(String idUFOperadora) {
		this.idUFOperadora = idUFOperadora;
	}

	public String getIdUFOperadora() {
		return this.idUFOperadora;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}
}