package sequence;

import org.junit.Test;
import utility.ReadPlantUml;

import java.util.ArrayList;
import java.util.List;

public class SequenceTest {
    @Test
    public void ユーザ作成のシーケンス図の一貫性() {
        // ファイルの読み込み
        // 返り値にオブジェクト, メソッド名, 引数(, DB名, テーブル名)を取得する(引数は確認をしたいシーケンス図のパス)
        // インスタンス化
        ReadPlantUml readPlantUml = new ReadPlantUml();

        List<String> previous = new ArrayList<String>();
        List<String> after = new ArrayList<String>();

        // view -> DBまでを取得
        System.out.println();
        List<String> previousObjects = readPlantUml.getObjects("docs/sequence/user/create.pu");
        for (String object : previousObjects) {
            String[] splitsObjects = object.split(" +");
            for (int i = 0; i < splitsObjects.length; i++) {
                if (splitsObjects[i].equals("as")) {
                    previous.add(splitsObjects[i - 1]);
                    String obj = splitsObjects[i + 1];
                    List<String> methods = readPlantUml.getMethods("docs/sequence/user/create.pu");
                    for (String method : methods) {
                        String[] splitsMethods = method.split(" +");
                        if (splitsMethods[0].equals(obj) && !(splitsMethods[0].equals("dao"))) {
                            String[] twoSplitsMethods = method.split(":");
                            previous.add(twoSplitsMethods[1]);
                            break;
                        } else if (splitsMethods[0].equals("dao")) {
                            String dbNameLine = readPlantUml.getDbName("docs/sequence/user/create.pu");
                            String[] dbNames = dbNameLine.split(" +");
                            String dbName = dbNames[1];
                            previous.add(dbName);
                        }
                    }
                }
            }
        }

        // DB -> Viewまでを取得
        List<String> afterObjects = readPlantUml.getObjects("docs/sequence/user/create.pu");
        for (String object : afterObjects) {
            String[] splitsObjects = object.split(" +");
            for (int i = 0; i < splitsObjects.length; i++) {
                if (splitsObjects[i].equals("as")) {
                    after.add(0, splitsObjects[i - 1]);
                    String obj = splitsObjects[i + 1];
                    List<String> methods = readPlantUml.getMethods("docs/sequence/user/create.pu");
                    for (String method : methods) {
                        String[] splitsMethods = method.split(" +");
                        if (splitsMethods[splitsMethods.length - 2].equals(obj)
                                && !(splitsMethods[splitsMethods.length - 2].equals("dao"))) {
                            String[] twoSplitsMethods = method.split(":");
                            break;
                        } else if (splitsMethods[splitsMethods.length - 2].equals("dao")) {
                            String dbName = readPlantUml.getDbName("docs/sequence/user/create.pu");
                        }
                    }
                }
            }
        }

        previous.addAll(after);
        System.out.println(previous.size());
        String[] results = new String[previous.size()];
        int i = 0;
        for (String pre : previous) {
            results[i] = pre;
            i = i + 1;
        }
        for (i = 0; i < results.length; i = i + 2) {
            if (i != 0) {
                System.out.println(results[i-1]);
                System.out.println(results[i]);
                System.out.println(results[i+1]);
                String kakunin = readPlantUml.readFile("src/main/java/controller/users/Create.java",
                        results[i + 1] + results[i]);
                System.out.println(kakunin);
            }
        }
    }
}
