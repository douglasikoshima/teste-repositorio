package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

public class AnaliseCreditoLinhaForm extends ValidatorActionForm  implements java.io.Serializable {
    private static final long serialVersionUID = -9013322349069373787L;

    // Campos
    private Integer id; // Var genérico para fazer pesquisas por ID
    private Integer idOferta; // ID da oferta
    private String nomeOferta; // Nome da oferta
    private String dsOfertafixa; // Descrição da Oferta
    private Integer idServico; // ID do serviço
    private String nomeServico; // Nome do serviço (tipo Linha)
    private String nomeComercialServico; // Nome comercial do serviço
    private String cdServico; // Código do serviço
    private int score; // Var para realizar pesquisar por determinado número de score
    private String scoresListAdicionar; // Var para armazenar a lista que será persistida no DAO
    private String scoresListRemover; // Var para armazenar a lista que será persistida no DAO
    
    private String comboServicoLinha;
    private String comboScore;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public void setNomeComercialServico(String nomeComercialServico) {
        this.nomeComercialServico = nomeComercialServico;
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

    public String getNomeOferta() {
        return nomeOferta;
    }

    public void setNomeOferta(String nomeOferta) {
        this.nomeOferta = nomeOferta;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getDsOfertafixa() {
        return dsOfertafixa;
    }

    public void setDsOfertafixa(String dsOfertafixa) {
        this.dsOfertafixa = dsOfertafixa;
    }

    public String getComboServicoLinha() {
		return comboServicoLinha;
	}

	public void setComboServicoLinha(String comboServicoLinha) {
		this.comboServicoLinha = comboServicoLinha;
	}

	public String getComboScore() {
		return comboScore;
	}

	public void setComboScore(String comboScore) {
		this.comboScore = comboScore;
	}

	public AnaliseCreditoLinhaTO buildTO() throws CatalogoPRSException {
        AnaliseCreditoLinhaTO analiseCreditoLinhaTO = new AnaliseCreditoLinhaTO();
        analiseCreditoLinhaTO.setId(this.id);
        analiseCreditoLinhaTO.setIdOferta(this.idOferta);
        analiseCreditoLinhaTO.setIdServico(this.idServico);
        analiseCreditoLinhaTO.setDsOfertafixa(this.dsOfertafixa);
        analiseCreditoLinhaTO.setIdServico(this.idServico);
        analiseCreditoLinhaTO.setScore(this.score);
        analiseCreditoLinhaTO.setScoresListAdicionar(this.scoresListAdicionar);
        analiseCreditoLinhaTO.setScoresListRemover(this.scoresListRemover);
        return analiseCreditoLinhaTO;
    }
}
