/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFATENDIMENTOPESQUISARESP
	#define STWFATENDIMENTOPESQUISARESP

struct st_AtendimentoPesquisaResp
{
	long idAtendimentoPesquisaResp;
	long idAtendimentoPesquisaSatisfa;
	int idPergunta;
    int idPessoaUsuario;
	char vlResposta[256];
} ;

struct st_vlAtendimentoPesquisaResp
{
	short idAtendimentoPesquisaResp;
	short idAtendimentoPesquisaSatisfa;
	short idPergunta;
    short idPessoaUsuario;
	short vlResposta;
} ;

#endif

