/**
 * ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.lang.String nmCaracteristica;

    public ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn() {
    }

    public ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.lang.String nmCaracteristica) {
           this.paginacaoIn = paginacaoIn;
           this.nmCaracteristica = nmCaracteristica;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the nmCaracteristica value for this ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.
     * 
     * @return nmCaracteristica
     */
    public java.lang.String getNmCaracteristica() {
        return nmCaracteristica;
    }


    /**
     * Sets the nmCaracteristica value for this ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.
     * 
     * @param nmCaracteristica
     */
    public void setNmCaracteristica(java.lang.String nmCaracteristica) {
        this.nmCaracteristica = nmCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn)) return false;
        ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn other = (ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn()))) &&
            ((this.nmCaracteristica==null && other.getNmCaracteristica()==null) || 
             (this.nmCaracteristica!=null &&
              this.nmCaracteristica.equals(other.getNmCaracteristica())));
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
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        if (getNmCaracteristica() != null) {
            _hashCode += getNmCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaCaracteristicaRaizCaracteristicaIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosBuscarListaCaracteristica>RaizCaracteristicaIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "nmCaracteristica"));
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
