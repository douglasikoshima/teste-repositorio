/**
 * ResultadoBuscarListaQtdeMaxAtivacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaQtdeMaxAtivacao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo[] listaAtivacaoServico;

    public ResultadoBuscarListaQtdeMaxAtivacao() {
    }

    public ResultadoBuscarListaQtdeMaxAtivacao(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo[] listaAtivacaoServico) {
           this.listaAtivacaoServico = listaAtivacaoServico;
    }


    /**
     * Gets the listaAtivacaoServico value for this ResultadoBuscarListaQtdeMaxAtivacao.
     * 
     * @return listaAtivacaoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo[] getListaAtivacaoServico() {
        return listaAtivacaoServico;
    }


    /**
     * Sets the listaAtivacaoServico value for this ResultadoBuscarListaQtdeMaxAtivacao.
     * 
     * @param listaAtivacaoServico
     */
    public void setListaAtivacaoServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaQtdeMaxAtivacaoListaAtivacaoServicoRetornoAtivacaoServivo[] listaAtivacaoServico) {
        this.listaAtivacaoServico = listaAtivacaoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaQtdeMaxAtivacao)) return false;
        ResultadoBuscarListaQtdeMaxAtivacao other = (ResultadoBuscarListaQtdeMaxAtivacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAtivacaoServico==null && other.getListaAtivacaoServico()==null) || 
             (this.listaAtivacaoServico!=null &&
              java.util.Arrays.equals(this.listaAtivacaoServico, other.getListaAtivacaoServico())));
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
        if (getListaAtivacaoServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAtivacaoServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAtivacaoServico(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaQtdeMaxAtivacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaQtdeMaxAtivacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAtivacaoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaAtivacaoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaQtdeMaxAtivacao>ListaAtivacaoServico>RetornoAtivacaoServivo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "RetornoAtivacaoServivo"));
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
