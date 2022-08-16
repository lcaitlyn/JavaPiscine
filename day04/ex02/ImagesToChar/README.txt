# file is executable
# ./README

rm -rf target
mkdir target
cp -r src/resources target
cd target
jar xf ../lib/JColor/JColor-5.5.1.jar
jar xf ../lib/JCommander/jcommander-1.78.jar
cd ..
javac -cp target src/java/edu/school21/printer/*/*.java -d target
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
