package activeobject;

public class Servant implements ActiveObject {
    public void search(String word, Display display) {
        System.out.print("search(" + word + ")");
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("found.");
        String url = "http://somewhere/" + word + ".html"; // dummy URL
        
        display.display("word = " + word + ", URL = " + url);
    }
}
