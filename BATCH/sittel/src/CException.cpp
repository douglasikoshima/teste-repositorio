/*
 * CException.cpp
 *
 *  Created on: 08/06/2013
 *      Author: Jones Randis
 */

#include "CException.h"

namespace batch {

CException::CException() : emsg("CException!") {}
CException::CException(std::string emsg) : emsg(emsg) {}
CException::~CException() throw() {}
const char* CException::what() const throw() { return emsg.c_str(); }

CException XConfigFile("Erro ao ler arquivo de configuracao!");
CException XConfigParam("Parametros incompletos!");
CException XParamDatabase("Parametro [usr|pwd|inst]_db invalido(s)!");
CException XDatabaseConnect("Erro ao conectar no banco de dados!");
CException XDatabaseDisconnect("Erro ao desconectar do banco de dados!");
CException XDatabaseSqlError("Erro ao executar query SQL!");
CException XTipoSolicitacao("Tipo de solicitacao invalido!");
CException XXmlParserError("Erro no parser XML!");
CException XXmlSchemaError("Erro no schema XML!");
CException XXmlCreateDocument("Erro ao criar documento XML!");
CException XXmlCreateElement("Erro ao criar elemento XML!");
CException XCreateFile("Erro ao abrir/criar arquivo!");
CException XCloseFile("Erro ao fechar/salvar arquivo!");
CException XReadFile("Erro ao ler arquivo!");
CException XMoveFile("Erro ao mover arquivo!");
CException XDelFile("Erro ao deletar arquivo!");
CException XChangeDir("Erro ao mudar de diretorio!");
CException XCurrentDir("Erro ao obter diretorio atual!");
CException XAlreadyRunning("Ja existe outro processo em execucao!");
CException XSoapCall("Erro ao conectar/chamar servico SOAP!");
CException XLoadParam("Nao foi possivel carregar parametro(s) de APOIO.PARAMETRO");
CException XReadBuffer("Erro ao ler buffer de dados");

} /* namespace batch */
