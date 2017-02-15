package admsistemas.AdmFormularios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;

public class CamposDinamicosForm extends ActionForm {

	private static final long serialVersionUID = 614975488963451736L;

	private Resultset resultset;
	private Resultset listaFormularios;
	private Campo[] listaCamposFormulario;
	private Collection listaCamposSubFormulario;
	private Resultset listaSubFormularios;
	private String[] vlCampoCombo;
	private String[] vlCampoComboAlt;
	private String[] frmDisponiveis;
	private String[] frmSelecionados;
	private String[] subFrmDisponiveis;
	private String[] listaCamposSelecionadosCombo;
	private Resultset listaCamposSelecionados;
	private String[] camposObrigatorios;
	private String[] camposNaoObrigatorios;
	private String vlCampo = ConstantesCRM.SVAZIO;
	private String fmtTexto = ConstantesCRM.SVAZIO;
	private String nmCampo = ConstantesCRM.SVAZIO;
	private String lbCampo = ConstantesCRM.SVAZIO;
	private String tpCampo = ConstantesCRM.SVAZIO;
	private String idCampo = ConstantesCRM.SVAZIO;
	private String tpCampoAlt = ConstantesCRM.SVAZIO;
	private String idCampoAlt = ConstantesCRM.SVAZIO;
	private String idFormulario = ConstantesCRM.SVAZIO;
	private String idClassificador = ConstantesCRM.SVAZIO;
	private String idSubForm = ConstantesCRM.SVAZIO;
	private String idFuncionalidade = ConstantesCRM.SVAZIO;
	private String nmFormulario = ConstantesCRM.SVAZIO;
	private String action = ConstantesCRM.SVAZIO;
	private String qtCamposFrm = ConstantesCRM.SVAZIO;
	private String idContato = ConstantesCRM.SVAZIO;
	private String contato = ConstantesCRM.SVAZIO;
	private String idSubFormulario = ConstantesCRM.SVAZIO;
	private Disponivel lstClassificacaoFrm = Disponivel.Factory.newInstance();
	private Disponivel lstFuncionalidade = Disponivel.Factory.newInstance();
	private Disponivel lstFrmDisponiveis = Disponivel.Factory.newInstance();
	private Disponivel lstSubFrm = Disponivel.Factory.newInstance();
	private Disponivel lstCamposExistentes = Disponivel.Factory.newInstance();
	// private Disponivel lstSubFrmDisponiveis = Disponivel.Factory.newInstance();
	private String funcSubFormulario;
	private String nomeSubFormulario;
	private Resultset listaEmpresasDisponiveis;
	private List listaCamposExistentes;
	private Campo campoFormulario;
	private List listaDominio;
	private RegrasEncaminhamentoDisponivelVO[] regrasEncaminhamento;
	private String[] listaContasSelecionadas;
	private String nrCNPJ;
	private br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel contasDisponiveis;
	private ClienteFormularioVO clienteFormularioVO;
	private String[] listaContasDisponiveis;
	private String tpOperacao;
	private Resultset listaPrazoAtendimento;
	private String[] listaPrazoAtendimentoSelecionado;
	private Collection listaFormularioFuncionalidade;
	private String[] listaContasDessassociadas;
	public String[] getListaContasDessassociadas() {
		return listaContasDessassociadas;
	}

	public void setListaContasDessassociadas(String[] listaContasDessassociadas) {
		this.listaContasDessassociadas = listaContasDessassociadas;
	}

		
	public CamposDinamicosForm() {
		this.clienteFormularioVO = ClienteFormularioVO.Factory.newInstance();
		this.clienteFormularioVO.addNewFuncionalidadeFrmVO();
		this.clienteFormularioVO.addNewClienteVO();
	}

	private String nmEmpresa;
	private String idPrazoSelecionado;
	private String nmPrazoSelecionado;
	private String duplicarFormulario;
	private ArrayList arrClientes;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getContato() {
		return this.contato;
	}

	public void setQtCamposFrm(String qtCamposFrm) {
		this.qtCamposFrm = qtCamposFrm;
	}

	public String getQtCamposFrm() {
		return this.qtCamposFrm;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}

	public String getIdCampo() {
		return this.idCampo;
	}

	public void setIdCampoAlt(String idCampoAlt) {
		this.idCampoAlt = idCampoAlt;
	}

	public String getIdCampoAlt() {
		return this.idCampoAlt;
	}

	public void setTpCampoAlt(String tpCampoAlt) {
		this.tpCampoAlt = tpCampoAlt;
	}

	public String getTpCampoAlt() {
		return this.tpCampoAlt;
	}

	public void setIdFormulario(String idFormulario) {
		this.idFormulario = idFormulario;
	}

	public String getIdFormulario() {
		return this.idFormulario;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getIdFuncionalidade() {
		return this.idFuncionalidade;
	}

	public void setTpCampo(String tpCampo) {
		this.tpCampo = tpCampo;
	}

	public String getTpCampo() {
		return this.tpCampo;
	}

	public void setNmCampo(String nmCampo) {
		this.nmCampo = nmCampo;
	}

	public String getNmCampo() {
		return this.nmCampo;
	}

	public void setLbCampo(String lbCampo) {
		this.lbCampo = lbCampo;
	}

	public String getLbCampo() {
		return this.lbCampo;
	}

	public void setVlCampo(String vlCampo) {
		this.vlCampo = vlCampo;
	}

	public String getVlCampo() {
		return this.vlCampo;
	}

	public void setFmtTexto(String fmtTexto) {
		this.fmtTexto = fmtTexto;
	}

	public String getFmtTexto() {
		return this.fmtTexto;
	}

	public void setVlCampoCombo(String[] vlCampoCombo) {
		this.vlCampoCombo = vlCampoCombo;
	}

	public String[] getVlCampoCombo() {
		if (this.vlCampoCombo == null || this.vlCampoCombo.length == 0) {
			this.vlCampoCombo = new String[1];
		}
		return this.vlCampoCombo;
	}

	public void setVlCampoComboAlt(String[] vlCampoComboAlt) {
		this.vlCampoComboAlt = vlCampoComboAlt;
	}

	public String[] getVlCampoComboAlt() {
		if (this.vlCampoComboAlt == null || this.vlCampoComboAlt.length == 0) {
			this.vlCampoComboAlt = new String[1];
		}
		return this.vlCampoComboAlt;
	}

	public void setFrmDisponiveis(String[] frmDisponiveis) {
		this.frmDisponiveis = frmDisponiveis;
	}

	public String[] getFrmDisponiveis() {
		if (this.frmDisponiveis == null || this.frmDisponiveis.length == 0) {
			this.frmDisponiveis = new String[0];
		}
		return this.frmDisponiveis;
	}

	public void setFrmSelecionados(String[] frmSelecionados) {
		this.frmSelecionados = frmSelecionados;
	}

	public String[] getFrmSelecionados() {
		if (this.frmSelecionados == null || this.frmSelecionados.length == 0) {
			this.frmSelecionados = new String[0];
		}
		return this.frmSelecionados;
	}

	public void setResultset(Resultset resultset) {
		this.resultset = resultset;
	}

	public Resultset getResultset() {
		return this.resultset;
	}

	public void setListaFormularios(Resultset listaFormularios) {
		this.listaFormularios = listaFormularios;
	}

	public Resultset getListaFormularios() {
		return this.listaFormularios;
	}

	public void setListaCamposFormulario(Campo[] listaCamposFormulario) {
		this.listaCamposFormulario = listaCamposFormulario;
	}

	public Campo[] getListaCamposFormulario() {
		return this.listaCamposFormulario;
	}

	public void setNmFormulario(String nmFormulario) {
		this.nmFormulario = nmFormulario;
	}

	public String getNmFormulario() {
		return this.nmFormulario;
	}

	public void setIdClassificador(String idClassificador) {
		this.idClassificador = idClassificador;
	}

	public String getIdClassificador() {
		return this.idClassificador;
	}

	public void setIdSubForm(String idSubForm) {
		this.idSubForm = idSubForm;
	}

	public String getIdSubForm() {
		return this.idSubForm;
	}

	public void setLstClassificacaoFrm(Disponivel lstClassificacaoFrm) {
		this.lstClassificacaoFrm = lstClassificacaoFrm;
	}

	public Disponivel getLstClassificacaoFrm() {
		return this.lstClassificacaoFrm;
	}

	public void setLstSubFrm(Disponivel lstSubFrm) {
		this.lstSubFrm = lstSubFrm;
	}

	public Disponivel getLstSubFrm() {
		return this.lstSubFrm;
	}

	public void setLstCamposExistentes(Disponivel lstCamposExistentes) {
		this.lstCamposExistentes = lstCamposExistentes;
	}

	public Disponivel getLstCamposExistentes() {
		return this.lstCamposExistentes;
	}

	public void setLstFuncionalidade(Disponivel lstFuncionalidade) {
		this.lstFuncionalidade = lstFuncionalidade;
	}

	public Disponivel getLstFuncionalidade() {
		return this.lstFuncionalidade;
	}

	public void setLstFrmDisponiveis(Disponivel lstFrmDisponiveis) {
		this.lstFrmDisponiveis = lstFrmDisponiveis;
	}

	public Disponivel getLstFrmDisponiveis() {
		return this.lstFrmDisponiveis;
	}

	public String getFuncSubFormulario() {
		return this.funcSubFormulario;
	}

	public void setFuncSubFormulario(String funcSubFormulario) {
		this.funcSubFormulario = funcSubFormulario;
	}

	public String getNomeSubFormulario() {
		return this.nomeSubFormulario;
	}

	public void setNomeSubFormulario(String nomeSubFormulario) {
		this.nomeSubFormulario = nomeSubFormulario;
	}

	public void setSubFrmDisponiveis(String[] subFrmDisponiveis) {
		this.subFrmDisponiveis = subFrmDisponiveis;
	}

	public String[] getSubFrmDisponiveis() {
		if (this.subFrmDisponiveis == null || this.subFrmDisponiveis.length == 0) {
			this.subFrmDisponiveis = new String[0];
		}
		return this.subFrmDisponiveis;
	}

	public void setListaSubFormularios(Resultset listaSubFormularios) {
		this.listaSubFormularios = listaSubFormularios;
	}

	public Resultset getListaSubFormularios() {
		return this.listaSubFormularios;
	}

	public Resultset getListaCamposSelecionados() {
		return this.listaCamposSelecionados;
	}

	public void setListaCamposSelecionados(Resultset listaCamposSelecionados) {
		this.listaCamposSelecionados = listaCamposSelecionados;
	}

	public void setListaCamposSelecionadosCombo(String[] listaCamposSelecionadosCombo) {
		this.listaCamposSelecionadosCombo = listaCamposSelecionadosCombo;
	}

	public String[] getListaCamposSelecionadosCombo() {
		if (this.listaCamposSelecionadosCombo == null || this.listaCamposSelecionadosCombo.length == 0) {
			this.listaCamposSelecionadosCombo = new String[0];
		}
		return this.listaCamposSelecionadosCombo;
	}

	public String getIdSubFormulario() {
		return this.idSubFormulario;
	}

	public void setIdSubFormulario(String idSubFormulario) {
		this.idSubFormulario = idSubFormulario;
	}

	public void setCamposObrigatorios(String[] camposObrigatorios) {
		this.camposObrigatorios = camposObrigatorios;
	}

	public String[] getCamposObrigatorios() {
		if (this.camposObrigatorios == null || this.camposObrigatorios.length == 0) {
			this.camposObrigatorios = new String[0];
		}
		return this.camposObrigatorios;
	}

	public void setCamposNaoObrigatorios(String[] camposNaoObrigatorios) {
		this.camposNaoObrigatorios = camposNaoObrigatorios;
	}

	public String[] getCamposNaoObrigatorios() {
		if (this.camposNaoObrigatorios == null || this.camposNaoObrigatorios.length == 0) {
			this.camposNaoObrigatorios = new String[0];
		}
		return this.camposNaoObrigatorios;
	}

	public void setListaCamposSubFormulario(Collection listaCamposSubFormulario) {
		this.listaCamposSubFormulario = listaCamposSubFormulario;
	}

	public Collection getListaCamposSubFormulario() {
		return this.listaCamposSubFormulario;
	}

	public RegrasEncaminhamentoDisponivelVO[] getRegrasEncaminhamento() {
		if (this.regrasEncaminhamento == null) {
			this.regrasEncaminhamento = new RegrasEncaminhamentoDisponivelVO[0];
		}
		return this.regrasEncaminhamento;
	}

	public void setRegrasEncaminhamento(RegrasEncaminhamentoDisponivelVO[] arg) {
		this.regrasEncaminhamento = arg;
	}

	public void setListaCamposExistentes(List listaCamposExistentes) {
		this.listaCamposExistentes = listaCamposExistentes;
	}

	public List getListaCamposExistentes() {
		return this.listaCamposExistentes;
	}

	public void setCampoFormulario(Campo campo) {
		this.campoFormulario = campo;
	}

	public Campo getCampoFormulario() {
		return this.campoFormulario;
	}

	public void setListaDominio(List listaDominio) {
		this.listaDominio = listaDominio;
	}

	public List getListaDominio() {
		return this.listaDominio;
	}

	public Resultset getListaEmpresasDisponiveis() {
		return this.listaEmpresasDisponiveis;
	}

	public void setListaEmpresasDisponiveis(Resultset listaEmpresasDisponiveis) {
		this.listaEmpresasDisponiveis = listaEmpresasDisponiveis;
	}

	public String getNrCNPJ() {
		return this.nrCNPJ;
	}

	public void setNrCNPJ(String nrCNPJ) {
		this.nrCNPJ = nrCNPJ;
	}

	public ClienteFormularioVO getClienteFormularioVO() {
		if (this.clienteFormularioVO == null) {
			this.clienteFormularioVO = ClienteFormularioVO.Factory.newInstance();
		}
		return this.clienteFormularioVO;
	}

	public void setConsultorRelacionamento(ClienteFormularioVO consultorRelacionamento) {
		this.clienteFormularioVO = consultorRelacionamento;
	}

	public br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel getContasDisponiveis() {
		if (this.contasDisponiveis == null) {
			this.contasDisponiveis = br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel.Factory
					.newInstance();
		}
		return this.contasDisponiveis;
	}

	public void setContasDisponiveis(
			br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel contasDisponiveis) {
		this.contasDisponiveis = contasDisponiveis;
	}

	public String[] getListaContasDisponiveis() {
		if (this.listaContasDisponiveis == null) {
			this.listaContasDisponiveis = new String[0];
		}
		return this.listaContasDisponiveis;
	}

	public void setListaContasDisponiveis(String[] listaContasDisponiveis) {
		this.listaContasDisponiveis = listaContasDisponiveis;
	}

	public String getTpOperacao() {
		return this.tpOperacao;
	}

	public void setTpOperacao(String tpOperacao) {
		this.tpOperacao = tpOperacao;
	}

	public String[] getListaContasSelecionadas() {
		if (this.listaContasSelecionadas == null) {
			this.listaContasSelecionadas = new String[0];
		}
		return this.listaContasSelecionadas;
	}

	public void setListaContasSelecionadas(String[] listaContasSelecionadas) {
		this.listaContasSelecionadas = listaContasSelecionadas;
	}

	public Resultset getListaPrazoAtendimento() {
		return this.listaPrazoAtendimento;
	}

	public void setListaPrazoAtendimento(Resultset listaPrazoAtendimento) {
		this.listaPrazoAtendimento = listaPrazoAtendimento;
	}

	public String[] getListaPrazoAtendimentoSelecionado() {
		if (this.listaPrazoAtendimentoSelecionado == null) {
			this.listaPrazoAtendimentoSelecionado = new String[0];
		}
		return this.listaPrazoAtendimentoSelecionado;
	}

	public void setListaPrazoAtendimentoSelecionado(String[] listaPrazoAtendimentoSelecionado) {
		this.listaPrazoAtendimentoSelecionado = listaPrazoAtendimentoSelecionado;
	}

	public Collection getListaFormularioFuncionalidade() {
		return this.listaFormularioFuncionalidade;
	}

	public void setListaFormularioFuncionalidade(Collection listaFormularioFuncionalidade) {
		this.listaFormularioFuncionalidade = listaFormularioFuncionalidade;
	}

	public String getNmEmpresa() {
		return this.nmEmpresa;
	}

	public void setNmEmpresa(String nmEmpresa) {
		this.nmEmpresa = nmEmpresa;
	}

	public String getIdPrazoSelecionado() {
		return this.idPrazoSelecionado;
	}

	public void setIdPrazoSelecionado(String idPrazoSelecionado) {
		this.idPrazoSelecionado = idPrazoSelecionado;
	}

	public String getNmPrazoSelecionado() {
		return this.nmPrazoSelecionado;
	}

	public void setNmPrazoSelecionado(String nmPrazoSelecionado) {
		this.nmPrazoSelecionado = nmPrazoSelecionado;
	}

	public String getDuplicarFormulario() {
		return this.duplicarFormulario;
	}

	public void setDuplicarFormulario(String duplicarFormulario) {
		this.duplicarFormulario = duplicarFormulario;
	}

	public ArrayList getArrClientes() {
		if (this.arrClientes == null) {
			this.arrClientes = new ArrayList();
		}
		return this.arrClientes;
	}

	public void setArrClientes(ArrayList arrClientes) {
		this.arrClientes = arrClientes;
	}
}