#
# $Id: makefile,v 1.1.118.1 2013/02/07 19:50:24 jones Exp $
#
.SILENT:

include ../../defines

DIR=negocio
SUBDIR=clienteCmm
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
SO_AUX=clienteCmm

include $(HOME)/TUXEFO/modulos.env
LIBCLI=$(TUXDIR_DES)/$(CLIENTE_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o serviço
#
PCPP=ApoioTipoComunicacaopc.pcpp GestorContaPessoaContapc.pcpp GestorContaContatopc.pcpp \
	GestorContapc.pcpp Portabilidadepc.pcpp EtapaPedidopc.pcpp ItemPedidopc.pcpp \
	ItemOrdemVendapc.pcpp OrdemVendapc.pcpp ProdutoEntregapc.pcpp Pedidopc.pcpp \
	PessoaLinha.pcpp Grupo.pcpp CPacote.pcpp classApoioParametro.pcpp \CorrespDevLinhaTelefonic.pcpp \
	CorrespondenciaDevolvida.pcpp  StatusCorrespondencia.pcpp CorrespDevolvidaConta.pcpp \
	HistoricoStatus.pcpp StatusUnidade.pcpp MotivoDevolucao.pcpp TipoCorrespondencia.pcpp \
	TipoApresentacaoPergunta.pcpp classCstAssunto.pcpp classCstSubAssunto.pcpp \
	classCstAtributo.pcpp classCstValorPossivel.pcpp classCstAtributoB01.pcpp UF.pcpp \
	BuscaCorrespondencia.pcpp BuscaPessoaCorrespondencia.pcpp HistoricoStatusCorrespondencia.pcpp \
	Pais.pcpp PessoaSegmentacaoHistorico.pcpp Prospect.pcpp SistemaOrigem.pcpp TipoCarteira.pcpp \
	TipoComunicacao.pcpp TipoDocumento.pcpp LinhaFaturamento.pcpp ContaLinhaFaturamento.pcpp Unidade.pcpp \
	classCanal.pcpp classDocumentoVO.pcpp classLogAcesso.pcpp classMeioContato.pcpp classPessoa.pcpp \
	classPessoaCanalPreferido.pcpp classPessoaRecusaMeioContato.pcpp ConsultaHexa.pcpp HLRHexa.pcpp \
	MonitoramentoHexa.pcpp classTipoLinha.pcpp classSegmentacao.pcpp classStatusUsuario.pcpp \
	classEstadoLinha.pcpp BuscaCorrespondenciaTI.pcpp ValorLivre.pcpp PessoaValorPossivel.pcpp \
	classCarteirizacao.pcpp classBuscaDadosLinha.pcpp classPlanoServicoLinha.pcpp classUsuario.pcpp \
	classPessoaLinha.pcpp classLinhaTelefonica.pcpp classPessoaComunicacao.pcpp classPessoaEndereco.pcpp \
	classTipoEndereco.pcpp RelCepLogradBairro.pcpp classCep.pcpp PessoaCR.pcpp BuscaDocumentoPessoa.pcpp \
	Conta.pcpp Parametro.pcpp Sexo.pcpp EstadoCivil.pcpp Ocupacao.pcpp Escolaridade.pcpp \
	classPPValPreAtv.pcpp AreaRegistroBloqueadopc.pcpp PessoaFisicapc.pcpp PessoaJuridicapc.pcpp \
	PessoaDocumentopc.pcpp FilaSetClientInfoPREpc.pcpp Documentopc.pcpp LinhaBasepc.pcpp \
	LinhaContapc.pcpp PessoaContapc.pcpp ContaEnderecopc.pcpp PessoaLinhaHistoricopc.pcpp \
	LogGSM.pcpp LogIMEI.pcpp ParametroEntrega.pcpp GestorMisc.pcpp LogSIMLock.pcpp LogDeviceManager.pcpp \
	LogFilaSetClientInfo.pcpp ApoioStatusUsuario.pcpp ApoioParametro.pcpp \
	FilaSetClientInfo.pcpp OrdemNotaFiscalpc.pcpp ParametrosPedidopc.pcpp LinhaConvergente.pcpp
CPP=md5.cpp 3des.cpp MDFive.cpp TripleDES.cpp CPacoteItr.cpp Funcoes.cpp Exception.cpp \
	ApoioTipoComunicacao.cpp GestorContaPessoaConta.cpp GestorContaContato.cpp GestorConta.cpp Portabilidade.cpp \
	classContaPontuacao.cpp classLinhaPonto.cpp AtlysLFAjustes.cpp AtlysLFCobranca.cpp \
	AtlysLFContaDetalhada.cpp AtlysLFFaturamento.cpp AtlysLFLinhaIntraGrupo.cpp AtlysLFPagamento.cpp \
	DES.cpp AtlysLFEstimativa.cpp AtlysAbaServicos.cpp FilaSetClientInfoPRE.cpp Documento.cpp \
	AreaRegistroBloqueado.cpp PessoaFisica.cpp PessoaJuridica.cpp Log.cpp PessoaDocumento.cpp \
	LinhaBase.cpp LinhaConta.cpp PessoaConta.cpp ContaEndereco.cpp PessoaLinhaHistorico.cpp Tools.cpp SaldoPontos.cpp
OBJ=lib/ItemOrdemVenda.o lib/ItemOrdemVendapc.o lib/EtapaPedido.o lib/EtapaPedidopc.o lib/ItemPedido.o \
	lib/ItemPedidopc.o lib/OrdemVenda.o lib/OrdemVendapc.o lib/ProdutoEntregapc.o lib/Pedido.o \
	lib/Pedidopc.o lib/PessoaLinha.o lib/Grupo.o lib/CPacote.o lib/classApoioParametro.o \
	lib/CorrespDevLinhaTelefonic.o lib/CorrespondenciaDevolvida.o \
	lib/StatusCorrespondencia.o lib/CorrespDevolvidaConta.o lib/HistoricoStatus.o lib/StatusUnidade.o \
	lib/MotivoDevolucao.o lib/TipoCorrespondencia.o lib/TipoApresentacaoPergunta.o lib/classCstAssunto.o \
	lib/classCstSubAssunto.o lib/classCstAtributo.o lib/classCstValorPossivel.o lib/classCstAtributoB01.o \
	lib/UF.o lib/BuscaCorrespondencia.o lib/BuscaPessoaCorrespondencia.o lib/HistoricoStatusCorrespondencia.o \
	lib/Pais.o lib/PessoaSegmentacaoHistorico.o lib/Prospect.o lib/SistemaOrigem.o lib/TipoCarteira.o \
	lib/TipoComunicacao.o lib/TipoDocumento.o lib/LinhaFaturamento.o lib/ContaLinhaFaturamento.o \
	lib/Unidade.o lib/classCanal.o lib/classDocumentoVO.o lib/classLogAcesso.o lib/classMeioContato.o \
	lib/ApoioTipoComunicacao.o lib/ApoioTipoComunicacaopc.o lib/GestorContaPessoaConta.o lib/GestorContaPessoaContapc.o \
	lib/GestorContaContato.o lib/GestorContaContatopc.o \
	lib/GestorConta.o lib/GestorContapc.o lib/Portabilidade.o lib/Portabilidadepc.o \
	lib/classPessoa.o lib/classPessoaCanalPreferido.o lib/classPessoaRecusaMeioContato.o \
	lib/ConsultaHexa.o lib/HLRHexa.o lib/MonitoramentoHexa.o lib/classTipoLinha.o lib/classSegmentacao.o \
	lib/classStatusUsuario.o lib/classEstadoLinha.o lib/BuscaCorrespondenciaTI.o lib/ValorLivre.o \
	lib/PessoaValorPossivel.o lib/classCarteirizacao.o lib/classBuscaDadosLinha.o \
	lib/classPlanoServicoLinha.o lib/classUsuario.o lib/classPessoaLinha.o lib/classLinhaTelefonica.o \
	lib/classPessoaComunicacao.o lib/classPessoaEndereco.o lib/classTipoEndereco.o \
	lib/RelCepLogradBairro.o lib/classCep.o lib/PessoaCR.o lib/BuscaDocumentoPessoa.o lib/Conta.o \
	lib/Parametro.o lib/Sexo.o lib/EstadoCivil.o lib/Ocupacao.o lib/Escolaridade.o \
	lib/classPPValPreAtv.o lib/AreaRegistroBloqueadopc.o lib/PessoaFisicapc.o lib/PessoaJuridicapc.o \
	lib/PessoaDocumentopc.o lib/FilaSetClientInfoPREpc.o lib/Documentopc.o lib/LinhaBasepc.o \
	lib/LinhaContapc.o lib/PessoaContapc.o lib/ContaEnderecopc.o lib/PessoaLinhaHistoricopc.o \
	lib/md5.o lib/3des.o lib/MDFive.o lib/TripleDES.o lib/CPacoteItr.o lib/Funcoes.o lib/Exception.o \
	lib/classContaPontuacao.o lib/classLinhaPonto.o lib/AtlysLFAjustes.o lib/AtlysLFCobranca.o \
	lib/AtlysLFContaDetalhada.o lib/AtlysLFFaturamento.o lib/AtlysLFLinhaIntraGrupo.o \
	lib/AtlysLFPagamento.o lib/DES.o lib/AtlysLFEstimativa.o lib/AtlysAbaServicos.o \
	lib/FilaSetClientInfoPRE.o lib/Documento.o lib/AreaRegistroBloqueado.o lib/PessoaFisica.o \
	lib/PessoaJuridica.o lib/Log.o lib/PessoaDocumento.o lib/LinhaBase.o lib/LinhaConta.o \
	lib/PessoaConta.o lib/ContaEndereco.o lib/PessoaLinhaHistorico.o lib/Tools.o lib/LogGSM.o lib/LogIMEI.o \
	lib/ParametroEntrega.o lib/GestorMisc.o lib/LogSIMLock.o lib/LogDeviceManager.o lib/LogFilaSetClientInfo.o \
	lib/ApoioStatusUsuario.o lib/ApoioParametro.o lib/FilaSetClientInfo.o lib/OrdemNotaFiscalpc.o \
	lib/SaldoPontos.o lib/ParametrosPedidopc.o lib/LinhaConvergente.o
CUSTSO=

all: $(SUBDIR)

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#
$(SRCDIR)/PessoaLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Grupo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPacote.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classApoioParametro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CorrespDevLinhaTelefonic.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CorrespondenciaDevolvida.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/StatusCorrespondencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CorrespDevolvidaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/HistoricoStatus.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/StatusUnidade.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/MotivoDevolucao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoCorrespondencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoApresentacaoPergunta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCstAssunto.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCstSubAssunto.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCstAtributo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCstValorPossivel.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCstAtributoB01.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/UF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/BuscaCorrespondencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/BuscaPessoaCorrespondencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/HistoricoStatusCorrespondencia.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Pais.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaoHistorico.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Prospect.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/SistemaOrigem.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoCarteira.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoComunicacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoDocumento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaFaturamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaLinhaFaturamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Unidade.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCanal.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classDocumentoVO.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classLogAcesso.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classMeioContato.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoaCanalPreferido.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoaRecusaMeioContato.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ConsultaHexa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/HLRHexa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/MonitoramentoHexa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classTipoLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classSegmentacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classStatusUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classEstadoLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/BuscaCorrespondenciaTI.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ValorLivre.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaValorPossivel.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCarteirizacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classBuscaDadosLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPlanoServicoLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoaLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classLinhaTelefonica.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoaComunicacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPessoaEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classTipoEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/RelCepLogradBairro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classCep.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaCR.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/BuscaDocumentoPessoa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Conta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Parametro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Sexo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/EstadoCivil.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Ocupacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Escolaridade.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classPPValPreAtv.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaFisicapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AreaRegistroBloqueadopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaJuridicapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDocumentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfoPREpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Documentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ApoioTipoComunicacaopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorContaPessoaContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorContaContatopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Portabilidadepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaBasepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/EtapaPedidopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ParametrosPedidopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/OrdemNotaFiscalpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ItemPedidopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/OrdemVendapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ItemOrdemVendapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ProdutoEntregapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Pedidopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaEnderecopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaHistoricopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/md5.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/3des.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/MDFive.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TripleDES.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPacoteItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Funcoes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Exception.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classContaPontuacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classLinhaPonto.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFAjustes.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFCobranca.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFContaDetalhada.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFFaturamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFLinhaIntraGrupo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFPagamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/DES.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysLFEstimativa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AtlysAbaServicos.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfoPRE.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Documento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaFisica.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AreaRegistroBloqueado.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaJuridica.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Log.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDocumento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ApoioTipoComunicacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorContaPessoaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorContaContato.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Portabilidade.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaBase.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaHistorico.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Tools.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LogGSM.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LogIMEI.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LogFilaSetClientInfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ParametroEntrega.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/GestorMisc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LogSIMLock.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LogDeviceManager.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ApoioStatusUsuario.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ApoioParametro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaConvergente.cpp:
	$(PROC) $*.pcpp

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  Compile
#
lib/PessoaLinha.o:$(SRCDIR)/PessoaLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinha.cpp -o $*.o
lib/Grupo.o:$(SRCDIR)/Grupo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Grupo.cpp -o $*.o
lib/CPacote.o:$(SRCDIR)/CPacote.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPacote.cpp -o $*.o
lib/classApoioParametro.o:$(SRCDIR)/classApoioParametro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classApoioParametro.cpp -o $*.o
lib/CorrespDevLinhaTelefonic.o:$(SRCDIR)/CorrespDevLinhaTelefonic.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CorrespDevLinhaTelefonic.cpp -o $*.o
lib/CorrespondenciaDevolvida.o:$(SRCDIR)/CorrespondenciaDevolvida.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CorrespondenciaDevolvida.cpp -o $*.o
lib/StatusCorrespondencia.o:$(SRCDIR)/StatusCorrespondencia.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/StatusCorrespondencia.cpp -o $*.o
lib/CorrespDevolvidaConta.o:$(SRCDIR)/CorrespDevolvidaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CorrespDevolvidaConta.cpp -o $*.o
lib/HistoricoStatus.o:$(SRCDIR)/HistoricoStatus.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/HistoricoStatus.cpp -o $*.o
lib/StatusUnidade.o:$(SRCDIR)/StatusUnidade.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/StatusUnidade.cpp -o $*.o
lib/MotivoDevolucao.o:$(SRCDIR)/MotivoDevolucao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/MotivoDevolucao.cpp -o $*.o
lib/TipoCorrespondencia.o:$(SRCDIR)/TipoCorrespondencia.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoCorrespondencia.cpp -o $*.o
lib/TipoApresentacaoPergunta.o:$(SRCDIR)/TipoApresentacaoPergunta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoApresentacaoPergunta.cpp -o $*.o
lib/classCstAssunto.o:$(SRCDIR)/classCstAssunto.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCstAssunto.cpp -o $*.o
lib/classCstSubAssunto.o:$(SRCDIR)/classCstSubAssunto.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCstSubAssunto.cpp -o $*.o
lib/classCstAtributo.o:$(SRCDIR)/classCstAtributo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCstAtributo.cpp -o $*.o
lib/classCstValorPossivel.o:$(SRCDIR)/classCstValorPossivel.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCstValorPossivel.cpp -o $*.o
lib/classCstAtributoB01.o:$(SRCDIR)/classCstAtributoB01.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCstAtributoB01.cpp -o $*.o
lib/UF.o:$(SRCDIR)/UF.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/UF.cpp -o $*.o
lib/BuscaCorrespondencia.o:$(SRCDIR)/BuscaCorrespondencia.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/BuscaCorrespondencia.cpp -o $*.o
lib/BuscaPessoaCorrespondencia.o:$(SRCDIR)/BuscaPessoaCorrespondencia.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/BuscaPessoaCorrespondencia.cpp -o $*.o
lib/HistoricoStatusCorrespondencia.o:$(SRCDIR)/HistoricoStatusCorrespondencia.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/HistoricoStatusCorrespondencia.cpp -o $*.o
lib/Pais.o:$(SRCDIR)/Pais.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Pais.cpp -o $*.o
lib/PessoaSegmentacaoHistorico.o:$(SRCDIR)/PessoaSegmentacaoHistorico.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoHistorico.cpp -o $*.o
lib/Prospect.o:$(SRCDIR)/Prospect.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Prospect.cpp -o $*.o
lib/SistemaOrigem.o:$(SRCDIR)/SistemaOrigem.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SistemaOrigem.cpp -o $*.o
lib/TipoCarteira.o:$(SRCDIR)/TipoCarteira.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoCarteira.cpp -o $*.o
lib/TipoComunicacao.o:$(SRCDIR)/TipoComunicacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoComunicacao.cpp -o $*.o
lib/TipoDocumento.o:$(SRCDIR)/TipoDocumento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoDocumento.cpp -o $*.o
lib/LinhaFaturamento.o:$(SRCDIR)/LinhaFaturamento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaFaturamento.cpp -o $*.o
lib/ContaLinhaFaturamento.o:$(SRCDIR)/ContaLinhaFaturamento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaLinhaFaturamento.cpp -o $*.o
lib/Unidade.o:$(SRCDIR)/Unidade.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Unidade.cpp -o $*.o
lib/classCanal.o:$(SRCDIR)/classCanal.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCanal.cpp -o $*.o
lib/classDocumentoVO.o:$(SRCDIR)/classDocumentoVO.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classDocumentoVO.cpp -o $*.o
lib/classLogAcesso.o:$(SRCDIR)/classLogAcesso.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classLogAcesso.cpp -o $*.o
lib/classMeioContato.o:$(SRCDIR)/classMeioContato.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classMeioContato.cpp -o $*.o
lib/classPessoa.o:$(SRCDIR)/classPessoa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoa.cpp -o $*.o
lib/classPessoaCanalPreferido.o:$(SRCDIR)/classPessoaCanalPreferido.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoaCanalPreferido.cpp -o $*.o
lib/classPessoaRecusaMeioContato.o:$(SRCDIR)/classPessoaRecusaMeioContato.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoaRecusaMeioContato.cpp -o $*.o
lib/ConsultaHexa.o:$(SRCDIR)/ConsultaHexa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ConsultaHexa.cpp -o $*.o
lib/HLRHexa.o:$(SRCDIR)/HLRHexa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/HLRHexa.cpp -o $*.o
lib/MonitoramentoHexa.o:$(SRCDIR)/MonitoramentoHexa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/MonitoramentoHexa.cpp -o $*.o
lib/classTipoLinha.o:$(SRCDIR)/classTipoLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classTipoLinha.cpp -o $*.o
lib/classSegmentacao.o:$(SRCDIR)/classSegmentacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classSegmentacao.cpp -o $*.o
lib/classStatusUsuario.o:$(SRCDIR)/classStatusUsuario.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classStatusUsuario.cpp -o $*.o
lib/classEstadoLinha.o:$(SRCDIR)/classEstadoLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classEstadoLinha.cpp -o $*.o
lib/BuscaCorrespondenciaTI.o:$(SRCDIR)/BuscaCorrespondenciaTI.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/BuscaCorrespondenciaTI.cpp -o $*.o
lib/ValorLivre.o:$(SRCDIR)/ValorLivre.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ValorLivre.cpp -o $*.o
lib/PessoaValorPossivel.o:$(SRCDIR)/PessoaValorPossivel.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaValorPossivel.cpp -o $*.o
lib/classCarteirizacao.o:$(SRCDIR)/classCarteirizacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCarteirizacao.cpp -o $*.o
lib/classBuscaDadosLinha.o:$(SRCDIR)/classBuscaDadosLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classBuscaDadosLinha.cpp -o $*.o
lib/classPlanoServicoLinha.o:$(SRCDIR)/classPlanoServicoLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPlanoServicoLinha.cpp -o $*.o
lib/classUsuario.o:$(SRCDIR)/classUsuario.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classUsuario.cpp -o $*.o
lib/classPessoaLinha.o:$(SRCDIR)/classPessoaLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoaLinha.cpp -o $*.o
lib/classLinhaTelefonica.o:$(SRCDIR)/classLinhaTelefonica.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classLinhaTelefonica.cpp -o $*.o
lib/classPessoaComunicacao.o:$(SRCDIR)/classPessoaComunicacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoaComunicacao.cpp -o $*.o
lib/classPessoaEndereco.o:$(SRCDIR)/classPessoaEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPessoaEndereco.cpp -o $*.o
lib/classTipoEndereco.o:$(SRCDIR)/classTipoEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classTipoEndereco.cpp -o $*.o
lib/RelCepLogradBairro.o:$(SRCDIR)/RelCepLogradBairro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/RelCepLogradBairro.cpp -o $*.o
lib/classCep.o:$(SRCDIR)/classCep.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classCep.cpp -o $*.o
lib/PessoaCR.o:$(SRCDIR)/PessoaCR.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaCR.cpp -o $*.o
lib/BuscaDocumentoPessoa.o:$(SRCDIR)/BuscaDocumentoPessoa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/BuscaDocumentoPessoa.cpp -o $*.o
lib/Conta.o:$(SRCDIR)/Conta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Conta.cpp -o $*.o
lib/Parametro.o:$(SRCDIR)/Parametro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Parametro.cpp -o $*.o
lib/Sexo.o:$(SRCDIR)/Sexo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Sexo.cpp -o $*.o
lib/EstadoCivil.o:$(SRCDIR)/EstadoCivil.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/EstadoCivil.cpp -o $*.o
lib/Ocupacao.o:$(SRCDIR)/Ocupacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Ocupacao.cpp -o $*.o
lib/Escolaridade.o:$(SRCDIR)/Escolaridade.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Escolaridade.cpp -o $*.o
lib/classPPValPreAtv.o:$(SRCDIR)/classPPValPreAtv.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classPPValPreAtv.cpp -o $*.o
lib/AreaRegistroBloqueadopc.o:$(SRCDIR)/AreaRegistroBloqueadopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueadopc.cpp -o $*.o
lib/PessoaFisicapc.o:$(SRCDIR)/PessoaFisicapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisicapc.cpp -o $*.o
lib/PessoaJuridicapc.o:$(SRCDIR)/PessoaJuridicapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridicapc.cpp -o $*.o
lib/PessoaDocumentopc.o:$(SRCDIR)/PessoaDocumentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumentopc.cpp -o $*.o
lib/FilaSetClientInfoPREpc.o:$(SRCDIR)/FilaSetClientInfoPREpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfoPREpc.cpp -o $*.o
lib/Documentopc.o:$(SRCDIR)/Documentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Documentopc.cpp -o $*.o
lib/ApoioTipoComunicacaopc.o:$(SRCDIR)/ApoioTipoComunicacaopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ApoioTipoComunicacaopc.cpp -o $*.o
lib/GestorContaPessoaContapc.o:$(SRCDIR)/GestorContaPessoaContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorContaPessoaContapc.cpp -o $*.o
lib/GestorContaContatopc.o:$(SRCDIR)/GestorContaContatopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorContaContatopc.cpp -o $*.o
lib/GestorContapc.o:$(SRCDIR)/GestorContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorContapc.cpp -o $*.o
lib/Portabilidadepc.o:$(SRCDIR)/Portabilidadepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Portabilidadepc.cpp -o $*.o
lib/LinhaBasepc.o:$(SRCDIR)/LinhaBasepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaBasepc.cpp -o $*.o
lib/LinhaContapc.o:$(SRCDIR)/LinhaContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaContapc.cpp -o $*.o
lib/Pedido.o:$(SRCDIR)/Pedido.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Pedido.cpp -o $*.o
lib/Pedidopc.o:$(SRCDIR)/Pedidopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Pedidopc.cpp -o $*.o
lib/OrdemNotaFiscalpc.o:$(SRCDIR)/OrdemNotaFiscalpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/OrdemNotaFiscalpc.cpp -o $*.o
lib/EtapaPedido.o:$(SRCDIR)/EtapaPedido.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/EtapaPedido.cpp -o $*.o
lib/EtapaPedidopc.o:$(SRCDIR)/EtapaPedidopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/EtapaPedidopc.cpp -o $*.o
lib/ParametrosPedidopc.o:$(SRCDIR)/ParametrosPedidopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ParametrosPedidopc.cpp -o $*.o
lib/ItemPedido.o:$(SRCDIR)/ItemPedido.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ItemPedido.cpp -o $*.o
lib/ItemPedidopc.o:$(SRCDIR)/ItemPedidopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ItemPedidopc.cpp -o $*.o
lib/OrdemVenda.o:$(SRCDIR)/OrdemVenda.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/OrdemVenda.cpp -o $*.o
lib/OrdemVendapc.o:$(SRCDIR)/OrdemVendapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/OrdemVendapc.cpp -o $*.o
lib/ItemOrdemVenda.o:$(SRCDIR)/ItemOrdemVenda.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ItemOrdemVenda.cpp -o $*.o
lib/ItemOrdemVendapc.o:$(SRCDIR)/ItemOrdemVendapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ItemOrdemVendapc.cpp -o $*.o
lib/ProdutoEntregapc.o:$(SRCDIR)/ProdutoEntregapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ProdutoEntregapc.cpp -o $*.o
lib/PessoaContapc.o:$(SRCDIR)/PessoaContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaContapc.cpp -o $*.o
lib/ContaEnderecopc.o:$(SRCDIR)/ContaEnderecopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEnderecopc.cpp -o $*.o
lib/PessoaLinhaHistoricopc.o:$(SRCDIR)/PessoaLinhaHistoricopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistoricopc.cpp -o $*.o
lib/md5.o:$(SRCDIR)/md5.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/md5.cpp -o $*.o
lib/3des.o:$(SRCDIR)/3des.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/3des.cpp -o $*.o
lib/MDFive.o:$(SRCDIR)/MDFive.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/MDFive.cpp -o $*.o
lib/TripleDES.o:$(SRCDIR)/TripleDES.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TripleDES.cpp -o $*.o
lib/CPacoteItr.o:$(SRCDIR)/CPacoteItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPacoteItr.cpp -o $*.o
lib/Funcoes.o:$(SRCDIR)/Funcoes.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Funcoes.cpp -o $*.o
lib/Exception.o:$(SRCDIR)/Exception.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Exception.cpp -o $*.o
lib/classContaPontuacao.o:$(SRCDIR)/classContaPontuacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classContaPontuacao.cpp -o $*.o
lib/classLinhaPonto.o:$(SRCDIR)/classLinhaPonto.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classLinhaPonto.cpp -o $*.o
lib/AtlysLFAjustes.o:$(SRCDIR)/AtlysLFAjustes.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFAjustes.cpp -o $*.o
lib/AtlysLFCobranca.o:$(SRCDIR)/AtlysLFCobranca.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFCobranca.cpp -o $*.o
lib/AtlysLFContaDetalhada.o:$(SRCDIR)/AtlysLFContaDetalhada.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFContaDetalhada.cpp -o $*.o
lib/AtlysLFFaturamento.o:$(SRCDIR)/AtlysLFFaturamento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFFaturamento.cpp -o $*.o
lib/AtlysLFLinhaIntraGrupo.o:$(SRCDIR)/AtlysLFLinhaIntraGrupo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFLinhaIntraGrupo.cpp -o $*.o
lib/AtlysLFPagamento.o:$(SRCDIR)/AtlysLFPagamento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFPagamento.cpp -o $*.o
lib/DES.o:$(SRCDIR)/DES.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/DES.cpp -o $*.o
lib/AtlysLFEstimativa.o:$(SRCDIR)/AtlysLFEstimativa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysLFEstimativa.cpp -o $*.o
lib/AtlysAbaServicos.o:$(SRCDIR)/AtlysAbaServicos.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AtlysAbaServicos.cpp -o $*.o
lib/FilaSetClientInfoPRE.o:$(SRCDIR)/FilaSetClientInfoPRE.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfoPRE.cpp -o $*.o
lib/Documento.o:$(SRCDIR)/Documento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Documento.cpp -o $*.o
lib/AreaRegistroBloqueado.o:$(SRCDIR)/AreaRegistroBloqueado.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueado.cpp -o $*.o
lib/PessoaFisica.o:$(SRCDIR)/PessoaFisica.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisica.cpp -o $*.o
lib/PessoaJuridica.o:$(SRCDIR)/PessoaJuridica.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridica.cpp -o $*.o
lib/Log.o:$(SRCDIR)/Log.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Log.cpp -o $*.o
lib/PessoaDocumento.o:$(SRCDIR)/PessoaDocumento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumento.cpp -o $*.o
lib/ApoioTipoComunicacao.o:$(SRCDIR)/ApoioTipoComunicacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ApoioTipoComunicacao.cpp -o $*.o
lib/GestorContaPessoaConta.o:$(SRCDIR)/GestorContaPessoaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorContaPessoaConta.cpp -o $*.o
lib/GestorContaContato.o:$(SRCDIR)/GestorContaContato.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorContaContato.cpp -o $*.o
lib/GestorConta.o:$(SRCDIR)/GestorConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorConta.cpp -o $*.o
lib/Portabilidade.o:$(SRCDIR)/Portabilidade.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Portabilidade.cpp -o $*.o
lib/LinhaBase.o:$(SRCDIR)/LinhaBase.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaBase.cpp -o $*.o
lib/LinhaConta.o:$(SRCDIR)/LinhaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaConta.cpp -o $*.o
lib/PessoaConta.o:$(SRCDIR)/PessoaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaConta.cpp -o $*.o
lib/ContaEndereco.o:$(SRCDIR)/ContaEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEndereco.cpp -o $*.o
lib/PessoaLinhaHistorico.o:$(SRCDIR)/PessoaLinhaHistorico.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistorico.cpp -o $*.o
lib/Tools.o:$(SRCDIR)/Tools.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Tools.cpp -o $*.o
lib/LogIMEI.o:$(SRCDIR)/LogIMEI.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LogIMEI.cpp -o $*.o
lib/LogGSM.o:$(SRCDIR)/LogGSM.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LogGSM.cpp -o $*.o
lib/LogFilaSetClientInfo.o:$(SRCDIR)/LogFilaSetClientInfo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LogFilaSetClientInfo.cpp -o $*.o
lib/ParametroEntrega.o:$(SRCDIR)/ParametroEntrega.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ParametroEntrega.cpp -o $*.o
lib/GestorMisc.o:$(SRCDIR)/GestorMisc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/GestorMisc.cpp -o $*.o
lib/LogSIMLock.o:$(SRCDIR)/LogSIMLock.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LogSIMLock.cpp -o $*.o
lib/LogDeviceManager.o:$(SRCDIR)/LogDeviceManager.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LogDeviceManager.cpp -o $*.o
lib/ApoioStatusUsuario.o:$(SRCDIR)/ApoioStatusUsuario.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ApoioStatusUsuario.cpp -o $*.o
lib/ApoioParametro.o:$(SRCDIR)/ApoioParametro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ApoioParametro.cpp -o $*.o
lib/FilaSetClientInfo.o:$(SRCDIR)/FilaSetClientInfo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfo.cpp -o $*.o
lib/SaldoPontos.o:$(SRCDIR)/SaldoPontos.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SaldoPontos.cpp -o $*.o
lib/LinhaConvergente.o:$(SRCDIR)/LinhaConvergente.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaConvergente.cpp -o $*.o

#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
 		$(LINK) -G -o $(LIBDIR)/lib$(SO_AUX).so $(OBJ) $(FWLIB) $(PLIBPATH)

install:
	if ! [ -d $(TUXDIR_DES)/$(CLIENTE_PTH) ]; then mkdir $(TUXDIR_DES)/$(CLIENTE_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBCLI) ]; then mkdir $(LIBCLI) 2> /dev/null; fi
	cp -f $(LIBDIR)/lib$(SO_AUX).so $(LIBCLI)
#	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
#	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

insbin:
	cp -f $(LIBDIR)/lib$(SUBDIR).so /opt/esac/dvfo01rt/server/lib

clean:
	rm -fr $(LIBDIR)/lib$(SO_AUX).so*
#	rm -f $(OBJ)
	rm -fr $(LIBDIR)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
