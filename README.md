### 目的
jsp & サーブレットでGradleプロジェクトの テンプレ

### 開発環境
+ java  1.8.0
+ Gradle 4.10.2
+ Groovy 2.4.15
+ Ant Apache Ant(TM) version 1.9.11 compiled on March 23 2018
+ Apach Tomcat 9.0
+ UIkit 3.0.2
+ Jenkins 2.156
+ mysql 5.7.0

### ローカルのURL
[http://localhost:8080/Sample/](http://localhost:8080/Sample/)

### Jenkins(ローカル)の設定
1. #### Jenkinsのインストール(homebrew)
 - brewコマンドでインストールする.`brew install jenkins`
 - Jenkinsの起動は`brew services start jenkins`
 - 起動したらアクセス [http://localhost:8080/](http://localhost:8080/)
2. #### Jenkinsの設定
 - Jenkinsを起動したらアカウントの設定をする
3. #### ポート番号を変更  
ローカルでJenkinsを起動するときに, プロジェクトを8080ポートで起動したいので違う番号(自分は51111)に変更する.
 - `cd /usr/local/Cellar/jenkins/2.156`で移動する.
 - 中に**homebrew.mxcl.jenkins.plist**があるはずなのでそれを編集する(なかったら探す).
 - viとかで開いて`<string>--httpPort=8080</string>` → `<string>--httpPort=51111</string>`に変更する.
 - Jenkinsの方でJenkinsの管理 > システムの管理 > Jenkinsの位置を下記のように書き換え保存(再起動したほうがいいかも)

 <img width="928" alt="2019-01-22 13 06 49" src="https://user-images.githubusercontent.com/38200453/51511922-a10c6980-1e46-11e9-83a3-3b1b800290ff.png">

4. #### 色々なプラグイン導入
あとで書く

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
#### DockerでMysqlのコンテナを立ち上げる
+ `cd mysql`で移動.
+ `docker-compose up -d`でコンテナを立ち上げる.
+ `docker ps`で起動を確認する(portは3333番).

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

