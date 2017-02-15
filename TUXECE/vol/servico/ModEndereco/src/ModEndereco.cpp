/*
 * Serviço de Modificacao do endereco
 * Versão inicial, 13/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

#define OP_INCLUIR  1
#define OP_EXCLUIR  2
#define OP_ALTERAR  3
#define OP_ASSOCIAR 4

DECLARE_TUXEDO_SERVICE(MODENDERECO);

void implMODENDERECO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{

	CTuxHelperClever helper;

	CPessoa		oPessoa;
    CEndereco	oEndereco;
	CConta      oConta;
	DOMNode    *po_Conta = NULL;

	char*	pcTagXmlIn = NULL;
	int     iOperacao = -1;
	long	iIdPessoa = -1;
	long    iIdPessoaEndereco = -1;
	long    iIdPessoaEnderecoAssociado = -1;
	int     iIdTipoEnderecoCobranca = -1;
	int		iIdTipoEndereco = -1;
	char	cNmTipoLogradouro[256];
	char	cNmTituloLogradouro[256];
	char	cNmLogradouro[256];
	char	cNrEndereco[256];
	char	cDsEnderecoComplemento[256];
	char	cNmBairro[256];
	char	cNrCep[256];
	char	cNmMunicipio[256];
	int		iIdUf = -1;
	int     iIdConta = -1;

	int     iError = 1;
	char    cMensagem[256];

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	if(!strcmp(pcTagXmlIn, "incluir"))
		iOperacao = OP_INCLUIR;
	else if(!strcmp(pcTagXmlIn, "excluir"))
		iOperacao = OP_EXCLUIR;
	else if(!strcmp(pcTagXmlIn, "alterar"))
		iOperacao = OP_ALTERAR;
	else if(!strcmp(pcTagXmlIn, "associar"))
		iOperacao = OP_ASSOCIAR;

	if(iOperacao != OP_EXCLUIR)
	{
		pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
		ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");

		oPessoa.setIdPessoa(iIdPessoa);
	}

	if(iOperacao != OP_INCLUIR )
	{
		pcTagXmlIn = helper.walkTree(dnode, "idEndereco", 0);
		ASSERT_LONG(iIdPessoaEndereco, pcTagXmlIn, "idEndereco");

		oPessoa.getEndereco()->setIdPessoaEndereco(iIdPessoaEndereco);
	}

	if(iOperacao == OP_INCLUIR ||
	   iOperacao == OP_ALTERAR)
	{

		pcTagXmlIn = helper.walkTree(dnode, "idTipoEndereco", 0);
		ASSERT_INT(iIdTipoEndereco, pcTagXmlIn, "idTipoEndereco");

		pcTagXmlIn = helper.walkTree(dnode, "nmTipoLogradouro", 0);
		strcpy(cNmTipoLogradouro, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "nmTituloLogradouro", 0);
		strcpy(cNmTituloLogradouro, pcTagXmlIn);
		
		pcTagXmlIn = helper.walkTree(dnode, "nmLogradouro", 0);
		strcpy(cNmLogradouro, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "nrEndereco", 0);
		strcpy(cNrEndereco, pcTagXmlIn);
		
		pcTagXmlIn = helper.walkTree(dnode, "dsEnderecoComplemento", 0);
		strcpy(cDsEnderecoComplemento, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "nmBairro", 0);
		strcpy(cNmBairro, pcTagXmlIn);
		
		pcTagXmlIn = helper.walkTree(dnode, "nrCep", 0);
		strcpy(cNrCep, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "nmMunicipio", 0);
		strcpy(cNmMunicipio, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "idUF", 0);
		ASSERT_INT(iIdUf, pcTagXmlIn, "idUF");				

		// Monta o objeto Endereco		
		oPessoa.getEndereco()->setIdTipoEndereco(iIdTipoEndereco);
		oPessoa.getEndereco()->setNmTipoLogradouro(cNmTipoLogradouro);
		oPessoa.getEndereco()->setNmTituloLogradouro(cNmTituloLogradouro);
		oPessoa.getEndereco()->setNmLogradouro(cNmLogradouro);
		oPessoa.getEndereco()->setNrEndereco(cNrEndereco);
		oPessoa.getEndereco()->setDsEnderecoComplemento(cDsEnderecoComplemento);
		oPessoa.getEndereco()->setNmBairro(cNmBairro);
		oPessoa.getEndereco()->setNrCep(cNrCep);
		oPessoa.getEndereco()->setNmMunicipio(cNmMunicipio);
		oPessoa.getEndereco()->setIdUf(iIdUf);

	}

	for(unsigned int i = 0; (po_Conta = walkDOM(dnode, "conta", i)) != NULL; i++)
	{
		pcTagXmlIn = helper.walkTree(po_Conta, "idConta", 0);
		ASSERT_INT(iIdConta, pcTagXmlIn, "idConta");

		pcTagXmlIn = helper.walkTree(po_Conta, "idEnderecoAssociado", 0);
		ASSERT_LONG(iIdPessoaEnderecoAssociado, pcTagXmlIn, "idEnderecoAssociado");

		pcTagXmlIn = helper.walkTree(po_Conta, "idTipoEnderecoCobranca", 0);

		if(pcTagXmlIn != NULL && strlen(pcTagXmlIn) > 0)
			ASSERT_INT(iIdTipoEnderecoCobranca, pcTagXmlIn, "idTipoEnderecoCobranca");

		oConta.setIdConta(iIdConta);
		oConta.setIdPessoaEnderecoAssociado(iIdPessoaEnderecoAssociado);
		oConta.setIdTipoEnderecoCobranca(iIdTipoEnderecoCobranca);
		oConta.setUser(atoi(this->getUser()));

		oPessoa.getEndereco()->getListaContas().push_back(oConta);
	}



	try
	{
		if(iOperacao == OP_INCLUIR)
			oPessoa.inserirEndereco();
		else if(iOperacao == OP_EXCLUIR)
			oPessoa.getEndereco()->excluirEndereco();
		else if(iOperacao == OP_ASSOCIAR)
			oPessoa.getEndereco()->associarContas();
		else if(iOperacao == OP_ALTERAR)
			oPessoa.alterarEndereco();
		else
		{
			setStatusCode("11E0099", "OPERACAO INVALIDA");
			return;
		}

		iError = 0;

	}
	catch(...)
	{
		iError = 1;	
	}

	if(iOperacao == OP_INCLUIR)
		strcpy(cMensagem, "INCLUSAO");
	else if(iOperacao == OP_EXCLUIR)
		strcpy(cMensagem, "EXCLUSAO");
	else if(iOperacao == OP_ALTERAR)
		strcpy(cMensagem, "ALTERACAO");
	else if(iOperacao == OP_ASSOCIAR)
		strcpy(cMensagem, "ASSOCIACAO");

	if(iError)
	{
		strcat(cMensagem, " NAO EFETUADA");
		setStatusCode("11W0001", cMensagem);
	}
	else	
	{
		// registrando contato
		//REG_CONTATO(iRes, REG_VALIDA_TAG); 
		REG_CONTATO_PROTOCOLO(iResult,REG_NAO_VALIDA_TAG,protocolo);

		// enviando mensagem de alerta
		ENV_MSG(iResMsg, ENV_MSG_VALIDA_TAG);

		xml_g->createTag("ProtocoloVO");
		xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
		xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	
		xml_g->closeTag();

		strcat(cMensagem, " EFETUADA COM SUCESSO");
		setStatusCode("11I0000", cMensagem);

	}

}
