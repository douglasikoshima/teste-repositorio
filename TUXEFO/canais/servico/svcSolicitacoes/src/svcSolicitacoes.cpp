
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void SolicitaNroProcesso( DOMNode * dnode, XMLGen* xml_g );
void SolicitaProcessoFaixa( DOMNode * dnode, XMLGen* xml_g );
void SolicitaProcessoConta( DOMNode * dnode, XMLGen * xml_g );
void SolicitaStatusProcesso( DOMNode * dnode, XMLGen * xml_g );
void SolicitaCampoProcesso( DOMNode * dnode, XMLGen * xml_g );

extern void getByNumeroProcesso( char * cdContaPrm, char * numeroProcessoPrm, XMLGen * xml_g );
extern void getByDateRange( char * cdContaPrm, char * startDatePrm, char * endDatePrm, XMLGen * xml_g );
extern void getByConta( char * cdContaPrm, XMLGen * xml_g );
extern void getProcessoStatusByNumeroProcesso( char * nrProcessoPrm, XMLGen * xml_g );
extern void getProcessoCamposByNumeroProcesso( char * nrProcessoPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( SOLICITACOES );
void implSOLICITACOES::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : SolicitaNroProcesso( dnode, xml_g );
                 break;
                             
        case 2 : SolicitaProcessoFaixa( dnode, xml_g );
                 break;

        case 3 : SolicitaProcessoConta( dnode, xml_g );
                 break;
                             
        case 4 : SolicitaStatusProcesso( dnode, xml_g );
                 break;

        case 5 : SolicitaCampoProcesso( dnode, xml_g );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}


void SolicitaNroProcesso( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm        = helper.walkTree( dnode, "cdConta", 0 );
    char * numeroProcessoPrm = helper.walkTree( dnode, "nrProcesso", 0 );
    
    getByNumeroProcesso( cdContaPrm, numeroProcessoPrm, xml_g );
}



void SolicitaProcessoFaixa( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm    = helper.walkTree( dnode, "cdConta", 0 );
    char * startDatePrm = helper.walkTree( dnode, "DataInicial", 0 );
    char * endDatePrm = helper.walkTree( dnode, "DataFinal", 0 );
    
    getByDateRange( cdContaPrm, startDatePrm, endDatePrm, xml_g );
}



void SolicitaProcessoConta( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    
    getByConta( cdContaPrm, xml_g );
}



void SolicitaStatusProcesso( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * numeroProcessoPrm = helper.walkTree( dnode, "nrProcesso", 0 );
    
    getProcessoStatusByNumeroProcesso( numeroProcessoPrm, xml_g );
}



void SolicitaCampoProcesso( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * numeroProcessoPrm = helper.walkTree( dnode, "nrProcesso", 0 );
    
    getProcessoCamposByNumeroProcesso( numeroProcessoPrm, xml_g );
}
