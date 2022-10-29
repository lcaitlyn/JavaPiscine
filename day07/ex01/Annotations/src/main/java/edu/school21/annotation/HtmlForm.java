package edu.school21.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface HtmlForm {
    String fileName();
    String action();
    String method();
}
