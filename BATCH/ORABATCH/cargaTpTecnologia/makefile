
APPNAME=cargaTpTecnologia

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_LOG=$(COMPILE_DIR)/log
COMPILE_ERR=$(COMPILE_DIR)/error
COMPILE_DAT=$(COMPILE_DIR)/data

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/
EXEC_LOG=$(EXEC_DIR)/log
EXEC_ERR=$(EXEC_DIR)/error
EXEC_DAT=$(EXEC_DIR)/data

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/*.sh \
		  $(EXEC_DIR)/$(APPNAME).cfg \
		  $(EXEC_DAT)/*.txt \
		  $(EXEC_DIR)/*.ctl 

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_ERR) ] ; then mkdir $(EXEC_ERR); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME)Atis.sh $(EXEC_DIR)
	
	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME)Cso.sh $(EXEC_DIR)

	# ATIS #
	# copia .ctl
	cp -pf $(COMPILE_DIR)/$(APPNAME)Atis.ctl $(EXEC_DIR)
		
	# copia .txt
	cp -pf $(COMPILE_DAT)/$(APPNAME)Atis.txt $(EXEC_DAT)
	
	# CSO #
	# copia .ctl
	cp -pf $(COMPILE_DIR)/$(APPNAME)Cso.ctl $(EXEC_DIR)
		
	# copia .txt
	cp -pf $(COMPILE_DAT)/$(APPNAME)Cso.txt $(EXEC_DAT)
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0744 $(EXEC_DIR)/$(APPNAME)Atis.sh
	chmod 0744 $(EXEC_DIR)/$(APPNAME)Cso.sh
	chmod 0664 $(EXEC_CFG)$(APPNAME).cfg
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

uninstall:
	rm -r -f $(EXEC_DIR)

