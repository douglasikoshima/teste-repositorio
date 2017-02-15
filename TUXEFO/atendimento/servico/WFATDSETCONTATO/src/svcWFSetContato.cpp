#include "tuxfw.h"
#include "../include/UtilWorkflow.h"
#include "../include/AtendimentoNotaHistorico.h"

DECLARE_TUXEDO_SERVICE(ATDSETCONTATO);

void implATDSETCONTATO::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDSETCONTATO::Execute()");
   
	AtendimentoNotaHistorico obj;
	struct stNotaHistorico buf;
	int retorno = 0;
	char *idAtendimentoNotaHistorico = walkTree(dnode,"idAtendimentoNotaHistorico",0);
	char *idAtendimentoNota = walkTree(dnode,"idAtendimentoNota",0);
	char *idOperacao = walkTree(dnode,"idOperacao",0);
	char *idPessoaUsuario = getUser();
	char *user = getUser();
	DOMNode*WFAtdNotaHistVO = walkDOM(dnode,"WFAtdNotaHistVO");
	char *dsObservacao = walkTree(WFAtdNotaHistVO,"dsObservacao",0);
	char *idMotivo = walkTree(WFAtdNotaHistVO,"idMotivo",0);
	memset(&buf,0,sizeof(stNotaHistorico));
	UtilWorkflow::strnullcpy(buf.idPessoaUsuario,user);
	UtilWorkflow::strnullcpy(buf.dsObservacao,dsObservacao);
	UtilWorkflow::strnullcpy(buf.idAtendimentoNota,idAtendimentoNota);
	UtilWorkflow::strnullcpy(buf.idMotivo,idMotivo);
	UtilWorkflow::strnullcpy(buf.idOperacaoNotaAtendimento,idOperacao);
	try
	{
		retorno = obj.inserir(&buf,user);
	}
	catch(TuxBasicOraException tboe)
	{
		ULOGE("Erro ao criar histórico %d",tboe.eCode);
		retorno = 0;
	}		
	XMLString::release(&idAtendimentoNotaHistorico);
	XMLString::release(&idAtendimentoNota);
	XMLString::release(&idOperacao);
	XMLString::release(&idPessoaUsuario);	
	XMLString::release(&dsObservacao);
	XMLString::release(&idMotivo);
	xml_g->createTag("WFAcaoRetornoVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	if(retorno == 1)
	{
		setStatusCode("00I0000","Sucesso");
		xml_g->addItem("acaoExecucao","S");
		xml_g->addItem("mensagem","Gravação com Sucesso");		
	}
	else
	{
		setStatusCode("00W0001","Problemas ao executar serviço");
		xml_g->addItem("acaoExecucao","N");
		xml_g->addItem("mensagem","Problemas ao gravar histórico");		
	}
	xml_g->closeTag();
	
	ULOG_END("implATDSETCONTATO::Execute()");
}