/**
 * ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao  implements java.io.Serializable {
    private java.lang.Long idTipoOperacao;

    private java.lang.String nmTipoOperacao;

    public ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao() {
    }

    public ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao(
           java.lang.Long idTipoOperacao,
           java.lang.String nmTipoOperacao) {
           this.idTipoOperacao = idTipoOperacao;
           this.nmTipoOperacao = nmTipoOperacao;
    }


    /**
     * Gets the idTipoOperacao value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.
     * 
     * @return idTipoOperacao
     */
    public java.lang.Long getIdTipoOperacao() {
        return idTipoOperacao;
    }


    /**
     * Sets the idTipoOperacao value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.
     * 
     * @param idTipoOperacao
     */
    public void setIdTipoOperacao(java.lang.Long idTipoOperacao) {
        this.idTipoOperacao = idTipoOperacao;
    }


    /**
     * Gets the nmTipoOperacao value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.
     * 
     * @return nmTipoOperacao
     */
    public java.lang.String getNmTipoOperacao() {
        return nmTipoOperacao;
    }


    /**
     * Sets the nmTipoOperacao value for this ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.
     * 
     * @param nmTipoOperacao
     */
    public void setNmTipoOperacao(java.lang.String nmTipoOperacao) {
        this.nmTipoOperacao = nmTipoOperacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao)) return false;
        ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao other = (ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTipoOperacao==null && other.getIdTipoOperacao()==null) || 
             (this.idTipoOperacao!=null &&
              this.idTipoOperacao.equals(other.getIdTipoOperacao()))) &&
            ((this.nmTipoOperacao==null && other.getNmTipoOperacao()==null) || 
             (this.nmTipoOperacao!=null &&
              this.nmTipoOperacao.equals(other.getNmTipoOperacao())));
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
        if (getIdTipoOperacao() != null) {
            _hashCode += getIdTipoOperacao().hashCode();
        }
        if (getNmTipoOperacao() != null) {
            _hashCode += getNmTipoOperacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCabecalhoListaCabecalhoCabecalhoListaTipoOperacaoTipoOperacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>>>ResultadoBuscarListaCabecalho>ListaCabecalho>Cabecalho>ListaTipoOperacao>TipoOperacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmTipoOperacao"));
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
