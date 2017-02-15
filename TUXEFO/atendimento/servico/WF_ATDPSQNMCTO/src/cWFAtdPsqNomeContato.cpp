/**
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Pesquisa nome de folhas de contato a partir de um padrão fornecido
 *          pelo usuário Web.
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

#include "../include/cWFAtdPsqNomeContato.h"

//============================================================================
cWFAtdPsqNomeContato::cWFAtdPsqNomeContato(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;
}

//============================================================================
void cWFAtdPsqNomeContato::Executar()
{
    ULOG_START("Executar()");

    CarregarDados();

    saida->createTag("WFListaContatosVO");
    saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

    if ( proCPesquisaNomeFolha() == PADRAO_NAO_ENCONTRADO )
    {
        string mensagem = "Não existem contatos com o padrão ('" +
                            (string)dados.pesquisa + "') solicitado";

        saida->addItem("status","1");
        saida->addItem("msgStatus",(char*)mensagem.c_str());
    }
    else
    {
        saida->addItem("pageNumber",dados.pageNumber);
        saida->addItem("status","0");
        saida->addItem("msgStatus","Operação concluida");
    }

    saida->closeTag();

    ULOG_END("Executar()");
}

//============================================================================
void cWFAtdPsqNomeContato::CarregarDados()
{
    ULOG_START("CarregarDados()");

    char *p;

    //===============================================================
    //==<pesquisa>==
    if (p=tx.walkTree( entrada, "pesquisa", 0 ),p) 
    {
        dados.pesquisa = p;
        status.pesquisa = 1;
        XMLString::release(&p);

        if ( dados.pesquisa.length() < 1 )
        {
            throw new TuxException("04I0000","Padrão de busca deve conter ao menos um caractere.");
        }
    }
    else
    {
        throw new TuxException("04I0000","Padrão de busca não fornecido");
    }
    ULOG("<pesquisa>='%s'",dados.pesquisa.c_str());

    //===============================================================
    //==<pageNumber>==
    if (p=tx.walkTree( entrada, "pageNumber", 0 ),p) 
    {
        dados.pageNumber = atoi(p);
        status.pageNumber = 1;
        XMLString::release(&p);
        if ( dados.pageNumber < 0 )
        {
            throw new TuxException("04I0000","valor de <pageNumber> não pode ser menor que zero");
        }
    }
    else
    {
        throw new TuxException("04I0000","Pagina a ser pesquisada não fornecida (<pageNumber>)");
    }
    ULOG("<pageNumber>=%d",dados.pageNumber);

    //===============================================================
    //==<idUFOperadora>==
    if (p=tx.walkTree( entrada, "idUFOperadora", 0 ),p) 
    {
        if ( *p )
        {
            dados.idUFOperadora = p;
            status.idUFOperadora = 1;
            XMLString::release(&p);
            ULOG("<idUFOperadora>=%s",dados.idUFOperadora.c_str());
        }
        else
        {
            ULOG("tag <idUFOperadora> sem valor");
        }
    }
    else
    {
        throw new TuxException("04I0000","tag <idUFOperadora> não fornecida");
    }

    //===============================================================
    //==<idTipoLinha>==
    if (p=tx.walkTree( entrada, "idTipoLinha", 0 ),p) 
    {
        if ( *p )
        {
            dados.idTipoLinha = p;
            status.idTipoLinha = 1;
            XMLString::release(&p);
            ULOG("<idTipoLinha>=%s",dados.idTipoLinha.c_str());
        }
        else
        {
            ULOG("tag <idTipoLinha> sem valor");
        }
    }
    else
    {
        ULOG("<idTipoLinha> não informado");
    }

    //===============================================================
    //==<idTipoCarteira>==
    if (p=tx.walkTree( entrada, "idTipoCarteira", 0 ),p) 
    {
        if ( *p )
        {
            dados.idTipoCarteira = p;
            status.idTipoCarteira = 1;
            XMLString::release(&p);
            ULOG("<idTipoCarteira>=%s",dados.idTipoCarteira.c_str());
        }
        else
        {
            ULOG("tag <idTipoCarteira> sem valor");
        }
    }
    else
    {
        ULOG("<idTipoCarteira> não informado");
    }

    //===============================================================
    //==<idSegmentacao>==
    if (p=tx.walkTree( entrada, "idSegmentacao", 0 ),p) 
    {
        if ( *p )
        {
            dados.idSegmentacao = p;
            status.idSegmentacao = 1;
            XMLString::release(&p);
            ULOG("<idSegmentacao>=%s",dados.idSegmentacao.c_str());
        }
        else
        {
            ULOG("tag <idSegmentacao> sem valor");
        }
    }
    else
    {
        ULOG("<idSegmentacao> não informado");
    }

    //===============================================================
    //==<idTipoRelacionamento>==
    if (p=tx.walkTree( entrada, "idTipoRelacionamento", 0 ),p) 
    {
        if ( *p )
        {
            dados.idTipoRelacionamento = p;
            status.idTipoRelacionamento = 1;
            XMLString::release(&p);
            ULOG("<idTipoRelacionamento>=%s",dados.idTipoRelacionamento.c_str());
        }
        else
        {
            ULOG("tag <idTipoRelacionamento> sem valor");
        }
    }
    else
    {
        throw new TuxException("04I0000","tag <idTipoRelacionamento> não fornecida");
    }

    //===============================================================
    //==<idGrupo>==
    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        if ( *p )
        {
            dados.idGrupo = p;
            status.idGrupo = 1;
            XMLString::release(&p);
            ULOG("<idGrupo>=%s",dados.idGrupo.c_str());
        }
        else
        {
            throw new TuxException("04I0000","Grupo de abertura não informado");
        }
    }
    else
    {
        throw new TuxException("04I0000","tag <idGrupo> não fornecida");
    }

    ULOG_END("CarregarDados()");
}
