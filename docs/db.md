## データベース
### データベース名:sample_gradle

## 制約
テーブル名を記述するときは, ### [テーブル名]で記述する.
<font color="Red">※また[テーブル名]の後ろに空白を2つ必ず入れる.</font>

#### テーブル

### users  

|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|1|id|int|11|Y|NO| AutoIncrement,Unique|カテゴリのID|なし|
|2|name|varchar|64|なし|NO|Unique|ユーザ名|なし|
|3|pass|varchar|64|なし|NO|なし|パスワード|なし|

### test_reports

|No.|項目名|型|長さ|主キー|NULL|制約|概要|備考|
|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|:--:|
|1|id|int|11|Y|NO| AutoIncrement,Unique|カテゴリのID|なし|
|2|test_name|varchar|255|なし|NO|Unique|ユーザ名|なし|
|3|duration|varchar|255|なし|NO|Unique|ユーザ名|なし|
|4|result|varchar|255|なし|NO|なし|パスワード|なし|
|5|created_at|dateTime||なし|NO|なし|作成時間|なし|
