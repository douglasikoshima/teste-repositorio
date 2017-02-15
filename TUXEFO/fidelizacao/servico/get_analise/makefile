#
# $Id: makefile,v 1.1 2009/07/31 15:34:24 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env
MODULODIR=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

DIR=servico
SUBDIR=get_analise
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
#INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
INCDIR=$(BASEPATH)/negocio/fidutil/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP= get_analise.pcpp
CPP= libget_analise.cpp 
OBJ= lib/libget_analise.o lib/get_analise.o 
CUSTSO=orautil cpputil fidutil

all: config $(SUBDIR)

INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
                         for(i=1;i<=flds;i++)              \
                          printf("$(DESINCDIRS)/%s ",v[i]);\
                         next}'`
COMPFLAGS= -Iinclude $(PINCLUDE) $(DESINCDIRS)/$(SUBDIR) $(INCAUX) $(CFLAGS)

#
#  FML
#  messages/msg_tpl.fml.h:messages/msg_tpl.fml
#	cd messages; MKFLDHDR messages/msg_tpl.fml; cd ..
#

#
#  Pre Compile
#  $(SRCDIR)/cpp_tpl.cpp:
#	 $(PROC) $*.pcpp
#     
$(SRCDIR)/libget_analise.cpp:
	$(PROC) $*.pcpp
$(SRCDIR)/get_analise.cpp:
	$(PROC) $*.pcpp

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp_tpl.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/libget_analise.o:$(SRCDIR)/libget_analise.cpp makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/libget_analise.cpp -o $*.o  
lib/get_analise.o:$(SRCDIR)/get_analise.cpp  makefile
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/get_analise.cpp -o $*.o 


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)
	strip $(LIBDIR)/lib$(SUBDIR).so
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(FIDELIZACAO_PTH)/lib

insbin:
#	cp -f $(LIBDIR)/lib$(SUBDIR).so $(SVRPATH)/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

config :
	@-mkdir lib
