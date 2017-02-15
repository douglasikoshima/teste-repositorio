/**
 * ResultBuscarListaMultimidia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ResultBuscarListaMultimidia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultimidiaListaMultimidiaMultimidia[] listaMultimidia;

    public ResultBuscarListaMultimidia() {
    }

    public ResultBuscarListaMultimidia(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultimidiaListaMultimidiaMultimidia[] listaMultimidia) {
           this.listaMultimidia = listaMultimidia;
    }


    /**
     * Gets the listaMultimidia value for this ResultBuscarListaMultimidia.
     * 
     * @return listaMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultimidiaListaMultimidiaMultimidia[] getListaMultimidia() {
        return listaMultimidia;
    }


    /**
     * Sets the listaMultimidia value for this ResultBuscarListaMultimidia.
     * 
     * @param listaMultimidia
     */
    public void setListaMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultimidiaListaMultimidiaMultimidia[] listaMultimidia) {
        this.listaMultimidia = listaMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultBuscarListaMultimidia)) return false;
        ResultBuscarListaMultimidia other = (ResultBuscarListaMultimidia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaMultimidia==null && other.getListaMultimidia()==null) || 
             (this.listaMultimidia!=null &&
              java.util.Arrays.equals(this.listaMultimidia, other.getListaMultimidia())));
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
        if (getListaMultimidia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaMultimidia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaMultimidia(), i);
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
        new org.apache.axis.description.TypeDesc(ResultBuscarListaMultimidia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ResultBuscarListaMultimidia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ListaMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">>>ResultBuscarListaMultimidia>ListaMultimidia>Multimidia"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "Multimidia"));
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
