/**
 * ContratoPJ.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.sap.MC.OrdemVenda;

public class ContratoPJ  implements java.io.Serializable {
    /* Data de inicio de vigencia */
    private java.util.Calendar dataInicioVigencia;

    /* Data de fim de vigencia */
    private java.util.Calendar dataFimVigencia;

    public ContratoPJ() {
    }

    public ContratoPJ(
           java.util.Calendar dataInicioVigencia,
           java.util.Calendar dataFimVigencia) {
           this.dataInicioVigencia = dataInicioVigencia;
           this.dataFimVigencia = dataFimVigencia;
    }


    /**
     * Gets the dataInicioVigencia value for this ContratoPJ.
     * 
     * @return dataInicioVigencia   * Data de inicio de vigencia
     */
    public java.util.Calendar getDataInicioVigencia() {
        return dataInicioVigencia;
    }


    /**
     * Sets the dataInicioVigencia value for this ContratoPJ.
     * 
     * @param dataInicioVigencia   * Data de inicio de vigencia
     */
    public void setDataInicioVigencia(java.util.Calendar dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }


    /**
     * Gets the dataFimVigencia value for this ContratoPJ.
     * 
     * @return dataFimVigencia   * Data de fim de vigencia
     */
    public java.util.Calendar getDataFimVigencia() {
        return dataFimVigencia;
    }


    /**
     * Sets the dataFimVigencia value for this ContratoPJ.
     * 
     * @param dataFimVigencia   * Data de fim de vigencia
     */
    public void setDataFimVigencia(java.util.Calendar dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ContratoPJ)) return false;
        ContratoPJ other = (ContratoPJ) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataInicioVigencia==null && other.getDataInicioVigencia()==null) || 
             (this.dataInicioVigencia!=null &&
              this.dataInicioVigencia.equals(other.getDataInicioVigencia()))) &&
            ((this.dataFimVigencia==null && other.getDataFimVigencia()==null) || 
             (this.dataFimVigencia!=null &&
              this.dataFimVigencia.equals(other.getDataFimVigencia())));
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
        if (getDataInicioVigencia() != null) {
            _hashCode += getDataInicioVigencia().hashCode();
        }
        if (getDataFimVigencia() != null) {
            _hashCode += getDataFimVigencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ContratoPJ.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "ContratoPJ"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInicioVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataInicioVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFimVigencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/OrdemVenda", "dataFimVigencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
