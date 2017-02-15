/**
 * ParametrosModeloTecnologia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosModeloTecnologia  implements java.io.Serializable {
    private long idTecnologia;

    private long[] idTecnologiaTpFrequenciaVl;

    public ParametrosModeloTecnologia() {
    }

    public ParametrosModeloTecnologia(
           long idTecnologia,
           long[] idTecnologiaTpFrequenciaVl) {
           this.idTecnologia = idTecnologia;
           this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the idTecnologia value for this ParametrosModeloTecnologia.
     * 
     * @return idTecnologia
     */
    public long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosModeloTecnologia.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idTecnologiaTpFrequenciaVl value for this ParametrosModeloTecnologia.
     * 
     * @return idTecnologiaTpFrequenciaVl
     */
    public long[] getIdTecnologiaTpFrequenciaVl() {
        return idTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the idTecnologiaTpFrequenciaVl value for this ParametrosModeloTecnologia.
     * 
     * @param idTecnologiaTpFrequenciaVl
     */
    public void setIdTecnologiaTpFrequenciaVl(long[] idTecnologiaTpFrequenciaVl) {
        this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }

    public long getIdTecnologiaTpFrequenciaVl(int i) {
        return this.idTecnologiaTpFrequenciaVl[i];
    }

    public void setIdTecnologiaTpFrequenciaVl(int i, long _value) {
        this.idTecnologiaTpFrequenciaVl[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosModeloTecnologia)) return false;
        ParametrosModeloTecnologia other = (ParametrosModeloTecnologia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologia == other.getIdTecnologia() &&
            ((this.idTecnologiaTpFrequenciaVl==null && other.getIdTecnologiaTpFrequenciaVl()==null) || 
             (this.idTecnologiaTpFrequenciaVl!=null &&
              java.util.Arrays.equals(this.idTecnologiaTpFrequenciaVl, other.getIdTecnologiaTpFrequenciaVl())));
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
        _hashCode += new Long(getIdTecnologia()).hashCode();
        if (getIdTecnologiaTpFrequenciaVl() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdTecnologiaTpFrequenciaVl());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdTecnologiaTpFrequenciaVl(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosModeloTecnologia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosModelo>Tecnologia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologiaTpFrequenciaVl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
