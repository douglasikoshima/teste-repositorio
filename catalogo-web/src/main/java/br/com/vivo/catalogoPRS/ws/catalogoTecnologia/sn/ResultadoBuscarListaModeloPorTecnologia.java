/**
 * ResultadoBuscarListaModeloPorTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ResultadoBuscarListaModeloPorTecnologia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia[] listaModeloPorTecnologia;

    public ResultadoBuscarListaModeloPorTecnologia() {
    }

    public ResultadoBuscarListaModeloPorTecnologia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia[] listaModeloPorTecnologia) {
           this.listaModeloPorTecnologia = listaModeloPorTecnologia;
    }


    /**
     * Gets the listaModeloPorTecnologia value for this ResultadoBuscarListaModeloPorTecnologia.
     * 
     * @return listaModeloPorTecnologia
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia[] getListaModeloPorTecnologia() {
        return listaModeloPorTecnologia;
    }


    /**
     * Sets the listaModeloPorTecnologia value for this ResultadoBuscarListaModeloPorTecnologia.
     * 
     * @param listaModeloPorTecnologia
     */
    public void setListaModeloPorTecnologia(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaModeloPorTecnologiaListaModeloPorTecnologiaModeloPorTecnologia[] listaModeloPorTecnologia) {
        this.listaModeloPorTecnologia = listaModeloPorTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaModeloPorTecnologia)) return false;
        ResultadoBuscarListaModeloPorTecnologia other = (ResultadoBuscarListaModeloPorTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaModeloPorTecnologia==null && other.getListaModeloPorTecnologia()==null) || 
             (this.listaModeloPorTecnologia!=null &&
              java.util.Arrays.equals(this.listaModeloPorTecnologia, other.getListaModeloPorTecnologia())));
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
        if (getListaModeloPorTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModeloPorTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModeloPorTecnologia(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaModeloPorTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ResultadoBuscarListaModeloPorTecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModeloPorTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ListaModeloPorTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>>ResultadoBuscarListaModeloPorTecnologia>ListaModeloPorTecnologia>ModeloPorTecnologia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ModeloPorTecnologia"));
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
