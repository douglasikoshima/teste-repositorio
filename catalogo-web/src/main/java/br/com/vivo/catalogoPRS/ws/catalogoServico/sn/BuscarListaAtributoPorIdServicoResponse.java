/**
 * BuscarListaAtributoPorIdServicoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class BuscarListaAtributoPorIdServicoResponse  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo[] resultadoBuscarListaAtributoPorIdServico;

    public BuscarListaAtributoPorIdServicoResponse() {
    }

    public BuscarListaAtributoPorIdServicoResponse(
           br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo[] resultadoBuscarListaAtributoPorIdServico) {
           this.resultadoBuscarListaAtributoPorIdServico = resultadoBuscarListaAtributoPorIdServico;
    }


    /**
     * Gets the resultadoBuscarListaAtributoPorIdServico value for this BuscarListaAtributoPorIdServicoResponse.
     * 
     * @return resultadoBuscarListaAtributoPorIdServico
     */
    public br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo[] getResultadoBuscarListaAtributoPorIdServico() {
        return resultadoBuscarListaAtributoPorIdServico;
    }


    /**
     * Sets the resultadoBuscarListaAtributoPorIdServico value for this BuscarListaAtributoPorIdServicoResponse.
     * 
     * @param resultadoBuscarListaAtributoPorIdServico
     */
    public void setResultadoBuscarListaAtributoPorIdServico(br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo[] resultadoBuscarListaAtributoPorIdServico) {
        this.resultadoBuscarListaAtributoPorIdServico = resultadoBuscarListaAtributoPorIdServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BuscarListaAtributoPorIdServicoResponse)) return false;
        BuscarListaAtributoPorIdServicoResponse other = (BuscarListaAtributoPorIdServicoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.resultadoBuscarListaAtributoPorIdServico==null && other.getResultadoBuscarListaAtributoPorIdServico()==null) || 
             (this.resultadoBuscarListaAtributoPorIdServico!=null &&
              java.util.Arrays.equals(this.resultadoBuscarListaAtributoPorIdServico, other.getResultadoBuscarListaAtributoPorIdServico())));
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
        if (getResultadoBuscarListaAtributoPorIdServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResultadoBuscarListaAtributoPorIdServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResultadoBuscarListaAtributoPorIdServico(), i);
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
        new org.apache.axis.description.TypeDesc(BuscarListaAtributoPorIdServicoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">buscarListaAtributoPorIdServicoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultadoBuscarListaAtributoPorIdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ResultadoBuscarListaAtributoPorIdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ResultadoBuscarListaAtributoPorIdServico"));
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
