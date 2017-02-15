package admsistemas.relatorios.relatorioRechamadaOnline;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class RelatorioForm extends ActionForm {

	private static final long serialVersionUID = -5691895021835238380L;

	private Resultset resultset;

	private String relatorio = ConstantesCRM.SVAZIO;
	private String tipo = ConstantesCRM.SVAZIO;
	private String dataInicio = ConstantesCRM.SVAZIO;
	private String dataTermino = ConstantesCRM.SVAZIO;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
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

	public void setResultset(Resultset resultset) {
		this.resultset = resultset;
	}

	public Resultset getResultset() {
		return this.resultset;
	}
}