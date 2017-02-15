/**
 * BuscarListaAcessoPlanoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class BuscarListaAcessoPlanoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano;

    public BuscarListaAcessoPlanoRequest() {
    }

    public BuscarListaAcessoPlanoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano) {
           this.parametrosBuscarListaAcessoPlano = parametrosBuscarListaAcessoPlano;
    }


    /**
     * Gets the parametrosBuscarListaAcessoPlano value for this BuscarListaAcessoPlanoRequest.
     * 
     * @return parametrosBuscarListaAcessoPlano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano getParametrosBuscarListaAcessoPlano() {
        return parametrosBuscarListaAcessoPlano;
    }


    /**
     * Sets the parametrosBuscarListaAcessoPlano value for this BuscarListaAcessoPlanoRequest.
     * 
     * @param parametrosBuscarListaAcessoPlano
     */
    public void setParametrosBuscarListaAcessoPlano(br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ParametrosBuscarListaAcessoPlano parametrosBuscarListaAcessoPlano) {
        this.parametrosBuscarListaAcessoPlano = parametrosBuscarListaAcessoPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAcessoPlanoRequest)) return false;
        BuscarListaAcessoPlanoRequest other = (BuscarListaAcessoPlanoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosBuscarListaAcessoPlano==null && other.getParametrosBuscarListaAcessoPlano()==null) || 
             (this.parametrosBuscarListaAcessoPlano!=null &&
              this.parametrosBuscarListaAcessoPlano.equals(other.getParametrosBuscarListaAcessoPlano())));
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
        if (getParametrosBuscarListaAcessoPlano() != null) {
            _hashCode += getParametrosBuscarListaAcessoPlano().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAcessoPlanoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">buscarListaAcessoPlanoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosBuscarListaAcessoPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ParametrosBuscarListaAcessoPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoPlano"));
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
