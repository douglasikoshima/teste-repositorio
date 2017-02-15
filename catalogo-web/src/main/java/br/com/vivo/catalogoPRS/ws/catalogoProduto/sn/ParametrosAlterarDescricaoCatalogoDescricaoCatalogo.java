/**
 * ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoProduto.sn;

public class ParametrosAlterarDescricaoCatalogoDescricaoCatalogo  implements java.io.Serializable {
    private long idProduto;

    private java.lang.String nmProduto;

    private java.lang.Long idTipoProduto;

    private java.lang.String dsNota;

    private java.lang.Long idCor;

    public ParametrosAlterarDescricaoCatalogoDescricaoCatalogo() {
    }

    public ParametrosAlterarDescricaoCatalogoDescricaoCatalogo(
           long idProduto,
           java.lang.String nmProduto,
           java.lang.Long idTipoProduto,
           java.lang.String dsNota,
           java.lang.Long idCor) {
           this.idProduto = idProduto;
           this.nmProduto = nmProduto;
           this.idTipoProduto = idTipoProduto;
           this.dsNota = dsNota;
           this.idCor = idCor;
    }


    /**
     * Gets the idProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @return idProduto
     */
    public long getIdProduto() {
        return idProduto;
    }


    /**
     * Sets the idProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @param idProduto
     */
    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }


    /**
     * Gets the nmProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @return nmProduto
     */
    public java.lang.String getNmProduto() {
        return nmProduto;
    }


    /**
     * Sets the nmProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @param nmProduto
     */
    public void setNmProduto(java.lang.String nmProduto) {
        this.nmProduto = nmProduto;
    }


    /**
     * Gets the idTipoProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @return idTipoProduto
     */
    public java.lang.Long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(java.lang.Long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the dsNota value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @return dsNota
     */
    public java.lang.String getDsNota() {
        return dsNota;
    }


    /**
     * Sets the dsNota value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @param dsNota
     */
    public void setDsNota(java.lang.String dsNota) {
        this.dsNota = dsNota;
    }


    /**
     * Gets the idCor value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @return idCor
     */
    public java.lang.Long getIdCor() {
        return idCor;
    }


    /**
     * Sets the idCor value for this ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.
     * 
     * @param idCor
     */
    public void setIdCor(java.lang.Long idCor) {
        this.idCor = idCor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarDescricaoCatalogoDescricaoCatalogo)) return false;
        ParametrosAlterarDescricaoCatalogoDescricaoCatalogo other = (ParametrosAlterarDescricaoCatalogoDescricaoCatalogo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idProduto == other.getIdProduto() &&
            ((this.nmProduto==null && other.getNmProduto()==null) || 
             (this.nmProduto!=null &&
              this.nmProduto.equals(other.getNmProduto()))) &&
            ((this.idTipoProduto==null && other.getIdTipoProduto()==null) || 
             (this.idTipoProduto!=null &&
              this.idTipoProduto.equals(other.getIdTipoProduto()))) &&
            ((this.dsNota==null && other.getDsNota()==null) || 
             (this.dsNota!=null &&
              this.dsNota.equals(other.getDsNota()))) &&
            ((this.idCor==null && other.getIdCor()==null) || 
             (this.idCor!=null &&
              this.idCor.equals(other.getIdCor())));
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
        if (getNmProduto() != null) {
            _hashCode += getNmProduto().hashCode();
        }
        if (getIdTipoProduto() != null) {
            _hashCode += getIdTipoProduto().hashCode();
        }
        if (getDsNota() != null) {
            _hashCode += getDsNota().hashCode();
        }
        if (getIdCor() != null) {
            _hashCode += getIdCor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAlterarDescricaoCatalogoDescricaoCatalogo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", ">>ParametrosAlterarDescricaoCatalogo>DescricaoCatalogo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "nmProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsNota");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "dsNota"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCor");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoProduto", "idCor"));
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
