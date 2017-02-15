/**
 * ResultObterExistenciaClassMult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ResultObterExistenciaClassMult  implements java.io.Serializable {
    private long qtdOcorrencia;

    private java.lang.String nmCor;

    private java.lang.String nmClassificacao;

    public ResultObterExistenciaClassMult() {
    }

    public ResultObterExistenciaClassMult(
           long qtdOcorrencia,
           java.lang.String nmCor,
           java.lang.String nmClassificacao) {
           this.qtdOcorrencia = qtdOcorrencia;
           this.nmCor = nmCor;
           this.nmClassificacao = nmClassificacao;
    }


    /**
     * Gets the qtdOcorrencia value for this ResultObterExistenciaClassMult.
     * 
     * @return qtdOcorrencia
     */
    public long getQtdOcorrencia() {
        return qtdOcorrencia;
    }


    /**
     * Sets the qtdOcorrencia value for this ResultObterExistenciaClassMult.
     * 
     * @param qtdOcorrencia
     */
    public void setQtdOcorrencia(long qtdOcorrencia) {
        this.qtdOcorrencia = qtdOcorrencia;
    }


    /**
     * Gets the nmCor value for this ResultObterExistenciaClassMult.
     * 
     * @return nmCor
     */
    public java.lang.String getNmCor() {
        return nmCor;
    }


    /**
     * Sets the nmCor value for this ResultObterExistenciaClassMult.
     * 
     * @param nmCor
     */
    public void setNmCor(java.lang.String nmCor) {
        this.nmCor = nmCor;
    }


    /**
     * Gets the nmClassificacao value for this ResultObterExistenciaClassMult.
     * 
     * @return nmClassificacao
     */
    public java.lang.String getNmClassificacao() {
        return nmClassificacao;
    }


    /**
     * Sets the nmClassificacao value for this ResultObterExistenciaClassMult.
     * 
     * @param nmClassificacao
     */
    public void setNmClassificacao(java.lang.String nmClassificacao) {
        this.nmClassificacao = nmClassificacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultObterExistenciaClassMult)) return false;
        ResultObterExistenciaClassMult other = (ResultObterExistenciaClassMult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.qtdOcorrencia == other.getQtdOcorrencia() &&
            ((this.nmCor==null && other.getNmCor()==null) || 
             (this.nmCor!=null &&
              this.nmCor.equals(other.getNmCor()))) &&
            ((this.nmClassificacao==null && other.getNmClassificacao()==null) || 
             (this.nmClassificacao!=null &&
              this.nmClassificacao.equals(other.getNmClassificacao())));
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
        _hashCode += new Long(getQtdOcorrencia()).hashCode();
        if (getNmCor() != null) {
            _hashCode += getNmCor().hashCode();
        }
        if (getNmClassificacao() != null) {
            _hashCode += getNmClassificacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultObterExistenciaClassMult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ResultObterExistenciaClassMult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdOcorrencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "qtdOcorrencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "nmClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
