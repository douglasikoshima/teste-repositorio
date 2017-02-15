#include "tuxfw.h"
#include "../include/AtendimentoNota.h"
#include "../include/svcWFAtdSetNota.h"
#include "../include/UtilWorkflow.h"

DECLARE_TUXEDO_SERVICE(ATDSETNOTA);

void implATDSETNOTA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   
   ULOG_START("implATDSETNOTA::Execute()");
   
	AtendimentoNota oAtendimentoNota;
	char*idAtendimentoNota;
	char*idAtendimento;
	char*idTipoNota;
	char*idPessoaUsuario;
	char*nmLoginUsuarioCTI;
	char*comentario;
	int retorno = 0;

	// os dados do XML de entrada
	idAtendimentoNota = walkTree(dnode,"idAtendimentoNota",0);
	idAtendimento = walkTree(dnode,"idAtendimento",0);
	idTipoNota = walkTree(dnode,"tipoNotaAtendimento",0);
	idPessoaUsuario = walkTree(dnode,"idUsuario",0);
	nmLoginUsuarioCTI = walkTree(dnode,"nmLoginUsuarioCTI",0);
	comentario = walkTree(dnode,"comentario",0);

	if(idAtendimento == NULL || !strcmp(idAtendimento,"") ||
	    idTipoNota == NULL || !strcmp(idTipoNota,""))
	{
		retorno = MSG_CODE_PARAMETRO_INVALIDO;		
	}
	else
	{
		struct stNota nota;
		strcpy(nota.idAtendimento,idAtendimento);
		strcpy(nota.idTipoNota,idTipoNota);
		strcpy(nota.idPessoaUsuario,((idPessoaUsuario==NULL)?"":idPessoaUsuario));		
		strcpy(nota.nmLoginUsuarioCTI,((nmLoginUsuarioCTI==NULL)?"":nmLoginUsuarioCTI));
		strcpy(nota.comentario,(comentario==NULL)?"":comentario);
		strcpy(nota.idAtendimentoNota,(idAtendimentoNota==NULL)?"":idAtendimentoNota);
		try
		{
			if(idAtendimentoNota == NULL || !strcmp(idAtendimentoNota,""))
			{
				retorno = oAtendimentoNota.criarNota(&nota);
			}
			else
			{
				retorno = oAtendimentoNota.editarNota(&nota);
			}
		}
		catch(TuxBasicOraException tboe)
		{
			ULOGE("Erro ao criar nota %d",tboe.eCode);
			retorno = MSG_CODE_ERRO_ORACLE;
		}
	}

	XMLString::release(&idAtendimento);
	XMLString::release(&idTipoNota);
	XMLString::release(&idPessoaUsuario);
	XMLString::release(&nmLoginUsuarioCTI);
	XMLString::release(&comentario);
	xml_g->createTag("WFAcaoRetornoVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	switch(retorno)
	{
		case MSG_CODE_PARAMETRO_INVALIDO:
			setStatusCode(MSG_PARAMETRO_INVALIDO,MSG_TEXT_PARAMETRO_INVALIDO);
			xml_g->addItem("acaoExecucao","N");
			xml_g->addItem("mensagem","Parâmetros de entrada inválidos");
			break;
		case MSG_CODE_CONSTRAINT_VIOLADA:
			setStatusCode(MSG_CONSTRAINT_VIOLADA,MSG_TEXT_CONSTRAINT_VIOLADA);
			xml_g->addItem("acaoExecucao","N");
			xml_g->addItem("mensagem","Chave violada");	
			break;
		case MSG_CODE_ERRO_ORACLE:
			setStatusCode(MSG_ERRO_ORACLE,MSG_TEXT_ERRO_ORACLE);
			xml_g->addItem("acaoExecucao","N");
			xml_g->addItem("mensagem","Erro ao inserir nota");	
			break;
		case MSG_CODE_PROCESSO_NOT_FOUND:
			setStatusCode(MSG_ERRO_PROCESSO_NOT_FOUND,MSG_TEXT_PROCESSO_NOT_FOUND);
			xml_g->addItem("acaoExecucao","N");
			xml_g->addItem("mensagem",MSG_TEXT_PROCESSO_NOT_FOUND);
			break;
		case MSG_CODE_PROCESSO_CRI:
			setStatusCode(MSG_ERRO_PROCESSO_CRI,MSG_TEXT_PROCESSO_CRI);
			xml_g->addItem("acaoExecucao","N");
			xml_g->addItem("mensagem",MSG_TEXT_PROCESSO_CRI);
			break;
		default:
			setStatusCode("00I0000","sucesso");
			xml_g->addItem("acaoExecucao","S");
			xml_g->addItem("mensagem","Gravação com Sucesso");	
			break;
	}
	xml_g->closeTag();
	
	ULOG_END("implATDSETNOTA::Execute()");
}