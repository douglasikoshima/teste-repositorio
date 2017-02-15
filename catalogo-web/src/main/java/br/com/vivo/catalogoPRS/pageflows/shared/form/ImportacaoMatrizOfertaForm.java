package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class ImportacaoMatrizOfertaForm extends ValidatorActionForm  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private FormFile nmArquivo;
	private boolean isValorSimCard;
	private String valorSimCard;
	private Integer idCanalAtendimento;
	
	public String getValorSimCard() {
		return valorSimCard;
	}
	public void setValorSimCard(String valorSimCard) {
		this.valorSimCard = valorSimCard;
	}
	public FormFile getNmArquivo() {
		return nmArquivo;
	}
	public void setNmArquivo(FormFile nmArquivo) {
		this.nmArquivo = nmArquivo;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public boolean getIsValorSimCard() {
		return isValorSimCard;
	}
	public void setIsValorSimCard(boolean isValorSimCard) {
		this.isValorSimCard = isValorSimCard;
	}
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
}
