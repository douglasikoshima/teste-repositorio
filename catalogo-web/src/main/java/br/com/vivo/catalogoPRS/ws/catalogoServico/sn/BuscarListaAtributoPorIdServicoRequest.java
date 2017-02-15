/**
 * BuscarListaAtributoPorIdServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaAtributoPorIdServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaAtributoPorIdServico parametrosBuscarListaAtributoPorIdServico;

    public BuscarListaAtributoPorIdServicoRequest() {
    }

    public BuscarListaAtributoPorIdServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaAtributoPorIdServico parametrosBuscarListaAtributoPorIdServico) {
           this.parametrosBuscarListaAtributoPorIdServico = parametrosBuscarListaAtributoPorIdServico;
    }


    /**
     * Gets the parametrosBuscarListaAtributoPorIdServico value for this BuscarListaAtributoPorIdServicoRequest.
     * 
     * @return parametrosBuscarListaAtributoPorIdServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaAtributoPorIdServico getParametrosBuscarListaAtributoPorIdServico() {
        return parametrosBuscarListaAtributoPorIdServico;
    }


    /**
     * Sets the parametrosBuscarListaAtributoPorIdServico value for this BuscarListaAtributoPorIdServicoRequest.
     * 
     * @param parametrosBuscarListaAtributoPorIdServico
     */
    public void setParametrosBuscarListaAtributoPorIdServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ParametrosBuscarListaAtributoPorIdServico parametrosBuscarListaAtributoPorIdServico) {
        this.parametrosBuscarListaAtributoPorIdServico = parametrosBuscarListaAtributoPorIdServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAtributoPorIdServicoRequest)) return false;
        BuscarListaAtributoPorIdServicoRequest other = (BuscarListaAtributoPorIdServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaAtributoPorIdServico==null && other.getParametrosBuscarListaAtributoPorIdServico()==null) || 
             (this.parametrosBuscarListaAtributoPorIdServico!=null &&
              this.parametrosBuscarListaAtributoPorIdServico.equals(other.getParametrosBuscarListaAtributoPorIdServico())));
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
        if (getParametrosBuscarListaAtributoPorIdServico() != null) {
            _hashCode += getParametrosBuscarListaAtributoPorIdServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAtributoPorIdServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaAtributoPorIdServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaAtributoPorIdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ParametrosBuscarListaAtributoPorIdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaAtributoPorIdServico"));
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
