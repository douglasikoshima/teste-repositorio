package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.datalayer.CanalVenda;

@SuppressWarnings("serial")
public class CanalVendaTO implements Serializable, Jsonable {
	
	private Long idCanalVenda;
	private String cdCanalVenda;
	private String nmCanalVenda;
	private String dsCanalVenda;
	private String inDisponivel;
	private String inCriacaoCatalogo;
    private EpsTO epsTO;
    
    private String checked;
    private String disabled;
	
	public CanalVendaTO() {}

	public CanalVendaTO(Long id) {
		this.idCanalVenda = id;
	}

	public CanalVendaTO(Long idCanalVenda, String cdCanalVenda, String nmCanalVenda, String dsCanalVenda) {
		super();
		this.idCanalVenda = idCanalVenda;
		this.cdCanalVenda = cdCanalVenda;
		this.nmCanalVenda = nmCanalVenda;
		this.dsCanalVenda = dsCanalVenda;
	}

	public CanalVendaTO(Long idCanalVenda, String cdCanalVenda, String nmCanalVenda, String dsCanalVenda, String inDisponivel, String inCriacaoCatalogo) {
		this.idCanalVenda = idCanalVenda;
		this.cdCanalVenda = cdCanalVenda;
		this.nmCanalVenda = nmCanalVenda;
		this.dsCanalVenda = dsCanalVenda;
		this.inDisponivel = inDisponivel;
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}
	public CanalVendaTO(CanalVenda canalVenda){
		if(canalVenda != null){
			this.idCanalVenda = canalVenda.getIdCanalVenda();
			this.cdCanalVenda = canalVenda.getCdCanalVenda();
			this.nmCanalVenda = canalVenda.getNmCanalVenda();
			this.dsCanalVenda = canalVenda.getDsCanalVenda();
		}
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject canalVendaJSON = new JSONObject();
		canalVendaJSON.put("idCanalVenda", this.idCanalVenda);
		canalVendaJSON.put("cdCanalVenda", this.cdCanalVenda);
		canalVendaJSON.put("nmCanalVenda", this.nmCanalVenda);
		canalVendaJSON.put("dsCanalVenda", this.dsCanalVenda);
		return canalVendaJSON;
	}
	
	public String getCdCanalVenda() {
		return cdCanalVenda;
	}

	public void setCdCanalVenda(String cdCanalVenda) {
		this.cdCanalVenda = cdCanalVenda;
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

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmCanalVenda() {
		return nmCanalVenda;
	}

	public void setNmCanalVenda(String nmCanalVenda) {
		this.nmCanalVenda = nmCanalVenda;
	}

    public String getChecked() {
        return checked != null ? checked : "";
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public EpsTO getEpsTO() {
        return epsTO;
    }

    public void setEpsTO(EpsTO epsTO) {
        this.epsTO = epsTO;
    }

    public String getNmEps() {
        return epsTO != null ? epsTO.getNmEps() : "" ;
    }

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((cdCanalVenda == null) ? 0 : cdCanalVenda.hashCode());
		result = PRIME * result + ((dsCanalVenda == null) ? 0 : dsCanalVenda.hashCode());
		result = PRIME * result + ((epsTO == null) ? 0 : epsTO.hashCode());
		result = PRIME * result + ((idCanalVenda == null) ? 0 : idCanalVenda.hashCode());
		result = PRIME * result + ((inCriacaoCatalogo == null) ? 0 : inCriacaoCatalogo.hashCode());
		result = PRIME * result + ((inDisponivel == null) ? 0 : inDisponivel.hashCode());
		result = PRIME * result + ((nmCanalVenda == null) ? 0 : nmCanalVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CanalVendaTO other = (CanalVendaTO) obj;
		if (cdCanalVenda == null) {
			if (other.cdCanalVenda != null)
				return false;
		} else if (!cdCanalVenda.equals(other.cdCanalVenda))
			return false;
		if (dsCanalVenda == null) {
			if (other.dsCanalVenda != null)
				return false;
		} else if (!dsCanalVenda.equals(other.dsCanalVenda))
			return false;
		if (epsTO == null) {
			if (other.epsTO != null)
				return false;
		} else if (!epsTO.equals(other.epsTO))
			return false;
		if (idCanalVenda == null) {
			if (other.idCanalVenda != null)
				return false;
		} else if (!idCanalVenda.equals(other.idCanalVenda))
			return false;
		if (inCriacaoCatalogo == null) {
			if (other.inCriacaoCatalogo != null)
				return false;
		} else if (!inCriacaoCatalogo.equals(other.inCriacaoCatalogo))
			return false;
		if (inDisponivel == null) {
			if (other.inDisponivel != null)
				return false;
		} else if (!inDisponivel.equals(other.inDisponivel))
			return false;
		if (nmCanalVenda == null) {
			if (other.nmCanalVenda != null)
				return false;
		} else if (!nmCanalVenda.equals(other.nmCanalVenda))
			return false;
		return true;
	}
	
}