/**
 * ParametrosAlterarParamDesconto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ParametrosAlterarParamDesconto  implements java.io.Serializable {
    private long idFormaPagamento;

    private long idCanal;

    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto[] descontoCondPagto;

    public ParametrosAlterarParamDesconto() {
    }

    public ParametrosAlterarParamDesconto(
           long idFormaPagamento,
           long idCanal,
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto[] descontoCondPagto) {
           this.idFormaPagamento = idFormaPagamento;
           this.idCanal = idCanal;
           this.descontoCondPagto = descontoCondPagto;
    }


    /**
     * Gets the idFormaPagamento value for this ParametrosAlterarParamDesconto.
     * 
     * @return idFormaPagamento
     */
    public long getIdFormaPagamento() {
        return idFormaPagamento;
    }


    /**
     * Sets the idFormaPagamento value for this ParametrosAlterarParamDesconto.
     * 
     * @param idFormaPagamento
     */
    public void setIdFormaPagamento(long idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }


    /**
     * Gets the idCanal value for this ParametrosAlterarParamDesconto.
     * 
     * @return idCanal
     */
    public long getIdCanal() {
        return idCanal;
    }


    /**
     * Sets the idCanal value for this ParametrosAlterarParamDesconto.
     * 
     * @param idCanal
     */
    public void setIdCanal(long idCanal) {
        this.idCanal = idCanal;
    }


    /**
     * Gets the descontoCondPagto value for this ParametrosAlterarParamDesconto.
     * 
     * @return descontoCondPagto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto[] getDescontoCondPagto() {
        return descontoCondPagto;
    }


    /**
     * Sets the descontoCondPagto value for this ParametrosAlterarParamDesconto.
     * 
     * @param descontoCondPagto
     */
    public void setDescontoCondPagto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto[] descontoCondPagto) {
        this.descontoCondPagto = descontoCondPagto;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto getDescontoCondPagto(int i) {
        return this.descontoCondPagto[i];
    }

    public void setDescontoCondPagto(int i, br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ParametrosAlterarParamDescontoDescontoCondPagto _value) {
        this.descontoCondPagto[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarParamDesconto)) return false;
        ParametrosAlterarParamDesconto other = (ParametrosAlterarParamDesconto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idFormaPagamento == other.getIdFormaPagamento() &&
            this.idCanal == other.getIdCanal() &&
            ((this.descontoCondPagto==null && other.getDescontoCondPagto()==null) || 
             (this.descontoCondPagto!=null &&
              java.util.Arrays.equals(this.descontoCondPagto, other.getDescontoCondPagto())));
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
        _hashCode += new Long(getIdFormaPagamento()).hashCode();
        _hashCode += new Long(getIdCanal()).hashCode();
        if (getDescontoCondPagto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDescontoCondPagto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDescontoCondPagto(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAlterarParamDesconto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ParametrosAlterarParamDesconto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFormaPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idFormaPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCanal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IdCanal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descontoCondPagto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DescontoCondPagto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>ParametrosAlterarParamDesconto>DescontoCondPagto"));
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
