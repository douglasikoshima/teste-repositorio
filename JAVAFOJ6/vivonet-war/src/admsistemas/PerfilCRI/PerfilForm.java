package admsistemas.PerfilCRI;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisAssociadasVODocument.PerfilVariaveisAssociadasVO;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisDisponiveisVODocument.PerfilVariaveisDisponiveisVO;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.admsistemas.vo.impl.AdmGrupoAberturaVODocumentImpl.AdmGrupoAberturaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.AdmNaturezaVODocumentImpl.AdmNaturezaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.AdmUFOperadoraSimplVODocumentImpl.AdmUFOperadoraSimplVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.PerfilDocumentImpl.PerfilImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.impl.CanalVODocumentImpl.CanalVOImpl;

public class PerfilForm extends ActionForm {

	private static final long serialVersionUID = 7078717354078906093L;

	private String inAcao;
	private String arrayLength;
	private String strMensagem = ConstantesCRM.SVAZIO;

	public void setMensagem(String strMensagem) {
		this.strMensagem = strMensagem;
	}

	public String getMensagem() {
		return this.strMensagem;
	}

	private String bloco = ConstantesCRM.SVAZIO;

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public String getBloco() {
		return this.bloco;
	}

	// Atributo de pesquisa
	private String inTotal = ConstantesCRM.SVAZIO;

	public void setInTotal(String inTotal) {
		this.inTotal = inTotal;
	}

	public String getInTotal() {
		return this.inTotal;
	}

	// Atributo de pesquisa
	private String inFim = ConstantesCRM.SVAZIO;

	public void setInFim(String inFim) {
		this.inFim = inFim;
	}

	public String getInFim() {
		return this.inFim;
	}

	// Id de Retorno
	private String idRetorno = ConstantesCRM.SVAZIO;

	public void setIdRetorno(String idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getIdRetorno() {
		return this.idRetorno;
	}

	// nome Perfil
	private String nmPerfil = ConstantesCRM.SVAZIO;

	public void setNmPerfil(String nmPerfil) {
		this.nmPerfil = nmPerfil;
	}

	public String getNmPerfil() {
		return this.nmPerfil;
	}

	// id Perfil
	private String idPerfil = ConstantesCRM.SVAZIO;

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdPerfil() {
		return this.idPerfil;
	}

	// Tipo Linha
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

	// Segmento
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

	// Carteira
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

	// Procedencia
	private String[] procedenciaAssociada;

	public String[] getProcedenciaAssociada() {
		return this.procedenciaAssociada;
	}

	public void setProcedenciaAssociada(String[] strings) {
		this.procedenciaAssociada = strings;
	}

	private String[] procedenciaDisponivel;

	public String[] getProcedenciaDisponivel() {
		return this.procedenciaDisponivel;
	}

	public void setProcedenciaDisponivel(String[] strings) {
		this.procedenciaDisponivel = strings;
	}

	// Natureza
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

	// Tipo Cliente
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

	// Grupo Abertura
	private String[] grupoAberturaAssociada;

	public String[] getGrupoAberturaAssociada() {
		return this.grupoAberturaAssociada;
	}

	public void setGrupoAberturaAssociada(String[] strings) {
		this.grupoAberturaAssociada = strings;
	}

	private String[] grupoAberturaDisponivel;

	public String[] getGrupoAberturaDisponivel() {
		return this.grupoAberturaDisponivel;
	}

	public void setGrupoAberturaDisponivel(String[] strings) {
		this.grupoAberturaDisponivel = strings;
	}

	// Canal
	private String[] canalAssociada;

	public String[] getCanalAssociada() {
		return this.canalAssociada;
	}

	public void setCanalAssociada(String[] strings) {
		this.canalAssociada = strings;
	}

	private String[] canalDisponivel;

	public String[] getCanalDisponivel() {
		return this.canalDisponivel;
	}

	public void setCanalDisponivel(String[] strings) {
		this.canalDisponivel = strings;
	}

	// Regional
	private String[] regionalAssociada;

	public String[] getRegionalAssociada() {
		return this.regionalAssociada;
	}

	public void setRegionalAssociada(String[] strings) {
		this.regionalAssociada = strings;
	}

	private String[] regionalDisponivel;

	public String[] getRegionalDisponivel() {
		return this.regionalDisponivel;
	}

	public void setRegionalDisponivel(String[] strings) {
		this.regionalDisponivel = strings;
	}

	// Perfil Variaveis
	private PerfilVariaveisVO perfilVariaveisVO;

	public PerfilVariaveisVO getPerfilVariaveisVO() {

		return this.perfilVariaveisVO;
	}

	public void setPerfilVariaveisVO(PerfilVariaveisVO perfilVariaveisVO) {
		this.perfilVariaveisVO = perfilVariaveisVO;
	}

	public PerfilForm() {

		this.perfilVariaveisVO = PerfilVariaveisVO.Factory.newInstance();

		this.perfilVariaveisVO.setPerfilArray(new PerfilImpl[0]);

		this.perfilVariaveisVO.setPerfilVariaveisDisponiveisVO(PerfilVariaveisDisponiveisVO.Factory
				.newInstance());
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmNaturezaVOArray(
				new AdmNaturezaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmGrupoAberturaVOArray(
				new AdmGrupoAberturaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmUFOperadoraSimplVOArray(
				new AdmUFOperadoraSimplVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setCanalVOArray(new CanalVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setCarterizacaoVOArray(
				new CarterizacaoVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setProcedenciaVOArray(
				new ProcedenciaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setSegmentacaoVOArray(
				new SegmentacaoVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setTipoClienteVOArray(
				new TipoClienteVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setTipoLinhaVOArray(
				new TipoLinhaVOImpl[0]);

		this.perfilVariaveisVO.setPerfilVariaveisAssociadasVO(PerfilVariaveisAssociadasVO.Factory
				.newInstance());
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmNaturezaVOArray(
				new AdmNaturezaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmGrupoAberturaVOArray(
				new AdmGrupoAberturaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmUFOperadoraSimplVOArray(
				new AdmUFOperadoraSimplVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setCanalVOArray(new CanalVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setCarterizacaoVOArray(
				new CarterizacaoVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setProcedenciaVOArray(
				new ProcedenciaVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setSegmentacaoVOArray(
				new SegmentacaoVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setTipoClienteVOArray(
				new TipoClienteVOImpl[0]);
		this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setTipoLinhaVOArray(
				new TipoLinhaVOImpl[0]);

		this.tipoLinhaDisponivel = new String[0];
		this.tipoLinhaAssociada = new String[0];

		this.segmentacaoDisponivel = new String[0];
		this.segmentacaoAssociada = new String[0];

		this.carterizacaoDisponivel = new String[0];
		this.carterizacaoAssociada = new String[0];

		this.procedenciaDisponivel = new String[0];
		this.procedenciaAssociada = new String[0];

		this.naturezaDisponivel = new String[0];
		this.naturezaAssociada = new String[0];

		this.tipoClienteDisponivel = new String[0];
		this.tipoClienteAssociada = new String[0];

		this.grupoAberturaDisponivel = new String[0];
		this.grupoAberturaAssociada = new String[0];

		this.canalDisponivel = new String[0];
		this.canalAssociada = new String[0];

		this.regionalDisponivel = new String[0];
		this.regionalAssociada = new String[0];
	}

	public void setArrayLength(String arrayLength) {
		this.arrayLength = arrayLength;
	}

	public String getArrayLength() {
		if (this.arrayLength == null) {
			this.arrayLength = ConstantesCRM.SVAZIO;
		}
		return this.arrayLength;
	}

	public void setInAcao(String inAcao) {
		this.inAcao = inAcao;
	}

	public String getInAcao() {
		if (this.inAcao == null) {
			this.inAcao = ConstantesCRM.SVAZIO;
		}

		return this.inAcao;
	}
}