package admsistemas.GrupoUsuarioRegrasEncaminhamento;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoSelecionadoVODocument.RegrasEncaminhamentoSelecionadoVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.TipoClienteVODocument.TipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;

public class UsuarioEncaminhamentoForm extends ActionForm {

	private static final long serialVersionUID = 8547528872193687974L;

	private Integer codigoGrupo = null;
	private GrupoVO[] aGrupoVO = null;
	private GrupoVO grupoVOSelecionado = null;
	private String[] tipoClienteDisponivel;
	private String[] segmentacaoDisponivel;
	private String[] carterizacaoDisponivel;
	private String[] procedenciaDisponivel;
	private String[] tipoClienteAssociada;
	private String[] segmentacaoAssociada;
	private String[] carterizacaoAssociada;
	private String[] procedenciaAssociada;
	private RegrasEncaminhamentoVO regrasEncaminhamentoVO = null;

	/**
	 * Construtor Default
	 */
	public UsuarioEncaminhamentoForm() {
		tipoClienteDisponivel = new String[0];
		segmentacaoDisponivel = new String[0];
		carterizacaoDisponivel = new String[0];
		procedenciaDisponivel = new String[0];
		tipoClienteAssociada = new String[0];
		segmentacaoAssociada = new String[0];
		carterizacaoAssociada = new String[0];
		procedenciaAssociada = new String[0];

		this.grupoVOSelecionado = GrupoVO.Factory.newInstance();

		TipoClienteVO[] tipoCliente = new TipoClienteVOImpl[0];
		SegmentacaoVO[] segmentacao = new SegmentacaoVOImpl[0];
		CarterizacaoVO[] carterizacao = new CarterizacaoVOImpl[0];
		ProcedenciaVO[] procedencia = new ProcedenciaVOImpl[0];

		this.regrasEncaminhamentoVO = RegrasEncaminhamentoVO.Factory.newInstance();
		this.regrasEncaminhamentoVO
				.setRegrasEncaminhamentoDisponivelVO(RegrasEncaminhamentoDisponivelVO.Factory.newInstance());
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoClienteVOArray(
				tipoCliente);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setSegmentacaoVOArray(
				segmentacao);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCarterizacaoVOArray(
				carterizacao);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setProcedenciaVOArray(
				procedencia);

		this.regrasEncaminhamentoVO
				.setRegrasEncaminhamentoSelecionadoVO(RegrasEncaminhamentoSelecionadoVO.Factory
						.newInstance());
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoClienteVOArray(
				tipoCliente);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setSegmentacaoVOArray(
				segmentacao);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCarterizacaoVOArray(
				carterizacao);
		this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setProcedenciaVOArray(
				procedencia);
	}

	public Integer getCodigoGrupo() {
		return this.codigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public GrupoVO[] getAGrupoVO() {
		return this.aGrupoVO;
	}

	public void setAGrupoVO(GrupoVO[] grupos) {
		this.aGrupoVO = grupos;
	}

	public GrupoVO getGrupoVOSelecionado() {
		return this.grupoVOSelecionado;
	}

	public void setGrupoVOSelecionado(GrupoVO grupoSelecionado) {
		this.grupoVOSelecionado = grupoSelecionado;
	}

	public RegrasEncaminhamentoVO getRegrasEncaminhamentoVO() {
		return this.regrasEncaminhamentoVO;
	}

	public void setRegrasEncaminhamentoVO(RegrasEncaminhamentoVO nRegrasEncaminhamentoVO) {
		this.regrasEncaminhamentoVO = nRegrasEncaminhamentoVO;
	}

	public String[] getCarterizacaoAssociada() {
		return carterizacaoAssociada;
	}

	public String[] getCarterizacaoDisponivel() {
		return carterizacaoDisponivel;
	}

	public String[] getProcedenciaAssociada() {
		return procedenciaAssociada;
	}

	public String[] getProcedenciaDisponivel() {
		return procedenciaDisponivel;
	}

	public String[] getSegmentacaoAssociada() {
		return segmentacaoAssociada;
	}

	public String[] getSegmentacaoDisponivel() {
		return segmentacaoDisponivel;
	}

	public String[] getTipoClienteAssociada() {
		return tipoClienteAssociada;
	}

	public String[] getTipoClienteDisponivel() {
		return tipoClienteDisponivel;
	}

	public void setCarterizacaoAssociada(String[] strings) {
		carterizacaoAssociada = strings;
	}

	public void setCarterizacaoDisponivel(String[] strings) {
		carterizacaoDisponivel = strings;
	}

	public void setProcedenciaAssociada(String[] strings) {
		procedenciaAssociada = strings;
	}

	public void setProcedenciaDisponivel(String[] strings) {
		procedenciaDisponivel = strings;
	}

	public void setSegmentacaoAssociada(String[] strings) {
		segmentacaoAssociada = strings;
	}

	public void setSegmentacaoDisponivel(String[] strings) {
		segmentacaoDisponivel = strings;
	}

	public void setTipoClienteAssociada(String[] strings) {
		tipoClienteAssociada = strings;
	}

	public void setTipoClienteDisponivel(String[] strings) {
		tipoClienteDisponivel = strings;
	}

}