import java.io.IOException;

public class SomeException extends IOException{ 

    public SomeException() {
        super ("Please, enter name of some existing directory.");
    }    
}
