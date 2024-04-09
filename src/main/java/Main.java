import animals.exceptions.AnimalNotSupported;
import tables.AnimalTable;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws AnimalNotSupported, SQLException {
        new Console().consoleCall();
    }
}
