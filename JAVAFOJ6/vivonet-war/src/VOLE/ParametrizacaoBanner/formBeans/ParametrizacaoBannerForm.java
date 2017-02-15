package VOLE.ParametrizacaoBanner.formBeans;

import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE;

@SuppressWarnings({ "rawtypes" })
public class ParametrizacaoBannerForm extends ActionForm {

	private static final long serialVersionUID = -9126989997375624296L;

	private Banner[] listaBanners;
	private RelacionamentoBannerVOLE[] listaRelacionamentos;
	private Banner banner;
	private HashMap listaAreas;
	private HashMap listaTiposBanner;
	private UFVO[] listaUF;
	private String[] filtroAreas;
	private String[] filtroUFs;
	private long[] relacionamentosSelecionados;
	private int nrPagina;
	private int itensPorPagina;
	private int totalItens;
	private boolean ultimaPagina;

	private HashMap arquivosTemporarios;

	public Banner[] getListaBanners() {
		if (this.listaBanners == null) {
			listaBanners = new Banner[0];
		}
		return this.listaBanners;
	}

	public void setListaBanners(Banner[] arg) {
		this.listaBanners = arg;
	}

	public RelacionamentoBannerVOLE[] getListaRelacionamentos() {
		if (this.listaRelacionamentos == null) {
			listaRelacionamentos = new RelacionamentoBannerVOLE[0];
		}
		return this.listaRelacionamentos;
	}

	public void setListaRelacionamentos(RelacionamentoBannerVOLE[] arg) {
		this.listaRelacionamentos = arg;
	}

	public Banner getBanner() {
		if (this.banner == null) {
			banner = new Banner();
		}
		return this.banner;
	}

	public void setBanner(Banner arg) {
		this.banner = arg;
	}

	public HashMap getListaAreas() {
		return this.listaAreas;
	}

	public void setListaAreas(HashMap arg) {
		this.listaAreas = arg;
	}

	public HashMap getListaTiposBanner() {
		return this.listaTiposBanner;
	}

	public void setListaTiposBanner(HashMap arg) {
		this.listaTiposBanner = arg;
	}

	public UFVO[] getListaUF() {
		if (this.listaUF == null) {
			this.listaUF = new UFVO[0];
		}
		return this.listaUF;
	}

	public void setListaUF(UFVO[] arg) {
		this.listaUF = arg;
	}

	public String[] getFiltroAreas() {
		if (this.filtroAreas == null) {
			this.filtroAreas = new String[0];
		}
		return this.filtroAreas;
	}

	public void setFiltroAreas(String[] arg) {
		this.filtroAreas = arg;
	}

	public String[] getFiltroUFs() {
		if (this.filtroUFs == null) {
			this.filtroUFs = new String[0];
		}
		return this.filtroUFs;
	}

	public void setFiltroUFs(String[] arg) {
		this.filtroUFs = arg;
	}

	public long[] getRelacionamentosSelecionados() {
		if (this.relacionamentosSelecionados == null) {
			this.relacionamentosSelecionados = new long[0];
		}
		return this.relacionamentosSelecionados;
	}

	public void setRelacionamentosSelecionados(long[] arg) {
		this.relacionamentosSelecionados = arg;
	}

	public int getNrPagina() {
		if (this.nrPagina == 0) {
			this.nrPagina = 1;
		}
		return this.nrPagina;
	}

	public void setNrPagina(int arg) {
		this.nrPagina = arg;
	}

	public int getItensPorPagina() {
		return this.itensPorPagina;
	}

	public void setItensPorPagina(int arg) {
		this.itensPorPagina = arg;
	}

	public int getTotalItens() {
		return this.totalItens;
	}

	public void setTotalItens(int arg) {
		this.totalItens = arg;
	}

	public boolean isUltimaPagina() {
		return this.ultimaPagina;
	}

	public void setUltimaPagina(boolean arg) {
		this.ultimaPagina = arg;
	}

	public HashMap getArquivosTemporarios() {
		if (this.arquivosTemporarios == null) {
			this.arquivosTemporarios = new HashMap();
		}
		return this.arquivosTemporarios;
	}

	public void setArquivosTemporarios(HashMap arg) {
		this.arquivosTemporarios = arg;
	}

}