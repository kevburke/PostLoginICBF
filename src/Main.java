import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection.Response res = Jsoup
                .connect("https://webapp.icbf.com/auth/")
                .data("username", "IE1313426", "password", "Oranmore11")
                .method(Connection.Method.POST)
                .execute();

//This will get you cookies
        Map<String, String> loginCookies = res.cookies();

//And this is the easiest way I've found to remain in session
        Document mainin = Jsoup.connect("https://webapp.icbf.com/profile/beef-eurostar")
                .cookies(loginCookies)
                .get();

        //System.out.println(mainin);
        String login = mainin.toString();
        String[] lines = login.split("\n");
        String[] cowcodes = new String[lines.length];
        String[] cowcodesonly = new String[1000];
        for (int i = 0; i < cowcodes.length; i++) {
            cowcodes[i] = "";
        }

        int x = 0;
        String[] words = new String[lines.length];
        ArrayList  Add = new ArrayList();
        for (int i = 186; i < lines.length; i++) {
            int cc = 0;
            if (lines[i].contains(">IE")) {// && lines[i].contains("<td><a class=\"modal-link\" data-modal-width=\"895\" href=\"/profile/animal-detail/")) {
                String proper = lines[i];
                String find = proper.substring(85);
                String[] findNum = find.split("/");
                //System.out.println(findNum[0]);

                Add.add(findNum[0]);
               // System.out.println(Add);

                //words = proper.split("/");
                //cowcodes[cc++]= words[3];
                /*for (int j = 0; j < words.length; j++) {
                    if (words[j]!= "" && (words[j].startsWith("1") ||
                            words[j].startsWith("2") ||
                            words[j].startsWith("3") ||
                            words[j].startsWith("4") ||
                            words[j].startsWith("5") ||
                            words[j].startsWith("6") ||
                            words[j].startsWith("7") ||
                            words[j].startsWith("8") ||
                            words[j].startsWith("9"))) {
                        if (!words[j].matches(""))
                        cowcodes[cc++] = words[j];
                    }*/


            }
        }
        String newId = (String) Add.get(0);
        System.out.print(newId);
        Document profile = Jsoup.connect("https://webapp.icbf.com/profile/animal-detail/" + newId)
                .cookies(loginCookies)
                .get();
        System.out.print("after socket");
        System.out.print(profile);

        //String login2 = profile.toString();
        //String[] lines2 = login2.split("\n");
        //String[] cowcodes2 = new String[lines2.length];
       // String[] cowcodesonly2 = new String[1000];
       // for (int i = 0; i < cowcodes2.length; i++) {
       //     cowcodes2[i] = "";

       //     System.out.print(cowcodes2[i]);
       // }

           /* Arrays.sort(cowcodes);
            for (int j = 0; j < cowcodes.length; j++) {
                System.out.println(cowcodes[j]);
            }*/
        /*for (int i = 3; i < cowcodes.length; i++) {
            Document cow = Jsoup.connect("http://webapp.icbf.com/profile/animal-detail/"+ cowcodes[i]+"/")
                    .cookies(loginCookies)
                    .get();
            System.out.println(cow);
        }*/


    }

   /* public static String[] RemoveNullValue(String[] firstArray) {

        List<String> list = new ArrayList<String>();

        firstArray = Arrays.stream(firstArray)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);
        return firstArray;
    }*/
}


