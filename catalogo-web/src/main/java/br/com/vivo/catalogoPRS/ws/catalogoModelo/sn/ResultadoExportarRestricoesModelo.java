/**
 * ResultadoExportarRestricoesModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoExportarRestricoesModelo  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloPaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo[] restricaoModelo;

    public ResultadoExportarRestricoesModelo() {
    }

    public ResultadoExportarRestricoesModelo(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloPaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo[] restricaoModelo) {
           this.paginacaoOut = paginacaoOut;
           this.restricaoModelo = restricaoModelo;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoExportarRestricoesModelo.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloPaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoExportarRestricoesModelo.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloPaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the restricaoModelo value for this ResultadoExportarRestricoesModelo.
     * 
     * @return restricaoModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo[] getRestricaoModelo() {
        return restricaoModelo;
    }


    /**
     * Sets the restricaoModelo value for this ResultadoExportarRestricoesModelo.
     * 
     * @param restricaoModelo
     */
    public void setRestricaoModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo[] restricaoModelo) {
        this.restricaoModelo = restricaoModelo;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo getRestricaoModelo(int i) {
        return this.restricaoModelo[i];
    }

    public void setRestricaoModelo(int i, br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarRestricoesModeloRestricaoModelo _value) {
        this.restricaoModelo[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarRestricoesModelo)) return false;
        ResultadoExportarRestricoesModelo other = (ResultadoExportarRestricoesModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoOut==null && other.getPaginacaoOut()==null) || 
             (this.paginacaoOut!=null &&
              this.paginacaoOut.equals(other.getPaginacaoOut()))) &&
            ((this.restricaoModelo==null && other.getRestricaoModelo()==null) || 
             (this.restricaoModelo!=null &&
              java.util.Arrays.equals(this.restricaoModelo, other.getRestricaoModelo())));
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
        if (getPaginacaoOut() != null) {
            _hashCode += getPaginacaoOut().hashCode();
        }
        if (getRestricaoModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRestricaoModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRestricaoModelo(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoExportarRestricoesModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoExportarRestricoesModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoExportarRestricoesModelo>PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("restricaoModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "RestricaoModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoExportarRestricoesModelo>RestricaoModelo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
