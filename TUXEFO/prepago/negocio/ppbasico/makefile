#
# $Id: makefile,v 1.1.2.1.90.1.10.1.18.1 2015/09/22 13:56:26 a5110705 Exp $
#
.SILENT:

include ../../defines

DIR=negocio
SUBDIR=ppbasico
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
SO_AUX=ppbasico

include $(HOME)/TUXEFO/modulos.env
LIBPP=$(TUXDIR_DES)/$(PREPAGO_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=PsqPFDocDadosContatopc.pcpp PsqPFDocDadosDocpc.pcpp PsqPFDocEMailCompc.pcpp PsqPFDocEMailPartpc.pcpp PsqPFDocLinPospc.pcpp PsqPFDocLinPrepc.pcpp PsqPJDocLinPospc.pcpp PsqPJDocLinPrepc.pcpp CEstadoCivil.pcpp PermissaoLinhaPUPpc.pcpp RegistraContatopc.pcpp FilaSetClientInfopc.pcpp CSexo.pcpp CTipoCarteira.pcpp CTipoDocumento.pcpp CCnae.pcpp CTipoEndereco.pcpp CTipoComunicacao.pcpp CTipoLinha.pcpp CUF.pcpp CPais.pcpp CValorPossivel.pcpp Parametropc.pcpp PessoaLinhaHistoricopc.pcpp Documentopc.pcpp PessoaComunicacaopc.pcpp PessoaLinhaPrepc.pcpp PessoaDocumentopc.pcpp UFpc.pcpp PessoaEnderecopc.pcpp PessoaLinhapc.pcpp Pessoapc.pcpp AreaRegistroBloqueadoPrepc.pcpp PessoaFisicapc.pcpp PessoaJuridicapc.pcpp PessoaContapc.pcpp ContaEnderecopc.pcpp LinhaContapc.pcpp SistemaOrigempc.pcpp PessoaValorPossivelpc.pcpp CLinhaBase.pcpp PessoaSegmentacaopc.pcpp PessoaSegmentacaoHistoricopc.pcpp CPesquisaPessoa.pcpp CCFOP.pcpp TipoContapc.pcpp TipoEnderecoCobrancapc.pcpp TipoRelacionamentopc.pcpp Contapc.pcpp TipoDocumentopc.pcpp classGravaLegado.pcpp ProcCadastro.pcpp ProcCadastroPP.pcpp ProcCadastroPPSeg.pcpp SincronizaPP.pcpp
CPP=PsqPFDocDadosContatopc.cpp PsqPFDocDadosDocpc.cpp PsqPFDocEMailCompc.cpp PsqPFDocEMailPartpc.cpp PsqPFDocLinPospc.cpp PsqPFDocLinPrepc.cpp PsqPJDocLinPospc.cpp PsqPJDocLinPrepc.cpp CEstadoCivilItr.cpp PermissaoLinhaPUP.cpp RegistraContato.cpp FilaSetClientInfo.cpp CSexoItr.cpp CTipoCarteiraItr.cpp CTipoDocumentoItr.cpp CCnaeItr.cpp CTipoEnderecoItr.cpp CTipoComunicacao.cpp CTipoComunicacaoItr.cpp CTipoLinhaItr.cpp CUFItr.cpp CPaisItr.cpp CValorPossivelItr.cpp CLinhaBaseItr.cpp Parametro.cpp PessoaLinhaHistorico.cpp Documento.cpp Pessoa.cpp PessoaComunicacao.cpp PessoaLinhaPre.cpp PessoaDocumento.cpp UF.cpp PessoaEndereco.cpp PessoaLinha.cpp PrePagoException.cpp Tools.cpp AreaRegistroBloqueadoPre.cpp PessoaFisica.cpp PessoaJuridica.cpp PessoaConta.cpp ContaEndereco.cpp LinhaConta.cpp SistemaOrigem.cpp PessoaValorPossivel.cpp PessoaSegmentacao.cpp PessoaSegmentacaoHistorico.cpp CPesquisaPessoaItr.cpp CCFOPItr.cpp TipoConta.cpp TipoEnderecoCobranca.cpp TipoRelacionamento.cpp Conta.cpp TipoDocumento.cpp CSafePointer.cpp classGravaLegadoItr.cpp classGravaLegado.cpp ProcCadastro.cpp ProcCadastroPP.cpp ProcCadastroPPSeg.cpp SincronizaPP.cpp
OBJ=lib/PsqPFDocDadosContatopc.o lib/PsqPFDocDadosDocpc.o lib/PsqPFDocEMailCompc.o lib/PsqPFDocEMailPartpc.o lib/PsqPFDocLinPospc.o lib/PsqPFDocLinPrepc.o lib/PsqPJDocLinPospc.o lib/PsqPJDocLinPrepc.o lib/CEstadoCivilItr.o lib/PermissaoLinhaPUP.o lib/PermissaoLinhaPUPpc.o lib/RegistraContato.o lib/RegistraContatopc.o lib/FilaSetClientInfo.o lib/FilaSetClientInfopc.o lib/CSexoItr.o lib/CTipoCarteiraItr.o lib/CTipoDocumentoItr.o lib/CTipoEnderecoItr.o lib/CTipoComunicacaoItr.o lib/CTipoLinhaItr.o lib/CUFItr.o lib/CPaisItr.o lib/CValorPossivelItr.o lib/CLinhaBaseItr.o lib/CEstadoCivil.o lib/CSexo.o lib/CTipoCarteira.o lib/CTipoDocumento.o lib/CTipoEndereco.o lib/CTipoComunicacao.o lib/CTipoLinha.o lib/CUF.o lib/CPais.o lib/CValorPossivel.o lib/CLinhaBase.o lib/Parametropc.o lib/PessoaLinhaHistoricopc.o lib/Documentopc.o lib/PessoaComunicacaopc.o lib/PessoaLinhaPrepc.o lib/PessoaDocumentopc.o lib/UFpc.o lib/PessoaEnderecopc.o lib/PessoaLinhapc.o lib/Pessoapc.o lib/Parametro.o lib/PessoaLinhaHistorico.o lib/Documento.o lib/Pessoa.o lib/PessoaComunicacao.o lib/PessoaLinhaPre.o lib/PessoaDocumento.o lib/UF.o lib/PessoaEndereco.o lib/PessoaLinha.o lib/PrePagoException.o lib/Tools.o lib/AreaRegistroBloqueadoPrepc.o lib/PessoaFisicapc.o lib/PessoaJuridicapc.o lib/AreaRegistroBloqueadoPre.o lib/PessoaFisica.o lib/PessoaJuridica.o lib/PessoaConta.o lib/PessoaContapc.o lib/ContaEndereco.o lib/ContaEnderecopc.o lib/LinhaConta.o lib/LinhaContapc.o lib/SistemaOrigem.o lib/SistemaOrigempc.o lib/PessoaValorPossivel.o lib/PessoaValorPossivelpc.o lib/PessoaSegmentacaopc.o lib/PessoaSegmentacao.o lib/PessoaSegmentacaoHistoricopc.o lib/PessoaSegmentacaoHistorico.o lib/CPesquisaPessoaItr.o lib/CPesquisaPessoa.o lib/CCnaeItr.o lib/CCnae.o lib/CCFOP.o lib/CCFOPItr.o lib/TipoContapc.o lib/TipoEnderecoCobrancapc.o lib/TipoRelacionamentopc.o lib/TipoConta.o lib/TipoEnderecoCobranca.o lib/TipoRelacionamento.o lib/Conta.o lib/Contapc.o lib/TipoDocumento.o lib/TipoDocumentopc.o lib/CSafePointer.o lib/classGravaLegadoItr.o lib/classGravaLegado.o lib/ProcCadastro.o lib/ProcCadastroPP.o lib/ProcCadastroPPSeg.o lib/SincronizaPP.o
CUSTSO=

all: $(SUBDIR)

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#
$(SRCDIR)/PsqPFDocDadosContatopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPFDocDadosDocpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPFDocEMailCompc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPFDocEMailPartpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPFDocLinPospc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPFDocLinPrepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPJDocLinPospc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PsqPJDocLinPrepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PermissaoLinhaPUP.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PermissaoLinhaPUPpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSafePointer.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesquisaPessoa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ContaEnderecopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaoHistorico.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaSegmentacaoHistoricopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/LinhaContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaValorPossivel.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaValorPossivelpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/SistemaOrigem.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/SistemaOrigempc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaFisicapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AreaRegistroBloqueadoPrepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaJuridicapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Pessoapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaEnderecopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/UFpc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaPrepc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDocumentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaComunicacaopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Documentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaHistoricopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Parametropc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CEstadoCivilItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSexoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoCarteiraItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCFOPItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoDocumentoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCnaeItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoEnderecoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoLinhaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoComunicacaoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUFItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPaisItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CValorPossivelItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPesquisaPessoaItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CEstadoCivil.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CSexo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoCarteira.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCFOP.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoDocumento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CCnae.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CTipoComunicacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CUF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CPais.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CValorPossivel.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Documento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaHistorico.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Parametro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Pessoa.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaComunicacao.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinhaPre.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaDocumento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaEndereco.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/UF.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaLinha.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PrePagoException.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Tools.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaFisica.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/AreaRegistroBloqueadoPre.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/PessoaJuridica.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CLinhaBase.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/CLinhaBaseItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoContapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Contapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/Conta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/RegistraContatopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/RegistraContato.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/FilaSetClientInfo.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoEnderecoCobrancapc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoRelacionamentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoDocumentopc.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoConta.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoEnderecoCobranca.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/TipoRelacionamento.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classGravaLegado.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/classGravaLegadoItr.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ProcCadastro.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ProcCadastroPP.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/ProcCadastroPPSeg.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/SincronizaPP.cpp:
	$(PROC) $*.pcpp	
	

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
#COMPFLAGS= -Iinclude -I$(INCDIR) $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  FML
#  messages/msg_tpl.fml.h:messages/msg_tpl.fml
#	cd messages; MKFLDHDR messages/msg_tpl.fml; cd ..
#

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#

lib/CEstadoCivilItr.o:$(SRCDIR)/CEstadoCivilItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CEstadoCivilItr.cpp -o $*.o
lib/PsqPFDocDadosContatopc.o:$(SRCDIR)/PsqPFDocDadosContatopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocDadosContatopc.cpp -o $*.o
lib/PsqPFDocDadosDocpc.o:$(SRCDIR)/PsqPFDocDadosDocpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocDadosDocpc.cpp -o $*.o
lib/PsqPFDocEMailCompc.o:$(SRCDIR)/PsqPFDocEMailCompc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocEMailCompc.cpp -o $*.o
lib/PsqPFDocEMailPartpc.o:$(SRCDIR)/PsqPFDocEMailPartpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocEMailPartpc.cpp -o $*.o
lib/PsqPFDocLinPospc.o:$(SRCDIR)/PsqPFDocLinPospc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocLinPospc.cpp -o $*.o
lib/PsqPFDocLinPrepc.o:$(SRCDIR)/PsqPFDocLinPrepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPFDocLinPrepc.cpp -o $*.o
lib/PsqPJDocLinPospc.o:$(SRCDIR)/PsqPJDocLinPospc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPJDocLinPospc.cpp -o $*.o
lib/PsqPJDocLinPrepc.o:$(SRCDIR)/PsqPJDocLinPrepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PsqPJDocLinPrepc.cpp -o $*.o
lib/CPesquisaPessoaItr.o:$(SRCDIR)/CPesquisaPessoaItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesquisaPessoaItr.cpp -o $*.o
lib/CSexoItr.o:$(SRCDIR)/CSexoItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSexoItr.cpp -o $*.o
lib/CTipoCarteiraItr.o:$(SRCDIR)/CTipoCarteiraItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoCarteiraItr.cpp -o $*.o
lib/CCFOPItr.o:$(SRCDIR)/CCFOPItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCFOPItr.cpp -o $*.o
lib/CTipoDocumentoItr.o:$(SRCDIR)/CTipoDocumentoItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoDocumentoItr.cpp -o $*.o
lib/CCnaeItr.o:$(SRCDIR)/CCnaeItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCnaeItr.cpp -o $*.o
lib/CTipoEnderecoItr.o:$(SRCDIR)/CTipoEnderecoItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoEnderecoItr.cpp -o $*.o
lib/CTipoLinhaItr.o:$(SRCDIR)/CTipoLinhaItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoLinhaItr.cpp -o $*.o
lib/CTipoComunicacaoItr.o:$(SRCDIR)/CTipoComunicacaoItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoComunicacaoItr.cpp -o $*.o
lib/CUFItr.o:$(SRCDIR)/CUFItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUFItr.cpp -o $*.o
lib/CPaisItr.o:$(SRCDIR)/CPaisItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPaisItr.cpp -o $*.o
lib/CValorPossivelItr.o:$(SRCDIR)/CValorPossivelItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CValorPossivelItr.cpp -o $*.o
lib/CEstadoCivil.o:$(SRCDIR)/CEstadoCivil.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CEstadoCivil.cpp -o $*.o
lib/CSexo.o:$(SRCDIR)/CSexo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSexo.cpp -o $*.o
lib/CTipoCarteira.o:$(SRCDIR)/CTipoCarteira.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoCarteira.cpp -o $*.o
lib/CCFOP.o:$(SRCDIR)/CCFOP.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCFOP.cpp -o $*.o
lib/CTipoDocumento.o:$(SRCDIR)/CTipoDocumento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoDocumento.cpp -o $*.o
lib/CCnae.o:$(SRCDIR)/CCnae.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CCnae.cpp -o $*.o
lib/CTipoEndereco.o:$(SRCDIR)/CTipoEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoEndereco.cpp -o $*.o
lib/CTipoLinha.o:$(SRCDIR)/CTipoLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoLinha.cpp -o $*.o
lib/CTipoComunicacao.o:$(SRCDIR)/CTipoComunicacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CTipoComunicacao.cpp -o $*.o
lib/CUF.o:$(SRCDIR)/CUF.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CUF.cpp -o $*.o
lib/CPais.o:$(SRCDIR)/CPais.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPais.cpp -o $*.o
lib/CValorPossivel.o:$(SRCDIR)/CValorPossivel.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CValorPossivel.cpp -o $*.o
lib/Documento.o:$(SRCDIR)/Documento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Documento.cpp -o $*.o
lib/PessoaLinhaHistorico.o:$(SRCDIR)/PessoaLinhaHistorico.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistorico.cpp -o $*.o
lib/Parametro.o:$(SRCDIR)/Parametro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Parametro.cpp -o $*.o
lib/Pessoa.o:$(SRCDIR)/Pessoa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Pessoa.cpp -o $*.o
lib/PessoaComunicacao.o:$(SRCDIR)/PessoaComunicacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaComunicacao.cpp -o $*.o
lib/PessoaLinhaPre.o:$(SRCDIR)/PessoaLinhaPre.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaPre.cpp -o $*.o
lib/PessoaDocumento.o:$(SRCDIR)/PessoaDocumento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumento.cpp -o $*.o
lib/PessoaEndereco.o:$(SRCDIR)/PessoaEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaEndereco.cpp -o $*.o
lib/UF.o:$(SRCDIR)/UF.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/UF.cpp -o $*.o
lib/PessoaConta.o:$(SRCDIR)/PessoaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaConta.cpp -o $*.o
lib/PessoaValorPossivel.o:$(SRCDIR)/PessoaValorPossivel.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaValorPossivel.cpp -o $*.o
lib/SistemaOrigem.o:$(SRCDIR)/SistemaOrigem.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SistemaOrigem.cpp -o $*.o
lib/PessoaSegmentacao.o:$(SRCDIR)/PessoaSegmentacao.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacao.cpp -o $*.o
lib/PessoaSegmentacaoHistorico.o:$(SRCDIR)/PessoaSegmentacaoHistorico.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoHistorico.cpp -o $*.o
lib/LinhaConta.o:$(SRCDIR)/LinhaConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaConta.cpp -o $*.o
lib/ContaEndereco.o:$(SRCDIR)/ContaEndereco.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEndereco.cpp -o $*.o
lib/PessoaLinha.o:$(SRCDIR)/PessoaLinha.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinha.cpp -o $*.o
lib/PrePagoException.o:$(SRCDIR)/PrePagoException.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PrePagoException.cpp -o $*.o
lib/Tools.o:$(SRCDIR)/Tools.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Tools.cpp -o $*.o
lib/AreaRegistroBloqueadoPre.o:$(SRCDIR)/AreaRegistroBloqueadoPre.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueadoPre.cpp -o $*.o
lib/PessoaFisica.o:$(SRCDIR)/PessoaFisica.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisica.cpp -o $*.o
lib/PessoaJuridica.o:$(SRCDIR)/PessoaJuridica.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridica.cpp -o $*.o
lib/PessoaComunicacaopc.o:$(SRCDIR)/PessoaComunicacaopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaComunicacaopc.cpp -o $*.o
lib/Documentopc.o:$(SRCDIR)/Documentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Documentopc.cpp -o $*.o
lib/PessoaLinhaHistoricopc.o:$(SRCDIR)/PessoaLinhaHistoricopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaHistoricopc.cpp -o $*.o
lib/Parametropc.o:$(SRCDIR)/Parametropc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Parametropc.cpp -o $*.o
lib/PessoaLinhaPrepc.o:$(SRCDIR)/PessoaLinhaPrepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhaPrepc.cpp -o $*.o
lib/PessoaDocumentopc.o:$(SRCDIR)/PessoaDocumentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaDocumentopc.cpp -o $*.o
lib/PessoaEnderecopc.o:$(SRCDIR)/PessoaEnderecopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaEnderecopc.cpp -o $*.o
lib/UFpc.o:$(SRCDIR)/UFpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/UFpc.cpp -o $*.o
lib/PessoaContapc.o:$(SRCDIR)/PessoaContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaContapc.cpp -o $*.o
lib/PessoaValorPossivelpc.o:$(SRCDIR)/PessoaValorPossivelpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaValorPossivelpc.cpp -o $*.o
lib/SistemaOrigempc.o:$(SRCDIR)/SistemaOrigempc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SistemaOrigempc.cpp -o $*.o
lib/PessoaSegmentacaopc.o:$(SRCDIR)/PessoaSegmentacaopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaopc.cpp -o $*.o
lib/PessoaSegmentacaoHistoricopc.o:$(SRCDIR)/PessoaSegmentacaoHistoricopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaSegmentacaoHistoricopc.cpp -o $*.o
lib/LinhaContapc.o:$(SRCDIR)/LinhaContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/LinhaContapc.cpp -o $*.o
lib/ContaEnderecopc.o:$(SRCDIR)/ContaEnderecopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ContaEnderecopc.cpp -o $*.o
lib/PessoaLinhapc.o:$(SRCDIR)/PessoaLinhapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaLinhapc.cpp -o $*.o
lib/Pessoapc.o:$(SRCDIR)/Pessoapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Pessoapc.cpp -o $*.o
lib/AreaRegistroBloqueadoPrepc.o:$(SRCDIR)/AreaRegistroBloqueadoPrepc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/AreaRegistroBloqueadoPrepc.cpp -o $*.o
lib/PessoaFisicapc.o:$(SRCDIR)/PessoaFisicapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaFisicapc.cpp -o $*.o
lib/PessoaJuridicapc.o:$(SRCDIR)/PessoaJuridicapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PessoaJuridicapc.cpp -o $*.o	
lib/CLinhaBase.o:$(SRCDIR)/CLinhaBase.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CLinhaBase.cpp -o $*.o	
lib/CLinhaBaseItr.o:$(SRCDIR)/CLinhaBaseItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CLinhaBaseItr.cpp -o $*.o	
lib/CPesquisaPessoa.o:$(SRCDIR)/CPesquisaPessoa.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CPesquisaPessoa.cpp -o $*.o
lib/TipoContapc.o:$(SRCDIR)/TipoContapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoContapc.cpp -o $*.o
lib/TipoEnderecoCobrancapc.o:$(SRCDIR)/TipoEnderecoCobrancapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoEnderecoCobrancapc.cpp -o $*.o
lib/TipoRelacionamentopc.o:$(SRCDIR)/TipoRelacionamentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoRelacionamentopc.cpp -o $*.o
lib/TipoDocumentopc.o:$(SRCDIR)/TipoDocumentopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoDocumentopc.cpp -o $*.o
lib/TipoDocumento.o:$(SRCDIR)/TipoDocumento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoDocumento.cpp -o $*.o
lib/TipoConta.o:$(SRCDIR)/TipoConta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoConta.cpp -o $*.o
lib/TipoEnderecoCobranca.o:$(SRCDIR)/TipoEnderecoCobranca.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoEnderecoCobranca.cpp -o $*.o
lib/TipoRelacionamento.o:$(SRCDIR)/TipoRelacionamento.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/TipoRelacionamento.cpp -o $*.o
lib/Conta.o:$(SRCDIR)/Conta.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Conta.cpp -o $*.o
lib/Contapc.o:$(SRCDIR)/Contapc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/Contapc.cpp -o $*.o
lib/RegistraContato.o:$(SRCDIR)/RegistraContato.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/RegistraContato.cpp -o $*.o
lib/RegistraContatopc.o:$(SRCDIR)/RegistraContatopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/RegistraContatopc.cpp -o $*.o
lib/FilaSetClientInfo.o:$(SRCDIR)/FilaSetClientInfo.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfo.cpp -o $*.o
lib/FilaSetClientInfopc.o:$(SRCDIR)/FilaSetClientInfopc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/FilaSetClientInfopc.cpp -o $*.o
lib/CSafePointer.o:$(SRCDIR)/CSafePointer.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/CSafePointer.cpp -o $*.o
lib/classGravaLegado.o:$(SRCDIR)/classGravaLegado.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classGravaLegado.cpp -o $*.o
lib/classGravaLegadoItr.o:$(SRCDIR)/classGravaLegadoItr.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/classGravaLegadoItr.cpp -o $*.o
lib/PermissaoLinhaPUP.o:$(SRCDIR)/PermissaoLinhaPUP.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PermissaoLinhaPUP.cpp -o $*.o
lib/PermissaoLinhaPUPpc.o:$(SRCDIR)/PermissaoLinhaPUPpc.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PermissaoLinhaPUPpc.cpp -o $*.o
lib/ProcCadastro.o:$(SRCDIR)/ProcCadastro.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ProcCadastro.cpp -o $*.o
lib/ProcCadastroPP.o:$(SRCDIR)/ProcCadastroPP.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ProcCadastroPP.cpp -o $*.o
lib/ProcCadastroPPSeg.o:$(SRCDIR)/ProcCadastroPPSeg.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/ProcCadastroPPSeg.cpp -o $*.o
lib/SincronizaPP.o:$(SRCDIR)/SincronizaPP.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi 
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/SincronizaPP.cpp -o $*.o
#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SO_AUX).so $(OBJ) $(FWLIB) $(PLIBPATH)

install:
	if ! [ -d $(TUXDIR_DES)/$(PREPAGO_PTH) ]; then mkdir $(TUXDIR_DES)/$(PREPAGO_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBPP) ]; then mkdir $(LIBPP) 2> /dev/null; fi
	cp $(LIBDIR)/lib$(SO_AUX).so $(LIBPP)

insbin:
	cp -f $(LIBDIR)/lib$(SUBDIR).so /opt/esac/dvfo01rt/server/lib

clean:
	rm -f $(LIBDIR)/lib$(SO_AUX).so*
	rm -f $(OBJ)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
