/**
 * ParametrosObterExistenciaItemPreco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosObterExistenciaItemPreco  implements java.io.Serializable {
    private java.util.Calendar dtInicial;

    private java.math.BigDecimal valor;

    private java.lang.String sgOrgVendas;

    private java.lang.Long codigoArea;

    private java.lang.String cdAdabas;

    private long idCanalDistribuicao;

    private long idMatrizOfertaItemPreco;

    private java.lang.Long idAcao;

    private java.util.Calendar dtFinal;

    private java.lang.Long idEscritorioVenda;

    public ParametrosObterExistenciaItemPreco() {
    }

    public ParametrosObterExistenciaItemPreco(
           java.util.Calendar dtInicial,
           java.math.BigDecimal valor,
           java.lang.String sgOrgVendas,
           java.lang.Long codigoArea,
           java.lang.String cdAdabas,
           long idCanalDistribuicao,
           long idMatrizOfertaItemPreco,
           java.lang.Long idAcao,
           java.util.Calendar dtFinal,
           java.lang.Long idEscritorioVenda) {
           this.dtInicial = dtInicial;
           this.valor = valor;
           this.sgOrgVendas = sgOrgVendas;
           this.codigoArea = codigoArea;
           this.cdAdabas = cdAdabas;
           this.idCanalDistribuicao = idCanalDistribuicao;
           this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
           this.idAcao = idAcao;
           this.dtFinal = dtFinal;
           this.idEscritorioVenda = idEscritorioVenda;
    }


    /**
     * Gets the dtInicial value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the valor value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return valor
     */
    public java.math.BigDecimal getValor() {
        return valor;
    }


    /**
     * Sets the valor value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param valor
     */
    public void setValor(java.math.BigDecimal valor) {
        this.valor = valor;
    }


    /**
     * Gets the sgOrgVendas value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return sgOrgVendas
     */
    public java.lang.String getSgOrgVendas() {
        return sgOrgVendas;
    }


    /**
     * Sets the sgOrgVendas value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param sgOrgVendas
     */
    public void setSgOrgVendas(java.lang.String sgOrgVendas) {
        this.sgOrgVendas = sgOrgVendas;
    }


    /**
     * Gets the codigoArea value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return codigoArea
     */
    public java.lang.Long getCodigoArea() {
        return codigoArea;
    }


    /**
     * Sets the codigoArea value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param codigoArea
     */
    public void setCodigoArea(java.lang.Long codigoArea) {
        this.codigoArea = codigoArea;
    }


    /**
     * Gets the cdAdabas value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return cdAdabas
     */
    public java.lang.String getCdAdabas() {
        return cdAdabas;
    }


    /**
     * Sets the cdAdabas value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param cdAdabas
     */
    public void setCdAdabas(java.lang.String cdAdabas) {
        this.cdAdabas = cdAdabas;
    }


    /**
     * Gets the idCanalDistribuicao value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return idCanalDistribuicao
     */
    public long getIdCanalDistribuicao() {
        return idCanalDistribuicao;
    }


    /**
     * Sets the idCanalDistribuicao value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param idCanalDistribuicao
     */
    public void setIdCanalDistribuicao(long idCanalDistribuicao) {
        this.idCanalDistribuicao = idCanalDistribuicao;
    }


    /**
     * Gets the idMatrizOfertaItemPreco value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return idMatrizOfertaItemPreco
     */
    public long getIdMatrizOfertaItemPreco() {
        return idMatrizOfertaItemPreco;
    }


    /**
     * Sets the idMatrizOfertaItemPreco value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param idMatrizOfertaItemPreco
     */
    public void setIdMatrizOfertaItemPreco(long idMatrizOfertaItemPreco) {
        this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
    }


    /**
     * Gets the idAcao value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return idAcao
     */
    public java.lang.Long getIdAcao() {
        return idAcao;
    }


    /**
     * Sets the idAcao value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param idAcao
     */
    public void setIdAcao(java.lang.Long idAcao) {
        this.idAcao = idAcao;
    }


    /**
     * Gets the dtFinal value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the idEscritorioVenda value for this ParametrosObterExistenciaItemPreco.
     * 
     * @return idEscritorioVenda
     */
    public java.lang.Long getIdEscritorioVenda() {
        return idEscritorioVenda;
    }


    /**
     * Sets the idEscritorioVenda value for this ParametrosObterExistenciaItemPreco.
     * 
     * @param idEscritorioVenda
     */
    public void setIdEscritorioVenda(java.lang.Long idEscritorioVenda) {
        this.idEscritorioVenda = idEscritorioVenda;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosObterExistenciaItemPreco)) return false;
        ParametrosObterExistenciaItemPreco other = (ParametrosObterExistenciaItemPreco) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.valor==null && other.getValor()==null) || 
             (this.valor!=null &&
              this.valor.equals(other.getValor()))) &&
            ((this.sgOrgVendas==null && other.getSgOrgVendas()==null) || 
             (this.sgOrgVendas!=null &&
              this.sgOrgVendas.equals(other.getSgOrgVendas()))) &&
            ((this.codigoArea==null && other.getCodigoArea()==null) || 
             (this.codigoArea!=null &&
              this.codigoArea.equals(other.getCodigoArea()))) &&
            ((this.cdAdabas==null && other.getCdAdabas()==null) || 
             (this.cdAdabas!=null &&
              this.cdAdabas.equals(other.getCdAdabas()))) &&
            this.idCanalDistribuicao == other.getIdCanalDistribuicao() &&
            this.idMatrizOfertaItemPreco == other.getIdMatrizOfertaItemPreco() &&
            ((this.idAcao==null && other.getIdAcao()==null) || 
             (this.idAcao!=null &&
              this.idAcao.equals(other.getIdAcao()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            ((this.idEscritorioVenda==null && other.getIdEscritorioVenda()==null) || 
             (this.idEscritorioVenda!=null &&
              this.idEscritorioVenda.equals(other.getIdEscritorioVenda())));
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
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getValor() != null) {
            _hashCode += getValor().hashCode();
        }
        if (getSgOrgVendas() != null) {
            _hashCode += getSgOrgVendas().hashCode();
        }
        if (getCodigoArea() != null) {
            _hashCode += getCodigoArea().hashCode();
        }
        if (getCdAdabas() != null) {
            _hashCode += getCdAdabas().hashCode();
        }
        _hashCode += new Long(getIdCanalDistribuicao()).hashCode();
        _hashCode += new Long(getIdMatrizOfertaItemPreco()).hashCode();
        if (getIdAcao() != null) {
            _hashCode += getIdAcao().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        if (getIdEscritorioVenda() != null) {
            _hashCode += getIdEscritorioVenda().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosObterExistenciaItemPreco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosObterExistenciaItemPreco"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "valor"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sgOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "sgOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoArea");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "codigoArea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAdabas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "cdAdabas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanalDistribuicao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanalDistribuicao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOfertaItemPreco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOfertaItemPreco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idEscritorioVenda");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idEscritorioVenda"));
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
