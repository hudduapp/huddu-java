import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    String baseUrl;
    String project;
    String stream;


    public ApiClient(String project, String stream) {
        baseUrl = "http://127.0.0.1:8000";
        this.project = project;
        this.stream = stream;
    }

    public void _request(String eventType, String body) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        String url = this.baseUrl + "/" + this.project + "/" + this.stream + "/" + eventType;

        HttpRequest request = HttpRequest.newBuilder(URI.create(
                url
        )).header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    }


    public void report(String eventType, String data) throws IOException, InterruptedException {
        _request(eventType, data);
    }
}
