/**
 * ResultadoAcao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public class ResultadoAcao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcaoListaAcaoAcao[] listaAcao;

    public ResultadoAcao() {
    }

    public ResultadoAcao(
           br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcaoListaAcaoAcao[] listaAcao) {
           this.listaAcao = listaAcao;
    }


    /**
     * Gets the listaAcao value for this ResultadoAcao.
     * 
     * @return listaAcao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcaoListaAcaoAcao[] getListaAcao() {
        return listaAcao;
    }


    /**
     * Sets the listaAcao value for this ResultadoAcao.
     * 
     * @param listaAcao
     */
    public void setListaAcao(br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcaoListaAcaoAcao[] listaAcao) {
        this.listaAcao = listaAcao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoAcao)) return false;
        ResultadoAcao other = (ResultadoAcao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAcao==null && other.getListaAcao()==null) || 
             (this.listaAcao!=null &&
              java.util.Arrays.equals(this.listaAcao, other.getListaAcao())));
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
        if (getListaAcao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcao(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoAcao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">ResultadoAcao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "ListaAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">>>ResultadoAcao>ListaAcao>Acao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "Acao"));
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
