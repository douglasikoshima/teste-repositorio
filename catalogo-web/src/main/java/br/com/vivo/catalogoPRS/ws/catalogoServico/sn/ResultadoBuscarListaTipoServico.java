/**
 * ResultadoBuscarListaTipoServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaTipoServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] listaTipoServico;

    public ResultadoBuscarListaTipoServico() {
    }

    public ResultadoBuscarListaTipoServico(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] listaTipoServico) {
           this.listaTipoServico = listaTipoServico;
    }


    /**
     * Gets the listaTipoServico value for this ResultadoBuscarListaTipoServico.
     * 
     * @return listaTipoServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] getListaTipoServico() {
        return listaTipoServico;
    }


    /**
     * Sets the listaTipoServico value for this ResultadoBuscarListaTipoServico.
     * 
     * @param listaTipoServico
     */
    public void setListaTipoServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaTipoServicoListaTipoServicoRetorno[] listaTipoServico) {
        this.listaTipoServico = listaTipoServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaTipoServico)) return false;
        ResultadoBuscarListaTipoServico other = (ResultadoBuscarListaTipoServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaTipoServico==null && other.getListaTipoServico()==null) || 
             (this.listaTipoServico!=null &&
              java.util.Arrays.equals(this.listaTipoServico, other.getListaTipoServico())));
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
        if (getListaTipoServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTipoServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTipoServico(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaTipoServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaTipoServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTipoServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaTipoServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">>>ResultadoBuscarListaTipoServico>ListaTipoServico>Retorno"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "Retorno"));
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
