package sequence;

import org.junit.Test;

import service.UserService;
import utility.ReadPlantUml;

import java.util.ArrayList;
import java.util.List;

public class SequenceTest extends UserService {
    @Test
    public void ユーザ作成のシーケンス図の一貫性() {
        // ファイルの読み込み
        // 返り値にオブジェクト, メソッド名, 引数(, DB名, テーブル名)を取得する(引数は確認をしたいシーケンス図のパス)
        // インスタンス化
        ReadPlantUml readPlantUml = new ReadPlantUml();

        // →方向のオブジェクト＆メソッド
        List<String> flow = new ArrayList<String>();
        String db = null;
        String table = null;
        // view -> dao(db名も取得している)までを取得
        System.out.println();
        List<String> methods = readPlantUml.getMethods("docs/sequence/user/create.pu"); // "->"を含むものをline取得する

        for (String method : methods) {
            String[] splitsMethods = method.split(" +"); // 空白で区切る
            List<String> previousObjects = readPlantUml.getObjects("docs/sequence/user/create.pu"); // objectのlineを取得
            loop: for (String object : previousObjects) {
                String[] splitsObjects = object.split(" +"); // 空白で区切る
                for (int i = 0; i < splitsObjects.length; i++) {
                    if (splitsObjects[i].equals("as")) {// asの前を取得する条件式
                        if (splitsMethods[0].equals("user") || splitsMethods[2].equals("user")) {
                            break loop;
                        } else {
                            if (splitsMethods[0].equals(splitsObjects[i + 1]) && !(splitsMethods[0].equals("dao"))) { // 先頭にオブジェクトがあるもの&&daoでは無い
                                if (method.contains(":")){
                                    String[] twoSplitsMethods = method.split(":"); // ":"で2つに分ける
                                    flow.add(splitsObjects[i - 1]); // asの前のオブジェクトをリストに追加
                                    flow.add("->"); // 右矢印方向を格納
                                    flow.add(twoSplitsMethods[1]); // : の後ろのメソッドを取得してリストに格納
                                } else {
                                    flow.add(splitsObjects[i - 1]); // asの前のオブジェクトをリストに追加
                                    flow.add("->"); // 左矢印方向を格納
                                    flow.add("メソッド_引数なし");
                                    break loop;
                                }
                                break loop; // 次のラインを比較する
                            } else if (splitsMethods[0].equals("dao")
                                    && splitsMethods[0].equals(splitsObjects[i + 1])) { // 先頭がdaoの場合(次が絶対DB)これはasの後ろに何を回おたかで変更する
                                flow.add(splitsObjects[i - 1]); // asの前のオブジェクトをリストに追加
                                flow.add("->"); // asの前のオブジェクトをリストに追加
                                flow.add("DBアクセス"); // asの前のオブジェクトをリストに追加
                                String dbNameLine = readPlantUml.getDbName("docs/sequence/user/create.pu"); // "database"が存在するlineを取得する
                                String[] dbNames = dbNameLine.split(" +"); // 空白で区切る
                                db = dbNames[1]; // DB名
                                table = dbNames[3]; // テーブル
                                break loop;
                            } else if (splitsMethods[2].equals(splitsObjects[i + 1])) { // 先頭にオブジェクトがあるもの{
                                if (method.contains(":")) {
                                    String[] twoSplitsMethods = method.split(":"); // ":"で2つに分ける
                                    flow.add(splitsObjects[i - 1]); // asの前のオブジェクトをリストに追加
                                    flow.add("<-"); // 左矢印方向を格納
                                    flow.add(twoSplitsMethods[1]); // : の後ろのメソッドを取得してリストに格納
                                    break loop; // 次のラインを比較する
                                } else {
                                    flow.add(splitsObjects[i - 1]); // asの前のオブジェクトをリストに追加
                                    flow.add("<-"); // 左矢印方向を格納
                                    flow.add("メソッド_引数なし");
                                    break loop;
                                }
                            }
                        }
                    }
                }
            }
        }

        // view -> daoまでを確認
        // jspに表示されるアノテーション
        List<String> anotation = new ArrayList<String>();
        String[] flowList = new String[flow.size()];
        int i = 0;
        // jspが呼ばれた回数
        int jspCount = -1;
        // 直前にjspが呼ばれたのかを判断する
        int preJsp = 0;
        // リスト → 配列に格納
        for (String pre : flow) {
            flowList[i] = pre;
            System.out.println(pre);
            i = i + 1;
        }

        // 配列に格納したものを順に取り出しソースコードと比較する
        for (i = 0; i < flowList.length - 1; i = i + 3) {
            if (i == flowList.length - 1) {
            } else {
                System.out.println(i + ":番目");
                if (flowList[i].contains(".jsp")) { // jspが呼ばれている場合
                    anotation.add(readPlantUml.searchJsp(flowList[i], "users", flowList[i + 2]));
                    preJsp++;
                    // jspが呼ばれた回数(anotationの数)を1増やす
                    jspCount++;
                } else if (preJsp == 1) {// 直前にjspが呼ばれている場合
                    String checkAnotation = anotation.get(jspCount);
                    System.out.println("オブジェクト" + flowList[i]);
                    System.out.println("メソッド" + flowList[i + 2]);
                    readPlantUml.searchJavaonJsp(flowList[i], "controller/users", flowList[i + 2], checkAnotation);
                    // 直前がjspでなくなるので0に変更する
                    preJsp = 0;
                } else if(flowList[i].contains("Service")){ // 直前にはjavaのコードが呼ばれてい場合
                    System.out.println("オブジェクト" + flowList[i]);
                    System.out.println("メソッド" + flowList[i + 2]);
                    readPlantUml.searchJava(flowList[i], "service", flowList[i + 2]);
                } else {
                    System.out.println("オブジェクト" + flowList[i]);
                    System.out.println("メソッド" + flowList[i + 2]);
                    readPlantUml.searchJava(flowList[i], "dao", flowList[i + 2]);
                }
            }
        }
    }
}
