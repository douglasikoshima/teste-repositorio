
#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_Cancelamento : public cWF_Acao
{
	public:
		 cWF_Cancelamento(DOMNode*, XMLGen* , char *);
        ~cWF_Cancelamento(){};

	public:
		void	Executar(); 
	
	private:
        // void enviarMsgCancelamento();
};

