/**
 * ResultadoExportarMatrizOfertaArquivoItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn;

public class ResultadoExportarMatrizOfertaArquivoItem  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem[] exportarMatrizOfertaArquivoItem;

    public ResultadoExportarMatrizOfertaArquivoItem() {
    }

    public ResultadoExportarMatrizOfertaArquivoItem(
           br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem[] exportarMatrizOfertaArquivoItem) {
           this.exportarMatrizOfertaArquivoItem = exportarMatrizOfertaArquivoItem;
    }


    /**
     * Gets the exportarMatrizOfertaArquivoItem value for this ResultadoExportarMatrizOfertaArquivoItem.
     * 
     * @return exportarMatrizOfertaArquivoItem
     */
    public br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem[] getExportarMatrizOfertaArquivoItem() {
        return exportarMatrizOfertaArquivoItem;
    }


    /**
     * Sets the exportarMatrizOfertaArquivoItem value for this ResultadoExportarMatrizOfertaArquivoItem.
     * 
     * @param exportarMatrizOfertaArquivoItem
     */
    public void setExportarMatrizOfertaArquivoItem(br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.ResultadoExportarMatrizOfertaArquivoItemExportarMatrizOfertaArquivoItemMatrizOfertaArquivoItem[] exportarMatrizOfertaArquivoItem) {
        this.exportarMatrizOfertaArquivoItem = exportarMatrizOfertaArquivoItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarMatrizOfertaArquivoItem)) return false;
        ResultadoExportarMatrizOfertaArquivoItem other = (ResultadoExportarMatrizOfertaArquivoItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.exportarMatrizOfertaArquivoItem==null && other.getExportarMatrizOfertaArquivoItem()==null) || 
             (this.exportarMatrizOfertaArquivoItem!=null &&
              java.util.Arrays.equals(this.exportarMatrizOfertaArquivoItem, other.getExportarMatrizOfertaArquivoItem())));
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
        if (getExportarMatrizOfertaArquivoItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExportarMatrizOfertaArquivoItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExportarMatrizOfertaArquivoItem(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoExportarMatrizOfertaArquivoItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">ResultadoExportarMatrizOfertaArquivoItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exportarMatrizOfertaArquivoItem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "ExportarMatrizOfertaArquivoItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", ">>>ResultadoExportarMatrizOfertaArquivoItem>ExportarMatrizOfertaArquivoItem>MatrizOfertaArquivoItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoMatrizOferta", "MatrizOfertaArquivoItem"));
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
