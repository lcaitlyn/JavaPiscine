package edu.school21.app;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.preprocessor.PreProcessorToUpperImpl;
import edu.school21.printer.Printer;
import edu.school21.printer.PrinterWithDateTimeImpl;
import edu.school21.printer.PrinterWithPrefixImpl;
import edu.school21.renderer.Renderer;
import edu.school21.renderer.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static  void main(String[] args) {
        {
            PreProcessor preProcessor = new PreProcessorToUpperImpl();
            Renderer renderer = new RendererErrImpl(preProcessor);
//            PrinterWithDateTimeImpl printer = new PrinterWithDateTimeImpl(renderer);
            PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
            printer.setPrefix("MANUAL ");
            printer.print("Hello!");
        }
        {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
            Printer printer = context.getBean("printerWithPrefixImpl", Printer.class);
//            Printer printer = context.getBean("printerWithDateTimeImpl", Printer.class);
            printer.print ("Hello!");
        }
    }
}
