/**
 * BuscarListaAcessoServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class BuscarListaAcessoServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico;

    public BuscarListaAcessoServicoRequest() {
    }

    public BuscarListaAcessoServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico) {
           this.parametrosBuscarListaAcessoServico = parametrosBuscarListaAcessoServico;
    }


    /**
     * Gets the parametrosBuscarListaAcessoServico value for this BuscarListaAcessoServicoRequest.
     * 
     * @return parametrosBuscarListaAcessoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico getParametrosBuscarListaAcessoServico() {
        return parametrosBuscarListaAcessoServico;
    }


    /**
     * Sets the parametrosBuscarListaAcessoServico value for this BuscarListaAcessoServicoRequest.
     * 
     * @param parametrosBuscarListaAcessoServico
     */
    public void setParametrosBuscarListaAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoServico parametrosBuscarListaAcessoServico) {
        this.parametrosBuscarListaAcessoServico = parametrosBuscarListaAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAcessoServicoRequest)) return false;
        BuscarListaAcessoServicoRequest other = (BuscarListaAcessoServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaAcessoServico==null && other.getParametrosBuscarListaAcessoServico()==null) || 
             (this.parametrosBuscarListaAcessoServico!=null &&
              this.parametrosBuscarListaAcessoServico.equals(other.getParametrosBuscarListaAcessoServico())));
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
        if (getParametrosBuscarListaAcessoServico() != null) {
            _hashCode += getParametrosBuscarListaAcessoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAcessoServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">buscarListaAcessoServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosBuscarListaAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoServico"));
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
