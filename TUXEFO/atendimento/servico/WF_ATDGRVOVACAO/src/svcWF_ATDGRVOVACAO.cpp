/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.4.15 $
 * @CVS     $Author: a5114878 $ - $Date: 2010/08/20 23:22:22 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../include/cWF_ATDGRVOVACAOPC.h"
#include <string>
using namespace std;

//------------------------------------------------------------------------------
// Implementação
//------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(ATDGRVOVACAO);
int GetInnerXML(string & strXML, const string & strTAGName);
int RemoveTag(string & strXML, const string & strTAGName);

void implATDGRVOVACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implATDGRVOVACAO::Execute()");

    string sCode = "04I0000";
    string mCode = "Execucao de ATDGRVOVACAO Concluida";

    cWF_ATDGRVOVACAO rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implATDGRVOVACAO::Execute()");
}

//------------------------------------------------------------------------------
// Construtor
//------------------------------------------------------------------------------

cWF_ATDGRVOVACAO::cWF_ATDGRVOVACAO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef(dnode, xml_g);

     if ( user ) { User = user; }
}

//------------------------------------------------------------------------------
// Implementa o negócio ao qual o serviço atende
// Caso de Uso: Aceitar oferta de retenção temporariamente
//------------------------------------------------------------------------------

void cWF_ATDGRVOVACAO::Executar() 
{
    cWF_Acao::Executar();

    if ( User.ToInt() == 0 )
    {
        SetMessage( "Usuário não informado", "N" );
        xml_g->closeTag();
        return;
    }

    if (idGrupo.ToInt() == 0)
    {
        getAtendimentoGrupoAtual();
        idGrupo = idGrupoAtual;
    }

    TuxHelper tx;
    string strNode (" ");
	strNode = (tx.getNodeAsString(tx.walkDOM(bdnode,"AtendimentoWorkflowVO",0))
                + strlen("AtendimentoWorkflowVO") + 2);

    ULOG("XML=%s",strNode.c_str());

    char * p;
    char strTipoDoc[5];
    if ( p = tx.walkTree( bdnode, "TIPO_DOC", 0 ),p )
    {
	strcpy( strTipoDoc,p );
	XMLString::release(&p);
    }


    //char *p0 = strstr(strNode.c_str(),"<CLIENTE>");
    //char *p1 = strstr(strNode.c_str(),"</CLIENTE>");

    //if ( dsObservacaoOV.Length() > 0 )
    //{
    //    procCAtualizarObservacaoAtendimento(idAtendimento.ToInt(),dsObservacaoOV.c_str());
    //}

    //if ( 0==p0 || 0==p1 )
	
	if( (strNode.find("<DadosOrdemVendaVO>") == 0) || (strNode.find("</DadosOrdemVendaVO>") == 0))
    {
        dsObservacao = "Dados da ordem de venda estão incorretos. Não foi possível enviar os dados ao legado.";
        idAgrEstTPrFt = idAgrEstTPrAt;
        inserirAndamento();
        inserirAndamentoObservacao();

        SetMessage( "Dados da ordem de venda estão incorretos!", "N" );
        xml_g->closeTag();
        return;
    }

	string strExcerptXml(strNode);

	//ULOG("wakim xml - 1=%s",strExcerptXml.c_str());
	
	// REMOVE O TAG <IDSTATUSSAP>
	if (RemoveTag(strExcerptXml, string ("IDSTATUSSAP")) == -1)
	{
		ULOG("ocorreu um erro ao tentar remover a TAG IDSTATUSSAP do XML.=%s",strExcerptXml.c_str());
	}
	//ULOG("wakim xml - 2=%s",strExcerptXml.c_str());
	// REMOVE O TAG <DSOBSERVACAO>
	if (RemoveTag(strExcerptXml, string ("DSOBSERVACAO")) == -1)
	{
		ULOG("ocorreu um erro ao tentar remover a TAG DSOBSERVACAO do XML=%s",strExcerptXml.c_str());
	}
	//ULOG("wakim xml - 3=%s",strExcerptXml.c_str());
	// REMOVE O TAG <IDRETENCAO>
	if (RemoveTag(strExcerptXml, string ("IDRETENCAO")) == -1)
	{
		ULOG("ocorreu um erro ao tentar remover a TAG IDRETENCAO do XML=%s",strExcerptXml.c_str());
	}
	
	if (RemoveTag(strExcerptXml, string ("ORDEMVENDA")) == -1)
	{
		ULOG("ocorreu um erro ao tentar remover a TAG IDRETENCAO do XML=%s",strExcerptXml.c_str());
	}
	//ULOG("wakim xml - 4=%s",strExcerptXml.c_str());
	// OBTEM O CONTEUDO DO TAG <DadosOrdemVendaVO>
	if (GetInnerXML(strExcerptXml, string("DadosOrdemVendaVO")) == -1 )
	{
		ULOG("Ocorreu um erro ao tentar obter o conteudo da TAG de XML DadosOrdemVendaVO do XML=%s",strExcerptXml.c_str());
	}
//	else
//	{
//		ULOG("Após o parser, Conteudo de XML=%s",strExcerptXml.c_str());
//	}
    //int tamXml = p1 - p0 + strlen("</CLIENTE>");
    //if ( tamXml > 2500 ) tamXml = 2500;
    //char excerptXml[2501];
    //memcpy(excerptXml,p0,tamXml);
    //excerptXml[tamXml]=0;

    ULOG("excerptXml=%s",strExcerptXml.c_str());

    proCAtualizarDadosStatusSAP(idStatusSAP.c_str(),strExcerptXml.c_str(),User.ToInt());

    //if ( !strcmp(strTipoDoc,"CPM") || !strcmp(strTipoDoc,"CGC") )
    //{
        
        //SyncRemoteCall("CriaOrdemVenda",strExcerptXml.c_str());
        
        //TuxMessage *ptmOut = rc.getOutputMessage();
        
        //char *codigoRetorno = ptmOut->getStatusCode();
        
        /*
        
        ULOG("codigoRetorno='%c'",*(codigoRetorno+2));
        
        if ( *(codigoRetorno+2) != 'I' )
        {
            ULOGE("'CriaOrdemVenda' retornou status code='%c'",*(codigoRetorno+2));
        
            char *statusText = ptmOut->getStatusText();
            if ( *statusText==0 ) { statusText = "Falha na chamada ao legado."; }
        
            dsObservacao = "Falha na chamada ao legado. Execução da ação não foi completada.";
            idAgrEstTPrFt = idAgrEstTPrAt;
            inserirAndamento();
            inserirAndamentoObservacao();
        
            SetMessage(statusText, "S" );
            xml_g->closeTag();
            return;
        }
        
        */
        
        
        /*
        string szRetorno = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"+(string)ptmOut->getMessageBody();
        
        ULOG( "szRetorno [%s]", szRetorno.c_str() );
        
        XercesDOMParser xercesDOMParser;
      	const char *pMemBufId = "inputInfo";
        MemBufInputSource memBufInputSource((const XMLByte*)szRetorno.c_str(),szRetorno.length(),pMemBufId);
        xercesDOMParser.parse(memBufInputSource);
        DOMNode* pDoc = xercesDOMParser.getDocument();
        

        char *retornoSAP=0;
        if ( retornoSAP=tx.walkTree(pDoc,"RETURN",0),retornoSAP )
        {
            if ( strlen(retornoSAP) > 0 )
            {
                    ULOGE( "'CriaOrdemVenda' retornou = '%s'",retornoSAP );
                
                    char * statusText = "Falha na chamada ao legado.";
                
                    dsObservacao = "Falha na chamada ao legado. Execução da ação não foi completada.";
                    idAgrEstTPrFt = idAgrEstTPrAt;
                    inserirAndamento();
                    inserirAndamentoObservacao();
                
                    SetMessage(statusText, "S" );
                    xml_g->closeTag();
                    return;
            }
        }


        char *tagNrOrdemVenda=0;
        if ( tagNrOrdemVenda=tx.walkTree(pDoc,"ORDER_NUMBER",0),tagNrOrdemVenda )
        {
             char nrOrdemVenda[16];
             SAFE_STRNCPY(nrOrdemVenda,tagNrOrdemVenda);
             XMLString::release(&tagNrOrdemVenda);
             ULOG("nrOrdemVenda='%s'",nrOrdemVenda);
        
             proCAtualizarOrdemVendaDadosStatusSAP(idAtendimento.ToInt(),idStatusSAP.c_str(),nrOrdemVenda,User.ToInt());
        }
        else
        {
             dsObservacao = "Numero da ordem de venda não foi enviado pelo legado.";
        
             char *statusText = ptmOut->getStatusText();
             if ( statusText && *statusText ) { dsObservacao += "\nRETORNO DO LEGADO=";dsObservacao += statusText; }
        
                idAgrEstTPrFt = idAgrEstTPrAt;
                inserirAndamento();
                inserirAndamentoObservacao();
        
                SetMessage("Falha: Numero da ordem de venda não foi enviado pelo legado", "S" );
                xml_g->closeTag();
                return;
        }

    } */

    getAtendimento();

    urlDestino = TP_RET_COM_RET_GRP_RET == idTipoRetornoContato ? "1" :"3";

    Encerrar();

    registrarAcaoDPR(User.ToInt(),idContato,"ATDGRVOVACAO");

    if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
    {
        SetMessage( "OV atualizada junto ao legado e encerramento concluído para Grupo de Retorno", "S" );
    }
    else if ( TP_RET_COM_RET_GRP_CRI == idTipoRetornoContato )
    {
        SetMessage( "OV atualizada junto ao legado e encerramento concluído Para Grupo de CRI", "S" );
    }
    else if ( strcmp(szSgRegraEncaminhamento,"MC")==0 )
    {
        SetMessage( "OV atualizada junto ao legado e encerramento concluído Para 'Meu Cliente'", "S" );
    }
    else
    {
        SetMessage( "OV atualizada junto ao legado e encerramento concluído Para Grupo de BKO", "S" );
    }

    xml_g->closeTag();
}

int RemoveTag(string & strXML, const string & strTAGName)
{
	int cutAt;

	if(strXML.length() <= 0)
	{
		return -1;
	}

	string strTemp(" ");
	strTemp.append(strXML);

	int iPosInicial = 0;
	int iPosFinal = 0;

	string strDelim;


	// OBTEM POSIÇÃO INICIAL
	strDelim = "<";
	strDelim.append(strTAGName.c_str());
	//strDelim.append(">");
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			iPosInicial = cutAt;
		}
	}

	// OBTEM POSIÇÃO FINAL
	strDelim = "</";
	strDelim.append(strTAGName.c_str());
	strDelim.append(">");
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			iPosFinal = cutAt + strDelim.length();
		}
	}


	if( (iPosInicial != 0 ) && (iPosFinal != 0 ))
	{
		strXML = strTemp.erase(iPosInicial, iPosFinal-iPosInicial);
		return 0;
	}
	else
	{
		return -1;
	}
}


int GetInnerXML(string & strXML, const string & strTAGName)
{
	//ULOG("DENTRO DA FUNCAO  GetInnerXML() ");

	//ULOG("Valor do XML=%s",strXML.c_str());
	int cutAt;

	if(strXML.length() <= 0)
	{
		return -1;
	}

	string strTemp(" ");
	strTemp.append(strXML);

	int iPosInicial = 0;
	int iPosFinal = 0;

	string strDelim;


	// OBTEM POSIÇÃO INICIAL
	strDelim = "<";
	strDelim.append(strTAGName.c_str());
	//strDelim.append(">");
	int icutAt2=0;
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			if ( (icutAt2 = strTemp.find_first_of(">",cutAt+1)) != strTemp.npos)
			{
				iPosInicial = icutAt2+1;
			}
		}
	}
	//ULOG("Valor da cutAt=%d",cutAt);
	//ULOG("Valor da icutAt2=%d",icutAt2);
	//ULOG("Valor da PosicaoInicial=%d",iPosInicial);

	// OBTEM POSIÇÃO FINAL
	strDelim = "</";
	strDelim.append(strTAGName.c_str());
	strDelim.append(">");
	if( (cutAt = strTemp.find(strDelim)) != strTemp.npos )
	{
		if(cutAt > 0)
		{
			iPosFinal = cutAt;
		}
	}
	
	//ULOG("Valor da cutAt=%d",cutAt);
	//ULOG("Valor da PosicaoFinial=%d",iPosFinal);
	
	if( (iPosInicial != 0 ) && (iPosFinal != 0 ))
	{
		strXML = strTemp.substr(iPosInicial, iPosFinal-iPosInicial);
		return 0;
	}
	else
	{
		return -1;
	}
	
	//ULOG("SAIU DA FUNCAO  GetInnerXML() ");	
}
