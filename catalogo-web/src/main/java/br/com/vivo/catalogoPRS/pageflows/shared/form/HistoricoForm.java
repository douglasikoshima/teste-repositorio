package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

public class HistoricoForm extends ValidatorActionForm  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long cdHistorico;
	private String login;
	private String dataScript;
	private String tabela;
	private String dsScript;
	private Long[] ids;
	private String pageNumber;
	
	private List <HistoricoVO> listaHistoricoVO;
	
	public Long[] getIds() {
		return ids;
	}
	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	public String getDataScript() {
		return dataScript;
	}
	public void setDataScript(String dataScript) {
			this.dataScript = dataScript;
	}
	public String getDsScript() {
		return dsScript;
	}
	public void setDsScript(String dsScript) {
		this.dsScript = dsScript;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setId(String id) {
		this.cdHistorico = Long.valueOf(id);
	}
	public String getTabela() {
		return tabela;
	}
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	public Long getCdHistorico() {
		return cdHistorico;
	}
	public void setCdHistorico(Long cdHistorico) {
		this.cdHistorico = cdHistorico;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<HistoricoVO> getListaHistoricoVO() {
		return listaHistoricoVO;
	}
	public void setListaHistoricoVO(List<HistoricoVO> listaHistoricoVO) {
		this.listaHistoricoVO = listaHistoricoVO;
	}
}
