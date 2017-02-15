/**
 * ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica  implements java.io.Serializable {
    private long idGrupoProduto;

    private java.util.Calendar dtCriacao;

    private java.lang.String nmUsuarioCriacao;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica[] valorCaracteristica;

    public ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica() {
    }

    public ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica(
           long idGrupoProduto,
           java.util.Calendar dtCriacao,
           java.lang.String nmUsuarioCriacao,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica[] valorCaracteristica) {
           this.idGrupoProduto = idGrupoProduto;
           this.dtCriacao = dtCriacao;
           this.nmUsuarioCriacao = nmUsuarioCriacao;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
           this.valorCaracteristica = valorCaracteristica;
    }


    /**
     * Gets the idGrupoProduto value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the dtCriacao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return dtCriacao
     */
    public java.util.Calendar getDtCriacao() {
        return dtCriacao;
    }


    /**
     * Sets the dtCriacao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param dtCriacao
     */
    public void setDtCriacao(java.util.Calendar dtCriacao) {
        this.dtCriacao = dtCriacao;
    }


    /**
     * Gets the nmUsuarioCriacao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return nmUsuarioCriacao
     */
    public java.lang.String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }


    /**
     * Sets the nmUsuarioCriacao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param nmUsuarioCriacao
     */
    public void setNmUsuarioCriacao(java.lang.String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the valorCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @return valorCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica[] getValorCaracteristica() {
        return valorCaracteristica;
    }


    /**
     * Sets the valorCaracteristica value for this ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.
     * 
     * @param valorCaracteristica
     */
    public void setValorCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica[] valorCaracteristica) {
        this.valorCaracteristica = valorCaracteristica;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica getValorCaracteristica(int i) {
        return this.valorCaracteristica[i];
    }

    public void setValorCaracteristica(int i, br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristicaValorCaracteristica _value) {
        this.valorCaracteristica[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica)) return false;
        ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica other = (ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
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
              this.nmUsuarioAlteracao.equals(other.getNmUsuarioAlteracao()))) &&
            ((this.valorCaracteristica==null && other.getValorCaracteristica()==null) || 
             (this.valorCaracteristica!=null &&
              java.util.Arrays.equals(this.valorCaracteristica, other.getValorCaracteristica())));
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
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
        if (getValorCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValorCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValorCaracteristica(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAssociarModeloCaracteristicaParametrosAssociarModeloCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosAssociarModeloCaracteristica>ParametrosAssociarModeloCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioCriacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioCriacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmUsuarioAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ParametrosAssociarModeloCaracteristica>ParametrosAssociarModeloCaracteristica>ValorCaracteristica"));
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
