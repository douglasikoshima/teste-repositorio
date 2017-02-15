package admsistemas.admCalendario.manterCalendario;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmDescricaoFeriadoVODocument.AdmDescricaoFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.AdmFeriadoVODocument.AdmFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFeriadoVODocument.AdmTipoFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.MunicipioVODocument.MunicipioVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormCalendario extends ActionForm {

	private static final long serialVersionUID = 1415488106029596371L;

	private ArrayList listFeriado;
	private AdmCalendarioContainerVO cmbCombo;
	private String ano;
	private String msgError = ConstantesCRM.SVAZIO;
	private UFVO[] listaUFVORelacionados;
	private UFVO[] listaUFVOExistente;
	private String indicativoMunicipal;
	private String dtFeriadoParam;
	private String idFeriadoParam;
	private String dsTipoFeriadoParam;
	private String indMovelParam;
	private String inRelatorioParam;
	private String idTipoFeriadoParam;
	private String dsFeriadoParam;
	private String idDsFeriadoParam;
	private String dsTipoFeriado;
	private String admDescricaoExistente;
	private String admDescricaoNova;
	private String admTipoFeriado;
	private String indicativoUF;
	private String idUF;
	private UFVO[] listaUFs;
	private String idMunicipio;
	private String[] arrayAnoCopia;
	private String[] arrayAnoBase;
	private String[] arrayMunicipioRelacionado;
	private String[] arrayMunicipioExistente;
	private String[] arrayUFVORelacionado;
	private String[] arrayUFVOExistente;
	private String contadorAdmFeriadoLenght;
	private String indMovel;
	private String inRelatorio;
	private String dsFeriado;
	private String idDsFeriado;
	private String dtFeriado;
	private String idFeriado;
	private MunicipioVO[] listaMunicipiosRelacionadosVO;
	private MunicipioVO[] listaMunicipiosExistentesVO;
	private AdmFeriadoVO[] listaAdmFeriadoVO;
	private MunicipioVO[] municipioVO;
	private UFVO[] uFVO;
	private AdmTipoFeriadoVO[] admTipoFeriadoVO;
	private AdmDescricaoFeriadoVO[] admDescricaoFeriadoVO;
	private AdmCalendarioContainerVO admCalendarioContainerVO;
	private UFVO[] listaUfsVORelacionadas;
	private UFVO[] listaUfsVOExistentes;
	private String idTipoFeriado;
	private MunicipioVO[] listaMunicipiosVO;

	public FormCalendario() {
		listaUfsVOExistentes = new UFVO[0];
		listaUfsVORelacionadas = new UFVO[0];
		listaMunicipiosVO = new MunicipioVO[0];
		listaUFs = new UFVO[0];
		listaAdmFeriadoVO = new AdmFeriadoVO[0];
		admTipoFeriadoVO = new AdmTipoFeriadoVO[0];
		listaMunicipiosVO = new MunicipioVO[0];
		listaMunicipiosRelacionadosVO = new MunicipioVO[0];
		listaMunicipiosExistentesVO = new MunicipioVO[0];
	}

	public void setListaMunicipiosVO(MunicipioVO[] listaMunicipiosVO) {

		this.listaMunicipiosVO = listaMunicipiosVO;
	}

	public MunicipioVO[] getListaMunicipiosVO() {
		if (this.listaMunicipiosVO == null || this.listaMunicipiosVO.length == 0) {
			this.listaMunicipiosVO = new MunicipioVO[1];
			this.listaMunicipiosVO[0] = MunicipioVO.Factory.newInstance();
		}
		return this.listaMunicipiosVO;
	}

	public void setListaUfsVOExistentes(UFVO[] listaUfsVOExistentes) {
		this.listaUfsVOExistentes = listaUfsVOExistentes;
	}

	public UFVO[] getListaUfsVOExistentes() {
		return this.listaUfsVOExistentes;
	}

	public void setListaUfsVORelacionadas(UFVO[] listaUfsVORelacionadas) {

		this.listaUfsVORelacionadas = listaUfsVORelacionadas;
	}

	public UFVO[] getListaUfsVORelacionadas() {
		return this.listaUfsVORelacionadas;
	}

	public void setAdmCalendarioContainerVO(AdmCalendarioContainerVO admCalendarioContainerVO) {
		this.admCalendarioContainerVO = admCalendarioContainerVO;
	}

	public AdmCalendarioContainerVO getAdmCalendarioContainerVO() {
		return this.admCalendarioContainerVO;
	}

	public void setAdmDescricaoFeriadoVO(AdmDescricaoFeriadoVO[] admDescricaoFeriadoVO) {
		this.admDescricaoFeriadoVO = admDescricaoFeriadoVO;
	}

	public AdmDescricaoFeriadoVO[] getAdmDescricaoFeriadoVO() {
		return this.admDescricaoFeriadoVO;
	}

	public void setAdmTipoFeriadoVO(AdmTipoFeriadoVO[] admTipoFeriadoVO) {
		this.admTipoFeriadoVO = admTipoFeriadoVO;
	}

	public AdmTipoFeriadoVO[] getAdmTipoFeriadoVO() {
		return this.admTipoFeriadoVO;
	}

	public void setuFVO(UFVO[] uFVO) {
		this.uFVO = uFVO;
	}

	public UFVO[] getuFVO() {
		return this.uFVO;
	}

	public void setMunicipioVO(MunicipioVO[] municipioVO) {
		this.municipioVO = municipioVO;
	}

	public MunicipioVO[] getMunicipioVO() {
		return this.municipioVO;
	}

	public void setListaAdmFeriadoVO(AdmFeriadoVO[] listaAdmFeriadoVO) {
		this.listaAdmFeriadoVO = listaAdmFeriadoVO;
	}

	public AdmFeriadoVO[] getListaAdmFeriadoVO() {
		if (this.listaAdmFeriadoVO == null) {
			this.listaAdmFeriadoVO = new AdmFeriadoVO[0];
		}

		return this.listaAdmFeriadoVO;
	}

	public void setListaMunicipiosExistentesVO(MunicipioVO[] listaMunicipiosExistentesVO) {
		this.listaMunicipiosExistentesVO = listaMunicipiosExistentesVO;
	}

	public MunicipioVO[] getListaMunicipiosExistentesVO() {
		return this.listaMunicipiosExistentesVO;
	}

	public void setListaMunicipiosRelacionadosVO(MunicipioVO[] listaMunicipiosRelacionadosVO) {
		this.listaMunicipiosRelacionadosVO = listaMunicipiosRelacionadosVO;
	}

	public MunicipioVO[] getListaMunicipiosRelacionadosVO() {
		return this.listaMunicipiosRelacionadosVO;
	}

	public void setIdFeriado(String idFeriado) {
		this.idFeriado = idFeriado;
	}

	public String getIdFeriado() {
		return this.idFeriado;
	}

	public void setDtFeriado(String dtFeriado) {
		this.dtFeriado = dtFeriado;
	}

	public String getDtFeriado() {
		return this.dtFeriado;
	}

	public void setIdDsFeriado(String idDsFeriado) {
		this.idDsFeriado = idDsFeriado;
	}

	public String getIdDsFeriado() {
		return this.idDsFeriado;
	}

	public void setDsFeriado(String dsFeriado) {
		this.dsFeriado = dsFeriado;
	}

	public String getDsFeriado() {
		return this.dsFeriado;
	}

	public void setIdTipoFeriado(String idTipoFeriado) {
		this.idTipoFeriado = idTipoFeriado;
	}

	public String getIdTipoFeriado() {
		return this.idTipoFeriado;
	}

	public void setInRelatorio(String inRelatorio) {
		this.inRelatorio = inRelatorio;
	}

	public String getInRelatorio() {
		return this.inRelatorio;
	}

	public void setIndMovel(String indMovel) {
		this.indMovel = indMovel;
	}

	public String getIndMovel() {
		return this.indMovel;
	}

	public void setContadorAdmFeriadoLenght(String contadorAdmFeriadoLenght) {
		this.contadorAdmFeriadoLenght = contadorAdmFeriadoLenght;
	}

	public String getContadorAdmFeriadoLenght() {
		return this.contadorAdmFeriadoLenght;
	}

	public void setArrayUFVOExistente(String[] arrayUFVOExistente) {
		this.arrayUFVOExistente = arrayUFVOExistente;
	}

	public String[] getArrayUFVOExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayUFVOExistente == null || this.arrayUFVOExistente.length == 0) {
			this.arrayUFVOExistente = new String[1];
		}

		return this.arrayUFVOExistente;
	}

	public void setArrayUFVORelacionado(String[] arrayUFVORelacionado) {
		this.arrayUFVORelacionado = arrayUFVORelacionado;
	}

	public String[] getArrayUFVORelacionado() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayUFVORelacionado == null || this.arrayUFVORelacionado.length == 0) {
			this.arrayUFVORelacionado = new String[1];
		}

		return this.arrayUFVORelacionado;
	}

	public void setArrayMunicipioExistente(String[] arrayMunicipioExistente) {
		this.arrayMunicipioExistente = arrayMunicipioExistente;
	}

	public String[] getArrayMunicipioExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayMunicipioExistente == null || this.arrayMunicipioExistente.length == 0) {
			this.arrayMunicipioExistente = new String[1];
		}

		return this.arrayMunicipioExistente;
	}

	public void setArrayMunicipioRelacionado(String[] arrayMunicipioRelacionado) {
		this.arrayMunicipioRelacionado = arrayMunicipioRelacionado;
	}

	public String[] getArrayMunicipioRelacionado() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayMunicipioRelacionado == null || this.arrayMunicipioRelacionado.length == 0) {
			this.arrayMunicipioRelacionado = new String[1];
		}

		return this.arrayMunicipioRelacionado;
	}

	public void setArrayAnoBase(String[] arrayAnoBase) {
		this.arrayAnoBase = arrayAnoBase;
	}

	public String[] getArrayAnoBase() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAnoBase == null || this.arrayAnoBase.length == 0) {
			this.arrayAnoBase = new String[1];
		}

		return this.arrayAnoBase;
	}

	public void setArrayAnoCopia(String[] arrayAnoCopia) {
		this.arrayAnoCopia = arrayAnoCopia;
	}

	public String[] getArrayAnoCopia() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAnoCopia == null || this.arrayAnoCopia.length == 0) {
			this.arrayAnoCopia = new String[1];
		}

		return this.arrayAnoCopia;
	}

	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public String getIdMunicipio() {
		return this.idMunicipio;
	}

	public void setListaUFs(UFVO[] listaUFs) {
		this.listaUFs = listaUFs;
	}

	public UFVO[] getListaUFs() {
		return this.listaUFs;
	}

	public void setIdUF(String idUF) {
		this.idUF = idUF;
	}

	public String getIdUF() {
		return this.idUF;
	}

	public void setIndicativoUF(String indicativoUF) {
		this.indicativoUF = indicativoUF;
	}

	public String getIndicativoUF() {
		return this.indicativoUF;
	}

	public void setAdmTipoFeriado(String admTipoFeriado) {
		this.admTipoFeriado = admTipoFeriado;
	}

	public String getAdmTipoFeriado() {
		return this.admTipoFeriado;
	}

	public void setAdmDescricaoNova(String admDescricaoNova) {
		this.admDescricaoNova = admDescricaoNova;
	}

	public String getAdmDescricaoNova() {
		return this.admDescricaoNova;
	}

	public void setAdmDescricaoExistente(String admDescricaoExistente) {
		this.admDescricaoExistente = admDescricaoExistente;
	}

	public String getAdmDescricaoExistente() {
		return this.admDescricaoExistente;
	}

	public void setDsTipoFeriado(String dsTipoFeriado) {
		this.dsTipoFeriado = dsTipoFeriado;
	}

	public String getDsTipoFeriado() {
		return this.dsTipoFeriado;
	}

	public void setIdDsFeriadoParam(String idDsFeriadoParam) {
		this.idDsFeriadoParam = idDsFeriadoParam;
	}

	public String getIdDsFeriadoParam() {
		return this.idDsFeriadoParam;
	}

	public void setDsFeriadoParam(String dsFeriadoParam) {
		this.dsFeriadoParam = dsFeriadoParam;
	}

	public String getDsFeriadoParam() {
		return this.dsFeriadoParam;
	}

	public void setIdTipoFeriadoParam(String idTipoFeriadoParam) {
		this.idTipoFeriadoParam = idTipoFeriadoParam;
	}

	public String getIdTipoFeriadoParam() {
		return this.idTipoFeriadoParam;
	}

	public void setInRelatorioParam(String inRelatorioParam) {
		this.inRelatorioParam = inRelatorioParam;
	}

	public String getInRelatorioParam() {
		return this.inRelatorioParam;
	}

	public void setIndMovelParam(String indMovelParam) {
		this.indMovelParam = indMovelParam;
	}

	public String getIndMovelParam() {
		return this.indMovelParam;
	}

	public void setDsTipoFeriadoParam(String dsTipoFeriadoParam) {
		this.dsTipoFeriadoParam = dsTipoFeriadoParam;
	}

	public String getDsTipoFeriadoParam() {
		return this.dsTipoFeriadoParam;
	}

	public void setIdFeriadoParam(String idFeriadoParam) {
		this.idFeriadoParam = idFeriadoParam;
	}

	public String getIdFeriadoParam() {
		return this.idFeriadoParam;
	}

	public void setDtFeriadoParam(String dtFeriadoParam) {
		this.dtFeriadoParam = dtFeriadoParam;
	}

	public String getDtFeriadoParam() {
		return this.dtFeriadoParam;
	}

	public void setIndicativoMunicipal(String indicativoMunicipal) {
		this.indicativoMunicipal = indicativoMunicipal;
	}

	public String getIndicativoMunicipal() {
		return this.indicativoMunicipal;
	}

	public void setListaUFVOExistente(UFVO[] listaUFVOExistente) {
		this.listaUFVOExistente = listaUFVOExistente;
	}

	public UFVO[] getListaUFVOExistente() {
		return this.listaUFVOExistente;
	}

	public void setListaUFVORelacionados(UFVO[] listaUFVORelacionados) {
		this.listaUFVORelacionados = listaUFVORelacionados;
	}

	public UFVO[] getListaUFVORelacionados() {
		return this.listaUFVORelacionados;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getAno() {
		if (this.ano == null) {
			this.ano = "";
		}
		return this.ano;
	}

	public void setCmbCombo(AdmCalendarioContainerVO cmbCombo) {
		this.cmbCombo = cmbCombo;
	}

	public AdmCalendarioContainerVO getCmbCombo() {
		if (this.cmbCombo == null) {
			this.cmbCombo = AdmCalendarioContainerVO.Factory.newInstance();
		}
		return this.cmbCombo;
	}

	public void setListFeriado(ArrayList listFeriado) {
		this.listFeriado = listFeriado;
	}

	public ArrayList getListFeriado() {
		return this.listFeriado;
	}
}