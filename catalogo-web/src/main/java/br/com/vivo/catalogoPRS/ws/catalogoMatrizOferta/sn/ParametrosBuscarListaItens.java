/**
 * ParametrosBuscarListaItens.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosBuscarListaItens  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.util.Calendar dtVigencia;

    private java.lang.Long idOfertaSAP;

    private long idOrganizacaoVendas;

    private long idCanalAtendimento;

    private long idMatrizOferta;

    private java.lang.String canalVendas;

    private java.lang.String cdProduto;

    private long[] listaDDD;

    private java.lang.String sgAcao;

    public ParametrosBuscarListaItens() {
    }

    public ParametrosBuscarListaItens(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.util.Calendar dtVigencia,
           java.lang.Long idOfertaSAP,
           long idOrganizacaoVendas,
           long idCanalAtendimento,
           long idMatrizOferta,
           java.lang.String canalVendas,
           java.lang.String cdProduto,
           long[] listaDDD,
           java.lang.String sgAcao) {
           this.paginacaoIn = paginacaoIn;
           this.dtVigencia = dtVigencia;
           this.idOfertaSAP = idOfertaSAP;
           this.idOrganizacaoVendas = idOrganizacaoVendas;
           this.idCanalAtendimento = idCanalAtendimento;
           this.idMatrizOferta = idMatrizOferta;
           this.canalVendas = canalVendas;
           this.cdProduto = cdProduto;
           this.listaDDD = listaDDD;
           this.sgAcao = sgAcao;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaItens.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaItens.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the dtVigencia value for this ParametrosBuscarListaItens.
     * 
     * @return dtVigencia
     */
    public java.util.Calendar getDtVigencia() {
        return dtVigencia;
    }


    /**
     * Sets the dtVigencia value for this ParametrosBuscarListaItens.
     * 
     * @param dtVigencia
     */
    public void setDtVigencia(java.util.Calendar dtVigencia) {
        this.dtVigencia = dtVigencia;
    }


    /**
     * Gets the idOfertaSAP value for this ParametrosBuscarListaItens.
     * 
     * @return idOfertaSAP
     */
    public java.lang.Long getIdOfertaSAP() {
        return idOfertaSAP;
    }


    /**
     * Sets the idOfertaSAP value for this ParametrosBuscarListaItens.
     * 
     * @param idOfertaSAP
     */
    public void setIdOfertaSAP(java.lang.Long idOfertaSAP) {
        this.idOfertaSAP = idOfertaSAP;
    }


    /**
     * Gets the idOrganizacaoVendas value for this ParametrosBuscarListaItens.
     * 
     * @return idOrganizacaoVendas
     */
    public long getIdOrganizacaoVendas() {
        return idOrganizacaoVendas;
    }


    /**
     * Sets the idOrganizacaoVendas value for this ParametrosBuscarListaItens.
     * 
     * @param idOrganizacaoVendas
     */
    public void setIdOrganizacaoVendas(long idOrganizacaoVendas) {
        this.idOrganizacaoVendas = idOrganizacaoVendas;
    }


    /**
     * Gets the idCanalAtendimento value for this ParametrosBuscarListaItens.
     * 
     * @return idCanalAtendimento
     */
    public long getIdCanalAtendimento() {
        return idCanalAtendimento;
    }


    /**
     * Sets the idCanalAtendimento value for this ParametrosBuscarListaItens.
     * 
     * @param idCanalAtendimento
     */
    public void setIdCanalAtendimento(long idCanalAtendimento) {
        this.idCanalAtendimento = idCanalAtendimento;
    }


    /**
     * Gets the idMatrizOferta value for this ParametrosBuscarListaItens.
     * 
     * @return idMatrizOferta
     */
    public long getIdMatrizOferta() {
        return idMatrizOferta;
    }


    /**
     * Sets the idMatrizOferta value for this ParametrosBuscarListaItens.
     * 
     * @param idMatrizOferta
     */
    public void setIdMatrizOferta(long idMatrizOferta) {
        this.idMatrizOferta = idMatrizOferta;
    }


    /**
     * Gets the canalVendas value for this ParametrosBuscarListaItens.
     * 
     * @return canalVendas
     */
    public java.lang.String getCanalVendas() {
        return canalVendas;
    }


    /**
     * Sets the canalVendas value for this ParametrosBuscarListaItens.
     * 
     * @param canalVendas
     */
    public void setCanalVendas(java.lang.String canalVendas) {
        this.canalVendas = canalVendas;
    }


    /**
     * Gets the cdProduto value for this ParametrosBuscarListaItens.
     * 
     * @return cdProduto
     */
    public java.lang.String getCdProduto() {
        return cdProduto;
    }


    /**
     * Sets the cdProduto value for this ParametrosBuscarListaItens.
     * 
     * @param cdProduto
     */
    public void setCdProduto(java.lang.String cdProduto) {
        this.cdProduto = cdProduto;
    }


    /**
     * Gets the listaDDD value for this ParametrosBuscarListaItens.
     * 
     * @return listaDDD
     */
    public long[] getListaDDD() {
        return listaDDD;
    }


    /**
     * Sets the listaDDD value for this ParametrosBuscarListaItens.
     * 
     * @param listaDDD
     */
    public void setListaDDD(long[] listaDDD) {
        this.listaDDD = listaDDD;
    }


    /**
     * Gets the sgAcao value for this ParametrosBuscarListaItens.
     * 
     * @return sgAcao
     */
    public java.lang.String getSgAcao() {
        return sgAcao;
    }


    /**
     * Sets the sgAcao value for this ParametrosBuscarListaItens.
     * 
     * @param sgAcao
     */
    public void setSgAcao(java.lang.String sgAcao) {
        this.sgAcao = sgAcao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaItens)) return false;
        ParametrosBuscarListaItens other = (ParametrosBuscarListaItens) obj;
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
            ((this.dtVigencia==null && other.getDtVigencia()==null) || 
             (this.dtVigencia!=null &&
              this.dtVigencia.equals(other.getDtVigencia()))) &&
            ((this.idOfertaSAP==null && other.getIdOfertaSAP()==null) || 
             (this.idOfertaSAP!=null &&
              this.idOfertaSAP.equals(other.getIdOfertaSAP()))) &&
            this.idOrganizacaoVendas == other.getIdOrganizacaoVendas() &&
            this.idCanalAtendimento == other.getIdCanalAtendimento() &&
            this.idMatrizOferta == other.getIdMatrizOferta() &&
            ((this.canalVendas==null && other.getCanalVendas()==null) || 
             (this.canalVendas!=null &&
              this.canalVendas.equals(other.getCanalVendas()))) &&
            ((this.cdProduto==null && other.getCdProduto()==null) || 
             (this.cdProduto!=null &&
              this.cdProduto.equals(other.getCdProduto()))) &&
            ((this.listaDDD==null && other.getListaDDD()==null) || 
             (this.listaDDD!=null &&
              java.util.Arrays.equals(this.listaDDD, other.getListaDDD()))) &&
            ((this.sgAcao==null && other.getSgAcao()==null) || 
             (this.sgAcao!=null &&
              this.sgAcao.equals(other.getSgAcao())));
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
        if (getDtVigencia() != null) {
            _hashCode += getDtVigencia().hashCode();
        }
        if (getIdOfertaSAP() != null) {
            _hashCode += getIdOfertaSAP().hashCode();
        }
        _hashCode += new Long(getIdOrganizacaoVendas()).hashCode();
        _hashCode += new Long(getIdCanalAtendimento()).hashCode();
        _hashCode += new Long(getIdMatrizOferta()).hashCode();
        if (getCanalVendas() != null) {
            _hashCode += getCanalVendas().hashCode();
        }
        if (getCdProduto() != null) {
            _hashCode += getCdProduto().hashCode();
        }
        if (getListaDDD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaDDD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaDDD(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSgAcao() != null) {
            _hashCode += getSgAcao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaItens.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaItens"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOfertaSAP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idOfertaSAP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganizacaoVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idOrganizacaoVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanalAtendimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanalAtendimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canalVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "canalVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "cdProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "listaDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ddd"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "sgAcao"));
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
