/**
 * ResultadoBuscarListaParamDesconto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn;

public class ResultadoBuscarListaParamDesconto  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto[] desconto;

    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas[] listaParcelas;

    private br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento[] listaCondicaoPagamento;

    public ResultadoBuscarListaParamDesconto() {
    }

    public ResultadoBuscarListaParamDesconto(
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto[] desconto,
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas[] listaParcelas,
           br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento[] listaCondicaoPagamento) {
           this.desconto = desconto;
           this.listaParcelas = listaParcelas;
           this.listaCondicaoPagamento = listaCondicaoPagamento;
    }


    /**
     * Gets the desconto value for this ResultadoBuscarListaParamDesconto.
     * 
     * @return desconto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto[] getDesconto() {
        return desconto;
    }


    /**
     * Sets the desconto value for this ResultadoBuscarListaParamDesconto.
     * 
     * @param desconto
     */
    public void setDesconto(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto[] desconto) {
        this.desconto = desconto;
    }

    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto getDesconto(int i) {
        return this.desconto[i];
    }

    public void setDesconto(int i, br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoDesconto _value) {
        this.desconto[i] = _value;
    }


    /**
     * Gets the listaParcelas value for this ResultadoBuscarListaParamDesconto.
     * 
     * @return listaParcelas
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas[] getListaParcelas() {
        return listaParcelas;
    }


    /**
     * Sets the listaParcelas value for this ResultadoBuscarListaParamDesconto.
     * 
     * @param listaParcelas
     */
    public void setListaParcelas(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaParcelasParcelas[] listaParcelas) {
        this.listaParcelas = listaParcelas;
    }


    /**
     * Gets the listaCondicaoPagamento value for this ResultadoBuscarListaParamDesconto.
     * 
     * @return listaCondicaoPagamento
     */
    public br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento[] getListaCondicaoPagamento() {
        return listaCondicaoPagamento;
    }


    /**
     * Sets the listaCondicaoPagamento value for this ResultadoBuscarListaParamDesconto.
     * 
     * @param listaCondicaoPagamento
     */
    public void setListaCondicaoPagamento(br.com.vivo.catalogoPRS.ws.catalogoDesconto.sn.ResultadoBuscarListaParamDescontoListaCondicaoPagamentoCondicaoPagamento[] listaCondicaoPagamento) {
        this.listaCondicaoPagamento = listaCondicaoPagamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarListaParamDesconto)) return false;
        ResultadoBuscarListaParamDesconto other = (ResultadoBuscarListaParamDesconto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.desconto==null && other.getDesconto()==null) || 
             (this.desconto!=null &&
              java.util.Arrays.equals(this.desconto, other.getDesconto()))) &&
            ((this.listaParcelas==null && other.getListaParcelas()==null) || 
             (this.listaParcelas!=null &&
              java.util.Arrays.equals(this.listaParcelas, other.getListaParcelas()))) &&
            ((this.listaCondicaoPagamento==null && other.getListaCondicaoPagamento()==null) || 
             (this.listaCondicaoPagamento!=null &&
              java.util.Arrays.equals(this.listaCondicaoPagamento, other.getListaCondicaoPagamento())));
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
        if (getDesconto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDesconto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDesconto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaParcelas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaParcelas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaParcelas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaCondicaoPagamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCondicaoPagamento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCondicaoPagamento(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarListaParamDesconto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">ResultadoBuscarListaParamDesconto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("desconto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "Desconto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>ResultadoBuscarListaParamDesconto>Desconto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaParcelas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ListaParcelas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>>ResultadoBuscarListaParamDesconto>ListaParcelas>Parcelas"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "Parcelas"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCondicaoPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "ListaCondicaoPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", ">>>ResultadoBuscarListaParamDesconto>ListaCondicaoPagamento>CondicaoPagamento"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoDesconto", "CondicaoPagamento"));
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
