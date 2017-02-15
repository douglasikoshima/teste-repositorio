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
//*  Modulo                   : VOLPARAM
//*  Fichero                  : VOLParam
//*  Tipo                     : .cpp
//*  Autor                    : Jones Randis
//*  Fecha primera version    : 
//*  Version actual           : 
//*//---------------------------------------------------------------------------
//*  Proposito:
//*
//*  Consultar/Incluir/Alterar/Excluir Parâmetros.
//*  
//*//---------------------------------------------------------------------------
//*  Dependencias:
//*
//*  Parametro.hpp 
//*
//*//---------------------------------------------------------------------------
//*  Consideraciones de portabilidad:
//*
//*  
////---------------------------------------------------------------------------

#include <tuxfw/tuxfw.h>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

#define XML_IN_OPERACAO                 "operacao"
#define XML_IN_CHAVE                    "chave"
#define XML_IN_VALOR                    "valor"
#define XML_IN_XMLNS                    "xmlns"
#define XML_OUT_VOLPARAMVO 		"VOLPARAMVO"
#define XML_OUT_VALOR                   "valor"
#define XML_OUT_XMLNS                   "xmlns"
#define XMLNS                           "creditos.tav.vivo.com.br/vo"

DECLARE_TUXEDO_SERVICE(VOLPARAM);

void implVOLPARAM::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;

	CParametro o_Parametro;
	char* pc_operacao = NULL;
	char* pc_chave    = NULL;
	char* pc_valor    = NULL;
	char* pc_xmlns    = NULL;
	short i_operacao  = 0;
	enum {CONSULTAR = 1, INCLUIR = 2, ALTERAR = 3, EXCLUIR = 4};


	// Tratamento da tag 'operacao' de entrada do xml
	pc_operacao = helper.walkTree(dnode, XML_IN_OPERACAO, 0);
	
	if (pc_operacao == NULL)
		throw new TuxBasicSvcException("00E0000", "TAG_<operacao>_INEXISTENTE");
	if (*pc_operacao == '\0')
		throw new TuxBasicSvcException("00E0000", "TAG_<operacao>__VALOR_VAZIO");

	if (strcasecmp(pc_operacao, "CONSULTAR") == 0)
		i_operacao = CONSULTAR;
	else if (strcasecmp(pc_operacao, "INCLUIR") == 0)
		i_operacao = INCLUIR;
	else if (strcasecmp(pc_operacao, "ALTERAR") == 0)
		i_operacao = ALTERAR;
	else if (strcasecmp(pc_operacao, "EXCLUIR") == 0)
		i_operacao = EXCLUIR;
	else
		throw new TuxBasicSvcException("00E0000", "TAG_<operacao>__VALOR_INVALIDO");


	// Tratamento da tag 'chave' de ontrada do xml
	pc_chave = helper.walkTree(dnode, XML_IN_CHAVE, 0);

	if (pc_chave == NULL)
		throw new TuxBasicSvcException("00E0000", "TAG_<chave>_INEXISTENTE");
	if (*pc_chave == '\0')
		throw new TuxBasicSvcException("00E0000", "TAG_<chave>__VALOR_VAZIO");

	o_Parametro.setChave(pc_chave);


	// Tratamento da tag 'valor' de entrada do xml
	if ((i_operacao == INCLUIR) || (i_operacao == ALTERAR))
	{
		pc_valor =  helper.walkTree(dnode, XML_IN_VALOR, 0);

		if (pc_valor == NULL)
			throw new TuxBasicSvcException("00E0000", "TAG_<valor>_INEXISTENTE");

		o_Parametro.setValor(pc_valor);
	}

	try
	{
		switch(i_operacao)
		{
			case CONSULTAR:
				o_Parametro.consultarParametrosCanais();
				break;
			case INCLUIR:
				o_Parametro.incluir();
				break;
			case ALTERAR:
				o_Parametro.alterar();
				break;
			case EXCLUIR:
				o_Parametro.excluir();
				break;
		}

		xml_g->createTag(XML_OUT_VOLPARAMVO);

		pc_xmlns = helper.walkTree(dnode, XML_IN_XMLNS, 0);
		
		xml_g->addProp(XML_OUT_XMLNS, (pc_xmlns == NULL)? XMLNS : pc_xmlns);
	
		if (i_operacao == CONSULTAR)
			xml_g->addItem(XML_OUT_VALOR, o_Parametro.getConsulta());
	
		xml_g->closeTag();

		setStatusCode("13I0000", "Sucesso.");
	}
	catch(TuxBasicOraException &e)
	{
		if (e.eCode == ORA_NO_DATA_FOUND)
			setStatusCode("13W0001", "Chave não existente.");
		else
			setStatusCode("13W9999", "Erro desconhecido.");
	}
	catch(...)
	{
		setStatusCode("13W9999", "Erro desconhecido.");
	}
}
