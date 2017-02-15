/**
 * ResultadoBuscarCanalAtendimentoCanalAtendimento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoCanalAtendimento.sn;

public class ResultadoBuscarCanalAtendimentoCanalAtendimento  implements java.io.Serializable {
    private long idCanal;

    private java.lang.String sgVivoNet;

    private java.lang.String nmCanal;

    private java.lang.String inDisponivel;

    public ResultadoBuscarCanalAtendimentoCanalAtendimento() {
    }

    public ResultadoBuscarCanalAtendimentoCanalAtendimento(
           long idCanal,
           java.lang.String sgVivoNet,
           java.lang.String nmCanal,
           java.lang.String inDisponivel) {
           this.idCanal = idCanal;
           this.sgVivoNet = sgVivoNet;
           this.nmCanal = nmCanal;
           this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the idCanal value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @return idCanal
     */
    public long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @param idCanal
     */
    public void setIdCanal(long idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the sgVivoNet value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @return sgVivoNet
     */
    public java.lang.String getSgVivoNet() {
        return sgVivoNet;
    }


    /**
     * Sets the sgVivoNet value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @param sgVivoNet
     */
    public void setSgVivoNet(java.lang.String sgVivoNet) {
        this.sgVivoNet = sgVivoNet;
    }


    /**
     * Gets the nmCanal value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @return nmCanal
     */
    public java.lang.String getNmCanal() {
        return nmCanal;
    }


    /**
     * Sets the nmCanal value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @param nmCanal
     */
    public void setNmCanal(java.lang.String nmCanal) {
        this.nmCanal = nmCanal;
    }


    /**
     * Gets the inDisponivel value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoBuscarCanalAtendimentoCanalAtendimento.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarCanalAtendimentoCanalAtendimento)) return false;
        ResultadoBuscarCanalAtendimentoCanalAtendimento other = (ResultadoBuscarCanalAtendimentoCanalAtendimento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCanal == other.getIdCanal() &&
            ((this.sgVivoNet==null && other.getSgVivoNet()==null) || 
             (this.sgVivoNet!=null &&
              this.sgVivoNet.equals(other.getSgVivoNet()))) &&
            ((this.nmCanal==null && other.getNmCanal()==null) || 
             (this.nmCanal!=null &&
              this.nmCanal.equals(other.getNmCanal()))) &&
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
        _hashCode += new Long(getIdCanal()).hashCode();
        if (getSgVivoNet() != null) {
            _hashCode += getSgVivoNet().hashCode();
        }
        if (getNmCanal() != null) {
            _hashCode += getNmCanal().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarCanalAtendimentoCanalAtendimento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CanalAtendimento", ">>ResultadoBuscarCanalAtendimento>CanalAtendimento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CanalAtendimento", "idCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgVivoNet");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CanalAtendimento", "sgVivoNet"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CanalAtendimento", "nmCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CanalAtendimento", "inDisponivel"));
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
