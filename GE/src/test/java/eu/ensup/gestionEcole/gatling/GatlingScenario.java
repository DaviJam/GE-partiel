package eu.ensup.gestionEcole.gatling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;


/**
 * The type Gatling scenario.
 */
public class GatlingScenario extends Simulation {


    /**
     * The Public ip.
     */
    String public_ip = System.getProperty("public_ip");
    /**
     * The Values.
     */
    Map<String,String> values = new HashMap<String, String>() {{
        put("email", "directeur@ensup.eu");
        put ("password", "directeur");
    }};
    /**
     * The Object mapper.
     */
    ObjectMapper objectMapper = new ObjectMapper();
    /**
     * The Rbody.
     */
    String rbody;
    /**
     * The Token.
     */
    String token = "Bearer ";
    {
        try {
            rbody = objectMapper.writeValueAsString(values);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://"+public_ip+":9999/ge-service/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(rbody))
                    .setHeader("Content-type","application/json")
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());

            token= token+jsonObject.getString("token");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Etudiant api.
     */
    ScenarioBuilder etudiant_api = scenario("EtudiantApi")
            .exec(http("liter tous les etudiants")
                    .get("/api/students/getall").header("Authorization",token))
            .pause(1)
            .exec(http("get un etudiant")
                    .get("/api/students/get/uuid1").header("Authorization",token)
            )
            .pause(1)
            .exec(http("Ajouter un etudiant")
                    .post("/api/students/add")
                    .header("Content-Type", "application/json")
                    .header("Authorization",token)
                    .body(StringBody("{" +
                            "    \"nom\": \"Ali\"," +
                            "    \"prenom\": \"Gator\"," +
                            "    \"email\": \"ali.gator@gmail.com\"," +
                            "    \"adresse\": \"2 rue des animaux sauvages\"," +
                            "    \"telephone\": \"0761615263\"," +
                            "    \"dateNaissance\": \"2022-07-05\"" +
                            "}"))
            )
            .pause(1);

    /**
     * The Cours api.
     */
    ScenarioBuilder cours_api = scenario("CoursApi")
            .exec(http("lister les cours")
                    .get("/api/course")
                    .header("Authorization",token)
            )
            .pause(1);


    /**
     * The Http protocol.
     */
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://"+public_ip+":9999/ge-service")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3")
            .authorizationHeader("Basic ZGlyZWN0ZXVyQGVuc3VwLmV1OmRpcmVjdGV1cg==")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

    /**
     * The Directeur.
     */
    ScenarioBuilder directeur = scenario("Directeur1").exec(etudiant_api, cours_api);
    /**
     * The Directeur 2.
     */
    ScenarioBuilder directeur2 = scenario("Directeur2").exec(etudiant_api, cours_api);


    {
        setUp(
                //permet de tester 10 utilisateur sur 10 sec
//                 directeur.injectOpen(rampUsers(10).during(60)),
                //permet de tester 10 utilisateur en meme temps
                directeur2.injectOpen(atOnceUsers(50))
        ).protocols(httpProtocol);
    }

}
