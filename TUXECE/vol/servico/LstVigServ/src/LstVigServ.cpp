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
//*  Modulo                   : LSTVIGSERV
//*  Fichero                  : LstVigServ
//*  Tipo                     : .cpp
//*  Autor                    : Michel Mastriani
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

DECLARE_TUXEDO_SERVICE(LSTVIGSERV);

//---------------------------------------------------------------------------
//*  Especificacion
//*  
//*  Execute
//*
//*  ->  DOMNode - no com informacoes XML para a consulta
//*  <-  XMLGen  - XML de retorno
//*  
//---------------------------------------------------------------------------
//*  Proposito
//*
//*  Montar XML com a vigencia de todos os servicos habilitados para uma 
//*  determinada linha telefonica
//*
//---------------------------------------------------------------------------
//*  Proceso
//*
//*  
//*
//---------------------------------------------------------------------------

void implLSTVIGSERV::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;
	
    CServico          oServico;
    list< CServico >  lstOServico;
    CLinha            oLinha;

	char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
 	int   iNrLinha = -1;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20E0000", "TAG_cdAreaRegistro_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20E0000", "TAG_cdAreaRegistro_VALOR_VAZIO");
	}

	if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("20E0000", "TAG_cdAreaRegistro_VALOR_INVALIDO");
	}
	
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20E0000", "TAG_nrLinha_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20E0000", "TAG_nrLinha_VALOR_VAZIO");
	}

	if ((iNrLinha = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("20E0000", "TAG_nrLinha_VALOR_INVALIDO");
	}


	// Monta o objeto oLinha
	oLinha.setCdAreaRegistro(iCdAreaRegistro);	
	oLinha.setNrLinha(iNrLinha);

	// Trazendo todos os servicos da linha
	oLinha.consultarVigServicosLinha(lstOServico);
	
	// Monta o XML de saída
	xml_g->createTag( "LSTVIGSERVVO" );
	xml_g->addProp( "xmlns", "capa.vol.vivo.com.br/vo" );

	while( 0 < lstOServico.size() ) {
		oServico = lstOServico.front();

		xml_g->createTag( "SERVICOSVIGENTES" );
			xml_g->addItem( "idServico", oServico.getIdServico() );
			xml_g->addItem( "nmServico", oServico.getNmServico() );
			xml_g->addItem( "dtVigenciaInicio", oServico.getDtVigenciaInicio() );
			xml_g->addItem( "dtVigenciaFinal", oServico.getDtVigenciaFinal() );
		xml_g->closeTag();

		lstOServico.pop_front();
	}

	xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header
	setStatusCode("20I0000", "MSG_OK");
}

