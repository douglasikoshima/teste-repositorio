/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/01/23 18:08:56 $
 **/

#ifndef STWFATENDIMENTOFRM
	#define STWFATENDIMENTOFRM

struct st_AtendimentoFrm
{
	long idAtendimentoFrm;
	long idAtendimento;
	int  idContatoFolhaCampo;
	int  idCampo;
	char textoPesquisa[256];
	long idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoFrm
{
	short idAtendimentoFrm;
	short idAtendimento;
	short idContatoFolhaCampo;
	short idCampo;
	short textoPesquisa;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

#ifndef STFORMULARIODINAMICO
	#define STFORMULARIODINAMICO

struct st_FormularioDinamico
{
	int idContato;
	char nrTelefone[256];
	int idTipoLinha;
	int idFaseProcesso;
} ;

struct st_vlFormularioDinamico
{
	short idContato;
	short nrTelefone;
	short idTipoLinha;
	short idFaseProcesso;
} ;

#endif


