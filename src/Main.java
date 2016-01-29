import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.*;

public class Main {

    private static String[] jumbo;

    public static void main(String[] args) throws Exception {

        Connection.Response res = Jsoup                                 //connect to ICBF
                .connect("https://webapp.icbf.com/auth/")
                .data("username", "", "password", "")
                .method(Connection.Method.POST)
                .execute();

//This will get you cookies
        Map<String, String> loginCookies = res.cookies();

// To remain in session
        Document mainin = Jsoup.connect("https://webapp.icbf.com/profile/beef-eurostar")
                .cookies(loginCookies)
                .get();

        //System.out.println(mainin);
        String login = mainin.toString();                   //doc to strings
        String[] lines = login.split("\n");                 //splitting to lines
        //String[] cowcodes = new String[lines.length];       //
       // String[] cowcodesonly = new String[1000];
       // for (int i = 0; i < cowcodes.length; i++) {
        //    cowcodes[i] = "";
       // }

        int x = 0;
       // String[] words = new String[lines.length];
        ArrayList  Add = new ArrayList();
        for (int i = 186; i < lines.length; i++) {
            int cc = 0;
            if (lines[i].contains(">IE")) {// && lines[i].contains("<td><a class=\"modal-link\" data-modal-width=\"895\" href=\"/profile/animal-detail/")) {
                String proper = lines[i];
                String find = proper.substring(85); //counting characters to get icbf id split
                String[] findNum = find.split("/"); //cutting off at the /
               // System.out.println(findNum[0]);     //print id's

                    Add.add(findNum[0]);                //stick id's into arrayliist
                   // System.out.println(Add);

            }
        }
        for (int xx = 0; xx < Add.size(); xx++) {


            String newId = (String) Add.get(xx);     //take id out and make connection to individual profiles
            //System.out.print(newId);
            Document profileConnect = Jsoup.connect("https://webapp.icbf.com/profile/animal-detail/" + newId)
                    .cookies(loginCookies)
                    .get();
            // System.out.print("after socket");

            //  System.out.print(profileConnect);
            String profile = profileConnect.toString();                   //doc to strings
            String[] linesin = profile.split("\n");
            String[] proper2 = new String[linesin.length];
            for (int i = 0; i < linesin.length; i++) {
                int cc=0,jum=0,num=0, sex=0, dob=0, nam=0, sta=0, bre=0,
                dam=0, sir=0;

                if (linesin[i].contains("span id=")) {// && lines[i].contains("<td><a class=\"modal-link\" data-modal-width=\"895\" href=\"/profile/animal-detail/")) {
                    proper2[cc] = linesin[i];
                    System.out.println(proper2[cc++]);

                    String dataName = proper2[cc].substring(25,28);
                    //System.out.println(dataName);
                    switch(dataName){
                        case "jum":
                            jumbo[jum++] = proper2[cc].substring(32,35);
                            System.out.println(jumbo[num]);
                        break;
                        case "num":

                            break;
                        case "sex":

                            break;
                        case "dob":

                            break;
                        case "nam":

                            break;
                        case "sta":

                            break;
                        case "bre":

                            break;
                        case "dam":

                            break;
                        case "sir":

                            break;
                        default:
                            System.out.println("you fucked up!  :" +dataName);
                            break;

                    }
                            cc++;
                }



            }

        }
//        for (int i = 0; i <proper2.length; i++) {
//            System.out.println(proper2[i]);
//        }

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


