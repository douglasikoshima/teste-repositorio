/**
 * ParametrosAssociarVariaveis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosAssociarVariaveis  implements java.io.Serializable {
    private long idMatrizOferta;

    private java.lang.Long idTipoCliente;

    private java.lang.String sgCarteira;

    private java.lang.String segmento;

    public ParametrosAssociarVariaveis() {
    }

    public ParametrosAssociarVariaveis(
           long idMatrizOferta,
           java.lang.Long idTipoCliente,
           java.lang.String sgCarteira,
           java.lang.String segmento) {
           this.idMatrizOferta = idMatrizOferta;
           this.idTipoCliente = idTipoCliente;
           this.sgCarteira = sgCarteira;
           this.segmento = segmento;
    }


    /**
     * Gets the idMatrizOferta value for this ParametrosAssociarVariaveis.
     * 
     * @return idMatrizOferta
     */
    public long getIdMatrizOferta() {
        return idMatrizOferta;
    }


    /**
     * Sets the idMatrizOferta value for this ParametrosAssociarVariaveis.
     * 
     * @param idMatrizOferta
     */
    public void setIdMatrizOferta(long idMatrizOferta) {
        this.idMatrizOferta = idMatrizOferta;
    }


    /**
     * Gets the idTipoCliente value for this ParametrosAssociarVariaveis.
     * 
     * @return idTipoCliente
     */
    public java.lang.Long getIdTipoCliente() {
        return idTipoCliente;
    }


    /**
     * Sets the idTipoCliente value for this ParametrosAssociarVariaveis.
     * 
     * @param idTipoCliente
     */
    public void setIdTipoCliente(java.lang.Long idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }


    /**
     * Gets the sgCarteira value for this ParametrosAssociarVariaveis.
     * 
     * @return sgCarteira
     */
    public java.lang.String getSgCarteira() {
        return sgCarteira;
    }


    /**
     * Sets the sgCarteira value for this ParametrosAssociarVariaveis.
     * 
     * @param sgCarteira
     */
    public void setSgCarteira(java.lang.String sgCarteira) {
        this.sgCarteira = sgCarteira;
    }


    /**
     * Gets the segmento value for this ParametrosAssociarVariaveis.
     * 
     * @return segmento
     */
    public java.lang.String getSegmento() {
        return segmento;
    }


    /**
     * Sets the segmento value for this ParametrosAssociarVariaveis.
     * 
     * @param segmento
     */
    public void setSegmento(java.lang.String segmento) {
        this.segmento = segmento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarVariaveis)) return false;
        ParametrosAssociarVariaveis other = (ParametrosAssociarVariaveis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOferta == other.getIdMatrizOferta() &&
            ((this.idTipoCliente==null && other.getIdTipoCliente()==null) || 
             (this.idTipoCliente!=null &&
              this.idTipoCliente.equals(other.getIdTipoCliente()))) &&
            ((this.sgCarteira==null && other.getSgCarteira()==null) || 
             (this.sgCarteira!=null &&
              this.sgCarteira.equals(other.getSgCarteira()))) &&
            ((this.segmento==null && other.getSegmento()==null) || 
             (this.segmento!=null &&
              this.segmento.equals(other.getSegmento())));
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
        _hashCode += new Long(getIdMatrizOferta()).hashCode();
        if (getIdTipoCliente() != null) {
            _hashCode += getIdTipoCliente().hashCode();
        }
        if (getSgCarteira() != null) {
            _hashCode += getSgCarteira().hashCode();
        }
        if (getSegmento() != null) {
            _hashCode += getSegmento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarVariaveis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAssociarVariaveis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "sgCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("segmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "segmento"));
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
