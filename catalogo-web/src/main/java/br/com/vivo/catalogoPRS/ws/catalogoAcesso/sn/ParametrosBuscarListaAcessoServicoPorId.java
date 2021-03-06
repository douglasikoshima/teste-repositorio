/**
 * ParametrosBuscarListaAcessoServicoPorId.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosBuscarListaAcessoServicoPorId  implements java.io.Serializable {
    private long[] listaAcessoServico;

    public ParametrosBuscarListaAcessoServicoPorId() {
    }

    public ParametrosBuscarListaAcessoServicoPorId(
           long[] listaAcessoServico) {
           this.listaAcessoServico = listaAcessoServico;
    }


    /**
     * Gets the listaAcessoServico value for this ParametrosBuscarListaAcessoServicoPorId.
     * 
     * @return listaAcessoServico
     */
    public long[] getListaAcessoServico() {
        return listaAcessoServico;
    }


    /**
     * Sets the listaAcessoServico value for this ParametrosBuscarListaAcessoServicoPorId.
     * 
     * @param listaAcessoServico
     */
    public void setListaAcessoServico(long[] listaAcessoServico) {
        this.listaAcessoServico = listaAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaAcessoServicoPorId)) return false;
        ParametrosBuscarListaAcessoServicoPorId other = (ParametrosBuscarListaAcessoServicoPorId) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaAcessoServico==null && other.getListaAcessoServico()==null) || 
             (this.listaAcessoServico!=null &&
              java.util.Arrays.equals(this.listaAcessoServico, other.getListaAcessoServico())));
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
        if (getListaAcessoServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcessoServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcessoServico(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaAcessoServicoPorId.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoServicoPorId"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idAcessoServico"));
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
