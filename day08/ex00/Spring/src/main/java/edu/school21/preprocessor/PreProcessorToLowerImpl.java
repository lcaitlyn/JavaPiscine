package edu.school21.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String proceed(String text) {
        return text.toLowerCase();
    }
}
