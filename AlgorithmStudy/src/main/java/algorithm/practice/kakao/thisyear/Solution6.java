package algorithm.practice.kakao.thisyear;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution6 {
    public static void main(String[] args) {
        /*String[] pages = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
        };*/
        String[] pages = {
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
        };
        int a = new Solution6().solution("Muzi", pages);
        System.out.println(a);
    }

    public int solution(String word, String[] pages) {
        // 기본점수, 외부링크수 구하기
        List<Page> list = new ArrayList<>();
        Map<String, Page> pageMap = new HashMap<>();

        String pt = "[^A-Z]"+word.toUpperCase()+"[^A-Z]";

        Pattern wordP = Pattern.compile(pt);
        Pattern urlP1 = Pattern.compile("CONTENT=");
        Pattern urlP2 = Pattern.compile("</HEAD>");
        Matcher m;
        for (int i = 0; i < pages.length; i++) {
            m = wordP.matcher(pages[i].toUpperCase());
            int basic = 0;
            while (m.find()) {
                basic++;
            }

            int s=0;
            int e=0;

            m = urlP1.matcher(pages[i].toUpperCase());
            if (m.find()) {
                s = m.end();
            }

            m = urlP2.matcher(pages[i].toUpperCase());
            if (m.find()) {
                e = m.start();
            }

            String[] token = pages[i].substring(s, e).replace("/>","").split("\"");
            String url = token[1];
            Page page = new Page(i, url, basic);

            Pattern linkP1 = Pattern.compile("HREF=");
            Pattern linkP2 = Pattern.compile("</A>");
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();
            m = linkP1.matcher(pages[i].toUpperCase());
            while (m.find()) {
                x.add(m.end());
            }
            m = linkP2.matcher(pages[i].toUpperCase());
            while (m.find()) {
                y.add(m.start());
            }
            for (int j = 0; j < x.size(); j++) {
                String[] str = pages[i].substring(x.get(j), y.get(j)).replace(">", "").split("\"");
                page.addOuterLink(str[1]);
            }
            pageMap.put(url, page);
            list.add(page);
        }

        if (list.size() == 1) {
            return 0;
        }

        // linkScore 계산
        for (int i = 0; i < list.size(); i++) {
            Page a = list.get(i);
            List<String> followingUrl = a.outerLink;
            for (String url : followingUrl) {
                Page b = pageMap.get(url);
                if (b == null) {
                    continue;
                }
                b.linkScore += (double) a.basicScore / followingUrl.size();
            }
        }

        Comparator<Page> c = (p1, p2)->{
            double s1 = p1.getMatchingScore();
            double s2 = p2.getMatchingScore();
            return s1 == s2 ? 0 : (s1 > s2 ? -1 : 1);
        };

        Collections.sort(list, c.thenComparing(Page::getIdx));

        int answer = list.get(0).idx;
        return answer;
    }

    public static class Page {
        int idx;
        String url;
        int basicScore;
        List<String> outerLink = new ArrayList<>();
        double linkScore = 0;

        public Page(int idx, String url, int basicScore) {
            this.idx = idx;
            this.url = url;
            this.basicScore = basicScore;
        }

        public void addOuterLink(String oUrl) { // 내가 링크건 애들
            this.outerLink.add(oUrl);
        }

        public double getMatchingScore() {
            return basicScore + linkScore;
        }

        public int getIdx() {
            return idx;
        }
    }
}
