package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

/**
 * VO de ErroLegado
 */
public class ErroLegadoVO extends ValueObject
{
  private static final long serialVersionUID = 1L;
  
    /**
    * CdErroLegado
    */
    private String cdErroLegado;
  
    /**
    * CdErroPadrao
    */
    private Integer cdErroPadrao;
  
    /**
    * CdServico
    */
    private Integer cdServico;
  
    /**
    * CdSistemaLegado
    */
    private Integer cdSistemaLegado;
  
    private String dsServico;
    
    private String dsSistemaLegado;
    
    private String cdServicoNegocio;
    
    private String dsServicoNegocio;
    

    public String getCdServicoNegocio() {
		return cdServicoNegocio;
	}

	public void setCdServicoNegocio(String cdServicoNegocio) {
		this.cdServicoNegocio = cdServicoNegocio;
	}

	public String getDsServico() {
		return dsServico;
	}

	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}

	public String getDsServicoNegocio() {
		return dsServicoNegocio;
	}

	public void setDsServicoNegocio(String dsServicoNegocio) {
		this.dsServicoNegocio = dsServicoNegocio;
	}

	public String getDsSistemaLegado() {
		return dsSistemaLegado;
	}

	public void setDsSistemaLegado(String dsSistemaLegado) {
		this.dsSistemaLegado = dsSistemaLegado;
	}

	/**
    * @param id
    */
  public ErroLegadoVO (String id)
  {
    super(id);
  }

  public ErroLegadoVO()
  {
    super();
  }

    /**
    * @param id
    */
  public void setId(String id)
  {
    this.id = id;

    if ((id != null) && !id.trim().equals(""))
    {
            String keys[] = id.split(":");
            setCdErroLegado(id);
            setCdServico(new Integer(keys[1]));
            setCdSistemaLegado(new Integer(keys[2]));
    }
  }

  public void setId()
  {
    this.id =
    getCdErroLegado()
    + ":" +
    getCdServico()
    + ":" +
    getCdSistemaLegado()
    ;
  }

  
    /**
    * Sets the value for CdErroLegado
    */
      public void setCdErroLegado(String cdErroLegado)
    {
        this.cdErroLegado = cdErroLegado;
    }
    
    /**
    * Gets the value for CdErroLegado
    */
      public String getCdErroLegado()
    {
        return cdErroLegado;
    }
      
    /**
    * Sets the value for CdErroPadrao
    */
      public void setCdErroPadrao(Integer cdErroPadrao)
    {
        this.cdErroPadrao = cdErroPadrao;
    }
    
    /**
    * Gets the value for CdErroPadrao
    */
      public Integer getCdErroPadrao()
    {
        return cdErroPadrao;
    }
      
    /**
    * Sets the value for CdServico
    */
      public void setCdServico(Integer cdServico)
    {
        this.cdServico = cdServico;
    }
    
    /**
    * Gets the value for CdServico
    */
      public Integer getCdServico()
    {
        return cdServico;
    }
      
    /**
    * Sets the value for CdSistemaLegado
    */
      public void setCdSistemaLegado(Integer cdSistemaLegado)
    {
        this.cdSistemaLegado = cdSistemaLegado;
    }
    
    /**
    * Gets the value for CdSistemaLegado
    */
      public Integer getCdSistemaLegado()
    {
        return cdSistemaLegado;
    }
      
}
