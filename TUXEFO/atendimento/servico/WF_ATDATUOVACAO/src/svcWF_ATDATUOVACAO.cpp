/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.4.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../include/cWF_ATDATUOVACAOPC.h"

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(ATDATUOVACAO);

void implATDATUOVACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implATDATUOVACAO::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de ATDATUOVACAO Concluida";

    cWF_ATDATUOVACAO rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();

        delete ex;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0097";
        mCode = errMsg;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0098";
        mCode = errMsg;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());
    
    ULOG_END("implATDATUOVACAO::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_ATDATUOVACAO::cWF_ATDATUOVACAO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Aceitar oferta de retenção temporariamente
//------------------------------------------------------------------------------

void cWF_ATDATUOVACAO::Executar() 
{
    cWF_Acao::Executar();

    if ( User.ToInt() == 0 )
    {
        SetMessage( "Usuário não informado", "N" );
        xml_g->closeTag();
        return;
    }

    getAtendimento();

    if ( proCObterDadosStatusSAP(idAtendimento.ToLong()) == false )
    {
        SetMessage( "Dados da ordem de venda não encontrados.", "N" );
        xml_g->closeTag();
        return;
    }

    char *p0 = strstr(dadosOrdemVenda.vlLogXMLIn,"<CLIENTE>");
    char *p1 = strstr(dadosOrdemVenda.vlLogXMLIn,"</CLIENTE>");

    if ( 0==p0 || 0==p1 )
    {
        dsObservacao = "Dados da ordem de venda estão incorretos.";
        idAgrEstTPrFt = idAgrEstTPrAt;
        inserirAndamento();
        inserirAndamentoObservacao();

        SetMessage( "Dados da ordem de venda estão incorretos.", "N" );
        xml_g->closeTag();
        return;
    }

    xml_g->createTag("WFAcaoOrdemVendaVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

    string xml = "<DadosOrdemVendaVO>"
                        "<IDSTATUSSAP>"
                            +(string)dadosOrdemVenda.idStatusSap+
                        "</IDSTATUSSAP>"+
                        "<DSOBSERVACAO>"
                            +(string)procCObterObservacaoAtendimento(idAtendimento.ToLong())+
                        "</DSOBSERVACAO>"+
                        "<IDRETENCAO>"
                            +(string)dadosOrdemVenda.vlIdReferencia+
                        "</IDRETENCAO>"
                        "<ORDEMVENDA>"
                            +(string)dadosOrdemVenda.strOrdemVenda+
                        "</ORDEMVENDA>"
                        +(string)dadosOrdemVenda.vlLogXMLIn+
                     "</DadosOrdemVendaVO>";

    xml_g->aggregateXML((char*)xml.c_str());

    if (idGrupo.ToInt() == 0)
    {
        getAtendimentoGrupoAtual();
        idGrupo = idGrupoAtual;
    }

    inserirAndamento();

    registrarAcaoDPR(User.ToInt(),idContato,"ATDATUOVACAO");

    SetMessage("Dados da ordem de venda devolvidos normalmente","S");

    xml_g->closeTag(); //WFAcaoOVVO
    xml_g->closeTag();
}
