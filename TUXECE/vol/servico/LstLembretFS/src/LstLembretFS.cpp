
/*
 * Serviço de Consulta a Lembrete da Senha
*/

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTLEMBRETFS);

void implLSTLEMBRETFS::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CPessoa oPessoa;
	
	char* pcTagXmlIn = NULL;
	int  iCdAreaRegistro = -1;
	int  iNrLinha = -1;
	int  iIdTipoRelacionamento = -1;
	long iIdPessoa = -1;

	//Recupera informações obrigatórias
	//Comportamentos possíveis: 
	//1) tag´s: cdAreaRegistro, nrLinha e idTipoRelacionamento
	//2) apenas a tag: idPessoa

	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pcTagXmlIn != NULL)
		iCdAreaRegistro = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	if (pcTagXmlIn != NULL)
		iNrLinha = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	if (pcTagXmlIn != NULL)
		iIdTipoRelacionamento = atoi(pcTagXmlIn);	

	// Monta o objeto Pessoa
	oPessoa.setIdTipoRelacionamento(iIdTipoRelacionamento);
	try
	{
		oPessoa.obterIdPessoa(iCdAreaRegistro, iNrLinha);
	}
	catch(...) 
	{
		setStatusCode("11W0001", "NAO FOI POSSIVEL EFETUAR A CONSULTA");	
	}


	// Monta o XML de saída
    xml_g->createTag("LSTLEMBRETFSVO");
    
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");

	try
	{

		oPessoa.getSenha().consultarLembreteFraseSecreta(oPessoa.getIdPessoa(),
			                                             oPessoa.getIdPessoaLinha(),
														 oPessoa.getIdTipoRelacionamento());

		// Consultar o Lembrete aramzenado no banco de dados e coloca na Tag XML
		xml_g->addItem("dsLembreteFS", oPessoa.getSenha().getDsLembreteFraseSecreta());

		// Consultar a Frase Secreta aramzenado no banco de dados e coloca na Tag XML
		xml_g->addItem("dsFraseSecreta", oPessoa.getSenha().getDsFraseSecreta());

		//seta mensagem de retorno - header
		setStatusCode("11I0000", "CONSULTA EFETUADA COM SUCESSO");

	}
	catch(...)
	{
		setStatusCode("11W0001", "NAO FOI POSSIVEL EFETUAR A CONSULTA");
	}

	xml_g->closeTag();

}



