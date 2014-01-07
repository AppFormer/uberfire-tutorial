#!/bin/sh

# Check preconditions: working copy must be clean!

if ! git diff --exit-code; then
    echo "Aborting due to uncommitted changes in working copy"
    exit 1
fi

if ! git diff --cached --exit-code; then
    echo "Aborting due to uncommitted changes in index";
    exit 1
fi

if ! git ls-files --other --exclude-standard --directory; then
    echo "Aborting due to untracked, unignored files in working copy"
    exit 1
fi


# Test each revision

for n in 1 2 3 4 5; do
    rev=`git log --oneline master | grep "Checkpoint $n -" | cut -f 1 -d ' '`
    if [ -z "$rev" ]; then
	echo "Couldn't find Checkpoint $n";
	exit 1
    fi
    git checkout $rev
    if ! mvn clean verify; then
	echo "Test failed for checkpoint $n ($rev)"
	exit 1
    fi
done

# get back to master

git checkout master
