/**
 * ResultadoBuscarListaAcessoServicoPorId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ResultadoBuscarListaAcessoServicoPorId  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico[] listaAcessoServicoPorId;

    public ResultadoBuscarListaAcessoServicoPorId() {
    }

    public ResultadoBuscarListaAcessoServicoPorId(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico[] listaAcessoServicoPorId) {
           this.listaAcessoServicoPorId = listaAcessoServicoPorId;
    }


    /**
     * Gets the listaAcessoServicoPorId value for this ResultadoBuscarListaAcessoServicoPorId.
     * 
     * @return listaAcessoServicoPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico[] getListaAcessoServicoPorId() {
        return listaAcessoServicoPorId;
    }


    /**
     * Sets the listaAcessoServicoPorId value for this ResultadoBuscarListaAcessoServicoPorId.
     * 
     * @param listaAcessoServicoPorId
     */
    public void setListaAcessoServicoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoPorIdListaAcessoServicoPorIdAcessoServico[] listaAcessoServicoPorId) {
        this.listaAcessoServicoPorId = listaAcessoServicoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaAcessoServicoPorId)) return false;
        ResultadoBuscarListaAcessoServicoPorId other = (ResultadoBuscarListaAcessoServicoPorId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAcessoServicoPorId==null && other.getListaAcessoServicoPorId()==null) || 
             (this.listaAcessoServicoPorId!=null &&
              java.util.Arrays.equals(this.listaAcessoServicoPorId, other.getListaAcessoServicoPorId())));
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
        if (getListaAcessoServicoPorId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcessoServicoPorId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcessoServicoPorId(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaAcessoServicoPorId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ResultadoBuscarListaAcessoServicoPorId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcessoServicoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaAcessoServicoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ResultadoBuscarListaAcessoServicoPorId>ListaAcessoServicoPorId>AcessoServico"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "AcessoServico"));
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
