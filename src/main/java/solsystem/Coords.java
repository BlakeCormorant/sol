package solsystem;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Coords<T>{
    public T x;
    public T y;
/*
    public Coords(){
        this.x = 0;
        this.y = 0;
    }
*/
	// I would like to get the following one working
    public Coords(T x, T y){
        this.x = x;
        this.y = y;
    }

    public void Set(T x, T y){
        this.x = x;
        this.y = y;
    }
/* // Can't seem to get this to work
    public void Set(Coords c){
        this.x = c.x;
        this.y = c.y;
    }
*/
}
