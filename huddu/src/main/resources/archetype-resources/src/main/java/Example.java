import java.io.IOException;

public class Example {
    public static void main(String[] args) throws IOException, InterruptedException {

        ApiClient _c = new ApiClient("<project_id>", "<stream_id>");
        _c.report("<event_id>", "<valid_json_string>");
        // Example:   _c.report("test", "{" + '"' + "data" + '"' + ": {" + '"' + "markdown" + '"' + ": " + '"' + "#Error" + '"' + "}}"); // has to a valid json !string! (what json.dumps() outputs in python)
    }
}
