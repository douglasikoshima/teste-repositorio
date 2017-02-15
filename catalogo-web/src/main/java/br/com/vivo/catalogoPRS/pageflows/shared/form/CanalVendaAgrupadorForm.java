package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class CanalVendaAgrupadorForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = 5230163792375151391L;
	
	private Integer idEps;
	private String nmEps;
	
	private Long idCanalVendaAlternarIndisponivel;
	
	private Long idCanalVenda;
	private String cdCanalVenda;
	private String nmCanalVenda;
	private String dsCanalVenda;
	private String inDisponivel;
	private String inCriacaoCatalogo;
	private Integer idEpsSearch;
	private Long[] idCanalVendaCheckedList;
	
	private String cdCanalVendaSearch;
	private String nmCanalVendaSearch;
	private String inDisponivelSearch;
	private String inCriacaoCatalogoSearch;
	
	private String canalVendaAgrupadorFormContentGroup;
	
	private Boolean tabelaAgrupadoresAtiva = Boolean.FALSE;
	
	public Boolean getTabelaAgrupadoresAtiva() {
		return tabelaAgrupadoresAtiva;
	}
	public void setTabelaAgrupadoresAtiva(Boolean tabelaAgrupadoresAtiva) {
		this.tabelaAgrupadoresAtiva = tabelaAgrupadoresAtiva;
	}
	public Long[] getIdCanalVendaCheckedList() {
		return idCanalVendaCheckedList;
	}
	public void setIdCanalVendaCheckedList(Long[] idCanalVendaCheckedList) {
		this.idCanalVendaCheckedList = idCanalVendaCheckedList;
	}
	public String getCdCanalVenda() {
		return cdCanalVenda;
	}
	public void setCdCanalVenda(String cdCanalVenda) {
		this.cdCanalVenda = cdCanalVenda;
	}
	public String getCdCanalVendaSearch() {
		return cdCanalVendaSearch;
	}
	public void setCdCanalVendaSearch(String cdCanalVendaSearch) {
		this.cdCanalVendaSearch = cdCanalVendaSearch;
	}
	public String getDsCanalVenda() {
		return dsCanalVenda;
	}
	public void setDsCanalVenda(String dsCanalVenda) {
		this.dsCanalVenda = dsCanalVenda;
	}
	public Long getIdCanalVenda() {
		return idCanalVenda;
	}
	public void setIdCanalVenda(Long idCanalVenda) {
		this.idCanalVenda = idCanalVenda;
	}
	public Long getIdCanalVendaAlternarIndisponivel() {
		return idCanalVendaAlternarIndisponivel;
	}
	public void setIdCanalVendaAlternarIndisponivel(
			Long idCanalVendaAlternarIndisponivel) {
		this.idCanalVendaAlternarIndisponivel = idCanalVendaAlternarIndisponivel;
	}
	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}
	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}
	public String getInCriacaoCatalogoSearch() {
		return inCriacaoCatalogoSearch;
	}
	public void setInCriacaoCatalogoSearch(String inCriacaoCatalogoSearch) {
		this.inCriacaoCatalogoSearch = inCriacaoCatalogoSearch;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getInDisponivelSearch() {
		return inDisponivelSearch;
	}
	public void setInDisponivelSearch(String inDisponivelSearch) {
		this.inDisponivelSearch = inDisponivelSearch;
	}
	public String getNmCanalVenda() {
		return nmCanalVenda;
	}
	public void setNmCanalVenda(String nmCanalVenda) {
		this.nmCanalVenda = nmCanalVenda;
	}
	public String getNmCanalVendaSearch() {
		return nmCanalVendaSearch;
	}
	public void setNmCanalVendaSearch(String nmCanalVendaSearch) {
		this.nmCanalVendaSearch = nmCanalVendaSearch;
	}
    public Integer getIdEpsSearch() {
        return idEpsSearch;
    }
    public void setIdEpsSearch(Integer idEpsSearch) {
        this.idEpsSearch = idEpsSearch;
    }	
	public Integer getIdEps() {
		return idEps;
	}
	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
	}
	public String getNmEps() {
		return nmEps;
	}
	public void setNmEps(String nmEps) {
		this.nmEps = nmEps;
	}
	public String getCanalVendaAgrupadorFormContentGroup() {
		return canalVendaAgrupadorFormContentGroup;
	}
	public void setCanalVendaAgrupadorFormContentGroup(String canalVendaAgrupadorFormContentGroup) {
		this.canalVendaAgrupadorFormContentGroup = canalVendaAgrupadorFormContentGroup;
	}
}
