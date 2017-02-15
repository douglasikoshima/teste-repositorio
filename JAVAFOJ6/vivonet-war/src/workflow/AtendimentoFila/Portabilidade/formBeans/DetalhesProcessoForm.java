package workflow.AtendimentoFila.Portabilidade.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument.ListaHistoricoRetencaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

public class DetalhesProcessoForm extends ActionForm {

	private static final long serialVersionUID = -4244580276730016696L;

	private RWFAtendimentoVO detalheProcesso;
	private AtendimentoVO dadosProcesso;
	private PessoaVO dadosCliente;
	private RDContatoVO dadosContato;
	private DetalheLinhaVO dadosLinha;
	private ListaDetalheLinhaVO listaLinhasAssociadas;
	private DetalheHistoricoRetencaoVO detalhamentoHistoricoRetencao;
	private ParametrosVO parametros;
	private WFAcaoRetornoVO wFAcaoRetornoVO;
	private PortabilidadeVO portabilidade;
	private GestorGerenteContaForm[] gestorGerenteContaForm;
	AtendimentoHistoricoVO[] historicoProcesso;
	ListaHistoricoRetencaoVO historicoRetencao;
	private String tipoPesquisa;

	// Variável utilizada para controle de origem de chamada
	// ao Detalhe do Processo de Portabilidade.
	// Valores possíveis: TELAATENDIMENTO e FILAPORTABILIDADE
	private String dsOrigem;

	public DetalheHistoricoRetencaoVO getDetalheHistoricoRetencao() {
		if (this.detalhamentoHistoricoRetencao == null) {
			this.detalhamentoHistoricoRetencao = DetalheHistoricoRetencaoVO.Factory.newInstance();
		}
		return this.detalhamentoHistoricoRetencao;
	}

	public void setDetalheHistoricoRetencao(DetalheHistoricoRetencaoVO detalhamentoHistoricoRetencao) {
		this.detalhamentoHistoricoRetencao = detalhamentoHistoricoRetencao;
	}

	public ParametrosVO getParametros() {
		if (this.parametros == null) {
			this.parametros = ParametrosVO.Factory.newInstance();
		}
		return this.parametros;
	}

	public void setParametros(ParametrosVO parametros) {
		this.parametros = parametros;
	}

	public WFAcaoRetornoVO getWFAcaoRetornoVO() {
		if (this.wFAcaoRetornoVO == null) {
			this.wFAcaoRetornoVO = WFAcaoRetornoVO.Factory.newInstance();
		}
		return this.wFAcaoRetornoVO;
	}

	public void setWFAcaoRetornoVO(WFAcaoRetornoVO arg1) {
		this.wFAcaoRetornoVO = arg1;
	}

	public ListaHistoricoRetencaoVO getHistoricoRetencao() {
		if (this.historicoRetencao == null) {
			this.historicoRetencao = ListaHistoricoRetencaoVO.Factory.newInstance();
		}
		return this.historicoRetencao;
	}

	public void setHistoricoRetencao(ListaHistoricoRetencaoVO historicoRetencao) {
		this.historicoRetencao = historicoRetencao;
	}

	public AtendimentoVO getDadosProcesso() {
		if (this.dadosProcesso == null) {
			this.dadosProcesso = AtendimentoVO.Factory.newInstance();
		}
		return this.dadosProcesso;
	}

	public void setDadosProcesso(AtendimentoVO dadosProcesso) {
		this.dadosProcesso = dadosProcesso;
	}

	public RWFAtendimentoVO getDetalheProcesso() {
		if (this.detalheProcesso == null) {
			this.detalheProcesso = RWFAtendimentoVO.Factory.newInstance();
		}
		return this.detalheProcesso;
	}

	public void setDetalheProcesso(RWFAtendimentoVO detalheProcesso) {
		this.detalheProcesso = detalheProcesso;
	}

	public PessoaVO getDadosCliente() {
		if (this.dadosCliente == null) {
			this.dadosCliente = PessoaVO.Factory.newInstance();
		}
		return this.dadosCliente;
	}

	public void setDadosCliente(PessoaVO dadosCliente) {
		this.dadosCliente = dadosCliente;
	}

	public RDContatoVO getDadosContato() {
		if (this.dadosContato == null) {
			this.dadosContato = RDContatoVO.Factory.newInstance();
		}
		return this.dadosContato;
	}

	public void setDadosContato(RDContatoVO dadosContato) {
		this.dadosContato = dadosContato;
	}

	public DetalheLinhaVO getDadosLinha() {
		if (this.dadosLinha == null) {
			this.dadosLinha = DetalheLinhaVO.Factory.newInstance();
		}
		return this.dadosLinha;
	}

	public void setDadosLinha(DetalheLinhaVO dadosLinha) {
		this.dadosLinha = dadosLinha;
	}

	public ListaDetalheLinhaVO getListaLinhasAssociadas() {
		if (this.listaLinhasAssociadas == null) {
			this.listaLinhasAssociadas = ListaDetalheLinhaVO.Factory.newInstance();
		}
		return this.listaLinhasAssociadas;
	}

	public void setListaLinhasAssociadas(ListaDetalheLinhaVO arg1) {
		this.listaLinhasAssociadas = arg1;
	}

	public String getTipoPesquisa() {
		if (this.tipoPesquisa == null) {
			this.tipoPesquisa = ConstantesCRM.SVAZIO;
		}
		return this.tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public AtendimentoHistoricoVO[] getHistoricoProcesso() {
		if (this.historicoProcesso == null) {
			this.historicoProcesso = new AtendimentoHistoricoVO[0];
		}
		return this.historicoProcesso;
	}

	public void setHistoricoProcesso(AtendimentoHistoricoVO[] historicoProcesso) {
		this.historicoProcesso = historicoProcesso;
	}

	public String getDsOrigem() {
		if (this.dsOrigem == null) {
			this.dsOrigem = "FILAPORTABILIDADE";
		}
		return this.dsOrigem;
	}

	public void setDsOrigem(String dsOrigem) {
		this.dsOrigem = dsOrigem;
	}

	public PortabilidadeVO getPortabilidade() {
		if (this.portabilidade == null) {
			this.portabilidade = PortabilidadeVO.Factory.newInstance();
		}
		return this.portabilidade;
	}

	public void setPortabilidade(PortabilidadeVO arg) {
		this.portabilidade = arg;
	}

	public GestorGerenteContaForm[] getGestorGerenteContaForm() {
		if (this.gestorGerenteContaForm == null) {
			this.gestorGerenteContaForm = new GestorGerenteContaForm[2];
		}
		return this.gestorGerenteContaForm;
	}

	public void setGestorGerenteContaForm(GestorGerenteContaForm[] arg) {
		this.gestorGerenteContaForm = arg;
	}
}