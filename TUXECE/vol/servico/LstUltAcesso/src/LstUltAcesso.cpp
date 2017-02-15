#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTULTACESSO);

void implLSTULTACESSO::Execute(DOMNode* dnode, XMLGen* xml_g) {
	
	CTuxHelperClever helper;

	CPessoa oPessoa;
	CRelacionamento oRelac;
	list< CRelacionamento >	lstORelac;

	char*  pcTagXmlIn = NULL;
	long   iIdPessoa = -1;
 	int    iIdCanal = -1;
	int	   i;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");

	///
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal");
	
	oPessoa.setIdPessoa(iIdPessoa);

	try{
	
		oPessoa.consultarHistQtdRelacCanal( lstORelac, 2, iIdCanal);		
	
	}

	catch(...){	}


	setStatusCode("11W0001", "NAO EXISTE ACESSO AO CANAL SELECIONADO");

	xml_g->createTag("LSTULTACESSOVO");
	xml_g->addProp("xmlns", "capa.vol.vivo.com.br/vo");

	// Seta mensagem de retorno - header	

	if( 0 < lstORelac.size() ) {
		for (i=0 ; i<=1 ; i++)	{
			oRelac = lstORelac.front();			

			if (lstORelac.size() == 1){
				xml_g->addItem( "dtUltimoAcesso", oRelac.getDtRelacionamento());
				xml_g->addItem( "hrUltimoAcesso", oRelac.getHrRelacionamento());
			
				//Considera o penúltimo acesso, pois o último foi o login atual.
				setStatusCode("11I0000", "CONSULTA EFETUADA COM SUCESSO");
			}
			
			if (lstORelac.size() > 0)
				lstORelac.pop_front();
		}
	}

	xml_g->closeTag();

}
