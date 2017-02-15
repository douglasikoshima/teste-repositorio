#define PSSRELACAOHUPCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CPss.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"
#include "../../../negocio/admatdCmm/include/COrg.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(PSSRELACAOHUP);

void implPSSRELACAOHUP::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   
   ULOG_START("implPSSRELACAOHUP::Execute()");
   CSafePointer oSafePointer;
   CNivel oNivel;
   COrganizacao oOrganizacao;
   CDepartamento oDepartamento;
   CCargo oCargo;
   
   int iRet;
   
   char* cidPessoa = oSafePointer.getTag(dnode,"idPessoa",0);
   //if( strlennull( cidPessoa ) <= 0 )
   //{
   //	setStatusCode("14E0000","idPessoa esta nulo");
   //	return;
   //}
   
   xml_g->createTag("NivelCargoOrgVO");
   //Adiciona a propriedade necessaria para o xml
   xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
   //Adiciona as tags necessarias
   xml_g->addItem("idPessoa", cidPessoa );
   
    if ( cidPessoa && *cidPessoa ){
   
    	CPessoa oPessoa(cidPessoa);
        oPessoa.GetXmlId("dadosAtuais", xml_g);
        //Recupera os dados 
        if( oCargo.ListCargoPorIdNivel(oPessoa.getNivel()) < 0 )
       {
           setStatusCode("14E0001","Falha na listagem de cargos por nível");
           ULOG_END("implPSSRELACAOHUP::Execute()");
           return;
       }
        if( oDepartamento.ListDeptoPorIdOrganizacao(oPessoa.getOrganizacao()) < 0 )
       {
           setStatusCode("14E0002","Falha na listagem de departamento por organização");
           ULOG_END("implPSSRELACAOHUP::Execute()");
           return;
       }
    }
   
   
    /* Buscando os dados comuns */
    if( oNivel.ListarTodosNivelOrganograma() < 0 )
    {
        setStatusCode("14E0003","Falha na listagem de níveis");
        ULOG_END("implPSSRELACAOHUP::Execute()");
        return;
    }
    if( oOrganizacao.ListarTodosOrganizacaoHierarquia() < 0 )
    {
        setStatusCode("14E0004","Falha na listagem de organização");
        ULOG_END("implPSSRELACAOHUP::Execute()");
        return;
    }
   
    //Monta o XML de retorno
    oNivel.GetXml("ListaNivelOrganogramaVO", "NivelOrganogramaSimplVO", xml_g );
   
    oCargo.GetXml("ListaCargoSimplVO", "CargoSimplVO",xml_g); 
   
    oOrganizacao.GetXml( "ListaOrganizacaoVO", "OrganizacaoSimplVO", xml_g );
   
    oDepartamento.GetXml("ListaUnidadeOrganogramaVO", "UnidadeOrganogramaVO",xml_g); 
   
    xml_g->closeTag();
   
    setStatusCode("14I0000","Sucesso na execução do método RelacaoHUP da Classe CPessoa"); 
    
    ULOG_END("implPSSRELACAOHUP::Execute()");
    
   return;
}
