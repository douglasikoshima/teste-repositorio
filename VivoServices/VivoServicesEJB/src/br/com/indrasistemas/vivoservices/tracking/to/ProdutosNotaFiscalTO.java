package br.com.indrasistemas.vivoservices.tracking.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ProdutosNotaFiscalTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	private String idItemOrdem;

	private String cdProduto;

	private String dsItemOrdem;

	private String vlItem;

	private String qtItem;

	public String getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}

	public String getDsItemOrdem() {
		return dsItemOrdem;
	}

	public void setDsItemOrdem(String dsItemOrdem) {
		this.dsItemOrdem = dsItemOrdem;
	}

	public String getIdItemOrdem() {
		return idItemOrdem;
	}

	public void setIdItemOrdem(String idItemOrdem) {
		this.idItemOrdem = idItemOrdem;
	}

	public String getQtItem() {
		return qtItem;
	}

	public void setQtItem(String qtItem) {
		this.qtItem = qtItem;
	}

	public String getVlItem() {
		return vlItem;
	}

	public void setVlItem(String vlItem) {
		this.vlItem = vlItem;
	}
}
