/**
 * ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo[] listaModelosPorIdModeloOut;

    public ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl() {
    }

    public ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo[] listaModelosPorIdModeloOut) {
           this.listaModelosPorIdModeloOut = listaModelosPorIdModeloOut;
    }


    /**
     * Gets the listaModelosPorIdModeloOut value for this ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl.
     * 
     * @return listaModelosPorIdModeloOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo[] getListaModelosPorIdModeloOut() {
        return listaModelosPorIdModeloOut;
    }


    /**
     * Sets the listaModelosPorIdModeloOut value for this ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl.
     * 
     * @param listaModelosPorIdModeloOut
     */
    public void setListaModelosPorIdModeloOut(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo[] listaModelosPorIdModeloOut) {
        this.listaModelosPorIdModeloOut = listaModelosPorIdModeloOut;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl)) return false;
        ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl other = (ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaModelosPorIdModeloOut==null && other.getListaModelosPorIdModeloOut()==null) || 
             (this.listaModelosPorIdModeloOut!=null &&
              java.util.Arrays.equals(this.listaModelosPorIdModeloOut, other.getListaModelosPorIdModeloOut())));
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
        if (getListaModelosPorIdModeloOut() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModelosPorIdModeloOut());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModelosPorIdModeloOut(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModelosPorIdModeloOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaModelosPorIdModeloOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ResultadoBuscarModeloPorTecnologiaTpFrequenciaVl>ListaModelosPorIdModeloOut>ModeloPorIdModelo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ModeloPorIdModelo"));
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
