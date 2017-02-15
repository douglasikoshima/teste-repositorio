/**
 * BuscarListaFabricanteResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn;

public class BuscarListaFabricanteResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricanteFabricante[] resultadoBuscarListaFabricante;

    public BuscarListaFabricanteResponse() {
    }

    public BuscarListaFabricanteResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricanteFabricante[] resultadoBuscarListaFabricante) {
           this.resultadoBuscarListaFabricante = resultadoBuscarListaFabricante;
    }


    /**
     * Gets the resultadoBuscarListaFabricante value for this BuscarListaFabricanteResponse.
     * 
     * @return resultadoBuscarListaFabricante
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricanteFabricante[] getResultadoBuscarListaFabricante() {
        return resultadoBuscarListaFabricante;
    }


    /**
     * Sets the resultadoBuscarListaFabricante value for this BuscarListaFabricanteResponse.
     * 
     * @param resultadoBuscarListaFabricante
     */
    public void setResultadoBuscarListaFabricante(br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricanteFabricante[] resultadoBuscarListaFabricante) {
        this.resultadoBuscarListaFabricante = resultadoBuscarListaFabricante;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaFabricanteResponse)) return false;
        BuscarListaFabricanteResponse other = (BuscarListaFabricanteResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaFabricante==null && other.getResultadoBuscarListaFabricante()==null) || 
             (this.resultadoBuscarListaFabricante!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaFabricante, other.getResultadoBuscarListaFabricante())));
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
        if (getResultadoBuscarListaFabricante() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaFabricante());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaFabricante(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaFabricanteResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", ">buscarListaFabricanteResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "ResultadoBuscarListaFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", ">ResultadoBuscarListaFabricante"));
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
