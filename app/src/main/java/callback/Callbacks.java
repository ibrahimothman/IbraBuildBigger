package callback;

/**
 * Created by dell on 4/17/2018.
 */

public class Callbacks {
    public interface EndpointAsyncTaskCallback {
        public void onEndpointAsyncTaskDone(String result);
    }

    public interface InterstitialAdCallback {
        public void onInterstitialClosed();
    }
    public interface JokeButtonCallback {
        public void onJokeButtonClicked();
    }

}
