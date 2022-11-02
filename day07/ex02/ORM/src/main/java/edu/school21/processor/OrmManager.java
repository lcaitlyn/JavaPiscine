package edu.school21.processor;

import com.google.auto.service.AutoService;
import com.sun.org.apache.xpath.internal.operations.Or;
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
import java.sql.*;
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
    final String INT_IDENTITY_PRIMARY_KEY = "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY";
    final String LONG_IDENTITY_PRIMARY_KEY = "id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY";
    final String VARCHAR_LENGTH_FIELD_QUERY_FORM = "%s VARCHAR(%d)";
    final String VARCHAR_NO_LENGTH_FIELD_QUERY_FORM = "%s VARCHAR(256)";
    final String INT_FIELD_QUERY_FORM = "%s INT";
    final String LONG_FIELD_QUERY_FORM = "%s BIGINT";
    final String FLOAT_FIELD_QUERY_FORM = "%s REAL";
    final String DOUBLE_FIELD_QUERY_FORM = "%s DOUBLE PRECISION";
    final String BOOLEAN_FIELD_QUERY_FORM = "%s BOOLEAN";
    final String CLOSE_TABLE_QUERY = ");\n\n";

    final String SAVE_QUERY = "insert into ? (?) values (?);";
    final String UPDATE_QUERY = "update ? set ? where id = ?";
    final String FIND_BY_ID_QUERY = "select * from ? where id = ?";

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

    private String getEntityFieldType(Field type) {
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

    private String getColumnQueryFromElement(Element element, Field field) {
        OrmColumnId ormColumnId = element.getAnnotation(OrmColumnId.class);
        OrmColumn ormColumn = element.getAnnotation(OrmColumn.class);

        if (ormColumnId != null) {
            if (field.getType().getSimpleName().toLowerCase().equals("long"))
                return LONG_IDENTITY_PRIMARY_KEY;
            else
                return INT_IDENTITY_PRIMARY_KEY;
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
        try (Statement statement = dataSource.getConnection().createStatement()) {
            String typesBuilder = "";
            String valuesBuilder = "";

            for (Field f : entity.getClass().getDeclaredFields()) {
                if (f.getAnnotation(OrmColumn.class) != null) {

                    typesBuilder += f.getAnnotation(OrmColumn.class).name() + ", ";

                    f.setAccessible(true);
                    if (f.getType().getSimpleName().equals("String")) {
                        valuesBuilder += "'" + f.get(entity).toString() + "'";
                    } else {
                        valuesBuilder += f.get(entity).toString();
                    }
                    valuesBuilder += ", ";
                    f.setAccessible(false);
                }
            }
            typesBuilder = typesBuilder.substring(0, typesBuilder.length() - 2);
            valuesBuilder = valuesBuilder.substring(0, valuesBuilder.length() - 2);

            String query = "INSERT INTO " + entity.getClass().getAnnotation(OrmEntity.class).table()
                    + " (" + typesBuilder + ") VALUES (" + valuesBuilder + ") RETURNING id";

            System.out.println(query);
            statement.execute(query);

            // заменяю id нашего объекта на тот, что находится в базе данных
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                Field field = entity.getClass().getDeclaredField("id");
                field.setAccessible(true);
                field.set(entity, resultSet.getObject("id"));
                field.setAccessible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update(Object entity) {
        try (Statement statement = dataSource.getConnection().createStatement()) {
            StringBuilder sb = new StringBuilder();
            String id = "";

            for (Field f : entity.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.getAnnotation(OrmColumn.class) != null) {
                    sb.append(f.getAnnotation(OrmColumn.class).name());
                    sb.append(" = ");
                    if (f.getType().getSimpleName().equals("String")) {
                        sb.append("'" + f.get(entity).toString() + "'");
                    } else {
                        sb.append(f.get(entity).toString());
                    }
                    sb.append(", ");

                } else if (f.getAnnotation(OrmColumnId.class) != null) {
                    id = f.get(entity).toString();
                }
                f.setAccessible(false);
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);

            String query = "UPDATE " + entity.getClass().getAnnotation(OrmEntity.class).table()
                    + " SET " + sb + " WHERE id = " + id;

            System.out.println(query);
            statement.execute(query);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            String query =
                    "SELECT * FROM " + aClass.getAnnotation(OrmEntity.class).table()
                    + " WHERE id = " + id;

            System.out.println(query);
            statement.execute(query);
            ResultSet resultSet = statement.getResultSet();

            if (!resultSet.next())
                throw new SQLException("Error! User (" + id + ") Not Found!");

            // создаем новый класс и накачиваем его данными
            T result = aClass.newInstance();
            for (Field f : result.getClass().getDeclaredFields()) {
                f.setAccessible(true);

                if (f.getAnnotation(OrmColumnId.class) != null) {
                    f.set(result, resultSet.getObject(f.getName()));
                } else if (f.getAnnotation(OrmColumn.class) != null) {
                    OrmColumn ormColumn = f.getAnnotation(OrmColumn.class);
                    f.set(result, resultSet.getObject(ormColumn.name()));
                }

                f.setAccessible(false);
            }
            connection.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
