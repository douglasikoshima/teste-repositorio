/**
 * AlterarAcessoPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class AlterarAcessoPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoPlano parametrosAlterarAcessoPlano;

    public AlterarAcessoPlanoRequest() {
    }

    public AlterarAcessoPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoPlano parametrosAlterarAcessoPlano) {
           this.parametrosAlterarAcessoPlano = parametrosAlterarAcessoPlano;
    }


    /**
     * Gets the parametrosAlterarAcessoPlano value for this AlterarAcessoPlanoRequest.
     * 
     * @return parametrosAlterarAcessoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoPlano getParametrosAlterarAcessoPlano() {
        return parametrosAlterarAcessoPlano;
    }


    /**
     * Sets the parametrosAlterarAcessoPlano value for this AlterarAcessoPlanoRequest.
     * 
     * @param parametrosAlterarAcessoPlano
     */
    public void setParametrosAlterarAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosAlterarAcessoPlano parametrosAlterarAcessoPlano) {
        this.parametrosAlterarAcessoPlano = parametrosAlterarAcessoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AlterarAcessoPlanoRequest)) return false;
        AlterarAcessoPlanoRequest other = (AlterarAcessoPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosAlterarAcessoPlano==null && other.getParametrosAlterarAcessoPlano()==null) || 
             (this.parametrosAlterarAcessoPlano!=null &&
              this.parametrosAlterarAcessoPlano.equals(other.getParametrosAlterarAcessoPlano())));
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
        if (getParametrosAlterarAcessoPlano() != null) {
            _hashCode += getParametrosAlterarAcessoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AlterarAcessoPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">alterarAcessoPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosAlterarAcessoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosAlterarAcessoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosAlterarAcessoPlano"));
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
