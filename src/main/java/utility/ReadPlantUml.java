package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.omg.CORBA.IRObject;

public class ReadPlantUml {

    public List<String> getObjects(String filePath) {
        List<String> results = new ArrayList<String>();
        try {
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
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
                // ファイルを閉じる
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
        try {
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile("->");
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        results.add(line);
                    }
                }
                // ファイルを閉じる
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
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
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
                // ファイルを閉じる
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

    public String readFile(String filePath, String searchNames) {
        String results = null;
        try {
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                int count = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Pattern p = Pattern.compile(searchNames);
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        results = line;
                        count = count + 1;
                        break;
                    }
                }
                // ファイルを閉じる
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

    public String searchJsp(String name, String filePath, String method) {
        // 返り値の初期化(get or postを呼んでいる行)
        String judge = null;
        String path = "src/main/webapp/WEB-INF/" + filePath;
        File dir = new File(path);

        // listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                // ファイルの場合
                if (list[i].isFile()) {
                    System.out.println(list[i].toString());
                    String[] file = list[i].toString().split("/", 0);
                    if (name.equals(file[file.length - 1])) {
                        String readPath = path + "/" + file[file.length - 1];
                        System.out.println(readPath);
                        judge = writeJsp(readPath, "docs/match/sequence.txt", method);
                    }
                }
                // ディレクトリの場合
                else if (list[i].isDirectory()) {
                    System.out.println(list[i].toString());
                }
            }
        } else {
            System.out.println("null");
        }
        return judge;
    }

    // get or postの判定をしてその中の部分をtextPathに上書きそのどちらが存在するのか確認
    public String writeJsp(String filePath, String textPath, String searchName) {
        // get or postが存在するのかを判定する
        String judge = null;
        // アノテーションを取得する
        String anotation = null;
        try {
            FileWriter textFile = new FileWriter(textPath);
            PrintWriter pw = new PrintWriter(new BufferedWriter(textFile));
            pw.print("");
            pw.close();

            FileWriter addFile = new FileWriter(textPath, true);
            PrintWriter addPw = new PrintWriter(new BufferedWriter(addFile));
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                // 条件にあう行を画面出力する
                String line;
                if (searchName.contains("post")) {
                    while ((line = bufferedReader.readLine()) != null) {
                        addPw.println(line);
                        if (line.contains("method=\"post\"")) {
                            judge = "OK";
                            anotation = line;
                        }
                    }
                } else if (searchName.contains("get")) {
                    while ((line = bufferedReader.readLine()) != null) {
                        addPw.println(line);
                        if (line.contains("method=\"gett\"")) {
                            judge = "OK";
                        }
                        // else if で a hrefタグで送るものを判定したい
                    }
                }
                if (judge == null) {
                    throw new IllegalArgumentException("定められたViewにget or postが記述されていません");
                }
                addPw.close();
                // ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return anotation;
    }

    public String[] searchJavaonJsp(String name, String filePath, String method, String checkAnotation) {
        // 返り値の初期化(get or postを呼んでいる行)
        String[] judge = new String[2];
        String path = "src/main/java/" + filePath;
        File dir = new File(path);
        String javaName = name + ".java";

        // listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                // ファイルの場合
                if (list[i].isFile()) {
                    System.out.println(list[i].toString());
                    String[] file = list[i].toString().split("/", 0);
                    if (javaName.equals(file[file.length - 1])) {
                        String readPath = path + "/" + file[file.length - 1];
                        System.out.println(readPath);
                        judge = writeJavaonJsp(readPath, "docs/match/sequence.txt", method, checkAnotation);
                    }
                }
                // ディレクトリの場合
                else if (list[i].isDirectory()) {
                    System.out.println(list[i].toString());
                }
            }
        } else {
            System.out.println("null");
        }
        return judge;
    }

    public String[] writeJavaonJsp(String filePath, String textPath, String searchName, String checkAnotation) {
        // anotationが存在するのかを判定する[0]
        // メソッドが存在するのかを判断する[1]
        String[] judge = new String[2];
        // importのい部分を取り除いてClassの部分のみを取得する(public classのあとを取得する)
        // 0の時はまだpublic classを見つけていない / 1の時はpublic classを見つけている → txtに書き込む
        int getinClass = 0;
        // txtに書き込むjavaのアノテーション部分とマッチさせるための check
        String check = "@WebServlet(\"/";
        try {
            FileWriter textFile = new FileWriter(textPath);
            PrintWriter pw = new PrintWriter(new BufferedWriter(textFile));
            pw.print("");
            pw.close();

            FileWriter addFile = new FileWriter(textPath, true);
            PrintWriter addPw = new PrintWriter(new BufferedWriter(addFile));
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                // 条件にあう行を画面出力する
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    addPw.println(line);
                    getinClass = 1;
                    if (line.contains(check)) { // jspから呼ばれているのでその呼び出し先のアノテーションがあっているかの確認 → ある時は judgeにOKが代入される
                        String ano = line.substring(check.length(), line.length() - 2);
                        if (line.contains(ano)) {
                            judge[0] = "アノテーションOK";
                        }
                    } else if (line.contains(searchName)) { // methodがあるか無いかの確認
                        judge[1] = "Methodあり";
                    }
                }
                addPw.close();
                // ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(judge[0]);
        System.out.println(judge[1]);
        return judge;
    }

    public String searchJava(String name, String filePath, String method) {

        // 返り値 : メソッド
        String judge = "null";
        String path = "src/main/java/" + filePath;
        File dir = new File(path);
        String javaName = name + ".java";

        // listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                // ファイルの場合
                if (list[i].isFile()) {
                    System.out.println("ファイル : " + list[i].toString());
                    String[] file = list[i].toString().split("/", 0);
                    if (javaName.equals(file[file.length - 1])) {
                        String readPath = path + "/" + file[file.length - 1];
                        writeJava(readPath, "docs/match/sequence.txt", method);
                    }
                }
                // ディレクトリの場合
                else if (list[i].isDirectory()) {
                    System.out.println("ディレクトリです : " + list[i].toString());
                }
            }
        } else {
            System.out.println("null");
        }
        return judge;
    }

    public String writeJava(String filePath, String textPath, String searchName) {
        // メソッドが存在するかを判定する
        String methodJudge = null;
        // importのい部分を取り除いてClassの部分のみを取得する(public classのあとを取得する)
        // 0の時はまだpublic classを見つけていない / 1の時はpublic classを見つけている → txtに書き込む
        int getinClass = 0;

        try {
            FileWriter textFile = new FileWriter(textPath);
            PrintWriter pw = new PrintWriter(new BufferedWriter(textFile));
            pw.print("");
            pw.close();

            FileWriter addFile = new FileWriter(textPath, true);
            PrintWriter addPw = new PrintWriter(new BufferedWriter(addFile));
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                // 条件にあう行を画面出力する
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("public class") || getinClass == 1) { // class 以下を書き込む条件式
                        addPw.println(line);
                        getinClass = 1;
                        if (line.contains(searchName)) { // methodがあるか無いかの確認
                            methodJudge = "Methodあり";
                        }
                    }
                }
                if (searchName.equals("メソッド_引数なし")) {
                    methodJudge = "引数なし";
                } else if (searchName.equals("DBアクセス")) {
                    methodJudge = "DBアクセス";
                }
                addPw.close();
                // ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("指定のクラスに特定のメソッド：" + methodJudge);
        return methodJudge;
    }

    public String searchDaoJava(String name, String filePath, String method) {

        // 返り値 : メソッド
        String judge = "null";
        String path = "src/main/java/" + filePath;
        File dir = new File(path);
        String javaName = name + ".java";

        // listFilesメソッドを使用して一覧を取得する
        File[] list = dir.listFiles();

        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                // ファイルの場合
                if (list[i].isFile()) {
                    System.out.println("ファイル : " + list[i].toString());
                    String[] file = list[i].toString().split("/", 0);
                    if (javaName.equals(file[file.length - 1])) {
                        String readPath = path + "/" + file[file.length - 1];
                        writeJava(readPath, "docs/match/sequence.txt", method);
                    }
                }
                // ディレクトリの場合
                else if (list[i].isDirectory()) {
                    System.out.println("ディレクトリです : " + list[i].toString());
                }
            }
        } else {
            System.out.println("null");
        }
        return judge;
    }

    public String writeDaoJava(String filePath, String textPath, String searchName) {
        // メソッドが存在するかを判定する
        String methodJudge = null;
        // importのい部分を取り除いてClassの部分のみを取得する(public classのあとを取得する)
        // 0の時はまだpublic classを見つけていない / 1の時はpublic classを見つけている → txtに書き込む
        int getinClass = 0;

        try {
            FileWriter textFile = new FileWriter(textPath);
            PrintWriter pw = new PrintWriter(new BufferedWriter(textFile));
            pw.print("");
            pw.close();

            FileWriter addFile = new FileWriter(textPath, true);
            PrintWriter addPw = new PrintWriter(new BufferedWriter(addFile));
            // Fileクラスに読み込むファイルを指定する
            File file = new File(filePath);
            // ファイルが存在するか確認
            if (file.exists()) {
                // ファイルの読み込み
                FileReader filereader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(filereader);
                // 条件にあう行を画面出力する
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.contains("public class") || getinClass == 1) { // class 以下を書き込む条件式
                        addPw.println(line);
                        getinClass = 1;
                        if (line.contains(searchName)) { // methodがあるか無いかの確認
                            methodJudge = "Methodあり";
                        }
                    }
                }
                if (searchName.equals("メソッド_引数なし")) {
                    methodJudge = "引数なし";
                } else if (searchName.equals("DBアクセス")) {
                    methodJudge = "DBアクセス";
                }
                addPw.close();
                // ファイルを閉じる
                bufferedReader.close();
                filereader.close();
            } else {
                System.out.print("ファイルは存在しません");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("指定のクラスに特定のメソッド：" + methodJudge);
        return methodJudge;
    }
}