package parallelization;// You can add imports


import java.util.concurrent.*;

public class ExamGrader {

    // You can add new methods, inner classes, etc.

    public interface RoundingFunction {
        int roundGrade(double grade);
    }


    public static class ExamQuestion {
        public double pointsObtained;
        public ExamQuestion nextQuestion;

        public ExamQuestion(double points, ExamQuestion next) {
            this.pointsObtained = points;
            this.nextQuestion = next;
        }

        public ExamQuestion getNextQuestion() {
            return nextQuestion;
        }
        public double getPointsObtained(){
            return pointsObtained;

        }
    }

    /**
     *  Write a method calculateExamGrade that calculates the grade
     *  obtained for an exam. The grade is the sum of the points
     *  obtained in all questions. The questions are provided in a
     *  linked list (class ExamQuestion).
     *
     *  However, the points are real numbers, while the exam grade
     *  is a natural number. To round the grade, the caller of this
     *  method provides a rounding function that you have to use
     *  to obtain the final result.
     *
     *  Look at the test "testWithTwoQuestions" to see how users will
     *  call this method.
     *
     *  You can assume that all points are positive numbers and that
     *  the list of questions is not empty.
     */
    public static int calculateExamGrade(ExamQuestion questions, RoundingFunction roundingFunction) {
        ExamQuestion currentquestion = questions;

        double sum = 0;

        while(currentquestion != null){
            sum += currentquestion.pointsObtained;
            System.out.println(sum);
            
            currentquestion = currentquestion.getNextQuestion();
        }
         return roundingFunction.roundGrade(sum);
    }

    static class calcuateExamGradeCallable implements Callable<Integer> {
        ExamQuestion questions;
        RoundingFunction roundingFunction;



        calcuateExamGradeCallable(ExamQuestion q, RoundingFunction r){
            questions = q;
            roundingFunction = r;

        }
        @Override
        public Integer call() throws Exception {
            return calculateExamGrade(questions,roundingFunction);
        }

    }


    /**
     * Write a method gradeExams that calculates the grades of two exams
     * using two threads to accelerate the grading (one exam graded per thread).
     * The method must return the two grades in an int array.
     * Like for the method calculateExamGrade, the caller of this method
     * provides a rounding function.
     *
     * Look at the test "testTwoShortExams" to see how users will
     * call this method.
     *
     * You MUST use threads (or a threadpool).
     * Catch (and ignore) all exceptions.
     */
    public static int[] gradeExams(ExamQuestion exam1, ExamQuestion exam2, RoundingFunction roundingFunction) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> futur1 = executor.submit(new calcuateExamGradeCallable(exam1,roundingFunction));
        Future<Integer> futur2 = executor.submit(new calcuateExamGradeCallable(exam2,roundingFunction));

        try {
            int note1 = futur1.get();
            int note2 = futur2.get();
            int[] array = {note1,note2};
            return array;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }


    }

