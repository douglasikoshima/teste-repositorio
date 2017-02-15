/**
 * ParamCarregarDispProdutoPP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ParamCarregarDispProdutoPP  implements java.io.Serializable {
    private long idProduto;

    private java.lang.String[] listaOrgVendas;

    private java.lang.String[] listaCanalAtendimento;

    private java.lang.String[] listaAcao;

    private java.lang.String nmUsuario;

    public ParamCarregarDispProdutoPP() {
    }

    public ParamCarregarDispProdutoPP(
           long idProduto,
           java.lang.String[] listaOrgVendas,
           java.lang.String[] listaCanalAtendimento,
           java.lang.String[] listaAcao,
           java.lang.String nmUsuario) {
           this.idProduto = idProduto;
           this.listaOrgVendas = listaOrgVendas;
           this.listaCanalAtendimento = listaCanalAtendimento;
           this.listaAcao = listaAcao;
           this.nmUsuario = nmUsuario;
    }


    /**
     * Gets the idProduto value for this ParamCarregarDispProdutoPP.
     * 
     * @return idProduto
     */
    public long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ParamCarregarDispProdutoPP.
     * 
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }


    /**
     * Gets the listaOrgVendas value for this ParamCarregarDispProdutoPP.
     * 
     * @return listaOrgVendas
     */
    public java.lang.String[] getListaOrgVendas() {
        return listaOrgVendas;
    }


    /**
     * Sets the listaOrgVendas value for this ParamCarregarDispProdutoPP.
     * 
     * @param listaOrgVendas
     */
    public void setListaOrgVendas(java.lang.String[] listaOrgVendas) {
        this.listaOrgVendas = listaOrgVendas;
    }


    /**
     * Gets the listaCanalAtendimento value for this ParamCarregarDispProdutoPP.
     * 
     * @return listaCanalAtendimento
     */
    public java.lang.String[] getListaCanalAtendimento() {
        return listaCanalAtendimento;
    }


    /**
     * Sets the listaCanalAtendimento value for this ParamCarregarDispProdutoPP.
     * 
     * @param listaCanalAtendimento
     */
    public void setListaCanalAtendimento(java.lang.String[] listaCanalAtendimento) {
        this.listaCanalAtendimento = listaCanalAtendimento;
    }


    /**
     * Gets the listaAcao value for this ParamCarregarDispProdutoPP.
     * 
     * @return listaAcao
     */
    public java.lang.String[] getListaAcao() {
        return listaAcao;
    }


    /**
     * Sets the listaAcao value for this ParamCarregarDispProdutoPP.
     * 
     * @param listaAcao
     */
    public void setListaAcao(java.lang.String[] listaAcao) {
        this.listaAcao = listaAcao;
    }


    /**
     * Gets the nmUsuario value for this ParamCarregarDispProdutoPP.
     * 
     * @return nmUsuario
     */
    public java.lang.String getNmUsuario() {
        return nmUsuario;
    }


    /**
     * Sets the nmUsuario value for this ParamCarregarDispProdutoPP.
     * 
     * @param nmUsuario
     */
    public void setNmUsuario(java.lang.String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamCarregarDispProdutoPP)) return false;
        ParamCarregarDispProdutoPP other = (ParamCarregarDispProdutoPP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idProduto == other.getIdProduto() &&
            ((this.listaOrgVendas==null && other.getListaOrgVendas()==null) || 
             (this.listaOrgVendas!=null &&
              java.util.Arrays.equals(this.listaOrgVendas, other.getListaOrgVendas()))) &&
            ((this.listaCanalAtendimento==null && other.getListaCanalAtendimento()==null) || 
             (this.listaCanalAtendimento!=null &&
              java.util.Arrays.equals(this.listaCanalAtendimento, other.getListaCanalAtendimento()))) &&
            ((this.listaAcao==null && other.getListaAcao()==null) || 
             (this.listaAcao!=null &&
              java.util.Arrays.equals(this.listaAcao, other.getListaAcao()))) &&
            ((this.nmUsuario==null && other.getNmUsuario()==null) || 
             (this.nmUsuario!=null &&
              this.nmUsuario.equals(other.getNmUsuario())));
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
        _hashCode += new Long(getIdProduto()).hashCode();
        if (getListaOrgVendas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaOrgVendas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaOrgVendas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaCanalAtendimento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCanalAtendimento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCanalAtendimento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaAcao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaAcao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaAcao(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNmUsuario() != null) {
            _hashCode += getNmUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamCarregarDispProdutoPP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamCarregarDispProdutoPP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCanalAtendimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaCanalAtendimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idCanalAtendimento"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "acao"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "nmUsuario"));
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
