package game;

import frame.Frame;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {

    private ArrayList<Frame> throwsList = new ArrayList<>();
    private List<Integer> scores = new ArrayList<>();

    private static String SPACE = "     ";

    public void printScores() {
        printCollection("Score", scores.stream());
    }

    public void printPinFalls() {
        printCollection("PinFalls", throwsList.stream());
    }

    public static void printFrames() {
        printCollection("Frame", IntStream.range(1, 11).boxed());
    }

    public static void printCollection(String title, Stream stream) {
        System.out.print(title + SPACE);
        stream.forEach(element -> System.out.print(element + SPACE));
        System.out.println();
    }
}
