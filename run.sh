
cd src
echo "=========== script start ==========="
echo "Compiling..."
javac -cp ../lib/guava-23.5-jre.jar ./Run.java
echo "Running..."

java -cp .:../lib/guava-23.5-jre.jar Run

echo "=========== script end ==========="