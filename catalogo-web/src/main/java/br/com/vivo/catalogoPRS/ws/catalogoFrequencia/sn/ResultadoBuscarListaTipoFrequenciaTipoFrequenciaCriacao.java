/**
 * ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn;

public class ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao  implements java.io.Serializable {
    private java.lang.Long idTipoFrequencia;

    private java.lang.String nmTipoFrequencia;

    private java.math.BigInteger qtFrequencia;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    public ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao() {
    }

    public ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao(
           java.lang.Long idTipoFrequencia,
           java.lang.String nmTipoFrequencia,
           java.math.BigInteger qtFrequencia,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao) {
           this.idTipoFrequencia = idTipoFrequencia;
           this.nmTipoFrequencia = nmTipoFrequencia;
           this.qtFrequencia = qtFrequencia;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the idTipoFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @return idTipoFrequencia
     */
    public java.lang.Long getIdTipoFrequencia() {
        return idTipoFrequencia;
    }


    /**
     * Sets the idTipoFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @param idTipoFrequencia
     */
    public void setIdTipoFrequencia(java.lang.Long idTipoFrequencia) {
        this.idTipoFrequencia = idTipoFrequencia;
    }


    /**
     * Gets the nmTipoFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @return nmTipoFrequencia
     */
    public java.lang.String getNmTipoFrequencia() {
        return nmTipoFrequencia;
    }


    /**
     * Sets the nmTipoFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @param nmTipoFrequencia
     */
    public void setNmTipoFrequencia(java.lang.String nmTipoFrequencia) {
        this.nmTipoFrequencia = nmTipoFrequencia;
    }


    /**
     * Gets the qtFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @return qtFrequencia
     */
    public java.math.BigInteger getQtFrequencia() {
        return qtFrequencia;
    }


    /**
     * Sets the qtFrequencia value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @param qtFrequencia
     */
    public void setQtFrequencia(java.math.BigInteger qtFrequencia) {
        this.qtFrequencia = qtFrequencia;
    }


    /**
     * Gets the dtCriacao value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao)) return false;
        ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao other = (ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idTipoFrequencia==null && other.getIdTipoFrequencia()==null) || 
             (this.idTipoFrequencia!=null &&
              this.idTipoFrequencia.equals(other.getIdTipoFrequencia()))) &&
            ((this.nmTipoFrequencia==null && other.getNmTipoFrequencia()==null) || 
             (this.nmTipoFrequencia!=null &&
              this.nmTipoFrequencia.equals(other.getNmTipoFrequencia()))) &&
            ((this.qtFrequencia==null && other.getQtFrequencia()==null) || 
             (this.qtFrequencia!=null &&
              this.qtFrequencia.equals(other.getQtFrequencia()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao())));
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
        if (getIdTipoFrequencia() != null) {
            _hashCode += getIdTipoFrequencia().hashCode();
        }
        if (getNmTipoFrequencia() != null) {
            _hashCode += getNmTipoFrequencia().hashCode();
        }
        if (getQtFrequencia() != null) {
            _hashCode += getQtFrequencia().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", ">>ResultadoBuscarListaTipoFrequencia>TipoFrequenciaCriacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "idTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "nmTipoFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtFrequencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "qtFrequencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFrequencia", "nmUsuarioCriacao"));
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
