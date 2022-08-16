# file is executable
# ./README

rm -rf target
mkdir target
# компилируем файлы все java файлы и перенаправляем в .class в новую папку target
javac src/java/edu/school21/printer/*/*.java -d target
cp -rf src/resources target
# создаем новый .jar файл, в который войдут все скомпилированные файлы .class, а так е указываем файл manifest.txt,
# в котором находится путь к Main классу
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .
# запускаем, указывая наш новый .jar файл
java -jar target/images-to-chars-printer.jar . 0
