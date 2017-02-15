#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Defines/Defines.h>

DECLARE_TUXEDO_SERVICE(MODALERTAVIP);


void incluir(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	int		iNrLinha			= 0;
	int		iCdAreaRegistro 	= 0;
	char	cDsContato[256];
	int		iIdTipoComunicacao  = 0;
	char	cDtAlerta[256];
	char	cSgTipoComunicacao[256];	
	char	cNmLoja[256];
	CLinha	oLinha;
	char cDtVisitaLoja[256];
	char*  pcTagXmlIn = NULL;
	CTuxHelperClever helper;


	pcTagXmlIn = helper.walkTree(dnode, "nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode, "cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	for (int i = 0; i < 2; i++)
	{
		pcTagXmlIn = helper.walkTree(dnode, "sgTipoComunicacao", i);

		if (pcTagXmlIn == NULL)
			break;

		strcpy(cSgTipoComunicacao, pcTagXmlIn);
	
		pcTagXmlIn = helper.walkTree(dnode, "dsContato", i);
		strcpy(cDsContato, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "dtAlerta", i);
		strcpy(cDtAlerta, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "dsLoja",	i);
		strcpy(cNmLoja, pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(dnode, "dtVisitaLoja", i);
		strcpy(cDtVisitaLoja, pcTagXmlIn);

		if (pcTagXmlIn == NULL)
			break;

		oLinha.setCdAreaRegistro(iCdAreaRegistro);
		oLinha.setNrLinha(iNrLinha);

		if (i == 0)
		{

			oLinha.excluirAlerta();

		}

		try{

			oLinha.incluirAlerta(cSgTipoComunicacao, cNmLoja, cDtAlerta, cDsContato, cDtVisitaLoja);

			statusCode.setCod("11I0000");
			statusCode.setMsg("Inclusão efetuada com sucesso.");	
		}

		catch ( ... )
		{
		
			statusCode.setCod("11E0000");
			statusCode.setMsg("Erro ao efetuar a inclusão.");	
		}
	}
	
}

void excluir(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	int		iNrLinha			= 0;
	int		iCdAreaRegistro 	= 0;
	char	cDsContato[256];
	int		iIdTipoComunicacao  = 0;
	char	cDtAlerta[256];
	char	cSgTipoComunicacao[256];	
	char	cNmLoja[256];
	CLinha	oLinha;
	char*   pcTagXmlIn = NULL;
	CTuxHelperClever helper;
	char    cDtVisitaLoja[256];

	pcTagXmlIn = helper.walkTree(dnode, "nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode, "cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);
		
	try{

			oLinha.excluirAlerta();	
			statusCode.setCod("11I0000");
			statusCode.setMsg("Alteração efetuada com sucesso.");	
	}
	catch ( ... )
	{
			statusCode.setCod("11E0000");
			statusCode.setMsg("Erro alterar o alerta .");	
	}

}

void implMODALERTAVIP::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	CStatusCode	  statusCode;

	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);

	if (!strcmp(pcTagXmlIn, "incluir"))
	{
		incluir(dnode, xml_g, statusCode);

	}else if (!strcmp(pcTagXmlIn, "excluir"))
	{	
		excluir(dnode, xml_g, statusCode);
	}

	
	setStatusCode(statusCode.getCod(), statusCode.getMsg());

}