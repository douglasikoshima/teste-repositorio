/**
 * ResultadoBuscarListaFabricantePorTipoProdutoFabricante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFabricante.sn;

public class ResultadoBuscarListaFabricantePorTipoProdutoFabricante  implements java.io.Serializable {
    private long idFabricante;

    private java.lang.String nmFabricante;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ResultadoBuscarListaFabricantePorTipoProdutoFabricante() {
    }

    public ResultadoBuscarListaFabricantePorTipoProdutoFabricante(
           long idFabricante,
           java.lang.String nmFabricante,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idFabricante = idFabricante;
           this.nmFabricante = nmFabricante;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idFabricante value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return idFabricante
     */
    public long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the nmFabricante value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return nmFabricante
     */
    public java.lang.String getNmFabricante() {
        return nmFabricante;
    }


    /**
     * Sets the nmFabricante value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param nmFabricante
     */
    public void setNmFabricante(java.lang.String nmFabricante) {
        this.nmFabricante = nmFabricante;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoBuscarListaFabricantePorTipoProdutoFabricante.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaFabricantePorTipoProdutoFabricante)) return false;
        ResultadoBuscarListaFabricantePorTipoProdutoFabricante other = (ResultadoBuscarListaFabricantePorTipoProdutoFabricante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idFabricante == other.getIdFabricante() &&
            ((this.nmFabricante==null && other.getNmFabricante()==null) || 
             (this.nmFabricante!=null &&
              this.nmFabricante.equals(other.getNmFabricante()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao())));
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
        _hashCode += new Long(getIdFabricante()).hashCode();
        if (getNmFabricante() != null) {
            _hashCode += getNmFabricante().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaFabricantePorTipoProdutoFabricante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", ">>ResultadoBuscarListaFabricantePorTipoProduto>Fabricante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "nmFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFabricante", "nmUsuarioAlteracao"));
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
