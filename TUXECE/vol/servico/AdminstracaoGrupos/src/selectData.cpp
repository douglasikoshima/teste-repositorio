/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Benaventes
 * @version $Revision: 1.1.6.4 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/10/09 21:09:57 $
 **/

#include "../include/selectData.h"
#include "../include/CGrupos.h"
#include "../include/CGruposLst.h"
#include "../include/CGruposLst.h"

#include "../../../commons/definesAtendimento.h"  // incidencia 3271

GruposRequest::GruposRequest(XMLGen*pxml):ctype(0),pxml(0L),xtra(0)
{
    GruposRequest::pxml=pxml;
}

GruposRequest::~GruposRequest(){}

int GruposRequest::SetQueryType( char ctype )
{
    ULOG_START("GruposRequest::SetQueryType()"); 
   
    int ret = 1 ;
   
    if ( ctype!='G' && ctype!='A' && ctype!='T' &&
         ctype!='R' && ctype!='F' && ctype!='C' )
    {
       ret = 0;
    }
	
   GruposRequest::ctype=ctype;

   ULOG_END("GruposRequest::SetQueryType()"); 

   return ret ;

}

int GruposRequest::QueryData( char*pnode,int iFiltroGrupos )
{
    ULOG_START("GruposRequest::QueryData()"); 
   
	GruposRequest::pnode=pnode;
	if( ctype=='G'||ctype=='F' )
	{
	   if ( iFiltroGrupos == 1 )
		   QueryGrupos();
		else
		   QueryGruposContato();
	}
	if(ctype=='A'||ctype=='F')
		QueryTypes(GetGrupoType("ABERTURA"),"GruposAbertura");
	if(ctype=='T'||ctype=='F')
		QueryTypes(GetGrupoType("TRATAMENTO"),"GruposTratamento");
	if(ctype=='R'||ctype=='F')
		QueryTypes(GetGrupoType("RETORNO"),"GruposRetorno");
	if(ctype=='C')
	{
		xtra=1;
		QueryTypes(GetGrupoType("TRATAMENTO"),"GruposTratamento");
	}
	
    QueryFluxosAtendimento();

	ULOG_END("GruposRequest::QueryData()"); 

	return 1;
}

/***********************************************************************/

GruposWriter::GruposWriter(char*idusr):idUsr(idusr)
{}
GruposWriter::~GruposWriter()
{}

int GruposWriter::WorkWithGrupo(
                                 DOMNode * node,
                                 long typeID, 
                                 long grID, 
                                 CGruposLst &oGrpsLst )
{

	return WriteGrupos(node,typeID, grID, oGrpsLst);

}

int GruposWriter::BatchWriter( DOMNode * dnode )
{
   ULOG_START("GruposWriter::BatchWriter()"); 

   CGrupos    oGrpsAbert;
   CGrupos    oGrpsTrata;
   CGrupos    oGrpsRetor;
   
   CGruposLst oGrpsLstAbert;
   CGruposLst oGrpsLstTrata;
   CGruposLst oGrpsLstRetor;

   DOMNode * subnode;
   
   long itemID;
   long idContato;
   long idx,nxt;
   long incluidos;
   
   long nxtValidacao;
   string idGruposValidacao;
   char valor[65];
   
   char*ptreeID;

	subnode=walkDOM(dnode,"ArvoreContatoVO");
	if(subnode)
	{
        ptreeID=walkTree(subnode,"codigo",0);
        idContato = atol(ptreeID);
        XMLString::release(&ptreeID);

        ULOG("idContato [%lu]\n", idContato );     
      
		// Carrega Todos Grupos Relacionados ao idContato
		// Não filtra exclusão lógica, porque pode ser 
		// necessário apenas desmarcar a flag de data
        oGrpsAbert.LoadIdGrupos( idContato, 1 ); // Abertura
        oGrpsTrata.LoadIdGrupos( idContato, 2 ); // Tratamento
        oGrpsRetor.LoadIdGrupos( idContato, 3 ); // Retorno

        ////////////////////////////////////////////////////////////////////////////
	    //subnode=walkDOM(dnode,"FluxoAtendimentoVO");
        char *sgFluxoAtendimento=walkTree(dnode,"sgFluxoAtendimentoAtual",0);
        if ( sgFluxoAtendimento )
        {
            if ( *sgFluxoAtendimento ) WriteFluxoAtendimento(idContato,sgFluxoAtendimento);
            XMLString::release(&sgFluxoAtendimento);
        }
	}

   /*----- Carrega todos os grupos vindos da lista XML -----*/
    subnode=walkDOM(dnode,"GruposTratamento");
    oGrpsLstTrata.ListaGrupos( subnode );

    subnode=walkDOM(dnode,"GruposRetorno");
    oGrpsLstRetor.ListaGrupos( subnode );

    subnode=walkDOM(dnode,"GruposAbertura");
    oGrpsLstAbert.ListaGrupos( subnode );
   
   for ( nxtValidacao=0; nxtValidacao < oGrpsLstAbert.Quantidade(); nxtValidacao++ )
   {
        sprintf( valor,"%lu",oGrpsLstAbert.Registro(nxtValidacao)->idGrupoMemLst );
		//ULOG("Valor [%s]\n", valor);
        if (idGruposValidacao.size()) 
			idGruposValidacao += ", ";
		idGruposValidacao += (string)valor;
   }
   
//   inicio incidencia 3271  
//   esta parte do codigo foi retirada devido a incidencia 3271 que corresponde a validacao da retirada de 
//   todos os grupos de tratamento na aba fase retorno. isto esta definido na documentacao

//   long idTipoFechamento = 0;
//   if (oGrpsLstAbert.Quantidade() > 0)
//   {
//	   idTipoFechamento = QueryFaseFechamento( idContato, idGruposValidacao);	
//   }
//
//   ULOG("TipoFechamento [%lu]\n", idTipoFechamento);
//   
//   if ( idTipoFechamento > 1 )
//   {
//		if (oGrpsLstTrata.Quantidade() == 0)
//		{
//			ULOG("Precisa de Grupos de Tratamento");
//			
//			ULOG_END("GruposWriter::BatchWriter()"); 
//			
//			return -1;
//		}
//   }
//   else
//		if (oGrpsLstTrata.Quantidade() > 0)
//		{
//			ULOG("Nao Precisa de Grupos de Tratamento");
//		}
//  fim incidencia 3271  

	/*
    *--- Identifica Grupos a Serem Removidos ------
    *
    */

   // Abertura
   bool flagTemNaListaXML = false;
   for( idx=0L; idx < oGrpsAbert.Quantidade(); idx++ )
   {
      for ( nxt=0L; nxt < oGrpsLstAbert.Quantidade(); nxt++ )
      {
         //
         //   Existe no banco de dados e tambem veio na Lista XML
         //   Portanto nao vai salva-los do banco de dados
         //
         if ( oGrpsAbert.Registro(idx)->idGrupoMem == oGrpsLstAbert.Registro(nxt)->idGrupoMemLst )
         {
              oGrpsAbert.Registro(idx)->flagListaXml = 1;    // TRUE - Nao remover do banco de dados
              oGrpsLstAbert.Registro(nxt)->flagSalvar = 0;   // FALSE - Nao salvar este grupo
              oGrpsLstAbert.Registro(nxt)->idSeqLst = oGrpsAbert.Registro(idx)->idSeqMem;
			  //ULOG("Atualizar Grupo de Abertura[%lu] com Sequencia [%lu]\n",oGrpsAbert.Registro(idx)->idGrupoMem,oGrpsLstAbert.Registro(nxt)->idSeqLst );
			  flagTemNaListaXML = true;
         }
      }
   }

	// Tratamento
	flagTemNaListaXML=false;
	incluidos=0L;

    for( idx=0L; idx < oGrpsTrata.Quantidade(); idx++ )
    {
        ULOG( "Passou A..." );
        
        for ( nxt=0L; nxt < (oGrpsLstTrata.Quantidade() - incluidos); nxt++ )
        {
            //
            //   Existe no banco de dados e tambem veio na Lista XML
            //   Portanto nao vai salva-los do banco de dados
            //
            ULOG( "Passou A1..." );
            
            if ( oGrpsTrata.Registro(idx)->idGrupoMem == oGrpsLstTrata.Registro(nxt)->idGrupoMemLst )
            {
                ULOG( "Passou A2..." );
                
                oGrpsTrata.Registro(idx)->flagListaXml = 1;    // TRUE - Nao remover do banco de dados
                oGrpsLstTrata.Registro(nxt)->flagSalvar = 0;   // FALSE - Nao salvar este grupo, atualiza sequencia
                oGrpsLstTrata.Registro(nxt)->idSeqLst = oGrpsTrata.Registro(idx)->idSeqMem;
                //ULOG("Atualizar Grupo de Tratamento [%lu] com Sequencia [%lu]\n",oGrpsTrata.Registro(idx)->idGrupoMem,oGrpsLstTrata.Registro(nxt)->idSeqLst );
                flagTemNaListaXML = true;
            } 
        }
        // =====================================================================
        // ==> SIME00000050 - Chamado do ClearQuest, 10/11/2006 - Cassio
        // Não pode remover um grupo da fase de tratamento se este estiver
        // associado a um tipo de fechamento de contato.
        //
        if ( 0 == oGrpsTrata.Registro(idx)->flagListaXml )
        {
            if ( oGrpsTrata.GrupoAssociadoTipoFechamento(idContato
                                                ,oGrpsTrata.Registro(idx)->idGrupoMem) )
            {
                ULOG( "Retornando -3" );
                return -3; 
            }
        }
        // <== SIME00000050
        // =====================================================================

        if ( flagTemNaListaXML == false &&
        ( TemNivelSequencia(oGrpsTrata.Registro(idx)->idSeqMem) > 0 ||
          TemAtendimentosAbertos(idContato, oGrpsTrata.Registro(idx)->idGrupoMem) > 0 ) )
        {
	 		// Marca como deleção lógica
			oGrpsTrata.Registro(idx)->flagListaXml = 1;    // TRUE - Nao remover do banco de dados
			oGrpsLstTrata.AddGrpLst(oGrpsTrata.Registro(idx)->idGrupoMem);
			oGrpsLstTrata.Registro(oGrpsLstTrata.Quantidade()-1)->flagSalvar = 2;   // FALSE - Nao salvar este grupo, atualiza sequencia e dtExclusao
			oGrpsLstTrata.Registro(oGrpsLstTrata.Quantidade()-1)->idSeqLst = oGrpsTrata.Registro(idx)->idSeqMem;
			incluidos++;
	    }
	    flagTemNaListaXML = false;
	}

	// Retorno
	flagTemNaListaXML = false;
	incluidos=0L;

	//   incidencia 3271 inicio 
	//   Esta validacao esta no documento de especificao da incidencia
	//   parte do documento
	//	 Na Fase de Retorno, essa regra também é válida, porém, se existir tipo de
	//   retorno "Retorno pelo Grupo de Retorno" configurado para a Folha, o sistema
	//   não permitirá que todos os grupos sejam desassociados, tendo que ficar ao 
	//   menos um grupo associado a esta fase.
	
	if ( (oGrpsLstRetor.Quantidade() == 0)
	   && oGrpsRetor.ExisteContatoTipoRetorno(idContato, TP_RET_COM_RET_GRP_RET ) == true )
	{
		return -2; 
	}
	// incidencia 3271 fim

	for( idx=0L; idx < oGrpsRetor.Quantidade(); idx++ )
	{
      ULOG( "Passou B..." );
      
      for ( nxt=0L; nxt < (oGrpsLstRetor.Quantidade() - incluidos); nxt++ )
      {
         //
         //   Existe no banco de dados e tambem veio na Lista XML
         //   Portanto nao vai salva-los do banco de dados
         //
         ULOG( "Passou B1..." );
         
         if ( oGrpsRetor.Registro(idx)->idGrupoMem == oGrpsLstRetor.Registro(nxt)->idGrupoMemLst )
         {
              ULOG( "Passou B2..." );
              oGrpsRetor.Registro(idx)->flagListaXml = 1;    // TRUE - Nao remover do banco de dados
              oGrpsLstRetor.Registro(nxt)->flagSalvar = 0;   // FALSE - Nao salvar este grupo
              oGrpsLstRetor.Registro(nxt)->idSeqLst = oGrpsRetor.Registro(idx)->idSeqMem;
              //ULOG("Atualizar Grupo de Retorno [%lu] com Sequencia [%lu]\n",oGrpsRetor.Registro(idx)->idGrupoMem,oGrpsLstRetor.Registro(nxt)->idSeqLst );
			  flagTemNaListaXML = true;
         }
      }

	  if (  flagTemNaListaXML == false
		 && ( TemAtendimentosAbertos(idContato, oGrpsRetor.Registro(idx)->idGrupoMem) > 0 ) )
	  {
	 		// Marca como deleção lógica
			oGrpsRetor.Registro(idx)->flagListaXml = 1;    // TRUE - Nao remover do banco de dados
			oGrpsLstRetor.AddGrpLst(oGrpsRetor.Registro(idx)->idGrupoMem);
			oGrpsLstRetor.Registro(oGrpsLstRetor.Quantidade()-1)->flagSalvar = 2;   // FALSE - Nao salvar este grupo, atualiza sequencia e dtExclusao
			oGrpsLstRetor.Registro(oGrpsLstRetor.Quantidade()-1)->idSeqLst = oGrpsRetor.Registro(idx)->idSeqMem;
			incluidos++;
	  }
	  flagTemNaListaXML = false;
	}

   /*
    *--- Remove Grupos do banco de dados que nao pertencem a lista XML ------
    *
    */

    ////////////////////////////////////////////////////////////////////////////
	// Abertura
	int i;
    string lstContatoGrupo;
    char temp[21];

    i=0;
	for( idx=0; idx < oGrpsAbert.Quantidade(); idx++ )
	{
        if ( i > 499 )
        {
            CleanPreviousFluxoFase( lstContatoGrupo );
            CleanPreviousRetornoSeq( lstContatoGrupo );
            CleanPreviousNivelSeq( lstContatoGrupo );
            CleanPreviousNiveis( lstContatoGrupo );
            CleanPreviousSequencia( lstContatoGrupo );
            CleanPreviousData( lstContatoGrupo );

            i = 0;
            lstContatoGrupo.erase();
        }

	    // Não veio na lista xml então entra na lista de deleção
        if ( oGrpsAbert.Registro(idx)->flagListaXml == 0 )
        {
            if ( i++ ) lstContatoGrupo +=",";
            sprintf(temp,"%ld",oGrpsAbert.Registro(idx)->idContatoGrupoMem);
            lstContatoGrupo +=temp;
        }
	}

	if ( lstContatoGrupo.empty() == false )
	{
		CleanPreviousFluxoFase( lstContatoGrupo );
		CleanPreviousRetornoSeq( lstContatoGrupo );
		CleanPreviousNivelSeq( lstContatoGrupo );
		CleanPreviousNiveis( lstContatoGrupo );
		CleanPreviousSequencia( lstContatoGrupo );
		CleanPreviousData( lstContatoGrupo );
        lstContatoGrupo.erase();
	}

    ////////////////////////////////////////////////////////////////////////////
    // Tratamento
    i=0;
	for( idx=0; idx < oGrpsTrata.Quantidade(); idx++ )
	{
        if ( i > 499 )
        {
            CleanPreviousRetornoSeq( lstContatoGrupo );
            CleanPreviousNivelSeq( lstContatoGrupo );
            CleanPreviousNiveis( lstContatoGrupo );
            CleanPreviousSequencia( lstContatoGrupo );
            CleanPreviousData( lstContatoGrupo );

            i = 0;
            lstContatoGrupo.erase();
        }

	    // Não veio na lista xml então entra na lista de deleção
        if ( oGrpsTrata.Registro(idx)->flagListaXml == 0 )
        {
            if ( i++ ) lstContatoGrupo +=",";
            sprintf(temp,"%ld",oGrpsTrata.Registro(idx)->idContatoGrupoMem);
            lstContatoGrupo +=temp;
        }
	}

	if ( lstContatoGrupo.empty() == false )
	{
		CleanPreviousRetornoSeq( lstContatoGrupo );
		CleanPreviousNivelSeq( lstContatoGrupo );
		CleanPreviousNiveis( lstContatoGrupo );
		CleanPreviousSequencia( lstContatoGrupo );
		CleanPreviousData( lstContatoGrupo );
        lstContatoGrupo.erase();
	}

    ////////////////////////////////////////////////////////////////////////////
    // Retorno
    i=0;
	for( idx=0; idx < oGrpsRetor.Quantidade(); idx++ )
	{
        if ( i > 499 )
        {
            CleanPreviousRetornoSeq( lstContatoGrupo );
            CleanPreviousNivelSeq( lstContatoGrupo );
            CleanPreviousNiveis( lstContatoGrupo );
            CleanPreviousSequencia( lstContatoGrupo );
            CleanPreviousData( lstContatoGrupo );

            i = 0;
            lstContatoGrupo.erase();
        }

	    // Não veio na lista xml então entra na lista de deleção
        if ( oGrpsRetor.Registro(idx)->flagListaXml == 0 )
        {
            if ( i++ ) lstContatoGrupo +=",";
            sprintf(temp,"%ld",oGrpsRetor.Registro(idx)->idContatoGrupoMem);
            lstContatoGrupo +=temp;
        }
	}

	if ( lstContatoGrupo.empty() == false )
	{
		CleanPreviousRetornoSeq( lstContatoGrupo );
		CleanPreviousNivelSeq( lstContatoGrupo );
		CleanPreviousNiveis( lstContatoGrupo );
		CleanPreviousSequencia( lstContatoGrupo );
		CleanPreviousData( lstContatoGrupo );
        lstContatoGrupo.erase();
	}

    ////////////////////////////////////////////////////////////////////////////
    subnode=walkDOM(dnode,"GruposTratamento");
    if ( subnode )
    {
        itemID=GetGrupoType("TRATAMENTO");
        WorkWithGrupo(subnode,itemID,idContato, oGrpsLstTrata );
    }

    subnode=walkDOM(dnode,"GruposRetorno");
    if ( subnode )
    {
        itemID=GetGrupoType("RETORNO");
        WorkWithGrupo(subnode,itemID,idContato, oGrpsLstRetor );
    }

    subnode=walkDOM(dnode,"GruposAbertura");
    if ( subnode )
    {
        itemID=GetGrupoType("ABERTURA");
        WorkWithGrupo(subnode,itemID,idContato, oGrpsLstAbert );
    }

    //------------------------------------------------------------------
    // Apaga os skills
    // Abertura não é afetada
    // Esta limpeza não pode ficar junto com as limpezas acima, pois as 
    // verificacoes dependem de os grupos ja estarem deletados
    //------------------------------------------------------------------

    ////////////////////////////////////////////////////////////////////////////
    // Tratamento
    long idGrupoArr[500];
    long idTipoSequenciaArr[500];

    i=0;
    for( idx=0; idx < oGrpsTrata.Quantidade(); idx++ )
    {
        if ( oGrpsTrata.Registro(idx)->flagListaXml == 0 )
        {
            if ( i > 499 )
            {
                CleanSkill( idContato, idTipoSequenciaArr, idGrupoArr );
                i = 0;
            }

            idGrupoArr[i] = oGrpsTrata.Registro(idx)->idGrupoMem;
            idTipoSequenciaArr[i] = oGrpsTrata.Registro(idx)->idSeqMem;
            i++;
        }
    }

    if ( i )
    {
        if (i < 500) idGrupoArr[i] = END_ARRAY; // Nao existem mais registros
        if (i < 500) idTipoSequenciaArr[i] = END_ARRAY; // Nao existem mais registros
        CleanSkill( idContato, idTipoSequenciaArr, idGrupoArr );
    }

    ////////////////////////////////////////////////////////////////////////////
	// Retorno
    i=0;
    for( idx=0; idx < oGrpsRetor.Quantidade(); idx++ )
    {
        if ( oGrpsRetor.Registro(idx)->flagListaXml == 0 )
        {
            if ( i > 499 )
            {
                CleanSkill( idContato, idTipoSequenciaArr, idGrupoArr );
                i = 0;
            }

            idGrupoArr[i] = oGrpsRetor.Registro(idx)->idGrupoMem;
            idTipoSequenciaArr[i] = oGrpsRetor.Registro(idx)->idSeqMem;
            i++;
        }
    }

    if ( i )
    {
        if (i < 500) idGrupoArr[i] = END_ARRAY; // Nao existem mais registros
        if (i < 500) idTipoSequenciaArr[i] = END_ARRAY; // Nao existem mais registros
        CleanSkill( idContato, idTipoSequenciaArr, idGrupoArr );
    }

    ULOG_END("GruposWriter::BatchWriter()"); 
    return 1;
}

NiveisRequest::NiveisRequest( XMLGen * pxml )
{
    NiveisRequest::pxml=pxml;
}

NiveisRequest::~NiveisRequest()
{
}

int NiveisRequest::QueryData(char*pnode)
{
    ULOG_START("NiveisRequest::QueryData()"); 

	NiveisRequest::pnode=pnode;

	pxml->createTag("GrupoInicio");
	pxml->createTag("GrupoVO");
	pxml->addItem("codigo",pnode);
	pxml->closeTag();
	pxml->closeTag();
	QueryLevels();
	
	ULOG_END("NiveisRequest::QueryData()"); 
	
	return 1;
}

NiveisWriter::NiveisWriter(char*idusr):idUsr(idusr)
{
}


NiveisWriter::~NiveisWriter()
{
}

int NiveisWriter::BatchWriter( DOMNode * dnode )
{
    ULOG_START("NiveisWriter::BatchWriter()"); 

	DOMNode * subnode;
	char * pmid;

	subnode=walkDOM(dnode,"GrupoInicio");
	if ( subnode == NULL )
		throw new TuxBasicSvcException("09E0010","Cannot find MandatoryLevel ID");
	
    pmid=walkTree(subnode,"codigo",0);
	if( pmid == NULL )
		throw new TuxBasicSvcException("09E0011","Invalid XML Data. Cannot find MandatoryLevel Code");

    CleanPreviousData( atol(pmid) );

	WorkWithNivel( dnode,atol(pmid) );

    XMLString::release(&pmid);
    
    ULOG_END("NiveisWriter::BatchWriter()");

	return 1;
}
