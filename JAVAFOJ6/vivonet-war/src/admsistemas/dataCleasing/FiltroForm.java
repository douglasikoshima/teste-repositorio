package admsistemas.dataCleasing;

import java.util.Collection;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;

import admsistemas.dataCleasing.DataCleasingController.Option;

public class FiltroForm extends ActionForm {

	private static final long serialVersionUID = 2013033577864294457L;

	private String dtAtualiza = ConstantesCRM.SVAZIO;
	private String nrCEPFim = ConstantesCRM.SVAZIO;
	private String nrCEPIni = ConstantesCRM.SVAZIO;
	private String[] idSelDDD;
	private String idDispDDD = ConstantesCRM.SVAZIO;
	private String[] idSelUF;
	private String idDispUF = ConstantesCRM.SVAZIO;
	private String tpFiltro = ConstantesCRM.SVAZIO;
	private Collection<Option> optionsUFDisp;
	private Collection<Option> optionsCdAreaDisp;

	public FiltroForm() {
	}

	public void setTpFiltro(String tpFiltro) {
		this.tpFiltro = tpFiltro;
	}

	public String getTpFiltro() {
		return this.tpFiltro;
	}

	public void setIdDispUF(String idDispUF) {
		this.idDispUF = idDispUF;
	}

	public String getIdDispUF() {
		return this.idDispUF;
	}

	public void setIdSelUF(String[] idSelUF) {
		this.idSelUF = idSelUF;
	}

	public String[] getIdSelUF() {
		if (this.idSelUF == null || this.idSelUF.length == 0) {
			this.idSelUF = new String[0];
		}
		return this.idSelUF;
	}

	public void setIdDispDDD(String idDispDDD) {
		this.idDispDDD = idDispDDD;
	}

	public String getIdDispDDD() {
		return this.idDispDDD;
	}

	public void setIdSelDDD(String[] idSelDDD) {
		this.idSelDDD = idSelDDD;
	}

	public String[] getIdSelDDD() {
		if (this.idSelDDD == null || this.idSelDDD.length == 0) {
			this.idSelDDD = new String[0];
		}
		return this.idSelDDD;
	}

	public void setNrCEPIni(String nrCEPIni) {
		this.nrCEPIni = nrCEPIni;
	}

	public String getNrCEPIni() {
		return this.nrCEPIni;
	}

	public void setNrCEPFim(String nrCEPFim) {
		this.nrCEPFim = nrCEPFim;
	}

	public String getNrCEPFim() {
		return this.nrCEPFim;
	}

	public void setDtAtualiza(String dtAtualiza) {
		this.dtAtualiza = dtAtualiza;
	}

	public String getDtAtualiza() {
		return this.dtAtualiza;
	}

	public void setOptionsUFDisp(Collection<Option> optionsUFDisp) {
		this.optionsUFDisp = optionsUFDisp;
	}

	public Collection<Option> getOptionsUFDisp() {
		return this.optionsUFDisp;
	}

	public void setOptionsCdAreaDisp(Collection<Option> optionsCdAreaDisp) {
		this.optionsCdAreaDisp = optionsCdAreaDisp;
	}

	public Collection<Option> getOptionsCdAreaDisp() {
		return this.optionsCdAreaDisp;
	}
}