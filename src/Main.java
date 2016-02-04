import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //arrays to store all animals details
    private static String[] jumbo;
    private static String[] num;
    private static String[] sex;
    private static String[] dob;
    private static String[] name;
    private static String[] status;
    private static String[] breed;
    private static String[] dam;
    private static String[] sire;
    private static String[] replacement;
    private static String[] replacement_maternal;
    private static String[] terminal;
    private static String[] diary;
    private static String newId = "";

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

        //set size of arrays
        jumbo = new String[Add.size()];
        num = new String[Add.size()];
        sex = new String[Add.size()];
        dob = new String[Add.size()];
        name = new String[Add.size()];
        status = new String[Add.size()];
        breed = new String[Add.size()];
        dam = new String[Add.size()];
        sire = new String[Add.size()];
        replacement = new String[Add.size()];
        replacement_maternal = new String[Add.size()];
        terminal = new String[Add.size()];
        diary = new String[Add.size()];
        int ju=0,nu=0, se=0, domm=0, nam=0, sta=0, bre=0, da=0, sir=0;
        for (int xx = 0; xx < Add.size(); xx++) {

            newId = (String) Add.get(xx);     //take id out and make connection to individual profiles
            //System.out.print(newId);
            Document profileConnect = Jsoup.connect("https://webapp.icbf.com/profile/animal-detail/" + newId)
                    .cookies(loginCookies)
                    .get();
            // System.out.print("after socket");

            //  System.out.print(profileConnect);
            String profile = profileConnect.toString();                     //doc to strings
            String[] linesin = profile.split("\n");                         //split on newline
            String[] proper2 = new String[linesin.length];                  //array of length of lines

            for (int i = 0; i < linesin.length; i++) {                      //loop through lines
                int cc = 0;

                if (linesin[i].contains("span id=")) {                      //read lines with span id

                    proper2[cc] = linesin[i];                               //lines in array
                    //System.out.println(proper2[cc]);
                    String dataName = proper2[cc].substring(25, 28);         //parse keys
                   // System.out.print(dataName + "==");    //prints output & array name its located iin
                    switch (dataName) {
                        case "jum":                                          //get jumbo stick it in array
                            String temp0 = proper2[cc].substring(32);
                            String[] words0 = temp0.split("<");
                            jumbo[ju++] = words0[0];

                           // System.out.println(jumbo[ju - 1]);
                            break;
                        case "num":                                           //get number stick it in array
                            String temp = proper2[cc].substring(30);
                            String[] words = temp.split("<");
                            num[nu++] = words[0];
                            //System.out.println(num[nu - 1]);
                            break;
                        case "sex":                                              //get sex stick it in array
                            if (proper2[cc].contains("Female"))
                                sex[se++] = "Female";
                            else
                                sex[se++] = "Male";
                          //  System.out.println(sex[se - 1]);
                            break;
                        case "dob":                                              //get date of birth stick it in array
                            String temp2 = proper2[cc].substring(30);
                            String[] words2 = temp2.split("<");
                            dob[domm++] = words2[0];
                         //   System.out.println(dob[domm - 1]);
                            break;
                        case "nam":                                                 //get name stick it in array
                            String temp3 = proper2[cc].substring(31);
                            String[] words3 = temp3.split("<");
                            name[nam++] = words3[0];
                          //  System.out.println(name[nam - 1]);
                            break;
                        case "sta":                                                 //get days on farm stick it in array
                            String temp4 = proper2[cc].substring(33);
                            String[] words4 = temp4.split("<");
                            status[sta++] = words4[0];
                         //   System.out.println(status[sta - 1]);
                            break;
                        case "bre":                                                  //get breed stick it in array
                            String temp5 = proper2[cc].substring(32);
                            String[] words5 = temp5.split("<");
                            breed[bre++] = words5[0];
                         //   System.out.println(breed[bre - 1]);
                            break;
                        case "dam":                                                  //get mother stick it in array
                            String temp6 = proper2[cc].substring(30);
                            String[] words6 = temp6.split("<");
                            dam[da++] = words6[0];
                         //   System.out.println(dam[da - 1]);
                            break;
                        case "sir":                                                  //get father stick it in array
                            String temp7 = proper2[cc].substring(31);
                            String[] words7 = temp7.split("<");
                            sire[sir++] = words7[0];
                         //   System.out.println(sire[sir - 1]);
                            break;
                        default:
                            System.out.println("oops  :" + dataName);          //print error message
                            break;
                    }
                    cc++;
                }
            }
            //System.out.println("finished first loop");
            Document euroStarConnect = Jsoup.connect("https://webapp.icbf.com/profile/beef-animal-eurostar/" + newId)
                    .cookies(loginCookies)
                    .get();
            // System.out.print("after socket");
            //System.out.print(euroStarConnect);
            String profile2 = euroStarConnect.toString();                     //doc to strings
            String[] linesin2 = profile2.split("\n");                         //split on newline
            String[] proper3 = new String[linesin2.length];
            String[] val1 = new String[20];
            String[] val2 = new String[20];
            String [] val3 = new String[20];
            int cc = 0;
            int bb =0;
            String []one_three = new String[10];
            for (int j = 0; j <linesin2.length ; j++) {
                int dd = 0;

                if (linesin2[j].contains("<td>€")) {

                    proper3[dd] = linesin2[j];           //all lines with key into proper3
                    String par = linesin2[j];
                    int ll =0;
//                    System.out.println(par);

//                    System.out.print(one_three[0]);
//                    par=one_three.toString();
//                    par= new StringBuilder(par).reverse().toString();
//                    System.out.print(""+par);

                    Pattern pattern = Pattern.compile("€(.*?)<");
                    Matcher matcher = pattern.matcher(par);
                    while (matcher.find()) {
                        one_three[ll] = matcher.group(1);
                        ll++;
                        //System.out.println(matcher.group(1));
                    }
                     for(int u =0;u<ll;u++)
                     System.out.println(one_three[u]);

                    //String[] value = proper3[0].split("€");//split proper3 on € put into value
//                    String[] value2 = proper3[0].split("</small></td>");//split proper3 on € put into value
//                    String [] fvalue = value[1].split("<");//split on < put into fvalue left with all numbers
//                    String [] fvalue2 = value2[0].split("</small> <small>");//split on < put into fvalue left with all numbers
//                    String [] fvalue3 = fvalue2[0].split("l>€");//split on < put into fvalue left with all numbers
                    //String a = fvalue2[1];
                   // String [] fvalue3 = a.split("</small> ");//split on < put into fvalue left with all numbers
                   // for(int u =0;u<=1;u++)
                  // System.out.println(value[u]);
//                System.out.println(value[1]);
//                   System.out.println(value2[0]);
////
//                    System.out.println(fvalue[0]);
//                   System.out.println(fvalue2[0]);
//                    System.out.println(fvalue3[0]);
//                    val1[cc] = fvalue[0];                //but numbers into

                   // String [] value2 = proper3[1].split("€");
                   // String[] ffvalue = value2[1].split("<");
                   // val2[cc] = fvalue2[0];
                   // val3[cc] = fvalue3[0];
                    cc++;
                 //  for (int jj = 0; jj <fvalue.length ; jj++) {
                  //    System.out.println(jj+ fvalue[jj]);
                  //  }
                    //val1[] = fvalue[0];
                    //System.out.println(val1);
                }
            }
            replacement[xx] =val1[0];
            replacement_maternal[xx] = val2[0];

            //System.out.print(val1[xx]);
            //**System.out.print(replacement[xx]);
           // System.out.println(replacement_maternal[xx]);
            //cc=0;
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



