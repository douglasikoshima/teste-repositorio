/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFCONDICAOAPARICAO
	#define STWFCONDICAOAPARICAO

struct st_CondicaoAparicao
{
	int  idCondicaoAparicao;
	int  idFluxoFase;
	int  idAtividade;
	int  idAtividadeAt;
	long idParametro;
	char dsQuery[256];
} ;

struct st_vlCondicaoAparicao
{
	short idCondicaoAparicao;
	short idFluxoFase;
	short idAtividade;
	short idAtividadeAt;
	short idParametro;
	short dsQuery;
} ;

#endif

