package hrpex;

public class HrpExMain {
    public static void main(String[] args) {
        System.out.println("hrp ex main");

        PerformanceEvaluation performanceEvaluation = new PerformanceEvaluation(new RawscoreEvaluator());
        performanceEvaluation.sumEvaluation();
        performanceEvaluation.percentageEvaluation();
    }
}
