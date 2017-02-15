/**
 * IncluirAcessoPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class IncluirAcessoPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlano parametrosIncluirAcessoPlano;

    public IncluirAcessoPlanoRequest() {
    }

    public IncluirAcessoPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlano parametrosIncluirAcessoPlano) {
           this.parametrosIncluirAcessoPlano = parametrosIncluirAcessoPlano;
    }


    /**
     * Gets the parametrosIncluirAcessoPlano value for this IncluirAcessoPlanoRequest.
     * 
     * @return parametrosIncluirAcessoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlano getParametrosIncluirAcessoPlano() {
        return parametrosIncluirAcessoPlano;
    }


    /**
     * Sets the parametrosIncluirAcessoPlano value for this IncluirAcessoPlanoRequest.
     * 
     * @param parametrosIncluirAcessoPlano
     */
    public void setParametrosIncluirAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosIncluirAcessoPlano parametrosIncluirAcessoPlano) {
        this.parametrosIncluirAcessoPlano = parametrosIncluirAcessoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IncluirAcessoPlanoRequest)) return false;
        IncluirAcessoPlanoRequest other = (IncluirAcessoPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosIncluirAcessoPlano==null && other.getParametrosIncluirAcessoPlano()==null) || 
             (this.parametrosIncluirAcessoPlano!=null &&
              this.parametrosIncluirAcessoPlano.equals(other.getParametrosIncluirAcessoPlano())));
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
        if (getParametrosIncluirAcessoPlano() != null) {
            _hashCode += getParametrosIncluirAcessoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IncluirAcessoPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">incluirAcessoPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosIncluirAcessoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosIncluirAcessoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosIncluirAcessoPlano"));
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
