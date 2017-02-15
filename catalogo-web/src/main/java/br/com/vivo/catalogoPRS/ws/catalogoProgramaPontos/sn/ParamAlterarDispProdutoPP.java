/**
 * ParamAlterarDispProdutoPP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ParamAlterarDispProdutoPP  implements java.io.Serializable {
    private java.lang.String[] listaIdProduto;

    private java.lang.String[] listaSgAcao;

    private long idOrgVendas;

    private br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel inDisponivel;

    private java.lang.String nmUsuario;

    public ParamAlterarDispProdutoPP() {
    }

    public ParamAlterarDispProdutoPP(
           java.lang.String[] listaIdProduto,
           java.lang.String[] listaSgAcao,
           long idOrgVendas,
           br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel inDisponivel,
           java.lang.String nmUsuario) {
           this.listaIdProduto = listaIdProduto;
           this.listaSgAcao = listaSgAcao;
           this.idOrgVendas = idOrgVendas;
           this.inDisponivel = inDisponivel;
           this.nmUsuario = nmUsuario;
    }


    /**
     * Gets the listaIdProduto value for this ParamAlterarDispProdutoPP.
     * 
     * @return listaIdProduto
     */
    public java.lang.String[] getListaIdProduto() {
        return listaIdProduto;
    }


    /**
     * Sets the listaIdProduto value for this ParamAlterarDispProdutoPP.
     * 
     * @param listaIdProduto
     */
    public void setListaIdProduto(java.lang.String[] listaIdProduto) {
        this.listaIdProduto = listaIdProduto;
    }


    /**
     * Gets the listaSgAcao value for this ParamAlterarDispProdutoPP.
     * 
     * @return listaSgAcao
     */
    public java.lang.String[] getListaSgAcao() {
        return listaSgAcao;
    }


    /**
     * Sets the listaSgAcao value for this ParamAlterarDispProdutoPP.
     * 
     * @param listaSgAcao
     */
    public void setListaSgAcao(java.lang.String[] listaSgAcao) {
        this.listaSgAcao = listaSgAcao;
    }


    /**
     * Gets the idOrgVendas value for this ParamAlterarDispProdutoPP.
     * 
     * @return idOrgVendas
     */
    public long getIdOrgVendas() {
        return idOrgVendas;
    }


    /**
     * Sets the idOrgVendas value for this ParamAlterarDispProdutoPP.
     * 
     * @param idOrgVendas
     */
    public void setIdOrgVendas(long idOrgVendas) {
        this.idOrgVendas = idOrgVendas;
    }


    /**
     * Gets the inDisponivel value for this ParamAlterarDispProdutoPP.
     * 
     * @return inDisponivel
     */
    public br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ParamAlterarDispProdutoPP.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ParamAlterarDispProdutoPPInDisponivel inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the nmUsuario value for this ParamAlterarDispProdutoPP.
     * 
     * @return nmUsuario
     */
    public java.lang.String getNmUsuario() {
        return nmUsuario;
    }


    /**
     * Sets the nmUsuario value for this ParamAlterarDispProdutoPP.
     * 
     * @param nmUsuario
     */
    public void setNmUsuario(java.lang.String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamAlterarDispProdutoPP)) return false;
        ParamAlterarDispProdutoPP other = (ParamAlterarDispProdutoPP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaIdProduto==null && other.getListaIdProduto()==null) || 
             (this.listaIdProduto!=null &&
              java.util.Arrays.equals(this.listaIdProduto, other.getListaIdProduto()))) &&
            ((this.listaSgAcao==null && other.getListaSgAcao()==null) || 
             (this.listaSgAcao!=null &&
              java.util.Arrays.equals(this.listaSgAcao, other.getListaSgAcao()))) &&
            this.idOrgVendas == other.getIdOrgVendas() &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
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
        if (getListaIdProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdProduto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaSgAcao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSgAcao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSgAcao(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getIdOrgVendas()).hashCode();
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        if (getNmUsuario() != null) {
            _hashCode += getNmUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParamAlterarDispProdutoPP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ParamAlterarDispProdutoPP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaIdProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idProduto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSgAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaSgAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "sgAcao"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">>ParamAlterarDispProdutoPP>inDisponivel"));
        elemField.setNillable(false);
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
