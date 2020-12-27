package serenity.spartan;


import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.Ensure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static net.serenitybdd.rest.RestRequests.given;


public class SpartanUtil {


        public static Map<String, Object> getRandomSpartanRequestPayload() {


            Faker faker = new Faker();
            Map<String, Object> payloadMap = new LinkedHashMap<>();
            payloadMap.put("name", faker.name().firstName());
            payloadMap.put("gender", faker.demographic().sex());
            payloadMap.put("phone", faker.number().numberBetween(5000000000L, 999999999L));

            return payloadMap;

        }


        @DisplayName("Admin user Should be able to Add Spartan")
        @Test
        public void testAdd1Data(){

            Map<String,Object> payload = SpartanUtil.getRandomSpartanRequestPayload();

            given()
                    .log().body()
                    .auth().basic("admin","admin")
                    .contentType(ContentType.JSON)
                    .body(payload).

            when()
                    .post("/spartans") ;
            Ensure.that("Request was successful",
                    thenResponse -> thenResponse.statusCode(201))  ;




        }







    }



