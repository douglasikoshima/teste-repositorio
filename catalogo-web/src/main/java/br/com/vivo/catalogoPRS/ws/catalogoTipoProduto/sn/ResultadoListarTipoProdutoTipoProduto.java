/**
 * ResultadoListarTipoProdutoTipoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTipoProduto.sn;

public class ResultadoListarTipoProdutoTipoProduto  implements java.io.Serializable {
    private long idTipoProduto;

    private java.lang.String nmTipoProduto;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.lang.String nmUsuarioAlteracao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String[] listaSgTipoProduto;

    public ResultadoListarTipoProdutoTipoProduto() {
    }

    public ResultadoListarTipoProdutoTipoProduto(
           long idTipoProduto,
           java.lang.String nmTipoProduto,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.lang.String nmUsuarioAlteracao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String[] listaSgTipoProduto) {
           this.idTipoProduto = idTipoProduto;
           this.nmTipoProduto = nmTipoProduto;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.listaSgTipoProduto = listaSgTipoProduto;
    }


    /**
     * Gets the idTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the nmTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return nmTipoProduto
     */
    public java.lang.String getNmTipoProduto() {
        return nmTipoProduto;
    }


    /**
     * Sets the nmTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param nmTipoProduto
     */
    public void setNmTipoProduto(java.lang.String nmTipoProduto) {
        this.nmTipoProduto = nmTipoProduto;
    }


    /**
     * Gets the dtCriacao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the listaSgTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @return listaSgTipoProduto
     */
    public java.lang.String[] getListaSgTipoProduto() {
        return listaSgTipoProduto;
    }


    /**
     * Sets the listaSgTipoProduto value for this ResultadoListarTipoProdutoTipoProduto.
     * 
     * @param listaSgTipoProduto
     */
    public void setListaSgTipoProduto(java.lang.String[] listaSgTipoProduto) {
        this.listaSgTipoProduto = listaSgTipoProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoListarTipoProdutoTipoProduto)) return false;
        ResultadoListarTipoProdutoTipoProduto other = (ResultadoListarTipoProdutoTipoProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.nmTipoProduto==null && other.getNmTipoProduto()==null) || 
             (this.nmTipoProduto!=null &&
              this.nmTipoProduto.equals(other.getNmTipoProduto()))) &&
            ((this.dtCriacao==null && other.getDtCriacao()==null) || 
             (this.dtCriacao!=null &&
              this.dtCriacao.equals(other.getDtCriacao()))) &&
            ((this.nmUsuarioCriacao==null && other.getNmUsuarioCriacao()==null) || 
             (this.nmUsuarioCriacao!=null &&
              this.nmUsuarioCriacao.equals(other.getNmUsuarioCriacao()))) &&
            ((this.nmUsuarioAlteracao==null && other.getNmUsuarioAlteracao()==null) || 
             (this.nmUsuarioAlteracao!=null &&
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.dtUltimaAlteracao==null && other.getDtUltimaAlteracao()==null) || 
             (this.dtUltimaAlteracao!=null &&
              this.dtUltimaAlteracao.equals(other.getDtUltimaAlteracao()))) &&
            ((this.listaSgTipoProduto==null && other.getListaSgTipoProduto()==null) || 
             (this.listaSgTipoProduto!=null &&
              java.util.Arrays.equals(this.listaSgTipoProduto, other.getListaSgTipoProduto())));
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
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        if (getNmTipoProduto() != null) {
            _hashCode += getNmTipoProduto().hashCode();
        }
        if (getDtCriacao() != null) {
            _hashCode += getDtCriacao().hashCode();
        }
        if (getNmUsuarioCriacao() != null) {
            _hashCode += getNmUsuarioCriacao().hashCode();
        }
        if (getNmUsuarioAlteracao() != null) {
            _hashCode += getNmUsuarioAlteracao().hashCode();
        }
        if (getDtUltimaAlteracao() != null) {
            _hashCode += getDtUltimaAlteracao().hashCode();
        }
        if (getListaSgTipoProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSgTipoProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSgTipoProduto(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoListarTipoProdutoTipoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", ">>ResultadoListarTipoProduto>TipoProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "nmTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTipoProduto", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSgTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ListaSgTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "sgTipoProduto"));
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
