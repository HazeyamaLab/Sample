
package sample;

import org.junit.Test;

import utility.Sample;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

//開発時にはここをコメントアウトする。
public class SampleTest {
    @Test
    public void maltiplicationで5と2の乗算結果が取得できる() {
        Sample sut = new Sample();
        int expected = 10;
        int actual = sut.maltiplication(5, 3);
        assertThat(actual, is(expected));
        System.out.println("正常な動作をしました。");
    }

    @Test
    public void divisionで5と8の除算結果が取得できる() throws Exception {
        Sample sut = new Sample();
        float expected = 0.625f;
        float actual = sut.division(5, 8);
        assertThat(actual, is(expected));
        System.out.println("正常な動作をしました。");
    }

    @Test
    public void 文字列と数字をくっつける() throws Exception {
        Sample sut = new Sample();
        String expected = "ゼロです";
        String actual = sut.plus(1);
        assertThat(actual, is(expected));
        System.out.println("正常な動作をしました。");
    }
}