package com.indracompany.catalogo.to;

import java.math.BigDecimal;

public class EspServicoPlanoMinutoTO {
	
	private Integer idServico;
	
	private Long qtMinutoLivreFMLocal;
	
	private Long qtMinutoLivreFFLocal;
	
	private BigDecimal vlMinutoAdicionalFFlocal;

	private String nmServico;

	public EspServicoPlanoMinutoTO() {
		super();
	}

	public EspServicoPlanoMinutoTO(Integer idServico) {
		super();
		this.idServico = idServico;
	}

	public EspServicoPlanoMinutoTO(Long idServico) {
		super();
		if(idServico != null){
			this.idServico = new Integer(idServico.intValue());
		}
	}
	
	public EspServicoPlanoMinutoTO(Integer idServico, Long qtMinutoLivreFMLocal, Long qtMinutoLivreFFLocal, BigDecimal vlMinutoAdicionalFFlocal, String nmServico) {
		super();
		this.idServico = idServico;
		this.qtMinutoLivreFMLocal = qtMinutoLivreFMLocal;
		this.qtMinutoLivreFFLocal = qtMinutoLivreFFLocal;
		this.vlMinutoAdicionalFFlocal = vlMinutoAdicionalFFlocal;
		this.nmServico = nmServico;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getNmServico() {
		return nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}

	public Long getQtMinutoLivreFFLocal() {
		return qtMinutoLivreFFLocal;
	}

	public void setQtMinutoLivreFFLocal(Long qtMinutoLivreFFLocal) {
		this.qtMinutoLivreFFLocal = qtMinutoLivreFFLocal;
	}

	public Long getQtMinutoLivreFMLocal() {
		return qtMinutoLivreFMLocal;
	}

	public void setQtMinutoLivreFMLocal(Long qtMinutoLivreFMLocal) {
		this.qtMinutoLivreFMLocal = qtMinutoLivreFMLocal;
	}

	public BigDecimal getVlMinutoAdicionalFFlocal() {
		return vlMinutoAdicionalFFlocal;
	}

	public void setVlMinutoAdicionalFFlocal(BigDecimal vlMinutoAdicionalFFlocal) {
		this.vlMinutoAdicionalFFlocal = vlMinutoAdicionalFFlocal;
	}


}
