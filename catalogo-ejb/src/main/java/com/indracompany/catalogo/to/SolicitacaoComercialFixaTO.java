package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.json.JSONException;
import org.json.JSONObject;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.datalayer.TipoSolicitacaoComercial;

public class SolicitacaoComercialFixaTO implements Jsonable, Serializable {

    private static final long serialVersionUID = 4867654801614392423L;
    
    private String cdServicoCatalogo;
    private String cdServicoOrigem;
    private String cdSolicitacaoComercial;
    private String cdTipoServico;
    private String cdTipoSolicitacaoComercial;
    private Boolean disponivel;
    private String nmServicoCatalogo;
    private String nmServicoOrigem;
    private String nmSistema;
    private String nmSolicitacaoComercial;
    private String nmTipoServico;
    private String nmTipoSolicitacaoComercial;
    private Integer prazoMaximoAtendimento;
    private Integer prazoMaximoVigencia;
    private Long idSolicitacaoComercial;
    private Boolean inDisponivel;
    private OperacaoComercialTO operacaoComercial;
    private String cdPS;
    private String cdoperacaocomercial;
    
    public OperacaoComercialTO getOperacaoComercial() {
		return operacaoComercial;
	}

	public void setOperacaoComercial(OperacaoComercialTO operacaoComercial) {
		this.operacaoComercial = operacaoComercial;
	}

	public SolicitacaoComercialFixaTO() {}
    
    public SolicitacaoComercialFixaTO(SolicitacaoComercial ent){
    	this.setIdSolicitacaoComercial(ent.getIdSolicitacaoComercial());
    	this.setCdSolicitacaoComercial(ent.getCdSolicitacaoComercial());
    	this.setNmSolicitacaoComercial(ent.getNmSolicitacaoComercial());
    	if(ent.getTipoSolicitacaoComercial() != null){
    		TipoSolicitacaoComercial tipoSolicitacaoComercial = ent.getTipoSolicitacaoComercial();
        	this.setCdTipoSolicitacaoComercial(tipoSolicitacaoComercial.getNmTipoSolicitacaoComercial());
        	this.setNmTipoSolicitacaoComercial(tipoSolicitacaoComercial.getNmTipoSolicitacaoComercial());    		
    	}
    }

	public JSONObject toJSONObject() throws JSONException {
		JSONObject slctComercialJson = new JSONObject();
		for(Field field: SolicitacaoComercialFixaTO.class.getDeclaredFields()){
			try {
				slctComercialJson.put(field.getName(), field.get(this)) ;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return slctComercialJson;
	}
    
	public String toString(){
		String slctComercialJsonStr = "";
		try {
			slctComercialJsonStr = this.toJSONObject().toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return slctComercialJsonStr;
	}
	
    public SolicitacaoComercialFixaTO(Long idSolicitacaoComercial) {
        this.idSolicitacaoComercial = idSolicitacaoComercial;
    }

    public String getCdServicoCatalogo() {
        return cdServicoCatalogo;
    }
    
    public void setCdServicoCatalogo(String cdServicoCatalogo) {
        this.cdServicoCatalogo = cdServicoCatalogo;
    }
    
    public String getCdServicoOrigem() {
        return cdServicoOrigem;
    }
    
    public void setCdServicoOrigem(String cdServicoOrigem) {
        this.cdServicoOrigem = cdServicoOrigem;
    }
    
    public String getCdSolicitacaoComercial() {
        return cdSolicitacaoComercial;
    }
    
    public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
        this.cdSolicitacaoComercial = cdSolicitacaoComercial;
    }
    
    public String getCdTipoServico() {
        return cdTipoServico;
    }
    
    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }
    
    public String getCdTipoSolicitacaoComercial() {
        return cdTipoSolicitacaoComercial;
    }
    
    public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
        this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
    }
    
    public Boolean getDisponivel() {
        return disponivel;
    }
    
    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    public String getNmServicoCatalogo() {
        return nmServicoCatalogo;
    }
    
    public void setNmServicoCatalogo(String nmServicoCatalogo) {
        this.nmServicoCatalogo = nmServicoCatalogo;
    }
    
    public String getNmServicoOrigem() {
        return nmServicoOrigem;
    }
    
    public void setNmServicoOrigem(String nmServicoOrigem) {
        this.nmServicoOrigem = nmServicoOrigem;
    }
    
    public String getNmSistema() {
        return nmSistema;
    }
    
    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
    }
    
    public String getNmSolicitacaoComercial() {
        return nmSolicitacaoComercial;
    }
    
    public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
        this.nmSolicitacaoComercial = nmSolicitacaoComercial;
    }

    public String getNmTipoServico() {
        return nmTipoServico;
    }
    
    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }
    
    public String getNmTipoSolicitacaoComercial() {
        return nmTipoSolicitacaoComercial;
    }
    
    public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
        this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
    }
    
    public Integer getPrazoMaximoAtendimento() {
        return prazoMaximoAtendimento;
    }
    
    public void setPrazoMaximoAtendimento(Integer prazoMaximoAtendimento) {
        this.prazoMaximoAtendimento = prazoMaximoAtendimento;
    }
    
    public Integer getPrazoMaximoVigencia() {
        return prazoMaximoVigencia;
    }
    
    public void setPrazoMaximoVigencia(Integer prazoMaximoVigencia) {
        this.prazoMaximoVigencia = prazoMaximoVigencia;
    }

    public Long getIdSolicitacaoComercial() {
        return idSolicitacaoComercial;
    }

    public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
        this.idSolicitacaoComercial = idSolicitacaoComercial;
    }

    public Boolean getInDisponivel() {
        return inDisponivel;
    }

    public void setInDisponivel(Boolean inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

	public String getCdPS() {
		return cdPS;
	}

	public void setCdPS(String cdPS) {
		this.cdPS = cdPS;
	}

	public String getCdoperacaocomercial() {
		return cdoperacaocomercial;
	}

	public void setCdoperacaocomercial(String cdoperacaocomercial) {
		this.cdoperacaocomercial = cdoperacaocomercial;
	}	
    
}