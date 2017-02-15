/**
 * ParametrosDesassociarListaTecnologiaTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosDesassociarListaTecnologiaTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[] listaTecnologiaTipoFrequenciaDesassociacao;

    public ParametrosDesassociarListaTecnologiaTipoFrequencia() {
    }

    public ParametrosDesassociarListaTecnologiaTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[] listaTecnologiaTipoFrequenciaDesassociacao) {
           this.listaTecnologiaTipoFrequenciaDesassociacao = listaTecnologiaTipoFrequenciaDesassociacao;
    }


    /**
     * Gets the listaTecnologiaTipoFrequenciaDesassociacao value for this ParametrosDesassociarListaTecnologiaTipoFrequencia.
     * 
     * @return listaTecnologiaTipoFrequenciaDesassociacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[] getListaTecnologiaTipoFrequenciaDesassociacao() {
        return listaTecnologiaTipoFrequenciaDesassociacao;
    }


    /**
     * Sets the listaTecnologiaTipoFrequenciaDesassociacao value for this ParametrosDesassociarListaTecnologiaTipoFrequencia.
     * 
     * @param listaTecnologiaTipoFrequenciaDesassociacao
     */
    public void setListaTecnologiaTipoFrequenciaDesassociacao(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarListaTecnologiaTipoFrequenciaListaTecnologiaTipoFrequenciaDesassociacaoTecnologiaTipoFrequenciaDesassociacao[] listaTecnologiaTipoFrequenciaDesassociacao) {
        this.listaTecnologiaTipoFrequenciaDesassociacao = listaTecnologiaTipoFrequenciaDesassociacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesassociarListaTecnologiaTipoFrequencia)) return false;
        ParametrosDesassociarListaTecnologiaTipoFrequencia other = (ParametrosDesassociarListaTecnologiaTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.listaTecnologiaTipoFrequenciaDesassociacao==null && other.getListaTecnologiaTipoFrequenciaDesassociacao()==null) || 
             (this.listaTecnologiaTipoFrequenciaDesassociacao!=null &&
              java.util.Arrays.equals(this.listaTecnologiaTipoFrequenciaDesassociacao, other.getListaTecnologiaTipoFrequenciaDesassociacao())));
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
        if (getListaTecnologiaTipoFrequenciaDesassociacao() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTecnologiaTipoFrequenciaDesassociacao());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTecnologiaTipoFrequenciaDesassociacao(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosDesassociarListaTecnologiaTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosDesassociarListaTecnologiaTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTecnologiaTipoFrequenciaDesassociacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ListaTecnologiaTipoFrequenciaDesassociacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>>ParametrosDesassociarListaTecnologiaTipoFrequencia>ListaTecnologiaTipoFrequenciaDesassociacao>TecnologiaTipoFrequenciaDesassociacao"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "TecnologiaTipoFrequenciaDesassociacao"));
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
