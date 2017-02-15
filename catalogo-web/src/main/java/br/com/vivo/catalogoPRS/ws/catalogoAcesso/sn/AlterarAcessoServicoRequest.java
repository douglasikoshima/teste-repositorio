/**
 * AlterarAcessoServicoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class AlterarAcessoServicoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoServico parametrosAlterarAcessoServico;

    public AlterarAcessoServicoRequest() {
    }

    public AlterarAcessoServicoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoServico parametrosAlterarAcessoServico) {
           this.parametrosAlterarAcessoServico = parametrosAlterarAcessoServico;
    }


    /**
     * Gets the parametrosAlterarAcessoServico value for this AlterarAcessoServicoRequest.
     * 
     * @return parametrosAlterarAcessoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoServico getParametrosAlterarAcessoServico() {
        return parametrosAlterarAcessoServico;
    }


    /**
     * Sets the parametrosAlterarAcessoServico value for this AlterarAcessoServicoRequest.
     * 
     * @param parametrosAlterarAcessoServico
     */
    public void setParametrosAlterarAcessoServico(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoServico parametrosAlterarAcessoServico) {
        this.parametrosAlterarAcessoServico = parametrosAlterarAcessoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarAcessoServicoRequest)) return false;
        AlterarAcessoServicoRequest other = (AlterarAcessoServicoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarAcessoServico==null && other.getParametrosAlterarAcessoServico()==null) || 
             (this.parametrosAlterarAcessoServico!=null &&
              this.parametrosAlterarAcessoServico.equals(other.getParametrosAlterarAcessoServico())));
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
        if (getParametrosAlterarAcessoServico() != null) {
            _hashCode += getParametrosAlterarAcessoServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarAcessoServicoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">alterarAcessoServicoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarAcessoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosAlterarAcessoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosAlterarAcessoServico"));
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
