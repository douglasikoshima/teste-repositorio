/**
 * Conta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package  br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta;


/**
 * Mantem informacoes das conta de clientes e prospects.
 */
public class Conta  implements java.io.Serializable {
    /* Codigo que identifica uma conta no vivonet
     * 						(idconta). */
    private java.math.BigInteger codigo;

    /* Codigo que identifica uma conta, no sistema
     * 						origem, por exemplo no Atlys, essa coluna e
     * 						representada pelo account_id. */
    private java.lang.String codigoContaSistemOrigem;

    /* Data de expiracao da conta */
    private java.util.Calendar dataExpiracao;

    /* Data de vencimento de conta. */
    private java.util.Date dataVencimento;

    /* Data de inicio do ciclo. */
    private java.util.Date dataInicialCiclo;

    /* Data de fim do ciclo. */
    private java.util.Date dataFinalCiclo;

    /* Data de alteracao do status de conta. Data de
     * 						inicio de vigencia de um status. */
    private java.util.Calendar dataAlteracaoStatus;

    /* Valor da ultima fatura */
    private java.math.BigDecimal valorUltimaFatura;

    /* Identifica se a conta deve ser enviada por
     * 						e-mail para o cliente. Caso o cliente deseje
     * 						receber a conta por e-mail, esse indicador
     * 						estara com o valor 1 (true). */
    private java.lang.Boolean contaPorEmail;

    /* Indica se a conta e a conta pagadora (utilizado
     * 						para hieraquia de contas). Se a conta for
     * 						pagadora, esse indicador tera o valor 1 (true). */
    private java.lang.Boolean contaPagadora;

    /* Referencia a entidade Ciclo. */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Ciclo ciclo;

    /* Referencia a entidade ClasseCredito */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.ClasseCredito classeCredito;

    /* Referencia a entidade TipoConta */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoConta tipoConta;

    /* Referencia a entidade LayoutConta */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutConta layoutConta;

    /* Referencia a entidade TipoRelatorio */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoRelatorio tipoRelatorio;

    /* Referencia a entidade StatusConta */
    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.StatusConta statusConta;

    private java.lang.Boolean smsContaGerada;

    /* Indica se eh enviado SMS de codigo de barras (1
     * 						- true) ou nao (0 - false) */
    private java.lang.Boolean smsCodigoBarras;

    /* Indica se a conta tem ativo o envio eletronico
     * 						da fatura (true) ou tem ativo o envio por
     * 						correio da fatura (false). */
    private java.lang.Boolean envioEletronicoFatura;

    private java.math.BigInteger qtdFaturasAbertas;

    /* Valor total do saldo devedor de todas as faturas
     * 						em aberto do cliente. */
    private java.math.BigDecimal valorSaldoDevedor;

    private java.lang.Boolean emailContaGerada;

    private java.lang.Boolean enviaBoleto;

    private br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.HierarquiaConta hierarquiaConta;

    private java.lang.Boolean faltaContatoGestor;

    public Conta() {
    }

    public Conta(
           java.math.BigInteger codigo,
           java.lang.String codigoContaSistemOrigem,
           java.util.Calendar dataExpiracao,
           java.util.Date dataVencimento,
           java.util.Date dataInicialCiclo,
           java.util.Date dataFinalCiclo,
           java.util.Calendar dataAlteracaoStatus,
           java.math.BigDecimal valorUltimaFatura,
           java.lang.Boolean contaPorEmail,
           java.lang.Boolean contaPagadora,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Ciclo ciclo,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.ClasseCredito classeCredito,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoConta tipoConta,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutConta layoutConta,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoRelatorio tipoRelatorio,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.StatusConta statusConta,
           java.lang.Boolean smsContaGerada,
           java.lang.Boolean smsCodigoBarras,
           java.lang.Boolean envioEletronicoFatura,
           java.math.BigInteger qtdFaturasAbertas,
           java.math.BigDecimal valorSaldoDevedor,
           java.lang.Boolean emailContaGerada,
           java.lang.Boolean enviaBoleto,
           br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.HierarquiaConta hierarquiaConta,
           java.lang.Boolean faltaContatoGestor) {
           this.codigo = codigo;
           this.codigoContaSistemOrigem = codigoContaSistemOrigem;
           this.dataExpiracao = dataExpiracao;
           this.dataVencimento = dataVencimento;
           this.dataInicialCiclo = dataInicialCiclo;
           this.dataFinalCiclo = dataFinalCiclo;
           this.dataAlteracaoStatus = dataAlteracaoStatus;
           this.valorUltimaFatura = valorUltimaFatura;
           this.contaPorEmail = contaPorEmail;
           this.contaPagadora = contaPagadora;
           this.ciclo = ciclo;
           this.classeCredito = classeCredito;
           this.tipoConta = tipoConta;
           this.layoutConta = layoutConta;
           this.tipoRelatorio = tipoRelatorio;
           this.statusConta = statusConta;
           this.smsContaGerada = smsContaGerada;
           this.smsCodigoBarras = smsCodigoBarras;
           this.envioEletronicoFatura = envioEletronicoFatura;
           this.qtdFaturasAbertas = qtdFaturasAbertas;
           this.valorSaldoDevedor = valorSaldoDevedor;
           this.emailContaGerada = emailContaGerada;
           this.enviaBoleto = enviaBoleto;
           this.hierarquiaConta = hierarquiaConta;
           this.faltaContatoGestor = faltaContatoGestor;
    }


    /**
     * Gets the codigo value for this Conta.
     * 
     * @return codigo   * Codigo que identifica uma conta no vivonet
     * 						(idconta).
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Conta.
     * 
     * @param codigo   * Codigo que identifica uma conta no vivonet
     * 						(idconta).
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the codigoContaSistemOrigem value for this Conta.
     * 
     * @return codigoContaSistemOrigem   * Codigo que identifica uma conta, no sistema
     * 						origem, por exemplo no Atlys, essa coluna e
     * 						representada pelo account_id.
     */
    public java.lang.String getCodigoContaSistemOrigem() {
        return codigoContaSistemOrigem;
    }


    /**
     * Sets the codigoContaSistemOrigem value for this Conta.
     * 
     * @param codigoContaSistemOrigem   * Codigo que identifica uma conta, no sistema
     * 						origem, por exemplo no Atlys, essa coluna e
     * 						representada pelo account_id.
     */
    public void setCodigoContaSistemOrigem(java.lang.String codigoContaSistemOrigem) {
        this.codigoContaSistemOrigem = codigoContaSistemOrigem;
    }


    /**
     * Gets the dataExpiracao value for this Conta.
     * 
     * @return dataExpiracao   * Data de expiracao da conta
     */
    public java.util.Calendar getDataExpiracao() {
        return dataExpiracao;
    }


    /**
     * Sets the dataExpiracao value for this Conta.
     * 
     * @param dataExpiracao   * Data de expiracao da conta
     */
    public void setDataExpiracao(java.util.Calendar dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }


    /**
     * Gets the dataVencimento value for this Conta.
     * 
     * @return dataVencimento   * Data de vencimento de conta.
     */
    public java.util.Date getDataVencimento() {
        return dataVencimento;
    }


    /**
     * Sets the dataVencimento value for this Conta.
     * 
     * @param dataVencimento   * Data de vencimento de conta.
     */
    public void setDataVencimento(java.util.Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }


    /**
     * Gets the dataInicialCiclo value for this Conta.
     * 
     * @return dataInicialCiclo   * Data de inicio do ciclo.
     */
    public java.util.Date getDataInicialCiclo() {
        return dataInicialCiclo;
    }


    /**
     * Sets the dataInicialCiclo value for this Conta.
     * 
     * @param dataInicialCiclo   * Data de inicio do ciclo.
     */
    public void setDataInicialCiclo(java.util.Date dataInicialCiclo) {
        this.dataInicialCiclo = dataInicialCiclo;
    }


    /**
     * Gets the dataFinalCiclo value for this Conta.
     * 
     * @return dataFinalCiclo   * Data de fim do ciclo.
     */
    public java.util.Date getDataFinalCiclo() {
        return dataFinalCiclo;
    }


    /**
     * Sets the dataFinalCiclo value for this Conta.
     * 
     * @param dataFinalCiclo   * Data de fim do ciclo.
     */
    public void setDataFinalCiclo(java.util.Date dataFinalCiclo) {
        this.dataFinalCiclo = dataFinalCiclo;
    }


    /**
     * Gets the dataAlteracaoStatus value for this Conta.
     * 
     * @return dataAlteracaoStatus   * Data de alteracao do status de conta. Data de
     * 						inicio de vigencia de um status.
     */
    public java.util.Calendar getDataAlteracaoStatus() {
        return dataAlteracaoStatus;
    }


    /**
     * Sets the dataAlteracaoStatus value for this Conta.
     * 
     * @param dataAlteracaoStatus   * Data de alteracao do status de conta. Data de
     * 						inicio de vigencia de um status.
     */
    public void setDataAlteracaoStatus(java.util.Calendar dataAlteracaoStatus) {
        this.dataAlteracaoStatus = dataAlteracaoStatus;
    }


    /**
     * Gets the valorUltimaFatura value for this Conta.
     * 
     * @return valorUltimaFatura   * Valor da ultima fatura
     */
    public java.math.BigDecimal getValorUltimaFatura() {
        return valorUltimaFatura;
    }


    /**
     * Sets the valorUltimaFatura value for this Conta.
     * 
     * @param valorUltimaFatura   * Valor da ultima fatura
     */
    public void setValorUltimaFatura(java.math.BigDecimal valorUltimaFatura) {
        this.valorUltimaFatura = valorUltimaFatura;
    }


    /**
     * Gets the contaPorEmail value for this Conta.
     * 
     * @return contaPorEmail   * Identifica se a conta deve ser enviada por
     * 						e-mail para o cliente. Caso o cliente deseje
     * 						receber a conta por e-mail, esse indicador
     * 						estara com o valor 1 (true).
     */
    public java.lang.Boolean getContaPorEmail() {
        return contaPorEmail;
    }


    /**
     * Sets the contaPorEmail value for this Conta.
     * 
     * @param contaPorEmail   * Identifica se a conta deve ser enviada por
     * 						e-mail para o cliente. Caso o cliente deseje
     * 						receber a conta por e-mail, esse indicador
     * 						estara com o valor 1 (true).
     */
    public void setContaPorEmail(java.lang.Boolean contaPorEmail) {
        this.contaPorEmail = contaPorEmail;
    }


    /**
     * Gets the contaPagadora value for this Conta.
     * 
     * @return contaPagadora   * Indica se a conta e a conta pagadora (utilizado
     * 						para hieraquia de contas). Se a conta for
     * 						pagadora, esse indicador tera o valor 1 (true).
     */
    public java.lang.Boolean getContaPagadora() {
        return contaPagadora;
    }


    /**
     * Sets the contaPagadora value for this Conta.
     * 
     * @param contaPagadora   * Indica se a conta e a conta pagadora (utilizado
     * 						para hieraquia de contas). Se a conta for
     * 						pagadora, esse indicador tera o valor 1 (true).
     */
    public void setContaPagadora(java.lang.Boolean contaPagadora) {
        this.contaPagadora = contaPagadora;
    }


    /**
     * Gets the ciclo value for this Conta.
     * 
     * @return ciclo   * Referencia a entidade Ciclo.
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Ciclo getCiclo() {
        return ciclo;
    }


    /**
     * Sets the ciclo value for this Conta.
     * 
     * @param ciclo   * Referencia a entidade Ciclo.
     */
    public void setCiclo(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.Ciclo ciclo) {
        this.ciclo = ciclo;
    }


    /**
     * Gets the classeCredito value for this Conta.
     * 
     * @return classeCredito   * Referencia a entidade ClasseCredito
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.ClasseCredito getClasseCredito() {
        return classeCredito;
    }


    /**
     * Sets the classeCredito value for this Conta.
     * 
     * @param classeCredito   * Referencia a entidade ClasseCredito
     */
    public void setClasseCredito(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.ClasseCredito classeCredito) {
        this.classeCredito = classeCredito;
    }


    /**
     * Gets the tipoConta value for this Conta.
     * 
     * @return tipoConta   * Referencia a entidade TipoConta
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoConta getTipoConta() {
        return tipoConta;
    }


    /**
     * Sets the tipoConta value for this Conta.
     * 
     * @param tipoConta   * Referencia a entidade TipoConta
     */
    public void setTipoConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }


    /**
     * Gets the layoutConta value for this Conta.
     * 
     * @return layoutConta   * Referencia a entidade LayoutConta
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutConta getLayoutConta() {
        return layoutConta;
    }


    /**
     * Sets the layoutConta value for this Conta.
     * 
     * @param layoutConta   * Referencia a entidade LayoutConta
     */
    public void setLayoutConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.LayoutConta layoutConta) {
        this.layoutConta = layoutConta;
    }


    /**
     * Gets the tipoRelatorio value for this Conta.
     * 
     * @return tipoRelatorio   * Referencia a entidade TipoRelatorio
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoRelatorio getTipoRelatorio() {
        return tipoRelatorio;
    }


    /**
     * Sets the tipoRelatorio value for this Conta.
     * 
     * @param tipoRelatorio   * Referencia a entidade TipoRelatorio
     */
    public void setTipoRelatorio(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.TipoRelatorio tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
    }


    /**
     * Gets the statusConta value for this Conta.
     * 
     * @return statusConta   * Referencia a entidade StatusConta
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.StatusConta getStatusConta() {
        return statusConta;
    }


    /**
     * Sets the statusConta value for this Conta.
     * 
     * @param statusConta   * Referencia a entidade StatusConta
     */
    public void setStatusConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.StatusConta statusConta) {
        this.statusConta = statusConta;
    }


    /**
     * Gets the smsContaGerada value for this Conta.
     * 
     * @return smsContaGerada
     */
    public java.lang.Boolean getSmsContaGerada() {
        return smsContaGerada;
    }


    /**
     * Sets the smsContaGerada value for this Conta.
     * 
     * @param smsContaGerada
     */
    public void setSmsContaGerada(java.lang.Boolean smsContaGerada) {
        this.smsContaGerada = smsContaGerada;
    }


    /**
     * Gets the smsCodigoBarras value for this Conta.
     * 
     * @return smsCodigoBarras   * Indica se eh enviado SMS de codigo de barras (1
     * 						- true) ou nao (0 - false)
     */
    public java.lang.Boolean getSmsCodigoBarras() {
        return smsCodigoBarras;
    }


    /**
     * Sets the smsCodigoBarras value for this Conta.
     * 
     * @param smsCodigoBarras   * Indica se eh enviado SMS de codigo de barras (1
     * 						- true) ou nao (0 - false)
     */
    public void setSmsCodigoBarras(java.lang.Boolean smsCodigoBarras) {
        this.smsCodigoBarras = smsCodigoBarras;
    }


    /**
     * Gets the envioEletronicoFatura value for this Conta.
     * 
     * @return envioEletronicoFatura   * Indica se a conta tem ativo o envio eletronico
     * 						da fatura (true) ou tem ativo o envio por
     * 						correio da fatura (false).
     */
    public java.lang.Boolean getEnvioEletronicoFatura() {
        return envioEletronicoFatura;
    }


    /**
     * Sets the envioEletronicoFatura value for this Conta.
     * 
     * @param envioEletronicoFatura   * Indica se a conta tem ativo o envio eletronico
     * 						da fatura (true) ou tem ativo o envio por
     * 						correio da fatura (false).
     */
    public void setEnvioEletronicoFatura(java.lang.Boolean envioEletronicoFatura) {
        this.envioEletronicoFatura = envioEletronicoFatura;
    }


    /**
     * Gets the qtdFaturasAbertas value for this Conta.
     * 
     * @return qtdFaturasAbertas
     */
    public java.math.BigInteger getQtdFaturasAbertas() {
        return qtdFaturasAbertas;
    }


    /**
     * Sets the qtdFaturasAbertas value for this Conta.
     * 
     * @param qtdFaturasAbertas
     */
    public void setQtdFaturasAbertas(java.math.BigInteger qtdFaturasAbertas) {
        this.qtdFaturasAbertas = qtdFaturasAbertas;
    }


    /**
     * Gets the valorSaldoDevedor value for this Conta.
     * 
     * @return valorSaldoDevedor   * Valor total do saldo devedor de todas as faturas
     * 						em aberto do cliente.
     */
    public java.math.BigDecimal getValorSaldoDevedor() {
        return valorSaldoDevedor;
    }


    /**
     * Sets the valorSaldoDevedor value for this Conta.
     * 
     * @param valorSaldoDevedor   * Valor total do saldo devedor de todas as faturas
     * 						em aberto do cliente.
     */
    public void setValorSaldoDevedor(java.math.BigDecimal valorSaldoDevedor) {
        this.valorSaldoDevedor = valorSaldoDevedor;
    }


    /**
     * Gets the emailContaGerada value for this Conta.
     * 
     * @return emailContaGerada
     */
    public java.lang.Boolean getEmailContaGerada() {
        return emailContaGerada;
    }


    /**
     * Sets the emailContaGerada value for this Conta.
     * 
     * @param emailContaGerada
     */
    public void setEmailContaGerada(java.lang.Boolean emailContaGerada) {
        this.emailContaGerada = emailContaGerada;
    }


    /**
     * Gets the enviaBoleto value for this Conta.
     * 
     * @return enviaBoleto
     */
    public java.lang.Boolean getEnviaBoleto() {
        return enviaBoleto;
    }


    /**
     * Sets the enviaBoleto value for this Conta.
     * 
     * @param enviaBoleto
     */
    public void setEnviaBoleto(java.lang.Boolean enviaBoleto) {
        this.enviaBoleto = enviaBoleto;
    }


    /**
     * Gets the hierarquiaConta value for this Conta.
     * 
     * @return hierarquiaConta
     */
    public br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.HierarquiaConta getHierarquiaConta() {
        return hierarquiaConta;
    }


    /**
     * Sets the hierarquiaConta value for this Conta.
     * 
     * @param hierarquiaConta
     */
    public void setHierarquiaConta(br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.MC.Conta.HierarquiaConta hierarquiaConta) {
        this.hierarquiaConta = hierarquiaConta;
    }


    /**
     * Gets the faltaContatoGestor value for this Conta.
     * 
     * @return faltaContatoGestor
     */
    public java.lang.Boolean getFaltaContatoGestor() {
        return faltaContatoGestor;
    }


    /**
     * Sets the faltaContatoGestor value for this Conta.
     * 
     * @param faltaContatoGestor
     */
    public void setFaltaContatoGestor(java.lang.Boolean faltaContatoGestor) {
        this.faltaContatoGestor = faltaContatoGestor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Conta)) return false;
        Conta other = (Conta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.codigoContaSistemOrigem==null && other.getCodigoContaSistemOrigem()==null) || 
             (this.codigoContaSistemOrigem!=null &&
              this.codigoContaSistemOrigem.equals(other.getCodigoContaSistemOrigem()))) &&
            ((this.dataExpiracao==null && other.getDataExpiracao()==null) || 
             (this.dataExpiracao!=null &&
              this.dataExpiracao.equals(other.getDataExpiracao()))) &&
            ((this.dataVencimento==null && other.getDataVencimento()==null) || 
             (this.dataVencimento!=null &&
              this.dataVencimento.equals(other.getDataVencimento()))) &&
            ((this.dataInicialCiclo==null && other.getDataInicialCiclo()==null) || 
             (this.dataInicialCiclo!=null &&
              this.dataInicialCiclo.equals(other.getDataInicialCiclo()))) &&
            ((this.dataFinalCiclo==null && other.getDataFinalCiclo()==null) || 
             (this.dataFinalCiclo!=null &&
              this.dataFinalCiclo.equals(other.getDataFinalCiclo()))) &&
            ((this.dataAlteracaoStatus==null && other.getDataAlteracaoStatus()==null) || 
             (this.dataAlteracaoStatus!=null &&
              this.dataAlteracaoStatus.equals(other.getDataAlteracaoStatus()))) &&
            ((this.valorUltimaFatura==null && other.getValorUltimaFatura()==null) || 
             (this.valorUltimaFatura!=null &&
              this.valorUltimaFatura.equals(other.getValorUltimaFatura()))) &&
            ((this.contaPorEmail==null && other.getContaPorEmail()==null) || 
             (this.contaPorEmail!=null &&
              this.contaPorEmail.equals(other.getContaPorEmail()))) &&
            ((this.contaPagadora==null && other.getContaPagadora()==null) || 
             (this.contaPagadora!=null &&
              this.contaPagadora.equals(other.getContaPagadora()))) &&
            ((this.ciclo==null && other.getCiclo()==null) || 
             (this.ciclo!=null &&
              this.ciclo.equals(other.getCiclo()))) &&
            ((this.classeCredito==null && other.getClasseCredito()==null) || 
             (this.classeCredito!=null &&
              this.classeCredito.equals(other.getClasseCredito()))) &&
            ((this.tipoConta==null && other.getTipoConta()==null) || 
             (this.tipoConta!=null &&
              this.tipoConta.equals(other.getTipoConta()))) &&
            ((this.layoutConta==null && other.getLayoutConta()==null) || 
             (this.layoutConta!=null &&
              this.layoutConta.equals(other.getLayoutConta()))) &&
            ((this.tipoRelatorio==null && other.getTipoRelatorio()==null) || 
             (this.tipoRelatorio!=null &&
              this.tipoRelatorio.equals(other.getTipoRelatorio()))) &&
            ((this.statusConta==null && other.getStatusConta()==null) || 
             (this.statusConta!=null &&
              this.statusConta.equals(other.getStatusConta()))) &&
            ((this.smsContaGerada==null && other.getSmsContaGerada()==null) || 
             (this.smsContaGerada!=null &&
              this.smsContaGerada.equals(other.getSmsContaGerada()))) &&
            ((this.smsCodigoBarras==null && other.getSmsCodigoBarras()==null) || 
             (this.smsCodigoBarras!=null &&
              this.smsCodigoBarras.equals(other.getSmsCodigoBarras()))) &&
            ((this.envioEletronicoFatura==null && other.getEnvioEletronicoFatura()==null) || 
             (this.envioEletronicoFatura!=null &&
              this.envioEletronicoFatura.equals(other.getEnvioEletronicoFatura()))) &&
            ((this.qtdFaturasAbertas==null && other.getQtdFaturasAbertas()==null) || 
             (this.qtdFaturasAbertas!=null &&
              this.qtdFaturasAbertas.equals(other.getQtdFaturasAbertas()))) &&
            ((this.valorSaldoDevedor==null && other.getValorSaldoDevedor()==null) || 
             (this.valorSaldoDevedor!=null &&
              this.valorSaldoDevedor.equals(other.getValorSaldoDevedor()))) &&
            ((this.emailContaGerada==null && other.getEmailContaGerada()==null) || 
             (this.emailContaGerada!=null &&
              this.emailContaGerada.equals(other.getEmailContaGerada()))) &&
            ((this.enviaBoleto==null && other.getEnviaBoleto()==null) || 
             (this.enviaBoleto!=null &&
              this.enviaBoleto.equals(other.getEnviaBoleto()))) &&
            ((this.hierarquiaConta==null && other.getHierarquiaConta()==null) || 
             (this.hierarquiaConta!=null &&
              this.hierarquiaConta.equals(other.getHierarquiaConta()))) &&
            ((this.faltaContatoGestor==null && other.getFaltaContatoGestor()==null) || 
             (this.faltaContatoGestor!=null &&
              this.faltaContatoGestor.equals(other.getFaltaContatoGestor())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getCodigoContaSistemOrigem() != null) {
            _hashCode += getCodigoContaSistemOrigem().hashCode();
        }
        if (getDataExpiracao() != null) {
            _hashCode += getDataExpiracao().hashCode();
        }
        if (getDataVencimento() != null) {
            _hashCode += getDataVencimento().hashCode();
        }
        if (getDataInicialCiclo() != null) {
            _hashCode += getDataInicialCiclo().hashCode();
        }
        if (getDataFinalCiclo() != null) {
            _hashCode += getDataFinalCiclo().hashCode();
        }
        if (getDataAlteracaoStatus() != null) {
            _hashCode += getDataAlteracaoStatus().hashCode();
        }
        if (getValorUltimaFatura() != null) {
            _hashCode += getValorUltimaFatura().hashCode();
        }
        if (getContaPorEmail() != null) {
            _hashCode += getContaPorEmail().hashCode();
        }
        if (getContaPagadora() != null) {
            _hashCode += getContaPagadora().hashCode();
        }
        if (getCiclo() != null) {
            _hashCode += getCiclo().hashCode();
        }
        if (getClasseCredito() != null) {
            _hashCode += getClasseCredito().hashCode();
        }
        if (getTipoConta() != null) {
            _hashCode += getTipoConta().hashCode();
        }
        if (getLayoutConta() != null) {
            _hashCode += getLayoutConta().hashCode();
        }
        if (getTipoRelatorio() != null) {
            _hashCode += getTipoRelatorio().hashCode();
        }
        if (getStatusConta() != null) {
            _hashCode += getStatusConta().hashCode();
        }
        if (getSmsContaGerada() != null) {
            _hashCode += getSmsContaGerada().hashCode();
        }
        if (getSmsCodigoBarras() != null) {
            _hashCode += getSmsCodigoBarras().hashCode();
        }
        if (getEnvioEletronicoFatura() != null) {
            _hashCode += getEnvioEletronicoFatura().hashCode();
        }
        if (getQtdFaturasAbertas() != null) {
            _hashCode += getQtdFaturasAbertas().hashCode();
        }
        if (getValorSaldoDevedor() != null) {
            _hashCode += getValorSaldoDevedor().hashCode();
        }
        if (getEmailContaGerada() != null) {
            _hashCode += getEmailContaGerada().hashCode();
        }
        if (getEnviaBoleto() != null) {
            _hashCode += getEnviaBoleto().hashCode();
        }
        if (getHierarquiaConta() != null) {
            _hashCode += getHierarquiaConta().hashCode();
        }
        if (getFaltaContatoGestor() != null) {
            _hashCode += getFaltaContatoGestor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Conta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Conta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoContaSistemOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "codigoContaSistemOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataExpiracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataExpiracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataVencimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataVencimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInicialCiclo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataInicialCiclo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFinalCiclo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataFinalCiclo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAlteracaoStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "dataAlteracaoStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorUltimaFatura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorUltimaFatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contaPorEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "contaPorEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contaPagadora");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "contaPagadora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciclo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "ciclo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "Ciclo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classeCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "classeCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "ClasseCredito"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoConta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("layoutConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "layoutConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "LayoutConta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRelatorio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "tipoRelatorio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "TipoRelatorio"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statusConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "statusConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "StatusConta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsContaGerada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "smsContaGerada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("smsCodigoBarras");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "smsCodigoBarras"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("envioEletronicoFatura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "envioEletronicoFatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdFaturasAbertas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "qtdFaturasAbertas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSaldoDevedor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "valorSaldoDevedor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailContaGerada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "emailContaGerada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enviaBoleto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "enviaBoleto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hierarquiaConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "hierarquiaConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "HierarquiaConta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faltaContatoGestor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Conta", "faltaContatoGestor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
