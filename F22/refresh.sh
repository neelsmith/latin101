#!/bin/bash
export GIT=`which git`
export PWD=`which pwd`
export LS=`which ls`
export CP=`which cp`
export CAT=`which cat`

export ROOT=`pwd`/repos
echo "Root directory for repos is " $ROOT
export REPOLIST=repos.txt

for REPONAME in $(cat $REPOLIST) ; do
  REPOPATH=https://github.com/$REPONAME
  UNAME=${REPONAME%/*}
  echo $REPONAME
  if [ ! -d $ROOT/$REPONAME ]
  then
    echo "Making directory " $REPONAME
    mkdir -p $ROOT/$UNAME
    echo "Cloning " $REPOPATH
    (cd $ROOT/$UNAME && $GIT clone $REPOPATH)
  else
    echo "Pulling in " $ROOT/$REPONAME
    (cd $ROOT/$REPONAME && $GIT pull)
  fi
  cd $ROOT
done;
