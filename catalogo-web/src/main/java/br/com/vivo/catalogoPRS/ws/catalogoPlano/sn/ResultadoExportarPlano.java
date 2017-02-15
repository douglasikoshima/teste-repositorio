/**
 * ResultadoExportarPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ResultadoExportarPlano  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano[] plano;

    public ResultadoExportarPlano() {
    }

    public ResultadoExportarPlano(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano[] plano) {
           this.paginacaoOut = paginacaoOut;
           this.plano = plano;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoExportarPlano.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoExportarPlano.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the plano value for this ResultadoExportarPlano.
     * 
     * @return plano
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano[] getPlano() {
        return plano;
    }


    /**
     * Sets the plano value for this ResultadoExportarPlano.
     * 
     * @param plano
     */
    public void setPlano(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano[] plano) {
        this.plano = plano;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano getPlano(int i) {
        return this.plano[i];
    }

    public void setPlano(int i, br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoExportarPlanoPlano _value) {
        this.plano[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarPlano)) return false;
        ResultadoExportarPlano other = (ResultadoExportarPlano) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoOut==null && other.getPaginacaoOut()==null) || 
             (this.paginacaoOut!=null &&
              this.paginacaoOut.equals(other.getPaginacaoOut()))) &&
            ((this.plano==null && other.getPlano()==null) || 
             (this.plano!=null &&
              java.util.Arrays.equals(this.plano, other.getPlano())));
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
        if (getPaginacaoOut() != null) {
            _hashCode += getPaginacaoOut().hashCode();
        }
        if (getPlano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPlano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPlano(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoExportarPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ResultadoExportarPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ResultadoExportarPlano>PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "Plano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">>ResultadoExportarPlano>Plano"));
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
