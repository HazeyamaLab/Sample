### リモートビルド時にのみ増えるマイグレートファイル
<string>migrationの所には.sql以外に入れない</string>
### 使い方
1. testの実行
2. gradle getLog(gL)タスクで, マイグレートのファイルをhtmlのファイルから生成する.  
3. gradle flywayMigrateを実行し, DBに格納する.

1. `gradle flywayInfo`で現在のVersionを確認する.  
2. `gradle flyway migrate`でマイグレートする.  
3. Versionが一つ増えたものが作成される.

###
Flyway
Flywayは, オープンソースのデータベースマイグレーションツール.  
Flywayを使うことで, データベースの状態をバージョン管理できるようになる.  
#### 説明
Flywayでは, データベースに適用する各SQLファイルをバージョンごとに作成して, 管理する.  
ファイル名は書式が決められており, バージョン番号から始まる形で指定する.  
バージョン番号は一意である必要があり, 同じバージョンのSQLファイルを用意することはできない.  
`flyway migrate`コマンドで, マイグレーションが実行され, まだデータベースに適用されていないバージョンのSQLが適用される.  
既に適用済みにSQLは, Flywayが判断して適用しないようにしてくれる.  

これによって、データベースのバージョン管理・マイグレーションがスムーズにできるようになる。
#### 書式

`V<Version>__<Description>.sql`

###  仕様
+ `src/main/resources/db/migration`配下のtest_reportsへのInsert文のsqlが蓄積される

+ V1__initial_DB.sqlは最初にtableを作るためのファイル
