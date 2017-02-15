/**
 * ResultadoBuscarListaServicoParametrizacaoSemPag.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoParametrizacaoSemPag  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPagListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] listaBuscarListaServicoParametrizacao;

    public ResultadoBuscarListaServicoParametrizacaoSemPag() {
    }

    public ResultadoBuscarListaServicoParametrizacaoSemPag(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPagListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] listaBuscarListaServicoParametrizacao) {
           this.listaBuscarListaServicoParametrizacao = listaBuscarListaServicoParametrizacao;
    }


    /**
     * Gets the listaBuscarListaServicoParametrizacao value for this ResultadoBuscarListaServicoParametrizacaoSemPag.
     * 
     * @return listaBuscarListaServicoParametrizacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPagListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] getListaBuscarListaServicoParametrizacao() {
        return listaBuscarListaServicoParametrizacao;
    }


    /**
     * Sets the listaBuscarListaServicoParametrizacao value for this ResultadoBuscarListaServicoParametrizacaoSemPag.
     * 
     * @param listaBuscarListaServicoParametrizacao
     */
    public void setListaBuscarListaServicoParametrizacao(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoSemPagListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] listaBuscarListaServicoParametrizacao) {
        this.listaBuscarListaServicoParametrizacao = listaBuscarListaServicoParametrizacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoParametrizacaoSemPag)) return false;
        ResultadoBuscarListaServicoParametrizacaoSemPag other = (ResultadoBuscarListaServicoParametrizacaoSemPag) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaBuscarListaServicoParametrizacao==null && other.getListaBuscarListaServicoParametrizacao()==null) || 
             (this.listaBuscarListaServicoParametrizacao!=null &&
              java.util.Arrays.equals(this.listaBuscarListaServicoParametrizacao, other.getListaBuscarListaServicoParametrizacao())));
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
        if (getListaBuscarListaServicoParametrizacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaBuscarListaServicoParametrizacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaBuscarListaServicoParametrizacao(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoParametrizacaoSemPag.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoParametrizacaoSemPag"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaBuscarListaServicoParametrizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaBuscarListaServicoParametrizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaServicoParametrizacaoSemPag>ListaBuscarListaServicoParametrizacao>RetornoBuscarListaServicoParametrizacao"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "RetornoBuscarListaServicoParametrizacao"));
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
