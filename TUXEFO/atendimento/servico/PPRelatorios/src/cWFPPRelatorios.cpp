/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Rotinas comuns para os pré-processamentos de relatórios
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/

#include "../include/cWFPPRelatorios.h"
#include "../../../commons/msgPadrao.h"

extern void proCObterDadosComuns(long _idAtendimento,struct DadosComuns *dadosComuns);
extern int proCObterIdGrupoAtual(long _idAtendimento);

extern void proCAtualizarProdRepresentante(st_PPRelatorios* _dados, st_vlPPRelatorios* _status);
extern void proCAtualizarQtMotRepreBKO(st_PPRelatorios *dados, st_vlPPRelatorios *status);

cWFPPRelatorios::cWFPPRelatorios()
{
    entrada=0;
    saida=0;
    mDados = 0;
    mStatus = 0;

    alocado = false;
}

cWFPPRelatorios::cWFPPRelatorios(st_PPRelatorios* dados,st_vlPPRelatorios* status
                                ,XMLGen*xml_g)
{
    mDados = dados;
    mStatus = status;

    entrada = 0;
    saida   = xml_g;
}

cWFPPRelatorios::cWFPPRelatorios(st_PPRelatorios* dados,st_vlPPRelatorios* status)
{
    mDados = dados;
    mStatus = status;

    entrada = 0;
    saida   = 0;
}

cWFPPRelatorios::cWFPPRelatorios(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    mDados = 0;
    mStatus = 0;

    alocado = false;

    carregaDados();
}

cWFPPRelatorios::~cWFPPRelatorios()
{
    if ( alocado )
    {
        delete mDados;
        delete mStatus;
    }
}

bool cWFPPRelatorios::atualizar()
{
    bool retorno = true;

    try
    {
        if ( mDados->idPessoaUsuario > 0 )
        {
            // Dados comuns compartilhados pelos relatórios relativos ao processo
            // que estiver sofrendo a ação no momento

            DadosComuns dadosComuns;

            proCObterDadosComuns(mDados->idAtendimento,&dadosComuns);

            setarIdUFOperadora(dadosComuns.idUFOperadora);
            setarIdGrupoOperadora(dadosComuns.idGrupoOperadora);
            setarDtAbertura(dadosComuns.dtAbertura);
            setarCdAreaRegistro(dadosComuns.cdAreaRegistro);

            if ( obterIdGrupoAtual() == -1 ) setarIdGrupoAtual(proCObterIdGrupoAtual(mDados->idAtendimento));

            proCAtualizarProdRepresentante(mDados,mStatus);

            // Quantidade e Motivos por Representante BKO - (REL4)
            ULOG("> REL4-Quantidade e Motivos Por Representante BKO");
            if ( obterIdFase() == RETORNO )
            {
                if ( obterIdMotivo() > 0 )
                {
                    proCAtualizarQtMotRepreBKO(mDados,mStatus);
                }
                else
                {
                    ULOG("idMotivo não informado.");
                }
            }
            else
            {
                ULOG("Somente processos em RETORNO fazem parte do relatório 4.");
            }
            ULOG("< REL4-Quantidade e Motivos Por Representante BKO");

        }
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codigoErro[25];
        sprintf(codigoErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        codErro = codigoErro;
        msgErro = ex->pMsg;

        delete ex;

        retorno = false;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        codErro = ex->getCode();
        msgErro = ex->getMessage();

        delete ex;

        retorno = false;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        codErro = "04E0097";
        msgErro = errMsg;

        retorno = false;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        codErro = "04E0098";
        msgErro = errMsg;

        retorno = false;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        codErro = "04E0099";
        msgErro = "erro desconhecido";

        retorno = false;
    }

    return retorno;
}

bool cWFPPRelatorios::setarValoresRelProdRepresentante(int idAtividade,int idFase)
{
    switch ( idAtividade )
    {
        case FECHAR_F:
        case FECHAR_DR:
        // case ENCERRAR_BKO_MASSA_EBM:
            setarQtFechados(1);
        return true;

        case ENCERRAR_BKO_EB:
        case ENCERRAR_BKO_EBR:
        case ENCERRAR_BKO_EBS:
            setarQtEncerrados(1);
        return true;

        case AGENDAR_AG:
        case AGENDAR_AR:
            if ( idFase == TRATAMENTO )
            {
                setarQtTratamento(1);
            }
            else if ( idFase == RETORNO )
            {
                setarQtRetorno(1);
            }
        return true;

        default:
            ULOGE("Atividade %d não contabilizada!",idAtividade);
        return false;
    }
}

void cWFPPRelatorios::carregaDados()
{
    if ( entrada )
    {
        mDados = new st_PPRelatorios;
        mStatus = new st_vlPPRelatorios;

        alocado = true;

        char* p;

        if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p)
        {
            idAtendimento = atol(p);
            XMLString::release(&p);
        }
        else
        {
            idAtendimento = -1;
        }

        if (p = tx.walkTree( entrada, "qtFechados", 0 ), p)
        {
            mDados->qtFechados = atoi(p);
            mStatus->qtFechados = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "qtRetorno", 0 ), p)
        {
            mDados->qtRetorno = atoi(p);
            mStatus->qtRetorno = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "qtTratamento", 0 ), p)
        {
            mDados->qtTratamento = atoi(p);
            mStatus->qtTratamento = 1;
            XMLString::release(&p);
        }

        if (p = tx.walkTree( entrada, "qtEncerrados", 0 ), p)
        {
            mDados->qtEncerrados = atoi(p);
            mStatus->qtEncerrados = 1;
            XMLString::release(&p);
        }
    }
}
