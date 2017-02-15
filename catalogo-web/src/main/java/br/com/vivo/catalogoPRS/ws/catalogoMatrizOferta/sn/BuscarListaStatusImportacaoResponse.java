/**
 * BuscarListaStatusImportacaoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class BuscarListaStatusImportacaoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao[] resultadoBuscarListaStatusImportacao;

    public BuscarListaStatusImportacaoResponse() {
    }

    public BuscarListaStatusImportacaoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao[] resultadoBuscarListaStatusImportacao) {
           this.resultadoBuscarListaStatusImportacao = resultadoBuscarListaStatusImportacao;
    }


    /**
     * Gets the resultadoBuscarListaStatusImportacao value for this BuscarListaStatusImportacaoResponse.
     * 
     * @return resultadoBuscarListaStatusImportacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao[] getResultadoBuscarListaStatusImportacao() {
        return resultadoBuscarListaStatusImportacao;
    }


    /**
     * Sets the resultadoBuscarListaStatusImportacao value for this BuscarListaStatusImportacaoResponse.
     * 
     * @param resultadoBuscarListaStatusImportacao
     */
    public void setResultadoBuscarListaStatusImportacao(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoBuscarListaStatusImportacaoListaStatusImportacao[] resultadoBuscarListaStatusImportacao) {
        this.resultadoBuscarListaStatusImportacao = resultadoBuscarListaStatusImportacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaStatusImportacaoResponse)) return false;
        BuscarListaStatusImportacaoResponse other = (BuscarListaStatusImportacaoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaStatusImportacao==null && other.getResultadoBuscarListaStatusImportacao()==null) || 
             (this.resultadoBuscarListaStatusImportacao!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaStatusImportacao, other.getResultadoBuscarListaStatusImportacao())));
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
        if (getResultadoBuscarListaStatusImportacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaStatusImportacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaStatusImportacao(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BuscarListaStatusImportacaoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">buscarListaStatusImportacaoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaStatusImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ResultadoBuscarListaStatusImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoBuscarListaStatusImportacao"));
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
