package aveh.soql;


public abstract class Condition implements Soqlable {
    protected String quote(String s) {
        if (s == null) {
            return "null";
        }
        StringBuffer str = new StringBuffer();
        str.append('\'');
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) == '\\') || (s.charAt(i) == '\"') || (s.charAt(i) == '\'')) {
                str.append('\\');
            }
            str.append(s.charAt(i));
        }
        str.append('\'');
        return str.toString();
    }

    public abstract void write(Output out);
}
