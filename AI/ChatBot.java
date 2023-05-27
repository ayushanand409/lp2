import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ChatBot {
    private static final int MAX_RESP = 3;

    private static List<String> findMatch(String input) {
        List<String> result = new ArrayList<>();
        for (Record record : knowledgeBase) {
            if (record.input.equals(input)) {
                copy(record.responses, result);
                return result;
            }
        }
        return result;
    }

    private static void copy(String[] array, List<String> list) {
        for (int i = 0; i < MAX_RESP; ++i) {
            list.add(array[i]);
        }
    }

    private static class Record {
        String input;
        String[] responses;

        Record(String input, String[] responses) {
            this.input = input;
            this.responses = responses;
        }
    }

    private static Record[] knowledgeBase = {
        new Record("WHAT IS YOUR NAME",
            new String[] {
                "MY NAME IS CHATTERBOT2.",
                "YOU CAN CALL ME CHATTERBOT2.",
                "WHY DO YOU WANT TO KNOW MY NAME?"
            }
        ),
        new Record("HI",
            new String[] {
                "HI THERE!",
                "HOW ARE YOU?",
                "HI!"
            }
        ),
        new Record("HOW ARE YOU",
            new String[] {
                "I'M DOING FINE!",
                "I'M DOING WELL AND YOU?",
                "WHY DO YOU WANT TO KNOW HOW AM I DOING?"
            }
        ),
        new Record("WHO ARE YOU",
            new String[] {
                "I'M AN A.I PROGRAM.",
                "I THINK THAT YOU KNOW WHO I'M.",
                "WHY ARE YOU ASKING?"
            }
        ),
        new Record("ARE YOU INTELLIGENT",
            new String[] {
                "YES, OF COURSE.",
                "WHAT DO YOU THINK?",
                "ACTUALLY, I'M VERY INTELLIGENT!"
            }
        ),
        new Record("ARE YOU REAL",
            new String[] {
                "DOES THAT QUESTION REALLY MATTER TO YOU?",
                "WHAT DO YOU MEAN BY THAT?",
                "I'M AS REAL AS I CAN BE."
            }
        )
    };

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            String input = scanner.nextLine();

            List<String> responses = findMatch(input);

            if (input.equals("BYE")) {
                System.out.println("IT WAS NICE TALKING TO YOU USER, SEE YOU NEXT TIME!");
                break;
            } else if (responses.isEmpty()) {
                System.out.println("I'M NOT SURE IF I UNDERSTAND WHAT YOU ARE TALKING ABOUT.");
            } else {
                int nSelection = random.nextInt(MAX_RESP);
                String response = responses.get(nSelection);
                System.out.println(response);
            }
        }
    }
}
