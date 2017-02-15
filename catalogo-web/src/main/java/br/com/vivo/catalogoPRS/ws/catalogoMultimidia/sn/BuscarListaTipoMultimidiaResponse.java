/**
 * BuscarListaTipoMultimidiaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class BuscarListaTipoMultimidiaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ListaTipoMultimidiaTipoMultimidia[] listaTipoMultimidia;

    public BuscarListaTipoMultimidiaResponse() {
    }

    public BuscarListaTipoMultimidiaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ListaTipoMultimidiaTipoMultimidia[] listaTipoMultimidia) {
           this.listaTipoMultimidia = listaTipoMultimidia;
    }


    /**
     * Gets the listaTipoMultimidia value for this BuscarListaTipoMultimidiaResponse.
     * 
     * @return listaTipoMultimidia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ListaTipoMultimidiaTipoMultimidia[] getListaTipoMultimidia() {
        return listaTipoMultimidia;
    }


    /**
     * Sets the listaTipoMultimidia value for this BuscarListaTipoMultimidiaResponse.
     * 
     * @param listaTipoMultimidia
     */
    public void setListaTipoMultimidia(br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ListaTipoMultimidiaTipoMultimidia[] listaTipoMultimidia) {
        this.listaTipoMultimidia = listaTipoMultimidia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaTipoMultimidiaResponse)) return false;
        BuscarListaTipoMultimidiaResponse other = (BuscarListaTipoMultimidiaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaTipoMultimidia==null && other.getListaTipoMultimidia()==null) || 
             (this.listaTipoMultimidia!=null &&
              java.util.Arrays.equals(this.listaTipoMultimidia, other.getListaTipoMultimidia())));
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
        if (getListaTipoMultimidia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTipoMultimidia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTipoMultimidia(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaTipoMultimidiaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">buscarListaTipoMultimidiaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ListaTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ListaTipoMultimidia"));
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
