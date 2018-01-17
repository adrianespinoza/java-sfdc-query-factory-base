package aveh.soql;

public class Output {
    public Output(String indent) {
        this.indent = indent;
    }

    private StringBuffer result = new StringBuffer();
    private StringBuffer currentIndent = new StringBuffer();
    private boolean newLineComing;

    private final String indent;

    public String toString() {
        return result.toString();
    }

    public Output print(Object o) {
        writeNewLineIfNeeded();
        result.append(o);
        return this;
    }

    public Output print(char c) {
        writeNewLineIfNeeded();
        result.append(c);
        return this;
    }

    public Output println(Object o) {
        writeNewLineIfNeeded();
        result.append(o);
        newLineComing = true;
        return this;
    }

    public Output println() {
        newLineComing = true;
        return this;
    }

    public void indent() {
        currentIndent.append(indent);
    }

    public void unindent() {
        currentIndent.setLength(currentIndent.length() - indent.length());
    }

    private void writeNewLineIfNeeded() {
        if (newLineComing) {
            result.append('\n').append(currentIndent);
            newLineComing = false;
        }
    }
}
