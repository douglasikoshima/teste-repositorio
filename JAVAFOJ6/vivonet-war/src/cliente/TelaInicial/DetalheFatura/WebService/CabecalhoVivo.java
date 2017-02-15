/**
 * CabecalhoVivo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cliente.TelaInicial.DetalheFatura.WebService;

public class CabecalhoVivo  implements java.io.Serializable {
    private java.lang.String loginUsuario;

    /* Descricao do canal de atendimento que e enviado
     * 						pelo SCA. */
    private java.lang.String canalAtendimento;

    /* Codigo da sessao, codigo unico que identifica
     * 						uma sessao. */
    private java.lang.String codigoSessao;

    private br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.CabecalhoVivoNomeAplicacao nomeAplicacao;

    private java.lang.String token;

    private java.lang.String enderecoIP;

    /* Codigo que identifica uma funcionalidade do
     * 						sistema. Exemplos de funcionalidade para o
     * 						sistema Vivo360: Ativacao e desativacao de
     * 						servicos, Migracao para GSM, Migracao para CDMA,
     * 						Troca de aparelho CDMA e GSM, Troca de chip,
     * 						troca de plano e etc. */
    private java.lang.String codigoFuncionalidade;

    private java.util.Calendar dataTransacao;

    /* Nome do servico que foi invocado, exemplo:
     * 						consultaPessoa, consultaLinha, etc. */
    private java.lang.String nomeServico;

    public CabecalhoVivo() {
    }

    public CabecalhoVivo(
           java.lang.String loginUsuario,
           java.lang.String canalAtendimento,
           java.lang.String codigoSessao,
           br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.CabecalhoVivoNomeAplicacao nomeAplicacao,
           java.lang.String token,
           java.lang.String enderecoIP,
           java.lang.String codigoFuncionalidade,
           java.util.Calendar dataTransacao,
           java.lang.String nomeServico) {
           this.loginUsuario = loginUsuario;
           this.canalAtendimento = canalAtendimento;
           this.codigoSessao = codigoSessao;
           this.nomeAplicacao = nomeAplicacao;
           this.token = token;
           this.enderecoIP = enderecoIP;
           this.codigoFuncionalidade = codigoFuncionalidade;
           this.dataTransacao = dataTransacao;
           this.nomeServico = nomeServico;
    }


    /**
     * Gets the loginUsuario value for this CabecalhoVivo.
     * 
     * @return loginUsuario
     */
    public java.lang.String getLoginUsuario() {
        return loginUsuario;
    }


    /**
     * Sets the loginUsuario value for this CabecalhoVivo.
     * 
     * @param loginUsuario
     */
    public void setLoginUsuario(java.lang.String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }


    /**
     * Gets the canalAtendimento value for this CabecalhoVivo.
     * 
     * @return canalAtendimento   * Descricao do canal de atendimento que e enviado
     * 						pelo SCA.
     */
    public java.lang.String getCanalAtendimento() {
        return canalAtendimento;
    }


    /**
     * Sets the canalAtendimento value for this CabecalhoVivo.
     * 
     * @param canalAtendimento   * Descricao do canal de atendimento que e enviado
     * 						pelo SCA.
     */
    public void setCanalAtendimento(java.lang.String canalAtendimento) {
        this.canalAtendimento = canalAtendimento;
    }


    /**
     * Gets the codigoSessao value for this CabecalhoVivo.
     * 
     * @return codigoSessao   * Codigo da sessao, codigo unico que identifica
     * 						uma sessao.
     */
    public java.lang.String getCodigoSessao() {
        return codigoSessao;
    }


    /**
     * Sets the codigoSessao value for this CabecalhoVivo.
     * 
     * @param codigoSessao   * Codigo da sessao, codigo unico que identifica
     * 						uma sessao.
     */
    public void setCodigoSessao(java.lang.String codigoSessao) {
        this.codigoSessao = codigoSessao;
    }


    /**
     * Gets the nomeAplicacao value for this CabecalhoVivo.
     * 
     * @return nomeAplicacao
     */
    public br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.CabecalhoVivoNomeAplicacao getNomeAplicacao() {
        return nomeAplicacao;
    }


    /**
     * Sets the nomeAplicacao value for this CabecalhoVivo.
     * 
     * @param nomeAplicacao
     */
    public void setNomeAplicacao(br.com.vivo.ctrls.cliente.contaonline.ws.MC.Geral.CabecalhoVivoNomeAplicacao nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }


    /**
     * Gets the token value for this CabecalhoVivo.
     * 
     * @return token
     */
    public java.lang.String getToken() {
        return token;
    }


    /**
     * Sets the token value for this CabecalhoVivo.
     * 
     * @param token
     */
    public void setToken(java.lang.String token) {
        this.token = token;
    }


    /**
     * Gets the enderecoIP value for this CabecalhoVivo.
     * 
     * @return enderecoIP
     */
    public java.lang.String getEnderecoIP() {
        return enderecoIP;
    }


    /**
     * Sets the enderecoIP value for this CabecalhoVivo.
     * 
     * @param enderecoIP
     */
    public void setEnderecoIP(java.lang.String enderecoIP) {
        this.enderecoIP = enderecoIP;
    }


    /**
     * Gets the codigoFuncionalidade value for this CabecalhoVivo.
     * 
     * @return codigoFuncionalidade   * Codigo que identifica uma funcionalidade do
     * 						sistema. Exemplos de funcionalidade para o
     * 						sistema Vivo360: Ativacao e desativacao de
     * 						servicos, Migracao para GSM, Migracao para CDMA,
     * 						Troca de aparelho CDMA e GSM, Troca de chip,
     * 						troca de plano e etc.
     */
    public java.lang.String getCodigoFuncionalidade() {
        return codigoFuncionalidade;
    }


    /**
     * Sets the codigoFuncionalidade value for this CabecalhoVivo.
     * 
     * @param codigoFuncionalidade   * Codigo que identifica uma funcionalidade do
     * 						sistema. Exemplos de funcionalidade para o
     * 						sistema Vivo360: Ativacao e desativacao de
     * 						servicos, Migracao para GSM, Migracao para CDMA,
     * 						Troca de aparelho CDMA e GSM, Troca de chip,
     * 						troca de plano e etc.
     */
    public void setCodigoFuncionalidade(java.lang.String codigoFuncionalidade) {
        this.codigoFuncionalidade = codigoFuncionalidade;
    }


    /**
     * Gets the dataTransacao value for this CabecalhoVivo.
     * 
     * @return dataTransacao
     */
    public java.util.Calendar getDataTransacao() {
        return dataTransacao;
    }


    /**
     * Sets the dataTransacao value for this CabecalhoVivo.
     * 
     * @param dataTransacao
     */
    public void setDataTransacao(java.util.Calendar dataTransacao) {
        this.dataTransacao = dataTransacao;
    }


    /**
     * Gets the nomeServico value for this CabecalhoVivo.
     * 
     * @return nomeServico   * Nome do servico que foi invocado, exemplo:
     * 						consultaPessoa, consultaLinha, etc.
     */
    public java.lang.String getNomeServico() {
        return nomeServico;
    }


    /**
     * Sets the nomeServico value for this CabecalhoVivo.
     * 
     * @param nomeServico   * Nome do servico que foi invocado, exemplo:
     * 						consultaPessoa, consultaLinha, etc.
     */
    public void setNomeServico(java.lang.String nomeServico) {
        this.nomeServico = nomeServico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CabecalhoVivo)) return false;
        CabecalhoVivo other = (CabecalhoVivo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.loginUsuario==null && other.getLoginUsuario()==null) || 
             (this.loginUsuario!=null &&
              this.loginUsuario.equals(other.getLoginUsuario()))) &&
            ((this.canalAtendimento==null && other.getCanalAtendimento()==null) || 
             (this.canalAtendimento!=null &&
              this.canalAtendimento.equals(other.getCanalAtendimento()))) &&
            ((this.codigoSessao==null && other.getCodigoSessao()==null) || 
             (this.codigoSessao!=null &&
              this.codigoSessao.equals(other.getCodigoSessao()))) &&
            ((this.nomeAplicacao==null && other.getNomeAplicacao()==null) || 
             (this.nomeAplicacao!=null &&
              this.nomeAplicacao.equals(other.getNomeAplicacao()))) &&
            ((this.token==null && other.getToken()==null) || 
             (this.token!=null &&
              this.token.equals(other.getToken()))) &&
            ((this.enderecoIP==null && other.getEnderecoIP()==null) || 
             (this.enderecoIP!=null &&
              this.enderecoIP.equals(other.getEnderecoIP()))) &&
            ((this.codigoFuncionalidade==null && other.getCodigoFuncionalidade()==null) || 
             (this.codigoFuncionalidade!=null &&
              this.codigoFuncionalidade.equals(other.getCodigoFuncionalidade()))) &&
            ((this.dataTransacao==null && other.getDataTransacao()==null) || 
             (this.dataTransacao!=null &&
              this.dataTransacao.equals(other.getDataTransacao()))) &&
            ((this.nomeServico==null && other.getNomeServico()==null) || 
             (this.nomeServico!=null &&
              this.nomeServico.equals(other.getNomeServico())));
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
        if (getLoginUsuario() != null) {
            _hashCode += getLoginUsuario().hashCode();
        }
        if (getCanalAtendimento() != null) {
            _hashCode += getCanalAtendimento().hashCode();
        }
        if (getCodigoSessao() != null) {
            _hashCode += getCodigoSessao().hashCode();
        }
        if (getNomeAplicacao() != null) {
            _hashCode += getNomeAplicacao().hashCode();
        }
        if (getToken() != null) {
            _hashCode += getToken().hashCode();
        }
        if (getEnderecoIP() != null) {
            _hashCode += getEnderecoIP().hashCode();
        }
        if (getCodigoFuncionalidade() != null) {
            _hashCode += getCodigoFuncionalidade().hashCode();
        }
        if (getDataTransacao() != null) {
            _hashCode += getDataTransacao().hashCode();
        }
        if (getNomeServico() != null) {
            _hashCode += getNomeServico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CabecalhoVivo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "CabecalhoVivo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "loginUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canalAtendimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "canalAtendimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoSessao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "codigoSessao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeAplicacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "nomeAplicacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", ">CabecalhoVivo>nomeAplicacao"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("token");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "token"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enderecoIP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "enderecoIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoFuncionalidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "codigoFuncionalidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataTransacao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "dataTransacao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeServico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral", "nomeServico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
