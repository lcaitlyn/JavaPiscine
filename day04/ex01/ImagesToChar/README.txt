# file is executable
# ./README

rm -rf target
mkdir target
javac src/java/edu/school21/printer/*/*.java -d target
cp -rf src/resources target
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar . 0
