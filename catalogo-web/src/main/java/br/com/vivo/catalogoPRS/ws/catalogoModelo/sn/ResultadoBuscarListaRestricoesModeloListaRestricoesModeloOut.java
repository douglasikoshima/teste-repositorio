/**
 * ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] listaModelo;

    public ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut() {
    }

    public ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] listaModelo) {
           this.paginacaoOut = paginacaoOut;
           this.listaModelo = listaModelo;
    }


    /**
     * Gets the paginacaoOut value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.
     * 
     * @return paginacaoOut
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut getPaginacaoOut() {
        return paginacaoOut;
    }


    /**
     * Sets the paginacaoOut value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.
     * 
     * @param paginacaoOut
     */
    public void setPaginacaoOut(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoOut paginacaoOut) {
        this.paginacaoOut = paginacaoOut;
    }


    /**
     * Gets the listaModelo value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.
     * 
     * @return listaModelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] getListaModelo() {
        return listaModelo;
    }


    /**
     * Sets the listaModelo value for this ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.
     * 
     * @param listaModelo
     */
    public void setListaModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] listaModelo) {
        this.listaModelo = listaModelo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut)) return false;
        ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut other = (ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut) obj;
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
            ((this.listaModelo==null && other.getListaModelo()==null) || 
             (this.listaModelo!=null &&
              java.util.Arrays.equals(this.listaModelo, other.getListaModelo())));
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
        if (getListaModelo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaModelo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaModelo(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoBuscarListaRestricoesModelo>ListaRestricoesModeloOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "PaginacaoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">PaginacaoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoBuscarListaRestricoesModelo>ListaRestricoesModeloOut>ListaModelo>Modelo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Modelo"));
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
