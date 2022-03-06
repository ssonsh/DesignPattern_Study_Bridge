package hrpex;

public abstract class Evaluation {
    private Evaluator evaluator;

    public Evaluation(Evaluator evaluator){
        this.evaluator = evaluator;
    }

    public void sumEvaluation(){
        System.out.println("[Evaluation] Call sumEvaluation");
        this.evaluator.sum();
    }

    public void percentageEvaluation(){
        System.out.println("[Evaluation] Call percentageEvaluation");
        this.evaluator.percentage();
    }
}
