/**
 * ParametrosAlterarFimVidaModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosAlterarFimVidaModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarFimVidaModeloParametrosFimVidaModelo parametrosFimVidaModelo;

    public ParametrosAlterarFimVidaModelo() {
    }

    public ParametrosAlterarFimVidaModelo(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarFimVidaModeloParametrosFimVidaModelo parametrosFimVidaModelo) {
           this.parametrosFimVidaModelo = parametrosFimVidaModelo;
    }


    /**
     * Gets the parametrosFimVidaModelo value for this ParametrosAlterarFimVidaModelo.
     * 
     * @return parametrosFimVidaModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarFimVidaModeloParametrosFimVidaModelo getParametrosFimVidaModelo() {
        return parametrosFimVidaModelo;
    }


    /**
     * Sets the parametrosFimVidaModelo value for this ParametrosAlterarFimVidaModelo.
     * 
     * @param parametrosFimVidaModelo
     */
    public void setParametrosFimVidaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAlterarFimVidaModeloParametrosFimVidaModelo parametrosFimVidaModelo) {
        this.parametrosFimVidaModelo = parametrosFimVidaModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarFimVidaModelo)) return false;
        ParametrosAlterarFimVidaModelo other = (ParametrosAlterarFimVidaModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosFimVidaModelo==null && other.getParametrosFimVidaModelo()==null) || 
             (this.parametrosFimVidaModelo!=null &&
              this.parametrosFimVidaModelo.equals(other.getParametrosFimVidaModelo())));
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
        if (getParametrosFimVidaModelo() != null) {
            _hashCode += getParametrosFimVidaModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarFimVidaModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosAlterarFimVidaModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosFimVidaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ParametrosFimVidaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosAlterarFimVidaModelo>ParametrosFimVidaModelo"));
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
