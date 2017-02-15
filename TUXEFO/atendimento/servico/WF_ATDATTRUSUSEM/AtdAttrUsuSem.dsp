# Microsoft Developer Studio Project File - Name="AtdAttrUsuSem" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=AtdAttrUsuSem - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "AtdAttrUsuSem.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "AtdAttrUsuSem.mak" CFG="AtdAttrUsuSem - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "AtdAttrUsuSem - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "AtdAttrUsuSem - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "AtdAttrUsuSem - Win32 Release"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 0
# PROP BASE Output_Dir "Release"
# PROP BASE Intermediate_Dir "Release"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 0
# PROP Output_Dir "Release"
# PROP Intermediate_Dir "Release"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD CPP /nologo /W4 /GX /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "AtdAttrUsuSem - Win32 Debug"

# PROP BASE Use_MFC 0
# PROP BASE Use_Debug_Libraries 1
# PROP BASE Output_Dir "Debug"
# PROP BASE Intermediate_Dir "Debug"
# PROP BASE Target_Dir ""
# PROP Use_MFC 0
# PROP Use_Debug_Libraries 1
# PROP Output_Dir "Debug"
# PROP Intermediate_Dir "Debug"
# PROP Target_Dir ""
# ADD BASE CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /GZ /c
# ADD CPP /nologo /Zp1 /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /FD /GZ /c
# SUBTRACT CPP /Fr /YX
# ADD BASE RSC /l 0x416 /d "_DEBUG"
# ADD RSC /l 0x416 /d "_DEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /debug /machine:I386 /pdbtype:sept

!ENDIF 

# Begin Target

# Name "AtdAttrUsuSem - Win32 Release"
# Name "AtdAttrUsuSem - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\..\commons\Collection\src\Collection.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProc.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\AgrupamentoEstadoTpProc\src\cWFAgrupamentoEstadoTpProcPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamento.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivo.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoMotivo\src\cWFAndamentoMotivoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoObservacao\src\cWFAndamentoObservacao.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoObservacao\src\cWFAndamentoObservacaoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Andamento\src\cWFAndamentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPR.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoProcessoFODPR\src\cWFAndamentoProcessoFODPRPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoTransferencia\src\cWFAndamentoTransferencia.cpp
# End Source File
# Begin Source File

SOURCE=..\AndamentoTransferencia\src\cWFAndamentoTransferenciaPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtdAttrUsuSem.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtdAttrUsuSemPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdTratamentoCri\src\cWFAtdTratamentoCri.cpp
# End Source File
# Begin Source File

SOURCE=..\AtdTratamentoCri\src\cWFAtdTratamentoCriPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoAndamentoAtual\src\cWFAtendimentoAndamentoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoCPFila\src\cWFAtendimentoCPFila.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoCPFila\src\cWFAtendimentoCPFilaPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGerarXMLDPR\src\cWFAtendimentoGerarXMLDPR.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoGrupoAtual\src\cWFAtendimentoGrupoAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisas.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoPesquisasPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Atendimento\src\cWFAtendimentoSkillPC.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtual.cpp
# End Source File
# Begin Source File

SOURCE=..\AtendimentoUsuarioAtual\src\cWFAtendimentoUsuarioAtualPC.cpp
# End Source File
# Begin Source File

SOURCE=..\Motivo\src\cWFMotivo.cpp
# End Source File
# Begin Source File

SOURCE=..\Motivo\src\cWFMotivoPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Usuario\src\cWFUsuario.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\workflow\servico\Usuario\src\cWFUsuarioPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcWFAtdAttrUsuSem.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\cWFAtdAttrUsuSem.h
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\msgPadrao.h
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\SmallString.h
# End Source File
# Begin Source File

SOURCE=.\include\stWFAtdAttrUsuSem.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# Begin Source File

SOURCE=.\xml\XMLIn.xml
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\cWFAtdAttrUsuSemPC.pcpp

!IF  "$(CFG)" == "AtdAttrUsuSem - Win32 Release"

!ELSEIF  "$(CFG)" == "AtdAttrUsuSem - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtdAttrUsuSemPC.pcpp

"cWFAtdAttrUsuSemPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc        parse=none        cpp_suffix=cpp        code=cpp        $(InputPath)        include=D:\Projeto\FrontOffice\TUXEFO-CRI-SKILL-03\TUXEFO\atendimento\servico\WF_ATDATTRUSUSEM\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
