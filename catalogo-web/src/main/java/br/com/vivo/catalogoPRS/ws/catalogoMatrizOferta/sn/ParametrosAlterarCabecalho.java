/**
 * ParametrosAlterarCabecalho.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ParametrosAlterarCabecalho  implements java.io.Serializable {
    private long idMatrizOferta;

    private java.lang.String nmMatrizOferta;

    private java.util.Calendar dtInicial;

    private java.util.Calendar dtFinal;

    private long idTipoMatriz;

    private long[] listaIdPlataforma;

    private long[] listaIdTipoOperacao;

    private long[] listaIdCanal;

    public ParametrosAlterarCabecalho() {
    }

    public ParametrosAlterarCabecalho(
           long idMatrizOferta,
           java.lang.String nmMatrizOferta,
           java.util.Calendar dtInicial,
           java.util.Calendar dtFinal,
           long idTipoMatriz,
           long[] listaIdPlataforma,
           long[] listaIdTipoOperacao,
           long[] listaIdCanal) {
           this.idMatrizOferta = idMatrizOferta;
           this.nmMatrizOferta = nmMatrizOferta;
           this.dtInicial = dtInicial;
           this.dtFinal = dtFinal;
           this.idTipoMatriz = idTipoMatriz;
           this.listaIdPlataforma = listaIdPlataforma;
           this.listaIdTipoOperacao = listaIdTipoOperacao;
           this.listaIdCanal = listaIdCanal;
    }


    /**
     * Gets the idMatrizOferta value for this ParametrosAlterarCabecalho.
     * 
     * @return idMatrizOferta
     */
    public long getIdMatrizOferta() {
        return idMatrizOferta;
    }


    /**
     * Sets the idMatrizOferta value for this ParametrosAlterarCabecalho.
     * 
     * @param idMatrizOferta
     */
    public void setIdMatrizOferta(long idMatrizOferta) {
        this.idMatrizOferta = idMatrizOferta;
    }


    /**
     * Gets the nmMatrizOferta value for this ParametrosAlterarCabecalho.
     * 
     * @return nmMatrizOferta
     */
    public java.lang.String getNmMatrizOferta() {
        return nmMatrizOferta;
    }


    /**
     * Sets the nmMatrizOferta value for this ParametrosAlterarCabecalho.
     * 
     * @param nmMatrizOferta
     */
    public void setNmMatrizOferta(java.lang.String nmMatrizOferta) {
        this.nmMatrizOferta = nmMatrizOferta;
    }


    /**
     * Gets the dtInicial value for this ParametrosAlterarCabecalho.
     * 
     * @return dtInicial
     */
    public java.util.Calendar getDtInicial() {
        return dtInicial;
    }


    /**
     * Sets the dtInicial value for this ParametrosAlterarCabecalho.
     * 
     * @param dtInicial
     */
    public void setDtInicial(java.util.Calendar dtInicial) {
        this.dtInicial = dtInicial;
    }


    /**
     * Gets the dtFinal value for this ParametrosAlterarCabecalho.
     * 
     * @return dtFinal
     */
    public java.util.Calendar getDtFinal() {
        return dtFinal;
    }


    /**
     * Sets the dtFinal value for this ParametrosAlterarCabecalho.
     * 
     * @param dtFinal
     */
    public void setDtFinal(java.util.Calendar dtFinal) {
        this.dtFinal = dtFinal;
    }


    /**
     * Gets the idTipoMatriz value for this ParametrosAlterarCabecalho.
     * 
     * @return idTipoMatriz
     */
    public long getIdTipoMatriz() {
        return idTipoMatriz;
    }


    /**
     * Sets the idTipoMatriz value for this ParametrosAlterarCabecalho.
     * 
     * @param idTipoMatriz
     */
    public void setIdTipoMatriz(long idTipoMatriz) {
        this.idTipoMatriz = idTipoMatriz;
    }


    /**
     * Gets the listaIdPlataforma value for this ParametrosAlterarCabecalho.
     * 
     * @return listaIdPlataforma
     */
    public long[] getListaIdPlataforma() {
        return listaIdPlataforma;
    }


    /**
     * Sets the listaIdPlataforma value for this ParametrosAlterarCabecalho.
     * 
     * @param listaIdPlataforma
     */
    public void setListaIdPlataforma(long[] listaIdPlataforma) {
        this.listaIdPlataforma = listaIdPlataforma;
    }


    /**
     * Gets the listaIdTipoOperacao value for this ParametrosAlterarCabecalho.
     * 
     * @return listaIdTipoOperacao
     */
    public long[] getListaIdTipoOperacao() {
        return listaIdTipoOperacao;
    }


    /**
     * Sets the listaIdTipoOperacao value for this ParametrosAlterarCabecalho.
     * 
     * @param listaIdTipoOperacao
     */
    public void setListaIdTipoOperacao(long[] listaIdTipoOperacao) {
        this.listaIdTipoOperacao = listaIdTipoOperacao;
    }


    /**
     * Gets the listaIdCanal value for this ParametrosAlterarCabecalho.
     * 
     * @return listaIdCanal
     */
    public long[] getListaIdCanal() {
        return listaIdCanal;
    }


    /**
     * Sets the listaIdCanal value for this ParametrosAlterarCabecalho.
     * 
     * @param listaIdCanal
     */
    public void setListaIdCanal(long[] listaIdCanal) {
        this.listaIdCanal = listaIdCanal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarCabecalho)) return false;
        ParametrosAlterarCabecalho other = (ParametrosAlterarCabecalho) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idMatrizOferta == other.getIdMatrizOferta() &&
            ((this.nmMatrizOferta==null && other.getNmMatrizOferta()==null) || 
             (this.nmMatrizOferta!=null &&
              this.nmMatrizOferta.equals(other.getNmMatrizOferta()))) &&
            ((this.dtInicial==null && other.getDtInicial()==null) || 
             (this.dtInicial!=null &&
              this.dtInicial.equals(other.getDtInicial()))) &&
            ((this.dtFinal==null && other.getDtFinal()==null) || 
             (this.dtFinal!=null &&
              this.dtFinal.equals(other.getDtFinal()))) &&
            this.idTipoMatriz == other.getIdTipoMatriz() &&
            ((this.listaIdPlataforma==null && other.getListaIdPlataforma()==null) || 
             (this.listaIdPlataforma!=null &&
              java.util.Arrays.equals(this.listaIdPlataforma, other.getListaIdPlataforma()))) &&
            ((this.listaIdTipoOperacao==null && other.getListaIdTipoOperacao()==null) || 
             (this.listaIdTipoOperacao!=null &&
              java.util.Arrays.equals(this.listaIdTipoOperacao, other.getListaIdTipoOperacao()))) &&
            ((this.listaIdCanal==null && other.getListaIdCanal()==null) || 
             (this.listaIdCanal!=null &&
              java.util.Arrays.equals(this.listaIdCanal, other.getListaIdCanal())));
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
        _hashCode += new Long(getIdMatrizOferta()).hashCode();
        if (getNmMatrizOferta() != null) {
            _hashCode += getNmMatrizOferta().hashCode();
        }
        if (getDtInicial() != null) {
            _hashCode += getDtInicial().hashCode();
        }
        if (getDtFinal() != null) {
            _hashCode += getDtFinal().hashCode();
        }
        _hashCode += new Long(getIdTipoMatriz()).hashCode();
        if (getListaIdPlataforma() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdPlataforma());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdPlataforma(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaIdTipoOperacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdTipoOperacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdTipoOperacao(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaIdCanal() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdCanal());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdCanal(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAlterarCabecalho.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ParametrosAlterarCabecalho"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmMatrizOferta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "nmMatrizOferta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dtFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "dtFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoMatriz");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoMatriz"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaIdPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idPlataforma"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdTipoOperacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaIdTipoOperacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idTipoOperacao"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ListaIdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "idCanal"));
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
