/**
 * ParametrosBuscarListaFormaCondPagto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class ParametrosBuscarListaFormaCondPagto  implements java.io.Serializable {
    private long[] listaTipoProduto;

    private java.lang.Long idPlataforma;

    private java.lang.Long idCanal;

    public ParametrosBuscarListaFormaCondPagto() {
    }

    public ParametrosBuscarListaFormaCondPagto(
           long[] listaTipoProduto,
           java.lang.Long idPlataforma,
           java.lang.Long idCanal) {
           this.listaTipoProduto = listaTipoProduto;
           this.idPlataforma = idPlataforma;
           this.idCanal = idCanal;
    }


    /**
     * Gets the listaTipoProduto value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @return listaTipoProduto
     */
    public long[] getListaTipoProduto() {
        return listaTipoProduto;
    }


    /**
     * Sets the listaTipoProduto value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @param listaTipoProduto
     */
    public void setListaTipoProduto(long[] listaTipoProduto) {
        this.listaTipoProduto = listaTipoProduto;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @return idPlataforma
     */
    public java.lang.Long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(java.lang.Long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idCanal value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @return idCanal
     */
    public java.lang.Long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ParametrosBuscarListaFormaCondPagto.
     * 
     * @param idCanal
     */
    public void setIdCanal(java.lang.Long idCanal) {
        this.idCanal = idCanal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaFormaCondPagto)) return false;
        ParametrosBuscarListaFormaCondPagto other = (ParametrosBuscarListaFormaCondPagto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaTipoProduto==null && other.getListaTipoProduto()==null) || 
             (this.listaTipoProduto!=null &&
              java.util.Arrays.equals(this.listaTipoProduto, other.getListaTipoProduto()))) &&
            ((this.idPlataforma==null && other.getIdPlataforma()==null) || 
             (this.idPlataforma!=null &&
              this.idPlataforma.equals(other.getIdPlataforma()))) &&
            ((this.idCanal==null && other.getIdCanal()==null) || 
             (this.idCanal!=null &&
              this.idCanal.equals(other.getIdCanal())));
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
        if (getListaTipoProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTipoProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTipoProduto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdPlataforma() != null) {
            _hashCode += getIdPlataforma().hashCode();
        }
        if (getIdCanal() != null) {
            _hashCode += getIdCanal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaFormaCondPagto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">ParametrosBuscarListaFormaCondPagto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "ListaTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "idTipoProduto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "idCanal"));
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
