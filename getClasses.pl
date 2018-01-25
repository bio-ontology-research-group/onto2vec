#!/usr/bin/perl
#use strict;
#Define all Needed Variables
use List::MoreUtils qw(uniq);
my $path = `pwd`;
chomp ($path);
my $inputfile= "$path/AllAxioms.lst";
my $outputfile= "$path/AllClasses.lst";
my @temparray=();
open (FILE, '>>', "$addoutfile");
open (FH,"$inputfile");
while (my $line =<FH>)
{
	chomp ($line);
	if ($line =~ /(\S+)\s+/)
		my $onto_class=$1;
		push @temparray $onto_class;

}
my @Finalarray= uniq @temparray;
foreach (@Finalarray)
{
 	my $final_class=$_;
	print FILE "$final_class\n";
}
