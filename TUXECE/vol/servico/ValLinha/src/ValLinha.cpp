#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Parametro/Parametro.hpp>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


static char *STR_11W0004						= "11W0004";
#define POS_PAGO								1
#define	POS_PAGO_GSM							5
#define PRE_PAGO								2
#define PRE_PAGO_GSM							6
#define PF										1
#define PJ										2
#define NC										0
#define TIPO_CONTROLE_CDMA						4
#define TIPO_CONTROLE_GSM						7
#define TAM_SENHA_CRIPTOGRAFADA					32
	


DECLARE_TUXEDO_SERVICE(VALLINHA);



void consultaValLinha(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	int    iCdAreaRegistro = -1;
 	int    iNrLinha = -1;	
	char*         pcTagXmlIn = NULL;
	CTuxHelperClever helper;
	CLinha           oLinha;
	CPessoa			 oPessoa;
	bool			 blAlterarMenu =  false;


	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20W0000", "TAG_cdAreaRegistro_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20W0000", "TAG_cdAreaRegistro_VALOR_VAZIO");
	}
	if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("20W0000", "TAG_cdAreaRegistro_VALOR_INVALIDO");
	}
	
	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20W0000", "TAG_nrLinha_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20W0000", "TAG_nrLinha_VALOR_VAZIO");
	}
	if ((iNrLinha = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("20W0000", "TAG_nrLinha_VALOR_INVALIDO");
	}
    
	// Monta o objeto oLinha
	oLinha.setCdAreaRegistro(iCdAreaRegistro);	
	oLinha.setNrLinha(iNrLinha);

	try{
			oLinha.consultarListaRestritiva();
	}

	catch ( ... )	
	{

	}
	try {

		int intRC = oLinha.validarLinha();

		if (intRC ==  ERR_LINHA_EXPIRADA)
		{
			statusCode.setCod("11W0002");
			statusCode.setMsg("LINHA EXPIRADA");
			return;
		}

	}
	catch( ... ) {

		statusCode.setCod("11W0001");
		statusCode.setMsg("LINHA NAO ENCONTRADA");
	
		return;
	}
	
	try {
        oLinha.consultarTipoLinha();
	}
	catch( ... ) {

		statusCode.setCod("11W0011");
		statusCode.setMsg("TIPO LINHA NAO ENCONTRADO");
		return;
	}

	try{
		oLinha.consultarDadosLinhaSessao();

	}
	catch( ... ){ }


	
	try{
	
		oPessoa.consultarTipoPessoa(oLinha.getNrLinha(), oLinha.getCdAreaRegistro(), PESSOA_CLIENTE);

		oPessoa.consultarTipoPessoa(oLinha.getNrLinha(), oLinha.getCdAreaRegistro(), PESSOA_USUARIO);
	}

	catch ( ... )
	{
		statusCode.setCod("11W0001");
		statusCode.setMsg("TIPO PESSOA NAO ENCONTRADO");
		return;
	}


	try {
	
		oLinha.consultarIdGrupoOperadora(iCdAreaRegistro);
	}

	catch ( ... )
	{
		statusCode.setCod("11W0001");
		statusCode.setMsg("GRUPO OPERADORA NAO ENCONTRADO");
	
		return;
	}
	
	try {
	
		oLinha.consultarPlanoPrincipal();
	}

	catch ( ... )
	{
		// não tem especificação caso não tenha o plano, vamos retornar vazio
	}
	
	oLinha.consultarVivoZAP();
	if ( oLinha.getIdTipoLinha () == PRE_PAGO || oLinha.getIdTipoLinha () == PRE_PAGO_GSM ||
		oLinha.getIdTipoLinha() == TIPO_CONTROLE_CDMA || oLinha.getIdTipoLinha() == TIPO_CONTROLE_GSM)
		oLinha.consultarPlanoControle();
	
	xml_g->createTag("VALLINHAVO");
	xml_g->addProp("xmlns", "senhas.tav.vivo.com.br/vo");

		xml_g->addItem("idTipoLinha", oLinha.getIdTipoLinha());

		if (oLinha.getPlanoControle() == 1)
			xml_g->addItem("sgTipoLinha", "VIVO Controle");
		else
			xml_g->addItem("sgTipoLinha", oLinha.getDsTipoLinha());
		
		xml_g->addItem("inPlanoControle", oLinha.getPlanoControle());
		xml_g->addItem("sgEstadoLinha", oLinha.getSgEstadoLinha());
		xml_g->addItem("idGrupoOperadora", oLinha.getIdGrupoOperadora());
		xml_g->addItem("inClienteUsuarioIguais", oLinha.verificarClienteUsuarioIguais() == true ? 1 : 0);
		xml_g->addItem("sgSistemaOrigem", oLinha.getSgSistOrig());
		xml_g->addItem("sgEstadoLinhaOriginal", oLinha.getSgEstadoLinhaOriginal());
		xml_g->addItem("inBloqueado", oLinha.getNrRestrito());
		xml_g->addItem("idLinhaTelefonica", oLinha.getIdLinhaTelefonica());

		xml_g->addItem("planoPrincipal", oLinha.getPlanoPrincipal());

		xml_g->addItem("senhaProvisoria", strlen(oLinha.getCdSenhaPreAtiva()) >= TAM_SENHA_CRIPTOGRAFADA ? 1 : 0);
			
		xml_g->addItem("usuarioCadastrado", oPessoa.getIdPessoa() == 0 ? 0 : 1);

		xml_g->addItem("vivoZAP", oLinha.getVivoZAP() == true ? 1 : 0);
		
		if ( oLinha.getIdTipoLinha() == PRE_PAGO || oLinha.getIdTipoLinha () == PRE_PAGO_GSM ||
		oLinha.getIdTipoLinha() == TIPO_CONTROLE_CDMA || oLinha.getIdTipoLinha() == TIPO_CONTROLE_GSM)
		{

			xml_g->addItem("inClientePJ", "0");
			xml_g->addItem("hasUsuarioPF","1"); 

		}
		else{
			xml_g->addItem("inClientePJ",  (oPessoa.getIdTipoCliente()) -1);

			if (oPessoa.getIdTipoUsuario() == NC)
				xml_g->addItem("hasUsuarioPF", -1); 
			else
				xml_g->addItem("hasUsuarioPF",  oPessoa.getIdTipoUsuario()==PF?1:0);

		}

		if ( ( !strcmp(oLinha.getSgEstadoLinha(), "D") && !( oLinha.getIdTipoLinha() == PRE_PAGO_GSM || oLinha.getIdTipoLinha() == PRE_PAGO )) 
			|| ( oLinha.getInLinhaCancelada() == 1 && ( oLinha.getIdTipoLinha() == PRE_PAGO_GSM || oLinha.getIdTipoLinha() == PRE_PAGO )) )
			xml_g->addItem("statusCancel",  2);
		else
			xml_g->addItem("statusCancel",  0);
			

		xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);
	
	statusCode.setCod("11I0000");
	statusCode.setMsg("LINHA ENCONTRADA");


	if (oLinha.getIdTipoLinha() == POS_PAGO || oLinha.getIdTipoLinha() == POS_PAGO_GSM)
	{

		if (oPessoa.getIdTipoCliente() == PJ)
		{

			if (oPessoa.getIdTipoUsuario() == PJ)
			{

				statusCode.setCod("11W0006");
				statusCode.setMsg("Identificamos que o número digitado é um terminal Vivo Empresas.");	

			}	
		}
	}
	
}



void implVALLINHA::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	CStatusCode	  statusCode;


	consultaValLinha(dnode, xml_g, statusCode);

	// Navega o XML e recupera as informacoes obrigatorias
/*	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);	

	
	if ((pcTagXmlIn == NULL) || (*pcTagXmlIn == '\0'))
		consultaValLinha(dnode, xml_g, statusCode);
	
	else if(!strcmp(pcTagXmlIn, "validaAcessoTAV"))
		ValidaAcessoTAV(dnode, xml_g, statusCode);*/


	setStatusCode(statusCode.getCod(), statusCode.getMsg());

}

