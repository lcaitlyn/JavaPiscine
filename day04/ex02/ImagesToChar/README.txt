# file is executable
# ./README

# JColor version 5.5.1
# full info: https://github.com/dialex/JColor
# download link: https://repo1.maven.org/maven2/com/diogonunes/JColor/5.5.1/JColor-5.5.1.jar

# JCommander version 1.78
# full info: https://jcommander.org/
# download link: https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar




rm -rf target
mkdir target
cp -r src/resources target
cd target

# Разархивируем библиотеки в target
jar xf ../lib/JColor/JColor-5.5.1.jar
jar xf ../lib/JCommander/jcommander-1.78.jar

cd ..

# Компилируем файлы все .java файлы, указывая в флаг -classpath папку target, которая содержит разархивированные библиотеки, и перенаправляем .class в папку target
javac -cp target src/java/edu/school21/printer/*/*.java -d target

# Собираем новый .jar архив, который состоит из библиотек, наших скомпилированных .class файлов и manifest.txt, в котором находится путь к Main классу
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# Запускаем, используя наш новый .jar архив
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
