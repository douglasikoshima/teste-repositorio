/**
 * IncluirAcessoServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class IncluirAcessoServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServico parametrosIncluirAcessoServico;

    public IncluirAcessoServicoRequest() {
    }

    public IncluirAcessoServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServico parametrosIncluirAcessoServico) {
           this.parametrosIncluirAcessoServico = parametrosIncluirAcessoServico;
    }


    /**
     * Gets the parametrosIncluirAcessoServico value for this IncluirAcessoServicoRequest.
     * 
     * @return parametrosIncluirAcessoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServico getParametrosIncluirAcessoServico() {
        return parametrosIncluirAcessoServico;
    }


    /**
     * Sets the parametrosIncluirAcessoServico value for this IncluirAcessoServicoRequest.
     * 
     * @param parametrosIncluirAcessoServico
     */
    public void setParametrosIncluirAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoServico parametrosIncluirAcessoServico) {
        this.parametrosIncluirAcessoServico = parametrosIncluirAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IncluirAcessoServicoRequest)) return false;
        IncluirAcessoServicoRequest other = (IncluirAcessoServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosIncluirAcessoServico==null && other.getParametrosIncluirAcessoServico()==null) || 
             (this.parametrosIncluirAcessoServico!=null &&
              this.parametrosIncluirAcessoServico.equals(other.getParametrosIncluirAcessoServico())));
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
        if (getParametrosIncluirAcessoServico() != null) {
            _hashCode += getParametrosIncluirAcessoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IncluirAcessoServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">incluirAcessoServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosIncluirAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosIncluirAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosIncluirAcessoServico"));
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
