include ../defines

SUBDIR=TuxHelperClever
SRCDIR=$(BASEPATH)/$(SUBDIR)/src
LIBDIR=$(BASEPATH)/$(SUBDIR)/lib
INCDIR=$(BASEPATH)/$(SUBDIR)/include/$(SUBDIR)
MSGDIR=$(BASEPATH)/$(SUBDIR)/messages


#CPP=lista de fontes a ser compilado
SRC=$(CPP)

#OBJ=lista de obj´s a ser linkado para gerar o servicex
OBJ=lib/MemoryManager.o lib/TuxHelperClever.o

all: $(LIBDIR)/lib$(SUBDIR).so


# Compile	
lib/MemoryManager.o:$(SRCDIR)/MemoryManager.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/MemoryManager.cpp -o $*.o
lib/TuxHelperClever.o:$(SRCDIR)/TuxHelperClever.cpp makefile
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(SACCPP) -Iinclude -Imessages $(PINCLUDE) $(DESINCDIRS) $(CFLAGS) $(SRCDIR)/TuxHelperClever.cpp -o $*.o

# Link
$(LIBDIR)/lib$(SUBDIR).so: $(OBJ)
	$(SACCPP) $(PLIBPATH) $(DESLIBDIR) $(LINK) $(LIBDIR)/lib$(SUBDIR).so $(OBJ) -lxml2

install:
	if [ -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so ]; then rm -f $(TUXDIR_DES)/lib/lib$(SUBDIR).so; fi
	cp -p $(LIBDIR)/lib$(SUBDIR).so $(TUXDIR_DES)/lib
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi

clean:
	rm -f $(LIBDIR)/lib$(SUBDIR).so*
	rm -f $(OBJ)

install_h:
	if ! [ -d $(TUXDIR_DES)/include/$(SUBDIR) ]; then mkdir $(TUXDIR_DES)/include/$(SUBDIR); fi
	if [ -f  $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SUBDIR); fi
