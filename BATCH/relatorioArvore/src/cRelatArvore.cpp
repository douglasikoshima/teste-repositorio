
#include "../include/cRelatArvore.h"

list<unsigned long>::iterator it;
list<unsigned long>::iterator itGrupo;
list<unsigned long>::iterator itGrupoRelContato;
list<unsigned long>::iterator itContato_NOK;
list<unsigned long>::iterator itvecGrupoRelContato;
/*---------------------------------------------------------------------*/
// Prototypes
/*---------------------------------------------------------------------*/

extern void proc_OrganizaTabela( int ttlPrm, cTrace * log );

extern bool proC_SelRelatorios
(
     char *sArquivoRel, 
     int * idTipoRelatorio,
     char * nmLogin,
     LIST_PK * pTipoCliente,
     LIST_PK * pTipoLinha,
     LIST_PK * pSegmentacao,
     LIST_PK * pTipoCarteira,
     LIST_PK * pCanal,
     LIST_PK * pNatureza,
     LIST_PK * pRegional,
     LIST_PK * pFechamento,
     LIST_PK * pGrupoTratamento,
     LIST_PK * pGrupoAbertura,
     LIST_PK * pGrupoRetorno,
     int *inDisponibilidade,
     st_vlRelatorioArvore * status,
     cTrace * log 
);

extern void proC_CarregaFiltro( 
                         char *sLogin,
                         char *dataSolicitacao,
                         int * idTipoRelatorio,
                         LIST_PK * pTipoCliente,
                         LIST_PK * pTipoLinha,
                         LIST_PK * pSegmentacao,
                         LIST_PK * pTipoCarteira,
                      	 LIST_PK * pCanal,
                         LIST_PK * pNatureza,
                         LIST_PK * pRegional,
                         LIST_PK * pFechamento,
                         LIST_PK * pGrupoTratamento,
                         LIST_PK * pGrupoAbertura,
                         LIST_PK * pGrupoRetorno,
                         int *inDisponibilidade,
                         st_vlRelatorioArvore * status,
                         cTrace * log );

extern void proC_Conectar( cTrace * log, char * sConnect, int * flgConectado );

extern void proC_Desconectar( cTrace * log );

extern int procObtemFolhas_Contato( bool FlagCorretos, 
                             LIST_PK * pContato_OK, 
                             LIST_PK * pContato_NOK,
                             LIST_PK * pTipoCliente,
                             LIST_PK * pTipoLinha,
                             LIST_PK * pSegmentacao,
                             LIST_PK * pTipoCarteira,
                             LIST_PK * pRegional,
                             LIST_PK * pFechamento,
                             LIST_PK * pGrupoTratamento,
                             LIST_PK * pGrupoAbertura,
                             LIST_PK * pGrupoRetorno,
                             int inDisponibilidade,
                             st_vlRelatorioArvore * status,
                             cTrace * log 
                           );

extern int proCDetalheContato( const unsigned long idContatoPrm, 
                               cRelatorioArvore * pRelatorio,
                               cTrace * log );

                                   
extern int procObtemFolhas_Grupo( bool FlagCorretos, 
									 LIST_PK * pGrupo_OK, 
									 LIST_PK * pGrupo_NOK,
									 LIST_PK * pTipoCliente,
									 LIST_PK * pTipoLinha,
									 LIST_PK * pSegmentacao,
									 LIST_PK * pTipoCarteira,
									 LIST_PK * pRegional,
									 LIST_PK * pGrupoAbertura,
                        			 LIST_PK * pCanal,
									 LIST_PK * pProcedencia,
									 LIST_PK * pNatureza,
									 int inDisponibilidade,
									 st_vlRelatorioArvore * status,
									 string listaGrupo ,
                                     cTrace * log
								   );


extern int proCDetalheRegistrosGrupo( LIST_PK * pGrupo,
                                      cRelatorioArvore * pRelatorio,
                                      cTrace * log );

extern int procObtemGrupoRelacionadosContatos( unsigned long idContato 
                                             ,string * listaGrupo 
                                             ,LIST_PK *vecGrupoRelContato,
                                             cTrace * log
                                             );

extern void proCNomeContato( const unsigned long idContatoPrm,string  *sNMContato, cTrace * log ) ;

extern void proCNomeGrupo( string * listaGrupo , LIST_PK  *vecGrupo, cTrace * log );

extern void proCNomeFase( string * lista , LIST_PK  *vecGrupo , unsigned long idContato, cTrace * log );
                                      
/*----------------------------------------------------------------------*/
//
/*----------------------------------------------------------------------*/
cRelatorioArvore::cRelatorioArvore( char * sArquivoConfiguracao )
{

    Propriedade prop( sArquivoConfiguracao );

    memset( &status,-1,sizeof(status) );
    szPws = prop.getParametro("pwd");
    szUsr = prop.getParametro("usr");
    szInst= prop.getParametro("inst");
    szPath= prop.getParametro("pth");
    sprintf( sDiretorioEntrada, "%s/InBox" , szPath );
    sprintf( sDiretorioSaida  , "%s/OutBox", szPath );

    TempoLimiteArqSaida = atoi(prop.getParametro( "ttlOut" ));


    int vlcTrace = atoi( prop.getParametro("log") );
    string msgcTrace;

    conectado = false;
    debugOn = vlcTrace;

    if (!szPws || !szUsr || !szPath || !szInst )
    {
        configuracaoCompleta = false;
    }
    else
    {
/*
    	printf( "User [%s]\n",szUsr );
    	printf( "Passwd [%s]\n",szPws );
    	printf( "banco [%s]\n",szInst );
*/    	
        szConnStr = (string)szUsr + "/" + (string)szPws + "@" + (string)szInst;

        configuracaoCompleta = true;

        msgcTrace = "Vai conectar em " + (string)szUsr + "/pwd???@" + (string)szInst;

    }

}




bool cRelatorioArvore::LeArquivoCnf( const char *sNomeArquivo )
{
    log->Trace( "> LeArquivoCnf" );
    FILE * fp;
    char sLido[1025];
    char * p;
    char * lido;
    list<unsigned long>::iterator it;

    memset( sLido,0x0,sizeof(sLido) );

    log->Trace( "Arquivo de variaveis [%s]", sNomeArquivo );
    fp = fopen( sNomeArquivo,"r");
    if ( fp == NULL )
    {
        log->Trace( ERROR_LEVEL,"ARQUIVO: [%s] - [%s]", sNomeArquivo, strerror(errno) );
        //log->Trace( "< LeArquivoCnf" );
        return false;
    }

    while( !feof(fp) )
    {
        fgets( sLido,sizeof(sLido),fp );
        if ( sLido[0] == '#' )
        {
            continue;
        }
        
        sLido[strlen(sLido)-1] = 0x0;
        log->Trace( "Registro Lido [%s]",sLido );
        /*------------------------------------------------*/
        // Atribui Atendente
        /*------------------------------------------------*/
        lido = strstr( sLido,"login" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                strcpy( sLogin,p );
            }
        }

        log->Trace( "Operador [%s]",sLogin );
        /*------------------------------------------------*/
        // Atribui Tipo de Consulta
        /*------------------------------------------------*/
        lido = strstr( sLido,"consulta" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                TipoRelatorio = atoi(p);
            }
        }

        /*------------------------------------------------*/
        // Atribui Tipo de Linha
        /*------------------------------------------------*/
        lido = strstr( sLido,"tipolinha" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pTipoLinha.push_back(strtoul(p,0,10));
                status.idTipoLinha = 1;
            }
        }
        if ( status.idTipoLinha == 1 )
        {
            log->Trace( "Filtrando por Tipo de Linha" );
        }

        /*------------------------------------------------*/
        // Atribui Segmento
        /*------------------------------------------------*/
        lido = strstr( sLido,"segmento" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pSegmentacao.push_back(strtoul(p,0,10));
                status.idSegmentacao = 1;
            }
        }
        if ( status.idSegmentacao == 1 )
        {
            log->Trace( "Filtrando por Segmentacao" );
        }
    
        /*------------------------------------------------*/
        // Atribui Tipo de Cliente
        /*------------------------------------------------*/
        lido = strstr( sLido,"tipocliente" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pTipoCliente.push_back(strtoul(p,0,10));
                status.idTipoRelacionamento = 1;
            }
        }
        if ( status.idTipoRelacionamento == 1 )
        {
            log->Trace( "Filtrando por Tipo de Cliente" );
        }

        /*------------------------------------------------*/
        // Atribui Tipo de Carteira
        /*------------------------------------------------*/
        lido = strstr( sLido,"tipocarteira" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pTipoCarteira.push_back(strtoul(p,0,10));
                status.idTipoCarteira = 1;
            }
        }

        if ( status.idTipoCarteira == 1 )
        {
            log->Trace( "Filtrando por Tipo de Carteira" );
        }

        /*------------------------------------------------*/
        // Atribui Regional
        /*------------------------------------------------*/
        lido = strstr( sLido,"regional" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pRegional.push_back(strtoul(p,0,10));
                status.idUFOperadora = 1;
            }
        }
        if ( status.idUFOperadora == 1 )
        {
            log->Trace( "Filtrando por Regional" );
        }

        /*------------------------------------------------*/
        // Atribui Natureza
        /*------------------------------------------------*/
        lido = strstr( sLido,"natureza" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pNatureza.push_back(strtoul(p,0,10));
                status.idNatureza = 1;
            }
        }
        if ( status.idNatureza == 1 )
        {
            pNatureza.sort();
            pNatureza.unique();
            log->Trace( "Filtrando por Natureza" );
        }

        /*------------------------------------------------*/
        // Atribui Canal
        /*------------------------------------------------*/
        lido = strstr( sLido,"canal" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pCanal.push_back(strtoul(p,0,10));
                status.Canal = 1;
            }
        }
        if ( status.Canal == 1 )
        {
            log->Trace( "Filtrando por Canal" );
        }

        /*------------------------------------------------*/
        // Atribui Tipo de Fechamento
        /*------------------------------------------------*/
        lido = strstr( sLido,"fechamento" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pFechamento.push_back(strtoul(p,0,10));
                status.idFaseGrupoFechamento = 1;
            }
        }
        if ( status.idFaseGrupoFechamento == 1 )
        {
            log->Trace( "Filtrando por FaseGrupoFechamento" );
        }

        /*------------------------------------------------*/
        // Atribui Grupo de Abertura
        /*------------------------------------------------*/
        lido = strstr( sLido,"grupoabertura" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pGrupoAbertura.push_back(strtoul(p,0,10));
                status.idGrupoAbertura = 1;
            }
        }
        if ( status.idGrupoAbertura == 1 )
        {
            log->Trace( "Filtrando por Grupo de Abertura" );
        }

        /*------------------------------------------------*/
        // Atribui Grupo de Tratamento
        /*------------------------------------------------*/
        lido = strstr( sLido,"grupotratamento" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pGrupoTratamento.push_back(strtoul(p,0,10));
                status.idGrupoTratamento = 1;
            }
        }
        if ( status.idGrupoTratamento == 1 )
        {
            log->Trace( "Filtrando por Grupo de Tratamento" );
        }

        /*------------------------------------------------*/
        // Atribui Grupo de Retorno
        /*------------------------------------------------*/
        lido = strstr( sLido,"grupotratamento" );
        if ( lido != NULL )
        {
            p = strtok(lido,"=");
            while (p != NULL)
            {
                p = strtok (NULL, " ,.");
                if ( p == NULL )
                    continue;
                pGrupoRetorno.push_back(strtoul(p,0,10));
                status.idGrupoRetorno = 1;
            }
        }
        if ( status.idGrupoRetorno == 1 )
        {
            log->Trace( "Filtrando por Grupo de Retorno" );
        }

    }

    fclose( fp );


    log->Trace( "< LeArquivoCnf" );

    return true;
}




bool cRelatorioArvore::Executa( void )
{
    //log->Trace( ">>> Iniciando geracao de relatorio..." );
    // contato 
    if ( TipoRelatorio == 1 )
    {
	    ObtemFolhasContato();
	    RelatorioContato();
    }

    // grupo
    if ( TipoRelatorio == 2 )
    {
        ObtemGrupo();
        RelatorioGrupo();
    }

    // contato grupo
    if ( TipoRelatorio == 3 )
    {
		// obter Contatos validos e invalidos

        ObtemFolhasContato();

		ObtemFolhasContatoGrupos(&pContato_OK,&pContatoGrupo_OK);

		ObtemFolhasContatoGrupos(&pContato_NOK,&pContatoGrupo_NOK);


		RelatorioContatoGrupo(&pContatoGrupo_OK,&pContatoGrupo_NOK);
    }

    //log->Trace( "*** Finalizando geracao de relatorio" );

    return true;
}


bool cRelatorioArvore::LimpaAmbiente( void )
{
    pContato_OK.clear();
    pContato_NOK.clear();
    pGrupo_OK.clear();
    pGrupo_NOK.clear();
    pTipoCliente.clear();
    pTipoLinha.clear();
    pSegmentacao.clear();
    pTipoCarteira.clear();
    pRegional.clear();
    pNatureza.clear();
    pCanal.clear();
    pFechamento.clear();
    pGrupoTratamento.clear();
    pGrupoAbertura.clear();
    pGrupoRetorno.clear();
	pProcedencia.clear();

    return true;
}




bool cRelatorioArvore::CarregaVariaveis( void )
{
    int idTipoRelatorio = -1;
    char nmLogin[256];
    char sArquivoRel[256];
    memset( sArquivoRel,0x0,sizeof(sArquivoRel) );
    memset( nmLogin    ,0x0,sizeof(nmLogin) );
    int disponivel = -1;

    /*----------------------*/
    // Carrega Variaveis
    /*----------------------*/
    // LeArquivoCnf( sArquivoVariaveis );
    fimRegistros = proC_SelRelatorios
    (
         sArquivoRel, 
         &idTipoRelatorio,
         nmLogin,
         &pTipoCliente,
         &pTipoLinha,
         &pSegmentacao,
         &pTipoCarteira,
         &pCanal,
         &pNatureza,
         &pRegional,
         &pFechamento,
         &pGrupoTratamento,
         &pGrupoAbertura,
         &pGrupoRetorno,
         &disponivel,
         &status,
         log
    );
    
    strcpy( sArquivoSaida,sArquivoRel );

    if ( fimRegistros ) return false;

    inDisponibilidade = disponivel;

    TipoRelatorio = idTipoRelatorio;
    if ( TipoRelatorio == 1 )
    {
        strcpy( sTipoConsulta,"Contato" );
    }
    
    if ( TipoRelatorio == 2 )
    {
        strcpy( sTipoConsulta,"Grupo" );
    }

    if ( TipoRelatorio == 3 )
    {
        strcpy( sTipoConsulta,"Contato_X_Grupo" );
    }
    
    strcpy( sLogin,nmLogin );

    log->Trace( "Tipo de Consulta [%d]",TipoRelatorio );
    log->Trace( "Selecionou Consulta por %s",sTipoConsulta );
    
    return true;
}





bool cRelatorioArvore::Conectar( void )
{
    int flgConectado;

    if ( !conectado )
        flgConectado = 0;
    else
        flgConectado = 1;

    proC_Conectar( log, (char *)szConnStr.c_str(), &flgConectado );

    if ( flgConectado == 1 )
       conectado = true;
    else
       conectado = false;
    
    return true;
}



void cRelatorioArvore::Desconectar()
{
    if ( conectado )
    {

        proC_Desconectar( log );
        conectado = false;
    }
}





bool cRelatorioArvore::ObtemFolhasContato( void )
{
    log->Trace( "> ObtemFolhasContato" );

    int ret = -1;


    // Registros que coincidem com as variaveis
    ret = procObtemFolhas_Contato( true, 
                                   &pContato_OK, 
                                   &pContato_NOK, 
                                   &pTipoCliente,
                                   &pTipoLinha,
                                   &pSegmentacao,
                                   &pTipoCarteira,
                                   &pRegional,
                                   &pFechamento,
                                   &pGrupoTratamento,
                                   &pGrupoAbertura,
                                   &pGrupoRetorno,
                                   inDisponibilidade,
                                   &status,
                                   log );

    if ( ret < 0 ) return false;

    log->Trace( "numero de elementos pContato_OK = %d",pContato_OK.size());
  
    // Nao coincidem com as variaveis
    ret = procObtemFolhas_Contato( false, 
                                   &pContato_OK, 
                                   &pContato_NOK, 
                                   &pTipoCliente,
                                   &pTipoLinha,
                                   &pSegmentacao,
                                   &pTipoCarteira,
                                   &pRegional,
                                   &pFechamento,
                                   &pGrupoTratamento,
                                   &pGrupoAbertura,
                                   &pGrupoRetorno,
                                   inDisponibilidade,
                                   &status,
                                   log );
    if ( ret < 0 ) return false;

    log->Trace( "numero de elementos pContato_NOK = %d",pContato_NOK.size());
    log->Trace( "< ObtemFolhasContato" );

    return true;
}

bool cRelatorioArvore::ObtemGrupo( void )
{
    log->Trace( "> ObtemGrupo" );

    int ret = -1;
	char msg[300];

    // Registros que coincidem com as variaveis
    ret =  procObtemFolhas_Grupo( true, 
								  &pGrupo_OK, 
								  &pGrupo_NOK,
								  &pTipoCliente,
								  &pTipoLinha,
								  &pSegmentacao,
								  &pTipoCarteira,
								  &pRegional,
								  &pGrupoAbertura,
                        		  &pCanal,
								  &pProcedencia,
								  &pNatureza,
								  inDisponibilidade,
								  &status,
								  listaGrupo,
                                  log
								 );



	sprintf(msg,"numero de elementos pGrupo_OK = %d",pGrupo_OK.size());
    log->Trace( msg);	

    if ( ret < 0 ) return false;

    // Nao coincidem com as variaveis
    ret =  procObtemFolhas_Grupo( false, 
								  &pGrupo_OK, 
								  &pGrupo_NOK,
								  &pTipoCliente,
								  &pTipoLinha,
								  &pSegmentacao,
								  &pTipoCarteira,
								  &pRegional,
								  &pGrupoAbertura,
                        		  &pCanal,
								  &pProcedencia,
								  &pNatureza,
								  inDisponibilidade,
								  &status,
								  listaGrupo,
                                  log
								 );
    
    if ( ret < 0 ) return false;

    log->Trace( "numero de elementos pGrupo_NOK = %d",pGrupo_NOK.size());
    log->Trace( "< ObtemGrupo" );
    
    return true;
}








bool cRelatorioArvore::RelatorioContato( void )
{
    log->Trace( "> RelatorioContato" );

    int ret = -1;

    OpenArquivoSaida();
    DetalheRegistros();
    CloseArquivoSaida();

    log->Trace( "< RelatorioContato" );

    return true;
}






bool cRelatorioArvore::RelatorioGrupo( void )
{
    int ret = -1;

    log->Trace( "> RelatorioGrupo" );

    OpenArquivoSaida();

    WriteArquivoSaida( "Grupos Corretos\n\n" );

    WriteArquivoSaida( "Grupo ; Tipo de Cliente ; Tipo de Linha ; Segmento  ; Carteira ; Regional ; Grupo de Abertura ; Canal ; Procedencia ; Natureza \n");


    ret = proCDetalheRegistrosGrupo( &pGrupo_OK, this, log );

    if ( ret < 0 )
    {
        CloseArquivoSaida();
        return false;
    }


    WriteArquivoSaida( "\nGrupos Incorretos\n\n" );

    WriteArquivoSaida( "Grupo ; Tipo de Cliente ; Tipo de Linha ; Segmento ; Carteira ; Regional ; Grupo de Abertura ; Canal ; Procedencia ; Natureza \n");


    ret = proCDetalheRegistrosGrupo( &pGrupo_NOK, this, log );

    if ( ret < 0 )
    {
        CloseArquivoSaida();
        return false;
    }

    CloseArquivoSaida();

    log->Trace( "< RelatorioGrupo" );

    return true;
}



bool cRelatorioArvore::DetalheRegistros( void )
{
    log->Trace( "> DetalheRegistros Contato" );

    /*---------------------------------------------------------------*/
    // Coerentes com as variaveis selecionadas
    /*---------------------------------------------------------------*/
    Cabecalho( true );
    for( it = pContato_OK.begin(); it != pContato_OK.end(); it++ )
    {
        proCDetalheContato( *it, this, log );
    }

    /*---------------------------------------------------------------*/
    // Coerentes com as variaveis selecionadas
    /*---------------------------------------------------------------*/
    Cabecalho( false );
    for( itContato_NOK = pContato_NOK.begin(); itContato_NOK != pContato_NOK.end(); itContato_NOK++ )
    {
        proCDetalheContato( *itContato_NOK, this, log );
    }

    log->Trace( "< DetalheRegistros Contato" );

    return true;

}




bool cRelatorioArvore::Cabecalho( bool flag_OK )
{
   int ret = -1;
   string Cabecalho;


   if ( flag_OK == true )
   {
       Cabecalho  = "Folhas Corretas\n";
   }
   else
   {
       Cabecalho  = "\nFolhas Incorretas\n";
   }

   WriteArquivoSaida( Cabecalho.c_str() );


   Cabecalho  = "Folha;Tipo de Cliente;Tipo de Linha"
                ";Segmento;Carteira;Regional;Fechamento;Grupo de Abertura"
                ";Grupo de Tratamento;Grupo de Retorno;Tipo de Retorno";

   WriteArquivoSaida( Cabecalho.c_str() );
   WriteArquivoSaida( "\n" );


   return true;
}




bool cRelatorioArvore::OpenArquivoSaida( void )
{
    char sRelatorio[512];
#ifndef WIN32
/*
	printf( "Diretorio Saida  [%s]\n",sDiretorioSaida );
	printf( "Tipo de Consulta [%s]\n",sTipoConsulta );
	printf( "Login            [%s]\n",sLogin );
	getchar();
*/
    sprintf( sRelatorio,"%s/%s", sDiretorioSaida, sArquivoSaida );
	log->Trace( "Arquivo de Saida [%s]",sRelatorio );

    fd = open( sRelatorio,O_WRONLY | O_CREAT | O_TRUNC, 0644 );
#else
    fd = open( "d:\\saida_teste.csv",O_WRONLY | O_CREAT | O_TRUNC, 0644 );
#endif
    if ( fd < 0)
    {
        log->Trace( ERROR_LEVEL,"OpenArquivoSaida -> [%s]:[%s]", sArquivoSaida,strerror(errno) );
        return false;
    }

    return true;

}




bool cRelatorioArvore::WriteArquivoSaida( const char * sRegistro )
{
    int retorno = -1;
    
    retorno = write( fd, sRegistro, strlen(sRegistro) );
    if ( retorno < 0 )
    {
        log->Trace( ERROR_LEVEL,"WriteArquivoSaida -> [%s]", strerror(errno) );
        return false;
    }
    
    return true;

}




void cRelatorioArvore::CloseArquivoSaida( void )
{
    close( fd );
}




void cRelatorioArvore::ObtemFolhasContatoGrupos( LIST_PK * vecContato ,VEC_CONTATOGRUPO *vecContatoGrupo)
{
     
    unsigned long idContato;
    string  sidGrupo;
    string  sNMContato;
    string  sNMGrupoCorreto;
    string  sNMGrupoInCorreto;
    string  sNMFaseCorreto;
    string  sNMFaseInCorreto;

    struct ContatoGrupo stContatoGrupo ;
    int ret ;

	// Limpa a lista
	vecContatoGrupo->clear();

    for ( it = vecContato->begin() ; it != vecContato->end(); it++ )
    {

       sidGrupo = "";  
	   sNMContato = "";
	   sNMGrupoCorreto = "";
	   sNMGrupoInCorreto = "";
	   sNMFaseCorreto = "";
	   sNMFaseInCorreto = "";

       idContato = *it; 
	   vecGrupoRelContato.clear();
	   pGrupo_OK.clear();
	   pGrupo_NOK.clear();
	   // obtem grupos relacionados ao contato
	   ret =  procObtemGrupoRelacionadosContatos(idContato,&sidGrupo,&vecGrupoRelContato,log);
	   // obtem grupos relacionados aos contatos e variaveis
       ret =  procObtemFolhas_Grupo( true, 
								  &pGrupo_OK, 
								  &pGrupo_NOK,
								  &pTipoCliente,
								  &pTipoLinha,
								  &pSegmentacao,
								  &pTipoCarteira,
								  &pRegional,
								  &pGrupoAbertura,
                        		  &pCanal,
								  &pProcedencia,
								  &pNatureza,
								  inDisponibilidade,
								  &status ,
								  sidGrupo,
                                  log
								 );


        for( itGrupo = pGrupo_OK.begin() ; itGrupo != pGrupo_OK.end(); itGrupo++ )
        {
            for( itvecGrupoRelContato = vecGrupoRelContato.begin(); itvecGrupoRelContato != vecGrupoRelContato.end(); itvecGrupoRelContato++ )
            {
                if ( *itGrupo == *itvecGrupoRelContato )
                    *itvecGrupoRelContato = 0;
            }
        }

	   
   	   for( itGrupoRelContato = vecGrupoRelContato.begin() ; itGrupoRelContato != vecGrupoRelContato.end(); itGrupoRelContato++)
	   {
		   if ( *itGrupoRelContato != 0 )
			   pGrupo_NOK.push_back( *itGrupoRelContato );

	   }


       proCNomeContato( idContato, &sNMContato, log ) ;  // retorna o nome do contato 

	   proCNomeGrupo( &sNMGrupoCorreto , &pGrupo_OK, log ); // retorna o nome de todos os grupos que atenden ao filtro

       proCNomeFase( &sNMFaseCorreto , &pGrupo_OK ,idContato, log  );

	   proCNomeGrupo( &sNMGrupoInCorreto , &pGrupo_NOK, log );  // // retorna o nome de todos os grupos que nao atenden ao filtro

	   proCNomeFase( &sNMFaseInCorreto , &pGrupo_NOK ,idContato, log  );

	   stContatoGrupo.nmContato = sNMContato ;
       stContatoGrupo.nmGrupoCoreto = sNMGrupoCorreto;
       stContatoGrupo.nmGrupoIncorreto = sNMGrupoInCorreto ;


	   stContatoGrupo.nmFaseCorreto = sNMFaseCorreto ;
       stContatoGrupo.nmFaseIncorreto= sNMFaseInCorreto;

	   vecContatoGrupo->push_back( stContatoGrupo );	   

   }
}



void cRelatorioArvore::RelatorioContatoGrupo(VEC_CONTATOGRUPO *vetContatoGrupo_OK,VEC_CONTATOGRUPO *vetContatoGrupo_NOK)
{
  struct ContatoGrupo strContatoGrupo ;  
  int i ;
  string relatorio;

  OpenArquivoSaida();

  WriteArquivoSaida( "\nContatos Corretos" );
    
  WriteArquivoSaida( "\nFolha ;Grupo Corretos ; Fase; Grupo Incorretos; FaseContato" );
  for (i=0; i< vetContatoGrupo_OK->size();i++)
  {
        strContatoGrupo = vetContatoGrupo_OK->at(i); 
        relatorio = strContatoGrupo.nmContato+SEPARADOR+strContatoGrupo.nmGrupoCoreto+SEPARADOR+ strContatoGrupo.nmFaseCorreto+SEPARADOR+ strContatoGrupo.nmGrupoIncorreto+SEPARADOR+ strContatoGrupo.nmFaseIncorreto ;
        WriteArquivoSaida("\n");
        WriteArquivoSaida(relatorio.c_str() );
  }

  WriteArquivoSaida("\n");
  WriteArquivoSaida( "\nContatos Incorretos" );

  WriteArquivoSaida( "\nFolha; Grupo Corretos; Fase; Grupo Incorretos; FaseContato" );
  for (i=0; i< vetContatoGrupo_NOK->size();i++)
  {
        strContatoGrupo = vetContatoGrupo_NOK->at(i); 

        relatorio = strContatoGrupo.nmContato+SEPARADOR+strContatoGrupo.nmGrupoCoreto+SEPARADOR+ strContatoGrupo.nmFaseCorreto+SEPARADOR+ strContatoGrupo.nmGrupoIncorreto+SEPARADOR+ strContatoGrupo.nmFaseIncorreto ;
        WriteArquivoSaida("\n");
        WriteArquivoSaida(relatorio.c_str() );
  }

  CloseArquivoSaida();

}





/***********************************************************************************************************/
char * cRelatorioArvore::format_time(time_t cal_time)
{
  struct tm *time_struct;
  static char string[30];

  time_struct=localtime(&cal_time);
  strftime(string, sizeof string, "%Y%m%d%H%M%S", time_struct);

  return(string);
}




void cRelatorioArvore::OrganizaArquivoSaida( void )
{
#ifndef WIN32
    DIR *pdir;
    struct dirent *pent;
#endif

    struct tm* ptr;
    time_t tm;
    char sDataLimite[20];
    agcData date;
    int pos = 0;
    

    memset( sDataLimite, 0x0, sizeof(sDataLimite) );

    tm = time( NULL );
    ptr = localtime( &tm );
    date.Altera( ptr->tm_mday,ptr->tm_mon+1,ptr->tm_year+1900 );
    
    date -= TempoLimiteArqSaida;
    date.Get( sDataLimite );

    proc_OrganizaTabela( TempoLimiteArqSaida, log );

    

#ifndef WIN32
    char tmp[64];
    char buffer[10];
    char* WorkDirectory;

    WorkDirectory = getcwd( NULL,64 );
    chdir( sDiretorioSaida );
    pdir=opendir( sDiretorioSaida ); // refers to output dir
    if (!pdir)
    {
        log->Trace( ERROR_LEVEL,"*** ERRO: [%s] -> [%s]",sDiretorioSaida,strerror(errno) );
        exit(1);
    }

    while ((pent=readdir(pdir)))
    {
        strcpy( tmp,pent->d_name );
        pos = strlen(tmp)-19;
        sprintf( buffer,"%.8s",(char *)&tmp[pos]);
        sprintf( tmp,"%.4s%.2s%.2s",(char*)&buffer[4],(char*)&buffer[2],buffer );
        if ( atoi(tmp) < atoi(sDataLimite) )
        {
            unlink( pent->d_name );
        }
    }

    closedir( pdir );
    
    chdir( WorkDirectory );

#endif

}
