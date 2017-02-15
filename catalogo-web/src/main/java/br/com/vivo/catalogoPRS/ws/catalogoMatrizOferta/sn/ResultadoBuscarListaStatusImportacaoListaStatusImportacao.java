/**
 * ResultadoBuscarListaStatusImportacaoListaStatusImportacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoBuscarListaStatusImportacaoListaStatusImportacao  implements java.io.Serializable {
    private long idStatusImportacao;

    private java.lang.String dscStatusImportacao;

    public ResultadoBuscarListaStatusImportacaoListaStatusImportacao() {
    }

    public ResultadoBuscarListaStatusImportacaoListaStatusImportacao(
           long idStatusImportacao,
           java.lang.String dscStatusImportacao) {
           this.idStatusImportacao = idStatusImportacao;
           this.dscStatusImportacao = dscStatusImportacao;
    }


    /**
     * Gets the idStatusImportacao value for this ResultadoBuscarListaStatusImportacaoListaStatusImportacao.
     * 
     * @return idStatusImportacao
     */
    public long getIdStatusImportacao() {
        return idStatusImportacao;
    }


    /**
     * Sets the idStatusImportacao value for this ResultadoBuscarListaStatusImportacaoListaStatusImportacao.
     * 
     * @param idStatusImportacao
     */
    public void setIdStatusImportacao(long idStatusImportacao) {
        this.idStatusImportacao = idStatusImportacao;
    }


    /**
     * Gets the dscStatusImportacao value for this ResultadoBuscarListaStatusImportacaoListaStatusImportacao.
     * 
     * @return dscStatusImportacao
     */
    public java.lang.String getDscStatusImportacao() {
        return dscStatusImportacao;
    }


    /**
     * Sets the dscStatusImportacao value for this ResultadoBuscarListaStatusImportacaoListaStatusImportacao.
     * 
     * @param dscStatusImportacao
     */
    public void setDscStatusImportacao(java.lang.String dscStatusImportacao) {
        this.dscStatusImportacao = dscStatusImportacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaStatusImportacaoListaStatusImportacao)) return false;
        ResultadoBuscarListaStatusImportacaoListaStatusImportacao other = (ResultadoBuscarListaStatusImportacaoListaStatusImportacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idStatusImportacao == other.getIdStatusImportacao() &&
            ((this.dscStatusImportacao==null && other.getDscStatusImportacao()==null) || 
             (this.dscStatusImportacao!=null &&
              this.dscStatusImportacao.equals(other.getDscStatusImportacao())));
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
        _hashCode += new Long(getIdStatusImportacao()).hashCode();
        if (getDscStatusImportacao() != null) {
            _hashCode += getDscStatusImportacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaStatusImportacaoListaStatusImportacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>ResultadoBuscarListaStatusImportacao>ListaStatusImportacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idStatusImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idStatusImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscStatusImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dscStatusImportacao"));
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
