/**
 * CriarCabecalhoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class CriarCabecalhoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho parametrosCriarCabecalho;

    public CriarCabecalhoRequest() {
    }

    public CriarCabecalhoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho parametrosCriarCabecalho) {
           this.parametrosCriarCabecalho = parametrosCriarCabecalho;
    }


    /**
     * Gets the parametrosCriarCabecalho value for this CriarCabecalhoRequest.
     * 
     * @return parametrosCriarCabecalho
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho getParametrosCriarCabecalho() {
        return parametrosCriarCabecalho;
    }


    /**
     * Sets the parametrosCriarCabecalho value for this CriarCabecalhoRequest.
     * 
     * @param parametrosCriarCabecalho
     */
    public void setParametrosCriarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosCriarCabecalho parametrosCriarCabecalho) {
        this.parametrosCriarCabecalho = parametrosCriarCabecalho;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarCabecalhoRequest)) return false;
        CriarCabecalhoRequest other = (CriarCabecalhoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCriarCabecalho==null && other.getParametrosCriarCabecalho()==null) || 
             (this.parametrosCriarCabecalho!=null &&
              this.parametrosCriarCabecalho.equals(other.getParametrosCriarCabecalho())));
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
        if (getParametrosCriarCabecalho() != null) {
            _hashCode += getParametrosCriarCabecalho().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarCabecalhoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">criarCabecalhoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCriarCabecalho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosCriarCabecalho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosCriarCabecalho"));
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
