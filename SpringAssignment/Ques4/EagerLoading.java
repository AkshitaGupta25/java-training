package SpringAssignment.Ques4;


public class EagerLoading {
    private static EagerLoading instance = new EagerLoading();
    private EagerLoading(){

    }

    public static EagerLoading getInstance(String url, String userName, String password){
        return instance;
    }

}
