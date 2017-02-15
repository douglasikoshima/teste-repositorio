/**
 * BuscarIdMatrizOfertaArquivoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarIdMatrizOfertaArquivoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultBuscarIdMatrizOfertaArquivo resultBuscarIdMatrizOfertaArquivo;

    public BuscarIdMatrizOfertaArquivoResponse() {
    }

    public BuscarIdMatrizOfertaArquivoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultBuscarIdMatrizOfertaArquivo resultBuscarIdMatrizOfertaArquivo) {
           this.resultBuscarIdMatrizOfertaArquivo = resultBuscarIdMatrizOfertaArquivo;
    }


    /**
     * Gets the resultBuscarIdMatrizOfertaArquivo value for this BuscarIdMatrizOfertaArquivoResponse.
     * 
     * @return resultBuscarIdMatrizOfertaArquivo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultBuscarIdMatrizOfertaArquivo getResultBuscarIdMatrizOfertaArquivo() {
        return resultBuscarIdMatrizOfertaArquivo;
    }


    /**
     * Sets the resultBuscarIdMatrizOfertaArquivo value for this BuscarIdMatrizOfertaArquivoResponse.
     * 
     * @param resultBuscarIdMatrizOfertaArquivo
     */
    public void setResultBuscarIdMatrizOfertaArquivo(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultBuscarIdMatrizOfertaArquivo resultBuscarIdMatrizOfertaArquivo) {
        this.resultBuscarIdMatrizOfertaArquivo = resultBuscarIdMatrizOfertaArquivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarIdMatrizOfertaArquivoResponse)) return false;
        BuscarIdMatrizOfertaArquivoResponse other = (BuscarIdMatrizOfertaArquivoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultBuscarIdMatrizOfertaArquivo==null && other.getResultBuscarIdMatrizOfertaArquivo()==null) || 
             (this.resultBuscarIdMatrizOfertaArquivo!=null &&
              this.resultBuscarIdMatrizOfertaArquivo.equals(other.getResultBuscarIdMatrizOfertaArquivo())));
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
        if (getResultBuscarIdMatrizOfertaArquivo() != null) {
            _hashCode += getResultBuscarIdMatrizOfertaArquivo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarIdMatrizOfertaArquivoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarIdMatrizOfertaArquivoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultBuscarIdMatrizOfertaArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultBuscarIdMatrizOfertaArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultBuscarIdMatrizOfertaArquivo"));
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
