/**
 * DesassociarProdModDesabilitarVariaveisRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class DesassociarProdModDesabilitarVariaveisRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModelo parametrosDesassociarProdutoModelo;

    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveis parametrosDesabilitarVariaveis;

    public DesassociarProdModDesabilitarVariaveisRequest() {
    }

    public DesassociarProdModDesabilitarVariaveisRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModelo parametrosDesassociarProdutoModelo,
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveis parametrosDesabilitarVariaveis) {
           this.parametrosDesassociarProdutoModelo = parametrosDesassociarProdutoModelo;
           this.parametrosDesabilitarVariaveis = parametrosDesabilitarVariaveis;
    }


    /**
     * Gets the parametrosDesassociarProdutoModelo value for this DesassociarProdModDesabilitarVariaveisRequest.
     * 
     * @return parametrosDesassociarProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModelo getParametrosDesassociarProdutoModelo() {
        return parametrosDesassociarProdutoModelo;
    }


    /**
     * Sets the parametrosDesassociarProdutoModelo value for this DesassociarProdModDesabilitarVariaveisRequest.
     * 
     * @param parametrosDesassociarProdutoModelo
     */
    public void setParametrosDesassociarProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesassociarProdutoModelo parametrosDesassociarProdutoModelo) {
        this.parametrosDesassociarProdutoModelo = parametrosDesassociarProdutoModelo;
    }


    /**
     * Gets the parametrosDesabilitarVariaveis value for this DesassociarProdModDesabilitarVariaveisRequest.
     * 
     * @return parametrosDesabilitarVariaveis
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveis getParametrosDesabilitarVariaveis() {
        return parametrosDesabilitarVariaveis;
    }


    /**
     * Sets the parametrosDesabilitarVariaveis value for this DesassociarProdModDesabilitarVariaveisRequest.
     * 
     * @param parametrosDesabilitarVariaveis
     */
    public void setParametrosDesabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosDesabilitarVariaveis parametrosDesabilitarVariaveis) {
        this.parametrosDesabilitarVariaveis = parametrosDesabilitarVariaveis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DesassociarProdModDesabilitarVariaveisRequest)) return false;
        DesassociarProdModDesabilitarVariaveisRequest other = (DesassociarProdModDesabilitarVariaveisRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosDesassociarProdutoModelo==null && other.getParametrosDesassociarProdutoModelo()==null) || 
             (this.parametrosDesassociarProdutoModelo!=null &&
              this.parametrosDesassociarProdutoModelo.equals(other.getParametrosDesassociarProdutoModelo()))) &&
            ((this.parametrosDesabilitarVariaveis==null && other.getParametrosDesabilitarVariaveis()==null) || 
             (this.parametrosDesabilitarVariaveis!=null &&
              this.parametrosDesabilitarVariaveis.equals(other.getParametrosDesabilitarVariaveis())));
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
        if (getParametrosDesassociarProdutoModelo() != null) {
            _hashCode += getParametrosDesassociarProdutoModelo().hashCode();
        }
        if (getParametrosDesabilitarVariaveis() != null) {
            _hashCode += getParametrosDesabilitarVariaveis().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DesassociarProdModDesabilitarVariaveisRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">desassociarProdModDesabilitarVariaveisRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDesassociarProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosDesassociarProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesassociarProdutoModelo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDesabilitarVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosDesabilitarVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosDesabilitarVariaveis"));
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
