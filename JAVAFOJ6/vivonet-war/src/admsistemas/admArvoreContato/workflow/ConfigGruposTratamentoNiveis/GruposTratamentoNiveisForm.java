package admsistemas.admArvoreContato.workflow.ConfigGruposTratamentoNiveis;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import admsistemas.admArvoreContato.workflow.ConfigGruposTratamentoNiveis.ConfigGruposTratamentoNiveisController.NivelVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoInicio;
import br.com.vivo.fo.admsistemas.vo.impl.GruposTratamentoNiveisVODocumentImpl.GruposTratamentoNiveisVOImpl.GrupoTratamentoImpl;

public class GruposTratamentoNiveisForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 6657636485040917326L;

	private int nivelAnterior;

	private int grupoSelecionado;
	private int nivelSelecionado;
	private int numNiveisRetorno;
	private NivelVO[] niveis;
	private GrupoVO[] gruposTratamentoArray;
	private GruposTratamentoNiveisVO gruposTratamentoNiveisVO;
	private String[] disponiveis;
	private String[] sequencia;

	public GruposTratamentoNiveisForm() {
		// inicializa combo de níveis
		niveis = new NivelVO[10];
		String[] labels = { "Nenhum", "1 Nível", "2 Níveis", "3 Níveis", "4 Níveis", "5 Níveis",
				"6 Níveis", "7 Níveis", "8 Níveis", "9 Níveis" };
		for (int i = 0; i < 10; i++) {
			niveis[i] = new NivelVO();
			niveis[i].setCodigo(i);
			niveis[i].setDescricao(labels[i]);
		}
		nivelSelecionado = 0;

		// inicializa Objeto GruposTratamentoNiveisVO
		gruposTratamentoNiveisVO = GruposTratamentoNiveisVO.Factory.newInstance();
		gruposTratamentoNiveisVO.setGrupoInicio(GrupoInicio.Factory.newInstance());
		gruposTratamentoNiveisVO.getGrupoInicio().setGrupoVO(GrupoVO.Factory.newInstance());
		gruposTratamentoNiveisVO.setGrupoTratamentoArray(new GrupoTratamentoImpl[0]);

		// inicializa arrays de retorno
		disponiveis = new String[0];
		sequencia = new String[0];
	}

	public GruposTratamentoNiveisVO getGruposTratamentoNiveisVO() {
		return gruposTratamentoNiveisVO;
	}

	public void setGruposTratamentoNiveisVO(GruposTratamentoNiveisVO gruposTratamentoNiveis) {
		gruposTratamentoNiveisVO = gruposTratamentoNiveis;
	}

	public GrupoVO[] getGruposTratamentoArray() {
		return gruposTratamentoArray;
	}

	public void setGruposTratamentoArray(GrupoVO[] grupoArray) {
		gruposTratamentoArray = grupoArray;
	}

	public int getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public NivelVO[] getNiveis() {
		return niveis;
	}

	public int getNivelSelecionado() {
		return nivelSelecionado;
	}

	public void setGrupoSelecionado(int grupo) {
		grupoSelecionado = grupo;
	}

	public void setNiveis(NivelVO[] niveis) {
		this.niveis = niveis;
	}

	public void setNivelSelecionado(int nivel) {
		nivelSelecionado = nivel;
	}

	public String[] getDisponiveis() {
		return disponiveis;
	}

	public void setdisponiveis(String[] disp) {
		disponiveis = disp;
	}

	public String[] getSequencia() {
		return sequencia;
	}

	public void setSequencia(String[] seq) {
		sequencia = seq;
	}

	public int getNumNiveisRetorno() {
		return this.numNiveisRetorno;
	}

	public void setNumNiveisRetorno(int num) {
		this.numNiveisRetorno = num;
	}

	public void setNivelAnterior(int nivelAnterior) {
		this.nivelAnterior = nivelAnterior;
	}

	public int getNivelAnterior() {
		return this.nivelAnterior;
	}
}