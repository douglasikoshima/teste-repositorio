package com.indracompany.catalogo.to;

import java.util.List;

public class IndicadorComercialDocumentoTO {

	private Long idIndicadorComercial;
	private String nmIndicadorComercial;
	private List<TipoDocumentoTO> tipoDocumentoTOList;
	
	public IndicadorComercialDocumentoTO() {
		super();
	}
	public IndicadorComercialDocumentoTO(Long idIndicadorComercial) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
	}
	public IndicadorComercialDocumentoTO(Long idIndicadorComercial, String nmIndicadorComercial) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
		this.nmIndicadorComercial = nmIndicadorComercial;
	}
	public IndicadorComercialDocumentoTO(Long idIndicadorComercial, String nmIndicadorComercial, List<TipoDocumentoTO> tipoDocumentoTOList) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
		this.nmIndicadorComercial = nmIndicadorComercial;
		this.tipoDocumentoTOList = tipoDocumentoTOList;
	}
	public Long getIdIndicadorComercial() {
		return idIndicadorComercial;
	}
	public void setIdIndicadorComercial(Long idIndicadorComercial) {
		this.idIndicadorComercial = idIndicadorComercial;
	}
	public String getNmIndicadorComercial() {
		return nmIndicadorComercial;
	}
	public void setNmIndicadorComercial(String nmIndicadorComercial) {
		this.nmIndicadorComercial = nmIndicadorComercial;
	}
	public List<TipoDocumentoTO> getTipoDocumentoTOList() {
		return tipoDocumentoTOList;
	}
	public void setTipoDocumentoTOList(List<TipoDocumentoTO> tipoDocumentoTOList) {
		this.tipoDocumentoTOList = tipoDocumentoTOList;
	}
}
