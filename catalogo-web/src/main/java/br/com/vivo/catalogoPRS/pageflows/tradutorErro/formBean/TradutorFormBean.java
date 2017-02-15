package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import java.util.ArrayList;
import java.util.Collection;

import com.framework.struts.DefaultForm;

public class TradutorFormBean extends DefaultForm {
		
	private static final long serialVersionUID = 1L;
	private String txtSearch[] = new String[10];
	protected final static String NEW_MODE = "new";
	protected final static String DEL_MODE = "delete";
	protected final static String VIEW_MODE = "view";
	protected final static String EDIT_MODE = "edit";
	
	{		
		for (int i = 0; i < txtSearch.length; i++) {
			txtSearch[i] = "";
		}
	}

	public String[] getTxtSearch() {
		return txtSearch;
	}

	public void setTxtSearch(String[] txtSearch) {
		this.txtSearch = txtSearch;
	}

	public String getTxtSearch(int index) {
		return this.txtSearch[index];
	}

	public void setTxtSearch(int index, String value) {
		this.txtSearch[index] = value;
	}

	public Collection getFooterPages() {
		ArrayList<Integer> footerPages = new ArrayList<Integer>();

		for (int i = 0; i < super.getVoList()
				.getTotalPages(super.getPageSize()); i++) {
			footerPages.add(new Integer(i + 1));
		}

		return footerPages;
	}
}
