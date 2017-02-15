package workflow.AtendimentoFila.Portabilidade.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;


public class GestorGerenteContaForm extends ActionForm {

	private long idGestorGerente;

	private String primeiroNome;

	private String nomeMeio;

	private String sobrenome;

	private String nrTelefone;

	private String dsEmail;

	private String nrCPF;

	private String nrTelefoneContato;

	private String dtAlteracao;

	private String dsLoginResponsavel;

	private boolean gestor;

	private boolean gerente;

	private String tpOperacao;

	private String tpRelacao;

	private String idContact;

	private String dataNascimento;
	private String consultoRelacionamento1;
	private String consultoRelacionamento2;
	private String mensagemEndereco;
	private String mensagemComplemento;

	public long getIdGestorGerente() {
		return this.idGestorGerente;
	}

	public void setIdGestorGerente(long arg) {
		this.idGestorGerente = arg;
	}

	public String getIdContact() {
		if (this.idContact == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.idContact;
	}

	public void setIdContact(String arg) {
		this.idContact = arg;
	}

	public String getPrimeiroNome() {
		if (this.primeiroNome == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.primeiroNome;
	}

	public void setPrimeiroNome(String arg) {
		this.primeiroNome = arg;
	}

	public String getNomeMeio() {
		if (this.nomeMeio == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.nomeMeio;
	}

	public void setNomeMeio(String arg) {
		this.nomeMeio = arg;
	}

	public String getSobrenome() {
		if (this.sobrenome == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.sobrenome;
	}

	public void setSobrenome(String arg) {
		this.sobrenome = arg;
	}

	public String getNrTelefone() {
		if (this.nrTelefone == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.nrTelefone;
	}

	public void setNrTelefone(String arg) {
		this.nrTelefone = arg;
	}

	public String getDsEmail() {
		if (this.dsEmail == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.dsEmail;
	}

	public void setDsEmail(String arg) {
		this.dsEmail = arg;
	}

	public String getNrCPF() {
		if (this.nrCPF == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.nrCPF;
	}

	public void setNrCPF(String arg) {
		this.nrCPF = arg;
	}

	public String getNrTelefoneContato() {
		if (this.nrTelefoneContato == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.nrTelefoneContato;
	}

	public void setNrTelefoneContato(String arg) {
		this.nrTelefoneContato = arg;
	}

	public String getDtAlteracao() {
		if (this.dtAlteracao == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.dtAlteracao;
	}

	public void setDtAlteracao(String arg) {
		this.dtAlteracao = arg;
	}

	public String getdsLoginResponsavel() {
		if (this.dsLoginResponsavel == null) {
			return ConstantesCRM.SVAZIO;
		}
		return this.dsLoginResponsavel;
	}

	public void setDsLoginResponsavel(String arg) {
		this.dsLoginResponsavel = arg;
	}

	public boolean isGestor() {
		return this.gestor;
	}

	public void setGestor(boolean arg) {
		this.gestor = arg;
	}

	public boolean isGerente() {
		return this.gerente;
	}

	public void setGerente(boolean arg) {
		this.gerente = arg;
	}

	public String getTpOperacao() {
		return this.tpOperacao;
	}

	public void setTpOperacao(String arg) {
		this.tpOperacao = arg;
	}

	public String getTpRelacao() {
		return this.tpRelacao;
	}

	public void setTpRelacao(String arg) {
		this.tpRelacao = arg;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getConsultoRelacionamento1() {
		return this.consultoRelacionamento1;
	}

	public void setConsultoRelacionamento1(String consultoRelacionamento1) {
		this.consultoRelacionamento1 = consultoRelacionamento1;
	}

	public String getConsultoRelacionamento2() {
		return this.consultoRelacionamento2;
	}

	public void setConsultoRelacionamento2(String consultoRelacionamento2) {
		this.consultoRelacionamento2 = consultoRelacionamento2;
	}

	public String getMensagemEndereco() {
		return this.mensagemEndereco;
	}

	public void setMensagemEndereco(String mensagemEndereco) {
		this.mensagemEndereco= mensagemEndereco;
	}

	public String getMensagemComplemento() {
		return this.mensagemComplemento;
	}

	public void setMensagemComplemento(String mensagemComplemento) {
		this.mensagemComplemento= mensagemComplemento;
	}
}
