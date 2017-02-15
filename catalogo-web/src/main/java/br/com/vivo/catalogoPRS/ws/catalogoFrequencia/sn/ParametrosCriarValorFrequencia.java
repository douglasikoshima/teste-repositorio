/**
 * ParametrosCriarValorFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ParametrosCriarValorFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ListaVlFrequenciaVlfrequencia[] listaVlFrequencia;

    public ParametrosCriarValorFrequencia() {
    }

    public ParametrosCriarValorFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ListaVlFrequenciaVlfrequencia[] listaVlFrequencia) {
           this.listaVlFrequencia = listaVlFrequencia;
    }


    /**
     * Gets the listaVlFrequencia value for this ParametrosCriarValorFrequencia.
     * 
     * @return listaVlFrequencia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ListaVlFrequenciaVlfrequencia[] getListaVlFrequencia() {
        return listaVlFrequencia;
    }


    /**
     * Sets the listaVlFrequencia value for this ParametrosCriarValorFrequencia.
     * 
     * @param listaVlFrequencia
     */
    public void setListaVlFrequencia(br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ListaVlFrequenciaVlfrequencia[] listaVlFrequencia) {
        this.listaVlFrequencia = listaVlFrequencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosCriarValorFrequencia)) return false;
        ParametrosCriarValorFrequencia other = (ParametrosCriarValorFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaVlFrequencia==null && other.getListaVlFrequencia()==null) || 
             (this.listaVlFrequencia!=null &&
              java.util.Arrays.equals(this.listaVlFrequencia, other.getListaVlFrequencia())));
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
        if (getListaVlFrequencia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaVlFrequencia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaVlFrequencia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosCriarValorFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ParametrosCriarValorFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaVlFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "ListaVlFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">ListaVlFrequencia"));
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
