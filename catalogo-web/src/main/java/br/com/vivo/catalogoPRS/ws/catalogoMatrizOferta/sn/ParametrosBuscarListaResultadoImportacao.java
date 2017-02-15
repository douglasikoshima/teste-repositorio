/**
 * ParametrosBuscarListaResultadoImportacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosBuscarListaResultadoImportacao  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.lang.Long idStatusArquivoImportacao;

    private java.lang.String nmArquivo;

    private java.lang.String userImportacao;

    private java.lang.String nmMatrizOferta;

    private java.util.Calendar dtImportacaoInicial;

    private java.util.Calendar dtImportacaoFinal;

    public ParametrosBuscarListaResultadoImportacao() {
    }

    public ParametrosBuscarListaResultadoImportacao(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.lang.Long idStatusArquivoImportacao,
           java.lang.String nmArquivo,
           java.lang.String userImportacao,
           java.lang.String nmMatrizOferta,
           java.util.Calendar dtImportacaoInicial,
           java.util.Calendar dtImportacaoFinal) {
           this.paginacaoIn = paginacaoIn;
           this.idStatusArquivoImportacao = idStatusArquivoImportacao;
           this.nmArquivo = nmArquivo;
           this.userImportacao = userImportacao;
           this.nmMatrizOferta = nmMatrizOferta;
           this.dtImportacaoInicial = dtImportacaoInicial;
           this.dtImportacaoFinal = dtImportacaoFinal;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idStatusArquivoImportacao value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return idStatusArquivoImportacao
     */
    public java.lang.Long getIdStatusArquivoImportacao() {
        return idStatusArquivoImportacao;
    }


    /**
     * Sets the idStatusArquivoImportacao value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param idStatusArquivoImportacao
     */
    public void setIdStatusArquivoImportacao(java.lang.Long idStatusArquivoImportacao) {
        this.idStatusArquivoImportacao = idStatusArquivoImportacao;
    }


    /**
     * Gets the nmArquivo value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return nmArquivo
     */
    public java.lang.String getNmArquivo() {
        return nmArquivo;
    }


    /**
     * Sets the nmArquivo value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param nmArquivo
     */
    public void setNmArquivo(java.lang.String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }


    /**
     * Gets the userImportacao value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return userImportacao
     */
    public java.lang.String getUserImportacao() {
        return userImportacao;
    }


    /**
     * Sets the userImportacao value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param userImportacao
     */
    public void setUserImportacao(java.lang.String userImportacao) {
        this.userImportacao = userImportacao;
    }


    /**
     * Gets the nmMatrizOferta value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return nmMatrizOferta
     */
    public java.lang.String getNmMatrizOferta() {
        return nmMatrizOferta;
    }


    /**
     * Sets the nmMatrizOferta value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param nmMatrizOferta
     */
    public void setNmMatrizOferta(java.lang.String nmMatrizOferta) {
        this.nmMatrizOferta = nmMatrizOferta;
    }


    /**
     * Gets the dtImportacaoInicial value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return dtImportacaoInicial
     */
    public java.util.Calendar getDtImportacaoInicial() {
        return dtImportacaoInicial;
    }


    /**
     * Sets the dtImportacaoInicial value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param dtImportacaoInicial
     */
    public void setDtImportacaoInicial(java.util.Calendar dtImportacaoInicial) {
        this.dtImportacaoInicial = dtImportacaoInicial;
    }


    /**
     * Gets the dtImportacaoFinal value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @return dtImportacaoFinal
     */
    public java.util.Calendar getDtImportacaoFinal() {
        return dtImportacaoFinal;
    }


    /**
     * Sets the dtImportacaoFinal value for this ParametrosBuscarListaResultadoImportacao.
     * 
     * @param dtImportacaoFinal
     */
    public void setDtImportacaoFinal(java.util.Calendar dtImportacaoFinal) {
        this.dtImportacaoFinal = dtImportacaoFinal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaResultadoImportacao)) return false;
        ParametrosBuscarListaResultadoImportacao other = (ParametrosBuscarListaResultadoImportacao) obj;
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
            ((this.idStatusArquivoImportacao==null && other.getIdStatusArquivoImportacao()==null) || 
             (this.idStatusArquivoImportacao!=null &&
              this.idStatusArquivoImportacao.equals(other.getIdStatusArquivoImportacao()))) &&
            ((this.nmArquivo==null && other.getNmArquivo()==null) || 
             (this.nmArquivo!=null &&
              this.nmArquivo.equals(other.getNmArquivo()))) &&
            ((this.userImportacao==null && other.getUserImportacao()==null) || 
             (this.userImportacao!=null &&
              this.userImportacao.equals(other.getUserImportacao()))) &&
            ((this.nmMatrizOferta==null && other.getNmMatrizOferta()==null) || 
             (this.nmMatrizOferta!=null &&
              this.nmMatrizOferta.equals(other.getNmMatrizOferta()))) &&
            ((this.dtImportacaoInicial==null && other.getDtImportacaoInicial()==null) || 
             (this.dtImportacaoInicial!=null &&
              this.dtImportacaoInicial.equals(other.getDtImportacaoInicial()))) &&
            ((this.dtImportacaoFinal==null && other.getDtImportacaoFinal()==null) || 
             (this.dtImportacaoFinal!=null &&
              this.dtImportacaoFinal.equals(other.getDtImportacaoFinal())));
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
        if (getIdStatusArquivoImportacao() != null) {
            _hashCode += getIdStatusArquivoImportacao().hashCode();
        }
        if (getNmArquivo() != null) {
            _hashCode += getNmArquivo().hashCode();
        }
        if (getUserImportacao() != null) {
            _hashCode += getUserImportacao().hashCode();
        }
        if (getNmMatrizOferta() != null) {
            _hashCode += getNmMatrizOferta().hashCode();
        }
        if (getDtImportacaoInicial() != null) {
            _hashCode += getDtImportacaoInicial().hashCode();
        }
        if (getDtImportacaoFinal() != null) {
            _hashCode += getDtImportacaoFinal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaResultadoImportacao.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosBuscarListaResultadoImportacao"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idStatusArquivoImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idStatusArquivoImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmArquivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmArquivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userImportacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "userImportacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtImportacaoInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtImportacaoInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtImportacaoFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtImportacaoFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
