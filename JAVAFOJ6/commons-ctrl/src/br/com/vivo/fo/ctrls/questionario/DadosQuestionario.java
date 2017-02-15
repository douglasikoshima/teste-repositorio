package br.com.vivo.fo.ctrls.questionario;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.questionario.vo.PerguntaRespondidaVODocument.PerguntaRespondidaVO;
import br.com.vivo.fo.questionario.vo.ScriptQuestionarioVODocument.ScriptQuestionarioVO;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Stack;

@SuppressWarnings({"rawtypes","unchecked"})
public class DadosQuestionario implements java.io.Serializable {

	private static final long serialVersionUID = -512372635661917593L;
	private String[] dados = null;
	private int indice = 0;
	private ScriptQuestionarioVO script;
	private Stack pilhaRespostas;
	// private PerguntaRespondidaVO[] seqRespostas;
	private String operacao = ConstantesCRM.SONE;
	// private long tempoDecorrido = 0l;
	private long horaInicioAtendimento = 0l;
	private ArrayList perguntasRespondidas;

	public ArrayList getPerguntasRespondidas() {
		if (this.perguntasRespondidas == null) {
			this.perguntasRespondidas = new ArrayList();
		}
		return this.perguntasRespondidas;
	}

	public void setPerguntasRespondidas(ArrayList perguntasRespondidas) {
		this.perguntasRespondidas = perguntasRespondidas;
	}

	public int getPerguntasRespondidasSize() {
		return this.getPerguntasRespondidas().size();
	}

	public long getHoraInicioAtendimento() {
		return this.horaInicioAtendimento;
	}

	public void setHoraInicioAtendimento(long horaInicioAtendimento) {
		this.horaInicioAtendimento = horaInicioAtendimento;
	}

	public String[] getDados() {
		if (this.dados == null) {
			this.dados = new String[8];
		}
		return this.dados;
	}

	public String getDados(int indice) {
		return this.getDados()[indice];
	}

	public void setDados(int indice, String value) {
		this.getDados()[indice] = value;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public int getIndice() {
		return this.indice;
	}

	public void incrementaIndice() {
		++this.indice;
	}

	public ScriptQuestionarioVO getScript() {
		/*
		 * if(this.script == null) { this.script = new ScriptQuestionarioVO.Factory.newInstance(); }
		 */
		return this.script;
	}

	public void setScript(ScriptQuestionarioVO script) {
		this.script = script;
	}

	public Stack getPilhaRespostas() {
		if (this.pilhaRespostas == null) {
			this.pilhaRespostas = new Stack();
		}
		return this.pilhaRespostas;
	}

	// public PerguntaRespondidaVO[] getSeqRespostas()
	// {
	/*
	 * if(this.seqRespostas == null) { this.seqRespostas =PerguntaRespondidaVO.Factory.newInstance(); }
	 */
	// return this.seqRespostas;
	// }
	// public void setSeqRespostas(PerguntaRespondidaVO[] seqRespostas){
	// this.seqRespostas = seqRespostas;
	// }
	public String getOperacao() {
		return this.operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public long getTempoDecorrido() {
		// return this.tempoDecorrido;
		return this.getTempoAtendimentoSegundos();
	}

	// public void setTempoDecorrido(long tempoDecorrido){
	// this.tempoDecorrido = tempoDecorrido;
	// }

	public void startAtendimento() {
		this.setHoraInicioAtendimento(GregorianCalendar.getInstance().getTimeInMillis());
	}

	public void addResposta(PerguntaRespondidaVO perguntaResp) {
		this.getPerguntasRespondidas().add(perguntaResp);
	}

	public void removeResposta(int indice) {
		if (indice <= this.getPerguntasRespondidas().size()) {
			this.getPerguntasRespondidas().remove(indice);
		}
	}

	public PerguntaRespondidaVO[] getRespostas() {
		// PerguntaRespondidaVO[] seqRespostas = new PerguntaRespondidaVO[0];
		return (PerguntaRespondidaVO[]) this.getPerguntasRespondidas().toArray(new PerguntaRespondidaVO[0]);
		// .toArray(seqRespostas);
	}

	public PerguntaRespondidaVO getRespostas(int indice) {
		// PerguntaRespondidaVO[] seqRespostas = new PerguntaRespondidaVO[0];
		return (PerguntaRespondidaVO) this.getPerguntasRespondidas().get(indice);
		// .toArray(seqRespostas);
	}

	public long getTempoAtendimentoSegundos() {
		long tempoTotal = 0l;
		long horaFimAtendimento = GregorianCalendar.getInstance().getTimeInMillis();
		tempoTotal = (long) (horaFimAtendimento - this.horaInicioAtendimento);
		tempoTotal = (tempoTotal / 1000);
		return tempoTotal;
	}
}
