# file is executable
# ./README

rm -rf target
mkdir target
# компилируем файлы все java файлы и перенаправляем в .class в новую папку target
javac src/java/edu/school21/printer/*/*.java -d target
# запускаем, указывая папку с файлами . class (-classpath target) и указывая путь до класса Main
java -cp target edu/school21/printer/app/Program . 0 it.bmp
