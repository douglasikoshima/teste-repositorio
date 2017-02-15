/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#include "../include/cWFAtdObterParam.h"

extern void proCObterLinha( DadosLinha *dl,st_AtdObterParam *dados,st_vlAtdObterParam *status );
extern void proCObterUfOperadora( DadosLinha *dl,st_AtdObterParam *dados,st_vlAtdObterParam *status );
extern void proCObterIdConta( DadosLinha *dl,char *_cdConta,char *_cdDigitoConta );
extern void proCGetPessoaLinhaHistorico(long idAtendimentoPrm,unsigned long *idPessoaHistoricoCRIPrm);
extern void proCGetLinhaAtendimento(const unsigned long *idPessoaHistoricoCRI, long *idLinhaAtendimento);
extern bool proCPesquisaGrupoFaseVariablesUsuario(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFaseUsuario(st_VariaveisUsuario* _dados, Collection* _grupos);

cWFAtdObterParam::cWFAtdObterParam(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(atdConta.cdConta,0,sizeof(atdConta.cdConta));
    memset(atdConta.cdDigitoConta,0,sizeof(atdConta.cdDigitoConta));

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&linAtendimento,0,sizeof(linAtendimento));

    carregaDados();
}

bool cWFAtdObterParam::executar(char **codErro,char **msgErro)
{
	ULOG_START("cWFAtdObterParam::executar()");
    DadosLinha dl;
    char dadosLinha[512];
    char dadosConta[256];

    if ( m_vlDados.idAtendimento == -1 )
    {
        *msgErro = "O valor de 'idAtendimento' é obrigatório";

        return false;
    }

	long idAtendimentoInicial;
	idAtendimentoInicial = m_stDados.idAtendimento;
	
	ULOG("idAtendimentoInicial = %ld",idAtendimentoInicial);

	memset(&dl,0,sizeof(dl));

    ObterDetalheAtd();

    ObterReaberAtend();
    
    ObterAtendPessoa();
   
    AtdCntConsultar();
    
    AtdCtoConsultar();

	ULOG("m_stDados.idPessoaLinhaHistorico=%ld",m_stDados.idPessoaLinhaHistorico);
	ULOG("m_vlDados.idPessoaLinhaHistorico=%d",m_vlDados.idPessoaLinhaHistorico);

    if ( -1 == m_vlDados.idPessoaLinhaHistorico )
    {
        if ( detAtendimento.idPessoaLinhaHistorico > 0 )
        {
            m_vlDados.idPessoaLinhaHistorico = 1;
            m_stDados.idPessoaLinhaHistorico = detAtendimento.idPessoaLinhaHistorico;
        }
    }

    if ( m_vlDados.idPessoaLinhaHistorico > 0 )
    {
        ObterDetalheAtendLinha();
        proCObterLinha( &dl,&m_stDados,&m_vlDados );
    }

	ULOG("m_stDados.idPessoaLinhaHistorico=%ld",m_stDados.idPessoaLinhaHistorico);
	ULOG("m_vlDados.idPessoaLinhaHistorico=%d",m_vlDados.idPessoaLinhaHistorico);

    proCObterUfOperadora( &dl,&m_stDados,&m_vlDados );
    
    // Incidência 3833
    // Este serviço é chamado somente em ambiente de back-office entretanto não é necessário
    // registrar uma chamada.
    // ChaAteConsultar(atdPessoa.idPessoa,dl.idPessoaDePara);

    // Se não tem dados de linha tenta ver se através do CRI consegue...
    if ( 0 == detAtendimento.idLinhaTelefonica || 
         0 == m_stDados.idPessoaLinhaHistorico )
    {
        TratamentoCRI( (long*)&detAtendimento.idLinhaTelefonica,(unsigned long*)&m_stDados.idPessoaLinhaHistorico );
    }

    if ( atdConta.cdConta[0] && atdConta.cdDigitoConta[0] )
    {
        proCObterIdConta( &dl,atdConta.cdConta,atdConta.cdDigitoConta );
    }

    /*	
    st_VariaveisUsuario variaveisUsuario;

	variaveisUsuario.idFase 				= 2;
	variaveisUsuario.idTipoCarteira 		= atoi(ObterValorTag(&xmlAtendimento,"idTipoCarteira"));
	variaveisUsuario.idSegmentacao 			= atoi(ObterValorTag(&xmlAtendimento,"idSegmentacao"));
	variaveisUsuario.idProcedencia 			= atoi(ObterValorTag(&xmlAtendimento,"idProcedencia"));
	variaveisUsuario.idContato 				= atoi(ObterValorTag(&xmlAtendimento,"idContato"));
	variaveisUsuario.idGrupoAbertura 		= atoi(ObterValorTag(&xmlAtendimento,"idGrupoAbertura"));
	variaveisUsuario.idTipoPessoa 			= atoi(ObterValorTag(&xmlPessoa,"idTipoPessoa"));
	variaveisUsuario.idTipoLinha 			= dl.idTipoLinha;
	variaveisUsuario.idTipoRelacionamento 	= atoi(ObterValorTag(&xmlPessoa,"inRspAbertura"));
	variaveisUsuario.idCanal 				= atoi(ObterValorTag(&xmlAtendimento,"idCanal"));
	variaveisUsuario.idPessoaUsuario 		= idUsuario;

	int idGrupoAbertura = ObterGrupoAbertura(variaveisUsuario);
    */

    if ( m_vlDados.idPessoaLinhaHistorico > 0 )
    {
        sprintf(dadosLinha,"%s%s",linAtendimento.cdAreaRegistro,linAtendimento.nrLinha);
    }
    else
    {
        dadosLinha[0] = 0;
    }

    if ( atdConta.cdConta[0] && atdConta.cdDigitoConta[0] )
    {
        sprintf(dadosConta,"%s-%s",atdConta.cdConta,atdConta.cdDigitoConta);
    }
    else
    {
        dadosConta[0] = 0;
    }

	ULOG("m_stDados.idAtendimento = %ld",m_stDados.idAtendimento);
	m_stDados.idAtendimento = idAtendimentoInicial;

    saida->createTag("ParametrosVO");
    saida->addProp("xmlns", "cliente.fo.vivo.com.br/vo");
        saida->addItem("idAtendimento",m_stDados.idAtendimento);
        saida->addItem("nrProtocolo",detAtendimento.idAtendimentoProtocolo);
        saida->addItem("idChamadaTelefonica","");
        // saida->addItem("idChamadaTelefonica",ObterValorTag(&xmlChamada,"idChamadaTelefonica"));
        saida->addItem("idGrupo",detAtendimento.idGrupoAbertura);
        saida->addItem("idGrupoAndamento",detAtendimento.idGrupoAndamento);
        saida->addItem("idContato",detAtendimento.idContato);
        saida->addItem("idLinha",dl.idLinhaTelefonica);
        saida->addItem("idLinhaAtendimento",detAtendimento.idLinhaTelefonica );
        saida->addItem("idConta",dl.idConta);
        saida->addItem("idTipoLinha",dl.idTipoLinha);
        saida->addItem("cdAreaRegistro",dl.cdAreaRegistro);
        saida->addItem("nrLinha",dadosLinha);
        saida->addItem("idPessoaLinhaHistorico",m_stDados.idPessoaLinhaHistorico);
        saida->addItem("nrTelefone",atdContato.nrTelefoneContato);
        saida->addItem("nrConta", dadosConta);
        saida->addItem("idClienteDePara",dl.idPessoaDePara);
        saida->addItem("idUsuarioDePara",atdPessoa.idPessoaDePara);
        saida->addItem("idPessoaCliente",atdPessoa.idPessoa);
        saida->addItem("idPessoaUsuario",atdPessoa.idPessoa);
        saida->addItem("inTipoPessoa",atdPessoa.sgTipoPessoa);
        saida->addItem("idTipoPessoa",atdPessoa.idTipoPessoa);
        saida->addItem("idTipoRelacionamento",atdPessoa.inRspAbertura);
        saida->addItem("nmContato",atdContato.nmContato);
        saida->addItem("nmFalandoCom",atdContato.nmFalandoCom);
        saida->addItem("idTipoCarteira",detAtendimento.idTipoCarteira);
        saida->addItem("idSegmentacao",detAtendimento.idSegmentacao);
        saida->addItem("idUfOperadora",dl.idUfOperadora);
        saida->addItem("idTipoReaberturaProcesso",detReabertura.idTipoReaberturaProcesso);
        saida->addItem("nmTipo",detReabertura.nmTipo);
    saida->closeTag();

	ULOG_END("cWFAtdObterParam::executar()");

    return true;
}

void cWFAtdObterParam::TratamentoCRI( long *idLinhaAtendimento,
                                      unsigned long *idPessoaHistoricoCRI )
{
    ULOG_START("cWFAtdObterParam::TratamentoCRI()");

    proCGetPessoaLinhaHistorico(m_stDados.idAtendimento,idPessoaHistoricoCRI );
    proCGetLinhaAtendimento( idPessoaHistoricoCRI,idLinhaAtendimento );

    ULOG_END("cWFAtdObterParam::TratamentoCRI()");
}
        
void cWFAtdObterParam::ObterDetalheAtd()
{
    ULOG_START("cWFAtdObterParam::ObterDetalheAtd()");
	
    cWFAtendimento cwfAtendimento;

    if ( !cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&detAtendimento) )
    {
        ULOG("processo %d não encontrado",m_stDados.idAtendimento);
		
        ULOG_END("cWFAtdObterParam::ObterDetalheAtd()");

        return;
    }

    ULOG_END("cWFAtdObterParam::ObterDetalheAtd()");
}

void cWFAtdObterParam::ObterReaberAtend()
{
    ULOG_START("cWFAtdObterParam::ObterReaberAtend()");

    cWFAtendimento cwfAtendimento;

    if ( !cwfAtendimento.ObtemReaberAtend(m_stDados.idAtendimento,0,&detReabertura) )
    {
        ULOG("falhou execução");
        ULOG_END("cWFAtdObterParam::ObterReaberAtend()");
        return;
    }

    ULOG_END("cWFAtdObterParam::ObterReaberAtend()");
}

void cWFAtdObterParam::ObterAtendPessoa()
{
    ULOG_START("cWFAtdObterParam::ObterAtendPessoa()");

    cWFAtendimentoPessoa cwfAtendimentoPessoa;

    if ( !cwfAtendimentoPessoa.ObtemAtendPessoa(m_stDados.idAtendimento,&atdPessoa) )
    {
        ULOG("Dados de pessoa não encontrados.");
    }

    ULOG_END("cWFAtdObterParam::ObterAtendPessoa()");
}

void cWFAtdObterParam::ObterDetalheAtendLinha()
{
    ULOG_START("cWFAtdObterParam::ObterDetalheAtendLinha()");

    cWFAtendimentoLinha cwfAtendimentoLinha;

    if ( cwfAtendimentoLinha.ObtemDetalheAtend(m_stDados.idAtendimento,&linAtendimento) )
    {
	    m_stDados.idPessoaLinhaHistorico = atoi(linAtendimento.idPessoaLinhaHistorico);
	    m_vlDados.idPessoaLinhaHistorico = 1;
    }

	ULOG_END("cWFAtdObterParam::ObterDetalheAtendLinha()");
}

void cWFAtdObterParam::AtdCntConsultar()
{
    ULOG_START("cWFAtdObterParam::AtdCntConsultar()");

    cWFAtendimentoConta cwfAtendimentoConta;

    if ( !cwfAtendimentoConta.obterAtendimentoConta(m_stDados.idAtendimento,&atdConta) )
    {
        ULOGW("dados da conta do processo não encontrados");
    }

    //xmlObtemCnt = p;

    ULOG_END("cWFAtdObterParam::AtdCntConsultar()");
}

void cWFAtdObterParam::AtdCtoConsultar()
{
    ULOG_START("cWFAtdObterParam::AtdCtoConsultar()");

    cWFAtendimentoContato cwfAtendimentoContato;

    // bool cWFAtendimentoContato::ObterAtendimentoContato(m_stDados.idAtendimento,&atdContato)

    if ( !cwfAtendimentoContato.ObterAtendimentoContato(m_stDados.idAtendimento,&atdContato) )
    {
        ULOG("dados do contato do atendimento não encontrados");
    }

    ULOG_END("cWFAtdObterParam::AtdCtoConsultar()");
}
//
//-----------------------------------------------------------------------------------------------
// Incidência 3833 - Setembro 2006, Cassio
//-----------------------------------------------------------------------------------------------
//
// Este serviço é chamado somente em ambiente de back-office portanto não é necessário
// registrar uma chamada.
//
// void cWFAtdObterParam::ChaAteConsultar(unsigned long _idPessoa,unsigned long _idPessoaDePara)
// {
//     ULOG_START("cWFAtdObterParam::ChaAteConsultar()");
//     
//     int tamSaida;
//     st_ChamadaTelefonica m_stDadosChamada;
//     st_vlChamadaTelefonica m_vlDadosChamada;
//     XMLGen saida;
// 
//     m_stDadosChamada.idChamadaTelefonica = 0;
//     m_vlDadosChamada.idChamadaTelefonica = 1;
// 
//     if ( _idPessoaDePara )
//     {
//         m_stDadosChamada.idPessoaDePara = _idPessoaDePara ;
//         m_vlDadosChamada.idPessoaDePara = 1;
//     }
// 
//     m_stDadosChamada.idGrauSatisfacao = 0;
//     m_stDadosChamada.idGrauSatisfacao = 0;
//     m_vlDadosChamada.idGrauSatisfacao = 1;
//    
//     m_vlDadosChamada.dtChamada = -1;
// 
//     // este elemento da estrutura nao foi inicializado desta forma esta gerando 
//     // um erro no objeto
//     m_vlDadosChamada.sgTipoRelacionamento = -1;
// 
//     cWFChamadaTelefonica cWFChamadaTelefonica(&m_stDadosChamada, &m_vlDadosChamada, &saida);
// 
//     if ( !cWFChamadaTelefonica.incluir() )
//     {
//         ULOGE("falhou execução");
//         ULOG_END("cWFAtdObterParam::ChaAteConsultar()");
//         return;
//     }
// 
//     char *p = saida.retrieveXML(&tamSaida);
// 
//     if ( !tamSaida )
//     {
//         ULOG("não gerou xml de saida");
// 
//         ULOG_END("cWFAtdObterParam::ChaAteConsultar()");
//         return;
//     }
//     
//     ULOG(" gerou xml de saida com %d bytes",tamSaida);
//     ULOG_END("cWFAtdObterParam::ChaAteConsultar()");
// 
//     xmlChamada = p;
// }

void cWFAtdObterParam::carregaDados()
{
	ULOG_START("cWFAtdObterParam::carregaDados()");

    if ( entrada )
    {
        char *p;

        if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
        {
            m_stDados.idAtendimento = atol(p);
            m_vlDados.idAtendimento = 1;
            XMLString::release(&p);

            ULOG("idAtendimento=%lu",m_stDados.idAtendimento);
        }
    }

	ULOG_END("cWFAtdObterParam::carregaDados()");
}

// char *ObterValorTag::_ObterValorTag(SmallString *ss,const char *idTag)
// {
// 	ULOG_START("ObterValorTag::_ObterValorTag()");
//     char *p=0;
//     MemBufInputSource *pMemBuf;
//     XercesDOMParser *pParser;
// 
//     if ( ss->c_str() == 0 ) 
//     {
//         valor = new char [10];
//         strcpy(valor,"");
//         ULOG_END("ObterValorTag::_ObterValorTag()");
//         return valor; 
//     }
// 
//     SmallString ssLocal;
// 
//     if ( !strstr(ss->c_str(),"encoding") )
//     {
//         ssLocal += "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
//     }
// 
//     ssLocal += (char*)ss->c_str();
// 
//     int tamSaida = ssLocal.Size();
// 
//     pParser = new XercesDOMParser;
//     pMemBuf = new MemBufInputSource((const XMLByte*)ssLocal.c_str(),tamSaida,gerarIDDom());
// 
//     valor = 0;
// 
//     if ( pParser && pMemBuf )
//     {
//         pParser->parse(*pMemBuf);
//         DOMNode* pDoc = pParser->getDocument();
// 
//         char *valorResult = tx.walkTree(pDoc,(char*)idTag,0);
// 
//         if ( valorResult ) 
//         {
//             valor = new char [ strlen(valorResult) + 1 ];
//             if ( valor )
//             {
//                 strcpy(valor,valorResult);
//             }
//             else
//             {
//                 p = "Erro de alocação de memória";
//             }
// 
//             ULOG("ObterValorTag: valor da tag '%s'=%d",idTag,valorResult);
// 
//             XMLString::release(&valorResult);
//         }
//         else
//         {
//             ULOG("ObterValorTag: valor da tag '%s' NAO ENCONTRADO"
//                         ,idTag);
//         }
//     }
//     else
//     {
//         p = "Nao foi possivel alocar memoria para objetos DOM e/ou Xerces";
//     }
// 
//     if ( pParser ) { delete pParser; }
//     if ( pMemBuf ) { delete pMemBuf; }
// 
//     if ( p )
//     {
//         ULOGE("%s",(p));
//         throw new TuxBasicSvcException("00E0000",p);
//     }
//     
//     ULOG_START("ObterValorTag::_ObterValorTag()");
// 
//     return valor;
// }
