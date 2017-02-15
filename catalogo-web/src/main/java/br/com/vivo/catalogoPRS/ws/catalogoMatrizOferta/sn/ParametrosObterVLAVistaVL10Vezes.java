/**
 * ParametrosObterVLAVistaVL10Vezes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosObterVLAVistaVL10Vezes  implements java.io.Serializable {
    private java.math.BigDecimal valorAVista;

    private java.lang.Long idCanalDistribuicao;

    public ParametrosObterVLAVistaVL10Vezes() {
    }

    public ParametrosObterVLAVistaVL10Vezes(
           java.math.BigDecimal valorAVista,
           java.lang.Long idCanalDistribuicao) {
           this.valorAVista = valorAVista;
           this.idCanalDistribuicao = idCanalDistribuicao;
    }


    /**
     * Gets the valorAVista value for this ParametrosObterVLAVistaVL10Vezes.
     * 
     * @return valorAVista
     */
    public java.math.BigDecimal getValorAVista() {
        return valorAVista;
    }


    /**
     * Sets the valorAVista value for this ParametrosObterVLAVistaVL10Vezes.
     * 
     * @param valorAVista
     */
    public void setValorAVista(java.math.BigDecimal valorAVista) {
        this.valorAVista = valorAVista;
    }


    /**
     * Gets the idCanalDistribuicao value for this ParametrosObterVLAVistaVL10Vezes.
     * 
     * @return idCanalDistribuicao
     */
    public java.lang.Long getIdCanalDistribuicao() {
        return idCanalDistribuicao;
    }


    /**
     * Sets the idCanalDistribuicao value for this ParametrosObterVLAVistaVL10Vezes.
     * 
     * @param idCanalDistribuicao
     */
    public void setIdCanalDistribuicao(java.lang.Long idCanalDistribuicao) {
        this.idCanalDistribuicao = idCanalDistribuicao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosObterVLAVistaVL10Vezes)) return false;
        ParametrosObterVLAVistaVL10Vezes other = (ParametrosObterVLAVistaVL10Vezes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.valorAVista==null && other.getValorAVista()==null) || 
             (this.valorAVista!=null &&
              this.valorAVista.equals(other.getValorAVista()))) &&
            ((this.idCanalDistribuicao==null && other.getIdCanalDistribuicao()==null) || 
             (this.idCanalDistribuicao!=null &&
              this.idCanalDistribuicao.equals(other.getIdCanalDistribuicao())));
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
        if (getValorAVista() != null) {
            _hashCode += getValorAVista().hashCode();
        }
        if (getIdCanalDistribuicao() != null) {
            _hashCode += getIdCanalDistribuicao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosObterVLAVistaVL10Vezes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterVLAVistaVL10Vezes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAVista");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "valorAVista"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanalDistribuicao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanalDistribuicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
