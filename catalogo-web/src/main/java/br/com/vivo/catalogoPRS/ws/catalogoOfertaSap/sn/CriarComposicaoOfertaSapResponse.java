/**
 * CriarComposicaoOfertaSapResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class CriarComposicaoOfertaSapResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoCriarComposicaoOferta resultadoCriarComposicaoOferta;

    public CriarComposicaoOfertaSapResponse() {
    }

    public CriarComposicaoOfertaSapResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoCriarComposicaoOferta resultadoCriarComposicaoOferta) {
           this.resultadoCriarComposicaoOferta = resultadoCriarComposicaoOferta;
    }


    /**
     * Gets the resultadoCriarComposicaoOferta value for this CriarComposicaoOfertaSapResponse.
     * 
     * @return resultadoCriarComposicaoOferta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoCriarComposicaoOferta getResultadoCriarComposicaoOferta() {
        return resultadoCriarComposicaoOferta;
    }


    /**
     * Sets the resultadoCriarComposicaoOferta value for this CriarComposicaoOfertaSapResponse.
     * 
     * @param resultadoCriarComposicaoOferta
     */
    public void setResultadoCriarComposicaoOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoCriarComposicaoOferta resultadoCriarComposicaoOferta) {
        this.resultadoCriarComposicaoOferta = resultadoCriarComposicaoOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CriarComposicaoOfertaSapResponse)) return false;
        CriarComposicaoOfertaSapResponse other = (CriarComposicaoOfertaSapResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoCriarComposicaoOferta==null && other.getResultadoCriarComposicaoOferta()==null) || 
             (this.resultadoCriarComposicaoOferta!=null &&
              this.resultadoCriarComposicaoOferta.equals(other.getResultadoCriarComposicaoOferta())));
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
        if (getResultadoCriarComposicaoOferta() != null) {
            _hashCode += getResultadoCriarComposicaoOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CriarComposicaoOfertaSapResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">criarComposicaoOfertaSapResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoCriarComposicaoOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ResultadoCriarComposicaoOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ResultadoCriarComposicaoOferta"));
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
