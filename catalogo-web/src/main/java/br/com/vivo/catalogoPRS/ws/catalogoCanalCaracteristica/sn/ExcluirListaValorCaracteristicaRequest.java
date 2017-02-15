/**
 * ExcluirListaValorCaracteristicaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ExcluirListaValorCaracteristicaRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristica parametrosExcluirListaValorCaracteristica;

    public ExcluirListaValorCaracteristicaRequest() {
    }

    public ExcluirListaValorCaracteristicaRequest(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristica parametrosExcluirListaValorCaracteristica) {
           this.parametrosExcluirListaValorCaracteristica = parametrosExcluirListaValorCaracteristica;
    }


    /**
     * Gets the parametrosExcluirListaValorCaracteristica value for this ExcluirListaValorCaracteristicaRequest.
     * 
     * @return parametrosExcluirListaValorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristica getParametrosExcluirListaValorCaracteristica() {
        return parametrosExcluirListaValorCaracteristica;
    }


    /**
     * Sets the parametrosExcluirListaValorCaracteristica value for this ExcluirListaValorCaracteristicaRequest.
     * 
     * @param parametrosExcluirListaValorCaracteristica
     */
    public void setParametrosExcluirListaValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosExcluirListaValorCaracteristica parametrosExcluirListaValorCaracteristica) {
        this.parametrosExcluirListaValorCaracteristica = parametrosExcluirListaValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirListaValorCaracteristicaRequest)) return false;
        ExcluirListaValorCaracteristicaRequest other = (ExcluirListaValorCaracteristicaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirListaValorCaracteristica==null && other.getParametrosExcluirListaValorCaracteristica()==null) || 
             (this.parametrosExcluirListaValorCaracteristica!=null &&
              this.parametrosExcluirListaValorCaracteristica.equals(other.getParametrosExcluirListaValorCaracteristica())));
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
        if (getParametrosExcluirListaValorCaracteristica() != null) {
            _hashCode += getParametrosExcluirListaValorCaracteristica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirListaValorCaracteristicaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">excluirListaValorCaracteristicaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirListaValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosExcluirListaValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosExcluirListaValorCaracteristica"));
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
