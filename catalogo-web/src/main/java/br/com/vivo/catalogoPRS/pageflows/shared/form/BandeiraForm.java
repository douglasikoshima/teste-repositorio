package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class BandeiraForm extends ValidatorActionForm implements java.io.Serializable {

		private static final long serialVersionUID = 1L;
		private Long paginaSolicitada;
		private Boolean cadastrar;
		
		private String nmBandeiraSearch;
		private String sgBandeiraSAPSearch;
		
		private Integer idBandeira;
		private String sgBandeiraSAP;
		private String nmBandeira;
		private FormFile imgBandeira;
		private String urlImagem;
		private String valorMinimo;
		private String cdInstituicaoFinanceira;	
		
		public Boolean getCadastrar() {
			return cadastrar;
		}
		public void setCadastrar(Boolean cadastrar) {
			this.cadastrar = cadastrar;
		}
		public Integer getIdBandeira() {
			return idBandeira;
		}
		public void setIdBandeira(Integer idBandeira) {
			this.idBandeira = idBandeira;
		}
		public FormFile getImgBandeira() {
			return imgBandeira;
		}
		public void setImgBandeira(FormFile imgBandeira) {
			this.imgBandeira = imgBandeira;
		}
		public String getUrlImagem() {
			return urlImagem;
		}
		public void setUrlImagem(String urlImagem) {
			this.urlImagem = urlImagem;
		}
		public String getNmBandeira() {
			return nmBandeira;
		}
		public void setNmBandeira(String nmBandeira) {
			this.nmBandeira = nmBandeira;
		}
		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}
		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}
		public String getSgBandeiraSAP() {
			return sgBandeiraSAP;
		}
		public void setSgBandeiraSAP(String sgBandeiraSAP) {
			this.sgBandeiraSAP = sgBandeiraSAP;
		}
		public String getNmBandeiraSearch() {
			return nmBandeiraSearch;
		}
		public void setNmBandeiraSearch(String nmBandeiraSearch) {
			this.nmBandeiraSearch = nmBandeiraSearch;
		}
		public String getSgBandeiraSAPSearch() {
			return sgBandeiraSAPSearch;
		}
		public void setSgBandeiraSAPSearch(String sgBandeiraSAPSearch) {
			this.sgBandeiraSAPSearch = sgBandeiraSAPSearch;
		}
		public String getValorMinimo() {
			return valorMinimo;
		}
		public void setValorMinimo(String valorMinimo) {
			this.valorMinimo = valorMinimo;
		}
		public String getCdInstituicaoFinanceira() {
			return cdInstituicaoFinanceira;
		}
		public void setCdInstituicaoFinanceira(String cdInstituicaoFinanceira) {
			this.cdInstituicaoFinanceira = cdInstituicaoFinanceira;
		}
		
}

