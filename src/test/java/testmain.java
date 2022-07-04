import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;


public class testmain {
    File file1, file2;
    @Rule
    TemporaryFolder folder = new TemporaryFolder();


    @Test
    @DisplayName("проверка создания файла")
    public void makeFileTest() throws IOException {
        folder.create();
        File one = folder.newFile("some.java");
        Main.makeFile(one);
        Assertions.assertTrue(one.exists());

    }

    @Test
    @DisplayName("тест на создание директории")
    public void MkDirectoryTest() throws IOException{
        folder.create();
        File two = folder.newFile("work");
        Main.makeDirectory(two);
        Assertions.assertTrue(two.exists());
    }

    @Test
    @DisplayName("тест записи сохранения игры в файл Dat")
    public void saveGameTest() throws IOException{
        folder.create();
        GameProgress one = new GameProgress(100,1,3,3.45);
        File file = folder.newFile("file.dat");
        Main.makeFile(file);

        Main.saveGame(one,"file.dat");
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(file.canRead());
        Assertions.assertTrue(file.canWrite());
        System.out.println(file.getName() + " существует");
        System.out.println("файл можно прочитать и можно записать");


    }
}
