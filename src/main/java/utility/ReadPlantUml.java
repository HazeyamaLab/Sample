package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadPlantUml {

    public List<String> getObjects(String filePath) {

        List<String> results = new ArrayList<String>();
        try {
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile("participant");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                       results.add(line);
                    }
                }
                //ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<String> getMethods(String filePath) {

        List<String> results = new ArrayList<String>();
        ;
        try {
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile("->");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        Pattern p1 = Pattern.compile(":");
                        Matcher m1 = p1.matcher(line);
                        if (m1.find()) {
                            results.add(line);
                        }
                    }
                }
                //ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    public String getDbName(String filePath) {

        String result = null;
        try {
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile("database");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        result = line;
                    }
                }
                //ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}




