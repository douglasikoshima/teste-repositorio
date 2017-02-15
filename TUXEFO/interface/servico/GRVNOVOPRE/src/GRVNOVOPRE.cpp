#define GRAVAPREPAGOCPP

#include<tuxfw.h>
#include"../../../negocio/UraPrePago/include/Util.h"
#include"../../../negocio/UraPrePago/include/CadastroPrePago.h"

DECLARE_TUXEDO_SERVICE(GRVNOVOPRE);


void implGRVNOVOPRE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	CadastroPrePago cadastroPrePago;
	stParametrosUren param;
	int retorno = 0;
	// recupera os dados do XML
	char*bairro = this->walkTree(dnode,"dsBairro",0);
	char*cdDDD = this->walkTree(dnode,"cdDDD",0);
	char*cdNumTelefone = this->walkTree(dnode,"cdNumTelefone",0);
	char*cep = this->walkTree(dnode,"nrCEP",0);
	char*cidade = this->walkTree(dnode,"dsCidade",0);
	char*complemento = this->walkTree(dnode,"dsComplemento",0);
	char*cpf = this->walkTree(dnode,"nrCPF",0);
	char*dtNascimento = this->walkTree(dnode,"dtNascimento",0);
	char*endereco = this->walkTree(dnode,"dsEndereco",0);
	char*nrEndereco = this->walkTree(dnode,"nrEndereco",0);
	char*municipio = this->walkTree(dnode,"dsMunicipio",0);
	char*nomeCliente = this->walkTree(dnode,"nmCliente",0);
	char*sexo = this->walkTree(dnode,"cdSexo",0);
	char*uf = this->walkTree(dnode,"sgUF",0);
	char*controle = this->walkTree(dnode,"cdControle",0);
	char*operacao = this->walkTree(dnode,"operacao",0);	
	char*inProtocolo = this->walkTree(dnode,"SMSPROT",0);
	char*inPromocoes = this->walkTree(dnode,"SMSPROM",0);
	char*inProdutos = this->walkTree(dnode,"SMSPROD",0);
	char*inParceiros = this->walkTree(dnode,"SMSPARC",0);
	char*interceptacao = this->walkTree(dnode,"interceptacao",0);
	char*estadoLinha = this->walkTree(dnode,"estadoLinha",0); 
	char*idCanal = this->walkTree(dnode,"idCanal",0); 

	tuxfw_getlogger()->debug("nova tag idCanal = %s",idCanal);

	// verificar tamanho dos campos
	if(Util::checkLength(cdDDD,2) || Util::checkLength(cdNumTelefone,11) ||
		Util::checkLength(nomeCliente,255) || Util::checkLength(sexo,1) ||
		Util::checkLength(dtNascimento,8) || Util::checkLength(endereco,255) ||
		Util::checkLength(nrEndereco,255) || Util::checkLength(complemento,255) ||
		Util::checkLength(bairro,255) || Util::checkLength(municipio,255) ||
		Util::checkLength(cep,8) || Util::checkLength(cidade,255) ||
		Util::checkLength(uf,255) || Util::checkLength(cpf,11) ||
		Util::checkLength(controle,255))
	{
		XMLString::release(&bairro);
		XMLString::release(&cdDDD);
		XMLString::release(&cdNumTelefone);
		XMLString::release(&cep);
		XMLString::release(&cidade);
		XMLString::release(&complemento);
		XMLString::release(&cpf);
		XMLString::release(&dtNascimento);
		XMLString::release(&endereco);
		XMLString::release(&nrEndereco);
		XMLString::release(&municipio);
		XMLString::release(&nomeCliente);
		XMLString::release(&sexo);
		XMLString::release(&uf);
		XMLString::release(&controle);
		XMLString::release(&operacao);
		XMLString::release(&interceptacao);
		XMLString::release(&estadoLinha);
		XMLString::release(&idCanal);

		setStatusCode("00W1125","Campo de entrada excedeu o limite");
		return;
	}
	
	// copiar para estrutura
	memset(&param,0,sizeof(stParametrosUren));

	if(estadoLinha != NULL && strcmp(estadoLinha,"1") == 0)
	{
		if(bairro!=NULL) strcpy(param.bairro,bairro);
		if(cdDDD!=NULL) strcpy(param.cdDDD,cdDDD);
		if(cdNumTelefone!=NULL) strcpy(param.cdNumTelefone,cdNumTelefone);
		if(cep!=NULL) strcpy(param.cep,cep);
		if(cidade!=NULL) strcpy(param.cidade,cidade);
		if(complemento!=NULL) strcpy(param.complemento,complemento);
		if(cpf!=NULL) strcpy(param.cpf,cpf);
		if(dtNascimento!=NULL) strcpy(param.dtNascimento,dtNascimento);
		if(endereco!=NULL) strcpy(param.endereco,endereco);
		if(nrEndereco!=NULL) strcpy(param.nrEndereco,nrEndereco);
		if(municipio!=NULL) strcpy(param.municipio,municipio);
		if(nomeCliente!=NULL) strcpy(param.nomeCliente,nomeCliente);
		if(sexo!=NULL) strcpy(param.sexo,sexo);
		if(uf!=NULL) strcpy(param.uf,uf);
		if(controle!=NULL) strcpy(param.cdControle,controle);
	}else{
		Util::strtrimcpy(param.bairro,bairro);
		Util::strtrimcpy(param.cdDDD,cdDDD);
		Util::strtrimcpy(param.cdNumTelefone,cdNumTelefone);
		Util::strtrimcpy(param.cep,cep);
		Util::strtrimcpy(param.cidade,cidade);
		Util::strtrimcpy(param.complemento,complemento);
		Util::strtrimcpy(param.cpf,cpf);
		Util::strtrimcpy(param.dtNascimento,dtNascimento);
		Util::strtrimcpy(param.endereco,endereco);
		Util::strtrimcpy(param.nrEndereco,nrEndereco);
		Util::strtrimcpy(param.municipio,municipio);
		Util::strtrimcpy(param.nomeCliente,nomeCliente);
		Util::strtrimcpy(param.sexo,sexo);
		Util::strtrimcpy(param.uf,uf);
		Util::strtrimcpy(param.cdControle,controle);
	}
	// tag de canal
	cadastroPrePago.setIdCanal(idCanal);
	// campos de blacklist
	cadastroPrePago.setInProtocolo(inProtocolo);
	cadastroPrePago.setInPromocoes(inPromocoes);
	cadastroPrePago.setInProdutos(inProdutos);
	cadastroPrePago.setInParceiros(inParceiros);
	/* 
	   Descrição: campo de interceptacao invertido (Andrei)	   
	   Vamos inverter o valor da tag interceptacao quando o webservice UREN passar a tag <interceptacao> com valor 1
	   Exemplo: Quando a tag for vazia, vamos passar <interceptacao>1</interceptacao> para o serviço PPINSPESSOA
	            Quando a tag for com valor 1, vamos passar <interceptacao>0</interceptacao> para o servico PPINSPESSOA
	   Estamos invertendo a tag porque a AVAYA não vai passar a tag, e o default é para interceptar a linha, ou seja, <interceptacao>1</interceptacao>.
	*/
	if(interceptacao != NULL && strcmp(interceptacao,"1") == 0)
		cadastroPrePago.setInterceptacao(false);

	// Se a tag <estadoLinha>1</estadoLinha> for 1, não vamos checar o estado de linha
	// Estamos rodando o batch e a linha mudou o estado de pré-ativa
	if(estadoLinha != NULL && strcmp(estadoLinha,"1") == 0)
		cadastroPrePago.setEstadoLinha(false);

    if(operacao != NULL && (!strcmp(operacao,"01") || !strcmp(operacao,"1")))
        cadastroPrePago.setOperacao(1);
    else if(operacao != NULL && (!strcmp(operacao,"02") || !strcmp(operacao,"2")))
        cadastroPrePago.setOperacao(2);
    else if(operacao != NULL && (!strcmp(operacao,"03") || !strcmp(operacao,"3")))		
        cadastroPrePago.setOperacao(3);
    else if(operacao != NULL && (!strcmp(operacao,"04") || !strcmp(operacao,"4")))		
        cadastroPrePago.setOperacao(4);

	cadastroPrePago.setTipoCadastro(TIPO_CADASTRO_URA);
	
	try
	{
		switch(cadastroPrePago.getOperacao())
		{
			case 1: retorno = cadastroPrePago.gravarCadastro(&param); break;
			case 2: retorno = cadastroPrePago.gravarAlteracao(&param); break;
			case 3: retorno = cadastroPrePago.gravarTrocaTitularidade(&param); break;
			case 4: retorno = cadastroPrePago.gravarRecadastro(&param); break;   /* SM de Melhorias na Interceptacao */
			default: retorno = ERROR_OPERACAO_INVALIDA; break;
		}		
	}
	catch (TuxBasicOraException tboe) {	
		retorno = ERROR_ACCESS_ORACLE;
		tuxfw_getlogger()->debug("Erro de Oracle: %d mensagem %s:",tboe.eCode,tboe.pMsg);
	}
	catch(TuxBasicSvcException tbse)
	{
		retorno = ERROR_SERVICE_REMOTECALL;
		tuxfw_getlogger()->debug("Erro de Chamada Remota ao serviço tuxedo: %d mensagem %s:",tbse.getCode(),tbse.getMessage());
	}

	switch(retorno)
	{
		case 1: 
			cadastroPrePago.getDadosLinha();	
			xml_g->createTag("CadastroPrePagoVO");
			xml_g->addProp("xmlns","atendimento.fo.vivo.com.br/vo");
			xml_g->addItem("idPessoaDepara",cadastroPrePago.getIdPessoaDepara());
			xml_g->addItem("idLinhaTelefonica",cadastroPrePago.getIdLinhaTelefonica());
			xml_g->closeTag();
			setStatusCode("00I0000","sucesso");
			break;
		case ERROR_DOCUMENTO_NOT_FOUND:
				setStatusCode("00W0001",ERROR_MSG_DOCUMENTO_NOT_FOUND);
			break;
		case ERROR_ENDERECO_NOT_FOUND:
				setStatusCode("00W0002",ERROR_MSG_ENDERECO_NOT_FOUND);
			break;
		case ERROR_PESSOA_DUPLICADA:
				setStatusCode("00W0003",ERROR_MSG_PESSOA_DUPLICADA);
			break;
		case ERROR_SERVICE_REMOTECALL:
				setStatusCode("00W0004",ERROR_MSG_SERVICE_REMOTECALL);
			break;
		case ERROR_ACCESS_ORACLE:
				setStatusCode("00W0005",ERROR_MSG_ACCESS_ORACLE);
			break;
		case ERROR_TIPO_LINHA:
				setStatusCode("00W1005",ERROR_MSG_TIPO_LINHA);
			break;
		case ERROR_ESTADO_LINHA:
				setStatusCode("00W1006",ERROR_MSG_ESTADO_LINHA);
			break;
		case ERROR_LINHA_NAO_ENCONTRADA:
				setStatusCode("00W1007",ERROR_MSG_LINHA_NAO_ENCONTRADA);
			break;
		case ERROR_OPERACAO_INVALIDA:
				setStatusCode("00W1100",ERROR_MSG_OPERACAO_INVALIDA);
			break;
		case ERROR_CAMPO_OBRIGATORIO_INVALIDO:
				setStatusCode("00W1123",ERROR_MSG_CAMPO_OBRIGATORIO_INVALIDO);
			break;
		case ERROR_PESSOA_NAO_ENCONTRADA:
				setStatusCode("00W1124",ERROR_MSG_PESSOA_NAO_ENCONTRADA);
			break;
		case ERROR_ENDERECO_NAO_ENCONTRADO:
				setStatusCode("00W1126",ERROR_MSG_ENDERECO_NAO_ENCONTRADO);				
			break;			
		default:
				setStatusCode("00E0001","Erro desconhecido");
			break;
	}

	// liberar memoria
	XMLString::release(&bairro);
	XMLString::release(&cdDDD);
	XMLString::release(&cdNumTelefone);
	XMLString::release(&cep);
	XMLString::release(&cidade);
	XMLString::release(&complemento);
	XMLString::release(&cpf);
	XMLString::release(&dtNascimento);
	XMLString::release(&endereco);
	XMLString::release(&nrEndereco);
	XMLString::release(&municipio);
	XMLString::release(&nomeCliente);
	XMLString::release(&sexo);
	XMLString::release(&uf);
	XMLString::release(&controle);
	XMLString::release(&operacao);
	XMLString::release(&interceptacao);
	XMLString::release(&estadoLinha);
	XMLString::release(&idCanal);

}