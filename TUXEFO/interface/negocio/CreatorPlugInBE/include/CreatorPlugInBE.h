#ifndef TXPB_CREATOR_PLUGIN_BE_H
#define TXPB_CREATOR_PLUGIN_BE_H 1

#include "../../PlugInBE/include/PlugInBE.h"
#include "../../PlugInATLYS/include/PlugInATLYS.h"
#include "../../PlugInPPS/include/PlugInPPS.h"
#include "../../PlugInNGIN/include/PlugInNGIN.h"

// Constantes para os sistemas legados.
#define SYS_NOT_SUPORTED -1
#define SYS_NOT_FOUND     0
#define ATLYS             1
#define PPS               2
#define NGIN              3


// Codigos de erro (0100 a 0199)
#define ECD_SYS_NOT_SUPORTED                   "24E0101"
#define ECD_SYS_NOT_FOUND                      "24E0102"
#define ECD_CAN_NOT_CREATE_PLUGIN              "24E0103"

// Mensagens de erro
#define EMSG_SYS_NOT_SUPORTED                  "Sistema legado não suportado"
#define EMSG_SYS_NOT_FOUND                     "Sistema legado não encontrado para esta linha" 
#define EMSG_CAN_NOT_CREATE_PLUGIN             "Erro criando plugin para o sistema legado"



class CreatorPlugInBE
{
public:

	// Chamador do Factory Metodo
	static PlugInBE* getPlugIn(DOMNode*, XMLGen*, char*);

	// Chamador para construir um PlugInNGIN
	static PlugInNGIN* getPlugInNGIN(DOMNode*, XMLGen*, char*);

	// Chamador para contruir um PlugInATLYS
	static PlugInATLYS* getPlugInATLYS(DOMNode*, XMLGen*, char*);

private:

	// Seleciona qual o sistema legado
	static int chooseSystem(char*, char*, int,char*);

	//  Dados da linha
	static int getDadosLinha(char*, char*, int,char*,char*,char*);
};


#endif
