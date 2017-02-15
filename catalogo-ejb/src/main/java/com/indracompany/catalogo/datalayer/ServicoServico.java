package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "SERVICOSERVICO_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOSERVICO_SQ", allocationSize = 1)
@Table(name="SERVICOSERVICO", schema = "CATALOGOPRS_OW")
public class ServicoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoServico() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOSERVICO_SQ")
	@Column(name = "IDSERVICOSERVICO")
	private Integer idServicoServico;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "INMEMBER")
	private String inMember;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
    
    @Column(name = "INCRIACAOCATALOGO")
    private String inCriacaoCatalogo;
    
    @Column(name = "INFIXA")
    private String inFixa;
    
	//bi-directional many-to-one association to Tiporelacionamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPORELACIONAMENTO")
	private TipoRelacionamento tipoRelacionamento;
    
    @ManyToOne
    @JoinColumn(name="IDSERVICO1")
    private Servico servico1;
    
    @ManyToOne
    @JoinColumn(name="IDSERVICO2")
    private Servico servico2;

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

	public Integer getIdServicoServico() {
		return idServicoServico;
	}

	public void setIdServicoServico(Integer idServicoServico) {
		this.idServicoServico = idServicoServico;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInMember() {
		return inMember;
	}

	public void setInMember(String inMember) {
		this.inMember = inMember;
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

	public TipoRelacionamento getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	public void setTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

    public Servico getServico1() {
        return servico1;
    }

    public void setServico1(Servico servico1) {
        this.servico1 = servico1;
    }

    public Servico getServico2() {
        return servico2;
    }

    public void setServico2(Servico servico2) {
        this.servico2 = servico2;
    }    
    
    public String getInCriacaoCatalogo() {
        return inCriacaoCatalogo;
    }

    public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
        this.inCriacaoCatalogo = inCriacaoCatalogo;
    }

    public String getInFixa() {
        return inFixa;
    }

    public void setInFixa(String inFixa) {
        this.inFixa = inFixa;
    }
}