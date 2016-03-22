import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //arrays to store all animals details
    private static String newId = "";
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
    private static String[] replacement_maternal_prog;
    private static String[] dairy;
    private static String[] calving_diff;
    private static String[] trait_reliability;
    private static String[] replaceStar;
    private static String[] termStar;
    private static String[] dairyStar;
    private static String[] docileStar;
    private static String[] carcassWeighStar;
    private static String[] carcassConformStar;
    private static String[] daughterMilkStar;
    private static String[] daughterCalvIntStar;
    private static String[] replacement_index;
    private static String[] docility_index;
    private static String[] docility_reliability;
    private static String[] carcassWeiIndx;
    private static String[] carcassWeightRel;
    private static String[] daughter_Calving_Diff;
    private static String[] daughter_Milk_index;
    private static String[] daughter_milk_rel;
    private static String[] carcass_conform_index;
    private static String[] carcass_conform_rel;
    private static String[] daughter_calving_rel;
    private static String[] daughter_calv_int;
    private static String[] daughter_calv_int_rel;


    // private static String[]
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
        dairy = new String[Add.size()];
        terminal = new String[Add.size()];
        replacement_maternal_prog = new String[Add.size()];
        replacement_index = new String[Add.size()];
        calving_diff = new String[Add.size()];
        trait_reliability = new String[Add.size()];
        replaceStar = new String[Add.size()];
        termStar = new String[Add.size()];
        dairyStar = new String[Add.size()];
        docileStar = new String[Add.size()];
        carcassWeighStar = new String[Add.size()];
        carcassWeiIndx = new String[Add.size()];
        carcassWeightRel = new String[Add.size()];
        carcassConformStar = new String[Add.size()];
        daughterMilkStar = new String[Add.size()];
        daughterCalvIntStar = new String[Add.size()];
        docility_index = new String[Add.size()];
        docility_reliability = new String[Add.size()];
        daughter_Calving_Diff = new String[Add.size()];
        daughter_calving_rel = new String[Add.size()];
        daughter_Milk_index = new String[Add.size()];
        carcass_conform_index = new String[Add.size()];
        carcass_conform_rel = new String[Add.size()];
        daughter_milk_rel = new String[Add.size()];
        daughter_calv_int = new String[Add.size()];
        daughter_calv_int_rel = new String[Add.size()];

        int ju=0,nu=0, se=0, domm=0, nam=0, sta=0, bre=0, da=0, sir=0;
        for (int xx = 0; xx <2;xx++){//Add.size(); xx++) {

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
            List<String> b = new ArrayList<String>();                               //create arraylist
            String profile2 = euroStarConnect.toString();                     //doc to strings
            String[] linesin2 = profile2.split("\n");                         //split on newline

            String[] diff =  new String[5];
            String[] diff3 = new String[5];
            String [] percent1 = new String[5];
            String[] percent3 = new String[5];

            String diff2 = "";
            String calv="";
            String percent= "";
            String percent2 = "";
            String repInd ="";
            String docile ="";
            String scale ="";
            String docPercent = "";
            String []one_three = new String[10];
            int ee=0;
            for (int j = 0; j <linesin2.length ; j++) {

                if (linesin2[j].contains("<td>€")) {                        //lines that include the € sign

                    String par = linesin2[j];
                    int ll =0;
                    Pattern pattern = Pattern.compile("€(.*?)<");           //search patterns that gets value after €
                    Matcher matcher = pattern.matcher(par);                 //before <
                    while (matcher.find()) {
                       // replacement[xx] = matcher.group(1).toString();
                        ll++;
                        b.add(matcher.group(1));
                      //  System.out.println(matcher.group(1));
                    }
                }

                String prepare ="";
                String[] prepare2;
                String[] prepare3;
                String stars;


                try {
                    if (linesin2[j].contains(" data-rating")) {
                        prepare = linesin2[j];
                        prepare2 = prepare.split("data-rating=\"");
                        prepare3 = prepare2[1].split("\"");
                        stars = prepare3[0];

                        //System.out.println("*******" + stars);
                        switch (ee){

                            case 1:
                                replaceStar[xx] = stars;
                               // System.out.println("*******" + stars);
                                break;
                            case 3:
                                termStar[xx]=stars;
                                break;
                            case 5:
                                dairyStar[xx]=stars;
                                break;
                            case 7:
                                docileStar[xx]=stars;
                                break;
                            case 9:
                                carcassWeighStar[xx]=stars;
                                break;
                            case 11:
                                carcassConformStar[xx]=stars;
                                break;
                            case 13:
                                daughterMilkStar[xx]=stars;
                                break;
                            case 15:
                                daughterCalvIntStar[xx]=stars;
                                break;
                            default:
                                break;
                        }
                        ee++;
                    }
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println(e);
                }
                /**
                 * Parsing Calving difficulty
                 */
                calv = linesin2[63];                     //searching calving
                diff = calv.split("<td>");
                diff2 = diff[1];
                diff3 = diff2.split("</");                                  //done
                percent = linesin2[64];
                percent1 = percent.split("<td>");
                percent2 = percent1[1];
                percent3 = percent2.split("<s");                            //done


                //Pattern newCalv = Pattern.compile("<td>(.*?)<");
                //Matcher matcher = newCalv.matcher(calv);
               // if(linesin2[j].contains("Calving difficulty")){
                 //   String nLine =linesin2[j++];
                 //  String [] aLine = nLine.split("<td>");
               // }
            }
            /**
             *Parsing Replacement Index reliability
             */
            String[] repInd2;
            String repInd3;
            String[] repInd4;
            repInd = linesin2[21];
            repInd2 = repInd.split("<td>");
            repInd3 = repInd2[1];
            repInd4 = repInd3.split(" \\(");                            //done

            /**
             * Docility index value and trait reliability
             */
            String doc;
            String [] doc1;
            String doc2;
            String[] doc3;
            String docrel;
            String [] docrel1;
            String docrel2;
            String[] docrel3;
            doc = linesin2[71];
            doc1 = doc.split("<td>");
            doc2 = doc1[1];
            doc3 = doc2.split("<");
          //  System.out.println(doc3[0]);                              //done
            docrel = linesin2[72];
            docrel1 = docrel.split("<td>");
            docrel2 = docrel1[1];
            docrel3 = docrel2.split("%");
           // System.out.println(docrel3[0]);                           //done

            /**
             * Carcass weight index and reliability
             */
            String car;
            String[] car1;
            String car2;
            String[] car3;
            car = linesin2[80];
            car1 = car.split("<td>");
            car2 = car1[1];
            car3 = car2.split("kg");
            //System.out.println(car3[0]);                              //done

            String carRel;
            String[] carRel1;
            String carRel2;
            String[] carRel3;
            carRel = linesin2[81];
            carRel1 = carRel.split("<td>");
            carRel2 = carRel1[1];
            carRel3 = carRel2.split("%");                               //done
           // System.out.println("carcass reliability " +carRel3[0]);

            /**
             * Carcass conformation index & reliability
             */
            String carcass;
            String[] carcass1;
            String carcass2;
            String[] carcass3;

            String carcassRel;
            String[] carcassRel1;
            String carcassRel2;
            String[] carcassRel3;

            carcass = linesin2[89];
            carcass1 = carcass.split("<td>");
            carcass2 = carcass1[1];
            carcass3 = carcass2.split("<");
            //System.out.println(carcass3[0]);                            //done

            carcassRel = linesin2[90];
            carcassRel1 = carcassRel.split("<td>");
            carcassRel2 = carcassRel1[1];
            carcassRel3 = carcassRel2.split("%");
            //System.out.println(carcassRel3[0]);                         //done

            /**
             * Daughter calving difficulty
             */
            String daughterCalv;
            String[] daughterCalv1;
            String daughterCalv2;
            String[] daughterCalv3;

            String dCalvRel;
            String[] dCalvRel1;
            String dCalvRel2;
            String[] dCalvRel3;

            daughterCalv = linesin2[100];
            daughterCalv1 = daughterCalv.split("<td>");
            daughterCalv2 = daughterCalv1[1];
            daughterCalv3 = daughterCalv2.split("%");
            //System.out.println(daughterCalv3[0]);                     //done

            dCalvRel = linesin2[101];
            dCalvRel1 =dCalvRel.split("<td>");
            dCalvRel2 = dCalvRel1[1];
            dCalvRel3 = dCalvRel2.split("%");
           // System.out.println(dCalvRel3[0]);                         //done

            /**
             * Daughter Milk index and reliability
             */

            String daughterMilk;
            String[] daughterMilk1;
            String daughterMilk2;
            String[] daughterMilk3;

            String dMilkRel;
            String[] dMilkRel1;
            String dMilkRel2;
            String[] dMilkRel3;

            daughterMilk = linesin2[108];
            daughterMilk1 = daughterMilk.split("<td>");
            daughterMilk2 = daughterMilk1[1];
            daughterMilk3 = daughterMilk2.split("kg");
           // System.out.println(daughterMilk3[0]);                     //done

            dMilkRel = linesin2[109];
            dMilkRel1 = dMilkRel.split("<td>");
            dMilkRel2 = dMilkRel1[1];
            dMilkRel3 = dMilkRel2.split("%");
            //System.out.println(dMilkRel3[0]);                         //done

            /**
             * Daughter calving interval
             */

            String dCalvInt;
            String[] dCalvInt1;
            String dCalvInt2;
            String[] dCalvInt3;

            String dCalvIntRel;
            String[] dCalvIntRel1;
            String dCalvIntRel2;
            String[] dCalvIntRel3;

            dCalvInt = linesin2[117];
            dCalvInt1 = dCalvInt.split("<td>");
            dCalvInt2 = dCalvInt1[1];
            dCalvInt3 = dCalvInt2.split("days");
            //System.out.println(dCalvInt3[0]);                     //done

            dCalvIntRel = linesin2[118];
            dCalvIntRel1 = dCalvIntRel.split("<td>");
            dCalvIntRel2 = dCalvIntRel1[1];
            dCalvIntRel3 = dCalvIntRel2.split("%");
            System.out.println(dCalvIntRel3[0]);                    //done

            trait_reliability[xx] = percent3[0];                //calving difficulty trait reliability
            calving_diff[xx] = diff3[0];                        //calving difficulty
            replacement[xx] =b.get(0);                          //
            replacement_maternal[xx] =b.get(1);
            replacement_maternal_prog[xx] =b.get(2);
            replacement_index[xx] = repInd4[0];                 //replacement index
            terminal[xx]=b.get(3);
            dairy[xx]= b.get(4);
            docility_index[xx] = doc3[0];                       //docility index
            docility_reliability[xx] = docrel3[0];              //docility reliability
            carcassWeiIndx[xx] = car3[0];                       //Carcass weight index
            carcassWeightRel[xx] =  carRel3[0];                 //Carcass weight reliability
            daughter_Milk_index[xx] = daughterMilk3[0];         //daughter milk index
            daughter_milk_rel[xx] = dMilkRel3[0];               //daughter milk reliability
            daughter_Calving_Diff[xx] = daughterCalv3[0];       //daughter calving difficulty
            daughter_calving_rel[xx] = dCalvRel3[0];            //daughter calving reliability
            carcass_conform_index[xx] = carcass3[0];            //Carcass conformation index
            carcass_conform_rel[xx] = carcassRel3[0];           //Carcass conformation reliability
            daughter_calv_int[xx] = dCalvInt3[0];               //daughter calving interval
            daughter_calv_int_rel[xx] = dCalvIntRel3[0];        //daughter calving interval reliability


            System.out.println("rep " +  replacement[xx]);
            System.out.println("rep_mater " +replacement_maternal[xx]);
            System.out.println("rep_mater_prog " + replacement_maternal_prog[xx]);
            System.out.println("terminal "+terminal[xx]);
            System.out.println("dairy " + dairy[xx]);
            System.out.println("cal " + calving_diff[xx]);
            System.out.println("trait_rel " + trait_reliability[xx]);
            System.out.println("replace_star " + replaceStar[xx]);
            System.out.println("terminal_star " + termStar[xx]);
            System.out.println("dairy_star " + dairyStar[xx]);
            System.out.println("docile_star " + docileStar[xx]);
            System.out.println("docile_index " + docility_index[xx]);
            System.out.println("carcass_weight_star " + carcassWeighStar[xx]);
            System.out.println("carcass_conformation_star " + carcassConformStar[xx]);
            System.out.println("daughter_milk_star "+ daughterMilkStar[xx]);
            System.out.println("daughter_calv_int_star " + daughterCalvIntStar[xx]);
            System.out.println("daughter_calving_difficulty"+ daughter_Calving_Diff[xx]);
            System.out.println("daughter_calving_difficulty_Reliability"+  daughter_calving_rel[xx]);
            System.out.println("daughter_milk_index"+ daughter_Milk_index[xx]);
            System.out.println("daughter_milk_reliability"+  daughter_milk_rel[xx]);
            System.out.println("carcass_conform_index "+ carcass_conform_index[xx]);
            System.out.println("carcass_conform_reliability "+ carcass_conform_rel[xx]);
            System.out.println("daughter_calving_interval "+  daughter_calv_int[xx]);
            System.out.println("daughter_calving_interval_reliability "+ daughter_calv_int_rel[xx]);

        }
    }
}



