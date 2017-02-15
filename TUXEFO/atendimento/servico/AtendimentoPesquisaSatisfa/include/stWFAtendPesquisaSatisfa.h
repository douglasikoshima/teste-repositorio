/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFATENDIMENTOPESQUISASATISFA
	#define STWFATENDIMENTOPESQUISASATISFA

struct st_AtendimentoPesquisaSatisfa
{
	long idAtendimentoPesquisaSatisfa;
	long idAtendimento;
	char dtPesquisa[256];
	int  idPessoaUsuario;
	char observacao[4000+1];
	int  vlNota;
} ;

struct st_vlAtendimentoPesquisaSatisfa
{
	short idAtendimentoPesquisaSatisfa;
	short idAtendimento;
	short dtPesquisa;
	short idPessoaUsuario;
	short observacao;
	short vlNota;
} ;

#endif

