# Microsoft Developer Studio Project File - Name="Atendimento" - Package Owner=<4>
# Microsoft Developer Studio Generated Build File, Format Version 6.00
# ** DO NOT EDIT **

# TARGTYPE "Win32 (x86) Console Application" 0x0103

CFG=Atendimento - Win32 Debug
!MESSAGE This is not a valid makefile. To build this project using NMAKE,
!MESSAGE use the Export Makefile command and run
!MESSAGE 
!MESSAGE NMAKE /f "Atendimento.mak".
!MESSAGE 
!MESSAGE You can specify a configuration when running NMAKE
!MESSAGE by defining the macro CFG on the command line. For example:
!MESSAGE 
!MESSAGE NMAKE /f "Atendimento.mak" CFG="Atendimento - Win32 Debug"
!MESSAGE 
!MESSAGE Possible choices for configuration are:
!MESSAGE 
!MESSAGE "Atendimento - Win32 Release" (based on "Win32 (x86) Console Application")
!MESSAGE "Atendimento - Win32 Debug" (based on "Win32 (x86) Console Application")
!MESSAGE 

# Begin Project
# PROP AllowPerConfigDependencies 0
# PROP Scc_ProjName ""
# PROP Scc_LocalPath ""
CPP=cl.exe
RSC=rc.exe

!IF  "$(CFG)" == "Atendimento - Win32 Release"

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
# ADD CPP /nologo /W3 /GX /O2 /D "WIN32" /D "NDEBUG" /D "_CONSOLE" /D "_MBCS" /YX /FD /c
# ADD BASE RSC /l 0x416 /d "NDEBUG"
# ADD RSC /l 0x416 /d "NDEBUG"
BSC32=bscmake.exe
# ADD BASE BSC32 /nologo
# ADD BSC32 /nologo
LINK32=link.exe
# ADD BASE LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386
# ADD LINK32 kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib kernel32.lib user32.lib gdi32.lib winspool.lib comdlg32.lib advapi32.lib shell32.lib ole32.lib oleaut32.lib uuid.lib odbc32.lib odbccp32.lib /nologo /subsystem:console /machine:I386

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

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
# ADD CPP /nologo /W3 /Gm /GX /ZI /Od /D "WIN32" /D "_DEBUG" /D "_CONSOLE" /D "_MBCS" /Fr /FD /GZ /c
# SUBTRACT CPP /YX
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

# Name "Atendimento - Win32 Release"
# Name "Atendimento - Win32 Debug"
# Begin Group "Source Files"

# PROP Default_Filter "cpp;c;cxx;rc;def;r;odl;idl;hpj;bat"
# Begin Source File

SOURCE=..\..\..\commons\Collection\src\Collection.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimento.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaBKO.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaCRI.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaCRIPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaRC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaRCPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoInBoxBKO.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoInBoxBKOPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoPesquisas.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoPesquisasPC.cpp
# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoSkillPC.cpp
# End Source File
# Begin Source File

SOURCE=..\..\..\..\TuxCli\RouterClient.cpp
# End Source File
# Begin Source File

SOURCE=.\src\svcWFAtendimento.cpp
# End Source File
# End Group
# Begin Group "Header Files"

# PROP Default_Filter "h;hpp;hxx;hm;inl"
# Begin Source File

SOURCE=.\include\cWFAtendimento.h
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\definesAtendimento.h
# End Source File
# Begin Source File

SOURCE=..\..\..\commons\msgPadrao.h
# End Source File
# Begin Source File

SOURCE=.\include\queryMacroFila.h
# End Source File
# Begin Source File

SOURCE=.\include\stWFAtendimento.h
# End Source File
# Begin Source File

SOURCE=.\include\stWFAtendimentoFila.h
# End Source File
# End Group
# Begin Group "Resource Files"

# PROP Default_Filter "ico;cur;bmp;dlg;rc2;rct;bin;rgs;gif;jpg;jpeg;jpe"
# Begin Source File

SOURCE=.\makefile
# End Source File
# End Group
# Begin Group "ProC"

# PROP Default_Filter "*.pcpp"
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaBKOPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoFilaBKOPC.pcpp

"cWFAtendimentoFilaBKOPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaCRIPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoFilaCRIPC.pcpp

"cWFAtendimentoFilaCRIPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoFilaRCPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoFilaRCPC.pcpp

"cWFAtendimentoFilaRCPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoInBoxBKOPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoInBoxBKOPC.pcpp

"cWFAtendimentoInBoxBKOPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoPC.pcpp

"cWFAtendimentoPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoPesquisasPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoPesquisasPC.pcpp

"cWFAtendimentoPesquisasPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# Begin Source File

SOURCE=.\src\cWFAtendimentoSkillPC.pcpp

!IF  "$(CFG)" == "Atendimento - Win32 Release"

!ELSEIF  "$(CFG)" == "Atendimento - Win32 Debug"

# Begin Custom Build
InputPath=.\src\cWFAtendimentoSkillPC.pcpp

"cWFAtendimentoSkillPC.cpp" : $(SOURCE) "$(INTDIR)" "$(OUTDIR)"
	proc  parse=none  cpp_suffix=cpp  code=cpp $(InputPath)  include=.\include

# End Custom Build

!ENDIF 

# End Source File
# End Group
# End Target
# End Project
