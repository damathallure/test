#!/bin/bash

###############################################################################################################
#                                                                                                              #
# Author: Francisco Javier Mena Barraza                                                                        #
# version: 1.0                                                                                                 #
# Description: Script used to send multiple ping commands for all nodes in CPS SITE                             #
# comand must be executed in the follow way: ./send_multiple_ping.sh <pings> <active> <standby> <getFiles>      #
# pings : [Integer] Number of pings to be send                                                                 #
# active : [String] Name of active site: TLC, MTY, GDL, MGC                                                    #
# standby : [String] Name of pasive site: TLC, MTY, GDL, MGC                                                   #
# getFiles : [Integer] sET 666 to copy hosts files from active and stand by servers, let null if not required #
#                                                                                                              #
###############################################################################################################

# Global variables
localPath="/var/tmp/TOL_dmth"

declare -A SERVERS
SERVERS[TLC]="10.150.248.89"
SERVERS[MTY]="10.150.246.89"
SERVERS[GDL]="10.36.232.89"
SERVERS[MGC]="10.32.215.89"

if [ $4 == "666" ];
then
        echo "Getting host files to buid ping command"
        sleep 3
        scp "${SERVERS[$2]}:/etc/hosts" "$localPath/00_support/hostActive.txt"
        scp "${SERVERS[$3]}:/etc/hosts" "$localPath/00_support/hostPasive.txt"
fi

echo "######################################     Sending pings to active servers $2"
sleep 3
awk -v np=$1 '$2~/^qns/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostActive.txt" | bash
awk -v np=$1 '$2~/^sessionmgr/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostActive.txt" | bash
awk -v np=$1 '$2~/^lb/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostActive.txt" | bash
awk -v np=$1 '$2~/^pcrfclient/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostActive.txt" | bash

echo "######################################     Sending pings to standby servers $3"
sleep 3
awk -v np=$1 '$2~/^qns/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostPasive.txt" | bash
awk -v np=$1 '$2~/^sessionmgr/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostPasive.txt" | bash
awk -v np=$1 '$2~/^lb/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostPasive.txt" | bash
awk -v np=$1 '$2~/^pcrfclient/{print "echo " $2 "\nping -c " np " " $1 }' "$localPath/00_support/hostPasive.txt" | bash

echo "Process done!"
