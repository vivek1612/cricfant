package com.cricfant;

import com.cricfant.constant.Skill;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static final Pattern extIdPattern = Pattern.compile("^.*?(\\d+).*$");
    public static final Pattern teamPattern = Pattern.compile(".* - (.*) v (.*)$");
    public static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fant_cric?useSSL=false"
                        , "root", "Admin@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Map<String,Integer> links = new HashMap<>();
        links.put("http://www.espncricinfo.com/ci/content/squad/1134853.html",7);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134858.html",6);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134852.html",4);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134830.html",8);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134861.html",5);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134860.html",9);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134829.html",15);
        links.put("http://www.espncricinfo.com/ci/content/squad/1134862.html",3);
        links.forEach((link, teamId) -> {
            try {
                getPlayers(link,teamId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void getMatches(String url) throws IOException, ParseException, SQLException {
        Document document = Jsoup.parse(new URL(url), 10000);
        Elements elements = document.select("li[venue]");
        int seq_num = 0;
        for (Element element : elements) {
            seq_num++;
            String dateStr = element.select(".fixture_date").first().text();
            String timeStr = element.select(".fixture_date").get(1).text().substring(0, 5);
            dateStr = dateStr.trim() + ", 2018";
            dateStr = dateStr.substring(4);
            Date date = DateFormat.getDateInstance(DateFormat.MEDIUM).parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeStr.split(":")[0]));
            c.set(Calendar.MINUTE, Integer.parseInt(timeStr.split(":")[1]));
            Element team = element.select(".play_team").first();
            String descr = team.text();
            Matcher matcher = teamPattern.matcher(descr);
            String team1 = null;
            String team2 = null;
            if (matcher.matches()) {
                team1 = matcher.group(1);
                team2 = matcher.group(2);
            } else {
                System.err.println("ERR");
            }
            String venue = element.select(".play_stadium").first().text();
            venue = venue.substring(0, venue.indexOf(","));
            if(venue.equals("M Chinnaswamy Stadium")){
                venue="M.Chinnaswamy Stadium";
            }
            Integer venueId = getId(venue,"venue");
            Integer team1Id = getId(team1, "team");
            Integer team2Id = getId(team2, "team");
            System.out.printf("%s,0,%d,%d,%d,%d,1,%s\n",FORMAT.format(c.getTime()),venueId,seq_num,team1Id,team2Id,descr);
        }
    }

    private static Integer getId(String name, String tableName) throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = connection.prepareStatement("select `id` from `"+tableName+"` where `name`=?");
            st.setString(1,name);
            rs = st.executeQuery();
            rs.first();
            int id = rs.getInt(1);

            return id;
        } catch (SQLException e) {
            System.err.println("ERR WITH "+name);
            throw e;
        }finally {
            rs.close();
            st.close();
        }
    }

    public static void getPlayers(String url, Integer teamId) throws IOException {
        Document document = Jsoup.parse(new URL(url), 10000);
        Elements playerElement = document.select("div .large-13.medium-13");
        for (Element element : playerElement) {
            Element link = element.select("h3>a").first();
            String name = link.html();
            Integer extId = 0;
            Matcher matcher = extIdPattern.matcher(link.attr("href"));
            if (matcher.matches()) {
                extId = Integer.valueOf(matcher.group(1));
            } else {
                System.err.println("error with " + link.attr("href"));
            }
            Element roleElement = element.select("span:nth-child(3)").first();
            Skill skill = null;
            String html = roleElement.html().toLowerCase();
            if (html.contains("allrounder")) {
                skill = Skill.ALL_ROUNDER;
            } else if (html.contains("wicketkeeper")) {
                skill = Skill.KEEPER;
            } else if (html.contains("bowler")) {
                skill = Skill.BOWLER;
            } else if (html.contains("batsman")) {
                skill = Skill.BATSMAN;
            } else {
                System.err.println("error in " + html);
            }
            System.out.printf("%s,%d,%d,%s\n", name, extId, teamId, skill);
        }
    }
}
