# file is executable
# ./README





rm -rf target
mkdir target

# Компилируем все .java файлы и перенаправляем созданные .class в новую папку target
javac src/java/edu/school21/printer/*/*.java -d target

cp -rf src/resources target

# Создаем новый .jar архив, в который войдут все скомпилированные файлы .class, а так же указываем файл manifest.txt, в котором находится путь к Main классу
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# Запускаем, указывая наш новый .jar файл
java -jar target/images-to-chars-printer.jar . 0
