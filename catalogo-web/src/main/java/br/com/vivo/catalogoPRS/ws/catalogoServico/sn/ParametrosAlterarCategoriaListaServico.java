/**
 * ParametrosAlterarCategoriaListaServico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoServico.sn;

public class ParametrosAlterarCategoriaListaServico  implements java.io.Serializable {
    private long idCategoria;

    private long[] listaIdServico;

    public ParametrosAlterarCategoriaListaServico() {
    }

    public ParametrosAlterarCategoriaListaServico(
           long idCategoria,
           long[] listaIdServico) {
           this.idCategoria = idCategoria;
           this.listaIdServico = listaIdServico;
    }


    /**
     * Gets the idCategoria value for this ParametrosAlterarCategoriaListaServico.
     * 
     * @return idCategoria
     */
    public long getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this ParametrosAlterarCategoriaListaServico.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the listaIdServico value for this ParametrosAlterarCategoriaListaServico.
     * 
     * @return listaIdServico
     */
    public long[] getListaIdServico() {
        return listaIdServico;
    }


    /**
     * Sets the listaIdServico value for this ParametrosAlterarCategoriaListaServico.
     * 
     * @param listaIdServico
     */
    public void setListaIdServico(long[] listaIdServico) {
        this.listaIdServico = listaIdServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosAlterarCategoriaListaServico)) return false;
        ParametrosAlterarCategoriaListaServico other = (ParametrosAlterarCategoriaListaServico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.idCategoria == other.getIdCategoria() &&
            ((this.listaIdServico==null && other.getListaIdServico()==null) || 
             (this.listaIdServico!=null &&
              java.util.Arrays.equals(this.listaIdServico, other.getListaIdServico())));
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
        _hashCode += new Long(getIdCategoria()).hashCode();
        if (getListaIdServico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdServico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdServico(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosAlterarCategoriaListaServico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", ">ParametrosAlterarCategoriaListaServico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "ListaIdServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoServico", "idServico"));
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
