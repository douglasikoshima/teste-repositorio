/**
 * ParametrosExportarRestricoesModeloPaginacaoIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosExportarRestricoesModeloPaginacaoIn  implements java.io.Serializable {
    private long itensPorPagina;

    private long paginaSolicitada;

    public ParametrosExportarRestricoesModeloPaginacaoIn() {
    }

    public ParametrosExportarRestricoesModeloPaginacaoIn(
           long itensPorPagina,
           long paginaSolicitada) {
           this.itensPorPagina = itensPorPagina;
           this.paginaSolicitada = paginaSolicitada;
    }


    /**
     * Gets the itensPorPagina value for this ParametrosExportarRestricoesModeloPaginacaoIn.
     * 
     * @return itensPorPagina
     */
    public long getItensPorPagina() {
        return itensPorPagina;
    }


    /**
     * Sets the itensPorPagina value for this ParametrosExportarRestricoesModeloPaginacaoIn.
     * 
     * @param itensPorPagina
     */
    public void setItensPorPagina(long itensPorPagina) {
        this.itensPorPagina = itensPorPagina;
    }


    /**
     * Gets the paginaSolicitada value for this ParametrosExportarRestricoesModeloPaginacaoIn.
     * 
     * @return paginaSolicitada
     */
    public long getPaginaSolicitada() {
        return paginaSolicitada;
    }


    /**
     * Sets the paginaSolicitada value for this ParametrosExportarRestricoesModeloPaginacaoIn.
     * 
     * @param paginaSolicitada
     */
    public void setPaginaSolicitada(long paginaSolicitada) {
        this.paginaSolicitada = paginaSolicitada;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExportarRestricoesModeloPaginacaoIn)) return false;
        ParametrosExportarRestricoesModeloPaginacaoIn other = (ParametrosExportarRestricoesModeloPaginacaoIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.itensPorPagina == other.getItensPorPagina() &&
            this.paginaSolicitada == other.getPaginaSolicitada();
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
        _hashCode += new Long(getItensPorPagina()).hashCode();
        _hashCode += new Long(getPaginaSolicitada()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosExportarRestricoesModeloPaginacaoIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosExportarRestricoesModelo>PaginacaoIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itensPorPagina");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "itensPorPagina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginaSolicitada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "paginaSolicitada"));
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
