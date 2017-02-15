/**
 * Endereco.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa;


/**
 * Mantem informacoes dos enderecos de pessoas (vendedores,
 * 				clientes, prospects ou usuarios).
 */
public class Endereco  implements java.io.Serializable {
    /* Codigo que identifica o endereco de um
     * 						cliente/prospect (idpessoaendereco no Vivonet). */
    private java.math.BigInteger codigo;

    /* Codigo que identifica o endereco no sistema
     * 						origem. Para atlys, essa informacao representa a
     * 						coluna ADDR_SEQ_NBR da tabela CUST_ADDR. */
    private java.math.BigInteger codigoEndSistemaOrigem;

    /* Numero da caixa postal */
    private java.lang.String caixaPostal;

    /* Nome da rua */
    private java.lang.String logradouro;

    /* Numero da residencia */
    private java.lang.String numero;

    /* Complemento, como numero do bloco, apartamento. */
    private java.lang.String complemento;

    /* Nome do bairro. */
    private java.lang.String bairro;

    /* Nome da cidade */
    private java.lang.String cidade;

    /* Numero do CEP. */
    private java.lang.String cep;

    /* Identifica se o endereco e o preferencial para o
     * 						cliente, sendo true o endereco preferencial. */
    private java.lang.Boolean enderecoPreferencial;

    /* Data em que o endereco foi cadastrado. */
    private java.util.Date dataCadastro;

    /* Data em que o endereco nao existe mais para
     * 						aquela pessoa */
    private java.util.Date dataExpiracao;

    /* Indica se existe numeracao para o endereco de
     * 						acordo com o CEP (valores validos, 0, 1 ou 2) */
    private java.math.BigInteger indicaNumeracaoEndereco;

    /* Numero inicial do intervalo de numeracao
     * 						permitido para o logradouro neste CEP e Bairro. */
    private java.math.BigInteger numeroInicial;

    /* Numero final do intervalo de numeracao permitido
     * 						para o logradouro neste CEP e Bairro. */
    private java.math.BigInteger numeroFinal;

    /* Indica se a faixa de numeracao se aplica a um
     * 						lado especifico da rua ou nao. Valores validos:
     * 						0 - Par e Impar; 1 - Impar; 2 - Par. */
    private java.math.BigInteger indicaLado;

    /* Descricao do lado. Valores validos: Par e Impar,
     * 						Impar ou Par. */
    private java.lang.String descricaoLado;

    private java.util.Calendar dataUltimaAlteracao;

    /* Referencia a entidade TipoLogradouro */
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro tipoLogradouro;

    /* Referencia a entidade TituloLogradouro. */
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro tituloLogradouro;

    /* Referencia a entidade Pais */
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais pais;

    /* Referencia a entidade TipoEndereco. */
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco tipoEndereco;

    /* Referencia a entidade EnderecoFiscal. */
    private br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.EnderecoFiscal enderecoFiscal;

    /* Flag que indica se o endereco do cliente
     * 						posicionado eh o endereco associado a conta do
     * 						cliente. true - endereco associado a conta;
     * 						false - endereco nao associado a conta. */
    private java.lang.Boolean enderecoAssociadoConta;

    public Endereco() {
    }

    public Endereco(
           java.math.BigInteger codigo,
           java.math.BigInteger codigoEndSistemaOrigem,
           java.lang.String caixaPostal,
           java.lang.String logradouro,
           java.lang.String numero,
           java.lang.String complemento,
           java.lang.String bairro,
           java.lang.String cidade,
           java.lang.String cep,
           java.lang.Boolean enderecoPreferencial,
           java.util.Date dataCadastro,
           java.util.Date dataExpiracao,
           java.math.BigInteger indicaNumeracaoEndereco,
           java.math.BigInteger numeroInicial,
           java.math.BigInteger numeroFinal,
           java.math.BigInteger indicaLado,
           java.lang.String descricaoLado,
           java.util.Calendar dataUltimaAlteracao,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro tipoLogradouro,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro tituloLogradouro,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais pais,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco tipoEndereco,
           br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.EnderecoFiscal enderecoFiscal,
           java.lang.Boolean enderecoAssociadoConta) {
           this.codigo = codigo;
           this.codigoEndSistemaOrigem = codigoEndSistemaOrigem;
           this.caixaPostal = caixaPostal;
           this.logradouro = logradouro;
           this.numero = numero;
           this.complemento = complemento;
           this.bairro = bairro;
           this.cidade = cidade;
           this.cep = cep;
           this.enderecoPreferencial = enderecoPreferencial;
           this.dataCadastro = dataCadastro;
           this.dataExpiracao = dataExpiracao;
           this.indicaNumeracaoEndereco = indicaNumeracaoEndereco;
           this.numeroInicial = numeroInicial;
           this.numeroFinal = numeroFinal;
           this.indicaLado = indicaLado;
           this.descricaoLado = descricaoLado;
           this.dataUltimaAlteracao = dataUltimaAlteracao;
           this.tipoLogradouro = tipoLogradouro;
           this.tituloLogradouro = tituloLogradouro;
           this.pais = pais;
           this.tipoEndereco = tipoEndereco;
           this.enderecoFiscal = enderecoFiscal;
           this.enderecoAssociadoConta = enderecoAssociadoConta;
    }


    /**
     * Gets the codigo value for this Endereco.
     * 
     * @return codigo   * Codigo que identifica o endereco de um
     * 						cliente/prospect (idpessoaendereco no Vivonet).
     */
    public java.math.BigInteger getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Endereco.
     * 
     * @param codigo   * Codigo que identifica o endereco de um
     * 						cliente/prospect (idpessoaendereco no Vivonet).
     */
    public void setCodigo(java.math.BigInteger codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the codigoEndSistemaOrigem value for this Endereco.
     * 
     * @return codigoEndSistemaOrigem   * Codigo que identifica o endereco no sistema
     * 						origem. Para atlys, essa informacao representa a
     * 						coluna ADDR_SEQ_NBR da tabela CUST_ADDR.
     */
    public java.math.BigInteger getCodigoEndSistemaOrigem() {
        return codigoEndSistemaOrigem;
    }


    /**
     * Sets the codigoEndSistemaOrigem value for this Endereco.
     * 
     * @param codigoEndSistemaOrigem   * Codigo que identifica o endereco no sistema
     * 						origem. Para atlys, essa informacao representa a
     * 						coluna ADDR_SEQ_NBR da tabela CUST_ADDR.
     */
    public void setCodigoEndSistemaOrigem(java.math.BigInteger codigoEndSistemaOrigem) {
        this.codigoEndSistemaOrigem = codigoEndSistemaOrigem;
    }


    /**
     * Gets the caixaPostal value for this Endereco.
     * 
     * @return caixaPostal   * Numero da caixa postal
     */
    public java.lang.String getCaixaPostal() {
        return caixaPostal;
    }


    /**
     * Sets the caixaPostal value for this Endereco.
     * 
     * @param caixaPostal   * Numero da caixa postal
     */
    public void setCaixaPostal(java.lang.String caixaPostal) {
        this.caixaPostal = caixaPostal;
    }


    /**
     * Gets the logradouro value for this Endereco.
     * 
     * @return logradouro   * Nome da rua
     */
    public java.lang.String getLogradouro() {
        return logradouro;
    }


    /**
     * Sets the logradouro value for this Endereco.
     * 
     * @param logradouro   * Nome da rua
     */
    public void setLogradouro(java.lang.String logradouro) {
        this.logradouro = logradouro;
    }


    /**
     * Gets the numero value for this Endereco.
     * 
     * @return numero   * Numero da residencia
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this Endereco.
     * 
     * @param numero   * Numero da residencia
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the complemento value for this Endereco.
     * 
     * @return complemento   * Complemento, como numero do bloco, apartamento.
     */
    public java.lang.String getComplemento() {
        return complemento;
    }


    /**
     * Sets the complemento value for this Endereco.
     * 
     * @param complemento   * Complemento, como numero do bloco, apartamento.
     */
    public void setComplemento(java.lang.String complemento) {
        this.complemento = complemento;
    }


    /**
     * Gets the bairro value for this Endereco.
     * 
     * @return bairro   * Nome do bairro.
     */
    public java.lang.String getBairro() {
        return bairro;
    }


    /**
     * Sets the bairro value for this Endereco.
     * 
     * @param bairro   * Nome do bairro.
     */
    public void setBairro(java.lang.String bairro) {
        this.bairro = bairro;
    }


    /**
     * Gets the cidade value for this Endereco.
     * 
     * @return cidade   * Nome da cidade
     */
    public java.lang.String getCidade() {
        return cidade;
    }


    /**
     * Sets the cidade value for this Endereco.
     * 
     * @param cidade   * Nome da cidade
     */
    public void setCidade(java.lang.String cidade) {
        this.cidade = cidade;
    }


    /**
     * Gets the cep value for this Endereco.
     * 
     * @return cep   * Numero do CEP.
     */
    public java.lang.String getCep() {
        return cep;
    }


    /**
     * Sets the cep value for this Endereco.
     * 
     * @param cep   * Numero do CEP.
     */
    public void setCep(java.lang.String cep) {
        this.cep = cep;
    }


    /**
     * Gets the enderecoPreferencial value for this Endereco.
     * 
     * @return enderecoPreferencial   * Identifica se o endereco e o preferencial para o
     * 						cliente, sendo true o endereco preferencial.
     */
    public java.lang.Boolean getEnderecoPreferencial() {
        return enderecoPreferencial;
    }


    /**
     * Sets the enderecoPreferencial value for this Endereco.
     * 
     * @param enderecoPreferencial   * Identifica se o endereco e o preferencial para o
     * 						cliente, sendo true o endereco preferencial.
     */
    public void setEnderecoPreferencial(java.lang.Boolean enderecoPreferencial) {
        this.enderecoPreferencial = enderecoPreferencial;
    }


    /**
     * Gets the dataCadastro value for this Endereco.
     * 
     * @return dataCadastro   * Data em que o endereco foi cadastrado.
     */
    public java.util.Date getDataCadastro() {
        return dataCadastro;
    }


    /**
     * Sets the dataCadastro value for this Endereco.
     * 
     * @param dataCadastro   * Data em que o endereco foi cadastrado.
     */
    public void setDataCadastro(java.util.Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    /**
     * Gets the dataExpiracao value for this Endereco.
     * 
     * @return dataExpiracao   * Data em que o endereco nao existe mais para
     * 						aquela pessoa
     */
    public java.util.Date getDataExpiracao() {
        return dataExpiracao;
    }


    /**
     * Sets the dataExpiracao value for this Endereco.
     * 
     * @param dataExpiracao   * Data em que o endereco nao existe mais para
     * 						aquela pessoa
     */
    public void setDataExpiracao(java.util.Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }


    /**
     * Gets the indicaNumeracaoEndereco value for this Endereco.
     * 
     * @return indicaNumeracaoEndereco   * Indica se existe numeracao para o endereco de
     * 						acordo com o CEP (valores validos, 0, 1 ou 2)
     */
    public java.math.BigInteger getIndicaNumeracaoEndereco() {
        return indicaNumeracaoEndereco;
    }


    /**
     * Sets the indicaNumeracaoEndereco value for this Endereco.
     * 
     * @param indicaNumeracaoEndereco   * Indica se existe numeracao para o endereco de
     * 						acordo com o CEP (valores validos, 0, 1 ou 2)
     */
    public void setIndicaNumeracaoEndereco(java.math.BigInteger indicaNumeracaoEndereco) {
        this.indicaNumeracaoEndereco = indicaNumeracaoEndereco;
    }


    /**
     * Gets the numeroInicial value for this Endereco.
     * 
     * @return numeroInicial   * Numero inicial do intervalo de numeracao
     * 						permitido para o logradouro neste CEP e Bairro.
     */
    public java.math.BigInteger getNumeroInicial() {
        return numeroInicial;
    }


    /**
     * Sets the numeroInicial value for this Endereco.
     * 
     * @param numeroInicial   * Numero inicial do intervalo de numeracao
     * 						permitido para o logradouro neste CEP e Bairro.
     */
    public void setNumeroInicial(java.math.BigInteger numeroInicial) {
        this.numeroInicial = numeroInicial;
    }


    /**
     * Gets the numeroFinal value for this Endereco.
     * 
     * @return numeroFinal   * Numero final do intervalo de numeracao permitido
     * 						para o logradouro neste CEP e Bairro.
     */
    public java.math.BigInteger getNumeroFinal() {
        return numeroFinal;
    }


    /**
     * Sets the numeroFinal value for this Endereco.
     * 
     * @param numeroFinal   * Numero final do intervalo de numeracao permitido
     * 						para o logradouro neste CEP e Bairro.
     */
    public void setNumeroFinal(java.math.BigInteger numeroFinal) {
        this.numeroFinal = numeroFinal;
    }


    /**
     * Gets the indicaLado value for this Endereco.
     * 
     * @return indicaLado   * Indica se a faixa de numeracao se aplica a um
     * 						lado especifico da rua ou nao. Valores validos:
     * 						0 - Par e Impar; 1 - Impar; 2 - Par.
     */
    public java.math.BigInteger getIndicaLado() {
        return indicaLado;
    }


    /**
     * Sets the indicaLado value for this Endereco.
     * 
     * @param indicaLado   * Indica se a faixa de numeracao se aplica a um
     * 						lado especifico da rua ou nao. Valores validos:
     * 						0 - Par e Impar; 1 - Impar; 2 - Par.
     */
    public void setIndicaLado(java.math.BigInteger indicaLado) {
        this.indicaLado = indicaLado;
    }


    /**
     * Gets the descricaoLado value for this Endereco.
     * 
     * @return descricaoLado   * Descricao do lado. Valores validos: Par e Impar,
     * 						Impar ou Par.
     */
    public java.lang.String getDescricaoLado() {
        return descricaoLado;
    }


    /**
     * Sets the descricaoLado value for this Endereco.
     * 
     * @param descricaoLado   * Descricao do lado. Valores validos: Par e Impar,
     * 						Impar ou Par.
     */
    public void setDescricaoLado(java.lang.String descricaoLado) {
        this.descricaoLado = descricaoLado;
    }


    /**
     * Gets the dataUltimaAlteracao value for this Endereco.
     * 
     * @return dataUltimaAlteracao
     */
    public java.util.Calendar getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }


    /**
     * Sets the dataUltimaAlteracao value for this Endereco.
     * 
     * @param dataUltimaAlteracao
     */
    public void setDataUltimaAlteracao(java.util.Calendar dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }


    /**
     * Gets the tipoLogradouro value for this Endereco.
     * 
     * @return tipoLogradouro   * Referencia a entidade TipoLogradouro
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }


    /**
     * Sets the tipoLogradouro value for this Endereco.
     * 
     * @param tipoLogradouro   * Referencia a entidade TipoLogradouro
     */
    public void setTipoLogradouro(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }


    /**
     * Gets the tituloLogradouro value for this Endereco.
     * 
     * @return tituloLogradouro   * Referencia a entidade TituloLogradouro.
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro getTituloLogradouro() {
        return tituloLogradouro;
    }


    /**
     * Sets the tituloLogradouro value for this Endereco.
     * 
     * @param tituloLogradouro   * Referencia a entidade TituloLogradouro.
     */
    public void setTituloLogradouro(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TituloLogradouro tituloLogradouro) {
        this.tituloLogradouro = tituloLogradouro;
    }


    /**
     * Gets the pais value for this Endereco.
     * 
     * @return pais   * Referencia a entidade Pais
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais getPais() {
        return pais;
    }


    /**
     * Sets the pais value for this Endereco.
     * 
     * @param pais   * Referencia a entidade Pais
     */
    public void setPais(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.Pais pais) {
        this.pais = pais;
    }


    /**
     * Gets the tipoEndereco value for this Endereco.
     * 
     * @return tipoEndereco   * Referencia a entidade TipoEndereco.
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }


    /**
     * Sets the tipoEndereco value for this Endereco.
     * 
     * @param tipoEndereco   * Referencia a entidade TipoEndereco.
     */
    public void setTipoEndereco(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }


    /**
     * Gets the enderecoFiscal value for this Endereco.
     * 
     * @return enderecoFiscal   * Referencia a entidade EnderecoFiscal.
     */
    public br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.EnderecoFiscal getEnderecoFiscal() {
        return enderecoFiscal;
    }


    /**
     * Sets the enderecoFiscal value for this Endereco.
     * 
     * @param enderecoFiscal   * Referencia a entidade EnderecoFiscal.
     */
    public void setEnderecoFiscal(br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow.ws.MC.Pessoa.EnderecoFiscal enderecoFiscal) {
        this.enderecoFiscal = enderecoFiscal;
    }


    /**
     * Gets the enderecoAssociadoConta value for this Endereco.
     * 
     * @return enderecoAssociadoConta   * Flag que indica se o endereco do cliente
     * 						posicionado eh o endereco associado a conta do
     * 						cliente. true - endereco associado a conta;
     * 						false - endereco nao associado a conta.
     */
    public java.lang.Boolean getEnderecoAssociadoConta() {
        return enderecoAssociadoConta;
    }


    /**
     * Sets the enderecoAssociadoConta value for this Endereco.
     * 
     * @param enderecoAssociadoConta   * Flag que indica se o endereco do cliente
     * 						posicionado eh o endereco associado a conta do
     * 						cliente. true - endereco associado a conta;
     * 						false - endereco nao associado a conta.
     */
    public void setEnderecoAssociadoConta(java.lang.Boolean enderecoAssociadoConta) {
        this.enderecoAssociadoConta = enderecoAssociadoConta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Endereco)) return false;
        Endereco other = (Endereco) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.codigoEndSistemaOrigem==null && other.getCodigoEndSistemaOrigem()==null) || 
             (this.codigoEndSistemaOrigem!=null &&
              this.codigoEndSistemaOrigem.equals(other.getCodigoEndSistemaOrigem()))) &&
            ((this.caixaPostal==null && other.getCaixaPostal()==null) || 
             (this.caixaPostal!=null &&
              this.caixaPostal.equals(other.getCaixaPostal()))) &&
            ((this.logradouro==null && other.getLogradouro()==null) || 
             (this.logradouro!=null &&
              this.logradouro.equals(other.getLogradouro()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.complemento==null && other.getComplemento()==null) || 
             (this.complemento!=null &&
              this.complemento.equals(other.getComplemento()))) &&
            ((this.bairro==null && other.getBairro()==null) || 
             (this.bairro!=null &&
              this.bairro.equals(other.getBairro()))) &&
            ((this.cidade==null && other.getCidade()==null) || 
             (this.cidade!=null &&
              this.cidade.equals(other.getCidade()))) &&
            ((this.cep==null && other.getCep()==null) || 
             (this.cep!=null &&
              this.cep.equals(other.getCep()))) &&
            ((this.enderecoPreferencial==null && other.getEnderecoPreferencial()==null) || 
             (this.enderecoPreferencial!=null &&
              this.enderecoPreferencial.equals(other.getEnderecoPreferencial()))) &&
            ((this.dataCadastro==null && other.getDataCadastro()==null) || 
             (this.dataCadastro!=null &&
              this.dataCadastro.equals(other.getDataCadastro()))) &&
            ((this.dataExpiracao==null && other.getDataExpiracao()==null) || 
             (this.dataExpiracao!=null &&
              this.dataExpiracao.equals(other.getDataExpiracao()))) &&
            ((this.indicaNumeracaoEndereco==null && other.getIndicaNumeracaoEndereco()==null) || 
             (this.indicaNumeracaoEndereco!=null &&
              this.indicaNumeracaoEndereco.equals(other.getIndicaNumeracaoEndereco()))) &&
            ((this.numeroInicial==null && other.getNumeroInicial()==null) || 
             (this.numeroInicial!=null &&
              this.numeroInicial.equals(other.getNumeroInicial()))) &&
            ((this.numeroFinal==null && other.getNumeroFinal()==null) || 
             (this.numeroFinal!=null &&
              this.numeroFinal.equals(other.getNumeroFinal()))) &&
            ((this.indicaLado==null && other.getIndicaLado()==null) || 
             (this.indicaLado!=null &&
              this.indicaLado.equals(other.getIndicaLado()))) &&
            ((this.descricaoLado==null && other.getDescricaoLado()==null) || 
             (this.descricaoLado!=null &&
              this.descricaoLado.equals(other.getDescricaoLado()))) &&
            ((this.dataUltimaAlteracao==null && other.getDataUltimaAlteracao()==null) || 
             (this.dataUltimaAlteracao!=null &&
              this.dataUltimaAlteracao.equals(other.getDataUltimaAlteracao()))) &&
            ((this.tipoLogradouro==null && other.getTipoLogradouro()==null) || 
             (this.tipoLogradouro!=null &&
              this.tipoLogradouro.equals(other.getTipoLogradouro()))) &&
            ((this.tituloLogradouro==null && other.getTituloLogradouro()==null) || 
             (this.tituloLogradouro!=null &&
              this.tituloLogradouro.equals(other.getTituloLogradouro()))) &&
            ((this.pais==null && other.getPais()==null) || 
             (this.pais!=null &&
              this.pais.equals(other.getPais()))) &&
            ((this.tipoEndereco==null && other.getTipoEndereco()==null) || 
             (this.tipoEndereco!=null &&
              this.tipoEndereco.equals(other.getTipoEndereco()))) &&
            ((this.enderecoFiscal==null && other.getEnderecoFiscal()==null) || 
             (this.enderecoFiscal!=null &&
              this.enderecoFiscal.equals(other.getEnderecoFiscal()))) &&
            ((this.enderecoAssociadoConta==null && other.getEnderecoAssociadoConta()==null) || 
             (this.enderecoAssociadoConta!=null &&
              this.enderecoAssociadoConta.equals(other.getEnderecoAssociadoConta())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getCodigoEndSistemaOrigem() != null) {
            _hashCode += getCodigoEndSistemaOrigem().hashCode();
        }
        if (getCaixaPostal() != null) {
            _hashCode += getCaixaPostal().hashCode();
        }
        if (getLogradouro() != null) {
            _hashCode += getLogradouro().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getComplemento() != null) {
            _hashCode += getComplemento().hashCode();
        }
        if (getBairro() != null) {
            _hashCode += getBairro().hashCode();
        }
        if (getCidade() != null) {
            _hashCode += getCidade().hashCode();
        }
        if (getCep() != null) {
            _hashCode += getCep().hashCode();
        }
        if (getEnderecoPreferencial() != null) {
            _hashCode += getEnderecoPreferencial().hashCode();
        }
        if (getDataCadastro() != null) {
            _hashCode += getDataCadastro().hashCode();
        }
        if (getDataExpiracao() != null) {
            _hashCode += getDataExpiracao().hashCode();
        }
        if (getIndicaNumeracaoEndereco() != null) {
            _hashCode += getIndicaNumeracaoEndereco().hashCode();
        }
        if (getNumeroInicial() != null) {
            _hashCode += getNumeroInicial().hashCode();
        }
        if (getNumeroFinal() != null) {
            _hashCode += getNumeroFinal().hashCode();
        }
        if (getIndicaLado() != null) {
            _hashCode += getIndicaLado().hashCode();
        }
        if (getDescricaoLado() != null) {
            _hashCode += getDescricaoLado().hashCode();
        }
        if (getDataUltimaAlteracao() != null) {
            _hashCode += getDataUltimaAlteracao().hashCode();
        }
        if (getTipoLogradouro() != null) {
            _hashCode += getTipoLogradouro().hashCode();
        }
        if (getTituloLogradouro() != null) {
            _hashCode += getTituloLogradouro().hashCode();
        }
        if (getPais() != null) {
            _hashCode += getPais().hashCode();
        }
        if (getTipoEndereco() != null) {
            _hashCode += getTipoEndereco().hashCode();
        }
        if (getEnderecoFiscal() != null) {
            _hashCode += getEnderecoFiscal().hashCode();
        }
        if (getEnderecoAssociadoConta() != null) {
            _hashCode += getEnderecoAssociadoConta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Endereco.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "Endereco"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEndSistemaOrigem");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "codigoEndSistemaOrigem"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caixaPostal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "caixaPostal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("logradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "logradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("complemento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "complemento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bairro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "bairro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cidade");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "cidade"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cep");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "cep"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enderecoPreferencial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "enderecoPreferencial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCadastro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "dataCadastro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataExpiracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "dataExpiracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicaNumeracaoEndereco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "indicaNumeracaoEndereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroInicial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "numeroInicial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "numeroFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicaLado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "indicaLado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descricaoLado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "descricaoLado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataUltimaAlteracao");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "dataUltimaAlteracao"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoLogradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "tipoLogradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TipoLogradouro"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloLogradouro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "tituloLogradouro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TituloLogradouro"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pais");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "Pais"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEndereco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "tipoEndereco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "TipoEndereco"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enderecoFiscal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "enderecoFiscal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "EnderecoFiscal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enderecoAssociadoConta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Pessoa", "enderecoAssociadoConta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
