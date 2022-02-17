package homework;

import java.util.*;

public class SongHw {
    public static void main(String[] args) {
        Song song1 = new Song("song1",5, Arrays.asList("Rock","Pop"));
        Song song2 = new Song("song2",2, Arrays.asList("Rock"));
        Song song3 = new Song("song3",7, Arrays.asList("Pop"));

        List<Song> listOfSongs = Arrays.asList(song1,song2,song3);

        List<String> genres = Arrays.asList("Pop","Rock");

        Map<String,String> songCount = new TreeMap<>();

        int max = 0;
        String name = "";
        for (int i = 0 ; i < genres.size() ; i++){
            max = 0;
            for (Song o : listOfSongs){
                if(o.genre.contains(genres.get(i)) == true){
                    if(o.playCount > max){
                        max = o.playCount;
                        name = o.title;
                    }
                }
            }
            String s = name + ", " + max;
            songCount.put(genres.get(i),s);
        }
        System.out.println(songCount);



    }
}
class Song {
    String title;
    int playCount;
    List<String> genre;

    public Song(String title, int playCount, List<String> genre) {
        this.title = title;
        this.playCount = playCount;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", playCount=" + playCount +
                ", genre=" + genre +
                '}';
    }
}
/*
Suppose you are given a List of songs.
genre = pop, rock,
write a function that returns for each genre the song that is played
the maximum number of times.
song = {"title-1", 5 , ["pop","rock"]}, {"title-2",6,["pop"]}, {"title-3",4,["rock"]}
output:
pop -> title-2,6
rock -> title-1, 5
 */
