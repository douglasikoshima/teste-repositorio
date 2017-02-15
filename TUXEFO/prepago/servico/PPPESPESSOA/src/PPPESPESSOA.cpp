///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPPESPESSOA
 * @author  Renato Striitzel Russo
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:16 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>
//-- ARQUIVOS HERDADOS DE SINCRONISMO (RENATO RUSSO/CADU) ---------------------
#include <Global.h>
#include <PrePagoException.h>
#include <PessoaEndereco.h>
#include <PessoaComunicacao.h>
#include <Documento.h>
//#include <CCnae.h>
#include <Pessoa.h>
#include <PessoaFisica.h>
#include <PessoaJuridica.h>
#include <PessoaLinha.h>
#include <LinhaConta.h>

//-- ARQUIVOS CRIADOS PELO EDER -----------------------------------------------
#include <CEstadoCivil.h>
#include <CSexo.h>
#include <CTipoCarteira.h>
#include <CTipoDocumento.h>
#include <CTipoEndereco.h>
#include <CTipoComunicacao.h>
#include <CUF.h>
#include <CPais.h>
#include <CValorPossivel.h>
#include <CFOP.h>

DECLARE_TUXEDO_SERVICE(PPPESPESSOA);

void implPPPESPESSOA::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPPESPESSOA");

//-- Informacoes para log ------------------------------------------------------------------------------
	int x;
	struct
	{
		char cTagName[2][LEN_CAMPO_TEXTO_PADRAO_FO+LEN_EOS];
		char cIdPessoa[2][LEN_IDPESSOA+LEN_EOS];
		int  iQuantidade;
	}stClienteUsuario;

//-- Instanciandos as classes necessarias ---------------------------------------------------------------------	
	//Classe de manipulacao e montagem de XML de retorno
	CEstadoCivil oEstadoCivil;
	CSexo oSexo;
	CTipoCarteira oTipoCarteira;
	CTipoDocumento oTipoDocumento;
	CTipoEndereco oTipoEndereco;
	CTipoComunicacao oTipoComunicacao;
	CUF oUF;
	CPais oPais;
	CValorPossivel oValorPossivel;
    CCFOP oCFOP;

	//Classes herdadas de Sincronismo
	CPessoaEndereco oPessoaEndereco;
	CPessoaEndereco oPessoaEnderecoConta;
	CPessoaComunicacao oPessoaComunicacao;
	CDocumento oDocumento;
	//CCnae oCnae;
	CPessoa oPessoa;
	CPessoaFisica oPessoaFisica;
	CPessoaJuridica oPessoaJuridica;
	CPessoaLinha oPessoaLinha;
	CLinhaConta oLinhaConta;

	//Estrutura com campos basicos do PrePagoPessoaVO
	TPrePagoPessoaVO tPrePagoPessoaVO;

	memset( &stClienteUsuario, 0, sizeof( stClienteUsuario ) );

//-- Variaveis de uso geral ---------------------------------------------------------------------	
	bool blListas = false;//Controla a exibicao de listas
	bool blIdPessoa = false;//Verifica se o idPessoa esta presente
	bool blPessoaJuridica = false;
	char cEnderecoConta[255+1];
	char cEndereco[255+1];
	int  nAchouEnderecoIgual = 0;

	//Recupera algumas tag necessarias no XML
	GETXML( tPrePagoPessoaVO, idPessoa );
	GETXML( tPrePagoPessoaVO, idLinha );
	GETXML( tPrePagoPessoaVO, inTipoPessoa );
	GETXML( tPrePagoPessoaVO, inAtual );
	GETXML( tPrePagoPessoaVO, inClientePosPago );
	GETXML( tPrePagoPessoaVO, inClienteUsuario );
	GETXML( tPrePagoPessoaVO, inListas );

	//Verifica se havera listas ou nao
	if( strlennull( tPrePagoPessoaVO.inListas ) > 0 )
	{
		if( strcmp( tPrePagoPessoaVO.inListas, "1" ) == 0 )
			blListas = true;
	}
	
	//Salva o status do idpessoa
	blIdPessoa = ( strlennull( tPrePagoPessoaVO.idPessoa ) > 0 );

	//Se nao eh para listar e nao tem idpessoa, entao da erro
	if( !blListas && !blIdPessoa  )
	{
	    setStatusCode( "12I0001", "idPessoa esta nulo e nao ha requisicao de listas para combos" );
		return;
	}
	
	//Soh verifica se havera pesquisa de cliente ou usuario se tiver idPessoa
	strcpy( tPrePagoPessoaVO.inClienteUsuario, "0" );
	if( blIdPessoa )
	{
		//Tem idPessoa e nao tem linha, entao eh pesquisa de usuario
		if( strlennull( tPrePagoPessoaVO.idLinha ) <= 0 )
		{
			strcpy( stClienteUsuario.cTagName[0], "Usuario" );
			strcpy( stClienteUsuario.cIdPessoa[0], tPrePagoPessoaVO.idPessoa );
			stClienteUsuario.iQuantidade = 1;
		}
		else
		{
			//Se tem idPessoa e idLinha, entao pesquisa usuario e cliente para 
			//verificar se sao iguais
			//Acerta o idLinha, para realizar a pesquisa
			oPessoaLinha.setIdLinhaTelefonica( tPrePagoPessoaVO.idLinha );
			/********************************************************************************
			* OBSERVACAO: Este metodo retorna o IDPESSOA no lugar do campo IDUSUARIOALTERACAO
			 ********************************************************************************/
			if( oPessoaLinha.buscaIdClienteUsuario() )
			{
				if( oPessoaLinha.getQuantidade() > 1 )
				{
					//Compara os dois ID
					if( strcmp( oPessoaLinha.getRegistro(0)->szIdUsuarioAlteracao, 
						        oPessoaLinha.getRegistro(1)->szIdUsuarioAlteracao ) == 0 )
					{
						//Se pessoa e usuario sao iguais, pesquisa somente cliente
						strcpy( stClienteUsuario.cTagName[0], "Cliente" );
						strcpy( stClienteUsuario.cIdPessoa[0], tPrePagoPessoaVO.idPessoa );
						stClienteUsuario.iQuantidade = 1;
						strcpy( tPrePagoPessoaVO.inClienteUsuario, "1" );
					}
					else
					{
						//se diferentes, pesquisa os dois
						strcpy( stClienteUsuario.cTagName[0], "Cliente" );
						strcpy( stClienteUsuario.cTagName[1], "Usuario" );
						strcpy( stClienteUsuario.cIdPessoa[0], oPessoaLinha.getRegistro(0)->szIdUsuarioAlteracao );
						strcpy( stClienteUsuario.cIdPessoa[1], oPessoaLinha.getRegistro(1)->szIdUsuarioAlteracao );
						stClienteUsuario.iQuantidade = 2;
					}
				}//if( oPessoaLinha.getQuantidade() > 1 )
				else
				{
					//Tem idPessoa, tem linha, e soh retornou um registro, soh pode ser cliente
					strcpy( stClienteUsuario.cTagName[0], "Cliente" );
					strcpy( stClienteUsuario.cIdPessoa[0], tPrePagoPessoaVO.idPessoa );
					stClienteUsuario.iQuantidade = 1;
				}
			}
			else
			{
				//Se nao achar a linha, entao pesquisa como sendo apenas um cliente, sem linha
				strcpy( stClienteUsuario.cTagName[0], "Cliente" );
				strcpy( stClienteUsuario.cIdPessoa[0], tPrePagoPessoaVO.idPessoa );
				stClienteUsuario.iQuantidade = 1;
			}//if( oPessoaLinha.buscaIdClienteUsuario() )
		}//if( strlennull( tPrePagoPessoaVO.idLinha ) <= 0 )
	}//if( blIdPessoa )

//-- Abre a tag principal ManterPrePagoVO ---------------------------------------------------------------------	
	xml_g->createTag("ManterPrePagoVO");
	xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo" );
	xml_g->addComment( "Container para recuprar combos e dados de clientes para pre-pago" );

	xml_g->addItem( "inClienteUsuario", tPrePagoPessoaVO.inClienteUsuario );
	xml_g->addItem( "inListas", tPrePagoPessoaVO.inListas );

//-- Inicio das listas ----------------------------------------------------------------
	//-- Verifica se existe a necessidade recuperar as listas para os combos
	if( blListas )
	{
		xml_g->createTag("Listas");
		//-- ListaSexo ---------------------------------------------------------------------	
        ULOG("Sexo");
		oSexo.ListAll();
		oSexo.getXmlBasico( "ListaSexo", xml_g );

		//-- ListaEstadoCivil ---------------------------------------------------------------------	
        ULOG("EstadoCivil");
		oEstadoCivil.ListAll();
		oEstadoCivil.getXmlBasico( "ListaEstadoCivil", xml_g );
		
		//-- ListaEscolaridade ---------------------------------------------------------------------	
        ULOG("ValorPossivel Escolaridade");
		oValorPossivel.ListEscolaridade();
		oValorPossivel.getXmlCombo( "ListaEscolaridade", "idEscolaridade", "dsEscolaridade", xml_g );

		//-- ListaOcupacao ---------------------------------------------------------------------	
        ULOG("ValorPossivel Ocupacao");
		oValorPossivel.ListOcupacao();
		oValorPossivel.getXmlCombo( "ListaOcupacao", "idOcupacao", "dsOcupacao", xml_g );

		//-- ListaClassificacaoEmpresa ---------------------------------------------------------------------	
        ULOG("TipoCarteira");
		oTipoCarteira.ListAll();
		oTipoCarteira.getXmlLista( "ListaClassificacaoEmpresa", xml_g );

		//-- ListaTributaria ---------------------------------------------------------------------	
		//Ainda esta sem definicao
        ULOG("CFOP");
		oCFOP.ListAll();
		oCFOP.getXmlLista( "ListaTributaria", xml_g );

		//-- ListaTipoDocumentoPF COM FILTROS -----------------------------------------------------	
        ULOG("TipoDocumento PFFiltrado");
		oTipoDocumento.ListPFFiltrado();
		oTipoDocumento.getXmlCombo2( "ListaTipoDocumentoPF", "idTipoDocumentoPF", "sgTipoDocumentoPF", "dsTipoDocumentoPF", xml_g );

		//-- ListaTipoDocumentoPJ COM FILTROS --------------------------------------------------------	
        ULOG("TipoDocumento PJFiltrado");
		oTipoDocumento.ListPJFiltrado();
		oTipoDocumento.getXmlCombo2( "ListaTipoDocumentoPJ", "idTipoDocumentoPJ", "sgTipoDocumentoPJ", "dsTipoDocumentoPJ", xml_g );

		//-- ListaDocAlternativa -----------------------------------------------------	
        ULOG("TipoDocumento ListAll");
		oTipoDocumento.ListAll();
		oTipoDocumento.getXmlCombo2( "ListaDocAlternativa", "idTipoDocAlternativo", "sgTipoDocAlternativo", "dsTipoDocAlternativo", xml_g );

		//-- ListaUF ---------------------------------------------------------------------	
        ULOG("UF");
		oUF.ListAll();
		oUF.getXmlLista( "ListaUF", xml_g );

		//-- ListaTipoTelefone ---------------------------------------------------------------------	
        ULOG("TipoComunicacao TipoTelefones");
		oTipoComunicacao.ListTipoTelefones();
		oTipoComunicacao.getXmlCombo( "ListaTipoTelefone", "idTipoTelefone", "sgTipoTelefone", "dsTipoTelefone", xml_g );

		//-- ListaInscricao ---------------------------------------------------------------------	
        ULOG("TipoComunicacao PJInscricoes");
		oTipoDocumento.ListPJInscricoes();
		oTipoDocumento.getXmlCombo2( "ListaInscricao", "idInscricao", "sgInscricao", "dsInscricao", xml_g );

		//-- ListaTipoEndereco ---------------------------------------------------------------------	
        ULOG("TipoEndereco");
		oTipoEndereco.ListAll();
		oTipoEndereco.getXmlLista( "ListaTipoEndereco", xml_g );

		//-- ListaContatoAlternativa ------------------------------------------------------	
        ULOG("TipoComunicacao");
		oTipoComunicacao.ListAll();
		oTipoComunicacao.getXmlCombo( "ListaContatoAlternativa", "idTipoContatoAlternativo", "sgTipoContatoAlternativo", "dsTipoContatoAlternativo", xml_g );

		//-- ListaCNAE ------------------------------------------------------	
		//oCnae.ListAll();
		//oCnae.getXmlCombo( "ListaCNAE", "idCNAE", "cdCNAE", "dsCNAE", "cdCFOP", xml_g );
		xml_g->createTag("ListaCNAE");
		xml_g->closeTag();//ListaCNAE

		xml_g->closeTag();//Listas
	}//if( blListas )
    //-- Fim das listas ----------------------------------------------------------------

	if( blIdPessoa )
	{
		for( int iCont = 0; iCont < stClienteUsuario.iQuantidade; iCont++ )
		{
			nAchouEnderecoIgual = 0;
			xml_g->createTag(stClienteUsuario.cTagName[iCont]);
			xml_g->createTag("PrePagoPessoaVO");

        	//-- Inicio de endereco ---------------------------------------------------------------
            ULOG("PessoaEnderecoConta");
			oPessoaEnderecoConta.Zera();
			oPessoaEnderecoConta.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
			oPessoaEndereco.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
			//Se for cliente, verifiva se existe o endereco da conta
			if( strcmp( stClienteUsuario.cTagName[iCont], "Usuario" ) != 0 )
			{
				oLinhaConta.setIdLinhaTelefonica( tPrePagoPessoaVO.idLinha );
                ULOG("oLinhaConta.buscaLinhaContaCliente");
				if( oLinhaConta.buscaLinhaContaCliente() )
				{
					//Se retornou a conta do tipo relacionamento cliente, parte para a busca de endereco
					oPessoaEnderecoConta.buscaPessoaEnderecoContaPorIdPessoa( oLinhaConta.getIdConta() );
				}
				else
					oPessoaEnderecoConta.Zera();
			}

            ULOG("oPessoaEndereco.buscaPessoaEnderecoPorIdPessoa");
			oPessoaEndereco.buscaPessoaEnderecoPorIdPessoa();

            ULOG("oPessoaEndereco.getQuantidade");
			if( oPessoaEndereco.getQuantidade() > 0 )
			{
				for( x = 0; x < oPessoaEndereco.getQuantidade(); x++ )
				{
					xml_g->createTag("Endereco");
					xml_g->createTag("EnderecoPrePagoVO");

					xml_g->addItem( "idEndereco", oPessoaEndereco.getIdPessoaEndereco(x) );
                    ULOG("oPessoaEnderecoConta.getQuantidade");
					if( oPessoaEnderecoConta.getQuantidade() > 0 )
					{
						strcpy( cEnderecoConta, oPessoaEnderecoConta.getIdPessoaEndereco(0) );
						strcpy( cEndereco     , oPessoaEndereco.getIdPessoaEndereco(x) );
						
						if( strcmp( cEndereco, cEnderecoConta ) == 0 )
						{
							xml_g->addItem( "inAtualEndereco", "1" );
							nAchouEnderecoIgual++;
						}
						else
							xml_g->addItem( "inAtualEndereco", "" );
					}
					else
					{
						if( strcmp( oPessoaEndereco.getInEnderecoPreferencial(x), "1" ) == 0 )
							xml_g->addItem( "inAtualEndereco", "1" );
						else
							xml_g->addItem( "inAtualEndereco", "" );
					}
					xml_g->addItem( "idTipoEnderecoSelecionado", oPessoaEndereco.getIdTipoEndereco(x) );
					xml_g->addItem( "idTituloLogradouro", "" );
					xml_g->addItem( "dsTituloLogradouro", oPessoaEndereco.getNmTituloLogradouro(x) );
					xml_g->addItem( "idTipoLogradouro", "" );
					xml_g->addItem( "dsTipoLogradouro", oPessoaEndereco.getNmTipoLogradouro(x) );
					xml_g->addItem( "dsLogradouro", oPessoaEndereco.getNmLogradouro(x) );
					xml_g->addItem( "nrEndereco", oPessoaEndereco.getNrEndereco(x) );
					xml_g->addItem( "dsComplementoEndereco", oPessoaEndereco.getDsEnderecoComplemento(x) );
					xml_g->addItem( "dsBairro", oPessoaEndereco.getNmBairro(x) );
					xml_g->addItem( "dsMunicipio", oPessoaEndereco.getNmMunicipio(x) );
					xml_g->addItem( "nrCEP", oPessoaEndereco.getNrCep(x) );

					xml_g->createTag("UF");
					oUF.ListId( oPessoaEndereco.getIdUF(x) );
					xml_g->addItem( "idUFEndereco", oUF.Registro()->cidUF );
					xml_g->addItem( "sgUFEndereco", oUF.Registro()->csgUF );
					xml_g->addItem( "dsUFEndereco", oUF.Registro()->cnmUF );
					xml_g->closeTag();//UF

					xml_g->createTag("Pais");
					oPais.ListId( oPessoaEndereco.getIdPais(x) );
					xml_g->addItem( "idPaisEndereco", oPais.Registro()->cidPais );
					xml_g->addItem( "sgPaisEndereco", oPais.Registro()->csgPais );
					xml_g->addItem( "dsPaisEndereco", oPais.Registro()->cdsPais );
					xml_g->closeTag();//Pais

					xml_g->closeTag();//Endereco

					xml_g->closeTag();//EnderecoPrePagoVO

					if( ( nAchouEnderecoIgual == 0 ) && ( (x+1) == oPessoaEndereco.getQuantidade() ) )
					{
						if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
						{
							xml_g->createTag("Endereco");

							xml_g->createTag("EnderecoPrePagoVO");

							xml_g->addItem( "idEndereco", oPessoaEnderecoConta.getIdPessoaEndereco(x) );
							xml_g->addItem( "inAtualEndereco", "1" );
							xml_g->addItem( "idTipoEnderecoSelecionado", oPessoaEnderecoConta.getIdTipoEndereco(x) );
							xml_g->addItem( "idTituloLogradouro", "" );
							xml_g->addItem( "dsTituloLogradouro", oPessoaEnderecoConta.getNmTituloLogradouro(x) );
							xml_g->addItem( "idTipoLogradouro", "" );
							xml_g->addItem( "dsTipoLogradouro", oPessoaEnderecoConta.getNmTipoLogradouro(x) );
							xml_g->addItem( "dsLogradouro", oPessoaEnderecoConta.getNmLogradouro(x) );
							xml_g->addItem( "nrEndereco", oPessoaEnderecoConta.getNrEndereco(x) );
							xml_g->addItem( "dsComplementoEndereco", oPessoaEnderecoConta.getDsEnderecoComplemento(x) );
							xml_g->addItem( "dsBairro", oPessoaEnderecoConta.getNmBairro(x) );
							xml_g->addItem( "dsMunicipio", oPessoaEnderecoConta.getNmMunicipio(x) );
							xml_g->addItem( "nrCEP", oPessoaEnderecoConta.getNrCep(x) );

							xml_g->createTag("UF");
							oUF.ListId( oPessoaEnderecoConta.getIdUF(x) );
							xml_g->addItem( "idUFEndereco", oUF.Registro()->cidUF );
							xml_g->addItem( "sgUFEndereco", oUF.Registro()->csgUF );
							xml_g->addItem( "dsUFEndereco", oUF.Registro()->cnmUF );
							xml_g->closeTag();//UF

							xml_g->createTag("Pais");
							oPais.ListId( oPessoaEnderecoConta.getIdPais(x) );
							xml_g->addItem( "idPaisEndereco", oPais.Registro()->cidPais );
							xml_g->addItem( "sgPaisEndereco", oPais.Registro()->csgPais );
							xml_g->addItem( "dsPaisEndereco", oPais.Registro()->cdsPais );
							xml_g->closeTag();//Pais

							xml_g->closeTag();//Endereco

							xml_g->closeTag();//EnderecoPrePagoVO
						}//if( oPessoaEnderecoConta.getQuantidade() > 0 ) 
					}//if( ( nAchouEnderecoIgual == 0 ) && ( x == oPessoaEndereco.getQuantidade() ) )

				}//for( int x = 0; x < oPessoaEndereco.Quantidade(); x++ )
			}//if( oPessoaEndereco.getQuantidade() > 0 )
	//-- Fim de endereco ---------------------------------------------------------------

	//-- Inicio de contato ---------------------------------------------------------------
			xml_g->createTag("Contato");
			oPessoaComunicacao.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
            ULOG("oPessoaComunicacao.buscaPessoaComunicacaoPorIdPessoa");
			if( oPessoaComunicacao.buscaPessoaComunicacaoPorIdPessoa() )
			{
				int nCont = 0;
				int nEmailCom = 0;
				int nEmailPart = 0;

				for( x = 0; x < oPessoaComunicacao.getQuantidade(); x++ )
				{
					xml_g->createTag("ListCont");

					xml_g->addItem( "idContato", oPessoaComunicacao.getIdPessoaComunicacao(x) );
					xml_g->addItem( "idTipoContatoSelecionado", oPessoaComunicacao.getIdTipoComunicacao(x) );
					xml_g->addItem( "dsContato", oPessoaComunicacao.getDsContato(x) );
					xml_g->addItem( "nmContato", oPessoaComunicacao.getNmContato(x) );
					oTipoComunicacao.ListId( oPessoaComunicacao.getIdTipoComunicacao(x) );
					xml_g->addItem( "sgContato", oTipoComunicacao.Registro()->csgTipoComunicacao );

					xml_g->closeTag();//ListaContato

				}//for( x = 0; x < oPessoaEndereco.Quantidade(); x++ )

			}//if( oPessoaComunicacao.buscaPessoaComunicacaoPorIdPessoa() )
			xml_g->closeTag();//Contato
	//-- Fim de contato ---------------------------------------------------------------

	//-- Inicio de Documento ---------------------------------------------------------------
			oDocumento.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
            ULOG("oDocumento.buscaDocumentoPorIdPessoa");
			if( oDocumento.buscaDocumentoPorIdPessoa() )
			{
				for( x = 0; x < oDocumento.getQuantidade(); x++ )
				{
					oTipoDocumento.ListId( oDocumento.getRegistro(x)->szIdTipoDocumento );

					xml_g->createTag("Doc");
					xml_g->addItem( "idDoc", oDocumento.getRegistro(x)->szIdDocumento );
					xml_g->addItem( "sgDoc", oTipoDocumento.Registro()->csgTipoDocumento );
					xml_g->addItem( "nrDoc", oDocumento.getRegistro(x)->szNrDocumento );
					xml_g->addItem( "idTipoDoc", oTipoDocumento.Registro()->csgClassificacao );
					xml_g->addItem( "dsTipoDoc", oTipoDocumento.Registro()->cdsTipoDocumento );
					xml_g->addItem( "dtExpedicaoDoc", oDocumento.getRegistro(x)->szDtEmissao );
					xml_g->addItem( "dsOrgaoEmissorDoc", oDocumento.getRegistro(x)->szSgOrgaoExpedidor );
					xml_g->addItem( "idUFDoc", oDocumento.getRegistro(x)->szIdUf );
					xml_g->closeTag();//Doc
				}//for( x = 0; x < oDocumento.getQuantidade(); x++ )
			}//if( oDocumento.buscaDocumentoPorIdPessoa() )
	//-- FIM de Documento ---------------------------------------------------------------

			oPessoa.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
            ULOG("oPessoa.buscaPessoaPorIdPessoa");
			if( oPessoa.buscaPessoaPorIdPessoa() )
			{
	//-- Inicio de PF ---------------------------------------------------------------
				if( strcmp( oPessoa.getIdTipoPessoa(), "1" ) == 0 )
					blPessoaJuridica = false;
				else
					blPessoaJuridica = true;

				oPessoaFisica.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
                ULOG("oPessoaFisica.buscaPessoaFisica");
				if( oPessoaFisica.buscaPessoaFisica() )
				{
					xml_g->createTag("PF");
					xml_g->addItem( "nmPessoa", oPessoa.tPessoa.szNmPessoa );
					xml_g->addItem( "idSexoSelecionado", oPessoaFisica.tPessoaFisica.szIdSexo );
					xml_g->addItem( "idEstadoCivilSelecionado", oPessoaFisica.tPessoaFisica.szIdEstadoCivil );
					xml_g->addItem( "dtNascimento", oPessoaFisica.tPessoaFisica.szDtNascimento );
					xml_g->addItem( "dsProfissao", oPessoa.tPessoa. szDsCargoContato );
					//Busca a escolaridade da pessoa
                    ULOG("oValorPossivel.ListEscolaridadePorIdPessoa");
					if( oValorPossivel.ListEscolaridadePorIdPessoa( stClienteUsuario.cIdPessoa[iCont] ) > 0 )
					{
						//Mesmo que a pesquisa retorne mais de um, monta o xml apenas o primeiro
						xml_g->addItem( "idEscolaridadeSelecionado", oValorPossivel.Registro(0)->cidValorPossivel );
						xml_g->addItem( "dsEscolaridadeSelecionado", oValorPossivel.Registro(0)->cdsValorPossivel );
					}
					//Busca a ocupacao da pessoa
                    ULOG("oValorPossivel.ListOcupacaoPorIdPessoa");
					if( oValorPossivel.ListOcupacaoPorIdPessoa( stClienteUsuario.cIdPessoa[iCont] ) > 0 )
					{
						//Mesmo que a pesquisa retorne mais de um, monta o xml apenas o primeiro
						xml_g->addItem( "idOcupacaoSelecionado", oValorPossivel.Registro()->cidValorPossivel );
					}
					xml_g->closeTag();//PF
				}
	//-- Iniciao de PF ---------------------------------------------------------------


	//-- Iniciao de PJ ---------------------------------------------------------------
				oPessoaJuridica.setIdPessoa( stClienteUsuario.cIdPessoa[iCont] );
                ULOG("oPessoaJuridica.buscaPessoaJuridica");
				if( oPessoaJuridica.buscaPessoaJuridica() )
				{
					xml_g->createTag("PJ");
					xml_g->addItem( "idGrupo", "" );
					xml_g->addItem( "dsRazaoSocial", oPessoa.tPessoa.szNmPessoa );
					xml_g->addItem( "nmFantasia", oPessoaJuridica.tPessoaJuridica.szNmFantasia );
					xml_g->addItem( "idTributariaSelecionado", oPessoaJuridica.tPessoaJuridica.szIdCFOP );
					xml_g->addItem( "idClassificacaoEmpresaSelecionado", oPessoa.tPessoa.szIdTipoCarteira );
					xml_g->addItem( "dtFundacao", oPessoaJuridica.tPessoaJuridica.szDtFundacao );
					xml_g->closeTag();//PJ
				}
	//-- Fim de PJ ---------------------------------------------------------------
			}//if( oPessoa.buscaPessoaPorIdPessoa() )

			xml_g->addItem( "idPessoa", stClienteUsuario.cIdPessoa[iCont] );
			if( blPessoaJuridica )
				xml_g->addItem( "inTipoPessoa", "PJ" );
			else
				xml_g->addItem( "inTipoPessoa", "PF" );
			xml_g->addItem( "inAtual", tPrePagoPessoaVO.inAtual );
			xml_g->addItem( "inClientePosPago", tPrePagoPessoaVO.inClientePosPago );
			
			xml_g->closeTag();//PrePagoPessoaVO

			xml_g->closeTag();//Cliente
		}//for( x = 0; x < stClienteUsuario.iQuantidade; x++ )
	}//if( blIdPessoa )
//-- Fecha a tag principal ManterPrePagoVO ---------------------------------------------------------------------	

	
//-- Inicio de Linha ---------------------------------------------------------------------	
	if( strlennull( tPrePagoPessoaVO.idLinha ) > 0 )
	{
		xml_g->createTag("Linha");
		xml_g->addItem( "idLinha", tPrePagoPessoaVO.idLinha );
		//xml_g->addItem( "nrCodArea", "" );
		//xml_g->addItem( "nrLinha", "" );
		//xml_g->addItem( "idEstadoLinha", "" );
		//xml_g->addItem( "idTipoLinha", "" );
		//xml_g->addItem( "idLinhaBase", "" );
		xml_g->closeTag();//Linha
	}
//-- Fim de Linha ---------------------------------------------------------------------	

	xml_g->closeTag();//ManterPrePagoVO

    ULOG("FINAL DO PROCESSAMENTO");
    ULOG_END("PPPESPESSOA");

    setStatusCode( "12I0000", "Sucesso" );
}
