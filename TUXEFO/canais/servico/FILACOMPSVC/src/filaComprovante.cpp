
#include<tuxfw.h>
#include "filaComprovante.h"
#include "../../negocio/FilaServico/include/FilaServico.hpp"

DECLARE_TUXEDO_SERVICE(FILACOMPSVC);


void implFILACOMPSVC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	FilaServico servico;
	char *dsOperacao = walkTree(dnode,"dsOperacao",0);
	char *tempoReprocessar = walkTree(dnode,"tempoReprocessar",0);
	char *tempoEntreExecucao = walkTree(dnode,"tempoEntreExecucao",0);
	char *tempoInativo = walkTree(dnode,"tempoInativo",0);
	char *registrosCursor = walkTree(dnode,"registrosCursor",0);
	char *qtTentativas = walkTree(dnode,"qtTentativas",0);
	char *cderro = walkTree(dnode,"cdErro",0);
	char *regIni = walkTree(dnode,"regIni",0);
	char *regFim = walkTree(dnode,"regFim",0);
	char *idComprovante = walkTree(dnode,"idcompservicodesativado",0);

	setStatusCode("00W0001","Operação inválida");

	xml_g->createTag("FilaComprovanteEmailVO");
	xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");

	try
	{

		if(!strcmp(dsOperacao,"getStatusParametro"))
		{
			char buffer[256];		
			servico.getParametro("DEAMMON_COMPROVANTE_TEMPO_REPROCESSAR",buffer);
			xml_g->addItem("tempoReprocessar",buffer);
			servico.getParametro("DEAMMON_COMPROVANTE_TEMPO_ENTRE_EXECUCAO",buffer);
			xml_g->addItem("tempoEntreExecucao",buffer);
			servico.getParametro("DEAMMON_COMPROVANTE_TEMPO_SEM_FILA",buffer);
			xml_g->addItem("tempoInativo",buffer);
			servico.getParametro("DEAMMON_COMPROVANTE_REGISTROS_POR_VEZ",buffer);
			xml_g->addItem("registrosCursor",buffer);
			servico.getParametro("DEAMMON_COMPROVANTE_QTTENTATIVAS",buffer);
			xml_g->addItem("qtTentativas",buffer);
			servico.getParametro("DEAMMON_COMPROVANTE_ATIVO",buffer);
			xml_g->addItem("ativo",buffer);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"setStatusParametro"))
		{
			servico.setStatusParametro("DEAMMON_COMPROVANTE_TEMPO_REPROCESSAR",tempoReprocessar);
			servico.setStatusParametro("DEAMMON_COMPROVANTE_TEMPO_ENTRE_EXECUCAO",tempoEntreExecucao);
			servico.setStatusParametro("DEAMMON_COMPROVANTE_TEMPO_SEM_FILA",tempoInativo);
			servico.setStatusParametro("DEAMMON_COMPROVANTE_REGISTROS_POR_VEZ",registrosCursor);
			servico.setStatusParametro("DEAMMON_COMPROVANTE_QTTENTATIVAS",qtTentativas);			
			setStatusCode("00I0000","Sucesso");		
		}
		else
		if(!strcmp(dsOperacao,"stopDaemonParametro") || !strcmp(dsOperacao,"startDaemonParametro"))
		{
			char *operacao = (!strcmp(dsOperacao,"startDaemonParametro"))?"1":"0";
			servico.setStatusParametro("DEAMMON_COMPROVANTE_ATIVO",operacao);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"consultaFila"))
		{
			servico.getRegistroAgrupado(xml_g);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"consultaDetalheFila"))
		{
			servico.getRegistros(xml_g,cderro,regIni,regFim);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"atualizaIdErroFila"))
		{			
			servico.atualizaIdErroFila(idComprovante);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"atualizaGrupoErroFila"))
		{
			servico.atualizaGrupoErroFila(cderro);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"apagaIdErroFila"))
		{
			char*id = NULL;
			for(int i=0;(id = walkTree(dnode,"idcompservicodesativado",i))!=NULL;i++)
				servico.apagaIdErroFila(id);
			setStatusCode("00I0000","Sucesso");
		}
		else
		if(!strcmp(dsOperacao,"apagaGrupoErroFila"))
		{
			char*cod = NULL;
			for(int i=0;(cod = walkTree(dnode,"cdErro",i))!=NULL;i++)
				servico.apagaGrupoErroFila(cod);
			setStatusCode("00I0000","Sucesso");
		}
	}
	catch(TuxBasicOraException eboe)
	{
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);
		setStatusCode("00W0002","Erro oracle");
	}

	

	xml_g->closeTag();

	XMLString::release(&dsOperacao);
	XMLString::release(&tempoReprocessar);
	XMLString::release(&tempoEntreExecucao);
	XMLString::release(&tempoInativo);
	XMLString::release(&registrosCursor);
	XMLString::release(&qtTentativas);
	XMLString::release(&cderro);
	XMLString::release(&regIni);
	XMLString::release(&regFim);
	XMLString::release(&idComprovante);


}