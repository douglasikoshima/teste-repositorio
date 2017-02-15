/**
 * 
 * @modulo  libins_listacampanha
 * @usecase insereListaCampanha
 * @author  Mario
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:01 $
 **/

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSLISTACAMP);  

extern int ins_lista(char* pnmLista, char* pnmArquivoLista, long* pidLista,
	char* idUsuario, char* pnmIdLista, char* pnmDescricao);

void implINSLISTACAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	//
	// declaracao de variaveis
	char * ptUsr;
	char *nmLista;
	char *nmArquivoLista;
	char *nmIdLista;
	char nmDescricao[255];
	long  idLista;
	char idUsuario[21 + 1];

	ULOG_START("INSLISTACAMP");
	ptUsr = getUser();
//	strcpy(idUsuario, "2000011599" );
	strncpy(idUsuario, ptUsr, 21 );
	// força o terminador
	idUsuario[21] = '\0';
	nmIdLista=NULL;

	//  Obtendo nome da lista do xml de entrada
	nmLista=walkTree(dnode,"nmLista", 0);

	// Obtendo nome do arquivo fisico do xml de entrada
	// ficou convencionado que o nome do arquivo contem o 
	// caminho completo de busca, nao sendo necessaria nenhuma 
	// parametrizacao no servico Tuxedo
	nmArquivoLista=walkTree(dnode,"nmArquivoLista", 0);

	// Quando a carga do arquivo estiver concluida, o java
	// ira enviar o id da lista para alterar o status para
	// disponivel.
//	get_tag(nmIdLista, dnode, "valor", 0, 0);
	nmIdLista=walkTree(dnode,"valor",0);

	//
	// invocando o registro da lista no banco
	if (ins_lista(nmLista, nmArquivoLista, &idLista, (char*)idUsuario,
		nmIdLista, nmDescricao) > 0)
	{
		//
		// Gera XML de saida contendo o id da lista recém-incluida
		xml_g->createTag("tns:retornoVO");
		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

		xml_g->addItem( "descricao",nmDescricao);
		xml_g->addItem( "valor",idLista);
		xml_g->closeTag();
	}	
	setStatusCode(OKCMP,OKMSG);
	ULOG_END("INSLISTACAMP");

	free(nmLista);
	free(nmArquivoLista);
	free(nmIdLista);
}
