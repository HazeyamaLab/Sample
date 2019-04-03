package dao;

import model.Mysql;
import org.junit.Test;
import utility.ReadMarkdown;

import static org.junit.Assert.assertNotNull;

public class UsersDaoTest extends UsersDao {

    @Test
    public void 上流の成果物に特定のtableに特定のカラムがあるか_login() {
        //評価するsql文の取得
        String sql = login;
        Mysql mysql = new Mysql();
        String tableName = mysql.searchTable(sql);
        ReadMarkdown readMarkdown = new ReadMarkdown();
        //match.txtに確かめたいテーブルを上書きする
        readMarkdown.readTable("docs/db.md", "docs/match/match.txt", tableName);

        String[] columns = mysql.searchColumn(sql);
        String conf = readMarkdown.readFile("docs/match/match.txt", columns); //confはTrue or False
        assertNotNull(conf);

    }

    @Test
    public void 上流の成果物に特定のtableに特定のカラムがあるか_create() {
        //評価するsql文の取得
        String sql = create;
        Mysql mysql = new Mysql();
        String tableName = mysql.searchTable(sql);
        ReadMarkdown readMarkdown = new ReadMarkdown();
        //match.txtに確かめたいテーブルを上書きする
        readMarkdown.readTable("docs/db.md", "docs/match/match.txt", tableName);

        String[] columns = mysql.searchColumn(sql);
        String conf = readMarkdown.readFile("docs/match/match.txt", columns); //confはTrue or False
        assertNotNull(conf);
    }

    @Test
    public void 上流の成果物に特定のtableに特定のカラムがあるか_update() {
        //評価するsql文の取得
        String sql = update;
        Mysql mysql = new Mysql();
        String tableName = mysql.searchTable(sql);
        ReadMarkdown readMarkdown = new ReadMarkdown();
        //match.txtに確かめたいテーブルを上書きする
        readMarkdown.readTable("docs/db.md", "docs/match/match.txt", tableName);

        String[] columns = mysql.searchColumn(sql);
        String conf = readMarkdown.readFile("docs/match/match.txt", columns); //confはTrue or False
        assertNotNull(conf);
    }

    @Test
    public void 上流の成果物に特定のtableに特定のカラムがあるか_delete() {
        //評価するsql文の取得
        String sql = delete;
        Mysql mysql = new Mysql();
        String tableName = mysql.searchTable(sql);
        ReadMarkdown readMarkdown = new ReadMarkdown();
        //match.txtに確かめたいテーブルを上書きする
        readMarkdown.readTable("docs/db.md", "docs/match/match.txt", tableName);

        String[] columns = mysql.searchColumn(sql);
        String conf = readMarkdown.readFile("docs/match/match.txt", columns); //confはTrue or False
        assertNotNull(conf);

    }

    @Test
    public void 上流の成果物に特定のtableに特定のカラムがあるか_getById() {
        //評価するsql文の取得
        String sql = getById;
        Mysql mysql = new Mysql();
        String tableName = mysql.searchTable(sql);
        ReadMarkdown readMarkdown = new ReadMarkdown();
        //match.txtに確かめたいテーブルを上書きする
        readMarkdown.readTable("docs/db.md", "docs/match/match.txt", tableName);

        String[] columns = mysql.searchColumn(sql);
        String conf = readMarkdown.readFile("docs/match/match.txt", columns); //confはTrue or False
        assertNotNull(conf);
    }

}
