package br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to;

import java.util.Date;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ListaTitularidadeLinhaWSTO extends BaseTransferObject {

    private static final long serialVersionUID = -1403022165339756081L;

	private long nrTelefone;
	private long cdArea;
	private boolean linhaAtiva;
	private String nrCPFCNPJ;
	private String dsModalidade;
	private String dsTecnologia;
	private String nrRG;
	private String dsOrgaoEmissorRG;
	private Date   dtNascimento;
	private String nome;
	private String nomeMeio;
	private String sobrenome;
	private String razaoSocial;
	private String dsEndereco;
	private String nrCEP;
	private String nmMunicipio;
	private String sgUF;
	private Date dtAtivacao = null;
	private Date dtDesativacao = null;

	public boolean isLinhaAtiva() {
		return linhaAtiva;
	}

	public void setLinhaAtiva(boolean linhaAtiva) {
		this.linhaAtiva = linhaAtiva;
	}

    public ListaTitularidadeLinhaWSTO() {
    }

	public String getNmMunicipio() {
		return nmMunicipio;
	}

	public void setNmMunicipio(String nmMunicipio) {
		this.nmMunicipio = nmMunicipio;
	}

	public String getNrRG() {
		return nrRG;
	}

	public void setNrRG(String nrRG) {
		this.nrRG = nrRG;
	}

	public String getNrCPFCNPJ() {
		return nrCPFCNPJ;
	}

	public void setNrCPFCNPJ(String nrCPFCNPJ) {
		this.nrCPFCNPJ = nrCPFCNPJ;
	}

	public String getDsOrgaoEmissorRG() {
		return dsOrgaoEmissorRG;
    }

	public void setDsOrgaoEmissorRG(String dsOrgaoEmissorRG) {
		this.dsOrgaoEmissorRG = dsOrgaoEmissorRG;
    }

	public Date getDtNascimento() {
		return dtNascimento;
    }

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
    }

	public String getNome() {
		return nome;
    }

	public void setNome(String nome) {
		this.nome = nome;
    }

	public String getNomeMeio() {
		return nomeMeio;
    }

	public void setNomeMeio(String nomeMeio) {
		this.nomeMeio = nomeMeio;
    }

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getNrCEP() {
		return nrCEP;
    }

	public void setNrCEP(String nrCEP) {
		this.nrCEP = nrCEP;
    }

	public String getSgUF() {
		return sgUF;
    }

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
    }

	public Date getDtAtivacao() {
	return dtAtivacao;
    }

	public void setDtAtivacao(Date dtAtivacao) {
	this.dtAtivacao = dtAtivacao;
    }

	public Date getDtDesativacao() {
	return dtDesativacao;
    }

	public void setDtDesativacao(Date dtDesativacao) {
	this.dtDesativacao = dtDesativacao;
    }

	public long getCdArea() {
		return cdArea;
	}

	public void setCdArea(long cdArea) {
		this.cdArea = cdArea;
	}

	public long getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(long nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getDsModalidade() {
		return dsModalidade;
	}

	public void setDsModalidade(String dsModalidade) {
		this.dsModalidade = dsModalidade;
	}

	public String getDsTecnologia() {
		return dsTecnologia;
	}

	public void setDsTecnologia(String dsTecnologia) {
		this.dsTecnologia = dsTecnologia;
	}

}
