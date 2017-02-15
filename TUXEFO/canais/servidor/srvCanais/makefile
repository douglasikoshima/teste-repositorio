include ../../defines

DIR=servidor
SERVER=srvCanais
SRCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/messages
CONFIGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/config

#EXTDEP=lista de bibliotecas utilizadas pelo servidor
#commons=DOMTreeReporter
#Negocio=Defines Util
#Servico=FILACOMPSVC, CADASTRAINSID, MSGCADSITEF, STATUSPTOCNT
EXTDEP=-lTuxHelperClever -lFilaServico -lFILACOMPSVC -lCadInSid -lCadastraInSid -lMSGCADSITEF -lCadastroMSGSitef -lLinhaCanais -lUtilCanais -lSetLstRestrita -lPontoContato -lsvcPontoContato -lFiltroCampanha -lsvcFiltroCampanha -lListaCampanha -lsvcListaCampanha -lsvcDetalheCampanha -lDetalheCampanha -lsvcCampanhaCrud -lCampanhaCrud

all: $(LIBDIR)/$(SERVER)

# Link

$(LIBDIR)/$(SERVER): $(OBJ)
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver \
	-o $(LIBDIR)/$(SERVER) \
	-r Oracle_XA \
	-f "$(PLIBPATH) $(DESLIBDIRS) " \
	-f "$(OBJ) $(EXTDEP)" \
	-f "-lxalan-c -lxerces-c -ltuxfw -L $(TUXDIR_DES)/Canais/lib " \
	-s "FILACOMPSVC" \
	-s "CADASTRAINSID" \
	-s "MSGCADSITEF" \
	-s "SETLSTRESTRITA" \
	-s "STATUSPTOCNT" \
	-s "LISTACAMPAN" \
	-s "CAMPFILTRO" \
	-s "DETALCAMPAN" \
	-s "CAMPANHCRUD" 
	if [ -f $(LIBDIR)/$(SERVER).txt ]; then rm $(LIBDIR)/$(SERVER).txt; fi
	echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 

install:
	if ! [ -d $(TUXDIR_DES)/Canais/bin ]; then mkdir -p $(TUXDIR_DES)/Canais/bin; fi
	if ! [ -d $(TUXDIR_DES)/Canais/config/env/app ]; then mkdir -p $(TUXDIR_DES)/Canais/config/env/app; fi
	if ! [ -d $(TUXDIR_DES)/Canais/config/xml/app ]; then mkdir -p $(TUXDIR_DES)/Canais/config/xml/app; fi
	if [ -f $(TUXDIR_DES)/Canais/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/Canais/bin/$(SERVER); fi
	if [ -f $(TUXDIR_DES)/Canais/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/Canais/bin/$(SERVER); fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/Canais/bin
	cp $(LIBDIR)/$(SERVER).txt $(TUXDIR_DES)/Canais/bin
	cp -f  $(CONFIGDIR)/$(SERVER).env  $(TUXDIR_DES)/Canais/config/env/app
	cp -f  $(CONFIGDIR)/$(SERVER).xml  $(TUXDIR_DES)/Canais/config/xml/app
	chmod ug+rwx $(TUXDIR_DES)/Canais/bin

clean:
	rm -f $(LIBDIR)/$(SERVER)