package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

public class HistoricoVO extends ValueObject {
	private static final long serialVersionUID = 1L;

	private Long cdHistorico;
	
	private String login;

	private String dataScript;

	private String tabela;

	private String dsScript;
	
	private Long[] ids;
	
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

	public HistoricoVO(String id) {
		super(id);
	}

	public HistoricoVO() {
		super();
	}

	@Override
	public void setId(String id) {
		this.cdHistorico = Long.valueOf(id);
	}
	
	@Override
	public void setId() {
		
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


}
