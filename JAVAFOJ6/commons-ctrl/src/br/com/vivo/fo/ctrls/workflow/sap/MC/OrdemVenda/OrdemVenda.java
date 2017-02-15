/**
 * OrdemVenda.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda;

public class OrdemVenda  implements java.io.Serializable {
    /* Numero da ordem de venda gerado pelo sistema
     * 						SAP. */
    private java.lang.String numeroOrdemVenda;

    /* Data de inicio de contrato do cliente com a
     * 						Vivo. */
    private java.util.Date dataInicioContrato;

    /* Data de fim de contrato do cliente com a Vivo. */
    private java.util.Date dataFimContrato;

    /* Numero do pedido (VBKD-BSTKD). Devera encaminhar
     * 						o numero do servico. Para lojas proprias, o SAP
     * 						devera concatenar esta informacao com o
     * 						escritorio de vendas. Ex.: 0000000001L111. */
    private java.lang.String numeroPedido;

    /* Data em que foi realizado o pedido. */
    private java.util.Date dataPedido;

    /* Deve ser enviado no servico de criacao de OV o
     * 						parametro C (So e permitida remessa completa)
     * 						sempre que a tecnologia do produto selecionado
     * 						for GSM. Diferente disso deve ser enviado o
     * 						parametro em Branco = Remessa parcial permitida. */
    private java.lang.String tipoRemessa;

    /* Este campo e obrigatorio, o valor para este
     * 						campo e X. Para o SAP, o programa devera
     * 						verificar os dados do cliente desta string, e
     * 						checar se o mesmo existe ou se os dados sao os
     * 						mesmos ja cadastrados. Se negativo, criar,
     * 						modificar ou expandir o cliente. */
    private java.lang.String emissor;

    /* Este campo e opcional. Se existir um recebedor
     * 						da Fatura diferente do emissor, devera existir
     * 						uma nova string com os dados deste cliente e com
     * 						o X na opcao Recebedor da Fatura. Para o SAP, se
     * 						este campo for preenchido devera ser imputado no
     * 						parceiro Recebedor da Fatura (BP) do cliente
     * 						Emissor */
    private java.lang.String receptorFatura;

    /* Este campo e opcional. Se existir um pagador
     * 						diferente do emissor, devera existir uma nova
     * 						string com os dados deste cliente e com o X na
     * 						opcao Pagador. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Pagador (PY) do cliente Emissor. */
    private java.lang.String pagador;

    /* Este campo e opcional. Se existir um recebedor
     * 						de mercadoria diferente do emissor, devera
     * 						existir uma nova string com os dados deste
     * 						cliente e com o X na opcao Recebedor da
     * 						Mercadoria. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Recebedor da Mercadoria (WE) do cliente Emissor. */
    private java.lang.String recebedorMercadoria;

    /* Sera informado neste campo o nome do
     * 						responsavel, RG e Telefone para entrega. */
    private java.lang.String condicaoEntrega;

    /* Numero de autorizacao do cartao de credito,
     * 						normalmente esta informacao vira na modificacao
     * 						da ordem de vendas. */
    private java.lang.String nsuCartao;

    /* Data para determinacao de preco. */
    private java.util.Date dataDeterminacaoPreco;

    /* Data proposta na qual o cliente deve receber o
     * 						fornecimento da sua mercadoria. */
    private java.util.Date dataRemessa;

    /* Deve ser utilizada a observacao informada na
     * 						Tela Resumo da Solicitacao de cada
     * 						funcionalidade */
    private java.lang.String observacao;

    /* Data de abertura da ordem de venda */
    private java.util.Calendar dataAbertura;

    /* Identifica os possiveis status do pedido,
     * 						podendo ser por exemplo: ANALISE DE
     * 						CANCELAMENTO, AVARIA TOTAL, EM TRANSITO, etc. */
    private java.lang.String statusPedido;

    /* Numero do documento de faturamento, criado apos
     * 						a geracao da OV. */
    private java.lang.String docFaturamento;

    /* Indicador de corrupcao da ordem de venda. Se o
     * 						valor for diferente de 0, os detalhes do pedido
     * 						nao poderao ser exibidos. Nesse caso, o detalhe
     * 						do pedido devera ser consultado diretamente no
     * 						SAP. 0 (false) - ordem de venda OK; 1 (true) -
     * 						ordem de venda corrompida. */
    private java.lang.Boolean ordemVendaCorrompida;

    private br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.TrackingAparelho trackingAparelho;

    private br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.ContratoPJ contratoPJ;

    public OrdemVenda() {
    }

    public OrdemVenda(
           java.lang.String numeroOrdemVenda,
           java.util.Date dataInicioContrato,
           java.util.Date dataFimContrato,
           java.lang.String numeroPedido,
           java.util.Date dataPedido,
           java.lang.String tipoRemessa,
           java.lang.String emissor,
           java.lang.String receptorFatura,
           java.lang.String pagador,
           java.lang.String recebedorMercadoria,
           java.lang.String condicaoEntrega,
           java.lang.String nsuCartao,
           java.util.Date dataDeterminacaoPreco,
           java.util.Date dataRemessa,
           java.lang.String observacao,
           java.util.Calendar dataAbertura,
           java.lang.String statusPedido,
           java.lang.String docFaturamento,
           java.lang.Boolean ordemVendaCorrompida,
           br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.TrackingAparelho trackingAparelho,
           br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.ContratoPJ contratoPJ) {
           this.numeroOrdemVenda = numeroOrdemVenda;
           this.dataInicioContrato = dataInicioContrato;
           this.dataFimContrato = dataFimContrato;
           this.numeroPedido = numeroPedido;
           this.dataPedido = dataPedido;
           this.tipoRemessa = tipoRemessa;
           this.emissor = emissor;
           this.receptorFatura = receptorFatura;
           this.pagador = pagador;
           this.recebedorMercadoria = recebedorMercadoria;
           this.condicaoEntrega = condicaoEntrega;
           this.nsuCartao = nsuCartao;
           this.dataDeterminacaoPreco = dataDeterminacaoPreco;
           this.dataRemessa = dataRemessa;
           this.observacao = observacao;
           this.dataAbertura = dataAbertura;
           this.statusPedido = statusPedido;
           this.docFaturamento = docFaturamento;
           this.ordemVendaCorrompida = ordemVendaCorrompida;
           this.trackingAparelho = trackingAparelho;
           this.contratoPJ = contratoPJ;
    }


    /**
     * Gets the numeroOrdemVenda value for this OrdemVenda.
     * 
     * @return numeroOrdemVenda   * Numero da ordem de venda gerado pelo sistema
     * 						SAP.
     */
    public java.lang.String getNumeroOrdemVenda() {
        return numeroOrdemVenda;
    }


    /**
     * Sets the numeroOrdemVenda value for this OrdemVenda.
     * 
     * @param numeroOrdemVenda   * Numero da ordem de venda gerado pelo sistema
     * 						SAP.
     */
    public void setNumeroOrdemVenda(java.lang.String numeroOrdemVenda) {
        this.numeroOrdemVenda = numeroOrdemVenda;
    }


    /**
     * Gets the dataInicioContrato value for this OrdemVenda.
     * 
     * @return dataInicioContrato   * Data de inicio de contrato do cliente com a
     * 						Vivo.
     */
    public java.util.Date getDataInicioContrato() {
        return dataInicioContrato;
    }


    /**
     * Sets the dataInicioContrato value for this OrdemVenda.
     * 
     * @param dataInicioContrato   * Data de inicio de contrato do cliente com a
     * 						Vivo.
     */
    public void setDataInicioContrato(java.util.Date dataInicioContrato) {
        this.dataInicioContrato = dataInicioContrato;
    }


    /**
     * Gets the dataFimContrato value for this OrdemVenda.
     * 
     * @return dataFimContrato   * Data de fim de contrato do cliente com a Vivo.
     */
    public java.util.Date getDataFimContrato() {
        return dataFimContrato;
    }


    /**
     * Sets the dataFimContrato value for this OrdemVenda.
     * 
     * @param dataFimContrato   * Data de fim de contrato do cliente com a Vivo.
     */
    public void setDataFimContrato(java.util.Date dataFimContrato) {
        this.dataFimContrato = dataFimContrato;
    }


    /**
     * Gets the numeroPedido value for this OrdemVenda.
     * 
     * @return numeroPedido   * Numero do pedido (VBKD-BSTKD). Devera encaminhar
     * 						o numero do servico. Para lojas proprias, o SAP
     * 						devera concatenar esta informacao com o
     * 						escritorio de vendas. Ex.: 0000000001L111.
     */
    public java.lang.String getNumeroPedido() {
        return numeroPedido;
    }


    /**
     * Sets the numeroPedido value for this OrdemVenda.
     * 
     * @param numeroPedido   * Numero do pedido (VBKD-BSTKD). Devera encaminhar
     * 						o numero do servico. Para lojas proprias, o SAP
     * 						devera concatenar esta informacao com o
     * 						escritorio de vendas. Ex.: 0000000001L111.
     */
    public void setNumeroPedido(java.lang.String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }


    /**
     * Gets the dataPedido value for this OrdemVenda.
     * 
     * @return dataPedido   * Data em que foi realizado o pedido.
     */
    public java.util.Date getDataPedido() {
        return dataPedido;
    }


    /**
     * Sets the dataPedido value for this OrdemVenda.
     * 
     * @param dataPedido   * Data em que foi realizado o pedido.
     */
    public void setDataPedido(java.util.Date dataPedido) {
        this.dataPedido = dataPedido;
    }


    /**
     * Gets the tipoRemessa value for this OrdemVenda.
     * 
     * @return tipoRemessa   * Deve ser enviado no servico de criacao de OV o
     * 						parametro C (So e permitida remessa completa)
     * 						sempre que a tecnologia do produto selecionado
     * 						for GSM. Diferente disso deve ser enviado o
     * 						parametro em Branco = Remessa parcial permitida.
     */
    public java.lang.String getTipoRemessa() {
        return tipoRemessa;
    }


    /**
     * Sets the tipoRemessa value for this OrdemVenda.
     * 
     * @param tipoRemessa   * Deve ser enviado no servico de criacao de OV o
     * 						parametro C (So e permitida remessa completa)
     * 						sempre que a tecnologia do produto selecionado
     * 						for GSM. Diferente disso deve ser enviado o
     * 						parametro em Branco = Remessa parcial permitida.
     */
    public void setTipoRemessa(java.lang.String tipoRemessa) {
        this.tipoRemessa = tipoRemessa;
    }


    /**
     * Gets the emissor value for this OrdemVenda.
     * 
     * @return emissor   * Este campo e obrigatorio, o valor para este
     * 						campo e X. Para o SAP, o programa devera
     * 						verificar os dados do cliente desta string, e
     * 						checar se o mesmo existe ou se os dados sao os
     * 						mesmos ja cadastrados. Se negativo, criar,
     * 						modificar ou expandir o cliente.
     */
    public java.lang.String getEmissor() {
        return emissor;
    }


    /**
     * Sets the emissor value for this OrdemVenda.
     * 
     * @param emissor   * Este campo e obrigatorio, o valor para este
     * 						campo e X. Para o SAP, o programa devera
     * 						verificar os dados do cliente desta string, e
     * 						checar se o mesmo existe ou se os dados sao os
     * 						mesmos ja cadastrados. Se negativo, criar,
     * 						modificar ou expandir o cliente.
     */
    public void setEmissor(java.lang.String emissor) {
        this.emissor = emissor;
    }


    /**
     * Gets the receptorFatura value for this OrdemVenda.
     * 
     * @return receptorFatura   * Este campo e opcional. Se existir um recebedor
     * 						da Fatura diferente do emissor, devera existir
     * 						uma nova string com os dados deste cliente e com
     * 						o X na opcao Recebedor da Fatura. Para o SAP, se
     * 						este campo for preenchido devera ser imputado no
     * 						parceiro Recebedor da Fatura (BP) do cliente
     * 						Emissor
     */
    public java.lang.String getReceptorFatura() {
        return receptorFatura;
    }


    /**
     * Sets the receptorFatura value for this OrdemVenda.
     * 
     * @param receptorFatura   * Este campo e opcional. Se existir um recebedor
     * 						da Fatura diferente do emissor, devera existir
     * 						uma nova string com os dados deste cliente e com
     * 						o X na opcao Recebedor da Fatura. Para o SAP, se
     * 						este campo for preenchido devera ser imputado no
     * 						parceiro Recebedor da Fatura (BP) do cliente
     * 						Emissor
     */
    public void setReceptorFatura(java.lang.String receptorFatura) {
        this.receptorFatura = receptorFatura;
    }


    /**
     * Gets the pagador value for this OrdemVenda.
     * 
     * @return pagador   * Este campo e opcional. Se existir um pagador
     * 						diferente do emissor, devera existir uma nova
     * 						string com os dados deste cliente e com o X na
     * 						opcao Pagador. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Pagador (PY) do cliente Emissor.
     */
    public java.lang.String getPagador() {
        return pagador;
    }


    /**
     * Sets the pagador value for this OrdemVenda.
     * 
     * @param pagador   * Este campo e opcional. Se existir um pagador
     * 						diferente do emissor, devera existir uma nova
     * 						string com os dados deste cliente e com o X na
     * 						opcao Pagador. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Pagador (PY) do cliente Emissor.
     */
    public void setPagador(java.lang.String pagador) {
        this.pagador = pagador;
    }


    /**
     * Gets the recebedorMercadoria value for this OrdemVenda.
     * 
     * @return recebedorMercadoria   * Este campo e opcional. Se existir um recebedor
     * 						de mercadoria diferente do emissor, devera
     * 						existir uma nova string com os dados deste
     * 						cliente e com o X na opcao Recebedor da
     * 						Mercadoria. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Recebedor da Mercadoria (WE) do cliente Emissor.
     */
    public java.lang.String getRecebedorMercadoria() {
        return recebedorMercadoria;
    }


    /**
     * Sets the recebedorMercadoria value for this OrdemVenda.
     * 
     * @param recebedorMercadoria   * Este campo e opcional. Se existir um recebedor
     * 						de mercadoria diferente do emissor, devera
     * 						existir uma nova string com os dados deste
     * 						cliente e com o X na opcao Recebedor da
     * 						Mercadoria. Para o SAP, se este campo for
     * 						preenchido devera ser imputado no parceiro
     * 						Recebedor da Mercadoria (WE) do cliente Emissor.
     */
    public void setRecebedorMercadoria(java.lang.String recebedorMercadoria) {
        this.recebedorMercadoria = recebedorMercadoria;
    }


    /**
     * Gets the condicaoEntrega value for this OrdemVenda.
     * 
     * @return condicaoEntrega   * Sera informado neste campo o nome do
     * 						responsavel, RG e Telefone para entrega.
     */
    public java.lang.String getCondicaoEntrega() {
        return condicaoEntrega;
    }


    /**
     * Sets the condicaoEntrega value for this OrdemVenda.
     * 
     * @param condicaoEntrega   * Sera informado neste campo o nome do
     * 						responsavel, RG e Telefone para entrega.
     */
    public void setCondicaoEntrega(java.lang.String condicaoEntrega) {
        this.condicaoEntrega = condicaoEntrega;
    }


    /**
     * Gets the nsuCartao value for this OrdemVenda.
     * 
     * @return nsuCartao   * Numero de autorizacao do cartao de credito,
     * 						normalmente esta informacao vira na modificacao
     * 						da ordem de vendas.
     */
    public java.lang.String getNsuCartao() {
        return nsuCartao;
    }


    /**
     * Sets the nsuCartao value for this OrdemVenda.
     * 
     * @param nsuCartao   * Numero de autorizacao do cartao de credito,
     * 						normalmente esta informacao vira na modificacao
     * 						da ordem de vendas.
     */
    public void setNsuCartao(java.lang.String nsuCartao) {
        this.nsuCartao = nsuCartao;
    }


    /**
     * Gets the dataDeterminacaoPreco value for this OrdemVenda.
     * 
     * @return dataDeterminacaoPreco   * Data para determinacao de preco.
     */
    public java.util.Date getDataDeterminacaoPreco() {
        return dataDeterminacaoPreco;
    }


    /**
     * Sets the dataDeterminacaoPreco value for this OrdemVenda.
     * 
     * @param dataDeterminacaoPreco   * Data para determinacao de preco.
     */
    public void setDataDeterminacaoPreco(java.util.Date dataDeterminacaoPreco) {
        this.dataDeterminacaoPreco = dataDeterminacaoPreco;
    }


    /**
     * Gets the dataRemessa value for this OrdemVenda.
     * 
     * @return dataRemessa   * Data proposta na qual o cliente deve receber o
     * 						fornecimento da sua mercadoria.
     */
    public java.util.Date getDataRemessa() {
        return dataRemessa;
    }


    /**
     * Sets the dataRemessa value for this OrdemVenda.
     * 
     * @param dataRemessa   * Data proposta na qual o cliente deve receber o
     * 						fornecimento da sua mercadoria.
     */
    public void setDataRemessa(java.util.Date dataRemessa) {
        this.dataRemessa = dataRemessa;
    }


    /**
     * Gets the observacao value for this OrdemVenda.
     * 
     * @return observacao   * Deve ser utilizada a observacao informada na
     * 						Tela Resumo da Solicitacao de cada
     * 						funcionalidade
     */
    public java.lang.String getObservacao() {
        return observacao;
    }


    /**
     * Sets the observacao value for this OrdemVenda.
     * 
     * @param observacao   * Deve ser utilizada a observacao informada na
     * 						Tela Resumo da Solicitacao de cada
     * 						funcionalidade
     */
    public void setObservacao(java.lang.String observacao) {
        this.observacao = observacao;
    }


    /**
     * Gets the dataAbertura value for this OrdemVenda.
     * 
     * @return dataAbertura   * Data de abertura da ordem de venda
     */
    public java.util.Calendar getDataAbertura() {
        return dataAbertura;
    }


    /**
     * Sets the dataAbertura value for this OrdemVenda.
     * 
     * @param dataAbertura   * Data de abertura da ordem de venda
     */
    public void setDataAbertura(java.util.Calendar dataAbertura) {
        this.dataAbertura = dataAbertura;
    }


    /**
     * Gets the statusPedido value for this OrdemVenda.
     * 
     * @return statusPedido   * Identifica os possiveis status do pedido,
     * 						podendo ser por exemplo: ANALISE DE
     * 						CANCELAMENTO, AVARIA TOTAL, EM TRANSITO, etc.
     */
    public java.lang.String getStatusPedido() {
        return statusPedido;
    }


    /**
     * Sets the statusPedido value for this OrdemVenda.
     * 
     * @param statusPedido   * Identifica os possiveis status do pedido,
     * 						podendo ser por exemplo: ANALISE DE
     * 						CANCELAMENTO, AVARIA TOTAL, EM TRANSITO, etc.
     */
    public void setStatusPedido(java.lang.String statusPedido) {
        this.statusPedido = statusPedido;
    }


    /**
     * Gets the docFaturamento value for this OrdemVenda.
     * 
     * @return docFaturamento   * Numero do documento de faturamento, criado apos
     * 						a geracao da OV.
     */
    public java.lang.String getDocFaturamento() {
        return docFaturamento;
    }


    /**
     * Sets the docFaturamento value for this OrdemVenda.
     * 
     * @param docFaturamento   * Numero do documento de faturamento, criado apos
     * 						a geracao da OV.
     */
    public void setDocFaturamento(java.lang.String docFaturamento) {
        this.docFaturamento = docFaturamento;
    }


    /**
     * Gets the ordemVendaCorrompida value for this OrdemVenda.
     * 
     * @return ordemVendaCorrompida   * Indicador de corrupcao da ordem de venda. Se o
     * 						valor for diferente de 0, os detalhes do pedido
     * 						nao poderao ser exibidos. Nesse caso, o detalhe
     * 						do pedido devera ser consultado diretamente no
     * 						SAP. 0 (false) - ordem de venda OK; 1 (true) -
     * 						ordem de venda corrompida.
     */
    public java.lang.Boolean getOrdemVendaCorrompida() {
        return ordemVendaCorrompida;
    }


    /**
     * Sets the ordemVendaCorrompida value for this OrdemVenda.
     * 
     * @param ordemVendaCorrompida   * Indicador de corrupcao da ordem de venda. Se o
     * 						valor for diferente de 0, os detalhes do pedido
     * 						nao poderao ser exibidos. Nesse caso, o detalhe
     * 						do pedido devera ser consultado diretamente no
     * 						SAP. 0 (false) - ordem de venda OK; 1 (true) -
     * 						ordem de venda corrompida.
     */
    public void setOrdemVendaCorrompida(java.lang.Boolean ordemVendaCorrompida) {
        this.ordemVendaCorrompida = ordemVendaCorrompida;
    }


    /**
     * Gets the trackingAparelho value for this OrdemVenda.
     * 
     * @return trackingAparelho
     */
    public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.TrackingAparelho getTrackingAparelho() {
        return trackingAparelho;
    }


    /**
     * Sets the trackingAparelho value for this OrdemVenda.
     * 
     * @param trackingAparelho
     */
    public void setTrackingAparelho(br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.TrackingAparelho trackingAparelho) {
        this.trackingAparelho = trackingAparelho;
    }


    /**
     * Gets the contratoPJ value for this OrdemVenda.
     * 
     * @return contratoPJ
     */
    public br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.ContratoPJ getContratoPJ() {
        return contratoPJ;
    }


    /**
     * Sets the contratoPJ value for this OrdemVenda.
     * 
     * @param contratoPJ
     */
    public void setContratoPJ(br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda.ContratoPJ contratoPJ) {
        this.contratoPJ = contratoPJ;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OrdemVenda)) return false;
        OrdemVenda other = (OrdemVenda) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroOrdemVenda==null && other.getNumeroOrdemVenda()==null) || 
             (this.numeroOrdemVenda!=null &&
              this.numeroOrdemVenda.equals(other.getNumeroOrdemVenda()))) &&
            ((this.dataInicioContrato==null && other.getDataInicioContrato()==null) || 
             (this.dataInicioContrato!=null &&
              this.dataInicioContrato.equals(other.getDataInicioContrato()))) &&
            ((this.dataFimContrato==null && other.getDataFimContrato()==null) || 
             (this.dataFimContrato!=null &&
              this.dataFimContrato.equals(other.getDataFimContrato()))) &&
            ((this.numeroPedido==null && other.getNumeroPedido()==null) || 
             (this.numeroPedido!=null &&
              this.numeroPedido.equals(other.getNumeroPedido()))) &&
            ((this.dataPedido==null && other.getDataPedido()==null) || 
             (this.dataPedido!=null &&
              this.dataPedido.equals(other.getDataPedido()))) &&
            ((this.tipoRemessa==null && other.getTipoRemessa()==null) || 
             (this.tipoRemessa!=null &&
              this.tipoRemessa.equals(other.getTipoRemessa()))) &&
            ((this.emissor==null && other.getEmissor()==null) || 
             (this.emissor!=null &&
              this.emissor.equals(other.getEmissor()))) &&
            ((this.receptorFatura==null && other.getReceptorFatura()==null) || 
             (this.receptorFatura!=null &&
              this.receptorFatura.equals(other.getReceptorFatura()))) &&
            ((this.pagador==null && other.getPagador()==null) || 
             (this.pagador!=null &&
              this.pagador.equals(other.getPagador()))) &&
            ((this.recebedorMercadoria==null && other.getRecebedorMercadoria()==null) || 
             (this.recebedorMercadoria!=null &&
              this.recebedorMercadoria.equals(other.getRecebedorMercadoria()))) &&
            ((this.condicaoEntrega==null && other.getCondicaoEntrega()==null) || 
             (this.condicaoEntrega!=null &&
              this.condicaoEntrega.equals(other.getCondicaoEntrega()))) &&
            ((this.nsuCartao==null && other.getNsuCartao()==null) || 
             (this.nsuCartao!=null &&
              this.nsuCartao.equals(other.getNsuCartao()))) &&
            ((this.dataDeterminacaoPreco==null && other.getDataDeterminacaoPreco()==null) || 
             (this.dataDeterminacaoPreco!=null &&
              this.dataDeterminacaoPreco.equals(other.getDataDeterminacaoPreco()))) &&
            ((this.dataRemessa==null && other.getDataRemessa()==null) || 
             (this.dataRemessa!=null &&
              this.dataRemessa.equals(other.getDataRemessa()))) &&
            ((this.observacao==null && other.getObservacao()==null) || 
             (this.observacao!=null &&
              this.observacao.equals(other.getObservacao()))) &&
            ((this.dataAbertura==null && other.getDataAbertura()==null) || 
             (this.dataAbertura!=null &&
              this.dataAbertura.equals(other.getDataAbertura()))) &&
            ((this.statusPedido==null && other.getStatusPedido()==null) || 
             (this.statusPedido!=null &&
              this.statusPedido.equals(other.getStatusPedido()))) &&
            ((this.docFaturamento==null && other.getDocFaturamento()==null) || 
             (this.docFaturamento!=null &&
              this.docFaturamento.equals(other.getDocFaturamento()))) &&
            ((this.ordemVendaCorrompida==null && other.getOrdemVendaCorrompida()==null) || 
             (this.ordemVendaCorrompida!=null &&
              this.ordemVendaCorrompida.equals(other.getOrdemVendaCorrompida()))) &&
            ((this.trackingAparelho==null && other.getTrackingAparelho()==null) || 
             (this.trackingAparelho!=null &&
              this.trackingAparelho.equals(other.getTrackingAparelho()))) &&
            ((this.contratoPJ==null && other.getContratoPJ()==null) || 
             (this.contratoPJ!=null &&
              this.contratoPJ.equals(other.getContratoPJ())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNumeroOrdemVenda() != null) {
            _hashCode += getNumeroOrdemVenda().hashCode();
        }
        if (getDataInicioContrato() != null) {
            _hashCode += getDataInicioContrato().hashCode();
        }
        if (getDataFimContrato() != null) {
            _hashCode += getDataFimContrato().hashCode();
        }
        if (getNumeroPedido() != null) {
            _hashCode += getNumeroPedido().hashCode();
        }
        if (getDataPedido() != null) {
            _hashCode += getDataPedido().hashCode();
        }
        if (getTipoRemessa() != null) {
            _hashCode += getTipoRemessa().hashCode();
        }
        if (getEmissor() != null) {
            _hashCode += getEmissor().hashCode();
        }
        if (getReceptorFatura() != null) {
            _hashCode += getReceptorFatura().hashCode();
        }
        if (getPagador() != null) {
            _hashCode += getPagador().hashCode();
        }
        if (getRecebedorMercadoria() != null) {
            _hashCode += getRecebedorMercadoria().hashCode();
        }
        if (getCondicaoEntrega() != null) {
            _hashCode += getCondicaoEntrega().hashCode();
        }
        if (getNsuCartao() != null) {
            _hashCode += getNsuCartao().hashCode();
        }
        if (getDataDeterminacaoPreco() != null) {
            _hashCode += getDataDeterminacaoPreco().hashCode();
        }
        if (getDataRemessa() != null) {
            _hashCode += getDataRemessa().hashCode();
        }
        if (getObservacao() != null) {
            _hashCode += getObservacao().hashCode();
        }
        if (getDataAbertura() != null) {
            _hashCode += getDataAbertura().hashCode();
        }
        if (getStatusPedido() != null) {
            _hashCode += getStatusPedido().hashCode();
        }
        if (getDocFaturamento() != null) {
            _hashCode += getDocFaturamento().hashCode();
        }
        if (getOrdemVendaCorrompida() != null) {
            _hashCode += getOrdemVendaCorrompida().hashCode();
        }
        if (getTrackingAparelho() != null) {
            _hashCode += getTrackingAparelho().hashCode();
        }
        if (getContratoPJ() != null) {
            _hashCode += getContratoPJ().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OrdemVenda.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "OrdemVenda"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroOrdemVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroOrdemVenda"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInicioContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataInicioContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFimContrato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataFimContrato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPedido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroPedido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPedido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataPedido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRemessa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "tipoRemessa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emissor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "emissor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receptorFatura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "receptorFatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "pagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recebedorMercadoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "recebedorMercadoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("condicaoEntrega");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "condicaoEntrega"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nsuCartao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "nsuCartao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDeterminacaoPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataDeterminacaoPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRemessa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataRemessa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("observacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "observacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAbertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataAbertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusPedido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "statusPedido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docFaturamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "docFaturamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordemVendaCorrompida");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "ordemVendaCorrompida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingAparelho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "trackingAparelho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "TrackingAparelho"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contratoPJ");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "contratoPJ"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "ContratoPJ"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
