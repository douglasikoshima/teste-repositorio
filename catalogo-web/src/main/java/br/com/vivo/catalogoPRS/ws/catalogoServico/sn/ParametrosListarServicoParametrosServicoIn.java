/**
 * ParametrosListarServicoParametrosServicoIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosListarServicoParametrosServicoIn  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn;

    private long idPlataforma;

    private java.lang.Long idCategoria;

    private java.lang.String nmServico;

    private java.lang.String cdcodigo;

    private java.lang.String nmPlano;

    private java.lang.String idSistema;

    public ParametrosListarServicoParametrosServicoIn() {
    }

    public ParametrosListarServicoParametrosServicoIn(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn,
           long idPlataforma,
           java.lang.Long idCategoria,
           java.lang.String nmServico,
           java.lang.String cdcodigo,
           java.lang.String nmPlano,
           java.lang.String idSistema) {
           this.paginacaoIn = paginacaoIn;
           this.idPlataforma = idPlataforma;
           this.idCategoria = idCategoria;
           this.nmServico = nmServico;
           this.cdcodigo = cdcodigo;
           this.nmPlano = nmPlano;
           this.idSistema = idSistema;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idPlataforma value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idCategoria value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return idCategoria
     */
    public java.lang.Long getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(java.lang.Long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the nmServico value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return nmServico
     */
    public java.lang.String getNmServico() {
        return nmServico;
    }


    /**
     * Sets the nmServico value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param nmServico
     */
    public void setNmServico(java.lang.String nmServico) {
        this.nmServico = nmServico;
    }


    /**
     * Gets the cdcodigo value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return cdcodigo
     */
    public java.lang.String getCdcodigo() {
        return cdcodigo;
    }


    /**
     * Sets the cdcodigo value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param cdcodigo
     */
    public void setCdcodigo(java.lang.String cdcodigo) {
        this.cdcodigo = cdcodigo;
    }


    /**
     * Gets the nmPlano value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return nmPlano
     */
    public java.lang.String getNmPlano() {
        return nmPlano;
    }


    /**
     * Sets the nmPlano value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param nmPlano
     */
    public void setNmPlano(java.lang.String nmPlano) {
        this.nmPlano = nmPlano;
    }


    /**
     * Gets the idSistema value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @return idSistema
     */
    public java.lang.String getIdSistema() {
        return idSistema;
    }


    /**
     * Sets the idSistema value for this ParametrosListarServicoParametrosServicoIn.
     * 
     * @param idSistema
     */
    public void setIdSistema(java.lang.String idSistema) {
        this.idSistema = idSistema;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosListarServicoParametrosServicoIn)) return false;
        ParametrosListarServicoParametrosServicoIn other = (ParametrosListarServicoParametrosServicoIn) obj;
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
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.idCategoria==null && other.getIdCategoria()==null) || 
             (this.idCategoria!=null &&
              this.idCategoria.equals(other.getIdCategoria()))) &&
            ((this.nmServico==null && other.getNmServico()==null) || 
             (this.nmServico!=null &&
              this.nmServico.equals(other.getNmServico()))) &&
            ((this.cdcodigo==null && other.getCdcodigo()==null) || 
             (this.cdcodigo!=null &&
              this.cdcodigo.equals(other.getCdcodigo()))) &&
            ((this.nmPlano==null && other.getNmPlano()==null) || 
             (this.nmPlano!=null &&
              this.nmPlano.equals(other.getNmPlano()))) &&
            ((this.idSistema==null && other.getIdSistema()==null) || 
             (this.idSistema!=null &&
              this.idSistema.equals(other.getIdSistema())));
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
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getIdCategoria() != null) {
            _hashCode += getIdCategoria().hashCode();
        }
        if (getNmServico() != null) {
            _hashCode += getNmServico().hashCode();
        }
        if (getCdcodigo() != null) {
            _hashCode += getCdcodigo().hashCode();
        }
        if (getNmPlano() != null) {
            _hashCode += getNmPlano().hashCode();
        }
        if (getIdSistema() != null) {
            _hashCode += getIdSistema().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosListarServicoParametrosServicoIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>ParametrosListarServico>ParametrosServicoIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdcodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdcodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "nmPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idSistema"));
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
