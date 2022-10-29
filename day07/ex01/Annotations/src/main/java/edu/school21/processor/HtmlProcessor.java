package edu.school21.processor;

import com.google.auto.service.AutoService;
import edu.school21.annotation.HtmlForm;
import edu.school21.annotation.HtmlInput;
import javafx.scene.shape.Path;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;


@SupportedAnnotationTypes("edu.school21.annotation.HtmlForm")
@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class HtmlProcessor extends AbstractProcessor {

    private void createHtmlFile(Element htmlFormElement) {
        final String FORM_CREATER_FORMATED = "<form action = \"%s\" method = \"%s\">\n";
        final String INPUT_FORMATED = "\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n";
        final String SUBMIT_BUTTON = "\t<input type = \"submit\" value = \"Send\">\n";
        final String FORM_CLOSER = "</form>\n";

        HtmlForm htmlForm = htmlFormElement.getAnnotation(HtmlForm.class);
        HtmlInput htmlInput;

        try (FileWriter fileWriter = new FileWriter("target/classes/" + htmlForm.fileName())) {
            File file = new File(String.valueOf(StandardLocation.SOURCE_PATH));

            fileWriter.write(String.format(FORM_CREATER_FORMATED, htmlForm.action(), htmlForm.method()));

            for (Element e : htmlFormElement.getEnclosedElements()) {
                htmlInput = e.getAnnotation(HtmlInput.class);
                if (htmlInput != null)
                    fileWriter.write(String.format(INPUT_FORMATED, htmlInput.type(), htmlInput.name(), htmlInput.placeholder()));
            }

            fileWriter.write(SUBMIT_BUTTON);
            fileWriter.write(FORM_CLOSER);


            fileWriter.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element e : elements) {
            createHtmlFile(e);
        }

        return false;
    }
}

//@SupportedAnnotationTypes("edu.school21.annotation.HtmlForm")
//@AutoService(Processor.class)
//public class HtmlProcessor extends AbstractProcessor {
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        Set<? extends Element> annotatedHtmlFormElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
//        for (Element annotatedHtmlFormElement : annotatedHtmlFormElements) {
//            printForm(annotatedHtmlFormElement);
//        }
//        return false;
//    }
//
//    private static void printForm(Element annotatedHtmlFormElement) {
//        HtmlForm htmlForm = annotatedHtmlFormElement.getAnnotation(HtmlForm.class);
//        FileWriter formFile;
//        try {
//            formFile = new FileWriter(htmlForm.fileName());
//            formFile.write(String.format("<form action = \"%s\" method = \"%s\">\n",
//                    htmlForm.action(), htmlForm.method()));
//            List<? extends Element> annotatedHtmlFormElementEnclosedElements = annotatedHtmlFormElement.getEnclosedElements();
//            for (Element annotatedHtmlFormElementEnclosedElement : annotatedHtmlFormElementEnclosedElements) {
//                HtmlInput htmlInput = annotatedHtmlFormElementEnclosedElement.getAnnotation(HtmlInput.class);
//                if (htmlInput != null) {
//                    formFile.write(String.format("\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n",
//                            htmlInput.type(), htmlInput.name(), htmlInput.placeholder()));
//                }
//            }
//            formFile.write("\t<input type = \"submit\" value = \"Send\">\n");
//            formFile.write("</form>\n");
//            formFile.flush();
//            formFile.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
