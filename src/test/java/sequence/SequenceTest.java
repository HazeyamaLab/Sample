package sequence;

import org.junit.Test;
import utility.ReadPlantUml;
import utility.Sequence;

import java.util.List;

public class SequenceTest {
    @Test
    public void ユーザ作成のシーケンス図の一貫性(){
        //ファイルの読み込み
        //返り値にオブジェクト, メソッド名, 引数(, DB名, テーブル名)を取得する(引数は確認をしたいシーケンス図のパス)
        // インスタンス化
        ReadPlantUml readPlantUml = new ReadPlantUml();

        //オブジェクトを取得
        List<String> objects = readPlantUml.getObjects("docs/sequence/user/create.pu");
        for(String object : objects){
            System.out.println(object);
        }

        //メソッド名を取得
        List<String> methods = readPlantUml.getMethods("docs/sequence/user/create.pu");
        for(String method : methods){
            System.out.println(method);
        }

        //DB名, テーブル名の取得
        String dbName = readPlantUml.getDbName("docs/sequence/user/create.pu");
        System.out.println(dbName);

        //取得したオブジェクトをディレクトリから探す インスタンス化 実行
        String object = "UserService";

        Sequence sequence = new Sequence();
        String dir = sequence.searchDir(object);

    }
}

