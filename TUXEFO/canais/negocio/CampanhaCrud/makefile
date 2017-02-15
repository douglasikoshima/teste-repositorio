#Compile Tuxedo negocio
include ../../defines

DIR=negocio
SUBDIR=CampanhaCrud
SRCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/include/$(SUBDIR)
INCPROC=$(BASEPATH)/$(DIR)/$(SUBDIR)/include
MSGDIR=$(BASEPATH)/$(DIR)/$(SUBDIR)/messages

#PCPP=lista de fontes a ser precompilado pelo PRO*C
PCPP=pcCampanhaCrud.pcpp

#CPP=lista de fontes a ser compilado
SRC=$(CPP) $(PCPP:.pcpp=.cpp)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/pcCampanhaCrud.o

all: $(LIBDIR)/lib$(SUBDIR).so

#FML
#messages/d.fml.h:messages/d.fml
#        cd messages; MKFLDHDR d.fml; cd ..

# Pre compile
$(SRCDIR)/pcCampanhaCrud.cpp:$(SRCDIR)/pcCampanhaCrud.pcpp makefile
	$(PROC) include=$(INCPROC) $*.pcpp

# Compile
lib/pcCampanhaCrud.o:$(SRCDIR)/pcCampanhaCrud.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/pcCampanhaCrud.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib	


install:
	if ! [ -d $(TUXDIR_DES)/Canais/lib ]; then mkdir -p $(TUXDIR_DES)/Canais/lib; fi
	if [ -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/Canais/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/Canais/lib	

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)	
	cd src; rm -f $(PCPP:.pcpp=.cpp); rm -f $(PCPP:.pcpp=.lis); cd ..

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
