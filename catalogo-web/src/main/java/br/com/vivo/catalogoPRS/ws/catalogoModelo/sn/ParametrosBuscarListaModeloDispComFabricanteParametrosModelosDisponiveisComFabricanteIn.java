/**
 * ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.catalogoPRS.ws.catalogoModelo.sn;

public class ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn  implements java.io.Serializable {
    private br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn;

    private long idTipoProduto;

    private java.lang.Long idFabricante;

    private java.lang.Long idTecnologia;

    public ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn() {
    }

    public ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn(
           br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn,
           long idTipoProduto,
           java.lang.Long idFabricante,
           java.lang.Long idTecnologia) {
           this.paginacaoIn = paginacaoIn;
           this.idTipoProduto = idTipoProduto;
           this.idFabricante = idFabricante;
           this.idTecnologia = idTecnologia;
    }


    /**
     * Gets the paginacaoIn value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @return paginacaoIn
     */
    public br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn getPaginacaoIn() {
        return paginacaoIn;
    }


    /**
     * Sets the paginacaoIn value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @param paginacaoIn
     */
    public void setPaginacaoIn(br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.PaginacaoIn paginacaoIn) {
        this.paginacaoIn = paginacaoIn;
    }


    /**
     * Gets the idTipoProduto value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @return idTipoProduto
     */
    public long getIdTipoProduto() {
        return idTipoProduto;
    }


    /**
     * Sets the idTipoProduto value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @param idTipoProduto
     */
    public void setIdTipoProduto(long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }


    /**
     * Gets the idFabricante value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @return idFabricante
     */
    public java.lang.Long getIdFabricante() {
        return idFabricante;
    }


    /**
     * Sets the idFabricante value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @param idFabricante
     */
    public void setIdFabricante(java.lang.Long idFabricante) {
        this.idFabricante = idFabricante;
    }


    /**
     * Gets the idTecnologia value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @return idTecnologia
     */
    public java.lang.Long getIdTecnologia() {
        return idTecnologia;
    }


    /**
     * Sets the idTecnologia value for this ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.
     * 
     * @param idTecnologia
     */
    public void setIdTecnologia(java.lang.Long idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn)) return false;
        ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn other = (ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn) obj;
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
            ((this.idTecnologia==null && other.getIdTecnologia()==null) || 
             (this.idTecnologia!=null &&
              this.idTecnologia.equals(other.getIdTecnologia())));
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
        if (getIdTecnologia() != null) {
            _hashCode += getIdTecnologia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosBuscarListaModeloDispComFabricanteParametrosModelosDisponiveisComFabricanteIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", ">>ParametrosBuscarListaModeloDispComFabricante>ParametrosModelosDisponiveisComFabricanteIn"));
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
        elemField.setFieldName("idTecnologia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/SN/CatalogoModelo", "idTecnologia"));
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
