/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 

#ifndef STWFATENDIMENTOPESQUISAATUAL
	#define STWFATENDIMENTOPESQUISAATUAL

struct st_AtendimentoPesquisaAtual
{
    long idAtendimento;
    long idAtendimentoPesquisaSatisfa;
    long idAtendimentoPesquisaSatisfaAtual;
    int idPessoaUsuario;
} ;

struct st_vlAtendimentoPesquisaAtual
{
    short idAtendimento;
    short idAtendimentoPesquisaSatisfa;
    short idAtendimentoPesquisaSatisfaAtual;
    short idPessoaUsuario;
} ;

#endif

