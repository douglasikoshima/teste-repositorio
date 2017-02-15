/**
 * ResultadoBuscarListaCarteiraListaCarteiraCarteira.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCarteira.sn;

public class ResultadoBuscarListaCarteiraListaCarteiraCarteira  implements java.io.Serializable {
    private java.lang.String sgCarteira;

    private java.lang.String dsCarteira;

    private long idTipoCliente;

    public ResultadoBuscarListaCarteiraListaCarteiraCarteira() {
    }

    public ResultadoBuscarListaCarteiraListaCarteiraCarteira(
           java.lang.String sgCarteira,
           java.lang.String dsCarteira,
           long idTipoCliente) {
           this.sgCarteira = sgCarteira;
           this.dsCarteira = dsCarteira;
           this.idTipoCliente = idTipoCliente;
    }


    /**
     * Gets the sgCarteira value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @return sgCarteira
     */
    public java.lang.String getSgCarteira() {
        return sgCarteira;
    }


    /**
     * Sets the sgCarteira value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @param sgCarteira
     */
    public void setSgCarteira(java.lang.String sgCarteira) {
        this.sgCarteira = sgCarteira;
    }


    /**
     * Gets the dsCarteira value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @return dsCarteira
     */
    public java.lang.String getDsCarteira() {
        return dsCarteira;
    }


    /**
     * Sets the dsCarteira value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @param dsCarteira
     */
    public void setDsCarteira(java.lang.String dsCarteira) {
        this.dsCarteira = dsCarteira;
    }


    /**
     * Gets the idTipoCliente value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @return idTipoCliente
     */
    public long getIdTipoCliente() {
        return idTipoCliente;
    }


    /**
     * Sets the idTipoCliente value for this ResultadoBuscarListaCarteiraListaCarteiraCarteira.
     * 
     * @param idTipoCliente
     */
    public void setIdTipoCliente(long idTipoCliente) {
        this.idTipoCliente = idTipoCliente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaCarteiraListaCarteiraCarteira)) return false;
        ResultadoBuscarListaCarteiraListaCarteiraCarteira other = (ResultadoBuscarListaCarteiraListaCarteiraCarteira) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sgCarteira==null && other.getSgCarteira()==null) || 
             (this.sgCarteira!=null &&
              this.sgCarteira.equals(other.getSgCarteira()))) &&
            ((this.dsCarteira==null && other.getDsCarteira()==null) || 
             (this.dsCarteira!=null &&
              this.dsCarteira.equals(other.getDsCarteira()))) &&
            this.idTipoCliente == other.getIdTipoCliente();
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
        if (getSgCarteira() != null) {
            _hashCode += getSgCarteira().hashCode();
        }
        if (getDsCarteira() != null) {
            _hashCode += getDsCarteira().hashCode();
        }
        _hashCode += new Long(getIdTipoCliente()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaCarteiraListaCarteiraCarteira.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", ">>>ResultadoBuscarListaCarteira>ListaCarteira>Carteira"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "sgCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "dsCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCarteira", "idTipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
