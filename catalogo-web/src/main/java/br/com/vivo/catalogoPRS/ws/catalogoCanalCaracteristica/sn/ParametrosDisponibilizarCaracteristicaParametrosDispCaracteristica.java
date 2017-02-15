/**
 * ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalCaracteristica.sn;

public class ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica  implements java.io.Serializable {
    private long idCaracteristica;

    private java.lang.String indisponivel;

    private java.lang.String insimulador;

    private java.util.Calendar dtUltimaAlteracao;

    private java.lang.String nmUsuarioAlteracao;

    public ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica() {
    }

    public ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica(
           long idCaracteristica,
           java.lang.String indisponivel,
           java.lang.String insimulador,
           java.util.Calendar dtUltimaAlteracao,
           java.lang.String nmUsuarioAlteracao) {
           this.idCaracteristica = idCaracteristica;
           this.indisponivel = indisponivel;
           this.insimulador = insimulador;
           this.dtUltimaAlteracao = dtUltimaAlteracao;
           this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }


    /**
     * Gets the idCaracteristica value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @return idCaracteristica
     */
    public long getIdCaracteristica() {
        return idCaracteristica;
    }


    /**
     * Sets the idCaracteristica value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @param idCaracteristica
     */
    public void setIdCaracteristica(long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }


    /**
     * Gets the indisponivel value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the insimulador value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @return insimulador
     */
    public java.lang.String getInsimulador() {
        return insimulador;
    }


    /**
     * Sets the insimulador value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @param insimulador
     */
    public void setInsimulador(java.lang.String insimulador) {
        this.insimulador = insimulador;
    }


    /**
     * Gets the dtUltimaAlteracao value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @return dtUltimaAlteracao
     */
    public java.util.Calendar getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }


    /**
     * Sets the dtUltimaAlteracao value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @param dtUltimaAlteracao
     */
    public void setDtUltimaAlteracao(java.util.Calendar dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }


    /**
     * Gets the nmUsuarioAlteracao value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @return nmUsuarioAlteracao
     */
    public java.lang.String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }


    /**
     * Sets the nmUsuarioAlteracao value for this ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.
     * 
     * @param nmUsuarioAlteracao
     */
    public void setNmUsuarioAlteracao(java.lang.String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica)) return false;
        ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica other = (ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCaracteristica == other.getIdCaracteristica() &&
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.insimulador==null && other.getInsimulador()==null) || 
             (this.insimulador!=null &&
              this.insimulador.equals(other.getInsimulador()))) &&
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
        _hashCode += new Long(getIdCaracteristica()).hashCode();
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getInsimulador() != null) {
            _hashCode += getInsimulador().hashCode();
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
        new org.apache.axis.description.TypeDesc(ParametrosDisponibilizarCaracteristicaParametrosDispCaracteristica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", ">>ParametrosDisponibilizarCaracteristica>ParametrosDispCaracteristica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "idCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("insimulador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "insimulador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "dtUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuarioAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoCaracteristica", "nmUsuarioAlteracao"));
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
