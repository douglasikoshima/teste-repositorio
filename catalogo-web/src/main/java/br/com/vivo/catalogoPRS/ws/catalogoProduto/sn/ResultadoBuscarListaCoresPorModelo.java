/**
 * ResultadoBuscarListaCoresPorModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ResultadoBuscarListaCoresPorModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor[] listaCores;

    public ResultadoBuscarListaCoresPorModelo() {
    }

    public ResultadoBuscarListaCoresPorModelo(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor[] listaCores) {
           this.listaCores = listaCores;
    }


    /**
     * Gets the listaCores value for this ResultadoBuscarListaCoresPorModelo.
     * 
     * @return listaCores
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor[] getListaCores() {
        return listaCores;
    }


    /**
     * Sets the listaCores value for this ResultadoBuscarListaCoresPorModelo.
     * 
     * @param listaCores
     */
    public void setListaCores(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ResultadoBuscarListaCoresPorModeloListaCoresCor[] listaCores) {
        this.listaCores = listaCores;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCoresPorModelo)) return false;
        ResultadoBuscarListaCoresPorModelo other = (ResultadoBuscarListaCoresPorModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaCores==null && other.getListaCores()==null) || 
             (this.listaCores!=null &&
              java.util.Arrays.equals(this.listaCores, other.getListaCores())));
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
        if (getListaCores() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCores());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCores(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCoresPorModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ResultadoBuscarListaCoresPorModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCores");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ListaCores"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>>ResultadoBuscarListaCoresPorModelo>ListaCores>Cor"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "Cor"));
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
