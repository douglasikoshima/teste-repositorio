/**
 * ParametrosConsultarInformacoesConfianca.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica;

public class ParametrosConsultarInformacoesConfianca  implements java.io.Serializable {
    private java.lang.String numeroDocumento;

    private java.lang.String inscricaoEstadual;

    private java.lang.String UF;

    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta tipoConsulta;

    private java.math.BigInteger prazo;

    private java.math.BigInteger codigoIBGE;

    private java.math.BigInteger codigoSUFRAMA;

    public ParametrosConsultarInformacoesConfianca() {
    }

    public ParametrosConsultarInformacoesConfianca(
           java.lang.String numeroDocumento,
           java.lang.String inscricaoEstadual,
           java.lang.String UF,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta tipoConsulta,
           java.math.BigInteger prazo,
           java.math.BigInteger codigoIBGE,
           java.math.BigInteger codigoSUFRAMA) {
           this.numeroDocumento = numeroDocumento;
           this.inscricaoEstadual = inscricaoEstadual;
           this.UF = UF;
           this.tipoConsulta = tipoConsulta;
           this.prazo = prazo;
           this.codigoIBGE = codigoIBGE;
           this.codigoSUFRAMA = codigoSUFRAMA;
    }


    /**
     * Gets the numeroDocumento value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return numeroDocumento
     */
    public java.lang.String getNumeroDocumento() {
        return numeroDocumento;
    }


    /**
     * Sets the numeroDocumento value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param numeroDocumento
     */
    public void setNumeroDocumento(java.lang.String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    /**
     * Gets the inscricaoEstadual value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return inscricaoEstadual
     */
    public java.lang.String getInscricaoEstadual() {
        return inscricaoEstadual;
    }


    /**
     * Sets the inscricaoEstadual value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param inscricaoEstadual
     */
    public void setInscricaoEstadual(java.lang.String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }


    /**
     * Gets the UF value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return UF
     */
    public java.lang.String getUF() {
        return UF;
    }


    /**
     * Sets the UF value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param UF
     */
    public void setUF(java.lang.String UF) {
        this.UF = UF;
    }


    /**
     * Gets the tipoConsulta value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return tipoConsulta
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta getTipoConsulta() {
        return tipoConsulta;
    }


    /**
     * Sets the tipoConsulta value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param tipoConsulta
     */
    public void setTipoConsulta(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.SN.NotaFiscalEletronica.ParametrosConsultarInformacoesConfiancaTipoConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }


    /**
     * Gets the prazo value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return prazo
     */
    public java.math.BigInteger getPrazo() {
        return prazo;
    }


    /**
     * Sets the prazo value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param prazo
     */
    public void setPrazo(java.math.BigInteger prazo) {
        this.prazo = prazo;
    }


    /**
     * Gets the codigoIBGE value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return codigoIBGE
     */
    public java.math.BigInteger getCodigoIBGE() {
        return codigoIBGE;
    }


    /**
     * Sets the codigoIBGE value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param codigoIBGE
     */
    public void setCodigoIBGE(java.math.BigInteger codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }


    /**
     * Gets the codigoSUFRAMA value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @return codigoSUFRAMA
     */
    public java.math.BigInteger getCodigoSUFRAMA() {
        return codigoSUFRAMA;
    }


    /**
     * Sets the codigoSUFRAMA value for this ParametrosConsultarInformacoesConfianca.
     * 
     * @param codigoSUFRAMA
     */
    public void setCodigoSUFRAMA(java.math.BigInteger codigoSUFRAMA) {
        this.codigoSUFRAMA = codigoSUFRAMA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosConsultarInformacoesConfianca)) return false;
        ParametrosConsultarInformacoesConfianca other = (ParametrosConsultarInformacoesConfianca) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroDocumento==null && other.getNumeroDocumento()==null) || 
             (this.numeroDocumento!=null &&
              this.numeroDocumento.equals(other.getNumeroDocumento()))) &&
            ((this.inscricaoEstadual==null && other.getInscricaoEstadual()==null) || 
             (this.inscricaoEstadual!=null &&
              this.inscricaoEstadual.equals(other.getInscricaoEstadual()))) &&
            ((this.UF==null && other.getUF()==null) || 
             (this.UF!=null &&
              this.UF.equals(other.getUF()))) &&
            ((this.tipoConsulta==null && other.getTipoConsulta()==null) || 
             (this.tipoConsulta!=null &&
              this.tipoConsulta.equals(other.getTipoConsulta()))) &&
            ((this.prazo==null && other.getPrazo()==null) || 
             (this.prazo!=null &&
              this.prazo.equals(other.getPrazo()))) &&
            ((this.codigoIBGE==null && other.getCodigoIBGE()==null) || 
             (this.codigoIBGE!=null &&
              this.codigoIBGE.equals(other.getCodigoIBGE()))) &&
            ((this.codigoSUFRAMA==null && other.getCodigoSUFRAMA()==null) || 
             (this.codigoSUFRAMA!=null &&
              this.codigoSUFRAMA.equals(other.getCodigoSUFRAMA())));
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
        if (getNumeroDocumento() != null) {
            _hashCode += getNumeroDocumento().hashCode();
        }
        if (getInscricaoEstadual() != null) {
            _hashCode += getInscricaoEstadual().hashCode();
        }
        if (getUF() != null) {
            _hashCode += getUF().hashCode();
        }
        if (getTipoConsulta() != null) {
            _hashCode += getTipoConsulta().hashCode();
        }
        if (getPrazo() != null) {
            _hashCode += getPrazo().hashCode();
        }
        if (getCodigoIBGE() != null) {
            _hashCode += getCodigoIBGE().hashCode();
        }
        if (getCodigoSUFRAMA() != null) {
            _hashCode += getCodigoSUFRAMA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosConsultarInformacoesConfianca.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "ParametrosConsultarInformacoesConfianca"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "NumeroDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inscricaoEstadual");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "InscricaoEstadual"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("UF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "UF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "TipoConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", ">ParametrosConsultarInformacoesConfianca>TipoConsulta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prazo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "Prazo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoIBGE");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "CodigoIBGE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSUFRAMA");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/NotaFiscalEletronica", "CodigoSUFRAMA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
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
