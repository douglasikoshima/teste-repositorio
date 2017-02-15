/**
 * BuscarListaAcaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcao.sn;

public class BuscarListaAcaoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcao resultadoAcao;

    public BuscarListaAcaoResponse() {
    }

    public BuscarListaAcaoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcao resultadoAcao) {
           this.resultadoAcao = resultadoAcao;
    }


    /**
     * Gets the resultadoAcao value for this BuscarListaAcaoResponse.
     * 
     * @return resultadoAcao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcao getResultadoAcao() {
        return resultadoAcao;
    }


    /**
     * Sets the resultadoAcao value for this BuscarListaAcaoResponse.
     * 
     * @param resultadoAcao
     */
    public void setResultadoAcao(br.com.vivo.catalogoPRS.ws.catalogoAcao.sn.ResultadoAcao resultadoAcao) {
        this.resultadoAcao = resultadoAcao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAcaoResponse)) return false;
        BuscarListaAcaoResponse other = (BuscarListaAcaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoAcao==null && other.getResultadoAcao()==null) || 
             (this.resultadoAcao!=null &&
              this.resultadoAcao.equals(other.getResultadoAcao())));
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
        if (getResultadoAcao() != null) {
            _hashCode += getResultadoAcao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaAcaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">buscarListaAcaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", "ResultadoAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcao", ">ResultadoAcao"));
        elemField.setMinOccurs(0);
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
