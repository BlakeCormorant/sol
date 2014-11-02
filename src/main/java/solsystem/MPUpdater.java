package solsystem;

/**
 * Created by BlakeCormorant on 22/10/14.
 */
public interface MPUpdater {
    public void Connect();
    public void Update(double day, Game g);
    public void Disconnect();
}
