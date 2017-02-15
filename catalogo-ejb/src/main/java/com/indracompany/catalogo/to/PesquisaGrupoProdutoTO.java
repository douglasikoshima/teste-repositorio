package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gustavo
 *
 */
public class PesquisaGrupoProdutoTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private PaginacaoDataTableTO paginacaoDataTableTO;
	private Parametros parametros = new Parametros();
	private List<LinhaResultado> resultado = new ArrayList<LinhaResultado>();
	
	public PaginacaoDataTableTO getPaginacaoDataTableTO() {
		return paginacaoDataTableTO;
	}
	public void setPaginacaoDataTableTO(PaginacaoDataTableTO paginacaoDataTableTO) {
		this.paginacaoDataTableTO = paginacaoDataTableTO;
	}
	public Parametros getParametros() {
		return parametros;
	}
	public void setParametros(Parametros parametros) {
		this.parametros = parametros;
	}
	public List<LinhaResultado> getResultado() {
		return resultado;
	}
	public void setResultado(List<LinhaResultado> resultado) {
		this.resultado = resultado;
	}
	
	public class Parametros {
		
		private Integer idTipoProduto;
		private Integer idFabricante;
		private Integer idModelo;
		private Integer idTecnologia;
		private List<Integer> idsCaracteristicas;
		private List<Integer> idsValoresCaracteristicas;
		
		public Integer getIdFabricante() {
			return idFabricante;
		}
		public void setIdFabricante(Integer idFabricante) {
			this.idFabricante = idFabricante;
		}
		public Integer getIdTipoProduto() {
			return idTipoProduto;
		}
		public void setIdTipoProduto(Integer idTipoProduto) {
			this.idTipoProduto = idTipoProduto;
		}
		public Integer getIdModelo() {
			return idModelo;
		}
		public void setIdModelo(Integer idModelo) {
			this.idModelo = idModelo;
		}
		public Integer getIdTecnologia() {
			return idTecnologia;
		}
		public void setIdTecnologia(Integer idTecnologia) {
			this.idTecnologia = idTecnologia;
		}
		public List<Integer> getIdsCaracteristicas() {
			return idsCaracteristicas;
		}
		public void setIdsCaracteristicas(List<Integer> idsCaracteristicas) {
			this.idsCaracteristicas = idsCaracteristicas;
		}
		public List<Integer> getIdsValoresCaracteristicas() {
			return idsValoresCaracteristicas;
		}
		public void setIdsValoresCaracteristicas(List<Integer> idsValoresCaracteristicas) {
			this.idsValoresCaracteristicas = idsValoresCaracteristicas;
		}
		
	}
	
	public class LinhaResultado {
		
		private Integer idGrupoProduto;
		private String nmGrupoProduto;
		private String nmFabricante;
		private String inFimVida;
		private String inDisponivel;
		private String inCaracteristica;
		private String inMultimidia;
		
		public Integer getIdGrupoProduto() {
			return idGrupoProduto;
		}
		public void setIdGrupoProduto(Integer idGrupoProduto) {
			this.idGrupoProduto = idGrupoProduto;
		}
		public String getInDisponivel() {
			return inDisponivel;
		}
		public void setInDisponivel(String inDisponivel) {
			this.inDisponivel = inDisponivel;
		}
		public String getInFimVida() {
			return inFimVida;
		}
		public void setInFimVida(String inFimVida) {
			this.inFimVida = inFimVida;
		}
		public String getNmFabricante() {
			return nmFabricante;
		}
		public void setNmFabricante(String nmFabricante) {
			this.nmFabricante = nmFabricante;
		}
		public String getNmGrupoProduto() {
			return nmGrupoProduto;
		}
		public void setNmGrupoProduto(String nmGrupoProduto) {
			this.nmGrupoProduto = nmGrupoProduto;
		}
		public String getInCaracteristica() {
			return inCaracteristica;
		}
		public void setInCaracteristica(String inCaracteristica) {
			this.inCaracteristica = inCaracteristica;
		}
		public String getInMultimidia() {
			return inMultimidia;
		}
		public void setInMultimidia(String inMultimidia) {
			this.inMultimidia = inMultimidia;
		}
		
	}
	
}