package solsystem;

/**
 * Created by BlakeCormorant on 22/10/14.
 */
public interface MPUpdater {
    public void Connect();
    public void Update(double day, Game g);
    public double GetGameDayUpdate(double day);
    public void Disconnect();               // TODO Pretty sure I should be calling this at some point.
}
