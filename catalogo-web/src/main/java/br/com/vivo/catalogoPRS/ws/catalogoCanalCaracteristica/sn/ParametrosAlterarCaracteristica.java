/**
 * ParametrosAlterarCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosAlterarCaracteristica  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosAlterarCaracteristicaParametrosCaracteristicaAlteracao parametrosCaracteristicaAlteracao;

    public ParametrosAlterarCaracteristica() {
    }

    public ParametrosAlterarCaracteristica(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosAlterarCaracteristicaParametrosCaracteristicaAlteracao parametrosCaracteristicaAlteracao) {
           this.parametrosCaracteristicaAlteracao = parametrosCaracteristicaAlteracao;
    }


    /**
     * Gets the parametrosCaracteristicaAlteracao value for this ParametrosAlterarCaracteristica.
     * 
     * @return parametrosCaracteristicaAlteracao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosAlterarCaracteristicaParametrosCaracteristicaAlteracao getParametrosCaracteristicaAlteracao() {
        return parametrosCaracteristicaAlteracao;
    }


    /**
     * Sets the parametrosCaracteristicaAlteracao value for this ParametrosAlterarCaracteristica.
     * 
     * @param parametrosCaracteristicaAlteracao
     */
    public void setParametrosCaracteristicaAlteracao(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ParametrosAlterarCaracteristicaParametrosCaracteristicaAlteracao parametrosCaracteristicaAlteracao) {
        this.parametrosCaracteristicaAlteracao = parametrosCaracteristicaAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarCaracteristica)) return false;
        ParametrosAlterarCaracteristica other = (ParametrosAlterarCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosCaracteristicaAlteracao==null && other.getParametrosCaracteristicaAlteracao()==null) || 
             (this.parametrosCaracteristicaAlteracao!=null &&
              this.parametrosCaracteristicaAlteracao.equals(other.getParametrosCaracteristicaAlteracao())));
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
        if (getParametrosCaracteristicaAlteracao() != null) {
            _hashCode += getParametrosCaracteristicaAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ParametrosAlterarCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosCaracteristicaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ParametrosCaracteristicaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosAlterarCaracteristica>ParametrosCaracteristicaAlteracao"));
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
