/**
 * ParametrosBuscarListaModeloParametrosModeloIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarListaModeloParametrosModeloIn  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn;

    private long idTipoProduto;

    private java.lang.Long idFabricante;

    private java.lang.String nmModelo;

    private java.lang.String inicio;

    private java.lang.String fim;

    private long[] listaTecnologia;

    private long[] listaCaracteristica;

    private long[] listaValorCaracteristica;

    public ParametrosBuscarListaModeloParametrosModeloIn() {
    }

    public ParametrosBuscarListaModeloParametrosModeloIn(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn,
           long idTipoProduto,
           java.lang.Long idFabricante,
           java.lang.String nmModelo,
           java.lang.String inicio,
           java.lang.String fim,
           long[] listaTecnologia,
           long[] listaCaracteristica,
           long[] listaValorCaracteristica) {
           this.paginacaoIn = paginacaoIn;
           this.idTipoProduto = idTipoProduto;
           this.idFabricante = idFabricante;
           this.nmModelo = nmModelo;
           this.inicio = inicio;
           this.fim = fim;
           this.listaTecnologia = listaTecnologia;
           this.listaCaracteristica = listaCaracteristica;
           this.listaValorCaracteristica = listaValorCaracteristica;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idTipoProduto value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idFabricante value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return idFabricante
     */
    public java.lang.Long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(java.lang.Long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the nmModelo value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return nmModelo
     */
    public java.lang.String getNmModelo() {
        return nmModelo;
    }


    /**
     * Sets the nmModelo value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param nmModelo
     */
    public void setNmModelo(java.lang.String nmModelo) {
        this.nmModelo = nmModelo;
    }


    /**
     * Gets the inicio value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return inicio
     */
    public java.lang.String getInicio() {
        return inicio;
    }


    /**
     * Sets the inicio value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param inicio
     */
    public void setInicio(java.lang.String inicio) {
        this.inicio = inicio;
    }


    /**
     * Gets the fim value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return fim
     */
    public java.lang.String getFim() {
        return fim;
    }


    /**
     * Sets the fim value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param fim
     */
    public void setFim(java.lang.String fim) {
        this.fim = fim;
    }


    /**
     * Gets the listaTecnologia value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return listaTecnologia
     */
    public long[] getListaTecnologia() {
        return listaTecnologia;
    }


    /**
     * Sets the listaTecnologia value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param listaTecnologia
     */
    public void setListaTecnologia(long[] listaTecnologia) {
        this.listaTecnologia = listaTecnologia;
    }


    /**
     * Gets the listaCaracteristica value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return listaCaracteristica
     */
    public long[] getListaCaracteristica() {
        return listaCaracteristica;
    }


    /**
     * Sets the listaCaracteristica value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param listaCaracteristica
     */
    public void setListaCaracteristica(long[] listaCaracteristica) {
        this.listaCaracteristica = listaCaracteristica;
    }


    /**
     * Gets the listaValorCaracteristica value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @return listaValorCaracteristica
     */
    public long[] getListaValorCaracteristica() {
        return listaValorCaracteristica;
    }


    /**
     * Sets the listaValorCaracteristica value for this ParametrosBuscarListaModeloParametrosModeloIn.
     * 
     * @param listaValorCaracteristica
     */
    public void setListaValorCaracteristica(long[] listaValorCaracteristica) {
        this.listaValorCaracteristica = listaValorCaracteristica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloParametrosModeloIn)) return false;
        ParametrosBuscarListaModeloParametrosModeloIn other = (ParametrosBuscarListaModeloParametrosModeloIn) obj;
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
            this.idTipoProduto == other.getIdTipoProduto() &&
            ((this.idFabricante==null && other.getIdFabricante()==null) || 
             (this.idFabricante!=null &&
              this.idFabricante.equals(other.getIdFabricante()))) &&
            ((this.nmModelo==null && other.getNmModelo()==null) || 
             (this.nmModelo!=null &&
              this.nmModelo.equals(other.getNmModelo()))) &&
            ((this.inicio==null && other.getInicio()==null) || 
             (this.inicio!=null &&
              this.inicio.equals(other.getInicio()))) &&
            ((this.fim==null && other.getFim()==null) || 
             (this.fim!=null &&
              this.fim.equals(other.getFim()))) &&
            ((this.listaTecnologia==null && other.getListaTecnologia()==null) || 
             (this.listaTecnologia!=null &&
              java.util.Arrays.equals(this.listaTecnologia, other.getListaTecnologia()))) &&
            ((this.listaCaracteristica==null && other.getListaCaracteristica()==null) || 
             (this.listaCaracteristica!=null &&
              java.util.Arrays.equals(this.listaCaracteristica, other.getListaCaracteristica()))) &&
            ((this.listaValorCaracteristica==null && other.getListaValorCaracteristica()==null) || 
             (this.listaValorCaracteristica!=null &&
              java.util.Arrays.equals(this.listaValorCaracteristica, other.getListaValorCaracteristica())));
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
        _hashCode += new Long(getIdTipoProduto()).hashCode();
        if (getIdFabricante() != null) {
            _hashCode += getIdFabricante().hashCode();
        }
        if (getNmModelo() != null) {
            _hashCode += getNmModelo().hashCode();
        }
        if (getInicio() != null) {
            _hashCode += getInicio().hashCode();
        }
        if (getFim() != null) {
            _hashCode += getFim().hashCode();
        }
        if (getListaTecnologia() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaTecnologia());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaTecnologia(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
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
        if (getListaValorCaracteristica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaValorCaracteristica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaValorCaracteristica(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloParametrosModeloIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarListaModelo>ParametrosModeloIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTipoProduto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTipoProduto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFabricante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idFabricante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmModelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "nmModelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "inicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "fim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idCaracteristica"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaValorCaracteristica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "ListaValorCaracteristica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idValorCaracteristica"));
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
