package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class ConsultaAdimplenciaFormBean extends ActionForm {

    private static final long serialVersionUID = 2083947505801142836L;
    private String            statusAvaliacao;
    private String            nrDocumento;
    private String            dtRequisicao;
    private String            dtResposta;
    private String            dsHistorico;
    private String            dtInterrupcao;
    private String            dsMotivoErro;
    private String            dsIntencaoCancelamento;
    private String            dsDestinoPrevisto;
    private String            dsModelo;
    private String            dsMaterial;
    private String            nmCor;
    private String            vlPreco;
    private String            dsMeioPagamento;
    private String            idTipoPagamento;
    private String            vlPercentualDesconto;
    private String            nrParcelas;
    private String            vlParcela;
    private String            sgOferta;
    private String            vlPrecoComDesconto;
    private String            nrOrdemVenda;
    private String            dsObsAnalista;
    private String            idRetencao;
    private String            dsMotivoAlteracaoEndereco;
    private String            idMotivoAlteracaoEndereco;
    private boolean           inBloquearEdicao = false;

    public boolean getInBloquearEdicao() {
        return this.inBloquearEdicao;
    }

    public void setInBloquearEdicao(boolean inBloquearEdicao) {
        this.inBloquearEdicao = inBloquearEdicao;
    }

    public String getDsMaterial() {
        if (this.dsMaterial == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsMaterial;
        }
    }

    public void setDsMaterial(String dsMaterial) {
        this.dsMaterial = dsMaterial;
    }

    public String getSgOferta() {
        if (this.sgOferta == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.sgOferta;
        }
    }

    public void setSgOferta(String sgOferta) {
        this.sgOferta = sgOferta;
    }

    public String getIdTipoPagamento() {
        if (this.idTipoPagamento == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.idTipoPagamento;
        }
    }

    public void setIdTipoPagamento(String idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public String getIdMotivoAlteracaoEndereco() {
        if (this.idMotivoAlteracaoEndereco == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.idMotivoAlteracaoEndereco;
        }
    }

    public void setIdMotivoAlteracaoEndereco(String idMotivoAlteracaoEndereco) {
        this.idMotivoAlteracaoEndereco = idMotivoAlteracaoEndereco;
    }

    public String getDsMotivoAlteracaoEndereco() {
        if (this.dsMotivoAlteracaoEndereco == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsMotivoAlteracaoEndereco;
        }
    }

    public void setDsMotivoAlteracaoEndereco(String dsMotivoAlteracaoEndereco) {
        this.dsMotivoAlteracaoEndereco = dsMotivoAlteracaoEndereco;
    }

    public String getIdRetencao() {
        if (this.idRetencao == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.idRetencao;
        }
    }

    public void setIdRetencao(String idRetencao) {
        this.idRetencao = idRetencao;
    }

    public String getDsObsAnalista() {
        if (this.dsObsAnalista == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsObsAnalista;
        }
    }

    public void setDsObsAnalista(String dsObsAnalista) {
        this.dsObsAnalista = dsObsAnalista;
    }

    public String getNrOrdemVenda() {
        if (this.nrOrdemVenda == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.nrOrdemVenda;
        }
    }

    public void setNrOrdemVenda(String nrOrdemVenda) {
        this.nrOrdemVenda = nrOrdemVenda;
    }

    public String getVlPrecoComDesconto() {
        if (this.vlPrecoComDesconto == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.vlPrecoComDesconto;
        }
    }

    public void setVlPrecoComDesconto(String vlPrecoComDesconto) {
        this.vlPrecoComDesconto = vlPrecoComDesconto;
    }

    public String getVlParcela() {
        if (this.vlParcela == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.vlParcela;
        }
    }

    public void setVlParcela(String vlParcela) {
        this.vlParcela = vlParcela;
    }

    public String getNrParcelas() {
        if (this.nrParcelas == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.nrParcelas;
        }
    }

    public void setNrParcelas(String nrParcelas) {
        this.nrParcelas = nrParcelas;
    }

    public String getVlPercentualDesconto() {
        if (this.vlPercentualDesconto == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.vlPercentualDesconto;
        }
    }

    public void setVlPercentualDesconto(String vlPercentualDesconto) {
        this.vlPercentualDesconto = vlPercentualDesconto;
    }

    public String getDsMeioPagamento() {
        if (this.dsMeioPagamento == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsMeioPagamento;
        }
    }

    public void setDsMeioPagamento(String dsMeioPagamento) {
        this.dsMeioPagamento = dsMeioPagamento;
    }

    public String getVlPreco() {
        if (this.vlPreco == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.vlPreco;
        }
    }

    public void setVlPreco(String vlPreco) {
        this.vlPreco = vlPreco;
    }

    public String getDsModelo() {
        if (this.dsModelo == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsModelo;
        }
    }

    public void setDsModelo(String dsModelo) {
        this.dsModelo = dsModelo;
    }

    public String getNmCor() {
        if (this.nmCor == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.nmCor;
        }
    }

    public void setNmCor(String nmCor) {
        this.nmCor = nmCor;
    }

    public String getDsDestinoPrevisto() {
        if (this.dsDestinoPrevisto == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsDestinoPrevisto;
        }
    }

    public void setDsDestinoPrevisto(String dsDestinoPrevisto) {
        this.dsDestinoPrevisto = dsDestinoPrevisto;
    }

    public String getDsIntencaoCancelamento() {
        if (this.dsIntencaoCancelamento == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsIntencaoCancelamento;
        }
    }

    public void setDsIntencaoCancelamento(String dsIntencaoCancelamento) {
        this.dsIntencaoCancelamento = dsIntencaoCancelamento;
    }

    public String getStatusAvaliacao() {
        if (this.statusAvaliacao == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.statusAvaliacao;
        }
    }

    public void setStatusAvaliacao(String statusAvaliacao) {
        this.statusAvaliacao = statusAvaliacao;
    }

    public String getNrDocumento() {
        if (this.nrDocumento == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.nrDocumento;
        }
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public String getDtRequisicao() {
        if (this.dtRequisicao == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dtRequisicao;
        }
    }

    public void setDtRequisicao(String dtRequisicao) {
        this.dtRequisicao = dtRequisicao;
    }

    public String getDtResposta() {
        if (this.dtResposta == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dtResposta;
        }
    }

    public void setDtResposta(String dtResposta) {
        this.dtResposta = dtResposta;
    }

    public String getDsHistorico() {
        if (this.dsHistorico == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsHistorico;
        }
    }

    public void setDsHistorico(String dsHistorico) {
        this.dsHistorico = dsHistorico;
    }

    public String getDtInterrupcao() {
        if (this.dtInterrupcao == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dtInterrupcao;
        }
    }

    public void setDtInterrupcao(String dtInterrupcao) {
        this.dtInterrupcao = dtInterrupcao;
    }

    public String getDsMotivoErro() {
        if (this.dsMotivoErro == null) {
            return ConstantesCRM.SVAZIO;
        } else {
            return this.dsMotivoErro;
        }
    }

    public void setDsMotivoErro(String dsMotivoErro) {
        this.dsMotivoErro = dsMotivoErro;
    }

}
