package br.com.vivo.fo.ctrls.VOLTAV.fatura.db;

public class TarefaCliente {
	private int id;
	private String nr_telefone;
	private int id_plataforma;
	private String fl_status;
	private int dia_envio;
	private String nr_contrato;
	private String nm_email;
	private int id_operadora;
	private String dt_ultimaexecucao;
	private int id_canal;
	private String data;
	private String situacao;
	private String descricao;
	private int statusId;
	private int sbscrp_id;

	public TarefaCliente() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNr_telefone() {
		return nr_telefone;
	}

	public void setNr_telefone(String nr_telefone) {
		this.nr_telefone = nr_telefone;
	}
	public int getId_plataforma() {
		return id_plataforma;
	}

	public void setId_plataforma(int id_plataforma) {
		this.id_plataforma = id_plataforma;
	}

	public String getFl_status() {
		return fl_status;
	}

	public void setFl_status(String fl_status) {
		this.fl_status = fl_status;
	}

	public int getDia_envio() {
		return dia_envio;
	}

	public void setDia_envio(int dia_envio) {
		this.dia_envio = dia_envio;
	}

	public String getNr_contrato() {
		return nr_contrato;
	}

	public void setNr_contrato(String nr_contrato) {
		this.nr_contrato = nr_contrato;
	}

	public String getNm_email() {
		return nm_email;
	}

	public void setNm_email(String nm_email) {
		this.nm_email = nm_email;
	}

	public int getId_operadora() {
		return id_operadora;
	}

	public void setId_operadora(int id_operadora) {
		this.id_operadora = id_operadora;
	}

	public String getDt_ultimaexecucao() {
		return dt_ultimaexecucao;
	}

	public void setDt_ultimaexecucao(String dt_ultimaexecucao) {
		this.dt_ultimaexecucao = dt_ultimaexecucao;
	}

	public int getId_canal() {
		return id_canal;
	}

	public void setId_canal(int id_canal) {
		this.id_canal = id_canal;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getSbscrp_id() {
		return sbscrp_id;
	}

	public void setSbscrp_id(int sbscrp_id) {
		this.sbscrp_id = sbscrp_id;
	}
}