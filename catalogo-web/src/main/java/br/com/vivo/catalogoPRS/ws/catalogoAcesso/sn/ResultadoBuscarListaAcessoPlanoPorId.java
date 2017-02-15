/**
 * ResultadoBuscarListaAcessoPlanoPorId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ResultadoBuscarListaAcessoPlanoPorId  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano[] listaAcessoPlanoPorId;

    public ResultadoBuscarListaAcessoPlanoPorId() {
    }

    public ResultadoBuscarListaAcessoPlanoPorId(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano[] listaAcessoPlanoPorId) {
           this.listaAcessoPlanoPorId = listaAcessoPlanoPorId;
    }


    /**
     * Gets the listaAcessoPlanoPorId value for this ResultadoBuscarListaAcessoPlanoPorId.
     * 
     * @return listaAcessoPlanoPorId
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano[] getListaAcessoPlanoPorId() {
        return listaAcessoPlanoPorId;
    }


    /**
     * Sets the listaAcessoPlanoPorId value for this ResultadoBuscarListaAcessoPlanoPorId.
     * 
     * @param listaAcessoPlanoPorId
     */
    public void setListaAcessoPlanoPorId(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoPorIdListaAcessoPlanoPorIdAcessoPlano[] listaAcessoPlanoPorId) {
        this.listaAcessoPlanoPorId = listaAcessoPlanoPorId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaAcessoPlanoPorId)) return false;
        ResultadoBuscarListaAcessoPlanoPorId other = (ResultadoBuscarListaAcessoPlanoPorId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAcessoPlanoPorId==null && other.getListaAcessoPlanoPorId()==null) || 
             (this.listaAcessoPlanoPorId!=null &&
              java.util.Arrays.equals(this.listaAcessoPlanoPorId, other.getListaAcessoPlanoPorId())));
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
        if (getListaAcessoPlanoPorId() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcessoPlanoPorId());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcessoPlanoPorId(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaAcessoPlanoPorId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ResultadoBuscarListaAcessoPlanoPorId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcessoPlanoPorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaAcessoPlanoPorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">>>ResultadoBuscarListaAcessoPlanoPorId>ListaAcessoPlanoPorId>AcessoPlano"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "AcessoPlano"));
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
