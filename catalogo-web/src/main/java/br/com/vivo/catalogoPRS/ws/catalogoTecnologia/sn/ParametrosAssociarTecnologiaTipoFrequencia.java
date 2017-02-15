/**
 * ParametrosAssociarTecnologiaTipoFrequencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosAssociarTecnologiaTipoFrequencia  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao tecnologiaTipoFrequenciaAssociacao;

    public ParametrosAssociarTecnologiaTipoFrequencia() {
    }

    public ParametrosAssociarTecnologiaTipoFrequencia(
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao tecnologiaTipoFrequenciaAssociacao) {
           this.tecnologiaTipoFrequenciaAssociacao = tecnologiaTipoFrequenciaAssociacao;
    }


    /**
     * Gets the tecnologiaTipoFrequenciaAssociacao value for this ParametrosAssociarTecnologiaTipoFrequencia.
     * 
     * @return tecnologiaTipoFrequenciaAssociacao
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao getTecnologiaTipoFrequenciaAssociacao() {
        return tecnologiaTipoFrequenciaAssociacao;
    }


    /**
     * Sets the tecnologiaTipoFrequenciaAssociacao value for this ParametrosAssociarTecnologiaTipoFrequencia.
     * 
     * @param tecnologiaTipoFrequenciaAssociacao
     */
    public void setTecnologiaTipoFrequenciaAssociacao(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosAssociarTecnologiaTipoFrequenciaTecnologiaTipoFrequenciaAssociacao tecnologiaTipoFrequenciaAssociacao) {
        this.tecnologiaTipoFrequenciaAssociacao = tecnologiaTipoFrequenciaAssociacao;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAssociarTecnologiaTipoFrequencia)) return false;
        ParametrosAssociarTecnologiaTipoFrequencia other = (ParametrosAssociarTecnologiaTipoFrequencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tecnologiaTipoFrequenciaAssociacao==null && other.getTecnologiaTipoFrequenciaAssociacao()==null) || 
             (this.tecnologiaTipoFrequenciaAssociacao!=null &&
              this.tecnologiaTipoFrequenciaAssociacao.equals(other.getTecnologiaTipoFrequenciaAssociacao())));
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
        if (getTecnologiaTipoFrequenciaAssociacao() != null) {
            _hashCode += getTecnologiaTipoFrequenciaAssociacao().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosAssociarTecnologiaTipoFrequencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosAssociarTecnologiaTipoFrequencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tecnologiaTipoFrequenciaAssociacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "TecnologiaTipoFrequenciaAssociacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosAssociarTecnologiaTipoFrequencia>TecnologiaTipoFrequenciaAssociacao"));
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
