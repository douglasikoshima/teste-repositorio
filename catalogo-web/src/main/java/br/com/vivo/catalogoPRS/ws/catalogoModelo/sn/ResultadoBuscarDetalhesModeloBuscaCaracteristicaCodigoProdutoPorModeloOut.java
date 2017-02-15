/**
 * ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo modelo;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica[] listaCaracteristica;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto;

    public ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut() {
    }

    public ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo modelo,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica[] listaCaracteristica,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto) {
           this.modelo = modelo;
           this.listaCaracteristica = listaCaracteristica;
           this.listaSistemaProduto = listaSistemaProduto;
    }


    /**
     * Gets the modelo value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @return modelo
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo getModelo() {
        return modelo;
    }


    /**
     * Sets the modelo value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @param modelo
     */
    public void setModelo(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOutModelo modelo) {
        this.modelo = modelo;
    }


    /**
     * Gets the listaCaracteristica value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @return listaCaracteristica
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica[] getListaCaracteristica() {
        return listaCaracteristica;
    }


    /**
     * Sets the listaCaracteristica value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @param listaCaracteristica
     */
    public void setListaCaracteristica(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica[] listaCaracteristica) {
        this.listaCaracteristica = listaCaracteristica;
    }


    /**
     * Gets the listaSistemaProduto value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @return listaSistemaProduto
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] getListaSistemaProduto() {
        return listaSistemaProduto;
    }


    /**
     * Sets the listaSistemaProduto value for this ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.
     * 
     * @param listaSistemaProduto
     */
    public void setListaSistemaProduto(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto[] listaSistemaProduto) {
        this.listaSistemaProduto = listaSistemaProduto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut)) return false;
        ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut other = (ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modelo==null && other.getModelo()==null) || 
             (this.modelo!=null &&
              this.modelo.equals(other.getModelo()))) &&
            ((this.listaCaracteristica==null && other.getListaCaracteristica()==null) || 
             (this.listaCaracteristica!=null &&
              java.util.Arrays.equals(this.listaCaracteristica, other.getListaCaracteristica()))) &&
            ((this.listaSistemaProduto==null && other.getListaSistemaProduto()==null) || 
             (this.listaSistemaProduto!=null &&
              java.util.Arrays.equals(this.listaSistemaProduto, other.getListaSistemaProduto())));
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
        if (getModelo() != null) {
            _hashCode += getModelo().hashCode();
        }
        if (getListaCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCaracteristica(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaSistemaProduto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaSistemaProduto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaSistemaProduto(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoBuscarDetalhesModeloBuscaCaracteristicaCodigoProdutoPorModeloOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoBuscarDetalhesModelo>BuscaCaracteristicaCodigoProdutoPorModeloOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Modelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>ResultadoBuscarDetalhesModelo>BuscaCaracteristicaCodigoProdutoPorModeloOut>Modelo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaCaracteristica"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaSistemaProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaSistemaProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">ListaSistemaProduto"));
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
