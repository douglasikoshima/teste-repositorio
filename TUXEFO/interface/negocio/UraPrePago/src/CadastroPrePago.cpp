// CadastroPrePago.cpp: implementation of the CadastroPrePago class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/CadastroPrePago.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

CadastroPrePago::CadastroPrePago()
{
	tuxfw_getlogger()->debug("CadastroPrePago::CadastroPrePago");
	memset(&m_stPessoaFisica,0,sizeof(stPessoaFisica));
	memset(&m_stLinha,0,sizeof(stLinha));
	this->m_regra = 0;
	this->setControle(false);
	this->setTipoCadastro(TIPO_CADASTRO_URA);
	this->setOperacao(0);
	memset(&m_inProtocolo,0,sizeof(m_inProtocolo));
	memset(&m_inPromocoes,0,sizeof(m_inPromocoes));
	memset(&m_inProdutos,0,sizeof(m_inProdutos));
	memset(&m_inParceiros,0,sizeof(m_inParceiros));
	memset(&m_idCanal,0,sizeof(m_idCanal));
	this->setInterceptacao(true); // o default é interceptar
	this->setEstadoLinha(true); // o default é verificar o estado de linha
	this->setTipoServico(0);
}

CadastroPrePago::~CadastroPrePago()
{
	tuxfw_getlogger()->debug("CadastroPrePago::~CadastroPrePago");

}

int CadastroPrePago::gravarCadastro(stParametrosUren*buffer)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gravarCadastro");
	int retornoBuscaCPF = 0;
	int retornoPessoa = 0;
	int retornoCadastro = 0;
	char idPessoa[21];	
	int existeLinha = 1;
	strcpy(idPessoa,"0");

	// verificar se é controle
	if(!strcmp(buffer->cdControle,"1"))
		this->setControle(true);

	
	// verificar se o estado da linha é Pré-Ativo
	if(this->getOperacao() == 1)
	{
		// se a tag estadoLinha for 1 não vai checar o estado linha
		if(this->getEstadoLinha() == true && !this->m_pessoaLinha.estadoLinha(buffer->cdNumTelefone,buffer->cdDDD,"P"))	
			return ERROR_ESTADO_LINHA;
	}
	else
	if(this->getOperacao() == 3) // verificar se o estado da linha é Ativo ou Barrado
	{
		if(!this->m_pessoaLinha.estadoLinhaTitularidade(buffer->cdNumTelefone,buffer->cdDDD))	
			return ERROR_ESTADO_LINHA;
	}

	// buscar a pessoa por CPF e Nome
	retornoBuscaCPF = this->m_pessoa.getLinhasAtivas(buffer,m_listPessoaLinha);	
	// Regras para novo cadastro
	this->m_regra = 0;
	// Se encontrou a pessoa na base

	// outra regra para o batch, sempre cadastramos uma nova pessoa, desconsideramos a existencia da pessoa na base
	if(this->getEstadoLinha() == false)
		retornoBuscaCPF = 0;

	if(retornoBuscaCPF == 1)
	{
		retornoPessoa = this->getPessoa(idPessoa);
		if(retornoPessoa == TYPE_PREPAGO)
			strcpy(m_stPessoaFisica.idPessoa,"");	
		else
		if(retornoPessoa == TYPE_POSPAGO)
			strcpy(m_stPessoaFisica.idPessoa,"");
		else
			return ERROR_CAMPO_OBRIGATORIO_INVALIDO;
	}
	else if(retornoBuscaCPF == 0)
	{
		// validar campos obrigatórios
		if(this->getOperacao() == 1 || this->getOperacao() == 3){

			if(strlen(buffer->dtNascimento) == 0 && strlen(buffer->endereco) == 0 && 
				strlen(buffer->nrEndereco) == 0 && strlen(buffer->bairro) == 0 &&
				strlen(buffer->municipio) == 0 && strlen(buffer->cep) == 0 &&
				strlen(buffer->cidade) == 0 && strlen(buffer->uf) == 0)
			{
				return ERROR_PESSOA_NAO_ENCONTRADA;
			}	

			if(strlen(buffer->dtNascimento) == 0 || strlen(buffer->endereco) == 0 || 
				strlen(buffer->nrEndereco) == 0 || strlen(buffer->bairro) == 0 ||
				strlen(buffer->municipio) == 0 || strlen(buffer->cep) == 0 ||
				strlen(buffer->cidade) == 0 || strlen(buffer->uf) == 0)
			{
				return ERROR_CAMPO_OBRIGATORIO_INVALIDO;
			}				
		}

		// gerar um novo cadastro
		if((retornoCadastro=this->novoCadastro(buffer)) != 1)
			return retornoCadastro;
	}	

	existeLinha = this->m_pessoaLinha.getLinha(buffer->cdNumTelefone,buffer->cdDDD,m_stLinha);

	if(this->getTipoCadastro() == TIPO_CADASTRO_EXTERNO && existeLinha == 0)
		return ERROR_LINHA_NAO_ENCONTRADA;
	
	// diferente de prepago
	if(strcmp(m_stLinha.sgTipoLinha,"PRÉ") != 0 && strcmp(m_stLinha.sgTipoLinha,"PRÉCHIP") != 0 && 
		strcmp(m_stLinha.sgTipoLinha,"CONT") != 0 && strcmp(m_stLinha.sgTipoLinha,"CONTCHIP") != 0)
		return ERROR_TIPO_LINHA;

	return this->gravarNGIN();
}

int CadastroPrePago::gravarAlteracao(stParametrosUren*buffer)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gravarAlteracao");
	// verificar se é controle
	if(!strcmp(buffer->cdControle,"1"))
		this->setControle(true);
	char idPessoa[256];
	char idPais[21];
	char idUF[21];
	char idTipoEndereco[21];
	int existeLinha = 1;
	char nrLinha[15];
	memset(&idPessoa,0,sizeof(idPessoa));
	memset(&nrLinha,0,sizeof(nrLinha));

	// linha formatada
	sprintf(nrLinha,"%s%s",buffer->cdDDD,buffer->cdNumTelefone);

	// procura a pessoa associada à linha, caso não encontrar vamos retornar 01- conta não cadastrada/desligada
	// O correto deveria ser 09 = Não localizado cadastro na Base e Transação sem dados
	// O documento ETD foi definido assim ETD- Redução de TMA - URA1.4.doc
	if(!this->m_pessoaLinha.getPessoaLinha(buffer->cdNumTelefone,buffer->cdDDD,idPessoa))
		return ERROR_LINHA_NAO_ENCONTRADA;

	// regra para alteração
	this->m_regra = 1;
	this->m_pessoaEndereco.consultarEnderecosDB(idPessoa,m_listEnderecoPrePago);

	EnderecoPrePago *endereco = NULL;
	if(m_listEnderecoPrePago.sizeOf() > 0)
		endereco = (EnderecoPrePago*) m_listEnderecoPrePago.get(0);
	else
		return ERROR_ENDERECO_NAO_ENCONTRADO;

	this->m_pessoaEndereco.getUF(buffer->uf,idUF);
	this->m_pessoaEndereco.getPais("BR",idPais);	
	// validação de linha
	existeLinha = this->m_pessoaLinha.getLinha(buffer->cdNumTelefone,buffer->cdDDD,m_stLinha);
	if(this->getTipoCadastro() == TIPO_CADASTRO_EXTERNO && existeLinha == 0)
		return ERROR_LINHA_NAO_ENCONTRADA;
	
	// diferente de prepago
	if(strcmp(m_stLinha.sgTipoLinha,"PRÉ") != 0 && strcmp(m_stLinha.sgTipoLinha,"PRÉCHIP") != 0 && 
		strcmp(m_stLinha.sgTipoLinha,"CONT") != 0 && strcmp(m_stLinha.sgTipoLinha,"CONTCHIP") != 0)
		return ERROR_TIPO_LINHA;

	tuxfw_getlogger()->debug("chamar serviço AbaServico");
	XMLGen xml;	
	xml.addItem("acao","ALTERAR");
	xml.createTag("DadosLupaCliente");
	xml.addProp("xmlns","cliente.fo.vivo.com.br/vo");
	xml.addItem("nrLinha",nrLinha);
	xml.closeTag();
	xml.createTag("DadosAbaLupaCliente");
		xml.addProp("xmlns","cliente.fo.vivo.com.br/vo");
		xml.createTag("EnderecoVO");
			xml.addItem("idEndereco",endereco->getIdEndereco());
			xml.createTag("TipoEnderecoVO");
			xml.addItem("idTipoEndereco",endereco->getIdTipoEnderecoSelecionado());
			xml.closeTag();
			xml.addItem("nmTipoLogradouro","Rua");
			xml.addItem("nmTituloLogradouro","Rua");
			xml.addItem("nmLogradouro",buffer->endereco);
			xml.addItem("nmBairro",buffer->bairro);
			xml.addItem("nmMunicipio",buffer->municipio);
			xml.addItem("nrEndereco",buffer->nrEndereco);
			xml.addItem("dsEnderecoComplemento",buffer->complemento);
			xml.addItem("nrCEP",buffer->cep);
			xml.createTag("UFVO");
				xml.addItem("idUF",idUF);
			xml.closeTag();
			xml.createTag("PaisVO");
				xml.addItem("idPais",idPais);
			xml.closeTag();
		xml.closeTag();
	xml.closeTag();

	TuxMessage o_inputMessage;
	TuxRemoteService o_remoteService;
	char statusTextCHAR[200];
	o_inputMessage.setUser("30");
	o_inputMessage.setMessageBody(&xml);
	o_inputMessage.setService("AbaEndereco");
	o_remoteService.setServiceName("AbaEndereco");
	o_remoteService.setInputMessage(&o_inputMessage);

	if(o_remoteService.remoteCall() != TUXFWRET_OK)
	{
		tuxfw_getlogger()->debug("Erro de acesso ao serviço AbaEndereco");
		throw new TuxBasicSvcException("11E0999","Erro de acesso ao serviço AbaEndereco");
	}

	char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
	char* statusText = o_remoteService.getOutputMessage()->getStatusText();
	tuxfw_getlogger()->debug("CadastroPrePago::gravarAlteracao:LastStatusCode: '%s'", statusCode);
	tuxfw_getlogger()->debug("CadastroPrePago::gravarAlteracao:LastStatusText: '%s'", statusText);
	tuxfw_getlogger()->debug("CadastroPrePago::gravarAlteracao:Liberando statusCode");
	tuxfw_getlogger()->debug("CadastroPrePago::gravarAlteracao:Liberando statusText");

	strncpy(statusTextCHAR,statusText,200);
	if(strlen(statusText) < 200)
		statusTextCHAR[strlen(statusText)]=0;
	else
		statusTextCHAR[200-1]=0;

	if((statusCode != NULL) && (strlen(statusCode) > 3) && (statusCode[2] == 'E'))
	{
		tuxfw_getlogger()->debug("Erro de acesso ao Serviço");
		if(!strcmp(statusCode,"12E1252"))
			throw new TuxBasicSvcException("00W0599",statusTextCHAR);
		free(statusCode);	
		free(statusText);
		throw new TuxBasicSvcException("00W0100",statusTextCHAR);
	}

	return 1;
}

int CadastroPrePago::gravarTrocaTitularidade(stParametrosUren*buffer)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gravarTrocaTitularidade");
	return this->gravarCadastro(buffer);
}
int CadastroPrePago::gravar(stParametrosUren*buffer)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gravar");
	int retornoBuscaCPF = 0;
	int retornoPessoa = 0;
	int retornoCadastro = 0;
	char idPessoa[21];	
	int existeLinha = 1;
	strcpy(idPessoa,"0");

	// verificar se é controle
	if(!strcmp(buffer->cdControle,"1"))
		this->setControle(true);

	// verificar se o estado da linha é A P ou B
	if(this->getEstadoLinha() == true && !this->m_pessoaLinha.estadoLinhaAtivo(buffer->cdNumTelefone,buffer->cdDDD))
	{
		return ERROR_ESTADO_LINHA;
	}

	// buscar a pessoa por CPF e Nome
	retornoBuscaCPF = this->m_pessoa.getLinhasAtivas(buffer,m_listPessoaLinha);	
	// Se encontrou a pessoa na base
	if(retornoBuscaCPF == 1)
	{
		retornoPessoa = this->getPessoa(idPessoa);
		if(retornoPessoa == TYPE_PREPAGO)
		{
			this->m_regra = 1;
			strcpy(m_stPessoaFisica.idPessoa,idPessoa);			
		}
		else
		if(retornoPessoa == TYPE_POSPAGO)
		{
			this->m_regra = 0;
			strcpy(m_stPessoaFisica.idPessoa,"");
		}
		else
			retornoBuscaCPF = 0;
	}

	if(retornoBuscaCPF == 0)
	{
		// gerar um novo cadastro
		this->m_regra = 0;
		if((retornoCadastro=this->novoCadastro(buffer)) != 1)
			return retornoCadastro;
	} 

	existeLinha = this->m_pessoaLinha.getLinha(buffer->cdNumTelefone,buffer->cdDDD,m_stLinha);

	if(this->getTipoCadastro() == TIPO_CADASTRO_EXTERNO && existeLinha == 0)
		return ERROR_LINHA_NAO_ENCONTRADA;
	
	// diferente de prepago
	if(strcmp(m_stLinha.sgTipoLinha,"PRÉ") != 0 && strcmp(m_stLinha.sgTipoLinha,"PRÉCHIP") != 0 && 
		strcmp(m_stLinha.sgTipoLinha,"CONT") != 0 && strcmp(m_stLinha.sgTipoLinha,"CONTCHIP") != 0)
		return ERROR_TIPO_LINHA;

	return this->gravarNGIN();
}

int CadastroPrePago::novoCadastro(stParametrosUren*buffer)
{
	tuxfw_getlogger()->debug("CadastroPrePago::novoCadastro()");
	Documento *documento = NULL;
	EnderecoPrePago *endereco = NULL;
	EnderecoPrePago *enderecoNovo = NULL;
	int findEndereco = 0;
	char idPais[21];
	char idUF[21];
	char idTipoEndereco[21];
	char dtNascimento[11];
	this->m_regra = 0;
	memset(&dtNascimento,0,11);
	
	if (this->getTipoServico() != TIPO_GRAVAPREPAGO) {
		findEndereco = this->m_pessoaEndereco.verificaEndereco(buffer->cep,buffer->endereco,m_listEnderecoPrePagoNovo);
	}

	Util::formatDate(buffer->dtNascimento,dtNascimento);

	strcpy(m_stPessoaFisica.dtNascimento,dtNascimento);
	strcpy(m_stPessoaFisica.nmPessoa,buffer->nomeCliente);
	strcpy(m_stPessoaFisica.idEstadoCivil,"0");
	// buscar o idSexo a partir da tag de entrada
	if(this->m_pessoaFisica.getSexoBySigla(buffer->sexo,m_stPessoaFisica.idSexo) == 0)
	{
	this->m_pessoaFisica.getSexoBySigla("NC",m_stPessoaFisica.idSexo);
	}
	this->m_pessoaEndereco.getPais("BR",idPais);		
	documento = new Documento();
	documento->setIdTipoDocumentoSelecionado("CPF");
	documento->setNrDocumento(buffer->cpf);
	m_listDocumento.add(documento);
	endereco = new EnderecoPrePago();
	
	if(findEndereco == 1)
	{
		enderecoNovo = (EnderecoPrePago*)m_listEnderecoPrePagoNovo.get(0);
		endereco->setDsLogradouro(enderecoNovo->getDsLogradouro());
		endereco->setDsMunicipio(enderecoNovo->getDsMunicipio());
		endereco->setDsBairro(enderecoNovo->getDsBairro());	
		endereco->setDsUFEndereco(enderecoNovo->getDsUFEndereco());
		endereco->setDsTipoLogradouro(enderecoNovo->getDsTipoLogradouro());
		endereco->setDsTituloLogradouro(enderecoNovo->getDsTituloLogradouro());
		this->m_pessoaEndereco.getUF(enderecoNovo->getDsUFEndereco(),idUF);
	}
	else
	{
		endereco->setDsLogradouro(buffer->endereco);
		endereco->setDsMunicipio(buffer->municipio);
		endereco->setDsBairro(buffer->bairro);	
		endereco->setDsUFEndereco(buffer->uf);
		endereco->setDsTipoLogradouro("Rua");
		endereco->setDsTituloLogradouro("Rua");
		endereco->setInEnderecoSujo(1);
		this->m_pessoaEndereco.getUF(buffer->uf,idUF);
	}
	
	this->m_pessoaEndereco.getTipoEndereco(idTipoEndereco);

	endereco->setNrCEP(buffer->cep);
	endereco->setInAtualEndereco("1");
	endereco->setDsUFEndereco(buffer->uf);
	endereco->setIdPaisEndereco(idPais);
	endereco->setDsPaisEndereco("BR");
	endereco->setIdUFEndereco(idUF);
	endereco->setIdTipoEnderecoSelecionado(idTipoEndereco);
	endereco->setNrEndereco(buffer->nrEndereco);
	endereco->setDsComplementoEndereco(buffer->complemento);
	m_listEnderecoPrePago.add(endereco);
	
	// tratar tag telefoneContato
	if (buffer->telefoneContato != NULL && strlen(buffer->telefoneContato) > 0) {
		ListaContato *contato = new ListaContato();	
		contato->setIdTipoContatoSelecionado("3");
		contato->setDsContato(buffer->telefoneContato);
		contato->setNovoCadastro(true);
		m_listListaContato.add(contato);
	}
	
	return 1;
}

stPessoaLinha* CadastroPrePago::getPrimeiraPessoa(char*tipoLinha[],int sizeTipo,char*estadoLinha[],int sizeEstado)
{
	tuxfw_getlogger()->debug("CadastroPrePago::getPrimeiraPessoa");

	for(int j=0;j<m_listPessoaLinha.sizeOf();j++)
	{
		stPessoaLinha* pessoa = (stPessoaLinha*)m_listPessoaLinha.get(j);
		for(int i=0;i<sizeTipo;i++)
		{
			if(!strcmp(pessoa->sgTipoLinha,tipoLinha[i]))
		{
				tuxfw_getlogger()->debug("achou Pessoa %s",tipoLinha[i]);
				// checar o estado da linha
				for(int k=0;k<sizeEstado;k++){
					if(!strcmp(pessoa->estadoLinha,estadoLinha[k]))
					{
						tuxfw_getlogger()->debug("achou EstadoLinha %s",estadoLinha[k]);
						return pessoa;
					}
				}
			}
		}
	}
	return NULL;
}

int CadastroPrePago::getPessoa(char*idPessoa)
{	
	tuxfw_getlogger()->debug("CadastroPrePago::getPessoa");
	char *arrayTipoLinhaPre[] = {"PRÉ","PRÉCHIP","CONT","CONTCHIP"};
	char *arrayTipoLinhaPos[] = {"POS","POSCHIP"};
	char *arrayEstadoLinhaPre[] = {"A","B"};
	char *arrayEstadoLinhaPos[] = {"A"};

	stPessoaLinha *pessoaNovaPre = getPrimeiraPessoa(arrayTipoLinhaPre,2,arrayEstadoLinhaPre,2);
	stPessoaLinha *pessoaNovaPos = getPrimeiraPessoa(arrayTipoLinhaPos,2,arrayEstadoLinhaPos,1);


	if(pessoaNovaPos!=NULL)
	{
		tuxfw_getlogger()->debug("TRAZER DADOS DA PESSOA POS");
	
		if(validaCampoObrigatorio(pessoaNovaPos->idPessoa) == 1)
		{
			strcpy(idPessoa,pessoaNovaPos->idPessoa);
			return TYPE_POSPAGO;
		}

	}
	

	if(pessoaNovaPre!=NULL)
	{
		tuxfw_getlogger()->debug("TRAZER DADOS DA PESSOA PRE");

		if(validaCampoObrigatorio(pessoaNovaPre->idPessoa) == 1)
		{
			strcpy(idPessoa,pessoaNovaPre->idPessoa);
			return TYPE_PREPAGO;
		}
	}
	// limpar dados
	memset(&m_stPessoaFisica,0,sizeof(m_stPessoaFisica));
	m_listDocumento.removeAll();
	m_listEnderecoPrePago.removeAll();
	m_listListaContato.removeAll();
	
	return 0;
}

int CadastroPrePago::validaCampoObrigatorio(char*idPessoa)
{
	tuxfw_getlogger()->debug("CadastroPrePago::validaCampoObrigatorio");
	tuxfw_getlogger()->debug("idPessoa = %s",idPessoa);

	if(this->getEstadoLinha() == false)
	{
		tuxfw_getlogger()->debug("Não é feito a validação de campos obrigatórios");
		return 1;
	}


	memset(&m_stPessoaFisica,0,sizeof(m_stPessoaFisica));
	m_listDocumento.removeAll();
	m_listEnderecoPrePago.removeAll();
	m_listListaContato.removeAll();

	this->m_pessoaFisica.getDadosPessoa(idPessoa,m_stPessoaFisica);
	this->m_pessoaDocumento.getDocumentos(idPessoa,m_listDocumento);
	this->m_pessoaEndereco.listEnderecos(idPessoa,m_listEnderecoPrePago);
	this->m_pessoaComunicacao.listPessoaComunicacao(idPessoa,m_listListaContato);		


	// validar dados de pessoa
	if(Util::isNull(m_stPessoaFisica.nmPessoa))
	{
		tuxfw_getlogger()->debug("Campo nmPessoa nulo");
		return 0;
	}
	else
	if(Util::isNull(m_stPessoaFisica.idSexo))
	{
		tuxfw_getlogger()->debug("Campo idSexo nulo");
		return 0;
	}	
	// validar documento
	if(m_listDocumento.sizeOf() == 0)
	{
		tuxfw_getlogger()->debug("Não existe documento para pessoa");
		return 0;
	}
	int i = 0;
	for(i = 0;i<m_listDocumento.sizeOf();i++)
	{
		Documento *doc = (Documento*) m_listDocumento.get(i);
		if(Util::isNull(doc->getNrDocumento()))
	{
			tuxfw_getlogger()->debug("Campo nrDocumento nulo");
			return 0;
		}
		else
		if(Util::isNull(doc->getIdTipoDocumentoSelecionado()))
		{
			tuxfw_getlogger()->debug("Campo idTipoDocumento nulo");
			return 0;
		}
	
	}
	// validar lista de contato
	if(m_listListaContato.sizeOf() == 0)
	{
		tuxfw_getlogger()->debug("Não existe contato para pessoa");
		return 0;
	}
	for(i = 0;i<m_listListaContato.sizeOf();i++)
	{
		ListaContato *contato = (ListaContato*) m_listListaContato.get(i);
		if(Util::isNull(contato->getIdTipoContatoSelecionado()))
		{			
			tuxfw_getlogger()->debug("Campo idTipoContato nulo");
			return 0;
		}
		else
		if(Util::isNull(contato->getDsContato()))
		{
			tuxfw_getlogger()->debug("Campo dsContato nulo");
			return 0;
		}
	}
	// validar endereço
	if(m_listEnderecoPrePago.sizeOf() == 0)
			{
		tuxfw_getlogger()->debug("Não existe endereço para pessoa");
		return 0;
			}

	for(i = 0; i<m_listEnderecoPrePago.sizeOf();i++)
	{
		EnderecoPrePago *endereco = (EnderecoPrePago*) m_listEnderecoPrePago.get(i);
		if(Util::isNull(endereco->getNrEndereco()))
		{
			tuxfw_getlogger()->debug("Campo nrEndereco nulo");
			return 0;
		}
		else
		if(Util::isNull(endereco->getDsLogradouro()))
		{
			tuxfw_getlogger()->debug("Campo dsLogradouro nulo");
			return 0;
		}
		else
		if(Util::isNull(endereco->getDsBairro()))
			{
			tuxfw_getlogger()->debug("Campo dsBairro nulo");
			return 0;
			}
		else
		if(Util::isNull(endereco->getDsMunicipio()))
		{
			tuxfw_getlogger()->debug("Campo dsMunicipio nulo");
			return 0;
		}		
		else
		if(Util::isNull(endereco->getNrCEP()))
		{
			tuxfw_getlogger()->debug("Campo nrCEP nulo");
			return 0;
	}	
		else
		if(Util::isNull(endereco->getIdUFEndereco()))
	{
			tuxfw_getlogger()->debug("Campo idUF nulo");
			return 0;
	}
	else
		if(Util::isNull(endereco->getIdPaisEndereco()))
	{
			tuxfw_getlogger()->debug("Campo idPais nulo");
			return 0;
	}
		else
		if(Util::isNull(endereco->getIdTipoEnderecoSelecionado()))
		{
			tuxfw_getlogger()->debug("Campo idTipoEndereco nulo");
	return 0;
		}		
	}

	return 1;
}

int  CadastroPrePago::gravarNGIN()
{
	tuxfw_getlogger()->debug("CadastroPrePago::gravarNGIN");
	XMLGen xml;
	this->gerarXMLCadastro(&xml);
	TuxMessage o_inputMessage;
	TuxRemoteService o_remoteService;
	char statusTextCHAR[200];
	o_inputMessage.setUser("30");
	o_inputMessage.setMessageBody(&xml);
	o_inputMessage.setService("DPPINSPESSOA");
	o_remoteService.setServiceName("DPPINSPESSOA");
	o_remoteService.setInputMessage(&o_inputMessage);

	if(o_remoteService.remoteCall() != TUXFWRET_OK)
	{
		tuxfw_getlogger()->debug("Erro de acesso ao serviço PPINSPESSOA");
		throw new TuxBasicSvcException("11E0999","Erro de acesso ao serviço PPINSPESSOA");
	}

	char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
	char* statusText = o_remoteService.getOutputMessage()->getStatusText();
	tuxfw_getlogger()->debug("CadastroPrePago::gravarNGIN:LastStatusCode: '%s'", statusCode);
	tuxfw_getlogger()->debug("CadastroPrePago::gravarNGIN:LastStatusText: '%s'", statusText);
	tuxfw_getlogger()->debug("CadastroPrePago::gravarNGIN:Liberando statusCode");
	tuxfw_getlogger()->debug("CadastroPrePago::gravarNGIN:Liberando statusText");

	strncpy(statusTextCHAR,statusText,200);
	if(strlen(statusText) < 200)
		statusTextCHAR[strlen(statusText)]=0;
	else
		statusTextCHAR[200-1]=0;

	if((statusCode != NULL) && (strlen(statusCode) > 3) && (statusCode[2] == 'E'))
	{
		tuxfw_getlogger()->debug("Erro de acesso ao Serviço");
		if(!strcmp(statusCode,"12E1252"))
			throw new TuxBasicSvcException("00W0599",statusTextCHAR);
		free(statusCode);	
		free(statusText);
		throw new TuxBasicSvcException("00W0100",statusTextCHAR);
	}

	return 1;
}

DOMNode* CadastroPrePago::gerarXMLCadastro(XMLGen*xml)
{	
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLCadastro");
	this->gerarXMLPrincipal(xml,"Cliente");
	this->gerarXMLPrincipal(xml,"Usuario");
	this->gerarXMLLinha(xml);
	xml->addItem("inClienteUsuario","1");
	xml->addItem("usrNaoInformado","1");
	if(this->getInterceptacao())
		xml->addItem("interceptacao","1");
	else
		xml->addItem("interceptacao","0");
	if(this->getOperacao() == 2)
		xml->addItem("dsTipoAcao","ALTERAR");
	else
	xml->addItem("dsTipoAcao","CADASTRAR");
	if(strlen(this->getIdCanal()) > 0)
		xml->addItem("idCanal",this->getIdCanal());
	else
		xml->addItem("idCanal","9");
	xml->addItem("idGrupoAbertura","1534");

	if(this->getOperacao() != 2){
		xml->createTag("PUP");
		xml->addItem("inProtocolo",this->getInProtocolo());
		xml->addItem("inPromocoes",this->getInPromocoes());
		xml->addItem("inProdutos",this->getInProdutos());
		xml->addItem("inParceiros",this->getInParceiros());
		xml->closeTag();
	}

	return NULL;
}

void CadastroPrePago::gerarXMLLinha(XMLGen*xml)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLLinha");
	xml->createTag("Linha");
	xml->addItem("idLinha",this->m_stLinha.idLinha);
	xml->addItem("nrLinha",this->m_stLinha.nrLinha);
	xml->closeTag();
}

void CadastroPrePago::gerarXMLPrincipal(XMLGen*xml,char*nomeTag)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLPrincipal");
	xml->createTag(nomeTag);
	xml->createTag("PrePagoPessoaVO");
	xml->addItem("idPessoa","");
	xml->addItem("inTipoPessoa","PF");
	// Documento
	this->gerarXMLDoc(xml);
	xml->createTag("Contato");
	this->gerarXMLContato(xml);
	xml->closeTag();
	xml->createTag("Endereco");
	this->gerarXMLEndereco(xml);
	xml->closeTag();

	// PF
	xml->createTag("PF");
	xml->addItem("nmPessoa",this->m_stPessoaFisica.nmPessoa);
	xml->addItem("idSexoSelecionado",this->m_stPessoaFisica.idSexo);
	xml->addItem("idEscolaridadeSelecionado",this->m_stPessoaFisica.idEscolaridade);
	xml->addItem("idOcupacaoSelecionado",this->m_stPessoaFisica.idOcupacao);
	xml->addItem("idEstadoCivilSelecionado",this->m_stPessoaFisica.idEstadoCivil);
	xml->addItem("dsProfissao",this->m_stPessoaFisica.dsOcupacao);
	xml->addItem("idCPR","");
	xml->addItem("nrCPR","");
	xml->addItem("dtNascimento",this->m_stPessoaFisica.dtNascimento);
	xml->closeTag();
	
	// Se for controle colocar a tag cdControle
	if(this->isControle())
		xml->addItem("cdControle","1");
	else
		xml->addItem("cdControle","0");
	
	xml->closeTag();
	xml->closeTag();
}

void CadastroPrePago::gerarXMLDocumento(XMLGen*xml)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLDocumento");
	for(int i=0;i<this->m_listDocumento.sizeOf();i++)
	{
		Documento *documento = (Documento*) this->m_listDocumento.get(i);
		xml->createTag("Documento");
		if(this->m_regra == 1)
		{
			xml->addItem("idDocumento",documento->getIdDocumento());
		}
		else
		{
			xml->addItem("idDocumento","");
		}
		xml->addItem("idTipoDocumentoSelecionado",documento->getIdTipoDocumentoSelecionado());
		xml->addItem("nrDocumento",documento->getNrDocumento());
		xml->addItem("dtExpedicao",documento->getDtExpedicao());
		xml->addItem("dsOrgaoEmissor",documento->getDsOrgaoEmissor());
		xml->addItem("idUFSelecionado",documento->getIdUfSelecionado());
		xml->closeTag();		
	}
}
void CadastroPrePago::gerarXMLDoc(XMLGen*xml)
{
   char nrDocAnterior[256];
   memset( nrDocAnterior, 0x0, sizeof(nrDocAnterior) );
   
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLDoc");
	for(int i=0;i<this->m_listDocumento.sizeOf();i++)
	{
		Documento *documento = (Documento*) this->m_listDocumento.get(i);
      
      tuxfw_getlogger()->debug("nrDocAnterior             [%s]",nrDocAnterior);
      tuxfw_getlogger()->debug("documento->getNrDocumento [%s]",(char*)documento->getNrDocumento());
      if ( strcmp(nrDocAnterior,(char *)documento->getNrDocumento()) )
      {
         strcpy( nrDocAnterior,(char*)documento->getNrDocumento() );
         tuxfw_getlogger()->debug("Passou documento valido");
         
		xml->createTag("Doc");
		if(this->m_regra == 1)
		{
			xml->addItem("idDoc",documento->getIdDocumento());
		}
		else
		{
			xml->addItem("idDoc","");
		}		
		xml->addItem("idTipoDoc",documento->getIdTipoDocumentoSelecionado());
		xml->addItem("nrDoc",documento->getNrDocumento());
		xml->addItem("dtExpedicaoDoc",documento->getDtExpedicao());
		xml->addItem("dsOrgaoEmissorDoc",documento->getDsOrgaoEmissor());
		xml->addItem("idUFDoc",documento->getIdUfSelecionado());
		xml->closeTag();		
      }
	}
}
void CadastroPrePago::gerarXMLEndereco(XMLGen*xml)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLEndereco");
	for(int i=0;i<this->m_listEnderecoPrePago.sizeOf();i++)
	{
		EnderecoPrePago *endereco = (EnderecoPrePago*) this->m_listEnderecoPrePago.get(i);
		xml->createTag("EnderecoPrePagoVO");
		if(this->m_regra == 1)
		{
			xml->addItem("idEndereco",endereco->getIdEndereco());
		}
		else
		{
			xml->addItem("idEndereco","");
		}
		xml->addItem("inAtualEndereco",endereco->getInAtualEndereco());
		xml->addItem("idTipoEnderecoSelecionado",endereco->getIdTipoEnderecoSelecionado());
		xml->addItem("dsTituloLogradouro",endereco->getDsTituloLogradouro());
		xml->addItem("dsTipoLogradouro",endereco->getDsTipoLogradouro());
		xml->addItem("dsLogradouro",endereco->getDsLogradouro());
		xml->addItem("nrEndereco",endereco->getNrEndereco());
		xml->addItem("dsComplementoEndereco",endereco->getDsComplementoEndereco());
		xml->addItem("dsBairro",endereco->getDsBairro());
		xml->addItem("dsMunicipio",endereco->getDsMunicipio());
		xml->addItem("nrCEP",endereco->getNrCEP());
		xml->addItem("inEnderecoSujo",endereco->getInEnderecoSujo());

		xml->createTag("UF");
		xml->addItem("idUFEndereco",endereco->getIdUFEndereco());
		xml->addItem("dsUFEndereco",endereco->getDsUFEndereco());
		xml->closeTag();
		xml->createTag("Pais");
		xml->addItem("idPaisEndereco",endereco->getIdPaisEndereco());
		xml->addItem("dsPaisEndereco",endereco->getDsPaisEndereco());
		xml->closeTag();
		xml->closeTag();	
	}
}
void CadastroPrePago::gerarXMLContato(XMLGen*xml)
{
	tuxfw_getlogger()->debug("CadastroPrePago::gerarXMLContato");
	for(int i=0;i<this->m_listListaContato.sizeOf();i++)
	{
		ListaContato *listaContato = (ListaContato*) this->m_listListaContato.get(i);
		xml->createTag("ListCont");
		if(this->m_regra == 1)
		{
		xml->addItem("idContato",listaContato->getIdContato());
		}
		else
		{
			xml->addItem("idContato","");
		}		
		xml->addItem("idTipoContatoSelecionado",listaContato->getIdTipoContatoSelecionado());
		xml->addItem("dsContato",listaContato->getDsContato());
		xml->addItem("nmContato",listaContato->getNmContato());
		if (listaContato->getNovoCadastro()) {
			xml->addItem("credenciada","true");
		}
		xml->closeTag();		
	}
}

void CadastroPrePago::setControle(bool controle)
{
	this->m_isControle = controle;
}
bool CadastroPrePago::isControle()
{
	return this->m_isControle;
}

void CadastroPrePago::setTipoCadastro(int tipo)
{
	this->m_tipoCadastro = tipo;
}

int CadastroPrePago::getTipoCadastro()
{
	return this->m_tipoCadastro;
}

void CadastroPrePago::setTipoServico(int tipo)
{
	this->m_tipoServico = tipo;
}

int CadastroPrePago::getTipoServico()
{
	return this->m_tipoServico;
}

void CadastroPrePago::setOperacao(int operacao)
{
	this->m_operacao = operacao;
}
int CadastroPrePago::getOperacao()
{
	return this->m_operacao;
}

void CadastroPrePago::setInProtocolo(char*inProtocolo)
{
	if(inProtocolo != NULL && strlen(inProtocolo) == 1)
		strcpy(this->m_inProtocolo,inProtocolo);
}
void CadastroPrePago::setInPromocoes(char*inPromocoes)
{
	if(inPromocoes != NULL && strlen(inPromocoes) == 1)
		strcpy(this->m_inPromocoes,inPromocoes);
}
void CadastroPrePago::setInProdutos(char*inProdutos)
{
	if(inProdutos != NULL && strlen(inProdutos) == 1)
		strcpy(this->m_inProdutos,inProdutos);
}
void CadastroPrePago::setInParceiros(char*inParceiros)
{
	if(inParceiros != NULL && strlen(inParceiros) == 1)
		strcpy(this->m_inParceiros,inParceiros);
}

char* CadastroPrePago::getInProtocolo()
{
	return this->m_inProtocolo;
}
char* CadastroPrePago::getInPromocoes()
{
	return this->m_inPromocoes;
}
char* CadastroPrePago::getInProdutos()
{
	return this->m_inProdutos;
}
char* CadastroPrePago::getInParceiros()
{
	return this->m_inParceiros;
}

void CadastroPrePago::getDadosLinha()
{
	memset(&this->m_idPessoaDepara,0,sizeof(m_idPessoaDepara));
	memset(&this->m_idLinhaTelefonica,0,sizeof(m_idLinhaTelefonica));
	this->m_pessoa.getDadosLinha(this->m_stLinha.idLinha,this->m_idPessoaDepara);	
	strcpy(this->m_idLinhaTelefonica,this->m_stLinha.idLinha);
}

char* CadastroPrePago::getIdLinhaTelefonica()
{
	return this->m_idLinhaTelefonica;
}
char* CadastroPrePago::getIdPessoaDepara()
{
	return this->m_idPessoaDepara;
}

bool CadastroPrePago::getInterceptacao()
{
	return this->m_interceptacao;
}
void CadastroPrePago::setInterceptacao(bool interceptacao)
{
	this->m_interceptacao = interceptacao;
}

bool CadastroPrePago::getEstadoLinha()
{
	return this->m_estadoLinha;
}
void CadastroPrePago::setEstadoLinha(bool estadoLinha)
{
	this->m_estadoLinha = estadoLinha;
}

void CadastroPrePago::setIdCanal(char*idCanal)
{
	if(idCanal != NULL)
	{
		Util::alltrim(idCanal);
		if(strlen(idCanal) < 15 && strlen(idCanal) > 0)
			strcpy(this->m_idCanal,idCanal);
	}	
	tuxfw_getlogger()->debug("valor de canal = %s",this->m_idCanal);
}
char*CadastroPrePago::getIdCanal()
{
	return this->m_idCanal;
}



int CadastroPrePago::gravarRecadastro( stParametrosUren * buffer )
{
    tuxfw_getlogger()->debug("> CadastroPrePago::gravarRecadastro");
    // verificar se é controle
    if(!strcmp(buffer->cdControle,"1"))
        this->setControle(true);
    char idPessoa[256];
    char idPais[21];
    char idUF[21];
    char idTipoEndereco[21];
    int existeLinha = 1;
    char nrLinha[15];
    memset( &idPessoa, 0x0, sizeof(idPessoa));
    memset( &nrLinha , 0x0, sizeof(nrLinha));

    // linha formatada
    sprintf(nrLinha,"%s%s",buffer->cdDDD,buffer->cdNumTelefone);

    // procura a pessoa associada à linha, caso não encontrar vamos retornar 01- conta não cadastrada/desligada
    // O correto deveria ser 09 = Não localizado cadastro na Base e Transação sem dados
    // O documento ETD foi definido assim ETD- Redução de TMA - URA1.4.doc
    if(!this->m_pessoaLinha.getPessoaLinha(buffer->cdNumTelefone,buffer->cdDDD,idPessoa))
        return ERROR_LINHA_NAO_ENCONTRADA;

    // regra para alteração
    this->m_regra = 1;
    this->m_pessoaEndereco.consultarEnderecosDB(idPessoa,m_listEnderecoPrePago);

    EnderecoPrePago *endereco = NULL;
    if(m_listEnderecoPrePago.sizeOf() > 0)
        endereco = (EnderecoPrePago*) m_listEnderecoPrePago.get(0);
    else
        return ERROR_ENDERECO_NAO_ENCONTRADO;

    this->m_pessoaEndereco.getUF(buffer->uf,idUF);
    this->m_pessoaEndereco.getPais("BR",idPais);	
    // validação de linha
    existeLinha = this->m_pessoaLinha.getLinha(buffer->cdNumTelefone,buffer->cdDDD,m_stLinha);
    if(this->getTipoCadastro() == TIPO_CADASTRO_EXTERNO && existeLinha == 0)
        return ERROR_LINHA_NAO_ENCONTRADA;

    // diferente de prepago
    if(strcmp(m_stLinha.sgTipoLinha,"PRÉ") != 0 && strcmp(m_stLinha.sgTipoLinha,"PRÉCHIP") != 0 && 
        strcmp(m_stLinha.sgTipoLinha,"CONT") != 0 && strcmp(m_stLinha.sgTipoLinha,"CONTCHIP") != 0)
        return ERROR_TIPO_LINHA;

    tuxfw_getlogger()->debug("chamar serviço AbaServico");
    XMLGen xml;	
    xml.addItem("acao","ALTERAR");
    xml.createTag("DadosLupaCliente");
    xml.addProp("xmlns","cliente.fo.vivo.com.br/vo");
    xml.addItem("nrLinha",nrLinha);
    xml.closeTag();
    xml.createTag("DadosAbaLupaCliente");
        xml.addProp("xmlns","cliente.fo.vivo.com.br/vo");
        xml.addItem( "CPFRECADASTRO",(char*)buffer->cpf );
        xml.createTag("EnderecoVO");
            xml.addItem("idEndereco",endereco->getIdEndereco());
            xml.createTag("TipoEnderecoVO");
            xml.addItem("idTipoEndereco",endereco->getIdTipoEnderecoSelecionado());
            xml.closeTag();
            xml.addItem("nmTipoLogradouro","Rua");
            xml.addItem("nmTituloLogradouro","Rua");
            xml.addItem("nmLogradouro",buffer->endereco);
            xml.addItem("nmBairro",buffer->bairro);
            xml.addItem("nmMunicipio",buffer->municipio);
            xml.addItem("nrEndereco",buffer->nrEndereco);
            xml.addItem("dsEnderecoComplemento",buffer->complemento);
            xml.addItem("nrCEP",buffer->cep);
            xml.createTag("UFVO");
                xml.addItem("idUF",idUF);
            xml.closeTag();
            xml.createTag("PaisVO");
                xml.addItem("idPais",idPais);
            xml.closeTag();
        xml.closeTag();
    xml.closeTag();

    TuxMessage o_inputMessage;
    TuxRemoteService o_remoteService;
    char statusTextCHAR[200];
    o_inputMessage.setUser("30");
    o_inputMessage.setMessageBody(&xml);
    o_inputMessage.setService("AbaEndereco");
    o_remoteService.setServiceName("AbaEndereco");
    o_remoteService.setInputMessage(&o_inputMessage);

    if(o_remoteService.remoteCall() != TUXFWRET_OK)
    {
        tuxfw_getlogger()->debug("Erro de acesso ao serviço AbaEndereco");
        throw new TuxBasicSvcException("11E0999","Erro de acesso ao serviço AbaEndereco");
    }

    char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
    char* statusText = o_remoteService.getOutputMessage()->getStatusText();
    tuxfw_getlogger()->debug("CadastroPrePago::gravarRecadastro:LastStatusCode: '%s'", statusCode);
    tuxfw_getlogger()->debug("CadastroPrePago::gravarRecadastro:LastStatusText: '%s'", statusText);
    tuxfw_getlogger()->debug("CadastroPrePago::gravarRecadastro:Liberando statusCode");
    tuxfw_getlogger()->debug("CadastroPrePago::gravarRecadastro:Liberando statusText");

    strncpy(statusTextCHAR,statusText,200);
    if(strlen(statusText) < 200)
        statusTextCHAR[strlen(statusText)]=0;
    else
        statusTextCHAR[200-1]=0;

    if((statusCode != NULL) && (strlen(statusCode) > 3) && (statusCode[2] == 'E'))
    {
        tuxfw_getlogger()->debug("Erro de acesso ao Serviço");
        if(!strcmp(statusCode,"12E1252"))
            throw new TuxBasicSvcException("00W0599",statusTextCHAR);
        free(statusCode);	
        free(statusText);
        throw new TuxBasicSvcException("00W0100",statusTextCHAR);
    }

    tuxfw_getlogger()->debug("< CadastroPrePago::gravarRecadastro");
    return 1;
}
