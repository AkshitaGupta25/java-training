package SpringAssignment.Ques4;


public class LazyLoading {
    private static LazyLoading instance = null;
    private LazyLoading(){

    }

    public static LazyLoading getInstance(String url, String userName, String password){
        if(instance == null)
            instance = new LazyLoading();
        return instance;
    }

}
