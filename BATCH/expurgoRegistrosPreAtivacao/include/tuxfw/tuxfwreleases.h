/**!
 * 
 * @module  
 * @purpose Concentrar a documentação da liberação global de versoes do framework
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: jones $ - $Date: 2013/07/10 18:04:55 $
 * @ID      $Id: tuxfwreleases.h,v 1.1.6.1 2013/07/10 18:04:55 jones Exp $
 **/

///
// Macro para documentação de disponibilização de releases oficiais do framework
//
#define TUXFW_SIGNATURE  "TUXFW v. 2.1.24 - $Revision: 1.1.6.1 $ $Date: 2013/07/10 18:04:55 $\0"
//
//  Historico parcial de releases oficiais do framework a partir de 03/08/2004
//
// $Log: tuxfwreleases.h,v $
// Revision 1.1.6.1  2013/07/10 18:04:55  jones
// expurgoRegistrosPreAtivacao
//
// Revision 1.1.2.1  2013/06/14 19:19:21  a5125996
// *** empty log message ***
//
// Revision 1.1.2.1  2013/06/06 17:32:19  a5125996
// *** empty log message ***
//
// Revision 1.1.2.1  2012/09/12 17:38:51  a5116174
// 25951  - demanda Yuri
//
// Revision 1.1.2.1  2008/01/04 14:48:47  rrusso
// no message
//
// Revision 1.1  2007/12/18 17:44:28  rrusso
// *** empty log message ***
//
// Revision 1.1  2007/11/22 19:49:39  rrusso
// *** empty log message ***
//
// Revision 1.1  2007/07/25 21:38:09  rrusso
// *** empty log message ***
//
// Revision 1.1  2007/06/27 22:14:55  rrusso
// Reprocessamento da fila do prepago
//
// Revision 1.1  2006/06/07 22:20:23  rrusso
// no message
//
// Revision 1.1  2006/06/07 22:06:07  rrusso
// no message
//
// Revision 1.24  2005/03/23 15:00:58  mgobbo
// Reativação do DUMP de xmls de entrada e saída
//
// Revision 1.23  2005/03/15 14:08:56  mgobbo
// Desabilitar a geração de DUMP dos XML de entrada e saída
//
// Revision 1.22  2005/03/11 18:40:07  mgobbo
// Reforçar limpeza de ponteiros após deleção da memória referenciada
//
// Revision 1.21  2005/03/08 14:49:32  mgobbo
// Remocão da validação de Integrigade do parser que gerava mensagens para stderr
//
// Revision 1.20  2005/03/07 13:10:50  mgobbo
// Acerto para salvar apenas a ultima versão do arquivo
//
// Revision 1.19  2005/03/04 18:25:38  mgobbo
// Acerto de referencia da versão de tuxfwreleases.h
// Correção de indice para array em tuxfwbasicsvc.cpp
//
// Revision 1.18  2005/03/04 16:14:37  mgobbo
// Gerar dump dos arquivos XML de entrada e saída para  preparação de documentação
// Enviar trecho do buffer de saida ao log quando há estouro de limite
//
// Revision 1.17  2005/02/22 20:07:50  mgobbo
// revisão memory leaks TuxMessage e TuxRemoteCall - aumento de mensagens de debug
//
// Revision 1.16  2005/02/18 15:08:53  mgobbo
// Aumento da quantidade de mensagens internas para depuração de reciclagem de log
// Revisão dos pontos de testes de refresh
//
// Revision 1.15  2005/02/15 00:33:42  mgobbo
// Atualização para suportar todos os flags de configuração
//
// Revision 1.14  2005/02/14 23:07:14  mgobbo
// Revisão de todas as referencias a tuxfw.h a partir do proprio framework, onde elas são proibidas.
//
// Revision 1.13  2005/02/13 17:49:48  mgobbo
// Alteração na estrutura de reciclagem de logs para permitir que os logs sejam reciclados mesmo que mensagens não sejam exibidas por estarem com o nivel inadequado.
// Ajustes de memory leaks
//
// Revision 1.12  2005/02/13 15:38:11  mgobbo
// Alteração do nivel default de log para LogError
//
// Revision 1.11  2005/02/12 01:16:58  mgobbo
// Reciclagem de logs
//
// Revision 1.10  2004/11/28 03:43:30  mgobbo
// Acerto de bux no log de TuxBasicOraException
//
// Revision 1.9  2004/11/23 14:56:44  mgobbo
// Retirada de debug interno de XMLGem
// Melhoria de mensagens de erro em TuxRemoteService
// Não colocação de tags vazias no header gerado por TuxMessage
//
// Revision 1.8  2004/11/19 04:10:45  mgobbo
// Remoção de memory leaks
// Refactoring de tuxMessage para solução de memory leaks e tratamentos de erros.
//
// Revision 1.7  2004/11/17 17:58:17  mgobbo
// melhorias de logs , incorporação de snprintf, e redução de memory leaks internos do framework
//
// Revision 1.6  2004/10/13 16:30:28  mgobbo
// Acerto de exceptions em remotecall
//
// Revision 1.5  2004/09/18 21:28:17  mgobbo
// Redução de pontos de log desnecessários
// Ajuste de mensagens no mecanismo de Ping
// Distribuição de logs em subdiretorios
// Acertos em TuxMessage
//
// Revision 1.4  2004/09/02 21:54:46  mgobbo
// Acerto de chamadas remotas
//
// Revision 1.3  2004/08/27 15:33:46  mgobbo
// Alteração de default de log para arquivo
// Correçoes em chamadas a servicos remotos
// Implementação de getNodeAsString em TuxHelper
// Alteração no stub de conexao ao banco de dados no Windows
// Revisçao / implementação de TuxedoPing
//
// Revision 1.2  2004/08/25 17:16:05  mgobbo
// Contorno de buffer overflow em TuxLog
// Acerto de mensagens de erro em caso de exceção genérica capturada pelo tuxfw
// Implementação de Tuxfwping
//
// Revision 1.1  2004/08/03 16:03:28  mgobbo
// Desvinculação das dependencias internas do framework de tuxfw.h para permitir movimentacao de tuxfw.h no diretorio staging
//