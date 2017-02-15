/**
 * ParametrosBuscarListaCabecalho.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosBuscarListaCabecalho  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.util.Calendar dtVigencia;

    private java.lang.String nmMatrizOferta;

    public ParametrosBuscarListaCabecalho() {
    }

    public ParametrosBuscarListaCabecalho(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.util.Calendar dtVigencia,
           java.lang.String nmMatrizOferta) {
           this.paginacaoIn = paginacaoIn;
           this.dtVigencia = dtVigencia;
           this.nmMatrizOferta = nmMatrizOferta;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaCabecalho.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaCabecalho.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the dtVigencia value for this ParametrosBuscarListaCabecalho.
     * 
     * @return dtVigencia
     */
    public java.util.Calendar getDtVigencia() {
        return dtVigencia;
    }


    /**
     * Sets the dtVigencia value for this ParametrosBuscarListaCabecalho.
     * 
     * @param dtVigencia
     */
    public void setDtVigencia(java.util.Calendar dtVigencia) {
        this.dtVigencia = dtVigencia;
    }


    /**
     * Gets the nmMatrizOferta value for this ParametrosBuscarListaCabecalho.
     * 
     * @return nmMatrizOferta
     */
    public java.lang.String getNmMatrizOferta() {
        return nmMatrizOferta;
    }


    /**
     * Sets the nmMatrizOferta value for this ParametrosBuscarListaCabecalho.
     * 
     * @param nmMatrizOferta
     */
    public void setNmMatrizOferta(java.lang.String nmMatrizOferta) {
        this.nmMatrizOferta = nmMatrizOferta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaCabecalho)) return false;
        ParametrosBuscarListaCabecalho other = (ParametrosBuscarListaCabecalho) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn()))) &&
            ((this.dtVigencia==null && other.getDtVigencia()==null) || 
             (this.dtVigencia!=null &&
              this.dtVigencia.equals(other.getDtVigencia()))) &&
            ((this.nmMatrizOferta==null && other.getNmMatrizOferta()==null) || 
             (this.nmMatrizOferta!=null &&
              this.nmMatrizOferta.equals(other.getNmMatrizOferta())));
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
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        if (getDtVigencia() != null) {
            _hashCode += getDtVigencia().hashCode();
        }
        if (getNmMatrizOferta() != null) {
            _hashCode += getNmMatrizOferta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaCabecalho.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaCabecalho"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
