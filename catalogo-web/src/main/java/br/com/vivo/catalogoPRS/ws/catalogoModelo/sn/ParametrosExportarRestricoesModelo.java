/**
 * ParametrosExportarRestricoesModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosExportarRestricoesModelo  implements java.io.Serializable {
    private long[] idModelo;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModeloPaginacaoIn paginacaoIn;

    public ParametrosExportarRestricoesModelo() {
    }

    public ParametrosExportarRestricoesModelo(
           long[] idModelo,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModeloPaginacaoIn paginacaoIn) {
           this.idModelo = idModelo;
           this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idModelo value for this ParametrosExportarRestricoesModelo.
     * 
     * @return idModelo
     */
    public long[] getIdModelo() {
        return idModelo;
    }


    /**
     * Sets the idModelo value for this ParametrosExportarRestricoesModelo.
     * 
     * @param idModelo
     */
    public void setIdModelo(long[] idModelo) {
        this.idModelo = idModelo;
    }

    public long getIdModelo(int i) {
        return this.idModelo[i];
    }

    public void setIdModelo(int i, long _value) {
        this.idModelo[i] = _value;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosExportarRestricoesModelo.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModeloPaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosExportarRestricoesModelo.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ParametrosExportarRestricoesModeloPaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosExportarRestricoesModelo)) return false;
        ParametrosExportarRestricoesModelo other = (ParametrosExportarRestricoesModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idModelo==null && other.getIdModelo()==null) || 
             (this.idModelo!=null &&
              java.util.Arrays.equals(this.idModelo, other.getIdModelo()))) &&
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn())));
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
        if (getIdModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdModelo(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosExportarRestricoesModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ParametrosExportarRestricoesModelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosExportarRestricoesModelo>PaginacaoIn"));
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
