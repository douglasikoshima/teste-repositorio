
APPNAME=corrigePessoaDoc

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_CRTL=$(COMPILE_DIR)/ctrl

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/
EXEC_LOG=$(EXEC_DIR)/log
EXEC_ERR=$(EXEC_DIR)/error
EXEC_DAT=$(EXEC_DIR)/data
CRTL_DIR=$(EXEC_DIR)/ctrl

all: build

build: 
	@echo

clean:
	rm -f $(EXEC_DIR)/*.sh
	rm -f $(EXEC_DIR)/*.sql
	rm -f $(EXEC_DIR)/$(APPNAME).cfg
	rm -r -f $(CRTL_DIR)

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
	if [ ! -d $(CRTL_DIR) ] ; then mkdir $(CRTL_DIR); fi
		
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT) $(CRTL_DIR)

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/*.sh $(EXEC_DIR)
	# copia .ctl
	# cp -pf $(COMPILE_CRTL)/*.ctl $(CRTL_DIR)
	# copia .sql
	cp -pf $(COMPILE_DIR)/*.sql $(EXEC_DIR)
	
	# seta permissoes
	chmod 0755 $(EXEC_DIR)
	chmod 0755 $(CRTL_DIR)
	chmod 0777 $(EXEC_DIR)/*.sh
	chmod 0744 $(EXEC_DIR)/*.sql
	chmod 0777 $(EXEC_CFG)$(APPNAME).cfg
	chmod 0777 $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT)

uninstall:
	rm -r -f $(EXEC_DIR)

