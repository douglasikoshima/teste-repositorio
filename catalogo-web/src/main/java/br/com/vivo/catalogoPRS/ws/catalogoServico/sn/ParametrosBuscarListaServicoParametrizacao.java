/**
 * ParametrosBuscarListaServicoParametrizacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosBuscarListaServicoParametrizacao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn;

    private long idPlataforma;

    private java.lang.String indisponivel;

    private java.lang.Long idCategoriaCatalogo;

    private java.lang.String nmServico;

    private java.lang.String cdServico;

    private java.lang.Long idTecnologia;

    private java.lang.Long idTpServico;

    public ParametrosBuscarListaServicoParametrizacao() {
    }

    public ParametrosBuscarListaServicoParametrizacao(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn,
           long idPlataforma,
           java.lang.String indisponivel,
           java.lang.Long idCategoriaCatalogo,
           java.lang.String nmServico,
           java.lang.String cdServico,
           java.lang.Long idTecnologia,
           java.lang.Long idTpServico) {
           this.paginacaoIn = paginacaoIn;
           this.idPlataforma = idPlataforma;
           this.indisponivel = indisponivel;
           this.idCategoriaCatalogo = idCategoriaCatalogo;
           this.nmServico = nmServico;
           this.cdServico = cdServico;
           this.idTecnologia = idTecnologia;
           this.idTpServico = idTpServico;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the indisponivel value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return indisponivel
     */
    public java.lang.String getIndisponivel() {
        return indisponivel;
    }


    /**
     * Sets the indisponivel value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param indisponivel
     */
    public void setIndisponivel(java.lang.String indisponivel) {
        this.indisponivel = indisponivel;
    }


    /**
     * Gets the idCategoriaCatalogo value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return idCategoriaCatalogo
     */
    public java.lang.Long getIdCategoriaCatalogo() {
        return idCategoriaCatalogo;
    }


    /**
     * Sets the idCategoriaCatalogo value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param idCategoriaCatalogo
     */
    public void setIdCategoriaCatalogo(java.lang.Long idCategoriaCatalogo) {
        this.idCategoriaCatalogo = idCategoriaCatalogo;
    }


    /**
     * Gets the nmServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return nmServico
     */
    public java.lang.String getNmServico() {
        return nmServico;
    }


    /**
     * Sets the nmServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param nmServico
     */
    public void setNmServico(java.lang.String nmServico) {
        this.nmServico = nmServico;
    }


    /**
     * Gets the cdServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return cdServico
     */
    public java.lang.String getCdServico() {
        return cdServico;
    }


    /**
     * Sets the cdServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param cdServico
     */
    public void setCdServico(java.lang.String cdServico) {
        this.cdServico = cdServico;
    }


    /**
     * Gets the idTecnologia value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTpServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @return idTpServico
     */
    public java.lang.Long getIdTpServico() {
        return idTpServico;
    }


    /**
     * Sets the idTpServico value for this ParametrosBuscarListaServicoParametrizacao.
     * 
     * @param idTpServico
     */
    public void setIdTpServico(java.lang.Long idTpServico) {
        this.idTpServico = idTpServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaServicoParametrizacao)) return false;
        ParametrosBuscarListaServicoParametrizacao other = (ParametrosBuscarListaServicoParametrizacao) obj;
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
            ((this.indisponivel==null && other.getIndisponivel()==null) || 
             (this.indisponivel!=null &&
              this.indisponivel.equals(other.getIndisponivel()))) &&
            ((this.idCategoriaCatalogo==null && other.getIdCategoriaCatalogo()==null) || 
             (this.idCategoriaCatalogo!=null &&
              this.idCategoriaCatalogo.equals(other.getIdCategoriaCatalogo()))) &&
            ((this.nmServico==null && other.getNmServico()==null) || 
             (this.nmServico!=null &&
              this.nmServico.equals(other.getNmServico()))) &&
            ((this.cdServico==null && other.getCdServico()==null) || 
             (this.cdServico!=null &&
              this.cdServico.equals(other.getCdServico()))) &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.idTpServico==null && other.getIdTpServico()==null) || 
             (this.idTpServico!=null &&
              this.idTpServico.equals(other.getIdTpServico())));
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
        if (getIndisponivel() != null) {
            _hashCode += getIndisponivel().hashCode();
        }
        if (getIdCategoriaCatalogo() != null) {
            _hashCode += getIdCategoriaCatalogo().hashCode();
        }
        if (getNmServico() != null) {
            _hashCode += getNmServico().hashCode();
        }
        if (getCdServico() != null) {
            _hashCode += getCdServico().hashCode();
        }
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getIdTpServico() != null) {
            _hashCode += getIdTpServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaServicoParametrizacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaServicoParametrizacao"));
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
        elemField.setFieldName("indisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "indisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoriaCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoriaCatalogo"));
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
        elemField.setFieldName("cdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "cdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTpServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idTpServico"));
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
