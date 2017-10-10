package eight;

public class Lambda {
    private String sample = "hi";

    public void runQuery() {
        QuerySomewhere querySomewhere = new QuerySomewhere();
        querySomewhere.execute(sample -> "hello");
    }

    public interface Callback {
        public String consume(String sample);
    }

    public class QuerySomewhere {
        public QuerySomewhere() {
        }

        public void execute(Callback callback) {
            System.out.println("got " + callback.consume("sample"));
        }
    }

    public static void main(String... args) {
        new Lambda().runQuery();
    }
}
