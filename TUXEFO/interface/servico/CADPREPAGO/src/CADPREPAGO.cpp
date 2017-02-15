#define CADPREPAGOCPP

#include<tuxfw.h>
#include"../../../negocio/UraPrePago/include/Util.h"
#include"../../../negocio/UraPrePago/include/CadastroPrePago.h"

DECLARE_TUXEDO_SERVICE(CADPREPAGO);


void implCADPREPAGO::Execute(DOMNode*dnode,XMLGen*xml_g)
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
	char*inProtocolo = this->walkTree(dnode,"SMSPROT",0);
	char*inPromocoes = this->walkTree(dnode,"SMSPROM",0);
	char*inProdutos = this->walkTree(dnode,"SMSPROD",0);
	char*inParceiros = this->walkTree(dnode,"SMSPARC",0);
	char*telefoneContato = this->walkTree(dnode,"telefoneContato",0);
	
	// copiar para estrutura
	memset(&param,0,sizeof(stParametrosUren));
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
	Util::strtrimcpy(param.telefoneContato,telefoneContato);
	
	// campos de black list
	cadastroPrePago.setInProtocolo(inProtocolo);
	cadastroPrePago.setInPromocoes(inPromocoes);
	cadastroPrePago.setInProdutos(inProdutos);
	cadastroPrePago.setInParceiros(inParceiros);

	cadastroPrePago.setTipoCadastro(TIPO_CADASTRO_EXTERNO);
	
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
		case 1: setStatusCode("00I0000","sucesso");
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
	XMLString::release(&telefoneContato);

}