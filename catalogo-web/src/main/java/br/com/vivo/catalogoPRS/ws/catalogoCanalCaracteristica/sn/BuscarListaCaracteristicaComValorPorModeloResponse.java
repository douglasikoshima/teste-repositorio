/**
 * BuscarListaCaracteristicaComValorPorModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class BuscarListaCaracteristicaComValorPorModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristica[] listaCaracteristica;

    public BuscarListaCaracteristicaComValorPorModeloResponse() {
    }

    public BuscarListaCaracteristicaComValorPorModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristica[] listaCaracteristica) {
           this.listaCaracteristica = listaCaracteristica;
    }


    /**
     * Gets the listaCaracteristica value for this BuscarListaCaracteristicaComValorPorModeloResponse.
     * 
     * @return listaCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristica[] getListaCaracteristica() {
        return listaCaracteristica;
    }


    /**
     * Sets the listaCaracteristica value for this BuscarListaCaracteristicaComValorPorModeloResponse.
     * 
     * @param listaCaracteristica
     */
    public void setListaCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn.ListaCaracteristicaCaracteristica[] listaCaracteristica) {
        this.listaCaracteristica = listaCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaCaracteristicaComValorPorModeloResponse)) return false;
        BuscarListaCaracteristicaComValorPorModeloResponse other = (BuscarListaCaracteristicaComValorPorModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaCaracteristica==null && other.getListaCaracteristica()==null) || 
             (this.listaCaracteristica!=null &&
              java.util.Arrays.equals(this.listaCaracteristica, other.getListaCaracteristica())));
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
        if (getListaCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCaracteristica(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaCaracteristicaComValorPorModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">buscarListaCaracteristicaComValorPorModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "ListaCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">ListaCaracteristica"));
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