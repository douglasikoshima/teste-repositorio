package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import java.util.List;
import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ParametrosLinhaTO extends BaseTransferObject {

    public ParametrosLinhaTO() {
    }

    private static final long serialVersionUID = -1307042197890032386L;

    private Integer           idUser;
    private Integer           idPessoa;
    private Integer           idPessoaDePara;
    private Integer           idLinhaTelefonica;
    private Integer           idTipoLinha;
    private Integer           nrLinha;
    private Integer           cdAreaRegistro;
    private Integer           idConta;
    private Integer           cdConta;
    private String            idLinhaSistemaOrigem;
    private String            idContaSistemaOrigem;
    private String            nmPessoa;
    private Integer           idTipoPessoa;
    private Integer           idCanal;
    private Integer           idProcedencia;
    private String            sgTipoCarteira;
    private String            dsTipoCarteira;
    private boolean           inCorporativo;
    private String            sgTipoPessoa;
    private String            dsTipoPessoa;
    private Integer           idSegmentacao;
    private String            dsSegmentacao;
    private Integer           idTipoCarteira;
    private String            sgTipoRelacionamento;
    private String            nmTipoRelacionamento;
    private Integer           idUFOperadora;
    private Long              idAtendimento;
    private String            nmPath;
    private List              linhasAssociadas;

    public Integer getCdAreaRegistro() {
        return cdAreaRegistro;
    }

    public void setCdAreaRegistro(Integer cdAreaRegistro) {
        this.cdAreaRegistro = cdAreaRegistro;
    }

    public Integer getCdConta() {
        return cdConta;
    }

    public void setCdConta(Integer cdConta) {
        this.cdConta = cdConta;
    }

    public String getDsSegmentacao() {
        return dsSegmentacao;
    }

    public void setDsSegmentacao(String dsSegmentacao) {
        this.dsSegmentacao = dsSegmentacao;
    }

    public String getDsTipoCarteira() {
        return dsTipoCarteira;
    }

    public void setDsTipoCarteira(String dsTipoCarteira) {
        this.dsTipoCarteira = dsTipoCarteira;
    }

    public String getDsTipoPessoa() {
        return dsTipoPessoa;
    }

    public void setDsTipoPessoa(String dsTipoPessoa) {
        this.dsTipoPessoa = dsTipoPessoa;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getIdContaSistemaOrigem() {
        return idContaSistemaOrigem;
    }

    public void setIdContaSistemaOrigem(String idContaSistemaOrigem) {
        this.idContaSistemaOrigem = idContaSistemaOrigem;
    }

    public String getIdLinhaSistemaOrigem() {
        return idLinhaSistemaOrigem;
    }

    public void setIdLinhaSistemaOrigem(String idLinhaSistemaOrigem) {
        this.idLinhaSistemaOrigem = idLinhaSistemaOrigem;
    }

    public Integer getIdLinhaTelefonica() {
        return idLinhaTelefonica;
    }

    public void setIdLinhaTelefonica(Integer idLinhaTelefonica) {
        this.idLinhaTelefonica = idLinhaTelefonica;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Integer getIdPessoaDePara() {
        return idPessoaDePara;
    }

    public void setIdPessoaDePara(Integer idPessoaDePara) {
        this.idPessoaDePara = idPessoaDePara;
    }

    public Integer getIdSegmentacao() {
        return idSegmentacao;
    }

    public void setIdSegmentacao(Integer idSegmentacao) {
        this.idSegmentacao = idSegmentacao;
    }

    public Integer getIdTipoCarteira() {
        return idTipoCarteira;
    }

    public void setIdTipoCarteira(Integer idTipoCarteira) {
        this.idTipoCarteira = idTipoCarteira;
    }

    public Integer getIdTipoLinha() {
        return idTipoLinha;
    }

    public void setIdTipoLinha(Integer idTipoLinha) {
        this.idTipoLinha = idTipoLinha;
    }

    public Integer getIdTipoPessoa() {
        return idTipoPessoa;
    }

    public void setIdTipoPessoa(Integer idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    public Integer getIdUFOperadora() {
        return idUFOperadora;
    }

    public void setIdUFOperadora(Integer idUFOperadora) {
        this.idUFOperadora = idUFOperadora;
    }

    public boolean isInCorporativo() {
        return inCorporativo;
    }

    public void setInCorporativo(boolean inCorporativo) {
        this.inCorporativo = inCorporativo;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getNmTipoRelacionamento() {
        return nmTipoRelacionamento;
    }

    public void setNmTipoRelacionamento(String nmTipoRelacionamento) {
        this.nmTipoRelacionamento = nmTipoRelacionamento;
    }

    public Integer getNrLinha() {
        return nrLinha;
    }

    public void setNrLinha(Integer nrLinha) {
        this.nrLinha = nrLinha;
    }

    public String getSgTipoCarteira() {
        return sgTipoCarteira;
    }

    public void setSgTipoCarteira(String sgTipoCarteira) {
        this.sgTipoCarteira = sgTipoCarteira;
    }

    public String getSgTipoPessoa() {
        return sgTipoPessoa;
    }

    public void setSgTipoPessoa(String sgTipoPessoa) {
        this.sgTipoPessoa = sgTipoPessoa;
    }

    public String getSgTipoRelacionamento() {
        return sgTipoRelacionamento;
    }

    public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }

    public Integer getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(Integer idCanal) {
        this.idCanal = idCanal;
    }

    public Integer getIdProcedencia() {
        return idProcedencia;
    }

    public void setIdProcedencia(Integer idProcedencia) {
        this.idProcedencia = idProcedencia;
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public String getNmPath() {
        return nmPath;
    }

    public void setNmPath(String nmPath) {
        this.nmPath = nmPath;
    }

    public List getLinhasAssociadas() {
        return linhasAssociadas;
    }

    public void setLinhasAssociadas(List linhasAssociadas) {
        this.linhasAssociadas = linhasAssociadas;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

}
