/**
 * @modulo  Campanha
 * @usecase SELLISTACAMP
 * @author  Robinson Vieira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

/**
 * Arquivo de include para a classe CSEL_LISTACAMPANHA
 */

#ifndef SEL_LISTACAMPANHAHH
#define SEL_LISTACAMPANHAHH
#include <stdio.h>
#include <string.h>
#include "tuxfw.h"

#define SCDISPONIVEL 0
#define SCDSDISPONIVEL "DISPONIVEL"
#define SCSUCESSO 1
#define SCDSSUCESSO "SUCESSO"
#define SCERRO 2
#define SCDSERRO "ERRO"
#define SCEMCARGA 3
#define SCDSEMCARGA "EM CARGA"

struct LISTACAMPANHA
{
	char szidLista[255];
	char sznmLista[255];
	char szdtCriacao[255+1];
	char szinStatusCarga[255+1];
	char szdsErroCarga[255+1];
	char szdtTentativas[255+1];
};

class CSEL_LISTACAMPANHA : private TuxHelper
{
	public:
		CSEL_LISTACAMPANHA();
		~CSEL_LISTACAMPANHA();
		int setMap(char *,DOMNode*,XMLGen*);
		void lista();

	protected:
		DOMNode *pDnode;
		XMLGen *pXml;
//		int iIdPessoaUsuarioAlteracao;
		int iQtdRegistros;
		char *pszOperacao;
		char pszNmLista[255];
		char *pszInStatusCarga;
		struct LISTACAMPANHA *pstListaCampanha;
		void listaGeral();
		void listaStatus();
		void montaXML();
};
#endif /* SEL_LISTACAMPANHAHH */
