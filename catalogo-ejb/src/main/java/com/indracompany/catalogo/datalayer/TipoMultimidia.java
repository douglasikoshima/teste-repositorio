package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Luciano
 *
 */
@Entity
@SequenceGenerator(name = "TIPOMULTIMIDIA_SQ" ,sequenceName = "CATALOGOPRS_OW.TIPOMULTIMIDIA_SQ", allocationSize = 1)
@Table(name="TIPOMULTIMIDIA", schema = "CATALOGOPRS_OW")
public class TipoMultimidia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer ID_IMAGEM = new Integer(1);
	public static final Integer ID_VIDEO = new Integer(2);
	public static final Integer ID_LINK = new Integer(3);
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOMULTIMIDIA_SQ")
	@Column(name="IDTIPOMULTIMIDIA")
	private Integer idTipoMultimidia;
	
	@Column(name="SGTIPOMULTIMIDIA")
	private String siglaTipoMultimidia;
	
	@Column(name="NMTIPOMILTIMIDIA")
	private String nomeTipoMultimidia;
	
	@Column(name="DTCRIACAO")
	private Date dataCriacao;
	
	@Column(name="DTALTERACAO")
	private Date dataUltimaAlteracao;
	
	@Column(name="NMUSUARIOCRIACAO")
	private String nomeUsuarioCriacao;
	
	@Column(name="NMUSUARIOALTERACAO")
	private String nomeUsuarioUltimaAlteracao;
	
	public TipoMultimidia() {
	}
	
	public TipoMultimidia(Integer idTipoMultimidia) {
		this.idTipoMultimidia = idTipoMultimidia;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public Integer getIdTipoMultimidia() {
		return idTipoMultimidia;
	}
	public void setIdTipoMultimidia(Integer idTipoMultimidia) {
		this.idTipoMultimidia = idTipoMultimidia;
	}
	public String getNomeTipoMultimidia() {
		return nomeTipoMultimidia;
	}
	public void setNomeTipoMultimidia(String nomeTipoMultimidia) {
		this.nomeTipoMultimidia = nomeTipoMultimidia;
	}
	public String getNomeUsuarioCriacao() {
		return nomeUsuarioCriacao;
	}
	public void setNomeUsuarioCriacao(String nomeUsuarioCriacao) {
		this.nomeUsuarioCriacao = nomeUsuarioCriacao;
	}
	public String getNomeUsuarioUltimaAlteracao() {
		return nomeUsuarioUltimaAlteracao;
	}
	public void setNomeUsuarioUltimaAlteracao(String nomeUsuarioUltimaAlteracao) {
		this.nomeUsuarioUltimaAlteracao = nomeUsuarioUltimaAlteracao;
	}
	public String getSiglaTipoMultimidia() {
		return siglaTipoMultimidia;
	}
	public void setSiglaTipoMultimidia(String siglaTipoMultimidia) {
		this.siglaTipoMultimidia = siglaTipoMultimidia;
	}
	
}