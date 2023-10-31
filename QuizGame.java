import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String question;
    private List<String> options;
    private int answer;

    public Question(String question, List<String> options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getquestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getanswer() {
        return answer;
    }
}

public class QuizGame {
    private static List<Question> questions = new ArrayList<>();
    private static int curIndex = 0;
    private static int score = 0;
    private static Timer timer;

    public static void main(String[] args) {
        initQuestions();

        Scanner scanner = new Scanner(System.in);
        timer = new Timer();

        System.out.println("Welcome to the Quiz Game!");

        askNextQ(scanner);

        scanner.close();
    }

    private static void initQuestions() {
        questions.add(new Question("What is the capital of France?",
                List.of("A. London", "B. Berlin", "C. Paris", "D. Madrid"), 2));
    }

    private static void askNextQ(Scanner scanner) {
        if (curIndex < questions.size()) {
            Question curQuestions = questions.get(curIndex);
            System.out.println("\nQuestion " + (curIndex + 1) + ": " + curQuestions.getquestion());
            List<String> options = curQuestions.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            int timeLimitInSeconds = 10;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    Result();
                    askNextQ(scanner);
                }
            }, timeLimitInSeconds * 1000);

            System.out.print("Your answer (enter A, B, C, or D): ");
            String userAnswer = scanner.next().toUpperCase();
            int userChoice = userAnswer.charAt(0) - 'A';

            if (userChoice == curQuestions.getanswer()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }

            curIndex++;
            timer.cancel();
            askNextQ(scanner);
        } else {
            Result();
        }
    }

    private static void Result() {
        System.out.println("\nQuiz ended!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.size() - score));
        System.exit(0);
    }
}