package ApiTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OffersApiTest {

    // ტესტი HTTP პასუხის სტატუსის სისწორის შესამოწმებლად
    @Test
    public void testApiResponseStatus() {
        // API-ზე GET მოთხოვნის გაგზავნა
        Response response = RestAssured.get("https://api.swoop.ge/");

        // პასუხის სტატუსის შემოწმება (200 OK)
        assertEquals(200, response.getStatusCode());

        // პასუხის ტიპის შემოწმება (application/json)
        assertEquals("application/json", response.getContentType());
    }

    // ტესტი JSON პასუხში მონაცემთა სტრუქტურის სისწორის შესამოწმებლად
    @Test
    public void testApiResponseDataStructure() {
        // GET მოთხოვნის გაგზავნა
        Response response = RestAssured.get("\n" +
                "https://api.swoop.ge/api/basket?LangID=1\n" +
                "Request Method:\n" +
                "GET");

        // პასუხის სხეულის JsonPath ობიექტად გარდაქმნა
        JsonPath jsonPath = response.jsonPath();

        // პირველი ელემენტის მონაცემების შემოწმება
        String id = jsonPath.getString("[0].ID");
        assertNotNull(id); // ID უნდა იყოს არა ცარიელი (null)

        String enabled = jsonPath.getString("[0].Enabled");
        assertNotNull(enabled); // Enabled უნდა იყოს არა ცარიელი (null)

        String icon = jsonPath.getString("[0].Icon");
        assertNotNull(icon); // Icon უნდა იყოს არა ცარიელი (null)

        String link = jsonPath.getString("[0].Link");
        assertNotNull(link); // Link უნდა იყოს არა ცარიელი (null)
    }

    // ტესტი JSON პასუხში ველების მნიშვნელობების სისწორის შესამოწმებლად
    @Test
    public void testApiResponseValues() {
        // GET მოთხოვნის გაგზავნა
        Response response = RestAssured.get("https://api.swoop.ge/");

        // პასუხის სხეულის JsonPath ობიექტად გარდაქმნა
        JsonPath jsonPath = response.jsonPath();

        // Enabled ველის მნიშვნელობის შემოწმება
        String enabled = jsonPath.getString("[0].Enabled");
        assertEquals("1", enabled); // Enabled-ის მნიშვნელობა უნდა იყოს 1

        // IsDeletable ველის მნიშვნელობის შემოწმება
        String isDeletable = jsonPath.getString("[0].IsDeletable");
        assertEquals("0", isDeletable); // IsDeletable-ის მნიშვნელობა უნდა იყოს 0
    }

    // ტესტი ნეგატიური სცენარებისთვის (მაგალითად, არასწორი მოთხოვნები)
    @Test
    public void testInvalidRequest() {
        // GET მოთხოვნის გაგზავნა არასწორი ID-ით
        Response response = RestAssured.get("https://api.swoop.ge/invalidID");

        // პასუხის სტატუსის შემოწმება — 404
        assertEquals(404, response.getStatusCode());

        // პასუხის ტექსტის შემოწმება (მაგალითად, შეცდომის შეტყობინება)
        String errorMessage = response.getBody().asString();
        assertTrue(errorMessage.contains("Not Found")); // უნდა შეიცავდეს "Not Found" ტექსტს
    }
}