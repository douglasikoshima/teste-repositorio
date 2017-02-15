package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "SERVICO", schema = "CATALOGOPRS_OW")
@NamedQueries( {
        @NamedQuery(name = "Servico.searchServicoLinha", query = "select s from Servico s inner join s.sistemaServico sis inner join sis.complementoLegado cl where s.tipoServico.cdTipoServico = '1' and s.tipoServico.inFixa = 'S' and s.sistemaServico.sistema.idSistema = 8 and s.inDisponivel = 'S' and s.inFixa = 'S'"),
        @NamedQuery(name = "Servico.carregarServicoFixaPlanoList", query = "select ss.servico2 from ServicoServico ss inner join ss.servico2.sistemaServico.complementoLegado cl where ss.tipoRelacionamento.sgTipoRelacionamento = 'CL' and ss.servico1.idServico = :idServicoFixa and ss.servico2.inDisponivel = 'S' and ss.servico2.tipoServico.cdTipoServico = 20 and ss.servico2.tipoServico.inFixa = 'S' and ss.servico2.sistemaServico.sistema.idSistema = 8"),
        @NamedQuery(name = "Servico.findServicoByIdTipoServico", query = "select new Servico(s.idServico, s.cdServico, s.descricao, s.nmComercial, s.inFixa, s.tipoServico, s.sistemaServico) from Servico s where s.tipoServico.idTipoServico = :idTipoServico and s.tipoServico.inFixa = 'S' and s.sistemaServico.sistema.idSistema = 8 and s.inFixa = 'S' order by s.nmComercial")})
public class Servico implements Serializable {

    public Servico() {
    }

    public Servico(Integer idServico) {
        this.idServico = idServico;
    }

    public Servico(Integer idServico, String cdServico, String descricao, String nomeComercial, String inFixa, TipoServico tipoServico,
            SistemaServico sistemaServico) {
        this.idServico = idServico;
        this.cdServico = cdServico;
        this.descricao = descricao;
        this.nmComercial = nomeComercial;
        this.inFixa = inFixa;
        this.tipoServico = tipoServico;
        this.sistemaServico = sistemaServico;
    }

    @Id
    @Column(name = "IDSERVICO", nullable = false)
    private Integer idServico;

    @Column(name = "ATIVAWIFI", length = 1)
    private String ativaWifi;

    @Column(name = "CDANATEL")
    private String cdAnatel;

    @Column(name = "CDPERCONTRATO")
    private String cdPerContrato;

    @Column(name = "CDSERVICO")
    private String cdServico;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DIASEXPIRACAO")
    private Integer diasExpiracao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTFINAL")
    private Date dtFinal;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTINICIAL", nullable = false)
    private Date dtInicial;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "IDTPSERVICOCATALOGO")
    private Integer idTpServicoCatalogo;

    @Column(name = "INDALTERACAOLEGADO")
    private String indAlteracaoLegado;

    @Column(name = "INDAPROVISIONA")
    private String indAprovisiona;

    @Column(name = "INDEXPIRACAOAUTOMATICA")
    private String indExpiracaoAutomatica;

    @Column(name = "INDISPGESTAO")
    private String inDispGestao;

    @Column(name = "INDISPONIVEL")
    private String inDisponivel;

    @Column(name = "INDISPONIVELLEGADO", nullable = false)
    private String inDisponivelLegado;

    @Column(name = "INDRESTRICAOEQUIP")
    private String indRestricaoEquip;

    @Column(name = "INPLANO")
    private String inPlano;

    @Column(name = "INSIMULACAO")
    private String inSimulacao;

    @Column(name = "NMCOMERCIAL", nullable = false)
    private String nmComercial;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

    @Column(name = "QTDMAXATIVCATALOGO")
    private Integer qtdMaxAtivCatalogo;

    @Column(name = "QTDMAXATIVLEGADO")
    private Integer qtdMaxAtivLegado;

    @Column(name = "QTDMINATIVCATALOGO")
    private Integer qtdMinAtivCatalogo;

    @Column(name = "QTDMINATIVLEGADO")
    private Integer qtdMinAtivLegado;

    @Column(name = "QTPERCONTRATO")
    private Integer qtPerContrato;

    @Column(name = "QTPERMINIMOCONTR")
    private Integer qtPerMinimoContr;

    @Column(name = "TPTARIFA")
    private String tpTarifa;

    @Column(name = "VALOR")
    private Float valor;

    @Column(name = "INVENDAISOLADA")
    private String inVendaIsolada;

    @Column(name = "INPOSFACTIBILIDADE")
    private String inPosFactibilidade;

    @Column(name = "INPREFACTIBILIDADE")
    private String inPreFactibilidade;

    @Column(name = "CDSISCOM")
    private String cdSiscom;

    @Column(name = "QTPERCENTUALAUDITORIA")
    private BigDecimal qtPercentualAuditoria;

    @Column(name = "NUORDEMATENDIMENTO")
    private Long nuOrdemAtendimento;

    @Column(name = "INFIXA")
    private String inFixa;

    @Column(name = "IN4G")
    private String in4g;

    @Column(name = "INALTERACAOCATALOGO")
    private String inAlteracaoCatalogo;

    // bi-directional many-to-one association to Acessoservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<AcessoServico> acessoServicoList;

    // bi-directional many-to-one association to Comboofertaplanoservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ComboOfertaPlanoServico> comboOfertaPlanoservicoList;

    // bi-directional many-to-one association to Comboofertaplanoservicoitem
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ComboOfertaPlanoServicoItem> comboOfertaplanoServicoItemList;

    // bi-directional many-to-one association to Disponibilidadeservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<DisponibilidadeServico> disponibilidadeservicoList;

    // bi-directional many-to-one association to Planoservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<PlanoServico> planoServicoList;

    // bi-directional many-to-one association to Produtoservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ProdutoServico> produtoServicoList;

    // bi-directional many-to-one association to Restricao
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<Restricao> restricaoList;

    // bi-directional many-to-one association to Restricaoitem
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<RestricaoItem> restricaoItemList;

    // bi-directional many-to-one association to Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORIALEGADO", nullable = false)
    private Categoria categoria1;

    // bi-directional many-to-one association to Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORIACATALOGO")
    private Categoria categoria2;

    // bi-directional many-to-one association to Tiposervico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDTIPOSERVICO")
    private TipoServico tipoServico;

    // bi-directional many-to-one association to Servicocsa
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoCSA> servicoCSAList;

    // bi-directional many-to-one association to Servicofuncionalidade
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoFuncionalidade> servicoFuncionalidadeList;

    // bi-directional many-to-one association to Servicoofertaservico
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoOfertaServico> servicoOfertaServicoList;

    // bi-directional many-to-one association to Servicoplataforma
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoPlataforma> servicoPlataformaList;

    // bi-directional many-to-one association to Servicorestricoe
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoRestricoes> servicoRestricoesList;

    // bi-directional many-to-one association to Servicotarifa
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    @OrderBy("precoBase DESC")
    private List<ServicoTarifa> servicoTarifaList;

    // bi-directional many-to-one association to Servicotecnologia
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoTecnologia> servicoTecnologiaList;

    // bi-directional many-to-one association to Servicotipoassinatura
    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoTipoAssinatura> servicoTipoAssinaturaList;

    // bi-directional many-to-one association to Sistemaservico
    @OneToOne(mappedBy = "servico", fetch = FetchType.LAZY)
    private SistemaServico sistemaServico;

    @OneToOne(mappedBy = "servico", fetch = FetchType.LAZY)
    private ServicoCategoriaScore servicoCategoriaScore;

    @OneToOne(mappedBy = "servico", cascade = CascadeType.ALL)
    private EspServicoDesconto espServicoDesconto;

    @OneToOne(mappedBy = "servico", cascade = CascadeType.ALL)
    private EspServicoPacote espServicoPacote;

    @OneToOne(mappedBy = "servico", cascade = CascadeType.ALL)
    private EspServicoPlanoMinuto espServicoPlanoMinuto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCATEGORIA")
    private Categoria categoria;

    @OneToMany(mappedBy = "servico", fetch = FetchType.LAZY)
    private List<ServicoUfRestricao> servicoUfRestricaoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPOLITICAPRECIFICACAO")
    private PoliticaPrecificacao politicaPrecificacao;
    
    @ManyToMany
    @JoinTable(name = "SERVICOTECNOLOGIA", joinColumns = @JoinColumn(name = "IDSERVICO"), inverseJoinColumns = @JoinColumn(name = "IDTECNOLOGIA"))
    private List<Tecnologia> tecnologiaList;

    @ManyToMany
    @JoinTable(name = "SERVICOSUBSEGMENTO", joinColumns = @JoinColumn(name = "IDSERVICO"), inverseJoinColumns = @JoinColumn(name = "IDSUBSEGMENTO"))
    private List<Segmento> segmentoList;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "servico")
    private ServicoSegmento servicoSegmento;
    
    public PoliticaPrecificacao getPoliticaPrecificacao() {
        return politicaPrecificacao;
    }

    public void setPoliticaPrecificacao(PoliticaPrecificacao politicaPrecificacao) {
        this.politicaPrecificacao = politicaPrecificacao;
    }

    public List<AcessoServico> getAcessoServicoList() {
        return acessoServicoList;
    }

    public void setAcessoServicoList(List<AcessoServico> acessoServicoList) {
        this.acessoServicoList = acessoServicoList;
    }

    public String getAtivaWifi() {
        return ativaWifi;
    }

    public void setAtivaWifi(String ativaWifi) {
        this.ativaWifi = ativaWifi;
    }

    public Categoria getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(Categoria categoria1) {
        this.categoria1 = categoria1;
    }

    public Categoria getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(Categoria categoria2) {
        this.categoria2 = categoria2;
    }

    public String getCdAnatel() {
        return cdAnatel;
    }

    public void setCdAnatel(String cdAnatel) {
        this.cdAnatel = cdAnatel;
    }

    public String getCdPerContrato() {
        return cdPerContrato;
    }

    public void setCdPerContrato(String cdPerContrato) {
        this.cdPerContrato = cdPerContrato;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public String getCdSiscom() {
        return cdSiscom;
    }

    public void setCdSiscom(String cdSiscom) {
        this.cdSiscom = cdSiscom;
    }

    public BigDecimal getQtPercentualAuditoria() {
        return qtPercentualAuditoria;
    }

    public void setQtPercentualAuditoria(BigDecimal qtPercentualAuditoria) {
        this.qtPercentualAuditoria = qtPercentualAuditoria;
    }

    public List<ComboOfertaPlanoServicoItem> getComboOfertaplanoServicoItemList() {
        return comboOfertaplanoServicoItemList;
    }

    public void setComboOfertaplanoServicoItemList(List<ComboOfertaPlanoServicoItem> comboOfertaplanoServicoItemList) {
        this.comboOfertaplanoServicoItemList = comboOfertaplanoServicoItemList;
    }

    public List<ComboOfertaPlanoServico> getComboOfertaPlanoservicoList() {
        return comboOfertaPlanoservicoList;
    }

    public void setComboOfertaPlanoservicoList(List<ComboOfertaPlanoServico> comboOfertaPlanoservicoList) {
        this.comboOfertaPlanoservicoList = comboOfertaPlanoservicoList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDiasExpiracao() {
        return diasExpiracao;
    }

    public void setDiasExpiracao(Integer diasExpiracao) {
        this.diasExpiracao = diasExpiracao;
    }

    public List<DisponibilidadeServico> getDisponibilidadeservicoList() {
        return disponibilidadeservicoList;
    }

    public void setDisponibilidadeservicoList(List<DisponibilidadeServico> disponibilidadeservicoList) {
        this.disponibilidadeservicoList = disponibilidadeservicoList;
    }

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

    public EspServicoDesconto getEspServicoDesconto() {
        return espServicoDesconto;
    }

    public void setEspServicoDesconto(EspServicoDesconto espServicoDesconto) {
        this.espServicoDesconto = espServicoDesconto;
    }

    public EspServicoPacote getEspServicoPacote() {
        return espServicoPacote;
    }

    public void setEspServicoPacote(EspServicoPacote espServicoPacote) {
        this.espServicoPacote = espServicoPacote;
    }

    public EspServicoPlanoMinuto getEspServicoPlanoMinuto() {
        return espServicoPlanoMinuto;
    }

    public void setEspServicoPlanoMinuto(EspServicoPlanoMinuto espServicoPlanoMinuto) {
        this.espServicoPlanoMinuto = espServicoPlanoMinuto;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdTpServicoCatalogo() {
        return idTpServicoCatalogo;
    }

    public void setIdTpServicoCatalogo(Integer idTpServicoCatalogo) {
        this.idTpServicoCatalogo = idTpServicoCatalogo;
    }

    public String getIndAlteracaoLegado() {
        return indAlteracaoLegado;
    }

    public void setIndAlteracaoLegado(String indAlteracaoLegado) {
        this.indAlteracaoLegado = indAlteracaoLegado;
    }

    public String getIndAprovisiona() {
        return indAprovisiona;
    }

    public void setIndAprovisiona(String indAprovisiona) {
        this.indAprovisiona = indAprovisiona;
    }

    public String getIndExpiracaoAutomatica() {
        return indExpiracaoAutomatica;
    }

    public void setIndExpiracaoAutomatica(String indExpiracaoAutomatica) {
        this.indExpiracaoAutomatica = indExpiracaoAutomatica;
    }

    public String getInDispGestao() {
        return inDispGestao;
    }

    public void setInDispGestao(String inDispGestao) {
        this.inDispGestao = inDispGestao;
    }

    public String getInDisponivel() {
        return inDisponivel;
    }

    public void setInDisponivel(String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    public String getInDisponivelLegado() {
        return inDisponivelLegado;
    }

    public void setInDisponivelLegado(String inDisponivelLegado) {
        this.inDisponivelLegado = inDisponivelLegado;
    }

    public String getIndRestricaoEquip() {
        return indRestricaoEquip;
    }

    public void setIndRestricaoEquip(String indRestricaoEquip) {
        this.indRestricaoEquip = indRestricaoEquip;
    }

    public String getInPlano() {
        return inPlano;
    }

    public void setInPlano(String inPlano) {
        this.inPlano = inPlano;
    }

    public String getInPosFactibilidade() {
        return inPosFactibilidade;
    }

    public void setInPosFactibilidade(String inPosFactibilidade) {
        this.inPosFactibilidade = inPosFactibilidade;
    }

    public String getInPreFactibilidade() {
        return inPreFactibilidade;
    }

    public void setInPreFactibilidade(String inPreFactibilidade) {
        this.inPreFactibilidade = inPreFactibilidade;
    }

    public String getInSimulacao() {
        return inSimulacao;
    }

    public void setInSimulacao(String inSimulacao) {
        this.inSimulacao = inSimulacao;
    }

    public String getInVendaIsolada() {
        return inVendaIsolada;
    }

    public void setInVendaIsolada(String inVendaIsolada) {
        this.inVendaIsolada = inVendaIsolada;
    }

    public String getNmComercial() {
        return nmComercial;
    }

    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
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

    public Long getNuOrdemAtendimento() {
        return nuOrdemAtendimento;
    }

    public void setNuOrdemAtendimento(Long nuOrdemAtendimento) {
        this.nuOrdemAtendimento = nuOrdemAtendimento;
    }

    public List<PlanoServico> getPlanoServicoList() {
        return planoServicoList;
    }

    public void setPlanoServicoList(List<PlanoServico> planoServicoList) {
        this.planoServicoList = planoServicoList;
    }

    public List<ProdutoServico> getProdutoServicoList() {
        return produtoServicoList;
    }

    public void setProdutoServicoList(List<ProdutoServico> produtoServicoList) {
        this.produtoServicoList = produtoServicoList;
    }

    public Integer getQtdMaxAtivCatalogo() {
        return qtdMaxAtivCatalogo;
    }

    public void setQtdMaxAtivCatalogo(Integer qtdMaxAtivCatalogo) {
        this.qtdMaxAtivCatalogo = qtdMaxAtivCatalogo;
    }

    public Integer getQtdMaxAtivLegado() {
        return qtdMaxAtivLegado;
    }

    public void setQtdMaxAtivLegado(Integer qtdMaxAtivLegado) {
        this.qtdMaxAtivLegado = qtdMaxAtivLegado;
    }

    public Integer getQtdMinAtivCatalogo() {
        return qtdMinAtivCatalogo;
    }

    public void setQtdMinAtivCatalogo(Integer qtdMinAtivCatalogo) {
        this.qtdMinAtivCatalogo = qtdMinAtivCatalogo;
    }

    public Integer getQtdMinAtivLegado() {
        return qtdMinAtivLegado;
    }

    public void setQtdMinAtivLegado(Integer qtdMinAtivLegado) {
        this.qtdMinAtivLegado = qtdMinAtivLegado;
    }

    public Integer getQtPerContrato() {
        return qtPerContrato;
    }

    public void setQtPerContrato(Integer qtPerContrato) {
        this.qtPerContrato = qtPerContrato;
    }

    public Integer getQtPerMinimoContr() {
        return qtPerMinimoContr;
    }

    public void setQtPerMinimoContr(Integer qtPerMinimoContr) {
        this.qtPerMinimoContr = qtPerMinimoContr;
    }

    public List<RestricaoItem> getRestricaoItemList() {
        return restricaoItemList;
    }

    public void setRestricaoItemList(List<RestricaoItem> restricaoItemList) {
        this.restricaoItemList = restricaoItemList;
    }

    public List<Restricao> getRestricaoList() {
        return restricaoList;
    }

    public void setRestricaoList(List<Restricao> restricaoList) {
        this.restricaoList = restricaoList;
    }

    public ServicoCategoriaScore getServicoCategoriaScore() {
        return servicoCategoriaScore;
    }

    public void setServicoCategoriaScore(ServicoCategoriaScore servicoCategoriaScore) {
        this.servicoCategoriaScore = servicoCategoriaScore;
    }

    public List<ServicoCSA> getServicoCSAList() {
        return servicoCSAList;
    }

    public void setServicoCSAList(List<ServicoCSA> servicoCSAList) {
        this.servicoCSAList = servicoCSAList;
    }

    public List<ServicoFuncionalidade> getServicoFuncionalidadeList() {
        return servicoFuncionalidadeList;
    }

    public void setServicoFuncionalidadeList(List<ServicoFuncionalidade> servicoFuncionalidadeList) {
        this.servicoFuncionalidadeList = servicoFuncionalidadeList;
    }

    public List<ServicoOfertaServico> getServicoOfertaServicoList() {
        return servicoOfertaServicoList;
    }

    public void setServicoOfertaServicoList(List<ServicoOfertaServico> servicoOfertaServicoList) {
        this.servicoOfertaServicoList = servicoOfertaServicoList;
    }

    public List<ServicoPlataforma> getServicoPlataformaList() {
        return servicoPlataformaList;
    }

    public void setServicoPlataformaList(List<ServicoPlataforma> servicoPlataformaList) {
        this.servicoPlataformaList = servicoPlataformaList;
    }

    public List<ServicoRestricoes> getServicoRestricoesList() {
        return servicoRestricoesList;
    }

    public void setServicoRestricoesList(List<ServicoRestricoes> servicoRestricoesList) {
        this.servicoRestricoesList = servicoRestricoesList;
    }

    public List<ServicoTarifa> getServicoTarifaList() {
        return servicoTarifaList;
    }

    public void setServicoTarifaList(List<ServicoTarifa> servicoTarifaList) {
        this.servicoTarifaList = servicoTarifaList;
    }

    public List<ServicoTecnologia> getServicoTecnologiaList() {
        return servicoTecnologiaList;
    }

    public void setServicoTecnologiaList(List<ServicoTecnologia> servicoTecnologiaList) {
        this.servicoTecnologiaList = servicoTecnologiaList;
    }

    public List<ServicoTipoAssinatura> getServicoTipoAssinaturaList() {
        return servicoTipoAssinaturaList;
    }

    public void setServicoTipoAssinaturaList(List<ServicoTipoAssinatura> servicoTipoAssinaturaList) {
        this.servicoTipoAssinaturaList = servicoTipoAssinaturaList;
    }

    public SistemaServico getSistemaServico() {
        return sistemaServico;
    }

    public void setSistemaServico(SistemaServico sistemaServico) {
        this.sistemaServico = sistemaServico;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getTpTarifa() {
        return tpTarifa;
    }

    public void setTpTarifa(String tpTarifa) {
        this.tpTarifa = tpTarifa;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getInFixa() {
        return inFixa;
    }

    public void setInFixa(String inFixa) {
        this.inFixa = inFixa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ServicoUfRestricao> getServicoUfRestricaoList() {
        return servicoUfRestricaoList;
    }

    public void setServicoUfRestricaoList(List<ServicoUfRestricao> servicoUfRestricaoList) {
        this.servicoUfRestricaoList = servicoUfRestricaoList;
    }

    public String getIn4g() {
        return in4g;
    }

    public void setIn4g(String in4g) {
        this.in4g = in4g;
    }

    public String getInAlteracaoCatalogo() {
        return inAlteracaoCatalogo;
    }

    public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
        this.inAlteracaoCatalogo = inAlteracaoCatalogo;
    }

	public List<Tecnologia> getTecnologiaList() {
		return tecnologiaList;
	}

	public void setTecnologiaList(List<Tecnologia> tecnologiaList) {
		this.tecnologiaList = tecnologiaList;
	}

	public List<Segmento> getSegmentoList() {
		return segmentoList;
	}

	public void setSegmentoList(List<Segmento> segmentoList) {
		this.segmentoList = segmentoList;
	}

	public ServicoSegmento getServicoSegmento() {
		return servicoSegmento;
	}

	public void setServicoSegmento(ServicoSegmento servicoSegmento) {
		this.servicoSegmento = servicoSegmento;
	}
}