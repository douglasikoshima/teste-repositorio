/**
 * ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto  implements java.io.Serializable {
    private long idGrupoProduto;

    private java.lang.String inDisponivel;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto() {
    }

    public ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto(
           long idGrupoProduto,
           java.lang.String inDisponivel,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idGrupoProduto = idGrupoProduto;
           this.inDisponivel = inDisponivel;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idGrupoProduto value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the inDisponivel value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto)) return false;
        ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto other = (ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
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
        new org.apache.axis.description.TypeDesc(ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosDesassociarTecnologiaTpFrequenciaVl>ParametrosDispGrupoProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "nmUsuarioAlteracao"));
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
