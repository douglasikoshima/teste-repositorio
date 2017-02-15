package br.com.indrasistemas.vivoservices.listapup.to;

import java.util.List;

public class LinhaPUPWSTO {

	public LinhaPUPWSTO() {
		// Constructor
	}

	private static final long serialVersionUID = -1347032197893232386L;

	public static final String CD_PROSSEGUE_ATENDIMENTO = "00";

	public static final String CD_LINHA_NAO_CADASTRADA_VIVONET = "01";

	public static final String CD_PARAMETROS_INVALIDOS = "02";

	public static final String CD_ERRO_BASE = "03";

	public static final String CD_ERRO_SUBROTINAS = "04";

	public static final String CD_LINHA_NAO_CADASTRADA_PUP = "05";

	public static final String CD_OPERACAO_NAO_PERMITIDA = "06";

	public static final String CD_ERRO_GENERICO = "99";

	private Integer cdOperacao;

	private List nrMIN;

	private Integer cdDDD;

	private Integer nrTelefone;

	private Integer cdCanal;

	private Integer cdProcedencia;

	private String sgCanal;

	private String sgProcedencia;

	private Integer inSMSProtocolos;

	private String dsSMSProtocolos;

	private String dtExpSMSProtocolos;

	private Integer inSMSPromocoes;

	private String dsSMSPromocoes;

	private String dtExpSMSPromocoes;

	private Integer inSMSProdutos;

	private String dsSMSProdutos;

	private String dtExpSMSProdutos;

	private Integer inSMSParceiros;

	private String dsSMSParceiros;

	private String dtExpSMSParceiros;

	private Integer inIVRPromocoes;

	private String dsIVRPromocoes;

	private String dtExpIVRPromocoes;

	private Integer inIVRProdutos;

	private String dsIVRProdutos;

	private String dtExpIVRProdutos;

	private Integer inIVRParceiros;

	private String dsIVRParceiros;

	private String dtExpIVRParceiros;

	private Integer inTelmktMsgVoz;
	
	private String dsTelmktMsgVoz;

	private String dtExpTelmktMsgVoz;
	
	private String dtInclusao;

	private String dtUltimaAlteracao;

	public Integer getCdCanal() {
		return this.cdCanal;
	}

	public void setCdCanal(Integer cdCanal) {
		this.cdCanal = cdCanal;
	}

	public Integer getCdDDD() {
		return this.cdDDD;
	}

	public void setCdDDD(Integer cdDDD) {
		this.cdDDD = cdDDD;
	}

	public Integer getCdOperacao() {
		return this.cdOperacao;
	}

	public void setCdOperacao(Integer cdOperacao) {
		this.cdOperacao = cdOperacao;
	}

	public Integer getCdProcedencia() {
		return this.cdProcedencia;
	}

	public void setCdProcedencia(Integer cdProcedencia) {
		this.cdProcedencia = cdProcedencia;
	}

	public Integer getNrTelefone() {
		return this.nrTelefone;
	}

	public void setNrTelefone(Integer nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public String getDsIVRParceiros() {
		return this.dsIVRParceiros;
	}

	public void setDsIVRParceiros(String dsIVRParceiros) {
		this.dsIVRParceiros = dsIVRParceiros;
	}

	public String getDsIVRProdutos() {
		return this.dsIVRProdutos;
	}

	public void setDsIVRProdutos(String dsIVRProdutos) {
		this.dsIVRProdutos = dsIVRProdutos;
	}

	public String getDsIVRPromocoes() {
		return this.dsIVRPromocoes;
	}

	public void setDsIVRPromocoes(String dsIVRPromocoes) {
		this.dsIVRPromocoes = dsIVRPromocoes;
	}

	public String getDsSMSParceiros() {
		return this.dsSMSParceiros;
	}

	public void setDsSMSParceiros(String dsSMSParceiros) {
		this.dsSMSParceiros = dsSMSParceiros;
	}

	public String getDsSMSProdutos() {
		return this.dsSMSProdutos;
	}

	public void setDsSMSProdutos(String dsSMSProdutos) {
		this.dsSMSProdutos = dsSMSProdutos;
	}

	public String getDsSMSPromocoes() {
		return this.dsSMSPromocoes;
	}

	public void setDsSMSPromocoes(String dsSMSPromocoes) {
		this.dsSMSPromocoes = dsSMSPromocoes;
	}

	public String getDsSMSProtocolos() {
		return this.dsSMSProtocolos;
	}

	public void setDsSMSProtocolos(String dsSMSProtocolos) {
		this.dsSMSProtocolos = dsSMSProtocolos;
	}

	public Integer getInIVRParceiros() {
		return this.inIVRParceiros;
	}

	public void setInIVRParceiros(Integer inIVRParceiros) {
		this.inIVRParceiros = inIVRParceiros;
	}

	public Integer getInIVRProdutos() {
		return this.inIVRProdutos;
	}

	public void setInIVRProdutos(Integer inIVRProdutos) {
		this.inIVRProdutos = inIVRProdutos;
	}

	public Integer getInIVRPromocoes() {
		return this.inIVRPromocoes;
	}

	public void setInIVRPromocoes(Integer inIVRPromocoes) {
		this.inIVRPromocoes = inIVRPromocoes;
	}

	public Integer getInSMSParceiros() {
		return this.inSMSParceiros;
	}

	public void setInSMSParceiros(Integer inSMSParceiros) {
		this.inSMSParceiros = inSMSParceiros;
	}

	public Integer getInSMSProdutos() {
		return this.inSMSProdutos;
	}

	public void setInSMSProdutos(Integer inSMSProdutos) {
		this.inSMSProdutos = inSMSProdutos;
	}

	public Integer getInSMSPromocoes() {
		return this.inSMSPromocoes;
	}

	public void setInSMSPromocoes(Integer inSMSPromocoes) {
		this.inSMSPromocoes = inSMSPromocoes;
	}

	public Integer getInSMSProtocolos() {
		return this.inSMSProtocolos;
	}

	public void setInSMSProtocolos(Integer inSMSProtocolos) {
		this.inSMSProtocolos = inSMSProtocolos;
	}

	public List getNrMIN() {
		return this.nrMIN;
	}

	public void setNrMIN(List nrMIN) {
		this.nrMIN = nrMIN;
	}

	public String getDtExpIVRParceiros() {
		return this.dtExpIVRParceiros;
	}

	public void setDtExpIVRParceiros(String dtExpIVRParceiros) {
		this.dtExpIVRParceiros = dtExpIVRParceiros;
	}

	public String getDtExpIVRProdutos() {
		return this.dtExpIVRProdutos;
	}

	public void setDtExpIVRProdutos(String dtExpIVRProdutos) {
		this.dtExpIVRProdutos = dtExpIVRProdutos;
	}

	public String getDtExpIVRPromocoes() {
		return this.dtExpIVRPromocoes;
	}

	public void setDtExpIVRPromocoes(String dtExpIVRPromocoes) {
		this.dtExpIVRPromocoes = dtExpIVRPromocoes;
	}

	public String getDtExpSMSParceiros() {
		return this.dtExpSMSParceiros;
	}

	public void setDtExpSMSParceiros(String dtExpSMSParceiros) {
		this.dtExpSMSParceiros = dtExpSMSParceiros;
	}

	public String getDtExpSMSProdutos() {
		return this.dtExpSMSProdutos;
	}

	public void setDtExpSMSProdutos(String dtExpSMSProdutos) {
		this.dtExpSMSProdutos = dtExpSMSProdutos;
	}

	public String getDtExpSMSPromocoes() {
		return this.dtExpSMSPromocoes;
	}

	public void setDtExpSMSPromocoes(String dtExpSMSPromocoes) {
		this.dtExpSMSPromocoes = dtExpSMSPromocoes;
	}

	public String getDtExpSMSProtocolos() {
		return this.dtExpSMSProtocolos;
	}

	public void setDtExpSMSProtocolos(String dtExpSMSProtocolos) {
		this.dtExpSMSProtocolos = dtExpSMSProtocolos;
	}

	public String getDtInclusao() {
		return this.dtInclusao;
	}

	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getDtUltimaAlteracao() {
		return this.dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(String dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public String getSgCanal() {
		return this.sgCanal;
	}

	public void setSgCanal(String sgCanal) {
		this.sgCanal = sgCanal;
	}

	public String getSgProcedencia() {
		return this.sgProcedencia;
	}

	public void setSgProcedencia(String sgProcedencia) {
		this.sgProcedencia = sgProcedencia;
	}

	public String getDsTelmktMsgVoz() {
		return this.dsTelmktMsgVoz;
	}

	public void setDsTelmktMsgVoz(String dsTelmktMsgVoz) {
		this.dsTelmktMsgVoz = dsTelmktMsgVoz;
	}

	public String getDtExpTelmktMsgVoz() {
		return this.dtExpTelmktMsgVoz;
	}

	public void setDtExpTelmktMsgVoz(String arg) {
		this.dtExpTelmktMsgVoz = arg;
	}

	public Integer getInTelmktMsgVoz() {
		return this.inTelmktMsgVoz;
	}

	public void setInTelmktMsgVoz(Integer inTelmktMsgVoz) {
		this.inTelmktMsgVoz = inTelmktMsgVoz;
	}

}
