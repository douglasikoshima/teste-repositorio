



#include "../../negocio/fidutil/include/retencao.hpp"
#include "../../negocio/fidutil/include/CallTux.h"

#define   EXECUTAR_BAIXA   1 
#define   EXECUTAR_ESTORNO 2 


struct Venda
{
    int idAparelho;
    char nrLinha[16];
    char cdSap[21];
    char vlrVenda[16];
    char vlAparelho[16];
};

struct stVendaPJ
{
    char szTipoDoc[10];
    char szPermaneciaSAP[21];
    char dtDE[16];
	char dtATE[16];
	int	 inComodato;
};

extern int EmiteTag( void );
extern void	BuscaSegmentacao( char * idSeg, char * sgSegm );
extern void GetIE( char * idPessoaPrm, char * sInscrEstadualPrm );
extern void GetIM( char * idPessoaPrm, char * sInscrMunicipalPrm );
extern void BuscaEmail( char * pidPessoaPrm, char * nmEmailPrm );
extern void SeparaEndereco( char * pEnderecoPrm, char * nmRuaPrm, char * NroPrm, char * nmComplementoPrm );
//extern int BaixaEstornoEstoque(int usuario,char *pidAparelho,char *pSGUF,char *pidPessoa,int inTipo,char *plinha);

extern int get_Cpf(char * pretencao,char *pnrCpf,char *pTipoDoc);

extern int get_parcelas(int quantidade,char *pcod);

extern int get_TipoPagto( char * pagto, char *pcod );

// extern int get_Vkgrp(char* pidret,char *pcod);

// extern int get_SapEmpresa(char* psgsigla,char *pcod); SM 279 

extern int get_SapEmpresaOrgVenda(char *pcod, char *nmTelefone );

extern int get_Retencao(char *pidRet);

extern int get_SapParametros(int iidGrupo,char * pidPessoa,char *psgUf, char *pidSeg,char *pVKGRP,char *pPOMETHOD, char* pAUGRU);

// extern int set_logInfo(char *pidret,char* perro, int iuser,char *pXml);
extern int set_logInfo(char *pidret,char* perro, int iuser, char *pXml, char* pOrdemVenda );

extern int formata_cep(char *pcepin,char* pcepout);

extern void IdentificaItemVenda( Venda *pVenda,char *pvlAparelho,char *ptipodoc );

extern int get_ParametrosPJ(char* psgOf,char* pidTpCart, char* pidContrato, stVendaPJ *pVendaPJ);//SM COMODATO E MULTA CONTRATUAL



DECLARE_TUXEDO_SERVICE(COMSAP);

void implCOMSAP::Execute(DOMNode*dnode,XMLGen*xml_g)
{

    ULOG_START("COMSAP()");
    
	char sgSegm[256];
    char nmRua[128];
    char nrEnder[8];
    char nmComplEnd[128];
    char nmEmailPrm[128];
    int iRetorno=0;
    char parm[512];
    char szRetorno[4000];
    char szXmlIn[2500];
    char *pXmlIn=NULL;
    int   iXmlInLen=0;
	float vlAparelhoControle=0.0;
	int  vlcontrole=-1;
	int  inPj=0;
  
    char szXml[4000];
    int iparcelas=0;
    int  iNrPedido=0;
    char szTipoDoc[10];
    CCallTuxSrv TuxServico;
    
	//variaveis para controle de erro
    //int  iErro=1;
    int  iErro=2;
    int  iCodErro=0;
    char szDsErro[255];
    char idSeg[255];
	
	char sgTipoPessoa[4];
	char sgOferta[4];
	char idCarteira[21+1];
	char idContrato[21+1];

    int inExecutar=0;
    int inRetornoOld=0;

    char szOrdemVenda[255]; //variavel para ordem de venda


    //DadosPessoa,DAparelho; 	
	//char szMaterial[255];
	struct stparametros{
		 // char szEMPRESA[255]; // SM 279 RETIRADO
		 char szORGVENDA[5];     // SM 279 INCLUIDO
		 char szNOME[255];
		 char szRUA_CLI[61];
         char szNUM_CLI[11];
         char szCOMPL_CLI[11];
		 char szBAIRRO_CLI[255];
		 char szCEP_CLI[10];
		 char szCIDADE_CLI[255];
		 char szRUA_ENT[61];
         char szNUM_ENT[11];
         char szCOMPL_ENT[11];
		 char szBAIRRO_ENT[255];
		 char szCIDADE_ENT[255];
		 char szCEP_ENT[10];
		 char szESTADO_ENT[255];
		 char szTELEFONE[255];
		 char szDOCUMENTO[255];
         char szE_MAIL01[36];
		 char szMaterial[255];
		 char szQUANTIDADE[255];
		 char szDATA_REMESSA[255];
		 char szVALOR[255];
		 char szMEIOPAGTO[255];
		 char szNUMERO_PEDIDO[255];
		 char szCOND_PGTO[255];
		 char szVKGRP[255];
		 char szPO_METHOD[255];
		 char szAUGRU[255];
		 char szOBSERVACAO[512];
		 char szIDAPARELHO[255];
		 char szIDPESSOA[255];
		 int  szIDGRUPO;

		 char szSGOFERTA[4];
		 char szSGTIPOPESSOA[4];
		 char szPermanencia[255];
		 char szIDTIPOCARTEIRA[21+1];
		
		 char szTipoDoc[255];
		 char szVALIDADE_DE[255];
		 char szVALIDADE_ATE[255];
	};

	//Parametros para chamar serviço tuxedo
	XercesDOMParser *pParser;
	MemBufInputSource *pMemBuf;
	XMLGen oEntrada;

	struct stparametros stOrdemVenda;
	struct	stVendaPJ ostVendaPj;

	const char *pMemBufId = "inputInfo";
 
	//header do xml para o SAP
	
    memset ( nmRua, 0x0, sizeof(nmRua));
    memset ( nrEnder, 0x0, sizeof(nrEnder));
    memset ( nmComplEnd, 0x0, sizeof(nmComplEnd));
    memset ( nmEmailPrm, 0x0, sizeof(nmEmailPrm) );
	memset( &stOrdemVenda, 0, sizeof( stparametros ) );	
	memset(	szOrdemVenda,0x0,sizeof(szOrdemVenda));
	memset( szDsErro, 0, sizeof( szDsErro ) );	
	memset( &ostVendaPj, 0, sizeof( ostVendaPj ) );	
    ostVendaPj.inComodato = 0;


	get_tag(parm,dnode,"SGTIPOPESSOA",0,-1);
	strcpy(stOrdemVenda.szSGTIPOPESSOA,parm);

    char sInscrEstad[256];
    char sInscrMunic[256];
    char idPessoa[256];
    memset( sInscrEstad, 0x0, sizeof(sInscrEstad) );
    memset( sInscrMunic, 0x0, sizeof(sInscrMunic) );
    memset( idPessoa   , 0x0, sizeof(idPessoa) );

	
	//OSs de COMODATO E MULTA PARA CLIENTES PJ
	if(strcmp(stOrdemVenda.szSGTIPOPESSOA,"PJ")==0)
	{
		inPj=1;
		get_tag(parm,dnode,"IDTIPOCARTEIRA",0,0);
		strcpy(stOrdemVenda.szIDTIPOCARTEIRA,parm);

		get_tag(parm,dnode,"SGOFERTA",0,0);
		strcpy(stOrdemVenda.szSGOFERTA,parm);

		get_tag(parm,dnode,"PRZVIGENCIA",0,0);
		strcpy(stOrdemVenda.szPermanencia,parm);
	
		get_ParametrosPJ(stOrdemVenda.szSGOFERTA,
			   			 stOrdemVenda.szIDTIPOCARTEIRA,
						 stOrdemVenda.szPermanencia,
						 &ostVendaPj);

		strcpy(stOrdemVenda.szTipoDoc,ostVendaPj.szTipoDoc);

		strcpy(stOrdemVenda.szPermanencia,ostVendaPj.szPermaneciaSAP);

		if(ostVendaPj.inComodato==1)
		{
			strcpy(stOrdemVenda.szVALIDADE_DE,ostVendaPj.dtDE);
			strcpy(stOrdemVenda.szVALIDADE_ATE,ostVendaPj.dtATE);
		}
		else
		{
			strcpy(stOrdemVenda.szVALIDADE_DE,"");
			strcpy(stOrdemVenda.szVALIDADE_ATE,"");
		}


	}
	ULOG(stOrdemVenda.szVALIDADE_DE);
	ULOG(stOrdemVenda.szVALIDADE_DE);
	

	///////////////////////////////////////////////////////////////////////////////
	// TAGs Obrigatorias
    // novas TAGs do sistema
    ///////////////////////////////////////////////////////////////////////////////
    get_tag(parm,dnode,"inExecutar",0,0);
	inExecutar = atoi(parm);

    get_tag(parm,dnode,"inRetornoOld",0,0);
	inRetornoOld = atoi(parm);

    
    get_tag( parm,dnode,"IDPESSOA",0,0 );
    strcpy( stOrdemVenda.szIDPESSOA,parm );
    BuscaEmail( parm, nmEmailPrm );   /* OS 1133 */
    strcpy( stOrdemVenda.szE_MAIL01, nmEmailPrm );

    //esta colocado aqui pois precisamos saber
    //qual é o estado do cliente
    memset( &parm, 0, sizeof( parm ) );	
    get_tag(parm,dnode,"ESTADO_ENT",0,-1);
    strcpy(stOrdemVenda.szESTADO_ENT,parm);
    get_tag(parm,dnode,"ESTADO_CLI",0,-1);
    if ( strlen(stOrdemVenda.szESTADO_ENT) == 0 )
        strcpy( stOrdemVenda.szESTADO_ENT,parm );

    get_tag(parm,dnode,"IDGRUPO",0,0);
    stOrdemVenda.szIDGRUPO =atoi(parm);

	//findbanco szIDAPARELHO
	get_tag(parm,dnode,"IDAPARELHO",0,0);    //Este id DEVE ser o IDAPARELHOCOR
    ULOG( ">>> Presumindo que seja idAparelhoCor [%d]", atoi(parm));
	strcpy(stOrdemVenda.szIDAPARELHO,parm);

    // SM250
    // @Marcelo Jan/2007
    Venda pVenda;
    memset( &pVenda,0x0,sizeof(pVenda) );
    pVenda.idAparelho = atoi( parm );

    get_tag(parm,dnode,"TELEFONE",0,0);
	strcpy(stOrdemVenda.szTELEFONE,parm);
    
    // SM250
    strcpy( pVenda.nrLinha,parm );


	// nova funcao para a sm279
	get_SapEmpresaOrgVenda(stOrdemVenda.szORGVENDA,stOrdemVenda.szTELEFONE);
	// ESTE METODO FOI SUBISTITUDIO PARA ATENDER A SM 279
	// get_SapEmpresa(stOrdemVenda.szESTADO_ENT,stOrdemVenda.szEMPRESA); // SM 279

	//if(!strlen(stOrdemVenda.szEMPRESA)) strcpy(stOrdemVenda.szEMPRESA,"2900");

    ULOG("stOrdemVenda.szORGVENDA = [%s]",stOrdemVenda.szORGVENDA);

    oEntrada.createTag( "CLIENTE" );
        oEntrada.addItem("ORGVENDA",stOrdemVenda.szORGVENDA);   // SM 279 MODIFICADO
	    // oEntrada.addItem("EMPRESA",stOrdemVenda.szEMPRESA);   // SM 279  RETIRADO 
	    get_tag(parm,dnode,"NOME",0,0);
	    strcpy(stOrdemVenda.szNOME,parm);
	    oEntrada.addItem("NOME",stOrdemVenda.szNOME);
	    get_tag(parm,dnode,"RUA_CLI",0,0);
        SeparaEndereco( parm, nmRua, nrEnder, nmComplEnd );
        strcpy( stOrdemVenda.szRUA_CLI,nmRua );
        strcpy( stOrdemVenda.szNUM_CLI,nrEnder );
        strcpy( stOrdemVenda.szCOMPL_CLI,nmComplEnd );
	    
        oEntrada.addItem( "RUA_CLI", stOrdemVenda.szRUA_CLI );
        oEntrada.addItem( "NUM_CLI", stOrdemVenda.szNUM_CLI );
        oEntrada.addItem( "COMPL_CLI", stOrdemVenda.szCOMPL_CLI );

	    get_tag(parm,dnode,"BAIRRO_CLI",0,0);
	    strcpy(stOrdemVenda.szBAIRRO_CLI,parm);
	    oEntrada.addItem("BAIRRO_CLI",stOrdemVenda.szBAIRRO_CLI);
	    get_tag(parm,dnode,"CIDADE_CLI",0,0);
	    strcpy(stOrdemVenda.szCIDADE_CLI,parm);
	    oEntrada.addItem("CIDADE_CLI",stOrdemVenda.szCIDADE_CLI);
	    get_tag(parm,dnode,"CEP_CLI",0,0);
        //CASO VENHA O CEP DESFORMATADO SERA COLOCADO O '-'
        formata_cep(parm,stOrdemVenda.szCEP_CLI);
	    oEntrada.addItem("CEP_CLI",stOrdemVenda.szCEP_CLI);
	    oEntrada.addItem("ESTADO_CLI",stOrdemVenda.szESTADO_ENT);
	    oEntrada.addItem("PAIS_CLI","BR");

        memset( parm, 0, sizeof( parm ) );	
        memset( nmRua, 0, sizeof( nmRua ) );	
        memset( nrEnder, 0, sizeof( nrEnder ) );	
        memset( nmComplEnd, 0, sizeof( nmComplEnd ) );	
        get_tag(parm,dnode,"RUA_ENT",0,-1);
        SeparaEndereco( parm, nmRua, nrEnder, nmComplEnd );
        strcpy( stOrdemVenda.szRUA_ENT,nmRua );
        strcpy( stOrdemVenda.szNUM_ENT,nrEnder );
        strcpy( stOrdemVenda.szCOMPL_ENT,nmComplEnd );

	    oEntrada.addItem( "RUA_ENT"  , stOrdemVenda.szRUA_ENT );
	    oEntrada.addItem( "NUM_ENT"  , stOrdemVenda.szNUM_ENT );
	    oEntrada.addItem( "COMPL_ENT", stOrdemVenda.szCOMPL_ENT );

        memset( &parm, 0, sizeof( parm ) );	
	    get_tag(parm,dnode,"BAIRRO_ENT",0,-1);
	    strcpy(stOrdemVenda.szBAIRRO_ENT,parm);
	    oEntrada.addItem("BAIRRO_ENT",stOrdemVenda.szBAIRRO_ENT);
	    memset( &parm, 0, sizeof( parm ) );	
	    get_tag(parm,dnode,"CIDADE_ENT",0,-1);
	    strcpy(stOrdemVenda.szCIDADE_ENT,parm);
	    oEntrada.addItem("CIDADE_ENT",stOrdemVenda.szCIDADE_ENT);
        memset( &parm, 0, sizeof( parm ) );	
	    get_tag(parm,dnode,"CEP_ENT",0,-1);
        formata_cep(parm,stOrdemVenda.szCEP_ENT);    
	    //tag foi capturada na "ESTADO_CLI"
	    oEntrada.addItem("CEP_ENT",stOrdemVenda.szCEP_ENT);
	    //tag foi capturada na "ESTADO_CLI"
	    oEntrada.addItem("ESTADO_ENT",stOrdemVenda.szESTADO_ENT);
	    oEntrada.addItem("PAIS_ENT","BR"); //FIXO
	    
    //  esta parte do codigo foi modificada para atender a sm 279
    //	get_tag(parm,dnode,"TELEFONE",0,0);
    //	strcpy(stOrdemVenda.szTELEFONE,parm);

	    oEntrada.addItem("TELEFONE",stOrdemVenda.szTELEFONE);
	    //oEntrada.addItem("TELEFONE","10190774843");
	    memset( &parm, 0, sizeof( parm ) );	
	    iNrPedido=get_tag(parm,dnode,"NUMERO_PEDIDO",0,-1);
	  
		if(strlen(parm)<=0) 
           get_Retencao(stOrdemVenda.szNUMERO_PEDIDO);
	    else			  
           strcpy(stOrdemVenda.szNUMERO_PEDIDO,parm);
        memset(&szTipoDoc, 0, sizeof( szTipoDoc ));



	    get_Cpf(stOrdemVenda.szIDPESSOA,stOrdemVenda.szDOCUMENTO,szTipoDoc);
		//OSs de COMODATO E MULTA PARA CLIENTES PJ
		if(inPj==1)
		{
			oEntrada.addItem("TIPO_DOC",stOrdemVenda.szTipoDoc);
            strcpy( idPessoa, (char*)stOrdemVenda.szIDPESSOA );
            GetIE( idPessoa, sInscrEstad );
            GetIM( idPessoa, sInscrMunic );
		}
		else
		{
			oEntrada.addItem("TIPO_DOC",szTipoDoc);    
		}
		
	    oEntrada.addItem( "DOCUMENTO", stOrdemVenda.szDOCUMENTO );
        
        /*
        if ( sInscrEstad[0] == 0x0 )
        {
           strcpy( sInscrEstad, "ISENTO" );
        }
        if ( sInscrMunic[0] == 0x0 )
        {
           strcpy( sInscrEstad, "ISENTO" );
        }
        */
        
	    oEntrada.addItem( "INSCR_ESTADUAL", sInscrEstad );
	    oEntrada.addItem( "INSCR_MUNICIPAL", sInscrMunic );
        oEntrada.addItem( "E_MAIL01", stOrdemVenda.szE_MAIL01 );

		get_tag( parm,dnode,"IDSEGMENTACAO",0,0 );
		strcpy( idSeg,parm );
		memset( sgSegm, 0x0, sizeof(sgSegm) );
		BuscaSegmentacao( idSeg, sgSegm );
		
		/*  Assim que for promovido para producao, retirar este codigo */
		if ( EmiteTag() )
		{
           oEntrada.addItem( "CLASSE", sgSegm );
		}
		
    oEntrada.closeTag();

    oEntrada.createTag( "ORDEMCAB" );
        get_tag( parm,dnode,"COND_PGTO",0,0 );
        iparcelas = atoi( parm );
		
		if(ostVendaPj.inComodato!=1)
		{
            sprintf( stOrdemVenda.szCOND_PGTO, "%d",iparcelas );
			get_parcelas( iparcelas,stOrdemVenda.szCOND_PGTO );
		}
		else
		{
			strcpy(stOrdemVenda.szCOND_PGTO,"S313"); //HARDCODE POR EM QUANTO
		}
        oEntrada.addItem( "COND_PGTO",stOrdemVenda.szCOND_PGTO );
        get_tag( parm,dnode,"IDSEGMENTACAO",0,0 );
        strcpy( idSeg,parm );

        ULOG( "CHAMADA DO SAP" );
        get_SapParametros(stOrdemVenda.szIDGRUPO,
				          stOrdemVenda.szIDPESSOA,
				          stOrdemVenda.szESTADO_ENT,
				          idSeg,
				          stOrdemVenda.szVKGRP, 
				          stOrdemVenda.szPO_METHOD, 
				          stOrdemVenda.szAUGRU);

        //parametros capturados na tabela RETENCAO.MATRIZAPARELHOSAP
        oEntrada.addItem( "AUGRU",stOrdemVenda.szAUGRU );
        oEntrada.addItem( "PO_METHOD",stOrdemVenda.szPO_METHOD );
        oEntrada.addItem( "VKGRP",stOrdemVenda.szVKGRP );
        oEntrada.addItem( "NUMERO_PEDIDO",stOrdemVenda.szNUMERO_PEDIDO );
        oEntrada.addItem( "PONTOS","" );
        //
        // @Marcelo - Feb/2007
        // POG: Presumindo que a TAG Linha eh enviada.
        // Solucionando o problema, de nao enviar a linha selecionada.
        parm[0] = 0x0;
        get_tag( parm,dnode,"LINHA",0,-1 );
        if ( parm[0] != NULL )
            oEntrada.addItem( "LINHA",parm );
        else
            oEntrada.addItem( "LINHA",stOrdemVenda.szTELEFONE );

        //get_pagto
        get_tag( parm,dnode,"MEIOPAGTO",0,0);
        get_TipoPagto( parm,stOrdemVenda.szMEIOPAGTO );
      
		ULOG( "MEIO DE PAGAMENTO SAP [%s]",stOrdemVenda.szMEIOPAGTO );
        oEntrada.addItem( "MEIOPAGTO",stOrdemVenda.szMEIOPAGTO );
		
		get_tag(parm,dnode,"OBSERVACAO",0,-1);
        strcpy(stOrdemVenda.szOBSERVACAO,parm);
        oEntrada.addItem("OBSERVACAO",stOrdemVenda.szOBSERVACAO);
		
		if(inPj==1)
		{	
			oEntrada.addItem( "VALI_DESDE",stOrdemVenda.szVALIDADE_DE );
			oEntrada.addItem( "VALI_ATE",stOrdemVenda.szVALIDADE_ATE );
		}
		else
		{
			oEntrada.addItem( "VALI_DESDE","" );
			oEntrada.addItem( "VALI_ATE","" );
		}
    oEntrada.closeTag();

	
	
		oEntrada.createTag( "ORDEMITEM" );
			get_tag(parm,dnode,"MATERIAL",0,0);
			strcpy(stOrdemVenda.szMaterial,parm);
			oEntrada.addItem("MATERIAL",stOrdemVenda.szMaterial);
		
			get_tag(parm,dnode,"QUANTIDADE",0,0);
			strcpy(stOrdemVenda.szQUANTIDADE,parm);
			oEntrada.addItem("QUANTIDADE",stOrdemVenda.szQUANTIDADE);

			get_tag(parm,dnode,"DATA_REMESSA",0,0);
			strcpy(stOrdemVenda.szDATA_REMESSA,parm);
			oEntrada.addItem("DATA_REMESSA",stOrdemVenda.szDATA_REMESSA);

			oEntrada.addItem("HORA_MANHA_INI","");
			oEntrada.addItem("HORA_MANHA_FIM","");
			oEntrada.addItem("HORA_TARDE_INI","");
			oEntrada.addItem("HORA_TARDE_FIM","");

			get_tag(parm,dnode,"VALOR",0,0);
			strcpy(pVenda.vlAparelho,parm);
			strcpy(stOrdemVenda.szVALOR,parm); //persistir o valor do aparelho

		  
			IdentificaItemVenda( &pVenda,stOrdemVenda.szVALOR,szTipoDoc);
			

			if(stOrdemVenda.szMEIOPAGTO[0]=='M' || stOrdemVenda.szMEIOPAGTO[0]=='I' )
			{
				oEntrada.addItem("VALOR","");
			}
			else
			{
					oEntrada.addItem("VALOR",stOrdemVenda.szVALOR);
			}
			
		
	 
		  
			if ( stOrdemVenda.szMaterial[1] == 'G' )  // Aparelho com CHIP
				oEntrada.addItem( "TIPO_VALOR","POS" );
			else
				oEntrada.addItem( "TIPO_VALOR","" );
			
			if(strcmp(stOrdemVenda.szSGTIPOPESSOA,"PJ")==0)
			{	
				oEntrada.addItem("PERMANENCIA",stOrdemVenda.szPermanencia);
			}
			else
			{
				oEntrada.addItem("PERMANENCIA","");
			} 
			
		oEntrada.closeTag();

		
	int iSemSIMCARD = 0;
	bool bNaoEnviar = true;
	ULOG( "Obtendo informação da TAG <SEMSIMCARD>");		
	get_tag(parm,dnode,"SEMSIMCARD",0,-1);
	if ( parm[0] != 0x0 )
	{
	   iSemSIMCARD = atoi( parm );
	}
	 
	//1523 – Troca PJ sem chip
	if((strcmp(stOrdemVenda.szSGTIPOPESSOA,"PJ")==0) && (iSemSIMCARD == 1))
	 {
		bNaoEnviar = false;
	 }
	 
	 //1523 – Troca PJ sem chip
	 if( bNaoEnviar == true ) // OS 
	 {		
		
		if ( pVenda.vlrVenda[0] != NULL )  // Aparelho com CHIP
		{
			ULOG( "Será enviada a informação do CHIP");		
			oEntrada.createTag( "ORDEMITEM" );

				oEntrada.addItem("MATERIAL",(char *)pVenda.cdSap );

				oEntrada.addItem( "QUANTIDADE",1 );

				oEntrada.addItem("DATA_REMESSA",stOrdemVenda.szDATA_REMESSA);

				oEntrada.addItem("HORA_MANHA_INI","");
				oEntrada.addItem("HORA_MANHA_FIM","");
				oEntrada.addItem("HORA_TARDE_INI","");
				oEntrada.addItem("HORA_TARDE_FIM","");
				
				if(stOrdemVenda.szMEIOPAGTO[0]=='M' || stOrdemVenda.szMEIOPAGTO[0]=='I' )
				{
						oEntrada.addItem("VALOR","");
				}
				else
				{
					oEntrada.addItem("VALOR",(char *)pVenda.vlrVenda);
				}
				
			   oEntrada.addItem("PERMANENCIA","");
			oEntrada.closeTag();
		}
	}
	else
	{
		ULOG( "Não será enviada a informação do CHIP");	
	}

	 	
	pXmlIn=oEntrada.retrieveXML(&iXmlInLen);



	if(pXmlIn!=NULL)
	{
		strcpy(szXmlIn,pXmlIn);
		free(pXmlIn);
	}

    ULOG( ">>> XML parametro para SAP [%s]",szXmlIn );

    // Com a OS 939
    // O controle de estoque deixou d e existir.
    switch(inExecutar)
    {
      case EXECUTAR_BAIXA:
           //BaixaEstornoEstoque(atoi(getUser()),stOrdemVenda.szIDAPARELHO,stOrdemVenda.szESTADO_ENT,stOrdemVenda.szIDPESSOA,1,stOrdemVenda.szTELEFONE) ;
           break ;
      case EXECUTAR_ESTORNO:
           //BaixaEstornoEstoque(atoi(getUser()),stOrdemVenda.szIDAPARELHO,stOrdemVenda.szESTADO_ENT,stOrdemVenda.szIDPESSOA,0,stOrdemVenda.szTELEFONE) ;
           break ;
      default:
      {  
		
        try
        {
            ULOG("INICIO DO SAP");

		if(strcmp(stOrdemVenda.szSGTIPOPESSOA,"PF")==0 && strcmp(stOrdemVenda.szSGOFERTA,"APC")==0 )
		{
			vlcontrole=999;
			throw new TuxBasicSvcException(NOKFID,"Erro tipo de Pessoa!");
		}

		vlAparelhoControle = atof(stOrdemVenda.szVALOR);
	
		if(vlAparelhoControle<=0 && stOrdemVenda.szMEIOPAGTO[0]=='T')
		{
			vlcontrole=666;
			throw new TuxBasicSvcException(NOKFID,"Aparelho jah existe");
		}

            
        	TuxServico.SetService("CriaOrdemVenda");
        	
        	TuxServico.SetUser("SAP");
        
        	ULOG("Configurou objeto TuxServico");
        	TuxServico.SetServiceName("CriaOrdemVenda");
        
        	TuxServico.SetBody(&oEntrada);
        
        	TuxServico.SetInputMessage();


        	ULOG("INICIO DO SAP -> TuxServico.RemoteCall() ");
        
            if(TuxServico.RemoteCall())
        	{
        	    
        	    ULOG("SUCESSO DO SAP -> TuxServico.RemoteCall() ");
        		if(	TuxServico.IsValidMessage())
        		{
        			ULOG("Entrou no IsValidMessage()");
        			TuxServico.GetMessgeStr(szXml);
        			sprintf(szRetorno, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", szXml);
        			
        		    ULOG("conteudo do xml()");
        			ULOG(szRetorno);
        		   
        			pParser =  new XercesDOMParser;
        			pMemBuf =  new MemBufInputSource( (const XMLByte*)szRetorno, strlen(szRetorno), pMemBufId);
                   
        			
        			pParser->parse(*pMemBuf);
        			DOMNode* pDoc = pParser->getDocument();
        			
        			memset(szOrdemVenda,0x0,sizeof(szOrdemVenda));
        			get_tag(parm,pDoc,"RETURN",0,0);
        			if(strlen(parm)<1)
        			{
        			    memset(parm,0x0,sizeof(parm));
                 		get_tag(parm,pDoc,"ORDER_NUMBER",0,0);
        				strcpy(szOrdemVenda,parm);
        				sprintf(szDsErro,"Ordem de Venda Gerada normalmente [%s]",szOrdemVenda);
        			}
        			else
        			{
        			 iErro=0;
        			 iCodErro=1;
        			 strcpy(szDsErro,parm);
        			}
        			
        			
        
        		//	strcpy(szRetorno,parm);
        
        	    	iRetorno=1;
        		}
        		else
        		{
        			memset( &parm, 0, 255);	
        			iErro=0;
        			iCodErro=20;
        
        			ULOG("Mensagem nao e valida!");
        			strcpy(szDsErro,"Mensagem de retorno não é válida!");
        			TuxServico.GetErrMsg();
        			
        		}
        	}
        	else
        	{
        		iErro=0;
        		iCodErro=666;
        		strcpy(szDsErro,"Falha na Comunicação com o Sistema");
        		ULOG("Falha na Comunicaçao com o Sistema");	
        	}
        	
        	ULOG("FIM DO SAP");
        	
        
        }
        catch(...)
        {
        
			if(vlcontrole==666)
			{
				iErro=0;
        		iCodErro=1;
        		strcpy(szDsErro,"O valor do aparelho com desconto é menor ou igual ao valor do chip, o que irá gerar uma Nota Fiscal com valor zero ou negativa.Modificar o desconto concedido");
			}
			else if(vlcontrole==999)
			{
				iErro=0;
        		iCodErro=1;
        		strcpy(szDsErro,"Oferta proibida para Pessoa Física");
			}
			else
			{

        		TuxServico.GetErrMsg();
        		iErro=0;
        		strcpy(parm,"");
			}
			
			delete pParser;
			delete pMemBuf;
        }
        
        
 //loga o que ocorreu com a comunicação do SAP 
 set_logInfo(stOrdemVenda.szNUMERO_PEDIDO,szDsErro,atoi(getUser()),szXmlIn,szOrdemVenda);
        
        	xml_g->createTag("tns:RetencaoRetornoSAPVO");
        		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        		xml_g->addItem("erro",iErro);
        		xml_g->addItem("codErro",iCodErro);
        		if ( iErro != 2 )
        		   xml_g->addItem("descErro",szDsErro);
        		xml_g->addItem("idRetencao",stOrdemVenda.szNUMERO_PEDIDO);
        		xml_g->addItem("ordemServico",szOrdemVenda);
        
        	xml_g->closeTag();

	/*		xml_g->createTag("tns:RetencaoRetornoSAPVO");
        		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        		xml_g->addItem("erro","1");
        		xml_g->addItem("codErro","0");
        		if ( iErro != 2 )
        		   xml_g->addItem("descErro","0");
        		xml_g->addItem("idRetencao","6661122");
				xml_g->addItem("ordemServico","EVIL00666");
        
        	xml_g->closeTag();*/
        
        	
            // Com a OS 939
            // Nao existe mais controle de estoque
            // if(iCodErro==0)
              // if( 0 == inRetornoOld   ) // verifica se ja nao existe uma retencao para este aparelho
                  // BaixaEstornoEstoque(atoi(getUser()),stOrdemVenda.szIDAPARELHO,stOrdemVenda.szESTADO_ENT,stOrdemVenda.szIDPESSOA,1,stOrdemVenda.szTELEFONE);
        	    
       }
    }
	delete pParser;
	delete pMemBuf;
  setStatusCode(OKFID, OKMSG);
  ULOG_END("COMSAP");
}



