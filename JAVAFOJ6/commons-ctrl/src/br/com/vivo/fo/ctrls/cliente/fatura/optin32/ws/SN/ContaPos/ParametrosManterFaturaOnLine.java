/**
 * ParametrosManterFaturaOnLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.cliente.fatura.optin32.ws.SN.ContaPos;

public class ParametrosManterFaturaOnLine  implements java.io.Serializable {
    private java.lang.String nrConta;

    private java.lang.Boolean indEnvioBoleto;

    private java.lang.Boolean indEnvioSms;

    private java.lang.Boolean indEnvioEmailNotificacao;

    private java.lang.Boolean indEnvioEmailFatura;

    private java.lang.Boolean indExpiracao;

    public ParametrosManterFaturaOnLine() {
    }

    public ParametrosManterFaturaOnLine(
           java.lang.String nrConta,
           java.lang.Boolean indEnvioBoleto,
           java.lang.Boolean indEnvioSms,
           java.lang.Boolean indEnvioEmailNotificacao,
           java.lang.Boolean indEnvioEmailFatura,
           java.lang.Boolean indExpiracao) {
           this.nrConta = nrConta;
           this.indEnvioBoleto = indEnvioBoleto;
           this.indEnvioSms = indEnvioSms;
           this.indEnvioEmailNotificacao = indEnvioEmailNotificacao;
           this.indEnvioEmailFatura = indEnvioEmailFatura;
           this.indExpiracao = indExpiracao;
    }


    /**
     * Gets the nrConta value for this ParametrosManterFaturaOnLine.
     * 
     * @return nrConta
     */
    public java.lang.String getNrConta() {
        return nrConta;
    }


    /**
     * Sets the nrConta value for this ParametrosManterFaturaOnLine.
     * 
     * @param nrConta
     */
    public void setNrConta(java.lang.String nrConta) {
        this.nrConta = nrConta;
    }


    /**
     * Gets the indEnvioBoleto value for this ParametrosManterFaturaOnLine.
     * 
     * @return indEnvioBoleto
     */
    public java.lang.Boolean getIndEnvioBoleto() {
        return indEnvioBoleto;
    }


    /**
     * Sets the indEnvioBoleto value for this ParametrosManterFaturaOnLine.
     * 
     * @param indEnvioBoleto
     */
    public void setIndEnvioBoleto(java.lang.Boolean indEnvioBoleto) {
        this.indEnvioBoleto = indEnvioBoleto;
    }


    /**
     * Gets the indEnvioSms value for this ParametrosManterFaturaOnLine.
     * 
     * @return indEnvioSms
     */
    public java.lang.Boolean getIndEnvioSms() {
        return indEnvioSms;
    }


    /**
     * Sets the indEnvioSms value for this ParametrosManterFaturaOnLine.
     * 
     * @param indEnvioSms
     */
    public void setIndEnvioSms(java.lang.Boolean indEnvioSms) {
        this.indEnvioSms = indEnvioSms;
    }


    /**
     * Gets the indEnvioEmailNotificacao value for this ParametrosManterFaturaOnLine.
     * 
     * @return indEnvioEmailNotificacao
     */
    public java.lang.Boolean getIndEnvioEmailNotificacao() {
        return indEnvioEmailNotificacao;
    }


    /**
     * Sets the indEnvioEmailNotificacao value for this ParametrosManterFaturaOnLine.
     * 
     * @param indEnvioEmailNotificacao
     */
    public void setIndEnvioEmailNotificacao(java.lang.Boolean indEnvioEmailNotificacao) {
        this.indEnvioEmailNotificacao = indEnvioEmailNotificacao;
    }


    /**
     * Gets the indEnvioEmailFatura value for this ParametrosManterFaturaOnLine.
     * 
     * @return indEnvioEmailFatura
     */
    public java.lang.Boolean getIndEnvioEmailFatura() {
        return indEnvioEmailFatura;
    }


    /**
     * Sets the indEnvioEmailFatura value for this ParametrosManterFaturaOnLine.
     * 
     * @param indEnvioEmailFatura
     */
    public void setIndEnvioEmailFatura(java.lang.Boolean indEnvioEmailFatura) {
        this.indEnvioEmailFatura = indEnvioEmailFatura;
    }


    /**
     * Gets the indExpiracao value for this ParametrosManterFaturaOnLine.
     * 
     * @return indExpiracao
     */
    public java.lang.Boolean getIndExpiracao() {
        return indExpiracao;
    }


    /**
     * Sets the indExpiracao value for this ParametrosManterFaturaOnLine.
     * 
     * @param indExpiracao
     */
    public void setIndExpiracao(java.lang.Boolean indExpiracao) {
        this.indExpiracao = indExpiracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosManterFaturaOnLine)) return false;
        ParametrosManterFaturaOnLine other = (ParametrosManterFaturaOnLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nrConta==null && other.getNrConta()==null) || 
             (this.nrConta!=null &&
              this.nrConta.equals(other.getNrConta()))) &&
            ((this.indEnvioBoleto==null && other.getIndEnvioBoleto()==null) || 
             (this.indEnvioBoleto!=null &&
              this.indEnvioBoleto.equals(other.getIndEnvioBoleto()))) &&
            ((this.indEnvioSms==null && other.getIndEnvioSms()==null) || 
             (this.indEnvioSms!=null &&
              this.indEnvioSms.equals(other.getIndEnvioSms()))) &&
            ((this.indEnvioEmailNotificacao==null && other.getIndEnvioEmailNotificacao()==null) || 
             (this.indEnvioEmailNotificacao!=null &&
              this.indEnvioEmailNotificacao.equals(other.getIndEnvioEmailNotificacao()))) &&
            ((this.indEnvioEmailFatura==null && other.getIndEnvioEmailFatura()==null) || 
             (this.indEnvioEmailFatura!=null &&
              this.indEnvioEmailFatura.equals(other.getIndEnvioEmailFatura()))) &&
            ((this.indExpiracao==null && other.getIndExpiracao()==null) || 
             (this.indExpiracao!=null &&
              this.indExpiracao.equals(other.getIndExpiracao())));
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
        if (getNrConta() != null) {
            _hashCode += getNrConta().hashCode();
        }
        if (getIndEnvioBoleto() != null) {
            _hashCode += getIndEnvioBoleto().hashCode();
        }
        if (getIndEnvioSms() != null) {
            _hashCode += getIndEnvioSms().hashCode();
        }
        if (getIndEnvioEmailNotificacao() != null) {
            _hashCode += getIndEnvioEmailNotificacao().hashCode();
        }
        if (getIndEnvioEmailFatura() != null) {
            _hashCode += getIndEnvioEmailFatura().hashCode();
        }
        if (getIndExpiracao() != null) {
            _hashCode += getIndExpiracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosManterFaturaOnLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "ParametrosManterFaturaOnLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "nrConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indEnvioBoleto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "indEnvioBoleto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indEnvioSms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "indEnvioSms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indEnvioEmailNotificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "indEnvioEmailNotificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indEnvioEmailFatura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "indEnvioEmailFatura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indExpiracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/ContaPos", "indExpiracao"));
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
