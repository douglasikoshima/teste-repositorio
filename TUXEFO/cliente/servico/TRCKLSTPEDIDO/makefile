#
# $Id: makefile,v 1.1 2009/07/31 15:34:54 a5110702 Exp $
#
.SILENT:

include ../../defines

DIR=servico
SUBDIR=TRCKLSTPEDIDO
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

LIBCLI=$(TUXDIR_DES)/$(CLIENTE_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=
CPP= main.cpp
OBJ= lib/main.o
CUSTSO=clienteCmm

all: $(SUBDIR)

#INCAUX= `echo $(CUSTSO) | awk '{ flds=split($$0,v," ");    \
#                         for(i=1;i<=flds;i++)              \
#                          printf("$(DESINCDIRS)/%s ",v[i]);\
#                         next}'`

INCAUX=-I$(HOME)/TUXEFO/cliente/negocio/clienteCmm/include/

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
     
$(SRCDIR)/main.cpp:
	$(PROC) include=$(TUXDIR_DES)/include/$(CUSTSO) $*.pcpp

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#main
lib/main.o:$(SRCDIR)/main.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/main.cpp -o $*.o  


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(TUXDIR_DES)/$(CLIENTE_PTH) ]; then mkdir $(TUXDIR_DES)/$(CLIENTE_PTH) 2> /dev/null; fi
	if ! [ -d $(LIBCLI) ]; then mkdir $(LIBCLI) 2> /dev/null; fi
	@-cp -f $(LIBDIR)/lib$(SUBDIR).so $(LIBCLI)

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -fr $(LIBDIR)
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..
