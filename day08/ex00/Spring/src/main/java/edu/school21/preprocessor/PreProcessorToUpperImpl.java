package edu.school21.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor {
    @Override
    public String proceed(String text) {
        return text.toUpperCase();
    }
}
