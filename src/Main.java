import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.*;

public class Main {

    private static String[] jumbo;
    private static String[] num;
    private static String[] sex;
    private static String[] dob;
    private static String[] name;
    private static String[] status;
    private static String[] breed;
    private static String[] dam;
    private static String[] sire;

    public static void main(String[] args) throws Exception {

        Connection.Response res = Jsoup                                 //connect to ICBF
                .connect("https://webapp.icbf.com/auth/")
                .data("username", "IE1313426", "password", "Oranmore11")
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


        jumbo = new String[Add.size()];
        num = new String[Add.size()];
        sex = new String[Add.size()];
        dob = new String[Add.size()];
        name = new String[Add.size()];
        status = new String[Add.size()];
        breed = new String[Add.size()];
        dam = new String[Add.size()];
        sire = new String[Add.size()];
        int jum=0,nu=0, se=0, domm=0, nam=0, sta=0, bre=0, da=0, sir=0;
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
                int cc=0;

                if (linesin[i].contains("span id=")) {// && lines[i].contains("<td><a class=\"modal-link\" data-modal-width=\"895\" href=\"/profile/animal-detail/")) {

                    proper2[cc] = linesin[i];
                    //System.out.println(proper2[cc]);

                    String dataName = proper2[cc].substring(25,28);
                    //System.out.println(dataName);
                    switch(dataName){
                        case "jum":
                            jumbo[jum++] = proper2[cc].substring(32,35);
                            System.out.println(jumbo[jum-1]);
                        break;
                        case "num":
                            String temp = proper2[cc].substring(30);
                            String[] words=temp.split("<");
                            num[nu++]= words[0];
                            System.out.println(num[nu-1]);
                            break;
                        case "sex":
                            if(proper2[cc].contains("Female"))
                                sex[se++] ="Female";
                            else
                                sex[se++] ="Male";
                            System.out.println(sex[se-1]);
                            break;
                        case "dob":
                            String temp2 = proper2[cc].substring(30);
                            String[] words2=temp2.split("<");
                            dob[domm++]= words2[0];
                            System.out.println(dob[domm-1]);
                            break;
                        case "nam":
                            String temp3 = proper2[cc].substring(31);
                            String[] words3=temp3.split("<");
                            name[nam++]= words3[0];
                            System.out.println(name[nam-1]);
                            break;
                        case "sta":
                            String temp4 = proper2[cc].substring(33);
                            String[] words4=temp4.split("<");
                            status[sta++]= words4[0];
                            System.out.println(status[sta-1]);
                            break;
                        case "bre":
                            String temp5 = proper2[cc].substring(32);
                            String[] words5=temp5.split("<");
                            breed[bre++]= words5[0];
                            System.out.println(breed[bre-1]);
                            break;
                        case "dam":
                            String temp6 = proper2[cc].substring(30);
                            String[] words6=temp6.split("<");
                            dam[da++]= words6[0];
                            System.out.println(dam[da-1]);
                            break;
                        case "sir":
                            String temp7 = proper2[cc].substring(31);
                            String[] words7=temp7.split("<");
                            sire[sir++]= words7[0];
                            System.out.println(sire[sir-1]);
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


