/**
 * AssociarProdModeloHabilitarVariaveisRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class AssociarProdModeloHabilitarVariaveisRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModelo parametrosAssociaProdutoModelo;

    private br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveis parametrosHabilitarVariaveis;

    public AssociarProdModeloHabilitarVariaveisRequest() {
    }

    public AssociarProdModeloHabilitarVariaveisRequest(
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModelo parametrosAssociaProdutoModelo,
           br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveis parametrosHabilitarVariaveis) {
           this.parametrosAssociaProdutoModelo = parametrosAssociaProdutoModelo;
           this.parametrosHabilitarVariaveis = parametrosHabilitarVariaveis;
    }


    /**
     * Gets the parametrosAssociaProdutoModelo value for this AssociarProdModeloHabilitarVariaveisRequest.
     * 
     * @return parametrosAssociaProdutoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModelo getParametrosAssociaProdutoModelo() {
        return parametrosAssociaProdutoModelo;
    }


    /**
     * Sets the parametrosAssociaProdutoModelo value for this AssociarProdModeloHabilitarVariaveisRequest.
     * 
     * @param parametrosAssociaProdutoModelo
     */
    public void setParametrosAssociaProdutoModelo(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosAssociaProdutoModelo parametrosAssociaProdutoModelo) {
        this.parametrosAssociaProdutoModelo = parametrosAssociaProdutoModelo;
    }


    /**
     * Gets the parametrosHabilitarVariaveis value for this AssociarProdModeloHabilitarVariaveisRequest.
     * 
     * @return parametrosHabilitarVariaveis
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveis getParametrosHabilitarVariaveis() {
        return parametrosHabilitarVariaveis;
    }


    /**
     * Sets the parametrosHabilitarVariaveis value for this AssociarProdModeloHabilitarVariaveisRequest.
     * 
     * @param parametrosHabilitarVariaveis
     */
    public void setParametrosHabilitarVariaveis(br.com.vivo.catalogoPRS.ws.catalogoProduto.sn.ParametrosHabilitarVariaveis parametrosHabilitarVariaveis) {
        this.parametrosHabilitarVariaveis = parametrosHabilitarVariaveis;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssociarProdModeloHabilitarVariaveisRequest)) return false;
        AssociarProdModeloHabilitarVariaveisRequest other = (AssociarProdModeloHabilitarVariaveisRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAssociaProdutoModelo==null && other.getParametrosAssociaProdutoModelo()==null) || 
             (this.parametrosAssociaProdutoModelo!=null &&
              this.parametrosAssociaProdutoModelo.equals(other.getParametrosAssociaProdutoModelo()))) &&
            ((this.parametrosHabilitarVariaveis==null && other.getParametrosHabilitarVariaveis()==null) || 
             (this.parametrosHabilitarVariaveis!=null &&
              this.parametrosHabilitarVariaveis.equals(other.getParametrosHabilitarVariaveis())));
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
        if (getParametrosAssociaProdutoModelo() != null) {
            _hashCode += getParametrosAssociaProdutoModelo().hashCode();
        }
        if (getParametrosHabilitarVariaveis() != null) {
            _hashCode += getParametrosHabilitarVariaveis().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssociarProdModeloHabilitarVariaveisRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">associarProdModeloHabilitarVariaveisRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAssociaProdutoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosAssociaProdutoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosAssociaProdutoModelo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosHabilitarVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "ParametrosHabilitarVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">ParametrosHabilitarVariaveis"));
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
