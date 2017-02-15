#
# $Id: makefile,v 1.1 2009/07/31 15:34:01 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env

# Modificacao dos parametros do Pro*C
# Os modulos deste codigo fazem include(Pro*C), necessario incluir o diretorio include
# na pesquisa
PROC=proc INCLUDE=$(TUXINCDIR) INCLUDE=include code=cpp cpp_suffix=cpp parse=none


DIR=servico
SUBDIR=ins_listacont
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
MODULODIR=$(TUXDIR_DES)/$(CAMPANHA_PTH)
MODULOLIB=$(TUXDIR_DES)/$(CAMPANHA_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP= ins_listaconteudo.pcpp ins_pessoalista.pcpp get_idpessoadepara.pcpp
CPP= libins_listacont.cpp 
OBJ= lib/libins_listacont.o lib/ins_listaconteudo.o lib/ins_pessoalista.o \
     lib/get_idpessoadepara.o 
CUSTSO=orautil cpputil cmputil

all:
#	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

install:
#	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

insbin:
#	cp -f $(LIBDIR)/lib$(SUBDIR).so $(SVRPATH)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(LIBDIR)/lib$(SUBDIR).o*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..


