/**
 * ParametrosBuscarListaAcessoPlano.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn;

public class ParametrosBuscarListaAcessoPlano  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn;

    private long[] listaIdPerfil;

    private long idPlataforma;

    private java.lang.String cdPlano;

    private java.lang.String nmComercial;

    private long[] listaIdPlano;

    public ParametrosBuscarListaAcessoPlano() {
    }

    public ParametrosBuscarListaAcessoPlano(
           br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn,
           long[] listaIdPerfil,
           long idPlataforma,
           java.lang.String cdPlano,
           java.lang.String nmComercial,
           long[] listaIdPlano) {
           this.paginacaoIn = paginacaoIn;
           this.listaIdPerfil = listaIdPerfil;
           this.idPlataforma = idPlataforma;
           this.cdPlano = cdPlano;
           this.nmComercial = nmComercial;
           this.listaIdPlano = listaIdPlano;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoGeral.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the listaIdPerfil value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return listaIdPerfil
     */
    public long[] getListaIdPerfil() {
        return listaIdPerfil;
    }


    /**
     * Sets the listaIdPerfil value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param listaIdPerfil
     */
    public void setListaIdPerfil(long[] listaIdPerfil) {
        this.listaIdPerfil = listaIdPerfil;
    }


    /**
     * Gets the idPlataforma value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return idPlataforma
     */
    public long getIdPlataforma() {
        return idPlataforma;
    }


    /**
     * Sets the idPlataforma value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param idPlataforma
     */
    public void setIdPlataforma(long idPlataforma) {
        this.idPlataforma = idPlataforma;
    }


    /**
     * Gets the cdPlano value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return cdPlano
     */
    public java.lang.String getCdPlano() {
        return cdPlano;
    }


    /**
     * Sets the cdPlano value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param cdPlano
     */
    public void setCdPlano(java.lang.String cdPlano) {
        this.cdPlano = cdPlano;
    }


    /**
     * Gets the nmComercial value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return nmComercial
     */
    public java.lang.String getNmComercial() {
        return nmComercial;
    }


    /**
     * Sets the nmComercial value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param nmComercial
     */
    public void setNmComercial(java.lang.String nmComercial) {
        this.nmComercial = nmComercial;
    }


    /**
     * Gets the listaIdPlano value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @return listaIdPlano
     */
    public long[] getListaIdPlano() {
        return listaIdPlano;
    }


    /**
     * Sets the listaIdPlano value for this ParametrosBuscarListaAcessoPlano.
     * 
     * @param listaIdPlano
     */
    public void setListaIdPlano(long[] listaIdPlano) {
        this.listaIdPlano = listaIdPlano;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaAcessoPlano)) return false;
        ParametrosBuscarListaAcessoPlano other = (ParametrosBuscarListaAcessoPlano) obj;
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
            ((this.listaIdPerfil==null && other.getListaIdPerfil()==null) || 
             (this.listaIdPerfil!=null &&
              java.util.Arrays.equals(this.listaIdPerfil, other.getListaIdPerfil()))) &&
            this.idPlataforma == other.getIdPlataforma() &&
            ((this.cdPlano==null && other.getCdPlano()==null) || 
             (this.cdPlano!=null &&
              this.cdPlano.equals(other.getCdPlano()))) &&
            ((this.nmComercial==null && other.getNmComercial()==null) || 
             (this.nmComercial!=null &&
              this.nmComercial.equals(other.getNmComercial()))) &&
            ((this.listaIdPlano==null && other.getListaIdPlano()==null) || 
             (this.listaIdPlano!=null &&
              java.util.Arrays.equals(this.listaIdPlano, other.getListaIdPlano())));
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
        if (getListaIdPerfil() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdPerfil());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdPerfil(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getIdPlataforma()).hashCode();
        if (getCdPlano() != null) {
            _hashCode += getCdPlano().hashCode();
        }
        if (getNmComercial() != null) {
            _hashCode += getNmComercial().hashCode();
        }
        if (getListaIdPlano() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaIdPlano());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaIdPlano(), i);
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
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaAcessoPlano.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", ">ParametrosBuscarListaAcessoPlano"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paginacaoIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", "PaginacaoIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoGeral", ">PaginacaoIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdPerfil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaIdPerfil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPerfilSCA"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPlataforma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPlataforma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "cdPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmComercial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "nmComercial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaIdPlano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "ListaIdPlano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoAcesso", "idPlano"));
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
