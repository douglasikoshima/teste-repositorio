#
# $Id: makefile,v 1.1 2009/07/31 15:34:35 a5110702 Exp $
#
.SILENT:

include ../../defines
include $(HOME)/TUXEFO/modulos.env

DIR=servico
SUBDIR=PrfUniRelaciona
SRCDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages
MODULODIR=$(TUXDIR_DES)/$(USUARIO_PTH)
MODULOLIB=$(TUXDIR_DES)/$(USUARIO_PTH)/lib

#
#  Fontes
#  PCPP=lista de fontes a ser precompilado pelo PRO*C
#  CPP=lista de fontes a ser compilado
#  OBJ=lista de obj´s a ser linkado para gerar o servicex
#
PCPP=
CPP= PrfUniRelaciona.cpp
OBJ= lib/PrfUniRelaciona.o
CUSTSO=acessoCmm

all: $(SUBDIR)

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
     
#$(SRCDIR)/PrfUniRelaciona.cpp:
#	$(PROC) $*.pcpp

#
#  Compile
#  lib/obj_tpl.o:$(SRCDIR)/cpp.cpp makefile
#        $(SACCPP) -Iinclude $(PINCLUDE) -I$(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
#
lib/PrfUniRelaciona.o:$(SRCDIR)/PrfUniRelaciona.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) $(COMPFLAGS) $(SRCDIR)/PrfUniRelaciona.cpp -o $*.o  


#
# Link
#
$(SUBDIR): $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIRS) \
		$(LINK) -G -o $(LIBDIR)/lib$(SUBDIR).so $(OBJ) $(FWLIB) $(XERCES_CLIB)

install:
	if ! [ -d $(MODULODIR) ]; then mkdir $(MODULODIR); fi
	if ! [ -d $(MODULOLIB) ]; then mkdir $(MODULOLIB); fi
	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/$(USUARIO_PTH)/lib
#	cp $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
#	@-mkdir $(TUXDIR_DES)/include/$(SUBDIR)  2> /dev/null
#	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR) 2> /dev/null

insbin:
	cp -f $(LIBDIR)/lib$(SUBDIR).so /opt/esac/dvfo01rt/server/lib

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); cd ..
	cd src; rm -f $(PCPP:.pcpp=.lis); cd ..

