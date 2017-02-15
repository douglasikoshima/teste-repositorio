#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Email/Email.hpp>

DECLARE_TUXEDO_SERVICE(PPESSOA);

void implPPESSOA::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CLinha	oLinha;
	CEmail  oEmail;

	char *  pcTagXmlIn = NULL;
	char *	pcArea = NULL;
	char *	pcNroLinha = NULL;
	char *	pcCdSenha = NULL;
	char *  pInSenha = NULL;
 	int		iCdAreaRegistro = -1;
 	int		iNrLinha = -1;
	int		iIdCanal = -1;

	int iRet;

	// Navega o XML e recupera as informacoes obrigatorias
	
	pcArea     = helper.walkTree( dnode, "cdAreaRegistro", 0);
	pcNroLinha = helper.walkTree( dnode, "nrLinha", 0);
	// pcCdSenha  = helper.walkTree( dnode, "cdsenha", 0);
	// pInSenha  = helper.walkTree( dnode, "inSenha", 0);   /* Indica se vai usar Senha onde 1 - nao vai usar senha e zero vai usar senha */
	
	// strcpy( pInSenha, "1" );
	
	tuxfw_getlogger()->debug("PPESSOA");

    if ( atoi(pInSenha) != 1 )
	{
		if ( NULL == pcArea || NULL == pcNroLinha ) {
			//ERROR(NRO_TP_CORR_ID_NE);
			//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
		}
		if ( '\0' == *pcArea || '\0' == *pcNroLinha ) {
			//ERROR(NRO_TP_CORR_ID_VV);
			//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
		}
	}
	else
	{
		tuxfw_getlogger()->debug( "Nao valida senha" );

		if ( NULL == pcArea || NULL == pcNroLinha ) {
			//ERROR(NRO_TP_CORR_ID_NE);
			//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
		}
		if ( '\0' == *pcArea || '\0' == *pcNroLinha ) {
			//ERROR(NRO_TP_CORR_ID_VV);
			//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
		}
	}

	iCdAreaRegistro = atoi( pcArea );
	iNrLinha = atoi( pcNroLinha );
	if ( 0 >= iCdAreaRegistro || 0 >= iNrLinha) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	//pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	//ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal");

		tuxfw_getlogger()->debug( "Pssou aqui" );

	//	oPessoa->obterIdPessoa(iCdAreaRegistro, iNrLinha);
	oLinha.setCdAreaRegistro( iCdAreaRegistro );
	oLinha.setNrLinha( iNrLinha );
	
	setStatusCode("20W0001", "CONSULTA NAO EFETUADA");	

	try
	{
		/*
		if ( atoi(pInSenha) != 1 )
		{
		   tuxfw_getlogger()->debug( ">>> Consulta por senha" );
		   iRet = oLinha.obterDadosLinhaPessoaSessao( pcCdSenha, iIdCanal );
		}
		else
		{
		*/
		   tuxfw_getlogger()->debug( ">>> Consulta sem senha" );
		   iRet = oLinha.obterDadosLinhaPessoaSessaoSemSenha( iIdCanal );
		//}

		if(iRet)
			return;
	}
	catch ( ... ){		
		return;
	}

	// Monta o XML de saída

    xml_g->createTag("LSTSESSAOVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");

	char buffer[128];
    sprintf( buffer, "%ld", oLinha.getPessoa()->getIdPessoa() );
    xml_g->addItem( "idPessoa", buffer );
	xml_g->addItem( "dataAtivacao", oLinha.getPessoa()->get_dtHabilitacao() );
	xml_g->addItem( "dataAlteracaoStatus", oLinha.getPessoa()->get_dtAlteracaoStatus() );


	oLinha.getPessoa()->consultarDocumento();
	
	xml_g->addItem( "dsTipoDocumento", oLinha.getPessoa()->getDsTipoDocumento() );
	xml_g->addItem( "nrDocumento", oLinha.getPessoa()->getNrDocumento() );
	

	xml_g->addItem( "idTipoRelacionamento", oLinha.getPessoa()->getIdTipoRelacionamento() );
	xml_g->addItem( "sgTipoRelacionamento", oLinha.getPessoa()->getSgTipoRelacionamento() );
	xml_g->addItem( "nmCliente", CUtil::trim(oLinha.getPessoa()->getNomeCliente()));
	xml_g->addItem( "nmUsuario", CUtil::trim(oLinha.getPessoa()->getNomeUsuario()));
    
    xml_g->addItem( "idTipoLinhaSistemaOrigem", oLinha.getIdTipoLinhaSistemaOrigem() );
	xml_g->addItem( "idTipoLinha", oLinha.getIdTipoLinha() );
	xml_g->addItem( "dsTipoLinha", oLinha.getDsTipoLinha() );
	xml_g->addItem( "inPlanoControle", oLinha.getPlanoControle());
	
	xml_g->addItem( "planoPrincipal", oLinha.getPlanoPrincipal());
	


	xml_g->addItem( "LembSenha", CUtil::trim(oLinha.getPessoa()->getSenhaData() ));

	//Linha
	xml_g->addItem( "idSistemaOrigem", oLinha.getIdSistemaOrigem() );
	
	xml_g->addItem( "idUFOperadora", oLinha.getIdUFOperadora() );

	xml_g->addItem( "idSegmentacao", oLinha.getIdSegmentacao() );
	//Linha
	xml_g->addItem( "idProcedencia", oLinha.getIdProcedencia());
	xml_g->addItem( "idTipoCarteira", oLinha.getPessoa()->getIdTipoCarteira());
	xml_g->addItem( "idConta", oLinha.getPessoa()->getIdConta() );
	//Pessoa
	xml_g->addItem( "idLinhaTelefonica", oLinha.getIdLinhaTelefonica() );
	xml_g->addItem( "idTipoPessoa", oLinha.getPessoa()->getIdTipoPessoa() );
	xml_g->addItem( "idPessoaCliente", oLinha.getPessoa()->getIdPessoaCliente() );

	xml_g->addItem( "sgEstadoLinha", oLinha.getSgEstadoLinha() );
	xml_g->addItem( "dsEstadoLinha", oLinha.getDsEstadoLinha() );


	//Data da Versão
	xml_g->addItem( "dtVersao", "13/12/2004 11:00");

	if(oLinha.getDigitoVerificador() >= 0)
		xml_g->addItem("digitoVerificador", oLinha.getDigitoVerificador());
	else
		xml_g->addItem("digitoVerificador", "");

	xml_g->addItem( "idTempoSessao", oLinha.getPessoa()->getRel()->getIdTempoSessao());

	/* inEmail:
		0 - Não possui nenhum e-mail
		1 - Possui 1 e-mail
		2 - Possui 2 ou mais e-mails
	*/

	try{
	
		oLinha.getPessoa()->consultarEmails();
	}
	catch( ... )
	{ 	
		
		oLinha.getPessoa()->getListaEmails().clear();

	}


	if (oLinha.getPessoa()->getListaEmails().size() < 1){
		xml_g->addItem( "inEmail", 0); //Pessoa não possui e-mails.
		xml_g->addItem( "nmEmail", "" );
	}
	else if (oLinha.getPessoa()->getListaEmails().size() == 1){
		xml_g->addItem( "inEmail", 1); //Pessoa possui 1 e-mail.
		oEmail = oLinha.getPessoa()->getListaEmails().front();
		xml_g->addItem( "nmEmail", CUtil::trim(oEmail.getDsEmail()) );
	}
	else if (oLinha.getPessoa()->getListaEmails().size() > 1){
		xml_g->addItem( "inEmail", 2); //Pessoa possui 2 ou mais e-mails.
		xml_g->addItem( "nmEmail", "" ); //o e-mail será retornado apenas no caso da pessoa ter 1 e-mail apenas.
	}
	xml_g->addItem( "sgUF", CUtil::trim(oLinha.getSgUF()));


	
	if (atoi(oLinha.getPessoa()->getIdLinhaSistemaOrigem()) > 0){

		if((oLinha.getIdTipoLinha()  == 1) || (oLinha.getIdTipoLinha()  == 5)){

			xml_g->addItem( "idLinhaSistemaOrigemPos", CUtil::trim(oLinha.getPessoa()->getIdLinhaSistemaOrigem()));			
			xml_g->addItem( "idLinhaSistemaOrigem",CUtil::trim(oLinha.getPessoa()->getIdContaSistemaOrigem())); //até o marco atualizar o tav
			xml_g->addItem( "sgSistemaOrigemPos", oLinha.getSgSistOrig());

		}else {

			xml_g->addItem( "idLinhaSistemaOrigemPre", CUtil::trim(oLinha.getPessoa()->getIdLinhaSistemaOrigem()));
			xml_g->addItem( "idLinhaSistemaOrigem",CUtil::trim(oLinha.getPessoa()->getIdContaSistemaOrigem())); //até o marco atualizar o tav
			xml_g->addItem( "sgSistemaOrigemPre", oLinha.getSgSistOrig());

		}
	}
	if (atoi(oLinha.getPessoa()->getIdContaSistemaOrigem()) > 0){
	
		if((oLinha.getIdTipoLinha()  == 1) || (oLinha.getIdTipoLinha()  == 5) || (oLinha.getIdTipoLinha()  == 4) || (oLinha.getIdTipoLinha()  == 7))
			xml_g->addItem( "idContaSistemaOrigem", CUtil::trim(oLinha.getPessoa()->getIdContaSistemaOrigem()));
	}else{
		xml_g->addItem( "idContaSistemaOrigem", "");
	}

/*
	if (oLinha.getIdTipoLinha()  == 4){ /// híbrido

		char cIdLinhaSistemaOrigem[255 +1]="";
		char cSgSistemaOrigem[255 +1]="";

		oLinha.consultarDadosHibrido(cIdLinhaSistemaOrigem,cSgSistemaOrigem);
		xml_g->addItem( "idLinhaSistemaOrigemPre",CUtil::trim(cIdLinhaSistemaOrigem));
		xml_g->addItem( "sgSistemaOrigemPre", CUtil::trim(cSgSistemaOrigem));

	}
*/

	if (oLinha.getPessoa()->getIdTipoRelacionamento() == 1)
	{

		oLinha.getPessoa()->setIdTipoRelacionamento(2);
		oLinha.getPessoa()->obterIdPessoa(iCdAreaRegistro, iNrLinha);
		oLinha.getPessoa()->consultarDocumento();
	}

		
		
	xml_g->addItem( "dsTipoDocumentoCliente", oLinha.getPessoa()->getDsTipoDocumento() );
	xml_g->addItem( "nrDocumentoCliente", oLinha.getPessoa()->getNrDocumento() );

	// DEMANDA SAPO
	xml_g->addItem( "primeiroNome", oLinha.getPessoa()->getNmNome() );
	xml_g->addItem( "nome", oLinha.getPessoa()->getNmPessoa() );
	xml_g->addItem( "sgSexo", oLinha.getPessoa()->getSgSexo() );
	xml_g->addItem( "dtNascimento", oLinha.getPessoa()->getDtNascimento() );
	
	//VIVO NEXT
	if( oLinha.getIdSubTipoLinha() > 0 ) 
	{
		xml_g->addItem( "idSubTipoLinha", oLinha.getIdSubTipoLinha() );
		xml_g->addItem( "cdSubTipoLinha", oLinha.getCdSubTipoLinha() );
		xml_g->addItem( "dsSubTipoLinha", oLinha.getDsSubTipoLinha() );
	}
	else
	{
		xml_g->addItem( "idSubTipoLinha", "" );
		xml_g->addItem( "cdSubTipoLinha", "" );
		xml_g->addItem( "dsSubTipoLinha", "" );
	}	

	xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header

	setStatusCode("20I0000", "CONSULTA EFETUADA COM SUCESSO");
}

