package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class GrupoProdutoCaracteristicaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer idGrupoProdutoCaracteristica;
	private CaracteristicaTO caracteristicaTO;
	private GrupoProdutoTO grupoProdutoTO;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private ValorCaracteristicaTO valorCaracteristicaTO;
	
	public GrupoProdutoCaracteristicaTO() {
	}
	
	public GrupoProdutoCaracteristicaTO(Integer idGrupoProdutoCaracteristica) {
		this.idGrupoProdutoCaracteristica = idGrupoProdutoCaracteristica;
	}
	
	public GrupoProdutoCaracteristicaTO(GrupoProdutoTO grupoProdutoTO, CaracteristicaTO caracteristicaTO) {
		this.grupoProdutoTO = grupoProdutoTO;
		this.caracteristicaTO = caracteristicaTO;
	}
	
	public GrupoProdutoCaracteristicaTO(GrupoProdutoTO grupoProdutoTO, ValorCaracteristicaTO valorCaracteristicaTO) {
		this.grupoProdutoTO = grupoProdutoTO;
		this.valorCaracteristicaTO = valorCaracteristicaTO;
		this.caracteristicaTO = valorCaracteristicaTO.getCaracteristicaTO();
	}
	
	public Integer getIdGrupoProdutoCaracteristica() {
		return idGrupoProdutoCaracteristica;
	}

	public void setIdGrupoProdutoCaracteristica(Integer idGrupoProdutoCaracteristica) {
		this.idGrupoProdutoCaracteristica = idGrupoProdutoCaracteristica;
	}

	public CaracteristicaTO getCaracteristicaTO() {
		return caracteristicaTO;
	}

	public void setCaracteristicaTO(CaracteristicaTO caracteristicaTO) {
		this.caracteristicaTO = caracteristicaTO;
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

	public GrupoProdutoTO getGrupoProdutoTO() {
		return grupoProdutoTO;
	}

	public void setGrupoProdutoTO(GrupoProdutoTO grupoProdutoTO) {
		this.grupoProdutoTO = grupoProdutoTO;
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

	public ValorCaracteristicaTO getValorCaracteristicaTO() {
		return valorCaracteristicaTO;
	}

	public void setValorCaracteristicaTO(ValorCaracteristicaTO valorCaracteristicaTO) {
		this.valorCaracteristicaTO = valorCaracteristicaTO;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idGrupoProdutoCaracteristica: " + this.idGrupoProdutoCaracteristica}, ", ");
	}
	
}