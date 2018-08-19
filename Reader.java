import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.Collections;
import java.util.*;
import java.util.Map.Entry;
public class Reader {

    public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		System.out.println("Wait For Some time for the date to load!!");
        //CSVReader[] matches;
        HashMap<String,Matches> matches=ReadCSV1("Files/matches.csv");
		System.out.println("Mathches.csv File Loaded");
        HashMap<Integer,Deliveries> deliveries=ReadCSV2("Files/deliveries.csv");
		System.out.println("Deliveres.csv File loaded!!");
        //printMap(matches);
        HashMap<Integer,HashMap<String,Q2>>ans=Question2(deliveries,matches);
		System.out.println();
        HashMap<String,Integer> top=TopTeam(matches);
		HashMap<String,Q2> Inning1,Inning2;
		Inning1=ans.get(1);
        Inning2=ans.get(2);
		
		while(true){
			System.out.println("Enter 1 for Top Teams");
		System.out.println("Enter 2 for List of total number of fours, sixes, total score with respect to team and year.");
		System.out.println("Enter 3 for Top 10 best economy rate bowler with respect to year who bowled at least 10 overs"); 	
		System.out.println("Enter Any Key to Exit");
		System.out.println();
			String str=br.readLine();
			if(str.equals("1"))
				PrintTop(top);
		
           
        else if(str.equals("2")){
			printQ2(Inning1);
			printQ2(Inning2);
		}
		else if(str.equals("3"))
			ECONOMY_FUNC(matches,deliveries);
		else{
			System.out.println("Thank You!!!!");
			break;
		}
		}
        
        

    }
    public static void PrintTop(HashMap<String,Integer> top){
        Set<Entry<String, Integer>> set = top.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        System.out.println("SEASON\tTEAM_NAME\tCOUNT");
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Integer> entry:list){
            String str=entry.getKey();
            System.out.println(str.substring(0,4)+"\t"+str.substring(5,str.length())+"\t\t"+entry.getValue());
        }
    }
    public static Set<String> unique_year_match(HashMap<String,Matches>matches){
            Set<String>years_match=new HashSet<String>();
            Set<String> keys = matches.keySet();
            for(String p:keys){
                Matches ob=matches.get(p);
                years_match.add(ob.SEASON);
            }

            years_match.remove("SEASON");
            return years_match;
            
    }
    public static HashMap<String,Matches> ReadCSV1(String filename){ 
        HashMap<String,Matches> matches  = 
                        new HashMap<String,Matches>();
        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
       /* matches.put(1,new Matches("match[1]","match[2]","match[3]",
                                "match[4]","match[5]","match[6]",
                                "match[7]","match[8]","match[9]"));*/

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] match = line.split(cvsSplitBy);

                
                String res="";

                try {
                    if (match[9]!=null) {
                        res=match[9]; 
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                        res="";
                     } 
                     //System.out.println(match[0].trim());
                     matches.put(match[0].trim(),new Matches(match[1].trim(),match[2].trim(),match[3].trim(),
                                                                    match[4].trim(),match[5].trim(),match[6].trim(),
                                                                    match[7].trim(),match[8].trim(),res.trim()));      
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return matches;
    }
    public static HashMap<Integer,Deliveries> ReadCSV2(String filename){ 
        HashMap<Integer,Deliveries> deliveries  = 
                        new HashMap<Integer,Deliveries>();
                        
        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int index=0;
       /* matches.put(1,new Matches("match[1]","match[2]","match[3]",
                                "match[4]","match[5]","match[6]",
                                "match[7]","match[8]","match[9]"));*/

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] deliver = line.split(cvsSplitBy);

                
                String res="";

                /*try {
                    if (deliver[9]!=null) {
                        res=deliver[9]; 
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                        res="";
                     }*/ 
        deliveries.put(index,new Deliveries(deliver[0].trim(),deliver[1].trim(),deliver[2].trim(),
                                         deliver[3].trim(),deliver[4].trim(),deliver[5].trim(),
                                         deliver[6].trim(),deliver[7].trim(),deliver[8].trim(),
                                         deliver[9].trim(),deliver[10].trim(),deliver[11].trim(),
                                         deliver[12].trim(),deliver[13].trim(),deliver[14].trim(),
                                         deliver[15].trim()));      
            
            index++;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return deliveries;
    }   
    
    public static void printMap(HashMap<String,Matches> map){
         
        Set<String> keys = map.keySet();
        for(String p:keys){
            Matches ob=map.get(p);
            System.out.println(ob.SEASON);
        }
    }
    public static void printMap1(HashMap<Integer,Deliveries> map){
         
        Set<Integer> keys = map.keySet();
        for(Integer p:keys){
            Deliveries ob=map.get(p);
            System.out.println(ob.MATCH_ID);
        }
    }
    public static void printQ2(HashMap<String,Q2> map){
        HashMap<String,Integer>ans=new HashMap<String,Integer>(); 
        Set<String> keys = map.keySet();
        for(String p:keys){
            Q2 ob=map.get(p);
            String temp=ob.SEASON+" "+ob.TEAM_NAME+" "+ob.FOURS+" "+ob.SIX+" "+ob.TOTAL_RUNS;
            ans.put(temp,Integer.parseInt(ob.SEASON));
            //System.out.println(ob.SEASON+" "+ob.TEAM_NAME+" "+ob.FOURS+" "+ob.SIX+" "+ob.TOTAL_RUNS);
        }
        Set<Entry<String, Integer>> set = ans.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        System.out.println("SEASON\tTEAM_NAME\tCOUNT");
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Integer> entry:list){
            String str=entry.getKey();
            System.out.println(str);
        }
    }
    public static HashMap<String,Integer> TopTeam(HashMap<String,Matches> map){
         //List<topTeam>=new ArrayList<topTeam>();
        HashMap<String,Integer> tops=new HashMap<String,Integer>(); 
        Set<topTeam>top=new HashSet<topTeam>();
        Set<String> keys = map.keySet();
        for(String p:keys){
            Matches ob=map.get(p);
            if(ob.SEASON.equals("2016") || ob.SEASON.equals("2017")){
                if(ob.TOSS_DECISION.equals("field")){
                    Integer count = tops.get(ob.SEASON+"#"+ob.TOSS_WINNER);
                    count=(count==null)?1:count+1;

                    tops.put(ob.SEASON+"#"+ob.TOSS_WINNER,count);
                    //System.out.println(p+" "+ob.SEASON+" "+ob.TOSS_WINNER);
                }
            }
        }
        return tops;
    }
    public static HashMap<Integer,HashMap<String,Q2>> Question2(HashMap<Integer,Deliveries> deliveries,
                                                HashMap<String,Matches> matches){
        Set<Integer> deliver_keys = deliveries.keySet();
        Set<String> match_keys = matches.keySet();
        HashMap<String,Q2> q21  = 
                        new HashMap<String,Q2>();
        HashMap<String,Q2> q22  = 
                        new HashMap<String,Q2>(); 
                                       
        
        int fours1=0,six1=0,total_runs1=0,fours2=0,six2=0,total_runs2=0;
        String team_name1="",year1="",team_name2="",year2="";
        for(String p:match_keys){
            //System.out.println(p);
            fours1=0;
            six1=0;
            total_runs1=0;
            fours2=0;
            six2=0;
            total_runs2=0;
            Matches match=matches.get(p);

            for(Integer q:deliver_keys){

                Deliveries deliver=deliveries.get(q);
                if(p.equals(deliver.MATCH_ID)){ 
                    if(deliver.INNING.equals("1")){
                        team_name1=deliver.BATTING_TEAM;
                        year1=match.SEASON;
                        total_runs1+=Integer.parseInt(deliver.TOTAL_RUNS);
                        if(deliver.BATSMAN_RUNS.equals("4"))
                          fours1++;
                        if(deliver.BATSMAN_RUNS.equals("6"))
                            six1++;
                    }
                    if(deliver.INNING.equals("2")){
                        team_name2=deliver.BATTING_TEAM;
                        year2=match.SEASON;
                        total_runs2+=Integer.parseInt(deliver.TOTAL_RUNS);
                        if(deliver.BATSMAN_RUNS.equals("4"))
                          fours2++;
                        if(deliver.BATSMAN_RUNS.equals("6"))
                            six2++;
                    }            
                }
            }
            //Deliveries d=deliveries.get(Integer.parseInt(p));
            //System.out.println(p+" "+year+" "+team_name+" "+fours+" "+six+" "+total_runs);
            q21.put(p,new Q2(year1,team_name1,fours1,six1,total_runs1));
            q22.put(p,new Q2(year2,team_name2,fours2,six2,total_runs2));
            //Deliveries ob=deliveries.get(p);
            
        }
        HashMap<Integer,HashMap<String,Q2>>ret=new HashMap<Integer,HashMap<String,Q2>>();
        ret.put(1,q21);
        ret.put(2,q22);
        return ret;
    }
    public static void ECONOMY_FUNC(HashMap<String,Matches> matches,HashMap<Integer,Deliveries> deliveries){
        HashMap<String,ECONOMY>eco=new HashMap<String,ECONOMY>();
        Set<String> keys_matches = matches.keySet();
        for(String p:keys_matches){
            Matches ob_match=matches.get(p);
            Set<Integer> keys_deliveries = deliveries.keySet();
            for(Integer q:keys_deliveries){
                Deliveries ob_deliveries=deliveries.get(q);
                if(p.equals(ob_deliveries.MATCH_ID)){
                    String temp=ob_match.SEASON+"#"+ob_deliveries.BOWLER;
                    ECONOMY count=eco.get(temp);
                    if(count!=null){
                        int ball=count.BALLS+1;
                        int run=count.RUNS+Integer.parseInt(ob_deliveries.TOTAL_RUNS);
                        eco.put(temp,new ECONOMY(ball,run));
                    }
                    else{
                     eco.put(temp,new ECONOMY(0,0));   
                    }    
                }
            }    
         }
         Set<String> keys = eco.keySet();
        for(String x:keys){
            ECONOMY ob=eco.get(x);
            System.out.println(x+" "+ob.BALLS+" "+ob.RUNS);
        }

        // HashMap<String,Integer>result=new HashMap<String,Integer>();


    }

}
class ECONOMY{
    public Integer BALLS;
    public Integer RUNS;
    ECONOMY(Integer BALLS,Integer RUNS){
        this.BALLS=BALLS;
        this.RUNS=RUNS;
    }
}
class Matches{
    public Integer MATCH_ID;
    public String SEASON;
    public String CITY;
    public String DATE;
    public String TEAM1;
    public String TEAM2;
    public String TOSS_WINNER;
    public String TOSS_DECISION;
    public String RESULT;
    public String WINNER;
    Matches(String SEASON,String CITY,String DATE,String TEAM1,String TEAM2,
        String TOSS_WINNER,String TOSS_DECISION,String RESULT,String WINNER){
        this.SEASON=SEASON;
        this.CITY=CITY;
        this.DATE=DATE;
        this.TEAM1=TEAM1;
        this.TEAM2=TEAM2;
        this.TOSS_WINNER=TOSS_WINNER;
        this.TOSS_DECISION=TOSS_DECISION;
        this.RESULT=RESULT;
        this.WINNER=WINNER;
     }

}
class Q2{
    public String MATCH_ID;
    public String SEASON;
    public Integer FOURS;
    public Integer SIX;
    public String TEAM_NAME;
    public Integer TOTAL_RUNS;
    
    Q2(String SEASON,String TEAM_NAME,Integer FOURS,Integer SIX,Integer TOTAL_RUNS){
        this.SEASON=SEASON;
        this.TEAM_NAME=TEAM_NAME;
        this.FOURS=FOURS;
        this.SIX=SIX;
        this.TOTAL_RUNS=TOTAL_RUNS;
     }
}
class Deliveries{
    String MATCH_ID;
    String INNING;
    String BATTING_TEAM;
    String BOWLING_TEAM;
    String OVER;
    String BALL;
    String BATSMAN;
    String BOWLER;
    String WIDE_RUNS;
    String BYE_RUNS;
    String LEGBYE_RUNS;
    String NOBALL_RUNS;
    String PENALTY_RUNS;
    String BATSMAN_RUNS;
    String EXTRA_RUNS;
    String TOTAL_RUNS;

    Deliveries(String MATCH_ID,String INNING,String BATTING_TEAM,
               String BOWLING_TEAM,String OVER,String BALL,
               String BATSMAN,String BOWLER,String WIDE_RUNS,
               String BYE_RUNS,String LEGBYE_RUNS,String NOBALL_RUNS,
               String PENALTY_RUNS,String BATSMAN_RUNS,String EXTRA_RUNS,String TOTAL_RUNS){
        this.MATCH_ID=MATCH_ID;
        this.INNING=INNING;
        this.BATTING_TEAM=BATTING_TEAM;
        this.BOWLING_TEAM=BOWLING_TEAM;
        this.OVER=OVER;
        this.BALL=BALL;
        this.BATSMAN=BATSMAN;
        this.BOWLER=BOWLER;
        this.WIDE_RUNS=WIDE_RUNS;
        this.BYE_RUNS=BYE_RUNS;
        this.LEGBYE_RUNS=LEGBYE_RUNS;
        this.NOBALL_RUNS=NOBALL_RUNS;
        this.PENALTY_RUNS=PENALTY_RUNS;
        this.BATSMAN_RUNS=BATSMAN_RUNS;
        this.EXTRA_RUNS=EXTRA_RUNS;
        this.TOTAL_RUNS=TOTAL_RUNS;
     }

}
class  topTeam{
    public String TEAM_NAME;
    public String SEASON;
    topTeam(String SEASON,String TEAM_NAME){
        this.TEAM_NAME=TEAM_NAME;
        this.SEASON=SEASON;
    }
}

