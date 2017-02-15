#include <stdio.h>
#include <tuxfw/tuxfw.h>
#define   COD_ERR_EXCEPT "13E0000"
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Senha/Senha.hpp>
#include <PessoaAbstract/PessoaAbstract.hpp>
#include <Lojista/Lojista.hpp>
#include <Venda/Venda.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>

static char *STR_13IOK                   = "13I0000";
static char *STR_13IOKMSG                = "CONSULTA EFETUADA COM SUCESSO";
static char *STR_13IOKALTMSG             = "ALTERACAO EFETUADA COM SUCESSO";
static char *STR_13IOKVALSENMSG          = "SENHA VALIDADA COM SUCESSO";
static char *STR_13IOKREGMSG             = "REGISTRO EFETUADO COM SUCESSO";


static char *STR_13W0001                 = "13W0001";
static char *STR_13W0002                 = "13W0002";
static char *STR_13W0003                 = "13W0003";
static char *STR_13W0004                 = "13W0004";
static char *STR_13W0005                 = "13W0005";
static char *STR_13W0006                 = "13W0006";

static char *STR_13WNOTALTMSG            = "ALTERACAO NAO EFETUADA";
static char *STR_13WNOTCON               = "CONSULTA NAO EFETUADA";
static char *STR_13WNOTIDUFOPER			 = "IDUFOPERADORA NAO ENCONTRADO NO BANCO";
static char *STR_13WNOTDSCUPOM			 = "DESCRICAO NAO ENCONTRADA";


static char *STR_13WNOTAG				 = "Xml de entrada inválido, Tag obrigatória nrsap ou idSitefVenda";

static char *STR_13W9999                 = "13W9999";
static char *STR_13WEXCDESC              = "ERRO DESCONHECIDO";



DECLARE_TUXEDO_SERVICE(DADLOJATERM);


static void consultarMunicipios(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstMunicipios;
	char		*ciIdUF = NULL;	
	int			iIdUF = 0;

	ciIdUF = helper.walkTree(dnode,"idUF", 0);

	iIdUF  = atoi(ciIdUF);

	lojista.consultarListaMunicipio(xml_g, iIdUF);
	statusCode.setCod(STR_13IOK);
	statusCode.setMsg(STR_13IOKMSG);
	
}


static void consultarUF(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 

	lojista.consultarListaUF(xml_g);
	statusCode.setCod(STR_13IOK);
	statusCode.setMsg(STR_13IOKMSG);



}

static void consultarLojas(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstLojas;
	char		*ciIdUF = NULL;	
	int			iIdUF = 0;
	char		*cNmLoja = NULL;
	char		*cNmMunicipio = NULL;


	ciIdUF = helper.walkTree(dnode,"idUF", 0);

	cNmMunicipio = helper.walkTree(dnode, "nmMunicipio", 0);

	iIdUF  = atoi(ciIdUF);

	lojista.consultarListaLojas(xml_g, iIdUF, cNmMunicipio);
	statusCode.setCod(STR_13IOK);
	statusCode.setMsg(STR_13IOKMSG);

}



static void consultarTerminais(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	char *cIdPessoaDePara = NULL;
	int	 iIdPessoaDePara = 0;
	char cGet[30] = "";
	CLojista		  lojista; 

	cIdPessoaDePara = helper.walkTree(dnode,"idPessoaDePara", 0);

	
	iIdPessoaDePara  = atoi(cIdPessoaDePara);

	
	try
	{
		lojista.consultarTerminaisLoja(xml_g, iIdPessoaDePara);
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode){		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao consultar lista Lojas");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		


}

static void consultarListaTerminais(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstTerminais;
	char		*cIdPessoaDePara = NULL;
	int			iIdPessoaDePara = 0;
	char		*ciIdUF = NULL;	
	int			iIdUF = -1;
	char		*cNmLoja = NULL;
	char		*cNmMunicipio = NULL;
	char		*cInLiberadoRecarga = NULL;
	int			iLiberadoRecarga = -1;
	char		*cInLiberadoPagamento = NULL;
	int			iLiberadoPagamento = -1;
	char		*cIdTerminal = NULL;
	int			iIdTerminal = -1;
	char        cGet[256] = "";
	int			iNrPagina = 0;
	char		*pcTagXmlIn = NULL;
	
	ciIdUF = helper.walkTree(dnode,"idUF", 0);

	iIdUF = atoi (ciIdUF);

	cNmMunicipio = helper.walkTree(dnode, "nmMunicipio", 0);

	cIdPessoaDePara = helper.walkTree(dnode,"idPessoaDePara", 0);

	if (cIdPessoaDePara != NULL)	
		iIdPessoaDePara  = atoi(cIdPessoaDePara);

	cNmMunicipio = helper.walkTree(dnode, "nmMunicipio", 0);

	if (cNmMunicipio != NULL)
		if (strcmp(cNmMunicipio, "0") == 0)
			cNmMunicipio = NULL;

	iIdPessoaDePara  = atoi(cIdPessoaDePara);

	cInLiberadoRecarga =  helper.walkTree(dnode, "inLiberadoRecarga", 0);

	if (cInLiberadoRecarga != NULL)
		iLiberadoRecarga = atoi(cInLiberadoRecarga);

	cInLiberadoPagamento =  helper.walkTree(dnode, "inLiberadoPagamento", 0);

	if (cInLiberadoPagamento != NULL)
		iLiberadoPagamento = atoi(cInLiberadoPagamento);
	
	cIdTerminal = helper.walkTree(dnode, "idTerminal", 0);

	if (cIdTerminal != NULL)
		iIdTerminal = atoi(cIdTerminal);
	
	pcTagXmlIn = helper.walkTree(dnode, "nrPagina", 0);

	iNrPagina = atoi(pcTagXmlIn);

	lojista.consultarListaTerminais(xml_g, iIdUF, iIdTerminal, iIdPessoaDePara, cNmMunicipio, iLiberadoRecarga, iLiberadoPagamento, iNrPagina);
	statusCode.setCod(STR_13IOK);
	statusCode.setMsg(STR_13IOKMSG);
	

}


static void alterarTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode, char *user)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstTerminais;
	char		*cIdPessoaDePara = NULL;
	int			iIdPessoaDePara = 0;
	char		*ciIdUF = NULL;	
	int			iIdUF = -1;
	char		*cNmLoja = NULL;
	char		*cNmMunicipio = NULL;
	char		*cInLiberadoRecarga = NULL;
	int			iLiberadoRecarga = 0;
	char		*cInLiberadoPagamento = NULL;
	int			iLiberadoPagamento = 0;
	char		*cIdTerminal = NULL;
	int			iIdTerminal = -1;
	char        cGet[256] = "";
	char		*cNrTerminal =  NULL;
	char	    *cNrIpTerminal = NULL;
	char		*cdLojaOperadoraCartao = NULL;
	char		*cIdUFOperadora = NULL;
	char        *cCdSitefSenha = NULL;
	char		*cIdCor = NULL;
	char		*cIdPessoa = NULL;
	char		*cNmPessoa = NULL;
	char		*cNmLocalidade = NULL;
	char		*cNmBairro = NULL;
	char	    *cNmTipoLogradouro = NULL;
	char		*cNmTituloLogradouro = NULL;
	char        *cNrEndereco = NULL;
	char		*cDsEnderecoComplemento = NULL;
	char		*cNrCep = NULL;
	char        *cNmLogradouro = NULL;
	int         iIdPessoa;
	int			iIdUfOperadora = 0;
	int			iIdCor = 0;
	char		*pcTagXmlIn = NULL;
	int         intRC = 0;
	int			iIdUser = 0;

	
	iIdUser = atoi(user);

	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	lojista.setIdTerminal(iIdTerminal);

	pcTagXmlIn = helper.walkTree(dnode,"idUF", 0);
	ASSERT_INT(iIdUF, pcTagXmlIn, "idUF");
	lojista.setIdUF(iIdUF);


	pcTagXmlIn = helper.walkTree(dnode,"idUfOperadora", 0);
	ASSERT_INT(iIdUfOperadora, pcTagXmlIn,  "idUfOperadora");
	lojista.setIdUFOperadora(iIdUfOperadora);


	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");
	lojista.setIdPessoaDeParaLoja(iIdPessoaDePara);


	cInLiberadoRecarga =  helper.walkTree(dnode, "inLiberadoRecarga", 0);

	if (cInLiberadoRecarga != NULL)
		iLiberadoRecarga  = atoi(cInLiberadoRecarga);


	lojista.setInLiberadoRecarga(iLiberadoRecarga);


	cInLiberadoPagamento =  helper.walkTree(dnode, "inLiberadoPagamento", 0);

	if (cInLiberadoPagamento != NULL)
		iLiberadoPagamento  = atoi(cInLiberadoPagamento);


	lojista.setInLiberadoPagamento(iLiberadoPagamento);



	cIdCor	 = helper.walkTree(dnode, "idCor", 0);

	if (cIdCor != NULL)
		iIdCor = atoi (cIdCor);

	cNrTerminal = helper.walkTree(dnode, "nrTerminal", 0);
	ASSERT_STR(cNrTerminal,"nrTerminal");
	lojista.setNrTerminal(cNrTerminal);

	
	cNrIpTerminal = helper.walkTree(dnode, "nrIpTerminal", 0);	
	ASSERT_STR(cNrIpTerminal,"nrIpTerminal");
	lojista.setNrIpTerminal(cNrIpTerminal);

	cdLojaOperadoraCartao = helper.walkTree(dnode, "cdLojaOperadoraCartao", 0);
	lojista.setCdLojaOperadoraCartao(cdLojaOperadoraCartao);

	cCdSitefSenha  = helper.walkTree(dnode, "cdSitefSenha", 0);
	lojista.setCdSitefSenha(cCdSitefSenha);

	cNmPessoa  = helper.walkTree(dnode, "nmPessoa", 0);
	lojista.setNmPessoa(cNmPessoa);

	cNmMunicipio = helper.walkTree(dnode, "nmMunicipio", 0);

	cNmLocalidade = helper.walkTree(dnode, "nmLocalidade", 0);

	cNmBairro = helper.walkTree(dnode, "nmBairro", 0);

	cNmTipoLogradouro = helper.walkTree(dnode, "nmTipoLogradouro", 0);
	
	cNmTituloLogradouro = helper.walkTree(dnode, "nmTituloLogradouro", 0);

	cNrEndereco = helper.walkTree(dnode, "nrEndereco", 0);

	cDsEnderecoComplemento = helper.walkTree(dnode, "dsEnderecoComplemento", 0);

	cNrCep = helper.walkTree(dnode, "nrCep", 0);

	cNmLogradouro = helper.walkTree(dnode, "nmLogradouro", 0);

	intRC = lojista.checaDadosTerminal();
		
	if (intRC == NO_ERROR)
	{		
		lojista.alterarEndereco(iIdTerminal, cNmMunicipio, cNmLocalidade, cNmBairro, cNmTipoLogradouro, cNmTituloLogradouro, cNmLogradouro, cNrEndereco, cDsEnderecoComplemento, cNrCep, iIdUF);

		iIdPessoa = lojista.getIdPessoa();

		lojista.alterarLoja(iIdPessoa, cNmPessoa); 

		lojista.alterarTerminal(iIdTerminal, iIdPessoaDePara, iIdUfOperadora, cNrTerminal, cNrIpTerminal, cCdSitefSenha, cdLojaOperadoraCartao, iLiberadoRecarga, iLiberadoPagamento, iIdCor);
		
		try{	

			lojista.registrarLog(iIdUser, cNmMunicipio, "Alteração");
		}
		catch( ... ){}

		statusCode.setCod(STR_13IOK);

		statusCode.setMsg("Alteração efetuado com sucesso");
	}
	else if (intRC == ERR_IP_EXISTENTE )
	{
		statusCode.setCod("13E0001");
		statusCode.setMsg("Número de IP já existente");
	}
	else if (intRC == ERR_NRTERMINAL_EXISTENTE)
	{
		statusCode.setCod("13E0002");
		statusCode.setMsg("Número de Terminal já existente");
	}
	else if (intRC == ERR_EXISTE_OUTRO_TERMIMNAL_RECARGA)
	{
		statusCode.setCod("13E0003");
		statusCode.setMsg("Já existe um terminal ativo com a funcionalidade de recarga e pagamento para esta loja.");
	}

	xml_g->createTag("ListaTerminaisVO");

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");


	xml_g->closeTag();


}




static void incluirTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode, char *user)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstTerminais;
	char		*cIdPessoaDePara = NULL;
	long			iIdPessoaDePara = 0;
	char		*ciIdUF = NULL;	
	int			iIdUF = -1;
	char		*cNmLoja = NULL;
	char		*cNmMunicipio = NULL;
	char		*cInLiberadoRecarga = NULL;
	int			iLiberadoRecarga = -1;
	char		*cInLiberadoPagamento = NULL;
	int			iLiberadoPagamento = -1;
	char		*cIdTerminal = NULL;
	int			iIdTerminal = -1;
	char        cGet[256] = "";
	char		*cNrTerminal =  NULL;
	char	    *cNrIpTerminal = NULL;
	char		*cdLojaOperadoraCartao = NULL;
	char		*cIdUFOperadora = NULL;
	char        *cCdSitefSenha = NULL;
	char		*cIdCor = NULL;
	char		*cIdPessoa = NULL;
	char		*cNmPessoa = NULL;
	char		*cNmLocalidade = NULL;
	char		*cNmBairro = NULL;
	char	    *cNmTipoLogradouro = NULL;
	char		*cNmTituloLogradouro = NULL;
	char        *cNrEndereco = NULL;
	char		*cDsEnderecoComplemento = NULL;
	char		*cNrCep = NULL;
	char        *cNmLogradouro = NULL;
	int         iIdPessoa;
	int			iIdUFOperadora = 0;
	int			iIdCor = 0;
	char		*pcTagXmlIn = NULL;
	int			intRC = 0;
	int			iIdUser= 0;


	iIdUser = atoi(user);
		
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	iIdPessoa = atoi(pcTagXmlIn);
	
	pcTagXmlIn = helper.walkTree(dnode,"idUF", 0);
	ASSERT_INT(iIdUF, pcTagXmlIn, "idUF");

	lojista.setIdUF(iIdUF);
	
	pcTagXmlIn = helper.walkTree(dnode,"idUfOperadora", 0);
	ASSERT_INT(iIdUFOperadora, pcTagXmlIn,  "idUfOperadora");

	lojista.setIdUFOperadora(iIdUFOperadora);


	cIdPessoaDePara = helper.walkTree(dnode,"idPessoaDePara", 0);
	
	if (cIdPessoaDePara != NULL)
		iIdPessoaDePara = atoi(cIdPessoaDePara);

	tuxfw_getlogger()->debug("iIdPessoaDePara  = %ld\r\n", iIdPessoaDePara );
	
	lojista.setIdPessoaDeParaLoja(iIdPessoaDePara);

	cInLiberadoRecarga =  helper.walkTree(dnode, "inLiberadoRecarga", 0);

	if (cInLiberadoRecarga!= NULL)
		iLiberadoRecarga = atoi(cInLiberadoRecarga);

	lojista.setInLiberadoRecarga(iLiberadoRecarga);

	cInLiberadoPagamento =  helper.walkTree(dnode, "inLiberadoPagamento", 0);

	if (cInLiberadoPagamento!= NULL)
		iLiberadoPagamento = atoi(cInLiberadoPagamento);

	lojista.setInLiberadoPagamento(iLiberadoPagamento);

	cIdCor	 = helper.walkTree(dnode, "idCor", 0);

	if (cIdCor != NULL)
		iIdCor = atoi (cIdCor);

	cNrTerminal = helper.walkTree(dnode,"nrTerminal", 0);	
	ASSERT_STR(cNrTerminal, "nrTerminal");	
	lojista.setNrTerminal(cNrTerminal);

	cNrIpTerminal = helper.walkTree(dnode, "nrIpTerminal", 0);
	lojista.setNrIpTerminal(cNrIpTerminal);

	cdLojaOperadoraCartao = helper.walkTree(dnode, "cdLojaOperadoraCartao", 0);
	lojista.setCdLojaOperadoraCartao(cdLojaOperadoraCartao);
	
	cCdSitefSenha = helper.walkTree(dnode,"cdSitefSenha", 0);
	ASSERT_STR(cCdSitefSenha, "cdSitefSenha");
	lojista.setCdSitefSenha(cCdSitefSenha);


	lojista.setIdTerminal(0);
	

	cNmPessoa     = helper.walkTree(dnode, "nmPessoa", 0);

	lojista.setNmPessoa(cNmPessoa);

	cNmMunicipio  = helper.walkTree(dnode, "nmMunicipio", 0);

	cNmLocalidade = helper.walkTree(dnode, "nmLocalidade", 0);

	cNmBairro     = helper.walkTree(dnode, "nmBairro", 0);

	cNmTipoLogradouro   = helper.walkTree(dnode, "nmTipoLogradouro", 0);
	
	cNmTituloLogradouro = helper.walkTree(dnode, "nmTituloLogradouro", 0);

	cNrEndereco = helper.walkTree(dnode, "nrEndereco", 0);

	cDsEnderecoComplemento = helper.walkTree(dnode, "dsEnderecoComplemento", 0);

	cNrCep = helper.walkTree(dnode, "nrCep", 0);

	cNmLogradouro = helper.walkTree(dnode, "nmLogradouro", 0);

	
	intRC = lojista.checaDadosTerminal();
	
	if (intRC == NO_ERROR)
	{
		
		lojista.setIdPessoaDeParaLoja(iIdPessoaDePara);

		if (iIdPessoaDePara == 0)

			lojista.incluirLoja(cNmPessoa, cNmMunicipio, cNmLocalidade, cNmBairro, cNmTipoLogradouro, cNmTituloLogradouro, cNmLogradouro, cNrEndereco, cDsEnderecoComplemento, cNrCep, iIdUF );

		tuxfw_getlogger()->debug("incluindo terminal\r\n");

		iIdPessoaDePara = lojista.getIdPessoaDeParaLoja();

		tuxfw_getlogger()->debug("iIdPessoaDePara = %ld\r\n", iIdPessoaDePara);

		
		lojista.incluirTerminal(iIdPessoaDePara, cNrTerminal, cNrIpTerminal, cCdSitefSenha, cdLojaOperadoraCartao, iLiberadoRecarga, iLiberadoPagamento, iIdCor, iIdUFOperadora);

		try{
		
			lojista.registrarLog(iIdUser, cNmMunicipio, "Inclusão");
		}
		catch(...) {}
							
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg("Inclusão efetuado com sucesso");
	}

	else if (intRC == ERR_IP_EXISTENTE )
	{
		statusCode.setCod("13E0001");
		statusCode.setMsg("Número de IP já existente");
	}
	else if (intRC == ERR_NRTERMINAL_EXISTENTE)
	{
		statusCode.setCod("13E0002");
		statusCode.setMsg("Número de Terminal já existente");
	}
	else if (intRC == ERR_EXISTE_OUTRO_TERMIMNAL_RECARGA)
	{
		statusCode.setCod("13E0004");
		statusCode.setMsg("Já existe um terminal ativo com a funcionalidade de recarga e pagamento para esta loja.");
	}

		

		
	xml_g->createTag("ListaTerminaisVO");

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");


	
	xml_g->closeTag();


}



static void excluirTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode, char *user)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	int			iIdTerminal = 0;
	char		*cNmLoja = NULL;
	char		*cNmMunicipio = NULL;
	char		*pcTagXmlIn = NULL;
	int			intRC = 0;
	int			iIdUser = 0;
	int			iIdPessoaDePara = 0;

	
	iIdUser = atoi(user);

	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");

	cNmMunicipio = helper.walkTree(dnode, "nmMunicipio", 0);

	cNmLoja = helper.walkTree(dnode, "nmPessoa", 0);
	lojista.setNmPessoa(cNmLoja);

		
	lojista.setIdTerminal(iIdTerminal);

	lojista.consultarDados();

	intRC = lojista.excluirTerminal(iIdTerminal, iIdPessoaDePara);

	if (intRC == ERR_TERMINAL_CONFIGURADO_RECARGA )
	{
		statusCode.setCod("13E0005");
		statusCode.setMsg("Este terminal não pode ser excluído, pois este configurado para realizar recarga e pagamento! Para excluir deverá alterar a configuração, removendo a opção de recarga e pagamento");
	}
	else if (intRC == ERR_EXISTE_HISTORICO_ATENDIMENTO)
	{
		statusCode.setCod("13E0008");
		statusCode.setMsg("Este terminal não pode ser excluído, pois existe histórico de atendimento.");

	}
		
	else
	{		
		
		try{
		
			lojista.registrarLog(iIdUser, cNmMunicipio, "Exclusão");
		}
		catch ( ... ) {	}

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg("Exclusão efetuada com sucesso");
	}		
	


	xml_g->createTag("ListaTerminaisVO");

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");


	
	xml_g->closeTag();


}


static void listaDadosLojaTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever  helper;
	CLojista		  lojista; 
	int			iIdTerminal = 0;
	int			iIdPessoaDePara = 0;
	char		*pcTagXmlIn = NULL;
	char		cGet[256] ="";
	

	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");

	try
	{		
		lojista.listaDadosLojaTerminal(iIdTerminal);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode)
		{		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao excluir Terminal");
			
		}else
		{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		

	xml_g->createTag("ManterTerminalVO");

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");

		xml_g->addItem("idTerminal", lojista.getIdTerminal());
		lojista.getNrTerminal(cGet);

		xml_g->addItem("nrTerminal", cGet);
		lojista.getNrIpTerminal(cGet);
			
		xml_g->addItem("nrIpTerminal", cGet);
		lojista.getCdLojaOperadoraCartao(cGet);

		xml_g->addItem("cdLojaOperadoraCartao", cGet);

		xml_g->addItem("idUfOperadora", lojista.getIdUFOperadora());
		xml_g->addItem("cdSitefSenha", lojista.getCdSitefSenha());
		xml_g->addItem("idCor", lojista.getIdCor());
		xml_g->addItem("inLiberadoRecarga", lojista.getInLiberadoRecarga());
		xml_g->addItem("inLiberadoPagamento", lojista.getInLiberadoPagamento());
		xml_g->addItem("idPessoaDePara", lojista.getIdPessoaDePara());
		
		lojista.getNmPessoa(cGet);
		xml_g->addItem("nmPessoa", cGet);

		xml_g->addItem("idPessoaEndereco", 0);
		xml_g->addItem("nmMunicipio", lojista.getNmMunicipio());
		xml_g->addItem("nmLocalidade", lojista.getNmLogradouro());
		xml_g->addItem("nmBairro", lojista.getNmBairro());
		xml_g->addItem("nmTipoLogradouro", lojista.getNmTipoLogradouro());
		xml_g->addItem("nmTituloLogradouro", lojista.getNmTituloLogradouro());
		xml_g->addItem("nmLogradouro", lojista.getNmLogradouro());
		xml_g->addItem("nrEndereco", lojista.getNrEndereco());

		tuxfw_getlogger()->debug("dadojaterm : lojista.getDsEnderecoComplemento() = %s\r\n", lojista.getDsEnderecoComplemento());
		xml_g->addItem("dsEnderecoComplemento", lojista.getDsEnderecoComplemento());
		xml_g->addItem("nrCep", lojista.getNrCep());
		
		xml_g->createTag("UFVO");
		xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo");
			xml_g->addItem("idUF", lojista.getIdUF());
			xml_g->addItem("sgUF", lojista.getSgUF(cGet));
			xml_g->addItem("nmUF", lojista.getNmUF());
		xml_g->closeTag();
	
	xml_g->closeTag();


}


static void consultarPesquisaLoja(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	list< CLojista > lstPesquisaLoja;
	
	char		*cNmPessoa = NULL;
	char        cGet[256] = "";
	char       cNmLoja[256] = "";
	

	
	cNmPessoa = helper.walkTree(dnode,"nmPessoa", 0);
	ASSERT_STR(cNmPessoa, "nmPessoa");


	strcpy (cNmLoja, "%");

	strcat (cNmLoja, cNmPessoa);

	strcat (cNmLoja, "%");

	
	tuxfw_getlogger()->debug("cNmLoja = %s\r\n", cNmLoja);

	try
	{		
		lojista.PesquisaNomeLoja(cNmLoja, xml_g);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode)
		{		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao excluir Terminal");
			
		}else
		{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		
}


static void resetSenhaTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	int			iIdTerminal = 0;
	char		*pcTagXmlIn = NULL;

	
	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");


	try
	{		
		lojista.resetSenhaTerminal(iIdTerminal);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode)
		{		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao resetar senha terminal");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		

	
	xml_g->closeTag();


}




static void consultarOperadora(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever	helper;
	CLojista			lojista; 
	int					iIdTerminal = 0;
	char				*pcTagXmlIn = NULL;
	list< CLojista >	lstOperadora;
	int					iIdUF = 0;
	
	
	pcTagXmlIn = helper.walkTree(dnode,"idUF", 0);

	if (pcTagXmlIn != NULL)
		iIdUF = atoi(pcTagXmlIn);

	
	try
	{		
		lojista.consultarOperadora( xml_g, iIdUF );

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode)
		{		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao consultar operadora");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		


}



static void consultarEndereco(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CLojista		  lojista; 
	int			iIdTerminal = 0;
	char		*pcTagXmlIn = NULL;
	int          iIdPessoaDePara = 0;

	list< CLojista > lstOperadora;

	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");
	
	try
	{		
		lojista.consultarEnderecoLoja(xml_g, iIdPessoaDePara);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode)
		{		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao consultar operadora");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
		}
	}		


}


void implDADLOJATERM::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;


	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	char *user = getUser();
	
	// descobrindo qual operação executar
	
	if(!strcmp(pcTagXmlIn,"consultarListaUF"))
		consultarUF(dnode,xml_g,statusCode);

	else if(!strcmp(pcTagXmlIn,"consultarMunicipios"))
		consultarMunicipios(dnode,xml_g,statusCode);

	else if(!strcmp(pcTagXmlIn,"consultarLojas"))
		consultarLojas(dnode,xml_g,statusCode);

	else if(!strcmp(pcTagXmlIn,"consultarTerminais"))
		consultarTerminais(dnode,xml_g,statusCode);
	
	else if(!strcmp(pcTagXmlIn,"consultarListaTerminais"))
		consultarListaTerminais(dnode,xml_g,statusCode);
	
	else if (!strcmp(pcTagXmlIn, "alterarTerminal"))
		alterarTerminal(dnode,xml_g,statusCode, user);

	else if (!strcmp(pcTagXmlIn, "incluirTerminal"))
		incluirTerminal(dnode,xml_g,statusCode, user);

	else if (!strcmp(pcTagXmlIn, "excluirTerminal"))
		excluirTerminal(dnode,xml_g,statusCode, user);

	else if (!strcmp(pcTagXmlIn, "listaDadosLojaTerminal"))
		listaDadosLojaTerminal(dnode,xml_g,statusCode);

	else if (!strcmp(pcTagXmlIn, "resetSenha"))
		resetSenhaTerminal(dnode,xml_g,statusCode);

	else if (!strcmp(pcTagXmlIn, "consultarPesquisaLoja"))
		consultarPesquisaLoja(dnode,xml_g,statusCode);

	else if (!strcmp(pcTagXmlIn, "consultarOperadora"))
		consultarOperadora(dnode, xml_g,statusCode);

	else if (!strcmp(pcTagXmlIn, "consultarEndereco"))
		consultarEndereco(dnode, xml_g,statusCode);

	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

}

