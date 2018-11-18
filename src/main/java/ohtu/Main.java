package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("opiskelijanumero " + studentNr);
        System.out.println("");
        for (Submission submission : subs) {
            System.out.println(submission);
        }

        int ttlUsedTime = 0;
        int ttlExersisesDone = 0;
        for (int i = 0; i < subs.length; i++) {
            ttlUsedTime += subs[i].getHours();
            ttlExersisesDone += subs[i].getExercises().length;
        }
        System.out.println("");
        System.out.println("yhteensä: " + ttlExersisesDone + " tehtävää " + ttlUsedTime + " tuntia");
    }
}
