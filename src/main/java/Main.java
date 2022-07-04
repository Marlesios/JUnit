import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        List<File> game = new ArrayList<>();
        List<File> files = new ArrayList<>();
        // sadasfadfadf

        //Главный директорий
        File Games = new File("E://Games1");
        //под директории в главный
        File src = new File("E://Games1/src");
        File res = new File("E://Games1/res");
        File savegames = new File("E://Games1/savegames");
        File temp = new File("E://Games1/temp");

        //директории в папку src
        File main = new File(src, "main");
        File test = new File(src, "test");

        //Файлы в папку /games/src/main
        File Main_java = new File(main, "Main.java");
        File Java_utils = new File(main, "Utils.java");

        //Файлы в /Games/res
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");

        //temp.txt для сохранений логов
        File temp_txt = new File(temp, "temp.txt");

        game.add(Games);
        game.add(src);
        game.add(res);
        game.add(savegames);
        game.add(temp);

        //добавляем в src
        game.add(main);
        game.add(test);

        //добавляем в папку /games/src/main
        files.add(Main_java);
        files.add(Java_utils);

        //добавляем в /Games/res
        game.add(drawables);
        game.add(vectors);
        game.add(icons);

        //temp.txt
        files.add(temp_txt);

        //цикл для добавления директорий
        for (File file : game) {
            //makeDirectory(file);
            sb.append(makeDirectory(file));
        }
        //цикл для добавлений файлов
        for (File file : files) {
            // makeFile(file);
            sb.append(makeFile(file));
        }

        // добавляем все логи в записную книгу temp.txt
        String one = sb.toString();
        byte[] buffer = one.getBytes();
        try (FileOutputStream writer = new FileOutputStream(temp_txt);
             BufferedOutputStream bos = new BufferedOutputStream(writer)) {
            bos.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String aga = "ну ты понял да";
        try (FileWriter nu = new FileWriter(temp_txt, true)) {
            nu.write(aga);
            nu.write("\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //обьявление GameProgress

        GameProgress first = new GameProgress(100, 1, 25, 100.50);
        GameProgress second = new GameProgress(90, 2, 24, 98.5);
        GameProgress third = new GameProgress(200, 3, 45, 3000.33);
        //используем метод для записи прогресса
        saveGame(first, "E://Games1/savegames/saveFirst.dat");
        saveGame(second, "E://Games1/savegames/saveSecond.dat");
        saveGame(third, "E://Games1/savegames/saveThird.dat");

        //создание List для маршрутов файлов архивации
        List<String> dat = new ArrayList<>();
        dat.add("E://Games1/savegames/saveFirst.dat");
        dat.add("E://Games1/savegames/saveSecond.dat");
        dat.add("E://Games1/savegames/saveThird.dat");

        makeZip("E://Games1/savegames/zip.zip", dat);


    }
    public static String makeDirectory(File file) {
        if (!file.exists()) {
            if (file.mkdir()) {
                return "Файл " + file.getName() + " создан \n";
            } else {
                System.out.println("не получилось");
            }
        }
        return null;
    }

    public static String makeFile(File file) {
        try {
            if (file.createNewFile())
                return "Файл " + file.getName() + " создан \n";

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // метод для создания прогресса для игры
    public static void saveGame(GameProgress pro, String str) {
        try (FileOutputStream fos = new FileOutputStream(str);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pro);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Метод для архивации файлов ZIP
    public static void makeZip(String str,List<String> list)  {
        FileInputStream fis;
        try{ ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(str));

            for (String path: list) {
                File input = new File(path);
                fis = new FileInputStream(input);
                ZipEntry entry = new ZipEntry(input.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                zout.write(buffer);
                zout.flush();
                fis.close();
                input.deleteOnExit();
            }zout.close();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}