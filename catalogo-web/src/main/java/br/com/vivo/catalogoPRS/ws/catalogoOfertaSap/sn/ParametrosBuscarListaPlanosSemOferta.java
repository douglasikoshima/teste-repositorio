/**
 * ParametrosBuscarListaPlanosSemOferta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn;

public class ParametrosBuscarListaPlanosSemOferta  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private java.lang.String nmPlano;

    private java.lang.String cdPlano;

    private long idPlataforma;

    private java.lang.Long idTecnologia;

    private long[] listaUf;

    private java.lang.String[] listaDDD;

    private java.lang.Long idSistema;
    
    public ParametrosBuscarListaPlanosSemOferta() {
    }

    public ParametrosBuscarListaPlanosSemOferta(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           java.lang.String nmPlano,
           java.lang.String cdPlano,
           long idPlataforma,
           java.lang.Long idTecnologia,
           long[] listaUf,
           java.lang.String[] listaDDD,
           java.lang.Long idSistema) {
           this.paginacaoIn = paginacaoIn;
           this.nmPlano = nmPlano;
           this.cdPlano = cdPlano;
           this.idPlataforma = idPlataforma;
           this.idTecnologia = idTecnologia;
           this.listaUf = listaUf;
           this.listaDDD = listaDDD;
           this.idSistema = idSistema;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the nmPlano value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return nmPlano
     */
    public java.lang.String getNmPlano() {
        return nmPlano;
    }


    /**
     * Sets the nmPlano value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param nmPlano
     */
    public void setNmPlano(java.lang.String nmPlano) {
        this.nmPlano = nmPlano;
    }


    /**
     * Gets the cdPlano value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return cdPlano
     */
    public java.lang.String getCdPlano() {
        return cdPlano;
    }


    /**
     * Sets the cdPlano value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param cdPlano
     */
    public void setCdPlano(java.lang.String cdPlano) {
        this.cdPlano = cdPlano;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idTecnologia value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the listaUf value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return listaUf
     */
    public long[] getListaUf() {
        return listaUf;
    }


    /**
     * Sets the listaUf value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param listaUf
     */
    public void setListaUf(long[] listaUf) {
        this.listaUf = listaUf;
    }


    /**
     * Gets the listaDDD value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return listaDDD
     */
    public java.lang.String[] getListaDDD() {
        return listaDDD;
    }


    /**
     * Sets the listaDDD value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param listaDDD
     */
    public void setListaDDD(java.lang.String[] listaDDD) {
        this.listaDDD = listaDDD;
    }


    /**
     * Gets the idSistema value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @return idSistema
     */
    public java.lang.Long getIdSistema() {
        return idSistema;
    }


    /**
     * Sets the idSistema value for this ParametrosBuscarListaPlanosSemOferta.
     * 
     * @param idSistema
     */
    public void setIdSistema(java.lang.Long idSistema) {
        this.idSistema = idSistema;
    }
    
    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaPlanosSemOferta)) return false;
        ParametrosBuscarListaPlanosSemOferta other = (ParametrosBuscarListaPlanosSemOferta) obj;
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
            ((this.nmPlano==null && other.getNmPlano()==null) || 
             (this.nmPlano!=null &&
              this.nmPlano.equals(other.getNmPlano()))) &&
            ((this.cdPlano==null && other.getCdPlano()==null) || 
             (this.cdPlano!=null &&
              this.cdPlano.equals(other.getCdPlano()))) &&
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.listaUf==null && other.getListaUf()==null) || 
             (this.listaUf!=null &&
              java.util.Arrays.equals(this.listaUf, other.getListaUf()))) &&
            ((this.listaDDD==null && other.getListaDDD()==null) || 
             (this.listaDDD!=null &&
              java.util.Arrays.equals(this.listaDDD, other.getListaDDD()))) &&
            ((this.idSistema==null && other.getIdSistema()==null) || 
             (this.idSistema!=null &&
              this.idSistema.equals(other.getIdSistema())));
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
        if (getNmPlano() != null) {
            _hashCode += getNmPlano().hashCode();
        }
        if (getCdPlano() != null) {
            _hashCode += getCdPlano().hashCode();
        }
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getListaUf() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaUf());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaUf(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getListaDDD() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaDDD());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaDDD(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getIdSistema() != null) {
            _hashCode += getIdSistema().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaPlanosSemOferta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", ">ParametrosBuscarListaPlanosSemOferta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "nmPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "cdPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ListaUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idUf"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ListaDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "ddd"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoOfertaSap", "idSistema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
