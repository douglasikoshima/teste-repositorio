
#include "../include/cObtArvoContatoPC.h"

DECLARE_TUXEDO_SERVICE(WFOBTARVOCONT);

void implWFOBTARVOCONT::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFOBTARVOCONT::Execute()");

    char *pBuffer;
    register int indx;
    int tam = -1;
    bool flagAtualizarRaiz = false;
    Collection resultadoRaiz;
    Collection resultado;
    cObtArvoContatoPC raiz;
    st_ContatoRaiz pdadosRaiz;
    st_Parametros parametros;
    st_ContatoFolha *pdadosResult;

    if ( pBuffer = walkTree( dnode, "idContato", 0 ),pBuffer )
    {
        strncpy(parametros.idContatoPai,pBuffer,sizeof(parametros.idContatoPai)-1);
        parametros.idContatoPai[sizeof(parametros.idContatoPai)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idGrupo", 0 ),pBuffer )
    {
        strncpy(parametros.idGrupo,pBuffer,sizeof(parametros.idGrupo)-1);
        parametros.idGrupo[sizeof(parametros.idGrupo)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idTipoCarteira", 0 ),pBuffer )
    {
        strncpy(parametros.idTipoCarteira,pBuffer,sizeof(parametros.idTipoCarteira)-1);
        parametros.idTipoCarteira[sizeof(parametros.idTipoCarteira)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idSegmentacao", 0 ),pBuffer )
    {
        strncpy(parametros.idSegmentacao,pBuffer,sizeof(parametros.idSegmentacao)-1);
        parametros.idSegmentacao[sizeof(parametros.idSegmentacao)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idTipoLinha", 0 ),pBuffer )
    {
        strncpy(parametros.idTipoLinha,pBuffer,sizeof(parametros.idTipoLinha)-1);
        parametros.idTipoLinha[sizeof(parametros.idTipoLinha)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idUFOperadora", 0 ),pBuffer )
    {
        strncpy(parametros.idUFOperadora,pBuffer,sizeof(parametros.idUFOperadora)-1);
        parametros.idUFOperadora[sizeof(parametros.idUFOperadora)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idTipoRelacionamento", 0 ),pBuffer )
    {
        strncpy(parametros.idTipoRel,pBuffer,sizeof(parametros.idTipoRel)-1);
        parametros.idTipoRel[sizeof(parametros.idTipoRel)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if ( pBuffer = walkTree( dnode, "idTipoSequencia", 0 ),pBuffer )
    {
        strncpy(parametros.idTipoSequencia,pBuffer,sizeof(parametros.idTipoSequencia)-1);
        parametros.idTipoSequencia[sizeof(parametros.idTipoSequencia)-1] = 0;
        XMLString::release(&pBuffer);
    }

    if( atoi(parametros.idTipoCarteira) == 0 )
    {
        strcpy( parametros.idTipoCarteira,"-1" );
    }

    // Segmentação pode conter valor zero.
    // Agosto, 2006 - Cassio
    //if( atoi(parametros.idSegmentacao) == 0 )
    //   strcpy( parametros.idSegmentacao,"-1" );

    if( atoi(parametros.idTipoLinha) == 0 )
    {
        strcpy( parametros.idTipoLinha,"-1" );
    }

    if( atoi(parametros.idTipoSequencia) == 0 )
    {
        strcpy( parametros.idTipoSequencia,"-1" );
    }

    if ( atoi(parametros.idContatoPai) == 0 )
    {
        raiz.obtemWFContatoRaiz( &resultadoRaiz );
        memcpy( (st_ContatoRaiz *)&pdadosRaiz,resultadoRaiz.GetItem(0),sizeof(struct st_ContatoRaiz) );
        strcpy( parametros.idContatoPai, pdadosRaiz.idContatoPai);
    }
    else
    {
        flagAtualizarRaiz = true;
    }

    // Se todas as variáveis forem fornecidas pode ser utilizada uma query mais
    // performática do que a query usada por obtemWFContatoFolha(). O negócio
    // da query de obtemWFContatoFolhaVar() é o mesmo de obtemWFContatoFolha()
    // apenas os tempos de acessos é que são diferentes. - Fev/2006 - Cassio.
    if ( atoi(parametros.idGrupo) > 0 )
    {
       if ( strcmp(parametros.idTipoCarteira,"-1") && strcmp(parametros.idSegmentacao,"-1")
          && strcmp(parametros.idTipoLinha,"-1") && strcmp(parametros.idTipoSequencia,"-1") )
        {
            raiz.obtemWFContatoFolhaVar( &parametros,&resultado,true );
        }
        else
        {
            raiz.obtemWFContatoFolhaVar( &parametros,&resultado,false );
        }
    }
    else
    {
        raiz.obtemWFContatoLista( &parametros,&resultado );
    }

    xml_g->createTag("AdmContatoFolhaVO");
    xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
   
    if (resultado.GetCount() == 0 && flagAtualizarRaiz == false )
    {
        xml_g->addItem( "idContato",(char*)pdadosRaiz.idContato);
        xml_g->addItem( "idNomeContato",(char*)pdadosRaiz.idNomeContato);
        xml_g->addItem( "nmContato",(char*)pdadosRaiz.nmContato);
        xml_g->addItem( "idContatoPai",(char*)pdadosRaiz.idContatoPai);
        xml_g->addItem( "inDisponibilidade",pdadosRaiz.inDisponibilidade );
        xml_g->closeTag();
    }
    else
    {
        for ( indx=0;indx < resultado.GetCount();indx++ )
        {
            pdadosResult  = (st_ContatoFolha *)resultado.GetItem(indx);

            // ULOG("------------------------");
            // ULOG("          idContato='%s'",(char*)pdadosResult->idContato.c_str());
            // ULOG("idContatoHierarquia='%s'",(char*)pdadosResult->idContatoHierarquia.c_str());
            // ULOG("      idNomeContato='%s'",(char*)pdadosResult->idNomeContato.c_str());
            // ULOG("          nmContato='%s'",(char*)pdadosResult->nmContato.c_str());
            // ULOG("    dsMensagemAviso='%s'",(char*)pdadosResult->dsMensagemAviso.c_str());
            // ULOG("       nmUrlContato='%s'",(char*)pdadosResult->nmUrlContato.c_str());
            // ULOG("             dsPath='%s'",(char*)pdadosResult->dsPath.c_str());
            // ULOG("            inFolha=%d",(char*)pdadosResult->inFolha);
            // ULOG("        inPermitido=%d",(char*)pdadosResult->inPermitido);

            if( flagAtualizarRaiz == true )
            {
                strcpy(pdadosRaiz.idContato, pdadosResult->idContato);
                strcpy(pdadosRaiz.idNomeContato, pdadosResult->idNomeContato);
                strcpy(pdadosRaiz.nmContato, pdadosResult->nmContato);
                strcpy(pdadosRaiz.idContatoPai, parametros.idContatoPai);
                strcpy(pdadosRaiz.inDisponibilidade, "0");
            }

            if ( indx == 0 && flagAtualizarRaiz == false )
            {
                xml_g->addItem( "idContato",(char*)pdadosRaiz.idContato);
                xml_g->addItem( "idNomeContato",(char*)pdadosRaiz.idNomeContato);
                xml_g->addItem( "nmContato",(char*)pdadosRaiz.nmContato);
                xml_g->addItem( "idContatoPai",(char*)pdadosRaiz.idContatoPai);
                xml_g->addItem( "inDisponibilidade",pdadosRaiz.inDisponibilidade );
            }

            if ( parametros.idGrupo > 0 )
            {
                xml_g->createTag("AdmContatoFolhaVO");
                xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
                xml_g->addItem( "idContato",(char*)pdadosResult->idContato);
                xml_g->addItem( "idNomeContato",(char*)pdadosResult->idNomeContato);
                xml_g->addItem( "idContatoPai",(char*)pdadosRaiz.idContatoPai);
                xml_g->addItem( "nmContato",(char*)pdadosResult->nmContato);

                tam = strlen(pdadosResult->dsPath);

                if ( tam > 0 )
                {
                    xml_g->addItem( "dsPath",(char*)pdadosResult->dsPath);
                }

                xml_g->addItem( "inFolha",pdadosResult->inFolha );
                xml_g->addItem( "urlFuncionalidade",pdadosResult->nmUrlContatoAcao );

                tam = strlen( pdadosResult->dsMensagemAviso);
                if ( tam > 0 )
                {
                    xml_g->addItem( "mensagem", (char*)pdadosResult->dsMensagemAviso);
                }

                xml_g->addItem( "inRelacionamento",pdadosResult->inPermitido );

                tam = strlen(pdadosResult->nmUrlContato);

                if ( tam > 0 )
                {
                    xml_g->createTag("AdmContatoInformacaoVO");
                    xml_g->addItem("nmURLContatoInformacao",(char*)pdadosResult->nmUrlContato);
                    xml_g->closeTag();
                }
                xml_g->closeTag();
            }
            else
            {
                xml_g->createTag("AdmContatoFolhaVO");
                xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
                xml_g->addItem( "idContato",(char*)pdadosResult->idContato);
                xml_g->addItem( "idNomeContato",(char*)pdadosResult->idNomeContato);
                xml_g->addItem( "idContatoPai",(char*)pdadosRaiz.idContatoPai);
                xml_g->addItem( "nmContato",(char*)pdadosResult->nmContato);

                tam = strlen(pdadosResult->dsPath);

                if ( tam > 0 )
                {
                    xml_g->addItem( "dsPath",(char*)pdadosResult->dsPath);
                }

                xml_g->addItem( "inFolha",pdadosResult->inFolha );
                xml_g->closeTag();
            }
        }
        xml_g->closeTag();

        xml_g->closeTag();
    }

    setStatusCode("09I0000","Obtencao de Arvore de Contato Concluida.");

    ULOG_END("implWFOBTARVOCONT::Execute()");
}
