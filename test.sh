cd game-engine/src


# compile all tests

# number of files
n=$(($(ls -l tests| grep -v ^l | wc -l)-1))

testN=0

pwd

for i in $(ls tests)
do	
	# conting tests
    c=$(grep -c "@Test" ./tests/$i)
    testN=$(($testN+$c))

    # compiling test
    javac -cp lib/*:. tests/$i
done

echo $testN
# run tests
java -cp lib/*:. org.junit.platform.console.ConsoleLauncher -p tests



# remove all compiled .class files
find . -name "*.class" -type f|xargs rm -f
