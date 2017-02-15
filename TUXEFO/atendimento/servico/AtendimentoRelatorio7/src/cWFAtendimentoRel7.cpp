/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:42 $
 **/

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include "../include/cWFAtendimentoRel7.h"
#include "../include/MapaErros.h"
#include "../../AtendimentoCommonsRel/include/cWFComunsRelatorios.h"
#include "../../../commons/msgPadrao.h"


extern void proCObterHora(st_AtendimentoRel7 *dados);
extern void proCObterDataHoje(st_AtendimentoRel7 *dados,st_vlAtendimentoRel7 *status);

extern bool proCObtemWFAtendimentoRel7N0(st_AtendimentoRel7 *dados
                                        ,st_vlAtendimentoRel7 *status
                                        ,MAP_NVZR &mapNvZr
                                        ,MAP_TTNZR &mapTtNZr
                                        ,XMLGen* saida
                                        ,Paginacao * pPagina );
extern bool proCCarregarArvoreContatos(st_AtendimentoRel7 *dados,st_vlAtendimentoRel7 *status,XMLGen* saida,MAP_AC &mapAC,MAP_TAB_RAIZ &mapRaiz,int &nivelMax);
extern bool proCCarregarPalitagem(st_AtendimentoRel7 *dados, st_vlAtendimentoRel7 *status, XMLGen* saida,VEC_PAL &vecPal);


cWFAtendimentoRel7::cWFAtendimentoRel7(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida = xml_g;

    arvoreContatosCarregada = false;
    arvorePalitagemCarregada = false;

    carregaDados();
    ULOG( "Passou por carregaDados()" );

    carregaDadosPaginacao();
    carregaDadosDom("FiltroDetalhe");
    carregaDadosDom("WFRelatoriosFiltroUFVO");

    proCObterDataHoje(&m_stDados,&m_vlDados);  // Obtem a data atual no formato DD/MM/AAAA

    proCObterHora(&m_stDados);  // Obtem a hora atual no formato HHMM

    if ( m_stDados.nivel )
    {
        // Carrega hierarquia de contatos
        CarregarArvoreContatos();

        // Carrega as folhas com quebra por data/hora e grupo
        CarregarPalitagem();
    }

    PadronizarData1();

    PadronizarData2();

    PadronizarDataHoje();
}

void cWFAtendimentoRel7::CarregarArvoreContatos()
{
    ULOG_START( "cWFAtendimentoRel7::CarregarArvoreContatos()" );

    arvoreContatosCarregada = proCCarregarArvoreContatos( &m_stDados
                                                        , &m_vlDados
                                                        , saida
                                                        , mapAC
                                                        , mapRaiz
                                                        , nivelMax );
    ULOG( "%s na carga da arvore",arvoreContatosCarregada 
            ? "SUCESSO":"FALHA");

    if ( arvoreContatosCarregada )
    {
        ProcurarDescendentes();
    }

    ULOG_END( "cWFAtendimentoRel7::CarregarArvoreContatos()" );
}

void cWFAtendimentoRel7::ProcurarDescendentes()
{
    ULOG_START( "cWFAtendimentoRel7::ProcurarDescendentes()" );


    MAP_AC::iterator itMapAC;
    MAP_TAB_RAIZ::iterator itMapTabRaiz;

    // Gera palitagem agrupada
    itMapAC = mapAC.begin();

    while ( itMapAC != mapAC.end() )
    {
        if ( mapRaiz.find(itMapAC->second.idContato) != mapRaiz.end() )
        {
            itMapAC->second.possuiDescendente = true;
        }

    // Este log foi desligado para redução de log e aumento de performance
    /*        ULOG((Endereco(__LINE__,__FILE__).
                mensagem("idContatoPai=%d,idContato=%d,nivel=%d,nmContato=%s,+niveis=%s"
                    ,itMapAC->second.idContatoPai,itMapAC->second.idContato
                    ,itMapAC->second.nivel,itMapAC->second.nmContato
                    ,itMapAC->second.possuiDescendente?"SIM":"NAO"))
                        .MsgPadrao());
    */
        
        itMapAC++;
    }

    ULOG_END( "cWFAtendimentoRel7::ProcurarDescendentes()" );
}

void cWFAtendimentoRel7::CarregarPalitagem()
{
    ULOG_START( "cWFAtendimentoRel7::CarregarPalitagem()" );

    arvorePalitagemCarregada = proCCarregarPalitagem( &m_stDados,&m_vlDados,saida,vecPal );

    ULOG( "%s na carga da arvore",
                arvorePalitagemCarregada ? "SUCESSO":"FALHA");

    ULOG_END( "cWFAtendimentoRel7::CarregarPalitagem()" );
}

void cWFAtendimentoRel7::ObterRelatorio()
{
    int sizeVecPal;
    int sizeVecContato;
    int i;
    MAP_AC::iterator itMapAC;
    MAP_AC::iterator itMapACOrigem;
    try
    {
        ConsistirParametros();

        if ( !m_stDados.nivel )
        {
            if ( proCObtemWFAtendimentoRel7N0(&m_stDados,&m_vlDados,mapNvZr,mapTtNZr,saida,&pPagina) )
            {
                ULOG( ">>> Terminando ObterRelatorio()" );
                return;
            }

            ULOGE( "proCObtemWFAtendimentoRel7N0() erro!" );

            throw MapaErroException(__LINE__,ERRO_REL_NIVEL_0,__FILE__);
        }

        if ( -1 == m_vlDados.sgUF )
        {
            // throw MapaErroException(__LINE__,ERRO_FALTA_SG_UF,__FILE__);
            ULOG( "Nao recebeu sgUF ..." );
        }

        if ( m_stDados.nivel > 1 && -1 == m_vlDados.idContatoPai )
        {
            // throw MapaErroException(__LINE__,ERRO_FALTA_CONTATO,__FILE__);
            ULOG( "Nao recebeu idContatoPai ..." );
        }

        sizeVecPal = vecPal.size();

        ULOG( "Associando %d folhas aos pais do nivel %d ..."
            ,sizeVecPal,m_stDados.nivel );
        //
        // Esta operação associa todos os descendentes respectivos ao nível passado 
        // no XML de entrada aos contatos pais da palitagem carregada da view
        // AtendimentoRelatorio7V01
        //
        if ( m_vlDados.idContatoOrigem != -1)
                vecContato.push_back(m_stDados.idContatoOrigem);

        for ( i = 0; i < sizeVecPal; i++ )
        {
            BuscarPai(vecPal.at(i).idContato,m_stDados.nivel,itMapAC);

            sizeVecContato = vecContato.size();

            if ( vecPal.at(i).idContato != 1 
                && (m_stDados.nivel <= 1 
                   || itMapAC->second.idContatoPai == m_stDados.idContatoPai) )
            {
                strcpy(vecPal.at(i).nmContato,itMapAC->second.nmContato);

                vecPal.at(i).idContatoPai = itMapAC->second.idContato;

                vecPal.at(i).possuiDescendente  = itMapAC->second.possuiDescendente;
            }
            else if (( vecPal.at(i).idContato != 1 )
                && (m_stDados.nivel > 1 ) && (-1 == m_vlDados.idContatoPai))
            {

                if ( m_vlDados.idContatoOrigem != -1)
                {

                    ULOG( "idContatoOrigem=%d idContato=%d"
                        ,m_stDados.idContatoOrigem,vecPal.at(i).idContato);

                    itMapACOrigem = mapAC.find(vecPal.at(i).idContato);
                    while (itMapACOrigem->second.idContatoPai != 1)
                    {
                        if (m_stDados.idContatoOrigem == itMapACOrigem->second.idContatoPai)
                        {
                            if (itMapAC->second.nivel == m_stDados.nivel)
                            {
                                strcpy(vecPal.at(i).nmContato,itMapAC->second.nmContato);

                                vecPal.at(i).idContatoPai = itMapAC->second.idContato;

                                vecPal.at(i).possuiDescendente  = itMapAC->second.possuiDescendente;
                            }
                        }
                        itMapACOrigem = mapAC.find(itMapACOrigem->second.idContatoPai);
                    }
                }
                else if (itMapAC->second.nivel == m_stDados.nivel)
                {
                    strcpy(vecPal.at(i).nmContato,itMapAC->second.nmContato);

                    vecPal.at(i).idContatoPai = itMapAC->second.idContato;

                    vecPal.at(i).possuiDescendente  = itMapAC->second.possuiDescendente;
                }
            }
        }

        ULOG( "Agrupando resultados ..." );

        // Gera palitagem agrupada
        for ( i = 0; i < sizeVecPal; i++ )
        {
            ULOG( "%s %s %s"
                  ,vecPal.at(i).data
                  ,vecPal.at(i).hora
                  ,vecPal.at(i).nmContato );

            // Se pertence ao grupo selecionado, agrupa
            if ( vecPal.at(i).nmContato[0] )
            {
                AgruparLinha(vecPal.at(i));
            }
        }


        // Faz o acumulo de quantidades de horas anteriores 
        AcumularHorasAnteriores();

        // Gera saida XML
        GerarSaidaXML();
    }
    catch (MapaErroException &mapaErroException)
    {
        ULOGE( "Excecao devido a '%s'"
                ,mapaErroException.obterMsgErroPadrao() );

        throw new TuxBasicSvcException((char*)mapaErroException.obterLabelErro()
                                      ,mapaErroException.obterMsgErroPadrao());
    }
    catch (...)
    {
        ULOGE( "Excecao devido a erro desconhecido");

        throw new TuxBasicSvcException("04E0000","Erro desconhecido");
    }
}

void cWFAtendimentoRel7::AgruparLinha(Palitagem &palitagem)
{
    string chave;
    char hora[5];
    MAP_GPAL::iterator itMapGPal;
    PalitagemAgrupada palitagemAgrupada;

    chave = (string)palitagem.hora + (string)palitagem.nmContato;

    ULOG( "chave=%s",chave.c_str() );

    sprintf(hora,"%.2s%.2s",palitagem.hora,palitagem.hora+3);

    itMapGPal = mapGPal.find(chave);

    if ( itMapGPal == mapGPal.end() )
    {
        ULOG( "Inserindo palitagem ..." );

        strcpy(palitagemAgrupada.hora,palitagem.hora);
        strcpy(palitagemAgrupada.descricao,palitagem.nmContato);

        palitagemAgrupada.qtdeHoje = 0;
        palitagemAgrupada.qtdeData1 = 0;
        palitagemAgrupada.qtdeData2 = 0;

        palitagemAgrupada.idContatoPai = palitagem.idContatoPai;
        palitagemAgrupada.possuiDescendente = palitagem.possuiDescendente;

        if ( !strcmp(palitagem.data,m_stDados.data1) )
        {
            palitagemAgrupada.qtdeData1 = palitagem.quantidade;
        }
        
        if ( !strcmp(palitagem.data,m_stDados.data2) )
        {
            palitagemAgrupada.qtdeData2 = palitagem.quantidade;
        }

        if ( !strcmp(palitagem.data,m_stDados.dataHoje) )
        {
            if ( strcmp(hora,m_stDados.horaAtual) <= 0 )
            {
                palitagemAgrupada.qtdeHoje = palitagem.quantidade;
            }
        }
        //else
        //{
        //    throw MapaErroException(__LINE__,ERRO_DATA_INVALIDA,__FILE__);
        //}

        // Insere item consolidado
        mapGPal[chave] = palitagemAgrupada;

        // Armazena as chaves
        vecKeys.push_back(chave);
    }
    else
    {
        ULOG( "Acumulando palitagem ..." );

        memcpy(&palitagemAgrupada,&(itMapGPal->second),sizeof(palitagemAgrupada));

        if ( !strcmp(palitagem.data,m_stDados.data1) )
        {
            palitagemAgrupada.qtdeData1 += palitagem.quantidade;
        }

        if ( !strcmp(palitagem.data,m_stDados.data2) )
        {
            palitagemAgrupada.qtdeData2 += palitagem.quantidade;
        }

        if ( !strcmp(palitagem.data,m_stDados.dataHoje) )
        {
            if ( strcmp(palitagemAgrupada.hora,m_stDados.horaAtual) <= 0 )
            {
                palitagemAgrupada.qtdeHoje += palitagem.quantidade;
            }
        }

        //else
        //{
        //    throw MapaErroException(__LINE__,ERRO_DATA_INVALIDA,__FILE__);
        //}

        // Se passar por aqui ao menos uma palitagem com idContatoPai com descendente(s)
        // informa que o agrupamento possui descendente(s)
        if ( palitagem.possuiDescendente )
        {
            palitagemAgrupada.possuiDescendente = true;
        }

        mapGPal[itMapGPal->first] = palitagemAgrupada;
    }

    //TotalizarHora(palitagem);
}

/*

  AcumularHorasAnteriores já esta fazendo este trabalho (é mais rápido)

void cWFAtendimentoRel7::TotalizarHora(Palitagem &palitagem)
{
    string chave;
    TTMAP_GPAL::iterator itTtMapGPal;
    TotalPalitagemAgrupada totalPalitagemAgrupada;

    chave = (string)palitagem.hora;

    ULOG("chave=%s",chave.c_str());

    itTtMapGPal = TtMapGPal.find(chave);

    if ( itTtMapGPal == TtMapGPal.end() )
    {
        ULOG(mensagemSimples("Inserindo totalizador palitagem ..."));

        strcpy(totalPalitagemAgrupada.hora,palitagem.hora);

        totalPalitagemAgrupada.qtdeHoje = 0;
        totalPalitagemAgrupada.qtdeData1 = 0;
        totalPalitagemAgrupada.qtdeData2 = 0;

        if ( !strcmp(palitagem.data,m_stDados.dataHoje) )
        {
            totalPalitagemAgrupada.qtdeHoje = palitagem.quantidade;
        }
        else if ( !strcmp(palitagem.data,m_stDados.data1) )
        {
            totalPalitagemAgrupada.qtdeData1 = palitagem.quantidade;
        }
        else if ( !strcmp(palitagem.data,m_stDados.data2) )
        {
            totalPalitagemAgrupada.qtdeData2 = palitagem.quantidade;
        }
        else
        {
            throw MapaErroException(__LINE__,ERRO_DATA_INVALIDA,__FILE__);
        }

        // Insere item consolidado
        TtMapGPal[chave] = totalPalitagemAgrupada;

    }
    else
    {
        ULOG(mensagemSimples("Acumulando totalizador palitagem ..."));

        memcpy(&totalPalitagemAgrupada,&(itTtMapGPal->second),sizeof(totalPalitagemAgrupada));

        if ( !strcmp(palitagem.data,m_stDados.dataHoje) )
        {
            totalPalitagemAgrupada.qtdeHoje += palitagem.quantidade;
        }
        else if ( !strcmp(palitagem.data,m_stDados.data1) )
        {
            totalPalitagemAgrupada.qtdeData1 += palitagem.quantidade;
        }
        else if ( !strcmp(palitagem.data,m_stDados.data2) )
        {
            totalPalitagemAgrupada.qtdeData2 += palitagem.quantidade;
        }
        else
        {
            throw MapaErroException(__LINE__,ERRO_DATA_INVALIDA,__FILE__);
        }

        TtMapGPal[itTtMapGPal->first] = totalPalitagemAgrupada;
    }
}
*/

void cWFAtendimentoRel7::AcumularHorasAnteriores()
{
    ULOG_START( "cWFAtendimentoRel7::AcumularHorasAnteriores()" );

    long sm;
    char hora[5];
    MAP_GPAL::iterator itMapGPal;
    MAP_GPAL::iterator itMapGPal2;
    string chave;
    TTMAP_GPAL::iterator itTtMapGPal;
    TotalPalitagemAgrupada totalPalitagemAgrupada;

    if ( mapGPal.size() <= 0L )
    {
        ULOG( "NIVEL SEM DADOS" );
        ULOG_END( "cWFAtendimentoRel7::AcumularHorasAnteriores()" );
        return;
    }

    ULOG( "=============================================" );
    ULOG( "==> ANTES" );

    itMapGPal = mapGPal.end();
    itMapGPal--;
    sm=mapGPal.size();

    while ( sm )
    {
        ULOG( "hora=%s,descricao=%s,qtdeHoje=%d,qtdeData1=%d,qtdeData2=%d"
                    ,itMapGPal->second.hora
                    ,itMapGPal->second.descricao
                    ,itMapGPal->second.qtdeHoje
                    ,itMapGPal->second.qtdeData1
                    ,itMapGPal->second.qtdeData2);

        itMapGPal--;
        sm--;
    }

    itMapGPal = mapGPal.end();
    itMapGPal--;
    sm=mapGPal.size();

    while ( sm )
    {
        itMapGPal2 = itMapGPal;
        itMapGPal2--;

        long sm2 = sm - 1L;

        //ULOG((Endereco(__LINE__,__FILE__).
        //    mensagem("sm2=%d",sm2);

        while ( sm2 > 0L )
        {
            //ULOG((Endereco(__LINE__,__FILE__).
            //    mensagem("itMapGPal->second.descricao=%s,itMapGPal2->second.descricao=%s"
            //            ,itMapGPal->second.descricao
            //            ,itMapGPal2->second.descricao);

            sprintf(hora,"%.2s%.2s",itMapGPal->second.hora,itMapGPal->second.hora+3);

            if ( strcmp(itMapGPal->second.descricao,itMapGPal2->second.descricao) == 0 )
            {   // somatorio para a quantidade do dia de hoje
                if (strcmp(hora,m_stDados.horaAtual) <= 0 )
                {
                   if ( itMapGPal->second.qtdeHoje )
                   {
                       itMapGPal->second.qtdeHoje  += itMapGPal2->second.qtdeHoje;
                   }
                }

                if ( itMapGPal->second.qtdeData1 )
                {
                    itMapGPal->second.qtdeData1 += itMapGPal2->second.qtdeData1;
                }

                if ( itMapGPal->second.qtdeData2 )
                {
                    itMapGPal->second.qtdeData2 += itMapGPal2->second.qtdeData2;
                }
            }

            itMapGPal2--;
            sm2--;
        }

        itMapGPal--;
        sm--;
    }

    ULOG( "=============================================" );
    ULOG( "==> DEPOIS" );

    itMapGPal = mapGPal.end();
    itMapGPal--;
    sm=mapGPal.size();

    while ( sm )
    {
        ULOG( "hora=%s,descricao=%s,qtdeHoje=%d,qtdeData1=%d,qtdeData2=%d"
                    ,itMapGPal->second.hora
                    ,itMapGPal->second.descricao
                    ,itMapGPal->second.qtdeHoje
                    ,itMapGPal->second.qtdeData1
                    ,itMapGPal->second.qtdeData2);

        itMapGPal--;
        sm--;
    }

    // Totais por hora
    itMapGPal = mapGPal.begin();

    while ( itMapGPal != mapGPal.end() )
    {
        chave = (string)(itMapGPal->second.hora);

         itTtMapGPal = TtMapGPal.find(chave);

        if ( itTtMapGPal == TtMapGPal.end() )
        {
            strcpy(totalPalitagemAgrupada.hora,itMapGPal->second.hora);

            totalPalitagemAgrupada.qtdeHoje = itMapGPal->second.qtdeHoje;
            totalPalitagemAgrupada.qtdeData1 = itMapGPal->second.qtdeData1;
            totalPalitagemAgrupada.qtdeData2 = itMapGPal->second.qtdeData2;

            TtMapGPal[chave] = totalPalitagemAgrupada;

            ULOG( "totalPalitagemAgrupada:hora=%s,qtdeHoje=%d,qtdeData1=%d,qtdeData2=%d (inserido)"
                        ,totalPalitagemAgrupada.hora
                        ,totalPalitagemAgrupada.qtdeHoje
                        ,totalPalitagemAgrupada.qtdeData1
                        ,totalPalitagemAgrupada.qtdeData2);
        }
        else
        {
            itTtMapGPal->second.qtdeHoje += itMapGPal->second.qtdeHoje;
            itTtMapGPal->second.qtdeData1 += itMapGPal->second.qtdeData1;
            itTtMapGPal->second.qtdeData2 += itMapGPal->second.qtdeData2;

            ULOG( "totalPalitagemAgrupada:hora=%s,qtdeHoje=%d,qtdeData1=%d,qtdeData2=%d (acumulado)"
                        ,itTtMapGPal->second.hora
                        ,itTtMapGPal->second.qtdeHoje
                        ,itTtMapGPal->second.qtdeData1
                        ,itTtMapGPal->second.qtdeData2);
        }

        itMapGPal++;
    }

    // Totais por hora
    //for ( itMapGPal = mapGPal.begin(); itMapGPal++; itMapGPal != mapGPal.end() )
    //{
    //    for ( itMapGPal2 = itMapGPal+1; itMapGPal2++; itMapGPal2 != mapGPal.end() )
    //    {
    //        if ( strcmp(itMapGPal2->second.descricao,itMapGPal->second.descricao)
    //        {
    //            string descricao = itMapGPal->second.descricao;
    //
    //            strcpy(itMapGPal->second.descricao,itMapGPal2->second.descricao);
    //
    //            strcpy(itMapGPal2->second.descricao,descricao.c_str());
    //        }
    //    }
    //}

    ULOG( "SORT POR DESCRICAO ==>" );

    // ordena por descrição
    itMapGPal = mapGPal.begin();

    while ( itMapGPal != mapGPal.end() )
    {
        itMapGPal++;

        itMapGPal2 = itMapGPal;

        itMapGPal--;

        while ( itMapGPal2 != mapGPal.end() )
        {
            if ( strcmp(itMapGPal2->second.descricao,itMapGPal->second.descricao) < 0 )
            {
                PalitagemAgrupada palitagemAgrupadaTemp;

                memcpy( &palitagemAgrupadaTemp.descricao,itMapGPal->second.descricao,TAM_VARCHAR );
                memcpy( &palitagemAgrupadaTemp.hora, itMapGPal->second.hora,TAM_HORA );
                palitagemAgrupadaTemp.idContatoPai = itMapGPal->second.idContatoPai;
                palitagemAgrupadaTemp.possuiDescendente = itMapGPal->second.possuiDescendente;
                palitagemAgrupadaTemp.qtdeData1 = itMapGPal->second.qtdeData1;
                palitagemAgrupadaTemp.qtdeData2 = itMapGPal->second.qtdeData2;
                palitagemAgrupadaTemp.qtdeHoje = itMapGPal->second.qtdeHoje;

                memcpy( itMapGPal->second.descricao,itMapGPal2->second.descricao,TAM_VARCHAR );
                memcpy( itMapGPal->second.hora,itMapGPal2->second.hora,TAM_HORA );
                itMapGPal->second.idContatoPai = itMapGPal2->second.idContatoPai;
                itMapGPal->second.possuiDescendente = itMapGPal2->second.possuiDescendente;
                itMapGPal->second.qtdeData1 = itMapGPal2->second.qtdeData1;
                itMapGPal->second.qtdeData2 = itMapGPal2->second.qtdeData2;
                itMapGPal->second.qtdeHoje = itMapGPal2->second.qtdeHoje;

                memcpy( itMapGPal2->second.descricao,&palitagemAgrupadaTemp.descricao,TAM_VARCHAR );
                memcpy( itMapGPal2->second.hora,&palitagemAgrupadaTemp.hora,TAM_HORA );
                itMapGPal2->second.idContatoPai = palitagemAgrupadaTemp.idContatoPai;
                itMapGPal2->second.possuiDescendente = palitagemAgrupadaTemp.possuiDescendente;
                itMapGPal2->second.qtdeData1 = palitagemAgrupadaTemp.qtdeData1;
                itMapGPal2->second.qtdeData2 = palitagemAgrupadaTemp.qtdeData2;
                itMapGPal2->second.qtdeHoje = palitagemAgrupadaTemp.qtdeHoje;
            }

            itMapGPal2++;
        }

        itMapGPal++;
    }

    ULOG( "<== SORT POR DESCRICAO" );

    // ordena por hora em ordem decrescente
    ULOG(  "SORT POR HORA ==>" );

    itMapGPal = mapGPal.begin();

    while ( itMapGPal != mapGPal.end() )
    {
        itMapGPal++;

        itMapGPal2 = itMapGPal;

        itMapGPal--;

        while ( itMapGPal2 != mapGPal.end() )
        {
            if ( strcmp(itMapGPal2->second.hora,itMapGPal->second.hora) < 0 )
            {
                PalitagemAgrupada palitagemAgrupadaTemp;

                memcpy( &palitagemAgrupadaTemp.descricao,itMapGPal->second.descricao,TAM_VARCHAR );
                memcpy( &palitagemAgrupadaTemp.hora, itMapGPal->second.hora,TAM_HORA );
                palitagemAgrupadaTemp.idContatoPai = itMapGPal->second.idContatoPai;
                palitagemAgrupadaTemp.possuiDescendente = itMapGPal->second.possuiDescendente;
                palitagemAgrupadaTemp.qtdeData1 = itMapGPal->second.qtdeData1;
                palitagemAgrupadaTemp.qtdeData2 = itMapGPal->second.qtdeData2;
                palitagemAgrupadaTemp.qtdeHoje = itMapGPal->second.qtdeHoje;

                memcpy( itMapGPal->second.descricao,itMapGPal2->second.descricao,TAM_VARCHAR );
                memcpy( itMapGPal->second.hora,itMapGPal2->second.hora,TAM_HORA );
                itMapGPal->second.idContatoPai = itMapGPal2->second.idContatoPai;
                itMapGPal->second.possuiDescendente = itMapGPal2->second.possuiDescendente;
                itMapGPal->second.qtdeData1 = itMapGPal2->second.qtdeData1;
                itMapGPal->second.qtdeData2 = itMapGPal2->second.qtdeData2;
                itMapGPal->second.qtdeHoje = itMapGPal2->second.qtdeHoje;

                memcpy( itMapGPal2->second.descricao,&palitagemAgrupadaTemp.descricao,TAM_VARCHAR );
                memcpy( itMapGPal2->second.hora,&palitagemAgrupadaTemp.hora,TAM_HORA );
                itMapGPal2->second.idContatoPai = palitagemAgrupadaTemp.idContatoPai;
                itMapGPal2->second.possuiDescendente = palitagemAgrupadaTemp.possuiDescendente;
                itMapGPal2->second.qtdeData1 = palitagemAgrupadaTemp.qtdeData1;
                itMapGPal2->second.qtdeData2 = palitagemAgrupadaTemp.qtdeData2;
                itMapGPal2->second.qtdeHoje = palitagemAgrupadaTemp.qtdeHoje;
            }

            itMapGPal2++;
        }

        itMapGPal++;
    }

    ULOG( "<== SORT POR HORA" );

    // Resultado do sort
    ULOG( "RESULTADO DO SORT ==>" );

    itMapGPal = mapGPal.begin();

    while ( itMapGPal != mapGPal.end() )
    {
        ULOG( "hora=%s,descricao=%s,qtdeHoje=%d,qtdeData1=%d,qtdeData2=%d"
                    ,itMapGPal->second.hora
                    ,itMapGPal->second.descricao
                    ,itMapGPal->second.qtdeHoje
                    ,itMapGPal->second.qtdeData1
                    ,itMapGPal->second.qtdeData2);

        itMapGPal++;
    }

    ULOG_END( "cWFAtendimentoRel7::AcumularHorasAnteriores()" );
}

void cWFAtendimentoRel7::GerarSaidaXML()
{
    ULOG_START( "cWFAtendimentoRel7::GerarSaidaXML()" );

    int ctRegs = 0;
    int pagina = pPagina.pageNumber; // Pagina Inicial
    char dsTituloRelatorio[34] = "Palitagem ";
    char dsNivel[24];
    string horaAnterior;
    int sizeKeys = vecKeys.size();
    MAP_GPAL::iterator itMapGPal;
    TTMAP_GPAL::iterator itTtMapGPal;

    saida->createTag("WFRelatorioDinamicoVO");
    saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

    if ( sizeKeys > 0 )
    {
        sprintf(dsNivel,"Nível %d",m_stDados.nivel);
        strcat(dsTituloRelatorio,dsNivel);

        saida->addItem("dsTituloRelatorio", dsTituloRelatorio);

        saida->addItem("ultimoNivel", m_stDados.nivel == nivelMax ? SIM : NAO);

            saida->createTag("ColunasRelatorio");
                saida->createTag("idColuna");
                saida->closeTag();
                saida->addItem("dsColuna","Hora");
            saida->closeTag();

            saida->createTag("ColunasRelatorio");
                saida->addItem("idColuna","idContatoPai");
                saida->addItem("dsColuna",dsNivel);
            saida->closeTag();

            saida->createTag("ColunasRelatorio");
                saida->createTag("idColuna");
                saida->closeTag();
                saida->addItem("dsColuna",m_stDados.dataHoje);
            saida->closeTag();

            saida->createTag("ColunasRelatorio");
                saida->createTag("idColuna");
                saida->closeTag();
                saida->addItem("dsColuna",m_stDados.data1);
            saida->closeTag();

            saida->createTag("ColunasRelatorio");
                saida->createTag("idColuna");
                saida->closeTag();
                saida->addItem("dsColuna",m_stDados.data2);
            saida->closeTag();

        itMapGPal = mapGPal.find(vecKeys.at(0));

        if ( itMapGPal == mapGPal.end() )
        {
            throw MapaErroException(__LINE__,ERRO_CHAVE_INVALIDA,__FILE__);
        }

        horaAnterior = itMapGPal->second.hora;

        int PosPagina =  pagina * NRO_MAX_REGS;
        int RegInicial = PosPagina - NRO_MAX_REGS;  
        
        ULOG( "Pagina solicitada [%d], Nro. Maximo de Registros por pagina [%d]",pagina,NRO_MAX_REGS );
        ULOG( "Elementos em memoria [%d], posicao do Registro Inicial [%d]",sizeKeys,RegInicial );

        /*
         * Alteracao para envio do XML em blocos.
         *
         * for ( int i = 0; i < sizeKeys; i++ )
         *
         */

        pPagina.hasNext = 0;
        for ( int i = RegInicial; i < sizeKeys; i++ )
        {
            if ( ctRegs > NRO_MAX_REGS )
            {
                pPagina.hasNext = 1;
                break;
            }

            itMapGPal = mapGPal.find(vecKeys.at(i));

            if ( itMapGPal == mapGPal.end() )
            {
                throw MapaErroException(__LINE__,ERRO_CHAVE_INVALIDA,__FILE__);
            }

            // Controla a mudança de hora
            if ( strcmp(itMapGPal->second.hora,horaAnterior.c_str()) )
            {
                 // Total acumulado na hora (HH:MM)
                itTtMapGPal = TtMapGPal.find(horaAnterior.c_str());

                if ( itTtMapGPal == TtMapGPal.end() )
                {
                    throw MapaErroException(__LINE__,ERRO_CHAVE_INVALIDA,__FILE__);
                }

                GerarTagValores(itTtMapGPal->second.hora
                               ,0
                               ,DESC_TOTAL
                               ,itTtMapGPal->second.qtdeHoje
                               ,itTtMapGPal->second.qtdeData1
                               ,itTtMapGPal->second.qtdeData2
                               ,false);

                horaAnterior = itMapGPal->second.hora;

                ULOG( "xml=%s %s %d %d %d"
                                 ,itTtMapGPal->second.hora
                                 ,DESC_TOTAL
                                 ,itTtMapGPal->second.qtdeHoje
                                 ,itTtMapGPal->second.qtdeData1
                                 ,itTtMapGPal->second.qtdeData2);
            }

            GerarTagValores(itMapGPal->second.hora
                           ,itMapGPal->second.idContatoPai
                           ,itMapGPal->second.descricao
                           ,itMapGPal->second.qtdeHoje
                           ,itMapGPal->second.qtdeData1
                           ,itMapGPal->second.qtdeData2
                           ,itMapGPal->second.possuiDescendente);

            ULOG( "xml=%s %s %d %d %d"
                             ,itMapGPal->second.hora
                             ,itMapGPal->second.descricao
                             ,itMapGPal->second.qtdeHoje
                             ,itMapGPal->second.qtdeData1
                             ,itMapGPal->second.qtdeData2);
            
            ctRegs++;
        } // for ( int i = 0; i < sizeKeys; i++ )
    }  // if ( sizeKeys > 0 )
                                
	ULOG( "Enviou [%d] linhas", ctRegs);
	                                
    if ( horaAnterior.size() ) 
    {
        // Total acumulado na hora da última linha (HH:MM)
        itTtMapGPal = TtMapGPal.find(horaAnterior.c_str());

        if ( itTtMapGPal == TtMapGPal.end() )
        {
            throw MapaErroException(__LINE__,ERRO_CHAVE_INVALIDA,__FILE__);
        }

        GerarTagValores(itTtMapGPal->second.hora
                       ,0
                       ,DESC_TOTAL
                       ,itTtMapGPal->second.qtdeHoje
                       ,itTtMapGPal->second.qtdeData1
                       ,itTtMapGPal->second.qtdeData2
                       ,false);

        ULOG( "xml=%s %s %d %d %d"
                         ,itTtMapGPal->second.hora
                         ,DESC_TOTAL
                         ,itTtMapGPal->second.qtdeHoje
                         ,itTtMapGPal->second.qtdeData1
                         ,itTtMapGPal->second.qtdeData2);
    }

    saida->createTag( "Paginacao" );
        saida->addProp("xmlns", "cliente.fo.vivo.com.br/vo");
        saida->addItem( "pageNumber",pPagina.pageNumber );
        saida->addItem( "hasNext",pPagina.hasNext );
    saida->closeTag();

    saida->closeTag();

    ULOG_END( "cWFAtendimentoRel7::GerarSaidaXML()" );

}

void cWFAtendimentoRel7::BuscarPai(int _idContato,int _nivel,MAP_AC::iterator &_itMapAC)
{
    _itMapAC = mapAC.find(_idContato);

    if ( _itMapAC == mapAC.end() )
    {
        if ( _idContato == 1 )
        {
            return;
        }

        throw MapaErroException(__LINE__,ERRO_ARVORE,__FILE__);
    }

    if ( _itMapAC->second.nivel != _nivel && 1 != _itMapAC->second.idContatoPai )
    {
        ULOG( "idContato=%d,idContatoPai=%d,Nivel=%d"
                    ,_idContato,_itMapAC->second.idContatoPai,_nivel);

        BuscarPai(_itMapAC->second.idContatoPai,_nivel,_itMapAC);
    }
}

void cWFAtendimentoRel7::PadronizarData1( )
{
    char data[sizeof(m_stDados.data1)+1];
    char *p, *j;

    strcpy(data,m_stDados.data1);

    if ( p = strstr(data,"/"), !p )
    {
        m_vlDados.data1 = -1;
        return;
    }

    j = data;
    *p = 0;
    int dia = atoi(j);

    j = p+1;
    if ( p = strstr(j,"/"), !p )
    {
        m_vlDados.data1 = -1;
        return;
    }

    *p = 0;
    int mes = atoi(j);
    int ano = atoi(p+1);

    sprintf(m_stDados.data1,"%02d/%02d/%04d",dia,mes,ano);

    ULOG( "Data de inicio de pesquisa = %s"
            ,m_stDados.data1);
}

void cWFAtendimentoRel7::PadronizarData2( )
{
    char data[sizeof(m_stDados.data2)+1];
    char *p, *j;

    strcpy(data,m_stDados.data2);

    if ( p = strstr(data,"/"), !p )
    {
        m_vlDados.data2 = -1;
        return;
    }

    j = data;
    *p = 0;
    int dia = atoi(j);

    j = p+1;
    if ( p = strstr(j,"/"), !p )
    {
        m_vlDados.data2 = -1;
        return;
    }

    *p = 0;
    int mes = atoi(j);
    int ano = atoi(p+1);

    sprintf(m_stDados.data2,"%02d/%02d/%04d",dia,mes,ano);

    ULOG( "Data de final de pesquisa = %s"
                ,m_stDados.data2);
}

void cWFAtendimentoRel7::PadronizarDataHoje( )
{
    /*

    char data[sizeof(m_stDados.dataHoje)+1];
    char *p, *j;

    strcpy(data,m_stDados.dataHoje);

    if ( p = strstr(data,"/"), !p )
    {
        m_vlDados.dataHoje = -1;
        return;
    }

    j = data;
    *p = 0;
    int dia = atoi(j);

    j = p+1;
    if ( p = strstr(j,"/"), !p )
    {
        m_vlDados.dataHoje = -1;
        return;
    }

    *p = 0;
    int mes = atoi(j);
    int ano = atoi(p+1);

    sprintf(m_stDados.dataHoje,"%02d/%02d/%04d",dia,mes,ano);

    */

    ULOG( "Data de hoje = %s",m_stDados.dataHoje);
}

void cWFAtendimentoRel7::carregaDados()
{
    char *p;
    char tmp[32];

    try
    {
        // Inicializa as estruturas de dados para armazenar os valores
        memset(&m_stDados,0,sizeof(m_stDados));
        memset(&m_vlDados,-1,sizeof(m_vlDados));

        if (p=tx.walkTree( entrada, "dtInicio", 0 ),p) 
        {
            sprintf(m_stDados.data1,"%.*s",sizeof(m_stDados.data1)-1,p);
            m_vlDados.data1 = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "dtFim", 0 ),p) 
        {
            sprintf(m_stDados.data2,"%.*s",sizeof(m_stDados.data2)-1,p);
            m_vlDados.data2 = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "nmGrupo", 0 ),p) 
        {
            sprintf(m_stDados.nmGrupo,"%.*s",sizeof(m_stDados.nmGrupo)-1,p);
            m_vlDados.nmGrupo = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "sgUF", 0 ),p) 
        {
			if (strlen(p) > 0)
			{
				sprintf(m_stDados.sgUF,"%.*s",sizeof(m_stDados.sgUF)-1,p);
				m_vlDados.sgUF = 1;
			}
           XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "idContatoPai", 0 ),p) 
        {
			if (strlen(p) > 0)
			{
				m_stDados.idContatoPai = atoi(p);
				m_vlDados.idContatoPai = 1;
			}
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
        {
            m_stDados.idGrupo = atoi(p);
            m_vlDados.idGrupo = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "nivel", 0 ),p) 
        {
            m_stDados.nivel = atoi(p);
            m_vlDados.nivel = 1;
            XMLString::release(&p);
        }

        ULOG( "dtInicio = '%s'",m_vlDados.data1 == 1 ? m_stDados.data1:"vazio");

        ULOG( "data2 = '%s'",m_vlDados.data2 == 1 ? m_stDados.data2:"vazio");

        ULOG( "nmGrupo = '%s'",m_vlDados.nmGrupo == 1 ? m_stDados.nmGrupo:"vazio");

        ULOG( "sgUF = '%s'",m_vlDados.sgUF == 1 ? m_stDados.sgUF:"vazio");

        ULOG( "idContatoPai = '%s'",m_vlDados.idContatoPai == 1 ? itoa(m_stDados.idContatoPai,tmp,10):"vazio");

        ULOG( "idGrupo = '%s'",m_vlDados.idGrupo == 1 ? itoa(m_stDados.idGrupo,tmp,10):"vazio");

        ULOG( "nivel = '%s'",m_vlDados.nivel == 1 ? itoa(m_stDados.nivel,tmp,10):"vazio");
    }
    catch (...)
    {
        throw MapaErroException(__LINE__,ERRO_INDEFINIDO,__FILE__);
    }

}

void cWFAtendimentoRel7::carregaDadosDom(char *node)
{
    DOMNode *dn;
    int index = 0;
    char *p0,*p1;
    char tmp[32];

    try
    {
        while ( dn = tx.walkDOM(entrada,node,index++ ) )
        {
            if ( p0 = tx.walkTree(dn,"idColuna",0),p0 )
            {
                p1 = tx.walkTree(dn,"valor",0);

                if ( !stricmp(p0,"nmGrupo") )
                {
                    if ( p1 )
                    {
                        sprintf(m_stDados.nmGrupo,"%.*s",sizeof(m_stDados.nmGrupo)-1, p1);
                        m_vlDados.nmGrupo = 1;
                        continue;
                    }
                }

                if ( !stricmp(p0,"sgUF") )
                {
					if (strlen( p1) > 0)
					{
                        sprintf(m_stDados.sgUF,"%.*s",sizeof(m_stDados.sgUF)-1, p1);
                        m_vlDados.sgUF = 1;
                        continue;
                    }
                }

                if ( !stricmp(p0,"idContatoPai") )
                {
					if (strlen( p1) > 0)
					{
                        m_stDados.idContatoPai = atoi(p1);
                        m_vlDados.idContatoPai = 1;
                        continue;
                    }
                }

                 if ( !stricmp(p0,"idContatoOrigem") )
                {
					if (strlen( p1) > 0)
					{
                        m_stDados.idContatoOrigem = atoi(p1);
                        m_vlDados.idContatoOrigem = 1;
                        continue;
                    }
                }

			   if ( !stricmp(p0,"idGrupo") )
                {
                    if ( p1 )
                    {
                        m_stDados.idGrupo = atoi(p1);
                        m_vlDados.idGrupo = 1;
                    }
                }
                if ( p1 ) XMLString::release(&p1);
                XMLString::release(&p0);
            }
            else if ( p0 = tx.walkTree(dn,"dsUF",0),p0 )
            {
                sprintf(m_stDados.sgUF,"%.*s",sizeof(m_stDados.sgUF)-1, p0);
                m_vlDados.sgUF = 1;
                XMLString::release(&p0);
            }
        }

        ULOG( "nmGrupo = '%s'",m_vlDados.nmGrupo == 1 ? m_stDados.nmGrupo:"vazio");

        ULOG( "sgUF = '%s'",m_vlDados.sgUF == 1 ? m_stDados.sgUF:"vazio");

        ULOG( "idContatoPai = '%s'",m_vlDados.idContatoPai == 1 ? itoa(m_stDados.idContatoPai,tmp,10):"vazio");

        ULOG( "idGrupo = '%s'",m_vlDados.idGrupo == 1 ? itoa(m_stDados.idGrupo,tmp,10):"vazio");
    }
    catch (...)
    {
        throw MapaErroException(__LINE__,ERRO_INDEFINIDO,__FILE__);
    }
}

void cWFAtendimentoRel7::ConsistirParametros()
{
    if ( -1 == m_vlDados.nivel )
    {
        throw MapaErroException(__LINE__,ERRO_NIVEL,__FILE__);
    }

    if ( !arvoreContatosCarregada && m_stDados.nivel )
    {
        throw MapaErroException(__LINE__,ERRO_CARGA_CONTATOS,__FILE__);
    }

    if ( !arvorePalitagemCarregada && m_stDados.nivel )
    {
        throw MapaErroException(__LINE__,ERRO_CARGA_PALITAGEM,__FILE__);
    }

    if ( -1 == m_vlDados.data1 )
    {
        throw MapaErroException(__LINE__,ERRO_DATA_INICIO,__FILE__);
    }

    if ( -1 == m_vlDados.data2 )
    {
        throw MapaErroException(__LINE__,ERRO_DATA_FIM,__FILE__);
    }
}

void cWFAtendimentoRel7::GerarTagValores(char *hora,int idContatoPai,char*descricao
                                        ,int qtdeHoje,int qtdeData1,int qtdeData2
                                        ,bool possuiDescendente)
{
    saida->createTag("ValoresRelatorio");

        saida->createTag("ValorColuna");
            saida->addItem("valor",hora);
        saida->closeTag();

        saida->createTag("ValorColuna");
            saida->addItem("idValor",idContatoPai);
            saida->addItem("valor",descricao);
        saida->closeTag();

        saida->createTag("ValorColuna");
            saida->addItem("valor",qtdeHoje);
        saida->closeTag();

        saida->createTag("ValorColuna");
            saida->addItem("valor",qtdeData1);
        saida->closeTag();

        saida->createTag("ValorColuna");
            saida->addItem("valor",qtdeData2);
        saida->closeTag();

        saida->addItem("inProximoNivel",possuiDescendente);

    saida->closeTag();
}



void cWFAtendimentoRel7::carregaDadosPaginacao( void )
{
    ULOG_START( "carregaDadosPaginacao()" );

    DOMNode *dn;
    int index = 0;
    char *p0;

    try
    {
        pPagina.pageNumber = 1;
        pPagina.hasNext = 0;
        dn = 0x0;
        dn = tx.walkDOM(entrada,"Paginacao",0 );
        if ( dn != NULL )
        {
            if ( p0 = tx.walkTree(dn,"pageNumber",0),p0 )
            {
                pPagina.pageNumber = atoi(p0);
                if ( pPagina.pageNumber == 0 )
                	pPagina.pageNumber = 1;
                XMLString::release(&p0);
            }
        }
        ULOG( "Solicitando pagina numero [%d]",pPagina.pageNumber );

        ULOG_END( "carregaDadosPaginacao()" );
   }
    catch ( ... )
    {
        throw MapaErroException(__LINE__,ERRO_INDEFINIDO,__FILE__);
    }

}
