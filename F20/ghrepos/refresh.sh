export GIT=`which git`
export PWD=`which pwd`
export LS=`which ls`
export CP=`which cp`
export CAT=`which cat`

export ROOT=`pwd`

for REPONAME in $(cat repos.txt) ; do
  REPOPATH=https://github.com/$REPONAME
  echo $REPOPATH
  if [ ! -d $REPONAME ]
  then
    echo "Making directory " $REPONAME
    mkdir $REPONAME
    echo "Cloning " $REPOPATH
    (cd $ROOT/$REPONAME && $GIT clone $REPOPATH)
  else
    echo "Pulling in " $ROOT/$DIR
    (cd $ROOT/$REPONAME && $GIT pull)
  fi
  cd $ROOT
done;
