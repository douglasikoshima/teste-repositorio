/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:55 $
 **/

#ifndef __CWFINIACAO_H__
#define __CWFINIACAO_H__

//------------------------------------------------------------------------------        
// Inserir insistência - Script 291
//------------------------------------------------------------------------------        

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

#define SUCESSO 0
#define ERRO    1

//------------------------------------------------------------------------------        
// cWF_InsereInsistencia 
//------------------------------------------------------------------------------        

class cWF_InsereInsistencia : public cWF_Acao
{
    public:
        //------------------------------------------------------------------------------        
         cWF_InsereInsistencia(DOMNode*,XMLGen*,char *);
         ~cWF_InsereInsistencia() {};

        //------------------------------------------------------------------------------        
    public:
        //------------------------------------------------------------------------------        
        void Executar();

        //------------------------------------------------------------------------------        
    protected: 
    
};

#endif // __CWFINIACAO_H__
