/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:31:15 $
 **/


#ifndef STWFATENDIMENTOBAIXA
	#define STWFATENDIMENTOBAIXA

struct st_AtendimentoBaixa
{
	int  idBaixa;
	int  idContato;
	int  idGrupo;
	int  idUFOperadora;
	int  idTipoLinha;
	int  idTipoCarteira;
	int  idTipoRelacionamento;
	int  idSegmentacao;
	int  idNomeBaixa;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoBaixa
{
	short idBaixa;
	short idContato;
	short  idGrupo;
	short  idUFOperadora;
	short  idTipoLinha;
	short  idTipoCarteira;
	short  idSegmentacao;
	short  idTipoRelacionamento;
	short idNomeBaixa;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

