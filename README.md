### 目的
jsp & サーブレットでGradleプロジェクトの テンプレ

### 必ずすること
docs配下に  
`docs/match/macth.text`を作成する(中身は空でOK).

### 開発環境
+ java  1.8.0
+ Gradle 5.2.1
+ Groovy 2.4.15
+ Apach Tomcat 9.0  
+ UIkit 3.0.2
+ mysql 5.7.0

### ローカルのURL
[http://localhost:8080/Sample/](http://localhost:8080/Sample/)

### Gradleプロジェクトの作成手順

1. #### Gradleプロジェクトの作成
 - ファイル > 新規 > プロジェクトで  
Gradle > Gradleプロジェクトを選択しプロジェクト名を記入し作成する.

2. #### プロジェクトファセットの設定
 - プロジェクトを選択して右クリック > プロパティ > プロジェクト・ファセット > ファセットホームへ変換」をクリックする.  
 - 変換したら色々チェックボックスがでる「動的WEBモジュール」にチェックを入れる. 「より詳しい構成が使用可能」と下にでるのでそれをクリックする.   
 - GradleのWebapp構成に合わせるためにコンテンツ・ディレクトリーの部分を以下のパスに変える.    
 `src/main/webapp`に変更.
 - 「動的WEBモジュール」の右側にあるランタイムタブで使用するランタイムにチェック入れて適応する.

### DB
### DockerでMysqlのコンテナを立ち上げる
+ `cd mysql`で移動.
+ `docker-compose up -d`でコンテナを立ち上げる.
+ `docker ps`で起動を確認する(portは3399番).

### gradleでtomcatを起動
+ tomcatの起動時にwarの更新をしてtomcatを起動する

`./gradlew tRW`

### gradleのテスト結果をDBに格納する手順

+ `gradle flywayInfo`でマイグレーションのの状態を確認する.(stateの状態を確認)
+ `gradle test`でテストの実行をする
+ `gradle getLogAll`でテスト結果をマイグレーションファイルにする.
+ `gradle flywayInfo`でマイグレーションファイルが増えているのかを確認する(state:Pending)
+ `gradle flywayMigrate`でマイグレートする.
+ `gradle flywayInfo`でマイグレーションのの状態を確認する.(state:Success)
+ DBに格納されていることを確認する

下記にDB格納後を示す. 
<img width="789" alt="スクリーンショット 2019-04-09 13 13 18" src="https://user-images.githubusercontent.com/38200453/55772922-53a8ab80-5ac9-11e9-8150-2fbd217590c2.png">

### buildScan

`./gradlew build --scan`を実行

- 実行時に出てきたURLにアクセスする  
- 最初のアクセス時にはメールの認証が必要

公式ドキュメント  
https://docs.gradle.com/build-scan-plugin/

