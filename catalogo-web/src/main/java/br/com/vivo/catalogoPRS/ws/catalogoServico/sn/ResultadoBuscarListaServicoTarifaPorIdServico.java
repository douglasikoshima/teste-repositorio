/**
 * ResultadoBuscarListaServicoTarifaPorIdServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ResultadoBuscarListaServicoTarifaPorIdServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa;

    public ResultadoBuscarListaServicoTarifaPorIdServico() {
    }

    public ResultadoBuscarListaServicoTarifaPorIdServico(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
           this.listaServicoTarifa = listaServicoTarifa;
    }


    /**
     * Gets the listaServicoTarifa value for this ResultadoBuscarListaServicoTarifaPorIdServico.
     * 
     * @return listaServicoTarifa
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] getListaServicoTarifa() {
        return listaServicoTarifa;
    }


    /**
     * Sets the listaServicoTarifa value for this ResultadoBuscarListaServicoTarifaPorIdServico.
     * 
     * @param listaServicoTarifa
     */
    public void setListaServicoTarifa(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
        this.listaServicoTarifa = listaServicoTarifa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaServicoTarifaPorIdServico)) return false;
        ResultadoBuscarListaServicoTarifaPorIdServico other = (ResultadoBuscarListaServicoTarifaPorIdServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaServicoTarifa==null && other.getListaServicoTarifa()==null) || 
             (this.listaServicoTarifa!=null &&
              java.util.Arrays.equals(this.listaServicoTarifa, other.getListaServicoTarifa())));
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
        if (getListaServicoTarifa() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaServicoTarifa());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaServicoTarifa(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaServicoTarifaPorIdServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaServicoTarifaPorIdServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaServicoTarifa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaServicoTarifa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ListaServicoTarifa"));
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
