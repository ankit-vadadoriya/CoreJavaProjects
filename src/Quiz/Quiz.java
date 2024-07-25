package Quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    public static class Question {
        private String questionText;
        private String[] options;
        private int correctAnswer;

        public Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public boolean isCorrect(int answer) {
            return answer == correctAnswer;
        }
    }

    private List<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            if (question.isCorrect(answer - 1)) {
                score++;
            }
        }

        System.out.println("You scored " + score + " out of " + questions.size());
        scanner.close();

        float result = (float) (score)*100 / questions.size();
        System.out.println("Result is : " + result + "%");
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Add sample questions
        quiz.addQuestion(new Question("What is the capital of France?", new String[]{"Paris", "London", "Berlin", "Madrid"}, 0));
        quiz.addQuestion(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Jupiter", "Venus"}, 1));
        quiz.addQuestion(new Question("Who wrote 'Romeo and Juliet'?", new String[]{"William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"}, 0));
        quiz.addQuestion(new Question("What is the largest lake in the world?", new String[]{"Caspian Sea", "Baikal", "Lake Superior", "Ontario"}, 1));
        quiz.addQuestion(new Question("Who wrote the novel “War and Peace”?", new String[]{"Anton Chekhov", "Fyodor Dostoevsky", "Leo Tolstoy", "Ivan Turgenev"}, 2));
        quiz.addQuestion(new Question("Which river is the longest in the world?", new String[]{"Amazon", "Mississippi", "Nile", "Yangtze"}, 2));
        quiz.addQuestion(new Question("What chemical element is designated as “Hg”?", new String[]{"Silver", "Tin", "Copper", "Mercury"}, 3));
        quiz.addQuestion(new Question("Which is the largest island?", new String[]{"New Guinea", "Andaman Nicobar", "Greenland", "Hawaii"}, 2));

        quiz.start();
    }
}
