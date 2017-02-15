/**
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/ 

#ifndef STWFREGCONTATOERRO
	#define STWFREGCONTATOERRO

struct st_RegContatoLogErro
{
	long idAtendimentoRegContatoErr;
	int idContato; 
	int idCanal; 
	int idGrupoAbertura; 
	int idProcedencia; 
	char idPessoaUsuarioAbertura[256+1];//<user> que vem no XML de entrada
	int idSegmentacao; 
	int idTipoCarteira; 
	char xmlEntrada[4000+1]; 
	char mensagemErro[1024+1];
};

struct st_vlRegContatoLogErro
{
	short idAtendimentoRegContatoErr; 
	short idContato; 
	short idCanal; 
	short idGrupoAbertura; 
	short idProcedencia; 
	short idPessoaUsuarioAbertura; 
	short idSegmentacao; 
	short idTipoCarteira; 
	short xmlEntrada; 
	short mensagemErro;
};

#endif

