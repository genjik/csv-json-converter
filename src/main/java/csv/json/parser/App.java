package csv.json.parser;

public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Path to file wasn't received");
            return;
        }

        Parser parser = new Parser();
        try {
            System.out.println(parser.parse("./src/main/resources/doc.csv"));
        } catch (Exception e) {
            System.out.println("Internal error");
        }
    }
}
