/**
 * ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia  implements java.io.Serializable {
    private long idGrupoProdutoTecn;

    private long idGrupoProduto;

    private long idTecnologia;

    public ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia() {
    }

    public ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia(
           long idGrupoProdutoTecn,
           long idGrupoProduto,
           long idTecnologia) {
           this.idGrupoProdutoTecn = idGrupoProdutoTecn;
           this.idGrupoProduto = idGrupoProduto;
           this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idGrupoProdutoTecn value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @return idGrupoProdutoTecn
     */
    public long getIdGrupoProdutoTecn() {
        return idGrupoProdutoTecn;
    }


    /**
     * Sets the idGrupoProdutoTecn value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @param idGrupoProdutoTecn
     */
    public void setIdGrupoProdutoTecn(long idGrupoProdutoTecn) {
        this.idGrupoProdutoTecn = idGrupoProdutoTecn;
    }


    /**
     * Gets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the idTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia)) return false;
        ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia other = (ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProdutoTecn == other.getIdGrupoProdutoTecn() &&
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            this.idTecnologia == other.getIdTecnologia();
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
        _hashCode += new Long(getIdGrupoProdutoTecn()).hashCode();
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        _hashCode += new Long(getIdTecnologia()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoModeloPorIdGrupoProdutoListaGrupoProdutoTecnologiaGrupoProdutoTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoModeloPorId>GrupoProduto>ListaGrupoProdutoTecnologia>GrupoProdutoTecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProdutoTecn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProdutoTecn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
