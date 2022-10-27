import greenfoot.*;

public class GameOver extends FinalJuego
{
    public GameOver()
    {
        super(1000, 600, 1); 
        Greenfoot.playSound("GameOverSound.mp3");
        crearBotones(520);
    }
}
