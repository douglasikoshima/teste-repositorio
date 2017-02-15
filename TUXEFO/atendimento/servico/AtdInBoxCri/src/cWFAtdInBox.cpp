/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "../include/cWFAtdInBox.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFInboxUsuario(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioDocumento(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxLinha(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxLinhaDocumento(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechUsuario(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechUsuarioDocumento(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechLinha(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechLinhaDocumento(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFAdq(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFAdqLinha(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFAdqDocumento(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFAdqConta(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFAdqNome(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);

// Adaptação da pesquisa do módulo de clientes
extern void proCConsultaWFAdqClientePorNome       (char *pszNmNome, char *pszNmNomeMeio, char *pszNmSobreNome, XMLGen *xml_g);
extern void proCConsultaWFAdqClientePorRazaoSocial(char *pszNmNome, XMLGen *xml_g);

// Adaptação da pesquisa do módulo de clientes para Contato Previo
extern void proCConsultaWFAdqClientePorNomeRC       (char *pszNmNome, char *pszNmNomeMeio, char *pszNmSobreNome, XMLGen *xml_g);
extern void proCConsultaWFAdqClientePorRazaoSocialRC(char *pszNmNome, XMLGen *xml_g);


extern bool proCConsultaWFInboxFechUsuarioEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechUsuarioDocumentoEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechLinhaEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechLinhaDocumentoEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioDocumentoEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxLinhaEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);
extern bool proCConsultaWFInboxLinhaDocumentoEnc(st_AtdInBox* dados, st_vlAtdInBox* status, XMLGen* saida);


cWFAtdInBox::cWFAtdInBox()
{
    entrada=0;
    saida=0;

    memset( &m_stFila,  0,sizeof(m_stFila) );
    memset( &m_vlFila, -1,sizeof(m_vlFila) );

}

cWFAtdInBox::~cWFAtdInBox()
{
}

cWFAtdInBox::cWFAtdInBox(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    carregaDados();
}


bool cWFAtdInBox::consultarBoxEnc( int idPessoaUsuario)
{

    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;


    m_stFila.idPessoaUsuarioBko = 0;        
    m_vlFila.idPessoaUsuarioBko = -1;

    if (m_vlFila.dtFechamentoInicio == -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            if (m_vlFila.tipoDocumento != -1)
                return proCConsultaWFInboxLinhaDocumentoEnc(&m_stFila, &m_vlFila, saida);
            else
                return proCConsultaWFInboxLinhaEnc(&m_stFila, &m_vlFila, saida);    
        }
        else if (m_vlFila.tipoDocumento != -1)
            return proCConsultaWFInboxUsuarioDocumentoEnc(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFInboxUsuarioEnc(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        if (m_vlFila.nrLinha != -1)
        {
            if (m_vlFila.tipoDocumento != -1)
                return proCConsultaWFInboxFechLinhaDocumentoEnc(&m_stFila, &m_vlFila, saida);
            else
                return proCConsultaWFInboxFechLinhaEnc(&m_stFila, &m_vlFila, saida);    
        }
        else if (m_vlFila.tipoDocumento != -1)
                return proCConsultaWFInboxFechUsuarioDocumentoEnc(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFInboxFechUsuarioEnc(&m_stFila, &m_vlFila, saida);
    }
    



}

bool cWFAtdInBox::consultarBox(int idPessoaUsuario)
{
    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;

    if (m_stFila.inAbaPesquisa != 3)
    {
        m_stFila.idPessoaUsuarioBko = 0;        
        m_vlFila.idPessoaUsuarioBko = -1;
    }
    else
    {
        m_stFila.idPessoaUsuarioBko = 0;        
        m_vlFila.idPessoaUsuarioBko = 1;
    }
    if (m_vlFila.dtFechamentoInicio == -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            if (m_vlFila.tipoDocumento != -1)
                return proCConsultaWFInboxLinhaDocumento(&m_stFila, &m_vlFila, saida);
            else
                return proCConsultaWFInboxLinha(&m_stFila, &m_vlFila, saida);   
        }
        else if (m_vlFila.tipoDocumento != -1)
            return proCConsultaWFInboxUsuarioDocumento(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFInboxUsuario(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        if (m_vlFila.nrLinha != -1)
        {
            if (m_vlFila.tipoDocumento != -1)
                return proCConsultaWFInboxFechLinhaDocumento(&m_stFila, &m_vlFila, saida);
            else
                return proCConsultaWFInboxFechLinha(&m_stFila, &m_vlFila, saida);   
        }
        else if (m_vlFila.tipoDocumento != -1)
            return proCConsultaWFInboxFechUsuarioDocumento(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFInboxFechUsuario(&m_stFila, &m_vlFila, saida);
    }
    
}




bool cWFAtdInBox::consultarBoxAdq(int idPessoaUsuario)
{
    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;

    if (m_vlFila.inTipoPesquisa == -1)
        return proCConsultaWFAdq(&m_stFila, &m_vlFila, saida);
    else if (m_stFila.inTipoPesquisa == 1)
        return proCConsultaWFAdqLinha(&m_stFila, &m_vlFila, saida);
    else if (m_stFila.inTipoPesquisa == 2)
        return proCConsultaWFAdqConta(&m_stFila, &m_vlFila, saida);
    else if (m_stFila.inTipoPesquisa == 3)
        return proCConsultaWFAdqNome(&m_stFila, &m_vlFila, saida);
    else if (m_stFila.inTipoPesquisa == 4)
        return proCConsultaWFAdqDocumento(&m_stFila, &m_vlFila, saida);
    else
        return true;
    
}



// Adaptacao do módulo de clientes portada para atender a incidência 3220
void cWFAtdInBox::consultarBoxAdqCliente(DOMNode* dnode, XMLGen* xml_g) {

    unsigned int iCont;

    char *pAux;

    char szSgTipoPesquisa[256];
    char szDsValor[256];
    char szDsNomeDoMeio[256];
    char szDsSobreNome[256];
    char szNmCompleto[256];

    try
    {

        memset(szSgTipoPesquisa, 0x00, sizeof(szSgTipoPesquisa));
        memset(szDsValor, 0x00, sizeof(szDsValor));
        memset(szDsNomeDoMeio, 0x00, sizeof(szDsNomeDoMeio));
        memset(szDsSobreNome, 0x00, sizeof(szDsSobreNome));
        memset(szNmCompleto, 0x00, sizeof(szNmCompleto));
    

        for(iCont=0;;iCont++) {
            ULOG("iCont(%d)", iCont);

            if((pAux = tx.walkTree(dnode, "sgTipoPesquisa", iCont))) {
                strcpy(szSgTipoPesquisa, pAux); XMLString::release(&pAux);
            } else break;
            ULOG("szSgTipoPesquisa[%s]", szSgTipoPesquisa);


            if((pAux = tx.walkTree(dnode, "dsValor", iCont))) {
                strcpy(szDsValor, pAux); XMLString::release(&pAux);
            } else break;
            ULOG("szDsValor[%s]", szDsValor);


            if((pAux = tx.walkTree(dnode, "dsNomeDoMeio", iCont))) {
                strcpy(szDsNomeDoMeio, pAux); XMLString::release(&pAux);
            }
            ULOG("szDsNomeDoMeio[%s]", szDsNomeDoMeio);


            if((pAux = tx.walkTree(dnode, "dsSobreNome", iCont))) {
                strcpy(szDsSobreNome, pAux); XMLString::release(&pAux);
            }
            ULOG("szDsSobreNome[%s]", szDsSobreNome);


            if((pAux = tx.walkTree(dnode, "nmCompleto", iCont))) {
                strcpy(szNmCompleto, pAux); XMLString::release(&pAux);
            }
            ULOG("szNmCompleto[%s]", szNmCompleto);


            if(!strcmp(szSgTipoPesquisa, "NOME")) {
                proCConsultaWFAdqClientePorNome(szDsValor, szDsNomeDoMeio, szDsSobreNome, xml_g);
            }
            
            if(!strcmp(szSgTipoPesquisa, "RAZAO")){
                proCConsultaWFAdqClientePorRazaoSocial(szDsValor, xml_g);
            }
            if(!strcmp(szSgTipoPesquisa, "NOMERC")) {
                proCConsultaWFAdqClientePorNomeRC(szDsValor, szDsNomeDoMeio, szDsSobreNome, xml_g);
            }
            
            if(!strcmp(szSgTipoPesquisa, "RAZAORC")){
                proCConsultaWFAdqClientePorRazaoSocialRC(szDsValor, xml_g);
            }
            
        }
    }
    catch(...)
    {
        throw;
    }
    

}





bool cWFAtdInBox::AtualizaProcessos( int idPessoaUsuario )
{
    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;
    

   return proCConsultaWFAdq(&m_stFila, &m_vlFila, saida);
    
}




void cWFAtdInBox::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.

    ULOG_START( "cWFAtdInBox::carregaDados()" );

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    char* p;

    if (p = tx.walkTree( entrada, "idAlerta", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idAlerta = atoi(p);
            m_vlFila.idAlerta = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idAlerta [%i]", m_stFila.idAlerta);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idContato = atoi(p);
            m_vlFila.idContato = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idContato [%i]", m_stFila.idContato);
    }

    if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idEstado = atoi(p);
            m_vlFila.idEstado = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idEstado [%i]", m_stFila.idEstado);
    }

    if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idSubEstado = atoi(p);
            m_vlFila.idSubEstado = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idSubEstado [%i]", m_stFila.idSubEstado);
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
    {
        m_stFila.idPessoaUsuario = atoi(p);
        m_vlFila.idPessoaUsuario = 1;
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
    }

    if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
            m_vlFila.dtAberturaInicio= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - dtAberturaInicio idAtendimento [%s]", m_stFila.dtAberturaInicio);
    }

    if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
            m_vlFila.dtAberturaFim= 1;
        }       
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado dtAberturaFim [%s]", m_stFila.dtAberturaFim);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
            m_vlFila.dtFechamentoInicio= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
            m_vlFila.dtFechamentoFim= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
    }

    if (p = tx.walkTree( entrada, "tipoDocumento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
            m_vlFila.tipoDocumento= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado Tipo Documento [%s]", m_stFila.tipoDocumento);
    }

    if (p = tx.walkTree( entrada, "documento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.documento,"%.*s",sizeof(m_stFila.documento)-1, p );
            m_vlFila.documento= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado documento [%s]", m_stFila.documento);
    }

    if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
            m_vlFila.pesquisa= 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado pesquisa [%s]", m_stFila.pesquisa);
    }

    if (p = tx.walkTree( entrada, "inTipoPesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.inTipoPesquisa = atoi(p);
            m_vlFila.inTipoPesquisa = 1;
            if ( m_stFila.inTipoPesquisa == 0 )
            {
                sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
                m_vlFila.tipoDocumento= 1;
                m_stFila.inTipoPesquisa = 4; // Documentos
            }
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado tpPesquisa [%i]", m_stFila.inTipoPesquisa);
    }

    if (p = tx.walkTree( entrada, "inAbaPesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.inAbaPesquisa = atoi(p);
            m_vlFila.inAbaPesquisa = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado inAbaPesquisa [%i]", m_stFila.inAbaPesquisa);
    }

    if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
    {
        sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
        m_vlFila.textoContato = 1;
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado textoContato [%s]", m_stFila.textoContato);
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idAtendimento = atol(p);
            m_vlFila.idAtendimento = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado idAtendimento [%lu]", m_stFila.idAtendimento);
    }

    if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
            m_vlFila.nrLinha = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado nrLinha [%s]", m_stFila.nrLinha);
    }



    if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.tbPausa = atoi(p);
            m_vlFila.tbPausa = 1;
        }
        XMLString::release(&p);
        ULOG("cWFAtdInBox - Pesquisa Fila - Encontrado tbPausa [%i]", m_stFila.tbPausa);
    }

    ULOG_END( "cWFAtdInBox::carregaDados()" );

}


