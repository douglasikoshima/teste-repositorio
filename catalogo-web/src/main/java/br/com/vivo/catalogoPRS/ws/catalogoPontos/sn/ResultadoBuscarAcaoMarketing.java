/**
 * ResultadoBuscarAcaoMarketing.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPontos.sn;

public class ResultadoBuscarAcaoMarketing  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo[] acoesIncentivo;

    public ResultadoBuscarAcaoMarketing() {
    }

    public ResultadoBuscarAcaoMarketing(
           br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo[] acoesIncentivo) {
           this.acoesIncentivo = acoesIncentivo;
    }


    /**
     * Gets the acoesIncentivo value for this ResultadoBuscarAcaoMarketing.
     * 
     * @return acoesIncentivo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo[] getAcoesIncentivo() {
        return acoesIncentivo;
    }


    /**
     * Sets the acoesIncentivo value for this ResultadoBuscarAcaoMarketing.
     * 
     * @param acoesIncentivo
     */
    public void setAcoesIncentivo(br.com.vivo.catalogoPRS.ws.catalogoPontos.sn.ResultadoBuscarAcaoMarketingAcoesIncentivoAcaoIncentivo[] acoesIncentivo) {
        this.acoesIncentivo = acoesIncentivo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarAcaoMarketing)) return false;
        ResultadoBuscarAcaoMarketing other = (ResultadoBuscarAcaoMarketing) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.acoesIncentivo==null && other.getAcoesIncentivo()==null) || 
             (this.acoesIncentivo!=null &&
              java.util.Arrays.equals(this.acoesIncentivo, other.getAcoesIncentivo())));
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
        if (getAcoesIncentivo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAcoesIncentivo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAcoesIncentivo(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarAcaoMarketing.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "ResultadoBuscarAcaoMarketing"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("acoesIncentivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "AcoesIncentivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", ">>ResultadoBuscarAcaoMarketing>AcoesIncentivo>acaoIncentivo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/Pontos", "acaoIncentivo"));
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
