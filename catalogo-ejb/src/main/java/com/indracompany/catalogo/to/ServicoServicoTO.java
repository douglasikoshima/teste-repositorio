package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

public class ServicoServicoTO implements Serializable {

    private static final long serialVersionUID = -1037402506888570528L;
    
    private Integer idServicoServico;

    private Date dtCriacao;
    private Date dtFinal;
    private Date dtInicial;
    private Date dtUltimaAlteracao;
    private String inDisponivel;
    private String inMember;
    private String nmUsuarioAlteracao;
    private String nmUsuarioCriacao;
    private String inCriacaoCatalogo;
    private Integer idTipoRelacionamento;
    private Integer idServico1;
    private Integer idServico2;
    
    public Date getDtCriacao() {
        return dtCriacao;
    }
    
    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
    
    public Date getDtFinal() {
        return dtFinal;
    }
    
    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }
    
    public Date getDtInicial() {
        return dtInicial;
    }
    
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }
    
    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }
    
    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }
    
    public Integer getIdServicoServico() {
        return idServicoServico;
    }
    
    public void setIdServicoServico(Integer idServicoServico) {
        this.idServicoServico = idServicoServico;
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
    
    public String getInMember() {
        return inMember;
    }
    
    public void setInMember(String inMember) {
        this.inMember = inMember;
    }
    
    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }
    
    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }
    
    public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }
    
    public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }
    
    public Integer getIdServico1() {
        return idServico1;
    }
    
    public void setIdServico1(Integer idServico1) {
        this.idServico1 = idServico1;
    }
    
    public Integer getIdServico2() {
        return idServico2;
    }
    
    public void setIdServico2(Integer idServico2) {
        this.idServico2 = idServico2;
    }
    
    public Integer getIdTipoRelacionamento() {
        return idTipoRelacionamento;
    }
    
    public void setTipoRelacionamento(Integer idTipoRelacionamento) {
        this.idTipoRelacionamento = idTipoRelacionamento;
    }
}
