/**
 * BuscarListaPlanosSemOfertaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class BuscarListaPlanosSemOfertaResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOferta resultadoBuscarListaPlanosSemOferta;

    public BuscarListaPlanosSemOfertaResponse() {
    }

    public BuscarListaPlanosSemOfertaResponse(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOferta resultadoBuscarListaPlanosSemOferta) {
           this.resultadoBuscarListaPlanosSemOferta = resultadoBuscarListaPlanosSemOferta;
    }


    /**
     * Gets the resultadoBuscarListaPlanosSemOferta value for this BuscarListaPlanosSemOfertaResponse.
     * 
     * @return resultadoBuscarListaPlanosSemOferta
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOferta getResultadoBuscarListaPlanosSemOferta() {
        return resultadoBuscarListaPlanosSemOferta;
    }


    /**
     * Sets the resultadoBuscarListaPlanosSemOferta value for this BuscarListaPlanosSemOfertaResponse.
     * 
     * @param resultadoBuscarListaPlanosSemOferta
     */
    public void setResultadoBuscarListaPlanosSemOferta(br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOferta resultadoBuscarListaPlanosSemOferta) {
        this.resultadoBuscarListaPlanosSemOferta = resultadoBuscarListaPlanosSemOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaPlanosSemOfertaResponse)) return false;
        BuscarListaPlanosSemOfertaResponse other = (BuscarListaPlanosSemOfertaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaPlanosSemOferta==null && other.getResultadoBuscarListaPlanosSemOferta()==null) || 
             (this.resultadoBuscarListaPlanosSemOferta!=null &&
              this.resultadoBuscarListaPlanosSemOferta.equals(other.getResultadoBuscarListaPlanosSemOferta())));
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
        if (getResultadoBuscarListaPlanosSemOferta() != null) {
            _hashCode += getResultadoBuscarListaPlanosSemOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaPlanosSemOfertaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">buscarListaPlanosSemOfertaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaPlanosSemOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ResultadoBuscarListaPlanosSemOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ResultadoBuscarListaPlanosSemOferta"));
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
