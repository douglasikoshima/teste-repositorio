/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/

#ifndef STFCHIMEDIATOCONTATO
	#define STFCHIMEDIATOCONTATO

struct st_FchImediatoContato
{
	long idAtendimento;
	int   idUsuarioBKO;
	int   idBaixa;
	int   idBaixaMensagem;
	int   idAgrupamentoEstadoTpProc;
	int   idAtividade;
	int   idGrupoAbertura;
	char* observacaoFechamento;
} ;


#endif

