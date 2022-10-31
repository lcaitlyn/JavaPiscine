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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;


@AutoService(Process.class)
@SupportedAnnotationTypes("edu.school21.annotation.OrmEntity")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class OrmManager extends AbstractProcessor {

    DataSource dataSource;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(OrmEntity.class);


        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");
        dataSource = hikariDataSource;



//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement()) {
//
//
//            statement.execute("CREATE TABLE IF NOT EXISTS TEST();");
//            dataSource.getConnection().createStatement().execute("CREATE TABLE IF NOT EXISTS TEST();"
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        try {
            dataSource.getConnection().createStatement().execute("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        for (Element e : elements) {
//            createTable(e);
//        }

        return false;
    }
    public void createTable(Element element) {
        final String DROP_TABLE_QUERY_FORM = "DROP TABLE IF EXISTS %s;\n\n";
        final String CREATE_TABLE_QUERY_FORM = "CREATE TABLE %s (\n";
//        final String IDENTITY_PRIMARY_KEY = "\tid INT IDENTITY PRIMARY KEY";
        final String IDENTITY_PRIMARY_KEY = "\tid SERIAL PRIMARY KEY";
        final String VARCHAR_FIELD_QUERY_FORM = ",\n\t%s VARCHAR(%d)";
        final String INT_FIELD_QUERY_FORM = ",\n\t%s INT";
        final String CLOSE_TABLE_QUERY = "\n);\n";


        try (FileWriter fileWriter = new FileWriter("./src/main/resources/schema.sql", true);
             ) {



            OrmEntity ormEntity = element.getAnnotation(OrmEntity.class);

            fileWriter.write(String.format(DROP_TABLE_QUERY_FORM, ormEntity.table()));
            System.out.printf((DROP_TABLE_QUERY_FORM), ormEntity.table());
            fileWriter.write(String.format(CREATE_TABLE_QUERY_FORM, ormEntity.table()));
            System.out.printf((CREATE_TABLE_QUERY_FORM), ormEntity.table());

            for (Element e : element.getEnclosedElements()) {
                OrmColumnId ormColumnId = e.getAnnotation(OrmColumnId.class);
                OrmColumn ormColumn = e.getAnnotation(OrmColumn.class);

                if (ormColumnId != null) {
                    fileWriter.write(IDENTITY_PRIMARY_KEY);
                    System.out.printf(IDENTITY_PRIMARY_KEY);
                }


                if (ormColumn != null) {
                    if (ormColumn.length() == 0) {
                        fileWriter.write(String.format(INT_FIELD_QUERY_FORM, ormColumn.name()));
                        System.out.printf(INT_FIELD_QUERY_FORM, ormColumn.name());
                    } else {
                        fileWriter.write(String.format(VARCHAR_FIELD_QUERY_FORM, ormColumn.name(), ormColumn.length()));
                        System.out.printf(VARCHAR_FIELD_QUERY_FORM, ormColumn.name(), ormColumn.length());
                    }
                }

            }

            fileWriter.write(CLOSE_TABLE_QUERY);
            System.out.printf(CLOSE_TABLE_QUERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Object entity) {

    }
    public void update(Object entity) {

    }

    public <T> T findById(Long id, Class<T> aClass) {
        return null;
    }
}
