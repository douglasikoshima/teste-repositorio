# 
# $Id: makefile,v 1.1 2009/07/31 15:34:49 a5110702 Exp $
#
.SILENT:

include ../../defines

EXTDEP=clienteCmm GetUrlRouter
TUXSERVICES=GETURLROUTER

DIR=servidor
SERVER=sCLIn39
SRCDIR= $(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR= $(BASEPATH)/$(DIR)/$(SERVER)/lib
CFGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

ORALIBLINK=-lclntsh -lld -lm -lm -lc_r -lpthreads
ORAXACMD=-r Oracle_XA

CSOLNK=`echo $(EXTDEP)|awk '{flds=split($$0,v," ");for(i=1;i<=flds;i++)printf("-l-l%s ",v[i]);next}'`

PCPP=
CPP=
SRC=$(CPP) $(PCPP:.pcpp=.cpp)
OBJ=

all: $(SERVER)

#FML
messages/d.fml.h:messages/d.fml
	cd messages; MKFLDHDR messages/d.fml; cd ..

# Pre Compile
$(SRCDIR)/a.cpp:$(SRCDIR)/a.pcpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp
$(SRCDIR)/b.cpp:$(SRCDIR)/b.pcpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(PROC) $*.pcpp

# Compile
lib/a.o:$(SRCDIR)/a.cpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o
lib/b.o:$(SRCDIR)/b.cpp $(INCDIR)/b.hpp $(INCDIR)/a.hpp makefile
	$(SACCPP) -Iinclude $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/a.cpp -o $*.o

# Link
$(SERVER): 
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver -o $(LIBDIR)/$(SERVER)   \
           -f " $(OBJ) " -f " $(ORALIBLINK) -brtl"         \
           $(CSOLNK)                                       \
           -f " $(DESLIBDIRS) $(PLIBPATH) -L$(ORALIBDIR) "   \
           -f " $(FWLIB) $(XERCES_CLIB) " $(ORAXACMD)      \
           -s $(TUXSERVICES)
	@echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 


install:
	if ! [ -d $(TUXDIR_CLI) ]; then mkdir $(TUXDIR_CLI); fi
	if ! [ -d $(TUXDIR_CLIBIN) ]; then mkdir $(TUXDIR_CLIBIN); fi
	if ! [ -d $(TUXDIR_CLICFG) ]; then mkdir $(TUXDIR_CLICFG); fi
	if ! [ -d $(TUXDIR_CLICFG)/env ]; then mkdir $(TUXDIR_CLICFG)/env; fi
	if ! [ -d $(TUXDIR_CLICFG)/env/app ]; then mkdir $(TUXDIR_CLICFG)/env/app; fi
	if ! [ -d $(TUXDIR_CLICFG)/xml ]; then mkdir $(TUXDIR_CLICFG)/xml; fi
	if ! [ -d $(TUXDIR_CLICFG)/xml/app ]; then mkdir $(TUXDIR_CLICFG)/xml/app; fi
	cp -f $(LIBDIR)/$(SERVER) $(TUXDIR_CLIBIN)
	if [ -f $(CFGDIR)/$(SERVER).env ]; then cp -f $(CFGDIR)/$(SERVER).env $(TUXDIR_CLICFG)/env/app 2>/dev/null; fi
	if [ -f $(CFGDIR)/$(SERVER).xml ]; then cp -f $(CFGDIR)/$(SERVER).xml $(TUXDIR_CLICFG)/xml/app 2>/dev/null; fi

#	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/bin
#	@-cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SERVER) 2>/dev/null
#	@-cp -f $(MSGDIR)/* $(TUXDIR_DES)/messages 2>/dev/null 

insbin:
	cp -f $(LIBDIR)/$(SERVER) /opt/esac/dvfo01rt/server/bin

clean:
	rm -fr $(LIBDIR)
	@-rm -f $(OBJ) 2>/dev/null
