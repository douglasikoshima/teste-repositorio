/*
 *
 *   147918 - Melhorias Login Meu Vivo Empresas V1.0
 *   [A0001] - Marcelo Nunes - Set/2015
 *
 */

#include <stdio.h>
#include <sys/time.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
double getRealTime(void);
void ElapsedTime(double StartTime, double EndTime, char * UsedTime);
void EmpresasGestor( DOMNode * dnode, XMLGen* xml_g );
void BuscaCNPJ( DOMNode * dnode, XMLGen* xml_g );
void BuscaEmpresaSistemaOrigem( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor_VOLE( DOMNode * dnode, XMLGen * xml_g );
void BuscaIE_Empresa( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJsVinculoIdGestor( DOMNode * dnode, XMLGen * xml_g );
int  BuscaCNPJIdGestor_GM( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor_GC( DOMNode * dnode, XMLGen * xml_g );
void AtualizaInPrimeiroAcesso( DOMNode * dnode, XMLGen * xml_g );

int obterParametro( char * cdParametro, char * dsValorParametro);
int ListaCNPJMasterProCNovo( char * idGestorPrm, char * pszNrPagina, char * qtdMaxLin, XMLGen * xml_g  );
int ListaCNPJMaster( DOMNode * dnode, XMLGen * xml_g );
int DetalheCNPJMaster( DOMNode * dnode, XMLGen * xml_g );
int ListaContas( DOMNode * dnode, XMLGen * xml_g  );

extern void getEmpresasByIdGestor( char * idPessoaGestorPrm, XMLGen* xml_g );
extern void getCNPJ( char * idPessoaPrm, XMLGen* xml_g );
extern void getEmpresaByAccountNumber( char * idSistemaOrigemPrm, XMLGen* xml_g );
extern void getCJNPJsByIdGestor( char * idPessoaGestorPrm, XMLGen* xml_g );   /*   MVE   */
extern void getCJNPJsByIdGestor_VOLE( char * idPessoaGestorPrm, XMLGen* xml_g );
extern void getInscricaoEstadualByEmpresa( char * idPessoaPrm, XMLGen* xml_g );
extern void getCNPJsVinculoIdGestor( char * idPessoaGestorPrm, XMLGen * xml_g );
extern int  getCJNPJsByIdGestor_GM( char * idPessoaGestorPrm, XMLGen* xml_g );   /*   MVE   */
extern void getCJNPJsByIdGestor_GC( char * idPessoaGestorPrm, char* nrDocumentoPrm, XMLGen * xml_g );  /*   MVE   */
extern void doAtualizaInPrimeiroAcesso( char * idPessoaGestorPrm, XMLGen * xml_g );

extern int ListaCNPJMasterProC( char * idGestorPrm, char * pszNrPagina, char * qtdMaxLin, XMLGen * xml_g  );
extern int DetalheCNPJMasterProC( char * nrCNPJ_Prm, char * pszNrPagina, char * qtdMaxLin, XMLGen * xml_g  );
extern int DetalheCNPJMasterProCRank( char * nrCNPJ_Prm, char * pszNrPagina, char * qtdMaxLin, XMLGen * xml_g  );
extern int DetalheCNPJMasterUnicoProC( char * nrCNPJ_Prm, XMLGen * xml_g  );
extern int ListaContasProC( char * idPessoaGestorPrm, 
                             char * nrDocumentoPrm, 
                             char * pszNrPagina, 
                             char * qtdMaxLin, 
                             XMLGen * xml_g );

extern int GestoresDeContaUnicoR( char * idPessoaGestorPrm, char * CNPJ, char * cdContaPrm, XMLGen * xml_g );
extern int ListaContaUnicoProC( char * idPessoaGestorPrm , char * nrDocumentoPrm , char * cdContaPrm , XMLGen * xml_g );
                             



DECLARE_TUXEDO_SERVICE( EMPRESAS );
void implEMPRESAS::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    double startTime, endTime;
    char UsedTime[16];
    memset(UsedTime, 0x0, sizeof(UsedTime));
    startTime = getRealTime();   // Inicia cronometro de Operacao
    int retorno = 0;
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : EmpresasGestor( dnode, xml_g );
                 break;
                             
        case 2 : BuscaCNPJ( dnode, xml_g  );
                 break;

        case 3 : BuscaEmpresaSistemaOrigem( dnode, xml_g  );
                 break;

        case 4 : BuscaCNPJIdGestor_VOLE( dnode, xml_g  );
                 break;

        case 5 : BuscaIE_Empresa( dnode, xml_g  );
                 break;

        case 6 : BuscaCNPJIdGestor( dnode, xml_g  );
                 break;

      case 10 : retorno = BuscaCNPJIdGestor_GM( dnode, xml_g  );
                break;

      case 11 : BuscaCNPJsVinculoIdGestor( dnode, xml_g  );
                break;

      case 12 : BuscaCNPJIdGestor_GC( dnode, xml_g  );
                break;

      case 16:  AtualizaInPrimeiroAcesso( dnode, xml_g  );
                break;

      case 20 : retorno = ListaCNPJMaster( dnode, xml_g  );   // [A0001] - Marcelo Nunes - Set/2015
                break;

      case 22 : retorno = DetalheCNPJMaster( dnode, xml_g  );   // [A0001] - Marcelo Nunes - Set/2015
                break;

      case 25 : retorno = ListaContas( dnode, xml_g  );   // [A0001] - Marcelo Nunes - Set/2015
    	  break;
    }
    
    ULOG( "Retorno consulta [%d]", retorno );
    
    endTime = getRealTime();   // Finaliza Cronometro de Operacao
    ElapsedTime(startTime, endTime, UsedTime);   // Retorna HH:MM:SS&&& Vai Executar [.FFFF em UsedTime - Tempo Gasto 
    ULOG( "### Tempo Total de execucao: %s", UsedTime );
    
    if ( retorno == -11 )
       setStatusCode( "11W0011", "Empresa não encontrada" );
    else if ( retorno == -1 || retorno == -5 )
       setStatusCode( "11W0011", "Gestor Master não tem inAssociacao = 1" );
    else
       setStatusCode( "11I0000", "Sucesso" );
       
}


void AtualizaInPrimeiroAcesso( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );

    doAtualizaInPrimeiroAcesso( gestorIdPrm, xml_g );
}

void EmpresasGestor( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getEmpresasByIdGestor( gestorIdPrm, xml_g );
}



void BuscaCNPJ( DOMNode * dnode, XMLGen* xml_g  )
{
    CTuxHelperClever helper;

    char * idPessoaPrm = helper.walkTree( dnode, "idPessoa", 0 );
    
    getCNPJ( idPessoaPrm, xml_g );
}



void BuscaEmpresaSistemaOrigem( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idContaSistemaOrigem", 0 );
    getEmpresaByAccountNumber( gestorIdPrm, xml_g );
}



void BuscaCNPJIdGestor_VOLE( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getCJNPJsByIdGestor_VOLE( gestorIdPrm, xml_g );
}



void BuscaCNPJIdGestor( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getCJNPJsByIdGestor( gestorIdPrm, xml_g );
}



void BuscaIE_Empresa( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * idPessoaPrm = helper.walkTree( dnode, "idPessoa", 0 );
    getInscricaoEstadualByEmpresa( idPessoaPrm, xml_g );
}



int BuscaCNPJIdGestor_GM( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    return getCJNPJsByIdGestor_GM( gestorIdPrm, xml_g );
}



void BuscaCNPJsVinculoIdGestor( DOMNode * dnode, XMLGen * xml_g )
{
   CTuxHelperClever helper;

   char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
   getCNPJsVinculoIdGestor( gestorIdPrm, xml_g );
}



void BuscaCNPJIdGestor_GC( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * nrDocumentoPrm = helper.walkTree( dnode, "nrDocumento", 0 );
    
    getCJNPJsByIdGestor_GC( gestorIdPrm, nrDocumentoPrm, xml_g );
}



/*
 *
 *   147918 - Melhorias Login Meu Vivo Empresas V1.0
 *   [A0001] - Marcelo Nunes - Set/2015
 *
 */
int ListaCNPJMaster( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * idGestor = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * pszNrPagina = helper.walkTree( dnode, "NrPagina", 0 );
    char * qtdMaxLin = helper.walkTree( dnode, "NrLinhas", 0 );
	char dsValorParametro[256];
	memset(&dsValorParametro,0,sizeof(dsValorParametro));
	obterParametro("ListaCNPJMasterProC",dsValorParametro);
	
	if (!strcmp(dsValorParametro,"1")) {
		return ListaCNPJMasterProCNovo( idGestor, pszNrPagina, qtdMaxLin, xml_g ); 
	} else {
		return ListaCNPJMasterProC( idGestor, pszNrPagina, qtdMaxLin, xml_g ); 
	}
    
    
}



/*
 *
 *   147918 - Melhorias Login Meu Vivo Empresas V1.0
 *   [A0001] - Marcelo Nunes - Set/2015
 *
 */
int DetalheCNPJMaster( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    int retorno;
    char * nrCNPJ_Prm = helper.walkTree( dnode, "cnpj", 0 );
    char * inUnico = helper.walkTree( dnode, "inUnico", 0 );
    char * pszNrPagina = helper.walkTree( dnode, "NrPagina", 0 );
    char * qtdMaxLin = helper.walkTree( dnode, "NrLinhas", 0 );

	char dsValorParametro[256];
	memset(&dsValorParametro,0,sizeof(dsValorParametro));
	obterParametro("DetalheCNPJMaster",dsValorParametro);	
    
    if ( inUnico[0] == '1' )
       retorno = DetalheCNPJMasterUnicoProC( nrCNPJ_Prm, xml_g  );
    else {
		if (!strcmp(dsValorParametro,"1")) {
			retorno = DetalheCNPJMasterProCRank( nrCNPJ_Prm, pszNrPagina, qtdMaxLin, xml_g   );			
		} else {
       retorno = DetalheCNPJMasterProC( nrCNPJ_Prm, pszNrPagina, qtdMaxLin, xml_g   );
		}	       
	}
   
    ULOG( "*** Retorno do Detalhe CNPJ Master [%d]", retorno );
    return retorno;

}



/*
 *
 *   147918 - Melhorias Login Meu Vivo Empresas V1.0
 *   [A0001] - Marcelo Nunes - Set/2015
 *
 */
int ListaContas( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;
    char * pszNrPagina = helper.walkTree( dnode, "NrPagina", 0 );
    char * qtdMaxLin = helper.walkTree( dnode, "NrLinhas", 0 );
    char * idGestor = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * nrCNPJ_Prm = helper.walkTree( dnode, "cnpj", 0 );
    char * cdConta_Prm = helper.walkTree( dnode, "cdConta", 0 );
    
    if ( cdConta_Prm[0] != 0x0 )
       return ListaContaUnicoProC( idGestor, nrCNPJ_Prm, cdConta_Prm, xml_g );
    else
       return ListaContasProC( idGestor, nrCNPJ_Prm, pszNrPagina, qtdMaxLin, xml_g );

}



/**
 * Returns the real time, in seconds, or -1.0 if an error occurred.
 *
 * Time is measured since an arbitrary and OS-dependent start time.
 * The returned real time is only useful for computing an elapsed time
 * between two calls to this function.
 */
double getRealTime(void)
{
    /* AIX, BSD, Cygwin, HP-UX, Linux, OSX, POSIX, Solaris. ----- */
    struct timeval tm;
    gettimeofday( &tm, NULL );
    return (double)tm.tv_sec + (double)tm.tv_usec / 1000000.0;
}



/*
 *   Retorna Tempo Usado
 *   StartTime - Tempo Inicial
 *   EndTime - Tempo Final
 *   UsedTime - Tempo Gasto
 */
void ElapsedTime(double StartTime, double EndTime, char * UsedTime)
{
    int hora, minuto, segundo;

    char Tempo[16];
    char Segundos[8];
    char Miliseg[4];
    char * Result = NULL;

    sprintf ( Tempo, "%lf.", (EndTime - StartTime) );
    Result = strtok(Tempo,".");
    sprintf(Segundos,"%s", Result);
    //printf("Segundos [%s]\n",Segundos);
    Result = strtok(NULL,".");
    sprintf(Miliseg, "%.4s", Result);
    //printf("Miliseg [%s]\n",Miliseg);

    hora = (atoi(Segundos)/3600);
    minuto = (atoi(Segundos)-(3600*hora))/60;
    segundo = (atoi(Segundos)-(3600*hora)-(minuto*60));
    sprintf( UsedTime, "%02d:%02d:%02d.%s", hora, minuto, segundo, Miliseg );

}
