package com.cricfant.service;

import com.cricfant.constant.Dismissal;
import com.cricfant.constant.MatchResult;
import com.cricfant.model.Match;
import com.cricfant.model.MatchPerf;
import com.cricfant.model.Player;
import com.cricfant.model.Tournament;
import com.cricfant.repository.MatchPerfRepository;
import com.cricfant.repository.MatchRepository;
import com.cricfant.repository.PlayerRepository;
import com.cricfant.repository.TournamentRepository;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
@Service
@Transactional
@Slf4j
public class MatchService {

    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    MatchPerfRepository matchPerfRepository;

    private static final Pattern runOutPattern = Pattern.compile("^run out \\((?<player>.*?)(/.*)?\\)$");
    private static final Pattern stumpedPattern = Pattern.compile("^st (.*) b (.*)$");
    private static final Pattern lbwPattern = Pattern.compile("^lbw b (.*)$");
    private static final Pattern hitWicketPattern = Pattern.compile("^hit wicket b (.*)$");
    private static final Pattern playerIdPattern = Pattern.compile("^.*/player/(\\d+).*$");
    private static final Pattern cAndBPattern = Pattern.compile("^c & b (.*)$");
    private static final Pattern caughtPattern = Pattern.compile("^c (.*) b (.*)$");
    private static final Pattern bowledPattern = Pattern.compile("^b (.*)$");

    public Optional<Match> getNextMatch(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        List<Match> matches = tournament.getMatches().stream()
                .filter(match -> !match.getLockedIn())
                .sorted(Comparator.comparingInt(Match::getSeqNum))
                .collect(Collectors.toList());
        if (matches.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(matches.get(0));
    }

    public Optional<Match> getLastMatch(Integer tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).get();
        List<Match> matches = tournament.getMatches().stream()
                .filter(match -> match.getLockedIn())
                .sorted(Comparator.comparingInt(Match::getSeqNum))
                .collect(Collectors.toList());
        if (matches.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(matches.get(matches.size() - 1));
    }

    public void processScoresForMatch(String url, Integer matchId) throws IOException {
        Match m = matchRepository.findById(matchId).get();
        List<MatchPerf> matchPerfs = getMatchPerfs(url, m);
        calculatePoints(matchPerfs);
        Set<MatchPerf> existingPerfs = matchPerfRepository.findAllByMatchId(matchId);
        matchPerfRepository.deleteAll(existingPerfs);
        matchPerfRepository.flush();
        matchPerfRepository.saveAll(matchPerfs);
    }

    public void setResultForMatch(MatchResult result, Integer matchId) {
        Match match = matchRepository.findById(matchId).get();
        match.setResult(result);
    }

    private void calculatePoints(List<MatchPerf> matchPerfs) {
        matchPerfs.forEach(matchPerf -> {
            matchPerf.setBattingPoints(getBattingPoints(matchPerf));
            matchPerf.setBowlingPoints(getBowlingPoints(matchPerf));
            matchPerf.setFieldingPoints(getFieldingPoints(matchPerf));
            matchPerf.setBonusPoints(getBonusPoints(matchPerf));
        });
    }

    private Integer getBattingPoints(MatchPerf mp) {

        Integer battingPoints = 0;
        Integer runsScored = mp.getRunsScored() == null ? 0 : mp.getRunsScored();
        Integer ballsFaced = mp.getBallsFaced() == null ? 0 : mp.getBallsFaced();
        Integer foursHit = mp.getFoursHit() == null ? 0 : mp.getFoursHit();
        Integer sixesHit = mp.getSixesHit() == null ? 0 : mp.getSixesHit();

        battingPoints += runsScored;
        battingPoints += runsScored - ballsFaced;
        if (runsScored >= 100) {
            battingPoints += 50;
        } else if (runsScored >= 75) {
            battingPoints += 30;
        } else if (runsScored >= 50) {
            battingPoints += 15;
        } else if (runsScored >= 25) {
            battingPoints += 5;
        }
        if (runsScored == 0 && mp.getDismissal().isOut()) {
            battingPoints += -5;
        }
        battingPoints += foursHit * 1;
        battingPoints += sixesHit * 2;
        return battingPoints;
    }

    private Integer getBowlingPoints(MatchPerf mp) {
        Integer bowlingPoints = 0;
        Integer wicketsTaken = mp.getWicketsTaken() == null ? 0 : mp.getWicketsTaken();
        Integer ballsBowled = mp.getBallsBowled() == null ? 0 : mp.getBallsBowled();
        Integer runsGiven = mp.getRunsGiven() == null ? 0 : mp.getRunsGiven();
        Integer dotBalls = mp.getDotsBowled() == null ? 0 : mp.getDotsBowled();
        Integer maidens = mp.getMaidensBowled() == null ? 0 : mp.getMaidensBowled();

        bowlingPoints += wicketsTaken * 20;
        Integer econ = new Double(Math.round((ballsBowled * 1.5) - runsGiven)).intValue();
        if (econ > 0) {
            econ *= 2;
        }
        bowlingPoints += econ;
        if (wicketsTaken >= 5) {
            bowlingPoints += 50;
        } else if (wicketsTaken >= 4) {
            bowlingPoints += 30;
        } else if (wicketsTaken >= 3) {
            bowlingPoints += 15;
        } else if (wicketsTaken >= 2) {
            bowlingPoints += 5;
        }

        bowlingPoints += dotBalls;
        bowlingPoints += maidens * 25;

        return bowlingPoints;
    }

    private Integer getFieldingPoints(MatchPerf mp) {
        Integer fieldingPoints = 0;
        Integer catches = mp.getCatches() == null ? 0 : mp.getCatches();
        Integer runOuts = mp.getRunOuts() == null ? 0 : mp.getRunOuts();
        Integer stumpings = mp.getStumpings() == null ? 0 : mp.getStumpings();

        fieldingPoints += catches * 10;
        fieldingPoints += runOuts * 10;
        fieldingPoints += stumpings * 10;

        return fieldingPoints;
    }

    private Integer getBonusPoints(MatchPerf mp) {
        Integer bonusPoints = 0;
        MatchResult result = mp.getMatch().getResult();
        if (result != null) {
            if (mp.getTeamNum() == 1 && result.equals(MatchResult.TEAM1_WIN)) {
                bonusPoints += 5;
            } else if (mp.getTeamNum() == 2 && result.equals(MatchResult.TEAM2_WIN)) {
                bonusPoints += 5;
            }
        }
        if (mp.getMom() != null && mp.getMom()) {
            bonusPoints += 25;
        }
        return bonusPoints;
    }

    private List<MatchPerf> getMatchPerfs(String url, Match m) throws IOException {
        Document document = Jsoup.parse(new URL(url), 10000);
        Map<String, MatchPerf> scoresMap = new LinkedHashMap<>();
        Elements header = document.select("article:nth-of-type(1) .wrap.header");
        String headerText = header.select("div:nth-of-type(4)").text();
        boolean hasMinutes = headerText.equals("M");

        Elements innings1BattingScores = document.select("article:nth-of-type(1) .wrap.batsmen");
        getBattingScores(innings1BattingScores, scoresMap, m, 1, hasMinutes);
        Elements innings1DNB = document.select("article:nth-of-type(1) .wrap.dnb");
        getDNB(innings1DNB, scoresMap, m, 1);
        Elements innings1BowlingScores = document.select("article:nth-of-type(1) .scorecard-section.bowling");
        getBowlingScores(innings1BowlingScores, scoresMap, m, 2);

        header = document.select("article:nth-of-type(2) .wrap.header");
        headerText = header.select("div:nth-of-type(4)").text();
        hasMinutes = headerText.equals("M");

        Elements innings2BattingScores = document.select("article:nth-of-type(2) .wrap.batsmen");
        getBattingScores(innings2BattingScores, scoresMap, m, 2, hasMinutes);
        Elements innings2DNB = document.select("article:nth-of-type(2) .wrap.dnb");
        getDNB(innings2DNB, scoresMap, m, 2);
        Elements innings2BowlingScores = document.select("article:nth-of-type(2) .scorecard-section.bowling");
        getBowlingScores(innings2BowlingScores, scoresMap, m, 1);

        List<MatchPerf> team1MatchPerfs = scoresMap.values().stream()
                .filter(MatchPerf -> MatchPerf.getTeamNum().equals(1))
                .collect(Collectors.toList());

        List<MatchPerf> team2MatchPerfs = scoresMap.values().stream()
                .filter(MatchPerf -> MatchPerf.getTeamNum().equals(2))
                .collect(Collectors.toList());

        for (MatchPerf MatchPerf : scoresMap.values()) {
            if (MatchPerf.getDismissal() == null) {
                MatchPerf.setDismissal(Dismissal.DNB);
                MatchPerf.setBatPos(Integer.MAX_VALUE);
                log.warn("UNKNOWN DISMISSAL FOR: " + MatchPerf);
            }
        }

        Integer momId = null;
        Element mom = document.select(".gp__cricket__player-match__player__detail__link").first();
        if (mom != null) {
            String momLink = mom.attr("href");
            Matcher matcher = playerIdPattern.matcher(momLink);
            if (matcher.matches()) {
                momId = Integer.valueOf(matcher.group(1));
            } else {
                log.warn("no mom found in " + momLink);
            }
        }

        List<MatchPerf> sortedMatchPerfs = scoresMap.values().stream().sorted(
                Comparator.comparingInt(MatchPerf::getTeamNum)
                        .thenComparingInt(MatchPerf::getBatPos)).collect(Collectors.toList());

        for (MatchPerf matchPerf : sortedMatchPerfs) {
            List<MatchPerf> scoresToSearch = matchPerf.getTeamNum().equals(1) ? team2MatchPerfs : team1MatchPerfs;
            if (matchPerf.getCaughtByName() != null) {
                Integer playerId = findPlayer(matchPerf.getCaughtByName(), scoresToSearch);
                if (playerId == null) {
                    throw new IllegalStateException("NO MATCH FOUND FOR CAUGHTBY " + matchPerf.getCaughtByName());
                }
                MatchPerf caughtBy = getByPlayerId(sortedMatchPerfs, playerId);
                Integer catches = caughtBy.getCatches() == null ? 0 : caughtBy.getCatches();
                caughtBy.setCatches(++catches);
            }
            if (matchPerf.getRunOutByName() != null) {
                Integer playerId = findPlayer(matchPerf.getRunOutByName(), scoresToSearch);
                if (playerId == null) {
                    throw new IllegalStateException("NO MATCH FOUND FOR RUNOUTBY " + matchPerf.getRunOutByName());
                }
                MatchPerf runOutBy = getByPlayerId(sortedMatchPerfs, playerId);
                Integer runOuts = runOutBy.getRunOuts() == null ? 0 : runOutBy.getRunOuts();
                runOutBy.setRunOuts(++runOuts);
            }
            if (matchPerf.getStumpedByName() != null) {
                Integer playerId = findPlayer(matchPerf.getStumpedByName(), scoresToSearch);
                if (playerId == null) {
                    throw new IllegalStateException("NO MATCH FOUND FOR STUMPEDBY " + matchPerf.getStumpedByName());
                }
                MatchPerf stumpedBy = getByPlayerId(sortedMatchPerfs, playerId);
                Integer stumpings = stumpedBy.getStumpings() == null ? 0 : stumpedBy.getStumpings();
                stumpedBy.setStumpings(++stumpings);
            }

            if (matchPerf.getPlayer().getExtId().equals(momId)) {
                matchPerf.setMom(true);
            }
        }
        return sortedMatchPerfs;
    }

    private MatchPerf getByPlayerId(List<MatchPerf> matchPerfs, Integer playerId) {
        for (MatchPerf matchPerf : matchPerfs) {
            if (matchPerf.getPlayer().getId().equals(playerId)) {
                return matchPerf;
            }
        }
        return null;
    }

    private void getBowlingScores(Elements scores, Map<String, MatchPerf> scoresMap, Match match, Integer teamNum) {
        Elements bScores = scores.select("tbody > tr");
        bScores.forEach(row -> {
            Element playerLink = row.select("td:nth-of-type(1) > a").get(0);
            String playerHref = playerLink.attr("href");
            String playerShortName = playerLink.text()
                    .replaceAll("\\(c\\)", "")
                    .replaceAll("†", "")
                    .replaceAll(",", "")
                    .trim();
            String playerExtId;
            Matcher matcher = playerIdPattern.matcher(playerHref);
            if (matcher.matches()) {
                playerExtId = matcher.group(1);
            } else {
                throw new IllegalStateException("PLAYER ID NOT FOUND IN: " + playerHref);
            }

            MatchPerf mp;
            if (scoresMap.containsKey(playerExtId)) {
                mp = scoresMap.get(playerExtId);
            } else {
                mp = new MatchPerf();
                mp.setMatch(match);
                try {
                    Player player = playerRepository.findByExtId(Integer.valueOf(playerExtId)).get();
                    mp.setPlayer(player);
                } catch (Exception e) {
                    throw new IllegalStateException("no player " + playerShortName + " (" + playerExtId + ")");
                }
                mp.setTeamNum(teamNum);
                mp.setPlayerShortName(playerShortName);
                scoresMap.put(playerExtId, mp);
            }

            String overs = row.select("td:nth-of-type(3)").text();
            String maidens = row.select("td:nth-of-type(4)").text();
            String runs = row.select("td:nth-of-type(5)").text();
            String wickets = row.select("td:nth-of-type(6)").text();
            String dots = row.select("td:nth-of-type(8)").text();
            String fours = row.select("td:nth-of-type(9)").text();
            String sixes = row.select("td:nth-of-type(10)").text();
            String wides = row.select("td:nth-of-type(11)").text();
            String noBalls = row.select("td:nth-of-type(12)").text();

            mp.setBallsBowled(calculateBalls(overs));
            mp.setMaidensBowled(Integer.valueOf(maidens));
            mp.setRunsGiven(Integer.valueOf(runs));
            mp.setWicketsTaken(Integer.valueOf(wickets));
            mp.setDotsBowled(Integer.valueOf(dots));
            mp.setFoursGiven(Integer.valueOf(fours));
            mp.setSixesGiven(Integer.valueOf(sixes));
            mp.setWidesBowled(Integer.valueOf(wides));
            mp.setNoBallsBowled(Integer.valueOf(noBalls));
        });
    }

    private void getDNB(Elements team1DNB, Map<String, MatchPerf> scoresMap,
                        Match match, Integer teamNum) {
        Elements playerLinks = team1DNB.select("a");
        playerLinks.forEach(element -> {
            String playerExtId;
            String playerLink = element.attr("href");
            String playerShortName = element.text()
                    .replaceAll("\\(c\\)", "")
                    .replaceAll("†", "")
                    .replaceAll(",", "")
                    .trim();
            Matcher matcher = playerIdPattern.matcher(playerLink);
            if (matcher.matches()) {
                playerExtId = matcher.group(1);
            } else {
                throw new IllegalStateException("PLAYER ID NOT FOUND IN: " + playerLink);
            }
            MatchPerf mp;
            if (scoresMap.containsKey(playerExtId)) {
                mp = scoresMap.get(playerExtId);
            } else {
                mp = new MatchPerf();
                mp.setMatch(match);
                try {
                    Player player = playerRepository.findByExtId(Integer.valueOf(playerExtId)).get();
                    mp.setPlayer(player);
                } catch (Exception e) {
                    throw new IllegalStateException("no player " + playerShortName + " (" + playerExtId + ")");
                }
                mp.setTeamNum(teamNum);
                mp.setPlayerShortName(playerShortName);
                scoresMap.put(playerExtId, mp);
            }
            mp.setDismissal(Dismissal.DNB);
            mp.setBatPos(Integer.MAX_VALUE);
        });
    }

    private void getBattingScores(Elements scores, Map<String, MatchPerf> scoresMap, Match match, Integer teamNum, boolean hasMinutes) {
        int index = 1;
        for (Element e : scores) {
            String playerLink = e.select(".cell.batsmen a").attr("href");
            String playerShortName = e.select(".cell.batsmen a").text()
                    .replaceAll("\\(c\\)", "").replaceAll("†", "").trim();
            String playerExtId;
            Matcher matcher = playerIdPattern.matcher(playerLink);
            if (matcher.matches()) {
                playerExtId = matcher.group(1);
            } else {
                throw new IllegalStateException("PLAYER ID NOT FOUND IN: " + playerLink);
            }
            MatchPerf mp;
            if (scoresMap.containsKey(playerExtId)) {
                mp = scoresMap.get(playerExtId);
            } else {
                mp = new MatchPerf();
                mp.setMatch(match);
                try {
                    Player player = playerRepository.findByExtId(Integer.valueOf(playerExtId)).get();
                    mp.setPlayer(player);
                } catch (Exception ex) {
                    throw new IllegalStateException("no player " + playerShortName + " (" + playerExtId + ")");
                }
                mp.setTeamNum(teamNum);
                mp.setPlayerShortName(playerShortName);
                scoresMap.put(playerExtId, mp);
            }
            mp.setBatPos(index++);
            String dismissal = e.select(".cell.commentary").text().replaceAll("†", "");
            if (dismissal.startsWith("c & b")) {
                mp.setDismissal(Dismissal.CAUGHT);
                Matcher cbMatcher = cAndBPattern.matcher(dismissal);
                if (cbMatcher.matches()) {
                    mp.setCaughtByName(cbMatcher.group(1));
                    mp.setDismissedByName(cbMatcher.group(1));
                } else {
                    throw new IllegalStateException("NO C and B FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("c ")) {
                mp.setDismissal(Dismissal.CAUGHT);
                Matcher cMatcher = caughtPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    String caughtBy = cMatcher.group(1);
                    if (!caughtBy.startsWith("sub ")) {
                        mp.setCaughtByName(caughtBy);
                    }
                    mp.setDismissedByName(cMatcher.group(2));
                } else {
                    throw new IllegalStateException("NO CAUGHT FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("not out")) {
                mp.setDismissal(Dismissal.NOT_OUT);
            } else if (dismissal.startsWith("b ")) {
                mp.setDismissal(Dismissal.BOWLED);
                Matcher cMatcher = bowledPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    mp.setDismissedByName(cMatcher.group(1));
                } else {
                    throw new IllegalStateException("NO BOWLED FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("run out")) {
                mp.setDismissal(Dismissal.RUN_OUT);
                Matcher cMatcher = runOutPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    String runOutBy = cMatcher.group("player");
                    if (!runOutBy.startsWith("sub ")) {
                        mp.setRunOutByName(runOutBy);
                    }
                } else {
                    throw new IllegalStateException("NO RUN OUT FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("st ")) {
                mp.setDismissal(Dismissal.STUMPED);
                Matcher cMatcher = stumpedPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    String stumpedBy = cMatcher.group(1);
                    if (!stumpedBy.startsWith("sub ")) {
                        mp.setStumpedByName(stumpedBy);
                    }
                    mp.setDismissedByName(cMatcher.group(2));
                } else {
                    throw new IllegalStateException("NO STUMPED FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("lbw ")) {
                mp.setDismissal(Dismissal.LBW);
                Matcher cMatcher = lbwPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    mp.setDismissedByName(cMatcher.group(1));
                } else {
                    throw new IllegalStateException("NO LBW FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("retired hurt")) {
                mp.setDismissal(Dismissal.RETIRED_HURT);
            } else if (dismissal.startsWith("hit wicket")) {
                mp.setDismissal(Dismissal.HIT_WICKET);
                Matcher cMatcher = hitWicketPattern.matcher(dismissal);
                if (cMatcher.matches()) {
                    mp.setDismissedByName(cMatcher.group(1));
                } else {
                    throw new IllegalStateException("NO HIT WICKET FOUND IN: " + dismissal);
                }
            } else if (dismissal.startsWith("absent hurt")) {
                mp.setDismissal(Dismissal.ABSENT_HURT);
            } else if (dismissal.startsWith("obstructing the field")) {
                mp.setDismissal(Dismissal.OBS_FIELD);
            } else {
                throw new IllegalStateException("UNKNOWN DISMISSAL: " + dismissal);
            }

            if (e.select("div:nth-of-type(3)").text().trim().equals("-")) {
                continue;
            }

            Integer ballsIndex = hasMinutes ? 5 : 4;
            Integer runs = Integer.valueOf(e.select("div:nth-of-type(3)").text());
            Integer balls = Integer.valueOf(e.select("div:nth-of-type(" + ballsIndex + ")").text());
            Integer fours = Integer.valueOf(e.select("div:nth-of-type(" + (ballsIndex + 1) + ")").text());
            Integer sixes = Integer.valueOf(e.select("div:nth-of-type(" + (ballsIndex + 2) + ")").text());
            mp.setRunsScored(runs);
            mp.setBallsFaced(balls);
            mp.setFoursHit(fours);
            mp.setSixesHit(sixes);
        }
    }

    private Integer calculateBalls(String overs) {
        String[] arr = overs.split("\\.");
        if (arr.length == 1) {
            return Integer.parseInt(arr[0]) * 6;
        }
        if (arr.length == 2) {
            if (arr[1].length() > 1) {
                throw new IllegalArgumentException("invalid overs: " + overs);
            }
            if (arr[0].equals("")) {
                arr[0] = "0";
            }
            if (arr[1].equals("")) {
                arr[1] = "0";
            }
            return (Integer.parseInt(arr[0]) * 6) + Integer.parseInt(arr[1]);
        }
        throw new IllegalArgumentException("invalid overs: " + overs);
    }

    private Integer findPlayer(String nameToMatch, List<MatchPerf> MatchPerfs) {
        List<MatchPerf> matchPerfs = new ArrayList<>();
        for (MatchPerf perf : MatchPerfs) {
            String name = perf.getPlayerShortName();
            if (name.equals(nameToMatch)) {
                if (!matchPerfs.contains(perf)) {
                    matchPerfs.add(perf);
                }
            }
        }
        for (MatchPerf ms : MatchPerfs) {
            String name = ms.getPlayerShortName().replaceFirst(".*?\\s+", "");
            if (name.equals(nameToMatch)) {
                if (!matchPerfs.contains(ms)) {
                    matchPerfs.add(ms);
                }
            }
        }
        if (matchPerfs.size() == 0) {
            return null;
        }
        if (matchPerfs.size() == 1) {
            return matchPerfs.get(0).getPlayer().getId();
        }
        log.warn("TOO MANY MATCHES FOR: " + nameToMatch + "," + MatchPerfs.get(0).getId());
        return null;
    }
}