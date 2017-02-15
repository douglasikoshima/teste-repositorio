/**
 * BuscarListaPlataformaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn;

public class BuscarListaPlataformaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma[] resultadoBuscarListaPlataforma;

    public BuscarListaPlataformaResponse() {
    }

    public BuscarListaPlataformaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma[] resultadoBuscarListaPlataforma) {
           this.resultadoBuscarListaPlataforma = resultadoBuscarListaPlataforma;
    }


    /**
     * Gets the resultadoBuscarListaPlataforma value for this BuscarListaPlataformaResponse.
     * 
     * @return resultadoBuscarListaPlataforma
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma[] getResultadoBuscarListaPlataforma() {
        return resultadoBuscarListaPlataforma;
    }


    /**
     * Sets the resultadoBuscarListaPlataforma value for this BuscarListaPlataformaResponse.
     * 
     * @param resultadoBuscarListaPlataforma
     */
    public void setResultadoBuscarListaPlataforma(br.com.vivo.catalogoPRS.ws.catalogoPlataforma.sn.ResultadoBuscarListaPlataformaPlataforma[] resultadoBuscarListaPlataforma) {
        this.resultadoBuscarListaPlataforma = resultadoBuscarListaPlataforma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlataformaResponse)) return false;
        BuscarListaPlataformaResponse other = (BuscarListaPlataformaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaPlataforma==null && other.getResultadoBuscarListaPlataforma()==null) || 
             (this.resultadoBuscarListaPlataforma!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaPlataforma, other.getResultadoBuscarListaPlataforma())));
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
        if (getResultadoBuscarListaPlataforma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaPlataforma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaPlataforma(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaPlataformaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlataforma", ">buscarListaPlataformaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlataforma", "ResultadoBuscarListaPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlataforma", ">ResultadoBuscarListaPlataforma"));
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
