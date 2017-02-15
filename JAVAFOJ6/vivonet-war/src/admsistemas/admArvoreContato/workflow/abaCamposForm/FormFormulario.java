package admsistemas.admArvoreContato.workflow.abaCamposForm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreGruposCamposDependentesVODocument.AdmArvoreGruposCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoVODocument.AdmCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposHistoricoVODocument.AdmCamposHistoricoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoUFOperadoraVODocument.AdmContatoUFOperadoraVO;
import br.com.vivo.fo.admsistemas.vo.AdmFaseProcessoVODocument.AdmFaseProcessoVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.AdmGruposCamposDependentesVODocument.AdmGruposCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.CampoObjetoFormularioVODocument.CampoObjetoFormularioVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;

public class FormFormulario extends ActionForm implements Serializable {

	private static final long serialVersionUID = -8432004673343598367L;

	private String idxNivelExcluido;
	private ArrayList camposDependentesExcluidos;
	private AdmArvoreGruposCamposDependentesVO arvoreCamposDependentes;
	private String[] arrayQtdeCamposPorGrupo;
	private String[] arrayNmCamposDependentes;
	private String[] arrayIdCamposDependentes;
	private String inContatoAssociado;
	private String inOperacao;
	private String nmGrupoCampos;
	private int arrayGrupoCamposLength = 0;
	private String idGrupoCampos;
	private String qtdeNiveis;
	private String inCamposComponentes;
	private String msgError = ConstantesCRM.SVAZIO;
	private String listaFormularioLength;
	private Vector listasso;
	private UFOperadoraUsuarioVO[] ufOperadorasExistentes;
	private UFOperadoraUsuarioVO[] ufOperadorasAssociadas;
	private AdmCamposHistoricoVO[] listaAssociada;
	private CampoObjetoFormularioVO[] listaAssociadaCamposObjetos;
	private CampoObjetoFormularioVO[] listaDisponiveisCamposObjetos;
	private String idContato;
	private String inMsgErro;
	private String idFaseProcessoAtual;
	private String idClassificadorCampoAtual;
	private AdmFaseProcessoVO[] arrayFaseProcesso;
	private AdmGrupoCamposDependentesVO[] arrayGrupoCamposDependentes;
	private AdmGrupoCamposVO[] arrayGrupoCampos;
	private AdmClassificadorCampoVO[] arrayCampoClassificador;
	private AdmContatoUFOperadoraVO[] operadorasExistentes;
	private AdmContatoUFOperadoraVO[] operadorasAssociadas;
	private AdmTipoLinhaVO[] tipoLinhasExistentes;
	private AdmTipoLinhaVO[] tipoLinhasAssociadas;
	private AdmCampoVO[] camposExistentes;
	private AdmCampoVO[] camposAssociados;
	private String[] aCamposSelecionados;
	private String[] aCamposExistentes;
	private String[] aTipoLinhaSelecionados;
	private String[] aTipoLinhaExistentes;
	private String[] aOperadoraSelecionados;
	private String[] aOperadoraExistentes;
	protected String[] camposExcluidos;
	private EnderecoVO enderecoVO;
	private int numeroCampos;
	private AtendimentoVO atendimentoVO;
	private ValorCampo[] valorCampo;
	protected String[] idCampoNiveis;
	private String idPai;
	private String descricaoPai;
	private AdmGruposCamposDependentesVO admGruposCamposDependentesVO;

	public FormFormulario() {
		arrayFaseProcesso = new AdmFaseProcessoVO[0];
		arrayGrupoCamposDependentes = new AdmGrupoCamposDependentesVO[0];
		arrayGrupoCampos = new AdmGrupoCamposVO[0];
		arrayCampoClassificador = new AdmClassificadorCampoVO[0];
		ufOperadorasExistentes = new UFOperadoraUsuarioVO[0];
		ufOperadorasAssociadas = new UFOperadoraUsuarioVO[0];
		operadorasExistentes = new AdmContatoUFOperadoraVO[0];
		operadorasAssociadas = new AdmContatoUFOperadoraVO[0];
		tipoLinhasExistentes = new AdmTipoLinhaVO[0];
		tipoLinhasAssociadas = new AdmTipoLinhaVO[0];
		camposExistentes = new AdmCampoVO[0];
		camposAssociados = new AdmCampoVO[0];
		listaAssociada = new AdmCamposHistoricoVO[0];
		listaAssociadaCamposObjetos = new CampoObjetoFormularioVO[0];
		listaDisponiveisCamposObjetos = new CampoObjetoFormularioVO[0];
		enderecoVO = EnderecoVO.Factory.newInstance();
		atendimentoVO = AtendimentoVO.Factory.newInstance();
		admGruposCamposDependentesVO = AdmGruposCamposDependentesVO.Factory.newInstance();
		idCampoNiveis = new String[20];
		camposExcluidos = new String[20];
		inContatoAssociado = ConstantesCRM.SZERO;
		idPai = ConstantesCRM.SVAZIO;
		descricaoPai = ConstantesCRM.SVAZIO;
		msgError = ConstantesCRM.SVAZIO;
	}

	public void setaOperadoraExistentes(String[] aOperadoraExistentes) {
		this.aOperadoraExistentes = aOperadoraExistentes;
	}

	public String[] getaOperadoraExistentes() {
		if (this.aOperadoraExistentes == null || this.aOperadoraExistentes.length == 0) {
			this.aOperadoraExistentes = new String[0];
		}
		return this.aOperadoraExistentes;
	}

	public void setaOperadoraSelecionados(String[] aOperadoraSelecionados) {
		this.aOperadoraSelecionados = aOperadoraSelecionados;
	}

	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}

	public EnderecoVO getEnderecoVO() {
		if (this.enderecoVO == null) {
			this.enderecoVO = EnderecoVO.Factory.newInstance();
		}
		return this.enderecoVO;
	}

	public String[] getCamposExcluidos() {
		if (this.camposExcluidos == null || this.camposExcluidos.length == 0) {
			this.camposExcluidos = new String[20];
		}
		return this.camposExcluidos;
	}

	public void setCamposExcluidos(String[] camposExcluidos) {
		this.camposExcluidos = camposExcluidos;
	}

	public String[] getaOperadoraSelecionados() {
		if (this.aOperadoraSelecionados == null || this.aOperadoraSelecionados.length == 0) {
			this.aOperadoraSelecionados = new String[0];
		}
		return this.aOperadoraSelecionados;
	}

	public void setaTipoLinhaExistentes(String[] aTipoLinhaExistentes) {
		this.aTipoLinhaExistentes = aTipoLinhaExistentes;
	}

	public String[] getIdCampoNiveis() {
		if (this.idCampoNiveis == null || this.idCampoNiveis.length == 0) {
			this.idCampoNiveis = new String[20];
		}
		return this.idCampoNiveis;
	}

	public void setIdCampoNiveis(String[] idCampoNiveis) {
		this.idCampoNiveis = idCampoNiveis;
	}

	public String[] getaTipoLinhaExistentes() {
		if (this.aTipoLinhaExistentes == null || this.aTipoLinhaExistentes.length == 0) {
			this.aTipoLinhaExistentes = new String[0];
		}
		return this.aTipoLinhaExistentes;
	}

	public void setQtdeNiveis(String qtdeNiveis) {
		this.qtdeNiveis = qtdeNiveis;
	}

	public String getQtdeNiveis() {
		if (this.qtdeNiveis == null) {
			this.qtdeNiveis = ConstantesCRM.SZERO;
		}
		return this.qtdeNiveis;
	}

	public void setIdPai(String idPai) {
		this.idPai = idPai;
	}

	public String getIdPai() {
		if (this.idPai == null) {
			this.idPai = ConstantesCRM.SZERO;
		}
		return this.idPai;
	}

	public void setInContatoAssociado(String inContatoAssociado) {
		this.inContatoAssociado = inContatoAssociado;
	}

	public String getInContatoAssociado() {
		if (this.inContatoAssociado == null) {
			this.inContatoAssociado = ConstantesCRM.SZERO;
		}
		return this.inContatoAssociado;
	}

	public void setInMsgErro(String inMsgErro) {
		this.inMsgErro = inMsgErro;
	}

	public String getInMsgErro() {
		if (this.inMsgErro == null) {
			this.inMsgErro = ConstantesCRM.SVAZIO;
		}
		return this.inMsgErro;
	}

	public void setDescricaoPai(String descricaoPai) {
		this.descricaoPai = descricaoPai;
	}

	public String getDescricaoPai() {
		if (this.descricaoPai == null) {
			this.descricaoPai = ConstantesCRM.SVAZIO;
		}
		return this.descricaoPai;
	}

	public void setaTipoLinhaSelecionados(String[] aTipoLinhaSelecionados) {
		this.aTipoLinhaSelecionados = aTipoLinhaSelecionados;
	}

	public String[] getaTipoLinhaSelecionados() {
		if (this.aTipoLinhaSelecionados == null || this.aTipoLinhaSelecionados.length == 0) {
			this.aTipoLinhaSelecionados = new String[0];
		}
		return this.aTipoLinhaSelecionados;
	}

	public void setArrayFaseProcesso(AdmFaseProcessoVO[] arrayFaseProcesso) {
		this.arrayFaseProcesso = arrayFaseProcesso;
	}

	public AdmFaseProcessoVO[] getArrayFaseProcesso() {
		return this.arrayFaseProcesso;
	}

	public void setArrayGrupoCamposDependentes(
			AdmGrupoCamposDependentesVO[] arrayGrupoCamposDependentes) {
		this.arrayGrupoCamposDependentes = arrayGrupoCamposDependentes;
	}

	public AdmGrupoCamposDependentesVO[] getArrayGrupoCamposDependentes() {
		return this.arrayGrupoCamposDependentes;
	}

	public void setArrayGrupoCampos(AdmGrupoCamposVO[] arrayGrupoCampos) {
		this.arrayGrupoCampos = arrayGrupoCampos;
	}

	public AdmGrupoCamposVO[] getArrayGrupoCampos() {
		return this.arrayGrupoCampos;
	}

	public void setaCamposExistentes(String[] aCamposExistentes) {
		this.aCamposExistentes = aCamposExistentes;
	}

	public String[] getaCamposExistentes() {
		if (this.aCamposExistentes == null || this.aCamposExistentes.length == 0) {
			this.aCamposExistentes = new String[0];
		}
		return this.aCamposExistentes;
	}

	public void setaCamposSelecionados(String[] aCamposSelecionados) {
		this.aCamposSelecionados = aCamposSelecionados;
	}

	public String[] getaCamposSelecionados() {
		if (this.aCamposSelecionados == null || this.aCamposSelecionados.length == 0) {
			this.aCamposSelecionados = new String[0];
		}
		return this.aCamposSelecionados;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setCamposAssociados(AdmCampoVO[] camposAssociados) {
		this.camposAssociados = camposAssociados;
	}

	public AdmCampoVO[] getCamposAssociados() {
		return this.camposAssociados;
	}

	public void setCamposExistentes(AdmCampoVO[] camposExistentes) {
		this.camposExistentes = camposExistentes;
	}

	public AdmCampoVO[] getCamposExistentes() {
		return this.camposExistentes;
	}

	public void setTipoLinhasAssociadas(AdmTipoLinhaVO[] tipoLinhasAssociadas) {
		this.tipoLinhasAssociadas = tipoLinhasAssociadas;
	}

	public AdmTipoLinhaVO[] getTipoLinhasAssociadas() {
		return this.tipoLinhasAssociadas;
	}

	public void setTipoLinhasExistentes(AdmTipoLinhaVO[] tipoLinhasExistentes) {
		this.tipoLinhasExistentes = tipoLinhasExistentes;
	}

	public AdmTipoLinhaVO[] getTipoLinhasExistentes() {
		return this.tipoLinhasExistentes;
	}

	public void setOperadorasAssociadas(AdmContatoUFOperadoraVO[] operadorasAssociadas) {
		this.operadorasAssociadas = operadorasAssociadas;
	}

	public AdmContatoUFOperadoraVO[] getOperadorasAssociadas() {
		return this.operadorasAssociadas;
	}

	public void setOperadorasExistentes(AdmContatoUFOperadoraVO[] operadorasExistentes) {
		this.operadorasExistentes = operadorasExistentes;
	}

	public AdmContatoUFOperadoraVO[] getOperadorasExistentes() {
		return this.operadorasExistentes;
	}

	public void setIdClassificadorCampoAtual(String idClassificadorCampoAtual) {
		this.idClassificadorCampoAtual = idClassificadorCampoAtual;
	}

	public String getIdClassificadorCampoAtual() {
		return this.idClassificadorCampoAtual;
	}

	public void setIdFaseProcessoAtual(String idFaseProcessoAtual) {
		this.idFaseProcessoAtual = idFaseProcessoAtual;
	}

	public String getIdFaseProcessoAtual() {
		return this.idFaseProcessoAtual;
	}

	public void setArrayCampoClassificador(AdmClassificadorCampoVO[] arrayCampoClassificador) {
		this.arrayCampoClassificador = arrayCampoClassificador;
	}

	public AdmClassificadorCampoVO[] getArrayCampoClassificador() {
		return this.arrayCampoClassificador;
	}

	public void setListaAssociada(AdmCamposHistoricoVO[] listaAssociada) {
		this.listaAssociada = listaAssociada;
	}

	public AdmCamposHistoricoVO[] getListaAssociada() {
		return this.listaAssociada;
	}

	public void setListaAssociadaCamposObjetos(CampoObjetoFormularioVO[] listaAssociadaCamposObjetos) {
		this.listaAssociadaCamposObjetos = listaAssociadaCamposObjetos;
	}

	public CampoObjetoFormularioVO[] getListaAssociadaCamposObjetos() {
		if (this.listaAssociadaCamposObjetos == null) {
			this.listaAssociadaCamposObjetos = new CampoObjetoFormularioVO[0];
		}
		return this.listaAssociadaCamposObjetos;
	}

	public void setListaDisponiveisCamposObjetos(
			CampoObjetoFormularioVO[] listaDisponiveisCamposObjetos) {
		this.listaDisponiveisCamposObjetos = listaDisponiveisCamposObjetos;
	}

	public CampoObjetoFormularioVO[] getListaDisponiveisCamposObjetos() {
		return this.listaDisponiveisCamposObjetos;
	}

	public void setUfOperadorasAssociadas(UFOperadoraUsuarioVO[] ufOperadorasAssociadas) {
		this.ufOperadorasAssociadas = ufOperadorasAssociadas;
	}

	public UFOperadoraUsuarioVO[] getUfOperadorasAssociadas() {
		return this.ufOperadorasAssociadas;
	}

	public void setUfOperadorasExistentes(UFOperadoraUsuarioVO[] ufOperadorasExistentes) {
		this.ufOperadorasExistentes = ufOperadorasExistentes;
	}

	public UFOperadoraUsuarioVO[] getUfOperadorasExistentes() {
		return this.ufOperadorasExistentes;
	}

	public void setListasso(Vector listasso) {
		this.listasso = listasso;
	}

	public Vector getListasso() {
		return this.listasso;
	}

	public void setListaFormularioLength(String listaFormularioLength) {
		this.listaFormularioLength = listaFormularioLength;
	}

	public String getListaFormularioLength() {
		return this.listaFormularioLength;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setInCamposComponentes(String inCamposComponentes) {
		this.inCamposComponentes = inCamposComponentes;
	}

	public String getInCamposComponentes() {
		if (this.inCamposComponentes == null) {
			this.inCamposComponentes = ConstantesCRM.SVAZIO;
		}
		return this.inCamposComponentes;
	}

	public void setIdGrupoCampos(String idGrupoCampos) {
		this.idGrupoCampos = idGrupoCampos;
	}

	public String getIdGrupoCampos() {
		if (this.idGrupoCampos == null) {
			this.idGrupoCampos = ConstantesCRM.SVAZIO;
		}
		return this.idGrupoCampos;
	}

	public void setArrayGrupoCamposLength(int arrayGrupoCamposLength) {
		this.arrayGrupoCamposLength = arrayGrupoCamposLength;
	}

	public int getArrayGrupoCamposLength() {
		return this.arrayGrupoCamposLength;
	}

	public void setNmGrupoCampos(String nmGrupoCampos) {
		this.nmGrupoCampos = nmGrupoCampos;
	}

	public String getNmGrupoCampos() {
		if (this.nmGrupoCampos == null) {
			this.nmGrupoCampos = ConstantesCRM.SVAZIO;
		}
		return this.nmGrupoCampos;
	}

	public void setInOperacao(String inOperacao) {
		this.inOperacao = inOperacao;
	}

	public String getInOperacao() {
		if (this.inOperacao == null) {
			this.inOperacao = ConstantesCRM.SVAZIO;
		}
		return this.inOperacao;
	}

	public int getNumeroCampos() {
		return this.numeroCampos;
	}

	public void setNumeroCampos(int numeroCampos) {
		this.numeroCampos = numeroCampos;
	}

	public AtendimentoVO getAtendimentoVO() {
		return this.atendimentoVO;
	}

	public void setAtendimentoVO(AtendimentoVO atendimentoVO) {
		this.atendimentoVO = atendimentoVO;
	}

	public AdmGruposCamposDependentesVO getAdmGruposCamposDependentesVO() {
		return this.admGruposCamposDependentesVO;
	}

	public void setAdmGruposCamposDependentesVO(
			AdmGruposCamposDependentesVO admGruposCamposDependentesVO) {
		this.admGruposCamposDependentesVO = admGruposCamposDependentesVO;
	}

	public ValorCampo[] getValorCampo() {
		if (valorCampo == null) {
			valorCampo = new ValorCampo[numeroCampos];
			for (int i = 0; i < valorCampo.length; i++) {
				valorCampo[i] = new ValorCampo();
				valorCampo[i].setValorArray(new String[0]);
			}
		}
		return (this.valorCampo);
	}

	public void setValorCampo(ValorCampo[] valorCampo) {
		this.valorCampo = valorCampo;
	}

	public void setArvoreCamposDependentes(AdmArvoreGruposCamposDependentesVO arvoreCamposDependentes) {
		this.arvoreCamposDependentes = arvoreCamposDependentes;
	}

	public AdmArvoreGruposCamposDependentesVO getArvoreCamposDependentes() {
		return this.arvoreCamposDependentes;
	}

	public void setCamposDependentesExcluidos(ArrayList camposDependentesExcluidos) {
		this.camposDependentesExcluidos = camposDependentesExcluidos;
	}

	public ArrayList getCamposDependentesExcluidos() {
		if (this.camposDependentesExcluidos == null) {
			this.camposDependentesExcluidos = new ArrayList();
		}
		return this.camposDependentesExcluidos;
	}

	public void setIdxNivelExcluido(String idxNivelExcluido) {
		this.idxNivelExcluido = idxNivelExcluido;
	}

	public String getIdxNivelExcluido() {
		if (this.idxNivelExcluido == null) {
			this.idxNivelExcluido = ConstantesCRM.SVAZIO;
		}
		return this.idxNivelExcluido;
	}
}