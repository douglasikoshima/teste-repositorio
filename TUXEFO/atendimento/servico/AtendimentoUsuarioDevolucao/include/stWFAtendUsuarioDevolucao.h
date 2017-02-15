/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/ 

#ifndef STWFATENDIMENTOUSUARIODEVOLUCAO
	#define STWFATENDIMENTOUSUARIODEVOLUCAO

struct st_AtendimentoUsuarioDevolucao
{
	long idAtendimento;
	int  idPessoaUsuario;
} ;

struct st_vlAtendimentoUsuarioDevolucao
{
	short idAtendimento;
	short idPessoaUsuario;
} ;

#endif

