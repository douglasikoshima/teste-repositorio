/**
 * ResultBuscarConfigProdutoPP.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn;

public class ResultBuscarConfigProdutoPP  implements java.io.Serializable {
    private java.lang.String[] listaSgAcao;

    private long[] listaIdOrgVendas;

    private long[] listaIdCanal;

    public ResultBuscarConfigProdutoPP() {
    }

    public ResultBuscarConfigProdutoPP(
           java.lang.String[] listaSgAcao,
           long[] listaIdOrgVendas,
           long[] listaIdCanal) {
           this.listaSgAcao = listaSgAcao;
           this.listaIdOrgVendas = listaIdOrgVendas;
           this.listaIdCanal = listaIdCanal;
    }


    /**
     * Gets the listaSgAcao value for this ResultBuscarConfigProdutoPP.
     * 
     * @return listaSgAcao
     */
    public java.lang.String[] getListaSgAcao() {
        return listaSgAcao;
    }


    /**
     * Sets the listaSgAcao value for this ResultBuscarConfigProdutoPP.
     * 
     * @param listaSgAcao
     */
    public void setListaSgAcao(java.lang.String[] listaSgAcao) {
        this.listaSgAcao = listaSgAcao;
    }


    /**
     * Gets the listaIdOrgVendas value for this ResultBuscarConfigProdutoPP.
     * 
     * @return listaIdOrgVendas
     */
    public long[] getListaIdOrgVendas() {
        return listaIdOrgVendas;
    }


    /**
     * Sets the listaIdOrgVendas value for this ResultBuscarConfigProdutoPP.
     * 
     * @param listaIdOrgVendas
     */
    public void setListaIdOrgVendas(long[] listaIdOrgVendas) {
        this.listaIdOrgVendas = listaIdOrgVendas;
    }


    /**
     * Gets the listaIdCanal value for this ResultBuscarConfigProdutoPP.
     * 
     * @return listaIdCanal
     */
    public long[] getListaIdCanal() {
        return listaIdCanal;
    }


    /**
     * Sets the listaIdCanal value for this ResultBuscarConfigProdutoPP.
     * 
     * @param listaIdCanal
     */
    public void setListaIdCanal(long[] listaIdCanal) {
        this.listaIdCanal = listaIdCanal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultBuscarConfigProdutoPP)) return false;
        ResultBuscarConfigProdutoPP other = (ResultBuscarConfigProdutoPP) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaSgAcao==null && other.getListaSgAcao()==null) || 
             (this.listaSgAcao!=null &&
              java.util.Arrays.equals(this.listaSgAcao, other.getListaSgAcao()))) &&
            ((this.listaIdOrgVendas==null && other.getListaIdOrgVendas()==null) || 
             (this.listaIdOrgVendas!=null &&
              java.util.Arrays.equals(this.listaIdOrgVendas, other.getListaIdOrgVendas()))) &&
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
        if (getListaIdOrgVendas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdOrgVendas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdOrgVendas(), i);
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
        new org.apache.axis.description.TypeDesc(ResultBuscarConfigProdutoPP.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", ">ResultBuscarConfigProdutoPP"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSgAcao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaSgAcao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "sgAcaoMarketing"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdOrgVendas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaIdOrgVendas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idOrgVendas"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "ListaIdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProgramaPontos", "idCanalAtend"));
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
