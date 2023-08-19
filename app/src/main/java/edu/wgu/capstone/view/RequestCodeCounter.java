package edu.wgu.capstone.view;

public class RequestCodeCounter {

    private static RequestCodeCounter instance = null;
    private int requestCode = 0;

    private RequestCodeCounter() {}

    public static RequestCodeCounter getInstance() {
        if(instance == null) {
            instance = new RequestCodeCounter();
        }
        return instance;
    }

    public int getRequestCode() {
        return requestCode++;
    }
}
