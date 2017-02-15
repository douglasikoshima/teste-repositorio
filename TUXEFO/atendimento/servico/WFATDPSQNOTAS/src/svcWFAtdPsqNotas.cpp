#include "tuxfw.h"
#include "../include/AtendimentoConsultor.h"
#include "../include/UtilWorkflow.h"

DECLARE_TUXEDO_SERVICE(ATDPSQNOTAS);

void implATDPSQNOTAS::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDPSQNOTAS::Execute()");

	AtendimentoConsultor oAtdConsultor;
	struct stPesquisaNotas buf;
	int retorno;
	// dados de entrada
	char *idAtendimento = walkTree(dnode,"idAtendimento",0);
	char *idAtendimentoProtocolo = walkTree(dnode,"nrProtocolo",0);
	char *dtAberturaIni = walkTree(dnode,"dtAberturaIni",0);
	char *dtAberturaFim = walkTree(dnode,"dtAberturaFim",0);
	char *nmUsuario = walkTree(dnode,"nmUsuario",0);
	char *reConsultor = walkTree(dnode,"reConsultor",0);
	char *login = walkTree(dnode,"login",0);
	char *idMotivo = walkTree(dnode,"idMotivo",0);
	char *idOperacao = walkTree(dnode,"idOperacao",0);
	char *idTipoNotaAtendimento = walkTree(dnode,"tipoNotaAtendimento",0);
	char *nrLinha = walkTree(dnode,"nrLinha",0);
	char *notaConsultor = walkTree(dnode,"notaConsultor",0);
	char *cliContatado = walkTree(dnode,"cliContatado",0);
	char *cliTransferido = walkTree(dnode,"cliTransferido",0);
	char *idUsuario = walkTree(dnode,"idUsuario",0);
	char *flgInbox = walkTree(dnode,"flgInbox",0);
	// seta com 0 as variaveis e copia os dados de entrada para o buffer
	memset(&buf,0,sizeof(stPesquisaNotas));
	UtilWorkflow::strnullcpy(buf.idAtendimento,idAtendimento);
	UtilWorkflow::strnullcpy(buf.idAtendimentoProtocolo,idAtendimentoProtocolo);
	UtilWorkflow::strnullcpy(buf.dtAberturaIni,dtAberturaIni);
	UtilWorkflow::strnullcpy(buf.dtAberturaFim,dtAberturaFim);
	UtilWorkflow::strnullcpy(buf.idMotivo,idMotivo);
	UtilWorkflow::strnullcpy(buf.idOperacao,idOperacao);
	UtilWorkflow::strnullcpy(buf.idTipoNotaAtendimento,idTipoNotaAtendimento);
	UtilWorkflow::strnullcpy(buf.nrLinha,nrLinha);
	UtilWorkflow::strnullcpy(buf.cliContatado,cliContatado);
	UtilWorkflow::strnullcpy(buf.cliTransferido,cliTransferido);
	UtilWorkflow::strnullcpy(buf.idUsuario,idUsuario);
	UtilWorkflow::strnullcpy(buf.reConsultor,reConsultor);
	UtilWorkflow::strnullcpy(buf.nmUsuario,nmUsuario);
	UtilWorkflow::strnullcpy(buf.login,login);
	UtilWorkflow::strnullcpy(buf.notaConsultor,notaConsultor);
	// xml de retorno
	xml_g->createTag("WFAtdNotasVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	try
	{
		// buscar as notas
		if(flgInbox!=NULL && !strcmp(flgInbox,"1"))
			retorno = oAtdConsultor.buscarNotasInbox(&buf,xml_g);
		else
			retorno = oAtdConsultor.buscarNotasAtendimento(&buf,xml_g);
	}
	catch(TuxBasicOraException tboe)
	{
		ULOGE("erro de oracle");
	}
	xml_g->addItem("nrRegistros",buf.nrRegistros);
	xml_g->addItem("totalRegistros",buf.totalRegistros);
	xml_g->closeTag();	
	XMLString::release(&idAtendimento);
	XMLString::release(&idAtendimentoProtocolo);
	XMLString::release(&dtAberturaIni);
	XMLString::release(&dtAberturaFim);
	XMLString::release(&nmUsuario);
	XMLString::release(&reConsultor);
	XMLString::release(&login);
	XMLString::release(&idMotivo);
	XMLString::release(&idOperacao);
	XMLString::release(&idTipoNotaAtendimento);
	XMLString::release(&nrLinha);
	XMLString::release(&notaConsultor);
	XMLString::release(&cliContatado);
	XMLString::release(&cliTransferido);
	XMLString::release(&idUsuario);
	XMLString::release(&flgInbox);	

	if(retorno == 1)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0001","Erro em subrotina");
		
   ULOG_END("implATDPSQNOTAS::Execute()");
}