package utility;

import java.io.File;

public class Sequence {

    public String searchDir(String object) {
        //返り値の初期化
        String dirc = null;
        String path = new File(".").getAbsoluteFile().getParent() + "/src/main/java/service" + object;
        File dir = new File(path);

        //フルパスで取得
        File[] files1 = dir.listFiles();
        for (int i = 0; i < files1.length; i++) {
            File file = files1[i];
            if (files1[i].isFile()) {
                //ファイル名表示
                System.out.println(file);
            } else if (files1[i].isDirectory()) {
                //ディレクトリ名表示(※１)
                System.out.println(file);
            }
        }
        return dirc;
    }
}
