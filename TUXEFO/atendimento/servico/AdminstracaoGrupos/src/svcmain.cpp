#include "selectData.h"
#include "CGrupos.h"

DECLARE_TUXEDO_SERVICE(readGrpsProcVO);
DECLARE_TUXEDO_SERVICE(WRITEGRPSPROC);
DECLARE_TUXEDO_SERVICE(READGRPSTRATN);
DECLARE_TUXEDO_SERVICE(WRITEGRPSTRAT);

char pSignature[]="Administracao Workflow;libCGPS;so;0.1;#20040510:112500#";


void implreadGrpsProcVO::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
    ULOG_START("implreadGrpsProcVO::Execute()");
	GruposRequest grp(XMLOut);
	char*preqnode,*ptype;
	int  iFiltroGrupos;
    char *p;

	preqnode=walkAttr(XMLIn,"readPerfProcVO","reqnode",0);
	ptype=walkAttr(XMLIn,"readPerfProcVO","type",0);
	if(!ptype||!preqnode)
		throw new TuxBasicSvcException("09E0001");
	if(!grp.SetQueryType(*ptype))
		throw new TuxBasicSvcException("09E0002");

    if ( p = walkTree(XMLIn,"filtroGrupos",0),p )
    {
	    iFiltroGrupos = atoi(p);
        XMLString::release(&p);
    }
	
	XMLOut->createTag("GruposProcessosVO");
	XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	grp.QueryData( preqnode,iFiltroGrupos );
	XMLOut->closeTag();

	setStatusCode("09I0000","Success Execution");
	ULOG_END("implreadGrpsProcVO::Execute()");
}

void implWRITEGRPSPROC::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
   ULOG_START("implWRITEGRPSPROC::Execute()");
	GruposWriter grp(getHdrAttr("user"));
	if(!grp.BatchWriter(XMLIn))
		throw new TuxBasicSvcException("09E0001");

	setStatusCode("09I0000","Success Execution");
	ULOG_END("");
}

void implREADGRPSTRATN::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
   ULOG_START("implREADGRPSTRATN::Execute()");
	NiveisRequest nrq(XMLOut);
	char*preqnode;

 	preqnode=walkAttr(XMLIn,"readGrpsTratNvl","reqnode",0);
	if(!preqnode)
		throw new TuxBasicSvcException("09E0001");

	XMLOut->createTag("GruposTratamentoNiveisVO");
	XMLOut->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	XMLOut->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	nrq.QueryData(preqnode);
	XMLOut->closeTag();

	setStatusCode("09I0000","Success Execution");
	ULOG_END("");
}

void implWRITEGRPSTRAT::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
   ULOG_START("implWRITEGRPSTRAT::Execute()");
	NiveisWriter nwr(getHdrAttr("user"));

	if(!nwr.BatchWriter(XMLIn))
		throw new TuxBasicSvcException("09E0001");

	printf("Calling setStatusCode in with value \"09I0000\"\n");
	setStatusCode("09I0000","Success Execution");
	ULOG_END("");
}
