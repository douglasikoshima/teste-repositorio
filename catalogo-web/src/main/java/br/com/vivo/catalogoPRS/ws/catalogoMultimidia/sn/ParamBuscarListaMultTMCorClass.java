/**
 * ParamBuscarListaMultTMCorClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn;

public class ParamBuscarListaMultTMCorClass  implements java.io.Serializable {
    private long idGrupoProduto;

    private java.lang.String[] listaSgTipoMultimidia;

    private java.lang.String[] listaSgClassificacao;

    public ParamBuscarListaMultTMCorClass() {
    }

    public ParamBuscarListaMultTMCorClass(
           long idGrupoProduto,
           java.lang.String[] listaSgTipoMultimidia,
           java.lang.String[] listaSgClassificacao) {
           this.idGrupoProduto = idGrupoProduto;
           this.listaSgTipoMultimidia = listaSgTipoMultimidia;
           this.listaSgClassificacao = listaSgClassificacao;
    }


    /**
     * Gets the idGrupoProduto value for this ParamBuscarListaMultTMCorClass.
     * 
     * @return idGrupoProduto
     */
    public long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ParamBuscarListaMultTMCorClass.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the listaSgTipoMultimidia value for this ParamBuscarListaMultTMCorClass.
     * 
     * @return listaSgTipoMultimidia
     */
    public java.lang.String[] getListaSgTipoMultimidia() {
        return listaSgTipoMultimidia;
    }


    /**
     * Sets the listaSgTipoMultimidia value for this ParamBuscarListaMultTMCorClass.
     * 
     * @param listaSgTipoMultimidia
     */
    public void setListaSgTipoMultimidia(java.lang.String[] listaSgTipoMultimidia) {
        this.listaSgTipoMultimidia = listaSgTipoMultimidia;
    }


    /**
     * Gets the listaSgClassificacao value for this ParamBuscarListaMultTMCorClass.
     * 
     * @return listaSgClassificacao
     */
    public java.lang.String[] getListaSgClassificacao() {
        return listaSgClassificacao;
    }


    /**
     * Sets the listaSgClassificacao value for this ParamBuscarListaMultTMCorClass.
     * 
     * @param listaSgClassificacao
     */
    public void setListaSgClassificacao(java.lang.String[] listaSgClassificacao) {
        this.listaSgClassificacao = listaSgClassificacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParamBuscarListaMultTMCorClass)) return false;
        ParamBuscarListaMultTMCorClass other = (ParamBuscarListaMultTMCorClass) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idGrupoProduto == other.getIdGrupoProduto() &&
            ((this.listaSgTipoMultimidia==null && other.getListaSgTipoMultimidia()==null) || 
             (this.listaSgTipoMultimidia!=null &&
              java.util.Arrays.equals(this.listaSgTipoMultimidia, other.getListaSgTipoMultimidia()))) &&
            ((this.listaSgClassificacao==null && other.getListaSgClassificacao()==null) || 
             (this.listaSgClassificacao!=null &&
              java.util.Arrays.equals(this.listaSgClassificacao, other.getListaSgClassificacao())));
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
        _hashCode += new Long(getIdGrupoProduto()).hashCode();
        if (getListaSgTipoMultimidia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSgTipoMultimidia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSgTipoMultimidia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaSgClassificacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSgClassificacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSgClassificacao(), i);
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
        new org.apache.axis.description.TypeDesc(ParamBuscarListaMultTMCorClass.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", ">ParamBuscarListaMultTMCorClass"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSgTipoMultimidia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ListaSgTipoMultimidia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "sgTipoMultimidia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSgClassificacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "ListaSgClassificacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMultimidia", "sgClassificacao"));
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
