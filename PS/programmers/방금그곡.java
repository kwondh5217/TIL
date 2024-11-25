import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        PriorityQueue<Song> pq = new PriorityQueue<>((a, b) -> {
            if(a.time == b.time) {
                return a.idx - b.idx;
            }
            return b.time - a.time;
        });
        
        for(int i = 0; i < musicinfos.length; i++) {
            String[] musics = musicinfos[i].split(",");
            String[] time = musics[0].split(":");
            int start = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]);
            time = musics[1].split(":");
            int end = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]);
            int minute = end - start;
            StringBuilder sb = new StringBuilder();
            musics[3] = convert(musics[3]);
            if(musics[3].length() < minute) {
                int idx = 0;
                for(int j = 0; j < minute; j++) {
                    sb.append(musics[3].charAt(idx));
                    idx = (idx + 1) % musics[3].length();
                }
            } else {
                sb.append(musics[3].substring(0, minute));
            }
            String newMusic = sb.toString();
            if(newMusic.contains(convert(m))) {
                pq.offer(new Song(i, minute, musics[2]));
            }
        }
        
        
        String answer = pq.isEmpty() ? "(None)" : pq.poll().title;
        return answer;
    }
    
    private String convert(String str) {
        str = str.replaceAll("C#", "c");
        str = str.replaceAll("D#", "d");
        str = str.replaceAll("F#", "f");
        str = str.replaceAll("G#", "g");
        str = str.replaceAll("A#", "a");
        str = str.replaceAll("B#", "b");
        return str;
    }
    
    static class Song {
        int idx;
        int time;
        String title;
        
        public Song (int idx, int time, String title) {
            this.idx = idx;
            this.time = time;
            this.title = title;
        }
    }
}
