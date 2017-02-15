/**
 * ParametrosBuscarListaOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ParametrosBuscarListaOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.lang.String cdOfertaServico;

    private java.lang.String nmOfertaServico;

    private java.lang.String cdCodigo;

    private java.lang.String nmComercial;

    public ParametrosBuscarListaOfertaServico() {
    }

    public ParametrosBuscarListaOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.lang.String cdOfertaServico,
           java.lang.String nmOfertaServico,
           java.lang.String cdCodigo,
           java.lang.String nmComercial) {
           this.paginacaoIn = paginacaoIn;
           this.cdOfertaServico = cdOfertaServico;
           this.nmOfertaServico = nmOfertaServico;
           this.cdCodigo = cdCodigo;
           this.nmComercial = nmComercial;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaOfertaServico.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaOfertaServico.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the cdOfertaServico value for this ParametrosBuscarListaOfertaServico.
     * 
     * @return cdOfertaServico
     */
    public java.lang.String getCdOfertaServico() {
        return cdOfertaServico;
    }


    /**
     * Sets the cdOfertaServico value for this ParametrosBuscarListaOfertaServico.
     * 
     * @param cdOfertaServico
     */
    public void setCdOfertaServico(java.lang.String cdOfertaServico) {
        this.cdOfertaServico = cdOfertaServico;
    }


    /**
     * Gets the nmOfertaServico value for this ParametrosBuscarListaOfertaServico.
     * 
     * @return nmOfertaServico
     */
    public java.lang.String getNmOfertaServico() {
        return nmOfertaServico;
    }


    /**
     * Sets the nmOfertaServico value for this ParametrosBuscarListaOfertaServico.
     * 
     * @param nmOfertaServico
     */
    public void setNmOfertaServico(java.lang.String nmOfertaServico) {
        this.nmOfertaServico = nmOfertaServico;
    }


    /**
     * Gets the cdCodigo value for this ParametrosBuscarListaOfertaServico.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ParametrosBuscarListaOfertaServico.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the nmComercial value for this ParametrosBuscarListaOfertaServico.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ParametrosBuscarListaOfertaServico.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaOfertaServico)) return false;
        ParametrosBuscarListaOfertaServico other = (ParametrosBuscarListaOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn()))) &&
            ((this.cdOfertaServico==null && other.getCdOfertaServico()==null) || 
             (this.cdOfertaServico!=null &&
              this.cdOfertaServico.equals(other.getCdOfertaServico()))) &&
            ((this.nmOfertaServico==null && other.getNmOfertaServico()==null) || 
             (this.nmOfertaServico!=null &&
              this.nmOfertaServico.equals(other.getNmOfertaServico()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial())));
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
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        if (getCdOfertaServico() != null) {
            _hashCode += getCdOfertaServico().hashCode();
        }
        if (getNmOfertaServico() != null) {
            _hashCode += getNmOfertaServico().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ParametrosBuscarListaOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "cdOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "nmComercial"));
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
