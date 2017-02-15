/**
 * ResultadoBuscarListaNmOfertaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn;

public class ResultadoBuscarListaNmOfertaServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] listaNomeOfertaServico;

    public ResultadoBuscarListaNmOfertaServico() {
    }

    public ResultadoBuscarListaNmOfertaServico(
           br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] listaNomeOfertaServico) {
           this.listaNomeOfertaServico = listaNomeOfertaServico;
    }


    /**
     * Gets the listaNomeOfertaServico value for this ResultadoBuscarListaNmOfertaServico.
     * 
     * @return listaNomeOfertaServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] getListaNomeOfertaServico() {
        return listaNomeOfertaServico;
    }


    /**
     * Sets the listaNomeOfertaServico value for this ResultadoBuscarListaNmOfertaServico.
     * 
     * @param listaNomeOfertaServico
     */
    public void setListaNomeOfertaServico(br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] listaNomeOfertaServico) {
        this.listaNomeOfertaServico = listaNomeOfertaServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaNmOfertaServico)) return false;
        ResultadoBuscarListaNmOfertaServico other = (ResultadoBuscarListaNmOfertaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaNomeOfertaServico==null && other.getListaNomeOfertaServico()==null) || 
             (this.listaNomeOfertaServico!=null &&
              java.util.Arrays.equals(this.listaNomeOfertaServico, other.getListaNomeOfertaServico())));
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
        if (getListaNomeOfertaServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaNomeOfertaServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaNomeOfertaServico(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaNmOfertaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">ResultadoBuscarListaNmOfertaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaNomeOfertaServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "ListaNomeOfertaServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", ">>>ResultadoBuscarListaNmOfertaServico>ListaNomeOfertaServico>OfertaServico"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaServico", "OfertaServico"));
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
