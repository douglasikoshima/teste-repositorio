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
//*  Modulo                   : SITEFNUMDOC
//*  Fichero                  : SitefNumDoc
//*  Tipo                     : .cpp
//*  Autor                    : Jones Randis
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Retornar o proximo valor de uma sequencia para obter numeros randomicos sequenciais.
//*  
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//* 
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#include <tuxfw/tuxfw.h>
#include <Venda/Venda.hpp>

#define SITEF_NUM_DOC_VO            "SITEFNUMDOCVO"
#define SITEF_NUM_DOC               "numDoc"

DECLARE_TUXEDO_SERVICE(SITEFNUMDOC);

void implSITEFNUMDOC::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	try {
		CVenda oVenda;

		oVenda.consultarSitefNumDoc();

		xml_g->createTag(SITEF_NUM_DOC_VO);
		xml_g->addItem(SITEF_NUM_DOC, oVenda.getSitefNumDoc());
		xml_g->closeTag();

		setStatusCode("11I0000", "Consulta realizado com sucesso.");
	}
	catch (...) {
		setStatusCode("11W0000", "Consulta não realizada.");
	}
}
