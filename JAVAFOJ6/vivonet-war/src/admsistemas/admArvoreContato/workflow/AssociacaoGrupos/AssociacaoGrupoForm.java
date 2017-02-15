package admsistemas.admArvoreContato.workflow.AssociacaoGrupos;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.ArvoreContatoVODocument.ArvoreContatoVO;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisAssosiadosVODocument;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisDisponiveisVODocument;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisVODocument.AssosiacaoGrupoVariaveisVO;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.GruposAberturaDocument.GruposAbertura;
import br.com.vivo.fo.admsistemas.vo.GruposDisponiveisDocument.GruposDisponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.admsistemas.vo.impl.AdmNaturezaVODocumentImpl.AdmNaturezaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class AssociacaoGrupoForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = -4035644519119877009L;

	private String dsPath;

	private String strMensagem = ConstantesCRM.SVAZIO;

	public void setMensagem(String strMensagem) {
		this.strMensagem = strMensagem;
	}

	public String getMensagem() {
		return this.strMensagem;
	}

	private String idRetorno = ConstantesCRM.SVAZIO;

	public void setIdRetorno(String idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getIdRetorno() {
		return this.idRetorno;
	}

	private String fechamento = ConstantesCRM.SVAZIO;

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public String getFechamento() {
		return this.fechamento;
	}

	private String contato = ConstantesCRM.SVAZIO;

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getContato() {
		return this.contato;
	}

	private String grupo = ConstantesCRM.SVAZIO;

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return this.grupo;
	}

	private br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO;

	public void setWFAcaoVO(br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO) {
		this.WFAcaoVO = WFAcaoVO;
	}

	public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO() {
		return this.WFAcaoVO;
	}

	private String[] gruposAssociados;

	public String[] getGruposAssociados() {
		return this.gruposAssociados;
	}

	public void setGruposAssociados(String[] strings) {
		this.gruposAssociados = strings;
	}

	private String[] gruposDisponiveis;

	public String[] getGruposDisponiveis() {
		return this.gruposDisponiveis;
	}

	public void setGruposDisponiveis(String[] strings) {
		this.gruposDisponiveis = strings;
	}

	private GruposProcessosVO gruposProcessos;

	public void setGruposProcessos(GruposProcessosVO processosVO) {
		this.gruposProcessos = processosVO;
	}

	public GruposProcessosVO getGruposProcessos() {
		return this.gruposProcessos;
	}

	private String[] carterizacaoAssociada;

	public String[] getCarterizacaoAssociada() {
		return this.carterizacaoAssociada;
	}

	public void setCarterizacaoAssociada(String[] strings) {
		this.carterizacaoAssociada = strings;
	}

	private String[] carterizacaoDisponivel;

	public String[] getCarterizacaoDisponivel() {
		return this.carterizacaoDisponivel;
	}

	public void setCarterizacaoDisponivel(String[] strings) {
		this.carterizacaoDisponivel = strings;
	}

	private String[] naturezaAssociada;

	public String[] getNaturezaAssociada() {
		return this.naturezaAssociada;
	}

	public void setNaturezaAssociada(String[] strings) {
		this.naturezaAssociada = strings;
	}

	private String[] naturezaDisponivel;

	public String[] getNaturezaDisponivel() {
		return this.naturezaDisponivel;
	}

	public void setNaturezaDisponivel(String[] strings) {
		this.naturezaDisponivel = strings;
	}

	private String[] tipoLinhaDisponivel;

	public String[] getTipoLinhaDisponivel() {
		return this.tipoLinhaDisponivel;
	}

	public void setTipoLinhaDisponivel(String[] strings) {
		this.tipoLinhaDisponivel = strings;
	}

	private String[] tipoLinhaAssociada;

	public String[] getTipoLinhaAssociada() {
		return this.tipoLinhaAssociada;
	}

	public void setTipoLinhaAssociada(String[] strings) {
		this.tipoLinhaAssociada = strings;
	}

	private String[] segmentacaoAssociada;

	public String[] getSegmentacaoAssociada() {
		return this.segmentacaoAssociada;
	}

	public void setSegmentacaoAssociada(String[] strings) {
		this.segmentacaoAssociada = strings;
	}

	private String[] segmentacaoDisponivel;

	public String[] getSegmentacaoDisponivel() {
		return this.segmentacaoDisponivel;
	}

	public void setSegmentacaoDisponivel(String[] strings) {
		this.segmentacaoDisponivel = strings;
	}

	private String[] tipoClienteAssociada;

	public String[] getTipoClienteAssociada() {
		return this.tipoClienteAssociada;
	}

	public void setTipoClienteAssociada(String[] strings) {
		this.tipoClienteAssociada = strings;
	}

	private String[] tipoClienteDisponivel;

	public String[] getTipoClienteDisponivel() {
		return this.tipoClienteDisponivel;
	}

	public void setTipoClienteDisponivel(String[] strings) {
		this.tipoClienteDisponivel = strings;
	}

	private AssosiacaoGrupoVariaveisVO assosiacaoGrupoVariaveisVO;

	public AssosiacaoGrupoVariaveisVO getAssosiacaoGrupoVariaveisVO() {
		return this.assosiacaoGrupoVariaveisVO;
	}

	public void setAssosiacaoGrupoVariaveisVO(AssosiacaoGrupoVariaveisVO assosiacaoGrupoVariaveisVO) {
		this.assosiacaoGrupoVariaveisVO = assosiacaoGrupoVariaveisVO;
	}

	public AssociacaoGrupoForm() {

		gruposDisponiveis = new String[0];
		gruposAssociados = new String[0];

		this.gruposProcessos = GruposProcessosVO.Factory.newInstance();

		this.gruposProcessos.setGruposDisponiveis(GruposDisponiveis.Factory.newInstance());
		this.gruposProcessos.getGruposDisponiveis().setGrupoProcessoVO(
				GrupoProcessoVO.Factory.newInstance());
		this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO()
				.setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
		this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO()
				.setGrupoVOArray(new GrupoVOImpl[0]);

		this.gruposProcessos.setGruposAbertura(GruposAbertura.Factory.newInstance());
		this.gruposProcessos.getGruposAbertura().setGrupoProcessoVO(
				GrupoProcessoVO.Factory.newInstance());
		this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO()
				.setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
		this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO()
				.setGrupoVOArray(new GrupoVOImpl[0]);

		this.tipoClienteDisponivel = new String[0];
		this.tipoClienteAssociada = new String[0];

		this.naturezaDisponivel = new String[0];
		this.naturezaAssociada = new String[0];

		this.carterizacaoDisponivel = new String[0];
		this.carterizacaoAssociada = new String[0];

		this.segmentacaoDisponivel = new String[0];
		this.segmentacaoAssociada = new String[0];

		this.tipoLinhaDisponivel = new String[0];
		this.tipoLinhaAssociada = new String[0];

		this.assosiacaoGrupoVariaveisVO = AssosiacaoGrupoVariaveisVO.Factory.newInstance();

		this.assosiacaoGrupoVariaveisVO
				.setAssosiacaoGrupoVariaveisAssosiadosVO(AssosiacaoGrupoVariaveisAssosiadosVODocument.AssosiacaoGrupoVariaveisAssosiadosVO.Factory
						.newInstance());
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO()
				.setTipoClienteVOArray(new TipoClienteVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO()
				.setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO()
				.setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO()
				.setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setTipoLinhaVOArray(
				new TipoLinhaVOImpl[0]);

		this.assosiacaoGrupoVariaveisVO
				.setAssosiacaoGrupoVariaveisDisponiveisVO(AssosiacaoGrupoVariaveisDisponiveisVODocument.AssosiacaoGrupoVariaveisDisponiveisVO.Factory
						.newInstance());
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO()
				.setTipoClienteVOArray(new TipoClienteVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO()
				.setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO()
				.setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO()
				.setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
		this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setTipoLinhaVOArray(
				new TipoLinhaVOImpl[0]);
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
	}
}