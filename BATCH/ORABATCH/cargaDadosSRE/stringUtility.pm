#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/stringUtility.pm 43.2 07/06/18 16:54:07
# (c) 2007, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   stringUtility.pm  -  03/17/2004 by aldebaran perseke
#
#   string utility functions
################################################################################

package stringUtility;

use     strict;

#   package attributes

my  %connective;

################################################################################
#   strip blanks from begin of a string
################################################################################

sub ltrim {
    my  $string = $_[ 0 ];

    if( $string =~ /^\s*(\S+.*)$/ ) {
        $1;
    } else {
        $string;
    }
}

################################################################################
#   strip blanks from end of a string
################################################################################

sub rtrim {
    my  $string = $_[ 0 ];

    if( $string =~ /^(.*\S+)\s*$/ ) {
        $1;
    } else {
        $string;
    }
}

################################################################################
#   strip blanks from begin and end of a string
################################################################################

sub trim {
    my  $string = $_[ 0 ];

    if( $string =~ /^\s+$/ ) {
        '';
    } else {
        &rtrim( &ltrim( $string ) );
    }
}

################################################################################
#   strip accent characters
################################################################################

sub stripAccents {
    my  $string = $_[ 0 ];

    $string =~ s/[·‡„‚¡¿√¬]/A/g;
    $string =~ s/[ÈÍ… ]/E/g;
    $string =~ s/[ÌÕ]/I/g;
    $string =~ s/[ÛıÙ”’‘]/O/g;
    $string =~ s/[˙¸⁄‹]/U/g;
    $string =~ s/[Á«]/C/g;
    $string =~ s/[∫\&]//g;

    $string;
}

################################################################################
#   load up an abbreviations flat file
################################################################################

sub loadAbbreviationHash {
    my  $inputFileName = $_[ 0 ];

    my  $FROM;
    my  $TO;
    my  %container;

    open ABBREVIATIONS, "< $inputFileName"
            or die qq/could open "$inputFileName" connectives flat file\n/;

    while( <ABBREVIATIONS> ) {
        chop;

        #ignore comment lines
        if ( '#' eq substr($_, 0, 1) ) {
            next;
        }

        ( $FROM, $TO ) = split /\|/;

        $container{ $FROM } = $TO;
    }

    close ABBREVIATIONS;

    %container;
}

################################################################################
#   abbreviate a string
################################################################################

sub abbreviate {
    my  $string = $_[ 0 ];
    my  $abbreviationHash = $_[ 1 ];

    if( ! defined $string ) {
        return 0;
    }

#   strip accent characters

    $string = &stripAccents( $string );

#   abbreviate the first word

    my  $word;
    my  $abbreviation = '';

    for( ;; ) {
        if( '' eq $string ) {
            last;
        }

#   extract a word from the string

        if( $string =~ /^[:\s\.]*([^:^\s^\.]+)[:\s\.](.*)$/ ) {
            $word = $1;
            $string = $2;
        } elsif( $string =~ /^\s*(\S+)$/ ) {
            if( '' eq $abbreviation ) {
                $abbreviation = $1;
            } else {
                $abbreviation .= " $1";
            }

            last;
        } else {
            if( '' eq $abbreviation ) {
                $abbreviation = $string;
            } else {
                $abbreviation .= " $string";
            }

            last;
        }

#   try to abbreviate the first word

        if( '' eq $abbreviation ) {
            if( exists $abbreviationHash->{ $word } ) {
                $word = $abbreviationHash->{ $word };
            }

            $abbreviation = $word;
        } else {
            if( '' ne $word ) {
                $abbreviation .= " $word";
            }
        }
    }

    $abbreviation;
}

################################################################################
#   load up the connectives flat file
################################################################################

sub loadConnectives {
    my  $inputFileName = $_[ 0 ];

    my  $CONNECTIVE;
    my  $DESCRIPTION;

    open CONNECTIVES, "< $inputFileName"
            or die qq/could open "$inputFileName" connectives flat file\n/;

    while( <CONNECTIVES> ) {
        chop;

        #ignore comment lines
        if ( '#' eq substr($_, 0, 1) ) {
            next;
        }

        ( $CONNECTIVE, $DESCRIPTION ) = split /\|/;

        $connective{ $CONNECTIVE } = $DESCRIPTION;
    }

    close CONNECTIVES;
}

################################################################################
#   almost equality strings comparision
################################################################################

sub almostEquals {
    my  $string1 = $_[ 0 ];
    my  $string2 = $_[ 1 ];

    if( ! defined $string1 ) {
        return 0;
    }

    if( ! defined $string2 ) {
        return 0;
    }

    my  $character1;
    my  $character2;
    my  $l1 = length( $string1 );
    my  $l2 = length( $string2 );
    my  $i = 0;
    my  $j = 0;
    my  $differences = 0;
    my  $maxErrorsPercentage = 20.0;

    for( ;; ) {
        if( $l1 == $i
                && $l2 == $j ) {
            last;
        }

        if( $l1 == $i
                || $l2 == $j ) {
            $differences += ( $l1 - $i ) + ( $l2 - $j );
            last;
        }

#   extract the ith character from string 1 and the jth from string 2

        $character1 = lc substr( $string1, $i, 1 );
        $character2 = lc substr( $string2, $j, 1 );

#   check if both characters are equal

        if( $character1 eq $character2 ) {
            $i++;
            $j++;
            next;
        }

#   if one string is bigger than the other, check the lack of current character

        if( $l1 > $l2
                && $i + 1 < $l1
                && lc substr( $string1, $i + 1, 1 ) eq $character2 ) {
            $i++;
            $differences++;
            next;
        }

        if( $l2 > $l1
                && $j + 1 < $l2
                && lc substr( $string2, $j + 1, 1 ) eq $character1 ) {
            $j++;
            $differences++;
            next;
        }

        $i++;
        $j++;
        $differences++;
    }

    if( 0 == $differences ) {
        1;
    } else {
        if( $l1 <= $l2
                && int( $l2 * $maxErrorsPercentage / 100.0 ) >= $differences ) {
            1;
        } elsif( $l1 > $l2
                && int( $l1 * $maxErrorsPercentage / 100.0 ) >= $differences ) {
            1;
        } else {
            0;
        }
    }
}

################################################################################
#   strings comparision by many approximation algorithms
################################################################################

sub stringCompare {
    my  $string1 = $_[ 0 ];
    my  $string2 = $_[ 1 ];

    if( ! defined $string1 ) {
        return 0;
    }

    if( ! defined $string2 ) {
        return 0;
    }

#   strip accent characters

    $string1 = &stripAccents( $string1 );
    $string2 = &stripAccents( $string2 );

#   first, try a simple and an almost equals comparision

    if( $string1 eq $string2 ) {
        return 1;
    }

    if( 1 == &almostEquals( $string1, $string2 ) ) {
        return 1;
    }

#   start comparing by words

    my  $previousString1;
    my  $previousString2;

    my  $word1;
    my  $word2;

    for( ;; ) {
        if( '' eq $string1 ) {
            last;
        }

        if( '' eq $string2 ) {
            last;
        }

#   extract the first word from string 1

        $previousString1 = $string1;

        if( $string1 =~ /^[\-:\s\/\.]*([^-^:^\s^\/^\.]+)[\-:\s\/\.](.*)$/ ) {
            $word1 = $1;
            $string1 = $2;
        } elsif( $string1 =~ /^\s*(\S+)$/ ) {
            $word1 = $1;
            $string1 = '';
        } else {
            $word1 = $string1;
            $string1 = '';
        }

#   extract the first word from string 2

        $previousString2 = $string2;

        if( $string2 =~ /^[\-:\s\/\.]*([^-^:^\s^\/^\.]+)[\-:\s\/\.](.*)$/ ) {
            $word2 = $1;
            $string2 = $2;
        } elsif( $string2 =~ /^\s*(\S+)$/ ) {
            $word2 = $1;
            $string2 = '';
        } else {
            $word2 = $string2;
            $string2 = '';
        }

#   check if both words are equal

        if( $word1 eq $word2 ) {
            next;
        }

#   check if one is a substring of the other

        if( length( $word1 ) >= length( $word2 )
                && substr( $word1, 0, length( $word2 ) ) eq $word2 ) {
            next;
        }

        if( length( $word2 ) >= length( $word1 )
                && substr( $word2, 0, length( $word1 ) ) eq $word1 ) {
            next;
        }

#   check if one is a abbreviation of the other

        if( substr( $word1, 0, 1 ) eq substr( $word2, 0, 1 ) ) {
            my  $word;
            my  $abbreviation;
            my  $i = 0;
            my  $j = 0;

            if( length( $word1 ) > length( $word2 ) ) {
                $word = $word1;
                $abbreviation = $word2;
            } else {
                $word = $word2;
                $abbreviation = $word1;
            }

            while( $i < length( $word )
                    && $j < length( $abbreviation ) ) {
                if( substr( $word, $i, 1 ) eq substr( $abbreviation, $j, 1 ) ) {
                    $j++;
                }
                $i++;
            }

            if( $j >= length( $abbreviation ) ) {
                next;
            }
        }

#   ignore some connectives

        if( exists $connective{ $word1 } ) {
            $string2 = $previousString2;
            next;
        }

        if( exists $connective{ $word2 } ) {
            $string1 = $previousString1;
            next;
        }

#   check if there is an option, separated by a slash

        if( $previousString1 =~ /^[^\/]+\/(.+)$/ ) {
            $string1 = $1;
            $string2 = $previousString2;
            next;
        }

        if( $previousString2 =~ /^[^\/]+\/(.+)$/ ) {
            $string2 = $1;
            $string1 = $previousString1;
            next;
        }

#   check if the words are almost equals

        if( 1 == &almostEquals( $word1, $word2 ) ) {
            next;
        }

        return 0;
    }

    1;
}

#   return 1 to module requiring it

1;

