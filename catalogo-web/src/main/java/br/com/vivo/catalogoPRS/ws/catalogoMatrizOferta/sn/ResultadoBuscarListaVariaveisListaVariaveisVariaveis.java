/**
 * ResultadoBuscarListaVariaveisListaVariaveisVariaveis.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoBuscarListaVariaveisListaVariaveisVariaveis  implements java.io.Serializable {
    private long idMatrizOfertaVariaveis;

    private java.lang.String nmTipoCliente;

    private java.lang.String nmCarteira;

    private java.lang.String nmSegmento;

    public ResultadoBuscarListaVariaveisListaVariaveisVariaveis() {
    }

    public ResultadoBuscarListaVariaveisListaVariaveisVariaveis(
           long idMatrizOfertaVariaveis,
           java.lang.String nmTipoCliente,
           java.lang.String nmCarteira,
           java.lang.String nmSegmento) {
           this.idMatrizOfertaVariaveis = idMatrizOfertaVariaveis;
           this.nmTipoCliente = nmTipoCliente;
           this.nmCarteira = nmCarteira;
           this.nmSegmento = nmSegmento;
    }


    /**
     * Gets the idMatrizOfertaVariaveis value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @return idMatrizOfertaVariaveis
     */
    public long getIdMatrizOfertaVariaveis() {
        return idMatrizOfertaVariaveis;
    }


    /**
     * Sets the idMatrizOfertaVariaveis value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @param idMatrizOfertaVariaveis
     */
    public void setIdMatrizOfertaVariaveis(long idMatrizOfertaVariaveis) {
        this.idMatrizOfertaVariaveis = idMatrizOfertaVariaveis;
    }


    /**
     * Gets the nmTipoCliente value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @return nmTipoCliente
     */
    public java.lang.String getNmTipoCliente() {
        return nmTipoCliente;
    }


    /**
     * Sets the nmTipoCliente value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @param nmTipoCliente
     */
    public void setNmTipoCliente(java.lang.String nmTipoCliente) {
        this.nmTipoCliente = nmTipoCliente;
    }


    /**
     * Gets the nmCarteira value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @return nmCarteira
     */
    public java.lang.String getNmCarteira() {
        return nmCarteira;
    }


    /**
     * Sets the nmCarteira value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @param nmCarteira
     */
    public void setNmCarteira(java.lang.String nmCarteira) {
        this.nmCarteira = nmCarteira;
    }


    /**
     * Gets the nmSegmento value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @return nmSegmento
     */
    public java.lang.String getNmSegmento() {
        return nmSegmento;
    }


    /**
     * Sets the nmSegmento value for this ResultadoBuscarListaVariaveisListaVariaveisVariaveis.
     * 
     * @param nmSegmento
     */
    public void setNmSegmento(java.lang.String nmSegmento) {
        this.nmSegmento = nmSegmento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaVariaveisListaVariaveisVariaveis)) return false;
        ResultadoBuscarListaVariaveisListaVariaveisVariaveis other = (ResultadoBuscarListaVariaveisListaVariaveisVariaveis) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOfertaVariaveis == other.getIdMatrizOfertaVariaveis() &&
            ((this.nmTipoCliente==null && other.getNmTipoCliente()==null) || 
             (this.nmTipoCliente!=null &&
              this.nmTipoCliente.equals(other.getNmTipoCliente()))) &&
            ((this.nmCarteira==null && other.getNmCarteira()==null) || 
             (this.nmCarteira!=null &&
              this.nmCarteira.equals(other.getNmCarteira()))) &&
            ((this.nmSegmento==null && other.getNmSegmento()==null) || 
             (this.nmSegmento!=null &&
              this.nmSegmento.equals(other.getNmSegmento())));
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
        _hashCode += new Long(getIdMatrizOfertaVariaveis()).hashCode();
        if (getNmTipoCliente() != null) {
            _hashCode += getNmTipoCliente().hashCode();
        }
        if (getNmCarteira() != null) {
            _hashCode += getNmCarteira().hashCode();
        }
        if (getNmSegmento() != null) {
            _hashCode += getNmSegmento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaVariaveisListaVariaveisVariaveis.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoBuscarListaVariaveis>ListaVariaveis>Variaveis"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaVariaveis");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaVariaveis"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoCliente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmTipoCliente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCarteira");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmCarteira"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmSegmento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmSegmento"));
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
