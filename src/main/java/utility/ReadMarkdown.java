package utility;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadMarkdown {

    //ファイルと文字を引数にその文字がファイルに存在しているのか
    public String readFile(String filePath, String[] searchNames) {
        String results = null;
        try {
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                int count = 0;
                for (int i = 0; i < searchNames.length; i++) {
                    System.out.println(searchNames[i]);
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        Pattern p = Pattern.compile("|" + searchNames[i] + "|");
                        Matcher m = p.matcher(line);
                        if (m.find()) {
                            count = count + 1;
                            break;
                        }
                    }

                }
                if (count == searchNames.length) {
                    results = "complete";
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

    //#の数(###なら3)だけのところを取ってくる(返ってくるのは)
    public String readNumber(String filePath, int number) {
        String num = "^";
        for (int i = 0; i < number; i++) {
            String plus = "#";
            num = num + plus;
        }
        String results = null;
        try {
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                //条件にあう行を画面出力する
                int count = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile(num + " ");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        count = count + 1;
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

    //table名を引数にfilePathに存在するtable名の部分の表をmatch.txtに上書きする
    public void readTable(String filePath, String textPath, String searchName) {
        String search = "^### " + searchName;
        String nextSearch = "^### ";
        try {
            FileWriter textFile = new FileWriter(textPath);
            PrintWriter pw = new PrintWriter(new BufferedWriter(textFile));
            pw.print("");
            pw.close();

            FileWriter addFile = new FileWriter(textPath, true);
            PrintWriter addPw = new PrintWriter(new BufferedWriter(addFile));
            //Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            //ファイルが存在するか確認
            if (file.exists()) {
                //ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                //条件にあう行を画面出力する
                int count = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile(search + "  ");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        count = 1;
                        continue;
                    }
                    if (count == 1) {
                        Pattern nextP = Pattern.compile(nextSearch);
                        Matcher nextM = nextP.matcher(line);
                        if (nextM.find()) {
                            break;
                        }
                        addPw.println(line);
                    }
                }
                addPw.close();
                //ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
