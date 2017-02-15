
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void EmpresasGestor( DOMNode * dnode, XMLGen* xml_g );
void BuscaCNPJ( DOMNode * dnode, XMLGen* xml_g );
void BuscaEmpresaSistemaOrigem( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor_VOLE( DOMNode * dnode, XMLGen * xml_g );
void BuscaIE_Empresa( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJsVinculoIdGestor( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor_GM( DOMNode * dnode, XMLGen * xml_g );
void BuscaCNPJIdGestor_GC( DOMNode * dnode, XMLGen * xml_g );

extern void getEmpresasByIdGestor( char * idPessoaGestorPrm, XMLGen* xml_g );
extern void getCNPJ( char * idPessoaPrm, XMLGen* xml_g );
extern void getEmpresaByAccountNumber( char * idSistemaOrigemPrm, XMLGen* xml_g );
extern void getCJNPJsByIdGestor( char * idPessoaGestorPrm, XMLGen* xml_g );   /*   MVE   */
extern void getCJNPJsByIdGestor_VOLE( char * idPessoaGestorPrm, XMLGen* xml_g );
extern void getInscricaoEstadualByEmpresa( char * idPessoaPrm, XMLGen* xml_g );
extern void getCNPJsVinculoIdGestor( char * idPessoaGestorPrm, XMLGen * xml_g );
extern void getCJNPJsByIdGestor_GM( char * idPessoaGestorPrm, XMLGen* xml_g );   /*   MVE   */
extern void getCJNPJsByIdGestor_GC( char * idPessoaGestorPrm, char* nrDocumentoPrm, XMLGen * xml_g );  /*   MVE   */


DECLARE_TUXEDO_SERVICE( EMPRESAS );
void implEMPRESAS::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
   CTuxHelperClever helper;
   char* p0 = NULL;


   p0 = helper.walkTree( dnode, "cdOperacao", 0 );
   int opcao = atoi( p0 );
   ULOG( "Solicitou opcao [%d]", opcao );
   switch( opcao )
   {
      case  1 : EmpresasGestor( dnode, xml_g );
                break;
                          
      case  2 : BuscaCNPJ( dnode, xml_g  );
                break;

      case  3 : BuscaEmpresaSistemaOrigem( dnode, xml_g  );
                break;

      case  4 : BuscaCNPJIdGestor_VOLE( dnode, xml_g  );
                break;

      case  5 : BuscaIE_Empresa( dnode, xml_g  );
                break;

      case  6 : BuscaCNPJIdGestor( dnode, xml_g  );
                break;

      case 10 : BuscaCNPJIdGestor_GM( dnode, xml_g  );
                break;

      case 11 : BuscaCNPJsVinculoIdGestor( dnode, xml_g  );
                break;

      case 12 : BuscaCNPJIdGestor_GC( dnode, xml_g  );
                break;

   }
    
    setStatusCode("11I0000","Sucesso");
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



void BuscaCNPJIdGestor_GM( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getCJNPJsByIdGestor_GM( gestorIdPrm, xml_g );
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
