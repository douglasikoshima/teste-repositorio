package admsistemas.parametrizacoes.servicosdepara.formbeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara;

public class ServicosDeParaForm extends ActionForm {

	private static final long serialVersionUID = 5572654554338023718L;

	public ServicosDeParaForm(){};

	private long idPlano;
	private String cdPlano;
	private String dsPlano;
	private String cdPlanoAtlys;
	private boolean ativo;
	private ServicosDePara[] listaServicos;

	public long getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(long idPlano) {
		this.idPlano = idPlano;
	}

	public String getCdPlano() {
		return cdPlano;
	}

	public void setCdPlano(String cdPlano) {
		this.cdPlano = cdPlano;
	}

	public String getDsPlano() {
		return dsPlano;
	}

	public void setDsPlano(String dsPlano) {
		this.dsPlano = dsPlano;
	}

	public String getCdPlanoAtlys() {
		return cdPlanoAtlys;
	}

	public void setCdPlanoAtlys(String cdPlanoAtlys) {
		this.cdPlanoAtlys = cdPlanoAtlys;
	}

	public boolean isAtivo() {
		return this.ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public ServicosDePara[] getListaServicos() {
		return this.listaServicos;
	}

	public void setListaServicos(ServicosDePara[] arg) {
		this.listaServicos = arg;
	}
}