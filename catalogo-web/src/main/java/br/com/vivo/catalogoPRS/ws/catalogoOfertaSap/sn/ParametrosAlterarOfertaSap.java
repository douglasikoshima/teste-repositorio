/**
 * ParametrosAlterarOfertaSap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class ParametrosAlterarOfertaSap  implements java.io.Serializable {
    private long idOfertaSap;

    private java.lang.String cdOfertaSap;

    private java.lang.String dscOfertaSap;

    private java.lang.String sgUtilizacao;

    private java.lang.String inDisponivel;

    public ParametrosAlterarOfertaSap() {
    }

    public ParametrosAlterarOfertaSap(
           long idOfertaSap,
           java.lang.String cdOfertaSap,
           java.lang.String dscOfertaSap,
           java.lang.String sgUtilizacao,
           java.lang.String inDisponivel) {
           this.idOfertaSap = idOfertaSap;
           this.cdOfertaSap = cdOfertaSap;
           this.dscOfertaSap = dscOfertaSap;
           this.sgUtilizacao = sgUtilizacao;
           this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the idOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @return idOfertaSap
     */
    public long getIdOfertaSap() {
        return idOfertaSap;
    }


    /**
     * Sets the idOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @param idOfertaSap
     */
    public void setIdOfertaSap(long idOfertaSap) {
        this.idOfertaSap = idOfertaSap;
    }


    /**
     * Gets the cdOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @return cdOfertaSap
     */
    public java.lang.String getCdOfertaSap() {
        return cdOfertaSap;
    }


    /**
     * Sets the cdOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @param cdOfertaSap
     */
    public void setCdOfertaSap(java.lang.String cdOfertaSap) {
        this.cdOfertaSap = cdOfertaSap;
    }


    /**
     * Gets the dscOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @return dscOfertaSap
     */
    public java.lang.String getDscOfertaSap() {
        return dscOfertaSap;
    }


    /**
     * Sets the dscOfertaSap value for this ParametrosAlterarOfertaSap.
     * 
     * @param dscOfertaSap
     */
    public void setDscOfertaSap(java.lang.String dscOfertaSap) {
        this.dscOfertaSap = dscOfertaSap;
    }


    /**
     * Gets the sgUtilizacao value for this ParametrosAlterarOfertaSap.
     * 
     * @return sgUtilizacao
     */
    public java.lang.String getSgUtilizacao() {
        return sgUtilizacao;
    }


    /**
     * Sets the sgUtilizacao value for this ParametrosAlterarOfertaSap.
     * 
     * @param sgUtilizacao
     */
    public void setSgUtilizacao(java.lang.String sgUtilizacao) {
        this.sgUtilizacao = sgUtilizacao;
    }


    /**
     * Gets the inDisponivel value for this ParametrosAlterarOfertaSap.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ParametrosAlterarOfertaSap.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarOfertaSap)) return false;
        ParametrosAlterarOfertaSap other = (ParametrosAlterarOfertaSap) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idOfertaSap == other.getIdOfertaSap() &&
            ((this.cdOfertaSap==null && other.getCdOfertaSap()==null) || 
             (this.cdOfertaSap!=null &&
              this.cdOfertaSap.equals(other.getCdOfertaSap()))) &&
            ((this.dscOfertaSap==null && other.getDscOfertaSap()==null) || 
             (this.dscOfertaSap!=null &&
              this.dscOfertaSap.equals(other.getDscOfertaSap()))) &&
            ((this.sgUtilizacao==null && other.getSgUtilizacao()==null) || 
             (this.sgUtilizacao!=null &&
              this.sgUtilizacao.equals(other.getSgUtilizacao()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel())));
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
        _hashCode += new Long(getIdOfertaSap()).hashCode();
        if (getCdOfertaSap() != null) {
            _hashCode += getCdOfertaSap().hashCode();
        }
        if (getDscOfertaSap() != null) {
            _hashCode += getDscOfertaSap().hashCode();
        }
        if (getSgUtilizacao() != null) {
            _hashCode += getSgUtilizacao().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarOfertaSap.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosAlterarOfertaSap"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "cdOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dscOfertaSap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "dscOfertaSap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgUtilizacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "sgUtilizacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "inDisponivel"));
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
