package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Map;

import com.indracompany.catalogo.datalayer.Plano;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SistemaPlano;
import com.indracompany.catalogo.datalayer.SistemaServico;

/**
 * @author Luiz
 * 
 */
public class CategorizacaoAnaliseCreditoTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public CategorizacaoAnaliseCreditoTO() {}
	
	public CategorizacaoAnaliseCreditoTO(Integer id, String nome, String nmPlataforma, Integer idPlataforma, String nmCategoria, Integer idCategoria, Integer[] idPlataformas, Integer[] idRegionais, Float valor, Float precoDe, Float precoAte, Integer idCategoriaScore, Integer idAnaliseCredito, Integer idCabecalhoAnaliseCredito, Map<String, Boolean> scoresConfigurados) {
		super();
		this.id = id;
		this.nome = nome;
		this.nmPlataforma = nmPlataforma;
		this.idPlataforma = idPlataforma;
		this.nmCategoria = nmCategoria;
		this.idCategoria = idCategoria;
		this.idPlataformas = idPlataformas;
		this.idRegionais = idRegionais;
		this.valor = valor;
		this.precoDe = precoDe;
		this.precoAte = precoAte;
		this.idCategoriaScore = idCategoriaScore;
		this.idAnaliseCredito = idAnaliseCredito;
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
		this.scoresConfigurados = scoresConfigurados;
	}

	public CategorizacaoAnaliseCreditoTO(
			Integer id,
			String nome,
			String nmCategoria,
			Integer idCategoria,
			Double valor,
			Integer idCategoriaScore,
			String cdCodigo,
			Integer idCabecalhoAnaliseCredito) {
		this.id = id;
		this.nome = nome + " - " + cdCodigo;
		this.nmCategoria = nmCategoria;
		this.idCategoria = idCategoria;
		this.valor = valor.floatValue();
		this.idCategoriaScore = idCategoriaScore;
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}

	public CategorizacaoAnaliseCreditoTO(
			Integer id,
			String nome,
			String nmCategoria,
			Integer idCategoria,
			Double valor,
			Integer idCategoriaScore,
			String cdCodigo) {
		this.id = id;
		this.nome = nome + " - " + cdCodigo;
		this.nmCategoria = nmCategoria;
		this.idCategoria = idCategoria;
		this.valor = valor.floatValue();
		this.idCategoriaScore = idCategoriaScore;
	}
	
	public CategorizacaoAnaliseCreditoTO(
			Integer id,
			String nome,
			String nmPlataforma,
			Integer idPlataforma,
			String nmCategoria,
			Integer idCategoria) {
		this.id = id;
		this.nome = nome;
		this.nmPlataforma = nmPlataforma;
		this.idPlataforma = idPlataforma; 
		this.nmCategoria = nmCategoria;
		this.idCategoria = idCategoria;
	}
	
	public CategorizacaoAnaliseCreditoTO(Plano plano, Integer idPlataforma, String nmPlataforma, SistemaPlano sistemaPlano) {
		this.id = plano.getIdPlano();
		this.nome = plano.getNmComercial() + " - " + sistemaPlano.getCdCodigo();
		this.nmPlataforma = nmPlataforma;
		this.idPlataforma = idPlataforma;
		
		if (plano.getPlanoCategoriaScore() != null && plano.getPlanoCategoriaScore().getCategoriaScore() != null) {
			this.idCategoriaScore = plano.getPlanoCategoriaScore().getIdPlanoCategoriaScore();
			this.nmCategoria = plano.getPlanoCategoriaScore().getCategoriaScore().getNmCategoriaScore();
			this.idCategoria = plano.getPlanoCategoriaScore().getCategoriaScore().getIdCategoriaScore();
		}		
	}
	
	public CategorizacaoAnaliseCreditoTO(Servico servico, Integer idPlataforma, String nmPlataforma, SistemaServico sistemaServico) {
        this(servico, sistemaServico);
		this.nmPlataforma = nmPlataforma;
		this.idPlataforma = idPlataforma;
	}
    
    public CategorizacaoAnaliseCreditoTO(Servico servico, SistemaServico sistemaServico) {
        this.id = servico.getIdServico();
        this.nome = servico.getNmComercial() + " - " + sistemaServico.getCdCodigo();        
        if (servico.getServicoCategoriaScore() != null && servico.getServicoCategoriaScore().getCategoriaScore() != null) {
            this.idCategoriaScore = servico.getServicoCategoriaScore().getIdServicoCategoriaScore();
            this.nmCategoria = servico.getServicoCategoriaScore().getCategoriaScore().getNmCategoriaScore();
            this.idCategoria = servico.getServicoCategoriaScore().getCategoriaScore().getIdCategoriaScore();
        }        
    }
	
	private Integer id;
	private String nome;
	private String nmPlataforma;
	private Integer idPlataforma; 
	private String nmCategoria;
	private Integer idCategoria;
	private Integer[] idPlataformas;
	private Integer[] idRegionais;
	private Float valor;
	private Float precoDe;
	private Float precoAte;
	private Integer idCategoriaScore;
	private Integer idAnaliseCredito;
	private Integer idCabecalhoAnaliseCredito;
	private Map<String, Boolean> scoresConfigurados;
	
	public Integer getId() {
		return id;
	}

	public Integer getIdCategoriaScore() {
		return idCategoriaScore;
	}

	public void setIdCategoriaScore(Integer idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNmCategoria() {
		return nmCategoria;
	}

	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}

	public String getNmPlataforma() {
		return nmPlataforma;
	}

	public void setNmPlataforma(String nmPlataforma) {
		this.nmPlataforma = nmPlataforma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer[] getIdPlataformas() {
		return idPlataformas;
	}

	public void setIdPlataformas(Integer[] idPlataformas) {
		this.idPlataformas = idPlataformas;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Float getPrecoAte() {
		return precoAte;
	}

	public void setPrecoAte(Float precoAte) {
		this.precoAte = precoAte;
	}

	public Float getPrecoDe() {
		return precoDe;
	}

	public void setPrecoDe(Float precoDe) {
		this.precoDe = precoDe;
	}

	public Integer getIdAnaliseCredito() {
		return idAnaliseCredito;
	}

	public void setIdAnaliseCredito(Integer idAnaliseCredito) {
		this.idAnaliseCredito = idAnaliseCredito;
	}

	public Integer getIdCabecalhoAnaliseCredito() {
		return idCabecalhoAnaliseCredito;
	}

	public void setIdCabecalhoAnaliseCredito(Integer idCabecalhoAnaliseCredito) {
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}

	public Integer[] getIdRegionais() {
		return idRegionais;
	}

	public void setIdRegionais(Integer[] idRegionais) {
		this.idRegionais = idRegionais;
	}

	public Map<String, Boolean> getScoresConfigurados() {
		return scoresConfigurados;
	}

	public void setScoresConfigurados(Map<String, Boolean> scoresConfigurados) {
		this.scoresConfigurados = scoresConfigurados;
	}
}
