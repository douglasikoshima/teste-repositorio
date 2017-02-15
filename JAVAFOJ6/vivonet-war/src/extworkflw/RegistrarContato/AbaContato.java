package extworkflw.RegistrarContato;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;

public class AbaContato extends ActionForm {

	private static final long serialVersionUID = -1914290554490748210L;

	private ListaTipoComunicacaoVO listaTipos = ListaTipoComunicacaoVO.Factory.newInstance();
	private String idTipoSelecionado = new String();
	private ComunicacaoVO contato;
	private String idComunicacao = new String();
	private String idPessoa = new String();
	private String idArray = new String();
	private String acao = new String();
	private ComunicacaoVO[] abaContato;
	private String inMsgRetorno = new String();
	private String dsComunicacao = new String();

	public AbaContato() {
		contato = ComunicacaoVO.Factory.newInstance();
		contato.addNewTipoComunicacaoVO();
	}

	public void setAbaContato(ComunicacaoVO[] abaContato) {
		this.abaContato = abaContato;
	}

	public ComunicacaoVO[] getAbaContato() {
		return this.abaContato;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getAcao() {
		return this.acao;
	}

	public void setDsComunicacao(String dsComunicacao) {
		this.dsComunicacao = dsComunicacao;
	}

	public String getDsComunicacao() {
		return this.dsComunicacao;
	}

	public void setIdArray(String idArray) {
		this.idArray = idArray;
	}

	public String getIdArray() {
		return this.idArray;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getIdPessoa() {
		return this.idPessoa;
	}

	public void setIdComunicacao(String idComunicacao) {
		this.idComunicacao = idComunicacao;
	}

	public String getIdComunicacao() {
		return this.idComunicacao;
	}

	public void setContato(ComunicacaoVO contato) {
		this.contato = contato;
	}

	public ComunicacaoVO getContato() {
		return this.contato;
	}

	public void setIdTipoSelecionado(String idTipoSelecionado) {
		this.idTipoSelecionado = idTipoSelecionado;
	}

	public String getIdTipoSelecionado() {
		return this.idTipoSelecionado;
	}

	public void setListaTipos(ListaTipoComunicacaoVO listaTipos) {
		this.listaTipos = listaTipos;
	}

	public ListaTipoComunicacaoVO getListaTipos() {
		return this.listaTipos;
	}

	public void setInMsgRetorno(String inMsgRetorno) {
		this.inMsgRetorno = inMsgRetorno;
	}

	public String getInMsgRetorno() {
		return this.inMsgRetorno;
	}
}