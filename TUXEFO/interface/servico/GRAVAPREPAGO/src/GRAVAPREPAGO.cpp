#define GRAVAPREPAGOCPP

#include<tuxfw.h>
#include"../../../negocio/UraPrePago/include/Util.h"
#include"../../../negocio/UraPrePago/include/CadastroPrePago.h"
#include <ctype.h>


DECLARE_TUXEDO_SERVICE(GRAVAPREPAGO);


void implGRAVAPREPAGO::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	CadastroPrePago cadastroPrePago;
	stParametrosUren param;
	char cepNum[20];
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
	char*inProtocolo = this->walkTree(dnode,"SMSPROT",0);
	char*inPromocoes = this->walkTree(dnode,"SMSPROM",0);
	char*inProdutos = this->walkTree(dnode,"SMSPROD",0);
	char*inParceiros = this->walkTree(dnode,"SMSPARC",0);
	char*interceptacao = this->walkTree(dnode,"interceptacao",0);
	char*estadoLinha = this->walkTree(dnode,"estadoLinha",0); 
	
	// vamos retirar os caracteres do cep
	if (cep != NULL) {
		memset(cepNum, 0, sizeof(cepNum));
		int len = strlen(cep);
		int j = 0;
		for (int i = 0; i < len; i++) {			
			if (isdigit(cep[i])) {
				cepNum[j++] = cep[i];
			}
		}
		XMLString::release(&cep);
		cep = cepNum;
	}
	
	tuxfw_getlogger()->debug("cep = %s cepNum = %s",cep, cepNum);
	
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
	}
	// campos de blacklist
	cadastroPrePago.setInProtocolo(inProtocolo);
	cadastroPrePago.setInPromocoes(inPromocoes);
	cadastroPrePago.setInProdutos(inProdutos);
	cadastroPrePago.setInParceiros(inParceiros);

	cadastroPrePago.setTipoCadastro(TIPO_CADASTRO_URA);
	cadastroPrePago.setTipoServico(TIPO_GRAVAPREPAGO);

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
	
	try
	{
		retorno = cadastroPrePago.gravar(&param);
	}
	catch (TuxBasicOraException tboe) {	
		retorno = ERROR_ACCESS_ORACLE;
		tuxfw_getlogger()->debug("Erro de Oracle: %d mensagem %s:",tboe.eCode,tboe.pMsg);
	}
	catch(TuxBasicSvcException tbse)
	{
		retorno = ERROR_SERVICE_REMOTECALL;
		tuxfw_getlogger()->debug("Erro de Chamada Remota ao serviço PPINSPESSOA: %d mensagem %s:",tbse.getCode(),tbse.getMessage());
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
		default:
				setStatusCode("00E0001","Erro desconhecido");
			break;
	}

	// liberar memoria
	XMLString::release(&bairro);
	XMLString::release(&cdDDD);
	XMLString::release(&cdNumTelefone);	
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
	XMLString::release(&interceptacao);
	XMLString::release(&estadoLinha);

}