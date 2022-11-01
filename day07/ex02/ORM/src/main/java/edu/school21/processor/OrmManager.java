package edu.school21.processor;

import com.google.auto.service.AutoService;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.annotation.OrmColumn;
import edu.school21.annotation.OrmColumnId;
import edu.school21.annotation.OrmEntity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


@AutoService(Process.class)
@SupportedAnnotationTypes("edu.school21.annotation.OrmEntity")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class OrmManager extends AbstractProcessor {
    final String DROP_TABLE_QUERY_FORM = "DROP TABLE IF EXISTS %s;\n\n";
    final String CREATE_TABLE_QUERY_FORM = "CREATE TABLE %s (\n";
    final String IDENTITY_PRIMARY_KEY = "id SERIAL PRIMARY KEY";
    final String VARCHAR_LENGTH_FIELD_QUERY_FORM = "%s VARCHAR(%d)";
    final String VARCHAR_NO_LENGTH_FIELD_QUERY_FORM = "%s VARCHAR(256)";
    final String INT_FIELD_QUERY_FORM = "%s INT";
    final String LONG_FIELD_QUERY_FORM = "%s BIGINT";
    final String FLOAT_FIELD_QUERY_FORM = "%s REAL";
    final String DOUBLE_FIELD_QUERY_FORM = "%s DOUBLE PRECISION";
    final String BOOLEAN_FIELD_QUERY_FORM = "%s BOOLEAN";
    final String CLOSE_TABLE_QUERY = ");\n\n";

    final String SAVE_QUERY = "insert into ? "

    DataSource dataSource;
    public OrmManager() {}

    public OrmManager(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            File file = new File("./target/schema.sql");
            if (!file.exists()) {
                System.err.println("scheme.sql not found! Rebuild project (mvn clean compile)");
                System.exit(-1);
            }

            StringBuilder sb = new StringBuilder();

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

            dataSource.getConnection().createStatement().execute(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(OrmEntity.class);

        // создаем экземпляры таблиц
        for (Element e : elements) {
            createTable(e);
        }

        return false;
    }

    public String getEntityFieldType(Field type) {
        switch (type.getType().getSimpleName().toLowerCase()) {
            case "string":
                return VARCHAR_NO_LENGTH_FIELD_QUERY_FORM;
            case "int":
            case "integer":
                return INT_FIELD_QUERY_FORM;
            case "long":
                return LONG_FIELD_QUERY_FORM;
            case "boolean":
                return BOOLEAN_FIELD_QUERY_FORM;
            case "double":
                return DOUBLE_FIELD_QUERY_FORM;
            case "float":
                return FLOAT_FIELD_QUERY_FORM;
        }
        return null;
    }

    public String getColumnQueryFromElement(Element element, Field field) {
        OrmColumnId ormColumnId = element.getAnnotation(OrmColumnId.class);
        OrmColumn ormColumn = element.getAnnotation(OrmColumn.class);

        if (ormColumnId != null) {
            return IDENTITY_PRIMARY_KEY;
        }

        if (ormColumn != null) {
            if (ormColumn.length() == 0) {
                return String.format(getEntityFieldType(field), ormColumn.name());
            } else {
                return String.format(VARCHAR_LENGTH_FIELD_QUERY_FORM, ormColumn.name(), ormColumn.length());
            }
        }
        return null;
    }

    private void createTable(Element element) {

        try (FileWriter fileWriter = new FileWriter("./target/schema.sql", true)) {

            OrmEntity ormEntity = element.getAnnotation(OrmEntity.class);

            fileWriter.write(String.format(DROP_TABLE_QUERY_FORM, ormEntity.table()));
            System.out.printf((DROP_TABLE_QUERY_FORM), ormEntity.table());
            fileWriter.write(String.format(CREATE_TABLE_QUERY_FORM, ormEntity.table()));
            System.out.printf((CREATE_TABLE_QUERY_FORM), ormEntity.table());

            Field [] fields = Class.forName(element.toString()).getDeclaredFields();

            List<Element> list = new ArrayList<>();

            // Вытаскиываем только поля. В элементах хранятятся: поля и все методы
            for (Element e : element.getEnclosedElements()) {
                if (!e.toString().contains(("("))) {
                    list.add(e);
                }
            }

            for (int i = 0; i < list.size() - 1; i++) {
                System.out.print("\t" + getColumnQueryFromElement(list.get(i), fields[i]) + ",\n");
                fileWriter.write("\t" + getColumnQueryFromElement(list.get(i), fields[i]) + ",\n");
            }
            System.out.print("\t" + getColumnQueryFromElement(list.get(list.size() - 1), fields[list.size() - 1]) + "\n");
            fileWriter.write("\t" + getColumnQueryFromElement(list.get(list.size() - 1), fields[list.size() - 1]) + "\n");
            System.out.print(CLOSE_TABLE_QUERY);
            fileWriter.write(CLOSE_TABLE_QUERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Object entity) {
        try (dataSource.getConnection().prepareStatement()) {

        }
    }
    public void update(Object entity) {

    }

    public <T> T findById(Long id, Class<T> aClass) {
        return null;
    }
}
