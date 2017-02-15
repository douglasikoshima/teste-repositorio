/**
 * 
 * @modulo  libins_listacampanha
 * @usecase insereListaCampanha
 * @author  Mario
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:35 $
 **/

#include "../../negocio/cmputil/include/campanha.hpp"
#include "ins_listaconteudo.hpp"
#include "ins_pessoalista.hpp"
#include "get_idpessoadepara.hpp"


DECLARE_TUXEDO_SERVICE(INSLISTACONT);  

#define FILEREAD_OK 1
#define FILEREAD_ERROR -1
#define ERR_CAMPANHA_LIBINSLISTACONT_TEL_CD	"00E1000"
#define ERR_CAMPANHA_LIBINSLISTACONT_TEL_MSG "Erro processar numero de telefone"

void implINSLISTACONT::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	//
	// declaracao de variaveis
	char * ptUsr;
	//
	char strLista[20];  
	int  idLista;
	long qtdReg;
	//
	int idUsuario;
	char* nome;
	int  telefone;				//numero de telefone do arquivo
	char* documento;       // NOME DO DOCUMENTO NO ARQUIVO
	//
	DOMNode* registros;
	DOMNode* item;
	char* aux;
	int idListaConteudo;

	ULOG_START("INSLISTACONT");
	ptUsr = getUser();
    idUsuario = atoi( ptUsr );
	xml_g->createTag("tns:CarregarListaCampanhaConteudoVO");

    //  Obtendo os dados do registro a ser incluido
	get_tag(strLista, dnode, "idLista", 0, 0);
	idLista = atoi( strLista );
	registros = TuxHelper::walkDOM(dnode,"registros",0);
	if ( registros != NULL ) 
	{
		xml_g->createTag("registros");
 

		for(qtdReg = 0;;qtdReg++) {
			item = TuxHelper::walkDOM(dnode,"item",qtdReg);
			if (item != NULL) 
			{
				nome = TuxHelper::walkTree(item,"nome",0);
				aux = TuxHelper::walkTree(item,"telefone",0);
				try {
					telefone = atoi(aux);
				} catch(...)
				{
					throw new TuxBasicSvcException( ERR_CAMPANHA_LIBINSLISTACONT_TEL_CD, ERR_CAMPANHA_LIBINSLISTACONT_TEL_MSG);
				}
				documento = TuxHelper::walkTree(item,"documento",0);

				//
				// invocando o registro da lista no banco
				try {
					if ( ins_listaconteudo( idUsuario, idLista, telefone, nome, documento, &idListaConteudo ) != -1)
					{
						xml_g->addItem( "id", idListaConteudo );
					}
				} catch (TuxException& te)
				{
					throw te;
				}

			} else {
				printf("\nfim de varre nodes - item");
				break;
			}
		}
		xml_g->closeTag();
		xml_g->addItem( "quantidade", qtdReg);
	}
	else
	{
		xml_g->addItem( "erro", "nao encontrados registros para incluir");
	}
	
	xml_g->closeTag();
	setStatusCode(OKCMP,"Successfull Execution");

	ULOG_END("INSLISTACONT");

}

