public class FlashCard {
   private String question;
   private String answer;

    public FlashCard(String  q, String a){
        question = q;
        answer = a;
    }

    public String getQuestion(){
        return  question;
    }

    public String getAnswer(){
        return  answer;
    }
}
