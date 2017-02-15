/**
 * ExcluirAcessoServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ExcluirAcessoServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoServico parametrosExcluirAcessoServico;

    public ExcluirAcessoServicoRequest() {
    }

    public ExcluirAcessoServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoServico parametrosExcluirAcessoServico) {
           this.parametrosExcluirAcessoServico = parametrosExcluirAcessoServico;
    }


    /**
     * Gets the parametrosExcluirAcessoServico value for this ExcluirAcessoServicoRequest.
     * 
     * @return parametrosExcluirAcessoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoServico getParametrosExcluirAcessoServico() {
        return parametrosExcluirAcessoServico;
    }


    /**
     * Sets the parametrosExcluirAcessoServico value for this ExcluirAcessoServicoRequest.
     * 
     * @param parametrosExcluirAcessoServico
     */
    public void setParametrosExcluirAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosExcluirAcessoServico parametrosExcluirAcessoServico) {
        this.parametrosExcluirAcessoServico = parametrosExcluirAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExcluirAcessoServicoRequest)) return false;
        ExcluirAcessoServicoRequest other = (ExcluirAcessoServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosExcluirAcessoServico==null && other.getParametrosExcluirAcessoServico()==null) || 
             (this.parametrosExcluirAcessoServico!=null &&
              this.parametrosExcluirAcessoServico.equals(other.getParametrosExcluirAcessoServico())));
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
        if (getParametrosExcluirAcessoServico() != null) {
            _hashCode += getParametrosExcluirAcessoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExcluirAcessoServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">excluirAcessoServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosExcluirAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosExcluirAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosExcluirAcessoServico"));
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
