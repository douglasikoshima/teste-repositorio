package VOLE.relatorioMVE;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;

public class RelatorioForm extends ActionForm {

	private static final long serialVersionUID = -7779694482169327787L;

	private Resultset resultset;

	private String dataInicio = ConstantesCRM.SVAZIO;
	private String dataTermino = ConstantesCRM.SVAZIO;
	private String relatorio = ConstantesCRM.SVAZIO;
	private String tipo = ConstantesCRM.SVAZIO;
	
	public Resultset getResultset() {
		return resultset;
	}
	public void setResultset(Resultset resultset) {
		this.resultset = resultset;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataTermino() {
		return dataTermino;
	}
	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}
	public String getRelatorio() {
		return relatorio;
	}
	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}