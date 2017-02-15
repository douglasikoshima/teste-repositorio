/**
 * ParametrosDesassociarTecnologiaTpFrequenciaVl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn;

public class ParametrosDesassociarTecnologiaTpFrequenciaVl  implements java.io.Serializable {
    private long idTecnologiaTpFrequenciaVl;

    private br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[] parametrosDispGrupoProduto;

    private java.lang.String justificativa;

    public ParametrosDesassociarTecnologiaTpFrequenciaVl() {
    }

    public ParametrosDesassociarTecnologiaTpFrequenciaVl(
           long idTecnologiaTpFrequenciaVl,
           br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[] parametrosDispGrupoProduto,
           java.lang.String justificativa) {
           this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
           this.parametrosDispGrupoProduto = parametrosDispGrupoProduto;
           this.justificativa = justificativa;
    }


    /**
     * Gets the idTecnologiaTpFrequenciaVl value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @return idTecnologiaTpFrequenciaVl
     */
    public long getIdTecnologiaTpFrequenciaVl() {
        return idTecnologiaTpFrequenciaVl;
    }


    /**
     * Sets the idTecnologiaTpFrequenciaVl value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @param idTecnologiaTpFrequenciaVl
     */
    public void setIdTecnologiaTpFrequenciaVl(long idTecnologiaTpFrequenciaVl) {
        this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
    }


    /**
     * Gets the parametrosDispGrupoProduto value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @return parametrosDispGrupoProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[] getParametrosDispGrupoProduto() {
        return parametrosDispGrupoProduto;
    }


    /**
     * Sets the parametrosDispGrupoProduto value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @param parametrosDispGrupoProduto
     */
    public void setParametrosDispGrupoProduto(br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto[] parametrosDispGrupoProduto) {
        this.parametrosDispGrupoProduto = parametrosDispGrupoProduto;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto getParametrosDispGrupoProduto(int i) {
        return this.parametrosDispGrupoProduto[i];
    }

    public void setParametrosDispGrupoProduto(int i, br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ParametrosDesassociarTecnologiaTpFrequenciaVlParametrosDispGrupoProduto _value) {
        this.parametrosDispGrupoProduto[i] = _value;
    }


    /**
     * Gets the justificativa value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @return justificativa
     */
    public java.lang.String getJustificativa() {
        return justificativa;
    }


    /**
     * Sets the justificativa value for this ParametrosDesassociarTecnologiaTpFrequenciaVl.
     * 
     * @param justificativa
     */
    public void setJustificativa(java.lang.String justificativa) {
        this.justificativa = justificativa;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosDesassociarTecnologiaTpFrequenciaVl)) return false;
        ParametrosDesassociarTecnologiaTpFrequenciaVl other = (ParametrosDesassociarTecnologiaTpFrequenciaVl) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idTecnologiaTpFrequenciaVl == other.getIdTecnologiaTpFrequenciaVl() &&
            ((this.parametrosDispGrupoProduto==null && other.getParametrosDispGrupoProduto()==null) || 
             (this.parametrosDispGrupoProduto!=null &&
              java.util.Arrays.equals(this.parametrosDispGrupoProduto, other.getParametrosDispGrupoProduto()))) &&
            ((this.justificativa==null && other.getJustificativa()==null) || 
             (this.justificativa!=null &&
              this.justificativa.equals(other.getJustificativa())));
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
        _hashCode += new Long(getIdTecnologiaTpFrequenciaVl()).hashCode();
        if (getParametrosDispGrupoProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getParametrosDispGrupoProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getParametrosDispGrupoProduto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getJustificativa() != null) {
            _hashCode += getJustificativa().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosDesassociarTecnologiaTpFrequenciaVl.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">ParametrosDesassociarTecnologiaTpFrequenciaVl"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologiaTpFrequenciaVl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "idTecnologiaTpFrequenciaVl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parametrosDispGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "ParametrosDispGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", ">>ParametrosDesassociarTecnologiaTpFrequenciaVl>ParametrosDispGrupoProduto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificativa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoTecnologia", "justificativa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
