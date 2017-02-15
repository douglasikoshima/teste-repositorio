/**
 * ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano  implements java.io.Serializable {
    private java.lang.String nmComercial;

    private java.lang.String cdAnatel;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    private long idPlano;

    private java.lang.String inDisponivel;

    private java.lang.Double valorAssinaturaMensal;

    private java.lang.Double qtdeMinFranquia;

    private java.lang.String indicadorTitularDependente;

    private java.lang.Double qtdeMaxDependCatalogo;

    private java.lang.String cdCodigo;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico[] listaServico;

    public ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano() {
    }

    public ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano(
           java.lang.String nmComercial,
           java.lang.String cdAnatel,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal,
           long idPlano,
           java.lang.String inDisponivel,
           java.lang.Double valorAssinaturaMensal,
           java.lang.Double qtdeMinFranquia,
           java.lang.String indicadorTitularDependente,
           java.lang.Double qtdeMaxDependCatalogo,
           java.lang.String cdCodigo,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico[] listaServico) {
           this.nmComercial = nmComercial;
           this.cdAnatel = cdAnatel;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.idPlano = idPlano;
           this.inDisponivel = inDisponivel;
           this.valorAssinaturaMensal = valorAssinaturaMensal;
           this.qtdeMinFranquia = qtdeMinFranquia;
           this.indicadorTitularDependente = indicadorTitularDependente;
           this.qtdeMaxDependCatalogo = qtdeMaxDependCatalogo;
           this.cdCodigo = cdCodigo;
           this.listaServico = listaServico;
    }


    /**
     * Gets the nmComercial value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the cdAnatel value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return cdAnatel
     */
    public java.lang.String getCdAnatel() {
        return cdAnatel;
    }


    /**
     * Sets the cdAnatel value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param cdAnatel
     */
    public void setCdAnatel(java.lang.String cdAnatel) {
        this.cdAnatel = cdAnatel;
    }


    /**
     * Gets the dtInicial value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the idPlano value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return idPlano
     */
    public long getIdPlano() {
        return idPlano;
    }


    /**
     * Sets the idPlano value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param idPlano
     */
    public void setIdPlano(long idPlano) {
        this.idPlano = idPlano;
    }


    /**
     * Gets the inDisponivel value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the valorAssinaturaMensal value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return valorAssinaturaMensal
     */
    public java.lang.Double getValorAssinaturaMensal() {
        return valorAssinaturaMensal;
    }


    /**
     * Sets the valorAssinaturaMensal value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param valorAssinaturaMensal
     */
    public void setValorAssinaturaMensal(java.lang.Double valorAssinaturaMensal) {
        this.valorAssinaturaMensal = valorAssinaturaMensal;
    }


    /**
     * Gets the qtdeMinFranquia value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return qtdeMinFranquia
     */
    public java.lang.Double getQtdeMinFranquia() {
        return qtdeMinFranquia;
    }


    /**
     * Sets the qtdeMinFranquia value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param qtdeMinFranquia
     */
    public void setQtdeMinFranquia(java.lang.Double qtdeMinFranquia) {
        this.qtdeMinFranquia = qtdeMinFranquia;
    }


    /**
     * Gets the indicadorTitularDependente value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return indicadorTitularDependente
     */
    public java.lang.String getIndicadorTitularDependente() {
        return indicadorTitularDependente;
    }


    /**
     * Sets the indicadorTitularDependente value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param indicadorTitularDependente
     */
    public void setIndicadorTitularDependente(java.lang.String indicadorTitularDependente) {
        this.indicadorTitularDependente = indicadorTitularDependente;
    }


    /**
     * Gets the qtdeMaxDependCatalogo value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return qtdeMaxDependCatalogo
     */
    public java.lang.Double getQtdeMaxDependCatalogo() {
        return qtdeMaxDependCatalogo;
    }


    /**
     * Sets the qtdeMaxDependCatalogo value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param qtdeMaxDependCatalogo
     */
    public void setQtdeMaxDependCatalogo(java.lang.Double qtdeMaxDependCatalogo) {
        this.qtdeMaxDependCatalogo = qtdeMaxDependCatalogo;
    }


    /**
     * Gets the cdCodigo value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the listaServico value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @return listaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico[] getListaServico() {
        return listaServico;
    }


    /**
     * Sets the listaServico value for this ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.
     * 
     * @param listaServico
     */
    public void setListaServico(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlanoListaServicoServico[] listaServico) {
        this.listaServico = listaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano)) return false;
        ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano other = (ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.cdAnatel==null && other.getCdAnatel()==null) || 
             (this.cdAnatel!=null &&
              this.cdAnatel.equals(other.getCdAnatel()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            this.idPlano == other.getIdPlano() &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
            ((this.valorAssinaturaMensal==null && other.getValorAssinaturaMensal()==null) || 
             (this.valorAssinaturaMensal!=null &&
              this.valorAssinaturaMensal.equals(other.getValorAssinaturaMensal()))) &&
            ((this.qtdeMinFranquia==null && other.getQtdeMinFranquia()==null) || 
             (this.qtdeMinFranquia!=null &&
              this.qtdeMinFranquia.equals(other.getQtdeMinFranquia()))) &&
            ((this.indicadorTitularDependente==null && other.getIndicadorTitularDependente()==null) || 
             (this.indicadorTitularDependente!=null &&
              this.indicadorTitularDependente.equals(other.getIndicadorTitularDependente()))) &&
            ((this.qtdeMaxDependCatalogo==null && other.getQtdeMaxDependCatalogo()==null) || 
             (this.qtdeMaxDependCatalogo!=null &&
              this.qtdeMaxDependCatalogo.equals(other.getQtdeMaxDependCatalogo()))) &&
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            ((this.listaServico==null && other.getListaServico()==null) || 
             (this.listaServico!=null &&
              java.util.Arrays.equals(this.listaServico, other.getListaServico())));
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
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getCdAnatel() != null) {
            _hashCode += getCdAnatel().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        _hashCode += new Long(getIdPlano()).hashCode();
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        if (getValorAssinaturaMensal() != null) {
            _hashCode += getValorAssinaturaMensal().hashCode();
        }
        if (getQtdeMinFranquia() != null) {
            _hashCode += getQtdeMinFranquia().hashCode();
        }
        if (getIndicadorTitularDependente() != null) {
            _hashCode += getIndicadorTitularDependente().hashCode();
        }
        if (getQtdeMaxDependCatalogo() != null) {
            _hashCode += getQtdeMaxDependCatalogo().hashCode();
        }
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        if (getListaServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServico(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaPlanoPorIdListaPlanoRetornoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>ResultadoBuscarListaPlanoPorId>ListaPlano>RetornoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAnatel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "cdAnatel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorAssinaturaMensal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "valorAssinaturaMensal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdeMinFranquia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtdeMinFranquia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorTitularDependente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "indicadorTitularDependente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdeMaxDependCatalogo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "qtdeMaxDependCatalogo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>>>>ResultadoBuscarListaPlanoPorId>ListaPlano>RetornoPlano>ListaServico>Servico"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Servico"));
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
