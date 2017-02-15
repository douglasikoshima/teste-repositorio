/**
 * BuscarListaFabricantePorTipoProdutoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn;

public class BuscarListaFabricantePorTipoProdutoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante[] resultadoBuscarListaFabricantePorTipoProduto;

    public BuscarListaFabricantePorTipoProdutoResponse() {
    }

    public BuscarListaFabricantePorTipoProdutoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante[] resultadoBuscarListaFabricantePorTipoProduto) {
           this.resultadoBuscarListaFabricantePorTipoProduto = resultadoBuscarListaFabricantePorTipoProduto;
    }


    /**
     * Gets the resultadoBuscarListaFabricantePorTipoProduto value for this BuscarListaFabricantePorTipoProdutoResponse.
     * 
     * @return resultadoBuscarListaFabricantePorTipoProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante[] getResultadoBuscarListaFabricantePorTipoProduto() {
        return resultadoBuscarListaFabricantePorTipoProduto;
    }


    /**
     * Sets the resultadoBuscarListaFabricantePorTipoProduto value for this BuscarListaFabricantePorTipoProdutoResponse.
     * 
     * @param resultadoBuscarListaFabricantePorTipoProduto
     */
    public void setResultadoBuscarListaFabricantePorTipoProduto(br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn.ResultadoBuscarListaFabricantePorTipoProdutoFabricante[] resultadoBuscarListaFabricantePorTipoProduto) {
        this.resultadoBuscarListaFabricantePorTipoProduto = resultadoBuscarListaFabricantePorTipoProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaFabricantePorTipoProdutoResponse)) return false;
        BuscarListaFabricantePorTipoProdutoResponse other = (BuscarListaFabricantePorTipoProdutoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaFabricantePorTipoProduto==null && other.getResultadoBuscarListaFabricantePorTipoProduto()==null) || 
             (this.resultadoBuscarListaFabricantePorTipoProduto!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaFabricantePorTipoProduto, other.getResultadoBuscarListaFabricantePorTipoProduto())));
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
        if (getResultadoBuscarListaFabricantePorTipoProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaFabricantePorTipoProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaFabricantePorTipoProduto(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaFabricantePorTipoProdutoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", ">buscarListaFabricantePorTipoProdutoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaFabricantePorTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "ResultadoBuscarListaFabricantePorTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", ">ResultadoBuscarListaFabricantePorTipoProduto"));
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
