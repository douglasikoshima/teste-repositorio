/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:16 $
 **/ 

#include <stdio.h>
#include <string.h>
#include <tuxfw.h>

#ifndef CAssocCampanha
	#define CAssocCampanha

#include "cAssocCampanhaPC.h"
#include "../../commons/Collection/include/Collection.h"
#include "../../commons/discadorUtil/include/AssociaContatosCampanha.h"
#include "../../commons/discadorUtil/include/AssociaUsuarioCampanha.h"
#include "RegistroContato.h"

#define CC_INFORMAR_NOME_ARQUIVO       "05E00001"
#define CC_ERRO_CRIAR_ARQUIVO          "05E00002"
#define CC_ERRO_PATH_NAO_ENCONTRADO    "05E00003"
#define CC_ERRO_IDENTIFICACAO_SERVIDOR "05E00004"


class cAssocCampanha:public TuxBasicSvc
{

	public:
		
		cAssocCampanha();

		void associaCampanha(int idSubCampanha);
		void associaUsuariosCampanha(int idSubCampanhaHistorico);

	private:
		char* geraArquivo(int idSubCampanhaHistorico);
		void  registraContatos(FILE* arquivo, Collection* registros);
		FILE* criaAquivo(char* novoArquivo);
		char*  montaLinkFTP(char* endereco);

};

#endif

