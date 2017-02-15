package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoTO;

public class RelacionamentoServicoFixaForm extends ValidatorActionForm  implements java.io.Serializable  {

	private static final long serialVersionUID = 9132003823336130959L;
	
    private String idServicoSearch;
    private Integer idSistemaSearch;
    private String idRelacionamento;
    private String idTipoRelacionamento;
    private String cdServico;
    private String cdPS;
    private String nomeDoServicoOrigem;
    private String nmComercial;
    
    private Integer idTipoServico;
    private String nmTipoServico;
    
    private Integer[] idServicoSelecionadoList;
    
    public RelacionamentoServicoFixaTO buildTO() {
        RelacionamentoServicoFixaTO to = new RelacionamentoServicoFixaTO();
        if (!StringUtils.isBlank(this.idServicoSearch)) {
            to.setIdServicoSearch(Integer.valueOf(this.idServicoSearch));
        }
        return to;
    }

    public List<ServicoServicoTO> buildTOListReccord() {
        List<ServicoServicoTO> toList = new ArrayList<ServicoServicoTO>();
        for (Integer idServico2 : idServicoSelecionadoList) {
            ServicoServicoTO to = this.getBasicServicoServicoTO();
            to.setIdServico2(idServico2);
            toList.add(to);
        }
        return toList;
    }

    private ServicoServicoTO getBasicServicoServicoTO() {
        ServicoServicoTO to = new ServicoServicoTO();
        to.setDtCriacao(new Date());
        to.setDtFinal(null);
        to.setDtInicial(null);
        to.setDtUltimaAlteracao(new Date());
        to.setIdServico1(Integer.valueOf(this.idServicoSearch));
        to.setInCriacaoCatalogo("S");
        to.setInDisponivel("S");
        to.setNmUsuarioAlteracao("");
        to.setNmUsuarioCriacao("");
        to.setTipoRelacionamento(Integer.valueOf(this.idTipoRelacionamento));
        return to;
    }

    public ServicoFixaTO buildTOServicoFixa(){
        ServicoFixaTO to = new ServicoFixaTO();
        to.setCdServico(this.cdServico);
        to.setCdPS(this.cdPS);
        to.setNomeDoServicoOrigem(this.nomeDoServicoOrigem);
        to.setNmComercial(this.nmComercial);
        to.setIdTipoServico(this.idTipoServico);
        to.setIdSistema(this.idSistemaSearch);
        return to;
    }
	
    public void clearSearchServicoData() {
        this.idTipoRelacionamento = null;
        this.cdPS = null;
        this.cdServico = null;
        this.nomeDoServicoOrigem = null;
        this.nmComercial = null;
        this.idTipoServico = null;
    }
    
	public String getCdPS() {
        return cdPS;
    }

    public void setCdPS(String cdPS) {
        this.cdPS = cdPS;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getNmComercial() {
        return nmComercial;
    }

    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
    }

    public String getNmTipoServico() {
        return nmTipoServico;
    }

    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }

    public String getNomeDoServicoOrigem() {
        return nomeDoServicoOrigem;
    }

    public void setNomeDoServicoOrigem(String nomeDoServicoOrigem) {
        this.nomeDoServicoOrigem = nomeDoServicoOrigem;
    }

    public String getIdRelacionamento() {
        return idRelacionamento;
    }

    public void setIdRelacionamento(String idRelacionamento) {
        this.idRelacionamento = idRelacionamento;
    }

    public String getIdServicoSearch() {
        return idServicoSearch;
    }

    public void setIdServicoSearch(String idServicoSearch) {
        this.idServicoSearch = idServicoSearch;
    }

    public String getIdTipoRelacionamento() {
        return idTipoRelacionamento;
    }

    public void setIdTipoRelacionamento(String idTipoRelacionamento) {
        this.idTipoRelacionamento = idTipoRelacionamento;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public Integer[] getIdServicoSelecionadoList() {
        return idServicoSelecionadoList;
    }

    public void setIdServicoSelecionadoList(Integer[] idServicoSelecionadoList) {
        this.idServicoSelecionadoList = idServicoSelecionadoList;
    }

	public Integer getIdSistemaSearch() {
		return idSistemaSearch;
	}

	public void setIdSistemaSearch(Integer idSistemaSearch) {
		this.idSistemaSearch = idSistemaSearch;
	}
}
