#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Linha/Linha.hpp>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


#define ID_ENDERECO_PREMIO 4

DECLARE_TUXEDO_SERVICE(LSTENDERECO);

void implLSTENDERECO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	
	CTuxHelperClever helper;

	CPessoa          oPessoa;
	CEndereco        oEndereco;
	CConta           oConta;
	CStr             oStr;
	CLinha			 oLinha;

	list <CEndereco> lstOEnd;
	list <CEndereco> lstOEndCobranca;
	list <CEndereco> lstOComplemento;

	char*  pcTagXmlIn = NULL;
	long   iIdPessoa = -1;
	int    iIdTipoRelacionamento = -1;
	int	   iCdAreaRegistro = -1;
	int	   iNrLinha = -1;
	int	   iIdConta = -1;
	int    iIdEndereco = -1;
	int    iIdSistemaOrigem = -1;
	char   c_Temp[256];

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");

	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento");

	
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");


	oLinha.setNrLinha(iNrLinha);
	oLinha.setCdAreaRegistro (iCdAreaRegistro);

	oLinha.consultarTipoLinha();


	try
	{
		CEndereco::consultarTipoEnd(lstOEnd);
	}
	catch(...)
	{		
		//seta mensagem de retorno - header
		setStatusCode("11I0001", "Erro na consulta de tipos de endereço.");
		return;
	}

	try
	{
		CEndereco::consultarTipoEndCobranca(lstOEndCobranca);
	}
	catch(...)
	{		
		//seta mensagem de retorno - header
		setStatusCode("11I0002", "Erro na consulta de tipos de endereço cobrança.");
		return;
	}
	
	try
	{
		CEndereco::consultarTipoComplemento(lstOComplemento);
	}
	catch(...)
	{		
		//seta mensagem de retorno - header
		setStatusCode("11I0003", "Erro na consulta de tipos de complemento.");
		return;
	}

	try
	{
		oPessoa.setIdPessoa(iIdPessoa);
		oPessoa.setNrLinha(iNrLinha);
		oPessoa.setCdAreaRegistro(iCdAreaRegistro);
		oPessoa.consultarEnderecos();
	}
	catch(...)
	{
		//seta mensagem de retorno - header
		setStatusCode("11W0099", "Consulta não efetuada.");
		return;
	}

	xml_g->createTag("LSTENDERECOVO");
	xml_g->addProp("xmlns", "dados.vol.vivo.com.br/vo");

		xml_g->createTag("tiposEnderecoVO");

		while(0 < lstOEnd.size()) 
		{
			oEndereco = lstOEnd.front();

			if (oEndereco.getIdTipoEndereco() == ID_ENDERECO_PREMIO )
			{
				lstOEnd.pop_front();
				continue;
			}

			xml_g->createTag("tipoEndereco");

				xml_g->addItem("idTipoEndereco", oEndereco.getIdTipoEndereco());
				xml_g->addItem("sgTipoEndereco", oEndereco.getSgTipoEndereco());
				xml_g->addItem("dsTipoEndereco", oEndereco.getDsTipoEndereco());

				if(oEndereco.getIdTipoEndereco() == ID_TIPO_ENDERECO_COBRANCA)
					xml_g->addItem("inAdiciona", 0);
				else
					xml_g->addItem("inAdiciona", oPessoa.getEndereco()->getQuantTipo(oEndereco.getIdTipoEndereco()) >= 3 ? 0 : 1);

			xml_g->closeTag(); //tipoEndereco

			lstOEnd.pop_front();
		}

		xml_g->closeTag(); //tiposEnderecoVO

		xml_g->createTag("tiposEnderecoCobrancaVO");

		while(0 < lstOEndCobranca.size()) 
		{
			oEndereco = lstOEndCobranca.front();

			xml_g->createTag("tipoEnderecoCobranca");

				xml_g->addItem("idTipoEnderecoCobranca", oEndereco.getIdTipoEnderecoCobranca());
				xml_g->addItem("sgTipoEnderecoCobranca", oEndereco.getSgTipoEnderecoCobranca());
				xml_g->addItem("dsTipoEnderecoCobranca",oEndereco.getDsTipoEnderecoCobranca());

			xml_g->closeTag(); //tipoEnderecoCobranca

			lstOEndCobranca.pop_front();
		}

		xml_g->closeTag(); //tiposEnderecoCobrancaVO



		xml_g->createTag("tiposComplementoVO");

		while(0 < lstOComplemento.size()) 
		{
			oEndereco = lstOComplemento.front();

			xml_g->createTag("tipoComplemento");

				xml_g->addItem("idTipoComplemento", oEndereco.getIdTipoComplemento());
				xml_g->addItem("sgTipoComplemento", oEndereco.getSgTipoComplemento());
				xml_g->addItem("dsTipoComplemento", oEndereco.getDsTipoComplemento());

			xml_g->closeTag(); //tipoComplemento

			lstOComplemento.pop_front();
		}

		xml_g->closeTag(); //tiposComplementoVO


		xml_g->createTag("enderecosVO");
		
		int i_TotalEnderecos = oPessoa.getListaEnderecos().size();

		while(0 < oPessoa.getListaEnderecos().size())
		{
			oEndereco = oPessoa.getListaEnderecos().front();

			xml_g->createTag("endereco");

			xml_g->addItem("idEndereco", oEndereco.getIdPessoaEndereco());

			xml_g->addItem("idTipoEndereco", oEndereco.getIdTipoEndereco());
			xml_g->addItem("dsTipoEndereco", oEndereco.getDsTipoEndereco());
			xml_g->addItem("nmTipoLogradouro", oEndereco.getNmTipoLogradouro());
			xml_g->addItem("nmTituloLogradouro", oEndereco.getNmTituloLogradouro());
			xml_g->addItem("nmLogradouro", oEndereco.getNmLogradouro());
			xml_g->addItem("nrEndereco", oEndereco.getNrEndereco());
			xml_g->addItem("dsEnderecoComplemento", oEndereco.getDsEnderecoComplemento());
			xml_g->addItem("nmBairro", oEndereco.getNmBairro());
			xml_g->addItem("nrCep", oEndereco.getNrCep());
			xml_g->addItem("nmMunicipio", oEndereco.getNmMunicipio());
						
			if(oEndereco.getIdUf() > 0)
			{
				xml_g->addItem( "idUF", oEndereco.getIdUf());
				oEndereco.consultarPais(oEndereco.getIdPessoaEndereco(), xml_g);
			}
			else
				xml_g->addItem( "idUF", "");
			
			xml_g->addItem("idSistemaOrigem", oEndereco.getIdSistemaOrigem());

			if  (oEndereco.getListaContas().size())
			{
				oConta = oEndereco.getListaContas().front();
				iIdConta = oConta.getIdConta();			
			}
			else						
				iIdConta = 0;
			
			if ( oEndereco.getIdSistemaOrigem() == ATLYS || ( iIdConta > 0 && (oLinha.getIdTipoLinha() == ID_CONTROLE_GSM || oLinha.getIdTipoLinha() == ID_CONTROLE_CDMA )))
				xml_g->addItem("inModifica", 0);
			else
				xml_g->addItem("inModifica", 1);
			
			if(oEndereco.getListaContas().size() > 0 ||
			   i_TotalEnderecos == 1) 
				xml_g->addItem("inRemove", "0");
			else
				xml_g->addItem("inRemove", "1");

			xml_g->createTag("contasVO");

			while(0 < oEndereco.getListaContas().size())
			{
				oConta = oEndereco.getListaContas().front();

				if(oConta.getIdConta() > 0)
				{
					xml_g->createTag("conta");

						xml_g->addItem("idConta", oConta.getIdConta());
						xml_g->addItem("idTipoConta", oConta.getIdTipoConta());
						xml_g->addItem("cdConta", oConta.getCdConta(c_Temp));						
						xml_g->addItem("idTipoEnderecoCobranca", oConta.getIdTipoEnderecoCobranca());
						xml_g->addItem("idEnderecoAssociado", oEndereco.getIdPessoaEndereco());
						if (oEndereco.getIdSistemaOrigem() == ATLYS || ( oLinha.getIdTipoLinha() == ID_CONTROLE_GSM || oLinha.getIdTipoLinha() == ID_CONTROLE_CDMA ))			
							xml_g->addItem("inAssocia", 0);

						else
							xml_g->addItem("inAssocia", 1);


						
						xml_g->createTag("linhasVO");

						while( 0 < oConta.getListaLinhas().size() )
						{
							oStr = oConta.getListaLinhas().front();

							xml_g->addItem("linha", oStr.getValue(c_Temp));

							oConta.getListaLinhas().pop_front();

						}

						xml_g->closeTag(); // linhasVO

					xml_g->closeTag(); //conta
				}

				oEndereco.getListaContas().pop_front();
			}

			xml_g->closeTag(); //contasVO
			
			oPessoa.getListaEnderecos().pop_front();

			xml_g->closeTag(); //endereco

		}

		xml_g->closeTag(); //enderecosVO
	
	xml_g->closeTag(); //LSTENDERECOVO

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Consulta efetuada com sucesso.");

}
