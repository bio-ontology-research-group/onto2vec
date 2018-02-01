#!/usr/bin/perl 
#use strict; 
#Define all Needed Variables
my $path = `pwd`;
chomp ($path);
my $vecfile ="vec200res.lst";
open (FILE1, '>>', "temporayfile2.lst");
#open (FH,"$vecfile");

open (FILE, "temporayfile.lst");
while (my $line2 =<FILE>)
{
	print ("$line2");	
	$line2 =~tr/][//d;
	print (FILE1 "$line2");
	
}
