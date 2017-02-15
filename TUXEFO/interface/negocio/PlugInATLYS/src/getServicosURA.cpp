#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include "../../VolNegocio/include/VolNegocio.h"

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::getServicosURA()
{

	CLinha			oLinha;
	CServico		oServico;
	list<CServico>	lstServicos;
	list<CServico>::iterator itrServico;
	list<CServico>	lstServicosVigentes;
	list<CServico>::iterator itrServicoVig;
	CParametro		oParametro;
	list<CParametro> lstParametro;
	list<CParametro>::iterator itrParametro;
	

	char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
 	int   iNrLinha = -1;
	char cValidadeInicial[11];
	char cValidade[11];

	char cDsCaixaPostal[256];
	char cDsIdentificador[256];
	char cDsBloqueioDDI[256];
	char cDsBloqueioDDD[256];
	char cDsChamadaEspera[256];
	char cDsTransfChamadas[256];
	char cDsServicoWAP[256];
	char cDsServicoConferencia[256];
	char cDsAntiIdentificador[256];
	char cDsTorpedoEmpresas[256];
	char cDsBloqueioLigacaoCobrar[256];
	char cCdServico[20];
	int  iServicoValido;
	int  iAddServicoBloqueioDDI;
	int  iAddServicoChamadaEspera;
	int  iAddServicoWAP;
	int	 iDesconsideraServico;
	bool blServTorpedoAtivado = false;
	char chrContato[256]="";
	char cSubject[256] = "";
	char cNickName[256] = "";
	char cSbscrpId[256];


	strcpy(cSbscrpId,getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 


        // Ler Tag <ProxyLinha> que já foi validade pelo Proxy.
        pcTagXmlIn = tuxhp->walkTree(dnode,"ProxyLinha", 0);

        if ((iNrLinha = atoi(pcTagXmlIn + 2)) <= 0) {
                throw new TuxBasicSvcException(ECD_ATL_TVI_NRLINHA, EMSG_ATL_TVI_NRLINHA);
        }

        *(pcTagXmlIn + 2) = '\0';

        if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
                throw new TuxBasicSvcException(ECD_ATL_TVI_CDAREAREGISTRO, EMSG_ATL_TVI_CDAREAREGISTRO);
        }

		pcTagXmlIn = tuxhp->walkTree(dnode,"cdContato", 0);

		if (pcTagXmlIn != NULL)
			strcpy (chrContato, pcTagXmlIn);


	// ACESSO AO DB do FO para buscar conteudo de parametros
    tuxfw_getlogger()->information("Busca conteudo de parametros"); 

	oParametro.setCdAreaRegistro(iCdAreaRegistro);

	oParametro.setChave(COD_FO_CAIXA_POSTAL);
	oParametro.consultarNomeServico();
	strcpy(cDsCaixaPostal, oParametro.getConsulta());

	oParametro.setChave(COD_FO_IDENTIFICADOR);
	oParametro.consultarNomeServico();
	strcpy(cDsIdentificador, oParametro.getConsulta());

	oParametro.setChave(COD_FO_BLOUQEIO_DDI);
	oParametro.consultarNomeServico();
	strcpy(cDsBloqueioDDI, oParametro.getConsulta());

	oParametro.setChave(COD_FO_BLOQUEIO_DDD);
	oParametro.consultarNomeServico();
	strcpy(cDsBloqueioDDD, oParametro.getConsulta());

	oParametro.setChave(COD_FO_CHAMADA_ESPERA);
	oParametro.consultarNomeServico();
	strcpy(cDsChamadaEspera, oParametro.getConsulta());

	oParametro.setChave(COD_FO_DESVIO_CHAMADAS);
	oParametro.consultarNomeServico();
	strcpy(cDsTransfChamadas, oParametro.getConsulta());

	oParametro.setChave(COD_FO_SERVICO_WAP);
	oParametro.consultarNomeServico();
	strcpy(cDsServicoWAP, oParametro.getConsulta());

	oParametro.setChave(COD_FO_CONFERENCIA);
	oParametro.consultarNomeServico();
	strcpy(cDsServicoConferencia, oParametro.getConsulta());
	
	oParametro.setChave(COD_FO_ANTI_IDENTIF_CHAMADA);
	oParametro.consultarNomeServico();
	strcpy(cDsAntiIdentificador, oParametro.getConsulta());

	oParametro.setChave(COD_FO_BLOQ_LIGACOES_COBRAR);
	oParametro.consultarNomeServico();
	strcpy(cDsBloqueioLigacaoCobrar, oParametro.getConsulta());

	// chama a API inputGetServiceCharge 
	char*valorTarifa = NULL;
	char*recurrChrgPeriod = NULL;
	char*chrgTypeCd = NULL;
	char*servicoTag = tuxhp->walkTree(dnode,"servico", 0);
	// comentando essa linha porque vamos fazer a consulta de tarifa para todos os wrappers de atlys
	if(servicoTag != NULL/*!strcmp(this->getServiceName(),"AcsAtlysURA")*/)
	{

		CParametro param;
		if(!strcmp(servicoTag,"UNBLOCKDDD"))
			param.setChave(COD_FO_BLOQUEIO_DDD);
		else
		if(!strcmp(servicoTag,"UNBLOCKDDI"))
			param.setChave(COD_FO_BLOUQEIO_DDI);
		else
		if(!strcmp(servicoTag,"VOICEMAIL"))
			param.setChave(COD_FO_CAIXA_POSTAL);
		else
		if(!strcmp(servicoTag,"CALLWAIT"))
			param.setChave(COD_FO_CHAMADA_ESPERA);
		else
		if(!strcmp(servicoTag,"CALLFWD"))
			param.setChave(COD_FO_DESVIO_CHAMADAS);
		else
		if(!strcmp(servicoTag,"CALLID"))
			param.setChave(COD_FO_IDENTIFICADOR);
		else
		if(!strcmp(servicoTag,"WARN"))
			param.setChave(servicoTag);
		else
		if(!strcmp(servicoTag,"WAP"))
			param.setChave(COD_FO_SERVICO_WAP);
		else
		if(!strcmp(servicoTag,"CONFERENCIA"))
			param.setChave(COD_FO_CONFERENCIA);
		else
		if(!strcmp(servicoTag,"ANTIBINA"))
			param.setChave(COD_FO_ANTI_IDENTIF_CHAMADA);

		else
		if(!strcmp(servicoTag,"BLOQCOBRAR"))
			param.setChave(COD_FO_BLOQ_LIGACOES_COBRAR);

		param.setCdAreaRegistro(iCdAreaRegistro);
		param.consultarNomeServico();		
		XMLGen xmlEnvioGetServiceCharge;

		xmlEnvioGetServiceCharge.createTag(XML_ATL_INPUT_SERVICE_CHARGE);
		xmlEnvioGetServiceCharge.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
		xmlEnvioGetServiceCharge.addProp("serviceName", param.getConsulta());
		xmlEnvioGetServiceCharge.closeTag();

		// Guarda o xml de retorno
		DOMNode* outputDOMNodeGetServiceCharge;
		tuxfw_getlogger()->debug("PlugInATLYS::getServico():callRemoteAPI()");
		outputDOMNodeGetServiceCharge =  callRemoteAPI(this->getServiceName(), &xmlEnvioGetServiceCharge, XML_ATL_INPUT_SERVICE_CHARGE );

		this->trataErro(outputDOMNodeGetServiceCharge);
		
		if(outputDOMNodeGetServiceCharge == NULL){
			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
			throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
		}

		// Validar de a Operação  Remota retornou erro.
		if(strcmp(getLastStatusCode(),"24I0000") != 0){

			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
			throw new TuxBasicSvcException("24E0000", getLastStatusText());
		}

		valorTarifa = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","chrgAmt",0);
		recurrChrgPeriod = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","recurrChrgPeriod",0);
		chrgTypeCd = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","chrgTypeCd",0);
	}

	// ACESSO AO DB do FO.
     tuxfw_getlogger()->information("CdAreaRegistro: %i iNrLinha %i\n", iCdAreaRegistro, iNrLinha); 

	// Set os atributos pertinentes a Linha
	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

    tuxfw_getlogger()->information("Obj.CdAreaRegistro: %i Obj.iNrLinha %i\n", oLinha.getCdAreaRegistro(),oLinha.getNrLinha()); 

    // Consultar a lista de todos servicos da linha
	oLinha.consultarServicos(lstServicos);

	// Trazendo todos os servicos habilitados da linha 
	oLinha.consultarVigServicosLinha(lstServicosVigentes);

	//Busca os serviços que possui conceito invertido para o cdAreaRegistro
	oParametro.setCdAreaRegistro(iCdAreaRegistro);
	oParametro.consultarServicosInvertidos(lstParametro);

/*
	iAddServicoBloqueioDDI=1;
	iAddServicoChamadaEspera=1;
	iAddServicoWAP=1;
*/
	//Verifica se os servicos com conceito inverso estao na lista, caso estejam significa que estao desativados, do contrario precisam ser adicionados pois estao ativos.
	for (itrServico = lstServicos.begin();  itrServico != lstServicos.end(); itrServico++){ 

        ULOG( "### Realizando iteracao de servicos inversos" );
        
		//Verfica se o serviço corrente da lista acima possui conceito inverso.
		for (itrParametro = lstParametro.begin();  itrParametro != lstParametro.end(); itrParametro++){ 
			//
            ULOG( "### Comparando lista de servicos inversos" );
            ULOG( ">>> itrParametro [%s] - itrServico [%s]", itrParametro->getConsulta(), itrServico->getSgServico() );
			if (Util::cmp(itrParametro->getConsulta(), itrServico->getSgServico())){
				itrServico->setSgServico("NAO_EXIBIR");
				itrParametro->setInAdicionarServico(0);
                ULOG( "??? Ajustando para nao exibir servico" );
			}
		}
	}

	// Adiciona na lista os servicos com conceito invertido, caso nao estjam na mesma.
	for (itrParametro = lstParametro.begin();  itrParametro != lstParametro.end(); itrParametro++){ 
		//
        ULOG( "### Adicao de servicos inversos" );
		if (itrParametro->getInAdicionarServico() == 1){

			oServico.setSgServico(itrParametro->getConsulta());
			oServico.setNmServico(itrParametro->getConsulta());
            
            ULOG( "@@@ Inserindo servico [%s]", itrParametro->getConsulta() );
            
			oServico.setInHabilitado(1);
			oServico.consultarVigenciaInvertida(iCdAreaRegistro, iNrLinha, itrParametro->getConsulta());
			lstServicos.push_back( oServico );
			lstServicosVigentes.push_back( oServico );
		}
	}

    tuxfw_getlogger()->information("Retornou do BD - OK"); 

	// Monta o XML de saída
	xml_g->createTag("ServicoVO");
	if((pcTagXmlIn = tuxhp->walkTree(dnode,"xmlns", 0)) != NULL)
	{
		xml_g->addProp( "xmlns", pcTagXmlIn );
	};	
	xml_g->addItem("valorTarifa",valorTarifa);

	while( 0 < lstServicos.size() ) 
    {
        ULOG( ">>>>> Existe servico a ser inserido..." );
        
		oServico = lstServicos.front();

		strcpy(cCdServico, "");

		//Setar as validades com valor VAZIO
		strcpy(cValidadeInicial,"");
		strcpy(cValidade,"");
		// Pesquisar na Lista de Servicos Vigentes
		// Verifica a vigencia antes de alterar a descricao do servico
		ULOG( "----> Verificando vigencia do servico a ser inserido..." );
        for (itrServicoVig = lstServicosVigentes.begin();  itrServicoVig != lstServicosVigentes.end(); itrServicoVig++){ 
			// Se o Servico da Linha == Servico Vigente da linha.
            ULOG( "=====> Vai comparar servico vigente..." );
			if (oServico == *itrServicoVig){
				if (Util::cmp(oServico.getNmServico(), itrServicoVig->getNmServico()))
				{
                    ULOG( "=====> Encontrado servico vigente [%s]", itrServicoVig->getNmServico() );
					strcpy(cValidadeInicial,itrServicoVig->getDtVigenciaInicio());
					strcpy(cValidade,itrServicoVig->getDtVigenciaFinal());
				}
			}
		}


		iServicoValido=1;
		// Altera a descricao do servico
		if (!strcmp(oServico.getSgServico(), cDsCaixaPostal))
		{
			strcpy(cCdServico, COD_CAIXA_POSTAL);
			oServico.setServicoAtlys(cDsCaixaPostal);
			oServico.setNmServico(DES_CAIXA_POSTAL);
            ULOG( "Possue servico [%s]",DES_CAIXA_POSTAL );
		}
		else if (!strcmp(oServico.getSgServico(), cDsIdentificador))
		{
			strcpy(cCdServico, COD_IDENTIFICADOR);
			oServico.setServicoAtlys(cDsIdentificador);
			oServico.setNmServico(DES_IDENTIFICADOR);
            ULOG( "Possue servico [%s]",DES_IDENTIFICADOR );
		}
		else if (!strcmp(oServico.getSgServico(), cDsBloqueioDDI))
		{
			strcpy(cCdServico, COD_BLOQUEIO_DDI);
			oServico.setServicoAtlys(cDsBloqueioDDI);
			oServico.setNmServico(DES_BLOQUEIO_DDI);
            ULOG( "Possue servico [%s]",DES_BLOQUEIO_DDI );
		}
		else if (!strcmp(oServico.getSgServico(), cDsBloqueioDDD))
		{
			strcpy(cCdServico, COD_BLOQUEIO_DDD);
			oServico.setServicoAtlys(cDsBloqueioDDD);
			oServico.setNmServico(DES_BLOQUEIO_DDD);
            ULOG( "Possue servico [%s]",DES_BLOQUEIO_DDI );		
        }
		else if (!strcmp(oServico.getSgServico(), cDsChamadaEspera))
		{
			strcpy(cCdServico, COD_CHAMADA_ESPERA);
			oServico.setServicoAtlys(cDsChamadaEspera);
			oServico.setNmServico(DES_CHAMADA_ESPERA);
            ULOG( "Possue servico [%s]",DES_BLOQUEIO_DDI );		
		}
		else if (!strcmp(oServico.getSgServico(), cDsTransfChamadas))
		{
			strcpy(cCdServico, COD_TRANSF_CHAMADAS);
			oServico.setServicoAtlys(cDsTransfChamadas);
			oServico.setNmServico(DES_TRANSF_CHAMADAS);
            ULOG( "Possue servico [%s]",DES_TRANSF_CHAMADAS );		
		}
		else if(!strcmp(oServico.getSgServico(),cDsServicoConferencia))
		{
			strcpy(cCdServico, COD_CONFERENCIA);
			oServico.setServicoAtlys(cDsServicoConferencia);
			oServico.setNmServico(DES_CONFERENCIA);
            ULOG( "Possue servico [%s]",DES_CONFERENCIA );		
		}
		else if(!strcmp(oServico.getSgServico(),cDsAntiIdentificador))
		{
			strcpy(cCdServico, COD_ANTIBINA);
			oServico.setServicoAtlys(cDsAntiIdentificador);
			oServico.setNmServico(DES_ANTIBINA);
            ULOG( "Possue servico [%s]",DES_ANTIBINA );		
		}
		else if(oParametro.consultarNomeServicoTorpedoEmpresas(oServico.getSgServico(), COD_TORPEDOEMPRESAS))
		{
			strcpy(cCdServico, COD_TORPEDOEMPRESAS);
			oServico.setServicoAtlys(COD_TORPEDOEMPRESAS);
			oServico.setNmServico(DES_TORPEDOEMPRESAS);	
            ULOG( "Possue servico [%s]",DES_TORPEDOEMPRESAS );		
				
			getAtributosTorpedo(iNrLinha, oServico.getSgServico(), cSubject, cNickName);
			blServTorpedoAtivado = true;
		}
		else if(!strcmp(oServico.getSgServico(), cDsBloqueioLigacaoCobrar))
		{
			strcpy(cCdServico, COD_BLOQCOBRAR);
			oServico.setServicoAtlys(cDsBloqueioLigacaoCobrar);
			oServico.setNmServico(DES_BLOQCOBRAR);
            ULOG( "Possue servico [%s]",DES_BLOQCOBRAR );		
		}
		else
			iServicoValido=0;

		//Desconsidera os servicos com conceito invertido.
		iDesconsideraServico=0;
		for (itrParametro = lstParametro.begin();  itrParametro != lstParametro.end(); itrParametro++){ 
			//
			if (Util::cmp(oServico.getSgServico(), itrParametro->getConsulta()) && oServico.getInHabilitado() == 0)
            {
                iDesconsideraServico = 1;
                ULOG( "!!! Desconsiderando servico [%s]", itrParametro->getConsulta() );
            }
		}


		if (iDesconsideraServico == 0)
		{
			//Considera apenas os serviços acima.
			if (iServicoValido == 1)
			{
				xml_g->createTag("ServicosItem");

				xml_g->addItem("nome", oServico.getNmServico());
				if (!strcmp(cCdServico, ""))
					xml_g->addItem("codigo", oServico.getIdServico());
				else
					xml_g->addItem("codigo", cCdServico);				
				xml_g->addItem("descricao",oServico.getNmServico());				

				if (oServico.getInHabilitado() == 1){
					xml_g->addItem("status", "ATIVADO" );

					if (!strcmp (oServico.getNmServico(),DES_TORPEDOEMPRESAS)){

						xml_g->addItem("empresaNome", cNickName);
						xml_g->addItem("empresaMensagem", cSubject);
					}
	
				}
				else{
					xml_g->addItem("status", "DESATIVADO" );
				}

				xml_g->addItem("validadeInicial",cValidadeInicial);
				xml_g->addItem("validade",cValidade );

				xml_g->closeTag();
			}

		}

		lstServicos.pop_front();
	}

	xml_g->closeTag();


	// Registro de contato

	
	if ((!strcmp(chrContato, "ContatoTorpedoEmpresas")) && (blServTorpedoAtivado == false))
		return; //nao registra neste caso, pois o usuario tentou acessar o Torpedo Empresas e o servico está desativado.


	this->registrarContato();

}
