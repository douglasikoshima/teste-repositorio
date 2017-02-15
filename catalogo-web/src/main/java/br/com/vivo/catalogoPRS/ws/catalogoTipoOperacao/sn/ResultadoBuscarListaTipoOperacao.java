/**
 * ResultadoBuscarListaTipoOperacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn;

public class ResultadoBuscarListaTipoOperacao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] listaTipoOperacao;

    public ResultadoBuscarListaTipoOperacao() {
    }

    public ResultadoBuscarListaTipoOperacao(
           br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] listaTipoOperacao) {
           this.listaTipoOperacao = listaTipoOperacao;
    }


    /**
     * Gets the listaTipoOperacao value for this ResultadoBuscarListaTipoOperacao.
     * 
     * @return listaTipoOperacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] getListaTipoOperacao() {
        return listaTipoOperacao;
    }


    /**
     * Sets the listaTipoOperacao value for this ResultadoBuscarListaTipoOperacao.
     * 
     * @param listaTipoOperacao
     */
    public void setListaTipoOperacao(br.com.vivo.catalogoPRS.ws.catalogoTipoOperacao.sn.ResultadoBuscarListaTipoOperacaoListaTipoOperacaoTipoOperacao[] listaTipoOperacao) {
        this.listaTipoOperacao = listaTipoOperacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaTipoOperacao)) return false;
        ResultadoBuscarListaTipoOperacao other = (ResultadoBuscarListaTipoOperacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaTipoOperacao==null && other.getListaTipoOperacao()==null) || 
             (this.listaTipoOperacao!=null &&
              java.util.Arrays.equals(this.listaTipoOperacao, other.getListaTipoOperacao())));
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
        if (getListaTipoOperacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTipoOperacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTipoOperacao(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaTipoOperacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", ">ResultadoBuscarListaTipoOperacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", "ListaTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", ">>>ResultadoBuscarListaTipoOperacao>ListaTipoOperacao>TipoOperacao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoOperacao", "TipoOperacao"));
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
