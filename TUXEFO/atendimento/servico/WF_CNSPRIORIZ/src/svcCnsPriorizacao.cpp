
#include <tuxfw.h>

DECLARE_TUXEDO_SERVICE(CNSPRIORIZ);

extern void proCConsultaPriorizacao(DOMNode *dom, XMLGen* saida);
extern void proCConsultaSegmentacao(XMLGen* saida);
extern void proCConsultaCarteira(XMLGen* saida);
extern void proCConsultaProcedencia(XMLGen* saida);

void implCNSPRIORIZ::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCNSPRIORIZ::Execute()");
   char* p;
	int inServico = 0;

	TuxHelper tx;

 	if ( p=tx.walkTree( dnode, "inServico", 0 ),p ) 
	{
		inServico = atoi(p);
        XMLString::release(&p);
	}

	if (inServico == 1)
	{
		xml_g->createTag("AdmArvoreParametroVO");
		xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo");
				xml_g->createTag("AdmArvoreParametroCombosVO");
					proCConsultaSegmentacao(xml_g);
					proCConsultaCarteira(xml_g);
					proCConsultaProcedencia(xml_g);
				xml_g->closeTag();
		xml_g->closeTag();
	}
	else
		proCConsultaPriorizacao(dnode, xml_g);

	setStatusCode("00I0000","Operação Concluida.");

	ULOG_END("implCNSPRIORIZ::Execute()");
}

