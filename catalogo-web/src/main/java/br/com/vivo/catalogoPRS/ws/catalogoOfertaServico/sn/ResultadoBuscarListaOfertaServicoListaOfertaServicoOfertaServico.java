/**
 * ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico  implements java.io.Serializable {
    private long idOfertaServico;

    private java.lang.String cdOfertaServico;

    private java.lang.String nmOfertaServico;

    private java.lang.String dsNota;

    private java.lang.Long rnum;

    public ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico() {
    }

    public ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico(
           long idOfertaServico,
           java.lang.String cdOfertaServico,
           java.lang.String nmOfertaServico,
           java.lang.String dsNota,
           java.lang.Long rnum) {
           this.idOfertaServico = idOfertaServico;
           this.cdOfertaServico = cdOfertaServico;
           this.nmOfertaServico = nmOfertaServico;
           this.dsNota = dsNota;
           this.rnum = rnum;
    }


    /**
     * Gets the idOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @return idOfertaServico
     */
    public long getIdOfertaServico() {
        return idOfertaServico;
    }


    /**
     * Sets the idOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @param idOfertaServico
     */
    public void setIdOfertaServico(long idOfertaServico) {
        this.idOfertaServico = idOfertaServico;
    }


    /**
     * Gets the cdOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @return cdOfertaServico
     */
    public java.lang.String getCdOfertaServico() {
        return cdOfertaServico;
    }


    /**
     * Sets the cdOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @param cdOfertaServico
     */
    public void setCdOfertaServico(java.lang.String cdOfertaServico) {
        this.cdOfertaServico = cdOfertaServico;
    }


    /**
     * Gets the nmOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @return nmOfertaServico
     */
    public java.lang.String getNmOfertaServico() {
        return nmOfertaServico;
    }


    /**
     * Sets the nmOfertaServico value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @param nmOfertaServico
     */
    public void setNmOfertaServico(java.lang.String nmOfertaServico) {
        this.nmOfertaServico = nmOfertaServico;
    }


    /**
     * Gets the dsNota value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @return dsNota
     */
    public java.lang.String getDsNota() {
        return dsNota;
    }


    /**
     * Sets the dsNota value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @param dsNota
     */
    public void setDsNota(java.lang.String dsNota) {
        this.dsNota = dsNota;
    }


    /**
     * Gets the rnum value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @return rnum
     */
    public java.lang.Long getRnum() {
        return rnum;
    }


    /**
     * Sets the rnum value for this ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.
     * 
     * @param rnum
     */
    public void setRnum(java.lang.Long rnum) {
        this.rnum = rnum;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico)) return false;
        ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico other = (ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idOfertaServico == other.getIdOfertaServico() &&
            ((this.cdOfertaServico==null && other.getCdOfertaServico()==null) || 
             (this.cdOfertaServico!=null &&
              this.cdOfertaServico.equals(other.getCdOfertaServico()))) &&
            ((this.nmOfertaServico==null && other.getNmOfertaServico()==null) || 
             (this.nmOfertaServico!=null &&
              this.nmOfertaServico.equals(other.getNmOfertaServico()))) &&
            ((this.dsNota==null && other.getDsNota()==null) || 
             (this.dsNota!=null &&
              this.dsNota.equals(other.getDsNota()))) &&
            ((this.rnum==null && other.getRnum()==null) || 
             (this.rnum!=null &&
              this.rnum.equals(other.getRnum())));
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
        _hashCode += new Long(getIdOfertaServico()).hashCode();
        if (getCdOfertaServico() != null) {
            _hashCode += getCdOfertaServico().hashCode();
        }
        if (getNmOfertaServico() != null) {
            _hashCode += getNmOfertaServico().hashCode();
        }
        if (getDsNota() != null) {
            _hashCode += getDsNota().hashCode();
        }
        if (getRnum() != null) {
            _hashCode += getRnum().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarListaOfertaServico>ListaOfertaServico>OfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "idOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "cdOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsNota");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "dsNota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rnum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "rnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
