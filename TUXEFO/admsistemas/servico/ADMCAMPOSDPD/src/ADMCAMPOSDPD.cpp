/**
 * @modulo  Admsistemas
 * @usecase Admsistemas
 * @author  Max
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:29 $
 **/

#ifdef WIN32
#include "../../../negocio/admatdCmm/include/CAdmCamposDepend.h"
#else
#include "../../negocio/admatdCmm/include/CAdmCamposDepend.h"
#endif

DECLARE_TUXEDO_SERVICE( ADMCAMPOSDPD );

void implADMCAMPOSDPD::Execute( DOMNode* dnode ,XMLGen* xml_g )
{
    ULOG_START( "implADMCAMPOSDPD::Execute()" );
    
    try
    {
        char* usuario = getUser();
        CAdmCamposDepend oCamposDepend( usuario, dnode , xml_g );

        switch ( oCamposDepend.getIOperacao() )
        {
            case OPERACAO_GETCAMPOSDEPENDENTES:
                oCamposDepend.ListaGrupos();
            break;

            case OPERACAO_SETCAMPOSVALORES    :
                oCamposDepend.InsereValor();
                // 'starta' o batch no banco que faz a atualização dos campos
                oCamposDepend.AtualizarCampos();
            break;

            case OPERACAO_GETCAMPOSVALORES    :
                oCamposDepend.CamposValores();
            break;

            case OPERACAO_GETTREE             :
                oCamposDepend.ListaArvore();
            break;

            case OPERACAO_GETCAMPOSNIVEL      :
                oCamposDepend.ListaCamposNiveis();
            break;

            case OPERACAO_PROXIMONIVEL        :
                oCamposDepend.ListaProximoNivel();
            break;

            case OPERACAO_SETTREE             :
                oCamposDepend.GravarArvore();
                // 'starta' o batch no banco que faz a atualização dos campos
                oCamposDepend.AtualizarCampos();
            break;

            case OPERACAO_LUPAGETCAMPODPD        :
                oCamposDepend.LupaGetCampo();
            break;

            default:
                throw new TuxException("14E9999","Operação inválida");
            break;
        }

        setStatusCode("14I0000","Sucesso no processamento"); 
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"14E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        setStatusCode(codErro,ex->pMsg);

        delete ex;
    }
    catch(TuxException *pTux)
    {
        char *pCode = pTux->getCode();
        if ( !pCode ) pCode = "14E9999";

        char *pMessage = pTux->getMessage();
        if ( !pMessage ) pMessage = "Erro não identificado.";

        setStatusCode(pCode,pMessage);

        delete pTux;
    }
    catch( char  * MsgErro )
    {
        setStatusCode("14E0099",MsgErro);
    }
    catch (...) 
    {
        setStatusCode("14E9999","Execução com erro"); 
    }

    ULOG_END("implADMCAMPOSDPD::Execute()");
}
