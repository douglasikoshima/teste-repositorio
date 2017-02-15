/**
 * AlterarCabecalhoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class AlterarCabecalhoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho parametrosAlterarCabecalho;

    public AlterarCabecalhoRequest() {
    }

    public AlterarCabecalhoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho parametrosAlterarCabecalho) {
           this.parametrosAlterarCabecalho = parametrosAlterarCabecalho;
    }


    /**
     * Gets the parametrosAlterarCabecalho value for this AlterarCabecalhoRequest.
     * 
     * @return parametrosAlterarCabecalho
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho getParametrosAlterarCabecalho() {
        return parametrosAlterarCabecalho;
    }


    /**
     * Sets the parametrosAlterarCabecalho value for this AlterarCabecalhoRequest.
     * 
     * @param parametrosAlterarCabecalho
     */
    public void setParametrosAlterarCabecalho(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ParametrosAlterarCabecalho parametrosAlterarCabecalho) {
        this.parametrosAlterarCabecalho = parametrosAlterarCabecalho;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarCabecalhoRequest)) return false;
        AlterarCabecalhoRequest other = (AlterarCabecalhoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarCabecalho==null && other.getParametrosAlterarCabecalho()==null) || 
             (this.parametrosAlterarCabecalho!=null &&
              this.parametrosAlterarCabecalho.equals(other.getParametrosAlterarCabecalho())));
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
        if (getParametrosAlterarCabecalho() != null) {
            _hashCode += getParametrosAlterarCabecalho().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarCabecalhoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">alterarCabecalhoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarCabecalho");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ParametrosAlterarCabecalho"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarCabecalho"));
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
