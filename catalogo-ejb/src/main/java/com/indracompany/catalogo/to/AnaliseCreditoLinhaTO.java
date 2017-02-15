package com.indracompany.catalogo.to;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class AnaliseCreditoLinhaTO {

    /* Atributos referente à tela */
    private Integer id; // Var genérico para fazer pesquisas por ID
    private Integer idOferta;
    private Integer idServico;
    private Integer score; // Número do score para realizar a pesquisa
    private String cdServico; // Quantidade de colunas
    private String nomeComercialServico; // combo nome comercial para pesquisa
    private String scoresListAdicionar; // Array com os ids de analise credito linha e os códigos de seus scores selecionados. Ex: 1|01;1|02;
    private String scoresListRemover;
    private List<Integer> idAnaliseCreditoList;
    private List<AnaliseCreditoTO> analiseCreditoTOList;

    /* Atributos referentes às entities */
    private AnaliseCreditoTO analiseCreditoTO;
    private String dsOfertafixa; // Nome da Oferta
    private String inFWT;
    private String inConvergenteCobre;
    private String inConvergenteFibra;
    private String inSpeedySoloCobre;
    private String inSpeedySoloFibra;
    private String inPortab;
    private Date dtInicial;
    private Date dtFinal;
    private Integer idOfertafixaCreditoScore;
    private String cdCor; // Código da coluna score
    private Integer cdPrioridade;
    private String nmServicoLinha;
    private String nmServicoPreCad;
    private String nmServicoPlano;
    private Integer idAnaliseCredito;
    private Boolean flagScore;
    private Boolean priorizado = false;
    private String cdOfertafixa;

    /* Get para ser usado no title na jsp com as informações da orferta selecionada */
    private static final String TEMPLATE_INFO = "%s (Servi&ccedil;o Linha) + %s (Servi&ccedil;o Plano) + %s (Tipo Oferta) + %s - %s (Per&iacute;odo Vig&ecirc;ncia)";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public AnaliseCreditoLinhaTO(Integer idOferta, Integer idAnaliseCredito, Boolean flagScore) {
        this.idOferta = idOferta;
        this.idAnaliseCredito = idAnaliseCredito;
        this.flagScore = flagScore;
    }

    public AnaliseCreditoLinhaTO() {
    }

    public String getInfo() {
        return String.format(TEMPLATE_INFO, this.getNmServicoLinha() == null ? "" : this.getNmServicoLinha(), this.getNmServicoPlano() == null ? ""
                : this.getNmServicoPlano(), this.getTipoOferta(), this.getDtInicialFormatado(), this.getDtFinalFormatado());
    }

    private String getDtFinalFormatado() {
        return this.getDtFinal() != null ? sdf.format(this.getDtFinal()) : "";
    }

    private String getDtInicialFormatado() {
        return this.getDtInicial() != null ? sdf.format(this.getDtInicial()) : "";
    }

    private String getTipoOferta() {
        StringBuilder sb = new StringBuilder();
        sb.append((this.getInConvergenteCobre().equals("S") ? "LSC | " : ""));
        sb.append((this.getInConvergenteFibra().equals("S") ? "LSF | " : ""));
        sb.append((this.getInSpeedySoloCobre().equals("S") ? "SSC | " : ""));
        sb.append((this.getInSpeedySoloFibra().equals("S") ? "SSF | " : ""));
        sb.append((this.getInPortab().equals("S") ? "PORT | " : ""));
        sb.append((this.getInFWT().equals("S") ? "FWT | " : ""));
        String retorno = sb.toString();
        if (retorno.lastIndexOf(" | ") > 0) {
            retorno = retorno.substring(0, sb.lastIndexOf(" | "));
        }
        return retorno;
    }

    /* Getters and Setters */
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getCdPrioridade() {
        return cdPrioridade;
    }

    public void setCdPrioridade(Integer cdPrioridade) {
        this.cdPrioridade = cdPrioridade;
    }

    public String getDsOfertafixa() {
        return dsOfertafixa;
    }

    public void setDsOfertafixa(String dsOfertafixa) {
        this.dsOfertafixa = dsOfertafixa;
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

    public Integer getIdOfertafixaCreditoScore() {
        return idOfertafixaCreditoScore;
    }

    public void setIdOfertafixaCreditoScore(Integer idOfertafixaCreditoScore) {
        this.idOfertafixaCreditoScore = idOfertafixaCreditoScore;
    }

    public String getInConvergenteCobre() {
        return inConvergenteCobre;
    }

    public void setInConvergenteCobre(String inConvergenteCobre) {
        this.inConvergenteCobre = inConvergenteCobre;
    }

    public String getInConvergenteFibra() {
        return inConvergenteFibra;
    }

    public void setInConvergenteFibra(String inConvergenteFibra) {
        this.inConvergenteFibra = inConvergenteFibra;
    }

    public String getInFWT() {
        return inFWT;
    }

    public void setInFWT(String inFWT) {
        this.inFWT = inFWT;
    }

    public String getInPortab() {
        return inPortab;
    }

    public void setInPortab(String inPortab) {
        this.inPortab = inPortab;
    }

    public String getInSpeedySoloCobre() {
        return inSpeedySoloCobre;
    }

    public void setInSpeedySoloCobre(String inSpeedySoloCobre) {
        this.inSpeedySoloCobre = inSpeedySoloCobre;
    }

    public String getInSpeedySoloFibra() {
        return inSpeedySoloFibra;
    }

    public void setInSpeedySoloFibra(String inSpeedySoloFibra) {
        this.inSpeedySoloFibra = inSpeedySoloFibra;
    }

    public String getNmServicoLinha() {
        return nmServicoLinha;
    }

    public void setNmServicoLinha(String nmServicoLinha) {
        this.nmServicoLinha = nmServicoLinha;
    }

    public String getNmServicoPlano() {
        return nmServicoPlano;
    }

    public void setNmServicoPlano(String nmServicoPlano) {
        this.nmServicoPlano = nmServicoPlano;
    }

    public String getNmServicoPreCad() {
        return nmServicoPreCad;
    }

    public void setNmServicoPreCad(String nmServicoPreCad) {
        this.nmServicoPreCad = nmServicoPreCad;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public String getNomeComercialServico() {
        return nomeComercialServico;
    }

    public String getScoresListAdicionar() {
        return scoresListAdicionar;
    }

    public void setScoresListAdicionar(String scoresListAdicionar) {
        this.scoresListAdicionar = scoresListAdicionar;
    }

    public String getScoresListRemover() {
        return scoresListRemover;
    }

    public void setScoresListRemover(String scoresListRemover) {
        this.scoresListRemover = scoresListRemover;
    }

    public String getCdCor() {
        return cdCor;
    }

    public void setCdCor(String cdCor) {
        this.cdCor = cdCor;
    }

    public List<AnaliseCreditoTO> getAnaliseCreditoTOList() {
        return analiseCreditoTOList;
    }

    public void setAnaliseCreditoTOList(List<AnaliseCreditoTO> analiseCretioTOList) {
        this.analiseCreditoTOList = analiseCretioTOList;
    }

    public void setAnaliseCreditoTOList(List<AnaliseCreditoTO> analiseCreditoTOListScore, String acao) {
        if (acao.equals("search")) {
            this.analiseCreditoTOList = new ArrayList<AnaliseCreditoTO>();
            for (AnaliseCreditoTO to : analiseCreditoTOListScore) {
                AnaliseCreditoTO analiseCreditoTO = new AnaliseCreditoTO(to.getIdAnaliseCredito(), to.getCdCor(), false);
                this.analiseCreditoTOList.add(analiseCreditoTO);
            }
        }

    }

    public List<Integer> getIdAnaliseCreditoList() {
        return idAnaliseCreditoList;
    }

    public void setIdAnaliseCreditoList(List<Integer> idAnaliseCreditoList) {
        this.idAnaliseCreditoList = idAnaliseCreditoList;
    }

    public void addCdCorList(Integer idAnaliseCreditoTO) {
        for (AnaliseCreditoTO to : analiseCreditoTOList) {
            if (to.getIdAnaliseCredito().equals(idAnaliseCreditoTO)) {
                to.setFlagScore(true);
            }
        }
    }

    @Override
    public String toString() {
        return String
                .format(
                        "[idAnaliseCredigoLinha=%s, cdCor=%s, cdCorList=%s, idOfertafixa=%s , dsOfertafixa=%s, inFWT=%s, inConvergenteFibra=%s, inConvergenteCobre=%s, inSpeedySoloFibra=%s, inSpeedySoloCobre=%s, inPortab=%s"
                                + ", dtInicial=%s, dtFinal=%s, nmServicoLinha=%s, nmServicoPreCad=%s, nmServicoPlano=%s]", analiseCreditoTOList,
                        cdCor, analiseCreditoTOList.toArray().toString(), idOferta, dsOfertafixa, inFWT, inConvergenteCobre, inConvergenteFibra,
                        inSpeedySoloFibra, inSpeedySoloCobre, inPortab, dtInicial, dtFinal, nmServicoLinha, nmServicoPreCad, nmServicoPlano);
    }

    public AnaliseCreditoTO getAnaliseCreditoTO() {
        return analiseCreditoTO;
    }

    public void setAnaliseCreditoTO(AnaliseCreditoTO analiseCredito) {
        this.analiseCreditoTO = analiseCredito;
    }

    public Integer getIdAnaliseCredito() {
        return idAnaliseCredito;
    }

    public void setIdAnaliseCredito(Integer idAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
    }

    public Boolean getFlagScore() {
        return flagScore;
    }

    public void setFlagScore(Boolean flagScore) {
        this.flagScore = flagScore;
    }

    public void setNomeComercialServico(String nomeComercialServico) {
        this.nomeComercialServico = nomeComercialServico;
    }
    
    public boolean isAllChecked() {
    	return this.analiseCreditoTOList.size() == CollectionUtils.countMatches(this.analiseCreditoTOList, new Predicate() {
			public boolean evaluate(Object obj) {
				return ((AnaliseCreditoTO) obj).getFlagScore();
			}
		});
    }

	public Boolean getPriorizado() {
		return priorizado;
	}

	public void setPriorizado(Boolean priorizado) {
		this.priorizado = priorizado;
	}

	public String getCdOfertafixa() {
		return cdOfertafixa;
	}

	public void setCdOfertafixa(String cdOfertafixa) {
		this.cdOfertafixa = cdOfertafixa;
	}
	
	
}
