//---------------------------------------------------------------------------
//                         (c) Consórcio Indra/PT-SI.
//                            xxxxxxxxxxxxxxxxxxxxxxx
//                                xxxxxxxxxxxxxx
//-----------------------------------------------------------------------------
// Los contenidos de este fichero son propiedad de Telefónica Consórcio Indra/PT-SI. 
// titular del copyright. Este fichero solo podra ser copiado, distribuido y utilizado, 
// en su totalidad o en parte, con el permiso escrito de Consórcio Indra/PT-SI 
// o de acuerdo con los terminos y condiciones establecidas en el acuerdo/contrato bajo 
// el que se suministra.
//---------------------------------------------------------------------------
//*  Modulo                   : LSTSERVICOS
//*  Fichero                  : LstServicos
//*  Tipo                     : .cpp
//*  Autor                    : Daniel Dantas
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Criar um servico que liste os servicos disponiveis para uma determinada
//*  linha telefonica
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*  Linha.hpp
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------
	

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTSERVICOS);

void implLSTSERVICOS::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CLinha				oLinha;
	CServico			oServico;
	list< CServico >	lstOServico;

	char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
 	int   iNrLinha = -1;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pcTagXmlIn != NULL)
		iCdAreaRegistro = atoi(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	if (pcTagXmlIn != NULL)
		iNrLinha = atoi(pcTagXmlIn);

	//Caso seja informada as tag cdAreaRegistro e nrLinha, então retornar todos os serviços e quais estão habilitados para a linha
	//do contrário, retornar todos os serviços existentes.
	if (iCdAreaRegistro > 0 && iNrLinha > 0){
		// Set os atributos pertinentes a Linha
		oLinha.setCdAreaRegistro(iCdAreaRegistro);
		oLinha.setNrLinha(iNrLinha);

		// Consultar a lista de servicos
		oLinha.consultarServicos( lstOServico );
	}else{
		CServico::consultarServicos( lstOServico );
	}

	// Logs
//	tuxfw_getlogger()->information("SAIDA: Valor de CServico.IdServico: %i\n", oServico.getIdServico()); 
//	tuxfw_getlogger()->information("SAIDA: Valor de CServico.SgServico: %s\n", oServico.getSgServico()); 
//	tuxfw_getlogger()->information("SAIDA: Valor de CServico.NmServico: %s\n", oServico.getNmServico()); 
	
	// Monta o XML de saída
	xml_g->createTag( "LSTSERVICOSVO" );
	xml_g->addProp( "xmlns", "capa.vol.vivo.com.br/vo" );

	while( 0 < lstOServico.size() ) {
		oServico = lstOServico.front();

		xml_g->createTag( "SERVICOS" );

		xml_g->addItem( "idServico", oServico.getIdServico() );
		xml_g->addItem( "sgServico", oServico.getSgServico() );
		xml_g->addItem( "nmServico", oServico.getNmServico() );
		xml_g->addItem( "inHabilitado", oServico.getInHabilitado() <= 0 ? 0 : 1 );

		xml_g->closeTag();

		lstOServico.pop_front();
	}

	xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}


