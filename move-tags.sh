git log --oneline | grep Checkpoint | awk '{print "git tag -f checkpoint-" $3 " " $1;}' > script.tmp
cat script.tmp
echo "If the above looks right, execute it with:"
echo "  sh script.tmp"
