package GradleMain;

public class GradleMainTest {

    static final String TEST_APPENDER_NAME = "fortest";

//    @Test
//    public void testDoSomething() throws Exception {
//        StringWriter writer = new StringWriter();
//        //現在のロガーに、writerを持つappenderを追加する。
//        addAppender(writer, TEST_APPENDER_NAME);
//
//        // テスト実行して、writerに流れてきている文字列を検証する。
//        GradleMain sut = new GradleMain();
//        sut.doSomething("log");
//        assertThat(writer.toString(), is("message ->log" + System.lineSeparator()));
//
//        // 追加したappenderを削除する。writerの中身も消す。
//        removeAppender(TEST_APPENDER_NAME);
//        writer.getBuffer().delete(0, writer.getBuffer().length());
//
//        // もう一度テスト実行して、writerに流れてきている文字列を検証する。今度は空である。
//        sut = new GradleMain();
//        sut.doSomething("logFinish");
//        assertTrue(writer.toString().length() == 0);
//    }
//
//    void addAppender(Writer writer, String name) {
//        final LoggerContext context = LoggerContext.getContext(false);
//        final Configuration config = context.getConfiguration();
//        final PatternLayout layout = PatternLayout.createDefaultLayout(config);
//
//        Appender appender = WriterAppender.createAppender(layout, null, writer, name, false, true);
//        appender.start();
//        config.addAppender(appender);
//        updateLoggers(appender, config);
//    }
//
//    private void updateLoggers(final Appender appender, final Configuration config) {
//        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
//            loggerConfig.addAppender(appender, null, null);
//        }
//        config.getRootLogger().addAppender(appender, null, null);
//    }
//
//    private void removeAppender(String name) {
//        final LoggerContext context = LoggerContext.getContext(false);
//        final Configuration config = context.getConfiguration();
//        for (final LoggerConfig loggerConfig : config.getLoggers().values()) {
//            loggerConfig.removeAppender(name);
//        }
//        config.getRootLogger().removeAppender(name);
//    }
}
