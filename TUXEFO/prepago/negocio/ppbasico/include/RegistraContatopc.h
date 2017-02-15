///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  RegistraContato
 * @usecase RegistraContato
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef REGISTRACONTATOPCH
#define REGISTRACONTATOPCH

class CRegistraContatopc
{

    public:
        void proCIncluiRegistraContato(TRegistraContato *ptRegistraContato);
        void proCAtualizaRegistraContato(TRegistraContato tRegistraContato);
        bool proCExisteRegistraContato(TRegistraContato tRegistraContato);
};

#endif
