package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author equipe Catalogo
 *
 */
public class ImportacaoServicoFixaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ImportacaoServicoFixaTO() {}
	
	private Integer id;
	private StatusArquivoImportacaoTO statusArquivoImportacaoTO;	
	private String nmArquivo;
	private Date dtImportacao;
	private String nmUsuarioImportacao;
	private Date dtProcessamento;
	private String descErro;
	private Date dtImportacaoInicial;
	private Date dtImportacaoFinal;

	
	public ImportacaoServicoFixaTO(Integer id) {
		super();
		this.id = id;
	}
	
	public String getDescErro() {
		return descErro;
	}
	public void setDescErro(String descErro) {
		this.descErro = descErro;
	}
	public Date getDtImportacao() {
		return dtImportacao;
	}
	public void setDtImportacao(Date dtImportacao) {
		this.dtImportacao = dtImportacao;
	}
	public Date getDtProcessamento() {
		return dtProcessamento;
	}
	public void setDtProcessamento(Date dtProcessamento) {
		this.dtProcessamento = dtProcessamento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StatusArquivoImportacaoTO getStatusArquivoImportacaoTO() {
		return statusArquivoImportacaoTO;
	}
	public void setStatusArquivoImportacaoTO(
			StatusArquivoImportacaoTO statusArquivoImportacaoTO) {
		this.statusArquivoImportacaoTO = statusArquivoImportacaoTO;
	}
	public String getNmArquivo() {
		return nmArquivo;
	}
	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}
	public String getNmUsuarioImportacao() {
		return nmUsuarioImportacao;
	}
	public void setNmUsuarioImportacao(String nmUsuarioImportacao) {
		this.nmUsuarioImportacao = nmUsuarioImportacao;
	}
	public Date getDtImportacaoFinal() {
		return dtImportacaoFinal;
	}
	public void setDtImportacaoFinal(Date dtImportacaoFinal) {
		this.dtImportacaoFinal = dtImportacaoFinal;
	}
	public Date getDtImportacaoInicial() {
		return dtImportacaoInicial;
	}
	public void setDtImportacaoInicial(Date dtImportacaoInicial) {
		this.dtImportacaoInicial = dtImportacaoInicial;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{
				"nmArquivo: " + this.nmArquivo, 
				"statusArquivoImportacaoTO: " + this.statusArquivoImportacaoTO
				}, ", ");
	}

}