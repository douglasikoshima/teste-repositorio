package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.CategoriaTO;
import com.indracompany.catalogo.to.FamiliaTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class FamiliaCategoriaForm extends ValidatorActionForm  implements java.io.Serializable {

    private static final long serialVersionUID = -1214727346924911128L;
    
    private String tipoPesquisa;
    
    private String codigoFamilia;
    private String nomeFamilia;
    private String origemFamilia;
    private String disponibilidadeFamilia;
    
    private String codigoCategoria;
    private String descricaoCategoria;
    private String idFamiliaCategoria;
    private String origemCategoria;
    private String disponibilidadeCategoria;        
    
    private String idFamilia;

    private String nomeFamiliaNew;
    private String descricaoCategoriaNew;
    private String nmFamiliaCategoriaEdit;
    private String idEditado;
    private String usuario;
    
    private String textDescFamilia;
    
    private String statusAtual;
    
    public FamiliaTO buildFamiliaTOSearch() throws CatalogoPRSException {
        FamiliaTO to = new FamiliaTO();
        try {
            to.setCdFamilia(this.getCodigoFamilia());
            if (!StringUtils.isBlank(this.getOrigemFamilia())) { 
                to.setInCriacaoCatalogo(this.getOrigemFamilia().equalsIgnoreCase("catalogo"));
            }
            if(!StringUtils.isBlank(this.getDisponibilidadeFamilia())) {
                to.setInDisponivel(this.getDisponibilidadeFamilia().equalsIgnoreCase("sim"));
            }
            to.setNmFamilia(this.getNomeFamilia());
        } catch (Exception e) {
            throw new CatalogoPRSException("Valor informado para pesquisa inv&aacute;lido");
        }
        return to;
    }
    
    public CategoriaTO buildCategoriaTOSearch() throws CatalogoPRSException {
        CategoriaTO to = new CategoriaTO();
        try {
            if (!StringUtils.isBlank(this.getCodigoCategoria())) {
                to.setIdCategoria(Integer.valueOf(this.getCodigoCategoria()));
            }
            if(!StringUtils.isBlank(this.getOrigemCategoria())) {
                to.setIndCatalogo(this.getOrigemCategoria().equalsIgnoreCase("catalogo"));
            }
            if(!StringUtils.isBlank(this.getDisponibilidadeCategoria())) {
                to.setInDisponivel(this.getDisponibilidadeCategoria().equalsIgnoreCase("sim"));
            }
            if(!StringUtils.isBlank(this.idFamiliaCategoria)) {
                to.setFamiliaTO(new FamiliaTO(Integer.valueOf(this.idFamiliaCategoria)));
            }
            to.setDsCategoria(this.getDescricaoCategoria());
        } catch (Exception e) {
            throw new CatalogoPRSException("Valor informado para pesquisa inv&aacute;lido");
        }
        return to;
    }
    
    public FamiliaTO buildFamiliaTOInsertChange() {
        FamiliaTO to = new FamiliaTO();
        to.setNmFamilia(this.getNomeFamiliaNew());
        to.setDsFamilia(this.getNomeFamiliaNew());
        if (!StringUtils.isBlank(this.getIdEditado())) {
            to.setIdFamilia(Integer.valueOf(this.getIdEditado()));
        }
        return to;
    }
    
    public CategoriaTO buildCategoriaTOInsertChange() {
        CategoriaTO to = new CategoriaTO();
        to.setDsCategoria(this.getDescricaoCategoriaNew());
        to.setNmCategoria(this.getDescricaoCategoriaNew());
        if (StringUtils.isBlank(this.idEditado)) {
            to.setNmUsuarioCriacao(this.usuario);
            to.setDtCriacao(new Date());
            if (this.idFamilia != null) {
                to.setFamiliaTO(new FamiliaTO(Integer.valueOf(this.idFamilia)));
            }
        } else {
            to.setIdCategoria(Integer.valueOf(this.idEditado));
        }
        
        return to;
    }
    
    public String getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(String codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getCodigoFamilia() {
		return codigoFamilia;
	}

	public void setCodigoFamilia(String codigoFamilia) {
		this.codigoFamilia = codigoFamilia;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

	public String getDescricaoCategoriaNew() {
		return descricaoCategoriaNew;
	}

	public void setDescricaoCategoriaNew(String descricaoCategoriaNew) {
		this.descricaoCategoriaNew = descricaoCategoriaNew;
	}

	public String getDisponibilidadeCategoria() {
		if(this.disponibilidadeCategoria == null) {
			this.disponibilidadeCategoria = "sim";
		}
		return disponibilidadeCategoria;
	}

	public void setDisponibilidadeCategoria(String disponibilidadeCategoria) {
		this.disponibilidadeCategoria = disponibilidadeCategoria;
	}

	public String getDisponibilidadeFamilia() {
		if(this.disponibilidadeFamilia == null) {
			this.disponibilidadeFamilia = "sim";
		}
		return disponibilidadeFamilia;
	}

	public void setDisponibilidadeFamilia(String disponibilidadeFamilia) {
		this.disponibilidadeFamilia = disponibilidadeFamilia;
	}

	public String getIdEditado() {
		return idEditado;
	}

	public void setIdEditado(String idEditado) {
		this.idEditado = idEditado;
	}

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getIdFamiliaCategoria() {
		return idFamiliaCategoria;
	}

	public void setIdFamiliaCategoria(String idFamiliaCategoria) {
		this.idFamiliaCategoria = idFamiliaCategoria;
	}

	public String getNomeFamiliaNew() {
		return nomeFamiliaNew;
	}

	public void setNomeFamiliaNew(String nomeFamiliaNew) {
		this.nomeFamiliaNew = nomeFamiliaNew;
	}

	public String getNomeFamilia() {
		return nomeFamilia;
	}

	public void setNomeFamilia(String nomeFamilia) {
		this.nomeFamilia = nomeFamilia;
	}

	public String getOrigemCategoria() {
		return origemCategoria;
	}

	public void setOrigemCategoria(String origemCategoria) {
		this.origemCategoria = origemCategoria;
	}

	public String getOrigemFamilia() {
		return origemFamilia;
	}

	public void setOrigemFamilia(String origemFamilia) {
		this.origemFamilia = origemFamilia;
	}

	public String getStatusAtual() {
		return statusAtual;
	}

	public void setStatusAtual(String statusAtual) {
		this.statusAtual = statusAtual;
	}

	 public String getTipoPesquisa() {
		 return tipoPesquisa;
	 }

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getTextDescFamilia() {
		return textDescFamilia;
	}

	public void setTextDescFamilia(String textDescFamilia) {
		this.textDescFamilia = textDescFamilia;
	}

	public void clean(){
        this.tipoPesquisa = null;
        
        this.codigoFamilia = null;
        this.nomeFamilia = null;
        this.origemFamilia = null;
        this.disponibilidadeFamilia = null;
        
        this.codigoCategoria = null;
        this.descricaoCategoria = null;
        this.idFamiliaCategoria = null;
        this.origemCategoria = null;
        this.disponibilidadeCategoria = null;        
        
        this.idFamilia = null;

        this.nomeFamiliaNew = null;
        this.descricaoCategoriaNew = null;
        this.idEditado = null;
        this.usuario = null;
        
        this.statusAtual = null;
    }

    public String getNmFamiliaCategoriaEdit() {
        return nmFamiliaCategoriaEdit;
    }

    public void setNmFamiliaCategoriaEdit(String nmFamiliaCategoriaEdit) {
        this.nmFamiliaCategoriaEdit = nmFamiliaCategoriaEdit;
    }
}
