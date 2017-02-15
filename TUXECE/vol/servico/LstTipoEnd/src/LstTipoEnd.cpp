/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Endereco/Endereco.hpp>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Util/Util.hpp>

DECLARE_TUXEDO_SERVICE(LSTTIPOEND);

void implLSTTIPOEND::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	char*	pcTagXmlIn = NULL;
	char	cOperacao[256]="";


	CPessoa oPessoa;
	CEndereco oEndereco;
	list< CEndereco >	lstOEnd;
	list< CEndereco > lstOComplemento;


	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	if (pcTagXmlIn != NULL)
		strcpy(cOperacao, pcTagXmlIn);

	if (strcmp(CUtil::trim(cOperacao), "") == 0)
	{
		try
		{
			CEndereco::consultarTipoEnd( lstOEnd );
		}
		catch( ... )
		{
			setStatusCode("11W0002", "Não foi possível efetuar a consulta");
			return;
		}

		if ( lstOEnd.size() == 0)
		{
			setStatusCode("11W0001", "Dados_Nao_encontrados");			
			return;
		}

		xml_g->createTag("LSTTIPOENDVO");
		xml_g->addProp("xmlns", "dados.vol.vivo.com.br/vo");

		// Monta o XML de saída
		while( 0 < lstOEnd.size() ) 
		{
			oEndereco = lstOEnd.front();

			xml_g->createTag("TIPOENDERECO");
				xml_g->addItem( "idTipoEndereco", oEndereco.getIdTipoEndereco() );
				xml_g->addItem( "sgTipoEndereco", oEndereco.getSgTipoEndereco() );
				xml_g->addItem( "dsTipoEndereco",oEndereco.getDsTipoEndereco() );
			xml_g->closeTag();

			lstOEnd.pop_front();

		}

		xml_g->closeTag();


	}else if (strcmp(CUtil::trim(cOperacao),"complemento") == 0)
	{
		
		try{

			CEndereco::consultarTipoComplemento(lstOComplemento);
			
		}catch( ... ){

			setStatusCode("11W0002", "Não foi possível efetuar a consulta");
			return;

		}

		xml_g->createTag("tiposComplementoVO");
		xml_g->addProp("xmlns","dados.vol.vivo.com.br/vo");
		// Monta o XML de saída
		while( 0 < lstOComplemento.size() )
		{
			oEndereco = lstOComplemento.front();


			xml_g->createTag("tipoComplemento");
				xml_g->addItem("idTipoComplemento", oEndereco.getIdTipoComplemento());
				xml_g->addItem("sgTipoComplemento", oEndereco.getSgTipoComplemento());
				xml_g->addItem("dsTipoComplemento", oEndereco.getDsTipoComplemento());
			xml_g->closeTag();


			lstOComplemento.pop_front();
		}
		xml_g->closeTag();
	}
	
	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Pesquisa_efetuada_com_sucesso");
	return;
}

