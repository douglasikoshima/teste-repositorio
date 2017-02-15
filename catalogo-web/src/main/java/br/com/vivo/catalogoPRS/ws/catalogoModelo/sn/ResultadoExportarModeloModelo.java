/**
 * ResultadoExportarModeloModelo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ResultadoExportarModeloModelo  implements java.io.Serializable {
    private java.lang.String nmGrupoProduto;

    private java.lang.String tipoProduto;

    private java.lang.String fabricante;

    private java.lang.String inDisponivel;

    private java.lang.String inFimVida;

    private java.lang.Long idGrupoProduto;

    private java.lang.Long qtdImagensAssociadas;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL[] listaTecnologiaTpFrequenciaVL;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaProdutosProduto[] listaProdutos;

    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaCaracteristicasCaracteristica[] listaCaracteristicas;

    public ResultadoExportarModeloModelo() {
    }

    public ResultadoExportarModeloModelo(
           java.lang.String nmGrupoProduto,
           java.lang.String tipoProduto,
           java.lang.String fabricante,
           java.lang.String inDisponivel,
           java.lang.String inFimVida,
           java.lang.Long idGrupoProduto,
           java.lang.Long qtdImagensAssociadas,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL[] listaTecnologiaTpFrequenciaVL,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaProdutosProduto[] listaProdutos,
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaCaracteristicasCaracteristica[] listaCaracteristicas) {
           this.nmGrupoProduto = nmGrupoProduto;
           this.tipoProduto = tipoProduto;
           this.fabricante = fabricante;
           this.inDisponivel = inDisponivel;
           this.inFimVida = inFimVida;
           this.idGrupoProduto = idGrupoProduto;
           this.qtdImagensAssociadas = qtdImagensAssociadas;
           this.listaTecnologiaTpFrequenciaVL = listaTecnologiaTpFrequenciaVL;
           this.listaProdutos = listaProdutos;
           this.listaCaracteristicas = listaCaracteristicas;
    }


    /**
     * Gets the nmGrupoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @return nmGrupoProduto
     */
    public java.lang.String getNmGrupoProduto() {
        return nmGrupoProduto;
    }


    /**
     * Sets the nmGrupoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @param nmGrupoProduto
     */
    public void setNmGrupoProduto(java.lang.String nmGrupoProduto) {
        this.nmGrupoProduto = nmGrupoProduto;
    }


    /**
     * Gets the tipoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @return tipoProduto
     */
    public java.lang.String getTipoProduto() {
        return tipoProduto;
    }


    /**
     * Sets the tipoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @param tipoProduto
     */
    public void setTipoProduto(java.lang.String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }


    /**
     * Gets the fabricante value for this ResultadoExportarModeloModelo.
     * 
     * @return fabricante
     */
    public java.lang.String getFabricante() {
        return fabricante;
    }


    /**
     * Sets the fabricante value for this ResultadoExportarModeloModelo.
     * 
     * @param fabricante
     */
    public void setFabricante(java.lang.String fabricante) {
        this.fabricante = fabricante;
    }


    /**
     * Gets the inDisponivel value for this ResultadoExportarModeloModelo.
     * 
     * @return inDisponivel
     */
    public java.lang.String getInDisponivel() {
        return inDisponivel;
    }


    /**
     * Sets the inDisponivel value for this ResultadoExportarModeloModelo.
     * 
     * @param inDisponivel
     */
    public void setInDisponivel(java.lang.String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }


    /**
     * Gets the inFimVida value for this ResultadoExportarModeloModelo.
     * 
     * @return inFimVida
     */
    public java.lang.String getInFimVida() {
        return inFimVida;
    }


    /**
     * Sets the inFimVida value for this ResultadoExportarModeloModelo.
     * 
     * @param inFimVida
     */
    public void setInFimVida(java.lang.String inFimVida) {
        this.inFimVida = inFimVida;
    }


    /**
     * Gets the idGrupoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @return idGrupoProduto
     */
    public java.lang.Long getIdGrupoProduto() {
        return idGrupoProduto;
    }


    /**
     * Sets the idGrupoProduto value for this ResultadoExportarModeloModelo.
     * 
     * @param idGrupoProduto
     */
    public void setIdGrupoProduto(java.lang.Long idGrupoProduto) {
        this.idGrupoProduto = idGrupoProduto;
    }


    /**
     * Gets the qtdImagensAssociadas value for this ResultadoExportarModeloModelo.
     * 
     * @return qtdImagensAssociadas
     */
    public java.lang.Long getQtdImagensAssociadas() {
        return qtdImagensAssociadas;
    }


    /**
     * Sets the qtdImagensAssociadas value for this ResultadoExportarModeloModelo.
     * 
     * @param qtdImagensAssociadas
     */
    public void setQtdImagensAssociadas(java.lang.Long qtdImagensAssociadas) {
        this.qtdImagensAssociadas = qtdImagensAssociadas;
    }


    /**
     * Gets the listaTecnologiaTpFrequenciaVL value for this ResultadoExportarModeloModelo.
     * 
     * @return listaTecnologiaTpFrequenciaVL
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL[] getListaTecnologiaTpFrequenciaVL() {
        return listaTecnologiaTpFrequenciaVL;
    }


    /**
     * Sets the listaTecnologiaTpFrequenciaVL value for this ResultadoExportarModeloModelo.
     * 
     * @param listaTecnologiaTpFrequenciaVL
     */
    public void setListaTecnologiaTpFrequenciaVL(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaTecnologiaTpFrequenciaVLTecnologiaTpFrequenciaVL[] listaTecnologiaTpFrequenciaVL) {
        this.listaTecnologiaTpFrequenciaVL = listaTecnologiaTpFrequenciaVL;
    }


    /**
     * Gets the listaProdutos value for this ResultadoExportarModeloModelo.
     * 
     * @return listaProdutos
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaProdutosProduto[] getListaProdutos() {
        return listaProdutos;
    }


    /**
     * Sets the listaProdutos value for this ResultadoExportarModeloModelo.
     * 
     * @param listaProdutos
     */
    public void setListaProdutos(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaProdutosProduto[] listaProdutos) {
        this.listaProdutos = listaProdutos;
    }


    /**
     * Gets the listaCaracteristicas value for this ResultadoExportarModeloModelo.
     * 
     * @return listaCaracteristicas
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaCaracteristicasCaracteristica[] getListaCaracteristicas() {
        return listaCaracteristicas;
    }


    /**
     * Sets the listaCaracteristicas value for this ResultadoExportarModeloModelo.
     * 
     * @param listaCaracteristicas
     */
    public void setListaCaracteristicas(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoExportarModeloModeloListaCaracteristicasCaracteristica[] listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultadoExportarModeloModelo)) return false;
        ResultadoExportarModeloModelo other = (ResultadoExportarModeloModelo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmGrupoProduto==null && other.getNmGrupoProduto()==null) || 
             (this.nmGrupoProduto!=null &&
              this.nmGrupoProduto.equals(other.getNmGrupoProduto()))) &&
            ((this.tipoProduto==null && other.getTipoProduto()==null) || 
             (this.tipoProduto!=null &&
              this.tipoProduto.equals(other.getTipoProduto()))) &&
            ((this.fabricante==null && other.getFabricante()==null) || 
             (this.fabricante!=null &&
              this.fabricante.equals(other.getFabricante()))) &&
            ((this.inDisponivel==null && other.getInDisponivel()==null) || 
             (this.inDisponivel!=null &&
              this.inDisponivel.equals(other.getInDisponivel()))) &&
            ((this.inFimVida==null && other.getInFimVida()==null) || 
             (this.inFimVida!=null &&
              this.inFimVida.equals(other.getInFimVida()))) &&
            ((this.idGrupoProduto==null && other.getIdGrupoProduto()==null) || 
             (this.idGrupoProduto!=null &&
              this.idGrupoProduto.equals(other.getIdGrupoProduto()))) &&
            ((this.qtdImagensAssociadas==null && other.getQtdImagensAssociadas()==null) || 
             (this.qtdImagensAssociadas!=null &&
              this.qtdImagensAssociadas.equals(other.getQtdImagensAssociadas()))) &&
            ((this.listaTecnologiaTpFrequenciaVL==null && other.getListaTecnologiaTpFrequenciaVL()==null) || 
             (this.listaTecnologiaTpFrequenciaVL!=null &&
              java.util.Arrays.equals(this.listaTecnologiaTpFrequenciaVL, other.getListaTecnologiaTpFrequenciaVL()))) &&
            ((this.listaProdutos==null && other.getListaProdutos()==null) || 
             (this.listaProdutos!=null &&
              java.util.Arrays.equals(this.listaProdutos, other.getListaProdutos()))) &&
            ((this.listaCaracteristicas==null && other.getListaCaracteristicas()==null) || 
             (this.listaCaracteristicas!=null &&
              java.util.Arrays.equals(this.listaCaracteristicas, other.getListaCaracteristicas())));
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
        if (getNmGrupoProduto() != null) {
            _hashCode += getNmGrupoProduto().hashCode();
        }
        if (getTipoProduto() != null) {
            _hashCode += getTipoProduto().hashCode();
        }
        if (getFabricante() != null) {
            _hashCode += getFabricante().hashCode();
        }
        if (getInDisponivel() != null) {
            _hashCode += getInDisponivel().hashCode();
        }
        if (getInFimVida() != null) {
            _hashCode += getInFimVida().hashCode();
        }
        if (getIdGrupoProduto() != null) {
            _hashCode += getIdGrupoProduto().hashCode();
        }
        if (getQtdImagensAssociadas() != null) {
            _hashCode += getQtdImagensAssociadas().hashCode();
        }
        if (getListaTecnologiaTpFrequenciaVL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTecnologiaTpFrequenciaVL());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTecnologiaTpFrequenciaVL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaProdutos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaProdutos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaProdutos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaCaracteristicas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCaracteristicas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCaracteristicas(), i);
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
        new org.apache.axis.description.TypeDesc(ResultadoExportarModeloModelo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ResultadoExportarModelo>Modelo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "tipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "fabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDisponivel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inDisponivel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inFimVida");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inFimVida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idGrupoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idGrupoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtdImagensAssociadas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "qtdImagensAssociadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTecnologiaTpFrequenciaVL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaTecnologiaTpFrequenciaVL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoExportarModelo>Modelo>ListaTecnologiaTpFrequenciaVL>TecnologiaTpFrequenciaVL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "TecnologiaTpFrequenciaVL"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaProdutos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaProdutos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoExportarModelo>Modelo>ListaProdutos>Produto"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Produto"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaracteristicas");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaCaracteristicas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>>>ResultadoExportarModelo>Modelo>ListaCaracteristicas>Caracteristica"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "Caracteristica"));
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
