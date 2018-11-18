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

        // Getting courses data
        String coursesURL = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String coursesBodyText = Request.Get(coursesURL).execute().returnContent().asString();
        Course[] courses = mapper.fromJson(coursesBodyText, Course[].class);

        for (Course course : courses) {
            course.initSubs();
            for (Submission sub : subs) {
                if (sub.getCourse().equals(course.getName())) {
                    course.addSubmission(sub);
                }
            }
        }

        System.out.println("opiskelijanumero " + studentNr + "\n");
        for (Course c : courses) {
            System.out.println(c.toString());
        }
    }
}