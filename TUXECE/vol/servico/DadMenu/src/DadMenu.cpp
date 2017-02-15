#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Menu/Menu.hpp>
#include <Linha/Linha.hpp>
#include <Pessoa/Pessoa.hpp>
#include <Relacionamento/Relacionamento.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


static char *STR_11IOK                   = "11I0000";
static char *STR_11IOKMSG                = "CONSULTA EFETUADA COM SUCESSO";
static char *STR_11IOKALTMSG             = "ALTERACAO EFETUADA COM SUCESSO";

static char *STR_11IW0001                = "11W0001";
static char *STR_11W01MSG                = "Não é possível desativar item raiz no Canal VOL";

#define STR_CONSULTARFILTROS			 "consultarFiltros"
#define STR_CONSULTARMENU				 "consultarMenu"
#define STR_ALTERARMENU					 "alterarMenu"



DECLARE_TUXEDO_SERVICE(DADMENU);



static void consultarFiltros(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	char	 cGet[256];
	char*    pcTagXmlIn = NULL;	
	CMenu oMenu;
	CMenu oFiltros;
	list < CMenu> lstMenu;
	list < CMenu> lstFiltros;
	int i = 0;
	int iIdCanal  = 1;
	int iIdTipoLinha = 0;
	bool bFiltraItensMenu = false;


	lstFiltros.clear();

	while (iIdCanal || iIdTipoLinha ){
	

		iIdCanal = iIdTipoLinha = 0;
	
		pcTagXmlIn = helper.walkTree(dnode,"idCanal", i);
		
		if (pcTagXmlIn != NULL)
			iIdCanal = atoi(pcTagXmlIn);
		
		oFiltros.setIdCanal(iIdCanal);
		
		pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", i);

		if (pcTagXmlIn != NULL)
			iIdTipoLinha = atoi(pcTagXmlIn);
		
		oFiltros.setIdTipoLinha(iIdTipoLinha);

		if (iIdCanal || iIdTipoLinha)
			bFiltraItensMenu = true;

		i++;

		lstFiltros.push_back(oFiltros);
	}


	

	xml_g->createTag("VOLTAVManterMenuVO");
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

		xml_g->createTag("MenuListaFiltrosVO");

		oMenu.consultarMenu(lstFiltros, lstMenu);

		while( 0 < lstMenu.size() ) {

			oMenu = lstMenu.front();

			xml_g->createTag("ItemMenu");				

			xml_g->addItem("idItemMenu", oMenu.getIdItemMenu());

			xml_g->addItem("nmItemMenu", oMenu.getNmItem());

			xml_g->closeTag();

			lstMenu.pop_front();


		}

		if (bFiltraItensMenu == false){
		
			oMenu.consultarTipoLinhaDisp(lstMenu);
				
			while( 0 < lstMenu.size() ) {

				oMenu = lstMenu.front();

				xml_g->createTag("TipoLinha");				

				xml_g->addItem("idTipoLinha", oMenu.getIdTipoLinha());

				xml_g->addItem("dsTipoLinha", oMenu.getDsTipoLinha());

				xml_g->closeTag();

				lstMenu.pop_front();

			}
			
			oMenu.consultarUFDisp(lstMenu);

			while( 0 < lstMenu.size() ) {
							
				oMenu = lstMenu.front();

				xml_g->createTag("Uf");

				sprintf(cGet, "%d", oMenu.getIdUF());

				xml_g->addItem("idUf", cGet);

				xml_g->addItem("sgUf", oMenu.getSgUF());

				xml_g->closeTag();

				lstMenu.pop_front();				
			}

			oMenu.consultarTipoRelacionamentoPessoa(lstMenu);

			while( 0 < lstMenu.size() ) {
							
				oMenu = lstMenu.front();


				if (oMenu.getIdGrupo() != 2 ) { //nao conta cliente pf
					
					xml_g->createTag("GrupoUsuario");

					xml_g->addItem("idGrupoUsuario", oMenu.getIdGrupo());

					xml_g->addItem("nmGrupoUsuario", oMenu.getDsGrupo());

					xml_g->closeTag();
				}
				lstMenu.pop_front();				
			}

		
		}
		
		xml_g->closeTag();
	xml_g->closeTag();
		


	statusCode.setCod(STR_11IOK);
	statusCode.setMsg(STR_11IOKMSG);

}


static void consultarMenu(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	char	 cGet[256];
	char*    pcTagXmlIn = NULL;	

	int  iIdItemMenu = 0;
	int  iIdCanal = 0;
	int  iIdTipoLinha = 0;
	int	 iIdUF = 0;
	int  iIdGrupo = 1;
	int	 iNrPagina = 1;
	int  iTotalPaginas = 0;
	CLinha oLinha;
	list < CLinha > lstLinha;
	CMenu oMenu;
	CMenu oFiltros;
	list < CMenu> lstMenu;
	list < CMenu> lstFiltros;
	list < CMenu> lstFiltrosPagina;
	int i = 0;

	while (iIdItemMenu || iIdCanal || iIdTipoLinha || iIdGrupo || iIdUF){
	

		iIdItemMenu = iIdCanal = iIdTipoLinha = iIdGrupo = iIdUF = 0;

		pcTagXmlIn = helper.walkTree(dnode,"idItemMenu", i);

		if (pcTagXmlIn != NULL)
			iIdItemMenu = atoi(pcTagXmlIn);

		oFiltros.setIdItemMenu(iIdItemMenu);
		
		pcTagXmlIn = helper.walkTree(dnode,"idCanal", i);
		
		if (pcTagXmlIn != NULL)
			iIdCanal = atoi(pcTagXmlIn);
		
		oFiltros.setIdCanal(iIdCanal);
		
		pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", i);

		if (pcTagXmlIn != NULL)
			iIdTipoLinha = atoi(pcTagXmlIn);
		
		oFiltros.setIdTipoLinha(iIdTipoLinha);


		pcTagXmlIn = helper.walkTree(dnode,"idUF", i);

		if (pcTagXmlIn != NULL)
			iIdUF = atoi(pcTagXmlIn);
		
		oFiltros.setIdUF(iIdUF);

		pcTagXmlIn = helper.walkTree(dnode,"idGrupo", i);

		if (pcTagXmlIn != NULL)
			iIdGrupo = atoi(pcTagXmlIn);
		
		oFiltros.setIdGrupo(iIdGrupo);

		lstFiltros.push_back(oFiltros);
		
		lstFiltrosPagina.push_back(oFiltros);

		i++;
	}

	pcTagXmlIn = helper.walkTree(dnode,"nrPagina", 0);

	if (pcTagXmlIn != NULL)
		iNrPagina =atoi(pcTagXmlIn);

	

	iTotalPaginas = oMenu.consultarNrPaginas(lstFiltrosPagina);


	try{
	
		oMenu.consultarListagemMenu(iNrPagina, lstFiltros, lstMenu);
	}

	catch( ... ){ 
	
		statusCode.setCod("11W0002");
		statusCode.setMsg("Consulta não efetuada");
		return;
	}

	xml_g->createTag("VOLTAVManterMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

		xml_g->addItem("totalPaginas", iTotalPaginas);
		

		while( 0 < lstMenu.size() ) {

			oMenu = lstMenu.front();
				
			xml_g->createTag("ResultadoMenuVO");		

			xml_g->addItem("idCanal", oMenu.getIdCanal());

			xml_g->addItem("nmItemPai", oMenu.getNmItemPai());

			xml_g->addItem("idUf", oMenu.getIdUF());

			xml_g->addItem("sgUf", oMenu.getSgUF());

			xml_g->addItem("idTipoLinha", oMenu.getIdTipoLinha());

			xml_g->addItem("dsTipoLinha", oMenu.getDsTipoLinha());

			xml_g->addItem("idGrupo", oMenu.getIdGrupo());

			xml_g->addItem("idItemMenu", oMenu.getIdItemMenu());

			xml_g->addItem("nmItem", oMenu.getNmItem());

			xml_g->addItem("inAtivo", oMenu.getInPermitido());

			xml_g->addItem("idGrupoUsuario", oMenu.getIdGrupo());

			xml_g->addItem("nmGrupoUsuario", oMenu.getDsGrupo());

			
			xml_g->closeTag();

			lstMenu.pop_front();

		}

	xml_g->closeTag();


	statusCode.setCod(STR_11IOK);
	statusCode.setMsg(STR_11IOKMSG);


}

static void alterarMenu(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	char	 cGet[256];
	char*    pcTagXmlIn = NULL;	

	int  iIdItemMenu = 0;
	int  iIdCanal = 0;
	int  iIdTipoLinha = 0;
	int	 iIdUF = 0;
	int  iIdGrupo = 0;
	int	 iNrPagina = 1;
	int  iTotalPaginas = 0;
	int  iAtivo = 0;
	CMenu oMenu;
	list < CMenu> lstMenu;
	int intRC = 0;


	pcTagXmlIn = helper.walkTree(dnode,"idItemMenu", 0);

	if (pcTagXmlIn != NULL)
		iIdItemMenu = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);

	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);

	if (pcTagXmlIn != NULL)
		iIdTipoLinha = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idUf", 0);

	if (pcTagXmlIn != NULL)
		iIdUF = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idGrupo", 0);

	if (pcTagXmlIn != NULL)
		iIdGrupo = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode,"inAtivo", 0);

	if (pcTagXmlIn != NULL)
		iAtivo =atoi(pcTagXmlIn);

	char* login = helper.walkTree(dnode,"login", 0);
	char* ipUsuario =  helper.walkTree(dnode,"ipUsuario", 0);
	char* nmItemMenu = helper.walkTree(dnode,"nmItemMenu", 0);

	xml_g->createTag("VOLTAVManterMenuVO");
	xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );
	


	try{	

		intRC = oMenu.alterarMenu(iIdItemMenu, iIdCanal, iIdUF, iIdTipoLinha, iIdGrupo, !iAtivo,login,ipUsuario,nmItemMenu);

		if (intRC == 0){
			statusCode.setCod(STR_11IOK);
			statusCode.setMsg(STR_11IOKALTMSG);
		}else{

			statusCode.setCod(STR_11IW0001);
			statusCode.setMsg(STR_11W01MSG);
		}

	}
	catch( ...){

		statusCode.setCod("11W0003");
		statusCode.setMsg("Alteração não efetuada");
		
	}

	xml_g->closeTag();
}




static void consultarMenuSuperior(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CMenu oMenu;
	list < CMenu> lstMenuPai;
	char*    pcTagXmlIn = NULL;	
	int     iIdCanal = 0;
	
	xml_g->createTag("ListaMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );
	
	pcTagXmlIn =  helper.walkTree(dnode,"idCanal", 0);

	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);

	try{	

		oMenu.consultarMenuPai( iIdCanal, lstMenuPai );
	}

	catch ( ...) 
	{
	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKALTMSG);
	}

	while( 0 < lstMenuPai.size() )
	{

		oMenu = lstMenuPai.front();

		xml_g->createTag("ItemMenuVO");
		xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo");

			xml_g->addItem("idItemMenu", oMenu.getIdItemMenuPai());
			xml_g->addItem("nmMenu", oMenu.getNmItemPai());
			xml_g->addItem("sqSequencia", oMenu.getSqApresentacao());
		xml_g->closeTag();

		lstMenuPai.pop_front();

	}

	xml_g->closeTag();

	statusCode.setCod(STR_11IOK);
	statusCode.setMsg("Consulta efetuado com sucesso");

}




static void consultarMenuFilho(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CMenu			 oMenu;
	list < CMenu>	 lstMenuFilho;
	int		 		 iIdItemMenu = 0;
	char*			 pcTagXmlIn = NULL;	
	int				 iIdCanal = 0;

	pcTagXmlIn = helper.walkTree(dnode, "idItemMenu",  0);

	if (pcTagXmlIn != NULL)
		iIdItemMenu = atoi(pcTagXmlIn);
		
	pcTagXmlIn =  helper.walkTree(dnode,"idCanal", 0);

	if (pcTagXmlIn != NULL)
		iIdCanal = atoi(pcTagXmlIn);
	
	xml_g->createTag("ListaMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );
	


	try{	

		oMenu.consultarMenuFilho( iIdItemMenu, iIdCanal, lstMenuFilho );
	}

	catch ( ...) 
	{
	
		statusCode.setCod(STR_11IOK);
		statusCode.setMsg(STR_11IOKALTMSG);
	}

	while( 0 < lstMenuFilho.size() )
	{

		oMenu = lstMenuFilho.front();

		xml_g->createTag("ItemMenuVO");
		xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo");
			xml_g->addItem("idItemMenu", oMenu.getIdItemMenu());
			xml_g->addItem("nmMenu", oMenu.getNmItem());
			xml_g->addItem("sqSequencia", oMenu.getSqApresentacao());
		xml_g->closeTag();

		lstMenuFilho.pop_front();

	}

	
	
	xml_g->closeTag();



	statusCode.setCod(STR_11IOK);
	statusCode.setMsg("Consulta efetuado com sucesso");

}



static void alterarOrdemMenu(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CMenu			 oMenu;
	list < CMenu>	 lstMenuFilho;
	int		 		 iIdItemMenu = 1;
	char*			 pcTagXmlIn = NULL;	
	int			     i  =0;
	int				 iSqApresentacao = 0;
	DOMNode			*po_ItemMenuVO;	

	while (iIdItemMenu)
	{
		po_ItemMenuVO = helper.walkDOM(dnode , "ItemMenuVO", i);

		iIdItemMenu = 0;

		pcTagXmlIn = helper.walkTree(po_ItemMenuVO,"idItemMenu", 0);

			
		if (pcTagXmlIn != NULL)
			iIdItemMenu = atoi(pcTagXmlIn);

		pcTagXmlIn = helper.walkTree(po_ItemMenuVO,"sqSequencia", 0);
			
		if (pcTagXmlIn != NULL)
			iSqApresentacao= atoi(pcTagXmlIn);

		oMenu.alterarOrdemMenu(iIdItemMenu, iSqApresentacao);

	
		i++;

	}

	
	xml_g->createTag("ListaMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	xml_g->closeTag();
	
	
	statusCode.setCod(STR_11IOK);
	statusCode.setMsg("alteração efetuada com sucesso");

	
}

static void insereMenuParceiro(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode, int idUsuario)
{

	CTuxHelperClever helper;
	CMenu			 oMenu;
	char*			 pcTagXmlIn = NULL;	
	char             cNmParceiro[256];
	char			 cDsIpParceiro[201];
	char			 cDsUrlParceiro[201];
	int				 idItemMenuPai = 0;
	int				 idContato = 0;


	
	pcTagXmlIn = helper.walkTree(dnode, "nmParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "nmParceiro");			

	strcpy (cNmParceiro, pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "dsIpParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "dsIpParceiro");

	strcpy (cDsIpParceiro, pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "dsUrlParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "dsUrlParceiro");
			
	strcpy (cDsUrlParceiro, pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "idItemPai", 0);

	ASSERT_STR(pcTagXmlIn, "idItemPai");
			
	idItemMenuPai = atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "idContato", 0);

	ASSERT_STR(pcTagXmlIn, "idContato");
			
	idContato = atoi(pcTagXmlIn);


	oMenu.insereMenuParceiro(cNmParceiro, cDsIpParceiro, cDsUrlParceiro, idItemMenuPai, idContato, idUsuario);

	
	xml_g->createTag("ListaMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	xml_g->closeTag();	
	
	statusCode.setCod(STR_11IOK);
	statusCode.setMsg("inclusão efetuada com sucesso");

}


static void alterarMenuParceiro(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode, int idUsuario)
{

	CTuxHelperClever helper;
	CMenu			 oMenu;
	char*			 pcTagXmlIn = NULL;	
	char             cNmParceiro[256];
	char			 cDsIpParceiro[201];
	char			 cDsUrlParceiro[201];
	int				 idItemMenu = 0;
	int				 idContato = 0;


	
	
	pcTagXmlIn = helper.walkTree(dnode, "nmParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "nmParceiro");			

	strcpy (cNmParceiro, pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "dsIpParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "dsIpParceiro");

	strcpy (cDsIpParceiro, pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "dsUrlParceiro", 0);

	ASSERT_STR(pcTagXmlIn, "dsUrlParceiro");
			
	strcpy (cDsUrlParceiro, pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idContato", 0);
	ASSERT_INT(idContato, pcTagXmlIn, "idContato");


	pcTagXmlIn = helper.walkTree(dnode,"idItemMenu", 0);
	ASSERT_INT(idItemMenu, pcTagXmlIn, "idItemMenu");


	oMenu.alterarMenuParceiro(cNmParceiro, cDsIpParceiro, cDsUrlParceiro, idItemMenu, idContato, idUsuario);

	
	xml_g->createTag("ListaMenuVO");
		xml_g->addProp( "xmlns", "voltav.fo.vivo.com.br/vo" );

	xml_g->closeTag();	
	
	statusCode.setCod(STR_11IOK);
	statusCode.setMsg("inclusão efetuada com sucesso");

}



void implDADMENU::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;
	int			  idUsuario = 0;
  char *user = this->getUser();

   
    idUsuario = atoi( user );


	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");
	
	if (!strcmp(pcTagXmlIn, STR_CONSULTARFILTROS))
		consultarFiltros(dnode, xml_g, statusCode);		
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARMENU))
		consultarMenu(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, STR_ALTERARMENU))
		alterarMenu(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, "consultarMenuSuperior"))
		consultarMenuSuperior(dnode, xml_g, statusCode);
	else if (!strcmp(pcTagXmlIn, "consultarMenuFilho"))
		consultarMenuFilho(dnode, xml_g,  statusCode);
	else if (!strcmp(pcTagXmlIn, "alterarOrdemMenu"))
		alterarOrdemMenu(dnode, xml_g,  statusCode);
	else if (!strcmp(pcTagXmlIn, "insereMenuParceiro"))
		insereMenuParceiro(dnode, xml_g,  statusCode, idUsuario);	
	else if (!strcmp(pcTagXmlIn, "alterarMenuParceiro"))
		alterarMenuParceiro(dnode, xml_g,  statusCode, idUsuario);	

	
	setStatusCode(statusCode.getCod(), statusCode.getMsg());
}