/**
 * ParametrosBuscarListaRestricoesPorServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosBuscarListaRestricoesPorServico  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn;

    private long[] idServico;

    private long idPlataforma;

    public ParametrosBuscarListaRestricoesPorServico() {
    }

    public ParametrosBuscarListaRestricoesPorServico(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn,
           long[] idServico,
           long idPlataforma) {
           this.paginacaoIn = paginacaoIn;
           this.idServico = idServico;
           this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idServico value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @return idServico
     */
    public long[] getIdServico() {
        return idServico;
    }


    /**
     * Sets the idServico value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @param idServico
     */
    public void setIdServico(long[] idServico) {
        this.idServico = idServico;
    }

    public long getIdServico(int i) {
        return this.idServico[i];
    }

    public void setIdServico(int i, long _value) {
        this.idServico[i] = _value;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaRestricoesPorServico.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaRestricoesPorServico)) return false;
        ParametrosBuscarListaRestricoesPorServico other = (ParametrosBuscarListaRestricoesPorServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.paginacaoIn==null && other.getPaginacaoIn()==null) || 
             (this.paginacaoIn!=null &&
              this.paginacaoIn.equals(other.getPaginacaoIn()))) &&
            ((this.idServico==null && other.getIdServico()==null) || 
             (this.idServico!=null &&
              java.util.Arrays.equals(this.idServico, other.getIdServico()))) &&
            this.idPlataforma == other.getIdPlataforma();
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
        if (getPaginacaoIn() != null) {
            _hashCode += getPaginacaoIn().hashCode();
        }
        if (getIdServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdServico(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getIdPlataforma()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaRestricoesPorServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosBuscarListaRestricoesPorServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
