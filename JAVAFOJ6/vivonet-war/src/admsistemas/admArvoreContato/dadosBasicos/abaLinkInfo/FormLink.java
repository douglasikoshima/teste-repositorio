package admsistemas.admArvoreContato.dadosBasicos.abaLinkInfo;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmObjetoLinkVODocument.AdmObjetoLinkVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoClienteVODocument.AdmTipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoOperadoraVODocument.AdmTipoOperadoraVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormLink extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1976450546758693731L;

	private String idTipoLinha;
	private String idTipoRelacionamento;
	private String idUFOperadora;
	// private String idUfOperadora;
	private String nmUfOperadora;
	private String nmTipoLinha;
	private String nmTipoRelacionamento;
	private String msgError = ConstantesCRM.SVAZIO;
	private String idContatoInformacao;
	private String listaLinkLegth;
	private String idTipoLinhaEditado;
	private String idUFOperadoraEditado;
	private String idTipoRelacionamentoEditado;
	private String idContatoEditado;
	private String nmLinkEditado;
	private String[] arrayAdmTipoClienteSelecionado;
	private String nmContato;
	private String[] arrayAdmLinkObjeto;
	private AdmObjetoLinkVO[] admObjetoLinkVO;
	private String nmLink;
	private String[] arrayAdmTipoOperadoraSelecionada;
	private String[] arrayAdmTipoOperadoraDisponivel;
	private AdmTipoOperadoraVO[] admTipoOperadoraSelecionadaVO;
	private AdmTipoOperadoraVO[] admTipoOperadoraDisponivelVO;
	private String[] arrayAdmTipoClienteDisponivel;
	private AdmTipoClienteVO[] admTipoClienteSelecionadoVO;
	private AdmTipoClienteVO[] admTipoClienteDisponivelVO;
	private String[] arrayTipoLinhaSelecionado;
	private String[] arrayTipoLinhaDisponivel;
	private AdmTipoLinhaVO[] admTipoLinhaSelecionadoVO;
	private AdmTipoLinhaVO[] admTipoLinhaDisponivelVO;
	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setAdmTipoLinhaDisponivelVO(AdmTipoLinhaVO[] admTipoLinhaDisponivelVO) {
		this.admTipoLinhaDisponivelVO = admTipoLinhaDisponivelVO;
	}

	public AdmTipoLinhaVO[] getAdmTipoLinhaDisponivelVO() {
		return this.admTipoLinhaDisponivelVO;
	}

	public void setAdmTipoLinhaSelecionadoVO(AdmTipoLinhaVO[] admTipoLinhaSelecionadoVO) {
		this.admTipoLinhaSelecionadoVO = admTipoLinhaSelecionadoVO;
	}

	public AdmTipoLinhaVO[] getAdmTipoLinhaSelecionadoVO() {
		return this.admTipoLinhaSelecionadoVO;
	}

	public void setArrayTipoLinhaDisponivel(String[] arrayTipoLinhaDisponivel) {
		this.arrayTipoLinhaDisponivel = arrayTipoLinhaDisponivel;
	}

	public String[] getArrayTipoLinhaDisponivel() {
		if (this.arrayTipoLinhaDisponivel == null || this.arrayTipoLinhaDisponivel.length == 0) {
			this.arrayTipoLinhaDisponivel = new String[1];
		}
		return this.arrayTipoLinhaDisponivel;
	}

	public void setArrayTipoLinhaSelecionado(String[] arrayTipoLinhaSelecionado) {
		this.arrayTipoLinhaSelecionado = arrayTipoLinhaSelecionado;
	}

	public String[] getArrayTipoLinhaSelecionado() {
		if (this.arrayTipoLinhaSelecionado == null || this.arrayTipoLinhaSelecionado.length == 0) {
			this.arrayTipoLinhaSelecionado = new String[1];
		}
		return this.arrayTipoLinhaSelecionado;
	}

	public void setAdmTipoClienteDisponivelVO(AdmTipoClienteVO[] admTipoClienteDisponivelVO) {
		this.admTipoClienteDisponivelVO = admTipoClienteDisponivelVO;
	}

	public AdmTipoClienteVO[] getAdmTipoClienteDisponivelVO() {
		return this.admTipoClienteDisponivelVO;
	}

	public void setAdmTipoClienteSelecionadoVO(AdmTipoClienteVO[] admTipoClienteSelecionadoVO) {
		this.admTipoClienteSelecionadoVO = admTipoClienteSelecionadoVO;
	}

	public AdmTipoClienteVO[] getAdmTipoClienteSelecionadoVO() {
		return this.admTipoClienteSelecionadoVO;
	}

	public void setArrayAdmTipoClienteDisponivel(String[] arrayAdmTipoClienteDisponivel) {
		this.arrayAdmTipoClienteDisponivel = arrayAdmTipoClienteDisponivel;
	}

	public String[] getArrayAdmTipoClienteDisponivel() {
		if (this.arrayAdmTipoClienteDisponivel == null
				|| this.arrayAdmTipoClienteDisponivel.length == 0) {
			this.arrayAdmTipoClienteDisponivel = new String[1];
		}
		return this.arrayAdmTipoClienteDisponivel;
	}

	public void setAdmTipoOperadoraDisponivelVO(AdmTipoOperadoraVO[] admTipoOperadoraDisponivelVO) {
		this.admTipoOperadoraDisponivelVO = admTipoOperadoraDisponivelVO;
	}

	public AdmTipoOperadoraVO[] getAdmTipoOperadoraDisponivelVO() {
		return this.admTipoOperadoraDisponivelVO;
	}

	public void setAdmTipoOperadoraSelecionadaVO(AdmTipoOperadoraVO[] admTipoOperadoraSelecionadaVO) {
		this.admTipoOperadoraSelecionadaVO = admTipoOperadoraSelecionadaVO;
	}

	public AdmTipoOperadoraVO[] getAdmTipoOperadoraSelecionadaVO() {
		return this.admTipoOperadoraSelecionadaVO;
	}

	public void setArrayAdmTipoOperadoraDisponivel(String[] arrayAdmTipoOperadoraDisponivel) {
		this.arrayAdmTipoOperadoraDisponivel = arrayAdmTipoOperadoraDisponivel;
	}

	public String[] getArrayAdmTipoOperadoraDisponivel() {
		if (this.arrayAdmTipoOperadoraDisponivel == null
				|| this.arrayAdmTipoOperadoraDisponivel.length == 0) {
			this.arrayAdmTipoOperadoraDisponivel = new String[1];
		}
		return this.arrayAdmTipoOperadoraDisponivel;
	}

	public void setArrayAdmTipoOperadoraSelecionada(String[] arrayAdmTipoOperadoraSelecionada) {
		this.arrayAdmTipoOperadoraSelecionada = arrayAdmTipoOperadoraSelecionada;
	}

	public String[] getArrayAdmTipoOperadoraSelecionada() {
		if (this.arrayAdmTipoOperadoraSelecionada == null
				|| this.arrayAdmTipoOperadoraSelecionada.length == 0) {
			this.arrayAdmTipoOperadoraSelecionada = new String[1];
		}
		return this.arrayAdmTipoOperadoraSelecionada;
	}

	public void setNmLink(String nmLink) {
		this.nmLink = nmLink;
	}

	public String getNmLink() {
		return this.nmLink;
	}

	public void setAdmObjetoLinkVO(AdmObjetoLinkVO[] admObjetoLinkVO) {
		this.admObjetoLinkVO = admObjetoLinkVO;
	}

	public AdmObjetoLinkVO[] getAdmObjetoLinkVO() {
		return this.admObjetoLinkVO;
	}

	public void setArrayAdmLinkObjeto(String[] arrayAdmLinkObjeto) {
		this.arrayAdmLinkObjeto = arrayAdmLinkObjeto;
	}

	public String[] getArrayAdmLinkObjeto() {
		if (this.arrayAdmLinkObjeto == null || this.arrayAdmLinkObjeto.length == 0) {
			this.arrayAdmLinkObjeto = new String[1];
		}
		return this.arrayAdmLinkObjeto;
	}

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

	public String getNmContato() {
		return this.nmContato;
	}

	public void setArrayAdmTipoClienteSelecionado(String[] arrayAdmTipoClienteSelecionado) {
		this.arrayAdmTipoClienteSelecionado = arrayAdmTipoClienteSelecionado;
	}

	public String[] getArrayAdmTipoClienteSelecionado() {
		if (this.arrayAdmTipoClienteSelecionado == null
				|| this.arrayAdmTipoClienteSelecionado.length == 0) {
			this.arrayAdmTipoClienteSelecionado = new String[1];
		}
		return this.arrayAdmTipoClienteSelecionado;
	}

	public void setNmLinkEditado(String nmLinkEditado) {
		this.nmLinkEditado = nmLinkEditado;
	}

	public String getNmLinkEditado() {
		return this.nmLinkEditado;
	}

	public void setIdContatoEditado(String idContatoEditado) {
		this.idContatoEditado = idContatoEditado;
	}

	public String getIdContatoEditado() {
		return this.idContatoEditado;
	}

	public void setIdTipoRelacionamentoEditado(String idTipoRelacionamentoEditado) {
		this.idTipoRelacionamentoEditado = idTipoRelacionamentoEditado;
	}

	public String getIdTipoRelacionamentoEditado() {
		return this.idTipoRelacionamentoEditado;
	}

	public void setIdUFOperadoraEditado(String idUFOperadoraEditado) {
		this.idUFOperadoraEditado = idUFOperadoraEditado;
	}

	public String getIdUFOperadoraEditado() {
		return this.idUFOperadoraEditado;
	}

	public void setIdTipoLinhaEditado(String idTipoLinhaEditado) {
		this.idTipoLinhaEditado = idTipoLinhaEditado;
	}

	public String getIdTipoLinhaEditado() {
		return this.idTipoLinhaEditado;
	}

	public void setListaLinkLegth(String listaLinkLegth) {
		this.listaLinkLegth = listaLinkLegth;
	}

	public String getListaLinkLegth() {
		return this.listaLinkLegth;
	}

	public void setIdContatoInformacao(String idContatoInformacao) {
		this.idContatoInformacao = idContatoInformacao;
	}

	public String getIdContatoInformacao() {
		return this.idContatoInformacao;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setNmTipoRelacionamento(String nmTipoRelacionamento) {
		this.nmTipoRelacionamento = nmTipoRelacionamento;
	}

	public String getNmTipoRelacionamento() {
		return this.nmTipoRelacionamento;
	}

	public void setNmTipoLinha(String nmTipoLinha) {
		this.nmTipoLinha = nmTipoLinha;
	}

	public String getNmTipoLinha() {
		return this.nmTipoLinha;
	}

	public void setNmUfOperadora(String nmUfOperadora) {
		this.nmUfOperadora = nmUfOperadora;
	}

	public String getNmUfOperadora() {
		return this.nmUfOperadora;
	}

	public void setIdUFOperadora(String idUFOperadora) {
		this.idUFOperadora = idUFOperadora;
	}

	public String getIdUFOperadora() {
		return this.idUFOperadora;
	}

	public void setIdTipoRelacionamento(String idTipoRelacionamento) {
		this.idTipoRelacionamento = idTipoRelacionamento;
	}

	public String getIdTipoRelacionamento() {
		return this.idTipoRelacionamento;
	}

	public void setIdTipoLinha(String idTipoLinha) {
		this.idTipoLinha = idTipoLinha;
	}

	public String getIdTipoLinha() {
		return this.idTipoLinha;
	}
}