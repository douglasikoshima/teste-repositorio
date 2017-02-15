/**
 * VerificarExistenciaComposicaoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class VerificarExistenciaComposicaoRequest  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarExistenciaComposicao parametrosVerificarExistenciaComposicao;

    public VerificarExistenciaComposicaoRequest() {
    }

    public VerificarExistenciaComposicaoRequest(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarExistenciaComposicao parametrosVerificarExistenciaComposicao) {
           this.parametrosVerificarExistenciaComposicao = parametrosVerificarExistenciaComposicao;
    }


    /**
     * Gets the parametrosVerificarExistenciaComposicao value for this VerificarExistenciaComposicaoRequest.
     * 
     * @return parametrosVerificarExistenciaComposicao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarExistenciaComposicao getParametrosVerificarExistenciaComposicao() {
        return parametrosVerificarExistenciaComposicao;
    }


    /**
     * Sets the parametrosVerificarExistenciaComposicao value for this VerificarExistenciaComposicaoRequest.
     * 
     * @param parametrosVerificarExistenciaComposicao
     */
    public void setParametrosVerificarExistenciaComposicao(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ParametrosVerificarExistenciaComposicao parametrosVerificarExistenciaComposicao) {
        this.parametrosVerificarExistenciaComposicao = parametrosVerificarExistenciaComposicao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarExistenciaComposicaoRequest)) return false;
        VerificarExistenciaComposicaoRequest other = (VerificarExistenciaComposicaoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.parametrosVerificarExistenciaComposicao==null && other.getParametrosVerificarExistenciaComposicao()==null) || 
             (this.parametrosVerificarExistenciaComposicao!=null &&
              this.parametrosVerificarExistenciaComposicao.equals(other.getParametrosVerificarExistenciaComposicao())));
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
        if (getParametrosVerificarExistenciaComposicao() != null) {
            _hashCode += getParametrosVerificarExistenciaComposicao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarExistenciaComposicaoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">verificarExistenciaComposicaoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosVerificarExistenciaComposicao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ParametrosVerificarExistenciaComposicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosVerificarExistenciaComposicao"));
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
