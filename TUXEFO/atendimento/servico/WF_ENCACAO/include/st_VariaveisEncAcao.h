/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/ 

#ifndef STWF_ENCACAO
    #define STWF_ENCACAO

struct st_WF_ENCACAO
{
    int  UsuarioAtual;
    long idAtendimento;
    int  idFase;
    int  idAgrEstTPrFt;
    int  idAgrEstTPrAt;
    int  idAtividade;

    char urlDestino[256];

    DOMNode* domTratWf;
    DOMNode* domWorkflow;
    DOMNode* domGrupo;
    DOMNode* domUsuario;
    DOMNode* domMotivo;

};


#endif
