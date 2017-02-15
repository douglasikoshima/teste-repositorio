package admsistemas.admArvoreContato.dadosBasicos.abaDadosBasicos;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFechamentoVODocument.AdmTipoFechamentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class DadosBasicosForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 9127882026787631485L;

	private String msgError = ConstantesCRM.SVAZIO;
	private AdmTipoRetornoContatoVO[] tipoRetornoContatoVO;
	private String vlPesoContato;
	private String qtDiasPrazoContato;
	private String[] arraytipoRetornoContato;
	private AdmMensagemAvisoVO[] listaMensagem;
	private AdmTipoFechamentoVO[] listaFechamento;
	private String mensagem;
	private String processoTec;
	private String fechamento;
	private String idContato;

	public DadosBasicosForm() {
		idContato = ConstantesCRM.SVAZIO;
		fechamento = ConstantesCRM.SVAZIO;
		processoTec = ConstantesCRM.SVAZIO;
		mensagem = ConstantesCRM.SVAZIO;
		listaFechamento = new AdmTipoFechamentoVO[0];
		listaMensagem = new AdmMensagemAvisoVO[0];
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public String getFechamento() {
		return this.fechamento;
	}

	public void setProcessoTec(String processoTec) {
		this.processoTec = processoTec;
	}

	public String getProcessoTec() {
		return this.processoTec;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setListaFechamento(AdmTipoFechamentoVO[] listaFechamento) {
		this.listaFechamento = listaFechamento;
	}

	public AdmTipoFechamentoVO[] getListaFechamento() {
		return this.listaFechamento;
	}

	public void setListaMensagem(AdmMensagemAvisoVO[] listaMensagem) {
		this.listaMensagem = listaMensagem;
	}

	public AdmMensagemAvisoVO[] getListaMensagem() {
		return this.listaMensagem;
	}

	public void setArraytipoRetornoContato(String[] arraytipoRetornoContato) {
		this.arraytipoRetornoContato = arraytipoRetornoContato;
	}

	public String[] getArraytipoRetornoContato() {
		if (this.arraytipoRetornoContato == null || this.arraytipoRetornoContato.length == 0) {
			this.arraytipoRetornoContato = new String[1];
		}
		return this.arraytipoRetornoContato;
	}

	public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
		this.qtDiasPrazoContato = qtDiasPrazoContato;
	}

	public String getQtDiasPrazoContato() {
		return this.qtDiasPrazoContato;
	}

	public void setVlPesoContato(String vlPesoContato) {
		this.vlPesoContato = vlPesoContato;
	}

	public String getVlPesoContato() {
		return this.vlPesoContato;
	}

	public void setTipoRetornoContatoVO(AdmTipoRetornoContatoVO[] tipoRetornoContatoVO) {
		this.tipoRetornoContatoVO = tipoRetornoContatoVO;
	}

	public AdmTipoRetornoContatoVO[] getTipoRetornoContatoVO() {
		return this.tipoRetornoContatoVO;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}
}
