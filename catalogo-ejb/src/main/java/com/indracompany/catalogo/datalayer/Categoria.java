package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@SequenceGenerator(name = "CATEGORIA_SQ", sequenceName = "CATALOGOPRS_OW.CATEGORIA_SQ", allocationSize = 1)
@Table(name="CATEGORIA", schema = "CATALOGOPRS_OW")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Categoria() {
        this.categoriaSistemaList = new ArrayList<CategoriaSistema>();
    }
    public Categoria(Integer idCategoria) {
        this();
        this.idCategoria = idCategoria;
        this.cdCategoria = String.valueOf(idCategoria);
    }
    
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SQ")
    @Column(name = "IDCATEGORIA", unique=true, nullable=false, precision=22)
    private Integer idCategoria;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "INDCATALOGO")
    private String indCatalogo;

    @Column(name = "INDISPONIVEL")
    private String inDisponivel;

    @Column(name = "NMCATEGORIA")
    private String nmCategoria;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmuUsuarioCriacao;
    
    @Column(name = "INFIXA")
    private String inFixa;
    
    @Column(name = "DSCATEGORIA")
    private String dsCategoria;
    
    @Column(name = "INALTERACAOCATALOGO")
    private String inAlteracaoCatalogo;
    
    @Column(name = "CDCATEGORIA")
    private String cdCategoria;

    //bi-directional many-to-one association to Categoriasistema
    @OneToMany(mappedBy="categoria", cascade = CascadeType.ALL)
    private List<CategoriaSistema> categoriaSistemaList;
    
    //bi-directional many-to-one association to Plano
    @OneToMany(mappedBy="categoria")
    private List<Plano> planoList;

    @ManyToOne
    @JoinColumn(name = "IDFAMILIA")
    private Familia familia;

    @OneToMany(mappedBy="categoria")
    private List<Servico> servicoList;

    @OneToMany(mappedBy="categoria1")
    private List<Servico> servicoList1;

    @OneToMany(mappedBy="categoria2")
    private List<Servico> servicoList2;
    
    @Transient
    public void addCategoriaSistema(CategoriaSistema categoriaSistema) {
        categoriaSistemaList.add(categoriaSistema);
    }
    
    public List<Servico> getServicoList() {
		return servicoList;
	}
	public void setServicoList(List<Servico> servicoList) {
		this.servicoList = servicoList;
	}
	public List<Servico> getServicoList1() {
		return servicoList1;
	}
	public void setServicoList1(List<Servico> servicoList1) {
		this.servicoList1 = servicoList1;
	}
	public List<Servico> getServicoList2() {
		return servicoList2;
	}
	public void setServicoList2(List<Servico> servicoList2) {
		this.servicoList2 = servicoList2;
	}
	public List<CategoriaSistema> getCategoriaSistemaList() {
        return categoriaSistemaList;
    }
    
    public void setCategoriaSistemaList(List<CategoriaSistema> categoriaSistemaList) {
        this.categoriaSistemaList = categoriaSistemaList;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getIndCatalogo() {
        return indCatalogo;
    }

    public void setIndCatalogo(String indCatalogo) {
        this.indCatalogo = indCatalogo;
    }

    public String getInDisponivel() {
        return inDisponivel;
    }

    public void setInDisponivel(String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }

    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }

    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    public String getNmuUsuarioCriacao() {
        return nmuUsuarioCriacao;
    }

    public void setNmuUsuarioCriacao(String nmuUsuarioCriacao) {
        this.nmuUsuarioCriacao = nmuUsuarioCriacao;
    }

    public List<Plano> getPlanoList() {
        return planoList;
    }

    public void setPlanoList(List<Plano> planoList) {
        this.planoList = planoList;
    }

    public String getInFixa() {
        return inFixa;
    }

    public void setInFixa(String inFixa) {
        this.inFixa = inFixa;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public void setDsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
    }

    public String getInAlteracaoCatalogo() {
        return inAlteracaoCatalogo;
    }

    public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
        this.inAlteracaoCatalogo = inAlteracaoCatalogo;
    }
    public String getCdCategoria() {
        return cdCategoria;
    }
    public void setCdCategoria(String cdCategoria) {
        this.cdCategoria = cdCategoria;
    }
}