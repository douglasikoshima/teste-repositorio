/**
 * ParametrosBuscarListaFormaCondPagtoPorTipoProduto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoFormaPagamento.sn;

public class ParametrosBuscarListaFormaCondPagtoPorTipoProduto  implements java.io.Serializable {
    private long[] listaTipoProduto;

    public ParametrosBuscarListaFormaCondPagtoPorTipoProduto() {
    }

    public ParametrosBuscarListaFormaCondPagtoPorTipoProduto(
           long[] listaTipoProduto) {
           this.listaTipoProduto = listaTipoProduto;
    }


    /**
     * Gets the listaTipoProduto value for this ParametrosBuscarListaFormaCondPagtoPorTipoProduto.
     * 
     * @return listaTipoProduto
     */
    public long[] getListaTipoProduto() {
        return listaTipoProduto;
    }


    /**
     * Sets the listaTipoProduto value for this ParametrosBuscarListaFormaCondPagtoPorTipoProduto.
     * 
     * @param listaTipoProduto
     */
    public void setListaTipoProduto(long[] listaTipoProduto) {
        this.listaTipoProduto = listaTipoProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaFormaCondPagtoPorTipoProduto)) return false;
        ParametrosBuscarListaFormaCondPagtoPorTipoProduto other = (ParametrosBuscarListaFormaCondPagtoPorTipoProduto) obj;
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
              java.util.Arrays.equals(this.listaTipoProduto, other.getListaTipoProduto())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaFormaCondPagtoPorTipoProduto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", ">ParametrosBuscarListaFormaCondPagtoPorTipoProduto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "ListaTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoFormaPagamento", "idTipoProduto"));
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
