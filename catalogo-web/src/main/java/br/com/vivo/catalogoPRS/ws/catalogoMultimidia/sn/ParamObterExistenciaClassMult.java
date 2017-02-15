/**
 * ParamObterExistenciaClassMult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ParamObterExistenciaClassMult  implements java.io.Serializable {
    private long idGrupoProduto;

    private java.lang.Long idCor;

    private java.lang.Long idClassificacao;

    private java.lang.String sgTipoMultimidia;

    public ParamObterExistenciaClassMult() {
    }

    public ParamObterExistenciaClassMult(
           long idGrupoProduto,
           java.lang.Long idCor,
           java.lang.Long idClassificacao,
           java.lang.String sgTipoMultimidia) {
           this.idGrupoProduto = idGrupoProduto;
           this.idCor = idCor;
           this.idClassificacao = idClassificacao;
           this.sgTipoMultimidia = sgTipoMultimidia;
    }


    /**
     * Gets the idGrupoProduto value for this ParamObterExistenciaClassMult.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParamObterExistenciaClassMult.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the idCor value for this ParamObterExistenciaClassMult.
     * 
     * @return idCor
     */
    public java.lang.Long getIdCor() {
        return idCor;
    }


    /**
     * Sets the idCor value for this ParamObterExistenciaClassMult.
     * 
     * @param idCor
     */
    public void setIdCor(java.lang.Long idCor) {
        this.idCor = idCor;
    }


    /**
     * Gets the idClassificacao value for this ParamObterExistenciaClassMult.
     * 
     * @return idClassificacao
     */
    public java.lang.Long getIdClassificacao() {
        return idClassificacao;
    }


    /**
     * Sets the idClassificacao value for this ParamObterExistenciaClassMult.
     * 
     * @param idClassificacao
     */
    public void setIdClassificacao(java.lang.Long idClassificacao) {
        this.idClassificacao = idClassificacao;
    }


    /**
     * Gets the sgTipoMultimidia value for this ParamObterExistenciaClassMult.
     * 
     * @return sgTipoMultimidia
     */
    public java.lang.String getSgTipoMultimidia() {
        return sgTipoMultimidia;
    }


    /**
     * Sets the sgTipoMultimidia value for this ParamObterExistenciaClassMult.
     * 
     * @param sgTipoMultimidia
     */
    public void setSgTipoMultimidia(java.lang.String sgTipoMultimidia) {
        this.sgTipoMultimidia = sgTipoMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamObterExistenciaClassMult)) return false;
        ParamObterExistenciaClassMult other = (ParamObterExistenciaClassMult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            ((this.idCor==null && other.getIdCor()==null) || 
             (this.idCor!=null &&
              this.idCor.equals(other.getIdCor()))) &&
            ((this.idClassificacao==null && other.getIdClassificacao()==null) || 
             (this.idClassificacao!=null &&
              this.idClassificacao.equals(other.getIdClassificacao()))) &&
            ((this.sgTipoMultimidia==null && other.getSgTipoMultimidia()==null) || 
             (this.sgTipoMultimidia!=null &&
              this.sgTipoMultimidia.equals(other.getSgTipoMultimidia())));
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        if (getIdCor() != null) {
            _hashCode += getIdCor().hashCode();
        }
        if (getIdClassificacao() != null) {
            _hashCode += getIdClassificacao().hashCode();
        }
        if (getSgTipoMultimidia() != null) {
            _hashCode += getSgTipoMultimidia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamObterExistenciaClassMult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamObterExistenciaClassMult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idCor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "sgTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
