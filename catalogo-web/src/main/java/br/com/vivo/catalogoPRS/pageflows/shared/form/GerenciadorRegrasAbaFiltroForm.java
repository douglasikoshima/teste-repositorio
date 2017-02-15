package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

public class GerenciadorRegrasAbaFiltroForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = -4057155030618881406L;
	
	private Map<String,String> servicoIndicadorComercialMap;
	private Integer idCanalAtendimento;
	private String gerenciadorRegrasConfiguracaoTOJSONNew;
	
	private Long idIndicadorComercial; 
	private Long[] idTipoDocumentoCheckList;

	public String getGerenciadorRegrasConfiguracaoTOJSONNew() {
		return gerenciadorRegrasConfiguracaoTOJSONNew;
	}
	public void setGerenciadorRegrasConfiguracaoTOJSONNew(
			String gerenciadorRegrasConfiguracaoTOJSONNew) {
		this.gerenciadorRegrasConfiguracaoTOJSONNew = gerenciadorRegrasConfiguracaoTOJSONNew;
	}
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public Map<String, String> getServicoIndicadorComercialMap() {
		return servicoIndicadorComercialMap;
	}
	public void setServicoIndicadorComercialMap(
			Map<String, String> servicoIndicadorComercialMap) {
		this.servicoIndicadorComercialMap = servicoIndicadorComercialMap;
	}
	public Long getIdIndicadorComercial() {
		return idIndicadorComercial;
	}
	public void setIdIndicadorComercial(Long idIndicadorComercial) {
		this.idIndicadorComercial = idIndicadorComercial;
	}
	public Long[] getIdTipoDocumentoCheckList() {
		return idTipoDocumentoCheckList;
	}
	public void setIdTipoDocumentoCheckList(Long[] idTipoDocumentoCheckList) {
		this.idTipoDocumentoCheckList = idTipoDocumentoCheckList;
	}	

}
