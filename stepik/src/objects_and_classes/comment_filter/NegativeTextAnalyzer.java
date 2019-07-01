package objects_and_classes.comment_filter;

public class NegativeTextAnalyzer extends KeywordAnalyzer{

    String[] negativeWords = {"=(", ":(", ":|"};

    public NegativeTextAnalyzer() {}

    @Override
    protected String[] getKeywords() {
        return this.negativeWords;
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE_TEXT;
    }
}
