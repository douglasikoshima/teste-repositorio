/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFCWFATENDIMENTOGERARXMLDPR
    #define STWFCWFATENDIMENTOGERARXMLDPR

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
//#include <vector>

using namespace std;

#include <tuxfw.h>
#include "stWFDefines.h"
#include "stWFDPRXMLException.h"
#include "stWFEstruturasXMLDPR.h"

//==============================================================================
// Implementação

//
//==============================================================================

//
// Não mudar a ordem em que as tags são geradas pois pode gerar problemas para
// o processo Tibco que consome estes VOs.
//
struct AtendimentoContatoVO
{
    XMLGen *xmlvo;

    AtendimentoContatoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoContatoDPR *atendimentocontatodpr)
    {
        if ( !atendimentocontatodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentocontatodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOCONTATOVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentocontatodpr->getIdAtendimento());
                xmlvo->addItem(NMCONTATO,atendimentocontatodpr->getNmContato());
                xmlvo->addItem(OPCODE,atendimentocontatodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoObservacaoVO
{
    XMLGen *xmlvo;

    AtendimentoObservacaoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoObservacaoDPR *atendimentoobservacaodpr)
    {
        if ( !atendimentoobservacaodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentoobservacaodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOOBSERVACAOVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentoobservacaodpr->getIdAtendimento());
                xmlvo->addItem(DSOBSERVACAO,atendimentoobservacaodpr->getDsObservacao());
                xmlvo->addItem(OPCODE,atendimentoobservacaodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoLinhaVO
{
    XMLGen *xmlvo;

    AtendimentoLinhaVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoLinhaDPR *atendimentolinhadpr)
    {
        if ( !atendimentolinhadpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentolinhadpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOLINHAVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentolinhadpr->getIdAtendimento());
                xmlvo->addItem(IDPESSOALINHAHISTORICO,atendimentolinhadpr->getIdPessoaLinhaHistorico());
                xmlvo->addItem(IDESTADOLINHA,atendimentolinhadpr->getIdEstadoLinha());
                xmlvo->addItem(OPCODE,atendimentolinhadpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoOrigemVO
{
    XMLGen *xmlvo;

    AtendimentoOrigemVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoOrigemDPR *atendimentoorigemdpr)
    {
        if ( !atendimentoorigemdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentoorigemdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOORIGEMVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentoorigemdpr->getIdAtendimento());
                xmlvo->addItem(IDATENDIMENTOORIGEM,atendimentoorigemdpr->getIdAtendimentoOrigem());
                xmlvo->addItem(OPCODE,atendimentoorigemdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoHierarquiaVO
{
    XMLGen *xmlvo;

    AtendimentoHierarquiaVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoHierarquiaDPR *atendimentohierarquiadpr)
    {
        if ( !atendimentohierarquiadpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentohierarquiadpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOHIERARQUIAVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentohierarquiadpr->getIdAtendimento());
                xmlvo->addItem(IDATENDIMENTOPAI,atendimentohierarquiadpr->getIdAtendimentoPai());
                xmlvo->addItem(OPCODE,atendimentohierarquiadpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoVO
{
    XMLGen *xmlvo;

    AtendimentoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoDPR *atendimentodpr)
    {
        if ( !atendimentodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentodpr->getIdAtendimento());
                xmlvo->addItem(DTABERTURA,atendimentodpr->getDtAbertura());
                xmlvo->addItem(DTPRAZOFINALINTERNO,atendimentodpr->getDtPrazoFinalInterno());
                xmlvo->addItem(VLPESOATENDIMENTO,atendimentodpr->getVlPesoAtendimento());
                xmlvo->addItem(IDCONTATO,atendimentodpr->getIdContato());
                xmlvo->addItem(IDCANAL,atendimentodpr->getIdCanal());
                xmlvo->addItem(IDPROCEDENCIA,atendimentodpr->getIdProcedencia());
                xmlvo->addItem(IDTIPOCARTEIRA,atendimentodpr->getIdTipoCarteira());
                xmlvo->addItem(IDSEGMENTACAO,atendimentodpr->getIdSegmentacao());
                xmlvo->addItem(QTINSISTENCIA,atendimentodpr->getQtInsistencia());
                xmlvo->addItem(OPCODE,atendimentodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoPessoaVO
{
    XMLGen *xmlvo;

    AtendimentoPessoaVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoPessoaDPR *atendimentopessoadpr)
    {
        if ( !atendimentopessoadpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentopessoadpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOPESSOAVO);
                xmlvo->addItem(IDATENDIMENTOPESSOA,atendimentopessoadpr->getIdAtendimentoPessoa());
                xmlvo->addItem(IDATENDIMENTO,atendimentopessoadpr->getIdAtendimento());
                xmlvo->addItem(IDPESSOADEPARA,atendimentopessoadpr->getIdPessoaDePara());
                xmlvo->addItem(IDTIPORELACIONAMENTO,atendimentopessoadpr->getIdTipoRelacionamento());
                xmlvo->addItem(OPCODE,atendimentopessoadpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoContatoComunicVO
{
    XMLGen *xmlvo;

    AtendimentoContatoComunicVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoContatoComunicDPR *atendimentocontatocomunicdpr)
    {
        if ( !atendimentocontatocomunicdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentocontatocomunicdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOCONTATOCOMUNICVO);
                xmlvo->addItem(IDATENDIMENTOCONTATOCOMUNIC,atendimentocontatocomunicdpr->getIdAtendimentoContatoComunic());
                xmlvo->addItem(IDATENDIMENTO,atendimentocontatocomunicdpr->getIdAtendimento());
                xmlvo->addItem(IDTIPOCOMUNICACAO,atendimentocontatocomunicdpr->getIdTipoComunicacao());
                xmlvo->addItem(DSCOMUNICACAO,atendimentocontatocomunicdpr->getDsComunicacao());
                xmlvo->addItem(NRORDEMUTILIZACAO,atendimentocontatocomunicdpr->getNrOrdemutilizacao());
                xmlvo->addItem(OPCODE,atendimentocontatocomunicdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoFRMVO
{
    XMLGen *xmlvo;

    AtendimentoFRMVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoFRMDPR *atendimentofrmdpr)
    {
        if ( !atendimentofrmdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentofrmdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOFRMVO);
                xmlvo->addItem(IDATENDIMENTOFRM,atendimentofrmdpr->getIdAtendimentoFrm());
                xmlvo->addItem(IDATENDIMENTO,atendimentofrmdpr->getIdAtendimento());
                xmlvo->addItem(IDCAMPO,atendimentofrmdpr->getIdCampo());
                xmlvo->addItem(OPCODE,atendimentofrmdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoFRMCampoVO
{
    XMLGen *xmlvo;

    AtendimentoFRMCampoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoFRMCampoDPR *atendimentofrmcampodpr)
    {
        if ( !atendimentofrmcampodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentofrmcampodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOFRMCAMPOVO);
                xmlvo->addItem(IDATENDIMENTOFRMCAMPO,atendimentofrmcampodpr->getIdAtendimentoFrmCampo());
                xmlvo->addItem(IDATENDIMENTOFRM,atendimentofrmcampodpr->getIdAtendimentoFrm());
                xmlvo->addItem(IDATENDIMENTO,atendimentofrmcampodpr->getIdAtendimento());
                xmlvo->addItem(IDCAMPO,atendimentofrmcampodpr->getIdCampo());
                xmlvo->addItem(IDDOMINIO,atendimentofrmcampodpr->getIdDominio());
                xmlvo->addItem(DSVALOR,atendimentofrmcampodpr->getDsValor());
                xmlvo->addItem(OPCODE,atendimentofrmcampodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoBaixaHistoricoVO
{
    XMLGen *xmlvo;

    AtendimentoBaixaHistoricoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoBaixaHistoricoDPR *atendimentobaixahistoricodpr)
    {
        if ( !atendimentobaixahistoricodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentobaixahistoricodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOBAIXAHISTORICOVO);
                xmlvo->addItem(IDATENDIMENTOBAIXAHISTORICO,atendimentobaixahistoricodpr->getIdAtendimentoBaixaHistorico());
                xmlvo->addItem(IDATENDIMENTO,atendimentobaixahistoricodpr->getIdAtendimento());
                xmlvo->addItem(IDBAIXA,atendimentobaixahistoricodpr->getIdBaixa());
                xmlvo->addItem(DTBAIXA,atendimentobaixahistoricodpr->getDtBaixa());
                xmlvo->addItem(OPCODE,atendimentobaixahistoricodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoFechamentoVO
{
    XMLGen *xmlvo;

    AtendimentoFechamentoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoFechamentoDPR *atendimentofechamentodpr)
    {
        if ( !atendimentofechamentodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentofechamentodpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOFECHAMENTOVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentofechamentodpr->getIdAtendimento());
                xmlvo->addItem(IDANDAMENTO,atendimentofechamentodpr->getIdAndamento());
                xmlvo->addItem(DTFECHAMENTO,atendimentofechamentodpr->getDtFechamento());
                xmlvo->addItem(IDPESSOAUSUARIO,atendimentofechamentodpr->getIdPessoaUsuario());
                xmlvo->addItem(OPCODE,atendimentofechamentodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoGrupoAtualVO
{
    XMLGen *xmlvo;

    AtendimentoGrupoAtualVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoGrupoAtualDPR *atendimentogrupoatualdpr)
    {
        if ( !atendimentogrupoatualdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentogrupoatualdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOGRUPOATUALVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentogrupoatualdpr->getIdAtendimento());
                xmlvo->addItem(IDGRUPO,atendimentogrupoatualdpr->getIdGrupo());
                xmlvo->addItem(OPCODE,atendimentogrupoatualdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoUsuarioAtualVO
{
    XMLGen *xmlvo;

    AtendimentoUsuarioAtualVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoUsuarioAtualDPR *atendimentousuarioatualdpr)
    {
        if ( !atendimentousuarioatualdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentousuarioatualdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOUSUARIOATUALVO);
                xmlvo->addItem(IDATENDIMENTO,atendimentousuarioatualdpr->getIdAtendimento());
                xmlvo->addItem(IDPESSOAUSUARIO,atendimentousuarioatualdpr->getIdPessoaUsuario());
                xmlvo->addItem(OPCODE,atendimentousuarioatualdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoTesteVO
{
    XMLGen *xmlvo;

    AtendimentoTesteVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoTesteDPR *atendimentotestedpr)
    {
        if ( !atendimentotestedpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentotestedpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOTESTEVO);
                xmlvo->addItem(IDATENDIMENTOTESTE,atendimentotestedpr->getIdAtendimentoTeste());
                xmlvo->addItem(IDATENDIMENTO,atendimentotestedpr->getIdAtendimento());
                xmlvo->addItem(DTTESTE,atendimentotestedpr->getDtTeste());
                xmlvo->addItem(IDPESSOAUSUARIO,atendimentotestedpr->getIdPessoaUsuario());
                xmlvo->addItem(OPCODE,atendimentotestedpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AtendimentoMensagemVO
{
    XMLGen *xmlvo;

    AtendimentoMensagemVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AtendimentoMensagemDPR *atendimentomensagemdpr)
    {
        if ( !atendimentomensagemdpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == atendimentomensagemdpr->isEmpty() )
        {
            xmlvo->createTag(ATENDIMENTOMENSAGEMVO);
                xmlvo->addItem(IDATENDIMENTOMENSAGEM,atendimentomensagemdpr->getIdAtendimentoMensagem());
                xmlvo->addItem(IDATENDIMENTO,atendimentomensagemdpr->getIdAtendimento());
                xmlvo->addItem(IDMENSAGEMATENDIMENTO,atendimentomensagemdpr->getIdMensagemAtendimento());
                xmlvo->addItem(DTMENSAGEM,atendimentomensagemdpr->getDtMensagem());
                xmlvo->addItem(OPCODE,atendimentomensagemdpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AndamentoVO
{
    XMLGen *xmlvo;

    AndamentoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AndamentoDPR *andamentodpr)
    {
        if ( !andamentodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == andamentodpr->isEmpty() )
        {
            xmlvo->createTag(ANDAMENTOVO);
                xmlvo->addItem(IDANDAMENTO,andamentodpr->getIdAndamento());
                xmlvo->addItem(IDATENDIMENTO,andamentodpr->getIdAtendimento());
                xmlvo->addItem(IDPESSOAUSUARIO,andamentodpr->getIdPessoaUsuario());
                xmlvo->addItem(DTANDAMENTO,andamentodpr->getDtAndamento());
                xmlvo->addItem(IDATIVIDADE,andamentodpr->getIdAtividade());
                xmlvo->addItem(IDAGRUPAMENTOESTADOTPPROC,andamentodpr->getIdAgrupamentoEstadoTpProc());
                xmlvo->addItem(IDGRUPO,andamentodpr->getIdGrupo());
                xmlvo->addItem(OPCODE,andamentodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct AndamentoObservacaoVO
{
    XMLGen *xmlvo;

    AndamentoObservacaoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(AndamentoObservacaoDPR *andamentoobservacaodpr)
    {
        if ( !andamentoobservacaodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == andamentoobservacaodpr->isEmpty() )
        {
            xmlvo->createTag(ANDAMENTOOBSERVACAOVO);
                xmlvo->addItem(IDANDAMENTO,andamentoobservacaodpr->getIdAndamento());
                xmlvo->addItem(DSANDAMENTOOBSERVACAO,andamentoobservacaodpr->getDsAndamentoObservacao());
                xmlvo->addItem(OPCODE,andamentoobservacaodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct PessoaVO
{
    XMLGen *xmlvo;

    PessoaVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(PessoaDPR *pessoadpr)
    {
        if ( !pessoadpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == pessoadpr->isEmpty() )
        {
            xmlvo->createTag(PESSOAVO);
                xmlvo->addItem(IDPESSOADEPARA,pessoadpr->getIdPessoaDePara());
                xmlvo->addItem(IDPESSOA,pessoadpr->getIdPessoa());
                xmlvo->addItem(NMPESSOA,pessoadpr->getNmPessoa());
                xmlvo->addItem(IDTIPOPESSOA,pessoadpr->getIdTipoPessoa());
                xmlvo->addItem(OPCODE,pessoadpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct PessoaDeParaVO
{
    XMLGen *xmlvo;

    PessoaDeParaVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(PessoaDeParaDPR *pessoadeparadpr)
    {
        if ( !pessoadeparadpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == pessoadeparadpr->isEmpty() )
        {
            xmlvo->createTag(PESSOADEPARAVO);
                xmlvo->addItem(IDPESSOA,pessoadeparadpr->getIdPessoa());
                xmlvo->addItem(IDPESSOADEPARA,pessoadeparadpr->getIdPessoaDePara());
                xmlvo->addItem(IDPESSOAORIGEM,pessoadeparadpr->getIdPessoaOrigem());
                xmlvo->addItem(OPCODE,pessoadeparadpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};
//
//==============================================================================
struct PessoaLinhaHistoricoVO
{
    XMLGen *xmlvo;

    PessoaLinhaHistoricoVO() { xmlvo=0; }

    void setXMLGen(XMLGen *xmlDpr) { xmlvo = xmlDpr; }

    void inserir(PessoaLinhaHistoricoDPR *pessoalinhahistoricodpr)
    {
        if ( !pessoalinhahistoricodpr ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);
        if ( !xmlvo ) throw DPRXMLException(ERR_DPR_PONTEIRONULO,__FILE__,__LINE__);

        if ( false == pessoalinhahistoricodpr->isEmpty() )
        {
            xmlvo->createTag(PESSOALINHAHISTORICOVO);
                xmlvo->addItem(IDPESSOALINHAHISTORICO,pessoalinhahistoricodpr->getIdPessoaLinhaHistorico());
                xmlvo->addItem(CDAREAREGISTRO,pessoalinhahistoricodpr->getCdAreaRegistro());
                xmlvo->addItem(NRLINHA,pessoalinhahistoricodpr->getNrLinha());
                xmlvo->addItem(IDTIPORELACIONAMENTO,pessoalinhahistoricodpr->getIdTipoRelacionamento());
                xmlvo->addItem(IDPESSOADEPARA,pessoalinhahistoricodpr->getIdPessoaDePara());
                xmlvo->addItem(IDLINHATELEFONICA,pessoalinhahistoricodpr->getIdLinhaTelefonica());
                xmlvo->addItem(DTRELACIONAMENTO,pessoalinhahistoricodpr->getDtRelacionamento());
                xmlvo->addItem(OPCODE,pessoalinhahistoricodpr->getOpCode());
            xmlvo->closeTag();
        }
    }
};

//
//==============================================================================
struct XMLDPR
{
    int idUser;
    const char *nomeServico;
    //int idContato;
    char *idAtendimento;
    XMLGen xmlDpr;

    XMLDPR() 
    { 
        idUser=0;nomeServico=0;//idContato=-1;
        andamentovo.setXMLGen(&xmlDpr);
        andamentoobservacaovo.setXMLGen(&xmlDpr);
        atendimentobaixahistoricovo.setXMLGen(&xmlDpr);
        atendimentocontatocomunicvo.setXMLGen(&xmlDpr);
        atendimentocontatovo.setXMLGen(&xmlDpr);
        atendimentofechamentovo.setXMLGen(&xmlDpr);
        atendimentofrmcampovo.setXMLGen(&xmlDpr);
        atendimentofrmvo.setXMLGen(&xmlDpr);
        atendimentogrupoatualvo.setXMLGen(&xmlDpr);
        atendimentohierarquiavo.setXMLGen(&xmlDpr);
        atendimentolinhavo.setXMLGen(&xmlDpr);
        atendimentomensagemvo.setXMLGen(&xmlDpr);
        atendimentoobservacaovo.setXMLGen(&xmlDpr);
        atendimentoorigemvo.setXMLGen(&xmlDpr);
        atendimentopessoavo.setXMLGen(&xmlDpr);
        atendimentotestevo.setXMLGen(&xmlDpr);
        atendimentousuarioatualvo.setXMLGen(&xmlDpr);
        atendimentovo.setXMLGen(&xmlDpr);
        pessoavo.setXMLGen(&xmlDpr);
        pessoadeparavo.setXMLGen(&xmlDpr);
        pessoalinhahistoricovo.setXMLGen(&xmlDpr);
        long idAtendimento = 0;
    }

    string obterXMLDpr() { int tam;return xmlDpr.retrieveXML(&tam); }
    string obterIDUser() { char temp[21];sprintf(temp,"%d",idUser);return (string)temp;}

    int obterTamAreaXML() { int tam;xmlDpr.retrieveXML(&tam);return tam; }

    AndamentoVO andamentovo;
    AndamentoObservacaoVO andamentoobservacaovo;
    AtendimentoBaixaHistoricoVO atendimentobaixahistoricovo;
    AtendimentoContatoComunicVO atendimentocontatocomunicvo;
    AtendimentoContatoVO atendimentocontatovo;
    AtendimentoFechamentoVO atendimentofechamentovo;
    AtendimentoFRMCampoVO atendimentofrmcampovo;
    AtendimentoFRMVO atendimentofrmvo;
    AtendimentoGrupoAtualVO atendimentogrupoatualvo;
    AtendimentoHierarquiaVO atendimentohierarquiavo;
    AtendimentoLinhaVO atendimentolinhavo;
    AtendimentoMensagemVO atendimentomensagemvo;
    AtendimentoObservacaoVO atendimentoobservacaovo;
    AtendimentoOrigemVO atendimentoorigemvo;
    AtendimentoPessoaVO atendimentopessoavo;
    AtendimentoTesteVO atendimentotestevo;
    AtendimentoUsuarioAtualVO atendimentousuarioatualvo;
    AtendimentoVO atendimentovo;
    PessoaVO pessoavo;
    PessoaDeParaVO pessoadeparavo;
    PessoaLinhaHistoricoVO pessoalinhahistoricovo;
};

#endif
