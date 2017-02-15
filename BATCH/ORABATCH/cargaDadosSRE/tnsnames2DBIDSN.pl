. $P2KHOME/scripts/use_perl
#!perl
#ident @(#) P2K: src/cacs/tools/loadDNE/tnsnames2DBIDSN.pl 61.2 08/11/09 05:25:03
# (c) 2007,2008, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   tnsnames2DBIDSN.pl  -  09/10/2004 by aldebaran perseke
#
#   Parse Oracle tnsnames file and genereate a perl DBI DSN
################################################################################

use strict;

#   global variables for the script

my  $ORACLE_HOME = $ENV{ 'ORACLE_HOME' };
my  $tnsnamesFileName = qq/$ORACLE_HOME\/network\/admin\/tnsnames.ora/;

################################################################################
#   parse Oracle tnsnames file
################################################################################

sub parseOracleTnsnamesFile {
    my  $tnsnamesFileName = shift;
	my  $ORACLE_SID = shift;

#   parse tnsnames file

    my  $fileContent = '';

    open TNSNAMES, qq/< $tnsnamesFileName/
            or die qq/could not open "$tnsnamesFileName" file\n/;

    while( <TNSNAMES> ) {
        chop;

        if( /^\#/ ) {
            next;
        }

        $fileContent .= $_;
    }

    close TNSNAMES;

#   standadize the tnsnames content

    my  $openParenthesis = 0;

    $_ = $fileContent;
    $fileContent = '';

    while( /^.+$/ ) {

#   remove spaces and tabs

        if( s/^[\s\t]*//o ) {
            if( $fileContent ne '' ) {
                $fileContent .= ' ';
            }
        }

#   add the number of open parenthesis

        if( /^(\()(.*)/o ) {
            $openParenthesis++;
            $fileContent .= qq/$1 /;
            $_ = $2;
        }

#   subtract the number of open parenthesis

        if( /^(\))(.*)/o ) {
            $openParenthesis--;
            if( 0 > $openParenthesis ) {
                die qq/error: file $tnsnamesFileName does not contain a valid list\n/;
            }
            $fileContent .= $1;
            $_ = $2;

#   extract the SIDs from the file content
            
            
            if( 0 == $openParenthesis ) {
                
                if ( $fileContent =~ /^[\s]\s*$ORACLE_SID|($ORACLE_SID\.world)\s*=\s*\((.+)\)\s*$/oi ) {
                
                    my  $description = $2;
                    my  $host = '';
                    my  $port = '';
                    my  $sid = '';
                    
                    
                    if( $description =~ /HOST\s*=\s*(\S+)/oi ) {
                        $host = lc $1;
                    }
                    if( $description =~ /port\s*=\s*(\d+)/oi ) {
                        $port = $1;
                    }
                    if( $description =~ /sid\s*=\s*(\S+)/oi ) {
                        $sid = lc $1;
                    }
                    
                    if( '' ne $host
                            && '' ne $port
                            && '' ne $sid ) {
                        return qq/dbi:Oracle:host=$host;port=$port;sid=$sid/;
                    }
                    $fileContent = '';
                }
            }
        }

#   get the arguments from a list

        if( /^([^\(^\)^\s^\t]+)([\(\)\s\t]*.*)/o ) {
            $fileContent .= $1;
            $_ = $2;
        }
    }
return '';
}

################################################################################
#   entry point
################################################################################

#   command line arguments parsing

my  $ORACLE_SID = '';

foreach my $argument( @ARGV ) {
    if( '--help' eq $argument ) {
        print STDOUT <<HELP_OUTPUT;
use: tnsnames2DBIDSN [options] ORACLE_SID
    --help - print this help message
HELP_OUTPUT
        exit 0;
    }

    if( $argument =~ /^-+/ ) {
        die qq/invalid argument $argument\n/;
    }

    $ORACLE_SID = $argument;
}

#   mandatory arguments

if( '' eq $ORACLE_SID ) {
    die qq/ORACLE_SID name is mandatory\n/;
}

if( ! -f $tnsnamesFileName ) {
    die qq/invalid tnsnames file name $tnsnamesFileName\n/;
}

#   output the DBI DSN corresponding to the SID

my $Connection = parseOracleTnsnamesFile( $tnsnamesFileName, $ORACLE_SID );

if( $Connection eq '' ) {
    die qq/invalid SID $ORACLE_SID\n/;
}
else {
    print STDOUT $Connection;
}

