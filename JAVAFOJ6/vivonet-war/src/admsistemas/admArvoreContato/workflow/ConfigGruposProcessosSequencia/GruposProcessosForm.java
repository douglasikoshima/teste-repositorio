package admsistemas.admArvoreContato.workflow.ConfigGruposProcessosSequencia;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.ArvoreContatoVODocument.ArvoreContatoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.GruposAberturaDocument.GruposAbertura;
import br.com.vivo.fo.admsistemas.vo.GruposDisponiveisDocument.GruposDisponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.admsistemas.vo.GruposRetornoDocument.GruposRetorno;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoDocument.GruposTratamento;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class GruposProcessosForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = -7331337455538450345L;

	private String dsPath;

	private String filtroGrupos;

	private String[] gruposDisponiveis;
	private String[] gruposAbertura;
	private String[] gruposTratamento;
	private String[] gruposRetorno;
	private String nodeSelecionado;
	private GruposProcessosVO gruposProcessos;

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

	public GruposProcessosForm() {
		gruposDisponiveis = new String[0];
		gruposAbertura = new String[0];
		gruposTratamento = new String[0];
		gruposRetorno = new String[0];

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

		this.gruposProcessos.setGruposTratamento(GruposTratamento.Factory.newInstance());
		this.gruposProcessos.getGruposTratamento().setGrupoProcessoVO(
				GrupoProcessoVO.Factory.newInstance());
		this.gruposProcessos.getGruposTratamento().getGrupoProcessoVO()
				.setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
		this.gruposProcessos.getGruposTratamento().getGrupoProcessoVO()
				.setGrupoVOArray(new GrupoVOImpl[0]);

		this.gruposProcessos.setGruposRetorno(GruposRetorno.Factory.newInstance());
		this.gruposProcessos.getGruposRetorno().setGrupoProcessoVO(
				GrupoProcessoVO.Factory.newInstance());
		this.gruposProcessos.getGruposRetorno().getGrupoProcessoVO()
				.setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
		this.gruposProcessos.getGruposRetorno().getGrupoProcessoVO()
				.setGrupoVOArray(new GrupoVOImpl[0]);
	}

	public String[] getGruposAbertura() {
		return gruposAbertura;
	}

	public String[] getGruposDisponiveis() {
		return gruposDisponiveis;
	}

	public String getNodeSelecionado() {
		return nodeSelecionado;
	}

	public GruposProcessosVO getGruposProcessos() {
		return gruposProcessos;
	}

	public String[] getGruposRetorno() {
		return gruposRetorno;
	}

	public String[] getGruposTratamento() {
		return gruposTratamento;
	}

	public void setGruposAbertura(String[] strings) {
		gruposAbertura = strings;
	}

	public void setGruposDisponiveis(String[] strings) {
		gruposDisponiveis = strings;
	}

	public void setNodeSelecionado(String string) {
		nodeSelecionado = string;
	}

	public void setGruposProcessos(GruposProcessosVO processosVO) {
		gruposProcessos = processosVO;
	}

	public void setGruposRetorno(String[] strings) {
		gruposRetorno = strings;
	}

	public void setGruposTratamento(String[] strings) {
		gruposTratamento = strings;
	}

	public void setFiltroGrupos(String filtroGrupos) {
		this.filtroGrupos = filtroGrupos;
	}

	public String getFiltroGrupos() {
		return this.filtroGrupos;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
	}
}