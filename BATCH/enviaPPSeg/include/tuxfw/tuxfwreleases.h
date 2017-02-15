/**!
 * 
 * @module  
 * @purpose Concentrar a documenta��o da libera��o global de versoes do framework
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:47:13 $
 * @ID      $Id: tuxfwreleases.h,v 1.1.6.2 2014/08/14 15:47:13 a5114878 Exp $
 **/

///
// Macro para documenta��o de disponibiliza��o de releases oficiais do framework
//
#define TUXFW_SIGNATURE  "TUXFW v. 2.1.24 - $Revision: 1.1.6.2 $ $Date: 2014/08/14 15:47:13 $\0"
//
//  Historico parcial de releases oficiais do framework a partir de 03/08/2004
//
// $Log: tuxfwreleases.h,v $
// Revision 1.1.6.2  2014/08/14 15:47:13  a5114878
// 88743 - Cadastro Autom�tico Vivo Valoriza
//
// Revision 1.1.4.1  2014/07/09 18:05:10  a5114878
// 88743 - Cadastro Autom�tico Vivo Valoriza
//
// Revision 1.1.4.1  2012/03/30 12:16:06  a5116174
// Demanda 22715 - Segmenta��o Percebida - Estabiliza��o dos Processos
//
// Revision 1.1.4.1  2009/12/23 17:05:15  a5114878
// Cria��o de novo servidor para cadastro pre-pago.
//
// Revision 1.1.2.1  2009/12/23 14:59:26  a5114878
// Cria��o de novo servidor para cadastro pre-pago.
//
// Revision 1.1  2006/06/07 22:20:23  rrusso
// no message
//
// Revision 1.1  2006/06/07 22:06:07  rrusso
// no message
//
// Revision 1.24  2005/03/23 15:00:58  mgobbo
// Reativa��o do DUMP de xmls de entrada e sa�da
//
// Revision 1.23  2005/03/15 14:08:56  mgobbo
// Desabilitar a gera��o de DUMP dos XML de entrada e sa�da
//
// Revision 1.22  2005/03/11 18:40:07  mgobbo
// Refor�ar limpeza de ponteiros ap�s dele��o da mem�ria referenciada
//
// Revision 1.21  2005/03/08 14:49:32  mgobbo
// Remoc�o da valida��o de Integrigade do parser que gerava mensagens para stderr
//
// Revision 1.20  2005/03/07 13:10:50  mgobbo
// Acerto para salvar apenas a ultima vers�o do arquivo
//
// Revision 1.19  2005/03/04 18:25:38  mgobbo
// Acerto de referencia da vers�o de tuxfwreleases.h
// Corre��o de indice para array em tuxfwbasicsvc.cpp
//
// Revision 1.18  2005/03/04 16:14:37  mgobbo
// Gerar dump dos arquivos XML de entrada e sa�da para  prepara��o de documenta��o
// Enviar trecho do buffer de saida ao log quando h� estouro de limite
//
// Revision 1.17  2005/02/22 20:07:50  mgobbo
// revis�o memory leaks TuxMessage e TuxRemoteCall - aumento de mensagens de debug
//
// Revision 1.16  2005/02/18 15:08:53  mgobbo
// Aumento da quantidade de mensagens internas para depura��o de reciclagem de log
// Revis�o dos pontos de testes de refresh
//
// Revision 1.15  2005/02/15 00:33:42  mgobbo
// Atualiza��o para suportar todos os flags de configura��o
//
// Revision 1.14  2005/02/14 23:07:14  mgobbo
// Revis�o de todas as referencias a tuxfw.h a partir do proprio framework, onde elas s�o proibidas.
//
// Revision 1.13  2005/02/13 17:49:48  mgobbo
// Altera��o na estrutura de reciclagem de logs para permitir que os logs sejam reciclados mesmo que mensagens n�o sejam exibidas por estarem com o nivel inadequado.
// Ajustes de memory leaks
//
// Revision 1.12  2005/02/13 15:38:11  mgobbo
// Altera��o do nivel default de log para LogError
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
// N�o coloca��o de tags vazias no header gerado por TuxMessage
//
// Revision 1.8  2004/11/19 04:10:45  mgobbo
// Remo��o de memory leaks
// Refactoring de tuxMessage para solu��o de memory leaks e tratamentos de erros.
//
// Revision 1.7  2004/11/17 17:58:17  mgobbo
// melhorias de logs , incorpora��o de snprintf, e redu��o de memory leaks internos do framework
//
// Revision 1.6  2004/10/13 16:30:28  mgobbo
// Acerto de exceptions em remotecall
//
// Revision 1.5  2004/09/18 21:28:17  mgobbo
// Redu��o de pontos de log desnecess�rios
// Ajuste de mensagens no mecanismo de Ping
// Distribui��o de logs em subdiretorios
// Acertos em TuxMessage
//
// Revision 1.4  2004/09/02 21:54:46  mgobbo
// Acerto de chamadas remotas
//
// Revision 1.3  2004/08/27 15:33:46  mgobbo
// Altera��o de default de log para arquivo
// Corre�oes em chamadas a servicos remotos
// Implementa��o de getNodeAsString em TuxHelper
// Altera��o no stub de conexao ao banco de dados no Windows
// Revis�ao / implementa��o de TuxedoPing
//
// Revision 1.2  2004/08/25 17:16:05  mgobbo
// Contorno de buffer overflow em TuxLog
// Acerto de mensagens de erro em caso de exce��o gen�rica capturada pelo tuxfw
// Implementa��o de Tuxfwping
//
// Revision 1.1  2004/08/03 16:03:28  mgobbo
// Desvincula��o das dependencias internas do framework de tuxfw.h para permitir movimentacao de tuxfw.h no diretorio staging
//