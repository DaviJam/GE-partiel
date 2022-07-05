 package eu.ensup.gestionEcole.gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class GatlingScenario extends Simulation {


    ScenarioBuilder etudiant_api = scenario("EtudiantApi")
            .exec(http("liter tous les etudiants")
                    .get("/api/students/getall"))
            .pause(1)
            .exec(http("get un etudiant")
                    .get("/api/students/get/uuid1")
            )
            .pause(1)
            .exec(http("Ajouter un etudiant")
                    .post("/api/students/add")
                    .header("Content-Type", "application/json")
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

    ScenarioBuilder cours_api = scenario("CoursApi")
            .exec(http("lister les cours")
                    .get("/api/course"))
            .pause(1);


    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://"+System.getProperty("public_ip")+":8080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .acceptEncodingHeader("gzip, deflate")
            .acceptLanguageHeader("it-IT,it;q=0.8,en-US;q=0.5,en;q=0.3")
            .authorizationHeader("Basic ZGlyZWN0ZXVyQGVuc3VwLmV1OmRpcmVjdGV1cg==")
            .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");

    ScenarioBuilder directeur = scenario("Directeur1").exec(etudiant_api, cours_api);
    ScenarioBuilder directeur2 = scenario("Directeur2").exec(etudiant_api, cours_api);


    {
        setUp(
                //permet de tester 10 utilisateur sur 10 sec
                directeur.injectOpen(rampUsers(10).during(10)),
                //permet de tester 10 utilisateur en meme temps
                directeur2.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocol);
    }

}