/**
 * ParametrosBuscarPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoPlano.sn;

public class ParametrosBuscarPlano  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn;

    private java.lang.String nmPlano;

    private java.lang.String cdCodigo;

    private long idPlataforma;

    private java.lang.Long idTecnologia;

    private java.lang.Long idSistema;

    private long[] listaUf;

    private java.lang.String[] listaDDD;

    public ParametrosBuscarPlano() {
    }

    public ParametrosBuscarPlano(
           br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn,
           java.lang.String nmPlano,
           java.lang.String cdCodigo,
           long idPlataforma,
           java.lang.Long idTecnologia,
           java.lang.Long idSistema,
           long[] listaUf,
           java.lang.String[] listaDDD) {
           this.paginacaoIn = paginacaoIn;
           this.nmPlano = nmPlano;
           this.cdCodigo = cdCodigo;
           this.idPlataforma = idPlataforma;
           this.idTecnologia = idTecnologia;
           this.idSistema = idSistema;
           this.listaUf = listaUf;
           this.listaDDD = listaDDD;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarPlano.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarPlano.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the nmPlano value for this ParametrosBuscarPlano.
     * 
     * @return nmPlano
     */
    public java.lang.String getNmPlano() {
        return nmPlano;
    }


    /**
     * Sets the nmPlano value for this ParametrosBuscarPlano.
     * 
     * @param nmPlano
     */
    public void setNmPlano(java.lang.String nmPlano) {
        this.nmPlano = nmPlano;
    }


    /**
     * Gets the cdCodigo value for this ParametrosBuscarPlano.
     * 
     * @return cdCodigo
     */
    public java.lang.String getCdCodigo() {
        return cdCodigo;
    }


    /**
     * Sets the cdCodigo value for this ParametrosBuscarPlano.
     * 
     * @param cdCodigo
     */
    public void setCdCodigo(java.lang.String cdCodigo) {
        this.cdCodigo = cdCodigo;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarPlano.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarPlano.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the idTecnologia value for this ParametrosBuscarPlano.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosBuscarPlano.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the idSistema value for this ParametrosBuscarPlano.
     * 
     * @return idSistema
     */
    public java.lang.Long getIdSistema() {
        return idSistema;
    }


    /**
     * Sets the idSistema value for this ParametrosBuscarPlano.
     * 
     * @param idSistema
     */
    public void setIdSistema(java.lang.Long idSistema) {
        this.idSistema = idSistema;
    }


    /**
     * Gets the listaUf value for this ParametrosBuscarPlano.
     * 
     * @return listaUf
     */
    public long[] getListaUf() {
        return listaUf;
    }


    /**
     * Sets the listaUf value for this ParametrosBuscarPlano.
     * 
     * @param listaUf
     */
    public void setListaUf(long[] listaUf) {
        this.listaUf = listaUf;
    }


    /**
     * Gets the listaDDD value for this ParametrosBuscarPlano.
     * 
     * @return listaDDD
     */
    public java.lang.String[] getListaDDD() {
        return listaDDD;
    }


    /**
     * Sets the listaDDD value for this ParametrosBuscarPlano.
     * 
     * @param listaDDD
     */
    public void setListaDDD(java.lang.String[] listaDDD) {
        this.listaDDD = listaDDD;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarPlano)) return false;
        ParametrosBuscarPlano other = (ParametrosBuscarPlano) obj;
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
            ((this.cdCodigo==null && other.getCdCodigo()==null) || 
             (this.cdCodigo!=null &&
              this.cdCodigo.equals(other.getCdCodigo()))) &&
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia()))) &&
            ((this.idSistema==null && other.getIdSistema()==null) || 
             (this.idSistema!=null &&
              this.idSistema.equals(other.getIdSistema()))) &&
            ((this.listaUf==null && other.getListaUf()==null) || 
             (this.listaUf!=null &&
              java.util.Arrays.equals(this.listaUf, other.getListaUf()))) &&
            ((this.listaDDD==null && other.getListaDDD()==null) || 
             (this.listaDDD!=null &&
              java.util.Arrays.equals(this.listaDDD, other.getListaDDD())));
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
        if (getCdCodigo() != null) {
            _hashCode += getCdCodigo().hashCode();
        }
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        if (getIdSistema() != null) {
            _hashCode += getIdSistema().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">ParametrosBuscarPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "nmPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdCodigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "cdCodigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idTecnologia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSistema");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idSistema"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaUf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaUf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "idUf"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDDD");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ListaDDD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoPlano", "ddd"));
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
