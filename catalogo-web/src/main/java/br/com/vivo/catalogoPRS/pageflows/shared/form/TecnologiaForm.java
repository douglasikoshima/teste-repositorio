package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import org.apache.struts.validator.ValidatorActionForm;

public class TecnologiaForm extends ValidatorActionForm implements Serializable
{	
	private static final long serialVersionUID = 1L;

	private Long idTecnologiaPai;
	
	private Long idEntidade;

	private String nmTecnologia;
	
	private String justificativa;

	public Long getIdTecnologiaPai() {
		return idTecnologiaPai;
	}
	
	public Long getIdEntidade() {
		return idEntidade;
	}
	
	public String getNmTecnologia() {
		return nmTecnologia;
	}
	
	public String getJustificativa() {
		return justificativa;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia==null?nmTecnologia:nmTecnologia.toUpperCase();
	}
	
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa==null?justificativa:justificativa.toUpperCase();
	}	

	public void setIdTecnologiaPai(Long idTecnologiaPai) {
		this.idTecnologiaPai = idTecnologiaPai;
	}	

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}			
}
