# file is executable
# ./README




rm -rf target
mkdir target

# Компилируем все .java файлы и перенаправляем созданные .class в новую папку target
javac src/java/edu/school21/printer/*/*.java -d target

# Запускаем, указывая папку с файлами .class (-classpath target) и путь до класса Main
java -cp target edu/school21/printer/app/Program . 0 it.bmp
