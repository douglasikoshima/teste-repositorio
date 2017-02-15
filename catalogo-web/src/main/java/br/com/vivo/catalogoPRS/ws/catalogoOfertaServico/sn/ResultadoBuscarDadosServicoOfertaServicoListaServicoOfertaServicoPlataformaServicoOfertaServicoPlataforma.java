/**
 * ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma  implements java.io.Serializable {
    private long idPlataforma;

    private java.lang.String nmPlataforma;

    public ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma() {
    }

    public ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma(
           long idPlataforma,
           java.lang.String nmPlataforma) {
           this.idPlataforma = idPlataforma;
           this.nmPlataforma = nmPlataforma;
    }


    /**
     * Gets the idPlataforma value for this ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the nmPlataforma value for this ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.
     * 
     * @return nmPlataforma
     */
    public java.lang.String getNmPlataforma() {
        return nmPlataforma;
    }


    /**
     * Sets the nmPlataforma value for this ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.
     * 
     * @param nmPlataforma
     */
    public void setNmPlataforma(java.lang.String nmPlataforma) {
        this.nmPlataforma = nmPlataforma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma)) return false;
        ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma other = (ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.nmPlataforma==null && other.getNmPlataforma()==null) || 
             (this.nmPlataforma!=null &&
              this.nmPlataforma.equals(other.getNmPlataforma())));
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
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getNmPlataforma() != null) {
            _hashCode += getNmPlataforma().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDadosServicoOfertaServicoListaServicoOfertaServicoPlataformaServicoOfertaServicoPlataforma.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarDadosServicoOfertaServico>ListaServicoOfertaServicoPlataforma>ServicoOfertaServicoPlataforma"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
