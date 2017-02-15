/**
 * NotaFiscal.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */
package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda;

public class NotaFiscal implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    /* Numero da nota fiscal gerado pelo SAP */
    private java.math.BigInteger numeroNF;

    /* Data de emissao da nota fiscal. */
    private java.util.Calendar dataEmissaoNF;

    /* Numero de serie da nota fiscal. */
    private java.lang.String numeroSerieNF;

    /* Valor para a base de calculo do ICMS */
    private java.math.BigDecimal valorBaseCalculoICMS;

    /* Porcentagem de base de calculo para o ICMS */
    private java.math.BigDecimal porcentagemICMS;

    /* Valor a ser pago correspondente ao ICMS */
    private java.math.BigDecimal valorICMS;

    /* Valor para a base de calculo do FUST */
    private java.math.BigDecimal valorBaseCalculoFUST;

    /* Porcentagem de base de calculo para o FUST */
    private java.math.BigDecimal porcentagemFUST;

    /* Valor a ser pago correspondente ao FUST */
    private java.math.BigDecimal valorFUST;

    /* Valor para a base de calculo do FUNTEL */
    private java.math.BigDecimal valorBaseCalculoFUNTEL;

    /* Porcentagem de base de calculo para o FUNTEL */
    private java.math.BigDecimal porcentagemFUNTEL;

    /* Valor a ser pago correspondente ao FUNTEL */
    private java.math.BigDecimal valorFUNTEL;

    /* Valor total da nota fiscal. */
    private java.math.BigDecimal valorTotalNF;

    /* Valor pago pelo cliente na nota fiscal. */
    private java.math.BigDecimal valorPagoNF;

    /* Data de anulacao da nota fiscal. */
    private java.util.Calendar dataAnulacaoNF;

    /* Codigo fiscal de operacoes */
    private java.lang.String cfop;

    /* De acordo com a lei 10703/03, do convenio 115/03
     * 						que introduziu o Hash Code, tambem conhecido por
     * 						chave de codificacao digital, deve ser gerada
     * 						por programa de informatica desenvolvido
     * 						especificamente para a autenticacao de dados
     * 						informatizados. A chave de codificacao digital
     * 						deve ser: I - gerada com base nos seguintes
     * 						dados constantes do documento fiscal: a) CNPJ ou
     * 						CPF do destinatario ou do tomador do servico; b)
     * 						numero do documento fiscal; c) valor total da
     * 						nota; d) base de calculo do ICMS; e) valor do
     * 						ICMS; II - obtida com a aplicacao do algoritmo
     * 						MD5 - "Message Digest" 5, de dominio publico; */
    private java.lang.String codigoHash;

    /* Nome da empresa emissora da nota fiscal */
    private java.lang.String nomeEmpresa;
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscalEletronica notaFiscalEletronica;

    public NotaFiscal() {
    }

    public NotaFiscal(
            java.math.BigInteger numeroNF,
            java.util.Calendar dataEmissaoNF,
            java.lang.String numeroSerieNF,
            java.math.BigDecimal valorBaseCalculoICMS,
            java.math.BigDecimal porcentagemICMS,
            java.math.BigDecimal valorICMS,
            java.math.BigDecimal valorBaseCalculoFUST,
            java.math.BigDecimal porcentagemFUST,
            java.math.BigDecimal valorFUST,
            java.math.BigDecimal valorBaseCalculoFUNTEL,
            java.math.BigDecimal porcentagemFUNTEL,
            java.math.BigDecimal valorFUNTEL,
            java.math.BigDecimal valorTotalNF,
            java.math.BigDecimal valorPagoNF,
            java.util.Calendar dataAnulacaoNF,
            java.lang.String cfop,
            java.lang.String codigoHash,
            java.lang.String nomeEmpresa,
            br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscalEletronica notaFiscalEletronica) {
        this.numeroNF = numeroNF;
        this.dataEmissaoNF = dataEmissaoNF;
        this.numeroSerieNF = numeroSerieNF;
        this.valorBaseCalculoICMS = valorBaseCalculoICMS;
        this.porcentagemICMS = porcentagemICMS;
        this.valorICMS = valorICMS;
        this.valorBaseCalculoFUST = valorBaseCalculoFUST;
        this.porcentagemFUST = porcentagemFUST;
        this.valorFUST = valorFUST;
        this.valorBaseCalculoFUNTEL = valorBaseCalculoFUNTEL;
        this.porcentagemFUNTEL = porcentagemFUNTEL;
        this.valorFUNTEL = valorFUNTEL;
        this.valorTotalNF = valorTotalNF;
        this.valorPagoNF = valorPagoNF;
        this.dataAnulacaoNF = dataAnulacaoNF;
        this.cfop = cfop;
        this.codigoHash = codigoHash;
        this.nomeEmpresa = nomeEmpresa;
        this.notaFiscalEletronica = notaFiscalEletronica;
    }

    /**
     * Gets the numeroNF value for this NotaFiscal.
     * 
     * @return numeroNF   * Numero da nota fiscal gerado pelo SAP
     */
    public java.math.BigInteger getNumeroNF() {
        return numeroNF;
    }

    /**
     * Sets the numeroNF value for this NotaFiscal.
     * 
     * @param numeroNF   * Numero da nota fiscal gerado pelo SAP
     */
    public void setNumeroNF(java.math.BigInteger numeroNF) {
        this.numeroNF = numeroNF;
    }

    /**
     * Gets the dataEmissaoNF value for this NotaFiscal.
     * 
     * @return dataEmissaoNF   * Data de emissao da nota fiscal.
     */
    public java.util.Calendar getDataEmissaoNF() {
        return dataEmissaoNF;
    }

    /**
     * Sets the dataEmissaoNF value for this NotaFiscal.
     * 
     * @param dataEmissaoNF   * Data de emissao da nota fiscal.
     */
    public void setDataEmissaoNF(java.util.Calendar dataEmissaoNF) {
        this.dataEmissaoNF = dataEmissaoNF;
    }

    /**
     * Gets the numeroSerieNF value for this NotaFiscal.
     * 
     * @return numeroSerieNF   * Numero de serie da nota fiscal.
     */
    public java.lang.String getNumeroSerieNF() {
        return numeroSerieNF;
    }

    /**
     * Sets the numeroSerieNF value for this NotaFiscal.
     * 
     * @param numeroSerieNF   * Numero de serie da nota fiscal.
     */
    public void setNumeroSerieNF(java.lang.String numeroSerieNF) {
        this.numeroSerieNF = numeroSerieNF;
    }

    /**
     * Gets the valorBaseCalculoICMS value for this NotaFiscal.
     * 
     * @return valorBaseCalculoICMS   * Valor para a base de calculo do ICMS
     */
    public java.math.BigDecimal getValorBaseCalculoICMS() {
        return valorBaseCalculoICMS;
    }

    /**
     * Sets the valorBaseCalculoICMS value for this NotaFiscal.
     * 
     * @param valorBaseCalculoICMS   * Valor para a base de calculo do ICMS
     */
    public void setValorBaseCalculoICMS(java.math.BigDecimal valorBaseCalculoICMS) {
        this.valorBaseCalculoICMS = valorBaseCalculoICMS;
    }

    /**
     * Gets the porcentagemICMS value for this NotaFiscal.
     * 
     * @return porcentagemICMS   * Porcentagem de base de calculo para o ICMS
     */
    public java.math.BigDecimal getPorcentagemICMS() {
        return porcentagemICMS;
    }

    /**
     * Sets the porcentagemICMS value for this NotaFiscal.
     * 
     * @param porcentagemICMS   * Porcentagem de base de calculo para o ICMS
     */
    public void setPorcentagemICMS(java.math.BigDecimal porcentagemICMS) {
        this.porcentagemICMS = porcentagemICMS;
    }

    /**
     * Gets the valorICMS value for this NotaFiscal.
     * 
     * @return valorICMS   * Valor a ser pago correspondente ao ICMS
     */
    public java.math.BigDecimal getValorICMS() {
        return valorICMS;
    }

    /**
     * Sets the valorICMS value for this NotaFiscal.
     * 
     * @param valorICMS   * Valor a ser pago correspondente ao ICMS
     */
    public void setValorICMS(java.math.BigDecimal valorICMS) {
        this.valorICMS = valorICMS;
    }

    /**
     * Gets the valorBaseCalculoFUST value for this NotaFiscal.
     * 
     * @return valorBaseCalculoFUST   * Valor para a base de calculo do FUST
     */
    public java.math.BigDecimal getValorBaseCalculoFUST() {
        return valorBaseCalculoFUST;
    }

    /**
     * Sets the valorBaseCalculoFUST value for this NotaFiscal.
     * 
     * @param valorBaseCalculoFUST   * Valor para a base de calculo do FUST
     */
    public void setValorBaseCalculoFUST(java.math.BigDecimal valorBaseCalculoFUST) {
        this.valorBaseCalculoFUST = valorBaseCalculoFUST;
    }

    /**
     * Gets the porcentagemFUST value for this NotaFiscal.
     * 
     * @return porcentagemFUST   * Porcentagem de base de calculo para o FUST
     */
    public java.math.BigDecimal getPorcentagemFUST() {
        return porcentagemFUST;
    }

    /**
     * Sets the porcentagemFUST value for this NotaFiscal.
     * 
     * @param porcentagemFUST   * Porcentagem de base de calculo para o FUST
     */
    public void setPorcentagemFUST(java.math.BigDecimal porcentagemFUST) {
        this.porcentagemFUST = porcentagemFUST;
    }

    /**
     * Gets the valorFUST value for this NotaFiscal.
     * 
     * @return valorFUST   * Valor a ser pago correspondente ao FUST
     */
    public java.math.BigDecimal getValorFUST() {
        return valorFUST;
    }

    /**
     * Sets the valorFUST value for this NotaFiscal.
     * 
     * @param valorFUST   * Valor a ser pago correspondente ao FUST
     */
    public void setValorFUST(java.math.BigDecimal valorFUST) {
        this.valorFUST = valorFUST;
    }

    /**
     * Gets the valorBaseCalculoFUNTEL value for this NotaFiscal.
     * 
     * @return valorBaseCalculoFUNTEL   * Valor para a base de calculo do FUNTEL
     */
    public java.math.BigDecimal getValorBaseCalculoFUNTEL() {
        return valorBaseCalculoFUNTEL;
    }

    /**
     * Sets the valorBaseCalculoFUNTEL value for this NotaFiscal.
     * 
     * @param valorBaseCalculoFUNTEL   * Valor para a base de calculo do FUNTEL
     */
    public void setValorBaseCalculoFUNTEL(java.math.BigDecimal valorBaseCalculoFUNTEL) {
        this.valorBaseCalculoFUNTEL = valorBaseCalculoFUNTEL;
    }

    /**
     * Gets the porcentagemFUNTEL value for this NotaFiscal.
     * 
     * @return porcentagemFUNTEL   * Porcentagem de base de calculo para o FUNTEL
     */
    public java.math.BigDecimal getPorcentagemFUNTEL() {
        return porcentagemFUNTEL;
    }

    /**
     * Sets the porcentagemFUNTEL value for this NotaFiscal.
     * 
     * @param porcentagemFUNTEL   * Porcentagem de base de calculo para o FUNTEL
     */
    public void setPorcentagemFUNTEL(java.math.BigDecimal porcentagemFUNTEL) {
        this.porcentagemFUNTEL = porcentagemFUNTEL;
    }

    /**
     * Gets the valorFUNTEL value for this NotaFiscal.
     * 
     * @return valorFUNTEL   * Valor a ser pago correspondente ao FUNTEL
     */
    public java.math.BigDecimal getValorFUNTEL() {
        return valorFUNTEL;
    }

    /**
     * Sets the valorFUNTEL value for this NotaFiscal.
     * 
     * @param valorFUNTEL   * Valor a ser pago correspondente ao FUNTEL
     */
    public void setValorFUNTEL(java.math.BigDecimal valorFUNTEL) {
        this.valorFUNTEL = valorFUNTEL;
    }

    /**
     * Gets the valorTotalNF value for this NotaFiscal.
     * 
     * @return valorTotalNF   * Valor total da nota fiscal.
     */
    public java.math.BigDecimal getValorTotalNF() {
        return valorTotalNF;
    }

    /**
     * Sets the valorTotalNF value for this NotaFiscal.
     * 
     * @param valorTotalNF   * Valor total da nota fiscal.
     */
    public void setValorTotalNF(java.math.BigDecimal valorTotalNF) {
        this.valorTotalNF = valorTotalNF;
    }

    /**
     * Gets the valorPagoNF value for this NotaFiscal.
     * 
     * @return valorPagoNF   * Valor pago pelo cliente na nota fiscal.
     */
    public java.math.BigDecimal getValorPagoNF() {
        return valorPagoNF;
    }

    /**
     * Sets the valorPagoNF value for this NotaFiscal.
     * 
     * @param valorPagoNF   * Valor pago pelo cliente na nota fiscal.
     */
    public void setValorPagoNF(java.math.BigDecimal valorPagoNF) {
        this.valorPagoNF = valorPagoNF;
    }

    /**
     * Gets the dataAnulacaoNF value for this NotaFiscal.
     * 
     * @return dataAnulacaoNF   * Data de anulacao da nota fiscal.
     */
    public java.util.Calendar getDataAnulacaoNF() {
        return dataAnulacaoNF;
    }

    /**
     * Sets the dataAnulacaoNF value for this NotaFiscal.
     * 
     * @param dataAnulacaoNF   * Data de anulacao da nota fiscal.
     */
    public void setDataAnulacaoNF(java.util.Calendar dataAnulacaoNF) {
        this.dataAnulacaoNF = dataAnulacaoNF;
    }

    /**
     * Gets the cfop value for this NotaFiscal.
     * 
     * @return cfop   * Codigo fiscal de operacoes
     */
    public java.lang.String getCfop() {
        return cfop;
    }

    /**
     * Sets the cfop value for this NotaFiscal.
     * 
     * @param cfop   * Codigo fiscal de operacoes
     */
    public void setCfop(java.lang.String cfop) {
        this.cfop = cfop;
    }

    /**
     * Gets the codigoHash value for this NotaFiscal.
     * 
     * @return codigoHash   * De acordo com a lei 10703/03, do convenio 115/03
     * 						que introduziu o Hash Code, tambem conhecido por
     * 						chave de codificacao digital, deve ser gerada
     * 						por programa de informatica desenvolvido
     * 						especificamente para a autenticacao de dados
     * 						informatizados. A chave de codificacao digital
     * 						deve ser: I - gerada com base nos seguintes
     * 						dados constantes do documento fiscal: a) CNPJ ou
     * 						CPF do destinatario ou do tomador do servico; b)
     * 						numero do documento fiscal; c) valor total da
     * 						nota; d) base de calculo do ICMS; e) valor do
     * 						ICMS; II - obtida com a aplicacao do algoritmo
     * 						MD5 - "Message Digest" 5, de dominio publico;
     */
    public java.lang.String getCodigoHash() {
        return codigoHash;
    }

    /**
     * Sets the codigoHash value for this NotaFiscal.
     * 
     * @param codigoHash   * De acordo com a lei 10703/03, do convenio 115/03
     * 						que introduziu o Hash Code, tambem conhecido por
     * 						chave de codificacao digital, deve ser gerada
     * 						por programa de informatica desenvolvido
     * 						especificamente para a autenticacao de dados
     * 						informatizados. A chave de codificacao digital
     * 						deve ser: I - gerada com base nos seguintes
     * 						dados constantes do documento fiscal: a) CNPJ ou
     * 						CPF do destinatario ou do tomador do servico; b)
     * 						numero do documento fiscal; c) valor total da
     * 						nota; d) base de calculo do ICMS; e) valor do
     * 						ICMS; II - obtida com a aplicacao do algoritmo
     * 						MD5 - "Message Digest" 5, de dominio publico;
     */
    public void setCodigoHash(java.lang.String codigoHash) {
        this.codigoHash = codigoHash;
    }

    /**
     * Gets the nomeEmpresa value for this NotaFiscal.
     * 
     * @return nomeEmpresa   * Nome da empresa emissora da nota fiscal
     */
    public java.lang.String getNomeEmpresa() {
        return nomeEmpresa;
    }

    /**
     * Sets the nomeEmpresa value for this NotaFiscal.
     * 
     * @param nomeEmpresa   * Nome da empresa emissora da nota fiscal
     */
    public void setNomeEmpresa(java.lang.String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    /**
     * Gets the notaFiscalEletronica value for this NotaFiscal.
     * 
     * @return notaFiscalEletronica
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscalEletronica getNotaFiscalEletronica() {
        return notaFiscalEletronica;
    }

    /**
     * Sets the notaFiscalEletronica value for this NotaFiscal.
     * 
     * @param notaFiscalEletronica
     */
    public void setNotaFiscalEletronica(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.OrdemVenda.NotaFiscalEletronica notaFiscalEletronica) {
        this.notaFiscalEletronica = notaFiscalEletronica;
    }
    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) obj;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.numeroNF == null && other.getNumeroNF() == null)
                || (this.numeroNF != null
                && this.numeroNF.equals(other.getNumeroNF())))
                && ((this.dataEmissaoNF == null && other.getDataEmissaoNF() == null)
                || (this.dataEmissaoNF != null
                && this.dataEmissaoNF.equals(other.getDataEmissaoNF())))
                && ((this.numeroSerieNF == null && other.getNumeroSerieNF() == null)
                || (this.numeroSerieNF != null
                && this.numeroSerieNF.equals(other.getNumeroSerieNF())))
                && ((this.valorBaseCalculoICMS == null && other.getValorBaseCalculoICMS() == null)
                || (this.valorBaseCalculoICMS != null
                && this.valorBaseCalculoICMS.equals(other.getValorBaseCalculoICMS())))
                && ((this.porcentagemICMS == null && other.getPorcentagemICMS() == null)
                || (this.porcentagemICMS != null
                && this.porcentagemICMS.equals(other.getPorcentagemICMS())))
                && ((this.valorICMS == null && other.getValorICMS() == null)
                || (this.valorICMS != null
                && this.valorICMS.equals(other.getValorICMS())))
                && ((this.valorBaseCalculoFUST == null && other.getValorBaseCalculoFUST() == null)
                || (this.valorBaseCalculoFUST != null
                && this.valorBaseCalculoFUST.equals(other.getValorBaseCalculoFUST())))
                && ((this.porcentagemFUST == null && other.getPorcentagemFUST() == null)
                || (this.porcentagemFUST != null
                && this.porcentagemFUST.equals(other.getPorcentagemFUST())))
                && ((this.valorFUST == null && other.getValorFUST() == null)
                || (this.valorFUST != null
                && this.valorFUST.equals(other.getValorFUST())))
                && ((this.valorBaseCalculoFUNTEL == null && other.getValorBaseCalculoFUNTEL() == null)
                || (this.valorBaseCalculoFUNTEL != null
                && this.valorBaseCalculoFUNTEL.equals(other.getValorBaseCalculoFUNTEL())))
                && ((this.porcentagemFUNTEL == null && other.getPorcentagemFUNTEL() == null)
                || (this.porcentagemFUNTEL != null
                && this.porcentagemFUNTEL.equals(other.getPorcentagemFUNTEL())))
                && ((this.valorFUNTEL == null && other.getValorFUNTEL() == null)
                || (this.valorFUNTEL != null
                && this.valorFUNTEL.equals(other.getValorFUNTEL())))
                && ((this.valorTotalNF == null && other.getValorTotalNF() == null)
                || (this.valorTotalNF != null
                && this.valorTotalNF.equals(other.getValorTotalNF())))
                && ((this.valorPagoNF == null && other.getValorPagoNF() == null)
                || (this.valorPagoNF != null
                && this.valorPagoNF.equals(other.getValorPagoNF())))
                && ((this.dataAnulacaoNF == null && other.getDataAnulacaoNF() == null)
                || (this.dataAnulacaoNF != null
                && this.dataAnulacaoNF.equals(other.getDataAnulacaoNF())))
                && ((this.cfop == null && other.getCfop() == null)
                || (this.cfop != null
                && this.cfop.equals(other.getCfop())))
                && ((this.codigoHash == null && other.getCodigoHash() == null)
                || (this.codigoHash != null
                && this.codigoHash.equals(other.getCodigoHash())))
                && ((this.nomeEmpresa == null && other.getNomeEmpresa() == null)
                || (this.nomeEmpresa != null
                && this.nomeEmpresa.equals(other.getNomeEmpresa())))
                && ((this.notaFiscalEletronica == null && other.getNotaFiscalEletronica() == null)
                || (this.notaFiscalEletronica != null
                && this.notaFiscalEletronica.equals(other.getNotaFiscalEletronica())));
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
        if (getNumeroNF() != null) {
            _hashCode += getNumeroNF().hashCode();
        }
        if (getDataEmissaoNF() != null) {
            _hashCode += getDataEmissaoNF().hashCode();
        }
        if (getNumeroSerieNF() != null) {
            _hashCode += getNumeroSerieNF().hashCode();
        }
        if (getValorBaseCalculoICMS() != null) {
            _hashCode += getValorBaseCalculoICMS().hashCode();
        }
        if (getPorcentagemICMS() != null) {
            _hashCode += getPorcentagemICMS().hashCode();
        }
        if (getValorICMS() != null) {
            _hashCode += getValorICMS().hashCode();
        }
        if (getValorBaseCalculoFUST() != null) {
            _hashCode += getValorBaseCalculoFUST().hashCode();
        }
        if (getPorcentagemFUST() != null) {
            _hashCode += getPorcentagemFUST().hashCode();
        }
        if (getValorFUST() != null) {
            _hashCode += getValorFUST().hashCode();
        }
        if (getValorBaseCalculoFUNTEL() != null) {
            _hashCode += getValorBaseCalculoFUNTEL().hashCode();
        }
        if (getPorcentagemFUNTEL() != null) {
            _hashCode += getPorcentagemFUNTEL().hashCode();
        }
        if (getValorFUNTEL() != null) {
            _hashCode += getValorFUNTEL().hashCode();
        }
        if (getValorTotalNF() != null) {
            _hashCode += getValorTotalNF().hashCode();
        }
        if (getValorPagoNF() != null) {
            _hashCode += getValorPagoNF().hashCode();
        }
        if (getDataAnulacaoNF() != null) {
            _hashCode += getDataAnulacaoNF().hashCode();
        }
        if (getCfop() != null) {
            _hashCode += getCfop().hashCode();
        }
        if (getCodigoHash() != null) {
            _hashCode += getCodigoHash().hashCode();
        }
        if (getNomeEmpresa() != null) {
            _hashCode += getNomeEmpresa().hashCode();
        }
        if (getNotaFiscalEletronica() != null) {
            _hashCode += getNotaFiscalEletronica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(NotaFiscal.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscal"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataEmissaoNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataEmissaoNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSerieNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "numeroSerieNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBaseCalculoICMS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorBaseCalculoICMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentagemICMS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "porcentagemICMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorICMS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorICMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBaseCalculoFUST");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorBaseCalculoFUST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentagemFUST");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "porcentagemFUST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorFUST");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorFUST"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorBaseCalculoFUNTEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorBaseCalculoFUNTEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentagemFUNTEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "porcentagemFUNTEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorFUNTEL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorFUNTEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorTotalNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorTotalNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorPagoNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "valorPagoNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAnulacaoNF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataAnulacaoNF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cfop");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "cfop"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoHash");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "codigoHash"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "nomeEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notaFiscalEletronica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "notaFiscalEletronica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "NotaFiscalEletronica"));
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
        return new org.apache.axis.encoding.ser.BeanSerializer(
                _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
            java.lang.String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(
                _javaType, _xmlType, typeDesc);
    }
}
