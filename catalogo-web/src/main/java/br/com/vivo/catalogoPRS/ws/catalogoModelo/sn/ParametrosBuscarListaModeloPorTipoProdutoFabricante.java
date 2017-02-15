/**
 * ParametrosBuscarListaModeloPorTipoProdutoFabricante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarListaModeloPorTipoProdutoFabricante  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn raizModeloIn;

    public ParametrosBuscarListaModeloPorTipoProdutoFabricante() {
    }

    public ParametrosBuscarListaModeloPorTipoProdutoFabricante(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn raizModeloIn) {
           this.raizModeloIn = raizModeloIn;
    }


    /**
     * Gets the raizModeloIn value for this ParametrosBuscarListaModeloPorTipoProdutoFabricante.
     * 
     * @return raizModeloIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn getRaizModeloIn() {
        return raizModeloIn;
    }


    /**
     * Sets the raizModeloIn value for this ParametrosBuscarListaModeloPorTipoProdutoFabricante.
     * 
     * @param raizModeloIn
     */
    public void setRaizModeloIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosBuscarListaModeloPorTipoProdutoFabricanteRaizModeloIn raizModeloIn) {
        this.raizModeloIn = raizModeloIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloPorTipoProdutoFabricante)) return false;
        ParametrosBuscarListaModeloPorTipoProdutoFabricante other = (ParametrosBuscarListaModeloPorTipoProdutoFabricante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.raizModeloIn==null && other.getRaizModeloIn()==null) || 
             (this.raizModeloIn!=null &&
              this.raizModeloIn.equals(other.getRaizModeloIn())));
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
        if (getRaizModeloIn() != null) {
            _hashCode += getRaizModeloIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloPorTipoProdutoFabricante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosBuscarListaModeloPorTipoProdutoFabricante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("raizModeloIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "RaizModeloIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarListaModeloPorTipoProdutoFabricante>RaizModeloIn"));
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
