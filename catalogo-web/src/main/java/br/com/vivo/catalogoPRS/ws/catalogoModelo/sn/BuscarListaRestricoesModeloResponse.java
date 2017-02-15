/**
 * BuscarListaRestricoesModeloResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class BuscarListaRestricoesModeloResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModelo resultadoBuscarListaRestricoesModelo;

    public BuscarListaRestricoesModeloResponse() {
    }

    public BuscarListaRestricoesModeloResponse(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModelo resultadoBuscarListaRestricoesModelo) {
           this.resultadoBuscarListaRestricoesModelo = resultadoBuscarListaRestricoesModelo;
    }


    /**
     * Gets the resultadoBuscarListaRestricoesModelo value for this BuscarListaRestricoesModeloResponse.
     * 
     * @return resultadoBuscarListaRestricoesModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModelo getResultadoBuscarListaRestricoesModelo() {
        return resultadoBuscarListaRestricoesModelo;
    }


    /**
     * Sets the resultadoBuscarListaRestricoesModelo value for this BuscarListaRestricoesModeloResponse.
     * 
     * @param resultadoBuscarListaRestricoesModelo
     */
    public void setResultadoBuscarListaRestricoesModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModelo resultadoBuscarListaRestricoesModelo) {
        this.resultadoBuscarListaRestricoesModelo = resultadoBuscarListaRestricoesModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaRestricoesModeloResponse)) return false;
        BuscarListaRestricoesModeloResponse other = (BuscarListaRestricoesModeloResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaRestricoesModelo==null && other.getResultadoBuscarListaRestricoesModelo()==null) || 
             (this.resultadoBuscarListaRestricoesModelo!=null &&
              this.resultadoBuscarListaRestricoesModelo.equals(other.getResultadoBuscarListaRestricoesModelo())));
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
        if (getResultadoBuscarListaRestricoesModelo() != null) {
            _hashCode += getResultadoBuscarListaRestricoesModelo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaRestricoesModeloResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">buscarListaRestricoesModeloResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaRestricoesModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ResultadoBuscarListaRestricoesModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoBuscarListaRestricoesModelo"));
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
