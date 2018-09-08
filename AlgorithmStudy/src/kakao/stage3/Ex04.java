package kakao.stage3;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex04 {
    public static void main(String[] args) {
        // 1글자를 2글자로 정규화해 줄 수도 있다
        // #이 붙은 것을 다른 알파벳으로 대체해도 된다......!! (정규식도 필요없음)
        // #이 붙은 애들은 소문자로하자

        String m = "CC#BCC#BCC#BCC#B";
//        String m = "E";
        String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        // 시작한 시각, 끝난 시각, 음악 제목, 악보 정보

        String answer = new Ex04().solution(m, musicinfos);
        System.out.println(answer);
    }

    public String solution(String m, String[] musicinfos) {
        // 내가 들은 멜로디 m 을 정규화
        m = m.replace("C#", "c").replace("D#", "d")
                .replace("F#", "f").replace("A#", "a");

        List<Song> songs = new ArrayList<>();

        // 데이터 파싱해서 노래 목록에 저장
        for (int i = 0; i < musicinfos.length; i++) {
            String[] token = musicinfos[i].split(",");
            String title = token[2];

            LocalTime start = LocalTime.parse(token[0]);
            LocalTime end = LocalTime.parse(token[1]);
            Duration between = Duration.between(start, end);
            int totalRuntimeMin = (int) (between.getSeconds() / 60);

            String notes = token[3].replace("C#", "c").replace("D#", "d")
                    .replace("F#", "f").replace("A#", "a");

            int length = notes.length();

            Song newSong = new Song(start, end, totalRuntimeMin, length, title, notes.toString());
            songs.add(newSong);
        }

        Collections.sort(songs); // 시작 시간 순으로 오름차순 정렬

        StringBuilder realRuntimeNotes = new StringBuilder();
        List<Integer> songIndex = new ArrayList<>();

        for (Song song : songs) {
            if (song.totalRuntimeMin >= song.length) { // 정시재생 혹은 반복재생
                for (int i = 0; i < song.totalRuntimeMin / song.length; i++) {
                    realRuntimeNotes.append(song.notes);
                    for (int j = 0; j < song.length; j++) {
                        songIndex.add(songs.indexOf(song));
                    }
                }
                for (int j = 0; j < song.totalRuntimeMin % song.length; j++) {
                    realRuntimeNotes.append(song.notes.charAt(j));
                    songIndex.add(songs.indexOf(song));
                }
            } else { // 중간에 짤림
                for (int i = 0; i < song.totalRuntimeMin; i++) {
                    realRuntimeNotes.append(song.notes.charAt(i));
                    songIndex.add(songs.indexOf(song));
                }
            }
        }

        // 확인
        System.out.println(m);
        System.out.println(realRuntimeNotes);
        System.out.println(songIndex);

        int[] playedMinBySong = new int[songs.size()]; // 항상 변수이름을 잘 정하자. 0번 인덱스 값은 0번째 노래의 점유율

        Pattern pattern = Pattern.compile(m);
        Matcher matcher = pattern.matcher(realRuntimeNotes.toString());

        boolean anyMatched = false;
        while (matcher.find()) { // 맞는 패턴 모두 찾기 - 패턴에 맞는 후보군 중에 조건에 가장 부합하는 애를 정답으로
            anyMatched = true;
            /*int matchStartIndex = matcher.start();
            for (int i = matchStartIndex; i < m.length(); i++) {
                playedMinBySong[songIndex.get(i)] = playedMinBySong[songIndex.get(i)] + 1;
            }*/
            System.out.println();
        }

        if (!anyMatched) {
            return "(None)";
        }



        String answer = "";

        return answer;


        /*Collections.sort(matchMusic, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String arr[] = o1.split(",");
                int len1 = getMin(arr[0], arr[1]);
                int len2 = getMin(arr[0], arr[1]);
                return len2 - len1;
            }
        });*/

    }

    public static class Song implements Comparable<Song> {
        LocalTime start;
        LocalTime end;
        int totalRuntimeMin;
        int length;
        String title;
        String notes;

        public Song(LocalTime start, LocalTime end, int totalRuntimeMin, int length, String title, String notes) {
            this.start = start;
            this.end = end;
            this.totalRuntimeMin = totalRuntimeMin;
            this.length = length;
            this.title = title;
            this.notes = notes;
        }

        @Override
        public int compareTo(Song o) {
            return this.start.compareTo(o.start);
        }
    }


}
