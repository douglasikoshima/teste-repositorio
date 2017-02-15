include ../../defines
DIR=servidor
SERVER=sVOLn01
SRCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/src
LIBDIR=$(BASEPATH)/$(DIR)/$(SERVER)/lib
INCDIR=$(BASEPATH)/$(DIR)/$(SERVER)/include/$(SERVER)
MSGDIR=$(BASEPATH)/$(DIR)/$(SERVER)/messages
#EXTDEP=lista de bibliotecas utilizadas pelo servidor
#Commons=PessoaAbstract
#Negocio=Defines Util Menu Senha Endereco Relacionamento Processo Pessoa Servico Ponto Linha Lojista
#Servico=LstMenu ValPriAcesso DadMenu
EXTDEP=-lDOMTreeReporter -lGet -lTuxHelperClever -lUtil -ladmatdCmm -lWFLstGrupoAssoc -lWFLstPerfilAssoc -lCTTGRPLISTAR -lcgps -lCttRetRelacao -lCttListarCont -lWFLstPrfGru -lCnsRetorno
all: $(LIBDIR)/$(SERVER)
# Link
$(LIBDIR)/$(SERVER): $(OBJ)
	if ! [ -d $(LIBDIR) ]; then mkdir $(LIBDIR); fi
	$(TUXDIR)/bin/buildserver \
	-o $(LIBDIR)/$(SERVER) \
	-r Oracle_XA \
	-f "$(PLIBPATH) $(DESLIBDIRS) " \
	-f "$(OBJ) $(EXTDEP)" \
	-f "-lxalan-c -lxerces-c -ltuxfw" \
	-s "LSTGRUPOASSOC" \
	-s "LSTPERFILASSOC" \
	-s "CTTGRPLISTAR" \
	-s "readGrpsProcVO" \
	-s "CttRetRelacao" \
	-s "CttListarCont" \
	-s "LSTPERFILGRU" \
	-s "READRETORNO"
	if [ -f $(LIBDIR)/$(SERVER).txt ]; then rm $(LIBDIR)/$(SERVER).txt; fi
	echo "$(EXTDEP)" > $(LIBDIR)/$(SERVER).txt 
install:
	if [ -f $(TUXDIR_DES)/bin/$(SERVER) ]; then rm -f $(TUXDIR_DES)/bin/$(SERVER); fi
	cp $(LIBDIR)/$(SERVER) $(TUXDIR_DES)/bin
	cp $(LIBDIR)/$(SERVER).txt $(TUXDIR_DES)/bin
	if [ -f $(INCDIR)/*.h* ]; then cp -f $(INCDIR)/*.h* $(TUXDIR_DES)/include/$(SERVER); fi
	if [ -f $(MSGDIR)/*.h* ]; then cp -f $(MSGDIR)/*.h* $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.fml ]; then cp -f $(MSGDIR)/*.fml $(TUXDIR_DES)/messages; fi
	if [ -f $(MSGDIR)/*.xsd ]; then cp -f $(MSGDIR)/*.xsd $(TUXDIR_DES)/messages; fi
	chmod ug+rwx $(TUXDIR_DES)/bin/$(SERVER)
clean:
	rm -f $(LIBDIR)/$(SERVER)
	rm -f $(OBJ)	
