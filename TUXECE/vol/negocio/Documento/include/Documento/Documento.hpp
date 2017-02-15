#ifndef __DOCUMENTOH__
#define __DOCUMENTOH__

class Documento{
public:
	Documento();
	~Documento();
	int getDocumento(char*documento,char*tipo,char*linha,char*ddd);
	int validaCPF(char*cpf);
};

#endif