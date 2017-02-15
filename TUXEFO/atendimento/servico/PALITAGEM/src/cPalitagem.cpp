/**
 * 
 * @modulo  Atendimento
 * @usecase Pré-processamento do relatório de palitagem
 * @author  Max
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cPalitagem.h"

// Funções de pré-processamento para relatórios
extern void proCAtualizarAtdPalitagem( int _idUFOperadora
                                      ,int _idContato
                                      ,short _idContatoFlag
                                      ,long _idPessoaUsuarioAbertura
                                      ,short _idPessoaUsuarioAberturaFlag
                                      ,int _idGrupoAbertura
                                      ,short _idGrupoAberturaFlag
                                      ,int _idUsuarioAlteracao
                                      ,short _idUsuarioAlteracaoFlag);

cPalitagem::cPalitagem(void)
{
    memset(&stDadosQuery,0,sizeof(TDadosQuery));
}

void cPalitagem::ProcessaPalitagem()
{
    proCAtualizarAtdPalitagem( stDadosQuery.idUFOperadora
							  ,stDadosQuery.idContato
							  ,stDadosQuery.idContatoFlag
							  ,stDadosQuery.idPessoaUsuarioAbertura
							  ,stDadosQuery.idPessoaUsuarioAberturaFlag
                              ,stDadosQuery.idGrupoAbertura
                              ,stDadosQuery.idGrupoAberturaFlag
                              ,stDadosQuery.idUsuarioAlteracao
                              ,stDadosQuery.idUsuarioAlteracaoFlag);
}

void cPalitagem::CarregaDados(DOMNode *dnode)
{
    char *pParam;
    TuxHelper tx;

    pParam = tx.walkTree(dnode, "idUFOperadora", 0);
    if(pParam) { stDadosQuery.idUFOperadora = atoi(pParam); XMLString::release(&pParam); }
        
    pParam = tx.walkTree(dnode, "idContato", 0);
    if(pParam) { stDadosQuery.idContato = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idContatoFlag", 0);
    if(pParam) { stDadosQuery.idContatoFlag = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idPessoaUsuarioAbertura", 0);
    if(pParam) { stDadosQuery.idPessoaUsuarioAbertura = atol(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idPessoaUsuarioAberturaFlag", 0);
    if(pParam) { stDadosQuery.idPessoaUsuarioAberturaFlag = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idGrupoAbertura", 0);
    if(pParam) { stDadosQuery.idGrupoAbertura = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idGrupoAberturaFlag", 0);
    if(pParam) { stDadosQuery.idGrupoAberturaFlag = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idUsuarioAlteracao", 0);
    if(pParam) { stDadosQuery.idUsuarioAlteracao = atoi(pParam); XMLString::release(&pParam); }
    
    pParam = tx.walkTree(dnode, "idUsuarioAlteracaoFlag", 0);
    if(pParam) { stDadosQuery.idUsuarioAlteracaoFlag = atoi(pParam); XMLString::release(&pParam);}
}
